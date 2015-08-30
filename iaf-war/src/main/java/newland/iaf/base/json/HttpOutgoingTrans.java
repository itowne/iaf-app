package newland.iaf.base.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

/**
 * 
 * @author rabbit
 * 
 */
public interface HttpOutgoingTrans {

	String outgoing(String url, String send)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException;

}
