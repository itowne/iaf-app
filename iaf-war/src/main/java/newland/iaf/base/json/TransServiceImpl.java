package newland.iaf.base.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import newland.iaf.base.json.riskControl.ReqBody;
import newland.iaf.base.json.riskControl.RespBody;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.transService")
public class TransServiceImpl implements TransService {

	@Resource(name = "com.newland.iaf.httpOutgoingTrans")
	HttpOutgoingTrans httpOutgoingTrans;

	private void trans(String url, Object reqHead, Object reqBody,
			Object respHead, Object respBody) {
		JSONObject jo = new JSONObject();
		// jo.put("head", reqHead.toJson());
		// jo.put("body", reqBody.toJson());
		try {
			httpOutgoingTrans.outgoing(url, jo.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ReqHeader newReqHeader(String msgName) {
		ReqHeader reqH = new ReqHeader();
		// reqH.setMsgName(msgName);
		/**
		 * TODO
		 */
		// reqH.setMsgSeq("1");
		return reqH;
	}

	@Override
	public void riskControl(ReqBody reqBody, RespBody respBody) {
		ReqHeader reqHead = newReqHeader("riskControl");
		RespHeader respHead = new RespHeader();
		/**
		 * TODO 获取服务url
		 */
		String url = null;
		trans(url, reqHead, reqBody, respHead, respBody);
	}

	@Override
	public void transLog(newland.iaf.base.json.transLog.ReqBody reqBody,
			newland.iaf.base.json.transLog.RespBody respBody) {
		ReqHeader reqHead = newReqHeader("transLog");
		RespHeader respHead = new RespHeader();
		/**
		 * TODO 获取服务url
		 */
		String url = null;
		trans(url, reqHead, reqBody, respHead, respBody);

	}

	@Override
	public void merchBaseInfo(
			newland.iaf.base.json.merchBaseInfo.ReqBody reqBody,
			newland.iaf.base.json.merchBaseInfo.RespBody respBody) {
		ReqHeader reqHead = newReqHeader("transLog");
		RespHeader respHead = new RespHeader();
		/**
		 * TODO 获取服务url
		 */
		String url = null;
		trans(url, reqHead, reqBody, respHead, respBody);
	}

	@Override
	public void serviceLog(newland.iaf.base.json.serviceLog.ReqBody reqBody,
			newland.iaf.base.json.serviceLog.RespBody respBody) {
		ReqHeader reqHead = newReqHeader("transLog");
		RespHeader respHead = new RespHeader();
		/**
		 * TODO 获取服务url
		 */
		String url = null;
		trans(url, reqHead, reqBody, respHead, respBody);
	}

}
