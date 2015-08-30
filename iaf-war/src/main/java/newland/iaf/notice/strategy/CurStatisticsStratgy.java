package newland.iaf.notice.strategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanStatisticsMgrService;
import newland.iaf.base.service.LoanStatisticsService;
import newland.iaf.base.service.LoanStatisticsSubService;
import newland.iaf.base.service.impl.FreezeResult;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.iaf.notice.ProcessType;
import newland.iaf.notice.annotation.MethodStrategy;

import org.ohuyo.commons.query.criterion.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@MethodStrategy(processType = ProcessType.SYNC, value = "curStatistics")
@Service("curStatisticsStratgy")
public class CurStatisticsStratgy implements LoanOrdService{
	
	@Resource (name = "loanStatisticsService")
	private LoanStatisticsService statisticsService;
	
	@Resource (name = "loanStatisticsSubService")
	private LoanStatisticsSubService statisticsSubService; 
	
	@Resource (name = "loanStatisticsMgrService")
	private LoanStatisticsMgrService statisticsMgrService;
	
	@Resource (name = "com.newland.iaf.instService")
	private InstService instService;
	
	@Resource (name = "loanOrdService")
	private LoanOrdService loanOrdService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 订单申请
	 * @param ord
	 * @return
	 */
	@Override
	public LoanOrd applyOrdBy(LoanOrd ord){
		if (ord != null){
			logger.info("订单申请请求，订单号：[" + ord.getLoanOrdId() + "]");
			/**
			 *  机构部份
			 */
			LoanStatistics ls = this.statisticsMgrService.query(ord.getIinst(), InstType.INST);
			ls.setCurAcceptCount(ls.getCurAcceptCount() + 1);//当前待受理 + 1
			ls.setLoanApplyCount(ls.getLoanApplyCount() + 1);//申请+1
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(ord.getImerch(), InstType.MERCH);
			ls.setCurAcceptCount(ls.getCurAcceptCount() + 1);//当前待受理 +1
			ls.setLoanApplyCount(ls.getLoanApplyCount() + 1);//申请+1
			this.statisticsMgrService.update(ls);
			
		}
		return null;
	}
	
	/**
	 * 受理请求
	 * @param loanOrd
	 */
	@Override
	public void acceptOrd(LoanOrd loanOrd){
		if (loanOrd != null){
			logger.info("订单受理请求，订单号：[" + loanOrd.getLoanOrdId() + "]");
			/**
			 *   机构部份待定
			 */
			LoanStatistics ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//当前待受理减1
			ls.setCurAuditCount(ls.getCurAuditCount() + 1);//当前待审核订单数加1
			ls.setLoanAcceptCount(ls.getLoanAcceptCount() + 1);//受理+1
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//当前待受理减1
			ls.setCurAuditCount(ls.getCurAuditCount() + 1);//当前待审核订单数加1
			ls.setLoanAcceptCount(ls.getLoanAcceptCount() + 1);//受理+1
			this.statisticsMgrService.update(ls);
			
		}
	}
	/**
	 * 审核订单
	 * @param loanOrd
	 */
	public void auditOrd(LoanOrd loanOrd){
		if (loanOrd != null){
			logger.info("订单审核请求，订单号：[" + loanOrd.getLoanOrdId() + "]");
			/**
			 *   机构部份待定
			 */
			LoanStatistics ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurAuditCount(ls.getCurAuditCount() - 1); //待审核-1
			ls.setCurCreditCount(ls.getCurCreditCount() + 1);//待放款 + 1
			ls.setLoanAuditCount(ls.getLoanAuditCount() + 1);//审核通过+1
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurAuditCount(ls.getCurAuditCount() - 1);//待审核-1
			ls.setCurCreditCount(ls.getCurCreditCount() + 1);//待放款 + 1
			ls.setLoanAuditCount(ls.getLoanAuditCount() + 1);//审核通过+1
			this.statisticsMgrService.update(ls);
		}
	}
	
	/**
	 * 审核放款
	 * @param loanOrd
	 */
	@Override
	public void auditCredit(LoanOrd loanOrd){
		
	}
	/**
	 * 机构产品发布
	 * @param loanPdt
	 * @param instSession
	 * @param ipaddr
	 */
	public void addLoanPdtService(LoanPdt loanPdt){
		if (loanPdt != null){
			logger.info("添加新产品，产品名称：[" + loanPdt.getPdtName() + "]");
			/**
			 *   机构部份待定
			 */
			LoanStatistics ls = this.statisticsMgrService.query(loanPdt.getIinst(), InstType.INST);
			ls.setProdCount(ls.getProdCount() + 1);
			this.statisticsMgrService.update(ls);
			Inst inst = this.instService.findInstById(loanPdt.getIinst());
			if (inst != null){
				Long pdtCount = inst.getPdtCount()==null ? 0 : inst.getPdtCount();
				inst.setPdtCount(pdtCount + 1);
				try {
					instService.updateInst(inst,null,null);
				} catch (Exception e) {
					logger.error("更新机构数据时出错，机构id:[" + inst.getIinst() + "]", e);
				}
			}
		}
	}
	/**
	 * 商户竟投发布
	 * @param bid
	 * @param session
	 * @param ipaddr
	 */
	public void save(DebitBid bid){
		if (bid != null){
			logger.info("添加新竟投，用途：[" + bid.getPurpose() + "]");
			/**
			 *   机构部份待定
			 */
			LoanStatistics ls = this.statisticsMgrService.query(bid.getImerch(), InstType.MERCH);
			ls.setMerchDebidBitCount(ls.getMerchDebidBitCount() + 1);
			this.statisticsMgrService.update(ls);
		}
	}
	/**
	 * 不用
	 */
	@Override
	public void updateLoanOrd(LoanOrd loanOrd) throws Exception {
		//  Auto-generated method stub
		
	}
	/**
	 * 不用
	 */
	@Override
	public LoanOrd findLoanOrdById(Long id) {
		//  Auto-generated method stub
		return null;
	}
	/**
	 * 不用
	 */
	@Override
	public List<LoanOrd> queryLoanOrd() throws Exception {
		//  Auto-generated method stub
		return null;
	}
	/**
	 * 订单过期
	 */
	@Override
	public void expireOrd(LoanOrd loanOrd) throws Exception {
		LoanStatistics ls = null;
		if (this.isExpire(loanOrd)){
			if (loanOrd.getOrdStat() == OrdStat.APPLY){
				/**
				 *   机构部份待定
				 */
				ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
				ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//待审核-1
				this.statisticsMgrService.update(ls);
				
				/**
				 * 商户部份
				 */
				ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
				ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//待审核-1
				this.statisticsMgrService.update(ls);
			}
			if (loanOrd.getOrdStat() == OrdStat.ACCEPT){
				/**
				 *   机构部份待定
				 */
				ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
				ls.setCurAuditCount(ls.getCurAuditCount() - 1);//待审核-1
				this.statisticsMgrService.update(ls);
				
				/**
				 * 商户部份
				 */
				ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
				ls.setCurAuditCount(ls.getCurAuditCount() - 1);//待审核-1
				this.statisticsMgrService.update(ls);
			}
			logger.info("订单已过期，订单号：[" + loanOrd.getLoanOrdId() + "]");
		}
		
	}

	/**
	 * 拒决订单
	 */
	@Override
	public OrdStat refuseOrd(LoanOrd loanOrd) throws Exception {
		LoanStatistics ls = null;
		switch(loanOrd.getOrdStat()){
		case ACCEPT:
			/**
			 *   机构部份待定
			 */
			ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurAuditCount(ls.getCurAuditCount() - 1);//待审核-1
			ls.setRefuseAuditCount(ls.getRefuseAuditCount() + 1);//拒决审核+1
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurAuditCount(ls.getCurAuditCount() - 1);//待审核-1
			ls.setRefuseAuditCount(ls.getRefuseAuditCount() + 1);//拒决审核+1
			this.statisticsMgrService.update(ls);
			break;
		case APPLY:
			/**
			 *  机构部份待定
			 */
			ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//待受理-1
			ls.setRefuseAcceptCount(ls.getRefuseAcceptCount() + 1);//拒决受理+1
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//待受理-1
			ls.setRefuseAcceptCount(ls.getRefuseAcceptCount() + 1);//拒决受理+1
			this.statisticsMgrService.update(ls);
			break;
		default: 
		}
		return null;
	}

	/**
	 * 撤销订单
	 */
	@Override
	public void cancelOrd(LoanOrd loanOrd) throws Exception {
		LoanStatistics ls = null;
		switch (loanOrd.getOrdStat()){
		case APPLY:
			/**
			 *  机构部份待定
			 */
			ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//待受理-1
			ls.setCurCancelCount(ls.getCurCancelCount() + 1);//撤销+1
			ls.setLoanCancelCount(ls.getLoanCancelCount() + 1);//撤销+1
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//待受理-1
			ls.setCurCancelCount(ls.getCurCancelCount() + 1);//撤销+1
			ls.setLoanCancelCount(ls.getLoanCancelCount() + 1);//撤销+1
			this.statisticsMgrService.update(ls);
			break;
		case ACCEPT:
			/**
			 *  机构部份待定
			 */
			ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurAuditCount(ls.getCurAuditCount() - 1);//待审核-1
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurAuditCount(ls.getCurAuditCount() - 1);//待审核-1
			this.statisticsMgrService.update(ls);
			break;
		default: 
		}
		
	}

	/**
	 * 放款
	 */
	@Override
	public void creditOrd(LoanOrd loanOrd) throws Exception {
		if (loanOrd != null){
			logger.info("放款审核请求，订单号：[" + loanOrd.getLoanOrdId() + "]");
			/**
			 *   机构部份待定
			 */
			LoanStatistics ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurCreditCount(ls.getCurCreditCount() - 1);
			ls.setCurRefundingCount(ls.getCurRefundingCount() + 1);
			ls.setCurRefundAmount(ls.getCurRefundAmount().add(loanOrd.getQuota()));
			ls.setLoanAmount(ls.getLoanAmount().add(loanOrd.getQuota()));
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurCreditCount(ls.getCurCreditCount() - 1);
			ls.setCurRefundingCount(ls.getCurRefundingCount() + 1);
			ls.setCurRefundAmount(ls.getCurRefundAmount().add(loanOrd.getQuota()));
			ls.setLoanAmount(ls.getLoanAmount().add(loanOrd.getQuota()));
			this.statisticsMgrService.update(ls);
		}
		
	}

	/**
	 * 还款
	 */
	@Override
	public void refundOrd(LoanOrd loanOrd, LoanOrdPlan plan) throws Exception {
		
	}

	/**
	 * 平台外还款
	 */
	@Override
	public void othRefundOrd(LoanOrd loanOrd, LoanOrdPlan plan,String memo)
			throws Exception {
		this.statisticsSubService.curLoanRefundingCount(InstType.INST, "i_inst");
		this.statisticsSubService.curLoanRefundingCount(InstType.INST, "i_merch");
		
	}

	/**
	 * 平台外放款
	 */
	@Override
	public void othCreditOrd(LoanOrd loanOrd,String memo) throws Exception {
		if (loanOrd != null){
			logger.info("放款审核请求，订单号：[" + loanOrd.getLoanOrdId() + "]");
			/**
			 *   机构部份待定
			 */
			LoanStatistics ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurCreditCount(ls.getCurCreditCount() - 1);
			ls.setCurRefundingCount(ls.getCurRefundingCount() + 1);
			ls.setCurRefundAmount(ls.getCurRefundAmount().add(loanOrd.getQuota()));
			//借款总额
			ls.setLoanAmount(ls.getLoanAmount().add(loanOrd.getQuota()));
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurCreditCount(ls.getCurCreditCount() - 1);
			ls.setCurRefundingCount(ls.getCurRefundingCount() + 1);
			ls.setCurRefundAmount(ls.getCurRefundAmount().add(loanOrd.getQuota()));
			ls.setLoanAmount(ls.getLoanAmount().add(loanOrd.getQuota()));
			this.statisticsMgrService.update(ls);
		}
		
	}

	/**
	 * 撤销放款
	 */
	@Override
	public void cancelCredit(LoanOrd loanOrd) throws Exception {
		if (loanOrd != null){
			logger.info("放款审核请求，订单号：[" + loanOrd.getLoanOrdId() + "]");
			/**
			 *   机构部份待定
			 */
			LoanStatistics ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurCreditCount(ls.getCurCreditCount() + 1);
			ls.setCurRefundingCount(ls.getCurRefundingCount() - 1);
			ls.setCurRefundAmount(ls.getCurRefundAmount().subtract(loanOrd.getQuota()));
			ls.setLoanAmount(ls.getLoanAmount().subtract(loanOrd.getQuota()));
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurCreditCount(ls.getCurCreditCount() + 1);
			ls.setCurRefundingCount(ls.getCurRefundingCount() - 1);
			ls.setCurRefundAmount(ls.getCurRefundAmount().subtract(loanOrd.getQuota()));
			ls.setLoanAmount(ls.getLoanAmount().subtract(loanOrd.getQuota()));
			this.statisticsMgrService.update(ls);
		}
		
	}

	/**
	 * 还款审核
	 */
	@Override
	public void auditRefund(LoanOrd loanOrd) throws Exception {
		this.statisticsSubService.curLoanRefundingCount(InstType.INST, "i_inst");
		this.statisticsSubService.curLoanRefundingCount(InstType.INST, "i_merch");
		
	}

	/**
	 * 撤销还款
	 */
	@Override
	public void cancelRefund(LoanOrd loanOrd, LoanOrdPlan plan)
			throws Exception {
		this.statisticsSubService.curLoanRefundingCount(InstType.INST, "i_inst");
		this.statisticsSubService.curLoanRefundingCount(InstType.INST, "i_merch");
		
	}

	/**
	 * 冻结审核
	 */
	@Override
	public void auditFreeze(LoanOrd ord, List<LoanOrdPlan> plans)
			throws Exception {
		//  Auto-generated method stub
		
	}

	/**
	 * 拒决冻结
	 */
	@Override
	public void refuseFreeze(LoanOrd ord, List<LoanOrdPlan> plans)
			throws Exception {
		//  Auto-generated method stub
		
	}

	/**
	 * 商户撤销订单
	 */
	@Override
	public void merchCancel(LoanOrd loanOrd) throws Exception {
		LoanStatistics ls = null;
		switch (loanOrd.getOrdStat()){
		case APPLY:
			/**
			 *  机构部份待定
			 */
			ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//待受理-1
			ls.setCurCancelCount(ls.getCurCancelCount() + 1);//撤销+1
			ls.setLoanCancelCount(ls.getLoanCancelCount() + 1);//撤销+1
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurAcceptCount(ls.getCurAcceptCount() - 1);//待受理-1
			ls.setCurCancelCount(ls.getCurCancelCount() + 1);//撤销+1
			ls.setLoanCancelCount(ls.getLoanCancelCount() + 1);//撤销+1
			this.statisticsMgrService.update(ls);
			break;
		case ACCEPT:
			/**
			 *  机构部份待定
			 */
			ls = this.statisticsMgrService.query(loanOrd.getIinst(), InstType.INST);
			ls.setCurAuditCount(ls.getCurAuditCount() - 1);//待审核-1
			this.statisticsMgrService.update(ls);
			
			/**
			 * 商户部份
			 */
			ls = this.statisticsMgrService.query(loanOrd.getImerch(), InstType.MERCH);
			ls.setCurAuditCount(ls.getCurAuditCount() - 1);//待审核-1
			this.statisticsMgrService.update(ls);
			break;
		default: 
		}
		
	}

	/**
	 * 不用
	 */
	@Override
	public void uploadPlan(LoanOrd loanOrd, List<LoanOrdPlan> plans)
			throws Exception {
		//  Auto-generated method stub
		
	}

	/**
	 * 不用
	 */
	@Override
	public LoanOrdPlan queryPlanById(Long iLoanOrdPlan) {
		//  Auto-generated method stub
		return null;
	}

	/**
	 * 不用
	 */
	@Override
	public List<LoanOrdPlan> queryPlan(Long iloanOrd) throws Exception {
		//  Auto-generated method stub
		return null;
	}

	/**
	 * 冻结订单
	 */
	@Override
	public FreezeResult freezeOrd(LoanOrd loanOrd, List<LoanOrdPlan> plans)
			throws Exception {
		//  Auto-generated method stub
		return null;
	}

	/**
	 * 解冻
	 */
	@Override
	public FreezeResult thaw(LoanOrd loanOrd, List<LoanOrdPlan> plans)
			throws Exception {
		//  Auto-generated method stub
		return null;
	}

	/**
	 * 拒决解冻
	 */
	@Override
	public FreezeResult refuseThaw(LoanOrd loanOrd, List<LoanOrdPlan> plans)
			throws Exception {
		//  Auto-generated method stub
		return null;
	}

	/**
	 * 解冻审核
	 */
	@Override
	public FreezeResult auditThaw(LoanOrd loanOrd, List<LoanOrdPlan> plans)
			throws Exception {
		//  Auto-generated method stub
		return null;
	}

	/**
	 * 不用
	 */
	@Override
	public boolean isExpire(LoanOrd loanOrd) {
		return this.loanOrdService.isExpire(loanOrd);
	}

	/**
	 * 不用
	 */
	@Override
	public List<LoanOrd> queryLoanOrdByStat(OrdStat stat) {
		//  Auto-generated method stub
		return null;
	}
	/**
	 * 不用
	 */
	@Override
	public LoanOrd queryLoanOrdById(Long iloanOrd) {
		//  Auto-generated method stub
		return null;
	}
	/**
	 * 不用
	 */
	@Override
	public List<LoanOrd> queryByCondition(LoanOrdCondition cond)
			throws Exception {
		//  Auto-generated method stub
		return null;
	}
	/**
	 * 不用
	 */
	@Override
	public List<LoanOrd> queryOrdByCon(LoanOrdCondition loanordcondition,
			Page page) throws Exception {
		//  Auto-generated method stub
		return null;
	}
	/**
	 * 不用
	 */
	@Override
	public LoanOrd queryByDebitbid(Long iDebitbid) {
		//  Auto-generated method stub
		return null;
	}

	/**
	 * 撤销冻结请求
	 */
	@Override
	public FreezeResult cancelFreeze(LoanOrd loanOrd,
			ArrayList<LoanOrdPlan> plans) throws Exception {
		//  Auto-generated method stub
		return null;
	}

	/**
	 * 不用
	 */
	@Override
	public List<LoanOrd> queryByDebit(Long iDebitbid) {
		//  Auto-generated method stub
		return null;
	}

	@Override
	public List<LoanOrd> queryByIinst(Long iInst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoanOrd> queryByImerch(Long iMerch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoanOrd queryByLoanOrdId(String loanOrdId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> queryTenDays(Date d, Date tenD, Long iinst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoanOrdPlan> queryIloanOrd(Date d, Date tenD, Long iinst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoanOrd> queryfreezeLoanOrd(Long iinst) {
		// TODO Auto-generated method stub
		return null;
	}
}
