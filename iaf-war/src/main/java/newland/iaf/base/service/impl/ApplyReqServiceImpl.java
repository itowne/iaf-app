package newland.iaf.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.dao.ApplyReqDao;
import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.condition.ApplyRequestCondition;
import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.ApplyType;
import newland.iaf.base.service.ApplyReqService;
import newland.iaf.utils.service.SerialNoService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("applyReqService")
public class ApplyReqServiceImpl implements ApplyReqService {
	@Resource (name = "applyReqDao")
	private ApplyReqDao applyReqDao;
	@Resource(name = "serialNoService")
	private SerialNoService serialNoService;
	@Override
	public ApplyRequest apply(ApplyRequest req) throws Exception {
		req.setBatchId(this.serialNoService.genSystemNo());
		this.applyReqDao.save(req);
		return req;
	}

	@Override
	public ApplyRequest process(ApplyRequest req) throws Exception {
		req.setStat(ApplyStat.SUCCESS);
		req.setUpdTime(new Date());
		this.applyReqDao.update(req);
		return req;
	}
	
	public ApplyRequest unFreezenPayed(ApplyRequest req) throws Exception {
		req.setStat(ApplyStat.MERCH_PAYED);
		req.setUpdTime(new Date());
		this.applyReqDao.update(req);
		return req;
	}
	
	public ApplyRequest unFreezenUnPayed(ApplyRequest req) throws Exception {
		req.setStat(ApplyStat.MERCH_DELIN);
		this.applyReqDao.update(req);
		return req;
	}

	@Override
	public ApplyRequest refuse(ApplyRequest req) throws Exception {
		req.setStat(ApplyStat.REFUSE);
		this.applyReqDao.update(req);
		return req;
	}

	@Override
	public List<ApplyRequest> queryBy(ApplyType type) {
		ApplyRequest req = new ApplyRequest();
		req.setApplyType(type);
		return this.applyReqDao.queryBy(req);
	}

	@Override
	public List<ApplyRequest> queryBy(ApplyRequestCondition cond) {
		return this.applyReqDao.queryBy(cond);
	}

	@Override
	public void cancel(ApplyRequest req) {
		req.setStat(ApplyStat.CANCEL);
		this.applyReqDao.update(req);
	}
	
	/**
	 * 复合查询 分页
	 * @param cond
	 * @return
	 */
	@Transactional
	public List<ApplyRequest> queryByPage(ApplyRequestCondition cond,Page page){
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iapplyReq"));
		cond.setOrders(orderList);
		return this.applyReqDao.queryByPage(cond, page);
	}

	@Override
	@Transactional
	public void update(ApplyRequest req) {
		this.applyReqDao.update(req);
	}

	@Override
	@Transactional
	public ApplyRequest queryByIloanOrd(Long iloanord,String periods) {
		return this.applyReqDao.queryByIloanOrd(iloanord,periods);
	}
}
