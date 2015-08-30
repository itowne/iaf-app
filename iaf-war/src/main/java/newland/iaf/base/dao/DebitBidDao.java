package newland.iaf.base.dao;

import java.math.BigInteger;
import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.condition.DebitBidCondition;

public interface DebitBidDao {

	/**
	 * 新增借款竞投申请
	 * 
	 * @param debitBid
	 */
	void saveDebitBid(DebitBid debitBid);
	/**
	 * 修改借款竞投申请
	 * 
	 * @param debitBid
	 */
	void updateDebitBid(DebitBid debitBid);
	/**
	 * 根据id查询借款竞投申请
	 * @param id
	 * @return
	 */
	DebitBid getDebitBidById(Long id);	
	/**
	 * 查询多条件查询借款竞投申请
	 * 借款金额(万)，年利率 (%)，借款期限（个月），订单状态，申请发布日期范围
	 * @return
	 */
	List<DebitBid> queryDebitBidByCon(DebitBidCondition debitBidCondition,Page page) throws Exception;
	/**
	 * 查询接口
	 * @param criteriaExecutor
	 * @return 
	 */
	void queryBy(CriteriaExecutor<DebitBid> criteriaExecutor);
	/**
	 * 统计竟投产品
	 * @return
	 */
	BigInteger countDebitBid();
}
