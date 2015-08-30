/**
 * 
 */
package newland.iaf.base.model.dict;

/**
 * @author Mr.Towne
 * 
 */
public enum DebitbidStat {

	/**
	 * 过期
	 */
	REVOCATION("撤销"),
	/**
	 * 正常
	 */
	NORMAL("正常");
	String desc;

	DebitbidStat(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
}
