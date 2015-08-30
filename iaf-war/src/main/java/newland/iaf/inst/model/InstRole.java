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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 机构角色表
 * 
 * @author lindaqun
 * 
 */
@Entity
@Table(name = "t_inst_role")
public class InstRole implements Serializable, Comparable<InstRole> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3521892001055094726L;

	/**
	 * 机构角色内部编号
	 */
	@Id
	@Column(name = "i_inst_role", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst_role", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_role")
	private Long iInstRole;
	/**
	 * 机构内部编号
	 */
	@Column(name = "i_inst", nullable = false, updatable = true)
	private Long iInst;
	/**
	 * 角色名称
	 */
	@Column(name = "role_name", nullable = false, updatable = true)
	private String roleName;
	/**
	 * 描述
	 */
	@Column(name = "memo", nullable = true, updatable = true)
	private String memo;
	/**
	 * 角色状态
	 * 0:禁用 ,1:正常 
	 */
	@Column(name = "role_stat", nullable = false, updatable = true)
	private Integer roleStat;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;
	/**
	 * 1:为管理员角色,0：为普通角色
	 */
	@Column(name = "flag")
	private Integer flag;
	@Column(name = "is_check")
	private Integer isCheck;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = InstUser.class,fetch=FetchType.LAZY, mappedBy = "instRoleSet")
	private Set<InstUser> instUserSet;

	public Long getiInstRole() {
		return iInstRole;
	}

	public void setiInstRole(Long iInstRole) {
		this.iInstRole = iInstRole;
	}

	public Long getiInst() {
		return iInst;
	}

	public void setiInst(Long iInst) {
		this.iInst = iInst;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getRoleStat() {
		return roleStat;
	}

	public void setRoleStat(Integer roleStat) {
		this.roleStat = roleStat;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Set<InstUser> getInstUserSet() {
		return instUserSet;
	}

	public void setInstUserSet(Set<InstUser> instUserSet) {
		this.instUserSet = instUserSet;
	}

	@Override
	public int compareTo(InstRole o) {
		return this.hashCode() - o.hashCode();
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

}
