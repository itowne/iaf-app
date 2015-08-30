package newland.iaf.merch.model.condition;

import newland.iaf.base.model.dict.ForbidenType;
import newland.iaf.merch.model.MerchUser;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(MerchUser.class)
public class MerchUserCondition extends DetachedCriteriaEx{
	
	/**
	 * 商户内部编号
	 */
	@Expression(operator = Operator.eq, propertyName = "loginName")
	private String loginName;
	/**
	 * 商户内部编号
	 */
	@Expression(operator = Operator.eq, propertyName = "userName")
	private String userName;
	
	@Expression(operator = Operator.eq, propertyName = "merchStat")
	private ForbidenType merchStat;
	/**
	 * 商户内部编号
	 */
	@Expression(operator = Operator.eq, propertyName = "imerch")
	private Long imerch;
	public Long getImerch() {
		return imerch;
	}
	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}
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
	public ForbidenType getMerchStat() {
		return merchStat;
	}
	public void setMerchStat(ForbidenType merchStat) {
		this.merchStat = merchStat;
	}
	
	
	
}
