package newland.iaf.base.model.dict;

public enum AllowType {
	/**
	 * 随便都可以访问
	 */
	ALL("随便都可以访问"),
	/**
	 * 机构用户才可以访问
	 */
	INST("机构用户才可以访问"),
	/**
	 * 商户用户才可以访问
	 */
	MERCH("商户用户才可以访问"),
	/**
	 * 专有，只有上传者才可以访问
	 */
	EXP("专有");
	String desc;
	AllowType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
}
