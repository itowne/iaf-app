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
@Service("schedule.merchBaseInfoSchedule")
public class MerchBaseInfoSchedule extends ScheduleTaskImpl {
	public MerchBaseInfoSchedule() {
		super("merchBaseInfoSchedule");
	}

	@Resource(name = "com.newland.iaf.merchBaseInfoService")
	private FtpFileService merchBaseInfoService;

	@Override
	protected void runImpl(Date date) throws Throwable {
		logger.info("开始下载商户信息文件: " + IafApplication.getYestoday(new Date()));
		this.merchBaseInfoService.fetch(IafApplication.getYestoday(new Date()));
		logger.info("商户信息文件下载完成: " + IafApplication.getYestoday(new Date()));

	}

}
