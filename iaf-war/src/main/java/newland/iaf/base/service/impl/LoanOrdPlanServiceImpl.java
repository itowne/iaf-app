package newland.iaf.base.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import newland.base.util.BeanUtils;
import newland.iaf.base.dao.LoanOrdPlanDao;
import newland.iaf.base.dao.LoanOrdPlanHisDao;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.LoanOrdPlanHis;
import newland.iaf.base.model.condition.LoanOrdPlanCondition;
import newland.iaf.base.service.LoanOrdPlanService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
@Service("loanOrdPlanService")
@Transactional
public class LoanOrdPlanServiceImpl implements LoanOrdPlanService{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "loanOrdPlanDao")
	private LoanOrdPlanDao loanOrdPlanDao;
	@Resource (name = "loanOrdPlanHisDao")
	private LoanOrdPlanHisDao loanOrdPlanHisDao;

	@Override
	public void saveLoanOrdPlan(List<LoanOrdPlan> plans) throws Exception {
		for (LoanOrdPlan plan : plans){
			plan.setPreStat(plan.getStat());
			this.loanOrdPlanDao.save(plan);
		}
	}
	
	@Override
	public int uploadLoanOrdPlan(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception {
		if (CollectionUtils.isEmpty(plans)) throw new Exception("上传列表为空！");
		List<LoanOrdPlan> hist = this.queryPlanById(loanOrd.getIloanOrd());
		BigDecimal quota = BigDecimal.ZERO;
		int success = 0;
		if (CollectionUtils.isEmpty(hist)){
			for (LoanOrdPlan plan : plans){
				setNewPlan(loanOrd, plan);
				this.loanOrdPlanDao.save(plan);
			}
			hist = plans;
		}else{
			for (Iterator<LoanOrdPlan> it = hist.iterator() ; it.hasNext() ;){
				LoanOrdPlan his  = it.next();
				if (his.getStat() == PlanStat.PAID_UP_LOAN || 
						his.getStat() == PlanStat.BALANCE_FREEZE ||
								his.getStat() == PlanStat.FREEZING ||
										his.getStat() == PlanStat.THAW_APPLY){
					continue;
				}else{
					it.remove();
					moveToHistory(his);
					delete(his);
				}
			}
			for (LoanOrdPlan plan : plans){
				boolean add = true;
				for (LoanOrdPlan old: hist){
					if (plan.getPeriod().intValue() == old.getPeriod().intValue()){
						add = false;
						break;
					}
				}
				if (add){
					setNewPlan(loanOrd, plan);
					this.loanOrdPlanDao.save(plan);
					hist.add(plan);
				}
			}
		}
		BigDecimal total = BigDecimal.ZERO;
		for (LoanOrdPlan plan: hist){
			success++;
			quota = quota.add(plan.getCapital()).setScale(2, BigDecimal.ROUND_HALF_UP);
			total = total.add(plan.getRepayment()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		BigDecimal repayment = (loanOrd.getRepayment()==null)?BigDecimal.ZERO:loanOrd.getRepayment();
		loanOrd.setRemainPayment(total.subtract(repayment));
		Integer recive = (loanOrd.getRecivePeriod()==null)?new Integer(0):loanOrd.getRecivePeriod();
		loanOrd.setRemainPeriod(success - recive.intValue());
		loanOrd.setCurtPayment(hist.get(0).getRepayment());
		logger.info("订单号：" + loanOrd.getLoanOrdId() + " 已还款额：" + loanOrd.getRepayment() + " 待还款金额：" + loanOrd.getRemainPayment() + " 已还款期数：" + loanOrd.getRecivePeriod() + " 待还款期数 ：" + loanOrd.getRemainPeriod());
		logger.info("订单号：" + loanOrd.getLoanOrdId() + " 还款本金：" + quota.toString() + " 贷款金额：" + loanOrd.getQuota().intValue());
		if (quota.intValue() != loanOrd.getQuota().intValue()) {
			for (LoanOrdPlan plan: plans){
				plan.setIloanOrdPlan(null);
			}
			logger.error("还款总本金与贷款金额金额不符！");
			throw new Exception("还款总本金与贷款金额金额不符！");
		}
		return success;
	}

	private void setNewPlan(LoanOrd loanOrd, LoanOrdPlan plan) throws Exception {
		if (plan.getRepayment().intValue() < plan.getCapital().intValue()){
			throw new Exception("还款金额不能小于本金");
		}
		plan.setIloanOrd(loanOrd.getIloanOrd());
		plan.setLoanOrdId(loanOrd.getLoanOrdId());
		plan.setUpdTime(new Date());
		plan.setPreStat(PlanStat.BALANCE);
		plan.setStat(PlanStat.BALANCE);
		plan.setImerch(loanOrd.getImerch());
		plan.setIinst(loanOrd.getIinst());
		plan.setGenTime(new Date());
	}

	private void delete(LoanOrdPlan plan) {
		if (plan.getStat() != PlanStat.PAID_UP_LOAN)
			this.loanOrdPlanDao.delete(plan);
		
	}

	private void moveToHistory(LoanOrdPlan plan) {
		LoanOrdPlanHis his = new LoanOrdPlanHis();
		try {
			BeanUtils.copyNoNullProperties(his, plan);
		} catch (IllegalAccessException e) {
			logger.error("复制计划对象出错", e);
		} catch (InvocationTargetException e) {
			logger.error("复制计划对象出错", e);
		}
		this.loanOrdPlanHisDao.save(his);
		
	}

	@Override
	public List<LoanOrdPlan> queryDelinQuencyPlan(Long iLoanOrd)
			throws Exception {
		LoanOrdPlan p = new LoanOrdPlan();
		p.setIloanOrd(iLoanOrd);
		p.setStat(PlanStat.DELIN_QUENCY);
		return this.loanOrdPlanDao.query(p);
	}

	@Override
	public void delinQuencyPlan(LoanOrdPlan plan) throws Exception {
		if (plan.getStat() == PlanStat.BALANCE){
			plan.setPreStat(plan.getStat());
			plan.setStat(PlanStat.DELIN_QUENCY);
			plan.setUpdTime(new Date());
			this.loanOrdPlanDao.update(plan);
		}
		
	}

	@Override
	public void refundPlan(LoanOrdPlan plan,String memo) throws Exception {
		if (plan.getStat() == PlanStat.BALANCE || plan.getStat() == PlanStat.DELIN_QUENCY){
			plan.setPreStat(plan.getStat());
			plan.setMemo(memo);
			plan.setStat(PlanStat.PAID_UP_LOAN);
			plan.setUpdTime(new Date());
			this.loanOrdPlanDao.update(plan);
			return ;
		}
		logger.info("订单号：" + plan.getLoanOrdId() + "当前计划期号：" + plan.getPeriod() + " 计划状态：" + plan.getStat().getDesc());
		throw new Exception("还款期号：" + plan.getPeriod() + " 已成功还款!");
	}

	@Override
	public void freezePlan(LoanOrdPlan plan) throws Exception {
		if (plan.getStat() == PlanStat.DELIN_QUENCY||plan.getStat() == PlanStat.BALANCE){
			plan.setPreStat(plan.getStat());
			plan.setStat(PlanStat.FREEZING);
			plan.setUpdTime(new Date());
			this.loanOrdPlanDao.update(plan);
			return ;
		}
		logger.info("订单号：" + plan.getLoanOrdId() + "当前计划期号：" + plan.getPeriod() + " 计划状态：" + plan.getStat().getDesc());
		throw new Exception("计划状态不正确！");
	}

	@Override
	public void thawPlan(LoanOrdPlan plan) throws Exception {
		if (plan.getStat() == PlanStat.THAW_APPLY){
			plan.setPreStat(plan.getStat());
			plan.setStat(PlanStat.PAID_UP_LOAN);
			plan.setUpdTime(new Date());
			this.loanOrdPlanDao.update(plan);
			return ;
		}
		logger.info("订单号：" + plan.getLoanOrdId() + "当前计划期号：" + plan.getPeriod() + " 计划状态：" + plan.getStat().getDesc());
		throw new Exception("计划状态不正确");
	}

	@Override
	public List<LoanOrdPlan> queryPlanById(Long iloanOrd) throws Exception {
		LoanOrdPlanCondition cond = new LoanOrdPlanCondition();
		cond.setIloanOrd(iloanOrd);
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("period"));
		cond.setOrders(orders);
		return this.loanOrdPlanDao.queryBy(cond);
	}

	@Override
	public LoanOrdPlan queryByPlanId(Long iLoanOrdPlan) {
		return this.loanOrdPlanDao.queryById(iLoanOrdPlan);
	}

	@Override
	public void update(LoanOrdPlan plan) {
		plan.setUpdTime(new Date());
		this.loanOrdPlanDao.update(plan);
		
	}

	@Override
	public List<LoanOrdPlan> queryBy(LoanOrdPlan plan) {
		return this.loanOrdPlanDao.query(plan);
	}

	@Override
	public LoanOrdPlan queryBy(Long iloanOrd, Integer period) {
		LoanOrdPlan plan = new LoanOrdPlan();
		plan.setIloanOrd(iloanOrd);
		plan.setPeriod(period);
		List<LoanOrdPlan> list = this.loanOrdPlanDao.query(plan);
		if (CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

	@Override
	public boolean isPaidUp(Long iloanOrd) {
		LoanOrdPlanCondition cond = new LoanOrdPlanCondition();
		cond.setIloanOrd(iloanOrd);
		cond.setLtstat(PlanStat.PAID_UP_LOAN);
		List<LoanOrdPlan> list = this.loanOrdPlanDao.queryBy(cond);
		if (CollectionUtils.isEmpty(list)) return true;
		return false;
	}

	@Override
	public List<LoanOrdPlan> queryByLoanOrdId(String loanOrdId) {
		LoanOrdPlan pop = new LoanOrdPlan();
		pop.setLoanOrdId(loanOrdId);
		return this.loanOrdPlanDao.queryByLoanOrdId(loanOrdId);
	}

	@Override
	public List<LoanOrdPlan> queryByIinst(Long id) {
		return loanOrdPlanDao.queryByIinst(id);
	}

	@Override
	public List<LoanOrdPlan> queryByImerch(Long id) {
		// TODO Auto-generated method stub
		return loanOrdPlanDao.queryByImerch(id);
	}

	@Override
	public List<LoanOrdPlan> queryByCond(LoanOrdPlanCondition cond, Page page) {
//		List<Order> orderList = new ArrayList<Order>();
//		orderList.add(Order.desc("iloanOrdPlan"));
//		cond.setOrders(orderList);
		List<LoanOrdPlan> plans = loanOrdPlanDao.queryByCond(cond, page);
		return plans;
	}

}
