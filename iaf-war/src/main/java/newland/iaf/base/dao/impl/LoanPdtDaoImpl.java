package newland.iaf.base.dao.impl;

import java.math.BigInteger;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.LoanPdtDao;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.model.dict.YesOrNoType;

import org.hibernate.Criteria;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author rabbit
 * 
 */
@Repository("com.newland.iaf.loanPdtDao")
public class LoanPdtDaoImpl extends BaseDao implements LoanPdtDao {

	@Override
	public void saveLoanPdt(LoanPdt loanPdt) {
		getSession().save(loanPdt);
	}

	@Override
	public void updateLoanPdt(LoanPdt loanPdt) {
		getSession().update(loanPdt);
	}

	@Override
	public LoanPdt getLoanPdtById(Long id) {
		return (LoanPdt) this.getSession().get(LoanPdt.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanPdt> queryLoanPdtByCon(LoanPdtCondition loanPdtCondition, Page page) throws Exception {
		List<LoanPdt> loanPdtList = this.getHibernateDao().findByCriteriaEx(loanPdtCondition, page, true);
		return loanPdtList;
	}

	@Override
	public BigInteger countLoanPdtByIinst(Long iInst) {
		return this.getHibernateDao().count("from LoanPdt where iinst=?", iInst);
	}
	
	/**
	 * 删除借款产品信息(逻辑删除.置isDelete为true)
	 * @param loanPdt 借款产品信息
	 * @throws Exception
	 */
	public void deleteLoanPdt (LoanPdt loanPdt) throws Exception {
		loanPdt = (LoanPdt)this.getSession().get(LoanPdt.class, loanPdt.getIloanPdt());
		loanPdt.setDeleteFlag(YesOrNoType.YES);
		this.getSession().update(loanPdt);
	}

	@Override
	public LoanPdt queryBy(CriteriaExecutor<LoanPdt> criteriaExecutor) {
		Criteria expr = this.getSession().createCriteria(LoanPdt.class);
		return criteriaExecutor.execute(expr);
	}

	@Override
	public BigInteger countLoanPdt() {
		return this.getHibernateDao().count("from " + LoanPdt.class.getName() + " lp where lp.deleteFlag=?", YesOrNoType.NO);
	}

	@Override
	public BigInteger countGroundingLoanPdt() {
		return this.getHibernateDao().count("from " + LoanPdt.class.getName() + " lp where lp.deleteFlag=? and lp.pdtStatus=?", YesOrNoType.NO, PdtStat.GROUNDING);
	}
	
	@Override
	public List<LoanPdt> queryAll() {
		// TODO Auto-generated method stub
		return this.getHibernateDao().find(LoanPdt.class);
	}

	@Override
	public LoanPdt queryByLoanPdtId(String loanPdtId) {
		// TODO Auto-generated method stub
		LoanPdt lp = new LoanPdt();
		lp.setLoadPdtId(loanPdtId);
		return this.getHibernateDao().getFirstOneByExample(lp);
	}

}
