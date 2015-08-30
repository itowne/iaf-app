package newland.base.exception.base;

import newland.base.exception.ExceptTraceIdGen;

/**
 * 基于<tt>Traceable,Describable</tt>的Exception参考实现
 * <p>
 * 尽管基于理想状态，<code>traceId</code>应该被定义为<code>final<code>。
 * 但考虑到<code>traceId</code>必须延迟到<code>getTracdId()</code>时才生成。
 * 为了避免在序列化时，有会可能丢失<code>traceId</code>的信息(<code>parent<code>被声明为<code>transient<code>)，
 * 尽管此时系统此时可能已经调用过<code>getTracdId()</code>方法，
 * 但如果被声明为<code>final</code>，将不可能在<code>getTracdId()</code>内进行赋值操作。
 * <p>
 * 当前实现将在第一次获取<code>parent.getTraceId()</code>时(<i>tracdId==null</i>)，进行当前对象的traceId赋值。
 * <p>
 * 
 * 对该对象进行序列化前，确保调用<tt>getTraceId()</tt>，保证该对象状态正确。
 * 
 * @author lance
 *
 */
public class CommonException extends Exception implements Traceable,Describable{
	
	/**
	 * version 1.0
	 */
	private static final long serialVersionUID = 1L;

	private final String code;
	
	private transient final Traceable parent;
	
	private final Long timestamp;
	
	private String traceId;	

	public CommonException(String code,String msg){
		super(msg);
		this.code = code;
		
		timestamp = System.currentTimeMillis();
		parent = null;
	}
	
	public CommonException(String code,String msg,Throwable e){
		super(msg,e);
		
		this.code = code;
		
		if(e instanceof Traceable){
			parent = (Traceable) e;
			timestamp = ((Traceable)e).getTimestamp();
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
