package newland.iaf.base.model.dict;

/**
 * 经营区域
 */
public enum AreaType {
	/**
	 * 城区
	 */
	DOWNTOWN("城区"),
	/**
	 * 郊区
	 */
	SUBURB("郊区"),
	/**
	 * 边远地区
	 */
	REMOTE("边远地区");
	
	String desc;

	AreaType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
}
