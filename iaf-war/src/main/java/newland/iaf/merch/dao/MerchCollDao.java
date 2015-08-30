package newland.iaf.merch.dao;

import java.util.List;

import newland.iaf.merch.model.MerchCollInst;
import newland.iaf.merch.model.MerchCollPdt;

public interface MerchCollDao {

	/**
	 * 新增商户收藏机构
	 * 
	 * @param merchCollInst
	 */
	void saveMerchCollInst(MerchCollInst merchCollInst);

	/**
	 * 删除商户收藏机构
	 * 
	 * @param merchCollInst
	 */
	void deleteMerchCollInst(MerchCollInst merchCollInst);
	/**
	 * 根据iMerchCollInst查询商户收藏产品
	 * 
	 * @param iMerchCollInst
	 */
	MerchCollInst getMerchCollInstById(Long iMerchCollInst) ;
	
	/**
	 * 根据商户内部编号查询商户收藏机构
	 */
	List<MerchCollInst> queryMerchCollInstByImerch(Long imerch);
	
	/**
	 * 新增商户收藏产品
	 * 
	 * @param merchCollPdt
	 */
	void saveMerchCollPdt(MerchCollPdt merchCollPdt);

	/**
	 * 删除商户收藏产品
	 * 
	 * @param merchCollPdt
	 */
	void deleteMerchCollPdt(MerchCollPdt merchCollPdt);
	/**
	 * 根据iMerchCollPdt查询商户收藏产品
	 * 
	 * @param iMerchCollPdt
	 */
	MerchCollPdt getMerchCollPdtById(Long iMerchCollPdt) ;
	
	/**
	 * 根据商户内部编号查询商户收藏产品列表
	 */
	List<MerchCollPdt> queryMerchCollPdtByImerch(Long imerch);
	/**
	 * 根据机构号商户号查询
	 */
	MerchCollInst queryByIinstAndImerch(Long iInst,Long imerch);
	/**
	 * 根据产品号 商户号查询
	 */
	MerchCollPdt queryByIloanPdtAndImerch(Long iLoanPdt,Long imerch);
}
