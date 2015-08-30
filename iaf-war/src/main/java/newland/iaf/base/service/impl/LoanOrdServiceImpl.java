package newland.iaf.base.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.IafApplication;
import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.LoanOrdDao;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.SysParamService;
import newland.iaf.notice.annotation.MethodTrigger;
import newland.iaf.utils.DateUtils;
import newland.iaf.utils.service.SerialNoService;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.ohuyo.commons.query.criterion.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("loanOrdService")
public class LoanOrdServiceImpl implements LoanOrdService {
	/**
	 * 贷款处理期限
	 */
	public static final int EXPIRE_DATE = 15;

	@Resource(name = "loanOrdDao")
	private LoanOrdDao loanOrdDao;
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	@Resource (name = "serialNoService")
	private SerialNoService serialNoService;
	@Resource (name = "fundFlowService")
	private FundFlowService fundFlowService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;
	/**
	 * 更新定单
	 */
	@Override
	@Transactional
	public void updateLoanOrd(LoanOrd loanOrd) throws Exception {
		this.loanOrdDao.updateLoanOrd(loanOrd);
		logger.info("更新订单，订单号：" + loanOrd.getIloanOrd());
		
	}
	/**
	 * 按订单编号查找
	 */
	@Override
	@Transactional
	public LoanOrd findLoanOrdById(Long id) {
		return this.loanOrdDao.findLoanOrdById(id);
	}
	
	/**
	 * 查询所有订单
	 */
	@Override
	public List<LoanOrd> queryLoanOrd() throws Exception {
		return this.loanOrdDao.queryLoanOrd();
	}


	/**
	 * 受理订单
	 */
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public void acceptOrd(LoanOrd loanOrd) throws Exception {
		if (loanOrd.getOrdStat() == OrdStat.APPLY){
			if (isExpire(loanOrd)){
				this.expireOrd(loanOrd);
				throw new Exception("订单已过期");
			}
			loanOrd.setOrdStat(OrdStat.ACCEPT);
			
			Map<SysParamName, SysParam> map = this.sysParamService.findSysParamMapByType(SysParamType.expireDate);
			try{
				Date now  = new Date();
				String sysParamValue = map.get(SysParamName.EXPIRE_DATE).getValue();
				Date expireDate = DateUtils.addDays(now, Integer.parseInt(sysParamValue));
				loanOrd.setExpiryDate(DateUtils.roundDate(expireDate, Calendar.DATE));
			}catch(Exception e){
				throw new Exception("系统参数获取异常："+e.getMessage());
			}
			loanOrd.setAcceptDate(IafApplication.roundToBeginDate(new Date()));
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.info("受理订单，订单号：" + loanOrd.getLoanOrdId());
			return ;
		}
		throw new Exception("该订单状态不正确，受理不成功");
	}
	@Override
	public boolean isExpire(LoanOrd loanOrd){
		/**
		 * 定单未审核通过超期才可失效
		 */
		if (loanOrd.getOrdStat().ordinal() < OrdStat.AUDIT.ordinal()){
			Date start = loanOrd.getExpiryDate();
			Date end = new Date();
			int i = DateUtils.compareDays(start, end);
			if (i > 0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	/**
	 * 订单过期
	 */
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public void expireOrd(LoanOrd loanOrd) throws Exception {
		/**
		 * 定单未审核通过超期才可失效
		 */
		if (this.isExpire(loanOrd)){
			if (loanOrd.getOrdStat() == OrdStat.APPLY)
				loanOrd.setOrdStat(OrdStat.APPLY_OVERDUE);
			if (loanOrd.getOrdStat() == OrdStat.ACCEPT)
				loanOrd.setOrdStat(OrdStat.ACCEPT_OVERDUE);
			this.loanOrdDao.expire(loanOrd);
			logger.info("订单已过期，订单号：[" + loanOrd.getLoanOrdId() + "]");
		}
		
	}
	/**
	 * 拒决订单
	 */
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public OrdStat refuseOrd(LoanOrd loanOrd) throws Exception {
		if (loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()){
			if (this.isExpire(loanOrd)) {
				this.expireOrd(loanOrd);
				throw new Exception("定单已过期");
			}
		
			switch (loanOrd.getOrdStat()){
			case ACCEPT:
				loanOrd.setOrdStat(OrdStat.AUDIT_REFUSE);
				logger.debug("审核未通过，订单号：" + loanOrd.getLoanOrdId());
				break;
			case APPLY:
				loanOrd.setOrdStat(OrdStat.ACCEPT_REFUSE);
				logger.info("机构不受理，订单号：" + loanOrd.getLoanOrdId());
				break;
			
			default: throw new Exception("该订单状态不正确, 处理失败");
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			return loanOrd.getOrdStat();
		}
		throw new Exception("该订单状态不正确, 处理失败");
	}

	/**
	 * 审核订单
	 */
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public void auditOrd(LoanOrd loanOrd) throws Exception {
		if (loanOrd.getOrdStat() == OrdStat.ACCEPT){
			if (this.isExpire(loanOrd)){
				this.expireOrd(loanOrd);
				throw new Exception("定单已过期");
			}
			loanOrd.setOrdStat(OrdStat.AUDIT);
			loanOrd.setCheckDate(IafApplication.roundToBeginDate(new Date()));
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.info("审核通过，订单号：" + loanOrd.getLoanOrdId());
			return ;
		}
		throw new Exception("该订单状态不正确, 处理失败");
	}

	/**
	 * 作废
	 */
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public void cancelOrd(LoanOrd loanOrd) throws Exception {
		if (loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()){
			if (this.isExpire(loanOrd)) this.expireOrd(loanOrd);
			switch (loanOrd.getOrdStat()){
			case APPLY:
			case AUDIT:
			case CREDITING:
			case REFUNDING:
			case REFUND:
			case DELIN_QUENCY:
			case ACCEPT:
				loanOrd.setOrdStat(OrdStat.CANCEL);
				logger.info("订单作废，订单号：" + loanOrd.getLoanOrdId());
				break;
			default: throw new Exception("该订单状态不正确, 处理失败");
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			return ;
		}
		throw new Exception("该订单状态不正确, 处理失败");
		
	}

	/**
	 * 放款
	 */
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public void creditOrd(LoanOrd loanOrd) throws Exception {
		if (loanOrd.getOrdStat() == OrdStat.AUDIT){
			loanOrd.setOrdStat(OrdStat.CREDITING);
			loanOrd.setCreditDate(DateUtils.roundDate(new Date(), Calendar.DATE));
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.info("放款中，订单号：" + loanOrd.getLoanOrdId());
			return ;
		}
		throw new Exception("该订单状态不正确, 处理失败");
	}
	/**
	 * 平台外放款
	 * @param loanOrd
	 * @throws Exception
	 */
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public void othCreditOrd(LoanOrd loanOrd,String memo) throws Exception{
		if (loanOrd.getOrdStat() == OrdStat.AUDIT){
			loanOrd.setOrdStat(OrdStat.REFUND);
			loanOrd.setCreditDate(new Date());
			loanOrd.setMemo(memo);
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.info("平台外放款，订单号：" + loanOrd.getLoanOrdId());
			return ;
		}
		throw new Exception("该订单状态不正确, 处理失败");
	}

	/**
	 * 还款
	 */
	@Override
	public void refundOrd(LoanOrd loanOrd, LoanOrdPlan plan) throws Exception {
//		if (loanOrd.getOrdStat() == OrdStat.REFUND || 
//				loanOrd.getOrdStat() == OrdStat.DELIN_QUENCY){
			this.loanOrdPlanService.refundPlan(plan,null);
			if (loanOrd.getRepayment() == null){
				loanOrd.setRepayment(BigDecimal.ZERO);
			}
			loanOrd.setRepayment(loanOrd.getRepayment().add(plan.getRepayment()));
			if (loanOrd.getRemainPayment() == null){
				loanOrd.setRemainPayment(loanOrd.getQuota());
			}
			loanOrd.setRemainPayment(loanOrd.getRemainPayment().subtract(plan.getRepayment()));
			if (loanOrd.getRecivePeriod() == null){
				loanOrd.setRecivePeriod(new Integer(0));
			}
			loanOrd.setRecivePeriod(loanOrd.getRecivePeriod() + 1);
			if (loanOrd.getRemainPeriod() == null){
				loanOrd.setRemainPeriod(loanOrd.getTerm());
			}
			loanOrd.setRemainPeriod(loanOrd.getRemainPeriod() - 1);
			loanOrd.setOrdStat(OrdStat.REFUNDING);
			loanOrd.setLastRefundDate(DateUtils.roundDate(new Date(), Calendar.DATE));
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.info("还款中，订单号：" + loanOrd.getLoanOrdId());
//		}
//		throw new Exception("该订单状态不正确, 处理失败");
		
	}
	@Override
	public void othRefundOrd(LoanOrd loanOrd, LoanOrdPlan plan,String memo) throws Exception{
		if (loanOrd.getOrdStat() == OrdStat.REFUND || 
				loanOrd.getOrdStat() == OrdStat.DELIN_QUENCY||loanOrd.getOrdStat()==OrdStat.REFUNDING){
			this.loanOrdPlanService.refundPlan(plan,memo);
			if (loanOrd.getRepayment() == null){
				loanOrd.setRepayment(BigDecimal.ZERO);
			}
			loanOrd.setRepayment(loanOrd.getRepayment().add(plan.getRepayment()));
			if (loanOrd.getRemainPayment() == null){
				loanOrd.setRemainPayment(loanOrd.getQuota());
			}
			loanOrd.setRemainPayment(loanOrd.getRemainPayment().subtract(plan.getRepayment()));
			if (loanOrd.getRecivePeriod() == null){
				loanOrd.setRecivePeriod(new Integer(0));
			}
			loanOrd.setRecivePeriod(loanOrd.getRecivePeriod() + 1);
			if (loanOrd.getRemainPeriod() == null){
				loanOrd.setRemainPeriod(loanOrd.getTerm());
			}
			loanOrd.setRemainPeriod(loanOrd.getRemainPeriod() - 1);
			List<LoanOrdPlan> list = this.loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
			loanOrd.setOrdStat(OrdStat.REFUND);
			int count = 0;
				if(!CollectionUtils.isEmpty(list)){
					for (LoanOrdPlan loanOrdPlan : list) {
						FundFlow ff = fundFlowService.queryByIloanOrdPlan(loanOrdPlan.getIlanOrdPlan());
						if(ff==null){
							break;
						}
						if(ff.getStatus()!=FundFlowStat.SUCCESS&&loanOrdPlan.getStat()!=PlanStat.PAID_UP_LOAN){
							break;
					}else{
						count++;
					}
				}
					if(count==list.size()){
						loanOrd.setOrdStat(OrdStat.PAID_UP_LOAN);
					}
			}
//			if (this.haveProcFundFlow(loanOrd, FundFlowType.REFUND)){
//				loanOrd.setOrdStat(OrdStat.REFUNDING);
//			}else{
//				if (CollectionUtils.isEmpty(list)){
//					loanOrd.setOrdStat(OrdStat.REFUND);
//				}else{
//					loanOrd.setOrdStat(OrdStat.DELIN_QUENCY);
//				}
//			}
			loanOrd.setUpdTime(new Date());
			loanOrd.setLastRefundDate(new Date());
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.info("平台外还款，订单号：" + loanOrd.getLoanOrdId());
			return ;
		}
		throw new Exception("该订单状态不正确, 处理失败");
	}
	/**
	 * 审核放款情况
	 */
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public void auditCredit(LoanOrd loanOrd) throws Exception {
		if (loanOrd.getOrdStat() == OrdStat.CREDITING){
			loanOrd.setOrdStat(OrdStat.REFUND);
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.debug("放款已到账，订单号：" + loanOrd.getLoanOrdId());
			return ;
		}
		throw new Exception("该订单状态不正确, 处理失败");
		
	}
	
	/**
	 * 审核还款情况
	 */
	@Override
	public void auditRefund(LoanOrd loanOrd) throws Exception {
		if (loanOrd.getOrdStat() == OrdStat.REFUNDING){
			List<LoanOrdPlan> list = this.loanOrdPlanService.queryDelinQuencyPlan(loanOrd.getIloanOrd());
			if (this.haveProcFundFlow(loanOrd, FundFlowType.REFUND)){
				loanOrd.setOrdStat(OrdStat.REFUNDING);
			}else{
				if (CollectionUtils.isEmpty(list)){
					boolean paidup = this.loanOrdPlanService.isPaidUp(loanOrd.getIloanOrd());
					if (loanOrd.getRemainPeriod().intValue() == 0 && paidup){
						loanOrd.setOrdStat(OrdStat.PAID_UP_LOAN);
						logger.info("已全额还款！订单号：" + loanOrd.getLoanOrdId());
					}else{
						loanOrd.setOrdStat(OrdStat.REFUND);
					}
				}else{
					loanOrd.setOrdStat(OrdStat.DELIN_QUENCY);
				}
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.debug("还款已到账，订单号：" + loanOrd.getLoanOrdId());
			return ;
		}
		throw new Exception("该订单状态不正确, 处理失败");
	}
	
	private boolean haveProcFundFlow(LoanOrd loanOrd, FundFlowType type){
		return this.fundFlowService.haveFundFlow(loanOrd.getIloanOrd(), type);
	}
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public LoanOrd applyOrdBy(LoanOrd ord) throws Exception{
		ord.setOrdStat(OrdStat.APPLY);
		String str = this.serialNoService.genOrdNo();
		ord.setLoanOrdId(this.serialNoService.genOrdNo());
		//ord.setExpiryDate(this.rollToNextCircle(IafApplication.roundToEndDate(new Date())));
		this.loanOrdDao.saveLoanOrd(ord);
		logger.debug("提交贷款申请订单，订单号：" + ord.getLoanOrdId());
		return ord;
	}
	
	private Date rollToNextCircle(Date date){
		return DateUtils.rollDate(date, Calendar.DATE, EXPIRE_DATE);
	}
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public void merchCancel(LoanOrd loanOrd) throws Exception {
		if (this.isExpire(loanOrd)) {
			this.expireOrd(loanOrd);
			throw new Exception("定单已过期");
		}
		//if (loanOrd.getOrdStat() == OrdStat.ACCEPT){
			loanOrd.setOrdStat(OrdStat.CANCEL);
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.debug("商户撤销一笔订单，订单号：" + loanOrd.getLoanOrdId());
		//}
		//throw new Exception("该订单状态不正确, 处理失败");
		
	}
	
	@Override
	public LoanOrdPlan queryPlanById(Long iLoanOrdPlan) {
		return this.loanOrdPlanService.queryByPlanId(iLoanOrdPlan);
	}
	@Override
	public List<LoanOrdPlan> queryPlan(Long iLoanOrd) throws Exception {
		return this.loanOrdPlanService.queryPlanById(iLoanOrd);
	}
	@Override
	public void uploadPlan(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception {
		if (loanOrd.getOrdStat().ordinal() < OrdStat.AUDIT.ordinal())
			throw new Exception("定单状态不正确");
		logger.info("上传还款计划，订单号：" + loanOrd.getLoanOrdId());
		int count = this.loanOrdPlanService.uploadLoanOrdPlan(loanOrd, plans);
		//loanOrd.setTerm(count);
		this.loanOrdDao.updateLoanOrd(loanOrd);
	}
	@Override
	public FreezeResult freezeOrd(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception {
		if (loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()
				&& loanOrd.getOrdStat().ordinal() > OrdStat.AUDIT.ordinal()){
			String planPeriods = "";
			BigDecimal amount = BigDecimal.ZERO;
			for (LoanOrdPlan plan : plans){
				if (plan.getStat() == PlanStat.DELIN_QUENCY||plan.getStat() == PlanStat.BALANCE){
					this.loanOrdPlanService.freezePlan(plan);
					planPeriods += plan.getPeriod() + ",";
					amount = amount.add(plan.getRepayment());
				}else{
					throw new Exception("计划状态不正确！");
				}
			}
			if (StringUtils.isBlank(planPeriods)) throw new Exception("冻结计划为空！");
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.debug("机构申请冻结一笔订单，订单号：" + loanOrd.getLoanOrdId() + " 计划期号：" + planPeriods);
			FreezeResult res = new FreezeResult();
			res.setAmount(amount);
			res.setPeriods(planPeriods);
			return res;
		}
		throw new Exception("订单状态不正确");
		
	}
	@Override
	public FreezeResult thaw(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception {
		if (CollectionUtils.isEmpty(plans)) throw new Exception("计划列表为空！");
		if (loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()
				&& loanOrd.getOrdStat().ordinal() > OrdStat.AUDIT.ordinal()){
			BigDecimal amount = BigDecimal.ZERO;
			String periods = "";
			for (LoanOrdPlan plan :plans){
				if (plan.getStat() == PlanStat.BALANCE_FREEZE){
					plan.setStat(PlanStat.THAW_APPLY);
					this.loanOrdPlanService.update(plan);
					amount = amount.add(plan.getRepayment());
					periods += plan.getPeriod().toString() + ",";
				}
				logger.debug("机构提交解冻申请，订单号：" + loanOrd.getLoanOrdId() + " 计划编号：" + plan.getIlanOrdPlan());
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			FreezeResult res = new FreezeResult();
			res.setAmount(amount);
			res.setPeriods(periods);
			return res;
		}
		throw new Exception("订单状态不正确");
	}

	@Override
	public List<LoanOrd> queryLoanOrdByStat(final OrdStat stat) {
		return this.loanOrdDao.query(new CriteriaExecutor<List<LoanOrd>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<LoanOrd> execute(Criteria expr) {
					expr.add(Restrictions.eq("ordStat", stat));
				return expr.list();
			}
			
		});
	}
	@Override
	@Transactional
	public LoanOrd queryLoanOrdById(Long iloanOrd) {
		return this.loanOrdDao.findLoanOrdById(iloanOrd);
	}
	@Override
	public void auditFreeze(LoanOrd loanOrd, List<LoanOrdPlan> plans)
			throws Exception {
		if (loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()
				&& loanOrd.getOrdStat().ordinal() > OrdStat.AUDIT.ordinal()){
			String planPeriods = "";
			BigDecimal amount = BigDecimal.ZERO;
			for (LoanOrdPlan plan : plans){
				plan.setStat(PlanStat.BALANCE_FREEZE);
				planPeriods += plan.getPeriod() + ",";
				amount = amount.add(plan.getRepayment());
				this.loanOrdPlanService.update(plan);				
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.debug("机构冻结一笔订单，订单号：" + loanOrd.getLoanOrdId() + " 计划期号：" + planPeriods);
			return ;
		}
		throw new Exception("订单状态不正确");
		
	}
	@Override
	public void refuseFreeze(LoanOrd loanOrd, List<LoanOrdPlan> plans)
			throws Exception {
		if (loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()
				&& loanOrd.getOrdStat().ordinal() > OrdStat.AUDIT.ordinal()){
			String planPeriods = "";
			BigDecimal amount = BigDecimal.ZERO;
			for (LoanOrdPlan plan : plans){
				plan.setStat(plan.getPreStat());
				planPeriods += plan.getPeriod() + ",";
				amount = amount.add(plan.getRepayment());
				this.loanOrdPlanService.update(plan);
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			logger.debug("机构拒决冻结订单，订单号：" + loanOrd.getLoanOrdId() + " 计划期号：" + planPeriods);
			return ;
		}
		throw new Exception("订单状态不正确");
		
	}
	@Override
	@Transactional
	public List<LoanOrd> queryByCondition(LoanOrdCondition cond)
			throws Exception {
		return this.loanOrdDao.queryByCondition(cond);
	}
	
	/**
	 * 分页查询我的订单申请
	 * @param loanordcondition
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public List<LoanOrd> queryOrdByCon(LoanOrdCondition loanordcondition, Page page) throws Exception{
		return this.loanOrdDao.queryOrdByCon(loanordcondition, page);
	}
	@Override
	@Transactional
	public LoanOrd queryByDebitbid(Long iDebitbid) {
		return loanOrdDao.queryByDebitbid(iDebitbid);
	}
	@Override
	public FreezeResult cancelFreeze(LoanOrd loanOrd,
			ArrayList<LoanOrdPlan> plans) throws Exception {
		if (CollectionUtils.isEmpty(plans)) throw new Exception("计划列表为空！");
		if (loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()
				&& loanOrd.getOrdStat().ordinal() > OrdStat.AUDIT.ordinal()){
			BigDecimal amount = BigDecimal.ZERO;
			String periods = "";
			for (LoanOrdPlan plan :plans){
				if (plan.getStat() == PlanStat.FREEZING){
					plan.setStat(plan.getPreStat());
					this.loanOrdPlanService.update(plan);
					amount = amount.add(plan.getRepayment());
					periods += plan.getPeriod().toString() + ",";
				}
				logger.debug("机构机构解冻订单，订单号：" + loanOrd.getLoanOrdId() + " 计划编号：" + plan.getIlanOrdPlan());
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			FreezeResult res = new FreezeResult();
			res.setAmount(amount);
			res.setPeriods(periods);
			return res;
		}
		throw new Exception("订单状态不正确");
	}
	@Override
	@Transactional
	public List<LoanOrd> queryByDebit(Long iDebitbid) {
		return loanOrdDao.queryByDebit(iDebitbid);
	}
	@Override
	public FreezeResult refuseThaw(LoanOrd loanOrd, List<LoanOrdPlan> plans)
			throws Exception {
		if (CollectionUtils.isEmpty(plans)) throw new Exception("计划列表为空！");
		if (loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()
				&& loanOrd.getOrdStat().ordinal() > OrdStat.AUDIT.ordinal()){
			BigDecimal amount = BigDecimal.ZERO;
			String periods = "";
			for (LoanOrdPlan plan :plans){
				if (plan.getStat() == PlanStat.THAW_APPLY){
					plan.setStat(plan.getPreStat());
					this.loanOrdPlanService.update(plan);
					amount = amount.add(plan.getRepayment());
					periods += plan.getPeriod().toString() + ",";
				}
				logger.debug("汇卡拒决解冻申请，订单号：" + loanOrd.getLoanOrdId() + " 计划编号：" + plan.getIlanOrdPlan());
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			FreezeResult res = new FreezeResult();
			res.setAmount(amount);
			res.setPeriods(periods);
			return res;
		}
		throw new Exception("订单状态不正确");
	}
	@Override
	public FreezeResult auditThaw(LoanOrd loanOrd, List<LoanOrdPlan> plans)
			throws Exception {
		if (CollectionUtils.isEmpty(plans)) throw new Exception("计划列表为空！");
		if (loanOrd.getOrdStat().ordinal() < OrdStat.PAID_UP_LOAN.ordinal()
				&& loanOrd.getOrdStat().ordinal() > OrdStat.AUDIT.ordinal()){
			BigDecimal amount = BigDecimal.ZERO;
			String periods = "";
			for (LoanOrdPlan plan :plans){
				if (plan.getStat() == PlanStat.THAW_APPLY){
					plan.setStat(PlanStat.PAID_UP_LOAN);
					this.loanOrdPlanService.update(plan);
					amount = amount.add(plan.getRepayment());
					periods += plan.getPeriod().toString() + ",";
				}
				logger.debug("机构提交解冻申请，订单号：" + loanOrd.getLoanOrdId() + " 计划编号：" + plan.getIlanOrdPlan());
			}
			List<LoanOrdPlan> list = this.loanOrdPlanService.queryDelinQuencyPlan(loanOrd.getIloanOrd());
			if (this.haveProcFundFlow(loanOrd, FundFlowType.REFUND)){
				loanOrd.setOrdStat(OrdStat.REFUNDING);
			}else{
				if (CollectionUtils.isEmpty(list)){
					boolean paidup = this.loanOrdPlanService.isPaidUp(loanOrd.getIloanOrd());
					if (loanOrd.getRemainPeriod().intValue() == 0 && paidup){
						loanOrd.setOrdStat(OrdStat.PAID_UP_LOAN);
						logger.info("已全额还款！订单号：" + loanOrd.getLoanOrdId());
					}else{
						loanOrd.setOrdStat(OrdStat.REFUND);
					}
				}else{
					loanOrd.setOrdStat(OrdStat.DELIN_QUENCY);
				}
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			FreezeResult res = new FreezeResult();
			res.setAmount(amount);
			res.setPeriods(periods);
			return res;
		}
		throw new Exception("订单状态不正确");
	}
	@Override
	@MethodTrigger(stratgy="curStatistics")
	public void cancelCredit(LoanOrd loanOrd) throws Exception {
		if (loanOrd.getOrdStat() != OrdStat.CREDITING) throw new Exception("订单状态不正确！");
		loanOrd.setOrdStat(OrdStat.AUDIT);
		this.loanOrdDao.updateLoanOrd(loanOrd);
	}
	@Override
	public void cancelRefund(LoanOrd loanOrd, LoanOrdPlan plan) throws Exception {
		if (loanOrd.getOrdStat() == OrdStat.REFUNDING){
			if (plan.getStat() != PlanStat.PAID_UP_LOAN) throw new Exception("计划状态不正确！");
			plan.setStat(plan.getPreStat());
			this.loanOrdPlanService.update(plan);
			List<LoanOrdPlan> list = this.loanOrdPlanService.queryDelinQuencyPlan(loanOrd.getIloanOrd());
			if (loanOrd.getRepayment() == null){
				loanOrd.setRepayment(BigDecimal.ZERO);
			}
			loanOrd.setRepayment(loanOrd.getRepayment().subtract(plan.getRepayment()));
			if (loanOrd.getRemainPayment() == null){
				loanOrd.setRemainPayment(loanOrd.getQuota());
			}
			loanOrd.setRemainPayment(loanOrd.getRemainPayment().add(plan.getRepayment()));
			if (loanOrd.getRecivePeriod() == null){
				loanOrd.setRecivePeriod(new Integer(0));
			}
			loanOrd.setRecivePeriod(loanOrd.getRecivePeriod() - 1);
			if (loanOrd.getRemainPeriod() == null){
				loanOrd.setRemainPeriod(loanOrd.getTerm());
			}
			loanOrd.setRemainPeriod(loanOrd.getRemainPeriod() + 1);
			if (this.haveProcFundFlow(loanOrd, FundFlowType.REFUND)){
				loanOrd.setOrdStat(OrdStat.REFUNDING);
			}else{
				if (CollectionUtils.isEmpty(list)){
					loanOrd.setOrdStat(OrdStat.REFUND);
				}else{
					loanOrd.setOrdStat(OrdStat.DELIN_QUENCY);
				}
			}
			this.loanOrdDao.updateLoanOrd(loanOrd);
			return ;
		}
		throw new Exception("订单状态不正确！");
	}
	@Override
	@Transactional
	public List<LoanOrd> queryByIinst(Long iInst) {
		// TODO Auto-generated method stub
		return this.loanOrdDao.queryByIinst(iInst);
	}
	@Override
	@Transactional
	public List<LoanOrd> queryByImerch(Long iMerch) {
		// TODO Auto-generated method stub
		return this.loanOrdDao.queryByImerch(iMerch);
	}
	@Override
	@Transactional
	public LoanOrd queryByLoanOrdId(String loanOrdId) {
		return this.loanOrdDao.queryByLoanOrdId(loanOrdId);
	}
	@Override
	@Transactional
	public List<Object> queryTenDays(Date d, Date tenD, Long iinst) {
		return this.loanOrdDao.queryTenDays(d, tenD, iinst);
	}
	@Override
	@Transactional
	public List<LoanOrdPlan> queryIloanOrd(Date d, Date tenD, Long iinst) {
		return this.loanOrdDao.queryIloanOrd(d, tenD, iinst);
	}
	@Override
	@Transactional
	public List<LoanOrd> queryfreezeLoanOrd(Long iinst) {
		return this.loanOrdDao.queryfreezeLoanOrd(iinst);
	}

}
