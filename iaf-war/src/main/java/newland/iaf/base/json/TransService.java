package newland.iaf.base.json;

/**
 * 
 * @author rabbit
 * 
 */
public interface TransService {
	void riskControl(newland.iaf.base.json.riskControl.ReqBody reqBody,
			newland.iaf.base.json.riskControl.RespBody respBody);

	void transLog(newland.iaf.base.json.transLog.ReqBody reqBody,
			newland.iaf.base.json.transLog.RespBody respBody);

	void merchBaseInfo(newland.iaf.base.json.merchBaseInfo.ReqBody reqBody,
			newland.iaf.base.json.merchBaseInfo.RespBody respBody);

	void serviceLog(newland.iaf.base.json.serviceLog.ReqBody reqBody,
			newland.iaf.base.json.serviceLog.RespBody respBody);

}
