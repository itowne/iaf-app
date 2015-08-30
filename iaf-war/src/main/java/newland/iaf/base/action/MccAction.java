package newland.iaf.base.action;

import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.service.MccService;
import newland.iaf.inst.action.InstBaseAction;
import newland.iaf.inst.model.Mcc;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@ParentPackage("json-default")
@Namespace("/")
@Action(value = "mcc")
@Results({
		@Result(name = "success", type = "json") })
public class MccAction extends InstBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1536780112752177343L;
	
	@Resource(name="mccService")
	private MccService mccService;
	
	private List<Mcc> mccList;
	
	private String code;
	
	public String execute(){
		mccList=mccService.getSecondBussin(code);
		return "success";
	}

	public List<Mcc> getMccList() {
		return mccList;
	}

	public void setMccList(List<Mcc> mccList) {
		this.mccList = mccList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
