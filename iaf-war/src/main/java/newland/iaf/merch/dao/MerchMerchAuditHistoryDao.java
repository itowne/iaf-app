package newland.iaf.merch.dao;

import newland.iaf.merch.model.MerchAuditHistory;

public interface MerchMerchAuditHistoryDao {

	/**
	 * 新增商户审核历史记录
	 * 
	 * @param merchAuditHistory
	 */
	void saveMerchAuditHistory(MerchAuditHistory merchAuditHistory);

	/**
	 * 更改商户审核历史记录
	 * 
	 * @param merchAuditHistory
	 */
	void updateMerchAuditHistory(MerchAuditHistory merchAuditHistory);
}
