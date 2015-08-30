package newland.iaf.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import newland.iaf.base.model.LoanStatisticsHis;
import newland.iaf.base.model.TransReport;
import newland.iaf.base.risk.model.RiskMonitor;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.base.trans.model.HcInstallLog;
import newland.iaf.merch.model.Merch;

public interface CreditReportQueryService {
	/**
	 * 统计收款流水
	 * @param merchId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	TransReport queryReciveTrans(String merchId, Date beginDate, Date endDate);
	/**
	 * 统计付款流水
	 * @param merchId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	TransReport queryPayTrans(String merchId, Date beginDate, Date endDate);
	/**
	 * 统计贷款流水
	 * @param imerch
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	TransReport queryLoanTrans(Long imerch, Date beginDate, Date endDate);
	/**
	 * 统计还款流水
	 * @param imerch
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	TransReport queryRefundTrans(Long imerch, Date beginDate, Date endDate);
	
	/**
	 * 查询巡检记录
	 * @param merchId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	List<HcInspectLog> queryInspectLog(String merchId, Date beginDate, Date endDate);
	/**
	 * 
	 * @param merchId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	List<HcInstallLog> queryInstallLog(String merchId, Date beginDate,
			Date endDate);
	
	/**
	 * 查询现有逾期和冻结订单
	 * @param imerch
	 * @return
	 */
	TransReport queryLoanRisk(Long imerch, QueryType type);
	/**
	 * 查询历史逾期记录
	 * @param imerch
	 * @param beginDate
	 * @param endDate
	 * @param type
	 * @param mixCount 容许的逾期笔数
	 * @return
	 */
	List<LoanStatisticsHis> queryDelinQuencyHis(Long imerch, Date beginDate, Date endDate, int mixCount);
	/**
	 * 查询当前欠款金额
	 * @param imerch
	 * @return
	 */
	BigDecimal queryBalanceAmount(Long imerch);
	
	/**
	 * 查询当前冻结总额
	 * @param imerch
	 * @return
	 */
	BigDecimal queryFreezeAmount(Long imerch);
	RiskMonitor queryRisk(Merch merch, Date date);

}
