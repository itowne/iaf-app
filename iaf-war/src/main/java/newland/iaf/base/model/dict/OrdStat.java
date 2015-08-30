package newland.iaf.base.model.dict;
/**
 * 订单状态定义
 * @author shizn
 *
 */
public enum OrdStat {
	/**
	 * 提交订单后的状态
	 */
	APPLY("提交申请"),
	
	/**
	 * 机构接受申请
	 */
	ACCEPT("已受理，审核中"),
	
	/**
	 * 审核通过，未放款
	 */
	AUDIT("审核通过,待放款"),
	/**
	 * 审核通过正在审核放款情况
	 */
	CREDITING("放款中"),
	/**
	 * 已还款，到账审核中
	 */
	REFUNDING("商户还款中，未审核"),
	/**
	 * 已放款，商户还款中
	 */
	REFUND("还款中"),
	/**
	 * 过期未还款
	 */
	DELIN_QUENCY("逾期"),
	/**
	 * 贷款全额还清
	 */
	PAID_UP_LOAN("已还清"),
	
	/* 以上是正常流程状态，以下是非正常流程状态  */
	
	/**
	 * 审核未通过
	 */
	AUDIT_REFUSE("审核未通过"),
	/**
	 * 受理过期
	 */
	ACCEPT_OVERDUE("受理逾期"),
	/**
	 * 机构不受理商户申请
	 */
	ACCEPT_REFUSE("不受理"),
	/**
	 * 申请过期状态
	 */
	APPLY_OVERDUE("申请逾期"),
	/**
	 * 机构作废订单，或商户撤销订单
	 */
	CANCEL("撤销申请");
	
	String desc;

	OrdStat (String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
}
