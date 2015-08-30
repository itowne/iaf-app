package newland.iaf.base.model.dict;

public enum PdtStat {
	/**
	 * 下架
	 */
	UNDERCARRIAGE("下架"),
	/**
	 * 上架
	 */
	GROUNDING("上架"),
	/**
	 * 过期
	 */
	EXPIRE("过期");
	String desc;
	PdtStat(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}

}
