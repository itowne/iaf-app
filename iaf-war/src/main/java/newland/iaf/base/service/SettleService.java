package newland.iaf.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import newland.iaf.base.trans.model.HcTransLog;

public interface SettleService {
	
	/**
	 * 根据日期查询未处理定单号
	 * @param date
	 * @return
	 */
	List<String> queryOrdersBy(Date date);
	/**
	 * 完成清算
	 */
	void settle(HcTransLog hcTransLog) throws Exception;
	
	/**
	 * 同步数据
	 * @param date TODO
	 */
	void synchronize(Date date);

}
