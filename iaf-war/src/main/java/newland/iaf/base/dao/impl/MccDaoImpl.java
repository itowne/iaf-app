package newland.iaf.base.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.MccDao;
import newland.iaf.inst.model.Mcc;

@Service("mccDao")
public class MccDaoImpl extends BaseDao implements MccDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Mcc> queryAll() {
		return this.getHibernateDao().find("from " + Mcc.class.getName());
	}

	@Override
	public List<Mcc> findByTypeCode(String typeCode) {
		Mcc mcc = new Mcc();
		mcc.setType(typeCode);
		return this.getHibernateDao().findByExample(mcc);
	}

	@Override
	public Mcc queryByName(String name) {
		Mcc mcc =new Mcc();
		mcc.setName(name);
		return this.getHibernateDao().getFirstOneByExample(mcc);
	}

	@Override
	public Mcc queryById(String id) {
		// TODO Auto-generated method stub
		return (Mcc) this.getSession().get(Mcc.class, id);
	}

}
