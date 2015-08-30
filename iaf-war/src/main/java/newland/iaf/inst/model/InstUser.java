/**
 * 
 */
package newland.iaf.inst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import newland.iaf.base.model.dict.InstUserStatType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Index;

/**
 * 机构用户表
 * 
 * @author lindaqun
 * 
 */
@Entity
@Table(name = "t_inst_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "login_name" }) })
@org.hibernate.annotations.Table(appliesTo = "t_inst_user", indexes = {
		@Index(name = "idx_inst_user1", columnNames = { "login_name" }),
		@Index(name = "idx_inst_user2", columnNames = { "user_name" }) })
public class InstUser implements Serializable, Comparable<InstUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010808897663560267L;

	/**
	 * 机构用户编号
	 */
	@Id
	@TableGenerator(name = "i_inst_user", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_user")
	@Column(name = "i_inst_user", nullable = false, updatable = false)
	private Long iinstUser;
	/**
	 * 机构编号
	 */
	@Column(name = "i_inst", nullable = false, updatable = true)
	private Long iinst;
	/**
	 * 登录名
	 */
	@Column(name = "login_name", nullable = false, updatable = true)
	private String loginName;
	/**
	 * 用户名
	 */
	@Column(name = "user_name", nullable = false, updatable = true)
	private String userName;
	/**
	 * 密码
	 */
	@Column(name = "passwd", nullable = false, updatable = true)
	private String passwd;
	/**
	 * 用户状态
	 */
	@Column(name = "usr_stat", nullable = false, updatable = true)
	private InstUserStatType usrStat;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;
	/**
	 * 更新时间
	 */
	@Column(name = "upd_time", nullable = false, updatable = true)
	private Date updTime;
	/**
	 * 最后登陆时间
	 */
	@Column(name = "last_login_time", nullable = false, updatable = true)
	private Date lastLoginTime;

	/**
	 * 职位
	 */
	@Column(name = "position", nullable = false, updatable = true)
	private String position;

	/**
	 * 电子邮件
	 */
	@Column(name = "mail", nullable = true, updatable = true)
	private String Mail;

	@Column(name = "qqnum", nullable = true, updatable = true)
	private String QQNum;

	/**
	 * 手机
	 */
	@Column(name = "phone", nullable = true, updatable = true)
	private String phone;
	
	/**
	 * 0:普通用户，1:管理员用户
	 */
	@Column(name = "flag")
	private Integer flag;
	/**
	 * 角色
	 */
	@ManyToMany(targetEntity = InstRole.class, fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "t_inst_user_role", joinColumns = { @JoinColumn(name = "i_inst_user", referencedColumnName = "i_inst_user", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "i_inst_role", referencedColumnName = "i_inst_role", nullable = false) })
	@Fetch(FetchMode.SUBSELECT)
	private Set<InstRole> instRoleSet;

	/**
	 * 
	 */
	@Transient
	private String instRoles;

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public InstUserStatType getUsrStat() {
		return usrStat;
	}

	public void setUsrStat(InstUserStatType usrStat) {
		this.usrStat = usrStat;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Long getIinstUser() {
		return iinstUser;
	}

	public void setIinstUser(Long iinstUser) {
		this.iinstUser = iinstUser;
	}

	public Set<InstRole> getInstRoleSet() {
		return instRoleSet;
	}

	public void setInstRoleSet(Set<InstRole> instRoleSet) {
		this.instRoleSet = instRoleSet;
	}

	public String getInstRoles() {
		StringBuilder sb = new StringBuilder();
		for (InstRole instRole : instRoleSet) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(instRole.getRoleName());
		}
		return sb.toString();
	}

	@Override
	public int compareTo(InstUser o) {
		if (o == null)
			throw new RuntimeException("");
		if (o.getIinst() == null) {
			return this.getIinst().intValue();
		} else
			return iinst.intValue() - o.getIinst().intValue();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String Mail) {
		this.Mail = Mail;
	}

	public String getQQNum() {
		return QQNum;
	}

	public void setQQNum(String qQNum) {
		QQNum = qQNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setInstRoles(String instRoles) {
		this.instRoles = instRoles;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
