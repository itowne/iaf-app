package newland.iaf.merch.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import newland.iaf.base.model.condition.CreditReportCondition;
import newland.iaf.merch.model.LoanCreditReport;

import org.ohuyo.commons.query.criterion.Page;

public interface CreditReportDao {
	/**
	 * 新增
	 * @param rpt
	 */
	void save(LoanCreditReport rpt);
	/**
	 * 更新
	 * @param rpt
	 */
	void update (LoanCreditReport rpt);
	/**
	 * 条件查询
	 * @param cond
	 * @return
	 */
	List<LoanCreditReport> queryBy(CreditReportCondition cond);
	/**
	 * 条件分页查询
	 * @param cond
	 * @param page
	 * @return
	 */
	List<LoanCreditReport> queryBy(CreditReportCondition cond, Page page);
	/**
	 * 删除报告
	 * @param report
	 */
	void delete(LoanCreditReport report);

	public List<?>  getHcTransLogDetail(String merchNo, Date begin, Date end);
}
