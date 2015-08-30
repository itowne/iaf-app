package newland.iaf.base.format.excel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import newland.iaf.base.format.BeanField;
import newland.iaf.base.format.BeanFieldType;
import newland.iaf.base.format.BeanFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

/**
 * 
 * @author rabbit
 * 
 */
public class ExcelBeanFormat<T> extends BeanFormat<T> {

	public ExcelBeanFormat(Class<T> cl) {
		super(cl);
		// TODO Auto-generated constructor stub
	}

	public void fromExcelRow(Row row, Field field)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, ParseException {
		BeanField ef = field.getAnnotation(BeanField.class);
		if (ef == null) {
			return;
		}

		String name = ef.name();
		if (StringUtils.isEmpty(name)) {
			name = field.getName();
		}
		int idx = ef.index();
		Cell c = row.getCell(idx);
		if (c == null) {
			return;
		}

		Object value = null;

		BeanFieldType type = fieldType(ef, field);
		String pattern = ef.pattern();
		String valueStr = null;
		switch (type) {
		case string:
			value = c.getStringCellValue();
			break;
		case datetime:
			if (DateUtil.isCellDateFormatted(c)) {
				value = c.getDateCellValue();
			} else {
				valueStr = c.getStringCellValue();
				if (StringUtils.isBlank(pattern)) {
					pattern = DATE_TIME_PATTERN;
				}
				SimpleDateFormat datetimeFormate = new SimpleDateFormat(pattern);
				value = datetimeFormate.parse(valueStr);
			}

			break;
		case date:
			if (DateUtil.isCellDateFormatted(c)) {
				value = c.getDateCellValue();
			} else {
				valueStr = c.getStringCellValue();
				if (StringUtils.isBlank(pattern)) {
					pattern = DATE_PATTERN;
				}
				SimpleDateFormat datetimeFormate = new SimpleDateFormat(pattern);
				value = datetimeFormate.parse(valueStr);
			}
			break;
		case integer:
			double i = c.getNumericCellValue();
			value = i;
			break;
		case longInteger:
			double li = c.getNumericCellValue();
			value = li;
			break;
		default:
			break;
		}
		setField(this, field, value);

	}

}
