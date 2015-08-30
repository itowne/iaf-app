package newland.iaf.base.model.dict;

public enum InstType {
	/**
	 * 商户
	 */
	MERCH("商户"),
	/**
	 * 机构
	 */
	INST("机构"),
	/**
	 * 汇卡
	 */
	HICARD("汇融易"),
	/**
	 * 其他
	 */
	OTHERS("其他");
	
	String desc;
	
	InstType(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}

}
