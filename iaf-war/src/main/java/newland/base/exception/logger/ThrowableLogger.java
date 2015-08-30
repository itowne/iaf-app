package newland.base.exception.logger;

import org.slf4j.Logger;

/**
 * <p>通用系统边界日志记录接口</p>
 * <p>该处接口实现基于<tt>org.slf4j.Logger</tt></p>
 * 
 * <p>
 * 	尽管在系统内部的异常类统一的有追踪号进行处理，但由于对于大量未知的，非可追踪的
 * （<i>Traceable</i>）类的异常，无法直接通过异常对象获取跟踪号。
 * 该接口实现将统一约束生成追踪号以及边界处记录日志的行为。
 * </p>
 * 
 * <p>
 * 对于系统内部记录异常的行为，将由各自系统内部实现决定，并不做约束。
 * </p>
 * <p>
 * 对于通常的异常处理，一般分为2种，一种：
 * </p>
 * <p><blockquote><pre>
 *  try{
 *  	...
 *  }catch(AppBizException e){
 *  	logger.error("xxxx",e);
 *  
 *     do something...
 *     return;
 *  }
 * </pre></p></blockquote>
 * <p>这种情况，一般需要做通用性的日志记录，以保证程序异常时切面的状态。 </p>
 * 
 * 
 *<p>但对于：</p>
 *<p><blockquote><pre>
 *  try{
 *  	...
 *  }catch(AppBizException e){
 *  	throw new AppRTException(e.getCode(),"",e);
 *  }
 *	</pre></p></blockquote>
 *<p>一般不需要做异常记录处理，该异常的完整堆栈以及状态描述，将在系统边界的拦截器中，由该记录器统一处理。</p>
 *
 * 
 * 
 * @author dvlp
 *
 */
public interface ThrowableLogger {

	public String logDebug(Logger logger,String msg, Throwable e) ;
	
	public String logInfo(Logger logger,String msg, Throwable e);
	
	public String logWarn(Logger logger,String msg, Throwable e);
	
	public String logError(Logger logger,String msg, Throwable e);
	
}
