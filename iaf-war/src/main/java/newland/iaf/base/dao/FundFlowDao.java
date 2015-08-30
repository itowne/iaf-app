package newland.iaf.base.dao;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
/**
 * 流水记录dao
 * @author shizn
 *
 */
public interface FundFlowDao {
	/**
	 * 新增资金流水
	 * @param fundFlow
	 */
	void save(FundFlow fundFlow);
	/**
	 * 更新资金流水
	 * @param fundFlow
	 */
	void update(FundFlow fundFlow);
	/**
	 * 查询流水记录
	 * @param fundFlow TODO
	 * @return
	 */
	List<FundFlow> queryFundFlowBy(FundFlow fundFlow);
	/**
	 * 复合查询
	 * @param cond
	 * @return
	 */
	List<FundFlow> queryByCondition(FundFlowCondition cond);
	/**
	 * 分页查询
	 * @param cond
	 * @param page
	 * @return
	 */
	List<FundFlow> queryByCondition(FundFlowCondition cond, Page page);
	/**
	 * 
	 * @param iloanOrd
	 * @param type
	 * @return
	 */
	boolean haveFundFlow(Long iloanOrd, FundFlowType type);
	/**
	 * 根据othSysNo(orderid)查询
	 * @param othSysNo
	 * @return
	 */
	FundFlow queryByOthSysNo(String othSysNo);
	
	List<FundFlow> listQueryBySysNo(String othSysNo);
	
	List<FundFlow> queryByIloanOrdAndType(Long iloanOrd, FundFlowType type);
	
	FundFlow queryByIloanOrdPlan(Long iLoanOrdPlan);
	
	public <T> T queryBy(CriteriaExecutor<T> executor, Class<?> clazz);
	
	List<Object> queryRefund(Long imerch,FundFlowStat stat,FundFlowType type);
}
