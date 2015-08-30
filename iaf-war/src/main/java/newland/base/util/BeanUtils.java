package newland.base.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.aopalliance.intercept.Interceptor;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.CollectionUtils;

/**
 * 扩展commons包下BeanUtils类，提供额外的工具操作方法
 * @author lindaqun
 * @email lindaqun@newlandpayment.com
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils
{

	 private static Map<String,Method> methodMap = new HashMap<String,Method>();
	  
		
		
		/**获得对象属性值*/
		public static Object getObjectPropValue(Object obj,String propertyName){
			if(obj == null || "".equals(propertyName)) return null;
			Class clazz = obj.getClass();
			String key = clazz.getPackage()+"."+clazz.getName();
			if(!methodMap.containsKey(key)){
				String methodName = "get"+propertyName.substring(0, 1).toUpperCase()+propertyName.substring(1);
				Method method = null;
				try {
					method = clazz.getDeclaredMethod(methodName, new Class[]{});
				} catch (Exception e) {
					e.printStackTrace();
					method = null;
				}
				if(method == null) return null;
				methodMap.put(key, method);
			}
			Method method_ = methodMap.get(key);
			try {
				return method_.invoke(obj,null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * copyNoNullProperties的特点是： 如果有个属性orig中为空，dest中不为空则不会将dest中的覆盖掉
		 * 防止源对象中的属性为空时，被覆盖给目标对象
		 * 
		 * @param dest
		 *            Object
		 * @param orig
		 *            Object
		 * @throws IllegalAccessException
		 * @throws InvocationTargetException
		 */
		public static void copyNoNullProperties(Object target, Object source)
				throws IllegalAccessException, InvocationTargetException {
			if (target == null)
				throw new IllegalArgumentException("No destination bean specified");
			if (source == null)
				throw new IllegalArgumentException("No origin bean specified");
			if (source instanceof DynaBean) {
				DynaProperty origDescriptors[] = ((DynaBean) source).getDynaClass()
						.getDynaProperties();
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if (PropertyUtils.isWriteable(target, name)) {
						Object value = ((DynaBean) source).get(name);
						if (value != null) {
							copyProperty(target, name, value);
						}
					}
				}
			} else if (source instanceof Map) {
				for (Iterator names = ((Map) source).keySet().iterator(); names
						.hasNext();) {
					String name = (String) names.next();
					if (PropertyUtils.isWriteable(target, name)) {
						Object value = ((Map) source).get(name);
						if (value != null) {
							copyProperty(target, name, value);
						}
					}
				}
			} else {
				PropertyDescriptor origDescriptors[] = PropertyUtils
						.getPropertyDescriptors(source);
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if (!"class".equals(name)
							&& PropertyUtils.isReadable(source, name)
							&& PropertyUtils.isWriteable(target, name))
						try {
							Object value = PropertyUtils.getSimpleProperty(source,
									name);
							if (value != null) {
								copyProperty(target, name, value);
							}
						} catch (NoSuchMethodException e) {
						}
				}
			}
		}
	 
}
