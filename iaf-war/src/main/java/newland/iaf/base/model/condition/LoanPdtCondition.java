package newland.iaf.base.model.condition;

import java.math.BigDecimal;
import java.util.List;

import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.model.dict.YesOrNoType;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(LoanPdt.class)
public class LoanPdtCondition extends DetachedCriteriaEx{

	private static final long serialVersionUID = 4792615768304774576L;
	
	/**
	 * 产品内部编号集合
	 */
	@Expression(operator = Operator.in, propertyName = "iloanPdt")
	private List<Long> iloanPdt ;
	@Expression(operator = Operator.eq, propertyName = "iloanPdt")
	private Long iiloanPdt ;
	/**
	 * 产品名称
	 */
	@Expression(operator = Operator.like,likeMatchMode = LikeMatchMode.anywhere, propertyName = "pdtName")
	private String pdtName;
	/**
	 * 机构
	 */
	@Expression(operator = Operator.eq, propertyName = "iinst")
	private Long iinst;
	/**
	 * 年利率
	 */
	@Expression(operator = Operator.eq, propertyName = "rate")
	private BigDecimal rate;
	/**
	 * 最大年利率
	 */
	@Expression(operator = Operator.le, propertyName = "rate")
	private BigDecimal maxRate;
	
	@Expression(operator = Operator.ge, propertyName = "rate")
	private BigDecimal minRate;
	
	@Expression(operator = Operator.eq, propertyName = "rateType")
	private RateType rateType;
	
	@Expression(operator = Operator.eq, propertyName = "minTermType")
	private TermType minTermType;
	
	@Expression(operator = Operator.eq, propertyName = "maxTermType")
	private TermType maxTermType;
	/**
	 * 产品金额
	 * 小于或等于贷款最大金额
	 */
	@Expression(operator = Operator.le, propertyName = "maxQuota")
	private BigDecimal maxQuota;
	
	@Expression(operator = Operator.ge, propertyName = "maxQuota")
	private BigDecimal maxgeQuota;
	
	@Expression(operator = Operator.le, propertyName = "minQuota")
	private BigDecimal minleQuota;
	
	@Expression(operator = Operator.le, propertyName = "maxTerm")
	private Integer maxgeTerm;
	
	@Expression(operator = Operator.ge, propertyName = "minTerm")
	private Integer minleTerm;
	
	@Expression(operator = Operator.le, propertyName = "creditTerm")
	private Integer creditTerm;
	
	@Expression(operator = Operator.eq, propertyName = "reqTotal")
	private Long reqTotal;
	/**
	 * 产品周期
	 * 小于或等于贷款最长周期
	 */
	@Expression(operator = Operator.le, propertyName = "maxTerm")
	private Integer maxTerm;
	
	/**
	 * 受理地区-省份
	 */
	@Expression(operator = Operator.eq, propertyName = "provinceCode")
	private String provinceCode;
	/**
	 * 受理地区-地市
	 */
	@Expression(operator = Operator.eq, propertyName = "region")
	private String region;
	/**
	 * 产品状态   上架or下架
	 */
	@Expression(operator = Operator.eq, propertyName = "pdtStatus")
	private PdtStat pdtStatus;
	
	/**
	 * 是否删除 ，true:已删除，false:未删除
	 */
	@Expression(operator = Operator.eq, propertyName = "deleteFlag")
	private YesOrNoType deleteFlag = YesOrNoType.NO;//默认查未删除的
	
	public PdtStat getPdtStatus() {
		return pdtStatus;
	}
	public void setPdtStatus(PdtStat pdtStatus) {
		this.pdtStatus = pdtStatus;
	}
	public String getPdtName() {
		return pdtName;
	}
	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}
	public Long getIinst() {
		return iinst;
	}
	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getMaxQuota() {
		return maxQuota;
	}
	public void setMaxQuota(BigDecimal maxQuota) {
		this.maxQuota = maxQuota;
	}
	public Integer getMaxTerm() {
		return maxTerm;
	}
	public void setMaxTerm(Integer maxTerm) {
		this.maxTerm = maxTerm;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public List<Long> getIloanPdt() {
		return iloanPdt;
	}
	public void setIloanPdt(List<Long> iloanPdt) {
		this.iloanPdt = iloanPdt;
	}
	public RateType getRateType() {
		return rateType;
	}
	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}
	public YesOrNoType getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(YesOrNoType deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public BigDecimal getMinleQuota() {
		return minleQuota;
	}
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
	public void setMinleQuota(BigDecimal minleQuota) {
		this.minleQuota = minleQuota;
	}
	public Long getReqTotal() {
		return reqTotal;
	}
	public void setReqTotal(Long reqTotal) {
		this.reqTotal = reqTotal;
	}
	public Integer getCreditTerm() {
		return creditTerm;
	}
	public void setCreditTerm(Integer creditTerm) {
		this.creditTerm = creditTerm;
	}
	public BigDecimal getMaxgeQuota() {
		return maxgeQuota;
	}
	public void setMaxgeQuota(BigDecimal maxgeQuota) {
		this.maxgeQuota = maxgeQuota;
	}
	public Integer getMaxgeTerm() {
		return maxgeTerm;
	}
	public void setMaxgeTerm(Integer maxgeTerm) {
		this.maxgeTerm = maxgeTerm;
	}
	public Integer getMinleTerm() {
		return minleTerm;
	}
	public void setMinleTerm(Integer minleTerm) {
		this.minleTerm = minleTerm;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
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
	public Long getIiloanPdt() {
		return iiloanPdt;
	}
	public void setIiloanPdt(Long iiloanPdt) {
		this.iiloanPdt = iiloanPdt;
	}
}
