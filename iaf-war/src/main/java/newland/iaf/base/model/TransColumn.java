package newland.iaf.base.model;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import newland.base.formater.Formatter;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface TransColumn {
	/**
	 * 生成字段名称
	 * @return
	 */
	String name() default "";
	/**
	 * 格式化器类
	 * @return
	 */
	Class<? extends Formatter> formatter() default Formatter.class;
	/**
	 * 格式化格式
	 * @return
	 */
	String pattern() default "";

}
