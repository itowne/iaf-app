package newland.iaf.backstage.service;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.backstage.model.BackStageAuth;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BackStageRoleAuth;
import newland.iaf.backstage.model.BsRoleAuth;
import newland.iaf.base.model.condition.BackStageRoleCondition;
import newland.iaf.base.service.IafSession;

public interface BackStageRoleService {
	
	void save(BackStageRole role) throws Exception;
	
	void update(BackStageRole role) throws Exception;
	
	BackStageRole queryByName(String roleName) throws Exception;
	
	BackStageRole queryById(Long irole) throws Exception;
	
	/**
	 * 分页查询角色信息
	 * @param con
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<BsRoleAuth> listRole(BackStageRoleCondition con,Page page) throws Exception;
	/**
	 * 查询所有角色 
	 */
	List<BackStageRole> queryAllRoles();
	/**
	 * 新增角色权限
	 * @param bsRoleAuth
	 */
	void saveRoleAuth(BsRoleAuth bsRoleAuth);
	/**
	 * 删除角色权限
	 * @param bsRoleAuth
	 */
	void deleteRoleAuth(BsRoleAuth bsRoleAuth);
	/**
	 * 更新角色权限
	 * @param bsRoleAuth
	 */
	void updateRoleAuth(BsRoleAuth bsRoleAuth);
	/**
	 * 角色名字是否重复
	 * @param roleName
	 * @return
	 */
	boolean checkRoleName(String roleName);
	/**
	 * 更新检查
	 * @param roleName
	 * @return
	 */
	boolean CheckUpdateRoleName(String roleName);
	/**
	 * 根据authCode查询权限
	 * @param authCode
	 * @return
	 */
	BackStageAuth queryByAuthCode(String authCode);
}
