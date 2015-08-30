package newland.iaf.merch.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import newland.iaf.base.model.dict.CreditType;
import newland.iaf.base.service.ProvinceService;

import org.hibernate.annotations.Index;

/**
 * 
 * 商户
 * 
 * @author wwa
 * 
 */
@Entity
@Table(name = "t_merch")
@org.hibernate.annotations.Table(appliesTo = "t_merch", indexes = {
		@Index(name = "idx_merch1", columnNames = { "merch_name" }),
		@Index(name = "idx_merch2", columnNames = { "merch_no", "merch_type" }),
		@Index(name = "idx_merch3", columnNames = { "reg_time" }) })
public class Merch implements Serializable {

	private static final long serialVersionUID = -2331865012986834429L;
	
	/**
	 * 内部编号
	 */
	@Id
	@TableGenerator(name = "i_merch", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_merch")
	@Column(name = "i_merch", updatable = false)
	private Long imerch;

	/**
	 * 商户ID
	 */
	@Column(name = "merch_no", updatable = false)
	private String merchNo;
	/**
	 * 商户名称
	 */
	@Column(name = "merch_name", length = 200)
	private String merchName;
	/**
	 * 别称
	 */
	@Column(name = "alias", length = 200)
	private String alias;
	/**
	 * 注册编号
	 */
	@Column(name = "reg_no", length = 50)
	private String regNo;
	/**
	 * 注册地址
	 */
	@Column(name = "reg_addr", length = 100)
	private String regAddr;
	/**
	 * 注册时间
	 */
	@Column(name = "reg_time")
	private Date regTime;
	/**
	 * 机具地址
	 */
	@Column(name = "pos_addr", length = 100)
	private String posAddr;
	/**
	 * 营业执照号
	 */
	@Column(name = "businlic", length = 100)
	private String businlic;
	/**
	 * 税务登记号
	 */
	@Column(name = "tax_reg", length = 100)
	private String taxReg;
	/**
	 * 开业时间
	 */
	@Column(name = "open_time")
	private Date openTime;
	/**
	 * 注册资本
	 */
	@Column(name = "reg_cap", length = 100)
	private String regCap;
	/**
	 * 联系人
	 */
	@Column(name = "contract", length = 50)
	private String contract;
	/**
	 * 联系电话
	 */
	@Column(name = "contract_tel", length = 50)
	private String contractTel;
	/**
	 * 邮件地址
	 */
	@Column(name = "email", length = 50)
	private String email;
	/**
	 * 商户类型
	 */
	@Enumerated
	@Column(name = "merch_type", nullable = false)
	private MerchType merchType;
	/**
	 * 信用情况
	 */
	@Column(name = "credit")
	private CreditType credit;
	/**
	 * 所属行业
	 */
	@Column(name = "industry", length = 100)
	private String industry;
	/**
	 * 公司规模
	 */
	@Column(name = "company_size", length = 50)
	private String companySize;
	/**
	 * 地址是否核实
	 */
	@Column(name = "audit_addr", length = 100)
	private String auditAddr;
	/**
	 * 营业执照号是否核实
	 */
	@Column(name = "audir_businlic", length = 100)
	private String audirBusinlic;
	/**
	 * 所属省市
	 */
	@Column(name = "province", length = 100)
	private String province;
	
	@Column (name = "qq_uid", length = 20)
	private String qqUid;
	/**
	 * 录入时间(商户已使用多长时间)
	 */
	@Column (name="original_time",length=100)
	private String originalTime;

	/**
	 * 交易时使用的银行行号
	 */
	@Column (name="bank_code",length=20)
	private String bankCode;
	
	@Column(name="area_name")
	private String areaName;
	
	/**
	 * logo图片
	 */
	@Column(name = "logo_i_file")
	private Long logoIfile;
	
	@Column(name = "debit_i_file")
	private Long debitIFile;
	
	@Column(name = "memo")
	private String memo;
	
	/**
	 * 商户经营数据核查情况
	 */
	@Column(name = "busi_data_verification")
	private String busiDataVerification;
	
	/**
	 * 商户状态  1:有效，0或者其他都为无效
	 */
	@Column(name = "merch_status")
	private String merchStatus;
	
	@Column(name="city_code")
	private String cityCode;
	
	@Transient
	private String cityName;

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long iMerch) {
		this.imerch = iMerch;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
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

	public String getTaxReg() {
		return taxReg;
	}

	public void setTaxReg(String taxReg) {
		this.taxReg = taxReg;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getRegCap() {
		return regCap;
	}

	public void setRegCap(String regCap) {
		this.regCap = regCap;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public MerchType getMerchType() {
		return merchType;
	}

	public String getMerchStatus() {
		return merchStatus;
	}

	public void setMerchStatus(String merchStatus) {
		this.merchStatus = merchStatus;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Long getDebitIFile() {
		return debitIFile;
	}

	public void setDebitIFile(Long debitIFile) {
		this.debitIFile = debitIFile;
	}

	public void setMerchType(MerchType merchType) {
		this.merchType = merchType;
	}

	public CreditType getCredit() {
		return credit;
	}

	public void setCredit(CreditType credit) {
		this.credit = credit;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getAuditAddr() {
		return auditAddr;
	}

	public void setAuditAddr(String auditAddr) {
		this.auditAddr = auditAddr;
	}

	public String getAudirBusinlic() {
		return audirBusinlic;
	}

	public void setAudirBusinlic(String audirBusinlic) {
		this.audirBusinlic = audirBusinlic;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public Long getLogoIfile() {
		return logoIfile;
	}

	public void setLogoIfile(Long logoIfile) {
		this.logoIfile = logoIfile;
	}

	public String getQqUid() {
		return qqUid;
	}

	public void setQqUid(String qqUid) {
		this.qqUid = qqUid;
	}

	public String getOriginalTime() {
		return originalTime;
	}

	public void setOriginalTime(String originalTime) {
		this.originalTime = originalTime;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBusiDataVerification() {
		return busiDataVerification;
	}

	public void setBusiDataVerification(String busiDataVerification) {
		this.busiDataVerification = busiDataVerification;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	
}
