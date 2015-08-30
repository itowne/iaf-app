package newland.iaf.backstage.model;

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

import newland.iaf.inst.model.InstAuth;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * 与backStageRole相同,用作角色与权限关联
 * @author Mr.Towne
 *
 */
@Entity(name = "bsRoleAuth")
@Table(name = "t_bs_role")
public class BsRoleAuth implements Serializable, Comparable<BsRoleAuth>{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -3165163623699463732L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_bs_role")
	@TableGenerator(name = "i_bs_role",initialValue = 100, allocationSize = 100)
	@Column (name = "i_bs_role",nullable = false, updatable = false)
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
	
	@ManyToMany(targetEntity = BackStageAuth.class, fetch = FetchType.EAGER, cascade = {
		CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "t_bs_role_auth", joinColumns = { @JoinColumn(name = "i_bs_role", referencedColumnName = "i_bs_role", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "i_bs_auth", referencedColumnName = "i_bs_auth", nullable = false) })
	@Fetch(FetchMode.SUBSELECT)
	private Set<BackStageAuth> bsAuthSet;

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

	public int compareTo(BsRoleAuth o) {
		// TODO Auto-generated method stub
		return this.hashCode() - o.hashCode();
	}

	public Set<BackStageAuth> getBsAuthSet() {
		return bsAuthSet;
	}

	public void setBsAuthSet(Set<BackStageAuth> bsAuthSet) {
		this.bsAuthSet = bsAuthSet;
	}

}
