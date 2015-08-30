package newland.iaf.base.model.dict;

public enum InstStatusType {
	/**
	 * 待审核
	 */
	NEED_CHECL("待审核"),
	/**
	 * 可用
	 */
	USED("可用"),
	/**
	 * 禁用
	 */
	UNUSED("禁用");
	String desc;
	InstStatusType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
}
