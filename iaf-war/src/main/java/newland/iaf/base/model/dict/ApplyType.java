package newland.iaf.base.model.dict;

public enum ApplyType {
	/**
	 * 冻结请求
	 */
	FREEZE("冻结请求"),
	/**
	 * 解冻
	 */
	THAW("解冻请求"),
	/**
	 * 差错处理
	 */
	ERR_HANDLING("差错处理");
	
	String desc;
	
	ApplyType(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}

}
