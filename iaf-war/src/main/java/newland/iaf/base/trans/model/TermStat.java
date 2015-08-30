package newland.iaf.base.trans.model;

public enum TermStat {
	/**
	 * 
	 */
	installed("1", "已装"),

	/**
	 * 
	 */
	waitForInstall("2", "待装"),

	/**
	 * 
	 */
	cancel("3", "退单"),

	/**
	 * 
	 */
	unstalled("4", "退单"),

	/**
	 * 
	 */
	miss("0", "丢失"),

	/**
	 * 
	 */
	stop("6", "停机 ");

	private String desc;

	private String value;

	private TermStat(String val, String desc) {
		this.desc = desc;
		value = val;
	}

	public String getDesc() {
		return desc;
	}

	public String toString() {
		return value;
	}

}
