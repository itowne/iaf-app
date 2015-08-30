package newland.iaf.base.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import newland.iaf.base.model.dict.PdtType;
@Entity
@Table(name = "t_pdt_operlog")
public class PdtOperLog extends OperLog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品内部编号
	 */
	@Column (name = "i_pdt", nullable = false)
	private Long ipdt;
	/**
	 * 产品编码
	 */
	@Column (name = "pdt_id", length = 8, nullable = false)
	private String pdtId;
	/**
	 * 产品类型
	 */
	@Enumerated
	@Column (name = "pdt_type")
	private PdtType pdtType;
	/**
	 * 产品名称
	 */
	@Column (name = "pdt_name", length = 30)
	private String pdtName;
	/**
	 * 贷款周期
	 */
	@Column (name = "term")
	private Integer term;
	/**
	 * 贷款利率
	 */
	@Column (name = "year_rate")
	private BigDecimal yearRate;
	/**
	 * 最小金额
	 */
	@Column (name = "min_amount")
	private BigDecimal minAmount;
	/**
	 * 最大金额
	 */
	@Column (name = "max_amount")
	private BigDecimal maxAmount;
	/**
	 * 用途
	 */
	@Column (name = "purpose", length = 200)
	private String purpose;
	public Long getIpdt() {
		return ipdt;
	}
	public String getPdtId() {
		return pdtId;
	}
	public PdtType getPdtType() {
		return pdtType;
	}
	public String getPdtName() {
		return pdtName;
	}
	public Integer getTerm() {
		return term;
	}
	public BigDecimal getYearRate() {
		return yearRate;
	}
	public BigDecimal getMinAmount() {
		return minAmount;
	}
	public BigDecimal getMaxAmount() {
		return maxAmount;
	}
	public void setIpdt(Long ipdt) {
		this.ipdt = ipdt;
	}
	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
	}
	public void setPdtType(PdtType pdtType) {
		this.pdtType = pdtType;
	}
	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}
	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}
	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

}
