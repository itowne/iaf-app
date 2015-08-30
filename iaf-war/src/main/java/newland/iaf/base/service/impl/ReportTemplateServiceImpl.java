package newland.iaf.base.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import newland.iaf.base.dao.ReportTemplateDao;
import newland.iaf.base.model.ReportTemplate;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.base.service.ReportTemplateService;
import newland.iaf.merch.model.HtmlFileType;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.SpringTemplateLoader;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
@Service("reportTemplateService")
public class ReportTemplateServiceImpl implements ReportTemplateService {
	
	@Resource (name = "reportTemplateDao")
	private ReportTemplateDao reportTemplateDao;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private static Configuration conf;
	
	/**
	 * 模板文件路径
	 */
	static String templatePath = "";
	
	static{
		String path = System.getProperty("REPORT_TEMPLATE");
		if (StringUtils.isBlank(path)){
			templatePath = "template"; 
		}else{
			templatePath = path;
		}
	}
	
	@PostConstruct
	public void init(){
		conf = new Configuration();
		conf.setObjectWrapper(new DefaultObjectWrapper());
		conf.setTemplateLoader(new SpringTemplateLoader(new PathMatchingResourcePatternResolver(), templatePath));
	}

	@Override
	public void renderTemplate(String template, Map<String, Object> root, OutputStream out) {
		Template temp = null;
		try {
			temp = conf.getTemplate(template);
		} catch (IOException e) {
			throw new RuntimeException("查找模板错误", e);
		}
		Writer writer = new OutputStreamWriter(out);
		try {
			temp.process(root, writer);
		} catch (TemplateException e) {
			logger.error("模板生成错误", e);
			throw new RuntimeException("模板生成错误", e);
		} catch (IOException e) {
			logger.error("写数据错误", e);
			throw new RuntimeException("写数据错误", e);
		}
	}

	@Override
	public void save(ReportTemplate temp, File file) {
		temp.setFileName(file.getName());
		this.reportTemplateDao.save(temp);

	}

	@Override
	public void update(ReportTemplate temp, File file) {
		temp.setFileName(file.getName());
		this.reportTemplateDao.update(temp);

	}

	@Override
	
	public ReportTemplate queryBy(PagePosition position, HtmlFileType type) {
		ReportTemplate report = null;
		//logger.info("type="+type);
		if (type == HtmlFileType.FOR_PDF){
			report = this.reportTemplateDao.queryBy(position);
		}
		//logger.info("report="+report);
		//logger.info("position="+position);
		if (report == null){
			report = new ReportTemplate();
			switch (position){
			case COVER:
				report.setFileName(ReportTemplateService.DEFAULT_COVER);
				break;
			case BACK_COVER:
				report.setFileName(ReportTemplateService.DEFAULT_BACK_COVER);
				break;
			case BASIC_INFO:
				report.setFileName(ReportTemplateService.DEFAULT_BASIC_INFO);
				break;
			case FIELD_SURVY:
				report.setFileName(ReportTemplateService.DEFAULT_FIELD_SURVY);
				break;
			case ROUTING_ISPECTION:
				report.setFileName(ReportTemplateService.DEFAULT_ROUTING_ISPECTION);
				break;
			case INSTALL:
				report.setFileName(ReportTemplateService.DEFAULT_INSTALL);
				break;
			case OTHER_INFO:
				report.setFileName(ReportTemplateService.DEFAULT_OTHER_INFO);
				break;
			case TRANSFER:
				report.setFileName(ReportTemplateService.DEFAULT_TRANSFER);
				break;
			case MERCH_BUSI_DATA_VERIFICATION:
				report.setFileName(ReportTemplateService.DEFAULT_MERCH_BUSI_DATA_VERIFICATION);
				break;
				default: throw new RuntimeException("未定义模板");
			}
		}
		return report;
	}
}
