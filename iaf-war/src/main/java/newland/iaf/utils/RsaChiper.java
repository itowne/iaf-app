package newland.iaf.utils;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author rabbit
 * 
 */
public class RsaChiper {

	private static final String algorithm = "RSA";

	private String transformation;

	private String securityProviderStr;

	private Provider securityProvider = null;

	private RSAPublicKey publicKey;

	private RSAPrivateKey privateKey;

	private int keysize = 0;

	public RsaChiper() {
		this(null, (String) null);
	}

	public void init() throws NoSuchAlgorithmException {
		if (publicKey == null && privateKey == null) {
			if (keysize <= 0) {
				throw new RuntimeException("keysize must set.");
			}
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			keygen.initialize(keysize);
			KeyPair keys = keygen.genKeyPair();
			publicKey = (RSAPublicKey) keys.getPublic();
			privateKey = (RSAPrivateKey) keys.getPrivate();
		}
	}

	public String toStirng() {
		StringBuilder sb = new StringBuilder();
		sb.append("publicKey:\n");
		sb.append("  encode:"
				+ Hex.encodeHexString(publicKey.getEncoded()).toUpperCase()
				+ "\n");
		sb.append("  algorithm:" + publicKey.getAlgorithm() + "\n");
		sb.append("  format:" + publicKey.getFormat() + "\n");
		BigInteger bi = publicKey.getModulus();
		byte[] buf = bi.toByteArray();
		sb.append("  modulus:\n");
		sb.append("    int: " + bi + "\n");
		sb.append("    hex: " + Hex.encodeHexString(buf).toUpperCase() + "\n");
		sb.append("    base64: " + Base64.encodeBase64String(buf) + "\n");

		bi = publicKey.getPublicExponent();
		buf = bi.toByteArray();
		sb.append("  public exponent:\n");
		sb.append("    int: " + bi + "\n");
		sb.append("    hex: " + Hex.encodeHexString(buf) + "\n");
		sb.append("    base64: " + Base64.encodeBase64String(buf) + "\n");

		sb.append("\n");
		bi = privateKey.getModulus();
		buf = bi.toByteArray();
		sb.append("privateKey:\n");

		sb.append("  encode:"
				+ Hex.encodeHexString(privateKey.getEncoded()).toUpperCase()
				+ "\n");
		sb.append("  algorithm:" + privateKey.getAlgorithm() + "\n");
		sb.append("  format:" + privateKey.getFormat() + "\n");

		sb.append("  modulus:\n");
		sb.append("    int: " + bi + "\n");
		sb.append("    hex: " + Hex.encodeHexString(buf) + "\n");
		sb.append("    base64: " + Base64.encodeBase64String(buf) + "\n");

		bi = privateKey.getPrivateExponent();
		buf = bi.toByteArray();
		sb.append("  private exponent:\n");
		sb.append("    int: " + bi + "\n");
		sb.append("    hex: " + Hex.encodeHexString(buf) + "\n");
		sb.append("    base64: " + Base64.encodeBase64String(buf) + "\n");
		return sb.toString();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException,
			DecoderException, NoSuchProviderException, InvalidKeySpecException,
			InvalidKeyException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
//		RsaChiper x = new RsaChiper();
		RsaChiper x = new RsaChiper("RSA/ECB/PKCS1Padding",(String)null);
		// x.init();
		// System.out.println(x.toStirng());

		String p = "22489fb2aa6aabc9b71bbf436cf8644199273d58d4c671d3d9c2dd7df79565196c22fe483b214cd6ac1c5e7104ec2f90ee5ceba3a8d219f545e361ae4b047ceaff1f06a88104254e7d58eaadc4cd8aadf97c199dd95529251d641b0404f7fc15bcc9e534df23d0c15cb44b7f24c76719440ab818f6b1f9ad6b34fe13a9a8e525";
		String m = "008dad38e3d567f17a6bd252cbe1642eb72c3a27087a061447892c1acf4069d6f282465289f97890e475e3360cef920bfede701783320da2fa6dae58744cd144646e9ca95f3c02e7cde67b62e74b68015875db7f7ddbf96060c3e081bb62ca2b229eafac0328aed4bc29bd95032fecad856c700169edc36c4653418dfbf0f66585";
		BigInteger bip = new BigInteger(Hex.decodeHex(p.toCharArray()));
		BigInteger bim = new BigInteger(Hex.decodeHex(m.toCharArray()));
		x.setRsaPrivateKey(bim, bip);
		//String str = "80be4d9582c946a40d986c233f7c3236575223c6899b714cd83e58a3754e5bc8342c971d0cc0f691551b14a87e3b7461a03b44fce4864c8034fbfbe94a322301bf18021cd6a6c1e08e05c9a0d76e8051f2771c6bd6cf566d7ca02dd2acd38276144896548e09ad2146572e009ff53c13628bbe09b68517585beee800bacef0ad";
		String str="6d6aa6b13ae01b3956ce17af23e635118824f8aae9593e561bc796ef21593faf23df40d9a0600f25ef460f9303d3478548e935e879c86988ab6942f7d87c9ccd97929075b21a7a5a422c7079ab21aa4a4a2d0aa918c256c083fd1973a35a0b7650428c6ae35a4496961179dec53670fd351e0a6645abae8239d003e50a3442eb";
		byte[] buf = Hex.decodeHex(str.toCharArray());
		byte[] buf2 = x.decryptByPrivateKey(buf);
		System.out.println(new String(buf2));
	}

	public RsaChiper(String transformation, String securityProvider) {
		if (StringUtils.isBlank(transformation)) {
			this.transformation = algorithm;
		} else {
			this.transformation = transformation;
		}
		this.securityProviderStr = securityProvider;
	}

	public RsaChiper(String transformation, Provider securityProvider) {
		if (StringUtils.isBlank(transformation)) {
			this.transformation = algorithm;
		} else {
			this.transformation = transformation;
		}
		this.securityProvider = securityProvider;
	}

	public byte[] encryptByPrivateKey(byte[] buf) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException {
		Cipher chip = chiper(Cipher.ENCRYPT_MODE, privateKey);
		return chip.doFinal(buf);
	}

	public byte[] encryptByPublicKey(byte[] buf) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException {
		Cipher chip = chiper(Cipher.ENCRYPT_MODE, publicKey);
		return chip.doFinal(buf);
	}

	public byte[] decryptByPrivateKey(byte[] buf) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException {
		Cipher chip = chiper(Cipher.DECRYPT_MODE, privateKey);
		return chip.doFinal(buf);
	}

	public byte[] decryptByPublicKey(byte[] buf)
			throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, NoSuchProviderException {
		Cipher chip = chiper(Cipher.DECRYPT_MODE, publicKey);
		return chip.doFinal(buf);
	}

	public Cipher chiper(int opmode, Key key) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			NoSuchProviderException {
		Cipher cipher = null;
		if (securityProvider != null) {
			cipher = Cipher.getInstance(transformation, securityProvider);
		} else if (!StringUtils.isBlank(securityProviderStr)) {
			cipher = Cipher.getInstance(transformation, securityProviderStr);
		} else {
			cipher = Cipher.getInstance(transformation);
		}
		cipher.init(opmode, key);
		return cipher;
	}

	public void setRsaPublicKey(byte[] x509) throws InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchProviderException {
		X509EncodedKeySpec spec = new X509EncodedKeySpec(x509);
		setRsaPrivateKey(spec);
	}

	public void rsaPublicKey(String x509Base64) throws InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchProviderException {
		setRsaPublicKey(x509Base64.getBytes());
	}

	public void setRsaPrivateKey(byte[] pkcs8) throws InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchProviderException {
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(pkcs8);
		setRsaPrivateKey(spec);
	}

	public void setRsaPublicKey(BigInteger modulus, BigInteger publicExponent)
			throws InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchProviderException {
		RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
		setRsaPublicKey(spec);
	}

	public void setRsaPrivateKey(BigInteger modulus, BigInteger privateExponent)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			InvalidKeySpecException {
		RSAPrivateKeySpec spec = new RSAPrivateKeySpec(modulus, privateExponent);
		setRsaPrivateKey(spec);
	}

	// ------------------------
	private void setRsaPublicKey(KeySpec spec) throws NoSuchAlgorithmException,
			NoSuchProviderException, InvalidKeySpecException {
		KeyFactory keyFactory = keyFactory();
		publicKey = (RSAPublicKey) keyFactory.generatePublic(spec);
	}

	private void setRsaPrivateKey(KeySpec spec)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			InvalidKeySpecException {
		KeyFactory keyFactory = keyFactory();
		privateKey = (RSAPrivateKey) keyFactory.generatePrivate(spec);
	}

	private KeyFactory keyFactory() throws NoSuchAlgorithmException,
			NoSuchProviderException {
		if (securityProvider != null) {
			return KeyFactory.getInstance(algorithm, securityProvider);
		}
		if (!StringUtils.isBlank(securityProviderStr)) {
			return KeyFactory.getInstance(algorithm, securityProviderStr);
		}
		return KeyFactory.getInstance(algorithm);
	}

	public int getKeysize() {
		return keysize;
	}

	public void setKeysize(int keysize) {
		this.keysize = keysize;
	}

}
