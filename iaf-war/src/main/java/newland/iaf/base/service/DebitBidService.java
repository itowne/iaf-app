package newland.iaf.base.service;

import java.math.BigInteger;
import java.util.List;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.base.service.impl.MerchSession;

import org.ohuyo.commons.query.criterion.Page;

public interface DebitBidService {
	/**
	 * 新增竟投产品
	 * @param bid
	 * @throws Exception
	 */
	void save(DebitBid bid) throws Exception;
	/**
	 * 更新竟投产品
	 * @param bid
	 * @throws Exception
	 */
	void update(DebitBid bid) throws Exception;

	/**
	 * 根据id查询借款竞投申请
	 * @param id
	 * @return
	 */
	DebitBid getDebitBidById(Long id) throws Exception;
	/**
	 * 分页查询多条件查询借款竞投申请
	 * 借款金额(万)，年利率 (%)，借款期限（个月），订单状态，申请发布日期范围
	 * @return
	 */
	List<DebitBid> queryDebitBidByCon(DebitBidCondition debitBidCondition,Page page) throws Exception;
	
	BigInteger countDebitBid();
	
	/**
	 * 同步方法，更新产品的请求次数
	 * @param loanPdt
	 * @throws Exception
	 */
	void updateDebitBidAcceptTotal(DebitBid debitBid) throws Exception;
}
