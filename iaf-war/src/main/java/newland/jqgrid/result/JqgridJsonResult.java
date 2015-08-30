/*
 *  Copyright 2010 黄瑞斌.
 */
package newland.jqgrid.result;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;
import newland.jqgrid.result.util.JqExpression;
import ognl.OgnlException;

import org.apache.struts2.StrutsConstants;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 *
 * @author 黄瑞斌
 */
public class JqgridJsonResult implements Result {

    static char[] hex = "0123456789ABCDEF".toCharArray();
    private static final Logger LOG = LoggerFactory.getLogger(JqgridJsonResult.class);
    private String name = "gridtable";
    private Logger logger = LoggerFactory.getLogger(getClass());

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private StringBuffer json = new StringBuffer("");
    private String defaultEncoding = "UTF-8";
    //@Inject("myDict")

    @Inject(StrutsConstants.STRUTS_I18N_ENCODING)
    public void setDefaultEncoding(String val) {
        this.defaultEncoding = val;
    }

    public void execute(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();

        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
        // try {

        ActionSupport action = (ActionSupport) invocation.getAction();

        Class clazz = action.getClass();
        Method dateSetMethod = null;
        BeanInfo info = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] props = info.getPropertyDescriptors();
        for (int i = 0; i < props.length; ++i) {
            PropertyDescriptor prop = props[i];
            Method method = prop.getReadMethod();
            if (method != null) {
                JqDataSet jqDataSet = method.getAnnotation(JqDataSet.class);
                if (jqDataSet != null) {
                	logger.info("action dataset name: [" + jqDataSet.name() + "] , request dataset name: [" + this.name + "]");
                    if (jqDataSet.name().equals(this.name)) {
                        dateSetMethod = method;
                        break;
                    }
                }
            }
        }

        try {
            DataSet dataSet = (DataSet) dateSetMethod.invoke(action, new Object[0]);
            populatorDataSet(dataSet, dateSetMethod.getAnnotation(JqDataSet.class).content(), invocation);

            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("application/json;charset=" + defaultEncoding);

            ServletOutputStream sos = response.getOutputStream();
            sos.write(this.json.toString().getBytes(defaultEncoding));
            sos.close();

        } catch (Exception ex) {
        	logger.debug("解析JSON出错", ex);
        }
//        } catch (IOException exception) {
//            LOG.error(exception.getMessage(), exception);
//            throw exception;
//        }
    }

    private void populatorDataSet(DataSet dataSet, String descriptions, ActionInvocation invocation) {

        String[] descs = getDescriptions(descriptions);
        this.add("{");
        this.add("page", dataSet.getPage());
        this.add("records", dataSet.getRecords());
        if (dataSet.getSidx() != null);
        this.add("sidx", dataSet.getSidx());
        if (dataSet.getSord() != null);
        this.add("sord", dataSet.getSord());
        //this.add("\"total\":"+dataSet.getTotal().toString());
        this.add("total", dataSet.getTotal());
        this.add("\"rows");
        this.add("\":[");
        JqExpression jqExpression = new JqExpression(invocation);
        for (int i = 0; i < dataSet.getGridModel().size(); i++) {
            Object object = dataSet.getGridModel().get(i);
            jqExpression.setData(dataSet.getGridModel().get(i));
            this.add("{\"cell\":[");
            for (int j = 0; j < descs.length; j++) {
                String str = "";
                try {
                    str = jqExpression.eval(descs[j]).toString();
                } catch (Exception ex) {
                	logger.debug("计算字段值出错", ex);
                }
                if(str==null)str="";
                this.add('"');
                this.escapseString(str);
                this.add('"');
                if (j < (descs.length - 1)) {
                    this.add(",");
                }
            }
            this.add("]");
            this.add(",\"id\":");
            this.string(i);
            this.add("}");
            if (i < (dataSet.getGridModel().size() - 1)) {
                this.add(",");
            }
        }
        this.add("]}");
    }

    private String[] getDescriptions(String descriptions) {
        String[] arr = descriptions.split(",");
        return arr;
    }

    private void add(String name, Object value, boolean hasData) {

        this.add('"');
        this.add(name);
        this.add("\":");
        this.string(value);
        if (hasData) {
            this.add(',');
        }
    }

    private void add(String name, Object value) {
        this.add(name, value, true);
    }

    private void add(Object obj) {
        this.json.append(obj);
    }

    private void escapseString(Object obj) {
        CharacterIterator it = new StringCharacterIterator(obj.toString());

        for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
            if (c == '"') {
                this.add("\\\"");
            } else if (c == '\\') {
                this.add("\\\\");
            } else if (c == '/') {
                this.add("\\/");
            } else if (c == '\b') {
                this.add("\\b");
            } else if (c == '\f') {
                this.add("\\f");
            } else if (c == '\n') {
                this.add("\\n");
            } else if (c == '\r') {
                this.add("\\r");
            } else if (c == '\t') {
                this.add("\\t");
            } else if (Character.isISOControl(c)) {
                this.unicode(c);
            } else {
                this.add(c);
            }
        }
    }

    private void string(Object obj) {
        this.add('"');

        escapseString(obj);

        this.add('"');
    }

    /**
     * Represent as unicode
     *
     * @param c
     *            character to be encoded
     */
    private void unicode(char c) {
        this.add("\\u");

        int n = c;

        for (int i = 0; i < 4; ++i) {
            int digit = (n & 0xf000) >> 12;

            this.add(hex[digit]);
            n <<= 4;
        }
    }
}
