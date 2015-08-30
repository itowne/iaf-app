package newland.iaf.base.dao.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.TransMsgDao;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.dict.TransStat;
import newland.iaf.utils.DateUtils;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
@Service("transMsgDao")
public class TransMsgDaoImpl extends BaseDao implements TransMsgDao {

	@Override
	public void save(TransMsg msg) {
		this.getSession().save(msg);

	}

	@Override
	public void update(TransMsg msg) {
		this.getSession().update(msg);

	}
	
	

	@Override
	public List<TransMsg> query(TransMsg msg) {
		return this.getHibernateDao().findByExample(msg);
	}

	@Override
	public TransMsg findById(String orderNo) {
		List<TransMsg> list = this.getHibernateDao().find("from " + TransMsg.class.getName() + " tm where tm.orderNo=?", orderNo);
		if (CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryBeforeDate(Date date, TransStat stat) {
		date = DateUtils.roundDate(date, Calendar.DATE);
		Criteria expr = this.getSession().createCriteria(TransMsg.class);
		expr.setProjection(Projections.property("orderNo")).
			add(Restrictions.lt("transDate", date)).
			add(Restrictions.ge("transStat", stat));
		return (List<String>)expr.list();
	}

	@Override
	public TransMsg findByOthSysNo(String orderId, BigDecimal amount) {
		BigDecimal max = amount.add(new BigDecimal(1));
		BigDecimal min = amount.subtract(new BigDecimal(1));
		Criteria expr = this.getSession().createCriteria(TransMsg.class);
		expr.add(Restrictions.eq("otherSysNo", orderId)).
			add(Restrictions.lt("orderAmount", max)).
			add(Restrictions.gt("orderAmount", min));
		List<TransMsg> list = expr.list();
		if (CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransMsg> queryBy(Date beginDate, Date endDate, TransStat stat) {
		Criteria expr = this.getSession().createCriteria(TransMsg.class);
		expr.add(Restrictions.ge("transDate", beginDate)).
		    add(Restrictions.lt("transDate", endDate)).
			add(Restrictions.eq("transStat", stat));
		return expr.list();
	}

	@Override
	public List<TransMsg> queryByOrderNo(String hcOrderNo) {
		// TODO Auto-generated method stub
		TransMsg tm = new TransMsg();
		tm.setOtherSysNo(hcOrderNo);
		Criteria expr = this.getSession().createCriteria(TransMsg.class);
		expr.add(Restrictions.eq("otherSysNo", hcOrderNo));
		return expr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransMsg> querySettleRecord() {
		// TODO Auto-generated method stub
		return this.getHibernateDao().find("from TransMsg where other_sys_no is NOT NULL");
	}
	
	

}
