package newland.iaf.base.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.LoanOrdPlanDao;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.condition.LoanOrdPlanCondition;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
@Service("loanOrdPlanDao")
public class LoanOrdPlanDaoImpl extends BaseDao implements LoanOrdPlanDao {

	@Override
	public void save(LoanOrdPlan plan) {
		this.getSession().save(plan);
	}

	@Override
	public void update(LoanOrdPlan plan) {
		this.getSession().update(plan);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanOrdPlan> findAll() {
		Query query = this.getSession().createQuery(
				"from " + LoanOrdPlan.class.getName());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanOrdPlan> query(LoanOrdPlan plan) {
		return this.getHibernateDao().findByExample(plan);
	}

	@Override
	public void delete(LoanOrdPlan plan) {
		this.getSession().delete(plan);

	}

	@Override
	public <T> T queryBy(CriteriaExecutor<T> executor) {
		Criteria criteria = this.getSession().createCriteria(LoanOrdPlan.class);
		return executor.execute(criteria);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> queryPeriodRefund(CriteriaExecutor<T> executor) {
		Criteria criteria = this.getSession().createCriteria(LoanOrdPlan.class);
		criteria.add(Restrictions.or(
				Restrictions.eq("stat", PlanStat.DELIN_QUENCY),
				Restrictions.and(Restrictions.eq("stat", PlanStat.BALANCE)
				// Restrictions.between("refundDate", beginDate, endDate)
						)));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public LoanOrdPlan queryById(Long iplanId) {
		List<LoanOrdPlan> list = this.getHibernateDao().find(
				"from " + LoanOrdPlan.class.getName()
						+ " lp where lp.iloanOrdPlan = ?", iplanId);
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanOrdPlan> queryBy(LoanOrdPlanCondition cond) {
		return this.getHibernateDao().findByCriteriaEx(cond);
	}

	@Override
	public void cleanInvalidPlan() {
		this.getHibernateDao()
				.executeUpdateBySql(
						"delete from t_loanord_plan  where i_loan_ord not in(select i_loan_ord from t_loan_ord )");
	}

	@Override
	public List<LoanOrdPlan> queryByLoanOrdId(String loanOrdId) {
		LoanOrdPlan pop = new LoanOrdPlan();
		pop.setLoanOrdId(loanOrdId);
		return this.getHibernateDao().findByExample(pop);
	}

	@Override
	public List<LoanOrdPlan> queryByIinst(Long id) {
		LoanOrdPlan pop = new LoanOrdPlan();
		pop.setIinst(id);
		return this.getHibernateDao().findByExample(pop);
	}

	@Override
	public List<LoanOrdPlan> queryByImerch(Long id) {
		LoanOrdPlan pop = new LoanOrdPlan();
		pop.setImerch(id);
		return this.getHibernateDao().findByExample(pop);
	}

	@Override
	public List<LoanOrdPlan> queryByCond(LoanOrdPlanCondition cond, Page page) {
		List<LoanOrdPlan> loanOrdPlanList =  this.getHibernateDao().findByCriteriaEx(cond, page, true);
		return loanOrdPlanList;
	}

}
