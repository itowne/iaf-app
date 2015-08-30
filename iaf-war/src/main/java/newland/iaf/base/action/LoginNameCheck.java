package newland.iaf.base.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import newland.iaf.inst.action.InstBaseAction;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.service.MerchService;
import newland.iaf.merch.service.MerchUserService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ParentPackage("json-default")
@Namespace("/")
@Action(value = "loginName")
@Results({
		@Result(name = "success", type = "json") })
public class LoginNameCheck extends InstBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2672944514614901388L;
	private String loginName;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource(name = "com.newland.iaf.merchUserService")
	private MerchUserService merchUserService;
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	private String msg;
	
	private String tip;
	
	private String merchName;
	public String execute(){
		msg="not";
		MerchUser mu = merchUserService.getMerchUser(null, loginName);
		if (mu != null) {
			log.error("登录名已被使用");
			msg="yes";
		}
		return "success";
	}
	
	public String merchNameCheck(){
		tip="not";
		Merch merch = merchService.getMerch(merchName);
		if (merch != null) {
			log.error("商户名已被使用");
			tip="yes";
		}
		return "success";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}
}
