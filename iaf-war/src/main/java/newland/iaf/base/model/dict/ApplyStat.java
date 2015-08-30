package newland.iaf.base.model.dict;

public enum ApplyStat {
	/**
	 * 申请中
	 */
	APPLY("申请中"),
	/**
	 * 已处理
	 */
	SUCCESS("已处理"),
	/**
	 * 退回
	 */
	REFUSE("退回"),
	/**
	 * 机构解冻
	 */
	CANCEL("撤销"),
	/**
	 * 商户未还款
	 */
	MERCH_DELIN("欠款逾期"),
	/**
	 * 商户已还款
	 */
	MERCH_PAYED("商户已还款"),
	FREEZE_APPLY("冻结申请中"),
	UNFREEZE_APPLY("解冻申请中"),
	FREEZE_ACCEPT("已受理"),
	FREEZE_REFUSE("冻结不受理"),
	UNFREEZE_ACCRPT("已受理"),
	UNFREEZE_REFUSE("解冻不受理"),
	FREEZE_SUCCESS("冻结完成"),
	FREEZE_FAILE("冻结失败"),
	UNFREEZE_SUCCESS("解冻完成"),
	UNFREEZE_FAILE("解冻失败");
	String desc;
	
	ApplyStat(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}

}
