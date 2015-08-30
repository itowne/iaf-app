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

import org.hibernate.annotations.Index;

/**
 * 贷款产品历史表
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_loan_pdt_his")
@org.hibernate.annotations.Table(appliesTo = "t_loan_pdt_his", indexes = { @Index(name = "idx_load_pdt_his1", columnNames = { "i_loan_pdt" }) })
public class LoanPdtHis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6318903844002364830L;

	/**
	 * 贷款产品历史内部编号
	 */
	@Id
	@TableGenerator(name = "t_loan_pdt_his", initialValue = 100, allocationSize = 1000)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "t_loan_pdt_his")
	@Column(name = "i_loan_pdt_his", updatable = false)
	private Long iloanPdtHis;

	/**
	 * 贷款产品内部编号
	 */
	@Column(name = "i_loan_pdt", nullable = false, updatable = false)
	private Long iloanPdt;

	@Column(name = "loan_pdt_id", updatable = false)
	private String loadPdtId;

	/**
	 * 机构代号
	 */
	@Column(name = "i_inst", nullable = false, updatable = false)
	private Long iinst;
	/**
	 * 产品名称
	 */
	@Column(name = "pdt_name", nullable = false, updatable = false)
	private String pdtName;

	/**
	 * 贷款最低金额
	 */
	@Column(name = "min_quota", nullable = false, updatable = false)
	private BigDecimal minQuota;

	/**
	 * 贷款最高金额
	 */
	@Column(name = "max_quota", nullable = false, updatable = false)
	private BigDecimal maxQuota;
	
	/**
	 * 最低金额万元格式
	 */
	@Transient
	private BigDecimal wangyuanMinQuota;
	/**
	 * 最高金额万元格式
	 */
	@Transient
	private BigDecimal wangyuanMaxQuota;

	/**
	 * 贷款最短周期
	 */
	@Column(name = "min_term", nullable = false, updatable = false)
	private int minTerm;

	/**
	 * 贷款最长周期
	 */
	@Column(name = "max_term", nullable = false, updatable = false)
	private int maxTerm;

	/**
	 * 年利率 rate of interest per annum
	 */
	@Column(name = "rate", nullable = false, updatable = false)
	private BigDecimal rate;
	
	@Column(name = "minTermType",updatable = true, length = 11)
	private TermType minTermType; 
	
	@Column(name = "maxTermType",updatable = true, length = 11)
	private TermType maxTermType;

	/**
	 * 放贷周期
	 */
	@Column(name = "credit_term", nullable = false, updatable = false)
	private Integer creditTerm;

	/**
	 * 放贷受理地区范围
	 */
	@Column(name = "region", nullable = false, updatable = false)
	private String region;
	
//	@ManyToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Province.class)
//	@JoinColumn(name = "region", insertable = false, updatable = false)
//	private Province province;

	@Column(name = "province_code", nullable = false, updatable = true,length=25)
	private String provinceCode; 
	
	@Column(name = "area", nullable = false, updatable = true,length=50)
	private String area;
	/**
	 * 还款方式
	 * 
	 * @TODO 待确认
	 */
	@Column(name = "repayment", updatable = false)
	private RefundType repayment;

	@Column(name = "rateType", updatable = true, length = 11)
	private RateType rateType;
	
	/**
	 * 产品特点的简单描述（有长度限制）
	 * 
	 */
	@Column(name = "introduce", nullable = true, updatable = true)
	private String introduce;

	/**
	 * 产品特点
	 */
	@Column(name = "feature", nullable = false, updatable = false)
	private String feature;

	/**
	 * 申请条件
	 */
	@Column(name = "req_cond", nullable = false, updatable = false)
	private String condition;

	/**
	 * 申请所需材料
	 */
	@Column(name = "rereq", nullable = false, updatable = false)
	private String cl;


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
	@Column(name = "upd_time", nullable = false, updatable = false)
	private Date updTime;

	public Long getIloanPdtHis() {
		return iloanPdtHis;
	}

	public void setIloanPdtHis(Long iloanPdtHis) {
		this.iloanPdtHis = iloanPdtHis;
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

	public int getMinTerm() {
		return minTerm;
	}

	public void setMinTerm(int minTerm) {
		this.minTerm = minTerm;
	}

	public int getMaxTerm() {
		return maxTerm;
	}

	public String getLoadPdtId() {
		return loadPdtId;
	}

	public void setLoadPdtId(String loadPdtId) {
		this.loadPdtId = loadPdtId;
	}

	public TermType getMinTermType() {
		return minTermType;
	}

	public void setMinTermType(TermType minTermType) {
		this.minTermType = minTermType;
	}

	public TermType getMaxTermType() {
		return maxTermType;
	}

	public void setMaxTermType(TermType maxTermType) {
		this.maxTermType = maxTermType;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	public void setMaxTerm(int maxTerm) {
		this.maxTerm = maxTerm;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getCreditTerm() {
		return creditTerm;
	}


	public String getArea() {
		return area;
	}

	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCreditTerm(Integer creditTerm) {
		this.creditTerm = creditTerm;
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

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public BigDecimal getWangyuanMinQuota() {
		wangyuanMinQuota = this.minQuota.divide(new BigDecimal(10000));
		return wangyuanMinQuota;
	}

	public void setWangyuanMinQuota(BigDecimal wangyuanMinQuota) {
		this.wangyuanMinQuota = wangyuanMinQuota;
		this.minQuota= wangyuanMinQuota.multiply(new BigDecimal(10000));
	}

	public BigDecimal getWangyuanMaxQuota() {
		wangyuanMaxQuota = this.maxQuota.divide(new BigDecimal(10000));
		return wangyuanMaxQuota;
	}

	public void setWangyuanMaxQuota(BigDecimal wangyuanMaxQuota) {
		this.wangyuanMaxQuota = wangyuanMaxQuota;
		this.maxQuota= wangyuanMaxQuota.multiply(new BigDecimal(10000));
	}

//	public Province getProvince() {
//		return province;
//	}
//
//	public void setProvince(Province province) {
//		this.province = province;
//	}

	public PdtStat getPdtStatus() {
		return pdtStatus;
	}

	public void setPdtStatus(PdtStat pdtStatus) {
		this.pdtStatus = pdtStatus;
	}

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


}
