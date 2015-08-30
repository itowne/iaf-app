package newland.iaf.base.trans.dao;

import java.util.List;

import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.base.trans.model.HcInstallLog;
import newland.iaf.base.trans.model.HcMerchBaseInfo;
import newland.iaf.base.trans.model.HcTrans;
import newland.iaf.base.trans.model.HcTransLog;

/**
 * 
 * @author rabbit
 * 
 */
public interface HcTransDao {
	void saveTransLog(HcTransLog transLog);

	void saveTransLogs(List<HcTransLog> list);

	HcTransLog getTransLogByOrderNo(String orderNo);

	void saveTrans(HcTrans trans);

	void updateTrans(HcTrans trans);

	HcTrans getTrans(HcTrans trans);

	boolean hasTrans(HcTrans trans);

	void saveInstallLog(HcInstallLog installLog);

	void saveInstallLogs(List<HcInstallLog> list);

	boolean hasRecordInstallLog(HcInstallLog il);

	// ---------------

	void saveMerchBaseInfo(HcMerchBaseInfo merchBaseInfo);

	void saveMerchBaseInfo(List<HcMerchBaseInfo> list);

	/**
	 * 
	 * @param inspectLog
	 */
	void saveInspectLog(HcInspectLog inspectLog);

	/**
	 * 
	 * @param list
	 */
	void saveinspectLogs(List<HcInspectLog> list);

	/**
	 * 
	 * @param ht
	 * @return
	 */
	boolean hasRecordHcInspectLog(HcInspectLog ht);

	<T> T queryBy(CriteriaExecutor<T> executor, Class<?> clazz);

}
