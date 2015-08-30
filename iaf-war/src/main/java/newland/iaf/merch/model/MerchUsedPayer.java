package newland.iaf.merch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Index;

/**
 * 商户常用付款人
 * @author lindaqun
 *
 */
@Entity
@Table(name = "t_merch_used_payer")
@org.hibernate.annotations.Table(appliesTo = "t_merch_used_payer", indexes = {
		@Index(name = "idx_merch_used_payer", columnNames = { "i_merch" }) })
public class MerchUsedPayer {
	
	/**
	 * 主键ID
	 */
	@Id
	@TableGenerator(name = "i_merch_used_payer", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_merch_used_payer")
	@Column(name = "i_merch_used_payer", updatable = false)
	private Long imerchUsedPayer;
	
	/**
	 * 商户主键ID
	 */
	@Column(name = "i_merch", updatable = true , nullable=false )
	private Long imerch;
	
	/**
	 * 付款人名称
	 */
	@Column(name = "payer_name", updatable = true , nullable=false )
	private String payerName;
	
	/**
	 * 付款人卡号
	 */
	@Column(name = "payer_card_no", updatable = true , nullable=false )
	private String payerCardNo;
	
	/**
	 * 付款人开户行名称
	 */
	@Column(name = "payer_bank_name", updatable = true , nullable=false )
	private String payerBankName;
	
	@Column(name = "bank_code", updatable = true , nullable=false )
	private String BankCode;
	
	/**
	 * 修改时间
	 */
	@Column(name = "upd_time", nullable=false )
	private Date updTime;

	public Long getImerchUsedPayer() {
		return imerchUsedPayer;
	}

	public void setImerchUsedPayer(Long imerchUsedPayer) {
		this.imerchUsedPayer = imerchUsedPayer;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerCardNo() {
		return payerCardNo;
	}

	public void setPayerCardNo(String payerCardNo) {
		this.payerCardNo = payerCardNo;
	}

	public String getPayerBankName() {
		return payerBankName;
	}

	public void setPayerBankName(String payerBankName) {
		this.payerBankName = payerBankName;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getBankCode() {
		return BankCode;
	}

	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	
	
}
