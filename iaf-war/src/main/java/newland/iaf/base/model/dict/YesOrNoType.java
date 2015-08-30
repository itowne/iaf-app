package newland.iaf.base.model.dict;

public enum YesOrNoType {
	/**
	 * 是
	 */
	YES("是"),
	/**
	 * 否
	 */
	NO("否");
	String desc;
	YesOrNoType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
}
