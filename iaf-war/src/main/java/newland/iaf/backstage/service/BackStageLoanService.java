package newland.iaf.backstage.service;

import java.io.File;
import java.util.List;
import java.util.Set;

import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.Freeze;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.ApplyRequestCondition;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.ApplyType;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.service.impl.IafConsoleSession;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 汇卡贷款服务
 * @author new
 *
 */
public interface BackStageLoanService {
	/**
	 * 根据订单状态查询订单列表
	 * @param type TODO
	 * @param stat
	 * @return
	 * @throws Exception
	 */
	List<FundFlow> queryFundFlowBy(FundFlowType type, FundFlowStat stat) throws Exception;
	/**
	 * 审核放款情况
	 * @param fundFlow
	 * @param session
	 * @param memo
	 * @param ipaddr TODO
	 * @return
	 * @throws Exception
	 */
	LoanOrd auditCredit(FundFlow fundFlow, IafConsoleSession session
			, String memo, String ipaddr) throws Exception;
	/**
	 * 撤销放款
	 * @param fundFlow
	 * @param session
	 * @param memo
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	LoanOrd cancelCredit(FundFlow fundFlow, IafConsoleSession session
			, String memo, String ipaddr) throws Exception;
	/**
	 * 审核还款情况
	 * @param fundFlow
	 * @param session
	 * @param memo
	 * @param ipaddr TODO
	 * @return
	 * @throws Exception
	 */
	LoanOrd auditRefund(FundFlow fundFlow, IafConsoleSession session
			, String memo, String ipaddr) throws Exception;
	
	/**
	 * 撤销还款
	 * @param fundFlow
	 * @param session
	 * @param memo
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	LoanOrd cancelRefund(FundFlow fundFlow, IafConsoleSession session
			, String memo, String ipaddr) throws Exception;
	/**
	 * 生成还款对账文件
	 * @param type TODO
	 * @return
	 * @throws Exception
	 */
	File genFlowsFile(List<FundFlow> flows, FundFlowType type) throws Exception;
	
	/**
	 * 根据请求类型查询申请单
	 * @param type
	 * @return
	 * @throws Exception
	 */
	List<ApplyRequest> queryApplyRequest(ApplyType type) throws Exception;
	/**
	 * 请求已处理
	 * @param req
	 * @param session TODO
	 * @param memo TODO
	 * @param ipaddr TODO
	 * @throws Exception
	 */
	void processApply(ApplyRequest req, IafConsoleSession session, String memo, String ipaddr) throws Exception;
	/**
	 * 退回请求
	 * @param req
	 * @param session TODO
	 * @param memo TODO
	 * @param ipaddr TODO
	 * @throws Exception
	 */
	void refuseApply(ApplyRequest req, IafConsoleSession session, String memo, String ipaddr) throws Exception;
	/**
	 * 查询订单
	 * @param iloanOrd
	 * @return
	 */
	LoanOrd queryLoanById(Long iloanOrd);
	/**
	 * 生成单笔贷款订单
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	File genOnceCreditFile(List<LoanOrd> list) throws Exception;
	/**
	 * 复合查询请求单
	 * @param cond
	 */
	List<ApplyRequest> queryApplyRequest(ApplyRequestCondition cond);
	/**
	 * 根据定单号与计划期号查询
	 * @param iloanOrd
	 * @param i
	 * @return
	 */
	LoanOrdPlan queryPlan(Long iloanOrd, Integer i);
	/**
	 * 生成冻结请求文件
	 * @param requestList
	 * @return
	 */
	File genApplyFile(List<Freeze> requestList);
	
	/**
	 * 查询订单列表
	 * @param cond
	 * @return
	 * @throws Exception 
	 */
	List<LoanOrd> queryLoanOrds(LoanOrdCondition cond, Page page) throws Exception;
	/**
	 * 屏蔽订单
	 * @param loanOrd
	 * @param userSession
	 * @param ipaddr
	 * @throws Exception 
	 */
	void shield(LoanOrd loanOrd, IafConsoleSession userSession, String ipaddr) throws Exception;
	/**
	 * 根据订单查询计划
	 * @param iloanOrd
	 * @return
	 * @throws Exception 
	 */
	List<LoanOrdPlan> queryPlan(Long iloanOrd) throws Exception;
	/**
	 * 根据类型和订单号查询资金记录
	 * @param refund
	 * @param iloanOrd
	 * @return
	 */
	List<FundFlow> queryFundFlowBy(FundFlowType refund, Long iloanOrd);
	/**
	 * 分页查询
	 * @param cond
	 * @param page
	 * @return
	 */
	List<FundFlow> queryFundFlowBy(FundFlowCondition cond, Page page);
	/**
	 * 根据流水类型查询
	 * @param types
	 * @param iloanOrd
	 * @return
	 */
	List<FundFlow> queryFundFlowBy(Set<FundFlowType> types, Long iloanOrd);
	/**
	 * 回滚
	 * @param msg
	 * @throws Exception
	 */
	void expiryTrans(TransMsg msg) throws Exception;
	/**
	 * 条件查询
	 * @param cond
	 * @param page
	 * @return
	 */
	List<ApplyRequest> queryApplyRequest(ApplyRequestCondition cond, Page page);
	
	void unFreezenUnPay(ApplyRequest req, IafConsoleSession session,
			String memo, String ipaddr) throws Exception;
	void unFreezenPayed(ApplyRequest req, IafConsoleSession session,
			String memo, String ipaddr) throws Exception;
	void reShield(LoanOrd loanOrd, IafConsoleSession userSession, String ipaddr)
			throws Exception;

}
