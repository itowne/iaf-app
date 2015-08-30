package newland.iaf.base.service;

import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.dict.InstType;

public interface LoanStatisticsMgrService {
	
	void update(LoanStatistics ls);
	
	LoanStatistics query(Long iinst, InstType type);
	
	void backupStatistics();
	
	void cleanStatistics();

	void cleanCurrent();

}
