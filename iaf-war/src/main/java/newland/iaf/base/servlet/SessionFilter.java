package newland.iaf.base.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newland.base.util.StringUtils;
import newland.iaf.base.service.IafSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登陆过滤器
 * 
 * @author rabbit
 * 
 */
public class SessionFilter extends HttpFilter {

	private static ThreadLocal<IafSession> threadLocal = new ThreadLocal<IafSession>();
	private static ThreadLocal<HttpServletRequest> requests = new ThreadLocal<HttpServletRequest>();
	/**
	 * 
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 需要登陆的url前缀
	 */
	private String needLoginUrlPrefix = null;
	/**
	 * 未登录的跳转页面
	 */
	private String redirectUrl = null;

	/**
	 * 无权限的跳转页面
	 */
	private String denyAuthRedirectUrl = null;

	/**
	 * filter参数名
	 */
	private static final String NEED_LOGIN_URL_PREFIX = "needLoginUrlPrefix";

	/**
	 * filter参数名
	 */
	private static final String REDIRECT_URL = "redirectUrl";

	/**
	 * 
	 */
	private static final String DENY_AUTH_REDIRECT_URL = "denyAuthRedirectUrl";

	/**
	 * 登陆标志tag
	 */
	public static final String IAF_LOGIN_SESSION = "IAF_LOGIN_SESSION";
	/**
	 * 登陆标志的值value
	 */
	private String loginFlagValue = null;
	/**
	 * 
	 */
	private static final String LOGIN_FLAG_VALUE = "loginFlagValue";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		redirectUrl = filterConfig.getInitParameter(REDIRECT_URL);
		if (StringUtils.isBlank(redirectUrl)) {
			new ServletException();
		}

		needLoginUrlPrefix = filterConfig
				.getInitParameter(NEED_LOGIN_URL_PREFIX);
		if (StringUtils.isBlank(needLoginUrlPrefix)) {
			new ServletException();
		}

		denyAuthRedirectUrl = filterConfig
				.getInitParameter(DENY_AUTH_REDIRECT_URL);
		if (StringUtils.isBlank(denyAuthRedirectUrl)) {
			new ServletException();
		}

		loginFlagValue = filterConfig.getInitParameter(LOGIN_FLAG_VALUE);
		if (StringUtils.isBlank(loginFlagValue)) {
			new ServletException();
		}

		String ctx = filterConfig.getServletContext().getContextPath();
		if (StringUtils.equals(ctx, "/")) {

		} else {
			redirectUrl = addctx(ctx, redirectUrl);
			needLoginUrlPrefix = addctx(ctx, needLoginUrlPrefix);
			denyAuthRedirectUrl = addctx(ctx, denyAuthRedirectUrl);
		}

	}

	private String addctx(String ctx, String url) {
		if (url.startsWith("/")) {
			url = ctx + url;
		} else {
			url = ctx + "/" + url;
		}
		return url;
	}

	@Override
	protected void doHttpFilter(HttpServletRequest req,
			HttpServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		String url = req.getRequestURI();

		if (!url.startsWith(needLoginUrlPrefix)) {
			chain.doFilter(req, resp);
			return;
		}
		String target = renderParam(req, url);
		HttpSession sess = req.getSession(false);
		if (sess == null || sess.isNew()) {
			resp.sendRedirect(redirectUrl + "?target=" + target);
			return;
		}

		IafSession iafSess = (IafSession) sess.getAttribute(IAF_LOGIN_SESSION);
		if (iafSess == null) {
			// 未登录状态访问需要登陆页面，作废原有session
			log.debug("未登录状态访问需要登陆页面[" + url + "]，作废原有session");
			sess.invalidate();
			resp.sendRedirect(redirectUrl + "?target=" + target);
			return;
		}

		if (!StringUtils.equals(iafSess.flag(), loginFlagValue)) {
			// 未登录状态访问需要登陆页面，作废原有session
			log.debug("未登录状态访问需要登陆页面[" + url + "]，作废原有session");
			sess.invalidate();
			resp.sendRedirect(redirectUrl + "?target=" + target);
			return;
		}

		if (!iafSess.hasAuth(url)) {
			log.debug("未授权状态访问页面[" + url + "]，作废原有session");
			sess.invalidate();
			resp.sendRedirect(denyAuthRedirectUrl);
			return;
		}
		
		threadLocal.set(iafSess);
		requests.set(req);
		try {
			chain.doFilter(req, resp);
		} finally {
			threadLocal.remove();
			requests.remove();
		}

	}

	private String renderParam(HttpServletRequest req, String url) {
		return url + "?" + req.getQueryString();
	}

	public static IafSession getIafSession() {
		return threadLocal.get();
	}
}
