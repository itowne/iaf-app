package newland.iaf.merch.model;

public enum MerchType {
	/**
	 * 金掌柜
	 */
	GOLD_SHOPKEEPER("金掌柜"),
	/**
	 * 银联商户
	 */
	UNION_PAY("银联商户"),
	/**
	 * 普通商户
	 */
	GENERAL("普通商户");
	
	/**
	 * 
	 */
	String desc;
	
	MerchType(String desc){
		this.desc = desc;
	}
	
	
	public String getDesc(){
		return desc;
	}
}
