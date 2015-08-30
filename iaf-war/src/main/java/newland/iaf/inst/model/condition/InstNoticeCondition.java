/**
 * 
 */
package newland.iaf.inst.model.condition;

import java.io.Serializable;
import java.util.Date;

import newland.iaf.base.model.dict.GeneralStatus;
import newland.iaf.inst.model.InstNotice;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

/**
 * @author lxf
 * 
 */
@CriteriaClass(InstNotice.class)
public class InstNoticeCondition extends DetachedCriteriaEx implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -241550885731700240L;

	@Expression(operator = Operator.eq, propertyName = "iinstNotice")
	private Long iinstNotice;
	@Expression(operator = Operator.ilike, propertyName = "title",likeMatchMode=LikeMatchMode.anywhere)
	private String title;
	
	@Expression(operator = Operator.eq, propertyName = "status")
	private GeneralStatus status = GeneralStatus.VALID;
	
	@Expression(operator = Operator.eq, propertyName = "updTime")
	private Date updTime;

	public Long getIinstNotice() {
		return iinstNotice;
	}
	public void setIinstNotice(Long iinstNotice) {
		this.iinstNotice = iinstNotice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public GeneralStatus getStatus() {
		return status;
	}
	public void setStatus(GeneralStatus status) {
		this.status = status;
	}
	public Date getUpdTime() {
		return updTime;
	}
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
}
