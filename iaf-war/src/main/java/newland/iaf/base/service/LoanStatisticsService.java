package newland.iaf.base.service;

import java.math.BigInteger;

import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.dict.InstType;

public interface LoanStatisticsService {
	/**
	 * 订单统计
	 */
	void statisticsLoanOrd();
	/**
	 * 产品统计
	 */
	void statisticsProd();
	/**
	 * 竟投统计
	 */
	void statisticsDebitBid();
	/**
	 * 更新
	 * @param ls
	 */
	void update(LoanStatistics ls);
	/**
	 * 查询
	 * @param iinst
	 * @param type
	 * @return
	 */
	LoanStatistics query(Long iinst, InstType type);
	/**
	 * 统计平台产品数量
	 * @return
	 */
	BigInteger getLoanPdtCount();
	/**
	 * 统计竟投产品数量
	 * @return
	 */
	BigInteger getDebitBidCount();
	/**
	 * 统计机构申请成功率
	 * @throws Exception 
	 */
	void statisticsSuccessRate() throws Exception;
	void statisticsCurrent();

}
