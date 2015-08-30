package newland.iaf.base.model.dict;

public enum PdtType {
	/**
	 * 机构产品
	 */
	INST_PROD("产品申请"),
	/**
	 * 竟标产品
	 */
	MERCH_PROD("普通申请");
	String desc;
	PdtType(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}

}
