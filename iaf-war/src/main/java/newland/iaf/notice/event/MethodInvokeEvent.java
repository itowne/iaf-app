package newland.iaf.notice.event;

import java.lang.reflect.Method;

import org.springframework.context.ApplicationEvent;

public class MethodInvokeEvent extends ApplicationEvent {

	private Method method;
	
	private Object[] args;
	
	private Object returnObject;
	
	private Throwable exception;
	
	private String processorKey;
	
	
	public MethodInvokeEvent(Object source,String processorKey, Method method, Object[] args){
		super(source);
		this.method = method;
		this.args = args;
		this.processorKey = processorKey;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Method getMethod() {
		return method;
	}

	public Object[] getArgs() {
		return args;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public String getProcessorKey() {
		return processorKey;
	}

}
