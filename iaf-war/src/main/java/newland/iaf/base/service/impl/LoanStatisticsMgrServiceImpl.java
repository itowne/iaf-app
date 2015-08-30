package newland.iaf.base.service.impl;

import javax.annotation.Resource;

import newland.iaf.base.dao.LoanStatisticsDao;
import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.service.LoanStatisticsMgrService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service ("loanStatisticsMgrService")
@Transactional
public class LoanStatisticsMgrServiceImpl implements LoanStatisticsMgrService {
	
	@Resource(name = "loanStatisticsDao")
	private LoanStatisticsDao statisticsDao;

	@Override
	public void update(LoanStatistics ls) {
		this.statisticsDao.update(ls);

	}

	@Override
	public  LoanStatistics query(Long iinst, InstType type) {
		LoanStatistics ls = this.statisticsDao.queryBy(iinst, type);
		if (ls == null){
			ls = new LoanStatistics();
			ls.setIinst(iinst);
			ls.setInstType(type);
			this.statisticsDao.save(ls);
		}
		return ls;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public synchronized void backupStatistics() {
		this.statisticsDao.copyToHis();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public synchronized void cleanStatistics() {
		this.statisticsDao.cleanAll();
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public synchronized void cleanCurrent(){
		this.statisticsDao.cleanCurrent();
	}

}
