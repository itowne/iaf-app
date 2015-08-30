package newland.iaf.merch.dao.impl;

import java.util.ArrayList;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.merch.dao.MerchUserDao;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.MerchUserType;
import newland.iaf.merch.model.condition.MerchUserCondition;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;

@Repository("com.newland.iaf.merchUserDao")
public class MerchUserDaoImpl extends BaseDao implements MerchUserDao {

	@Override
	public void saveMerchUser(MerchUser merchUser) {
		this.getSession().save(merchUser);
	}

	@Override
	public List<MerchUser> queryMerchUser() {
		return this.getHibernateDao().find(MerchUser.class);
	}

	@Override
	public MerchUser getMerchUser(Long imerchUser) {
		return (MerchUser) this.getSession().get(MerchUser.class, imerchUser);
	}

	@Override
	public void updateMerchUser(MerchUser merchUser) {
		this.getSession().update(merchUser);
	}

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
	@SuppressWarnings("unchecked")
	public List<MerchUser> listMerchUser(MerchUserCondition con, Page page)
			throws Exception {
		return this.getHibernateDao().findByCriteriaEx(con, page, true);
	}

	@Override
	public MerchUser getMerchUser(String merchNo, String loginName) {
		MerchUser usr = new MerchUser();
		usr.setMerchNo(merchNo);
		usr.setLoginName(loginName);
		return this.getHibernateDao().getFirstOneByExample(usr);
	}

	@Override
	public MerchUser getMerchUser(String merchNo, String loginName,
			MerchUserType type) {
		MerchUser usr = new MerchUser();
		usr.setMerchNo(merchNo);
		usr.setLoginName(loginName);
		usr.setUserType(type);
		return this.getHibernateDao().getFirstOneByExample(usr);
	}
	
	@Override
	public MerchUser getMerchUserByMerchNo(String merchNo){
		MerchUser usr = new MerchUser();
		usr.setMerchNo(merchNo);
		return this.getHibernateDao().getFirstOneByExample(usr);
	}
	
	@Override
	public List<MerchUser> getMerchUserByLoginName(String loginName) {
		MerchUser usr = new MerchUser();
		usr.setLoginName(loginName);
		List<MerchUser> merchUserList = this.getHibernateDao().findByExample(usr);
		return merchUserList;
	}
}
