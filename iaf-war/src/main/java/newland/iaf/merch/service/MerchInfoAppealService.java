/**
 * 
 */
package newland.iaf.merch.service;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.condition.MerchAppealContidition;
import newland.iaf.merch.model.MerchInfoAppeal;

/**
 * @author Mr.Towne
 *
 */
public interface MerchInfoAppealService {
	/**
	 * 添加申诉
	 * @param merchInfoAppeal
	 */
	void saveMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal);
	
	void updateMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal);
	
	List<MerchInfoAppeal> query(MerchAppealContidition mac,Page page);
	
	List<MerchInfoAppeal> findAppealByMerchNo(String merchNO);
}
