package newland.iaf.base.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.OperLogDao;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.OrdOperLog;
import newland.iaf.base.model.condition.OperLogCondition;
import newland.iaf.base.model.condition.OrdOperLogCondition;

import org.apache.commons.collections.CollectionUtils;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
@Service("operLogDao")
public class OperLogDaoImpl extends BaseDao implements OperLogDao{

	@Override
	public void save(OperLog log) {
		this.getSession().save(log);
		
	}

	@Override
	public OperLog queryById(String traceNo) {
		return (OperLog)this.getSession().load(OperLog.class, traceNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperLog> queryByCondition(OrdOperLogCondition cond,Page page){
		List<OperLog> list =  this.getHibernateDao().findByCriteriaEx(cond, page, true);
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OperLog> queryByCondition(OrdOperLogCondition cond){
		List<OperLog> list =  this.getHibernateDao().findByCriteriaEx(cond);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperLog> queryByCondition(OperLogCondition cond, Page page) {
		List<OperLog> list =  this.getHibernateDao().findByCriteriaEx(cond, page, true);
		return list;
	}

	@Override
	public void deleteBy(String traceNo) {
		//String sql = "delete " + OrdOperLog.class.getName() + " oo where oo.otherTraceNo = ?";
		//this.getHibernateDao().executeUpdate(sql, traceNo);
		OrdOperLog ool = new OrdOperLog();
		ool.setTraceNo(traceNo);
		this.getSession().delete(ool);
	}

}
