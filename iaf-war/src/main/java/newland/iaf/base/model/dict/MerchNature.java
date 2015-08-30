package newland.iaf.base.model.dict;
/**
 * 商户性质
 * @author  lxf 
 *
 */
public enum MerchNature {
	/**
	 * 国营
	 */
	stateRun("国营"),
	
	/**
	 * 集体
	 */
     collective("集体"),
	
	/**
	 * 私企
	 */
    privateEnter("私企"),
	/**
	 * 个体工商户
	 */
    indBusiness("个体工商户"),
	/**
	 * 合资
	 */
    jointVenture("合资");
	
	String desc;

	MerchNature (String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
}
