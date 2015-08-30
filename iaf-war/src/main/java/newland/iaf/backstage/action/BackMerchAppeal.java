package newland.iaf.backstage.action;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.Province;
import newland.iaf.base.model.condition.MerchAppealContidition;
import newland.iaf.base.model.dict.AppealState;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.merch.model.MerchInfoAppeal;
import newland.iaf.merch.service.MerchInfoAppealService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

@ParentPackage("struts")
@Namespace("/backstage")
@Action(value = "backMerchAppeal")
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "detail", type = "dispatcher", location = "/backstage/backmerchappeal/detail.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/backstage/backmerchappeal/index.jsp") })
public class BackMerchAppeal extends BSBaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1183723938640934325L;
	
	@Resource(name="merchInfoAppealService")
	private MerchInfoAppealService merchInfoAppealService;
	
	private DataSet dataSet;// 用户JQGrid数据
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<MerchInfoAppeal> merchAppealList;
	
	private String merchNo ;
	
	private String beginTime;
	
	private String endTime;
	
	private String index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchInfoAppeal merchInfoAppeal;
	 
	private String appealPhone;
	 
	private String appealMan;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String appealState;
	
	@Resource(name = "provinceService")
	private ProvinceService provinceService; 
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> cityMap;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> provMap;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String provinceCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String region;
	
	private String merchName;
	
	private Province pro_cit;
	
	private String pro_citString;
	
	public String execute() {
		provMap = provinceService.getProvince();
		cityMap = provinceService.getProvince(provinceCode);
		merchName = "";
		merchNo = "";
		beginTime = "";
		endTime = "";
		appealPhone = "";
		appealMan = "";
		appealState = "";
		return "success";
	}

	public String list() throws Exception{
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			if (dataSet == null) {
				dataSet = new DataSet();
				page.setPageOffset(0);
			} else {
				page.setPageOffset(dataSet.getPage() - 1);
			}
			MerchAppealContidition mac = new MerchAppealContidition();
			if (StringUtils.isNotBlank(merchName)) {
				mac.setMerchName(merchName.trim()+"%");
			}
			if(StringUtils.isNotBlank(merchNo)){
				mac.setMerchNo(merchNo.trim()+"%");
			}
			if(StringUtils.isNotBlank(beginTime)){
				mac.setBeginTime(this.parser(beginTime, Date.class));
			}
			if(StringUtils.isNotBlank(endTime)){
				mac.setEndTime(this.parser(endTime,Date.class));
			}
			if(StringUtils.isNotBlank(appealPhone)){
				mac.setAppealPhone(appealPhone.trim()+"%");
			}
			if(StringUtils.isNotBlank(appealMan)){
				mac.setAppealMan(appealMan.trim()+"%");
			}
			if(StringUtils.isNotBlank(appealState)){
				mac.setAppealState(AppealState.valueOf(appealState.trim()));
			}
			if(StringUtils.isNotBlank(provinceCode)&&!provinceCode.equals("1")){
				mac.setProvince(provinceCode);
			}
			if(StringUtils.isNotBlank(region)){
				mac.setCityCode(region);
			}
			merchAppealList=merchInfoAppealService.query(mac, page);
			for(MerchInfoAppeal app : merchAppealList){
				pro_citString = "";
				if(StringUtils.isNotBlank(app.getProvince())){
					pro_cit = this.provinceService.queryByProvCode(app.getProvince());
					if(pro_cit != null){
						pro_citString = pro_cit.getName();
					}
				}
				if(StringUtils.isNotBlank(app.getCityCode())){
					pro_cit = this.provinceService.queryByProvCode(app.getCityCode());
					if(pro_cit != null){
						pro_citString += pro_cit.getName();
					}
				}
				app.setProvinceCity(pro_citString);
			}
			dataSet.setGridModel(merchAppealList);
			dataSet.setRecords(merchAppealList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String detail(){
		merchInfoAppeal=merchAppealList.get(Integer.parseInt(index));
		return "detail";
	}
	
	public String acceptAppealApply(){
		merchInfoAppeal.setAppealState(AppealState.ACCEPT);
		this.merchInfoAppealService.updateMerchInfoAppeal(merchInfoAppeal);
		return "success";
	}
	
	public String denyAppealApply(){
		merchInfoAppeal.setAppealState(AppealState.DENY);
		this.merchInfoAppealService.updateMerchInfoAppeal(merchInfoAppeal);
		return "success";
	}
	
	@JqDataSet(content = "{o:merchName},{o:merchNo},{o:provinceCity},{o:appealMan},{o:appealPhone},{i:(format.time)({o:genTime})},{o:appealState.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<MerchInfoAppeal> getMerchAppealList() {
		return merchAppealList;
	}

	public void setMerchAppealList(List<MerchInfoAppeal> merchAppealList) {
		this.merchAppealList = merchAppealList;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public MerchInfoAppeal getMerchInfoAppeal() {
		return merchInfoAppeal;
	}

	public void setMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal) {
		this.merchInfoAppeal = merchInfoAppeal;
	}

	public String getAppealPhone() {
		return appealPhone;
	}

	public void setAppealPhone(String appealPhone) {
		this.appealPhone = appealPhone;
	}

	public String getAppealMan() {
		return appealMan;
	}

	public void setAppealMan(String appealMan) {
		this.appealMan = appealMan;
	}

	public String getAppealState() {
		return appealState;
	}

	public void setAppealState(String appealState) {
		this.appealState = appealState;
	}

	public Map<String, String> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<String, String> cityMap) {
		this.cityMap = cityMap;
	}

	public Map<String, String> getProvMap() {
		return provMap;
	}

	public void setProvMap(Map<String, String> provMap) {
		this.provMap = provMap;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	
}
