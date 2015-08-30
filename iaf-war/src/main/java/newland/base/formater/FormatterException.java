/*
 * Created on 2004-7-11
 *
 * Project: jasine.base
 */
package newland.base.formater;

import newland.base.exception.AppRTException;


/**
 * FormatterException
 * Project: jasine.base
 * @author shen
 *
 * 2004-7-11
 */
public class FormatterException extends AppRTException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormatterException(Formatter formatter, String message) {
	    super("FORMATTER_DEFAULT_ERR","格式化器:"+formatter.getName()+", 错误:"+message);
	}

	public FormatterException(Formatter formatter, String message, Throwable cause) {
	    super("FORMATTER_DEFAULT_ERR","格式化器:"+formatter.getName()+", 错误:"+message, cause);
	}
}
