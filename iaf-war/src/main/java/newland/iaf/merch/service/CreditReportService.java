package newland.iaf.merch.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import newland.iaf.base.model.condition.CreditReportCondition;
import newland.iaf.merch.model.LoanCreditReport;

import org.ohuyo.commons.query.criterion.Page;
/**
 * 信用报告DAO
 * @author new
 *
 */
public interface CreditReportService {
	/**
	 * 保存
	 * @param rpt
	 */
	void save(LoanCreditReport rpt);
	/**
	 * 分页条件查询
	 * @param imerch
	 * @return
	 */
	List<LoanCreditReport> queryBy(CreditReportCondition cond, Page page);
	/**
	 * 条件查询
	 * @param cond
	 * @return
	 */
	List<LoanCreditReport> queryBy(CreditReportCondition cond);
	/**
	 * 更新报告
	 * @param report
	 */
	void update(LoanCreditReport report);
	/**
	 * 删除报告
	 * @param report
	 */
	void delete(LoanCreditReport report);
	
	public List<?>  getHcTransLogDetail(String merchNo, Date begin, Date end);
}
