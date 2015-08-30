package newland.iaf.base.json;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.httpOutgoingTrans")
public class HttpOutgoingTransImpl implements HttpOutgoingTrans {

	protected static Logger log = LoggerFactory
			.getLogger(HttpOutgoingTransImpl.class);

	private static final String requestCharset = "gb18030";

	private static final String responseCharset = "gb18030";

	@Override
	public String outgoing(String url, String send)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		String recv = null;
		try {
			HttpPost httppost = new HttpPost(url);
			StringEntity s = new StringEntity(send);
			s.setContentEncoding(requestCharset);
			s.setContentType("application/json");
			httppost.setEntity(s);
			HttpResponse res = client.execute(httppost);
			int statusCode = res.getStatusLine().getStatusCode();
			log.debug("statusCode=" + statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				byte[] br = EntityUtils.toByteArray(entity);
				recv = new String(br, responseCharset);
				return recv;
			} else {
				throw new RuntimeException("交易发送失败");
			}
		} finally {
			log.debug("send=[" + send + "],\nrecv=[" + recv + "].");
			client.getConnectionManager().shutdown();
		}
	}
}
