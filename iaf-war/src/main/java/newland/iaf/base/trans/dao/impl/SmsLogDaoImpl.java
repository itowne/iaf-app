package newland.iaf.base.trans.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.trans.dao.SmsLogDao;
import newland.iaf.base.trans.model.SmsLog;

import org.springframework.stereotype.Service;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.smsLogDao")
public class SmsLogDaoImpl extends BaseDao implements SmsLogDao {

	@Override
	public void save(SmsLog smsLog) {
		this.getSession().save(smsLog);
	}

	@Override
	public void update(SmsLog smsLog) {
		this.getSession().save(smsLog);
	}

	@Override
	public List<SmsLog> find() {
		return this.getHibernateDao().find(SmsLog.class);
	}

}
