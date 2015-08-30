package newland.iaf.merch.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import newland.iaf.base.model.TransRec;
import newland.iaf.base.model.TransReport;

/**
 * 商户交易记录统计类
 * @author new
 *
 */
public class MerchTransReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TransRec> trList = new ArrayList<TransRec>() ;
	
	private TransRec trTotal;
	
	
	//数据分析
	/**
	 * 单笔交易平均金额
	 */
	private BigDecimal avgTrade;
	/**
	 * 日均收款交易额
	 */
	private BigDecimal avgDayTrade;
	/**
	 * 日均收款交易笔数
	 */
	private BigDecimal avgDayNum;
	/**
	 * 月均收款交易额
	 */
	private BigDecimal avgMonthTrade;
	/**
	 * 月均收款交易笔数
	 */
	private BigDecimal avgMonthNum ;
	/**
	 * 前一个月
	 */
	private TransReport preMonthRecive;
	
	private TransReport preMonthPayment;
	
	private TransReport preMonthLoan;
	
	private TransReport preMonthRefund;
	
	/**
	 * 前三个月
	 */
	private TransReport preThreeMonthRecive;
	
	private TransReport preThreeMonthPayment;
	
	private TransReport preThreeMonthLoan;
	
	private TransReport preThreeMonthRefund;
	
	/**
	 * 前半年
	 */
	private TransReport preHalfYearMonthRecive;
	
	private TransReport preHalfYearMonthPayment;
	
	private TransReport preHalfYearMonthLoan;
	
	private TransReport preHalfYearMonthRefund;

	public TransReport getPreMonthRecive() {
		return preMonthRecive;
	}

	public void setPreMonthRecive(TransReport preMonthRecive) {
		this.preMonthRecive = preMonthRecive;
	}

	public TransReport getPreMonthPayment() {
		return preMonthPayment;
	}

	public void setPreMonthPayment(TransReport preMonthPayment) {
		this.preMonthPayment = preMonthPayment;
	}

	public TransReport getPreMonthLoan() {
		return preMonthLoan;
	}

	public void setPreMonthLoan(TransReport preMonthLoan) {
		this.preMonthLoan = preMonthLoan;
	}

	public TransReport getPreMonthRefund() {
		return preMonthRefund;
	}

	public void setPreMonthRefund(TransReport preMonthRefund) {
		this.preMonthRefund = preMonthRefund;
	}

	public TransReport getPreThreeMonthRecive() {
		return preThreeMonthRecive;
	}

	public void setPreThreeMonthRecive(TransReport preThreeMonthRecive) {
		this.preThreeMonthRecive = preThreeMonthRecive;
	}

	public TransReport getPreThreeMonthPayment() {
		return preThreeMonthPayment;
	}

	public void setPreThreeMonthPayment(TransReport preThreeMonthPayment) {
		this.preThreeMonthPayment = preThreeMonthPayment;
	}

	public TransReport getPreThreeMonthLoan() {
		return preThreeMonthLoan;
	}

	public void setPreThreeMonthLoan(TransReport preThreeMonthLoan) {
		this.preThreeMonthLoan = preThreeMonthLoan;
	}

	public TransReport getPreThreeMonthRefund() {
		return preThreeMonthRefund;
	}

	public void setPreThreeMonthRefund(TransReport preThreeMonthRefund) {
		this.preThreeMonthRefund = preThreeMonthRefund;
	}

	public TransReport getPreHalfYearMonthRecive() {
		return preHalfYearMonthRecive;
	}

	public void setPreHalfYearMonthRecive(TransReport preHalfYearMonthRecive) {
		this.preHalfYearMonthRecive = preHalfYearMonthRecive;
	}

	public TransReport getPreHalfYearMonthPayment() {
		return preHalfYearMonthPayment;
	}

	public void setPreHalfYearMonthPayment(TransReport preHalfYearMonthPayment) {
		this.preHalfYearMonthPayment = preHalfYearMonthPayment;
	}

	public TransReport getPreHalfYearMonthLoan() {
		return preHalfYearMonthLoan;
	}

	public void setPreHalfYearMonthLoan(TransReport preHalfYearMonthLoan) {
		this.preHalfYearMonthLoan = preHalfYearMonthLoan;
	}

	public TransReport getPreHalfYearMonthRefund() {
		return preHalfYearMonthRefund;
	}

	public void setPreHalfYearMonthRefund(TransReport preHalfYearMonthRefund) {
		this.preHalfYearMonthRefund = preHalfYearMonthRefund;
	}

	public List<TransRec> getTrList() {
		return trList;
	}

	public void setTrList(List<TransRec> trList) {
		this.trList = trList;
	}

	public TransRec getTrTotal() {
		return trTotal;
	}

	public void setTrTotal(TransRec trTotal) {
		this.trTotal = trTotal;
	}

	public BigDecimal getAvgTrade() {
		return avgTrade;
	}

	public void setAvgTrade(BigDecimal avgTrade) {
		this.avgTrade = avgTrade;
	}

	public BigDecimal getAvgDayTrade() {
		return avgDayTrade;
	}

	public void setAvgDayTrade(BigDecimal avgDayTrade) {
		this.avgDayTrade = avgDayTrade;
	}

	public BigDecimal getAvgDayNum() {
		return avgDayNum;
	}

	public void setAvgDayNum(BigDecimal avgDayNum) {
		this.avgDayNum = avgDayNum;
	}

	public BigDecimal getAvgMonthTrade() {
		return avgMonthTrade;
	}

	public void setAvgMonthTrade(BigDecimal avgMonthTrade) {
		this.avgMonthTrade = avgMonthTrade;
	}

	public BigDecimal getAvgMonthNum() {
		return avgMonthNum;
	}

	public void setAvgMonthNum(BigDecimal avgMonthNum) {
		this.avgMonthNum = avgMonthNum;
	}
	
}
