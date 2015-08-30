package newland.iaf.base.model.dict;

public enum RefundType {
	/**
	 * 分期还款
	 */
	TERM_LOAN("等额分期还款"),
	/**
	 * 一次性归还
	 */
	ONE_TIME("一次性还本");
	String desc;
	RefundType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
	
}
