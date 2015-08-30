package newland.iaf.base.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.LoanOrdDao;
import newland.iaf.base.dao.LoanOrdPlanDao;
import newland.iaf.base.dao.LoanStatisticsDao;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.LoanStatisticsHis;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.TransReport;
import newland.iaf.base.model.condition.DateConvertUtils;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.risk.dao.RiskControlService;
import newland.iaf.base.risk.model.RiskMonitor;
import newland.iaf.base.service.CreditReportQueryService;
import newland.iaf.base.service.QueryType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.trans.dao.HcTransDao;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.base.trans.model.HcInstallLog;
import newland.iaf.base.trans.model.HcTransLog;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.service.MerchService;
import newland.iaf.utils.DateUtils;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("creditReportQueryService")
@Transactional
public class CreditReportQueryServiceImpl implements CreditReportQueryService {

	@Resource(name = "loanOrdDao")
	private LoanOrdDao loanOrdDao;
	@Resource(name = "loanOrdPlanDao")
	private LoanOrdPlanDao loanOrdPlanDao;
	@Resource(name = "com.newland.iaf.hcTransDao")
	private HcTransDao hcTransDao;
	@Resource(name = "loanStatisticsDao")
	private LoanStatisticsDao loanStatisticsDao;
	@Resource (name = "com.newland.iaf.merchService")
	private MerchService merchService;
	@Resource (name = "com.newland.riskControlService")
	private RiskControlService riskControlService;
	
	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;

	@Override
	public TransReport queryReciveTrans(final String merchId,
			final Date beginDate, final Date endDate) {
		List<Object> obj = (List<Object>) this.hcTransDao.queryBy(
				new CriteriaExecutor<List<Object>>() {

					@SuppressWarnings("unchecked")
					@Override
					public List<Object> execute(Criteria expr) {
						expr.setProjection(
								Projections.projectionList()
										.add(Projections.sum("amt"))
										.add(Projections.rowCount())
										.add(Projections.avg("amt")))
								.add(Restrictions.eq("merchNo", merchId))
								.add(Restrictions.eq("transType", "N"))
								.add(Restrictions.between("transDate",
										beginDate, endDate));
						return expr.list();
					}
				}, HcTransLog.class);
		return genTransReport(obj, DateUtils.compareDays(beginDate, endDate));
	}

	@Override
	public TransReport queryPayTrans(final String merchId,
			final Date beginDate, final Date endDate) {
		List<Object> obj = (List<Object>) this.hcTransDao.queryBy(
				new CriteriaExecutor<List<Object>>() {

					@SuppressWarnings("unchecked")
					@Override
					public List<Object> execute(Criteria expr) {
						expr.setProjection(
								Projections.projectionList()
										.add(Projections.sum("amt"))
										.add(Projections.rowCount())
										.add(Projections.avg("amt")))
								.add(Restrictions.eq("merchNo", merchId))
								.add(Restrictions.ne("transType", "N"))
								.add(Restrictions.between("transDate",
										beginDate, endDate));
						return expr.list();
					}
				}, HcTransLog.class);
		return genTransReport(obj, DateUtils.compareDays(beginDate, endDate));
	}

	static Set<OrdStat> ordStatus = new HashSet<OrdStat>();
	static {
		ordStatus.add(OrdStat.REFUND);
		ordStatus.add(OrdStat.PAID_UP_LOAN);
	}

	@Override
	public TransReport queryLoanTrans(final Long imerch, final Date beginDate,
			final Date endDate) {
		List<Object> obj = this.loanOrdDao
				.queryBy(new CriteriaExecutor<List<Object>>() {

					@SuppressWarnings("unchecked")
					@Override
					public List<Object> execute(Criteria expr) {
						expr.setProjection(
								Projections.projectionList()
										.add(Projections.sum("quota"))
										.add(Projections.rowCount())
										.add(Projections.avg("quota")))
								.add(Restrictions.eq("imerch", imerch))
								.add(Restrictions.between("ordDate", beginDate,
										endDate))
								.add(Restrictions.ge("ordStat",
										OrdStat.REFUNDING))
								.add(Restrictions.le("ordStat",
										OrdStat.PAID_UP_LOAN));
						return expr.list();
					}
				});
		return genTransReport(obj, DateUtils.compareDays(beginDate, endDate));
	}

	@Override
	public TransReport queryRefundTrans(final Long imerch,
			final Date beginDate, final Date endDate) {
		List<Object> obj = this.loanOrdPlanDao
				.queryBy(new CriteriaExecutor<List<Object>>() {

					@SuppressWarnings("unchecked")
					@Override
					public List<Object> execute(Criteria expr) {
						expr.setProjection(
								Projections.projectionList()
										.add(Projections.sum("repayment"))
										.add(Projections.rowCount())
										.add(Projections.avg("repayment")))
								.add(Restrictions.eq("imerch", imerch))
								.add(Restrictions.between("updTime", beginDate,
										endDate))
								.add(Restrictions.eq("stat",
										PlanStat.PAID_UP_LOAN));
						return expr.list();
					}
				});
		return genTransReport(obj, DateUtils.compareDays(beginDate, endDate));
	}

	private TransReport genTransReport(List obj, int days) {
		if (days == 0)
			days = 1;
		TransReport trans = new TransReport();
		if (obj != null) {
			Object[] res = (Object[]) obj.get(0);
			Object amount = res[0];
			Object count = res[1];
//			Object avg = BigDecimal.ZERO;
//			if (amount != null) {
//				((BigDecimal) amount).divide(new BigDecimal(days),2);
//			}
			Object avg = res[2];
			trans.setAmount((BigDecimal) ((amount == null) ? BigDecimal.ZERO
					: amount));
			trans.setTransCount((Long) ((count == null) ? new Long(0) : count));
			trans.setDailyAvgAmount((Double)((avg == null)?new Double(0):(((BigDecimal) amount).divide(new BigDecimal(days),2))).doubleValue());
//			trans.setDailyAvgAmount((Double) ((avg == null) ? new Double(0)
//			: BigDecimal.ZERO.doubleValue()));
		}
		return trans;
	}

	@Override
	public List<HcInspectLog> queryInspectLog(final String merchId,
			final Date beginDate, final Date endDate) {
		return this.hcTransDao.queryBy(
				new CriteriaExecutor<List<HcInspectLog>>() {

					@SuppressWarnings("unchecked")
					@Override
					public List<HcInspectLog> execute(Criteria expr) {
						expr.add(Restrictions.eq("hcMerchNo", merchId)).add(
								Restrictions.between("inspectDate", beginDate,
										endDate))
										.addOrder(Order.asc("inspectDate"));
						return expr.list();
					}
				}, HcInspectLog.class);
	}

	@Override
	public List<HcInstallLog> queryInstallLog(final String merchId,
			final Date beginDate, final Date endDate) {
		return this.hcTransDao.queryBy(
				new CriteriaExecutor<List<HcInstallLog>>() {

					@SuppressWarnings("unchecked")
					@Override
					public List<HcInstallLog> execute(Criteria expr) {
						expr.add(Restrictions.eq("hcMerchNo", merchId))
						.add(Restrictions.or(Restrictions.between(
								"installDate", beginDate, endDate),
								Restrictions.between(
										"uninstallDate", beginDate, endDate)
								))
						.addOrder(Order.asc("installDate"));
						return expr.list();
					}
				}, HcInstallLog.class);
	}

	@Override
	public TransReport queryLoanRisk(final Long imerch, final QueryType type) {
		List<Object> obj = this.loanOrdPlanDao
				.queryBy(new CriteriaExecutor<List<Object>>() {

					@SuppressWarnings("unchecked")
					@Override
					public List<Object> execute(Criteria expr) {
						expr.setProjection(
								Projections.projectionList()
										.add(Projections.sum("repayment"))
										.add(Projections.rowCount())
										.add(Projections.avg("repayment")))
								.add(Restrictions.eq("imerch", imerch));
						switch (type) {
						case ALL:
							expr.add(Restrictions.lt("stat",
									PlanStat.THAW_APPLY));
							break;
						case BALANCE:
							Date beginDate = DateUtils.roundDate(new Date(),
									Calendar.DATE);
							beginDate = DateConvertUtils
									.getFirstDateOfMonth(beginDate);
							Date endDate = DateUtils.roundDate(new Date(),
									Calendar.DATE);
							endDate = DateConvertUtils
									.getLastDateOfMonth(endDate);
							expr.add(Restrictions.eq("stat", PlanStat.BALANCE))
									.add(Restrictions.between("refundDate",
											beginDate, endDate));
							break;
						case DELIN_QUENCY:
							expr.add(Restrictions.eq("stat",
									PlanStat.DELIN_QUENCY));
							break;
						case FREEZE:
							expr.add(Restrictions.ge("stat", PlanStat.FREEZING))
									.add(Restrictions.lt("stat",
											PlanStat.THAW_APPLY));
							break;
						}
						return expr.list();
					}
				});
		return genTransReport(obj, 0);
	}

	@Override
	public List<LoanStatisticsHis> queryDelinQuencyHis(final Long imerch,
			final Date beginDate, final Date endDate, final int mixCount) {
		return this.loanStatisticsDao
				.queryBy(new CriteriaExecutor<List<LoanStatisticsHis>>() {

					@Override
					public List<LoanStatisticsHis> execute(Criteria expr) {
						expr.add(Restrictions.eq("imerch", imerch))
								.add(Restrictions.between("timeStamp",
										beginDate, endDate))
								.add(Restrictions
										.eq("instType", InstType.MERCH))
								.add(Restrictions.ge("overdueCount", mixCount));
						return expr.list();
					}
				});
	}

	@Override
	public BigDecimal queryBalanceAmount(Long imerch) {
		return this.loanStatisticsDao.queryBy(imerch, InstType.MERCH)
				.getDebtAmount();
	}

	@Override
	public BigDecimal queryFreezeAmount(Long imerch) {
		return this.loanStatisticsDao.queryBy(imerch, InstType.MERCH)
				.getFreezeAmount();
	}
	
	@Override
	public RiskMonitor queryRisk(Merch merch, Date date){
		RiskMonitor rm = new RiskMonitor();
		rm.setUninstallNum(riskControlService.getUninstallPosNum(merch.getMerchNo(), date));
		rm.setRate(riskControlService.transLogAmtReduce(merch.getMerchNo()));
		boolean hasTransLog = riskControlService.hasTransLog(merch.getMerchNo());
		SysParam param = sysParamService.getSysParam(SysParamType.riskControl,
				SysParamName.RISK_CONTROL_POS_TRANS_DAYS);
		rm.setHasTransLog(hasTransLog?"正常":"商户已有"+param.getValue()+"日没有pos交易");
		BigDecimal debit = queryBalanceAmount(merch.getImerch());
		BigDecimal amt = riskControlService.getHcTransLogAmt(merch.getMerchNo(),
				DateUtils.addMonths(new Date(), -1));
		rm.setAmt(amt);
		boolean refund = debit.compareTo(amt) > 0;
		rm.setRefund(refund?"正常":"上月交易少于本期还款金额");
		BigDecimal freezeAmt = queryFreezeAmount(merch.getImerch());
		boolean freeze = freezeAmt.compareTo(BigDecimal.ZERO) > 0;
		rm.setFreeze(freeze?"正常":"商户清算账户被冻结");
		TransReport delin = queryLoanRisk(merch.getImerch(),
				QueryType.DELIN_QUENCY);
		rm.setExpireNum(delin.getTransCount().intValue());
		rm.setDebitAmt(queryBalanceAmount(merch.getImerch()));
		return rm;
	}

	public static void main(String[] args) {
		Date beginDate = new Date();
		Date endDate = DateUtils.rollDate(beginDate, Calendar.DATE, 20);
		System.err.println(DateUtils.compareDays(beginDate, endDate));
	}

}
