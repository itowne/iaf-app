package newland.iaf.base.service;

import newland.iaf.base.model.LoanOrd;

/**
 * 订单跟踪服务
 * 
 * @author new
 * 
 */
public interface LoanOrdTrackService {
	/**
	 * 订单计划状态跟踪
	 */
	void trackOrdAndPlanStat(LoanOrd loanOrd);

	/**
	 * 定单过期跟踪
	 * 
	 * @param loanOrd
	 * @throws Exception
	 */
	void trackLoanOrdExpire(LoanOrd loanOrd) throws Exception;

}
