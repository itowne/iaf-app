package newland.iaf.backstage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.base.model.condition.BackStageUserCondition;
import newland.iaf.base.service.impl.IafConsoleSession;

public interface BackStageUserService {
	
	/**
	 * 登录
	 * @param loginName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	BackStageUser login(String loginName, String password) throws Exception;
	
	/**
	 * 根据登录名查询 
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	BackStageUser queryBy(String loginName) throws Exception;
	/**
	 * 新建用户
	 * @param user
	 * @throws Exception
	 */
	void save(BackStageUser user) throws Exception;
	/**
	 * 修改密码
	 * @param user
	 * @param oldPwd
	 * @param newPwd
	 * @return TODO
	 * @throws Exception
	 */
	BackStageUser changePwd(BackStageUser user, String oldPwd, String newPwd) throws Exception;
	/**
	 * 新建后台管理Session对象
	 * @param user
	 * @return
	 * @throws Exception
	 */
	IafConsoleSession newIafConsoleSession(BackStageUser user, HttpServletRequest req,String ipAddr) throws Exception;

	/**
	 * 退出登录
	 * @param user
	 * @param ipaddr TODO
	 */
	void logout(BackStageUser user, String ipaddr);
	
	/**
	 * 保存后台管理用户
	 * @param backStageUser
	 * @throws Exception
	 */
	void saveBackStageUser(BackStageUser backStageUser) throws Exception;
	
	/**
	 * 更新后台管理用户
	 * @param backStageUser
	 * @throws Exception
	 */
	void updateBackStageUser(BackStageUser backStageUser) throws Exception;
	
	/**
	 * 分页查询
	 * 
	 * @param con
	 *            查询条件
	 * @param page
	 *            分页
	 * @return
	 * @throws Exception
	 */
	public List<BackStageUser> listUser(BackStageUserCondition con, Page page) throws Exception;


}
