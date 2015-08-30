package newland.iaf.base.model.dict;

public enum CreditType {
	/**
	 * A
	 */
	A("A"),
	/**
	 * B
	 */
	B("B"),
	/**
	 * C
	 */
	C("C");
	
	String desc;
	CreditType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
}
