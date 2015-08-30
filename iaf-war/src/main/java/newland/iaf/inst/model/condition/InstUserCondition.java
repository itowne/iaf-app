package newland.iaf.inst.model.condition;

import java.io.Serializable;
import java.util.List;

import newland.iaf.base.model.dict.InstUserStatType;
import newland.iaf.inst.model.InstUser;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;

/**
 * @author Mr.Towne
 * 
 */
@CriteriaClass(InstUser.class)
public class InstUserCondition extends DetachedCriteriaEx implements
		Serializable {

	private static final long serialVersionUID = -241550885731700240L;

	@Expression(operator = Operator.ilike, propertyName = "loginName",likeMatchMode=LikeMatchMode.anywhere)
	private String loginName;

	@Expression(operator = Operator.ilike, propertyName = "userName",likeMatchMode=LikeMatchMode.anywhere)
	private String userName;

	@Expression(operator = Operator.eq, propertyName = "usrStat")
	private InstUserStatType usrStat;

	@Expression(operator = Operator.in, propertyName = "iinstUser")
	private List<Long> iinstUser;

	private Long iinstRole;
	
	@Expression(operator = Operator.eq, propertyName = "iinst")
	private Long iinst;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getIinstRole() {
		return iinstRole;
	}

	public void setIinstRole(Long iinstRole) {
		this.iinstRole = iinstRole;
	}

	public List<Long> getIinstUser() {
		return iinstUser;
	}

	public void setIinstUser(List<Long> iinstUser) {
		this.iinstUser = iinstUser;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	public InstUserStatType getUsrStat() {
		return usrStat;
	}

	public void setUsrStat(InstUserStatType usrStat) {
		this.usrStat = usrStat;
	}

}
