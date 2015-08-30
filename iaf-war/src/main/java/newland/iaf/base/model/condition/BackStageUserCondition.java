package newland.iaf.base.model.condition;

import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.base.model.dict.InstUserStatType;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(BackStageUser.class)
public class BackStageUserCondition extends DetachedCriteriaEx{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1803672771343795194L;

	@Expression(operator = Operator.eq, propertyName = "iuser")
	private Long iuser;
	
	@Expression(operator = Operator.like,likeMatchMode=LikeMatchMode.anywhere, propertyName = "userName")
	private String userName;
	
	@Expression(operator = Operator.ilike,likeMatchMode=LikeMatchMode.anywhere, propertyName = "loginName")
	private String loginName;
	
	@Expression(operator = Operator.eq, propertyName = "stat")
	private InstUserStatType status;
	
	private Long iBsRole;

	public Long getIuser() {
		return iuser;
	}

	public void setIuser(Long iuser) {
		this.iuser = iuser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public InstUserStatType getStatus() {
		return status;
	}

	public void setStatus(InstUserStatType status) {
		this.status = status;
	}

	public Long getiBsRole() {
		return iBsRole;
	}

	public void setiBsRole(Long iBsRole) {
		this.iBsRole = iBsRole;
	}
	
	
}
