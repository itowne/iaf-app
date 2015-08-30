package newland.iaf.base.trans.json.goldKeeperPasswdCheck;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.apache.http.client.ClientProtocolException;

/**
 * 
 * @author rabbit
 * 
 */
public interface PwdChkService {

	PwdChkRespose check(String userName, String pwd)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException, ParseException;
}
