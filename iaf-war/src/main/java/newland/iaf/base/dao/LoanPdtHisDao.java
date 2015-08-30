package newland.iaf.base.dao;

import newland.iaf.base.model.LoanPdtHis;

/**
 * 
 * @author rabbit
 * 
 */
public interface LoanPdtHisDao {

	void saveLoanPdtHis(LoanPdtHis loanPdtHis);

	LoanPdtHis getLoanPdtHis(Long iloanPdtHis);

}
