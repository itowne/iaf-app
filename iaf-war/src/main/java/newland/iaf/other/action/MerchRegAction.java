package newland.iaf.other.action;

import java.util.Map;

import javax.annotation.Resource;

import newland.base.util.DigestUtil;
import newland.iaf.backstage.action.BSBaseAction;
import newland.iaf.base.model.dict.CreditType;
import newland.iaf.base.model.dict.ForbidenType;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.base.service.MccService;
import newland.iaf.merch.model.AcceptType;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchType;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.MerchUserType;
import newland.iaf.merch.service.MerchCreditReportService;
import newland.iaf.merch.service.MerchService;
import newland.iaf.merch.service.MerchUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antlr.Utils;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 商户注册
 * 
 * @author rabbit
 * 
 */
@ParentPackage("struts")
@Namespace("/")
@Action(value = "merchReg")
@Results({
		@Result(name = "index", type = "dispatcher", location = "/merchReg.jsp"),
		@Result(name="merchRegNext",type="dispatcher",location="/merchRegNext.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/merchRegSuccess.jsp") })
public class MerchRegAction extends BSBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1264330253513963661L;

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.merchUserService")
	private MerchUserService merchUserService;
	
	@Resource(name = "merchCreditReportService")
	private MerchCreditReportService merchCreditReportService;
	
	@Resource(name="mccService")
	private MccService mccService;
	
	private String mccCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> mccMap;

	private String loginNameError;

	private String passwd;

	private String passwdError;

	private String confirmPasswd;

	private String confirmPasswdError;

	private String merchName;

	private String merchNameError;

	private String merchNature = "0";

	private String merchNatureError;

	private String contract;

	private String contractTel;

	private String captcha;

	private String qqUid;

	private String bankCode;

	private String accountName;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private String captchaError;

	@Override
	public String execute() throws Exception {
		return "index";
	}

	private String loginName;
	
	private String code;

	/**
	 * 商户注册申请
	 * 
	 * @return
	 */
	@InputConfig(resultName = "merchRegNext")
	@Validations(requiredStrings = {
			//@RequiredStringValidator(fieldName = "loginName", shortCircuit = true, message = "登录账号不能为空！"),
			//@RequiredStringValidator(fieldName = "passwd", shortCircuit = true, message = "密码不能为空！"),
			//@RequiredStringValidator(fieldName = "confirmPasswd", shortCircuit = true, message = "确认密码不能为空！"),
			//@RequiredStringValidator(fieldName = "merchName", shortCircuit = true, message = "商户名称不能为空！"),
			@RequiredStringValidator(fieldName = "contract", shortCircuit = true, message = "联系人不能为空！"),
			@RequiredStringValidator(fieldName = "contractTel", shortCircuit = true, message = "联系电话不能为空！") })
	public String reg() throws Exception {
//		if (!check()) {
//			return "merchRegNext";
//		}

		Merch merch = new Merch();
		merch.setContract(contract);
		merch.setContractTel(contractTel);
		if(StringUtils.isNotBlank(merchName)){
			merch.setMerchName(merchName);
		}else{
			merch.setMerchName("--");
		}
		merch.setMerchType(MerchType.GENERAL);
		merch.setQqUid("--");
		merch.setCredit(CreditType.C);
		merch.setMerchStatus("1");
		if(StringUtils.isNotBlank(code)){
			merch.setIndustry(mccService.queryById(code).getName());
		}else{
			merch.setIndustry("--");
		}
		merchService.saveMerch(merch);

		MerchBusiInfo mbi = new MerchBusiInfo();
		mbi.setImerch(merch.getImerch());
		String str = "--";
//		if ("stateRun".equals(merchNature)) {
//			str = "国贸";
//		} else if ("collective".equals(merchNature)) {
//			str = "集体";
//		} else if ("privateEnter".equals(merchNature)) {
//			str = "私企";
//		} else if ("indBusiness".equals(merchNature)) {
//			str = "个体工商户";
//		} else if ("jointVenture".equals(merchNature)) {
//			str = "合资";
//		}
		mbi.setMerchNatrue(str);
		mbi.setBankCode("--");
		mbi.setAccountName("--");
		merchService.saveMerchBusiInfo(mbi);

		MerchLegalPers mlp = new MerchLegalPers();
		mlp.setImerch(merch.getImerch());
		merchService.saveMerchLegalPers(mlp);

		MerchUser mu = new MerchUser();
		mu.setLoginName(loginName);
		mu.setImerch(merch.getImerch());
		mu.setMerchStat(ForbidenType.USED);
		mu.setUserType(MerchUserType.common);
		mu.setMerchNo(merch.getMerchNo());
		mu.setPasswd(DigestUtil.getSHA(passwd));
		merchUserService.regMerchUser(mu);
		//
		this.merchCreditReportService.setInfoPermission(merch,
				PagePosition.BASIC_INFO, AcceptType.getAcceptType(true));
		this.merchCreditReportService.setInfoPermission(merch,
				PagePosition.FIELD_SURVY, AcceptType.getAcceptType(true));
		this.merchCreditReportService.setInfoPermission(merch,
				PagePosition.OTHER_INFO, AcceptType.getAcceptType(true));
		this.merchCreditReportService.setInfoPermission(merch,
				PagePosition.ROUTING_ISPECTION, AcceptType.getAcceptType(true));
		this.merchCreditReportService.setInfoPermission(merch,
				PagePosition.TRANSFER, AcceptType.getAcceptType(true));
		this.merchCreditReportService.setInfoPermission(merch,
				PagePosition.INSTALL, AcceptType.getAcceptType(true));
		this.merchCreditReportService.setInfoPermission(merch,
				PagePosition.MERCH_BUSI_DATA_VERIFICATION, AcceptType.getAcceptType(true));
		return "success";
	}

	public String merchRegNext(){
		if (!check()) {
			return "index";
		}
		mccCode="";
		mccMap=mccService.getFirstBussin();
		return "merchRegNext";
	}
	
	private boolean check() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String kaptcha = (String) session.get("KAPTCHA_SESSION_KEY");
		if (!StringUtils.equals(captcha, kaptcha)) {
			captchaError = "验证码不正确!";
			log.error(captchaError);
			return false;
		}

		boolean ret = true;

		if (!StringUtils.equals(passwd, confirmPasswd)) {
			confirmPasswdError = "密码不一致";
			log.error(confirmPasswdError);
			ret = false;
		}

		MerchUser mu = merchUserService.getMerchUser(null, loginName);
		if (mu != null) {
			loginNameError = "登录名已被使用";
			log.error(loginNameError);
			ret = false;
		}
		
		
//		Merch merch = merchService.getMerch(merchName);
//		if (merch != null) {
//			merchNameError = "商户名已被使用";
//			log.error(merchNameError);
//			ret = false;
//		}
		return ret;
	}

	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getConfirmPasswd() {
		return confirmPasswd;
	}

	public void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}

	public String getMerchName() {
		return merchName;
	}

	public String getMccCode() {
		return mccCode;
	}

	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	public Map<String, String> getMccMap() {
		return mccMap;
	}

	public void setMccMap(Map<String, String> mccMap) {
		this.mccMap = mccMap;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getMerchNature() {
		return merchNature;
	}

	public void setMerchNature(String merchNature) {
		this.merchNature = merchNature;
	}

	public String getContractTel() {
		return contractTel;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getLoginNameError() {
		return loginNameError;
	}

	public void setLoginNameError(String loginNameError) {
		this.loginNameError = loginNameError;
	}

	public String getConfirmPasswdError() {
		return confirmPasswdError;
	}

	public void setConfirmPasswdError(String confirmPasswdError) {
		this.confirmPasswdError = confirmPasswdError;
	}

	public String getMerchNameError() {
		return merchNameError;
	}

	public void setMerchNameError(String merchNameError) {
		this.merchNameError = merchNameError;
	}

	public String getMerchNatureError() {
		return merchNatureError;
	}

	public void setMerchNatureError(String merchNatureError) {
		this.merchNatureError = merchNatureError;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPasswdError() {
		return passwdError;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getQqUid() {
		return qqUid;
	}

	public void setQqUid(String qqUid) {
		this.qqUid = qqUid;
	}

	public void setPasswdError(String passwdError) {
		this.passwdError = passwdError;
	}

	public String getCaptchaError() {
		return captchaError;
	}

	public void setCaptchaError(String captchaError) {
		this.captchaError = captchaError;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

}
