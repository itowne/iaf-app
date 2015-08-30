package newland.iaf.base.dao.impl;

import java.util.Date;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.LoanOrdDao;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.PdtType;

import org.hibernate.Criteria;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("loanOrdDao")
public class LoanOrdDaoImpl extends BaseDao implements LoanOrdDao {

	@Override
	public void saveLoanOrd(LoanOrd loanOrd) {
		this.getSession().save(loanOrd);
	}

	@Override
	public void updateLoanOrd(LoanOrd loanOrd) {
		loanOrd.setUpdTime(new Date());
		this.getSession().update(loanOrd);
	}

	@Override
	public void deleteLoanOrd(LoanOrd loanOrd) {
		this.getSession().delete(loanOrd);
	}

	@Override
	public LoanOrd findLoanOrdById(Long id) {
		return (LoanOrd) this.getSession().get(LoanOrd.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanOrd> queryLoanOrd() {
		return this.getHibernateDao().find(LoanOrd.class);
	}

	@Override
	public List<LoanOrd> query(CriteriaExecutor<List<LoanOrd>> executor) {
		Criteria expr = this.getSession().createCriteria(LoanOrd.class);
		return executor.execute(expr);
		
	}

	@Override
	public List<LoanOrd> queryOrdByCon(LoanOrdCondition loanordcondition,
			Page page) throws Exception {
		List<LoanOrd> list = this.getHibernateDao().findByCriteriaEx(loanordcondition, page, true);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanOrd> queryByCondition(LoanOrdCondition cond) {
		List<LoanOrd> list =  this.getHibernateDao().findByCriteriaEx(cond);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void expire(LoanOrd loanOrd) {
		this.updateLoanOrd(loanOrd);
		
	}

	@Override
	public LoanOrd queryByDebitbid(Long iDebitbid) {
		LoanOrd loanOrd = new LoanOrd();
		loanOrd.setIloanPdt(iDebitbid);
		loanOrd.setPdtType(PdtType.MERCH_PROD);
		return this.getHibernateDao().getFirstOneByExample(loanOrd);
	}
	
	@SuppressWarnings("unchecked")
	public List<LoanOrd> queryByDebit(Long iDebitbid){
		LoanOrd loanOrd = new LoanOrd();
		loanOrd.setIloanPdt(iDebitbid);
		loanOrd.setPdtType(PdtType.MERCH_PROD);
		return this.getHibernateDao().findByExample(loanOrd);
	}

	@Override
	public <T> T queryBy(CriteriaExecutor<T> executor) {
		Criteria criteria = this.getSession().createCriteria(LoanOrd.class);
		return executor.execute(criteria);
	}

	@Override
	public List<LoanOrd> queryByIinst(Long iInst) {
		LoanOrd lo = new LoanOrd();
		lo.setIinst(iInst);
		return this.getHibernateDao().findByExample(lo);
	}

	@Override
	public List<LoanOrd> queryByImerch(Long iMerch) {
		LoanOrd lo = new LoanOrd();
		lo.setImerch(iMerch);
		return this.getHibernateDao().findByExample(lo);
	}

	@Override
	public LoanOrd queryByLoanOrdId(String loanOrdId) {
		LoanOrd loanOrd = new LoanOrd();
		loanOrd.setLoanOrdId(loanOrdId);
		return this.getHibernateDao().getFirstOneByExample(loanOrd);
	}

	@Override
	public <T> T queryBy(CriteriaExecutor<T> executor, Class<?> clazz) {
		Criteria expr = this.getSession().createCriteria(clazz);
		return executor.execute(expr);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryTenDays(Date d, Date tenD, Long iinst) {
		//还款计划表中plan_stat=5表示已还款
		return this.getHibernateDao().find("select sum(repayment),count(*) from LoanOrdPlan where refund_date BETWEEN ? AND ? and i_inst=? and plan_stat !=?", d,tenD,iinst,5);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanOrdPlan> queryIloanOrd(Date d, Date tenD, Long iinst) {
		return this.getHibernateDao().find(" from LoanOrdPlan where refund_date BETWEEN ? AND ? and i_inst=? and plan_stat !=? ", d,tenD,iinst,5);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanOrd> queryfreezeLoanOrd(Long iinst) {
		return this.getHibernateDao().find(" from LoanOrd where i_inst=? and i_merch in( from LoanOrdPlan where plan_stat=? )", iinst,3);
	}

}
