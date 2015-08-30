package newland.iaf.backstage.schedule.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.backstage.schedule.ScheduleTaskImpl;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanOrdTrackService;
import newland.iaf.base.service.impl.LoanOrdTrackServiceImpl;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.StaleObjectStateException;
import org.ohuyo.commons.query.criterion.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("schedule.loanOrdTrackSchedule")
public class LoanOrdTraceSchedule extends ScheduleTaskImpl {
	@Resource(name = "loanOrdTrackService")
	private LoanOrdTrackService loanOrdTrackService;
	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;
	private Logger logger = LoggerFactory.getLogger(LoanOrdTraceSchedule.class);

	public LoanOrdTraceSchedule() {
		super("loarOrdSchedule");
	}

	@Override
	protected void runImpl(Date date) throws Throwable {
		Page page = new Page();
		page.setCapacity(100);// 设置每页显示数
		page.setPageOffset(0);
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setLeStat(OrdStat.PAID_UP_LOAN);
		for (;;) {
			List<LoanOrd> list = this.loanOrdService.queryOrdByCon(cond, page);
			if (CollectionUtils.isNotEmpty(list)) {
				for (LoanOrd ord : list) {
					try {
						try {
							this.loanOrdTrackService.trackLoanOrdExpire(ord);
						} catch (StaleObjectStateException e) {
							ord = this.loanOrdService.queryLoanOrdById(ord
									.getIloanOrd());
							this.loanOrdTrackService.trackLoanOrdExpire(ord);
						}
						try {
							this.loanOrdTrackService.trackOrdAndPlanStat(ord);
						} catch (StaleObjectStateException e) {
							ord = this.loanOrdService.queryLoanOrdById(ord
									.getIloanOrd());
							this.loanOrdTrackService.trackOrdAndPlanStat(ord);
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			} else {
				break;
			}
			page.setPageOffset(page.getPageOffset() + 1);
		}
	}
}
