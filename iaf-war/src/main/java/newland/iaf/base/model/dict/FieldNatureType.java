package newland.iaf.base.model.dict;

/**
 * 营业用地性质
 */
public enum FieldNatureType {
	/**
	 * 自有
	 */
	OWN("自有"),
	/**
	 * 租用
	 */
	RENT("租用");

	String desc;

	FieldNatureType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
}
