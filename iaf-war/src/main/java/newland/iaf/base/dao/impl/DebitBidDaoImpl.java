package newland.iaf.base.dao.impl;

import java.math.BigInteger;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.DebitBidDao;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.base.model.dict.DebitbidStat;

import org.hibernate.Criteria;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;

@Repository("debitBidDao")
public class DebitBidDaoImpl extends BaseDao implements DebitBidDao {

	@Override
	public void saveDebitBid(DebitBid debitBid) {
		this.getSession().save(debitBid);
	}

	@Override
	public void updateDebitBid(DebitBid debitBid) {
		this.getSession().update(debitBid);
	}

	@Override
	public DebitBid getDebitBidById(Long id) {
		return (DebitBid) this.getSession().get(DebitBid.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DebitBid> queryDebitBidByCon(DebitBidCondition debitBidCondition,Page page) {
		List<DebitBid> DebitBidList =  this.getHibernateDao().findByCriteriaEx(debitBidCondition, page, true);
		return DebitBidList;
	}

	@Override
	public void queryBy(CriteriaExecutor<DebitBid> criteriaExecutor) {
		Criteria criteria = this.getSession().createCriteria(DebitBid.class);
		criteriaExecutor.execute(criteria);
		
	}

	@Override
	public BigInteger countDebitBid() {
		return this.getHibernateDao().count("from " + DebitBid.class.getName() + " db where db.bidStat in(?,?)", DebitbidStat.NORMAL,DebitbidStat.REVOCATION);
	}
}
