package newland.iaf.base.model.condition;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import newland.iaf.IafApplication;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.dict.DebitbidStat;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.merch.model.MerchType;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(DebitBid.class)
public class DebitBidCondition extends DetachedCriteriaEx {

	private static final long serialVersionUID = -657397260319403800L;
	
	@Expression(operator = Operator.in, propertyName = "idebitBid")
	private List<Long> idebitBidList;
	
	@Expression(operator = Operator.eq, propertyName = "rateType")
	private RateType rateType;
	
	@Expression(operator = Operator.eq, propertyName = "termType")
	private TermType termType;
	/**
	 * 商户id
	 */
	@Expression(operator = Operator.eq, propertyName = "iMerchUser")
	private Long iMerchUser;
	/**
	 * 商户号
	 */
	@Expression(operator = Operator.eq, propertyName = "imerch")
	private Long imerch;
	/**
	 * 借款金额
	 */
	@Expression(operator = Operator.eq, propertyName = "quota")
	private BigDecimal quota;
	@Expression(operator = Operator.ge, propertyName = "quota")
	private BigDecimal Minquota;
	
	@Expression(operator = Operator.le, propertyName = "quota")
	private BigDecimal Maxquota;
	/**
	 * 小于或等于金额
	 */
	@Expression(operator = Operator.le, propertyName = "quota")
	private BigDecimal maxQuota;
	/**
	 * 年利率
	 */
	@Expression(operator = Operator.eq, propertyName = "yearRate")
	private BigDecimal rate;
	/**
	 * 小于或等于年利率
	 */
	@Expression(operator = Operator.le, propertyName = "yearRate")
	private BigDecimal maxRate;
	
	@Expression(operator = Operator.ge, propertyName = "yearRate")
	private BigDecimal minRate;
	/**
	 * 借款周期
	 */
	@Expression(operator = Operator.eq, propertyName = "term")
	private Integer term;
	/**
	 * 小于或等于借款周期
	 */
	@Expression(operator = Operator.le, propertyName = "term")
	private Integer maxTerm;
	
	@Expression(operator = Operator.ge, propertyName = "term")
	private Integer minTerm;
	
	@Expression(operator = Operator.le, propertyName = "acceptTotal")
	private Long MaxacceptTotal;
	
	@Expression(operator = Operator.ge, propertyName = "acceptTotal")
	private Long MinacceptTotal;
	/**
	 * 竞投状态
	 */
	@Expression(operator = Operator.eq, propertyName = "bidStat")
	private DebitbidStat debitbidStat;
	/**
	 * 地区
	 */
	@Expression(operator = Operator.eq, propertyName = "region")
	private String region;
	
	@Expression(operator = Operator.ilike,likeMatchMode=LikeMatchMode.anywhere, propertyName = "merchName")
	private String merchName;
	
	@Expression(operator = Operator.eq, propertyName = "merchType")
	private MerchType merchType;
	
	@Expression(operator = Operator.eq, propertyName = "debitBidId")
	private String debitBidId;
	
	/**
	 * 申请发布起始日期
	 */
	@Expression(operator = Operator.ge, propertyName = "genTime")
	private Date startGenDate;
	
	/**
	 * 申请发布终止日期
	 */
	@Expression(operator = Operator.lt, propertyName = "genTime")
	private Date endGenDate;

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public DebitbidStat getDebitbidStat() {
		return debitbidStat;
	}

	public void setDebitbidStat(DebitbidStat debitbidStat) {
		this.debitbidStat = debitbidStat;
	}

	public TermType getTermType() {
		return termType;
	}

	public void setTermType(TermType termType) {
		this.termType = termType;
	}

	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
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

	public Date getStartGenDate() {
		return startGenDate;
	}

	public void setStartGenDate(Date startGenDate) {
		this.startGenDate = IafApplication.roundToBeginDate(startGenDate);
	}

	public Date getEndGenDate() {
		return endGenDate;
	}

	public Long getiMerchUser() {
		return iMerchUser;
	}

	public void setiMerchUser(Long iMerchUser) {
		this.iMerchUser = iMerchUser;
	}

	public Long getImerch() {
		return imerch;
	}

	public BigDecimal getMaxquota() {
		return Maxquota;
	}

	public void setMaxquota(BigDecimal maxquota) {
		Maxquota = maxquota;
	}

	public BigDecimal getMinRate() {
		return minRate;
	}

	public Long getMaxacceptTotal() {
		return MaxacceptTotal;
	}

	public void setMaxacceptTotal(Long maxacceptTotal) {
		MaxacceptTotal = maxacceptTotal;
	}

	public Long getMinacceptTotal() {
		return MinacceptTotal;
	}

	public void setMinacceptTotal(Long minacceptTotal) {
		MinacceptTotal = minacceptTotal;
	}

	public void setMinRate(BigDecimal minRate) {
		this.minRate = minRate;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public Integer getMinTerm() {
		return minTerm;
	}

	public void setMinTerm(Integer minTerm) {
		this.minTerm = minTerm;
	}

	public BigDecimal getMinquota() {
		return Minquota;
	}

	public void setMinquota(BigDecimal minquota) {
		Minquota = minquota;
	}

	public void setEndGenDate(Date endGenDate) {
		this.endGenDate = IafApplication.roundToEndDate(endGenDate);
	}

	public List<Long> getIdebitBidList() {
		return idebitBidList;
	}

	public BigDecimal getMaxQuota() {
		return maxQuota;
	}

	public void setIdebitBidList(List<Long> idebitBidList) {
		this.idebitBidList = idebitBidList;
	}

	public void setMaxQuota(BigDecimal maxQuota) {
		this.maxQuota = maxQuota;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public BigDecimal getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(BigDecimal maxRate) {
		this.maxRate = maxRate;
	}

	public Integer getMaxTerm() {
		return maxTerm;
	}

	public void setMaxTerm(Integer maxTerm) {
		this.maxTerm = maxTerm;
	}

	public String getDebitBidId() {
		return debitBidId;
	}

	public void setDebitBidId(String debitBidId) {
		this.debitBidId = debitBidId;
	}

}
