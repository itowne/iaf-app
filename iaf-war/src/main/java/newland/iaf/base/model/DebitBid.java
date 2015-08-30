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

import newland.iaf.base.model.dict.DebitbidStat;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.RefundType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchType;

import org.hibernate.annotations.Index;

/**
 * 借款竞投申请
 * 
 * @author wwa
 * 
 */
@Entity
@Table(name = "t_debit_bid")
@org.hibernate.annotations.Table(appliesTo = "t_debit_bid", indexes = {
		@Index(name = "idx_debit_bid1", columnNames = { "term" }),
		@Index(name = "idx_debit_bid2", columnNames = { "year_rate" }) })
public class DebitBid implements Serializable {

	private static final long serialVersionUID = 8635559142172476385L;

	/**
	 * 内部编号
	 */
	@Id
	@TableGenerator(name = "i_debit_bid", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_debit_bid")
	@Column(name = "i_debit_bid", nullable = false, updatable = false)
	private Long idebitBid;
	/**
	 * 竟投产品编号
	 */
	@Column(name = "debit_bid_id", length = 8, nullable = false)
	private String debitBidId;

	/**
	 * 商户内部编号
	 */
	@Column(name = "i_merch", nullable = false, updatable = false)
	private Long imerch;
	
	/**
	 * 商户名称
	 */
	@Column (name = "merch_name", length = 50)
	private String merchName;
	/**
	 * 商户类型
	 */
	@Enumerated
	@Column (name = "merch_type", nullable = false)
	private MerchType merchType;
	
	/**
	 * 还款方式
	 */
	@Enumerated
	@Column (name = "refund_type")
	private RefundType refundType;
	
	@Column (name = "rateType")
	private RateType rateType;
	
	@Column (name = "termType")
	private TermType termType;

	/**
	 * 商户用户内部编号
	 */
	@Column(name = "i_merch_user", nullable = false, updatable = false)
	private Long iMerchUser;

	/**
	 * 借款金额
	 */
	@Column(name = "quota", nullable = false, updatable = false)
	private BigDecimal quota;
	
	/**
	 * 借款金额万元格式
	 */
	@Transient
	private BigDecimal wangyuanQuota;
	
	@Transient
	private String debitRate;
	
	@Transient
	private String debitTerm;
	
	/**
	 * 放贷受理地区范围
	 */
	@Column(name = "region", nullable = false, updatable = true, length = 6)
	private String region;
	
//	@ManyToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Province.class)
//	@JoinColumn(name = "region", insertable = false, updatable = false)
//	private Province province;

	/**
	 * 借款周期
	 */
	@Column(name = "term", nullable = false, updatable = false)
	private Integer term;

	/**
	 * 年利率
	 */
	@Column(name = "year_rate", nullable = false, updatable = false)
	private BigDecimal yearRate;
	
	/**
	 * 用途
	 */
	@Column(name = "purpose", nullable = false, updatable = false)
	private String purpose;

	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	/**
	 * version
	 */
	@Column(name = "version")
	private Long version;

	/**
	 * 修改时间
	 */
	@Column(name = "upd_time", nullable = false)
	private Date updTime;

	/**
	 * 竞投状态
	 */
	@Enumerated
	@Column(name = "bid_stat", nullable = false)
	private DebitbidStat bidStat;
	
	/**
	 * 受理次数
	 */
	@Column(name = "accept_total")
	private Long acceptTotal;

	/**
	 * 失效日期
	 */
	@Column(name = "exp_date", nullable = false, updatable = false)
	private Date expireDate;

	@Transient
	private Merch merch;
	
	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public void setIdebitBid(Long idebitBid) {
		this.idebitBid = idebitBid;
	}

	public Long getImerch() {
		return imerch;
	}

	public Long getiMerchUser() {
		return iMerchUser;
	}

	public void setiMerchUser(Long iMerchUser) {
		this.iMerchUser = iMerchUser;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}


	public String getPurpose() {
		return purpose;
	}

	public TermType getTermType() {
		return termType;
	}

	public void setTermType(TermType termType) {
		this.termType = termType;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Date getGenTime() {
		return genTime;
	}

	public RateType getRateType() {
		return rateType;
	}

	public String getDebitTerm() {
		return term+termType.getDesc();
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public String getDebitRate() {
		return rateType.getDesc()+yearRate+"%";
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}


	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getDebitBidId() {
		return debitBidId;
	}

	public void setDebitBidId(String debitBidId) {
		this.debitBidId = debitBidId;
	}

	public Long getIdebitBid() {
		return idebitBid;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public RefundType getRefundType() {
		return refundType;
	}

	public void setRefundType(RefundType refundType) {
		this.refundType = refundType;
	}

	public DebitbidStat getBidStat() {
		return bidStat;
	}

	public void setBidStat(DebitbidStat bidStat) {
		this.bidStat = bidStat;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public MerchType getMerchType() {
		return merchType;
	}

	public void setMerchType(MerchType merchType) {
		this.merchType = merchType;
	}

	public BigDecimal getWangyuanQuota() {
		wangyuanQuota = this.quota.divide(new BigDecimal(10000));
		DecimalFormat df = new DecimalFormat("0");
		String s = df.format(wangyuanQuota);
		wangyuanQuota = new BigDecimal(s);
		return wangyuanQuota;
	}

	public void setWangyuanQuota(BigDecimal wangyuanQuota) {
		this.wangyuanQuota = wangyuanQuota;
		this.quota= wangyuanQuota.multiply(new BigDecimal(10000));
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

//	public Province getProvince() {
//		return province;
//	}
//
//	public void setProvince(Province province) {
//		this.province = province;
//	}

	public Long getAcceptTotal() {
		return acceptTotal;
	}

	public void setAcceptTotal(Long acceptTotal) {
		this.acceptTotal = acceptTotal;
	}

}
