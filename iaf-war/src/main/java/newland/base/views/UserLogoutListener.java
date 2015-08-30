package newland.base.views;

import java.util.Calendar;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import newland.iaf.backstage.service.BackStageUserService;
import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.service.InstUserService;
import newland.iaf.merch.service.MerchUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class UserLogoutListener implements HttpSessionListener {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		IafSession sess = (IafSession)se.getSession().getAttribute(SessionFilter.IAF_LOGIN_SESSION);
		if (sess instanceof MerchSession) {
			MerchUserService merchUserService = (MerchUserService)WebApplicationContextUtils
					.getWebApplicationContext(
							se.getSession().getServletContext()).getBean("com.newland.iaf.merchUserService");
			MerchSession ms = (MerchSession)sess;
			merchUserService.logout(ms.getMerch(), ms.getMerchUser(), ms.getIpaddr());
			logger.info("商户退出, 商户名称："
					+ ((MerchSession) sess).getMerch().getMerchName());
		} else if (sess instanceof InstSession) {
			InstUserService instUserService = (InstUserService)WebApplicationContextUtils
					.getWebApplicationContext(
							se.getSession().getServletContext()).getBean("com.newland.iaf.instUserService");
			InstSession is = (InstSession)sess;
			instUserService.logout(is, is.getIpaddr());
			logger.info("机构退出, 退出机构："
					+ ((InstSession) sess).getInst().getInstName());
		} else if (sess instanceof IafConsoleSession) {
			BackStageUserService backStageUserService = (BackStageUserService)WebApplicationContextUtils
					.getWebApplicationContext(
							se.getSession().getServletContext()).getBean("backStageUserService");
			IafConsoleSession is = (IafConsoleSession)sess;
			backStageUserService.logout(is.getUser(), is.getIpaddr());
			logger.info("后台用户退出："
					+ ((IafConsoleSession) sess).getUser().getLoginName());
		}

	}
	
	public static void main(String[] args){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, -1);
		System.err.println(cal.getTime());
	}

}
