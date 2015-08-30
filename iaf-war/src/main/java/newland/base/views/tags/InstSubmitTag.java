package newland.base.views.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newland.base.views.components.HcSubmitComponent;
import newland.base.views.components.InstSubmitComponent;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractClosingTag;

import com.opensymphony.xwork2.util.ValueStack;

public class InstSubmitTag extends AbstractClosingTag{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7399982950997598231L;
	protected String action;
	protected String method;
	protected String align;
	protected String type;
	protected String src;
	protected String authCode;

	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new InstSubmitComponent(stack, req, res);
	}

	protected void populateParams() {
		super.populateParams();

		InstSubmitComponent submit = ((InstSubmitComponent) component);
		submit.setAction(action);
		submit.setMethod(method);
		submit.setAlign(align);
		submit.setType(type);
		submit.setSrc(src);
		submit.setAuthCode(authCode);
		
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthCode() {
		return authCode;
	}
}
