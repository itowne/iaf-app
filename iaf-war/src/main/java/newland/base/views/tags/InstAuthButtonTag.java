package newland.base.views.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newland.base.views.components.InstAuthButtonComponent;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

public class InstAuthButtonTag extends AbstractUITag{
	
	private static final long serialVersionUID = -1616271042859357718L;

	private String authCode;
	private String disabledFlag;
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new InstAuthButtonComponent(stack, req, res);
	}
	
	protected void populateParams() {
		super.populateParams();
		InstAuthButtonComponent instAuthButtonComponent = (InstAuthButtonComponent)component;
		instAuthButtonComponent.setAuthCode(authCode);
		instAuthButtonComponent.setDisabledFlag(disabledFlag);
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getDisabledFlag() {
		return disabledFlag;
	}

	public void setDisabledFlag(String disabledFlag) {
		this.disabledFlag = disabledFlag;
	}

}
