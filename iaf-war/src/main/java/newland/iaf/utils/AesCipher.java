package newland.iaf.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * 
 * 
 * @author rabbit
 * 
 */
public class AesCipher {
	private String keyStr;
	private final static String ALGORITHM = "AES";
	private String ivStr;
	private byte[] key;
	private byte[] iv;
	private Cipher encryptCipher;
	private String transformation;
	private Cipher decryptCipher;
	private static final String DEFAULT_TRANSFORMATION = "AES/CFB/NoPadding";

	public AesCipher() throws NoSuchAlgorithmException, DecoderException,
			InvalidKeyException, NoSuchPaddingException,
			InvalidAlgorithmParameterException {
		this(256);
	}

	public AesCipher(int keySize) throws NoSuchAlgorithmException,
			DecoderException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException {
		KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
		kgen.init(keySize); // 192 and 256 bits may not be available
		SecretKey skey = kgen.generateKey();
		key = skey.getEncoded();
		keyStr = Hex.encodeHexString(key);
		transformation = DEFAULT_TRANSFORMATION;

		encryptCipher = Cipher.getInstance(transformation);
		encryptCipher.init(Cipher.ENCRYPT_MODE, skey);
		iv = encryptCipher.getIV();
		if (iv != null) {
			ivStr = Hex.encodeHexString(iv);
		}
		initDecryptCipher();
	}

	public AesCipher(String keyStr, String ivStr) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, DecoderException {
		this(keyStr, ivStr, null);
	}

	public AesCipher(String keyStr, String ivStr, String transformation)
			throws DecoderException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException {
		this.keyStr = keyStr;
		iv = Hex.decodeHex(ivStr.toCharArray());
		key = Hex.decodeHex(keyStr.toCharArray());
		if (transformation == null) {
			this.transformation = DEFAULT_TRANSFORMATION;
		} else {
			this.transformation = transformation;
		}
		init();
	}

	private void init() throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException {
		initEncryptCipher();
		initDecryptCipher();
	}

	private void initEncryptCipher() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException {
		SecretKeySpec skeySpec = new SecretKeySpec(key, ALGORITHM);
		IvParameterSpec ips = new IvParameterSpec(iv);
		encryptCipher = Cipher.getInstance(transformation);
		encryptCipher.init(Cipher.ENCRYPT_MODE, skeySpec, ips);
	}

	private void initDecryptCipher() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException {
		SecretKeySpec skeySpec = new SecretKeySpec(key, ALGORITHM);
		IvParameterSpec ips = new IvParameterSpec(iv);
		decryptCipher = Cipher.getInstance(transformation);
		decryptCipher.init(Cipher.DECRYPT_MODE, skeySpec, ips);
	}

	public byte[] encrypt(byte[] buf) throws IllegalBlockSizeException,
			BadPaddingException {
		return encryptCipher.doFinal(buf);
	}

	public byte[] decrypt(byte[] buf) throws IllegalBlockSizeException,
			BadPaddingException {
		return decryptCipher.doFinal(buf);
	}

	public String decryptString(byte[] buf) throws IllegalBlockSizeException,
			BadPaddingException {
		return new String(decrypt(buf));
	}

	public String decryptString(byte[] buf, String charset)
			throws IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return new String(decrypt(buf), charset);
	}

	public byte[] encrypt(String str, String charset)
			throws IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return encryptCipher.doFinal(str.getBytes(charset));
	}

	public byte[] encrypt(String str) throws IllegalBlockSizeException,
			BadPaddingException {
		return encryptCipher.doFinal(str.getBytes());
	}

	public String getKeyStr() {
		return keyStr;
	}

	public String getIvStr() {
		return ivStr;
	}

	public String getTransformation() {
		return transformation;
	}
}
