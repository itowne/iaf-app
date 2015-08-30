package newland.base.exception.locale;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import newland.base.exception.base.Describable;
import newland.base.exception.base.Paramsable;


/**
 * <p>异常本地化接口<tt>ThrowableTranslate</tt>的抽象类实现</p>
 * 
 * @author dvlp
 *
 */
public abstract class AbstractThrowableTranslate implements ThrowableTranslate{

	private static final String _UNKNOWN_EXCEPTION_CODE = "UNKNOWN_EXP";
	
	private static final String _UNKNOWN_EXCEPTION_MSG_OTHERS = "SYS FAILED FOR UNKNOWN EXCEPTION!";
	
	private static final String _UNKNOWN_EXCEPTION_MSG_ZHCN = "系统异常，未知错误！";
	
	public String getDescription(Throwable e, Locale locale) {
		String description = null;
		MessageSource  messageSource = getMessageSource(e);
		if(messageSource != null){
			if(e instanceof Describable){
					if (e instanceof Paramsable) {
						try{
							description = messageSource.getMessage(((Describable) e)
								.getCode(), ((Paramsable) e).getParams(), locale);
						}catch(NoSuchMessageException exception){
						} 
					} else {
						try{
							description = messageSource.getMessage(((Describable) e)
								.getCode(), null, locale);
						}catch(NoSuchMessageException exception){
						}
					}
				}
			}
			if (description == null && messageSource != null) {
				try{
					description = translateUnknownExceptionByMessageSource(e, messageSource,locale);
				}catch(NoSuchMessageException exception){
				}
			}
		
		if (description == null) {
			description = translateUnknownException(e,locale);
		}
		
		return description;
	}
	
	protected String translateUnknownException(Throwable e, Locale locale) {
		if(locale.equals(Locale.SIMPLIFIED_CHINESE)){
			return _UNKNOWN_EXCEPTION_MSG_ZHCN;
		}else{
			return _UNKNOWN_EXCEPTION_MSG_OTHERS;
		}
	}

	protected String translateUnknownExceptionByMessageSource(Throwable e,MessageSource messageSource, Locale locale) {
		return messageSource.getMessage(_UNKNOWN_EXCEPTION_CODE,new Object[]{e.getMessage()}, locale);
	}

	protected abstract MessageSource getMessageSource(Throwable e);
	

}
