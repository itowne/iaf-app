package newland.iaf.backstage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;

@Entity
@Table (name = "t_bs_role")
public class BackStageRole implements Serializable,Comparable<BackStageRole> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3873116545591663734L;

	/**
	 * 
	 */	
	@Id
	@Column (name = "i_bs_role",nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_bs_role")
	@TableGenerator(name = "i_bs_role",initialValue = 100, allocationSize = 100)
	private Long iBsRole;
	
	@Column (name = "role_name")
	private String roleName;
	
	@Column (name = "role_desc")
	private String roleDesc;
	
	@Column (name = "gen_time")
	private Date genTime;
	
	@Column (name = "upd_time")
	private Date updTime;
	
	@Column (name = "status")
	private Integer stat;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = BackStageUser.class,fetch=FetchType.LAZY, mappedBy = "bsRoleSet")
	private Set<BackStageUser> bsUserSet;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	public Long getiBsRole() {
		return iBsRole;
	}

	public void setiBsRole(Long iBsRole) {
		this.iBsRole = iBsRole;
	}

	public Set<BackStageUser> getBsUserSet() {
		return bsUserSet;
	}

	public void setBsUserSet(Set<BackStageUser> bsUserSet) {
		this.bsUserSet = bsUserSet;
	}

	@Override
	public int compareTo(BackStageRole o) {
		// TODO Auto-generated method stub
		return this.hashCode() - o.hashCode();
	}

}
