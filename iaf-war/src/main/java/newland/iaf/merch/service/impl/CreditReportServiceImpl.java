package newland.iaf.merch.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.condition.CreditReportCondition;
import newland.iaf.merch.dao.CreditReportDao;
import newland.iaf.merch.model.LoanCreditReport;
import newland.iaf.merch.service.CreditReportService;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;

@Service("creditReportService")
public class CreditReportServiceImpl implements CreditReportService {
	
	@Resource (name = "creditReportDao")
	private CreditReportDao creditReportDao;

	@Override
	public void save(LoanCreditReport rpt) {
		this.creditReportDao.save(rpt);

	}

	@Override
	public List<LoanCreditReport> queryBy(CreditReportCondition cond, Page page) {
		return this.creditReportDao.queryBy(cond, page);
	}

	@Override
	public List<LoanCreditReport> queryBy(CreditReportCondition cond) {
		return this.creditReportDao.queryBy(cond);
	}

	@Override
	public void update(LoanCreditReport report) {
		this.creditReportDao.update(report);
		
	}

	@Override
	public void delete(LoanCreditReport report) {
		this.creditReportDao.delete(report);
	}


	@Override
	public List<?> getHcTransLogDetail(String merchNo, Date begin, Date end) {
		// TODO Auto-generated method stub
		return this.creditReportDao.getHcTransLogDetail(merchNo, begin, end);
	}

}
