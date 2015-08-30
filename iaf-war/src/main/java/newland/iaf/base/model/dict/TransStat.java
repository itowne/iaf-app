package newland.iaf.base.model.dict;

public enum TransStat {
	/**
	 * 初始态
	 */
	INITIAL("生成支付指令"),
	/**
	 * 转账成功未确认
	 */
	UNCONFIRMED("支付指令已提交,状态未确认"),
	/**
	 * 转账成功已确认
	 */
	SUCCESS("支付已确认"),
	/**
	 * 异常
	 */
	ABNORMAL("支付异常"),
	/**
	 * 撤销
	 */
	EXPIRY("撤销"),
	/**
	 * 回滚
	 */
	EXPIRY_ERR("回滚异常");
	
	String desc;
	
	TransStat(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}

}
