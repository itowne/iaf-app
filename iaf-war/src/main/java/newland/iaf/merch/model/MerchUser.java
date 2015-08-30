package newland.iaf.merch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import newland.iaf.base.model.dict.ForbidenType;

import org.hibernate.annotations.Index;

/**
 * 
 * 商户用户
 * 
 * @author wwa
 * 
 */
@Entity
@Table(name = "t_merch_user", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"login_name" }) })
@org.hibernate.annotations.Table(appliesTo = "t_merch_user", indexes = {
		@Index(name = "idx_merch_user1", columnNames = { "merch_no" }),
		@Index(name = "idx_merch_user2", columnNames = { "login_name" }) })
public class MerchUser implements Serializable {

	private static final long serialVersionUID = -5320040315742017533L;

	/**
	 * 内部编号
	 */
	@Id
	@TableGenerator(name = "i_merch_user", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_merch_user")
	@Column(name = "i_merch_user", updatable = false)
	private Long imerchUser;

	/**
	 * 商户内部编号
	 */
	@Column(name = "i_merch", nullable = false, updatable = false)
	private Long imerch;

	/**
	 * 商户编号
	 */
	@Column(name = "merch_no", length = 30, updatable = false)
	private String merchNo;

	/**
	 * 登录名称
	 */
	@Column(name = "login_name", length = 50, nullable = false, updatable = true)
	private String loginName;

	/**
	 * 
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", length = 30, nullable = false, updatable = false)
	private MerchUserType userType;

	/**
	 * 密码
	 */
	@Column(name = "passwd", length = 50)
	private String passwd;

	/**
	 * 姓名
	 */
	@Column(name = "user_name", length = 50)
	private String userName;

	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	/**
	 * 修改时间
	 */
	@Column(name = "upd_time", nullable = false)
	private Date updTime;

	/**
	 * 最后登录时间
	 */
	@Column(name = "last_login_time")
	private Date lastLoginTime;

	/**
	 * 商户状态
	 */
	@Column(name = "merch_stat", length = 50, nullable = false)
	private ForbidenType merchStat;

	public Long getImerchUser() {
		return imerchUser;
	}

	public void setImerchUser(Long imerchUser) {
		this.imerchUser = imerchUser;
	}

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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public ForbidenType getMerchStat() {
		return merchStat;
	}

	public void setMerchStat(ForbidenType merchStat) {
		this.merchStat = merchStat;
	}

	public MerchUserType getUserType() {
		return userType;
	}

	public void setUserType(MerchUserType userType) {
		this.userType = userType;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

}
