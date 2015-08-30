package newland.iaf.backstage.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newland.base.formater.Formatter;
import newland.iaf.IafApplication;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.service.MerchService;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
import com.opensymphony.xwork2.ActionSupport;

public class BSBaseAction extends ActionSupport implements SessionAware,
				ServletRequestAware, ServletResponseAware{
	Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name = "dateFormatter")
	private Formatter dateFormatter;
	@Resource(name = "timeFormatter")
	private Formatter timeFormatter;
	@Resource(name = "amountFormatter")
	private Formatter amountFormatter;
	

	@Resource(name = "wanyuanFormatter")
	private Formatter wanyuanFormatter;
	
	public String wanyuanFormat(Object obj){
		return wanyuanFormatter.format(obj);
	}
	
	@Resource (name = "provinceService")
	private ProvinceService provinceService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	public Map<String, String> provinceMap;
	public Map<String, String> getProvinceMap() {
		if (provinceMap == null){
			this.provinceMap = this.provinceService.getProvince("440000");
		}
		return provinceMap;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected IafConsoleSession getUserSession(){
		IafConsoleSession ses = (IafConsoleSession)SessionFilter.getIafSession();
		return ses;
	}
	
	protected HttpServletRequest getRequest(){
		return this.request;
	}
	
	protected HttpServletResponse getReponse(){
		return this.response;
	}
	
	@SuppressWarnings("unchecked")
	protected <T>  T parser(String obj, Class<T> clazz){
		if (obj == null || "".equals(obj)) return null;
		if (ClassUtils.isAssignable(clazz, String.class)) return (T)obj;
		if (ClassUtils.isAssignable(clazz, Date.class)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return (T)sdf.parse(obj);
			} catch (ParseException e) {
				logger.debug("格式化错误", e);
				return null;
			}
		}
		if (ClassUtils.isAssignable((Class) clazz, Number.class)) {
			try{
				if (clazz == Long.class){
					return (T)Long.getLong(obj);
				}else if (clazz == Double.class){
					return (T)new Double(obj);
				}else if (clazz == BigDecimal.class){
					return (T)new BigDecimal(obj);
				}else if (clazz == Integer.class){
					return (T) Integer.valueOf(obj);
				}
			}catch(Throwable e){
				logger.debug("格式化错误", e);
				return null;
			}
		}

		throw new RuntimeException("不支持的类型");
	}
	
	public String getIpaddr(){
		if (this.getUserSession() == null) return this.request.getRemoteAddr();
		return this.getUserSession().getIpaddr();
	}

	protected BackStageUser getUser() {
		return this.getUserSession().getUser();
	}
	
	public IafConsoleSession getIafConsoleSession(){
		IafConsoleSession ics = (IafConsoleSession) SessionFilter.getIafSession();
		return ics;
	}
	
	protected String getRoles(){
		return this.getUserSession().getRoles();
	}
	
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
	protected Object getSessionObj(String key){
		return this.session.get(key);
	}
	
	protected void setSessionObj(String key, Object obj){
		this.session.put(key, obj);
	}
	
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	
	
	public String getBaseUrl() {
		String url = this.request.getScheme() + "://";
		url += this.request.getServerName() + ":";
		url += this.request.getServerPort() + "/";
		url += this.request.getContextPath();
		return url;
	}

	public Formatter getTimeFormatter() {
		return timeFormatter;
	}

	public void setTimeFormatter(Formatter timeFormatter) {
		this.timeFormatter = timeFormatter;
	}

	public Formatter getAmountFormatter() {
		return amountFormatter;
	}

	public void setAmountFormatter(Formatter amountFormatter) {
		this.amountFormatter = amountFormatter;
	}

	public Formatter getWanyuanFormatter() {
		return wanyuanFormatter;
	}

	public void setWanyuanFormatter(Formatter wanyuanFormatter) {
		this.wanyuanFormatter = wanyuanFormatter;
	}

	public Formatter getDateFormatter() {
		return dateFormatter;
	}

	public void setDateFormatter(Formatter dateFormatter) {
		this.dateFormatter = dateFormatter;
	}
	
	public String encode(String source){
		try {
			return URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("编码时出错", e);
			return source;
		}
	}
	
	public String decode(String source){
		try {
			return URLDecoder.decode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("编码时出错", e);
			return source;
		}
	}

	public String getCustomerUid(){
		return IafApplication.getCustomerUid();
	}
	
	public String getMerchUid(Long imerch){
		ApplicationContext content = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		MerchService merchService = (MerchService)content.getBean("com.newland.iaf.merchService");
		if (imerch == null) return "";
		Merch merch = merchService.findMerchById(imerch);
		if (merch == null) return "";
		return merch.getQqUid();
	}
	
	public String getInstUid(Long iinst){
		ApplicationContext content = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		InstService instService = (InstService)content.getBean("com.newland.iaf.instService");
		if (iinst == null) return "";
		Inst inst = instService.findInstById(iinst);
		if (inst == null) return "";
		return inst.getQqUid();
	}
	
	protected boolean isLogin(){
		if (this.getRequest().getSession(false) == null) return false;
		IafSession sess = (IafSession)this.getRequest().getSession(false).getAttribute(SessionFilter.IAF_LOGIN_SESSION);
		if (sess == null) return false;
		if (sess instanceof IafConsoleSession) return true;
		return false;
	}
}
