package newland.iaf.backstage.action;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import newland.iaf.backstage.model.BackStageAuth;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.backstage.model.BsRoleAuth;
import newland.iaf.backstage.service.BackStageUserService;
import newland.iaf.base.model.dict.InstUserStatType;
import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.RsaService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.IafMenuNode;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MenuNode;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.InstAuth;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@ParentPackage("struts")
@Namespace("/")
@Action(value = "iafLogin")
@Results({
		@Result(name = "login", type = "dispatcher", location = "/backLogin.jsp"),
		@Result(name = "logout", type = "dispatcher", location = "/backLogin.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/backstage/index.jsp") })
public class IafLoginAction extends BSBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "backStageUserService")
	private BackStageUserService backStageUserService;

	@Resource(name = "com.newland.iaf.rsaService")
	private RsaService rsaService;

	public String execute() {
		return "login";
	}

	private String loginName;

	private String password;

	private String checkCode;

	private String errorMsg;
	
	private String target;

	
	@InputConfig(resultName = "login")
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "loginName", shortCircuit = true, message = "用户名不能为空！"),
			@RequiredStringValidator(fieldName = "password", shortCircuit = true, message = "密码不能为空！") })
	public String login() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String sCode = (String) session.get("KAPTCHA_SESSION_KEY");
		if (!checkCode.equals(sCode)) {
			errorMsg = "验证码不正确!";
			return "login";
		}
		IafConsoleSession sess=null;
		try {
			password = rsaService.encrypt(password);
			BackStageUser user = this.backStageUserService.login(loginName,
					password);
			if(user.getStat()==InstUserStatType.UNUSED){
				errorMsg="登录用户已被禁用";
				return "login";
			}
			Set<BackStageRole> backStageRoleSet = user.getBsRoleSet();
			for (BackStageRole backStageRole : backStageRoleSet) {
				if(backStageRole.getStat().intValue()==0){
					errorMsg="登录用户所属角色已被禁用";
					return "login";
				}
			}
			 sess=this.backStageUserService.newIafConsoleSession(user, getRequest(),
					getIpaddr());
		} catch (Exception e) {
			addActionError(e.getMessage());
			errorMsg = e.getMessage();
			return "login";
		}
		sess.reg(ServletActionContext.getRequest(),loginName,"iaf");
		Map<Long, IafMenuNode> xx = sess.getMenuMap();
		String s = show(xx);
		return "success";
	}

	public String logout() throws Exception {
		HttpSession sess = ServletActionContext.getRequest().getSession(false);
		if (sess != null) {
			sess.invalidate();
		}
		return "logout";
	}
	
	public String homepageLogin(){
		if(isLogin()==false){
			return "logout";
		}
		return "success";
	}
	
	public String show(Map<Long, IafMenuNode> nodes) {
		StringBuilder sb = new StringBuilder();
		show(nodes, sb, 0);
		return sb.toString();
	}

	public void show(Map<Long, IafMenuNode> nodes, StringBuilder sb, int deep) {
		Set<Long> keySet = nodes.keySet();
		for (Long key : keySet) {
			IafMenuNode mn = nodes.get(key);
			BackStageAuth ia = mn.getBackStageAuth();
			String menuName = ia.getMenuName();
			for (int i = 0; i < deep; i++) {
				sb.append("  ");
			}
			sb.append(menuName);
			sb.append('\n');

			Map<Long, IafMenuNode> sub = mn.getSubNodeMap();
			if (sub != null && !sub.isEmpty()) {
				show(sub, sb, deep + 1);
			}
		}
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

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getTarget() {
		if (StringUtils.isBlank(target)){
			return "/backstage/welcome.jsp";
		}
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
