package newland.iaf.merch.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import newland.base.util.DigestUtil;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.RsaService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.trans.json.goldKeeperPasswdCheck.PwdChkRespose;
import newland.iaf.base.trans.json.goldKeeperPasswdCheck.PwdChkService;
import newland.iaf.base.trans.model.JumpTicket;
import newland.iaf.base.trans.service.JumpTicketService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.MerchUserType;
import newland.iaf.merch.service.MerchService;
import newland.iaf.merch.service.MerchUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 
 * @author rabbit
 * 
 */
@ParentPackage("struts")
@Namespace("/")
@Action(value = "merchLogin")
@Results({
		@Result(name = "list", type = "dispatcher"),
		@Result(name = "error", type = "dispatcher", location = "/merchLogin.jsp"),
		@Result(name = "logout", type = "dispatcher", location = "/merchLogin.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/merch/index.jsp"),
		@Result(name = "applyProduct", type = "dispatcher", location = "/merch/hicard-apply-prod.jsp"),
		})
public class MerchLoginAction extends MerchBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4579751232907588849L;

	/**
	 * 
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.merchUserService")
	private MerchUserService merchUserService;

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.jumpTicketService")
	private JumpTicketService jumpTicketService;

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.pwdChkService")
	private PwdChkService pwdChkService;

	@Resource(name = "com.newland.iaf.rsaService")
	private RsaService rsaService;
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;

	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	/**
	 * 
	 */
	private String loginName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 错误信息
	 */
	private String errorMsg;

	/**
	 * 跳转票据
	 */
	private String loginTicket;

	/**
	 * 验证码
	 */
	private String SecurityCode;

	private String target;
	
	private String applytarget;
	/**
	 * 
	 */
	private MerchUserType merchUserType;

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

	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	
	@Resource(name = "provinceService")
	private ProvinceService provinceService; 
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> cityMap;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> provMap;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String provinceCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;
	
	private List<LoanPdt> pdtList;
	
	/**
	 * 金掌柜客户端跳转专用方法
	 * 
	 * @return
	 */

	public String jump() {
		log.debug("loginTicket=" + loginTicket);
		JumpTicket jt;
		try {
			jt = jumpTicketService.decode(loginTicket);
		} catch (Exception e) {
			errorMsg = "不合法的跳转";
			log.error("loginTicket解码失败", e);
			return "error";
		}
		if (!jumpTicketService.check(jt)) {
			errorMsg = "不合法的跳转";
			log.error("loginTicket过期");
			return "error";
		}
		MerchUser mu = merchUserService.getMerchUser(jt.getHiMerchNo(),
				jt.getLoginName(), MerchUserType.hicard);
		if (mu == null) {
			errorMsg = "用户不存在";
			log.error("loginTicket过期");
			return "error";
		}
		MerchSession s = merchUserService.newMerchSession(mu.getImerchUser(),
				getIpaddr());
		s.reg(ServletActionContext.getRequest(),jt.getLoginName(),"merch");
		return "success";

	}
	
	public String jumpToApplyProduct() {
		log.debug("loginTicket=" + loginTicket);
		JumpTicket jt;
		try {
			jt = jumpTicketService.decode(loginTicket);
		} catch (Exception e) {
			errorMsg = "不合法的跳转";
			log.error("loginTicket解码失败", e);
			return "error";
		}
		if (!jumpTicketService.check(jt)) {
			errorMsg = "不合法的跳转";
			log.error("loginTicket过期");
			return "error";
		}
		MerchUser mu = merchUserService.getMerchUser(jt.getHiMerchNo(),
				jt.getLoginName(), MerchUserType.hicard);
		if (mu == null) {
			errorMsg = "用户不存在";
			log.error("loginTicket过期");
			return "error";
		}
		if (instList == null)
			instList = instService.queryInst();
		provMap = provinceService.getProvince();
		cityMap = provinceService.getProvince(provinceCode);
		pdtList=loanPdtService.queryAll();
		MerchSession s = merchUserService.newMerchSession(mu.getImerchUser(),
				getIpaddr());
		s.reg(ServletActionContext.getRequest(),jt.getLoginName(),"merch");
		return "applyProduct";
	}

	@InputConfig(resultName = "error")
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "loginName", shortCircuit = true, message = "用户名不能为空！"),
			@RequiredStringValidator(fieldName = "password", shortCircuit = true, message = "密码不能为空！") })
	@Override
	public String execute() throws Exception {
		if (merchUserType == null) {
			merchUserType = MerchUserType.common;
		}
		if (!check()) {
			return "error";
		}
		
		MerchUser mu = merchUserService.getMerchUser(null, loginName,
				merchUserType);
		MerchSession s = merchUserService.newMerchSession(mu.getImerchUser(),
				getIpaddr());
		s.reg(ServletActionContext.getRequest(),loginName,"merch");

		return "success";
	}

	public String homepageLogin(){
		if(isLogin()==false){
			return "logout";
		}
		return "success";
	}
	
	public String init() {
		this.target = this.getRequest().getParameter("target");
		//初始化跳转贷款申请路径
		this.applytarget = this.getRequest().getParameter("applytarget");
		if (isLogin() == false) {
			return "error";
		} else {
			// this.target = this.decode(this.target);
			return "success";
		}

	}

	private boolean check() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String sCode = (String) session.get("KAPTCHA_SESSION_KEY");
		if (!SecurityCode.equals(sCode)) {
			errorMsg = "验证码不正确!";
			log.error(errorMsg);
			return false;
		}
		MerchUser mu = merchUserService.getMerchUser(null, loginName,
				merchUserType);
		if (mu == null) {
			errorMsg = "登录名错误或者密码错误";
			log.error(errorMsg);
			return false;
		}
		
		if(mu.getMerchStat().toString().equals("UNUSED")){
			errorMsg = "登录用户已被禁用";
			log.error(errorMsg);
			return false;
		}
		
		Merch merch = merchService.findMerchById(mu.getImerch());
		if(merch==null){
			errorMsg = "找不到该商户信息!";
			log.error(errorMsg);
			return false;
		}
		
		if(!"1".equals(merch.getMerchStatus())){
			errorMsg = "该商户已被禁用!";
			log.error(errorMsg);
			return false;
		}
		password = rsaService.encrypt(password);

		if (MerchUserType.hicard.equals(mu.getUserType())) {
			try {
				PwdChkRespose resp =pwdChkService.check(loginName, password);
				if(resp==null){
					errorMsg="金掌柜登录无返回信息!";
					return false;
				}
				
				if(resp.getRetCode().equals("0003_01")){
					//系统无此用户
					errorMsg=resp.getRetMessage();
					return false;
				}
				
				if(resp.getRetCode().equals("0003_02")){
					//登录密码错误
					errorMsg=resp.getRetMessage();
					return false;
				}
				if(resp.getRetCode().equals("0003_03")){
					//系统异常
					errorMsg=resp.getRetMessage();
					return false;
				}
				
				if(resp.getRetCode().equals("0003_04")){
					//MAC码匹配有误
					errorMsg=resp.getRetMessage();
					return false;
				}
				if(resp.getRetCode().equals("0003_05")){
					//只能广州商户登陆!
					//errorMsg=resp.getRetMessage();
					errorMsg="本地区商户暂未开通汇融易业务,详情请致电400-628-6928咨询！";
					return false;
				}
				
				if(resp.getRetCode().equals("0003_00")){
					//登陆成功
					return true;
				}
			} catch (Exception e) {
				log.error("验证失败", e);
				errorMsg = "系统繁忙，请稍后再试.";
				log.error(errorMsg);
				return false;
			}
		}

		if (StringUtils.equals(mu.getPasswd(), DigestUtil.getSHA(password))) {
			return true;
		}
		errorMsg = "登录名错误或者密码错误";
		log.error(errorMsg);
		return false;
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

	public String getSecurityCode() {
		return SecurityCode;
	}

	public void setSecurityCode(String securityCode) {
		SecurityCode = securityCode;
	}

	public String getLoginTicket() {
		return loginTicket;
	}

	public void setLoginTicket(String loginTicket) {
		this.loginTicket = loginTicket;
	}

	public MerchUserType getMerchUserType() {
		return merchUserType;
	}

	public void setMerchUserType(MerchUserType merchUserType) {
		this.merchUserType = merchUserType;
	}

	public String getTarget() {
		if (StringUtils.isBlank(target)) {
			target = "/merch/welcome";
		}
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getApplytarget() {
		if (StringUtils.isBlank(applytarget)) {
			applytarget = "/merch/merchProdReq";
		}
		return applytarget;
	}

	public void setApplytarget(String applytarget) {
		this.applytarget = applytarget;
	}

	public Map<String, String> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<String, String> cityMap) {
		this.cityMap = cityMap;
	}

	public Map<String, String> getProvMap() {
		return provMap;
	}

	public void setProvMap(Map<String, String> provMap) {
		this.provMap = provMap;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public List<LoanPdt> getPdtList() {
		return pdtList;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

}
