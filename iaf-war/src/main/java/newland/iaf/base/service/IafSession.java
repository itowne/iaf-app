package newland.iaf.base.service;

/**
 * IAF平台session接口
 * 
 * @author rabbit
 * 
 */
public interface IafSession {
	
	boolean hasAuth(String reqUrl);

	String flag();
}
