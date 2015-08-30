package newland.iaf.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.merch.model.MerchType;

import org.hibernate.annotations.Index;

/**
 * 贷款订单
 * 
 * @author wwa
 * 
 */
@Entity
@Table(name = "t_loan_ord", uniqueConstraints = { @UniqueConstraint(columnNames = "loan_ord_id") })
@org.hibernate.annotations.Table(appliesTo = "t_loan_ord", indexes = {
		@Index(name = "idx_loan_ord1", columnNames = { "i_merch" }),
		@Index(name = "idx_loan_ord2", columnNames = { "i_inst" }),
		@Index(name = "idx_loan_ord3", columnNames = { "i_merch", "ord_stat" }),
		@Index(name = "idx_loan_ord4", columnNames = { "i_inst", "ord_stat" }),
		@Index(name = "idx_loan_ord5", columnNames = { "ord_stat" }),
		@Index(name = "idx_loan_ord6", columnNames = { "i_merch", "ord_date" }),
		@Index(name = "idx_loan_ord7", columnNames = { "i_inst", "ord_date" }) })
public class LoanOrd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2666220668418002949L;

	/**
	 * 主键ID
	 */
	@Id
	@TableGenerator(name = "i_loan_ord", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_loan_ord")
	@Column(name = "i_loan_ord")
	private Long iloanOrd;

	/**
	 * 内部编号
	 */
	@Column(name = "loan_ord_id", length = 12)
	private String loanOrdId;

	/**
	 * 产品表内部编号(竞投时未竞投内部编号，产品时为产品历史编号)
	 */
	@Column(name = "i_loan_pdt")
	private Long iloanPdt;
	/**
	 * 产品编号
	 */
	@Column(name = "loan_pdt_id", length = 8)
	private String loanPdtId;
	/**
	 * 产品名称
	 */
	@Column(name = "pdt_name", length = 50)
	private String pdtName;
	/**
	 * 产品类型
	 */
	@Enumerated
	@Column(name = "pdt_type", nullable = false)
	private PdtType pdtType;

	/**
	 * 商户内部编号
	 */
	@Column(name = "i_merch", nullable = false)
	private Long imerch;

	@Column(name = "merch_id", length = 20)
	private String merchId;
	/**
	 * 商户名称
	 */
	@Column(name = "merch_name", length = 50)
	private String merchName;
	/**
	 * 商户类型
	 */
	@Enumerated
	@Column(name = "merch_type")
	private MerchType merchType;
	@Transient
	private String traceNo;

	/**
	 * 机构内部编号
	 */
	@Column(name = "i_inst", nullable = false)
	private Long iinst;
	/**
	 * 机构名称
	 */
	@Column(name = "inst_name", length = 50)
	private String instName;

	@Column(name = "organ_id", length = 20)
	private String organId;

	/**
	 * 原借款金额
	 */
	@Column(name = "pre_quota", nullable = true)
	private BigDecimal preQuota;
	
	/**
	 * 借款金度
	 */
	@Column(name = "quota", nullable = false)
	private BigDecimal quota;

	/**
	 * 借款金额万元格式
	 */
	@Transient
	public BigDecimal wangyuanQuota;
	
	@Transient
	public BigDecimal wangyuanPreQuota;
	
	@Transient
	public String pdtRate;
	
	@Transient
	public String pdtTerm;
	
	@Transient
	public BigDecimal pdtQuoat;

	/**
	 * 借款期限
	 */
	@Column(name = "term", nullable = false)
	public Integer term;

	/**
	 * 年利率
	 */
	@Column(name = "rate", nullable = false)
	private BigDecimal rate;

	/**
	 * 申请日期
	 */
	@Column(name = "ord_date", nullable = false)
	private Date ordDate;

	/**
	 * 截止日期
	 */
	@Column(name = "expiry_date", nullable = false)
	private Date expiryDate;

	/**
	 * 受理日期
	 */
	@Column(name = "accept_date", updatable = true)
	private Date acceptDate;

	/**
	 * 审批日期
	 */
	@Column(name = "check_date", updatable = true)
	private Date checkDate;

	/**
	 * 放款日期
	 */
	@Column(name = "credit_date", updatable = true)
	private Date creditDate;

	/**
	 * version
	 */
	@Version
	@Column(name = "version")
	private Long version;

	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false)
	private Date genTime;

	/**
	 * 订单状态
	 */
	@Enumerated
	@Column(name = "ord_stat", nullable = false)
	private OrdStat ordStat;
	/**
	 * 借款用途
	 */
	@Column(name = "purpose", length = 200)
	private String purpose;

	/**
	 * 已还金额
	 */
	@Column(name = "repayment")
	private BigDecimal repayment;
	
	@Column(name = "rateType")
	private RateType rateType; 
	
	@Column(name = "termType")
	private TermType termType;
	/**
	 * 待还金额
	 */
	@Column(name = "remain_payment")
	private BigDecimal remainPayment;
	/**
	 * 当期应还款
	 */
	@Column(name = "curtPayment")
	private BigDecimal curtPayment;
	/**
	 * 已还期数
	 */
	@Column(name = "recive_period")
	private Integer recivePeriod;
	/**
	 * 待还期数
	 */
	@Column(name = "remain_period")
	private Integer remainPeriod;

	@Column(name = "upd_time", nullable = false)
	private Date updTime;

	/**
	 * 是否屏蔽  0代表未屏蔽，1代表屏蔽
	 */
	@Column(name = "shield", nullable = false)
	private Integer shield = new Integer(0);

	@Column(name = "last_refund_date")
	private Date lastRefundDate;
	
	private String memo;
	
	@Column(name = "freeze_memo")
	private String freezeMemo;
	
	@Column(name = "overdue")
	private BigDecimal overdue;
	
	@Column(name="unfreeze_memo")
	private String unFreezeMemo;
	
	@Column(name="freeze_date")
	private Date freezeDate;

	public Long getIloanOrd() {
		return iloanOrd;
	}

	public void setIloanOrd(Long iloanOrd) {
		this.iloanOrd = iloanOrd;
	}

	public String getLoanOrdId() {
		return loanOrdId;
	}

	public void setLoanOrdId(String loadOrdId) {
		this.loanOrdId = loadOrdId;
	}

	public Long getIloanPdt() {
		return iloanPdt;
	}

	public void setIloanPdt(Long iloanPdt) {
		this.iloanPdt = iloanPdt;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long iMerch) {
		this.imerch = iMerch;
	}

	public BigDecimal getPdtQuoat() {
		return quota.divide(new BigDecimal(10000), BigDecimal.ROUND_UNNECESSARY).setScale(1);
	}

	public String getPdtTerm() {
		return term+termType.getDesc();
	}

	public Long getIinst() {
		return iinst;
	}

	public String getPdtRate() {
		return rateType.getDesc()+rate+"%";
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	public BigDecimal getPreQuota() {
		return preQuota;
	}

	public void setPreQuota(BigDecimal preQuota) {
		this.preQuota = preQuota;
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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getVersion() {
		return version;
	}

	public TermType getTermType() {
		return termType;
	}

	public void setTermType(TermType termType) {
		this.termType = termType;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public OrdStat getOrdStat() {
		return ordStat;
	}

	public void setOrdStat(OrdStat ordStat) {
		this.ordStat = ordStat;
	}

	public BigDecimal getRepayment() {
		return repayment;
	}

	public BigDecimal getRemainPayment() {
		return remainPayment;
	}

	public BigDecimal getCurtPayment() {
		return curtPayment;
	}

	public Integer getRecivePeriod() {
		return recivePeriod;
	}

	public Integer getRemainPeriod() {
		return remainPeriod;
	}

	public void setRepayment(BigDecimal repayment) {
		this.repayment = repayment;
	}

	public void setRemainPayment(BigDecimal remainPayment) {
		this.remainPayment = remainPayment;
	}

	public void setCurtPayment(BigDecimal curtPayment) {
		this.curtPayment = curtPayment;
	}

	public void setRecivePeriod(Integer recivePeriod) {
		this.recivePeriod = recivePeriod;
	}

	public void setRemainPeriod(Integer remainPeriod) {
		this.remainPeriod = remainPeriod;
	}

	public PdtType getPdtType() {
		return pdtType;
	}

	public void setPdtType(PdtType pdtType) {
		this.pdtType = pdtType;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getLoanPdtId() {
		return loanPdtId;
	}

	public void setLoanPdtId(String loanPdtId) {
		this.loanPdtId = loanPdtId;
	}

	public String getPdtName() {
		return pdtName;
	}

	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
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

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public Integer getShield() {
		return shield;
	}

	public void setShield(Integer shield) {
		this.shield = shield;
	}

	public BigDecimal getWangyuanQuota() {
		wangyuanQuota = this.quota.divide(new BigDecimal(10000),BigDecimal.ROUND_UNNECESSARY).setScale(1);
		//DecimalFormat df = new DecimalFormat("0");
		//String s = df.format(wangyuanQuota);
		//wangyuanQuota = new BigDecimal(s);
		return wangyuanQuota;
	}

	public void setWangyuanQuota(BigDecimal wangyuanQuota) {
		this.wangyuanQuota = wangyuanQuota;
		this.quota = wangyuanQuota.multiply(new BigDecimal(10000));
	}

	public BigDecimal getWangyuanPreQuota(){
		wangyuanPreQuota = this.preQuota.divide(new BigDecimal(10000),BigDecimal.ROUND_UNNECESSARY).setScale(1);
		return wangyuanPreQuota;
	}
	
	public void setWangyuanPreQuota(BigDecimal wangyuanPreQuota){
		this.wangyuanPreQuota = wangyuanPreQuota;
		this.preQuota = wangyuanPreQuota.multiply(new BigDecimal(10000));
	}
	
	public String getMerchId() {
		return merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public Date getLastRefundDate() {
		return lastRefundDate;
	}

	public void setLastRefundDate(Date lastRefundDate) {
		this.lastRefundDate = lastRefundDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFreezeMemo() {
		return freezeMemo;
	}

	public void setFreezeMemo(String freezeMemo) {
		this.freezeMemo = freezeMemo;
	}

	public BigDecimal getOverdue() {
		return overdue;
	}

	public void setOverdue(BigDecimal overdue) {
		this.overdue = overdue;
	}

	public String getUnFreezeMemo() {
		return unFreezeMemo;
	}

	public void setUnFreezeMemo(String unFreezeMemo) {
		this.unFreezeMemo = unFreezeMemo;
	}

	public Date getFreezeDate() {
		return freezeDate;
	}

	public void setFreezeDate(Date freezeDate) {
		this.freezeDate = freezeDate;
	}

}
