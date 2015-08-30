package newland.iaf.base.trans.dao;

import java.util.List;

import newland.iaf.base.trans.model.HcInspectLog;

/**
 * 
 * @author rabbit
 * 
 */
public interface InspectLogDao {

	/**
	 * 
	 * @param inspectLog
	 */
	void save(HcInspectLog inspectLog);

	/**
	 * 
	 * @param list
	 */
	void save(List<HcInspectLog> list);
}
