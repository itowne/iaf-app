package newland.base.views.result;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;

public class PlainResult implements Result {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String valueName;

	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		ValueStack stack = invocation.getStack();
		String value = (String) stack.findValue(valueName);
		response.setContentType("text/plain");
		OutputStream outputStream = response.getOutputStream();
		if (value == null) value = "";
		outputStream.write(value.getBytes());
		outputStream.flush();

	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

}
