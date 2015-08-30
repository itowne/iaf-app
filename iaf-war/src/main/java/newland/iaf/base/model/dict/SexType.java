package newland.iaf.base.model.dict;

public enum SexType {
	/**
	 * 男
	 */
	MAN("男"),
	/**
	 * 女
	 */
	woman("女");
	String desc;
	SexType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}

}
