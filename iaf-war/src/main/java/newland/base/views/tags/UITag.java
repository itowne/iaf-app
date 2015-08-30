package newland.base.views.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newland.base.views.components.UI;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

public class UITag extends AbstractUITag {

	private static final long serialVersionUID = 7642749891632011025L;

	/**
	 * 页面需要的组件
	 */
	private String includes;

	public String getIncludes() {
		return includes;
	}

	public void setIncludes(String includes) {
		this.includes = includes;
	}

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new UI(arg0, arg1, arg2);
	}

	protected void populateParams() {
		super.populateParams();
		UI ui = (UI) component;
		ui.setIncludes(includes);
	}

}