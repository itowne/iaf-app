package newland.base.exception.locale;

import org.springframework.context.MessageSource;

/**
 * <p>通用异常格式化器</p>
 * @author dvlp
 *
 */
public class SimpleExceptionTranslate extends AbstractThrowableTranslate{
	
	private MessageSource messageSource;
	
	@Override
	protected MessageSource getMessageSource(Throwable e) {
		return messageSource;
	}
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	

}
