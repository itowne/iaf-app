package newland.iaf.base.model.dict;
/**
 * 商户申诉状态
 * @author wlh
 *
 */
public enum AppealState {
	/**
	 * 申诉中
	 */
	APPLY("申诉中"),
	/**
	 * 已受理
	 */
	ACCEPT("已受理"),
	/**
	 * 不受理
	 */
	DENY("不受理");
	
	String desc;

	AppealState (String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
	
}
