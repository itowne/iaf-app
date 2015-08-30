package newland.iaf.backstage.schedule.dao;

import newland.iaf.backstage.schedule.model.RunStat;
import newland.iaf.backstage.schedule.model.ScheduleCtrl;
import newland.iaf.backstage.schedule.model.ScheduleCtrlHis;

public interface ScheduleCtrlDao {
	
	void save(ScheduleCtrl ctrl);
	
	void save(ScheduleCtrlHis his);
	
	void update(ScheduleCtrl ctrl);
	
	ScheduleCtrl query(String name, RunStat stat);
	
	ScheduleCtrl query(String name);

}
