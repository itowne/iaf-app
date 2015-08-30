package newland.iaf.merch.action;

public class MerchTransAction extends MerchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute(){
		return "index";
	}
	
	public String confirm(){
		return "confirm";
	}
	
	public String trans(){
		return "success";
	}

}
