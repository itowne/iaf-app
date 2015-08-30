package newland.iaf.backstage.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;



public class BackStageRoleAuth implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 808856693713162375L;

	
	/**
	 * 机构角色权限内部编号
	 */
	@Id
	@Column(name = "i_bs_role_auth", nullable = false, updatable = false)
	@TableGenerator(name = "i_bs_role_auth", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_bs_role_auth")
	private Long iBsRoleAuth;

	/**
	 * 机构角色内部编号
	 */
	@Column(name = "i_bs_role", nullable = false, updatable = false)
	private Long iBsRole;
	/**
	 * 机构权限内部编号
	 */
	@Column(name = "i_bs_auth", nullable = false, updatable = false)
	private Long iBsAuth;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;
	public Long getiBsRoleAuth() {
		return iBsRoleAuth;
	}
	public void setiBsRoleAuth(Long iBsRoleAuth) {
		this.iBsRoleAuth = iBsRoleAuth;
	}
	public Long getiBsRole() {
		return iBsRole;
	}
	public void setiBsRole(Long iBsRole) {
		this.iBsRole = iBsRole;
	}
	public Long getiBsAuth() {
		return iBsAuth;
	}
	public void setiBsAuth(Long iBsAuth) {
		this.iBsAuth = iBsAuth;
	}
	public Date getGenTime() {
		return genTime;
	}
	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}
}
