package newland.iaf.base.sms;

/**
 * 
 * @author rabbit
 * 
 */
public enum AuthCodeType {

	/**
	 * 机构用户登录验证码
	 */
	instUsrLoginAuthCode,

	/**
	 * 商户用户登录验证码
	 */
	merchUsrLoginAuthCode,

	/**
	 * 机构用户支付验证码
	 */
	instUsrPayAuthCode,

	/**
	 * 商户登录验证码
	 */
	merchUsrPayAuthoCode,

	/**
	 * 汇卡后台用户登录验证码
	 */
	bsUserLoginAuthCode;
}
