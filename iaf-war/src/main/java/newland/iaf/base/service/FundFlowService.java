package newland.iaf.base.service;

import java.util.List;
import java.util.Set;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.OrdStat;
/**
 * 资金流水记录服务
 * @author new
 *
 */
public interface FundFlowService {
	
	/**
	 * 新增资金流水
	 * @param fundFlow
	 */
	void save(FundFlow fundFlow);
	/**
	 * 更新资金流水
	 * @param fundFlow
	 * @param stat 
	 */
	void update(FundFlow fundFlow, FundFlowStat stat);
	/**
	 * 查询流水记录
	 * @param type TODO
	 * @param stat
	 * @return
	 */
	List<FundFlow> queryFundFlowBy(FundFlowType type, FundFlowStat stat);
	/**
	 * 查询所有相关类
	 * @param type
	 * @param status
	 * @return
	 */
	List<FundFlow> queryFundFlowBy(FundFlowType type, Set<FundFlowStat> status);

	/**
	 * 审核放款
	 * @return
	 * @throws Exception
	 */
	FundFlow auditCredit(FundFlow fundFlow) throws Exception;
	/**
	 * 撤销放款
	 * @param fundFlow
	 * @return
	 * @throws Exception
	 */
	FundFlow cancel(FundFlow fundFlow) throws Exception;
	
	/**
	 * 审核还款
	 * @return
	 * @throws Exception
	 */
	FundFlow auditRefund(FundFlow fundFlow) throws Exception;
	/**
	 * 复合查询
	 * @param cond
	 * @return
	 */
	List<FundFlow> queryFundFlowBy(FundFlowCondition cond);
	/**
	 * 分页查询
	 * @param cond
	 * @param page
	 */
	List<FundFlow> queryFundFlowBy(FundFlowCondition cond, Page page);
	/**
	 * 检查是否有未处理还款请求
	 * @param iloanOrd
	 * @param type
	 * @return
	 */
	boolean haveFundFlow(Long iloanOrd, FundFlowType type);
	List<FundFlow> queryByIloanOrdAndType(Long iloanOrd, FundFlowType type);
	
	FundFlow queryByIloanOrdPlan(Long iLoanOrdPlan);
	
	List<Object> queryTrade(FundFlowStat stat,Object[] type);
	
	List<Object> queryCredit(Long iinst,FundFlowStat stat,FundFlowType type);
	
	
}
