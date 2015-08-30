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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;

/**
 * 与InstRole相同,用作角色与权限关联
 * 
 * @author Mr.Towne
 * 
 */
@Entity(name = "roleAuth")
@Table(name = "t_inst_role")
public class RoleAuth implements Serializable, Comparable<RoleAuth> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6539596487403533507L;
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
	 */
	@Column(name = "role_stat", nullable = false, updatable = true)
	private Integer roleStat;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;
	
	@Column(name = "flag")
	private Integer flag;

	@ManyToMany(targetEntity = InstAuth.class, fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "t_inst_role_auth", joinColumns = { @JoinColumn(name = "i_inst_role", referencedColumnName = "i_inst_role", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "i_inst_auth", referencedColumnName = "i_inst_auth", nullable = false) })
	@Fetch(FetchMode.SUBSELECT)
	private Set<InstAuth> instAuthSet;
	
	@Column(name = "is_check")
	private Integer isCheck;

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

	@Override
	public int compareTo(RoleAuth o) {
		return this.hashCode() - o.hashCode();
	}

	// if (o == null) {
	// throw new RuntimeException("o is null ...");
	// } else {
	// if (o.getiInstRole().toString() == null
	// || o.getiInstRole().toString().equals("")) {
	// return o.getiInstRole().intValue();
	// } else {
	// return iInstRole.intValue() - o.getiInstRole().intValue();
	// }
	// }

	public Set<InstAuth> getInstAuthSet() {
		return instAuthSet;
	}

	public void setInstAuthSet(Set<InstAuth> instAuthSet) {
		this.instAuthSet = instAuthSet;
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
