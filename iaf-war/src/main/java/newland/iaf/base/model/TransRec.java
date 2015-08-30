package newland.iaf.base.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 2013-10-08 信用报告 交易记录对象
 * 
 * @author Mr.Towne
 * 
 */
public class TransRec implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7247677662851993185L;

	/**
	 * 日期
	 */
	private String date;
	// ===金额===
	/**
	 * 银行卡收款流水
	 */
	private BigDecimal bankCardTrans = BigDecimal.ZERO;
	/**
	 * 现金收款流水
	 */
	private BigDecimal cashTrans = BigDecimal.ZERO;
	/**
	 * 付款流水
	 */
	private BigDecimal payTrans = BigDecimal.ZERO;
	/**
	 * 平台内贷款流水
	 */
	private BigDecimal loanTrans = BigDecimal.ZERO;
	/**
	 * 平台内还款流水
	 */
	private BigDecimal refundTrans = BigDecimal.ZERO;
	// ===笔数===
	private Long bankCardCount = new Long(0);
	/**
	 * 现金收款流水
	 */
	private Long cashCount = new Long(0);
	/**
	 * 付款流水
	 */
	private Long payCount = new Long(0);
	/**
	 * 平台内贷款流水
	 */
	private Long loanCount = new Long(0);
	/**
	 * 平台内还款流水
	 */
	private Long refundCount = new Long(0);
	// ===日均===
	/**
	 * 银行卡流水
	 */
	private Double bankCardAvg = new Double(0);
	/**
	 * 现金收款流水
	 */
	private Double cashAvg = new Double(0);
	/**
	 * 付款流水
	 */
	private Double payAvg = new Double(0);
	/**
	 * 平台内贷款流水
	 */
	private Double loanAvg = new Double(0);
	/**
	 * 平台内还款流水
	 */
	private Double refundAvg = new Double(0);
	//===汇总统计===
	//总金额
	/**
	 * 银行卡流水
	 */
	private BigDecimal bankCardTotal=BigDecimal.ZERO;
	/**
	 * 现金流水
	 */
	private BigDecimal cashTotal=BigDecimal.ZERO;
	/**
	 * 付款流水
	 */
	private BigDecimal payTotal = BigDecimal.ZERO;
	/**
	 * 平台内贷款流水
	 */
	private BigDecimal loanTotal = BigDecimal.ZERO;
	/**
	 * 平台内还款流水
	 */
	private BigDecimal refundTotal = BigDecimal.ZERO;
	
	private BigDecimal amountTotal=BigDecimal.ZERO;
	//笔数
	/**
	 * 银行卡流水
	 */
	private Long bankCardTotalCount = new Long(0);
	/**
	 * 现金流水
	 */
	private Long cashTotalCount=new Long(0);
	/**
	 * 付款流水
	 */
	private Long payTotalCount = new Long(0);
	/**
	 * 平台内贷款流水
	 */
	private Long loanTotalCount = new Long(0);
	/**
	 * 平台内还款流水
	 */
	private Long refundTotalCount = new Long(0);
	
	private Long conutTotal = new Long(0);
	//日均
	/**
	 * 银行卡流水
	 */
	private Double bankCardTotalAvg = new Double(0);
	/**
	 * 现金流水
	 */
	private Double cashTotalAvg=new Double(0);
	/**
	 * 付款流水
	 */
	private Double payTotalAvg = new Double(0);
	/**
	 * 平台内贷款流水
	 */
	private Double loanTotalAvg = new Double(0);
	/**
	 * 平台内还款流水
	 */
	private Double refundTotalAvg = new Double(0);
	
	private Double dailyAvgTotal = new Double(0);
	//月均
	/**
	 * 银行卡流水
	 */
	private BigDecimal bankCardTotalMonth = BigDecimal.ZERO;
	/**
	 * 现金流水
	 */
	private BigDecimal cashTotalMonth=BigDecimal.ZERO;
	/**
	 * 付款流水
	 */
	private BigDecimal payTotalMonth = BigDecimal.ZERO;
	/**
	 * 平台内贷款流水
	 */
	private BigDecimal loanTotalMonth = BigDecimal.ZERO;
	/**
	 * 平台内还款流水
	 */
	private BigDecimal refundTotalMonth = BigDecimal.ZERO;
	
	private BigDecimal monthTotal=BigDecimal.ZERO;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getBankCardTrans() {
		return bankCardTrans;
	}

	public void setBankCardTrans(BigDecimal bankCardTrans) {
		this.bankCardTrans = bankCardTrans;
	}

	public BigDecimal getCashTrans() {
		return cashTrans;
	}

	public void setCashTrans(BigDecimal cashTrans) {
		this.cashTrans = cashTrans;
	}

	public BigDecimal getPayTrans() {
		return payTrans;
	}

	public void setPayTrans(BigDecimal payTrans) {
		this.payTrans = payTrans;
	}

	public BigDecimal getLoanTrans() {
		return loanTrans;
	}

	public void setLoanTrans(BigDecimal loanTrans) {
		this.loanTrans = loanTrans;
	}

	public BigDecimal getRefundTrans() {
		return refundTrans;
	}

	public void setRefundTrans(BigDecimal refundTrans) {
		this.refundTrans = refundTrans;
	}

	public Long getBankCardCount() {
		return bankCardCount;
	}

	public void setBankCardCount(Long bankCardCount) {
		this.bankCardCount = bankCardCount;
	}

	public Long getCashCount() {
		return cashCount;
	}

	public void setCashCount(Long cashCount) {
		this.cashCount = cashCount;
	}

	public Long getPayCount() {
		return payCount;
	}

	public void setPayCount(Long payCount) {
		this.payCount = payCount;
	}

	public Long getLoanCount() {
		return loanCount;
	}

	public void setLoanCount(Long loanCount) {
		this.loanCount = loanCount;
	}

	public Long getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Long refundCount) {
		this.refundCount = refundCount;
	}

	public Double getBankCardAvg() {
		return bankCardAvg;
	}

	public void setBankCardAvg(Double bankCardAvg) {
		this.bankCardAvg = bankCardAvg;
	}

	public Double getCashAvg() {
		return cashAvg;
	}

	public void setCashAvg(Double cashAvg) {
		this.cashAvg = cashAvg;
	}

	public Double getPayAvg() {
		return payAvg;
	}

	public void setPayAvg(Double payAvg) {
		this.payAvg = payAvg;
	}

	public Double getLoanAvg() {
		return loanAvg;
	}

	public void setLoanAvg(Double loanAvg) {
		this.loanAvg = loanAvg;
	}

	public Double getRefundAvg() {
		return refundAvg;
	}

	public void setRefundAvg(Double refundAvg) {
		this.refundAvg = refundAvg;
	}

	public BigDecimal getBankCardTotal() {
		return bankCardTotal;
	}

	public void setBankCardTotal(BigDecimal bankCardTotal) {
		this.bankCardTotal = bankCardTotal;
	}

	public BigDecimal getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(BigDecimal cashTotal) {
		this.cashTotal = cashTotal;
	}

	public BigDecimal getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(BigDecimal payTotal) {
		this.payTotal = payTotal;
	}

	public BigDecimal getLoanTotal() {
		return loanTotal;
	}

	public void setLoanTotal(BigDecimal loanTotal) {
		this.loanTotal = loanTotal;
	}

	public BigDecimal getRefundTotal() {
		return refundTotal;
	}

	public void setRefundTotal(BigDecimal refundTotal) {
		this.refundTotal = refundTotal;
	}

	public Long getBankCardTotalCount() {
		return bankCardTotalCount;
	}

	public void setBankCardTotalCount(Long bankCardTotalCount) {
		this.bankCardTotalCount = bankCardTotalCount;
	}

	public Long getCashTotalCount() {
		return cashTotalCount;
	}

	public void setCashTotalCount(Long cashTotalCount) {
		this.cashTotalCount = cashTotalCount;
	}

	public Long getPayTotalCount() {
		return payTotalCount;
	}

	public void setPayTotalCount(Long payTotalCount) {
		this.payTotalCount = payTotalCount;
	}

	public Long getLoanTotalCount() {
		return loanTotalCount;
	}

	public void setLoanTotalCount(Long loanTotalCount) {
		this.loanTotalCount = loanTotalCount;
	}

	public Long getRefundTotalCount() {
		return refundTotalCount;
	}

	public void setRefundTotalCount(Long refundTotalCount) {
		this.refundTotalCount = refundTotalCount;
	}

	public Double getBankCardTotalAvg() {
		return bankCardTotalAvg;
	}

	public void setBankCardTotalAvg(Double bankCardTotalAvg) {
		this.bankCardTotalAvg = bankCardTotalAvg;
	}

	public Double getCashTotalAvg() {
		return cashTotalAvg;
	}

	public void setCashTotalAvg(Double cashTotalAvg) {
		this.cashTotalAvg = cashTotalAvg;
	}

	public Double getPayTotalAvg() {
		return payTotalAvg;
	}

	public void setPayTotalAvg(Double payTotalAvg) {
		this.payTotalAvg = payTotalAvg;
	}

	public Double getLoanTotalAvg() {
		return loanTotalAvg;
	}

	public void setLoanTotalAvg(Double loanTotalAvg) {
		this.loanTotalAvg = loanTotalAvg;
	}

	public Double getRefundTotalAvg() {
		return refundTotalAvg;
	}

	public void setRefundTotalAvg(Double refundTotalAvg) {
		this.refundTotalAvg = refundTotalAvg;
	}

	public BigDecimal getBankCardTotalMonth() {
		return bankCardTotalMonth;
	}

	public void setBankCardTotalMonth(BigDecimal bankCardTotalMonth) {
		this.bankCardTotalMonth = bankCardTotalMonth;
	}

	public BigDecimal getCashTotalMonth() {
		return cashTotalMonth;
	}

	public void setCashTotalMonth(BigDecimal cashTotalMonth) {
		this.cashTotalMonth = cashTotalMonth;
	}

	public BigDecimal getPayTotalMonth() {
		return payTotalMonth;
	}

	public void setPayTotalMonth(BigDecimal payTotalMonth) {
		this.payTotalMonth = payTotalMonth;
	}

	public BigDecimal getLoanTotalMonth() {
		return loanTotalMonth;
	}

	public void setLoanTotalMonth(BigDecimal loanTotalMonth) {
		this.loanTotalMonth = loanTotalMonth;
	}

	public BigDecimal getRefundTotalMonth() {
		return refundTotalMonth;
	}

	public void setRefundTotalMonth(BigDecimal refundTotalMonth) {
		this.refundTotalMonth = refundTotalMonth;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public Long getConutTotal() {
		return conutTotal;
	}

	public void setConutTotal(Long conutTotal) {
		this.conutTotal = conutTotal;
	}

	public Double getDailyAvgTotal() {
		return dailyAvgTotal;
	}

	public void setDailyAvgTotal(Double dailyAvgTotal) {
		this.dailyAvgTotal = dailyAvgTotal;
	}

	public BigDecimal getMonthTotal() {
		return monthTotal;
	}

	public void setMonthTotal(BigDecimal monthTotal) {
		this.monthTotal = monthTotal;
	}
}
