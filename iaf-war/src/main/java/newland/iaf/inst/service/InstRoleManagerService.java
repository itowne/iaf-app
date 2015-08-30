/**
 * 
 */
package newland.iaf.inst.service;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUserRole;
import newland.iaf.inst.model.RoleAuth;
import newland.iaf.inst.model.condition.InstRoleCondition;

/**
 * 机构角色管理操作Service接口
 * 
 * @author lindaqun
 * 
 */
public interface InstRoleManagerService {

	/**
	 * 分页查询当前机构的角色，条件信息后续完善
	 * 
	 * @return
	 * @throws Exception
	 *             一些可能的异常信息
	 */
	List queryInstRoleByCon() throws Exception;

	/**
	 * 增加当前机构的角色信息，如果发现错误抛出对应错误信息
	 * 
	 * @param InstRole
	 *            角色信息
	 * @return Exception 一些可能的异常信息
	 */
	void addInstRole(InstRole instrole, InstSession session, String ipaddr)
			throws Exception;

	/**
	 * 根据角色ID查找角色信息
	 * 
	 * @param id
	 *            角色ID
	 * @return InstRole 角色信息
	 * @throws Exception
	 *             异常信息
	 */
	InstRole findInstRole(Long id) throws Exception;

	/**
	 * 修改角色信息
	 * 
	 * @param instrole
	 *            角色信息
	 * @throws Exception
	 *             一些可能的异常信息
	 */
	void updateInstRole(InstRole instrole, InstSession session, String ipaddr) throws Exception;

	/**
	 * 删除角色信息
	 * 
	 * @param instrole
	 *            角色信息
	 * @throws Exception
	 *             一些可能的异常信息
	 */
	void deleteInstRole(InstRole instrole, InstSession session, String ipaddr) throws Exception;

	/**
	 * 根据机构号查询角色
	 */
	List<InstRole> queryByInst(Long iInst);

	/**
	 * 查询多条件查询角色 角色名 角色状态
	 * 
	 * @return
	 */
	List<RoleAuth> queryInstRoleByCon(InstRoleCondition instRoleCondition,
			Page page) throws Exception;

	/**
	 * 角色名字是否重复
	 * 
	 * @param roleName
	 * @return
	 */
	boolean checkRoleName(String roleName, Long iInst);

	/**
	 * 新增角色
	 * 
	 * @param roleAuth
	 */
	void saveRoleAuth(RoleAuth roleAuth, InstSession session, String ipaddr);

	/**
	 * 删除角色
	 * 
	 * @param roleAuth
	 */
	void deleteRoleAuth(RoleAuth roleAuth, InstSession session, String ipaddr);

	/**
	 * 更新角色
	 * 
	 * @param roleAuth
	 */
	void updateRoleAuthIAF(RoleAuth roleAuth,Long iinst,IafConsoleSession session, String ipaddr);
	
	/**
	 * 新增角色
	 * 
	 * @param roleAuth
	 */
	void saveRoleAuthIAF(RoleAuth roleAuth,Long iinst, IafConsoleSession session, String ipaddr);

	/**
	 * 删除角色
	 * 
	 * @param roleAuth
	 */
	void deleteRoleAuthIAF(RoleAuth roleAuth,Long iinst,IafConsoleSession session, String ipaddr);

	/**
	 * 更新角色
	 * 
	 * @param roleAuth
	 */
	void updateRoleAuth(RoleAuth roleAuth, InstSession session, String ipaddr);

	/**
	 * 根据权限名查询权限
	 * 
	 * @param authName
	 * @return
	 */
	InstAuth queryByAuthName(String authName);

	/**
	 * 更新检查
	 * 
	 * @param roleName
	 * @return
	 */
	boolean CheckUpdateRoleName(String roleName, Long iInst);

	/**
	 * 根据机构号查询角色
	 * 
	 * @param iInst
	 * @return
	 */
	List<InstRole> queryByIinst(Long iInst);

	/**
	 * 暂停角色
	 * 
	 * @param instRole
	 */
	void cancelInstRole(RoleAuth roleAuth);
}
