package newland.iaf.base.sms;

import java.util.Date;

/**
 * 验证码
 * 
 * @author rabbit
 * 
 */
public class SmsAuthCode {

	/**
	 * 
	 */
	private String loginName;

	/**
	 * 短信号
	 */
	private String value;

	/**
	 * 生成时间
	 */
	private Date genTime;

	/**
	 * 过期时间
	 */
	private Date expTime;

	/**
	 * 验证码类型
	 */
	private AuthCodeType codeType;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public AuthCodeType getCodeType() {
		return codeType;
	}

	public void setCodeType(AuthCodeType codeType) {
		this.codeType = codeType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
