package newland.iaf.base.service;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.condition.LoanOrdPlanCondition;

/**
 * 订单还款计划服务
 * @author new
 *
 */
public interface LoanOrdPlanService {
	/**
	 *  保存订单计划
	 * @param plans
	 * @throws Exception
	 */
	void saveLoanOrdPlan(List<LoanOrdPlan> plans) throws Exception;
	/**
	 * 更新定单计划
	 * @param plans
	 * @return TODO
	 * @throws Exception
	 */
	int uploadLoanOrdPlan(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception;
	/**
	 * 查询逾期定单计划
	 * @param iLoanOrd
	 * @return
	 * @throws Exception
	 */
	List<LoanOrdPlan> queryDelinQuencyPlan(Long iLoanOrd) throws Exception;
	/**
	 * 计划逾期
	 * @param plan
	 * @throws Exception
	 */
	void delinQuencyPlan(LoanOrdPlan plan) throws Exception;
	/**
	 * 还款
	 * @param plan
	 * @throws Exception
	 */
	void refundPlan(LoanOrdPlan plan,String memo) throws Exception;
	/**
	 * 冻结
	 * @param plan
	 * @throws Exception
	 */
	void freezePlan(LoanOrdPlan plan) throws Exception;
	/**
	 * 解冻
	 * @throws Exception
	 */
	void thawPlan(LoanOrdPlan plan) throws Exception;
	/**
	 * 根据订单ID查询计划
	 * @param iLoanOrd
	 * @return
	 * @throws Exception
	 */
	List<LoanOrdPlan> queryPlanById(Long iLoanOrd) throws Exception;
	
	/**
	 * 查询计划
	 * @param iLoanOrdPlan
	 * @return
	 */
	LoanOrdPlan queryByPlanId(Long iLoanOrdPlan);
	/**
	 * 更新计划
	 * @param plan
	 */
	void update(LoanOrdPlan plan);
	/**
	 * 查询
	 * @param plan
	 * @return
	 */
	List<LoanOrdPlan> queryBy(LoanOrdPlan plan);
	/**
	 * 根据订单与期号查询
	 * @param iloanOrd
	 * @param period
	 * @return
	 */
	LoanOrdPlan queryBy(Long iloanOrd, Integer period);
	/**
	 * 是否已还款还成
	 * @return
	 */
	boolean isPaidUp(Long iloanOrd);
	
	List<LoanOrdPlan> queryByLoanOrdId(String loanOrdId);
	
	List<LoanOrdPlan> queryByIinst(Long id);
	List<LoanOrdPlan> queryByImerch(Long id);
	
	List<LoanOrdPlan> queryByCond(LoanOrdPlanCondition cond,Page page);

}
