package newland.iaf.backstage.schedule.impl;

import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.backstage.schedule.ScheduleTaskImpl;
import newland.iaf.base.service.LoanStatisticsMgrService;
import newland.iaf.base.service.LoanStatisticsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service("schedule.loanCurrentStatisticsSchedule")
public class LoanCurrentStatisticsSchedule extends ScheduleTaskImpl {
	
	public LoanCurrentStatisticsSchedule() {
		super("loanCurrentStatisticsSchedule");
	}

	@Resource (name = "loanStatisticsService")
	private LoanStatisticsService loanStatisticsService;
	@Resource (name = "loanStatisticsMgrService")
	private LoanStatisticsMgrService mgrService;
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void runImpl(Date date) throws Throwable {
		logger.info("开始统计当前贷款数据：" + new Date());
		this.mgrService.cleanCurrent();
		this.loanStatisticsService.statisticsCurrent();
		logger.info("当前贷款数据统计结束：" + new Date());
	}

}
