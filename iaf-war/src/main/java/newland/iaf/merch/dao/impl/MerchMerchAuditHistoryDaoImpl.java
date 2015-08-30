package newland.iaf.merch.dao.impl;

import org.springframework.stereotype.Repository;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.merch.dao.MerchMerchAuditHistoryDao;
import newland.iaf.merch.model.MerchAuditHistory;

@Repository("merchMerchAuditHistoryDao")
public class MerchMerchAuditHistoryDaoImpl extends BaseDao implements MerchMerchAuditHistoryDao {

	@Override
	public void saveMerchAuditHistory(MerchAuditHistory merchAuditHistory) {
		this.getSession().save(merchAuditHistory);
	}

	@Override
	public void updateMerchAuditHistory(MerchAuditHistory merchAuditHistory) {
		this.getSession().update(merchAuditHistory);
	}

}
