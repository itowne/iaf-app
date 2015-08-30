package newland.iaf.other.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.other.dao.InitDataDao;

import org.springframework.stereotype.Repository;

@Repository("newland.iaf.initDataDao")
public class InitDataDaoImpl extends BaseDao implements InitDataDao {

	public void execSql(String sql) {
		this.getHibernateDao().executeUpdateBySql(sql, (Object[]) null);
	}

	public void execSqls(List<String> sqls) {
		for (String sql : sqls) {
			execSql(sql);
		}
	}

}
