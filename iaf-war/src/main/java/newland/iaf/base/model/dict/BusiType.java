package newland.iaf.base.model.dict;

/**
 * 商户的经营状况
 */
public enum BusiType {

	/**
	 * 商户经营总体良好。
	 */
	GOOD("商户经营总体良好。"),
	/**
	 * 商户经营状况一般。
	 */
	ORDINARY("商户经营状况一般。"),
	/**
	 * 商户经营状况较差，但会有改善。
	 */
	WORSE("商户经营状况较差，但会有改善。"),
	/**
	 * 商户经营状况差，风险很高。
	 */
	BAD("商户经营状况差，风险很高。");

	String desc;

	BusiType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
}
