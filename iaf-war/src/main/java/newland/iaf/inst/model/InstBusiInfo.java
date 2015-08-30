/**
 * 
 */
package newland.iaf.inst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Index;

/**
 * 机构业务资料表信息
 * 
 * @author lindaqun
 * 
 */
@Entity
@Table(name = "t_inst_busi_info")
@org.hibernate.annotations.Table(appliesTo = "t_inst_busi_info", indexes = { @Index(name = "idx_inst_busi_info1", columnNames = { "accept_region" }) })
public class InstBusiInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6431464686463364829L;
	/**
	 * 机构业务资料内部编号
	 */
	@Id
	@Column(name = "i_inst_busi_info", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst_busi_info", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_busi_info")
	private Long iInstBusiInfo;

	/**
	 * 机构内部编号
	 */
	@Column(name = "i_inst", nullable = false, updatable = true)
	private Long iInst;

	/**
	 * 受理地区
	 */
	@Column(name = "accept_region", nullable = true, updatable = true)
	private String acceptRegion;

	/**
	 * 放贷专用账号
	 */
	@Column(name = "loan_account_no", nullable = true, updatable = true)
	private String loanAccountNo;

	/**
	 * 开户行
	 */
	@Column(name = "bank", nullable = true, updatable = true)
	private String bank;

	/**
	 * 银行编号
	 */
	@Column(name = "bank_code", length = 20, nullable = true, updatable = true)
	private String bankCode;

	/**
	 * 放贷持卡人名称
	 */
	@Column(name = "loan_name", nullable = true, updatable = true)
	private String loanName;
	
	/**
	 * 放贷银行行号
	 */
	@Column(name = "loan_bank_code", nullable = true, updatable = true)
	private String loanBankCode;

	/**
	 * 还贷银行卡账号
	 */
	@Column(name = "repayment_no", nullable = true, updatable = true)
	private String repaymentNo;

	/**
	 * 还贷银行开户行
	 */
	@Column(name = "repayment_bank", nullable = true, updatable = true)
	private String repaymentBank;

	/**
	 * 还贷持卡人姓名
	 */
	@Column(name = "repayment_name", nullable = true, updatable = true)
	private String repaymentName;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Long getiInstBusiInfo() {
		return iInstBusiInfo;
	}

	public void setiInstBusiInfo(Long iInstBusiInfo) {
		this.iInstBusiInfo = iInstBusiInfo;
	}

	public Long getiInst() {
		return iInst;
	}

	public void setiInst(Long iInst) {
		this.iInst = iInst;
	}

	public String getAcceptRegion() {
		return acceptRegion;
	}

	public void setAcceptRegion(String acceptRegion) {
		this.acceptRegion = acceptRegion;
	}

	public String getLoanAccountNo() {
		return loanAccountNo;
	}

	public void setLoanAccountNo(String loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getRepaymentNo() {
		return repaymentNo;
	}

	public void setRepaymentNo(String repaymentNo) {
		this.repaymentNo = repaymentNo;
	}

	public String getRepaymentBank() {
		return repaymentBank;
	}

	public void setRepaymentBank(String repaymentBank) {
		this.repaymentBank = repaymentBank;
	}

	public String getRepaymentName() {
		return repaymentName;
	}

	public void setRepaymentName(String repaymentName) {
		this.repaymentName = repaymentName;
	}

	public String getLoanBankCode() {
		return loanBankCode;
	}

	public void setLoanBankCode(String loanBankCode) {
		this.loanBankCode = loanBankCode;
	}
}
