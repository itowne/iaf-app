package newland.iaf.notice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import newland.iaf.notice.ProcessType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodStrategy {
	
	String value();
	
	ProcessType processType() default ProcessType.SYNC;
}
