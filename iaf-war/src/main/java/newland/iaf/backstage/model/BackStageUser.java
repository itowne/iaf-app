package newland.iaf.backstage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
import newland.iaf.base.model.dict.SexType;
import newland.iaf.inst.model.InstRole;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
@Table (name = "t_bs_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "login_name" }) })
public class BackStageUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_bs_user")
	@TableGenerator(name = "i_bs_user")
	@Column(name = "i_bs_user", nullable = false, updatable = false)
	private Long iuser;
	
	@Column (name = "user_name")
	private String userName;
	
	@Column (name = "login_name")
	private String loginName;
	
	@Column (name = "password")
	private String password;
	
	/*
	 * 性别
	 */
	@Column (name = "sex_type")
	private SexType sexType;
	
	/*
	 * 地址
	 */
	@Column (name = "address")
	private String address;
	
	/*
	 * QQ号
	 */
	@Column (name = "qq_num")
	private String qqNum;
	
	/*
	 * email
	 */
	@Column (name = "email")
	private String email;
	
	/*
	 * 手机号码
	 */
	@Column (name = "mobile")
	private String mobile;

	@ManyToMany (targetEntity = BackStageRole.class,fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "t_bs_user_role"
		, joinColumns = {@JoinColumn(name  = "i_bs_user", referencedColumnName = "i_bs_user" , nullable = false)},
			inverseJoinColumns = {@JoinColumn (name = "i_bs_role", referencedColumnName = "i_bs_role", nullable = false)}
			)
	@Fetch(FetchMode.SUBSELECT)
	private Set<BackStageRole> bsRoleSet;
	
	@Column (name = "gen_time")
	private Date genTime;
	
	@Column (name = "upd_time")
	private Date updTime;

	@Transient
	private String bsRoles;
	
	/**
	 * 用户状态
	 */
	@Enumerated
	@Column(name = "status", nullable = false)
	private InstUserStatType stat;

	public Long getIuser() {
		return iuser;
	}

	public String getUserName() {
		return userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public InstUserStatType getStat() {
		return stat;
	}

	public void setStat(InstUserStatType stat) {
		this.stat = stat;
	}

	public void setIuser(Long iuser) {
		this.iuser = iuser;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public SexType getSexType() {
		return sexType;
	}

	public void setSexType(SexType sexType) {
		this.sexType = sexType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Set<BackStageRole> getBsRoleSet() {
		return bsRoleSet;
	}

	public void setBsRoleSet(Set<BackStageRole> bsRoleSet) {
		this.bsRoleSet = bsRoleSet;
	}
	
	public void setBsRoles(String bsRoles){
		this.bsRoles=bsRoles;
	}
	
	public String getBsRoles() {
		StringBuilder sb = new StringBuilder();
		for (BackStageRole bsRole : bsRoleSet) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(bsRole.getRoleName());
		}
		return sb.toString();
	}

}
