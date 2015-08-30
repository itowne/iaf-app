package newland.iaf.base.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.TransReportDao;
import newland.iaf.base.model.TransReport;
import newland.iaf.base.model.condition.TransReportCondition;

public class TransReportDaoImpl extends BaseDao implements TransReportDao {

	@Override
	public void save(TransReport report) {
		this.getSession().save(report);

	}

	@Override
	public void update(TransReport report) {
		this.getSession().update(report);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransReport> query(TransReportCondition cond) {
		return this.getHibernateDao().findByCriteriaEx(cond);
	}

}
