package newland.iaf.base.risk.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import newland.iaf.base.trans.model.HcTransLogMouth;

/**
 * 
 * @author rabbit
 * 
 */
public interface RiskControlService {

	/**
	 * 生成指定月份的交易汇总
	 * 
	 * @param date
	 */
	void genTransLogMouth(Date date);

	/**
	 * 生成指定范围的交易汇总
	 * 
	 * @param begin
	 * @param end
	 */
	void genTransLogMouth(Date begin, Date end);

	/**
	 * 查询指定商户，月份+-length
	 * 
	 * @param merch
	 * @param index
	 * @param length
	 * @return
	 */
	List<HcTransLogMouth> getTransLogMouth(String merchNo, Date mouth,
			int length);

	/**
	 * 
	 * @param date
	 * @return
	 */
	int getUninstallPosNum(String merchNo, Date date);

	/**
	 * 
	 * @param merchNo
	 * @return
	 */
	boolean hasTransLog(String merchNo);

	/**
	 * 
	 * @param merchNo
	 * @param mouth
	 * @return
	 */
	BigDecimal getHcTransLogAmt(String merchNo, Date mouth);

	/**
	 * 
	 * @param merchNo
	 * @return
	 */
	BigDecimal transLogAmtReduce(String merchNo);
}
