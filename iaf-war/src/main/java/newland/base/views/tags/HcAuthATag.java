package newland.base.views.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newland.base.views.components.HcAuthAComponent;
import newland.base.views.components.InstAuthAComponent;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

public class HcAuthATag extends AbstractUITag{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8874238800333585920L;
	
	private String authCode;
	private String href;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new HcAuthAComponent(stack, req, res);
	}
	
	protected void populateParams() {
		super.populateParams();
		HcAuthAComponent hcAuthAComponent = (HcAuthAComponent)component;
		hcAuthAComponent.setAuthCode(authCode);
		hcAuthAComponent.setHref(href);
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
