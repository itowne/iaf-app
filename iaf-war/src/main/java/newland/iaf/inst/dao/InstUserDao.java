/**
 * 
 */
package newland.iaf.inst.dao;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstRoleAuth;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.InstUserRole;
import newland.iaf.inst.model.condition.InstRoleCondition;
import newland.iaf.inst.model.condition.InstUserCondition;

/**
 * 机构用户操作DAO接口
 * 
 * @author lindaqun
 * 
 */
public interface InstUserDao {
	/**
	 * 查询所有的机构用户表信息
	 * 
	 * @return
	 */
	List<InstUser> queryInstUser();

	/**
	 * 保存新增机构用户表信息
	 * 
	 * @param InstUser
	 *            机构用户表信息对象
	 */
	void saveInstUser(InstUser instuser);

	/**
	 * 保存机构用户表信息
	 * 
	 * @param InstUser
	 *            机构用户表信息对象
	 */
	void updateInstUser(InstUser instuser);

	/**
	 * 根据ID查询机构用户表信息
	 * 
	 * @param id
	 *            机构用户表信息ID
	 * @return
	 */
	InstUser getInstUser(Long iinstUser);

	InstUser getInstUserByLoginName(String loginName);

	InstUser getInstUser(String loginName, String passwd);

	// ------------------------------
	/**
	 * 保存新增机构用户角色信息
	 * 
	 * @param InstUserRole
	 *            机构用户角色信息对象
	 */
	void saveInstUserRole(InstUserRole instuserrole);

	/**
	 * 更新机构用户角色信息
	 * 
	 * @param InstUserRole
	 *            机构用户角色信息对象
	 */
	void updateInstUserRole(InstUserRole instuserrole);

	/**
	 * 根据用户编号查询角色
	 * 
	 * @param iInstUsr
	 * @return
	 */
	List<InstUserRole> listInstUserRoleByIInstUsr(Long iInstUsr);

	/**
	 * 保存新增机构角色表信息
	 * 
	 * @param InstRole
	 *            机构角色表信息对象
	 */
	void saveInstRole(InstRole instrole);

	/**
	 * 保存机构角色表信息
	 * 
	 * @param InstRole
	 *            机构角色表信息对象
	 */
	void updateInstRole(InstRole instrole);

	/**
	 * 根据ID查询机构角色表信息
	 * 
	 * @param id
	 *            机构角色表信息ID
	 * @return
	 */
	InstRole getInstRole(Long id);

	// ----------------------------
	/**
	 * 根据ID查询机构权限表信息
	 * 
	 * @param id
	 *            机构权限表信息ID
	 * @return
	 */
	InstAuth getInstAuth(Long iInstAuth);

	/**
	 * 根据用户内部编号获取所有的权限
	 * 
	 * @return
	 */
	List<InstAuth> findInstAuthByIInstUsr(Long iInstUsr);

	/**
	 * 取所有的权限
	 * 
	 * @return
	 */
	List<InstAuth> ListInstAuth();

	// ---------------------------
	/**
	 * 保存新增机构角色权限表信息
	 * 
	 * @param InstRoleAuth
	 *            机构角色权限表信息对象
	 */
	void saveInstRoleAuth(InstRoleAuth instroleauth);

	/**
	 * 保存机构角色权限表信息
	 * 
	 * @param InstRoleAuth
	 *            机构角色权限表信息对象
	 */
	void updateInstRoleAuth(InstRoleAuth instroleauth);

	/**
	 * 删除机构角色权限表信息
	 * 
	 * @param InstUser
	 *            机构角色权限表信息对象
	 */
	void deleteInstRoleAuth(InstRoleAuth instroleauth);

	/**
	 * 
	 * @param iInstRole
	 * @return
	 */
	List<InstRoleAuth> listInstRoleAuthByIInstRole(Long iInstRole);

	/**
	 * 
	 * @param iInstRole
	 * @return
	 */
	List<InstRoleAuth> listInstRoleAuthByIInstRole(List<Long> iInstRoleList);

	InstUser test(String loginName, String roleName);

	/**
	 * 根据机构用户编号查询角色
	 * 
	 * @return
	 */
	List<InstRole> queryByInstUser(Long iInst);

	/**
	 * 
	 * @param instUserCondition
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<InstUser> querytInstUserByCon(InstUserCondition instUserCondition,
			Page page) throws Exception;

	/**
	 * 删除机构用户
	 * 
	 * @param instUser
	 */
	void deleteInstUser(InstUser instUser);

	/**
	 * 检查注册用户是否重复
	 * 
	 * @param loginName
	 * @param iInst
	 * @return
	 */
	boolean checkRegInstUser(String loginName, Long iInst);

	/**
	 * 根据机构号,角色号查询角色
	 * 
	 * @param iInst
	 * @param roleName
	 * @return
	 */
	InstRole queryByiInstAndIrole(Long iInst, Long iRole);

	/**
	 * 查询多条件查询角色 角色名 角色状态
	 * 
	 * @return
	 */
	List<InstRole> queryInstRoleByCon(InstRoleCondition instRoleCondition,
			Page page) throws Exception;
	
	public boolean checkUpdateInstUser(String loginName, Long iInst) ;
}
