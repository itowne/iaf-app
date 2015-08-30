package newland.base.exception;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 *异常返回Result 指定
 * @author syx
 * @version Ver 1.0 2011-11-24 初版
 *
 * @since Ver 1.0
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface ExcResult {
    /**
     *返回result名称
     * @return
     */
    String name();
}
