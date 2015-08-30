package newland.iaf.base.sms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.trans.dao.SmsLogDao;
import newland.iaf.base.trans.model.SmsLog;
import newland.iaf.base.trans.service.impl.InstallLogServiceImpl;
import newland.iaf.inst.service.InstUserService;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.smsService")
@Transactional
public class SmsServiceImpl implements SmsService {

	/**
	 * 系统厂商
	 */
	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;

	@Resource(name = "com.newland.iaf.smsLogDao")
	private SmsLogDao smsLogDao;

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.smsService")
	private SmsService smsService;

	private static Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

	public String getUrl() {
		Map<SysParamName, SysParam> urls = sysParamService
				.findSysParamMapByType(SysParamType.smsUrl);
		SysParam p = urls.get(SysParamName.SMS_URL_1);
		String url = p.getValue();
		return url;
	}

	public boolean send(SmsLog smsLog) {
		smsLog.setGenTime(new Date());
		String url = getUrl();
		smsLog.setSmsUrl(url);
		smsLog.setGenTime(new Date());
		smsLog.setUpdTime(new Date());
		smsLogDao.save(smsLog);

		// msg.setPhone("18605919561");
		// msg.setPhone("13960902343");
		String res = null;
		boolean ret = true;
		try {
			res = send(smsLog.getPhone(), smsLog.getMsg(), smsLog.getSmsUrl());
		} catch (IOException e) {
			ret = false;
			log.error("发生短信失败", e);
		}
		smsLog.setStatus(res);
		smsLogDao.update(smsLog);
		return ret;
	}

	public String send(String phone, String msgContext, String url)
			throws IOException {
		Msg msg = new Msg();
		msg.setPhone(phone);
		msg.setMsgStr(msgContext);
		JSONObject jsonObject = JSONObject.fromObject(msg);
		String str = jsonObject.toString();
		return URLPost(url, str);
	}

	/**
	 * 此方法基本不需要变动，直接使用
	 * 
	 * @param url
	 * @param str
	 * @return string
	 * @throws IOException
	 */
	private String URLPost(String ur, String str) throws IOException {
		log.debug("send sms=[" + str + "], url=[" + ur + "].");
		URL url = new URL(ur);
		URLConnection rulConnection = url.openConnection();
		HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
		httpUrlConnection.setDoOutput(true);
		httpUrlConnection.setDoInput(true);
		httpUrlConnection.setUseCaches(false);
		httpUrlConnection.setRequestProperty("Content-Type",
				"application/json; charset=GBK");
		httpUrlConnection.setConnectTimeout(8000);
		httpUrlConnection.setReadTimeout(10000);
		httpUrlConnection.setRequestMethod("POST");
		OutputStream out = null;
		InputStream inStrm = null;
		try {
			out = httpUrlConnection.getOutputStream();
			out.write(str.getBytes("gbk"));
			out.flush();
			int response = httpUrlConnection.getResponseCode();
			String result = null;
			// 返回200说明已经请求成功
			switch (response) {
			case 200:
				inStrm = httpUrlConnection.getInputStream();
				result = IOUtils.toString(inStrm);
				log.debug("send sms success.");
			default:
				log.debug("send sms error. code=" + response);
			}
			return result;
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inStrm);
		}

	}

}
