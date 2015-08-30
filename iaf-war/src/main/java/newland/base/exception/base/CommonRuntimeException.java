package newland.base.exception.base;

import newland.base.exception.ExceptTraceIdGen;

/**
 * 基于<tt>Traceable,Describable</tt>的RuntimeException参考实现
 * 
 * @author lance
 * @see com.newland.base.exception.base.CommonException
 */
public class CommonRuntimeException extends RuntimeException implements Traceable,Describable{

	/**
	 * version 1.0
	 */
	private static final long serialVersionUID = 1L;

	private final String code;
	
	private transient final Traceable parent;
	
	private final long timestamp;
	
	private String traceId;
	
	public CommonRuntimeException(String code,String msg){
		super(msg);
		this.code = code;
		
		timestamp = System.currentTimeMillis();
		parent = null;
	}
	
	public CommonRuntimeException(String code,String msg,Throwable e){
		super(msg,e);
		
		this.code = code;
		
		if(e instanceof Traceable){
			parent = (Traceable) e; 
			timestamp = ((Traceable) e).getTimestamp();
		}else{
			parent = null;
			timestamp = System.currentTimeMillis();
		}
	}
	

	public String getCode() {
		return code;
	}
	
	public String toString(){
		
		String name = getClass().getName();
		String traceId = getTraceId();
		String code = getCode();
		String message = getLocalizedMessage();
		
		StringBuilder sb = new StringBuilder();
		sb.append(name+":");
		
		sb.append(traceId == null?"":"trace[ " + traceId + " ],");
		sb.append(code == null?"":"code[ " + code + " ],");
		sb.append(message == null?"":"msg[ " + message + " ]");
		
		return sb.toString();
	}

	public String getTraceId() {
		if(traceId != null){
			return traceId;
		}
		
		if(parent != null){
			traceId = parent.getTraceId();
			return traceId;
		}
		
		try{
			traceId = ExceptTraceIdGen.getInstance().getTraceId();
		}catch(Exception e){
			traceId = null;
		}
		return traceId;
	}
	public long getTimestamp(){
		return timestamp;
	}
	

}
