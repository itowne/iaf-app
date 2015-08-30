package newland.base.views.result;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;

public class WordResult implements Result{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2726352049696711616L;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		ValueStack stack = invocation.getStack();
		String fileName = (String)stack.findValue("fileName");
		  try {
              HttpServletResponse response = ServletActionContext.getResponse();
              response.setContentType("application/msword;pageEncoding=utf-8");
              response.setHeader("Content-disposition","attachment; fileName="+new String(fileName.getBytes("GB2312"), "8859_1"));
        } catch (UnsupportedEncodingException e) {
              logger.error("生成word出错!",e);
        }
	}

}
