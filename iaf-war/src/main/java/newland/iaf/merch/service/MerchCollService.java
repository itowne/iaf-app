package newland.iaf.merch.service;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.condition.InstCondition;
import newland.iaf.merch.model.MerchCollInst;
import newland.iaf.merch.model.MerchCollPdt;

public interface MerchCollService {

	/**
	 * 添加商户收藏机构
	 * 
	 * @param merchCollInst
	 */
	void addMerchCollInst(MerchCollInst merchCollInst) throws Exception;

	/**
	 * 删除商户收藏机构
	 * 
	 * @param merchCollInst
	 */
	void deleteMerchCollInst(Long iMerchCollInst) throws Exception;
	/**
	 * 根据多条件查询商户收藏机构
	 */
	List<Inst> queryMerchCollInstByCon(String acceptRegion,Long imerch,InstCondition instCondition,Page page) throws Exception;
	
	/**
	 * 新增商户收藏产品
	 * 
	 * @param merchCollPdt
	 */
	void addMerchCollPdt(MerchCollPdt merchCollPdt) throws Exception;

	/**
	 * 删除商户收藏产品
	 * 
	 * @param merchCollPdt
	 */
	void deleteMerchCollPdt(Long iMerchCollPdt) throws Exception;
	
	/**
	 * 根据多条件查询商户收藏产品列表
	 */
	List<LoanPdt> queryMerchCollPdtByCon(Long imerch,LoanPdtCondition loanPdtCondition,Page page) throws Exception;
	
	/**
	 * 根据机构号 商户号查询
	 */
	MerchCollInst queryByIinstAndImerch(Long iInst,Long imerch)throws Exception;
	/**
	 * 根据产品号 商户号查询
	 */
	MerchCollPdt queryByIloanPdtAndImerch(Long iLoanPdt,Long imerch)throws Exception;
}
