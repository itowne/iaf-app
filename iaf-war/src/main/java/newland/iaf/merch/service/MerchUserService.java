package newland.iaf.merch.service;

import java.util.List;

import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.MerchUserType;
import newland.iaf.merch.model.condition.MerchUserCondition;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 
 *
 */
public interface MerchUserService {

	/**
	 * 注册商户用户信息
	 * 
	 * @param merchUser
	 *            商户用户信息对象
	 */
	void regMerchUser(MerchUser merchUser);

	MerchUser getMerchUser(String merchNo, String loginName);

	MerchUser getMerchUser(String merchNo, String loginName, MerchUserType type);
	
	List<MerchUser> getMerchUserByLoginName(String loginName);

	MerchSession newMerchSession(Long imerchUser, String ipaddr);

	/**
	 * 修改商户用户的密码
	 * 
	 * @param merchUser
	 */
	void updateMerchUserPwd(MerchUser merchUser, String newPwd,
			MerchSession session, String ipaddr);

	/**
	 * 根据商户ID查找商户用户
	 * 
	 * @param con
	 *            查询条件
	 * @param page
	 *            分页
	 * @return
	 * @throws Exception
	 */
	List<MerchUser> listMerchUser(MerchUserCondition con, Page page)
			throws Exception;

	/**
	 * 更新商户用户
	 * 
	 * @param merchUser
	 * @throws Exception
	 */
	void updateMerchUser(MerchUser merchUser, MerchSession session,
			String ipaddr) throws Exception;

	/**
	 * 退出登录
	 * 
	 * @param merch
	 * @param merchUser
	 * @param ipaddr
	 *            TODO
	 */
	void logout(Merch merch, MerchUser merchUser, String ipaddr);
	
	/**
	 * 后台新增商户用户
	 * @param merchUser
	 * @param session
	 * @param ipaddr
	 * @throws Exception
	 */
	public void addMerchUserIAF(MerchUser merchUser, IafConsoleSession session,
			String ipaddr) throws Exception ;
	
	/**
	 * 后台修改商户用户
	 * @param merchUser
	 * @param session
	 * @param ipaddr
	 * @throws Exception
	 */
	public void updateMerchUserIAF(MerchUser merchUser, IafConsoleSession session,
			String ipaddr) throws Exception ;
	 
}
