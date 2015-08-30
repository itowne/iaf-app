package newland.base.views.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;

import org.apache.struts2.components.UIBean;

import com.opensymphony.xwork2.util.ValueStack;

public class InstAuthAComponent extends UIBean {
	
	private String authCode;
	private String href;
	
	public InstAuthAComponent(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
	}

	@Override
	protected String getDefaultTemplate() {
		return "instautha";
	}

	protected void evaluateExtraParams() {
		super.evaluateExtraParams();
		HttpSession sess = request.getSession();
		InstSession instSess = (InstSession) (IafSession) sess
				.getAttribute(SessionFilter.IAF_LOGIN_SESSION);
		if(href!=null){
			addParameter("href", href);
		}
		addParameter("hasInstAuthA", instSess.hasInstAuth(authCode));
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
