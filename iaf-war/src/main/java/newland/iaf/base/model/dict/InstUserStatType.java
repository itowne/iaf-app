package newland.iaf.base.model.dict;

public enum InstUserStatType {
	/**
	 * 停用
	 */
	UNUSED("停用"),
	/**
	 * 正常
	 */
	USED("正常");
	String desc;
	InstUserStatType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
}
