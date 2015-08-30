package newland.iaf.merch.service;

import java.util.List;

import newland.iaf.merch.model.MerchUsedPayer;

public interface MerchUsedPayerService {
	
	/**
	 * 保存商户常用付款人
	 * @param merchUsedPayer 商户常用付款人
	 * @throws Exception
	 */
	void saveMerchUsedPayer(MerchUsedPayer merchUsedPayer) throws Exception;
	
	/**
	 * 根据商户ID查找商户常用付款人
	 * @param imerch 商户ID
	 * @return
	 * @throws Exception
	 */
	List<MerchUsedPayer> listMerchUsedPayerByImerch(Long imerch) throws Exception;
}
