package newland.iaf.utils.service;

public interface SerialNoService {
	/**
	 * 生成系统流水号
	 * @return
	 */
	String genSystemNo();
	/**
	 * 生成机构代码
	 * @return
	 */
	String genInstNo();
	/**
	 * 生成商户代码
	 * @return
	 */
	String genMerchNo();
	/**
	 * 生成产品代码
	 * @return
	 */
	String genInstProdNo();
	/**
	 * 生成竟标产品代码
	 * @return
	 */
	String genMerchDebitDibNo();
	/**
	 * 生成文件名
	 * @return
	 */
	String genFileNo();
	/**
	 * 生成订单编号
	 * @return
	 */
	String genOrdNo();
	/**
	 * 生成计划编号
	 * @return
	 */
	String genPlanNo();
	/**
	 * 生成订单资金流水号
	 * @return
	 */
	String genFundFlowNo();
	/**
	 * 生成支付流水号
	 * @return
	 */
	String genTransLogNo();
	
	String genNewDate();

}
