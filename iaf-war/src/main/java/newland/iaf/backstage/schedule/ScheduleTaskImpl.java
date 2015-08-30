package newland.iaf.backstage.schedule;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.backstage.schedule.dao.ScheduleCtrlDao;
import newland.iaf.backstage.schedule.model.RunStat;
import newland.iaf.backstage.schedule.model.ScheduleCtrl;
import newland.iaf.backstage.schedule.model.ScheduleCtrlHis;
import newland.iaf.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class ScheduleTaskImpl implements ScheduleTask {
	@Resource (name = "schedule.ctrlDao")
	private ScheduleCtrlDao ctrlDao;
	
	private String scheduleName;
	
	public ScheduleTaskImpl(String scheduleName){
		this.scheduleName = scheduleName;
	}
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public synchronized void run() throws Exception {
		ScheduleCtrl ctrl = this.ctrlDao.query(scheduleName);
		if (ctrl == null){
			ctrl = new ScheduleCtrl();
			ctrl.setScheduleName(scheduleName);
			this.ctrlDao.save(ctrl);
			saveHis(ctrl);
		}
		Date date = ctrl.getRunTime();
		if (date == null){
			date = new Date();
		}
		Date runDate = date;
		if (ctrl.getRunStat() != null &&
				ctrl.getRunStat() == RunStat.FAIL)
			runDate = DateUtils.rollDate(date, Calendar.YEAR, -1);
		runDate = DateUtils.roundDate(date, Calendar.DATE);
		try{
			logger.info(this.scheduleName + "定时服务开始运行....");
			ctrl.setRunTime(new Date());
			ctrl.setRunStat(RunStat.RUNNING);
			this.ctrlDao.update(ctrl);
			saveHis(ctrl);
			this.runImpl(runDate);
		}catch(Throwable e){
			logger.error(this.scheduleName + " 定时器运行错误", e);
			ctrl.setEndTime(new Date());
			ctrl.setRunTime(date);
			ctrl.setRunStat(RunStat.FAIL);
			ctrl.setErrMsg(e.getMessage());
			this.ctrlDao.update(ctrl);
			this.saveHis(ctrl);
			return;
		}
		ctrl.setEndTime(new Date());
		ctrl.setRunStat(RunStat.SUCCESS);
		this.ctrlDao.update(ctrl);
		this.saveHis(ctrl);
		logger.info(this.scheduleName + "定时服务运行结束....");
	}

	private void saveHis(ScheduleCtrl ctrl) {
		ScheduleCtrlHis his = new ScheduleCtrlHis();
		his.setEndTime(ctrl.getEndTime());
		his.setErrMsg(ctrl.getErrMsg());
		his.setIpaddr(ctrl.getIpaddr());
		his.setRunStat(ctrl.getRunStat());
		his.setRunTime(ctrl.getRunTime());
		his.setScheduleName(ctrl.getScheduleName());
		his.setGenTime(new Date());
		this.ctrlDao.save(his);
	}
	
	protected abstract void runImpl(Date date) throws Throwable;

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

}
