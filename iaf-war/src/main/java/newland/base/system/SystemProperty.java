package newland.base.system;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * <p>系统参数类</p>
 * 
 * 
 * @author lance
 *
 */
public final class SystemProperty {
	
	private static Logger logger = LoggerFactory.getLogger(SystemProperty.class);
	
	private Properties systProps;
	
	private static SystemProperty instance;
	
	private static String _SYST_PROPERTIES="system.properties";
	
	private static String _KEY_SYSTNAME = "systname";
	
	private static String _MACHINE_ID = null; 
	
	private SystemProperty(){
		init();
	}
	
	public static SystemProperty getInstance(){
		if(instance == null){
			instance = new SystemProperty();
		}
		return instance;
	}
	
	private void init(){
		if(systProps == null){
			URL url = getClassLoader().getResource(_SYST_PROPERTIES);
			if(url == null){
				if(logger.isDebugEnabled())
					logger.debug("try to load syst-property from file:"+_SYST_PROPERTIES+",but not exists!");
				return;
			}
						
			if(logger.isDebugEnabled())
				logger.debug("try to load syst-property from file:"+_SYST_PROPERTIES);
			
			try{
				InputStream is = url.openStream();
				systProps = new Properties();
				systProps.load(is);
			}catch(Exception e){
				logger.error("load syst-property from file:"+_SYST_PROPERTIES+" failed!the url is:"+url.toExternalForm(),e);
			}
			
		}
	}
	
	public String getSysName(){
		return getSystProperty(_KEY_SYSTNAME);
	}	
	
	public String getMachineID(){
		if(_MACHINE_ID == null){
			String hostname = null;
			try {
				hostname = InetAddress.getLocalHost().getHostName();
			} catch (Exception e) {
				logger.error("get Machineid failed for:",e);
			}
			if(hostname != null){
				try{
					_MACHINE_ID = hostname.substring((hostname.length()<2)?0:(hostname.length()-2));
				}catch(Exception e){
					logger.error("get Machineid failed for:",e);
				}
			}
		}
		return _MACHINE_ID;
	}
	
	public String getSystProperty(String key){
		if(systProps == null)
			return getInnerSystProperty(key);
		String value = systProps.getProperty(key);
		if(value == null){
			return getInnerSystProperty(key);
		}
		return value;
	}
	
	private String getInnerSystProperty(String key){
		String value = System.getProperty(key);
		return value;
	}

	private ClassLoader getClassLoader(){
		try{
			return Thread.currentThread().getContextClassLoader();
		}catch(Exception e){
		}
		return ResourceUtils.class.getClassLoader();
	}
	
}
