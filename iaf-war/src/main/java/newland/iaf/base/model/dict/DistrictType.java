package newland.iaf.base.model.dict;

/**
 * 经营地段
 */
public enum DistrictType {
	/**
	 * 商业区
	 */
	BUSIN("商业区"),
	/**
	 * 工业区
	 */
	INDUSTRY("工业区"),
	/**
	 * 住宅
	 */
	HOUSE("住宅");

	String desc;

	DistrictType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
}
