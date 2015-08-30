package newland.iaf.base.model.condition;

import java.util.Date;

import newland.iaf.IafApplication;
import newland.iaf.base.model.ApplyRequest;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.Operator;
@CriteriaClass(ApplyRequest.class)
public class TransReportCondition extends DetachedCriteriaEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expression(operator = Operator.eq, propertyName = "imerch")
	private Long imerch;
	
	@Expression(operator = Operator.ge, propertyName = "reportDate")
	private Date beginDate;
	
	@Expression(operator = Operator.lt, propertyName = "reportDate")
	private Date endDate;

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
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
	
	

}
