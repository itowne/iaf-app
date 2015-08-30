package newland.iaf.base.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import newland.iaf.base.model.dict.PdtType;

import org.hibernate.annotations.Index;

/**
 * 订单相关操作流水
 * 
 * @author new
 * 
 */
@Entity
@Table (name = "t_ord_operlog")
@org.hibernate.annotations.Table(appliesTo = "t_ord_operlog", indexes = { 
		@Index(name = "idx_ordoper_log1", columnNames = { "i_loan_ord"})
		})
public class OrdOperLog extends OperLog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 定单编号
	 */
	@Column (name = "i_loan_ord", nullable = false)
	Long iloanOrd;
	/**
	 * 订单编码
	 */
	@Column (name = "loan_ord_id", length = 12, nullable = false)
	String loanOrdId;
	/**
	 * 产品内部编号
	 */
	@Column (name = "i_loan_pdt", nullable = false)
	Long iloanPdt;
	/**
	 * 产品编号
	 */
	@Column (name = "loan_pdt_id", length = 8, nullable = false)
	String loanPdtId;
	/**
	 * 产品类型
	 */
	@Column (name = "pdt_type", insertable = true, updatable = true, nullable = false)
	@Enumerated
	PdtType pdtType;
	/**
	 * 交易金额
	 */
	@Column (name = "amount", nullable = false)
	BigDecimal amount;
	/**
	 * 订单期数
	 */
	@Column (name = "term")
	Integer term;
	/**
	 * 订单利率
	 */
	@Column (name = "year_rate")
	BigDecimal yearRate;
	/**
	 * 交易期号
	 */
	@Column (name = "period")
	String period;
	/**
	 * 其它跟踪号
	 */
	@Column (name = "oth_trace_no", length = 32)
	String otherTraceNo;

	public Long getiLoanOrd() {
		return iloanOrd;
	}

	public Long getiLoanPdt() {
		return iloanPdt;
	}

	public PdtType getPdtType() {
		return pdtType;
	}

	public Integer getTerm() {
		return term;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public String getPeriod() {
		return period;
	}

	public void setIloanOrd(Long iLoanOrd) {
		this.iloanOrd = iLoanOrd;
	}

	public void setIloanPdt(Long iloanPdt) {
		this.iloanPdt = iloanPdt;
	}

	public void setPdtType(PdtType pdtType) {
		this.pdtType = pdtType;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getIloanPdt() {
		return iloanPdt;
	}

	public String getLoanPdtId() {
		return loanPdtId;
	}

	public void setLoanPdtId(String loanPdtId) {
		this.loanPdtId = loanPdtId;
	}

	public Long getIloanOrd() {
		return iloanOrd;
	}

	public String getLoanOrdId() {
		return loanOrdId;
	}

	public void setLoanOrdId(String loanOrdId) {
		this.loanOrdId = loanOrdId;
	}

	public String getOtherTraceNo() {
		return otherTraceNo;
	}

	public void setOtherTraceNo(String otherTraceNo) {
		this.otherTraceNo = otherTraceNo;
	}

}
