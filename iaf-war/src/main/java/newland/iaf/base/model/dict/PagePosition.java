package newland.iaf.base.model.dict;

public enum PagePosition {
	/**
	 * 封面
	 */
	COVER(""),
	/**
	 * 基本资料页
	 */
	BASIC_INFO("封面"),
	/**
	 * 现场调查
	 */
	FIELD_SURVY("基本资料"),
	/**
	 * 交易记录
	 */
	TRANSFER("交易记录"),
	/**
	 * 服务巡检记录
	 */
	ROUTING_ISPECTION("服务巡检记录"),
	/**
	 * 装机撤机
	 */
	INSTALL("装机撤机"),
	/**
	 * 其他文件
	 */
	OTHER_INFO("其他文件"),
	/**
	 * 经营数据核查情况
	 */
	MERCH_BUSI_DATA_VERIFICATION("经营数据核查情况"),
	/**
	 * 封底
	 */
	BACK_COVER("封底");
	
	String desc;
	
	PagePosition(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
}
