package newland.iaf.inst.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.dict.CreditType;
import newland.iaf.base.service.MccService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.InstCollMerch;
import newland.iaf.inst.service.InstCollService;
import newland.iaf.merch.model.LoanCreditReport;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchType;
import newland.iaf.merch.model.condition.MerchCondition;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 机构关注商户 action
 * 
 * @author wwa
 * 
 */
@ParentPackage("struts")
@Namespace("/inst")
@Action(value = "instCollMerch")
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "failed", type = "dispatcher", location = "/inst/concerned-customer.jsp"),
		@Result(name = "instCollCreditReport", type = "dispatcher", location = "/inst/concerned-detail.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/inst/concerned-customer.jsp") })
public class InstCollMerchAction extends InstBaseAction {

	private static final long serialVersionUID = -8528980593946325029L;

	private DataSet dataSet;// 用户JQGrid数据

	@Resource(name = "com.newland.iaf.instCollService")
	private InstCollService instCollService;
	
	@Resource(name="mccService")
	private MccService mccService;
	
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
	private String cityCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String provinceCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String region;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Merch> merchList;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String merchName;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String industry;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String credit;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long imerch;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long index;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String dateRange;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Date beginDate;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Date endDate;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanCreditReport report;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> mccMap;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String companySize;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String mccCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String code;
	
	private String merchType;
	
	private String contract;
	
    private String contractTel;
    
	@Override
	@Begin
	
	public String execute() throws Exception {
		region="";
		provinceCode="";
		cityCode="";
		provMap = provinceService.getProvince();
		code=null;
		mccCode = "";
		mccMap=mccService.getFirstBussin();
		return "success";
	}

	public String add() throws Exception {
		region="";
		provinceCode="";
		cityCode="";
		provMap = provinceService.getProvince();
		code=null;
		mccCode = "";
		mccMap=mccService.getFirstBussin();
		InstSession instSess = (InstSession) SessionFilter.getIafSession();
		Long iinst = instSess.getInst().getIinst();
		InstCollMerch icm = this.instCollService.queryByIinstAndImerch(iinst, imerch);
		if(icm==null){
			icm = new InstCollMerch();
			icm.setiInst(iinst);
			icm.setiMerch(imerch);
			icm.setiInstUsr(instSess.getInstUsr().getIinstUser());
			this.instCollService.addInstCollMerch(icm);
		}
		return "success";
	}

	public String list() throws Exception {
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);
			if (dataSet == null) {
				dataSet = new DataSet();
				page.setPageOffset(0);
			} else {
				page.setPageOffset(dataSet.getPage().intValue() - 1);
			}
			MerchCondition merchCondition = new MerchCondition();
			if (StringUtils.isNotBlank(provinceCode)
					&& !provinceCode.equals("1")) {
				merchCondition.setProvince(provinceCode);
			}
			if (StringUtils.isNotBlank(region)) {
				merchCondition.setCityCode(region);
			}
			if (StringUtils.isNotBlank(merchName)) {
				merchCondition.setMerchName(merchName.trim());
			}
			if (StringUtils.isNotBlank(merchType)) {
				merchCondition.setMerchType(MerchType.valueOf(merchType));
			}
			if (StringUtils.isNotBlank(contract)) {
				merchCondition.setContract(contract);
			}
			if (StringUtils.isNotBlank(contractTel)) {
				merchCondition.setContractTel(contractTel);
			}
			
			InstSession instSession = (InstSession) SessionFilter
					.getIafSession();
			Long iinst = instSession.getInst().getIinst();
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.desc("imerch"));
			merchCondition.setOrders(orderList);
			merchList = this.instCollService.queryInstCollMerchByCon(iinst,
					merchCondition, page);
			for(Merch me : merchList){
				String area = "";
				provinceMap = provinceService.getProvince();
				cityMap = provinceService.getProvince(me.getProvince());
				if(StringUtils.isNotBlank(me.getProvince())){
					area = provinceMap.get(me.getProvince());
				}
				if(StringUtils.isNotBlank(me.getCityCode())){
					area += cityMap.get(me.getCityCode());
				}
				me.setCityName(area);
			}
			dataSet.setGridModel(merchList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String viewDetail() throws Exception {
		if (index == null) {
			super.addActionMessage("对象为空，请检查");
			return "failed";
		}
		Merch merch = merchList.get(index.intValue());
		LoanOrd loanOrd = new LoanOrd();
		loanOrd.setImerch(merch.getImerch());
		this.setSessionObj("loanOrd", loanOrd);
		return "instCollCreditReport";
	}
	
	public String move() throws Exception {
		if (index == null) {
			super.addActionMessage("机构对象为空，请检查");
			return "failed";
		}
		InstSession instSess = (InstSession) SessionFilter.getIafSession();
		Merch merch = merchList.get(index.intValue());
		InstCollMerch ic = instCollService.queryByIinstAndImerch(instSess.getInst().getIinst(),merch.getImerch());
		this.instCollService.deleteInstCollMerch(ic.getiInstCollMerch());
		return execute();
	}

	@JqDataSet(content = "{o:merchName},{o:merchType},{o:cityName},{o:contract},{o:contractTel}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<Merch> getMerchList() {
		return merchList;
	}

	public void setMerchList(List<Merch> merchList) {
		this.merchList = merchList;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public LoanCreditReport getReport() {
		return report;
	}

	public void setReport(LoanCreditReport report) {
		this.report = report;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Map<String, String> getMccMap() {
		return mccMap;
	}

	public void setMccMap(Map<String, String> mccMap) {
		this.mccMap = mccMap;
	}

	public String getMerchType() {
		return merchType;
	}

	public void setMerchType(String merchType) {
		this.merchType = merchType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMccCode() {
		return mccCode;
	}

	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
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

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getContractTel() {
		return contractTel;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

}
