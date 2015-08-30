package newland.iaf.backstage.schedule.impl;

import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.IafApplication;
import newland.iaf.backstage.schedule.ScheduleTaskImpl;
import newland.iaf.base.trans.service.FtpFileService;

import org.springframework.stereotype.Service;

/**
 * 
 * @author rabbit
 * 
 */
@Service("schedule.transLogSchedule")
public class TransLogSchedule extends ScheduleTaskImpl {

	public TransLogSchedule() {
		super("transLogSchedule");
	}

	@Resource(name = "com.newland.iaf.transLogService")
	private FtpFileService transLogService;

	@Override
	protected void runImpl(Date date) throws Throwable {
		logger.info("开始下载交易记录文件: " + IafApplication.getYestoday(new Date()));
		this.transLogService.fetch(IafApplication.getYestoday(new Date()));
		logger.info("交易记录文件下载完成: " + IafApplication.getYestoday(new Date()));

	}

}
