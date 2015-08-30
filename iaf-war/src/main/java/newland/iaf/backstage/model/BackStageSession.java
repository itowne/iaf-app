package newland.iaf.backstage.model;

import java.io.Serializable;
import java.util.List;

public class BackStageSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 汇卡用户编号
	 */
	public static final Long HICARD_INST = new Long(0);
	/**
	 * 汇卡机构编码
	 */
	public static final String HICARD_INST_ID = "00000000";
	/**
	 * 后台管理用户
	 */
	BackStageUser user;
	/**
	 * 后台管理角色列表
	 */
	List<BackStageRole> roles;
	
	public BackStageUser getUser() {
		return user;
	}
	public List<BackStageRole> getRoles() {
		return roles;
	}
	public void setUser(BackStageUser user) {
		this.user = user;
	}
	public void setRoles(List<BackStageRole> roles) {
		this.roles = roles;
	}

}
