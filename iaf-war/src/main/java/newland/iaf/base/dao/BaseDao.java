package newland.iaf.base.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.ohuyo.commons.query.HibernateDaoSupport;

public abstract class BaseDao extends HibernateDaoSupport {
	/**
	 * add for 声明式配置
	 */
	@Resource(name = "com.newland.iaf.sessionFactory")
	public void setInjectSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
