package newland.iaf.base.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.dao.DebitBidDao;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.PdtOperLog;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.OperLogService;
import newland.iaf.notice.annotation.MethodTrigger;
import newland.iaf.utils.service.SerialNoService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("debitBidService")
@Transactional
public class DebitBidServiceImpl implements DebitBidService {

	@Resource(name = "debitBidDao")
	private DebitBidDao debitBidDao;
	@Resource(name = "serialNoService")
	private SerialNoService serialNoService;

	@Resource(name = "operLogService")
	OperLogService operLogService;

	@Override
	public DebitBid getDebitBidById(Long id) throws Exception {
		return this.debitBidDao.getDebitBidById(id);
	}

	@Override
	@Transactional
	public List<DebitBid> queryDebitBidByCon(
			DebitBidCondition debitBidCondition, Page page) throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("idebitBid"));
		debitBidCondition.setOrders(orderList);
		List<DebitBid> debitBidList = this.debitBidDao.queryDebitBidByCon(
				debitBidCondition, page);
		return debitBidList;
	}

	@Override
	@Transactional
	@MethodTrigger(stratgy="curStatistics")
	@CacheEvict(value = "iafcache", key = "'debigBidCount'")
	public void save(DebitBid bid)
			throws Exception {
		bid.setDebitBidId(this.serialNoService.genMerchDebitDibNo());
		this.debitBidDao.saveDebitBid(bid);
	}

	@Override
	public void update(DebitBid bid)
			throws Exception {
		this.debitBidDao.updateDebitBid(bid);
	}

	@Override
	public BigInteger countDebitBid() {
		return this.debitBidDao.countDebitBid();
	}
	
	/**
	 * 同步方法，更新竞投受理次数
	 * @param loanPdt
	 * @throws Exception
	 */
	public synchronized void updateDebitBidAcceptTotal(DebitBid debitBid) throws Exception{
		debitBid.setAcceptTotal(debitBid.getAcceptTotal() == null ?new Long(1):(debitBid.getAcceptTotal()+1));
		this.debitBidDao.updateDebitBid(debitBid);
	}
}
