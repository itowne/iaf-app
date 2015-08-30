/*
 * NumOrLetterValidator.java
 * 
 * 功能：
 * 类名:NumOrLetterValidator
 * 
 *   ver     变更日期    修改人    修改说明
 * ──────────────────────────────────
 *   V1.0   2011-3-4    黄瑞斌       初版
 * 
 * Copyright (c) 2006, 2010 NewlandComputer All Rights Reserved.
 * LICENSE INFORMATION
 */
package newland.base.validator.validators;


import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.RegexFieldValidator;

/**
 *
 * @author 黄瑞斌
 */
public class EmailValidator extends RegexFieldValidator {
	 private static final String EXPRESS = "^((([a-zA-Z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-zA-Z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$|^$/i";
	 public void validate(Object object) throws ValidationException {
	        super.setExpression(EXPRESS);
	        super.validate(object);
	    }
/*	 public static void main(String agrs[]){
		 String a = "请问11@请问qq.请问1comqw";
		 String b = "请问11@请问qq.1请问comqw";
		 String c = "1-1@cib-9.com.cn.12.com";
		 String d = "AAAA@sina.com";
		 Pattern pattern = Pattern.compile(EXPRESS);
		 Matcher matcher = pattern.matcher(d);
		 System.out.println(matcher.matches());
	 }*/
}
