package newland.iaf.base.service;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

import newland.iaf.base.model.ReportTemplate;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.merch.model.HtmlFileType;

public interface ReportTemplateService {
	/**
	 * 默认封面模板
	 */
	public static final String DEFAULT_COVER = "default_cover.ftl";
	/**
	 * 默认基础信息模板
	 */
	public static final String DEFAULT_BASIC_INFO = "default_basic_info.ftl";
	/**
	 * 默认现场调查
	 */
	public static final String DEFAULT_FIELD_SURVY = "default_field_survy.ftl";
	/**
	 * 默认装机撤机
	 */
	public static final String DEFAULT_INSTALL = "default_install.ftl";
	/**
	 * 默认交易记录模板
	 */
	public static final String DEFAULT_TRANSFER = "default_transfer.ftl";
	/**
	 * 默认巡检记录模板
	 */
	public static final String DEFAULT_ROUTING_ISPECTION = "default_routing_ispection.ftl";
	/**
	 * 默认封底
	 */
	public static final String DEFAULT_BACK_COVER = "default_back_cover.ftl";
	/**
	 * 默认其他资料模板
	 */
	public static final String DEFAULT_OTHER_INFO = "default_other_info.ftl";
	/**
	 * 默认经营数据核查情况模版
	 */
	public static final String DEFAULT_MERCH_BUSI_DATA_VERIFICATION = "default_merch_busi_data_verification.ftl";
	/**
	 * 默认拒决模板
	 */
	public static final String DEFAULT_DENY = "default_deny.ftl";
	
	
	
	
	/**
	 * 根据页面及对象渲染模板
	 * @param root
	 * @param positon
	 * @param writer  输出流
	 */
	void renderTemplate(String template,Map<String, Object> root, OutputStream out);
	/**
	 * 新增
	 * @param temp
	 */
	void save(ReportTemplate temp, File file);
	/**
	 * 更新
	 * @param temp
	 */
	void update(ReportTemplate temp, File file);
	/**
	 * 
	 * @param positon
	 * @param type TODO
	 * @return
	 */
	ReportTemplate queryBy(PagePosition positon, HtmlFileType type);
	
}
