package newland.iaf.base.trans.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import newland.iaf.base.format.BeanField;

import org.hibernate.annotations.Index;

/**
 * 交易解析类
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_hc_merch_base_info")
@org.hibernate.annotations.Table(appliesTo = "t_hc_merch_base_info", indexes = {
		@Index(name = "idx_hc_merch_base_info1", columnNames = { "hc_merch_no" }),
		@Index(name = "idx_hc_merch_base_info4", columnNames = { "merch_status" }) })
public class HcMerchBaseInfo {

	/**
	 * 
	 */
	@Id
	@TableGenerator(name = "i_hc_merch_base_info", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_hc_merch_base_info")
	@Column(name = "i_hc_merch_base_info")
	private Long ihcMerchBaseInfo;

	/**
	 * 
	 */
	@Column(name = "serial_no")
	@BeanField(index = 0)
	private Long serialNo;

	/**
	 * 汇卡商户号
	 */
	@Column(name = "hc_merch_no")
	@BeanField(index = 1)
	private String hcMerchNo;

	/**
	 * 商户登陆账号
	 */
	@Column(name = "merch_login_name")
	@BeanField(index = 2)
	private String merchLoginName;

	/**
	 * 商户状态
	 */
	@Column(name = "merch_status")
	@BeanField(index = 3)
	private String merchStatus;

	/**
	 * 商户风险等级
	 */
	@Column(name = "risk_class")
	@BeanField(index = 4)
	private String riskClass;

	/**
	 * 商户名称
	 */
	@Column(name = "merch_name")
	@BeanField(index = 5)
	private String merchName;

	/**
	 * 商户行业类别
	 */
	@BeanField(index = 6)
	@Column(name = "merch_type")
	private String merchType;

	/**
	 * 装机地址
	 */
	@BeanField(index = 7)
	@Column(name = "pos_addr")
	private String posAddr;

	/**
	 * 营业执照号
	 */
	@BeanField(index = 8)
	@Column(name = "businlic")
	private String businlic;

	/**
	 * 注册地址
	 */
	@BeanField(index = 9)
	@Column(name = "reg_addr")
	private String regAddr;

	/**
	 * 结算账号开户姓名
	 */
	@BeanField(index = 10)
	@Column(name = "settle_acct_name")
	private String settleAccountName;

	/**
	 * 结算帐号
	 */
	@BeanField(index = 11)
	@Column(name = "settle_acct_no")
	private String settleAccountNo;

	/**
	 * 结算银行
	 */
	@BeanField(index = 12)
	@Column(name = "settle_bank")
	private String settleBank;

	/**
	 * 注册时间
	 */
	@BeanField(index = 13, pattern = "yyyy-MM-dd")
	@Column(name = "reg_time")
	private Date regTime;

	/**
	 * 注册资本
	 */
	@BeanField(index = 14)
	@Column(name = "reg_cap")
	private String regCap;

	/**
	 * 开业时间
	 */
	@BeanField(index = 15, pattern = "yyyy-MM-dd")
	@Column(name = "open_time")
	private Date openTime;

	/**
	 * 税务登记号
	 */
	@BeanField(index = 16)
	@Column(name = "tax_reg_no")
	private String taxRegNo;

	/**
	 * 法人姓名
	 */
	@BeanField(index = 17)
	@Column(name = "legal_per_name")
	private String legalPerName;

	/**
	 * 法人身份证号
	 */
	@BeanField(index = 18)
	@Column(name = "legal_per_cerd_id")
	private String legalPerCerdId;

	/**
	 * 联系人
	 */
	@BeanField(index = 19)
	@Column(name = "contract")
	private String contract;

	/**
	 * 联系电话
	 */
	@BeanField(index = 20)
	@Column(name = "contract_tel")
	private String contractTel;

	/**
	 * 商户性质
	 */
	@BeanField(index = 21)
	@Column(name = "nature")
	private String nature;
	
	/**
	 * 录入时间(商户已使用多长时间)
	 */
	@BeanField(index = 22)
	@Column(name = "original_time")
	private String originalTime;
	
	/**
	 * 交易时使用银行行号
	 * @return
	 */
	@BeanField(index = 23)
	@Column(name = "bank_code")
	private String bankCode;
	
	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	public String getMerchLoginName() {
		return merchLoginName;
	}

	public void setMerchLoginName(String merchLoginName) {
		this.merchLoginName = merchLoginName;
	}

	public String getMerchStatus() {
		return merchStatus;
	}

	public void setMerchStatus(String merchStatus) {
		this.merchStatus = merchStatus;
	}

	public String getRiskClass() {
		return riskClass;
	}

	public void setRiskClass(String riskClass) {
		this.riskClass = riskClass;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getMerchType() {
		return merchType;
	}

	public void setMerchType(String merchType) {
		this.merchType = merchType;
	}

	public String getPosAddr() {
		return posAddr;
	}

	public void setPosAddr(String posAddr) {
		this.posAddr = posAddr;
	}

	public String getBusinlic() {
		return businlic;
	}

	public void setBusinlic(String businlic) {
		this.businlic = businlic;
	}

	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	public String getSettleAccountNo() {
		return settleAccountNo;
	}

	public void setSettleAccountNo(String settleAccountNo) {
		this.settleAccountNo = settleAccountNo;
	}

	public String getSettleBank() {
		return settleBank;
	}

	public void setSettleBank(String settleBank) {
		this.settleBank = settleBank;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getRegCap() {
		return regCap;
	}

	public void setRegCap(String regCap) {
		this.regCap = regCap;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getTaxRegNo() {
		return taxRegNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public void setTaxRegNo(String taxRegNo) {
		this.taxRegNo = taxRegNo;
	}

	public String getLegalPerName() {
		return legalPerName;
	}

	public void setLegalPerName(String legalPerName) {
		this.legalPerName = legalPerName;
	}

	public String getLegalPerCerdId() {
		return legalPerCerdId;
	}

	public void setLegalPerCerdId(String legalPerCerdId) {
		this.legalPerCerdId = legalPerCerdId;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getContractTel() {
		return contractTel;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getHcMerchNo() {
		return hcMerchNo;
	}

	public void setHcMerchNo(String hcMerchNo) {
		this.hcMerchNo = hcMerchNo;
	}

	public String getSettleAccountName() {
		return settleAccountName;
	}

	public void setSettleAccountName(String settleAccountName) {
		this.settleAccountName = settleAccountName;
	}

	public String getOriginalTime() {
		return originalTime;
	}

	public void setOriginalTime(String originalTime) {
		this.originalTime = originalTime;
	}

}
