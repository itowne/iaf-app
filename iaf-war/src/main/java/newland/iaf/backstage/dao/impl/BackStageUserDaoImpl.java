package newland.iaf.backstage.dao.impl;

import java.util.List;

import newland.iaf.backstage.dao.BackStageUserDao;
import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.model.condition.BackStageUserCondition;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;

@Service ("backStageUserDao")
public class BackStageUserDaoImpl extends BaseDao implements BackStageUserDao {

	@Override
	public void save(BackStageUser user) {
		this.getSession().save(user);

	}

	@Override
	public void update(BackStageUser user) {
		this.getSession().update(user);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BackStageUser> query(BackStageUser user) {
		return this.getHibernateDao().findByExample(user);
	}
	
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
	public List<BackStageUser> listUser(BackStageUserCondition con, Page page) throws Exception {
		return this.getHibernateDao().findByCriteriaEx(con, page, true);
	}
	
	@Override
	public List<BackStageUser> listUser(BackStageUserCondition con){
		return this.getHibernateDao().findByCriteriaEx(con);
	}

}
