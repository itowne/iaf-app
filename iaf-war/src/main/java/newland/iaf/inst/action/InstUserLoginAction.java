package newland.iaf.inst.action;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import newland.iaf.IafApplication;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MenuNode;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.base.sms.AuthCodeService;
import newland.iaf.base.sms.AuthCodeType;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.service.InstService;
import newland.iaf.inst.service.InstUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 机构用户登陆Action
 * 
 * @author lindaqun
 * 
 */
@ParentPackage("struts")
@Namespace("/")
@Action(value = "instUserLogin")
@Results({
		@Result(name = "list", type = "dispatcher"),
		@Result(name = "error", type = "dispatcher", location = "/instLogin.jsp"),
		@Result(name = "smsAuthCode", type = "dispatcher", location = "/smsAuthCode.jsp"),
		@Result(name = "logout", type = "dispatcher", location = "/instLogin.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/inst/index.jsp") })
public class InstUserLoginAction extends InstBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 867598564576230693L;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "com.newland.iaf.instUserService")
	private InstUserService instUserService;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;

	@Resource(name = "com.newland.iaf.authCodeService")
	private AuthCodeService authCodeService;

	private InstUser instuser;

	private String loginName;

	private String smsAuthCode;

	private String password;

	private String errorMsg;

	private String checkCode;
	
	private String target;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public InstUser getInstuser() {
		return instuser;
	}

	public void setInstuser(InstUser instuser) {
		this.instuser = instuser;
	}

	@InputConfig(resultName = "error")
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "loginName", shortCircuit = true, message = "用户名不能为空！"),
			@RequiredStringValidator(fieldName = "password", shortCircuit = true, message = "密码不能为空！"),
			@RequiredStringValidator(fieldName = "smsAuthCode", shortCircuit = true, message = "手机验证码不能为空！") })
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String sCode = (String) session.get("KAPTCHA_SESSION_KEY");
		if (!checkCode.equals(sCode)) {
			errorMsg = "验证码不正确!";
			log.error(errorMsg);
			return "error";
		}
		HttpSession ses = ServletActionContext.getRequest().getSession(false);
		log.debug("session id=" + ses.getId());
		if (IafApplication.isTest() == false) {
			boolean b = authCodeService.check(loginName, smsAuthCode,
					AuthCodeType.instUsrLoginAuthCode, getSession());
			if (!b) {
				errorMsg = "手机验证码错误";
				log.debug(errorMsg);
				return "error";
			}
		}
		InstUser usr = instUserService.loginInstUser(loginName, password);
		if (usr == null) {
			errorMsg = "登录名错误或者密码错误";
			log.debug(errorMsg);
			return "error";
		}
		
		if(usr.getUsrStat().toString().equals("UNUSED")){
			errorMsg = "登录用户已被禁用";
			log.debug(errorMsg);
			return "error";
		}
		
		Set<InstRole> instRoleSet = usr.getInstRoleSet();
		for (InstRole instRole : instRoleSet) {
			if(instRole.getRoleStat().intValue()==0){
				errorMsg = "登录用户所属角色已被禁用";
				log.debug(errorMsg);
				return "error";
			}
		}
		
		Inst inst = instService.findInstById(usr.getIinst());
		if(inst.getInstStat().toString().equals("UNUSED")){
			errorMsg = "登录机构被禁用";
			log.debug(errorMsg);
			return "error";
		}
		
		log.debug(loginName + password);
		InstSession sess = null;
		try {
			sess = instUserService.newInstSession(usr.getIinstUser(),
					getIpaddr());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		sess.reg(ServletActionContext.getRequest(),loginName,"inst");
		Map<Long, MenuNode> xx = sess.getMenuMap();
		String s = show(xx);
		return "success";
	}
	
	public String homepageLogin(){
		if(isLogin()==false){
			return "logout";
		}
		//if(sess==null){
			//return "logout";
		//}
		return "success";
	}
	
	public String init(){
		this.target = this.getRequest().getParameter("target");
		if (isLogin() == false){
			return "error";
		}else{
			//this.target = this.encode(this.target);
			return "success";
		}
	}

	public String show(Map<Long, MenuNode> nodes) {
		StringBuilder sb = new StringBuilder();
		show(nodes, sb, 0);
		return sb.toString();
	}

	public void show(Map<Long, MenuNode> nodes, StringBuilder sb, int deep) {
		Set<Long> keySet = nodes.keySet();
		for (Long key : keySet) {
			MenuNode mn = nodes.get(key);
			InstAuth ia = mn.getInstAuth();
			String menuName = ia.getMenuName();
			for (int i = 0; i < deep; i++) {
				sb.append("  ");
			}
			sb.append(menuName);
			sb.append('\n');

			Map<Long, MenuNode> sub = mn.getSubNodeMap();
			if (sub != null && !sub.isEmpty()) {
				show(sub, sb, deep + 1);
			}
		}
	}

	public String smsAuthCode() throws Exception {
		log.debug("send sms authCode for loginName=[" + loginName + "]");
		if (StringUtils.isBlank(loginName)) {
			errorMsg = "登录名称不能为空";
			log.debug(errorMsg);
			return smsAuthCode;
		}
		// HttpSession ses = ServletActionContext.getRequest().getSession();
		HttpSession ses = ServletActionContext.getRequest().getSession(false);
		log.debug("session id=" + ses.getId());
		InstUser usr = instUserService.getInstUserByLoginName(loginName);
		if (usr == null) {
			errorMsg = "用户不存在";
			log.debug(errorMsg);
			return smsAuthCode;
		}
		authCodeService.send(usr, this.getSession());
		return "smsAuthCode";
	}

	public String logout() throws Exception {
		HttpSession sess = ServletActionContext.getRequest().getSession(false);
		if (sess != null) {
			sess.invalidate();
		}
		return "logout";
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getSmsAuthCode() {
		return smsAuthCode;
	}

	public void setSmsAuthCode(String smsAuthCode) {
		this.smsAuthCode = smsAuthCode;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getTarget() {
		if (StringUtils.isBlank(target)){
			return "/inst/welcome";
		}
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
