package newland.iaf.merch.dao;

import java.util.List;

import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.MerchUserType;
import newland.iaf.merch.model.condition.MerchUserCondition;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 
 * @author rabbit
 * 
 */
public interface MerchUserDao {
	/**
	 * 新增商户用户
	 * 
	 * @param merchUser
	 */
	void saveMerchUser(MerchUser merchUser);

	/**
	 * 查询全部商户用户
	 * 
	 * @param merchUser
	 */
	List<MerchUser> queryMerchUser();

	/**
	 * 
	 * @param merchUser
	 */
	void updateMerchUser(MerchUser merchUser);

	/**
	 * 根据id查询商户用户
	 * 
	 * @param id
	 */
	MerchUser getMerchUser(Long imerchUser);

	/**
	 * 
	 * @param loginName
	 * @return
	 */
	MerchUser getMerchUser(String merchNo, String loginName);

	/**
	 * 
	 * @param loginName
	 * @param type
	 * @return
	 */
	MerchUser getMerchUser(String merchNo, String loginName, MerchUserType type);
	
	/**
	 * 根据merchNo查找merchUser
	 */
	MerchUser getMerchUserByMerchNo(String merchNo);
	
	/**
	 * 根据LoginName查找同登录名账户的个数
	 */
	List<MerchUser> getMerchUserByLoginName(String LoginName);

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

}
