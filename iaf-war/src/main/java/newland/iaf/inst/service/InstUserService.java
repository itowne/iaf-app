/**
 * 
 */
package newland.iaf.inst.service;

import java.util.List;

import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.InstUserRole;
import newland.iaf.inst.model.condition.InstUserCondition;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 机构用户登录Service操作服务
 * 
 * @author lindaqun
 * 
 */
public interface InstUserService {

	/**
	 * 商户用户登录
	 */
	InstUser loginInstUser(String loginName, String passwd);

	/**
	 * 
	 * @param iinstUser
	 * @return
	 */
	InstSession newInstSession(Long iinstUser, String address);

	/**
	 * 修改机构用户的登陆密码
	 * 
	 * @param instuser
	 *            机构用户信息
	 * @throws Exception
	 *             异常信息
	 */
	void changeUserPassword(Long iinstuser, String oldPassword,
			String newPassword, InstSession session, String ipaddr)
			throws Exception;

	/**
	 * 修改机构用户的其他信息
	 * 
	 * @param instuser
	 *            机构用户信息
	 * @throws Exception
	 *             异常信息
	 */
	void changeInstUserInfo(InstUser instuser, InstSession session,
			String ipaddr) throws Exception;

	/**
	 * 多条件查询机构帐号管理 帐号 使用者 所属角色 状态
	 * 
	 * @param instUserCondition
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<InstUser> queryInstUserByCon(InstUserCondition instUserCondition,
			Page page) throws Exception;

	/**
	 * 根据机构用户号查询角色
	 * 
	 * @param iInstUser
	 * @return
	 */
	List<InstRole> queryByInstUser(Long iInst);

	/**
	 * 新增机构用户
	 * 
	 * @param instUser
	 */
	void addInstUser(InstUser instUser, InstSession session, String ipaddr);

	/**
	 * 更新机构用户
	 * 
	 * @param instUser
	 */
	void updateInstUser(InstUser instUser, InstSession session, String ipaddr);

	/**
	 * 新增机构用户
	 * 
	 * @param instUser
	 */
	void addInstUserIAF(InstUser instUser, Long iinst,
			IafConsoleSession session, String ipaddr);

	/**
	 * 更新机构用户
	 * 
	 * @param instUser
	 */
	void updateInstUserIAF(InstUser instUser, Long iinst,
			IafConsoleSession session, String ipaddr);

	/**
	 * 根据机构用户编号查询机构用户
	 * 
	 * @param iInstUser
	 * @return
	 */
	InstUser queryByInstUserId(Long iInstUser);

	/**
	 * 更新机构用户和角色映射
	 * 
	 * @param iur
	 */
	void updateInstUserRole(InstUserRole iur, InstSession session, String ipaddr);

	/**
	 * 启用机构用户
	 */
	void reCoverInstUsr(InstUser instUser);

	/**
	 * 删除机构用户
	 */
	void deleteInstUser(InstUser instUser, InstSession session, String ipaddr);

	/**
	 * 删除机构用户
	 */
	void deleteInstUserIAF(InstUser instUser, Long iinst,
			IafConsoleSession session, String ipaddr);

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
	 * 退出登录
	 * 
	 * @param inst
	 * @param instUsr
	 * @param ipaddr
	 *            TODO
	 */
	void logout(InstSession instSession, String ipaddr);

	InstUser getInstUserByLoginName(String loginName);
	public boolean checkUpdateInstUser(String loginName, Long iInst) ;
}
