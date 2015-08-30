package newland.iaf.base.model.condition;

import newland.iaf.backstage.model.BsRoleAuth;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(BsRoleAuth.class)
public class BackStageRoleCondition extends DetachedCriteriaEx{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6709471231170546038L;

	@Expression(operator = Operator.eq, propertyName = "irole")
	private Long irole;
	
	@Expression(operator = Operator.like,likeMatchMode=LikeMatchMode.anywhere, propertyName = "roleName")
	private String roleName;
	
	@Expression(operator = Operator.eq, propertyName = "stat")
	private Integer stat;

	public Long getIrole() {
		return irole;
	}

	public void setIrole(Long irole) {
		this.irole = irole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}
	

	
	
}
