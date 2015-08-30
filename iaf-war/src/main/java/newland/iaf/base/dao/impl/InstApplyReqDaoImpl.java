package newland.iaf.base.dao.impl;

import java.util.Date;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.InstApplyReqDao;
import newland.iaf.base.model.InstApplyRequest;
import newland.iaf.base.model.condition.InstApplyRequestCondition;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
@Service("instApplyReqDao")
public class InstApplyReqDaoImpl extends BaseDao implements InstApplyReqDao {

	@Override
	public void save(InstApplyRequest req) {
		this.getSession().save(req);

	}

	@Override
	public void update(InstApplyRequest req) {
		req.setUpdTime(new Date());
		this.getSession().update(req);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstApplyRequest> query(InstApplyRequest req) {
		return this.getHibernateDao().findByExample(req);
	}
	/**
	 * 根据ID查找对应的信息
	 * @param iinstApplyReq
	 * @return
	 */
	public InstApplyRequest getInstApplyRequest(Long iinstApplyReq){
		Object o = this.getSession().get(InstApplyRequest.class, iinstApplyReq);
		if(o !=null){
			return (InstApplyRequest)o;
		}
		return null;
	}
	/**
	 * 根据条件分页查询
	 * @param cond
	 * @param page
	 * @return
	 */
	public List<InstApplyRequest> listInstApplyRequest(InstApplyRequestCondition cond,Page page){
		return this.getHibernateDao().findByCriteriaEx(cond, page, true);
	}

	@Override
	public List<InstApplyRequest> queryAll() {
		return this.getHibernateDao().find(InstApplyRequest.class);
	}

}
