package newland.iaf.base.model.dict;
/**
 * 支付方式定义
 * @author new
 *
 */
public enum GateWayType {
	/**
	 * 金掌柜
	 */
	HICARD("N", "汇融易转账"),
	/**
	 * 网银
	 */
	NETBANK("B", "网银转账");
	
	private String value;
	
	private String desc;
	
	GateWayType(String value, String desc){
		this.value = value;
		this.desc = desc;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public static GateWayType getTransType(String value){
		if (value.equals("N")){
			return HICARD;
		}else if (value.equals("B")){
			return NETBANK;
		}
		return null;
	}

}
