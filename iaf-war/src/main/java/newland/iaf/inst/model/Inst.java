package newland.iaf.inst.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import newland.iaf.base.model.dict.InstStatusType;

import org.hibernate.annotations.Index;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 机构
 * 
 * @author tzy
 * 
 */
@Entity
@Table(name = "t_inst")
@org.hibernate.annotations.Table(appliesTo = "t_inst", indexes = {
		@Index(name = "idx_inst1", columnNames = { "inst_name" }),
		@Index(name = "idx_inst2", columnNames = { "inst_nature" }),
		@Index(name = "idx_inst3", columnNames = { "reg_capital" }) })
public class Inst implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1660365288187047681L;
	/**
	 * 内部编号
	 */
	@Id
	@Column(name = "i_inst", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst")
	private Long iinst;

	/**
	 * 汇卡内部编号()
	 */
	@Column(name = "inst_id", updatable = true)
	private String instId;

	/**
	 * 机构名称
	 */
	@Column(name = "inst_name", nullable = false, updatable = true)
	private String instName;
	/**
	 * 融资性机构经营许可证
	 */
	@Column(name = "loan_permit", nullable = false, updatable = true)
	private String loanPermit;
	/**
	 * 注册时间
	 */
	@Column(name = "reg_time", nullable = false, updatable = true)
	private Date regTime;
	/**
	 * 注册资金
	 */
	@Column(name = "reg_capital", nullable = false, updatable = true)
	private BigDecimal regCapital;
	/**
	 * 营业执照号
	 */
	@Column(name = "busi_license", nullable = true, updatable = true)
	private String busiLicense;
	/**
	 * 税务登记号
	 */
	@Column(name = "tax_reg_no", nullable = true, updatable = true)
	private String taxRegNo;
	/**
	 * 开业时间
	 */
	@Column(name = "open_time", nullable = true, updatable = true)
	private Date openTime;
	/**
	 * 机构性质
	 */
	@Column(name = "inst_nature", nullable = true, updatable = true)
	private String instNature;
	/**
	 * 联系人
	 */
	@Column(name = "contact", nullable = true, updatable = true)
	private String contact;
	/**
	 * 联系电话
	 */
	@Column(name = "contact_phone", nullable = true, updatable = true)
	private String contactPhone;
	/**
	 * 注册地址
	 */
	@Column(name = "reg_addr", nullable = true, updatable = true)
	private String regAddr;
	
	/**
	 * 外文名称
	 */
	@Column(name = "english_name")
	private String englishName;
	/**
	 * 经营范围
	 */
	@Column(name = "busi_region")
	private String busiRegion;
	/**
	 * 公司口号
	 */
	@Column(name = "catchword")
	private String catchword;
	
	/**
	 * 员工数
	 */
	@Column(name = "people_count")
	private String peopleCount;
	
	/**
	 * 官网
	 */
	@Column(name = "official_website")
	private String officialWebsite;
	
	/**
	 * 官方电话（如95555）
	 */
	@Column(name = "short_phone")
	private String shortPhone;
	
	/**
	 * 机构简介
	 */
	@Column(name = "introduce",length=500)
	private String introduce;
	
	/**
	 * 总资产
	 */
	@Column(name = "total_capital", nullable = false, updatable = true)
	private BigDecimal totalCapital;
	/**
	 * 状态
	 */
	@Column(name = "inst_stat", nullable = false, updatable = true)
	private InstStatusType instStat;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;
	/**
	 * 更新时间
	 */
	@Column(name = "upd_time", nullable = false, updatable = true)
	private Date updTime;
	
	/**
	 * 受理成功率
	 */
	@Column (name = "succ_rate")
	private BigDecimal successRate;
	
	@Column (name = "pdt_count")
	private Long pdtCount;

	@Column(name = "logo_i_file")
	private Long logoIfile;
	
	@Column (name = "qq_uid", length = 20)
	private String qqUid;
	
	@Transient
	private InstBusiInfo instBusiInfo;

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iInst) {
		this.iinst = iInst;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getLoanPermit() {
		return loanPermit;
	}

	public void setLoanPermit(String loanPermit) {
		this.loanPermit = loanPermit;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public BigDecimal getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}

	public String getBusiLicense() {
		return busiLicense;
	}

	public void setBusiLicense(String busiLicense) {
		this.busiLicense = busiLicense;
	}

	public String getTaxRegNo() {
		return taxRegNo;
	}

	public void setTaxRegNo(String taxRegNo) {
		this.taxRegNo = taxRegNo;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getInstNature() {
		return instNature;
	}

	public void setInstNature(String instNature) {
		this.instNature = instNature;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public InstStatusType getInstStat() {
		return instStat;
	}

	public void setInstStat(InstStatusType instStat) {
		this.instStat = instStat;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Long getLogoIfile() {
		return logoIfile;
	}

	public void setLogoIfile(Long logoIfile) {
		this.logoIfile = logoIfile;
	}

	public BigDecimal getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(BigDecimal successRate) {
		this.successRate = successRate;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getBusiRegion() {
		return busiRegion;
	}

	public void setBusiRegion(String busiRegion) {
		this.busiRegion = busiRegion;
	}

	public String getCatchword() {
		return catchword;
	}

	public void setCatchword(String catchword) {
		this.catchword = catchword;
	}

	public String getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(String peopleCount) {
		this.peopleCount = peopleCount;
	}

	public String getOfficialWebsite() {
		return officialWebsite;
	}

	public void setOfficialWebsite(String officialWebsite) {
		this.officialWebsite = officialWebsite;
	}

	public String getShortPhone() {
		return shortPhone;
	}

	public void setShortPhone(String shortPhone) {
		this.shortPhone = shortPhone;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Long getPdtCount() {
		return pdtCount;
	}

	public void setPdtCount(Long pdtCount) {
		this.pdtCount = pdtCount;
	}

	public BigDecimal getTotalCapital() {
		return totalCapital;
	}

	public void setTotalCapital(BigDecimal totalCapital) {
		this.totalCapital = totalCapital;
	}

	public String getQqUid() {
		return qqUid;
	}

	public void setQqUid(String qqUid) {
		this.qqUid = qqUid;
	}

	public InstBusiInfo getInstBusiInfo() {
		return instBusiInfo;
	}

	public void setInstBusiInfo(InstBusiInfo instBusiInfo) {
		this.instBusiInfo = instBusiInfo;
	}
	
}
