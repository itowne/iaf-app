package newland.iaf.base.trans.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.trans.dao.HcTransDao;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.base.trans.model.HcInstallLog;
import newland.iaf.base.trans.model.HcMerchBaseInfo;
import newland.iaf.base.trans.model.HcTrans;
import newland.iaf.base.trans.model.HcTransLog;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.hcTransDao")
public class HcTransDaoImpl extends BaseDao implements HcTransDao {

	@Override
	public void saveTransLog(HcTransLog transLog) {
		this.getSession().save(transLog);
	}

	@Override
	public void saveTransLogs(List<HcTransLog> list) {
		this.getHibernateDao().saveList(list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveTrans(HcTrans trans) {
		this.getSession().save(trans);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateTrans(HcTrans trans) {
		this.getSession().update(trans);
	}

	@Override
	public void saveInstallLog(HcInstallLog installLog) {
		this.getSession().save(installLog);
	}

	@Override
	public void saveInstallLogs(List<HcInstallLog> list) {
		this.getHibernateDao().saveList(list);
	}

	@Override
	public void saveMerchBaseInfo(HcMerchBaseInfo merchBaseInfo) {
		this.getSession().save(merchBaseInfo);
	}

	@Override
	public void saveMerchBaseInfo(List<HcMerchBaseInfo> list) {
		this.getHibernateDao().saveList(list);
	}

	@Override
	public void saveInspectLog(HcInspectLog inspectLog) {
		this.getSession().save(inspectLog);
	}

	@Override
	public void saveinspectLogs(List<HcInspectLog> list) {
		this.getHibernateDao().saveList(list);
	}

	@Override
	public HcTransLog getTransLogByOrderNo(String orderNo) {
		HcTransLog htl = new HcTransLog();
		htl.setOrderNo(orderNo);
		return this.getHibernateDao().getFirstOneByExample(htl);
	}

	@Override
	public <T> T queryBy(CriteriaExecutor<T> executor, Class<?> clazz) {
		Criteria expr = this.getSession().createCriteria(clazz);
		return executor.execute(expr);
	}

	@Override
	public HcTrans getTrans(HcTrans trans) {
		return this.getHibernateDao().getFirstOneByExample(trans);

	}

	@Override
	public boolean hasRecordHcInspectLog(HcInspectLog hil) {
		return this.getHibernateDao().hasRecordByExample(hil);
	}

	@Override
	public boolean hasRecordInstallLog(HcInstallLog il) {
		return this.getHibernateDao().hasRecordByExample(il);
	}

	@Override
	public boolean hasTrans(HcTrans trans) {
		return this.getHibernateDao().hasRecordByExample(trans);
	}
}
