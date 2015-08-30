package newland.iaf.base.service;

/**
 * RSA服务
 * 
 * @author rabbit
 * 
 */
public interface RsaService {
	/**
	 * 解密
	 * @param hex
	 * @return
	 */
	String encrypt(String hex);
}
