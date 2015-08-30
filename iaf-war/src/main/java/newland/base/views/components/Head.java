package newland.base.views.components;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.UIBean;

import com.opensymphony.xwork2.util.ValueStack;

public class Head extends UIBean {

	/**
	 * html文档的标题
	 */
	private String title;
	/**
	 * html文档的关键字
	 */
	private String keywords;
	/**
	 * html文档的描述
	 */
	private String description;
	/**
	 * html文档引用的样式表
	 */
	private String styles;
	/**
	 * html文档引用的脚本
	 */
	private String scripts;

	private List<String> cssFiles;
	private List<String> jsFiles;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public String getScripts() {
		return scripts;
	}

	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

	public List<String> getCssFiles() {
		return cssFiles;
	}

	public void setCssFiles(List<String> cssFiles) {
		this.cssFiles = cssFiles;
	}

	public List<String> getJsFiles() {
		return jsFiles;
	}

	public void setJsFiles(List<String> jsFiles) {
		this.jsFiles = jsFiles;
	}

	public Head(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
	}

	@Override
	protected String getDefaultTemplate() {
		return "head";
	}

	protected void evaluateExtraParams() {
		super.evaluateExtraParams();
		if (title != null) {
			addParameter("title", title);
		}
		if (keywords != null) {
			addParameter("keywords", keywords);
		}
		if (description != null) {
			addParameter("description", description);
		}
		if (styles != null) {
			cssFiles = new ArrayList<String>();
			String[] tmpArray = styles.trim().split(",");
			for (int i = 0, len = tmpArray.length; i < len; i++) {
				cssFiles.add(tmpArray[i]);
			}
			addParameter("cssFiles", cssFiles);
		}
		if (scripts != null) {
			jsFiles = new ArrayList<String>();
			String[] tmpArray = scripts.trim().split(",");
			for (int i = 0, len = tmpArray.length; i < len; i++) {
				jsFiles.add(tmpArray[i]);
			}
			addParameter("jsFiles", jsFiles);
		}
	}
}