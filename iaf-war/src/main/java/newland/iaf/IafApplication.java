package newland.iaf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import newland.iaf.utils.DateUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IafApplication {
	
	static Logger logger = LoggerFactory.getLogger(IafApplication.class);
	
	/**
	 * 操作系统类型
	 */
	public static final String OS_LEVEL = "os.arch";
	/**
	 * 登录超时时间
	 */
	static int LOING_TIME_OUT = 900;
	public static int getSessionTimeout(){
		return LOING_TIME_OUT;
	}
	
	private static String applicationPath;
	
	static {
		String parent = System.getProperty("applicationPath");
		if (StringUtils.isBlank(parent)){
			File file = new File("");
			parent = file.getAbsolutePath();
		}
		applicationPath = parent + "/application";
	}
	
	public static final boolean isWindows(){
		String osarch = System.getProperty(OS_LEVEL);
		if (StringUtils.isBlank(osarch)) return false;
		if (osarch.equalsIgnoreCase("x86")) return true;
//		if (osarch.equalsIgnoreCase("amd64")) return true;
		return false;
	}
	
	public static String renderPathSuffix(String path){
		if (StringUtils.isNotBlank(path)){
			boolean hasSuffix = false;
			if (StringUtils.lastIndexOf(path, "/") == path.length() - 1){
				hasSuffix = true;
			}
			if (hasSuffix == false){
				if (StringUtils.lastIndexOf(path, "\\") == path.length() - 1){
					hasSuffix = true;
				}
			}
			if (hasSuffix == false){
				return path + "/";
			}else{
				return path;
			}
		}
		return "/";
	}
	
	public static String getApplicationPath(){
		return applicationPath;
	}
	
	public static String getPdfFontPath(){
		String font = System.getProperty("gen_pdf_font");
		if (StringUtils.isBlank(font)) {
			if (isWindows()){
				font = "c:\\windows\\fonts\\simsun.ttc";
			}else{
				font = getApplicationPath() + "/pdffont/simsun.ttc";
			}
			logger.warn("没有配置PDF生成字库, 使用系统默认字库！[" + font + "]");
		} 
		return font;
	}

	
	public static String getKeyFileTempPath(){
		String path = System.getProperty("key_file_path");
		if (StringUtils.isBlank(path)){
			path = applicationPath + "/keyfile";
			logger.warn("未配置密钥保存路径，使用默认路径[" + path + "]");
		}
		File temp = new File(path);
		if (temp.exists() == false) temp.mkdirs();
		return path;
	}
	
	public static String creditReportPath(){
		String path = System.getProperty("rpt_file_path");
		if (StringUtils.isBlank(path)){
			path = applicationPath + "/rpt";
			logger.warn("经营数据报告保存路径未设置, 使用系统默认路径！[" + path + "]");
			
		}
		File temp = new File(path);
		if (temp.exists() == false) temp.mkdirs();
		return path;
	}
	
	static String testFile = "runtime.properties";
	
	public static boolean isTest(){
		try {
			Properties props = getProps();
			if (props == null) return false;
			String test = props.getProperty("test");
			if (StringUtils.isBlank(test)) return false;
			boolean isTest = Boolean.valueOf(test);
			if (isTest){
				logger.debug("使用测试模式");
			}else{
				logger.debug("使用非测试模式");
			}
			return isTest;
		} catch (Throwable e) {
			logger.warn("载入文件出错", e);
			return false;
		} 

	}
	
	private static Properties props;
	
	private static long lastModify = -1;
	
	public static Properties getProps() throws Throwable{
		File file = new File(testFile);
		if (file.exists() == false) return null;
		boolean modify = true;
		synchronized(IafApplication.class){
			if (lastModify < 0){
				lastModify = file.lastModified();
				modify = true;
			}else if (file.lastModified() != lastModify){
				modify = true;
			}else{
				modify = false;
			}
			if (modify){
				props = new Properties();
				props.load(new FileInputStream(file));
			}
		}
		return props;
	}
	
	public static String getProperties(String key){
		try {
			Properties props = getProps();
			if (props == null) return "";
			String value = props.getProperty(key);
			return value;
		}catch(Throwable e){
			return "";
		}
	}
	
	public static Date roundToBeginDate(Date date){
		if (date == null) return null;
		return DateUtils.roundDate(date, Calendar.DATE);
	}
	
	public static Date roundToEndDate(Date date){
		if (date == null) return null;
		date = DateUtils.rollDate(date, Calendar.DATE, 1);
		return DateUtils.roundDate(date, Calendar.DATE);
	}
	
	public static Date getYestoday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date yestoday = calendar.getTime();
		return yestoday; 
	}
	
	public static boolean isRollback(){
		try {
			Properties props = getProps();
			if (props == null) return true;
			String test = props.getProperty("rollback");
			if (StringUtils.isBlank(test)) return true;
			boolean isRollback = Boolean.valueOf(test);
			if (isRollback){
				logger.debug("使用回滚模式");
			}else{
				logger.debug("使用不回滚模式");
			}
			return isRollback;
		} catch (Throwable e) {
			logger.warn("载入文件出错", e);
			return true;
		} 
	}
	
	public static void main(String[] args){
		System.err.println(IafApplication.roundToBeginDate(new Date()));
	}
	
	public static String getCustomerUid(){
		return getProperties("customerUid");
	}
	
	public static boolean isSameSettleAcct(){
		try {
			Properties props = getProps();
			if (props == null) return true;
			String test = props.getProperty("same_settle_acct");
			if (StringUtils.isBlank(test)) return false;
			boolean isSame = Boolean.valueOf(test);
			if (isSame){
				logger.debug("相同的交易账户与清算账户");
			}else{
				logger.debug("交易账户为商户交易账户");
			}
			return isSame;
		} catch (Throwable e) {
			logger.warn("载入文件出错", e);
			return true;
		} 
	}

}
