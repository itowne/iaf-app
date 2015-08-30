package newland.base.views.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.UIBean;

import com.opensymphony.xwork2.util.ValueStack;

public class UI extends UIBean {

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

	public UI(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
	}

	@Override
	protected String getDefaultTemplate() {
		return "ui";
	}

	protected void evaluateExtraParams() {
		super.evaluateExtraParams();
		if (includes != null) {
			String[] tmpArray = includes.trim().split(",");
			for (int i = 0, len = tmpArray.length; i < len; i++) {
				addParameter(tmpArray[i], true);
			}
		}
	}

}