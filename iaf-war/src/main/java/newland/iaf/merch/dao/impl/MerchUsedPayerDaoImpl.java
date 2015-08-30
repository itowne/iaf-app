package newland.iaf.merch.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.merch.dao.MerchUsedPayerDao;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchUsedPayer;

@Repository("merchUsedPayerDao")
public class MerchUsedPayerDaoImpl extends BaseDao  implements MerchUsedPayerDao {

	@Override
	public void saveMerchUsedPayer(MerchUsedPayer merchUsedPayer)
			throws Exception {
		this.getSession().save(merchUsedPayer);
	}

	@Override
	public void updateMerchUsedPayer(MerchUsedPayer merchUsedPayer)
			throws Exception {
		this.getSession().update(merchUsedPayer);
	}

	@Override
	public List<MerchUsedPayer> listMerchUsedPayerByImerch(Long imerch)
			throws Exception {
		MerchUsedPayer payer = new MerchUsedPayer();
		payer.setImerch(imerch);
		return this.getHibernateDao().findByExample(payer);
	}

	@Override
	public MerchUsedPayer findMerchUsedPayerByimerchUsedPayer(
			Long imerchUsedPayer) throws Exception {
		return (MerchUsedPayer)this.getSession().get(MerchUsedPayer.class, imerchUsedPayer);
	}

	@Override
	public void deleteMerchUsedPayer(MerchUsedPayer merchUsedPayer)
			throws Exception {
		this.getSession().delete(merchUsedPayer);
	}

}
