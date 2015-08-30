package newland.iaf.merch.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.merch.dao.MerchCollDao;
import newland.iaf.merch.model.MerchCollInst;
import newland.iaf.merch.model.MerchCollPdt;

import org.springframework.stereotype.Repository;

@Repository("merchCollDao")
public class MerchCollDaoImpl extends BaseDao implements MerchCollDao {

	@Override
	public void saveMerchCollInst(MerchCollInst merchCollInst) {
		this.getSession().save(merchCollInst);
	}

	@Override
	public void deleteMerchCollInst(MerchCollInst merchCollInst) {
		this.getSession().delete(merchCollInst);
	}

	@Override
	public List<MerchCollInst> queryMerchCollInstByImerch(Long imerch) {
		MerchCollInst merchCollInst = new MerchCollInst();
		merchCollInst.setImerch(imerch);
		return this.getHibernateDao().findByExample(merchCollInst);
	}

	@Override
	public void saveMerchCollPdt(MerchCollPdt merchCollPdt) {
		this.getSession().save(merchCollPdt);
	}

	@Override
	public void deleteMerchCollPdt(MerchCollPdt merchCollPdt) {
		this.getSession().delete(merchCollPdt);
	}

	@Override
	public List<MerchCollPdt> queryMerchCollPdtByImerch(Long imerch) {
		MerchCollPdt merchCollPdt = new MerchCollPdt();
		merchCollPdt.setImerch(imerch);
		return this.getHibernateDao().findByExample(merchCollPdt);
	}

	@Override
	public MerchCollPdt getMerchCollPdtById(Long iMerchCollPdt) {
		return (MerchCollPdt) this.getSession().get(MerchCollPdt.class, iMerchCollPdt);
	}

	@Override
	public MerchCollInst getMerchCollInstById(Long iMerchCollInst) {
		return (MerchCollInst) this.getSession().get(MerchCollInst.class, iMerchCollInst);
	}

	@Override
	public MerchCollInst queryByIinstAndImerch(Long iInst, Long imerch) {
		MerchCollInst mci = new MerchCollInst();
		mci.setiInst(iInst);
		mci.setImerch(imerch);
		return this.getHibernateDao().getFirstOneByExample(mci);
	}

	@Override
	public MerchCollPdt queryByIloanPdtAndImerch(Long iLoanPdt,Long imerch) {
		MerchCollPdt mcp = new MerchCollPdt();
		mcp.setiLoanPdt(iLoanPdt);
		mcp.setImerch(imerch);
		return this.getHibernateDao().getFirstOneByExample(mcp);
	}
}
