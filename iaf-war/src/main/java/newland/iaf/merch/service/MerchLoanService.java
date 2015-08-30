package newland.iaf.merch.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.merch.model.Merch;

/**
 * 商户贷款接口
 * 
 * @author new
 * 
 */
public interface MerchLoanService {
	/**
	 * 增加竟标产品
	 * @param bid
	 * @param merchUser
	 * @param memo
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	DebitBid addDebitBid(DebitBid bid, MerchSession session, 
			String memo, String ipaddr) throws Exception;
	/**
	 * 修改竟标产品
	 * @param bid
	 * @param merchUser
	 * @param memo
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	DebitBid updateDebitBid(DebitBid bid, MerchSession session, 
			String memo, String ipaddr)throws Exception;
	/**
	 * 产品上架
	 * @param bid
	 * @param merchUser
	 * @param memo
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	DebitBid grounding(DebitBid bid, MerchSession session, 
		String memo, String ipaddr) throws Exception;
	/**
	 * 产品下架
	 * @param bid
	 * @param merchUser
	 * @param memo
	 * @param ipaddr
	 * @return
	 * @throws Exception
	 */
	DebitBid undercarriage(DebitBid bid, MerchSession session, 
			String memo, String ipaddr) throws Exception;
	/**
	 * 提交贷款申请-产品类
	 * @param loanPdt
	 * @param quota
	 * @param term
	 * @param memo
	 *            操作备注
	 * @param ipaddr TODO
	 * @param merch
	 * @param user
	 * 
	 * @return
	 * @throws Exception
	 */
	LoanOrd applyLoanPtd(LoanPdt loanPdt, BigDecimal quota, Integer term,
			MerchSession session,String purpose, String memo, String ipaddr,String termType) throws Exception;

	/**
	 * 向机构定投放竟标产品
	 * 
	 * @param debitBid
	 * @param insts
	 * @param memo
	 * @param ipaddr TODO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<LoanOrd> applyDebitBid(DebitBid debitBid, Set<Long> insts,
			MerchSession session,  String memo, String ipaddr) throws Exception;

	/**
	 * 撤销贷款申请
	 * 
	 * @param loanOrd
	 * @param memo
	 * @param ipaddr TODO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	LoanOrd cancelLoanOrd(LoanOrd loanOrd, MerchSession session, String memo, String ipaddr)
			throws Exception;

	/**
	 * 修改贷款申请
	 * 
	 * @param loanOrd
	 * @param memo
	 * @param ipaddr TODO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	LoanOrd updateLoanOrd(LoanOrd loanOrd, MerchSession session, String memo, String ipaddr)
			throws Exception;

	/**
	 * 还款申请
	 * 
	 * @param loanOrd
	 * @param plan 还款计划
	 * @param transCfg TODO
	 * @param memo
	 * @param ipaddr TODO
	 * @param dealSeri
	 * @param user
	 * @return
	 * @throws Exception
	 */
	LoanOrd refund(LoanOrd loanOrd, LoanOrdPlan plan, TransMsg transCfg, 
			MerchSession session, String memo, String ipaddr) throws Exception;

	/**
	 * 平台外还款
	 * 
	 * @param loanOrd
	 * @param plan
	 * @param memo
	 * @param ipaddr TODO
	 * @param dealSeri
	 * @param user
	 * @return
	 * @throws Exception
	 */
	LoanOrd othRefund(LoanOrd loanOrd, LoanOrdPlan plan, MerchSession session, 
			String memo, String ipaddr) throws Exception;

	/**
	 * 根据订单号查询订单
	 * 
	 * @param iLoanOrd
	 * @return
	 * @throws Exception
	 */
	LoanOrd queryOrdById(Long iLoanOrd) throws Exception;

	/**
	 * 根据商户与状态查询订单
	 * 
	 * @param merch
	 * @param stat
	 * @return
	 * @throws Exception
	 */
	List<LoanOrd> queryOrd(Merch merch, OrdStat stat) throws Exception;

	/**
	 * 根据计划编号查询计划
	 * 
	 * @param iLoanOrdPlan
	 * @return
	 * @throws Exception
	 */
	LoanOrdPlan queryPlanById(Long iLoanOrdPlan) throws Exception;

	/**
	 * 根据订单号查询计划
	 * 
	 * @param loanOrd
	 * @return
	 * @throws Exception
	 */
	List<LoanOrdPlan> queryPlan(LoanOrd loanOrd) throws Exception;
	
	LoanOrd batchRefund(LoanOrd loanOrd, List<LoanOrdPlan> plans, TransMsg transCfg, 
			MerchSession session, String memo, String ipaddr)throws Exception;
	
	public FundFlow genFundFlow(LoanOrd loanOrd, LoanOrdPlan plan, TransMsg transCfg, FundFlowType type,
			FundFlowStat fundFlowStat,MerchSession session,String memo,String ipaddr) throws Exception;
	
	public LoanOrd instOthRefund(LoanOrd loanOrd, LoanOrdPlan plan, InstSession session, 
			String memo, String ipaddr) throws Exception;

}
