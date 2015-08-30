package newland.iaf.base.dao;

import java.util.Date;
import java.util.List;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.condition.LoanOrdCondition;

import org.hibernate.Criteria;
import org.ohuyo.commons.query.criterion.Page;

public interface LoanOrdDao {

	/**
	 * 新增贷款订单
	 * 
	 * @param loanOrd
	 */
	void saveLoanOrd(LoanOrd loanOrd);

	/**
	 * 修改贷款订单
	 * 
	 * @param loanOrd
	 */
	void updateLoanOrd(LoanOrd loanOrd);

	/**
	 * 删除贷款订单
	 * 
	 * @param loanOrd
	 */
	void deleteLoanOrd(LoanOrd loanOrd);

	/**
	 * 根据id查询贷款订单
	 * 
	 * @param id
	 * @return
	 */
	LoanOrd findLoanOrdById(Long id);

	/**
	 * 查询贷款订单
	 * 
	 * @return
	 */
	List<LoanOrd> queryLoanOrd();

	/**
	 * 自定义查询条件
	 * 
	 * @param expr
	 * @return
	 */
	public List<LoanOrd> query(CriteriaExecutor<List<LoanOrd>> executor);
	
	/**
	 * 分页查询我的订单申请
	 * @param loanordcondition
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<LoanOrd> queryOrdByCon(LoanOrdCondition loanordcondition, Page page) throws Exception;

	/**
	 * 复合条件查询
	 * @param cond
	 * @return
	 */
	List<LoanOrd> queryByCondition(LoanOrdCondition cond);
	/**
	 * 定单过期，独立使用以使状态生效
	 * @param loanOrd
	 */
	void expire(LoanOrd loanOrd);
	/**
	 * 根据竞投编号查询订单
	 * @param iDebitbid
	 * @return
	 */
	LoanOrd queryByDebitbid(Long iDebitbid);
	/**
	 * 根据竞投id查村订单，返回list
	 * @param iDebitbid
	 * @return
	 */
	List<LoanOrd> queryByDebit(Long iDebitbid);
	/**
	 * 查询
	 * @param executor
	 * @return
	 */
	<T> T queryBy(CriteriaExecutor<T> executor);
	/**
	 * 根据机构号查询订单 (机构信息变更同步更新订单记录的机构信息)
	 */
	List<LoanOrd> queryByIinst(Long iInst);
	/**
	 * 根据商户ID号查询订单 (商户信息变更同步更新订单记录的商户信息)
	 */
	List<LoanOrd> queryByImerch(Long iMerch);
	
	LoanOrd queryByLoanOrdId(String loanOrdId);
	
	public <T> T queryBy(CriteriaExecutor<T> executor, Class<?> clazz);
	
	List<Object> queryTenDays(Date d,Date tenD,Long iinst); 
	
	List<LoanOrdPlan> queryIloanOrd(Date d,Date tenD,Long iinst);
	
	List<LoanOrd> queryfreezeLoanOrd(Long iinst);
}
