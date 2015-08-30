package newland.iaf.base.format.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import newland.iaf.base.format.BeanField;
import newland.iaf.base.format.BeanFieldCache;
import newland.iaf.base.format.BeanFieldType;
import newland.iaf.base.format.BeanFormat;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author rabbit
 * 
 */
public class JsonBeanFormat<T> extends BeanFormat<T> {

	public JsonBeanFormat(Class<T> cl) {
		super(cl);
	}

	public final String format(T obj) {
		return format(obj, 0);
	}

	public final String format(T obj, int indentFactor) {
		return toJSONObject(obj).toString(indentFactor);
	}

	public final void parse(String json, T target) throws ParseException {
		JSONObject ob = JSONObject.fromObject(json);
		parseJSONObject(ob, target);
	}

	public final JSONObject toJSONObject(T obj) {
		JSONObject jsonOb = new JSONObject();
		Collection<BeanFieldCache> v = nameCacheMap.values();
		for (BeanFieldCache c : v) {
			try {
				toJSONObjectField(jsonOb, obj, c);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return jsonOb;
	}

	public final void parseJSONObject(JSONObject ob, T target) {

		Collection<BeanFieldCache> v = nameCacheMap.values();
		for (BeanFieldCache c : v) {
			try {
				parseJSONObjectImpl(ob, target, c);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		// Field[] fields = target.getClass().getDeclaredFields();
		// for (Field field : fields) {
		// try {
		// parseJSONObjectImpl(ob, field, target);
		// } catch (Exception e) {
		// throw new RuntimeException(e);
		// }
		// }
	}

	private void toJSONObjectField(JSONObject jsonOb, T target,
			BeanFieldCache cache) throws IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchMethodException,
			InvocationTargetException {

		Object value = getField(target, cache.field);
		if (value == null) {
			return;
		}
		BeanFieldType type = cache.type;
		String name = cache.name;

		switch (type) {
		case string:
			jsonOb.put(name, value);
			break;
		case datetime:
			jsonOb.put(name, cache.dateFormat.format(value));
			break;
		case date:
			jsonOb.put(name, cache.dateFormat.format(value));
			break;
		case integer:
			jsonOb.put(name, value);
			break;
		case longInteger:
			jsonOb.put(name, value);
			break;
		case classType:
			JsonBeanFormat classTypeFormat = (JsonBeanFormat) cache.beanFormat;
			jsonOb.put(name, classTypeFormat.format(value));
			break;
		case list:
			JSONArray ja = new JSONArray();
			Collection<?> c = (Collection<?>) value;
			JsonBeanFormat listFormat = (JsonBeanFormat) cache.beanFormat;
			for (Object o : c) {
				ja.add(listFormat.format(o));
			}
			jsonOb.put(name, ja);
			break;
		default:
			break;
		}
		return;
	}

	@SuppressWarnings("unchecked")
	public void parseJSONObjectImpl(JSONObject ob, T target,
			BeanFieldCache cache) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			SecurityException, NoSuchMethodException,
			InvocationTargetException, ParseException {

		String name = cache.name;
		if (!ob.containsKey(name)) {
			return;
		}
		Field field = cache.field;
		String valueStr = ob.getString(name);
		Object value = null;
		@SuppressWarnings("rawtypes")
		Class clz = field.getType();
		BeanFieldType type = cache.type;
		switch (type) {
		case string:
			value = valueStr;
			break;
		case datetime:
			value = cache.decimalFormat.parse(valueStr);
			break;
		case date:
			value = cache.decimalFormat.parse(valueStr);
			break;
		case integer:
			value = Integer.parseInt(valueStr);
			break;
		case longInteger:
			value = Long.parseLong(valueStr);
			break;
		case decimal:
			if (cache.decimalFormat != null) {
				value = cache.decimalFormat.parse(valueStr);
			} else {
				value = new BigDecimal(valueStr);
			}
			break;
		case classType:
			JSONObject in = JSONObject.fromObject(valueStr);
			Object obj = clz.newInstance();
			parseJSONObject(in, (T) obj);
			value = obj;
			break;
		case list:
			JSONArray ja = JSONArray.fromObject(valueStr);
			Type gt = field.getGenericType();
			Class<?> itemClz = null;
			for (Type t1 : ((ParameterizedType) gt).getActualTypeArguments()) {
				itemClz = (Class<?>) t1;
				break;
			}
			@SuppressWarnings({ "rawtypes" })
			Collection c = (Collection) clz.newInstance();
			for (int i = 0; i < ja.size(); i++) {
				Object item = itemClz.newInstance();
				parseJSONObject(ja.getJSONObject(i), (T) item);
				c.add(item);
			}
			value = c;
			break;
		default:
			break;
		}
		setField(target, field, value);
	}

}
