package newland.iaf.inst.action;

import javax.annotation.Resource;

import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.service.InstUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;

/**
 * 机构用户修改密码
 * @author tang
 *
 */
@ParentPackage("struts")
@Namespace("/inst")
@Action(value = "instPwd")
@Results({
	@Result(name = "error", type = "dispatcher", location = "/inst/password.jsp"),
	@Result(name = "success", type = "redirect", location = "/inst/passwordSuccess.jsp") })
public class InstPwdAction extends InstBaseAction {

	private static final long serialVersionUID = 5823837990835188724L;

	private final Logger log = this.logger;

	@Resource(name = "com.newland.iaf.instUserService")
	private InstUserService instUserService;

	private String oldPwd;

	private String newPwd;

	private String confirmPwd;
	
	private String oldPwdError;

	private String newPwdError;

	private String confirmPwdError;
	
	@Override
	public String execute() throws Exception {
		InstSession instSession = this.getUserSession();
		InstUser instUser = instSession.getInstUsr();
		Long iinstuser = instUser.getIinstUser();
		
		//if(check()){
			try{
				this.instUserService.changeUserPassword(iinstuser, oldPwd, newPwd,instSession,getIpaddr());
			}catch (Exception e){
				oldPwdError = "旧密码输入错误";
				log.error(oldPwdError);
				super.addActionMessage(oldPwdError);
				return "error";
			}
			return "success";
		//}
		
		//return "error";
	}

	/**
	 * 验证字段
	 */
	private boolean check(){
		boolean ret = true;
		if (StringUtils.isBlank(oldPwd)) {
			oldPwdError = "旧密码不能为空";
			log.error(oldPwdError);
			super.addActionMessage(oldPwdError);
			ret = false;
		}
		if (StringUtils.isBlank(newPwd)) {
			newPwdError = "新密码不能为空";
			log.error(newPwdError);
			super.addActionMessage(newPwdError);
			ret = false;
		}else if(newPwd.length()<6||newPwd.length()>18){
			newPwdError = "密码长度要在6~18位";
			log.error(newPwdError);
			super.addActionMessage(newPwdError);
			ret = false;
		}
		if (StringUtils.isBlank(confirmPwd)) {
			confirmPwdError = "确认密码不能为空";
			log.error(confirmPwdError);
			super.addActionMessage(confirmPwdError);
			ret = false;
		}else if(!StringUtils.equals(newPwd, confirmPwd)){
			confirmPwdError = "两次密码不一致";
			log.error(confirmPwdError);
			super.addActionMessage(confirmPwdError);
			ret = false;
		}
		return ret;
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

	public String getOldPwdError() {
		return oldPwdError;
	}

	public void setOldPwdError(String oldPwdError) {
		this.oldPwdError = oldPwdError;
	}

	public String getNewPwdError() {
		return newPwdError;
	}

	public void setNewPwdError(String newPwdError) {
		this.newPwdError = newPwdError;
	}

	public String getConfirmPwdError() {
		return confirmPwdError;
	}

	public void setConfirmPwdError(String confirmPwdError) {
		this.confirmPwdError = confirmPwdError;
	}

}
