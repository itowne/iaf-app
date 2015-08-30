package newland.base.exception.locale;

import java.util.Locale;

/**
 *<p>异常消息本地格式化翻译接口</p>
 *<p>该接口调度一般发生在界面层或同其他外部系统边界交互，需要进行异常
 *本地化时。</p>
 * 
 * @author lance
 */
public interface ThrowableTranslate {

	/**
	 * <p>
	 * 		根据传入异常对象<tt>Throwable e</tt>和
	 * 		本地化对象<tt>Locale locale</tt>
	 *      格式化成具体信息
	 * </p>
	 * <p>对处理过程异常一律返回空对象<tt>null</tt></p>
	 * 
	 * @param e
	 * @param locale
	 * @return
	 */
	public String getDescription(Throwable e,Locale locale);
	
}
