package newland.iaf.base.model.dict;

public enum MerryType {
	/**
	 * 已婚
	 */
	MERRYED("已婚"),
	/**
	 * 未婚
	 */
	UNMERRYED("未婚"),
	/**
	 * 离婚
	 */
	DIVORCE("其他");
	String desc;
	MerryType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
}
