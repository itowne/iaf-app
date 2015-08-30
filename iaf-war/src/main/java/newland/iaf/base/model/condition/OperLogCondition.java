package newland.iaf.base.model.condition;

import java.util.Date;

import newland.iaf.IafApplication;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.Operator;
@CriteriaClass(OperLog.class)
public class OperLogCondition extends DetachedCriteriaEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 机构内部编号
	 */
	@Expression(operator = Operator.eq, propertyName = "iinst")
	private Long iinst;
	/**
	 * 机构代码
	 */
	@Expression(operator = Operator.eq, propertyName = "instId")
	private String instId;
	
	@Expression(operator = Operator.eq, propertyName = "imerch")
	private Long imerch;
	
	@Expression(operator = Operator.eq, propertyName = "merchId")
	private String merchId;
	/**
	 * 操作类型
	 */
	@Expression(operator = Operator.eq, propertyName = "operStat")
	private OperStat operStat;
	/**
	 * 生成日期
	 */
	@Expression(operator = Operator.eq, propertyName = "genTime")
	private Date genTime;

	/**
	 * 角色代号
	 */
	@Expression(operator = Operator.eq, propertyName = "irole")
	private Long irole;
	/**
	 * 用户代号
	 */
	@Expression(operator = Operator.eq, propertyName = "iuser")
	private Long iuser;
	/**
	 * 登录名称
	 */
	@Expression(operator = Operator.eq, propertyName = "loginName")
	private String loginName;
	/**
	 * 机构类型
	 */
	@Expression(operator = Operator.eq, propertyName = "instType")
	private InstType instType;
	/**
	 * 起始时间
	 */
	@Expression(operator = Operator.ge, propertyName = "genTime")
	private Date beginTime;
	
	/**
	 * 结束时间
	 */
	@Expression(operator = Operator.lt, propertyName = "genTime")
	private Date endTime;
	
	/**
	 * ip地址
	 */
	@Expression(operator = Operator.ilike, propertyName = "ipAddr")
	private String ipAddr;
	
	
	public Long getIinst() {
		return iinst;
	}
	public String getInstId() {
		return instId;
	}
	public OperStat getOperStat() {
		return operStat;
	}
	public Date getGenTime() {
		return genTime;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public void setOperStat(OperStat operStat) {
		this.operStat = operStat;
	}
	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Long getIrole() {
		return irole;
	}
	public Long getIuser() {
		return iuser;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setIrole(Long irole) {
		this.irole = irole;
	}
	public void setIuser(Long iuser) {
		this.iuser = iuser;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public InstType getInstType() {
		return instType;
	}
	public void setInstType(InstType instType) {
		this.instType = instType;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = IafApplication.roundToBeginDate(beginTime);
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = IafApplication.roundToEndDate(endTime);
	}
	public Long getImerch() {
		return imerch;
	}
	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}
	public String getMerchId() {
		return merchId;
	}
	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

}
