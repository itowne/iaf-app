package newland.iaf.base.service;

import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.dict.InstType;

public interface LoanStatisticsSubService {
	/**
	 * 拒决审核订单数
	 */
	void loanRefuseAuditCount(InstType type, String prop);
	/**
	 * 拒决受理订单数
	 */
	void loanRefuseAcceptCount(InstType type, String prop);
	/**
	 * 当前待放款订单数
	 */
	void curLoanCreditCount(InstType type, String prop);
	/**
	 * 当前待审核订单数
	 */
	void curLoanAuditCount(InstType type, String prop);
	/**
	 * 审核通过订单数 - 包括所有已审核
	 */
	void loanAuditCount(InstType type, String prop);
	/**
	 * 当前待受理订单数
	 */
	void curLoanAcceptCount(InstType type, String prop);
	/**
	 * 当前撤销订单数
	 */
	void curLoanCancelCount(InstType type, String prop);
	/**
	 * 受理订单数  - 包含所有已受理
	 */
	void loanAcceptCount(InstType type, String prop);
	/**
	 * 撤销订单数  - 包含所有撤销订单
	 */
	void loanCancelCount(InstType type, String prop);
	/**
	 * 申请订单数 - 包含所有订单
	 */
	void loanApplyCount(InstType type, String prop);
	/**
	 * 订单总数
	 */
	void loanCount(InstType type, String prop);

	void update(LoanStatistics ls);

	LoanStatistics query(Long iinst, InstType type);
	/**
	 * 当前冻结总额
	 */
	void freezeAmount(InstType type, String prop);
	/**
	 * 当前逾期总额
	 */
	void overdueAmount(InstType type, String prop);
	/**
	 * 当前欠款总额
	 */
	void debtAmount(InstType type, String prop);
	/**
	 * 已还款总额
	 */
	void loanRefundAmount(InstType type, String prop);
	/**
	 * 待放款总额
	 */
	void curCreditAmount(InstType type, String prop);
	/**
	 * 贷款总额
	 */
	void loanAmount(InstType type, String prop);

	void statisticsProd();

	void statisticsDebitBid();
	/**
	 * 待还款总额
	 */
	void curRefundAmount(InstType type, String prop);
	/**
	 * 当期应还款总额
	 */
	void curPeriodRefund(InstType type, String prop);
	/**
	 * 当前还款中订单数量
	 * @param type
	 * @param prop
	 */
	void curLoanRefundingCount(InstType type, String prop);
	/**
	 * 统计机构成功率
	 * @throws Exception
	 */
	void statisticsInstSuccess() throws Exception;

}
