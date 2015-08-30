package newland.base.util;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
/**
 * Bean方法拦截
 * @author new
 *
 */
public abstract class BeanProxy implements MethodInterceptor {

	private Object target;

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		if (methodCheck(method)){
			return proxy.invokeSuper(target, args);
		}
		return null;
	}

	protected abstract boolean methodCheck(Method method);

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Object obj, BeanProxy proxy ){
		Enhancer enhancer = new Enhancer ();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(proxy);
		return (T) enhancer.create();
	}

}
