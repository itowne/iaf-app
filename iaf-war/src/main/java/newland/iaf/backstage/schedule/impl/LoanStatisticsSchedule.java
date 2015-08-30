package newland.iaf.backstage.schedule.impl;

import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.backstage.schedule.ScheduleTaskImpl;
import newland.iaf.base.service.LoanStatisticsMgrService;
import newland.iaf.base.service.LoanStatisticsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service("schedule.loanStatisticsSchedule")
public class LoanStatisticsSchedule extends ScheduleTaskImpl {
	
	@Resource (name = "loanStatisticsService")
	private LoanStatisticsService loanStatisticsService;
	@Resource (name = "loanStatisticsMgrService")
	private LoanStatisticsMgrService mgrService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public LoanStatisticsSchedule(){
		super("loanStatisticsSchedule");
	}

	@Override
	protected void runImpl(Date date) throws Throwable {
		logger.info("备份统计结果：" + new Date());
		this.mgrService.backupStatistics();
		logger.info("备份统计结果结束：" + new Date());
		this.mgrService.cleanStatistics();
		logger.info("开始统计订单信息" + new Date());
		this.loanStatisticsService.statisticsLoanOrd();
		logger.info("订单信息统计结束:" + new Date());
		logger.info("开始统计产品信息：" + new Date());
		this.loanStatisticsService.statisticsProd();
		logger.info("产品信息统计结束：" + new Date());
		logger.info("开始统计竟投产品：" + new Date());
		this.loanStatisticsService.statisticsDebitBid();
		logger.info("竟投产品统计结束：" + new Date());
		logger.info("开始统计贷款申请成功率：" + new Date());
		this.loanStatisticsService.statisticsSuccessRate();
		logger.info("贷款申请成功率统计结束：" + new Date());

	}

}
