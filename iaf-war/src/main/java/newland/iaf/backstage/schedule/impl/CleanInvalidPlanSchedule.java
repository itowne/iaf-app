package newland.iaf.backstage.schedule.impl;

import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.backstage.schedule.ScheduleTaskImpl;
import newland.iaf.base.dao.LoanOrdDao;
import newland.iaf.base.dao.LoanOrdPlanDao;
import newland.iaf.base.model.condition.LoanOrdCondition;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("schedule.cleanInvalidPlanSchedule")
@Transactional
public class CleanInvalidPlanSchedule extends ScheduleTaskImpl {
	
	@Resource (name = "loanOrdPlanDao")
	private LoanOrdPlanDao loanOrdPlanDao;

	public CleanInvalidPlanSchedule() {
		super("cleanInvalidPlanSchedule");
	}

	@Override
	protected void runImpl(Date date) throws Throwable {
		loanOrdPlanDao.cleanInvalidPlan();
	}

}
