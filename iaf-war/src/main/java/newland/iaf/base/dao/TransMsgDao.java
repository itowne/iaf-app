package newland.iaf.base.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.dict.TransStat;

public interface TransMsgDao {
	
	void save(TransMsg msg);
	
	void update(TransMsg msg);
	
	List<TransMsg> query(TransMsg msg);

	TransMsg findById(String orderNo);
	/**
	 * 查询给定日期之前的订单
	 * @param date
	 * @return
	 */
	List<String> queryBeforeDate(Date date, TransStat stat);

	/**
	 * 按订单号与金额查询
	 * @param orderId
	 * @param amount
	 * @return
	 */
	TransMsg findByOthSysNo(String orderId, BigDecimal amount);
	
	/**
	 * 查询时间段内指定状态交易
	 * @param beginDate
	 * @param endDate
	 * @param stat
	 * @return
	 */
	List<TransMsg> queryBy(Date beginDate, Date endDate, TransStat stat);
	/**
	 * 根据汇卡放款还款清算号查询
	 * @param hcOrderNo
	 * @return
	 */
	List<TransMsg> queryByOrderNo(String hcOrderNo);
	
	public List<TransMsg> querySettleRecord();

}
