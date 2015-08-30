package newland.iaf.merch.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.model.condition.CreditReportCondition;
import newland.iaf.merch.dao.CreditReportDao;
import newland.iaf.merch.model.LoanCreditReport;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;

@Service("creditReportDao")
public class CreditReportDaoImpl extends BaseDao implements CreditReportDao {

	@Override
	public void save(LoanCreditReport rpt) {
		this.getSession().save(rpt);

	}

	@Override
	public void update(LoanCreditReport rpt) {
		this.getSession().update(rpt);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanCreditReport> queryBy(CreditReportCondition cond) {
		return this.getHibernateDao().findByCriteriaEx(cond);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanCreditReport> queryBy(CreditReportCondition cond, Page page) {
		return this.getHibernateDao().findByCriteriaEx(cond, page, true);
	}

	@Override
	public void delete(LoanCreditReport report) {
		this.getSession().delete(report);
	}

	@Override
	public List<?> getHcTransLogDetail(String merchNo, Date begin, Date end) {
		// TODO Auto-generated method stub
		return this.getHibernateDao().find("select sum(htl.amt),count(*) from HcTransLog htl where trans_date>=? and trans_date<? and merch_no=? and trans_type=?",begin, end, merchNo, "N");
	}

}
