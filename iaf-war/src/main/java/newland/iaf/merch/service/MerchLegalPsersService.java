package newland.iaf.merch.service;

import newland.iaf.merch.model.MerchLegalPers;

public interface MerchLegalPsersService {

	/**
	 * 新增商户法人
	 * 
	 * @param merchLegalPers
	 */
	void saveMerchLegalPers(MerchLegalPers merchLegalPers);

	/**
	 * 更改商户法人
	 * 
	 * @param merchLegalPers
	 */
	void updateMerchLegalPers(MerchLegalPers merchLegalPers);
}
