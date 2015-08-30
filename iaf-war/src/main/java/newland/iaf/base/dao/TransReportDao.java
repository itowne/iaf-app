package newland.iaf.base.dao;

import java.util.List;

import newland.iaf.base.model.TransReport;
import newland.iaf.base.model.condition.TransReportCondition;

public interface TransReportDao {
	
	void save(TransReport report);
	
	void update(TransReport report);
	
	List<TransReport> query(TransReportCondition cond);

}
