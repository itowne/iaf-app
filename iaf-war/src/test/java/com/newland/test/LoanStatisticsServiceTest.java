package com.newland.test;

import javax.annotation.Resource;

import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.service.LoanStatisticsMgrService;
import newland.iaf.base.service.LoanStatisticsService;
import newland.iaf.base.service.LoanStatisticsSubService;

import org.junit.Test;

import com.newland.BeanTest;

public class LoanStatisticsServiceTest extends BeanTest {
	
	@Resource (name = "loanStatisticsService")
	private LoanStatisticsService loanStatisticsService;
	@Resource (name = "loanStatisticsSubService")
	private LoanStatisticsSubService subService;
	@Resource (name = "loanStatisticsMgrService")
	private LoanStatisticsMgrService mgrService;
	//@Test
	public void test(){
		this.loanStatisticsService.statisticsLoanOrd();
		this.loanStatisticsService.statisticsDebitBid();
		this.loanStatisticsService.statisticsProd();
		this.subService.curPeriodRefund(InstType.INST, "iinst");
		this.mgrService.backupStatistics();
	}
	//@Test
	public void test1(){
		this.subService.curPeriodRefund(InstType.INST, "iinst");
		this.mgrService.backupStatistics();
	}
	@Test
	public void test2(){
		this.loanStatisticsService.getDebitBidCount();
		this.loanStatisticsService.getDebitBidCount();
		this.loanStatisticsService.getLoanPdtCount();
		this.loanStatisticsService.getLoanPdtCount();
	}

}
