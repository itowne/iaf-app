package newland.base.views.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;

import org.apache.struts2.components.UIBean;

import com.opensymphony.xwork2.util.ValueStack;

public class HcAuthButtonComponent extends UIBean{
	
	private String authCode;
	private String disabledFlag;
	private String id;

	public HcAuthButtonComponent(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
	}

	@Override
	protected String getDefaultTemplate() {
		return "hcauthbutton";
	}
	
	protected void evaluateExtraParams() {
		super.evaluateExtraParams();
		HttpSession sess = request.getSession();
		IafConsoleSession iafConsoleSession = (IafConsoleSession) (IafSession) sess
				.getAttribute(SessionFilter.IAF_LOGIN_SESSION);
		if(disabledFlag!=null){
			addParameter("disabledFlag", disabledFlag);
		}
		addParameter("hasHcAuthButton", iafConsoleSession.hasHcAuth(authCode));
		if(id!=null){
			addParameter("id",id);
		}
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
