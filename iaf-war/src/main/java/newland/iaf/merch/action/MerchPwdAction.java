package newland.iaf.merch.action;

import javax.annotation.Resource;

import newland.base.util.DigestUtil;
import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.service.MerchUserService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * 修改密码
 * 
 * @author wwa
 * 
 */
@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "merchPwd")
@Results({
		@Result(name = "input", type = "dispatcher", location = "/merch/password.jsp"),
		@Result(name = "error", type = "dispatcher", location = "/merch/password.jsp"),
		@Result(name = "success", type = "redirect", location = "/merch/password_change_success.jsp") })
public class MerchPwdAction extends MerchBaseAction {

	private static final long serialVersionUID = -5911577685087122595L;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "com.newland.iaf.merchUserService")
	private MerchUserService merchUserService;

	private String oldPwd;

	private String newPwd;

	private String confirmPwd;

	private String msg;
	
	private Merch merch;

	@Override
	@InputConfig(resultName = "input")
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "oldPwd", shortCircuit = true, message = "旧密码不能为空!"),
			@RequiredStringValidator(fieldName = "newPwd", shortCircuit = true, message = "新密码不能为空!"),
			@RequiredStringValidator(fieldName = "confirmPwd", shortCircuit = true, message = "密码确认不能为空!") })
	public String execute() throws Exception {
		MerchSession merchSession = (MerchSession) SessionFilter
				.getIafSession();

		MerchUser merchUser = merchSession.getMerchUser();
		merch =merchSession.getMerch();
		if (!merchUser.getPasswd().equals(DigestUtil.getSHA(oldPwd.trim()))) {
			msg = "旧密码输入错误";
			//addFieldError("error", msg);
			super.addActionMessage(msg);
			return "error";
		}

		this.merchUserService.updateMerchUserPwd(merchUser, newPwd,
				merchSession, getIpaddr());
		msg = "密码修改成功";

		return "success";
	}

	public String input() {
		merch=this.getMerchSession().getMerch();
		return "input";
	}

	public MerchUserService getMerchUserService() {
		return merchUserService;
	}

	public void setMerchUserService(MerchUserService merchUserService) {
		this.merchUserService = merchUserService;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

}
