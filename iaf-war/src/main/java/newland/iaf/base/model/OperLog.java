package newland.iaf.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;

import org.hibernate.annotations.Index;

/**
 * 操作流水类
 * 
 * @author new
 * 
 */
@Entity
@Table (name = "t_oper_log")
@Inheritance(strategy = InheritanceType.JOINED)
@org.hibernate.annotations.Table(appliesTo = "t_oper_log", indexes = { 
		@Index(name = "idx_oper_log1", columnNames = { "i_inst" }) ,
		@Index(name = "idx_oper_log2", columnNames = { "i_inst", "gen_time" }),
		@Index(name = "idx_oper_log3", columnNames = { "i_merch"}) ,
		@Index(name = "idx_oper_log4", columnNames = { "i_merch","gen_time" }) 
		})
public class OperLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 跟踪号
	 */
	@Id
	@Column (name = "trace_no", length=16)
	String traceNo;
	/**
	 * 生成日期
	 */
	@Column (name = "gen_time", nullable = false)
	Date genTime;
	/**
	 * 
	 */
	@Column (name = "ip_addr")
	String ipAddr;
	/**
	 * 备注
	 */
	@Column (name = "memo")
	String memo;
	/**
	 * 角色
	 */
	@Column (name = "i_roles", length = 100)
	String iroles;
	/**
	 * 角色名称
	 */
	@Column (name = "role_names")
	String roleNames;
	/**
	 * 用户
	 */
	@Column (name = "i_user")
	Long iuser;
	/**
	 * 用户名称
	 */
	@Column (name = "user_name")
	String userName;
	/**
	 * 登录名称
	 */
	@Column (name = "login_name", nullable = false)
	String loginName;
	/**
	 * 机构代号
	 */
	@Column (name = "i_inst", updatable = false)
	Long iinst;
	
	@Column (name = "inst_id", length = 50)
	String instId;
	
	@Column (name = "i_merch")
	Long imerch;
	
	@Column (name = "merch_id", length = 50)
	String merchId;

	
	/**
	 * 机构类型
	 */
	@Enumerated
	@Column (name = "inst_type", nullable = false)
	InstType instType;
	/**
	 * 操作类型/概述
	 */
	@Enumerated
	@Column (name = "oper_type", nullable = false)
	OperType operType;
	/**
	 * 操作结果
	 */
	@Column (name = "oper_result")
	String operResult;
	/**
	 * 操作状态
	 */
	@Enumerated
	@Column (name = "oper_stat", nullable = false)
	OperStat operStat;

	public String getTraceNo() {
		return traceNo;
	}

	public Date getGenTime() {
		return genTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public String getMemo() {
		return memo;
	}

	public String getIroles() {
		return iroles;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public Long getIuser() {
		return iuser;
	}

	public String getUserName() {
		return userName;
	}

	public Long getIinst() {
		return iinst;
	}

	public InstType getInstType() {
		return instType;
	}

	public OperType getOperType() {
		return operType;
	}

	public String getOperResult() {
		return operResult;
	}

	public OperStat getOperStat() {
		return operStat;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setIroles(String role) {
		this.iroles = role;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public void setIuser(Long user) {
		this.iuser = user;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	public void setInstType(InstType instType) {
		this.instType = instType;
	}

	public void setOperType(OperType operType) {
		this.operType = operType;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	public void setOperStat(OperStat operStat) {
		this.operStat = operStat;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

}
