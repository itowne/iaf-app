package newland.iaf.backstage.schedule.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import newland.iaf.IafApplication;
import newland.iaf.backstage.schedule.ScheduleTaskImpl;
import newland.iaf.base.trans.service.FtpFileService;
@Service("schedule.inspectLogSchedule")
public class InspectLogSchedule extends ScheduleTaskImpl {
	
	@Resource (name = "com.newland.iaf.inspectLogService")
	private FtpFileService inspectLogService;
	
	public InspectLogSchedule(){
		super("inspectLogSchedule");
	}

	@Override
	protected void runImpl(Date date) throws Throwable {
		logger.info("开始下载巡检记录文件: " + IafApplication.getYestoday(new Date()));
		this.inspectLogService.fetch(IafApplication.getYestoday(new Date()));
		logger.info("巡检记录文件下载完成: " + IafApplication.getYestoday(new Date()));
	}

}
