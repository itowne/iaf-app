package newland.iaf.base.model.dict;

public enum HcTransType {

	/**
	 * 0/消费
	 */
	CONSUM("0"),
	/**
	 * 6/资金归集
	 */
	CASH_SWEEP("6"),
	/**
	 * 3/查询
	 */
	QUERY("3"),
	/**
	 * 2/重打印
	 */
	RE_PRINT("2"),
	/**
	 * 7/付款
	 */
	PAYMENT("7"),
	/**
	 * 汇融易转账
	 */
	TRANS("8");
	
	String value ;
	HcTransType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
