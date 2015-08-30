package newland.iaf.base.json.transLog;

import java.math.BigDecimal;
import java.util.Date;

import newland.iaf.base.format.BeanField;

/**
 * 交易记录
 * 
 * @author rabbit
 * 
 */
public class Rec {
	/**
	 * 发卡机构
	 */
	@BeanField
	private String cardIssuer;

	/**
	 * 卡号范围
	 */
	@BeanField
	private String cardRange;

	/**
	 * 商户
	 */
	@BeanField
	private String goldKeeperNo;

	/**
	 * 金额
	 */
	@BeanField
	private BigDecimal amt;

	/**
	 * 收单机构
	 */
	@BeanField
	private String acqIssuer;

	/**
	 * 交易日期
	 */
	@BeanField
	private Date time;

	public String getCardIssuer() {
		return cardIssuer;
	}

	public void setCardIssuer(String cardIssuer) {
		this.cardIssuer = cardIssuer;
	}

	public String getCardRange() {
		return cardRange;
	}

	public void setCardRange(String cardRange) {
		this.cardRange = cardRange;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getAcqIssuer() {
		return acqIssuer;
	}

	public void setAcqIssuer(String acqIssuer) {
		this.acqIssuer = acqIssuer;
	}

	public String getGoldKeeperNo() {
		return goldKeeperNo;
	}

	public void setGoldKeeperNo(String goldKeeperNo) {
		this.goldKeeperNo = goldKeeperNo;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}