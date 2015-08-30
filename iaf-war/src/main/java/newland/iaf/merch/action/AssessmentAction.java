package newland.iaf.merch.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.MccService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "assessment")
@Results({
		@Result(name = "index", type = "dispatcher", location = "/assessment.jsp") })
public class AssessmentAction extends MerchBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6179803758367063470L;
	
	@Resource(name="loanPdtService")
	private LoanPdtService loanPdtService;
	
	@Resource(name="mccService")
	private MccService mccservice;
	
	private List<LoanPdt> pdtList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String mccCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String code;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> mccMap;

	public String execute(){
		code=null;
		mccCode = "";
		mccMap=mccservice.getFirstBussin();
		pdtList=loanPdtService.queryAll();
		return "index";
	}

	

	public String getMccCode() {
		return mccCode;
	}

	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, String> getMccMap() {
		return mccMap;
	}

	public void setMccMap(Map<String, String> mccMap) {
		this.mccMap = mccMap;
	}



	public List<LoanPdt> getPdtList() {
		return pdtList;
	}



	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

}
