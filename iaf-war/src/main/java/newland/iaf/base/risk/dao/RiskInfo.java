package newland.iaf.base.risk.dao;

import java.math.BigDecimal;
import java.util.List;

import newland.iaf.base.trans.model.HcInstallLog;
import newland.iaf.base.trans.model.HcTransLogMouth;

/**
 * 
 * @author rabbit
 * 
 */
public class RiskInfo {
	/**
	 * 撤机记录数
	 */
	private int uninstallNum;

	/**
	 * 还款逾期记录数
	 */
	private int num;

	/**
	 * 平台内欠款总额
	 */
	private BigDecimal debit;

	/**
	 * 冻结记录
	 */
	private int fezzNum;

	/**
	 * 
	 */
	private BigDecimal rate;

	/**
	 * 上个月交易額
	 */
	private BigDecimal xx;

	/**
	 * 本期应还总额
	 */
	private BigDecimal xxx;

}
