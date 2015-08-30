package newland.iaf.base.format;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author rabbit
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanField {
	String name() default "";

	boolean firstLetterUpper() default false;

	int index() default -1;

	BeanFieldType type() default BeanFieldType.auto;

	String pattern() default "";
}
