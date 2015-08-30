/**
 * 
 */
package newland.iaf.inst.model.condition;

import java.io.Serializable;
import java.util.List;

import newland.iaf.inst.model.RoleAuth;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.Operator;

/**
 * @author Mr.Towne
 *
 */
@CriteriaClass(RoleAuth.class)
public class InstRoleCondition  extends DetachedCriteriaEx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7377820168256305586L;
	
	
	@Expression(operator=Operator.like,propertyName="roleName")
	private String roleName;
	
	@Expression(operator=Operator.eq,propertyName="roleStat")
	private Integer roleStat;
		
	@Expression(operator = Operator.eq, propertyName = "iInst")
	private Long iInst;
		
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleStat() {
		return roleStat;
	}
	public void setRoleStat(Integer roleStat) {
		this.roleStat = roleStat;
	}
	public Long getiInst() {
		return iInst;
	}
	public void setiInst(Long iInst) {
		this.iInst = iInst;
	}
}
