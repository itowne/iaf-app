package com.hicard;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hicard {
	
	static Logger logger = LoggerFactory.getLogger(Hicard.class);
		
	public static String encrypt(String file, String obj) {
		try {
			String	base64ForKey = readFromFile(file);
			RSAPublicKey publicKey = (RSAPublicKey)getPublicKey(base64ForKey) ;
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(1, publicKey);
			byte[] be = cipher.doFinal(obj.getBytes());
			String str = Base64.encode(be);
			return str;
		} catch (Throwable e) {
			logger.error("加密失败", e);
			return null;
		}
	}
	public static String encrypt(String file, String obj, String charset) {
		try {
			String	base64ForKey = readFromFile(file);
			RSAPublicKey publicKey = (RSAPublicKey)getPublicKey(base64ForKey) ;
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(1, publicKey);
			byte[] be = cipher.doFinal(obj.getBytes(charset));
			String str = Base64.encode(be);
			return str;
		} catch (Throwable e) {
			logger.error("加密失败", e);
			return null;
		}
	}

	public static String decrypt(String file, String obj) {
		try {
			String	base64ForKey = readFromFile(file);
			RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(base64ForKey);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(2, privateKey);
			byte[] be = cipher.doFinal(Base64.decode(obj));
			String str = new String(be);
			return str;
		} catch (Throwable e) {
			logger.error("解密失败", e);
			throw new RuntimeException("解密失败", e);
		}
	}
	
	public static String decrypt(String file, String obj, String charset) {
		try {
			String	base64ForKey = readFromFile(file);
			RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(base64ForKey);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(2, privateKey);
			byte[] be = cipher.doFinal(Base64.decode(obj));
			String str = new String(be, 0, be.length, charset);
			return str;
		} catch (Throwable e) {
			logger.error("解密失败", e);
			throw new RuntimeException("解密失败", e);
		}
	}

	public static String sign(String file, String obj) {
		try {
			String base64ForKey = readFromFile(file);
			RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(base64ForKey);
			Signature signet = Signature.getInstance("MD5withRSA");
			signet.initSign(privateKey);
			signet.update(obj.getBytes());
			byte[] signed = signet.sign();
			String str = new String(Base64.encode(signed));
			return str;
		} catch (Throwable e) {
			logger.debug("签名失败", e);
			throw new RuntimeException("签名失败", e);
		}
	}
	
	public static String sign(String file, String obj, String charset) {
		try {
			String base64ForKey = readFromFile(file);
			RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(base64ForKey);
			Signature signet = Signature.getInstance("MD5withRSA");
			signet.initSign(privateKey);
			signet.update(obj.getBytes(charset));
			byte[] signed = signet.sign();
			String str = new String(Base64.encode(signed));
			return str;
		} catch (Throwable e) {
			logger.debug("签名失败", e);
			throw new RuntimeException("签名失败", e);
		}
	}

	public static boolean verify(String file, String obj, String signed) {
		try {
			String base64ForKey = readFromFile(file);
			RSAPublicKey publicKey = (RSAPublicKey)getPublicKey(base64ForKey) ;
			Signature signetcheck = Signature.getInstance("MD5withRSA");
			signetcheck.initVerify(publicKey);
			signetcheck.update(obj.getBytes());
			if (signetcheck.verify(Base64.decode(signed))) {
				logger.debug("签名通过");
				return true;
			}
			logger.debug("签名异常！");
		} catch (Throwable e) {
			logger.error("验签失败", e);
		}
		return false;
	}
	
	public static boolean verify(String file, String obj, String signed, String charset) {
		try {
			String base64ForKey = readFromFile(file);
			RSAPublicKey publicKey = (RSAPublicKey)getPublicKey(base64ForKey) ;
			Signature signetcheck = Signature.getInstance("MD5withRSA");
			signetcheck.initVerify(publicKey);
			signetcheck.update(obj.getBytes(charset));
			if (signetcheck.verify(Base64.decode(signed))) {
				logger.debug("签名通过");
				return true;
			}
			logger.debug("签名异常！");
		} catch (Throwable e) {
			logger.error("验签失败", e);
		}
		return false;
	}
	
	public  static PublicKey getPublicKey(String base64) throws Throwable{
		byte[] keyBytes;
		keyBytes = Base64.decode(base64);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}
	
	public static PrivateKey getPrivateKey(String base64) throws Throwable{
		byte[] keyBytes;
		keyBytes = Base64.decode(base64);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
	
	public static void saveToFile(OutputStream out, String base64) throws Throwable{
		BufferedOutputStream bout = null;
		try{
			bout = new BufferedOutputStream(out);
			bout.write(base64.getBytes("utf-8"));
			bout.flush();
		}finally{
			if (bout != null)
				bout.close();
		}
	}
	
	public static String readFromStream(InputStream in) throws Throwable{
		BufferedInputStream bin = null;
		ByteArrayOutputStream bout = null;
		try{
			bin = new BufferedInputStream(in);
			byte[] bytes = new byte[1024];
			int len = 0;
			bout = new ByteArrayOutputStream();
			while((len = bin.read(bytes)) > 0){
				bout.write(bytes, 0, len);
				bout.flush();
			}
			return bout.toString();
		}finally{
			if (bin != null){
				bin.close();
			}
			if (bout != null){
				bout.close();
			}
		}
	}
	
	public static String readFromFile(String fileName) throws Throwable{
		FileInputStream in = new FileInputStream(fileName);
		return readFromStream(in);
	}
	
	public static KeyPair genRSAKey() throws Throwable{
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024); 
		return keyPairGen.generateKeyPair();
	}
	
	public static void writeKeyToFile(String fileName, Key key) throws Throwable{
		FileOutputStream fout = new FileOutputStream(fileName);
		String base64 = Base64.encode(key.getEncoded());
		fout.write(base64.getBytes());
		fout.flush();
		fout.close();
	}
}
