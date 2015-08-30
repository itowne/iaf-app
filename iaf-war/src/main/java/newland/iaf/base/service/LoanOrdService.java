package newland.iaf.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.impl.FreezeResult;
import newland.iaf.merch.model.MerchType;

public interface LoanOrdService {
	
	/**
	 * 修改贷款订单
	 * 
	 * @param loanOrd
	 */
	void updateLoanOrd(LoanOrd loanOrd) throws Exception;
	
	/**
	 * 根据机构号查询订单 (机构信息变更同步更新订单记录的机构信息)
	 */
	List<LoanOrd> queryByIinst(Long iInst);
	/**
	 * 根据商户ID号查询订单 (商户信息变更同步更新订单记录的商户信息)
	 */
	List<LoanOrd> queryByImerch(Long iMerch);

	/**
	 * 根据id查询贷款订单
	 * @param id
	 * @return
	 */
	LoanOrd findLoanOrdById(Long id);
	/**
	 * 查询贷款订单
	 * @return
	 */
	List<LoanOrd> queryLoanOrd() throws Exception;
	/**
	 * 根据贷款产品生成订单
	 * @param pdt
	 * @return
	 */
	LoanOrd applyOrdBy(LoanOrd ord) throws Exception;
	/**
	 *   受理订单
	 * @param loanOrd
	 * @throws Exception
	 */
	void acceptOrd(LoanOrd loanOrd) throws Exception;
	/**
	 * 订单过期
	 * @param loanOrd
	 * @throws Exception
	 */
	void expireOrd(LoanOrd loanOrd) throws Exception;
	/**
	 * 拒决定单
	 * @param loanOrd
	 * @return TODO
	 * @throws Exception
	 */
	OrdStat refuseOrd(LoanOrd loanOrd) throws Exception;
	/**
	 * 审核订单
	 * @param loanOrd
	 * @throws Exception
	 */
	void auditOrd(LoanOrd loanOrd) throws Exception;
	/**
	 * 作废订单
	 * @param loanOrd
	 * @throws Exception
	 */
	void cancelOrd(LoanOrd loanOrd) throws Exception;
	/**
	 * 放款
	 * @param loanOrd 订单
	 * @param iLoanOrdDealSeri 资金流水
	 * @throws Exception
	 */
	void creditOrd(LoanOrd loanOrd) throws Exception;
	/**
	 * 还款
	 * @param loanOrd
	 * @param iLoanOrdDealSeri 资金流水
	 * @throws Exception
	 */
	void refundOrd(LoanOrd loanOrd, LoanOrdPlan plan) throws Exception;
	/**
	 * 平台外还款
	 * @param loanOrd
	 * @param iLoanOrdDealSeri 资金流水
	 * @throws Exception
	 */
	public void othRefundOrd(LoanOrd loanOrd, LoanOrdPlan plan,String memo) throws Exception;
	/**
	 * 平台外放款
	 * @param loanOrd
	 * @param iLoanOrdDealSeri 资金流水
	 * @throws Exception
	 */
	public void othCreditOrd(LoanOrd loanOrd,String memo) throws Exception;
	/**
	 * 放款情况审核
	 * @param loanOrd
	 * @throws Exception
	 */
	public void auditCredit(LoanOrd loanOrd) throws Exception;
	/**
	 * 撤销放款申请
	 * @param loanOrd
	 * @throws Exception
	 */
	public void cancelCredit(LoanOrd loanOrd) throws Exception;
	/**
	 * 还款情况审核
	 * @param loanOrd
	 * @throws Exception
	 */
	public void auditRefund(LoanOrd loanOrd) throws Exception;
	/**
	 * 撤销还款审请
	 * @param loanOrd
	 * @param plan TODO
	 * @throws Exception
	 */
	public void cancelRefund(LoanOrd loanOrd, LoanOrdPlan plan) throws Exception;
	/**
	 * 冻结生效
	 * @param ord
	 * @param plans
	 * @throws Exception
	 */
	public void auditFreeze(LoanOrd ord, List<LoanOrdPlan> plans) throws Exception;
	/**
	 * 拒决冻结
	 * @param ord
	 * @param plans
	 * @throws Exception
	 */
	public void refuseFreeze(LoanOrd ord, List<LoanOrdPlan> plans) throws Exception;
	/**
	 * 商户撤销定单
	 * @param loanOrd
	 * @throws Exception
	 */
	public void merchCancel(LoanOrd loanOrd) throws Exception;
	/**
	 * 上传还款计划
	 * @param loanOrd
	 */
	public void uploadPlan(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception;

	/**
	 * 根据计划编号查询计划
	 * @param iLoanOrdPlan
	 * @return
	 */
	LoanOrdPlan queryPlanById(Long iLoanOrdPlan);
	/**
	 * 根据订单编号查询计划列表
	 * @param iloanOrd
	 * @return
	 * @throws Exception 
	 */
	List<LoanOrdPlan> queryPlan(Long iloanOrd) throws Exception;
	/**
	 * 冻结请求
	 * @param loanOrd
	 * @param plans
	 * @return TODO
	 * @throws Exception 
	 */
	FreezeResult freezeOrd(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception;
	/**
	 * 解冻
	 * @param loanOrd
	 * @param plans
	 * @return TODO
	 * @throws Exception 
	 */
	FreezeResult thaw(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception;
	/**
	 * 拒决解冻
	 * @param loanOrd
	 * @param plans
	 * @return
	 * @throws Exception
	 */
	FreezeResult refuseThaw(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception;
	/**
	 * 解冻审核
	 * @param loanOrd
	 * @param plans
	 * @return
	 * @throws Exception
	 */
	FreezeResult auditThaw(LoanOrd loanOrd, List<LoanOrdPlan> plans) throws Exception;

	/**
	 * 判断订单是还过期
	 * @param loanOrd
	 * @return
	 */
	boolean isExpire(LoanOrd loanOrd);

	/**
	 * 根据订单状态查询
	 * @param stat
	 * @return
	 */
	List<LoanOrd> queryLoanOrdByStat(OrdStat stat);

	/**
	 * 根据订单号查询
	 * @param iloanOrd
	 * @return
	 */
	LoanOrd queryLoanOrdById(Long iloanOrd);
	/**
	 * 复合条件查询
	 * @param cond
	 * @return
	 * @throws Exception
	 */
	List<LoanOrd> queryByCondition(LoanOrdCondition cond) throws Exception;
	
	/**
	 * 分页查询我的订单申请
	 * @param loanordcondition
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<LoanOrd> queryOrdByCon(LoanOrdCondition loanordcondition, Page page) throws Exception;

	/**
	 * 根据竞投编号查询订单
	 * @param iDebitbid
	 * @return
	 */
	LoanOrd queryByDebitbid(Long iDebitbid);

	/**
	 * 
	 * @param loanOrd
	 * @param plans
	 * @return
	 * @throws Exception 
	 */
	FreezeResult cancelFreeze(LoanOrd loanOrd, ArrayList<LoanOrdPlan> plans) throws Exception;
	/**
	 * 根据竞投id查村订单，返回list
	 * @param iDebitbid
	 * @return
	 */
	List<LoanOrd> queryByDebit(Long iDebitbid);
	
	LoanOrd queryByLoanOrdId(String loanOrdId);
	
	List<Object> queryTenDays(Date d,Date tenD,Long iinst); 
	
	List<LoanOrdPlan> queryIloanOrd(Date d,Date tenD,Long iinst);
	
	List<LoanOrd> queryfreezeLoanOrd(Long iinst);
}
