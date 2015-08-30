package newland.iaf.base.model.dict;

/**
 * 商户的考察意见
 */
public enum OpinType {
	/**
	 * 优质商户。
	 */
	HIGHQUALITY("优质商户。"),
	/**
	 * 一般商户，风险较低。
	 */
	LOWRISK("高风险等级商户，暂不考虑发展。"),
	/**
	 * 一般商户，有一定风险，需要对其业务有所监控。
	 */
	GENERALRISK("高风险等级商户，暂不考虑发展。"),
	/**
	 * 高风险等级商户，暂不考虑发展。
	 */
	HIGHRISK("高风险等级商户，暂不考虑发展。");

	String desc;

	OpinType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
}
