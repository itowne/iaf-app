package newland.base.views.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newland.base.views.components.HcAuthButtonComponent;
import newland.base.views.components.InstAuthButtonComponent;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

public class HcAuthButtonTag extends AbstractUITag{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6177174376065099457L;
	
	private String authCode;
	private String disabledFlag;
	private String id;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		// TODO Auto-generated method stub
		return new HcAuthButtonComponent(stack, req, res);
	}

	protected void populateParams() {
		super.populateParams();
		HcAuthButtonComponent hcAuthButtonComponent = (HcAuthButtonComponent)component;
		hcAuthButtonComponent.setAuthCode(authCode);
		hcAuthButtonComponent.setDisabledFlag(disabledFlag);
		hcAuthButtonComponent.setId(id);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
