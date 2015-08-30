package newland.iaf.base.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.Province;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.inst.action.InstBaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@ParentPackage("json-default")
@Namespace("/")
@Action(value = "province")
@Results({
		@Result(name = "success", type = "json") })
public class ProvinceAction extends InstBaseAction{

	private static final long serialVersionUID = 6782809813382041227L;

	@Resource(name = "provinceService")
	private ProvinceService provinceService;
		
	private List<Province> provinceList = new ArrayList<Province>();
	
	private String provinceCode;

	@Override
	public String execute() {
		provinceList = this.provinceService.getProvince2(provinceCode);
        return SUCCESS;
    }

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
}
