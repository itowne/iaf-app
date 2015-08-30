package newland.iaf.inst.action;

import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.InstApplyRequest;
import newland.iaf.base.model.dict.SexType;
import newland.iaf.base.service.InstApplyReqService;
import newland.iaf.base.service.ProvinceService;

import org.apache.commons.lang3.StringUtils;
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
 * 首页机构注册Action
 * 
 * @author wwa
 * 
 */
@ParentPackage("struts")
@Namespace("/")
@Action(value = "instReg")
@Results({
		@Result(name = "error", type = "dispatcher", location = "/instReg.jsp"),
		@Result(name = "instRegSuccess", type = "dispatcher", location = "/instSignUpSuccess.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/instReg.jsp") })
public class InstRegAction extends InstBaseAction {

	private static final long serialVersionUID = -8723690993731343648L;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 机构注册申请
	 */
	@Resource(name = "instApplyReqService")
	InstApplyReqService instApplyReqService;

	/**
	 * 
	 */
	@Resource(name = "provinceService")
	private ProvinceService provinceService;

	/**
	 * 
	 */
	private Map<String, String> cityMap;

	/**
	 * 
	 */
	private Map<String, String> provMap;

	// 公司名称
	private String instName;

	// 公司名称错误信息
	private String instNameError;

	// 省份
	private String province;

	/**
	 * 省份代码
	 */
	private String provinceCode;

	// 地市
	private String city;

	/**
	 * 城市代碼
	 */
	private String cityCode;

	// 联系人
	private String contactName;

	// 联系人错误信息
	private String contactNameError;

	// 联系电话
	private String mobilePhone;

	// 联系电话错误信息
	private String mobilePhoneError;

	// 性别
	private String gender;

	// 验证码
	private String captcha;

	// 验证码错误信息
	private String captchaError;

	@Override
	public String execute() throws Exception {
		provMap = provinceService.getProvince();
		provinceCode="";
		cityMap = provinceService.getProvince(provinceCode);
		return "success";
	}

	/**
	 * 到机构注册页面
	 */
	public String toInstReg() throws Exception {
		return execute();
	}

	/**
	 * 机构注册
	 */
	@InputConfig(resultName = "success")
	@Validations(
	    requiredStrings = {
	        @RequiredStringValidator(fieldName = "instName",   shortCircuit=true, message="公司名称不能为空！"),
	        @RequiredStringValidator(fieldName = "provinceCode", shortCircuit=true, message="省份不能为空！"),
	        @RequiredStringValidator(fieldName = "cityCode",   shortCircuit=true, message="城市不能为空！"),
	        @RequiredStringValidator(fieldName = "contactName", shortCircuit=true, message="联系人不能为空！"),
	        @RequiredStringValidator(fieldName = "mobilePhone",   shortCircuit=true, message="联系电话不能为空！"),
	        @RequiredStringValidator(fieldName = "gender", shortCircuit=true, message="性别不能为空！" )
	    }
	)
	public String instReg() throws Exception {
		provMap = provinceService.getProvince();
		cityMap = provinceService.getProvince(provinceCode);
		if (!check()) {
			return "error";
		}

		InstApplyRequest req = new InstApplyRequest();
		req.setInstName(instName);
		req.setProvince(provMap.get(provinceCode));
		req.setProvinceCode(provinceCode);
		req.setCity(cityMap.get(cityCode));
		req.setCityCode(cityCode);
		req.setContactName(contactName);
		req.setMobilePhone(mobilePhone);

		if (StringUtils.equals("MAN", gender)) {
			req.setGender(SexType.MAN);
		} else {
			req.setGender(SexType.woman);
		}

		this.instApplyReqService.apply(req);
		return "instRegSuccess";
	}

	/**
	 * 验证字段
	 */
	private boolean check() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String kaptcha = (String) session.get("KAPTCHA_SESSION_KEY");
		if (!StringUtils.equals(captcha, kaptcha)) {
			captchaError = "验证码不正确!";
			log.error(captchaError);
			return false;
		}
		return true;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getInstNameError() {
		return instNameError;
	}

	public void setInstNameError(String instNameError) {
		this.instNameError = instNameError;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNameError() {
		return contactNameError;
	}

	public void setContactNameError(String contactNameError) {
		this.contactNameError = contactNameError;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getMobilePhoneError() {
		return mobilePhoneError;
	}

	public void setMobilePhoneError(String mobilePhoneError) {
		this.mobilePhoneError = mobilePhoneError;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getCaptchaError() {
		return captchaError;
	}

	public void setCaptchaError(String captchaError) {
		this.captchaError = captchaError;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
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
}
