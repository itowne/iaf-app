package newland.iaf.base.dao.impl;

import org.springframework.stereotype.Repository;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.LoanPdtHisDao;
import newland.iaf.base.model.LoanPdtHis;

/**
 * 
 * @author rabbit
 * 
 */
@Repository("com.newland.iaf.loanPdtHisDao")
public class LoanPdtHisDaoImpl extends BaseDao implements LoanPdtHisDao {

	@Override
	public void saveLoanPdtHis(LoanPdtHis loanPdt) {
		this.getSession().save(loanPdt);
	}

	@Override
	public LoanPdtHis getLoanPdtHis(Long iloanPdtHis) {
		return (LoanPdtHis) getSession().get(LoanPdtHis.class, iloanPdtHis);
	}

}
