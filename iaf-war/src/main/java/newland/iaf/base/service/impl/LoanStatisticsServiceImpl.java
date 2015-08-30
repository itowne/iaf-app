package newland.iaf.base.service.impl;

import java.math.BigInteger;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.LoanStatisticsMgrService;
import newland.iaf.base.service.LoanStatisticsService;
import newland.iaf.base.service.LoanStatisticsSubService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("loanStatisticsService")
@Transactional
public class LoanStatisticsServiceImpl implements LoanStatisticsService {
	
	@Resource (name = "loanStatisticsSubService")
	private LoanStatisticsSubService subService;
	@Resource (name = "loanStatisticsMgrService")
	private LoanStatisticsMgrService mgrService;
	@Resource (name = "loanPdtService")
	private LoanPdtService loanPdtService;
	@Resource (name = "debitBidService")
	private DebitBidService debitBidService;

	@Override
	public void statisticsLoanOrd() {
		this.countLoanByInstType(InstType.INST);
		this.countLoanByInstType(InstType.MERCH);
	}
	@Override
	public final void update(LoanStatistics ls){
		this.mgrService.update(ls);
	}
	
	@Override
	public void statisticsCurrent(){
		this.countCurrent(InstType.INST);
		this.countCurrent(InstType.MERCH);
	}
	
	private void countCurrent(final InstType type){
		final String prop = (type == InstType.INST)?"iinst":"imerch";
		/**
		 * 当前还款中订单数 
		 */
		subService.curLoanRefundingCount(type, prop);
		/**
		 * 当前待受理订单数
		 */
		subService.curLoanAcceptCount(type, prop);
		/**
		 * 当前撤销订单数
		 */
		subService.curLoanCancelCount(type, prop);
		/**
		 * 当前待审核订单数
		 */
		subService.curLoanAuditCount(type, prop);
		/**
		 * 当前待放款订单数
		 */
		subService.curLoanCreditCount(type, prop);
		/**
		 * 待放款总额
		 */
		subService.curCreditAmount(type, prop);
		/**
		 * 待还款总额
		 */
		subService.curRefundAmount(type, prop);
		
		/**
		 * 当期应还款总额
		 */
		subService.curPeriodRefund(type, prop);
	}
		

	private void countLoanByInstType(final InstType type) {
		countCurrent(type);
		
		final String prop = (type == InstType.INST)?"iinst":"imerch";
		/**
		 * 订单总数
		 */
		subService.loanCount(type, prop);
		/**
		 * 申请订单数 - 包含所有订单
		 */
		subService.loanApplyCount(type, prop);
		
		/**
		 * 受理订单数  - 包含所有已受理
		 */
		subService.loanAcceptCount(type, prop);
		/**
		 * 撤销订单数  - 包含所有撤销订单
		 */
		subService.loanCancelCount(type, prop);
		/**
		 * 审核通过订单数 - 包括所有已审核
		 */
		subService.loanAuditCount(type, prop);
		
		/**
		 * 拒决受理订单数
		 */
		subService.loanRefuseAcceptCount(type, prop);
		/**
		 * 拒决审核订单数
		 */
		subService.loanRefuseAuditCount(type, prop);
		/**
		 * 贷款总额
		 */
		subService.loanAmount(type, prop);
		/**
		 * 已还款总额
		 */
		subService.loanRefundAmount(type, prop);
		/**
		 * 当前欠款总额
		 */
		subService.debtAmount(type, prop);
		/**
		 * 当前逾期总额
		 */
		subService.overdueAmount(type, prop);
		/**
		 * 当前冻结总额
		 */
		subService.freezeAmount(type, prop);
	}

	

	@Override
	public final LoanStatistics query(Long iinst, InstType type){
		return mgrService.query(iinst, type);
	}
	
	public void init(){
	
	}

	

	@Override
	public void statisticsProd() {
		this.subService.statisticsProd();
	}

	@Override
	public void statisticsDebitBid() {
		this.subService.statisticsDebitBid();

	}
	@Override
	@Cacheable(value = "iafcache", key = "'loanPdtCount'")
	public BigInteger getLoanPdtCount() {
		return this.loanPdtService.countGroundingPdt();
	}
	
	@Override
	@Cacheable(value = "iafcache", key = "'debigBidCount'")
	public BigInteger getDebitBidCount() {
		return this.debitBidService.countDebitBid();
	}
	@Override
	public void statisticsSuccessRate() throws Exception {
		this.subService.statisticsInstSuccess();
	}

}
