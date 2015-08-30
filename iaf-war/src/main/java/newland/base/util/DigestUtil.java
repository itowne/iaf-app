package newland.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 密码加密工具
 * 
 * @author lindaqun
 * @date 2012-10-16 下午03:23:39
 * 
 */
public class DigestUtil {
	/**
	 * 做SHA加密
	 * 
	 * @param password
	 * @return
	 */
	public static String getSHA(String password) {
		byte[] buf = DigestUtils.sha(password);
		return Base64.encodeBase64String(buf);
	}

	/**
	 * 二进制转化成16进制表示，每个数字之间加入":"作为分割
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) { // 二行制转字符串
		String pwd = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				pwd = pwd + "0" + stmp;
			else
				pwd = pwd + stmp;
			if (n < b.length - 1)
				pwd = pwd + ":";
		}
		return pwd.toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println("pwd:" + DigestUtil.getSHA("123"));
		System.out.println("MD5 pwd:" + DigestUtil.getMD5("123"));
	}

	/**
	 * 做MD5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
		byte[] buf = DigestUtils.md5(str);
		return Base64.encodeBase64String(buf);
	}

	/**
	 * 二进制转化成16进制表示
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex2(byte[] b) { // 二行制转字符串
		String pwd = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				pwd = pwd + "0" + stmp;
			else
				pwd = pwd + stmp;
		}
		return pwd;
	}

}