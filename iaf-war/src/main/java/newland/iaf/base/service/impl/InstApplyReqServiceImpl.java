package newland.iaf.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.dao.InstApplyReqDao;
import newland.iaf.base.model.InstApplyRequest;
import newland.iaf.base.model.condition.InstApplyRequestCondition;
import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.service.InstApplyReqService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 机构审请服务
 * @author new
 *
 */
@Service("instApplyReqService")
@Transactional
public class InstApplyReqServiceImpl implements InstApplyReqService {
	@Resource (name = "instApplyReqDao")
	private InstApplyReqDao instApplyReqDao;

	@Override
	public void apply(InstApplyRequest req) throws Exception {
		Date now = new Date();
		req.setGenTime(now);
		req.setUpdTime(now);
		req.setStat(ApplyStat.APPLY);
		this.instApplyReqDao.save(req);
	}

	@Override
	public void update(InstApplyRequest req) throws Exception {
		req.setUpdTime(new Date());
		this.instApplyReqDao.update(req);
	}

	@Override
	public void query(ApplyStat stat) throws Exception {
		InstApplyRequest req = new InstApplyRequest();
		req.setStat(stat);
		this.instApplyReqDao.query(req);

	}
	
	/**
	 * 根据ID查找对应的信息
	 * @param iinstApplyReq
	 * @return
	 */
	public InstApplyRequest getInstApplyRequest(Long iinstApplyReq){
		return this.instApplyReqDao.getInstApplyRequest(iinstApplyReq);
	}
	
	/**
	 * 根据条件分页查询
	 * @param cond
	 * @param page
	 * @return
	 */
	public List<InstApplyRequest> listInstApplyRequest(InstApplyRequestCondition cond,Page page){
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iinstApplyReq"));
		cond.setOrders(orderList);
		return this.instApplyReqDao.listInstApplyRequest(cond, page);
	}

	@Override
	public List<InstApplyRequest> queryAll() {
		return this.instApplyReqDao.queryAll();
	}

}
