package newland.iaf.base.risk.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import newland.iaf.base.trans.model.HcTransLogMouth;
import newland.iaf.base.trans.model.HcTransLogMouthPK;

/**
 * 
 * @author rabbit
 * 
 */
public interface RiskControlDao {
	/**
	 * 
	 * @param date
	 */
	void insertTransLogMouth(Date date);

	/**
	 * 
	 * @param date
	 */
	void deleteTransLogMouth(Date date);

	/**
	 * 
	 * @param date
	 * @return
	 */
	boolean hasTransLogMouth(Date date);

	/**
	 * 
	 * @param merchNo
	 * @param date
	 * @param amount
	 * @return
	 */
	List<HcTransLogMouth> getList(String merchNo, Date date, int amount);

	/**
	 * 
	 * @param merchNo
	 * @param begin
	 * @param end
	 * @return
	 */
	List<HcTransLogMouth> getList(String merchNo, Date begin, Date end);

	/**
	 * 取得指定商户某日期后的撤机笔数
	 * 
	 * @param merchNo
	 * @param date
	 * @return
	 */
	int countUninstallNum(String merchNo, Date date);

	/**
	 */
	HcTransLogMouth getHcTransLogMouth(HcTransLogMouthPK pk);

	/**
	 * 从date开始往后，是否有交易记录
	 * 
	 * @param merchNo
	 * @param date
	 * @return
	 */
	boolean hasTransLog(String merchNo, Date date);

	/**
	 * 取得指定商户指定月份的交易总额
	 * 
	 * @param merchNo
	 * @param mouth
	 * @return
	 */
	BigDecimal getHcTransLogAmt(String merchNo, Date begin, Date end);
}
