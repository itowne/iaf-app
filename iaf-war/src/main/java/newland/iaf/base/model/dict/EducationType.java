package newland.iaf.base.model.dict;

public enum EducationType {
	
	/**
	 * 小学
	 */
	PRIMARY_SCHOOLS("小学"),
	/**
	 * 初中
	 */
	JUNIOR_MIDDLE("初中"),
	/**
	 * 高中
	 */
	SENIOR_MIDDLE("高中"),
	/**
	 * 中专
	 */
	SECONDARY_VOCATIONAL("中专"),
	/**
	 * 大专
	 */
	COLLEGE_DEGREE("大专"),
	/**
	 * 本科
	 */
	BACHELOR("本科"),
	/**
	 * 硕士
	 */
	MASTER("硕士"),
	/**
	 * 博士
	 */
	DOCTOR("博士");
	String desc;
	EducationType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}

}
