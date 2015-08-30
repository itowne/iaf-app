package newland.base.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 请求参数解板器
 * @author new
 *
 */
public class RequestParams {
	
	private String source;
	
	private Map<String, String> params;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public RequestParams(String str){
		this.source = str;
	}
	
	public Map<String, String> getParams(){
		if (params == null){
			params = new HashMap<String, String>();
			String[] ValuePair = StringUtils.split(source, "&");
			if (ValuePair != null && ValuePair.length > 0){
				for (String str: ValuePair){
					String[] keyValue = StringUtils.split(str, "=");
					if (keyValue != null && keyValue.length > 0){
						if (keyValue.length < 2){
							params.put(keyValue[0], "");
						}else{
							params.put(keyValue[0], keyValue[1]);
						}
					}
				}
			}
		}
		return params;
	}
	
	public String getParam(String key){
		String value = this.getParams().get(key);
		logger.debug(key + " = " + value);
		return value;
	}
	
	public void printParam(){
		if (getParams() == null) return;
		for (Map.Entry<String, String> entry: getParams().entrySet()){
			System.err.println(entry.getKey() + " = " + entry.getValue());
		}
	}
	
	public static void main(String[] args){
		System.err.println("---------------------------------------------------------------");
		RequestParams param = new RequestParams("merId=000000000000001&settleNo=000000000000005&organNo=0000000001&pwd=123456&orderNo=0407105731519450&transType=0&currCode=CNY&charge=1&orderAmount=1.00&gateType=N&gateId=N&resultMode=&version=v1.0.0&priv1=0&bgRetUrl=&callBackUrl= http://192.168.10.47:8080/struts1/gateway.do?operate=resGateway&userId=01");
		param.printParam();
		System.err.println("---------------------------------------------------------------");
		param = new RequestParams("orderNo=2013040600000802&merId=000000000000001&gateType=N&organNo=0000000001&settleNo=000000000000005&password=123456&currCode=CNY&orderAmount=16945.21&charge=1&gateId=N&version=v1.0.0&priv1=0&userId=01&resultMode=&bgRetUrl=&callBackUrl=&transType=0");
		param.printParam();
		System.err.println("---------------------------------------------------------------");
	}

}
