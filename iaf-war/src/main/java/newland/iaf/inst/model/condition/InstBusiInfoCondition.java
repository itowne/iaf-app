package newland.iaf.inst.model.condition;

import java.io.Serializable;

import newland.iaf.inst.model.InstBusiInfo;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(InstBusiInfo.class)
public class InstBusiInfoCondition extends DetachedCriteriaEx implements Serializable{

	private static final long serialVersionUID = 2715360815162213221L;
	
	/**
	 * 机构ID
	 */
	@Expression(operator = Operator.eq, propertyName = "iInst")
	private Long iInst;;

	/**
	 * 受理地区
	 */
	@Expression(operator = Operator.ilike, propertyName = "acceptRegion",likeMatchMode=LikeMatchMode.anywhere)
	private String acceptRegion;
	
	public String getAcceptRegion() {
		return acceptRegion;
	}

	public void setAcceptRegion(String acceptRegion) {
		this.acceptRegion = acceptRegion;
	}
	
	
	
}
