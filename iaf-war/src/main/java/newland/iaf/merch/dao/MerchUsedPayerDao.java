package newland.iaf.merch.dao;

import java.util.List;

import newland.iaf.merch.model.MerchUsedPayer;

/**
 * 商户常用付款人操作DAO
 * @author lindaqun
 *
 */
public interface MerchUsedPayerDao {
	/**
	 * 保存商户常用付款人
	 * @param merchUsedPayer 商户常用付款人
	 * @throws Exception
	 */
	void saveMerchUsedPayer(MerchUsedPayer merchUsedPayer) throws Exception;
	
	/**
	 * 修改商户常用付款人
	 * @param merchUsedPayer 商户常用付款人
	 * @throws Exception
	 */
	void updateMerchUsedPayer(MerchUsedPayer merchUsedPayer) throws Exception;
	
	/**
	 * 根据商户ID查找商户常用付款人
	 * @param imerch 商户ID
	 * @return
	 * @throws Exception
	 */
	List<MerchUsedPayer> listMerchUsedPayerByImerch(Long imerch) throws Exception;
	
	/**
	 * 根据商户常用付款人ID查找商户常用付款人
	 * @param imerchUsedPayer 商户常用付款人ID
	 * @return
	 * @throws Exception
	 */
	MerchUsedPayer findMerchUsedPayerByimerchUsedPayer(Long imerchUsedPayer) throws Exception;
	
	/**
	 * 删除商户常用付款人
	 * @param merchUsedPayer
	 * @throws Exception
	 */
	void deleteMerchUsedPayer(MerchUsedPayer merchUsedPayer) throws Exception;
}
