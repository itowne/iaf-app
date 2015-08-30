package newland.iaf.base.dao;

import java.util.List;

import newland.iaf.base.model.ReportTemplate;
import newland.iaf.base.model.dict.PagePosition;

public interface ReportTemplateDao {
	/**
	 * 新增模板
	 * @param rptTemplate
	 */
	void save(ReportTemplate rptTemplate);
	/**
	 * 更新模板
	 * @param rptTemp
	 */
	void update (ReportTemplate rptTemp);
	/**
	 * 查询模板
	 * @return
	 */
	List<ReportTemplate> queryAll();
	/**
	 * 根据页面查询模板
	 * @param position
	 * @return
	 */
	ReportTemplate queryBy(PagePosition position);

}
