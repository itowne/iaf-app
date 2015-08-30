package newland.iaf.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.DebitBidDao;
import newland.iaf.base.dao.FundFlowDao;
import newland.iaf.base.dao.LoanOrdDao;
import newland.iaf.base.dao.LoanOrdPlanDao;
import newland.iaf.base.dao.LoanPdtDao;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.condition.DateConvertUtils;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.DebitbidStat;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.YesOrNoType;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanStatisticsMgrService;
import newland.iaf.base.service.LoanStatisticsSubService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.iaf.utils.DateUtils;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
@Service ("loanStatisticsSubService")
@Transactional
public class LoanStatisticsSubServiceImpl implements LoanStatisticsSubService {
	
	@Resource (name = "com.newland.iaf.loanPdtDao")
	private LoanPdtDao loanPdtDao;
	
	@Resource (name = "loanOrdDao")
	private LoanOrdDao loanOrdDao;
	
	@Resource (name = "loanOrdPlanDao")
	private LoanOrdPlanDao loanOrdPlanDao;
	
	@Resource (name = "fundFlowDao")
	private FundFlowDao fundFlowDao;
	
	@Resource (name = "debitBidDao")
	private DebitBidDao debitBidDao;
	
	@Resource (name = "com.newland.iaf.instService")
	private InstService instService;
	
	@Resource (name = "loanStatisticsMgrService")
	private LoanStatisticsMgrService statisticsMgrService;
	
	@Override
	public final void update(LoanStatistics ls){
		this.statisticsMgrService.update(ls);
	}

	@Override
	public final LoanStatistics query(Long iinst, InstType type){
		return this.statisticsMgrService.query(iinst, type);
	}
	
	/**
	 * 拒决审核订单数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void loanRefuseAuditCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("ordStat", OrdStat.AUDIT_REFUSE))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setRefuseAuditCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 拒决受理订单数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void loanRefuseAcceptCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("ordStat", OrdStat.ACCEPT_REFUSE))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setRefuseAcceptCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 当前待放款订单数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void curLoanCreditCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("ordStat", OrdStat.AUDIT))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) {
					return null;
				}
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setCurCreditCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 当前还款中订单数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void curLoanRefundingCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("ordStat", OrdStat.REFUND))
						.add(Restrictions.eq("shield", new Integer(0)));
						//.add(Restrictions.gt("ordStat", OrdStat.AUDIT))
						//.add(Restrictions.lt("ordStat", OrdStat.PAID_UP_LOAN));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setCurRefundingCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 当前待审核订单数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void curLoanAuditCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("ordStat", OrdStat.ACCEPT))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setCurAuditCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 审核通过订单数 - 包括所有已审核
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void loanAuditCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.ge("ordStat", OrdStat.AUDIT))
						.add(Restrictions.le("ordStat", OrdStat.PAID_UP_LOAN))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setLoanAuditCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 当前待受理订单数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void curLoanAcceptCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("ordStat", OrdStat.APPLY))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setCurAcceptCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 当前撤销订单数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void curLoanCancelCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("ordStat", OrdStat.CANCEL))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setCurCancelCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 受理订单数  - 包含所有已受理
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void loanAcceptCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.ge("ordStat", OrdStat.ACCEPT))
						.add(Restrictions.le("ordStat", OrdStat.ACCEPT_OVERDUE))
						.add(Restrictions.eq("shield", new Integer(0)));;
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setLoanAcceptCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 撤销订单数  - 包含所有撤销订单
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void loanCancelCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("ordStat", OrdStat.CANCEL))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setLoanCancelCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 申请订单数 - 包含所有订单
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void loanApplyCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){

			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setLoanApplyCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 订单总数-只包括正常状态
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void loanCount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){
			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount()))
						.add(Restrictions.le("ordStat", OrdStat.PAID_UP_LOAN))
						.add(Restrictions.eq("shield", new Integer(0)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setLoanCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 当前冻结总额
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void freezeAmount(final InstType type, final String prop) {
		this.loanOrdPlanDao.queryBy(new CriteriaExecutor<LoanOrdPlan>(){
			@Override
			public LoanOrdPlan execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.sum("repayment")))
						.add(Restrictions.eq("stat", PlanStat.BALANCE_FREEZE));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					BigDecimal count = (BigDecimal)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setFreezeAmount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 当前逾期总额
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void overdueAmount(final InstType type, final String prop) {
		this.loanOrdPlanDao.queryBy(new CriteriaExecutor<LoanOrdPlan>(){
			@Override
			public LoanOrdPlan execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.rowCount())
						.add(Projections.sum("repayment")))
						
						.add(Restrictions.ge("stat", PlanStat.DELIN_QUENCY))
						.add(Restrictions.le("stat", PlanStat.FREEZING));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					BigDecimal amount = (BigDecimal)objs[2];
					LoanStatistics ls = query(iinst, type);
					ls.setOverDueAmount(amount);
					ls.setOverDueCount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 当前欠款总额 - 包括逾期与冻结的
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void debtAmount(final InstType type, final String prop) {
		this.loanOrdPlanDao.queryBy(new CriteriaExecutor<LoanOrdPlan>(){
			@Override
			public LoanOrdPlan execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.sum("repayment")))
						.add(Restrictions.ge("stat", PlanStat.BALANCE))
						.add(Restrictions.lt("stat", PlanStat.PAID_UP_LOAN));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					BigDecimal count = (BigDecimal)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setDebtAmount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 已还款总额
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void loanRefundAmount(final InstType type, final String prop) {
		this.loanOrdPlanDao.queryBy(new CriteriaExecutor<LoanOrdPlan>(){
			@Override
			public LoanOrdPlan execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.sum("repayment")))
						.add(Restrictions.eq("stat", PlanStat.PAID_UP_LOAN));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					BigDecimal count = (BigDecimal)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setRefundAmount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 待放款总额
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void curCreditAmount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){
			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.sum("quota")))
						.add(Restrictions.eq("ordStat", OrdStat.AUDIT));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					BigDecimal count = (BigDecimal)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setCurCreditAmount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	/**
	 * 贷款总额
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void loanAmount(final InstType type, final String prop) {
		this.loanOrdDao.queryBy(new CriteriaExecutor<LoanOrd>(){
			@Override
			public LoanOrd execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.sum("quota")))
						.add(Restrictions.ge("ordStat", OrdStat.AUDIT))
						.add(Restrictions.le("ordStat", OrdStat.PAID_UP_LOAN));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					BigDecimal count = (BigDecimal)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setLoanAmount(count);
					update(ls);
				}
				return null;
			}
		});
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void statisticsProd() {
		this.loanPdtDao.queryBy(new CriteriaExecutor<LoanPdt>(){
			@Override
			public LoanPdt execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("iinst"))
						.add(Projections.rowCount()))
						.add(Restrictions.eq("deleteFlag", YesOrNoType.NO));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, InstType.INST);
					ls.setProdCount(count);
					update(ls);
				}
				return null;
			}
		});
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void statisticsDebitBid() {
		this.debitBidDao.queryBy(new CriteriaExecutor<DebitBid>(){

			@Override
			public DebitBid execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("imerch"))
						.add(Projections.rowCount()));
						//.add(Restrictions.eq("bidStat", DebitbidStat.NORMAL));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					Long count = (Long)objs[1];
					LoanStatistics ls = query(iinst, InstType.MERCH);
					ls.setMerchDebidBitCount(count);
					update(ls);
				}
				return null;
			}
		});

	}
	/**
	 * 待还款总额
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void curRefundAmount(final InstType type, final String prop) {
		this.loanOrdPlanDao.queryBy(new CriteriaExecutor<LoanOrdPlan>(){
			@Override
			public LoanOrdPlan execute(Criteria expr) {
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(prop))
						.add(Projections.sum("repayment")))
						.add(Restrictions.eq("stat", PlanStat.BALANCE));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				for (Object[] objs: list){
					Long iinst = (Long)objs[0];
					BigDecimal count = (BigDecimal)objs[1];
					LoanStatistics ls = query(iinst, type);
					ls.setCurRefundAmount(count);
					update(ls);
				}
				return null;
			}
		});
		
	}
	
	private LoanOrd queryById(Long iloanOrd){
		return this.loanOrdDao.findLoanOrdById(iloanOrd);
	}

	/**
	 * 当期应还款总额及订单数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void curPeriodRefund(final InstType type, final String prop) {
		this.loanOrdPlanDao.queryBy(new CriteriaExecutor<LoanOrdPlan>(){
			@Override
			public LoanOrdPlan execute(Criteria expr) {
				Date beginDate = DateUtils.roundDate(new Date(), Calendar.DATE);
				beginDate = DateConvertUtils.getFirstDateOfMonth(beginDate);
				Date endDate = DateUtils.roundDate(new Date(), Calendar.DATE);
				endDate = DateConvertUtils.getLastDateOfMonth(endDate);
				expr.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("loanOrdId"))
						.add(Projections.groupProperty(prop))
						.add(Projections.sum("repayment"))
						.add(Projections.rowCount()))
						.add(Restrictions.or(
								Restrictions.eq("stat", PlanStat.DELIN_QUENCY),
								Restrictions.and(
										Restrictions.eq("stat", PlanStat.BALANCE)
										//Restrictions.between("refundDate", beginDate, endDate)
										)));
				List<Object[]> list = expr.list();
				if (CollectionUtils.isEmpty(list)) return null;
				Map<Long, St> map = new HashMap<Long, St>();
				for (Object[] objs: list){
					Long iinst = (Long)objs[1];
					St st = map.get(iinst);
					BigDecimal count = (BigDecimal)objs[2];
					if (st == null){
						st = new St();
						map.put(iinst, st);
					}
					st.increamAmount(count);
					st.increamCount(new Long(1));
				}
				for (Map.Entry<Long, St> entry: map.entrySet()){
					Long iinst = entry.getKey();
					LoanStatistics ls = query(iinst, type);
					ls.setCurPeriodRefundAmount(entry.getValue().amount);
					ls.setExpireRefund(entry.getValue().count);
					update(ls);
				}
				return null;
			}
		});
		
	}
	
	private static class St {
		
		public Long iinst;
		
		public BigDecimal amount = BigDecimal.ZERO;
		
		public Long count = new Long(0);
		
		public void increamAmount(BigDecimal amount){
			this.amount = this.amount.add(amount);
		}
		
		public void increamCount(Long count){
			this.count = this.count + count;
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
	public void statisticsInstSuccess() throws Exception{
		List<Inst> list = this.instService.queryInst();
		if (CollectionUtils.isEmpty(list) == false){
			for (Inst inst : list){
				LoanStatistics ls = this.statisticsMgrService.query(inst.getIinst(), InstType.INST);
				if (ls != null){
					Long accept = ls.getLoanAcceptCount();
					Long audit = ls.getLoanAuditCount();
					if (accept.intValue() == 0){
						inst.setSuccessRate(new BigDecimal(0));
					}else{
						inst.setSuccessRate(new BigDecimal(audit.intValue()/accept.intValue()*100));
					}
					inst.setPdtCount(ls.getProdCount());
					this.instService.updateInst(inst,null,null);
				}
			}
		}
	}
}
