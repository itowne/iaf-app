package newland.base.util;
import java.io.UnsupportedEncodingException;

public class StringUtils extends org.apache.commons.lang.StringUtils{
	/**
	 * 手机字段正则表达式
	 */
	private static final String MOBILE_PATTERN = "^13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$";
	/**
	 * 
	 */
	private static final String INT_PATTERN = "^[0-9_]+$";
	
	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		return str.matches(INT_PATTERN);
	}
	
	/**
	 * 隐藏手机字符串部分字段
	 * @param mobileStr
	 * @return
	 */
	public static String mobileFilter(String mobileStr) {
		return mobileStr.substring(0, 3) + "****" + mobileStr.substring(7, 11);
	}
	
	/**
	 * 隐藏邮箱字符串部分字段
	 * @param mailStr
	 * @return
	 */
	public static String mailFilter(String mailStr) { 
		String[] email = mailStr.split("@");
		String tempStr = email[0].substring(0, 3);
		for(int i = 0; i < email[0].length() - 3; i++) {
			tempStr += "*";
		}
		tempStr += "@";
		tempStr += email[1];
		return tempStr;
	}
	
	/**
     * 检验手机号的准确性
     * @param mobileNum
     * @return
     */
    public static boolean isMobile(String mobileNum) {
    	return mobileNum.matches(MOBILE_PATTERN);
    }
    
    /**
     * 字节级切割,确保切割出的字符对应的字节数小等于传入参数。<p>
     * 且字符完整 
     * 
     * @param input 输入字符串
     * @param charset 字符集
     * @param max 最大字节数
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String spiltByByteMaxLength(String input,String charset,int max) throws UnsupportedEncodingException{
    	if(input == null)
    		return null;
    	int offset = 0;
    	StringBuilder sb = new StringBuilder(max);
    	for(int i = 0;i<input.length();i++){
    		offset += input.substring(i, i+1).getBytes(charset).length;
    		if(offset > max)
    			break;
    		
    		sb.append(input.charAt(i));
    	}
    	return sb.toString();
    }
    
}
