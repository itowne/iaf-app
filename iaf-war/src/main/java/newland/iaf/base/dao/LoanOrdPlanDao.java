package newland.iaf.base.dao;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.condition.LoanOrdPlanCondition;
/**
 * 订单计划数据接口
 * @author new
 *
 */
public interface LoanOrdPlanDao {
	
	void save(LoanOrdPlan plan);
	
	void update(LoanOrdPlan plan);
	
	void delete(LoanOrdPlan plan);
	
	List<LoanOrdPlan> findAll();
	
	List<LoanOrdPlan> query(LoanOrdPlan plan);
	
	LoanOrdPlan queryById(Long iplanId);

	<T> T queryBy(CriteriaExecutor<T> executor);
	
	List<LoanOrdPlan> queryBy(LoanOrdPlanCondition cond);
	
	List<LoanOrdPlan> queryByCond(LoanOrdPlanCondition cond,Page page);
	/**
	 * 清除无效计划
	 */
	void cleanInvalidPlan();
	List<LoanOrdPlan> queryByLoanOrdId(String loanOrdId);
	
	List<LoanOrdPlan> queryByIinst(Long id);
	List<LoanOrdPlan> queryByImerch(Long id);
}