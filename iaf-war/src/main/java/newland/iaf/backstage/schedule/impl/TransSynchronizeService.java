package newland.iaf.backstage.schedule.impl;

import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.backstage.schedule.ScheduleTaskImpl;
import newland.iaf.base.service.SettleService;

import org.springframework.stereotype.Service;

@Service("schedule.transSynchronizeService")
public class TransSynchronizeService extends ScheduleTaskImpl {
	
	@Resource (name = "settleService")
	private SettleService settleService;

	public TransSynchronizeService() {
		super("transSynchronizeService");
	}

	@Override
	protected void runImpl(Date date) throws Throwable {
		//logger.info("开始同步清算数据！");
		//this.settleService.synchronize(date);
		//logger.info("同步结束");
	}

}
