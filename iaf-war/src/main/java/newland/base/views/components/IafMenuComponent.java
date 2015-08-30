package newland.base.views.components;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.IafMenuNode;
import newland.iaf.base.servlet.SessionFilter;

import org.apache.struts2.components.UIBean;

import com.opensymphony.xwork2.util.ValueStack;

public class IafMenuComponent extends UIBean {
	
	public IafMenuComponent(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
	}

	@Override
	protected String getDefaultTemplate() {
		return "iafmenu";
	}

	protected void evaluateExtraParams() {
		super.evaluateExtraParams();
		HttpSession sess = request.getSession();
		IafConsoleSession ses =  (IafConsoleSession)(IafSession)sess
				.getAttribute(SessionFilter.IAF_LOGIN_SESSION);
		List<IafMenuNode> menu = ses.getMenuList();
		addParameter("menu", menu);
	}
}
