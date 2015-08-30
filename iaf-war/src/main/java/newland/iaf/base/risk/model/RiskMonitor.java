package newland.iaf.base.risk.model;

import java.math.BigDecimal;

public class RiskMonitor {
	/**
	 * 商户在平台内目前欠款总额
	 */
	private BigDecimal debitAmt;
	/**
	 * 商户目前还款逾期记录
	 */
	private Integer expireNum;
	/**
	 * 由于未能按时还款，商户的清算账号已被借贷方冻结
	 */
	private String freeze;
	/**
	 * 上个月的交易额少于本期应还款金额
	 */
	private String refund;
	/**
	 * 连续n天是否有交易记录
	 */
	private String hasTransLog;
	/**
	 * 上月POS交易额比前一个月下降比例
	 */
	private BigDecimal rate;
	/**
	 * 撤机数
	 */
	private Integer uninstallNum;
	
	private BigDecimal amt;
	
	public BigDecimal getDebitAmt() {
		return debitAmt;
	}
	public void setDebitAmt(BigDecimal debitAmt) {
		this.debitAmt = debitAmt;
	}
	public Integer getExpireNum() {
		return expireNum;
	}
	public void setExpireNum(Integer expireNum) {
		this.expireNum = expireNum;
	}
	public String getFreeze() {
		return freeze;
	}
	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public String getHasTransLog() {
		return hasTransLog;
	}
	public void setHasTransLog(String hasTransLog) {
		this.hasTransLog = hasTransLog;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Integer getUninstallNum() {
		return uninstallNum;
	}
	public void setUninstallNum(Integer uninstallNum) {
		this.uninstallNum = uninstallNum;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	} 

}
