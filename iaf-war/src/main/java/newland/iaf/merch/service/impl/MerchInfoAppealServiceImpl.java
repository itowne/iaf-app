/**
 * 
 */
package newland.iaf.merch.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import newland.iaf.base.model.condition.MerchAppealContidition;
import newland.iaf.merch.dao.MerchInfoAppealDao;
import newland.iaf.merch.model.MerchInfoAppeal;
import newland.iaf.merch.service.MerchInfoAppealService;

/**
 * @author Mr.Towne
 *
 */
@Service("merchInfoAppealService")
@Transactional
public class MerchInfoAppealServiceImpl implements MerchInfoAppealService {
	
	@Resource(name="merchInfoAppealDao")
	private MerchInfoAppealDao merchInfoAppealDao;
    
	public void saveMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal) {
		merchInfoAppealDao.saveMerchInfoAppeal(merchInfoAppeal);
	}

	@Override
	public void updateMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal){
		this.merchInfoAppealDao.updateMerchInfoAppeal(merchInfoAppeal);
	}
	
	@Override
	public List<MerchInfoAppeal> query(MerchAppealContidition mac,Page page) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iMerchInfoAppeal"));
		mac.setOrders(orderList);
		List<MerchInfoAppeal>  macList=merchInfoAppealDao.query(mac, page);
		return macList;
	}
	
	@Override
	public List<MerchInfoAppeal> findAppealByMerchNo(String merchNO){
		return this.merchInfoAppealDao.queryByMerchNo(merchNO);
		
	}

}
