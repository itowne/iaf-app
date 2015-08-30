package newland.iaf.base.trans.json.goldKeeperPasswdCheck;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import newland.iaf.IafApplication;
import newland.iaf.base.format.json.JsonBeanFormat;
import newland.iaf.base.json.HttpOutgoingTrans;
import newland.iaf.base.json.ReqHeader;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.utils.AesCipher;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.pwdChkService")
@Transactional
public class PwdChkServiceImpl implements PwdChkService {

	@Resource(name = "com.newland.iaf.httpOutgoingTrans")
	private HttpOutgoingTrans httpOutgoingTrans;

	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;

	protected static Logger log = LoggerFactory
			.getLogger(PwdChkServiceImpl.class);

	private static final String OK = "0003_00";

	public AesCipher getAesChipher() throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, DecoderException {
		Map<SysParamName, SysParam> set = sysParamService
				.findSysParamMapByType(SysParamType.pwdCheck);
		String key = set.get(SysParamName.PWD_CHECK_AES_KEY).getValue();
		String iv = set.get(SysParamName.PWD_CHECK_AES_IV).getValue();
		return new AesCipher(key, iv);
	}

	public PwdChkRespose check(String userName, String pwd)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException, ParseException {

		ReqHeader reqH = new ReqHeader();
		reqH.setDataTime(new Date());
		reqH.setTransType("0003");

		UserData ud = new UserData();

		try {
			pwd = Hex.encodeHexString(getAesChipher().encrypt(pwd))
					.toLowerCase();
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		ud.setPwd(pwd);
		ud.setUserName(userName);
		String saltValue = sysParamService.getSysParam(SysParamType.pwdCheck,
				SysParamName.PWD_CHECK_SALT_VALUE).getValue();
		String mac = userName + "+" + saltValue;
		mac = DigestUtils.md5Hex(mac).toUpperCase();
		ud.setMac(mac);

		PwdChkReqest req = new PwdChkReqest();
		req.setAppHead(reqH);
		req.setUserData(ud);

		JsonBeanFormat jbf = new JsonBeanFormat(PwdChkReqest.class);
		String send = jbf.format(req);

		SysParam param = sysParamService.getSysParam(SysParamType.pwdCheck,
				SysParamName.PWD_CHECK_URL);
		String url = param.getValue();
		String recv = null;
		try {
			recv = httpOutgoingTrans.outgoing(url, send);
		} catch (Exception e) {
			log.info("send=" + send + "\nrecv=" + recv);
		}
		PwdChkRespose resp = new PwdChkRespose();
		JsonBeanFormat jbfResp = new JsonBeanFormat(PwdChkRespose.class);
		jbfResp.parse(recv, resp);
		log.debug(resp.getRetCode());
		return resp;
		//return StringUtils.equals(OK, resp.getRetCode());
	}
}
