/**
 * 
 */
package newland.iaf.inst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Index;

/**
 * 机构角色权限表
 * 
 * @author lindaqun
 * 
 */
//@Entity
//@Table(name = "t_inst_role_auth")
//@org.hibernate.annotations.Table(appliesTo = "t_inst_role_auth", indexes = {
//		@Index(name = "idx_inst_role_auth1", columnNames = { "i_inst" }),
//		@Index(name = "idx_inst_role_auth2", columnNames = { "i_inst_role" }),
//		@Index(name = "idx_inst_role_auth3", columnNames = { "i_inst_auth" }) })
public class InstRoleAuth implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8291193725486427294L;

	/**
	 * 机构角色权限内部编号
	 */
	@Id
	@Column(name = "i_inst_role_auth", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst_role_auth", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_role_auth")
	private Long iInstRoleAuth;
	/**
	 * 机构内部编号
	 */
	@Column(name = "i_inst", nullable = false, updatable = false)
	private Long iInst;
	/**
	 * 机构角色内部编号
	 */
	@Column(name = "i_inst_role", nullable = false, updatable = false)
	private Long iInstRole;
	/**
	 * 机构权限内部编号
	 */
	@Column(name = "i_inst_auth", nullable = false, updatable = false)
	private Long iInstAuth;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	public Long getiInstRoleAuth() {
		return iInstRoleAuth;
	}

	public void setiInstRoleAuth(Long iInstRoleAuth) {
		this.iInstRoleAuth = iInstRoleAuth;
	}

	public Long getiInst() {
		return iInst;
	}

	public void setiInst(Long iInst) {
		this.iInst = iInst;
	}

	public Long getiInstRole() {
		return iInstRole;
	}

	public void setiInstRole(Long iInstRole) {
		this.iInstRole = iInstRole;
	}

	public Long getiInstAuth() {
		return iInstAuth;
	}

	public void setiInstAuth(Long iInstAuth) {
		this.iInstAuth = iInstAuth;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

}
