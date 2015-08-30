package newland.iaf.base.dao.impl;

import java.util.Date;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.ReportTemplateDao;
import newland.iaf.base.model.ReportTemplate;
import newland.iaf.base.model.dict.PagePosition;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
@Service("reportTemplateDao")
public class ReportTemplateDaoImpl extends BaseDao implements ReportTemplateDao {

	@Override
	public void save(ReportTemplate rptTemplate) {
		this.getSession().save(rptTemplate);

	}

	@Override
	public void update(ReportTemplate rptTemp) {
		rptTemp.setUpdTime(new Date());
		this.getSession().update(rptTemp);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportTemplate> queryAll() {
		return this.getHibernateDao().find(ReportTemplate.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReportTemplate queryBy(PagePosition position) {
		ReportTemplate rt = new ReportTemplate();
		rt.setPosition(position);
		List<ReportTemplate> list = this.getHibernateDao().findByExample(rt);
		if (CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

}
