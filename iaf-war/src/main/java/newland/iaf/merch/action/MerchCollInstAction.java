package newland.iaf.merch.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.FileType;
import newland.iaf.base.model.TFile;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.condition.InstCondition;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.MerchCollInst;
import newland.iaf.merch.service.MerchCollService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 商户关注机构  Action
 * @author wwa
 *
 */
@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "merchCollInst")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "failed",  type = "dispatcher",location = "/merch/org-attention.jsp"),
	@Result(name = "success",  type = "dispatcher",location = "/merch/org-attention.jsp"),
	@Result(name="viewDetail",type="dispatcher",location="/merch/org-attention-detail.jsp"),
	@Result(name="returnAction",type="dispatcher",location="/merch/org-attention.jsp")
    })
public class MerchCollInstAction extends MerchBaseAction {
	
	private static final long serialVersionUID = 3856457951478663011L;

	private DataSet dataSet;//用户JQGrid数据
	
	@Resource(name="merchCollService")
	private MerchCollService merchCollService;
	
	@Resource(name="loanPdtService")
	private LoanPdtService loanPdtService;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	@Resource(name = "tfileService")
	private TFileService tfileService;
	
//	@Resource(name = "provinceService")
//	private ProvinceService provinceService;
//
//	@In(scope = ScopeType.CONVERSATION)
//	@Out(scope = ScopeType.CONVERSATION)
//	private Map<String, String> cityMap;
//
//	@In(scope = ScopeType.CONVERSATION)
//	@Out(scope = ScopeType.CONVERSATION)
//	private Map<String, String> provMap;
//	
//	@In(scope = ScopeType.CONVERSATION)
//	@Out(scope = ScopeType.CONVERSATION)
//	private String region;
//	
//	@In(scope = ScopeType.CONVERSATION)
//	@Out(scope = ScopeType.CONVERSATION)
//	private String cityCode;
//	
//	@In(scope = ScopeType.CONVERSATION)
//	@Out(scope = ScopeType.CONVERSATION)
//	private String provinceCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String instName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanPdtCount;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String instNature;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst inst;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String iinst;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<TFile> tfileList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String contact;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String contactPhone;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String acceptRegion;
	
	@Override
	@Begin
	
	public String execute() throws Exception {
//		provMap = provinceService.getProvince();
//		cityMap = provinceService.getProvince(provinceCode);
		instList = instService.queryInst();
		iinst = "";
		return "success";
	}

	public String add() throws Exception {
		MerchSession merchSess = (MerchSession) SessionFilter.getIafSession();
		Long imerchUser = merchSess.getMerchUser().getImerchUser();
		Long imerch = merchSess.getMerch().getImerch();
		MerchCollInst mci = this.merchCollService.queryByIinstAndImerch(new Long(iinst), imerch);
		if(mci==null){
			mci = new MerchCollInst();
			mci.setGenTime(new Date());
			mci.setiInst(new Long(iinst));
			mci.setImerch(imerch);
			mci.setiMerchUsr(imerchUser);
			this.merchCollService.addMerchCollInst(mci);
		}
		return execute();
	}
	
	public String list() throws Exception{
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);//设置每页显示数
			if(dataSet == null){
				dataSet = new DataSet();
				page.setPageOffset(0);
			}else{
				page.setPageOffset(dataSet.getPage()-1);
			}
			InstCondition instCondition = new InstCondition();
			//设置查询条件
			if(StringUtils.isNotBlank(instName)){
				instCondition.setInstName(instName);
			}
			if(StringUtils.isNotBlank(instNature)){
				instCondition.setInstNature(instNature);
			}
			
			if(StringUtils.isNotBlank(loanPdtCount)){
				instCondition.setLoanPdtCount(new Long(loanPdtCount));
			}
			if(StringUtils.isNotBlank(contact)){
				instCondition.setContact(contact);
			}
			if(StringUtils.isNotBlank(contactPhone)){
				instCondition.setContact(contactPhone);
			}
			MerchSession merchSession = (MerchSession) SessionFilter.getIafSession();
			
			Long imerch = merchSession.getMerch().getImerch();
			instList = this.merchCollService.queryMerchCollInstByCon(acceptRegion, imerch, instCondition, page);
			if(instList.size() != 0){
				for(Inst temp : instList){
					InstBusiInfo instBusiInfo = instService.findInstBusiInfoByiinst(temp.getIinst());
					temp.setInstBusiInfo(instBusiInfo);
				}
			}
			dataSet.setGridModel(instList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public String move() throws Exception{
		if(index == null){
			super.addActionMessage("机构对象为空，请检查");
			return "failed";
		}
		MerchSession merchSession = (MerchSession) SessionFilter.getIafSession();
		inst = instList.get(index.intValue());
		MerchCollInst mci = merchCollService.queryByIinstAndImerch(inst.getIinst(),merchSession.getMerch().getImerch());
		this.merchCollService.deleteMerchCollInst(mci.getiMerchCollInst());
		return execute();
	}
	
	public String viewInst(){
		inst = instList.get(index.intValue());
		tfileList = this.tfileService.queryInstFile(inst.getIinst(), FileType.instFile);
		return "viewDetail";
	}
	
	public String returnAction(){
		return "returnAction";
	}
		
	@JqDataSet(content = "{o:instName},{o:instNature},{o:instBusiInfo.acceptRegion},{o:pdtCount},{o:contact},{o:contactPhone},{o:iinst}")
	public DataSet getDataSet() {
		return dataSet;
	}
	
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public MerchCollService getMerchCollService() {
		return merchCollService;
	}

	public void setMerchCollService(MerchCollService merchCollService) {
		this.merchCollService = merchCollService;
	}

	public LoanPdtService getLoanPdtService() {
		return loanPdtService;
	}

	public void setLoanPdtService(LoanPdtService loanPdtService) {
		this.loanPdtService = loanPdtService;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getLoanPdtCount() {
		return loanPdtCount;
	}

	public void setLoanPdtCount(String loanPdtCount) {
		this.loanPdtCount = loanPdtCount;
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public String getInstNature() {
		return instNature;
	}

	public void setInstNature(String instNature) {
		this.instNature = instNature;
	}

	public List<TFile> getTfileList() {
		return tfileList;
	}

	public void setTfileList(List<TFile> tfileList) {
		this.tfileList = tfileList;
	}

	public String getIinst() {
		return iinst;
	}

	public void setIinst(String iinst) {
		this.iinst = iinst;
	}

//	public Map<String, String> getCityMap() {
//		return cityMap;
//	}
//
//	public void setCityMap(Map<String, String> cityMap) {
//		this.cityMap = cityMap;
//	}
//
//	public Map<String, String> getProvMap() {
//		return provMap;
//	}
//
//	public void setProvMap(Map<String, String> provMap) {
//		this.provMap = provMap;
//	}
//
//	public String getCityCode() {
//		return cityCode;
//	}
//
//	public void setCityCode(String cityCode) {
//		this.cityCode = cityCode;
//	}
//
//	public String getProvinceCode() {
//		return provinceCode;
//	}
//
//	public void setProvinceCode(String provinceCode) {
//		this.provinceCode = provinceCode;
//	}
//
//	public String getRegion() {
//		return region;
//	}
//
//	public void setRegion(String region) {
//		this.region = region;
//	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getAcceptRegion() {
		return acceptRegion;
	}

	public void setAcceptRegion(String acceptRegion) {
		this.acceptRegion = acceptRegion;
	}
}
