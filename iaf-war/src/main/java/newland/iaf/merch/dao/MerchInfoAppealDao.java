/**
 * 
 */
package newland.iaf.merch.dao;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.condition.MerchAppealContidition;
import newland.iaf.merch.model.MerchInfoAppeal;

/**
 * @author Mr.Towne
 *
 */
public interface MerchInfoAppealDao {
	
	/**
	 * 添加申诉
	 * @param merchInfoAppeal
	 */
	void saveMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal);
	
	void updateMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal);
	
	List<MerchInfoAppeal> query(MerchAppealContidition merchAppealContidition, Page page);
	
	List<MerchInfoAppeal> queryByMerchNo(String merchNO);
}
