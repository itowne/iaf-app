package newland.iaf.base.model.condition;

import java.util.Date;

import newland.iaf.IafApplication;
import newland.iaf.merch.model.LoanCreditReport;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.Operator;
@CriteriaClass(LoanCreditReport.class)
public class CreditReportCondition extends DetachedCriteriaEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expression(operator = Operator.eq, propertyName = "reportId")
	private String reportId;
	
	@Expression(operator = Operator.eq, propertyName = "ireport")
	private Long ireport;
	
	@Expression(operator = Operator.eq, propertyName = "imerch")
	private Long imerch;
	
	@Expression(operator = Operator.eq, propertyName = "merchName")
	private String merchName;
	
	@Expression(operator = Operator.ge, propertyName = "reportDate")
	private Date beginDate;
	
	@Expression(operator = Operator.lt, propertyName = "reportDate")
	private Date endDate;
	
	@Expression(operator = Operator.eq, propertyName = "status")
	private Integer status;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public Long getIreport() {
		return ireport;
	}

	public void setIreport(Long ireport) {
		this.ireport = ireport;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
