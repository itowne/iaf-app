package newland.iaf.base.model.condition;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import newland.iaf.IafApplication;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;
@CriteriaClass(FundFlow.class)
public class FundFlowCondition extends DetachedCriteriaEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 流水类型
	 */
	@Expression(operator = Operator.eq, propertyName = "type")
	private FundFlowType type;
	
	@Expression(operator = Operator.in, propertyName = "type")
	private Set<FundFlowType> types;
	/**
	 * 状态集合
	 */
	@Expression(operator = Operator.in, propertyName = "status")
	private Set<FundFlowStat> status;
	/**
	 * 状态
	 */
	@Expression(operator = Operator.eq, propertyName = "status")
	private FundFlowStat stat;
	
	@Expression(operator = Operator.eq, propertyName = "amount")
	private BigDecimal quota;
	
	@Expression(operator = Operator.ge, propertyName = "amount")
	private BigDecimal minQuota;
	
	@Expression(operator = Operator.le, propertyName = "amount")
	private BigDecimal maxQuota;
	
	@Expression(operator = Operator.ilike, propertyName = "loanOrdId")
	private String loanOrdId;
	
	@Expression(operator = Operator.eq, propertyName = "iloanOrd")
	private Long iloanOrd;
	
	@Expression(operator = Operator.ilike, propertyName = "instName")
	private String instName;
	
	@Expression(operator = Operator.eq, propertyName = "iinst")
	private Long iinst;
	
	@Expression(operator = Operator.eq, propertyName = "imerch")
	private Long imerch;
	
	@Expression(operator = Operator.ilike,likeMatchMode=LikeMatchMode.anywhere, propertyName = "merchName")
	private String merchName;
	
	@Expression(operator = Operator.ge, propertyName = "genTime")
	private Date beginDate;
	
	@Expression(operator = Operator.lt, propertyName = "genTime")
	private Date endDate;
	
	@Expression(operator = Operator.eq, propertyName = "otherSysTraceNo")
	private String otherSysNo;
	
	public FundFlowType getType() {
		return type;
	}
	public void setType(FundFlowType type) {
		this.type = type;
	}
	public Set<FundFlowStat> getStatus() {
		return status;
	}
	public void setStatus(Set<FundFlowStat> status) {
		this.status = status;
	}
	public FundFlowStat getStat() {
		return stat;
	}
	public void setStat(FundFlowStat stat) {
		this.stat = stat;
	}
	public String getLoanOrdId() {
		return loanOrdId;
	}
	public void setLoanOrdId(String loanOrdId) {
		this.loanOrdId = loanOrdId;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getMerchName() {
		return merchName;
	}
	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = IafApplication.roundToBeginDate(beginDate);
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = IafApplication.roundToEndDate(endDate);
	}
	public Long getIloanOrd() {
		return iloanOrd;
	}
	public void setIloanOrd(Long iloanOrd) {
		this.iloanOrd = iloanOrd;
	}
	public Set<FundFlowType> getTypes() {
		return types;
	}
	public void setTypes(Set<FundFlowType> types) {
		this.types = types;
	}
	public String getOtherSysNo() {
		return otherSysNo;
	}
	public void setOtherSysNo(String otherSysNo) {
		this.otherSysNo = otherSysNo;
	}
	public BigDecimal getQuota() {
		return quota;
	}
	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}
	public Long getIinst() {
		return iinst;
	}
	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}
	public Long getImerch() {
		return imerch;
	}
	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}
	public BigDecimal getMinQuota() {
		return minQuota;
	}
	public void setMinQuota(BigDecimal minQuota) {
		this.minQuota = minQuota;
	}
	public BigDecimal getMaxQuota() {
		return maxQuota;
	}
	public void setMaxQuota(BigDecimal maxQuota) {
		this.maxQuota = maxQuota;
	}

}
