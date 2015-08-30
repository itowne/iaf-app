package newland.iaf.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.RefundType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.model.dict.YesOrNoType;
import newland.iaf.inst.model.Inst;

import org.hibernate.annotations.Index;

/**
 * 贷款产品
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_loan_pdt")
@org.hibernate.annotations.Table(appliesTo = "t_loan_pdt", indexes = {
		@Index(name = "idx_load_pdt1", columnNames = { "min_quota" }),
		@Index(name = "idx_load_pdt2", columnNames = { "max_quota" }),
		@Index(name = "idx_load_pdt3", columnNames = { "min_term" }),
		@Index(name = "idx_load_pdt4", columnNames = { "max_term" }),
		@Index(name = "idx_load_pdt5", columnNames = { "upd_time" }) })
public class LoanPdt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 944245037988922335L;

	/**
	 * 贷款产品内部编号
	 */
	@Id
	@TableGenerator(name = "i_loan_pdt", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_loan_pdt")
	@Column(name = "i_loan_pdt", updatable = false)
	private Long iloanPdt;

	@Column(name = "i_loan_pdt_his", nullable = false)
	private Long iloanPdtHis;

	/**
	 * 
	 */
	@Column(name = "load_pdt_id", updatable = false)
	private String loadPdtId;
	/**
	 * 产品名称
	 */
	@Column(name = "pdt_name", nullable = false)
	private String pdtName;

	/**
	 * 机构代号
	 */
	@Column(name = "i_inst", nullable = false, updatable = false)
	private Long iinst;
	
	/**
	 * 机构名称
	 */
//	@Column(name = "inst_name", nullable = true)
//	private String instName;

	/**
	 * 贷款最低金额
	 */
	@Column(name = "min_quota", nullable = false, updatable = true)
	private BigDecimal minQuota;

	/**
	 * 最低金额万元格式
	 */
	@Transient
	private BigDecimal wangyuanMinQuota;
	@Transient
	private BigDecimal wangyuanMaxQuota;

	/**
	 * 贷款最高金额
	 */
	@Column(name = "max_quota", nullable = false, updatable = true)
	private BigDecimal maxQuota;

	/**
	 * 贷款最短周期
	 */
	@Column(name = "min_term", nullable = false, updatable = true)
	private Integer minTerm;

	/**
	 * 贷款最长周期
	 */
	@Column(name = "max_term", nullable = false, updatable = true)
	private Integer maxTerm;
	
	@Column(name = "minTermType",updatable = true, length = 11)
	private TermType minTermType; 
	
	@Column(name = "maxTermType",updatable = true, length = 11)
	private TermType maxTermType;

	/**
	 * 年利率 rate of interest per annum
	 */
	@Column(name = "rate", nullable = false, updatable = true)
	private BigDecimal rate;

	@Transient
	private String rateUi;

	@Transient
	private String termUi;

	@Transient
	private String quotaUi;
	
	@Transient
	private String pdtRate;
	
	@Transient
	private String pdtMinTerm;
	
	@Transient
	private String pdtMaxTerm;

	/**
	 * 放贷周期
	 */
	@Column(name = "credit_term", nullable = false, updatable = true)
	private Integer creditTerm;

	/**
	 * 放贷受理地区范围
	 */
	@Column(name = "region", nullable = false, updatable = true, length = 6)
	private String region;

//	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = Province.class)
//	@JoinColumn(name = "region", insertable = false, updatable = false)
//	private Province province;

	@Column(name = "province_code", nullable = false, updatable = true, length = 25)
	private String provinceCode;

	@Column(name = "area", nullable = false, updatable = true, length = 50)
	private String area;
	/**
	 * 还款方式
	 * 
	 * @TODO 待确认
	 */
	@Column(name = "repayment", updatable = true, length = 20)
	private RefundType repayment;
	
	@Column(name = "rateType", updatable = true, length = 11)
	private RateType rateType;

	/**
	 * 产品特点的简单描述（有长度限制）
	 * 
	 */
	@Column(name = "introduce", nullable = true, updatable = true, length = 200)
	private String introduce;

	/**
	 * 产品特点
	 */
	@Column(name = "feature", nullable = false, updatable = true)
	private String feature;

	/**
	 * 申请条件
	 */
	@Column(name = "req_cond")
	private String condition;

	/**
	 * 申请次数
	 */
	@Column(name = "req_total")
	private Long reqTotal;

	/**
	 * 申请所需材料
	 */
	@Column(name = "rereq")
	private String cl;

	/**
	 * 版本
	 */
	@Column(name = "version")
	private Long version;

	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	/**
	 * 产品状态
	 */
	@Enumerated
	@Column(name = "pdt_stat", nullable = false)
	private PdtStat pdtStatus;

	/**
	 * 更新时间
	 */
	@Column(name = "upd_time", nullable = false, updatable = true)
	private Date updTime;

	/**
	 * 是否删除 ，YES:已删除，NO:未删除
	 */
	@Column(name = "delete_flag", updatable = true)
	private YesOrNoType deleteFlag;

	/**
	 * 大图id用作 首页 我要借款显示
	 */
	@Column(name = "logo_i_file")
	private Long logoIfile;
	/**
	 * 小图用作首页显示
	 */
	@Column(name = "pdt_i_file")
	private Long pdtIfile;

	@Transient
	private Inst inst;

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public Long getIloanPdt() {
		return iloanPdt;
	}

	public void setIloanPdt(Long iloanPdt) {
		this.iloanPdt = iloanPdt;
	}

	public String getPdtName() {
		return pdtName;
	}

	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}

	public TermType getMinTermType() {
		return minTermType;
	}

	public void setMinTermType(TermType minTermType) {
		this.minTermType = minTermType;
	}

	public String getPdtMinTerm() {
		return minTerm+minTermType.getDesc();
	}

	public String getPdtMaxTerm() {
		return maxTerm+maxTermType.getDesc();
	}

	public TermType getMaxTermType() {
		return maxTermType;
	}

	public void setMaxTermType(TermType maxTermType) {
		this.maxTermType = maxTermType;
	}

	//约定：取值的时候四舍五入保留整数
	public BigDecimal getMinQuota() {
		DecimalFormat df = new DecimalFormat("0");
		String s = df.format(minQuota);
		minQuota = new BigDecimal(s);
		return minQuota;
	}
	
	public void setMinQuota(BigDecimal minQuota) {
		this.minQuota = minQuota;
	}

	//约定：取值的时候四舍五入保留整数
	public BigDecimal getMaxQuota() {
		DecimalFormat df = new DecimalFormat("0");
		String s = df.format(maxQuota);
		maxQuota = new BigDecimal(s);
		return maxQuota;
	}

	public void setMaxQuota(BigDecimal maxQuota) {
		this.maxQuota = maxQuota;
	}

	public Integer getMinTerm() {
		return minTerm;
	}

	public void setMinTerm(Integer minTerm) {
		this.minTerm = minTerm;
	}

	public Integer getMaxTerm() {
		return maxTerm;
	}

	public void setMaxTerm(Integer maxTerm) {
		this.maxTerm = maxTerm;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getCreditTerm() {
		return creditTerm;
	}

	public void setCreditTerm(Integer creditTerm) {
		this.creditTerm = creditTerm;
	}

	public Long getPdtIfile() {
		return pdtIfile;
	}

	public void setPdtIfile(Long pdtIfile) {
		this.pdtIfile = pdtIfile;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iInst) {
		this.iinst = iInst;
	}

//	public String getInstName() {
//		return instName;
//	}
//
//	public void setInstName(String instName) {
//		this.instName = instName;
//	}

	public String getLoadPdtId() {
		return loadPdtId;
	}

	public void setLoadPdtId(String loadPdtId) {
		this.loadPdtId = loadPdtId;
	}

	public PdtStat getPdtStatus() {
		return pdtStatus;
	}

	public void setPdtStatus(PdtStat pdtStatus) {
		this.pdtStatus = pdtStatus;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public YesOrNoType getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(YesOrNoType deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Long getIloanPdtHis() {
		return iloanPdtHis;
	}

	public void setIloanPdtHis(Long iloanPdtHis) {
		this.iloanPdtHis = iloanPdtHis;
	}

	public Long getLogoIfile() {
		return logoIfile;
	}

	public void setLogoIfile(Long logoIfile) {
		this.logoIfile = logoIfile;
	}

	public BigDecimal getWangyuanMinQuota() {
		wangyuanMinQuota = this.minQuota.divide(new BigDecimal(10000));
		DecimalFormat df = new DecimalFormat("0");
		String s = df.format(wangyuanMinQuota);
		wangyuanMinQuota = new BigDecimal(s);
		return wangyuanMinQuota;
	}

	public void setWangyuanMinQuota(BigDecimal wangyuanMinQuota) {
		this.wangyuanMinQuota = wangyuanMinQuota;
		this.minQuota = wangyuanMinQuota.multiply(new BigDecimal(10000));
	}

	public BigDecimal getWangyuanMaxQuota() {
		wangyuanMaxQuota = this.maxQuota.divide(new BigDecimal(10000));
		DecimalFormat df = new DecimalFormat("0");
		String s = df.format(wangyuanMaxQuota);
		wangyuanMaxQuota = new BigDecimal(s);
		return wangyuanMaxQuota;
	}

	public void setWangyuanMaxQuota(BigDecimal wangyuanMaxQuota) {
		this.wangyuanMaxQuota = wangyuanMaxQuota;
		this.maxQuota = wangyuanMaxQuota.multiply(new BigDecimal(10000));
	}

	public Long getReqTotal() {
		return reqTotal;
	}

	public void setReqTotal(Long reqTotal) {
		this.reqTotal = reqTotal;
	}

//	public Province getProvince() {
//		return province;
//	}
//
//	public void setProvince(Province province) {
//		this.province = province;
//	}

	public RefundType getRepayment() {
		return repayment;
	}

	public void setRepayment(RefundType repayment) {
		this.repayment = repayment;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	//取值的时候四舍五入保留2位小数
	public String getRateUi() {
		return new DecimalFormat("0.00").format(rate);
	}

	public String getTermUi() {
		return minTerm+minTermType.getDesc()+ "-" + maxTerm+maxTermType.getDesc();
	}

	public String getQuotaUi() {
		return getMinQuota() + "-" + getMaxQuota();
	}

	public String getPdtRate() {
		return rateType.getDesc()+rate+"%";
	}

}
