package newland.iaf.base.model.dict;
/**
 * 资金流类型定义
 * @author new
 *
 */
public enum FundFlowType {
	/**
	 * 还款
	 */
	REFUND("平台内还款：金掌柜"),
	/**
	 * 平台外还款
	 */
	OTH_REFUND("平台外还款"),
	/**
	 * 放款
	 */
	CREDIT("平台内放款：金掌柜"),
	/**
	 * 平台外放款
	 */
	OTH_CREDIT("平台外放款");
	
	String desc;
	
	FundFlowType(String desc){
		this.desc=desc;
	}

	public String getDesc() {
		return desc;
	}

}
