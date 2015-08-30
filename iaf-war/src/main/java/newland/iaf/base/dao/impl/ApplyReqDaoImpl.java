package newland.iaf.base.dao.impl;

import java.util.List;

import newland.iaf.base.dao.ApplyReqDao;
import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.condition.ApplyRequestCondition;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
@Service("applyReqDao")
public class ApplyReqDaoImpl extends BaseDao  implements ApplyReqDao {

	@Override
	public void save(ApplyRequest req) {
		this.getSession().save(req);

	}

	@Override
	public void update(ApplyRequest req) {
		this.getSession().update(req);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplyRequest> queryBy(ApplyRequest req) {
		return this.getHibernateDao().findByExample(req);
	}

	@Override
	public List<ApplyRequest> queryBy(ApplyRequestCondition cond) {
		return this.getHibernateDao().findByCriteriaEx(cond);
	}
	
	/**
	 * 复合查询 分页
	 * @param cond
	 * @return
	 */
	public List<ApplyRequest> queryByPage(ApplyRequestCondition cond,Page page){
		List<ApplyRequest> applyList = this.getHibernateDao().findByCriteriaEx(cond, page, true);
		return applyList;
	}

	@Override
	public ApplyRequest queryByIloanOrd(Long iloanord,String periods) {
		ApplyRequest ar = new ApplyRequest();
		ar.setIloanOrd(iloanord);
		ar.setPeriods(periods);
		return this.getHibernateDao().getFirstOneByExample(ar);
	}
}
