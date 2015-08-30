package newland.iaf.backstage.schedule.dao.impl;

import java.util.List;

import newland.iaf.backstage.schedule.dao.ScheduleCtrlDao;
import newland.iaf.backstage.schedule.model.RunStat;
import newland.iaf.backstage.schedule.model.ScheduleCtrl;
import newland.iaf.backstage.schedule.model.ScheduleCtrlHis;
import newland.iaf.base.dao.BaseDao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
@Service ("schedule.ctrlDao")
@Transactional
public class ScheduleCtrlDaoImpl extends BaseDao implements ScheduleCtrlDao {

	@Override
	public void save(ScheduleCtrl ctrl) {
		this.getSession().save(ctrl);

	}

	@Override
	public void update(ScheduleCtrl ctrl) {
		this.getSession().update(ctrl);

	}

	@Override
	public ScheduleCtrl query(String name, RunStat stat) {
		ScheduleCtrl ctrl = new ScheduleCtrl();
		ctrl.setScheduleName(name);
		ctrl.setRunStat(stat);
		List<ScheduleCtrl> ctrls = this.getHibernateDao().findByExample(ctrl);
		if (CollectionUtils.isEmpty(ctrls)) return null;
		return ctrls.get(0);
	}

	@Override
	public ScheduleCtrl query(String name) {
		ScheduleCtrl ctrl = new ScheduleCtrl();
		ctrl.setScheduleName(name);
		List<ScheduleCtrl> ctrls = this.getHibernateDao().findByExample(ctrl);
		if (CollectionUtils.isEmpty(ctrls)) return null;
		return ctrls.get(0);
	}

	@Override
	public void save(ScheduleCtrlHis his) {
		this.getSession().save(his);
		
	}

}
