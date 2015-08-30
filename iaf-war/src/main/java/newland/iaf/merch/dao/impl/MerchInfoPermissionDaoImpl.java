package newland.iaf.merch.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.merch.dao.MerchInfoPermissionDao;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchInfoPermission;

import org.springframework.stereotype.Service;

@Service("merchInfoPermissionDao")
public class MerchInfoPermissionDaoImpl extends BaseDao implements
		MerchInfoPermissionDao {

	@Override
	public void save(MerchInfoPermission perm) {
		this.getSession().save(perm);

	}

	@Override
	public void update(MerchInfoPermission perm) {
		this.getSession().update(perm);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchInfoPermission> query(MerchInfoPermission perm,Merch merch) {
		perm.setImerch(merch.getImerch());
		return this.getHibernateDao().findByExample(perm);
	}

}
