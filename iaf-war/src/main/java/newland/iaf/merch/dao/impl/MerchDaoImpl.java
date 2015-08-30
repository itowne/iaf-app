package newland.iaf.merch.dao.impl;

import java.util.Date;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.MerchFieldSurvy;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.merch.dao.MerchDao;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.condition.MerchCondition;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 */
@Repository("com.newland.iaf.merchDao")
public class MerchDaoImpl extends BaseDao implements MerchDao {

	public boolean hasMerch(Merch merch) {
		return this.getHibernateDao().hasRecordByExample(merch);
	}

	@Override
	public void saveMerch(Merch merch) {
		this.getSession().save(merch);
	}

	@Override
	public void updateMerch(Merch merch) {
		this.getSession().update(merch);
	}

	@Override
	public void saveMerchBusiInfo(MerchBusiInfo MerchBusiInfo) {
		this.getSession().save(MerchBusiInfo);
	}

	@Override
	public void updateMerchBusiInfo(MerchBusiInfo MerchBusiInfo) {
		this.getSession().update(MerchBusiInfo);
	}

	@Override
	public void saveMerchLegalPers(MerchLegalPers merchLegalPers) {
		this.getSession().save(merchLegalPers);
	}

	@Override
	public void updateMerchLegalPers(MerchLegalPers merchLegalPers) {
		this.getSession().update(merchLegalPers);
	}

	@Override
	public Merch getMerch(Long imerch) {
		return (Merch) this.getSession().get(Merch.class, imerch);
	}

	@Override
	public MerchLegalPers getMerchLegalPers(Long iMerchLegalPers) {
		return (MerchLegalPers) this.getSession().get(MerchLegalPers.class,
				iMerchLegalPers);
	}

	@Override
	public MerchBusiInfo getMerchBusiInfo(Long iMerchBusiInfo) {
		return (MerchBusiInfo) this.getSession().get(MerchBusiInfo.class,
				iMerchBusiInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Merch> queryMerchByCon(MerchCondition merchCondition, Page page) {
		List<Merch> merchList = this.getHibernateDao().findByCriteriaEx(
				merchCondition, page, true);
		return merchList;
	}

	@Override
	public Merch finMerchById(Long id) {
		return (Merch) this.getSession().get(Merch.class, id);
	}

	/**
	 * 根据ID查找业务信息
	 * 
	 * @param iinst
	 * @return
	 */
	public MerchBusiInfo getMerchBusiInfoByImerch(Long imerch) {
		MerchBusiInfo i = new MerchBusiInfo();
		i.setImerch(imerch);
		return this.getHibernateDao().getFirstOneByExample(i);
	}

	/**
	 * 根据ID查找法人信息
	 * 
	 * @param iinst
	 * @return
	 */
	public MerchLegalPers getMerchLegalPersByImerch(Long imerch) {
		MerchLegalPers i = new MerchLegalPers();
		i.setImerch(imerch);
		return this.getHibernateDao().getFirstOneByExample(i);
	}

	@Override
	public Merch getMerchByMerchNo(String merchNo) {
		Merch m = new Merch();
		m.setMerchNo(merchNo);
		return this.getHibernateDao().getFirstOneByExample(m);
	}

	public Merch getMerch(String merchName) {
		Merch merch = new Merch();
		merch.setMerchName(merchName);
		return this.getHibernateDao().getFirstOneByExample(merch);
	}

	@Override
	public MerchFieldSurvy getMerchFieldSurvyByImerch(Long iMerch) {
		MerchFieldSurvy merchFieldSurvy = new MerchFieldSurvy();
		merchFieldSurvy.setiMerch(iMerch);
		return this.getHibernateDao().getFirstOneByExample(merchFieldSurvy);
	}

	@Override
	public List<HcInspectLog> queryHcInspectLogByMerchNo(String merchNo) {
		HcInspectLog hcInspectLog = new HcInspectLog();
		hcInspectLog.setHcMerchNo(merchNo);
		return this.getHibernateDao().findByExample(hcInspectLog);
	}

	/**
	 * 保存现场调查信息
	 * 
	 * @param merchFieldSurvy
	 * @throws Exception
	 */
	public void saveMerchFieldSurvy(MerchFieldSurvy merchFieldSurvy)
			throws Exception {
		this.getSession().save(merchFieldSurvy);
	}

	/**
	 * 更新现场调查信息
	 * 
	 * @param merchFieldSurvy
	 * @throws Exception
	 */
	public void updateMerchFieldSurvy(MerchFieldSurvy merchFieldSurvy)
			throws Exception {
		this.getSession().update(merchFieldSurvy);
	}

	@Override
	public List<Merch> queryMerch() {
		return this.getHibernateDao().find(Merch.class);
	}

	@Override
	public MerchUser queryUserByImerch(Long imerch) {
		MerchUser mu =new MerchUser();
		mu.setImerch(imerch);
		return this.getHibernateDao().getFirstOneByExample(mu);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryTenDays(Date d,Date tenD,Long imerch) {
		return this.getHibernateDao().find("select sum(repayment),count(*) from LoanOrdPlan where refund_date BETWEEN ? AND ? and i_merch=? and plan_stat !=?", d,tenD,imerch,5);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanOrdPlan> queryIloanOrd(Date d, Date tenD, Long imerch) {
		return this.getHibernateDao().find(" from LoanOrdPlan where refund_date BETWEEN ? AND ? and i_merch=? and plan_stat !=? ", d,tenD,imerch,5);
	}
}
