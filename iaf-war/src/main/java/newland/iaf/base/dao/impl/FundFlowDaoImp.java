package newland.iaf.base.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.FundFlowDao;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
@Service("fundFlowDao")
public class FundFlowDaoImp extends BaseDao implements FundFlowDao {

	@Override
	public void save(FundFlow fundFlow) {
		this.getSession().save(fundFlow);

	}

	@Override
	public void update(FundFlow fundFlow) {
		this.getSession().update(fundFlow);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FundFlow> queryFundFlowBy(FundFlow fundFlow) {
		return (List<FundFlow>) this.getHibernateDao().findByExample(fundFlow);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FundFlow> queryByCondition(FundFlowCondition cond) {
		List<FundFlow> list =  this.getHibernateDao().findByCriteriaEx(cond);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FundFlow> queryByCondition(FundFlowCondition cond, Page page) {
		return this.getHibernateDao().findByCriteriaEx(cond, page, true);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean haveFundFlow(Long iloanOrd, FundFlowType type) {
		Criteria cri = this.getSession().createCriteria(FundFlow.class);
		cri.setProjection(Projections.rowCount())
			.add(Restrictions.eq("iloanOrd", iloanOrd))
			.add(Restrictions.eq("status", FundFlowStat.AUDIT))
			.add(Restrictions.eq("type", type));
		List list = cri.list();
		if (CollectionUtils.isEmpty(list)) return false;
		Object obj = (Object)list.get(0);
		if (obj == null) return false;
		Long count = (Long)obj;
		if (count.intValue() <= 0) return false;
		return true;
	}

	@Override
	public FundFlow queryByOthSysNo(String othSysNo) {
		FundFlow ff = new FundFlow();
		ff.setOtherSysTraceNo(othSysNo);
		return this.getHibernateDao().getFirstOneByExample(ff);
	}

	@Override
	public List<FundFlow> listQueryBySysNo(String othSysNo) {
		FundFlow ff = new FundFlow();
		ff.setOtherSysTraceNo(othSysNo);
		return this.getHibernateDao().findByExample(ff);
	}

	@Override
	public List<FundFlow> queryByIloanOrdAndType(Long iloanOrd,
			FundFlowType type) {
		// TODO Auto-generated method stub
		FundFlow ff = new FundFlow();
		ff.setIloanOrd(iloanOrd);
		ff.setType(type);
		return this.getHibernateDao().findByExample(ff);
	}

	@Override
	public FundFlow queryByIloanOrdPlan(Long iLoanOrdPlan) {
		// TODO Auto-generated method stub
		FundFlow ff = new FundFlow();
		ff.setIloanOrdPlan(iLoanOrdPlan);
		return this.getHibernateDao().getFirstOneByExample(ff);
	}
	
	public <T> T queryBy(CriteriaExecutor<T> executor, Class<?> clazz) {
		Criteria expr = this.getSession().createCriteria(clazz);
		return executor.execute(expr);
	}

	@Override
	public List<Object> queryRefund(Long imerch, FundFlowStat stat,
			FundFlowType type) {
		return this.getHibernateDao().find("select sum(amt),count(*) from HcTransLog htl where ", "");
	}

}
