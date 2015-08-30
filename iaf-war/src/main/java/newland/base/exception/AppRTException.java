package newland.base.exception;

import newland.base.exception.base.CommonRuntimeException;
import newland.base.exception.base.Paramsable;

public class AppRTException  extends CommonRuntimeException implements Paramsable{

	private Object[] params;
	
	private static final long serialVersionUID = 1L;
	
	public AppRTException(String code, String msg) {
		super(code, msg);
	}
	public AppRTException(String code, String msg,Throwable e) {
		super(code, msg,e);
	}
	public AppRTException(String code, Object[] params, String msg) {
        this(code, msg);
        this.params = params;
	}
	public AppRTException(String code, Object[] params, String msg, Throwable e) {
		this(code, msg, e);
		this.params = params;
	}

	public Object[] getParams() {
		return params;
	}
	
}
