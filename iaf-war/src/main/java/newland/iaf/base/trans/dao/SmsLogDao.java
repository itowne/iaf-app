package newland.iaf.base.trans.dao;

import java.util.List;

import newland.iaf.base.trans.model.SmsLog;

/**
 * 
 * @author rabbit
 * 
 */
public interface SmsLogDao {

	void save(SmsLog smsLog);

	void update(SmsLog smsLog);

	List<SmsLog> find();

}
