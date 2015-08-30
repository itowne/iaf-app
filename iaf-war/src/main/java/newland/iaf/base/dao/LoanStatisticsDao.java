package newland.iaf.base.dao;

import java.util.List;

import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.LoanStatisticsHis;
import newland.iaf.base.model.dict.InstType;

public interface LoanStatisticsDao {
	
	void save(LoanStatistics ls);
	
	void update (LoanStatistics is);
	
	LoanStatistics queryBy(Long iinst, InstType type);
	
	void copyToHis();

	void cleanAll();

	void cleanCurrent();
	
	List<LoanStatisticsHis> queryBy(CriteriaExecutor<List<LoanStatisticsHis>> executor);

}
