package newland.iaf.base.dao.impl;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.LoanOrdPlanHisDao;
import newland.iaf.base.model.LoanOrdPlanHis;

import org.springframework.stereotype.Service;
@Service("loanOrdPlanHisDao")
public class LoanOrdPlanHisDaoImpl extends BaseDao implements LoanOrdPlanHisDao {

	@Override
	public void save(LoanOrdPlanHis his) {
		this.getSession().save(his);
		
	}

}
