package newland.iaf.backstage.schedule.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.backstage.schedule.ScheduleTaskImpl;
import newland.iaf.base.risk.dao.RiskControlService;

import org.springframework.stereotype.Service;

/**
 * 
 * @author rabbit
 * 
 */
@Service("schedule.transLogMouth")
public class TransLogMouthSchedule extends ScheduleTaskImpl {

	public TransLogMouthSchedule() {
		super("schedule.transLogMouth");
	}

	@Resource(name = "com.newland.riskControlService")
	private RiskControlService riskControlService;

	@Override
	protected void runImpl(Date date) throws Throwable {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("开始计算汇卡交易流水月汇总信息: " + df.format(date));
		try {
			this.riskControlService.genTransLogMouth(date);
			logger.info("计算汇卡交易流水月汇总信息结束: ");
		} catch (Throwable t) {
			logger.error("计算汇卡交易流水月汇总信息失败: ", t);
		}
	}
}
