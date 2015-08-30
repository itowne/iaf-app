package newland.base.exception;

import newland.base.exception.base.CommonException;
import newland.base.exception.base.Paramsable;

public class AppBizException extends CommonException implements Paramsable{

	private static final long serialVersionUID = 1L;
	
	private Object[] params;
	
	public AppBizException(String code, String msg) {
		super(code, msg);
	}
	public AppBizException(String code, String msg,Throwable e) {
		super(code, msg,e);
	}
	public AppBizException(String code, Object[] params, String msg) {
        this(code, msg);
        this.params = params;
	}
	public AppBizException(String code, Object[] params, String msg, Throwable e) {
		this(code, msg, e);
		this.params = params;
	}

	public Object[] getParams() {
		return params;
	}
	

}
