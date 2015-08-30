/*
 * 源程序名称: PropertiesReader.java 
 * 模块名称：工具类
 * @author lindaqun 
 * 使用方法:String path = PropertiesReader.getProperty("info_path","template.properties");
 */

package newland.base.util;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 资源文件读取类
 * @author lindaqun
 *
 */
public class PropertiesReader {

	private static Logger _log = Logger.getLogger(PropertiesReader.class);   

	private static Map<String, Properties> propsMap = new HashMap<String, Properties>();  
	
	/**  
	*   
	* 方法用途和描述: 获得属性   
	*   
	* @param propertyFile 属性文件(包括类路径)  
	* @param key 属性键  
	* @return 属性值  
	*/ 
	public static String getProperty(String key,String propertyFilePath)
	{
		Properties prop = getProperties(propertyFilePath);   
		return prop == null ? null : prop.getProperty(key);  
	}
	
	
	public static Properties getProperties(String propertyFilePath) {   
		 if (propertyFilePath == null) {   
		    _log.error("propertyFilePath is null!");   
		     return null;   
		 }   
		  Properties ppts = propsMap.get(propertyFilePath);   
		  if (ppts == null) {   
		   ppts = loadPropertyFile(propertyFilePath);   
		   if (ppts != null) {   
			   propsMap.put(propertyFilePath, ppts);   
		   }   
		  }   
		  return ppts;   
	 } 
	
	
	private static Properties loadPropertyFile( String propertyFilePath) {   
			
		Properties prop = new Properties();
		InputStream in=null;
		try {   
			    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				in=classLoader.getResourceAsStream(propertyFilePath);
				
				prop.load(in);
				
				return prop;   
		} catch (java.io.FileNotFoundException e) {   
			  _log.error("FileInputStream(\"" + propertyFilePath   + "\")! FileNotFoundException: " + e);   
			  return null;   
		} catch (java.io.IOException e) {   
			   _log.error("Properties.load(InputStream)! IOException: " + e);   
			   return null;   
		}   
    }  
	
	
	
}
