package newland.iaf.base.model.dict;

public enum OperStat {
	/**
	 * 失败
	 */
	FAIL("失败"),
	/**
	 * 成功
	 */
	SUCCESS("成功");
	
	String desc;
	
	OperStat(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return this.desc;
	}
}
