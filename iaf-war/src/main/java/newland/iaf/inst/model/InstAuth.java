/**
 * 
 */
package newland.iaf.inst.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 机构权限表
 * 
 * @author lindaqun
 * 
 */
@Entity
@Table(name = "t_inst_auth")
public class InstAuth implements Serializable, Comparable<InstAuth> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1093569098606579815L;

	/**
	 * 机构权限内部编号
	 */
	@Id
	@Column(name = "i_inst_auth", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst_auth", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_auth")
	private Long iInstAuth;

	/**
	 * 
	 */
	@Column(name = "p_i_inst_auth", updatable = true)
	private Long parentIinstAuth;

	/**
	 * 
	 */
	@Column(name = "auth_code", nullable = false, updatable = true)
	private String authCode;

	/**
	 * 机构权限名称
	 */
	@Column(name = "auth_name", nullable = false, updatable = true)
	private String authName;

	/**
	 * 菜单名称
	 */
	@Column(name = "menu_name", updatable = true)
	private String menuName;

	/**
	 * 
	 */
	@Column(name = "memo", nullable = false, updatable = true)
	private String memo;

	/**
	 * 权限类型
	 */
	@Column(name = "auth_type", nullable = false, updatable = true)
	@Enumerated(EnumType.STRING)
	private InstAuthType authType;

	/**
	 * url
	 */
	@Column(name = "url", nullable = false, updatable = true)
	private String url;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = RoleAuth.class, mappedBy = "instAuthSet")
	private Set<InstRole> instRoleSet;

	public Long getiInstAuth() {
		return iInstAuth;
	}

	public void setiInstAuth(Long iInstAuth) {
		this.iInstAuth = iInstAuth;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<InstRole> getInstRoleSet() {
		return instRoleSet;
	}

	public void setInstRoleSet(Set<InstRole> instRoleSet) {
		this.instRoleSet = instRoleSet;
	}

	@Override
	public int compareTo(InstAuth o) {
		return this.hashCode() - o.hashCode();
	}

	// if (o == null) {
	// throw new RuntimeException("o is null ...");
	// } else {
	// if (o.getiInstAuth().toString() == null
	// || o.getiInstAuth().toString().equals("")) {
	// return o.getiInstAuth().intValue();
	// } else {
	// return iInstAuth.intValue() - o.getiInstAuth().intValue();
	// }
	// }

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public InstAuthType getAuthType() {
		return authType;
	}

	public void setAuthType(InstAuthType authType) {
		this.authType = authType;
	}

	public Long getParentIinstAuth() {
		return parentIinstAuth;
	}

	public void setParentIinstAuth(Long parentIinstAuth) {
		this.parentIinstAuth = parentIinstAuth;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}
