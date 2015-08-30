package newland.iaf.base.format.sms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.base.sms.AuthCodeService;
import newland.iaf.base.sms.SmsService;
import newland.iaf.base.trans.model.SmsLog;
import newland.iaf.base.trans.model.SmsMsgType;
import newland.iaf.base.trans.service.FtpFileService;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.service.InstUserService;

import org.junit.Test;

import com.newland.BeanTest;

public class SmsServiceImplTest extends BeanTest {

	@Resource(name = "com.newland.iaf.smsService")
	private SmsService smsService;

	@Resource(name = "com.newland.iaf.merchBaseInfoService")
	private FtpFileService merchBaseInfoService;

	@Resource(name = "com.newland.iaf.instUserService")
	private InstUserService instUserServcie;

	@Resource(name = "com.newland.iaf.authCodeService")
	private AuthCodeService authCodeService;

	@Test
	public void test() throws Exception {
//		initDemoService.initBaseDate();
//		initDemoService.initDemoData();

		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date = df.parse("20130405");
		// merchBaseInfoService.fetch(date);

		SmsLog smsLog = new SmsLog();
		smsLog.setMsg("利率计算器你搞下");

		smsLog.setPhone("18605919561");
		// smsLog.setPhone("13960902343");
		// smsLog.setPhone("18650702106");
		smsLog.setMsgType(SmsMsgType.commonMsg);
		InstUser usr = instUserServcie.getInstUserByLoginName("wm");
		// smsService.send(smsLog);
		authCodeService.send(usr);
	}

}
