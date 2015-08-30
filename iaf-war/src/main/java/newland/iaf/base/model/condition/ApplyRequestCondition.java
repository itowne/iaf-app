package newland.iaf.base.model.condition;

import java.util.Date;
import java.util.List;
import java.util.Set;

import newland.iaf.IafApplication;
import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.ApplyType;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(ApplyRequest.class)
public class ApplyRequestCondition extends DetachedCriteriaEx {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expression(operator = Operator.in, propertyName = "iinst")
	private List<Long> iinstList;

	@Expression(operator = Operator.ilike,likeMatchMode=LikeMatchMode.anywhere, propertyName = "instName")
	private String instName;
	
	@Expression(operator = Operator.ilike,likeMatchMode=LikeMatchMode.anywhere, propertyName = "merchName")
	private String merchName;
	
	@Expression(operator = Operator.eq, propertyName = "genTime")
	private Date genTime;
	
	@Expression(operator = Operator.ge, propertyName = "genTime")
	private Date beginDate;
	
	@Expression(operator = Operator.lt, propertyName = "genTime")
	private Date endDate;
	
	@Expression(operator = Operator.ge, propertyName = "acceptDate")
	private Date acceptbeginDate;
	
	@Expression(operator = Operator.lt, propertyName = "acceptDate")
	private Date acceptendDate;
	
	@Expression(operator = Operator.eq, propertyName = "batchId")
	private String batchId;
	
	@Expression(operator = Operator.eq, propertyName = "stat")
	private ApplyStat stat;
	
	@Expression(operator = Operator.like, propertyName = "merchNo")
	private String merchNo;
	
	@Expression(operator = Operator.eq, propertyName = "applyType")
	private ApplyType applyType;
	
	@Expression(operator = Operator.eq, propertyName = "iinst")
	private Long iinst;
	
	@Expression(operator = Operator.eq, propertyName = "iloanOrd")
	private Long iloanOrd;
	
	@Expression(operator = Operator.in, propertyName = "stat")
	private Set<ApplyStat> status;

	public Set<ApplyStat> getStatus() {
		return status;
	}

	public void setStatus(Set<ApplyStat> status) {
		this.status = status;
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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public ApplyStat getStat() {
		return stat;
	}

	public void setStat(ApplyStat stat) {
		this.stat = stat;
	}

	public ApplyType getApplyType() {
		return applyType;
	}

	public void setApplyType(ApplyType applyType) {
		this.applyType = applyType;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	public Long getIloanOrd() {
		return iloanOrd;
	}

	public void setIloanOrd(Long iloanOrd) {
		this.iloanOrd = iloanOrd;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public List<Long> getIinstList() {
		return iinstList;
	}

	public void setIinstList(List<Long> iinstList) {
		this.iinstList = iinstList;
	}

	public Date getAcceptbeginDate() {
		return acceptbeginDate;
	}

	public void setAcceptbeginDate(Date acceptbeginDate) {
		this.acceptbeginDate = acceptbeginDate;
	}

	public Date getAcceptendDate() {
		return acceptendDate;
	}

	public void setAcceptendDate(Date acceptendDate) {
		this.acceptendDate = acceptendDate;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

}
