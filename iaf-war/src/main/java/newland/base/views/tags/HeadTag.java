package newland.base.views.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newland.base.views.components.Head;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

public class HeadTag extends AbstractUITag {

	private static final long serialVersionUID = 7642749891632011025L;

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

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new Head(arg0, arg1, arg2);
	}

	protected void populateParams() {
		super.populateParams();
		Head head = (Head) component;
		head.setTitle(title);
		head.setKeywords(keywords);
		head.setDescription(description);
		head.setStyles(styles);
		head.setScripts(scripts);
	}

}