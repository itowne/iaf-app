package newland.iaf.inst.service;

import java.io.File;
import java.util.List;
import java.util.Set;

import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.model.Inst;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 机构贷款订单处理类
 * 
 * @author new
 * 
 */
public interface InstLoanService {
	/**
	 * 添加贷款产品
	 * 
	 * @param pdt
	 * @param session
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	LoanPdt addLoanPdt(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception;

	/**
	 * 产品上架
	 * 
	 * @param pdt
	 * @param session
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	LoanPdt grounding(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception;

	/**
	 * 产品下架
	 * 
	 * @param pdt
	 * @param session
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	LoanPdt undercarriage(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception;

	/**
	 * 更新产品
	 * 
	 * @param pdt
	 * @param session
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	LoanPdt updateLoanPdt(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception;

	/**
	 * 删除产品
	 * 
	 * @param pdt
	 * @param session
	 * @param ipaddr
	 * @throws Exception
	 */
	void deleteLoanPdt(LoanPdt pdt, InstSession session, String ipaddr)
			throws Exception;

	/**
	 * 受理
	 * 
	 * @param loanOrd
	 * @param memo
	 * @param ipaddr
	 *            TODO
	 * @param instUser
	 * @return
	 * @throws Exception
	 */
	LoanOrd accept(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception;

	/**
	 * 审核
	 * 
	 * @param loanOrd
	 * @param memo
	 * @param ipaddr
	 *            TODO
	 * @param instUser
	 * @return
	 * @throws Exception
	 */
	LoanOrd audit(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception;

	/**
	 * 发放贷款
	 * 
	 * @param loanOrd
	 * @param transCfg
	 *            TODO
	 * @param ipaddr
	 *            TODO
	 * @param dealSeri
	 * @param instUser
	 * @return
	 * @throws Exception
	 */
	LoanOrd credit(LoanOrd loanOrd, TransMsg transCfg, InstSession session,
			String memo, String ipaddr,FundFlow fundFlow) throws Exception;

	/**
	 * 平台外发放贷款
	 * 
	 * @param loanOrd
	 * @param ipaddr
	 *            TODO
	 * @param dealSeri
	 * @param instUser
	 * @return
	 * @throws Exception
	 */
	LoanOrd othCredit(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception;

	/**
	 * 批量放款
	 * 
	 * @param ords
	 * @param transCfg
	 *            TODO
	 * @param session
	 * @param ipaddr
	 * @throws Exception
	 */
	void batchCredit(List<LoanOrd> ords, TransMsg transCfg,
			InstSession session, String memo, String ipaddr) throws Exception;

	/**
	 * 平台外批量放款
	 * 
	 * @param ords
	 * @param session
	 * @param memo
	 * @param ipaddr
	 * @throws Exception
	 */
	void batchOthCredit(List<LoanOrd> ords, InstSession session, String memo,
			String ipaddr) throws Exception;

	/**
	 * 拒决
	 * 
	 * @param loanOrd
	 * @param ipaddr
	 *            TODO
	 * @param instUser
	 * @return
	 * @throws Exception
	 */
	LoanOrd refuse(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception;

	/**
	 * 作废
	 * 
	 * @param loanOrd
	 * @param memo
	 *            TODO
	 * @param ipaddr
	 *            TODO
	 * @param instUser
	 * @return
	 * @throws Exception
	 */
	LoanOrd cancel(LoanOrd loanOrd, InstSession session, String memo,
			String ipaddr) throws Exception;

	/**
	 * 冻结
	 * 
	 * @param loanOrd
	 * @param plans
	 * @param ipaddr
	 *            TODO
	 * @param instUser
	 * @return
	 * @throws Exception
	 */
	LoanOrd freeze(LoanOrd loanOrd, List<LoanOrdPlan> plans,
			InstSession session, String memo, String ipaddr) throws Exception;

	/**
	 * 解冻
	 * 
	 * @param loanOrd
	 * @param req
	 * @param ipaddr
	 *            TODO
	 * @param instUser
	 * @return
	 * @throws Exception
	 */
	LoanOrd cancelFreeze(LoanOrd loanOrd, ApplyRequest req,
			InstSession session, String memo, String ipaddr) throws Exception;

	/**
	 * 解冻
	 * 
	 * @param loanOrd
	 * @param plans
	 * @param ipaddr
	 *            TODO
	 * @param instUser
	 * @return
	 * @throws Exception
	 */
	LoanOrd thaw(LoanOrd loanOrd, List<LoanOrdPlan> plans, InstSession session,
			String memo, String ipaddr) throws Exception;

	/**
	 * 上传还款计划
	 * 
	 * @param plans
	 * @param ipaddr
	 *            TODO
	 * @throws Exception
	 */
	void uploadLoanOrdPlan(LoanOrd loanOrd, List<LoanOrdPlan> plans,
			InstSession session, String memo, String ipaddr) throws Exception;
	
	/**
	 * 修改还款计划
	 * 
	 * @param plans
	 * @param ipaddr
	 *            TODO
	 * @throws Exception
	 */
	void modifyLoanOrdPlan(LoanOrd loanOrd, List<LoanOrdPlan> plans,
			InstSession session, String memo, String ipaddr) throws Exception;
	
	/**
	 * 上传还款计划
	 * 
	 * @param loanOrd
	 * @param session
	 * @param memo
	 * @param ipaddr
	 *            TODO
	 * @param plans
	 * @throws Exception
	 */
	void uploadLoanOrdPlan(LoanOrd loanOrd, File file, InstSession session,
			String memo, String ipaddr) throws Exception;

	/**
	 * 根据ID查询订单
	 * 
	 * @param LoanOrd
	 * @return
	 * @throws Exception
	 */
	LoanOrd queryLoanOrdById(Long iLoanOrd) throws Exception;

	/**
	 * 根据机构号和状态查询订单列表
	 * 
	 * @param inst
	 * @param stat
	 *            状态为NULL时查询所有订单
	 * @return
	 * @throws Exception
	 */

	List<LoanOrd> queryOrd(Inst inst, Set<OrdStat> status) throws Exception;

	/**
	 * 根据计划ID查询计划
	 * 
	 * @param iLoanOrdPlan
	 * @return
	 * @throws Exception
	 */
	LoanOrdPlan queryPlanById(Long iLoanOrdPlan) throws Exception;

	/**
	 * 根据订单查询计划列表
	 * 
	 * @param inst
	 * @return
	 * @throws Exception
	 */
	List<LoanOrdPlan> queryPlan(LoanOrd loanOrd) throws Exception;

	/**
	 * 根据状态查询贷款产品列表
	 * 
	 * @param inst
	 * @param stat
	 * @return
	 * @throws Exception
	 */
	List<LoanPdt> queryByInst(Inst inst, PdtStat stat, Page page)
			throws Exception;

	/**
	 * 根据产品ID查询贷款产品
	 * 
	 * @param ipdt
	 * @return
	 * @throws Exception
	 */
	LoanPdt queryByPdtId(Long ipdt) throws Exception;

	/**
	 * 复合条件查询
	 * 
	 * @param cond
	 * @return
	 * @throws Exception
	 */
	List<LoanOrd> queryByCond(LoanOrdCondition cond, Page page)
			throws Exception;

	List<LoanOrd> queryByCon(LoanOrdCondition cond)
			throws Exception;
	/**
	 * 查询订单处理流水
	 * 
	 * @param loanOrd
	 * @return
	 */
	List<OperLog> queryOperLogBy(LoanOrd loanOrd);

	/**
	 * 还款计划文件解析
	 * 
	 * @param planFile
	 * @return
	 */
	List<LoanOrdPlan> convertPlanFrom(File planFile);

	/**
	 * 根据机构查询冻结请求
	 * 
	 * @param inst
	 * @param loanOrd
	 *            TODO
	 * @param apply
	 * @return
	 */
	List<ApplyRequest> queryFreezeReq(Inst inst, LoanOrd loanOrd,
			ApplyStat apply);

	/**
	 * 主动受理竟投产品
	 * 
	 * @param bid
	 * @param session
	 * @param memo
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	LoanOrd applyDebit(DebitBid bid, InstSession session, String memo,
			String ipaddr) throws Exception;

	/**
	 * 用新计划开表覆盖旧列表 用于显示
	 * 
	 * @param oldPlans
	 * @param newPlans
	 * @return
	 */
	List<LoanOrdPlan> overRidePlan(List<LoanOrdPlan> oldPlans,
			List<LoanOrdPlan> newPlans);

	/**
	 * 根据竟投查询机构是否申请过
	 * 
	 * @param debitBid
	 * @param inst
	 * @return
	 * @throws Exception
	 */
	LoanOrd queryLoanOrdByDebit(DebitBid debitBid, Inst inst) throws Exception;
	/**
	 * 生成fundflow
	 * @param loanOrd
	 * @param transCfg
	 * @param type
	 * @param fundFlowStat
	 * @return
	 * @throws Exception
	 */
	public FundFlow genFundFlow(LoanOrd loanOrd, TransMsg transCfg,
			FundFlowType type, FundFlowStat fundFlowStat) throws Exception;
	public FundFlow othGenFundFlow(LoanOrd loanOrd, TransMsg transCfg,
			FundFlowType type, FundFlowStat fundFlowStat) throws Exception;
	public List<LoanOrd> queryByCondProAudi(LoanOrdCondition cond, Page page) throws Exception;
	public LoanOrd queryDebitByInst(DebitBid debitBid, Inst inst) throws Exception;
	
}
