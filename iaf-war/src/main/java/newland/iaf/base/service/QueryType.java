package newland.iaf.base.service;

public enum QueryType {
	/**
	 * 所有
	 */
	ALL,
	/**
	 * 查询当期欠款
	 */
	BALANCE,
	/**
	 * 逾期
	 */
	DELIN_QUENCY,
	/**
	 * 冻结
	 */
	FREEZE;
}
