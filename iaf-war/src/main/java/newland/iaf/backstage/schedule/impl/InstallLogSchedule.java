package newland.iaf.backstage.schedule.impl;

import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.IafApplication;
import newland.iaf.backstage.schedule.ScheduleTaskImpl;
import newland.iaf.base.trans.service.FtpFileService;

import org.springframework.stereotype.Service;

@Service("schedule.installLogSchedule")
public class InstallLogSchedule extends ScheduleTaskImpl {
	
	public InstallLogSchedule() {
		super("installLogSchedule");
	}

	@Resource (name = "com.newland.iaf.installLogService")
	private FtpFileService installLogService;

	@Override
	protected void runImpl(Date date) throws Throwable {
		logger.info("开始下载安装记录文件: " + IafApplication.getYestoday(new Date()));
		this.installLogService.fetch(IafApplication.getYestoday(new Date()));
		logger.info("安装巡检记录文件下载完成: " + IafApplication.getYestoday(new Date()));

	}

}
