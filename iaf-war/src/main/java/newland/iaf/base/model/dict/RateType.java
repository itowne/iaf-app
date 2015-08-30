package newland.iaf.base.model.dict;
/**
 * 利率类型
 * @author Mr.Towne
 *
 */
public enum RateType {
	/**
	 * 日利率
	 */
	DAY("日利率"),
	/**
	 * 月利率
	 */
	MONTH("月利率"),
	/**
	 * 年利率
	 */
	YEAR("年利率");
	String desc;
	
	RateType(String desc){
		this.desc=desc ;
	}

	public String getDesc() {
		return desc;
	}
	
}
