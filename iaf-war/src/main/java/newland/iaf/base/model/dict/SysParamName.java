package newland.iaf.base.model.dict;

/**
 * 
 * @author rabbit
 * 
 */
public enum SysParamName {
	/**
	 * 交易记录文件获取参数_ip地址
	 */
	FTP_REMOTE_IPADDR,

	/**
	 * 交易记录文件获取参数_路径
	 */
	FTP_REOMTE_PATH,

	/**
	 * 交易记录文件获取参数_端口
	 */
	FTP_REMOTE_PORT,

	/**
	 * 文件名前缀
	 */
	FTP_FILE_PREFIX,

	/**
	 * 
	 */
	FTP_LOCAL_PATH,

	FTP_USERNAME,

	FTP_PASSWD,

	/**
	 * 
	 */
	JUMP_TICKET_KEY,

	/**
	 * 
	 */
	JUMP_TICKET_IV,

	JUMP_TICKET_EXPIRE_SECOND,

	/**
	 * 
	 */
	PWD_CHECK_URL,
		
	PWD_CHECK_AES_KEY,
	
	PWD_CHECK_AES_IV,
	
	PWD_CHECK_SALT_VALUE,
	
	
	/**
	 * 金掌柜支付网关地址
	 */
	PAY_URL,

	/**
	 * 上月POS交易额比前一个月下降
	 */
	RISK_CONTROL_RATE,

	/**
	 * 没有pos交易连续天数
	 */
	RISK_CONTROL_POS_TRANS_DAYS,

	/**
	 * 商户上传文件路径
	 */
	MERCH_UPLOAD_PATH,

	/**
	 * 机构上传文件路劲
	 */
	INST_UPLOAD_PATH,

	/**
	 * 短信url地址1
	 */
	SMS_URL_1,
	/**
	 * 省份代码
	 */
	PROVINCE,
	/**
	 * 失效日期
	 */
	EXPIRE_DATE,
	/**
	 * 平台交易密码
	 */
	TRADE_PWD,
	/**
	 * 交易机构号
	 */
	ORGANNO,
	/**
	 * 交易下降率
	 */
	RISK_TRADE_RATE,
	/**
	 * 最低手续费
	 */
	MIN_RATE,
	/**
	 * 最高手续费
	 */
	MAX_RATE,
	/**
	 * 交易费率
	 */
	TRADE_RATE;
}
