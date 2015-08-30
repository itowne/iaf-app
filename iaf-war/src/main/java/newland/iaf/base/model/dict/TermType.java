package newland.iaf.base.model.dict;

public enum TermType {

	/**
	 * 天
	 */
	DAY("天"),
	/**
	 * 个月
	 */
	MONTH("个月"),
	/**
	 * 年
	 */
	YEAR("年");
	
	String desc;

	TermType(String desc){
		this.desc=desc;
	}
	public String getDesc() {
		return desc;
	}
}
