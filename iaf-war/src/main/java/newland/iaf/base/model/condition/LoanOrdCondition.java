package newland.iaf.base.model.condition;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import newland.iaf.IafApplication;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.merch.model.MerchType;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;
/**
 * 订单查询条件类
 * @author new
 *
 */
@CriteriaClass(LoanOrd.class)
public class LoanOrdCondition extends DetachedCriteriaEx{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expression(operator = Operator.in, propertyName = "iloanOrd")
	private List<Long> iloanOrdList;
	
	@Expression(operator = Operator.ilike, propertyName = "loanOrdId")
	private String loanOrdId;
	
	@Expression(operator = Operator.ilike, propertyName = "pdtName")
	private String loanPdtName;
	
	@Expression(operator = Operator.eq, propertyName = "ordStat")
	private OrdStat ordStat;
	
	@Expression(operator = Operator.in, propertyName = "ordStat")
	private Set<OrdStat> ordStatSet;
	
	@Expression(operator = Operator.ilike,likeMatchMode = LikeMatchMode.anywhere
			, propertyName = "merchName")
	private String merchName;
	
	@Expression(operator = Operator.eq, propertyName = "iloanPdt")
	private Long iloanPdt;
	
	@Expression(operator = Operator.eq, propertyName = "loanPdtId")
	private String iloanPdtId;
	
	@Expression(operator = Operator.eq, propertyName = "imerch")
	private Long imerch;
	
	@Expression(operator = Operator.eq, propertyName = "merchType")
	private MerchType merchType;
	
	@Expression(operator = Operator.eq, propertyName = "termType")
	private TermType termType;
	
	@Expression(operator = Operator.eq, propertyName = "rateType")
	private RateType rateType;
	
	@Expression(operator = Operator.eq, propertyName = "quota")
	private BigDecimal loanAmount;
		
	@Expression(operator = Operator.ge, propertyName = "quota")
	private BigDecimal minLoanAmount;
	
	@Expression(operator = Operator.le, propertyName = "quota")
	private BigDecimal maxLoanAmount;
	
	@Expression(operator = Operator.ge, propertyName = "term")
	private Integer minTerm;
	
	@Expression(operator = Operator.le, propertyName = "term")
	private Integer maxTerm;
	
	@Expression(operator = Operator.eq, propertyName = "term")
	private Integer term;
	
	@Expression(operator = Operator.ge, propertyName = "ordDate")
	private Date beginDate;
	
	@Expression(operator = Operator.le, propertyName = "ordDate")
	private Date endDate;
	
	@Expression(operator = Operator.eq, propertyName = "pdtType")
	private PdtType pdtType;
	
	@Expression(operator = Operator.ge, propertyName = "ordDate")
	private Date applyDate;
		
	@Expression(operator = Operator.lt, propertyName = "ordDate")
	private Date maxApplyDate;
	
	@Expression(operator = Operator.ge, propertyName = "acceptDate")
	private Date acceptDate;
	
	@Expression(operator = Operator.eq, propertyName = "ordDate")
	private Date acceptDateEQ;
	
	@Expression(operator = Operator.lt, propertyName = "acceptDate")
	private Date maxAcceptDate;
	
	@Expression(operator = Operator.ge, propertyName = "rate")
	private BigDecimal minRate;
	
	@Expression(operator = Operator.le, propertyName = "rate")
	private BigDecimal maxRate;
	
	@Expression(operator = Operator.in, propertyName = "ordStat")
	private Set<OrdStat> status;
	
	@Expression(operator = Operator.eq, propertyName = "iinst")
	private Long iinst;
	
	@Expression(operator = Operator.eq, propertyName = "instType")
	private InstType instType;
	
	@Expression(operator = Operator.eq, propertyName = "ilondOrd")
	private Long iloanOrd;
	
	@Expression(operator = Operator.ilike,likeMatchMode=LikeMatchMode.anywhere, propertyName = "instName")
	private String instName;
	
	@Expression(operator = Operator.eq, propertyName = "lastRefundDate")
	private Date lastRefundDate;
	
	@Expression(operator = Operator.ge, propertyName = "lastRefundDate")
	private Date beginLastRefundDate;
	
	@Expression(operator = Operator.le, propertyName = "lastRefundDate")
	private Date endLastRefundDate;
	
	@Expression(operator = Operator.le, propertyName = "curtPayment")
	private BigDecimal curtRepayment;
	

	/**
	 * 借款金额
	 */
	@Expression(operator = Operator.eq, propertyName = "quota")
	private BigDecimal quota;
	
	@Expression(operator = Operator.ge, propertyName = "quota")
	private BigDecimal minQuta;
	
	@Expression(operator = Operator.le, propertyName = "quota")
	private BigDecimal maxQuota;
	/**
	 * 年利率
	 */
	@Expression(operator = Operator.eq, propertyName = "rate")
	private BigDecimal rate;
	
	/**
	 * 申请发布起始日期
	 */
	@Expression(operator = Operator.ge, propertyName = "ordDate")
	private Date startReqDate;
	
	@Expression(operator = Operator.eq, propertyName = "updTime")
	private Date updTime;
	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	/**
	 * 申请发布终止日期
	 */
	@Expression(operator = Operator.lt, propertyName = "ordDate")
	private Date endReqDate;
	
	public TermType getTermType() {
		return termType;
	}

	public void setTermType(TermType termType) {
		this.termType = termType;
	}

	@Expression(operator = Operator.eq, propertyName = "expiryDate")
	private Date expiryDateEq;
	
	@Expression(operator = Operator.ge, propertyName = "expiryDate")
	private Date startexpiryDate;
	
	@Expression(operator = Operator.lt, propertyName = "expiryDate")
	private Date endexpiryDate;
	
	@Expression(operator = Operator.le, propertyName = "ordStat")
	private OrdStat leStat;
	
	@Expression(operator = Operator.eq, propertyName = "shield")
	private Integer shield;

	public BigDecimal getMinRate() {
		return minRate;
	}

	public void setMinRate(BigDecimal minRate) {
		this.minRate = minRate;
	}

	public BigDecimal getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(BigDecimal maxRate) {
		this.maxRate = maxRate;
	}

	@Expression(operator = Operator.eq, propertyName = "loanOrdId")
	private String loanPdtId;
	
	public String getLoanPdtId() {
		return loanPdtId;
	}

	public String getIloanPdtId() {
		return iloanPdtId;
	}

	public void setIloanPdtId(String iloanPdtId) {
		this.iloanPdtId = iloanPdtId;
	}

	public void setLoanPdtId(String loanPdtId) {
		this.loanPdtId = loanPdtId;
	}

	public String getLoanOrdId() {
		return loanOrdId;
	}

	public Date getAcceptDateEQ() {
		return acceptDateEQ;
	}

	public void setAcceptDateEQ(Date acceptDateEQ) {
		this.acceptDateEQ = IafApplication.roundToBeginDate(acceptDateEQ);
	}

	public void setLoanOrdId(String loanOrdId) {
		this.loanOrdId = loanOrdId;
	}

	public String getLoanPdtName() {
		return loanPdtName;
	}

	public void setLoanPdtName(String loanPdtName) {
		this.loanPdtName = loanPdtName;
	}

	public OrdStat getOrdStat() {
		return ordStat;
	}

	public Date getMaxAcceptDate() {
		return maxAcceptDate;
	}

	public void setMaxAcceptDate(Date maxAcceptDate) {
		this.maxAcceptDate = IafApplication.roundToEndDate(maxAcceptDate);
	}

	public Date getMaxApplyDate() {
		return maxApplyDate;
	}

	public void setMaxApplyDate(Date maxApplyDate) {
		this.maxApplyDate = IafApplication.roundToEndDate(maxApplyDate);
	}

	public void setOrdStat(OrdStat ordStat) {
		this.ordStat = ordStat;
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

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = IafApplication.roundToBeginDate(beginDate);
	}

	public Date getExpiryDateEq() {
		return expiryDateEq;
	}

	public void setExpiryDateEq(Date expiryDateEq) {
		this.expiryDateEq = IafApplication.roundToBeginDate(expiryDateEq);
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getStartexpiryDate() {
		return startexpiryDate;
	}

	public void setStartexpiryDate(Date startexpiryDate) {
		this.startexpiryDate = IafApplication.roundToBeginDate(startexpiryDate);
	}

	public Date getEndexpiryDate() {
		return endexpiryDate;
	}

	public void setEndexpiryDate(Date endexpiryDate) {
		this.endexpiryDate = IafApplication.roundToEndDate(endexpiryDate);
	}

	public void setEndDate(Date endDate) {
		this.endDate = IafApplication.roundToEndDate(endDate);
	}

	public BigDecimal getMinQuta() {
		return minQuta;
	}

	public void setMinQuta(BigDecimal minQuta) {
		this.minQuta = minQuta;
	}

	public BigDecimal getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(BigDecimal maxQuota) {
		this.maxQuota = maxQuota;
	}

	public PdtType getPdtType() {
		return pdtType;
	}

	public void setPdtType(PdtType pdtType) {
		this.pdtType = pdtType;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = IafApplication.roundToBeginDate(applyDate);
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = IafApplication.roundToBeginDate(acceptDate);
	}

	public Set<OrdStat> getStatus() {
		return status;
	}

	public void setStatus(Set<OrdStat> status) {
		this.status = status;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	public InstType getInstType() {
		return instType;
	}

	public void setInstType(InstType instType) {
		this.instType = instType;
	}

	public Long getIloanOrd() {
		return iloanOrd;
	}

	public void setIloanOrd(Long iloanOrd) {
		this.iloanOrd = iloanOrd;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getStartReqDate() {
		return startReqDate;
	}

	public void setStartReqDate(Date startReqDate) {
		this.startReqDate = IafApplication.roundToBeginDate(startReqDate);
	}

	public Date getEndReqDate() {
		return endReqDate;
	}

	public void setEndReqDate(Date endReqDate) {
		this.endReqDate = IafApplication.roundToEndDate(endReqDate);
	}

	public List<Long> getIloanOrdList() {
		return iloanOrdList;
	}

	public void setIloanOrdList(List<Long> iloanOrdList) {
		this.iloanOrdList = iloanOrdList;
	}

	public OrdStat getLeStat() {
		return leStat;
	}

	public void setLeStat(OrdStat leStat) {
		this.leStat = leStat;
	}

	public Long getIloanPdt() {
		return iloanPdt;
	}

	public void setIloanPdt(Long iloanPdt) {
		this.iloanPdt = iloanPdt;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public Integer getShield() {
		return shield;
	}

	public void setShield(Integer shield) {
		this.shield = shield;
	}

	public Date getLastRefundDate() {
		return lastRefundDate;
	}

	public void setLastRefundDate(Date lastRefundDate) {
		this.lastRefundDate = lastRefundDate;
	}

	public Date getBeginLastRefundDate() {
		return beginLastRefundDate;
	}

	public void setBeginLastRefundDate(Date beginLastRefundDate) {
		this.beginLastRefundDate = DateConvertUtils.getFirstDateOfMonth(beginLastRefundDate);;
	}

	public Date getEndLastRefundDate() {
		return endLastRefundDate;
	}

	public void setEndLastRefundDate(Date endLastRefundDate) {
		DateConvertUtils.getLastDateOfMonth(endLastRefundDate);
		this.endLastRefundDate = DateConvertUtils.getLastDateOfMonth(endLastRefundDate);
	}

	public BigDecimal getCurtRepayment() {
		return curtRepayment;
	}

	public void setCurtRepayment(BigDecimal curtRepayment) {
		this.curtRepayment = curtRepayment;
	}

	public BigDecimal getMinLoanAmount() {
		return minLoanAmount;
	}

	public void setMinLoanAmount(BigDecimal minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}

	public BigDecimal getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(BigDecimal maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
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

	public Set<OrdStat> getOrdStatSet() {
		return ordStatSet;
	}

	public void setOrdStatSet(Set<OrdStat> ordStatSet) {
		this.ordStatSet = ordStatSet;
	}

	
}
