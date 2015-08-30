package newland.iaf.backstage.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
/**
 * 汇卡 角色 用户映射
 * @author Mr.Towne
 *
 */
public class BackStageUserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5871618972709172243L;
	
	/**
	 * 汇卡用户角色内部编号
	 */
	@Id
	@Column(name = "i_bs_user_role", nullable = false, updatable = false)
	@TableGenerator(name = "i_bs_user_role", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_bs_user_role")
	private Long iBsUserRole;
	/**
	 * 汇卡用户内部编号
	 */
	@Column(name = "i_bs_user", nullable = false, updatable = true)
	private Long iBsUser;
	/**
	 * 汇卡角色内部编号
	 */
	@Column(name = "i_bs_role", nullable = false, updatable = true)
	private Long iBsRole;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;
	public Long getiBsUserRole() {
		return iBsUserRole;
	}
	public void setiBsUserRole(Long iBsUserRole) {
		this.iBsUserRole = iBsUserRole;
	}
	public Long getiBsUser() {
		return iBsUser;
	}
	public void setiBsUser(Long iBsUser) {
		this.iBsUser = iBsUser;
	}
	public Long getiBsRole() {
		return iBsRole;
	}
	public void setiBsRole(Long iBsRole) {
		this.iBsRole = iBsRole;
	}
	public Date getGenTime() {
		return genTime;
	}
	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

}
