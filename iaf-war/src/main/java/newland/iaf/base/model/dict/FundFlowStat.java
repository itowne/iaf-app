package newland.iaf.base.model.dict;
/**
 * 资金流水状态定义
 * @author new
 *
 */
public enum FundFlowStat {
	/**
	 * 流水异常
	 */
	ABNORMAL("流水异常"),
	/**
	 * 放款交易初始,生成交易信息
	 */
	INIT("放款初始化,未提交"),
	/**
	 * 还款交易初始,生成交易信息
	 */
	REFUND_INIT("还款初始化，未提交"),
	/**
	 * 受理中
	 */
	AUDIT("受理中"),
	/**
	 * 转账中
	 */
	TRANSFER("转账中"),
	/**
	 * 已达账
	 */
	SUCCESS("已对账"),
	/**
	 * 机构撤销
	 */
	CANCEL("撤销"),
	/**
	 * 
	 */
	EXPIRY("退款");
	
	String desc;
	FundFlowStat(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}

}
