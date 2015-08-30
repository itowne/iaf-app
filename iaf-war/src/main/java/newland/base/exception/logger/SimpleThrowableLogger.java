package newland.base.exception.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;

import newland.base.exception.ExceptTraceIdGen;
import newland.base.exception.base.Traceable;


/**
 * <p>通用系统边界日志记录实现</p>
 * 
 * @author lance
 *
 */
public class SimpleThrowableLogger  implements ThrowableLogger{
	
	private static SimpleThrowableLogger instance;
	
	public static final ThrowableLogger getLogger(){
		if(instance == null){
			instance = new SimpleThrowableLogger();
		}
		return instance;
	}
	
	private String getTraceId(Throwable e){
		String traceId = null;
		if(e instanceof Traceable){
			traceId = ((Traceable) e).getTraceId();
		}
		if(traceId == null){
			traceId = ExceptTraceIdGen.getInstance().getTraceId();
		}
		return traceId;
	}

	private String getTimestampDescription(Throwable e){
		long millis = 0;
		if(e instanceof Traceable){
			millis = ((Traceable) e).getTimestamp();
		}else{
			millis = System.currentTimeMillis();
		}
		Date date = new Date();
		date.setTime(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
		
		return sdf.format(date);
	}
	
	public String logDebug(Logger logger,String msg, Throwable e) {
		String traceId = getTraceId(e);
		String tms = getTimestampDescription(e);
		if(logger.isDebugEnabled()){
			logger.debug("["+tms+"-"+traceId+"]"+msg,e);
		}
		return traceId;
	}
	public String logInfo(Logger logger,String msg, Throwable e) {
		String traceId = getTraceId(e);
		String tms = getTimestampDescription(e);
		if(logger.isInfoEnabled()){
			logger.info("["+tms+"-"+traceId+"]"+msg,e);
		}
		return traceId;
	}
	public String logWarn(Logger logger,String msg, Throwable e){
		String traceId = getTraceId(e);
		String tms = getTimestampDescription(e);
		if(logger.isWarnEnabled()){
			logger.warn("["+tms+"-"+traceId+"]"+msg,e);
		}
		return traceId;
	}
	public String logError(Logger logger,String msg, Throwable e) {
		String traceId = getTraceId(e);
		String tms = getTimestampDescription(e);
		if(logger.isErrorEnabled()){
			logger.error("["+tms+"-"+traceId+"]"+msg,e);
		}
		return traceId;
	}




	
}