package newland.iaf.base.sms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import newland.iaf.base.trans.model.SmsLog;
import newland.iaf.base.trans.model.SmsMsgType;
import newland.iaf.inst.dao.InstUserDao;
import newland.iaf.inst.model.InstUser;
import newland.iaf.merch.model.MerchUser;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.authCodeService")
public class AuthCodeServiceImpl implements AuthCodeService {

	private final static String SMS_AUTH_CODE = "smsAuthCode";

	/** 机构用户信息操作DAO接口 **/
	@Resource(name = "com.newland.iaf.instUserDao")
	private InstUserDao instUserDao;

	@Resource(name = "com.newland.iaf.smsService")
	private SmsService smsService;

	private int minute = 5;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public String send(InstUser usr, HttpSession session) {
		SmsAuthCode sac = send(usr);
		session.setAttribute(SMS_AUTH_CODE, sac);
		return sac.getValue();
	}

	@Override
	public String send(InstUser usr, Map<String, Object> session) {
		SmsAuthCode sac = send(usr);
		session.put(SMS_AUTH_CODE, sac);
		return sac.getValue();
	}

	@Override
	public SmsAuthCode send(InstUser usr) {
		String authCode = RandomStringUtils.randomNumeric(6);
		SmsLog smsLog = new SmsLog();
		smsLog.setPhone(usr.getPhone());
		smsLog.setTargIinst(usr.getIinst());
		smsLog.setSrcIinstUsr(usr.getIinstUser());

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		smsLog.setMsg("汇融易短信验证码为【" + authCode + "】，其有效时长为" + minute
				+ "分钟；为了您的账号安全，请及时使用，不要向他人提供您的验证码。" + df.format(new Date())
				//+ "【汇卡商务】"
				);

		smsLog.setMsgType(SmsMsgType.authCode);
		if (smsService.send(smsLog) == false) {
			return null;
		}
		SmsAuthCode sac = new SmsAuthCode();
		sac.setCodeType(AuthCodeType.instUsrLoginAuthCode);
		sac.setValue(authCode);
		Date date = new Date();
		sac.setGenTime(date);
		sac.setExpTime(DateUtils.addMinutes(date, minute));
		sac.setLoginName(usr.getLoginName());
		return sac;
	}

	@Override
	public String send(MerchUser usr, HttpSession session) {
		String authCode = RandomStringUtils.randomNumeric(6);
		SmsLog smsLog = new SmsLog();
		// smsLog.setPhone(usr.getPhone());
		smsLog.setTargImerch(usr.getImerch());
		smsLog.setTargImerchUsr(usr.getImerchUser());
		smsLog.setMsg("汇融易短信验证码  [" + authCode + "], 为了您的账号安全，请不要向他人提供您的验证码。");
		smsLog.setMsgType(SmsMsgType.authCode);
		if (smsService.send(smsLog) == false) {
			return null;
		}
		SmsAuthCode sac = new SmsAuthCode();
		sac.setCodeType(AuthCodeType.merchUsrLoginAuthCode);
		sac.setValue(authCode);
		Date date = new Date();
		sac.setGenTime(date);
		sac.setExpTime(DateUtils.addMinutes(date, minute));
		session.setAttribute(SMS_AUTH_CODE, sac);
		return authCode;
	}

	@Override
	public boolean check(String loginName, String authCode,
			AuthCodeType codeType, Map<String, Object> session) {
		SmsAuthCode sac = (SmsAuthCode) session.remove(SMS_AUTH_CODE);
		return check(loginName, authCode, codeType, sac);
	}

	private boolean check(String loginName, String authCode,
			AuthCodeType codeType, SmsAuthCode sac) {
		if (sac == null) {
			log.debug("session中验证码信息 不存在.");
			return false;
		}
		if (!StringUtils.equals(loginName, sac.getLoginName())) {
			log.debug("登录名不匹配,input=[" + loginName + "], session=["
					+ sac.getLoginName() + "].");
			return false;
		}

		if (!codeType.equals(sac.getCodeType())) {
			log.debug("短信验证码类型不匹配,input=[" + codeType + "], session=["
					+ sac.getCodeType() + "].");
			return false;
		}

		if (!StringUtils.equals(authCode, sac.getValue())) {
			log.debug("短信验证码不匹配,input=[" + authCode + "], session=["
					+ sac.getValue() + "].");
			return false;
		}
		Date date = new Date();
		if (date.after(sac.getExpTime())) {
			log.debug("短信验证码已经失效,input=[" + authCode + "], session=["
					+ sac.getValue() + "].");
			return false;
		}
		return true;
	}

	@Override
	public boolean check(String loginName, String authCode,
			AuthCodeType codeType, HttpSession session) {
		if (session == null) {
			log.debug("session 不存在.");
			return false;
		}
		log.debug("session id=" + session.getId());
		SmsAuthCode sac = (SmsAuthCode) session.getAttribute(SMS_AUTH_CODE);
		session.removeAttribute(SMS_AUTH_CODE);
		return check(loginName, authCode, codeType, sac);
	}
}
