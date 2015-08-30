package newland.iaf.base.format;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.TypeUtils;

/**
 * 
 * @author rabbit
 * 
 */
public abstract class BeanFormat<T> {
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DATE_PATTERN = "yyyy-MM-dd";

	protected Class<T> targetClass;

	protected Map<Integer, BeanFieldCache> indexCacheMap = new TreeMap<Integer, BeanFieldCache>();

	protected Map<String, BeanFieldCache> nameCacheMap = new TreeMap<String, BeanFieldCache>();

	public BeanFormat(Class<T> cl) {
		targetClass = cl;
		init();
	}

	// @SuppressWarnings("unchecked")
	// public BeanFormat() {
	//
	// Type genType = getClass().getGenericSuperclass();
	// Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
	// targetClass = (Class<T>) params[0];
	// init();
	// }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
		Field[] fields = targetClass.getDeclaredFields();
		for (Field field : fields) {
			BeanField bf = field.getAnnotation(BeanField.class);
			if (bf == null) {
				continue;
			}
			BeanFieldCache c = new BeanFieldCache();
			c.beanField = bf;
			c.field = field;
			c.type = fieldType(bf, field);
			c.index = bf.index();

			String name = bf.name();
			if (StringUtils.isEmpty(name)) {
				name = field.getName();
			}
			if (bf.firstLetterUpper() == true) {
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
			}
			c.name = name;

			String pattern = bf.pattern();
			switch (c.type) {
			case datetime:
				if (StringUtils.isBlank(pattern)) {
					pattern = DATE_TIME_PATTERN;
				}
				c.dateFormat = new SimpleDateFormat(pattern);
				break;
			case date:
				if (StringUtils.isBlank(pattern)) {
					pattern = DATE_PATTERN;
				}
				c.dateFormat = new SimpleDateFormat(pattern);
				break;
			case decimal:
				if (!StringUtils.isBlank(pattern)) {
					c.decimalFormat = new DecimalFormat(pattern);
				}
				break;
			case enumType:
				c.enumClass = (Class<? extends Enum>) field.getType();
				break;
			case classType:
				Class clz = (Class) field.getType();
				try {
					c.beanFormat = ConstructorUtils.invokeConstructor(
							this.getClass(), clz);
				} catch (Exception e) {
					System.err.println(clz.toString());
					throw new RuntimeException("clz=" + clz.toString(), e);
				}
				break;
			default:
				break;
			}

			if (c.index != -1 && indexCacheMap.containsKey(c.index)) {
				throw new RuntimeException("index=[" + c.index + "] conflict");
			}
			indexCacheMap.put(c.index, c);

			if (nameCacheMap.containsKey(c.name)) {
				throw new RuntimeException("name=[" + c.name + "] conflict");
			}
			nameCacheMap.put(c.name, c);

		}

	}

	protected BeanFieldType fieldType(BeanField jf, Field field) {
		Class<?> clz = field.getType();
		BeanFieldType type = jf.type();
		clz.isEnum();
		if (type.equals(BeanFieldType.auto)) {
			if (clz.isAssignableFrom(String.class)) {
				type = BeanFieldType.string;
			} else if (clz.isAssignableFrom(Integer.class)) {
				type = BeanFieldType.integer;
			} else if (clz.isAssignableFrom(Long.class)) {
				type = BeanFieldType.longInteger;
			} else if (clz.isAssignableFrom(BigDecimal.class)) {
				type = BeanFieldType.decimal;
			} else if (clz.isAssignableFrom(Date.class)) {
				type = BeanFieldType.datetime;
			} else if (Collection.class.isAssignableFrom(clz)) {
				type = BeanFieldType.list;
			} else if (clz.isEnum()) {
				type = BeanFieldType.enumType;
			} else {
				type = BeanFieldType.classType;
			}
		}
		return type;
	}

	protected Object getField(Object target, Field field)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		return FieldUtils.readField(field, target, true);
		// Class<?> clz = field.getDeclaringClass();
		// String name = field.getName();
		// String methodName = "get" + name.substring(0, 1).toUpperCase()
		// + name.substring(1);
		// Method method = null;
		// try {
		// method = clz.getDeclaredMethod(methodName, (Class[]) null);
		// } catch (Exception e) {
		// methodName = "get" + name;
		// method = clz.getDeclaredMethod(methodName, (Class[]) null);
		// }
		// return method.invoke(target, (Object[]) null);
	}

	protected void setField(Object target, Field field, Object value)
			throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchMethodException, InvocationTargetException {
		FieldUtils.writeField(field, target, value, true);
		// String name = null;
		// Class<?> clz = null;
		// if (field.isAccessible()) {
		// field.set(target, value);
		// } else {
		// clz = field.getDeclaringClass();
		// Class<?> fieldClz = field.getType();
		// name = field.getName();
		//
		// String methodName = "set" + name.substring(0, 1).toUpperCase()
		// + name.substring(1);
		// Method method = null;
		// Class<?>[] params = { fieldClz };
		// try {
		// method = clz.getDeclaredMethod(methodName, params);
		// } catch (Exception e) {
		// methodName = "set" + name;
		// method = clz.getDeclaredMethod(methodName, params);
		// }
		// Object[] objs = { value };
		// method.invoke(target, objs);
		// }
	}

	public static void main(String[] args) {
		List<Integer> xx = new ArrayList<Integer>();
		List<Date> xx2 = new ArrayList<Date>();

		Class<? extends List> clz = xx.getClass();
		Class<? extends List> clz2 = xx.getClass();

		System.out.println(clz);
		System.out.println(clz2);

		System.out.println(clz.hashCode());
		System.out.println(clz2.hashCode());

		System.out.println(ccc(clz));
		System.out.println();

	}

	private static Type ccc(Class<? extends List> clz) {
		Type genType = clz.getGenericSuperclass();
		TypeVariable<?>[] xx = clz.getTypeParameters();
		System.out.println(xx[0].getGenericDeclaration());
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return params[0];
	}

	public static Class xxx(Field field) {
		Type gt = field.getGenericType();
		Class<?> itemClz = null;
		for (Type t1 : ((ParameterizedType) gt).getActualTypeArguments()) {
			itemClz = (Class<?>) t1;
			break;
		}
		return itemClz;
	}

}
