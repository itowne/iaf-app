package newland.base.views.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newland.base.views.components.IafMenuComponent;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

public class IafMenuTag extends AbstractUITag{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5724378705771706981L;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new IafMenuComponent(stack, req, res);
	}
}
