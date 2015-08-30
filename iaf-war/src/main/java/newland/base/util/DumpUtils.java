package newland.base.util;

import org.apache.commons.lang.ArrayUtils;

public class DumpUtils {
	
	private static char[] HEX_VOCABLE = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	public static String toBinStr(byte[] bs){
		StringBuilder sb = new StringBuilder();
		for(byte b:bs){
			for(int i = 0;i<8;i++){
				sb.insert(0, b & 01);
				b = (byte) (b >> 1);
			}
			sb.insert(0," ");
		}
		return sb.toString();
	}
	
	public static String bytesToHex(byte[] bs){
		StringBuilder sb = new StringBuilder();
		for(byte b:bs){
			int high = (b >> 4) & 0x0f;
			int low = b & 0x0f;
			sb.append(HEX_VOCABLE[high]);
			sb.append(HEX_VOCABLE[low]);
		}
		return sb.toString();
	}
	
	public static String HexToASCII(String str){
		String[] strs = strToArray(str);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<strs.length;){
			String temstr = strs[i]+strs[i+1];
			int high = Integer.decode("0x" + temstr)>>4;
			int low = (Integer.decode("0x" + temstr) & 0x0f);
			int l = 0x30;
			int h1 = Integer.decode("0x" + Integer.toHexString(high+l));
			int h2 = Integer.decode("0x" + Integer.toHexString(low+l));
			sb.append((char)h1);
			sb.append((char)h2);
			i=i+2;
		}
		return sb.toString();
	}
	
	public static String[] strToArray(String str){
		String[] strs = new String[str.length()];
		for(int i=0;i<str.length();i++){
			strs[i] = String.valueOf(str.charAt(i));
		}
		return strs;
	}
	
	
	public static byte[] hexToBytes(String hex){
		if(hex.length() % 2 != 0)
			throw new IllegalArgumentException("input string should be any multiple of 2!");
		hex.toUpperCase();
		
		byte[] byteBuffer = new byte[hex.length() / 2];
		
		byte padding = 0x00;
		boolean paddingTurning = false;
		for(int i = 0; i< hex.length();i++){
			if(paddingTurning){
				char c = hex.charAt(i);
				int index = indexOf(hex, c);
				padding = (byte) ((padding << 4) | index);  
				byteBuffer[i/2] = padding;
				padding = 0x00;
				paddingTurning = false;
			}else{
				char c = hex.charAt(i);
				int index = indexOf(hex, c);
				padding = (byte)(padding | index);
				paddingTurning = true;
			}
			
		}
		return byteBuffer;
	}
	
	private static int indexOf(String input,char c){
		int index = ArrayUtils.indexOf(HEX_VOCABLE, c);
		if(index < 0){
			throw new IllegalArgumentException("err input:"+input);
		}
		return index;
	}

}
