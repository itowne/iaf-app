package newland.iaf.merch.model;

public enum AcceptType {
	/**
	 * 允许
	 */
	ALLOW,
	/**
	 * 拒决
	 */
	DENY;
	
	public boolean isAllow(){
		if (this == ALLOW){
			return true;
		}
		return false;
	}
	
	public static AcceptType getAcceptType(boolean flag){
		if (flag) return ALLOW;
		return DENY;
	}

}
