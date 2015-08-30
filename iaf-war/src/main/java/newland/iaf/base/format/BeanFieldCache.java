package newland.iaf.base.format;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;

/**
 * 
 * @author rabbit
 * 
 */
public class BeanFieldCache {
	public Field field;
	public Integer index;
	public String name;
	public BeanField beanField;
	public BeanFieldType type;
	public DateFormat dateFormat;
	public DecimalFormat decimalFormat;
	public BeanFormat beanFormat;
	public Class<? extends Enum> enumClass;
}