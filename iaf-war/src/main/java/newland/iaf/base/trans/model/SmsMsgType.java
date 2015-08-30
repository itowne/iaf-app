package newland.iaf.base.trans.model;

/**
 * 短信消息类型
 * 
 * @author rabbit
 * 
 */
public enum SmsMsgType {
	/**
	 * 系统消息
	 */
	sysMsg("系统消息"),

	/**
	 * 风险预警
	 */
	riskAlert("风险预警"),

	/**
	 * 普通消息
	 */
	commonMsg("普通消息"),

	/**
	 * 订单消息
	 */
	orderMsg("订单消息"),

	/**
	 * 冻结申诉
	 */
	freezeAppeal("冻结申诉"),

	/**
	 * 
	 */
	authCode("短信验证码");

	private final String desc;

	private SmsMsgType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
