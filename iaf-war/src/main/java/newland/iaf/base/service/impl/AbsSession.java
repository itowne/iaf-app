package newland.iaf.base.service.impl;

import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import newland.iaf.IafApplication;
import newland.iaf.base.service.IafSession;
import newland.iaf.base.servlet.SessionFilter;

/**
 * 
 * @author rabbit
 * 
 */
public abstract class AbsSession implements IafSession {
	/**
	 * 授权的URL
	 */
	private Set<String> needAuthUrls;

	/**
	 * 需要授权URL
	 */
	private Set<String> allowUrls;

	private String ipaddr;

	@Override
	public boolean hasAuth(String reqUrl) {
		if (getNeedAuthUrls() == null) {
			return true;
		}

		if (!getNeedAuthUrls().contains(reqUrl)) {
			return true;
		}
		return getAllowUrls().contains(reqUrl);
	}

	public Set<String> getAllowUrls() {
		if (allowUrls == null) {
			return new TreeSet<String>();
		}
		return allowUrls;
	}

	public void setAllowUrls(Set<String> allowUrls) {
		this.allowUrls = allowUrls;
	}

	public Set<String> getNeedAuthUrls() {
		return needAuthUrls;
	}

	public void setAuthUrls(Set<String> authUrls) {
		this.needAuthUrls = authUrls;
	}

	public void reg(HttpServletRequest req,String loginName,String type) {
		HttpSession sess = req.getSession(true);
		if (!sess.isNew()) {
			sess.invalidate();
			sess = req.getSession(true);
		}
		sess.setMaxInactiveInterval(IafApplication.getSessionTimeout());
		sess.setAttribute(SessionFilter.IAF_LOGIN_SESSION, this);
		sess.setAttribute("loginName",loginName );
		sess.setAttribute("type", type);
		this.setIpaddr(req.getRemoteAddr());
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
}
