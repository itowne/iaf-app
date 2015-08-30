package newland.iaf.base.dao.impl;

import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.ProvinceDao;
import newland.iaf.base.model.Province;

import org.springframework.stereotype.Service;

@Service("provinceDao")
public class ProvinceDaoImpl extends BaseDao implements ProvinceDao {

	@Override
	public void save(Province province) {
		this.getSession().save(province);

	}

	@Override
	public void update(Province provincd) {
		this.getSession().update(provincd);

	}

	@Override
	public List<Province> queryAll() {
		return this.getHibernateDao().find("from " + Province.class.getName());
	}

	@Override
	public List<Province> findByPreProvCode(String preCodeCode) {
		Province p = new Province();
		p.setPreProvCode(preCodeCode);
		return this.getHibernateDao().findByExample(p);
	}

	@Override
	public Province queryByCityCode(String provCode) {
		return (Province) this.getSession().get(Province.class, provCode);
	}
}
