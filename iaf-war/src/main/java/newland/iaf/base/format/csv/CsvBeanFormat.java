package newland.iaf.base.format.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import newland.iaf.base.format.BeanField;
import newland.iaf.base.format.BeanFieldCache;
import newland.iaf.base.format.BeanFieldType;
import newland.iaf.base.format.BeanFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;

/**
 * 
 * @author rabbit
 * 
 */
public class CsvBeanFormat<T> extends BeanFormat<T> {
	private static Logger log = LoggerFactory.getLogger(CsvBeanFormat.class);

	public CsvBeanFormat(Class<T> cl) {
		super(cl);
	}

	public List<T> parseList(InputStream is, String charSet, int skipLine)
			throws IOException, IllegalArgumentException, SecurityException,
			ParseException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, InstantiationException {
		InputStreamReader isr = new InputStreamReader(is, charSet);
		return parseList(isr, skipLine);
	}

	public List<T> parseList(Reader reader, int skipLine) throws IOException,
			IllegalArgumentException, SecurityException, ParseException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, InstantiationException {
		BufferedReader br = new BufferedReader(reader);
		for (int i = 0; i < skipLine; i++) {
			br.readLine();
		}
		CsvReader cr = new CsvReader(br);
		try {
			return parseList(cr);
		} finally {
			cr.close();
		}
	}

	public List<T> parseList(CsvReader cr) throws IOException,
			IllegalArgumentException, SecurityException, ParseException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, InstantiationException {
		ArrayList<T> targetList = new ArrayList<T>();
		int num = 0;
		while (cr.readRecord()) {
			T t = parse(cr);
			targetList.add(t);
			num++;
			if (num % 1000 == 0) {
				log.debug("parse " + num);
			}
		}
		return targetList;
	}

	public T parse(CsvReader cr) throws IllegalArgumentException,
			SecurityException, ParseException, IOException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, InstantiationException {
		T target = (T) targetClass.newInstance();
		if (indexCacheMap.isEmpty()) {
			return target;
		}
		Collection<BeanFieldCache> v = indexCacheMap.values();
		for (BeanFieldCache c : v) {
			parse(cr, target, c);
		}
		return target;

	}

	private void parse(CsvReader cr, Object target, BeanFieldCache cache) {
		try {
			parseImpl(cr, target, cache);
		} catch (Exception e) {
			throw new RuntimeException("field=" + cache.field.getName(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private void parseImpl(CsvReader cr, Object target, BeanFieldCache cache)
			throws ParseException, IOException, IllegalArgumentException,
			SecurityException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		BeanField sf = cache.beanField;
		int idx = sf.index();
		String str = cr.get(idx);
		if (str == null) {
			return;
		}

		Object value = null;
		if (StringUtils.isEmpty(str)) {
			return;
		}

		BeanFieldType type = cache.type;
		switch (type) {
		case string:
			value = str;
			break;
		case datetime:
			value = cache.dateFormat.parse(str);
			break;
		case date:
			value = cache.dateFormat.parse(str);
			break;
		case integer:
			value = Integer.parseInt(str);
			break;
		case longInteger:
			value = Long.parseLong(str);
			break;
		case decimal:
			if (cache.decimalFormat != null) {
				value = cache.decimalFormat.parse(str);
			} else {
				value = new BigDecimal(str);
			}
			break;
		case enumType:
			value = Enum.valueOf(cache.enumClass, str);
			break;
		default:
			break;
		}
		setField(target, cache.field, value);
	}
}
