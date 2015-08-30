package newland.iaf.base.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 放款对账生成excel 内容对象
 * @author Mr.Towne
 *
 */
public class CreditExcel {

	/**
	 * 放款流水号
	 */
	private String traceNo;
	
	/**
	 * 支付订单号
	 */
	private String otherSysTraceNo;
	
	/**
	 * 货款订单号
	 */
	private String loanOrdId;
	
	/**
	 * 放款方汇卡商户号
	 */
	private String merchNo;
	
	/**
	 * 放款机构
	 */
	private String instName;
	
	/**
	 * 收款商户号
	 */
	private String receiveMerchNo;
	/**
	 * 收款商户名
	 */
	private String receiveMerchName;
	/**
	 * 放款金额
	 */
	private BigDecimal creditQuota;
	
	/**
	 * 交易手续费
	 */
	private BigDecimal tradeCharge;
	
	/**
	 * 交易金额
	 */
	private BigDecimal tradeQuota;
	
	/**
	 * 放款交易日期
	 */
	private Date creditDate;
	
	/**
	 * 收款卡帐号
	 */
	private String accountNo;
	
	/**
	 * 收款卡账户名
	 */
	private String accountName;
	
	/**
	 * 收款卡开户行名
	 */
	private String acountBankName;
	
	/**
	 * 收款卡开户行号
	 */
	private String accountBankNo;
	
	/**
	 * 状态
	 */
	private String stat;

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getOtherSysTraceNo() {
		return otherSysTraceNo;
	}

	public void setOtherSysTraceNo(String otherSysTraceNo) {
		this.otherSysTraceNo = otherSysTraceNo;
	}

	public String getLoanOrdId() {
		return loanOrdId;
	}

	public void setLoanOrdId(String loanOrdId) {
		this.loanOrdId = loanOrdId;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getReceiveMerchName() {
		return receiveMerchName;
	}

	public void setReceiveMerchName(String receiveMerchName) {
		this.receiveMerchName = receiveMerchName;
	}

	public BigDecimal getCreditQuota() {
		return creditQuota;
	}

	public void setCreditQuota(BigDecimal creditQuota) {
		this.creditQuota = creditQuota;
	}

	public BigDecimal getTradeCharge() {
		return tradeCharge;
	}

	public void setTradeCharge(BigDecimal tradeCharge) {
		this.tradeCharge = tradeCharge;
	}

	public BigDecimal getTradeQuota() {
		return tradeQuota;
	}

	public void setTradeQuota(BigDecimal tradeQuota) {
		this.tradeQuota = tradeQuota;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAcountBankName() {
		return acountBankName;
	}

	public void setAcountBankName(String acountBankName) {
		this.acountBankName = acountBankName;
	}

	public String getAccountBankNo() {
		return accountBankNo;
	}

	public void setAccountBankNo(String accountBankNo) {
		this.accountBankNo = accountBankNo;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getReceiveMerchNo() {
		return receiveMerchNo;
	}

	public void setReceiveMerchNo(String receiveMerchNo) {
		this.receiveMerchNo = receiveMerchNo;
	}
}
