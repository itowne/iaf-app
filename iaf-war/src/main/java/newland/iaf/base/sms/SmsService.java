package newland.iaf.base.sms;

import java.io.IOException;

import newland.iaf.base.trans.model.SmsLog;

/**
 * 
 * @author rabbit
 * 
 */
public interface SmsService {

	boolean send(SmsLog smsLog);

	String send(String phone, String msgContext, String url) throws IOException;

}
