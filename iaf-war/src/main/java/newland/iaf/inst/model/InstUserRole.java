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
 * 机构用户角色表
 * 
 * @author lindaqun
 * 
 */
//@Entity
//@Table(name = "t_inst_user_role")
//@org.hibernate.annotations.Table(appliesTo = "t_inst_user_role", indexes = {
//		@Index(name = "idx_inst_user_role1", columnNames = { "i_inst_user" }),
//		@Index(name = "idx_inst_user_role2", columnNames = { "i_inst_role" }) })
public class InstUserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7350949947559025630L;

	/**
	 * 机构用户角色内部编号
	 */
	@Id
	@Column(name = "i_inst_usr_role", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst_usr_role", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_usr_role")
	private Long iInstUsrRole;
	/**
	 * 机构用户内部编号
	 */
	@Column(name = "i_inst_user", nullable = false, updatable = true)
	private Long iInstUser;
	/**
	 * 机构角色内部编号
	 */
	@Column(name = "i_inst_role", nullable = false, updatable = true)
	private Long iInstRole;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	public Long getiInstUsrRole() {
		return iInstUsrRole;
	}

	public void setiInstUsrRole(Long iInstUsrRole) {
		this.iInstUsrRole = iInstUsrRole;
	}

	public Long getiInstUser() {
		return iInstUser;
	}

	public void setiInstUser(Long iInstUser) {
		this.iInstUser = iInstUser;
	}

	public Long getiInstRole() {
		return iInstRole;
	}

	public void setiInstRole(Long iInstRole) {
		this.iInstRole = iInstRole;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

}
