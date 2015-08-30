package newland.iaf.inst.model.condition;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import newland.iaf.IafApplication;
import newland.iaf.base.model.dict.InstStatusType;
import newland.iaf.inst.model.Inst;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(Inst.class)
public class InstCondition extends DetachedCriteriaEx implements Serializable{

	private static final long serialVersionUID = -8001607815054854842L;
	
	/**
	 * 内部编号机构
	 */
	@Expression(operator = Operator.in, propertyName = "iinst")
	private List<Long> iinst;
	/**
	 * 机构名称
	 */
	@Expression(operator = Operator.ilike, propertyName = "instName",likeMatchMode=LikeMatchMode.anywhere)
	private String instName;
	
	@Expression(operator = Operator.ilike, propertyName = "contact")
	private String contact;
	
	@Expression(operator = Operator.ilike, propertyName = "contactPhone")
	private String contactPhone;
	/**
	 * 机构性质
	 */
	@Expression(operator = Operator.eq, propertyName = "instNature")
	private String instNature;
	
	@Expression(operator = Operator.eq, propertyName = "instStat")
	private InstStatusType instStatusType;
	
	@Expression(operator = Operator.ge, propertyName = "genTime")
	private Date startGenTime;
	
	@Expression(operator = Operator.le, propertyName = "genTime")
	private Date endGenTime;
	/**
	 * 注册地址
	 */
	@Expression(operator = Operator.ilike, propertyName = "regAddr",likeMatchMode=LikeMatchMode.anywhere)
	private String regAddr;
	
	/**
	 * 注册资金
	 */
	@Expression(operator = Operator.eq, propertyName = "regCapital")
	private BigDecimal regCapital;
	/**
	 * 放贷产品数量
	 * @return
	 */
	@Expression(operator = Operator.eq, propertyName = "pdtCount")
	private Long loanPdtCount;
	
	/**
	 * 受理成功率
	 */
	@Expression(operator = Operator.eq, propertyName = "successRate")
	private BigDecimal successRate;
	
	public Long getLoanPdtCount() {
		return loanPdtCount;
	}
	public void setLoanPdtCount(Long loanPdtCount) {
		this.loanPdtCount = loanPdtCount;
	}
	public List<Long> getIinst() {
		return iinst;
	}
	public void setIinst(List<Long> iinst) {
		this.iinst = iinst;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public BigDecimal getRegCapital() {
		return regCapital;
	}
	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}
	public String getRegAddr() {
		return regAddr;
	}
	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}
	public String getInstNature() {
		return instNature;
	}
	public void setInstNature(String instNature) {
		this.instNature = instNature;
	}
	public BigDecimal getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(BigDecimal successRate) {
		this.successRate = successRate;
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
	public InstStatusType getInstStatusType() {
		return instStatusType;
	}
	public void setInstStatusType(InstStatusType instStatusType) {
		this.instStatusType = instStatusType;
	}
	public Date getStartGenTime() {
		return startGenTime;
	}
	public void setStartGenTime(Date startGenTime) {
		this.startGenTime = IafApplication.roundToBeginDate(startGenTime);
	}
	public Date getEndGenTime() {
		return endGenTime;
	}
	public void setEndGenTime(Date endGenTime) {
		this.endGenTime = IafApplication.roundToEndDate(endGenTime);
	}
	
}
