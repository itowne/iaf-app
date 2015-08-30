package newland.iaf.base.model.dict;

/**
 * 系统参数类型
 * 
 * @author rabbit
 * 
 */
public enum SysParamType {

	/**
	 * 跳转密钥
	 */
	jumpTicketCrypt,

	/**
	 * 商户基本信息
	 */
	merchBaseInfo,

	/**
	 * 机构基本信息
	 */
	instBaseInfo,

	/**
	 * 服务巡查记录
	 */
	serviceLog,

	/**
	 * 交易记录
	 */
	transLog,

	/**
	 * 金掌柜密码校验
	 */
	pwdCheck,

	/**
	 * 安装记录
	 */
	installLog,

	/**
	 * 机构LOGO文件
	 */
	instLogo,
	/**
	 * 商户LOGO文件
	 */
	merchLogo,
	/**
	 * 产品LOGO文件
	 */
	loanPdtLogo,
	/**
	 * 风险控制
	 */
	riskControl,

	/**
	 * 短信url
	 */
	smsUrl,
	/**
	 * 金掌柜支付网关
	 */
	payUrl,
	/**
	 * 省份表
	 */
	province,
	/**
	 * 失效日期
	 */
	expireDate,
	/**
	 * organNo交易机构号
	 */
	organNo,
	/**
	 * 交易平台密码
	 */
	tradePwd,
	/**
	 *交易手续费
	 */
	rate;
}
