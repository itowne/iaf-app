package newland.iaf.backstage.model;

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

import newland.iaf.backstage.action.BackstageRoleAction;
import newland.iaf.inst.model.InstAuthType;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.RoleAuth;


@Entity
@Table(name = "t_bs_auth")
public class BackStageAuth implements Serializable, Comparable<BackStageAuth> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 238178268085573328L;
	
	@Id
	@Column(name = "i_bs_auth", nullable = false, updatable = false)
	@TableGenerator(name = "i_bs_auth", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_bs_auth")
	private Long iBsAuth;

	/**
	 * 
	 */
	@Column(name = "p_i_bs_auth", updatable = true)
	private Long parentIBsAuth;

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

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = BsRoleAuth.class, mappedBy = "bsAuthSet")
	private Set<BackStageRole> bsRoleSet;

	@Override
	public int compareTo(BackStageAuth o) {
		return this.hashCode() - o.hashCode();
	}

	public Long getiBsAuth() {
		return iBsAuth;
	}

	public void setiBsAuth(Long iBsAuth) {
		this.iBsAuth = iBsAuth;
	}

	public Long getParentIBsAuth() {
		return parentIBsAuth;
	}

	public void setParentIBsAuth(Long parentIBsAuth) {
		this.parentIBsAuth = parentIBsAuth;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public InstAuthType getAuthType() {
		return authType;
	}

	public void setAuthType(InstAuthType authType) {
		this.authType = authType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<BackStageRole> getBsRoleSet() {
		return bsRoleSet;
	}

	public void setBsRoleSet(Set<BackStageRole> bsRoleSet) {
		this.bsRoleSet = bsRoleSet;
	}

}
