package newland.iaf.base.sms;

import java.util.Map;

import javax.servlet.http.HttpSession;

import newland.iaf.inst.model.InstUser;
import newland.iaf.merch.model.MerchUser;

/**
 * 
 * @author rabbit
 * 
 */
public interface AuthCodeService {
	/**
	 * 
	 * @param usr
	 * @param session
	 * @return
	 */
	String send(InstUser usr, HttpSession session);

	SmsAuthCode send(InstUser usr);

	String send(MerchUser usr, HttpSession session);

	/**
	 * 
	 * @param authCode
	 * @param codeType
	 * @param session
	 * @return
	 */
	boolean check(String loginName, String authCode, AuthCodeType codeType,
			HttpSession session);

	String send(InstUser usr, Map<String, Object> session);

	boolean check(String loginName, String authCode, AuthCodeType codeType,
			Map<String, Object> session);
}
