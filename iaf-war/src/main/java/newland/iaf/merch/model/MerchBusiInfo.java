package newland.iaf.merch.model;

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
 * 
 * 商户业务资料
 * 
 * @author wwa
 * 
 */
@Entity
@Table(name = "t_merch_busi_info")
@org.hibernate.annotations.Table(appliesTo = "t_merch_busi_info", indexes = {
		@Index(name = "idx_merch_busi_info1", columnNames = { "bank" }),
		@Index(name = "idx_merch_busi_info2", columnNames = { "account_no" }) })
public class MerchBusiInfo implements Serializable {

	private static final long serialVersionUID = -786802910227987855L;

	/**
	 * 内部编号
	 */
	@Id
	@TableGenerator(name = "i_merch_busi_info", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_merch_busi_info")
	@Column(name = "i_merch_busi_info", updatable = false)
	private Long iMerchBusiInfo;

	@Column(name = "i_merch", nullable = false, updatable = false)
	private Long imerch;
	/**
	 * 结算银行
	 */
	@Column(name = "bank")
	private String bank;

	/**
	 * 贷款资金划入账号
	 */
	@Column(name = "account_no")
	private String accountNo;

	/**
	 * 贷款账号名
	 */
	@Column(name = "account_name")
	private String accountName;
	
	/**
	 * 商户性质
	 */
	@Column(name = "merch_natrue")
	private String merchNatrue;
	
	/**
	 * 银行编号
	 */
	@Column(name = "bank_code", length = 20, nullable = true, updatable = true)
	private String bankCode;

	public Long getiMerchBusiInfo() {
		return iMerchBusiInfo;
	}

	public void setiMerchBusiInfo(Long iMerchBusiInfo) {
		this.iMerchBusiInfo = iMerchBusiInfo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getMerchNatrue() {
		return merchNatrue;
	}

	public void setMerchNatrue(String merchNatrue) {
		this.merchNatrue = merchNatrue;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
