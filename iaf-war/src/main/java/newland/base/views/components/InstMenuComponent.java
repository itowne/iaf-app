package newland.base.views.components;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MenuNode;
import newland.iaf.base.servlet.SessionFilter;

import org.apache.struts2.components.UIBean;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 菜单
 * 
 * @author rabbit
 * 
 */
public class InstMenuComponent extends UIBean {

	public InstMenuComponent(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
	}

	@Override
	protected String getDefaultTemplate() {
		return "instmenu";
	}

	protected void evaluateExtraParams() {
		super.evaluateExtraParams();
		HttpSession sess = request.getSession();
		InstSession instSess = (InstSession) (IafSession) sess
				.getAttribute(SessionFilter.IAF_LOGIN_SESSION);
		List<MenuNode> menu = instSess.getMenuList();
		addParameter("menu", menu);
	}
}
