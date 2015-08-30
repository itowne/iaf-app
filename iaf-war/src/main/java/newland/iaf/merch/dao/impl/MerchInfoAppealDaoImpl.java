/**
 * 
 */
package newland.iaf.merch.dao.impl;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.condition.MerchAppealContidition;
import newland.iaf.merch.dao.MerchInfoAppealDao;
import newland.iaf.merch.model.MerchInfoAppeal;

/**
 * @author Mr.Towne
 *
 */
@Repository("merchInfoAppealDao")
public class MerchInfoAppealDaoImpl extends BaseDao implements MerchInfoAppealDao {

	@Override
	public void saveMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal) {
		this.getSession().save(merchInfoAppeal);
	}

	@Override
	public void updateMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal){
		this.getSession().update(merchInfoAppeal);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MerchInfoAppeal> query(MerchAppealContidition merchAppealContidition,
			Page page) {
		List<MerchInfoAppeal> merchInfoAppealList =  this.getHibernateDao().findByCriteriaEx(merchAppealContidition, page, true);
		return merchInfoAppealList;
	}

	@Override
	public List<MerchInfoAppeal> queryByMerchNo(String merchNO){
		MerchInfoAppeal mia = new MerchInfoAppeal();
		mia.setMerchNo(merchNO);
		return this.getHibernateDao().findByExample(mia);
	}
}
