package newland.iaf.base.model.dict;

public enum ForbidenType {
	/**
	 * 可用
	 */
	USED("可用"),
	/**
	 * 禁用
	 */
	UNUSED("禁用");
	String desc;
	ForbidenType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
}
