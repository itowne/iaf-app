/**
 * 
 */
package newland.iaf.inst.dao;

import java.util.List;

import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.RoleAuth;
import newland.iaf.inst.model.condition.InstRoleCondition;

import org.ohuyo.commons.query.criterion.Page;

/**
 * @author Mr.Towne
 *
 */
public interface RoleAuthDao {
	
	/**
	 * 角色名字是否重复
	 * @param roleName
	 * @return
	 */
	boolean checkRoleName(String roleName,Long iInst);
	
	/**
	 * 查询多条件查询角色
	 * 角色名 角色状态
	 * @return
	 */
	List<RoleAuth> queryInstRoleByCon(InstRoleCondition instRoleCondition,Page page) throws Exception;
	/**
	 *  新增角色
	 * @param roleAuth
	 */
	void saveRoleAuth(RoleAuth roleAuth);
	
	/**
	 * 删除角色
	 * @param roleAuth
	 */
	void deleteRoleAuth(RoleAuth roleAuth);
	/**
	 * 更新角色
	 * @param roleAuth
	 */
	void updateRoleAuth(RoleAuth roleAuth);
	/**
	 * 根据名字查权限
	 * @param authName
	 * @return
	 */
	InstAuth queryByAuthName(String authName);	
	/**
	 * 更新检查
	 * @param roleName
	 * @return
	 */
	boolean CheckUpdateRoleName(String roleName,Long iInst);
	/**
	 * 根据机构号查询角色
	 * @param iInst
	 * @return
	 */
	List<InstRole> queryByIinst(Long iInst);
	/**
	 * 暂停角色
	 * @param instRole
	 */
	void cancelInstRole(RoleAuth roleAuth);
}
