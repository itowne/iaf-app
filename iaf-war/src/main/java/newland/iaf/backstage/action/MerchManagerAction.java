package newland.iaf.backstage.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.MerchFieldSurvy;
import newland.iaf.base.model.Province;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.MccService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Mcc;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchInfoAppeal;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.condition.MerchCondition;
import newland.iaf.merch.service.MerchInfoAppealService;
import newland.iaf.merch.service.MerchService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
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
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台管理 商户管理Action
 * 
 * @author lindaqun
 * 
 */
@ParentPackage("struts")
@Namespace("/backstage/merchmanager")
@Action(value = "merchManager",interceptorRefs = {
		@InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png, image/pjpeg",
//				"maximumSize", "2097152"
		}),
		@InterceptorRef(value = "base_stack")})
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "modify", type = "dispatcher", location = "/backstage/merchmanager/merchModify.jsp"),
		@Result(name = "fieldSurvey", type = "dispatcher", location = "/backstage/merchmanager/fieldSurvey.jsp"),
		@Result(name="other",type="dispatcher",location="/backstage/merchmanager/addMemoSuccess.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/backstage/merchmanager/merchManagerIndex.jsp"),
		@Result(name = "input", type = "dispatcher", location = "/backstage/merchmanager/checkUploadSize.jsp")
})
public class MerchManagerAction extends BSBaseAction {

	private static final long serialVersionUID = 6130787955468318604L;

	private DataSet dataSet;// 用户JQGrid数据

	@Resource(name="mccService")
	private MccService mccService;
	
	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;
	
	@Resource(name = "fundFlowService")
	private FundFlowService fundFlowService;
	
	@Resource(name="merchInfoAppealService")
	private MerchInfoAppealService merchInfoAppealService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Merch> merchList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Merch merch;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchBusiInfo merchBusiInfo;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchLegalPers merchLegalPers;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String merchName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String contract;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchUser merchUser;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long imerch;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchFieldSurvy merchFieldSurvy;

	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	@Resource(name = "provinceService")
	private ProvinceService provinceService;
	
	private String merchNo;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private TFile tfile;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private TFile debitTFile;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> mccMap;
	
	private String firstCode;
	
	private String secondCode;
	
	private String code;
	
	private String mccCode;
	
	private String tel;
	
	private List<TFile> otherFiles;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> provMap;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String cityCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String provinceCode;
	
	private String provinceId;
	
	@Resource(name = "tfileService")
	private TFileService tFileService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String region;
		
	private File[] upload;
	private String[] filetype;
	private String[] uploadFileName;
	private String[] uploadContentType;
	
	private String stat;
	
	private String ifile;
	
	@Begin
	public String execute() {
		provMap = provinceService.getProvince();
		merchName="";
		merchNo="";
		contract="";
		tel="";
		provinceCode="";
		cityCode="";
		region="";
		provinceId="";
		stat="";
		return "success";
	}

	public String list() throws Exception {
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
			MerchCondition merchCondition = new MerchCondition();
			// 设置查询条件
			if (StringUtils.isNotBlank(merchName)) {
				merchCondition.setMerchName(merchName.trim());
			}
			if (StringUtils.isNotBlank(contract)) {
				merchCondition.setContract(contract.trim());
			}
			
			if(StringUtils.isNotBlank(stat)){
				//if(stat.equals("0")){
					//merchCondition.setNeMerchStatus("0");
				//}else{
					merchCondition.setMerchStatus(stat);
			//	}
				
			}
			if(StringUtils.isNotBlank(merchNo)){
				merchCondition.setMerchNo(merchNo.trim());
			}
			
			if(StringUtils.isNotBlank(provinceCode)&&!provinceCode.equals("1")){
				merchCondition.setProvince(provinceCode);
			}
			if(StringUtils.isNotBlank(region)){
				merchCondition.setCityCode(region);
			}
			
			if(StringUtils.isNotBlank(tel)){
				merchCondition.setContractTel(tel.trim());
			}
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.asc("imerch"));
			merchCondition.setOrders(orderList);
			merchList = this.merchService.queryMerchByCon(merchCondition, page);
//			List<Merch> merchListx = new ArrayList<Merch>();
//			if(!CollectionUtils.isEmpty(merchList)){
//				for (Merch merch : merchList) {
//					if(StringUtils.isNotBlank(merch.getProvince())){
//						String str = getAreaNames(merch.getProvince());
//						merch.setAreaName(str);
//						merchListx.add(merch);
//					}else{
//						merchListx.add(merch);
//					}
//				}
//			}
			
			dataSet.setGridModel(merchList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String modifymerch() throws Exception {
		provMap = provinceService.getProvince();
		merch = this.merchList.get(index);
		if(merch!=null){
			provinceId=merch.getProvince();
		}else{
			provinceId="";
		}
//		if(StringUtils.isNotBlank(merch.getProvince())){
//			 Province p=provinceService.queryByProvCode(merch.getProvince());
//			 if(p!=null){
//				 provinceId=p.getPreProvCode();
//			 }else{
//				 provinceId="";
//			 }
//		}else{
//			provinceId="";
//		}
		
		code=null;
		mccCode="";
		mccMap=mccService.getFirstBussin();
		//Mcc mc = mccService.queryByName(merch.getIndustry());//获取二级行业id
		Mcc mc = new Mcc();
		if(StringUtils.isNotBlank(merch.getIndustry())){
			 mc = mccService.queryById(merch.getIndustry());
		}
		if(mc!=null){
			secondCode=mc.getId();
			//firstCode = mccService.queryByName(mc.getName()).getType();//获取一级行业id
			firstCode = mc.getType();//获取一级行业id
		}else{
			secondCode=null;
			firstCode=null;
		}
		merchBusiInfo = this.merchService.getMerchBusiInfoByImerch(merch.getImerch());
		if(merchBusiInfo==null||merchBusiInfo.equals("")){
			merchBusiInfo = new MerchBusiInfo();
			merchBusiInfo.setImerch(merch.getImerch());
		}
		merchLegalPers = this.merchService.getMerchLegalPersByImerch(merch.getImerch());
		if(merchLegalPers==null||merchLegalPers.equals("")){
			merchLegalPers= new MerchLegalPers();
			merchLegalPers.setImerch(merch.getImerch());
		}
		
		if(merch.getLogoIfile()==null){
			tfile=null;
		}else{
			tfile = this.tFileService.getTFileByIfile(merch.getLogoIfile());
		}
		if(merch.getDebitIFile()==null){
			debitTFile=null;
		}else{
			debitTFile=this.tFileService.getTFileByIfile(merch.getDebitIFile());
		}
		return "modify";
	}

	public String fieldSurvey() throws Exception {
		merch = this.merchList.get(index);
		// 现场调查
		imerch = merch.getImerch();
		merchFieldSurvy = this.merchService.getMerchFieldSurvyByImerch(imerch);
		if(merchFieldSurvy==null){
			merchFieldSurvy=new MerchFieldSurvy();
		}
		return "fieldSurvey";
	}

	@InputConfig(resultName = "fieldSurvey")
	@Validations(
		requiredStrings = {
//				@RequiredStringValidator(fieldName = "merchFieldSurvy.manageFieldSquare", shortCircuit = true, message = "用地面积不能为空!"),
//				@RequiredStringValidator(fieldName = "merchFieldSurvy.manageBussinessHours", shortCircuit = true, message = "营业时间不能为空!"),
//				@RequiredStringValidator(fieldName = "merchFieldSurvy.manageRangeMajor", shortCircuit = true, message = "经营范围-主业不能为空!"),
//				@RequiredStringValidator(fieldName = "merchFieldSurvy.manageRangeAvocation", shortCircuit = true, message = "经营范围-副业不能为空!"),
//				@RequiredStringValidator(fieldName = "merchFieldSurvy.manageBranchRange", shortCircuit = true, message = "分支机构-范围不能为空!"),
//				@RequiredStringValidator(fieldName = "merchFieldSurvy.manageBranchCount", shortCircuit = true, message = "经营范围-副业不能为空!")
		}
	)
	public String savefieldSurvey() throws Exception {
		//merchFieldSurvy= this.merchService.getMerchFieldSurvyByImerch(imerch);
		merchFieldSurvy.setiMerch(imerch);
		IafConsoleSession ics = (IafConsoleSession) SessionFilter.getIafSession();
		merchFieldSurvy.setManageBussinessHours(merchFieldSurvy.getStartHour()+":"+merchFieldSurvy.getStartMin()+"--"+merchFieldSurvy.getEndHour()+":"+merchFieldSurvy.getEndMin());
		try {
			if (merchFieldSurvy != null && merchFieldSurvy.getiFieldSurvy() != null) {
				// 更新现场调查信息
				this.merchService.updateMerchFieldSurvy(merchFieldSurvy,ics,getIpaddr());
			} else {
				// 新增现场调查信息
				this.merchService.saveMerchFieldSurvy(merchFieldSurvy,ics,getIpaddr());
			}
			Merch merch = this.merchService.findMerchById(imerch);
			MerchLegalPers merchLegalPers = this.merchService.getMerchLegalPersByImerch(imerch);
			this.merchService.updateCredit(merch, merchLegalPers,merchFieldSurvy);
			
			super.addActionMessage("保存成功");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "success";
		}
	}

	@InputConfig(resultName = "modify")
	@Validations(
		requiredStrings = {
			@RequiredStringValidator(fieldName = "merch.merchName", shortCircuit = true, message = "商户名称不能为空!"),
//			@RequiredStringValidator(fieldName = "merch.regNo", shortCircuit = true, message = "注册编号不能为空!"),
//			@RequiredStringValidator(fieldName = "merch.regAddr", shortCircuit = true, message = "注册地址不能为空!"),
			@RequiredStringValidator(fieldName = "merch.posAddr", shortCircuit = true, message = "机具地址不能为空!"),
//			@RequiredStringValidator(fieldName = "merch.qqUid", shortCircuit = true, message = "qq不能为空!"),
//			@RequiredStringValidator(fieldName = "merch.businlic", shortCircuit = true, message = "营业执照号不能为空!"),
//			@RequiredStringValidator(fieldName = "merch.taxReg", shortCircuit = true, message = "税务登记号不能为空!"),
			@RequiredStringValidator(fieldName = "merch.contract", shortCircuit = true, message = "联系人不能为空!"),
			@RequiredStringValidator(fieldName = "merch.contractTel", shortCircuit = true, message = "联系电话不能为空!")
//			@RequiredStringValidator(fieldName = "merch.email", shortCircuit = true, message = "邮件地址不能为空!"),
//			@RequiredStringValidator(fieldName = "merch.auditAddr", shortCircuit = true, message = "地址是否核实不能为空!"),
//			@RequiredStringValidator(fieldName = "merch.audirBusinlic", shortCircuit = true, message = "营业执照是否核实不能为空!"),
//			@RequiredStringValidator(fieldName = "merch.province", shortCircuit = true, message = "所属省份不能为空!"),
//			@RequiredStringValidator(fieldName = "merchBusiInfo.bankCode", shortCircuit = true, message = "银行编号不能为空!"),
//			@RequiredStringValidator(fieldName = "merchBusiInfo.bank", shortCircuit = true, message = "结算银行不能为空!"),
//			@RequiredStringValidator(fieldName = "merchBusiInfo.accountNo", shortCircuit = true, message = "贷款资金帐号不能为空!"),
//			@RequiredStringValidator(fieldName = "merchBusiInfo.merchNatrue", shortCircuit = true, message = "商户性质不能为空!"),
//			@RequiredStringValidator(fieldName = "merchLegalPers.legalPersName", shortCircuit = true, message = "法人姓名不能为空!"),
//			@RequiredStringValidator(fieldName = "merchLegalPers.cerdNo", shortCircuit = true, message = "身份证号不能为空!"),
//			@RequiredStringValidator(fieldName = "merchLegalPers.faAddr", shortCircuit = true, message = "家庭住址不能为空!")
		},
		requiredFields = {
//			@RequiredFieldValidator(fieldName = "merch.regCap", shortCircuit = true, message = "注册资本不能为空!"),
//			@RequiredFieldValidator(fieldName = "merch.credit", shortCircuit = true, message = "信用情况不能为空!"),
//			@RequiredFieldValidator(fieldName = "merchLegalPers.age", shortCircuit = true, message = "年龄不能为空!"),
//			@RequiredFieldValidator(fieldName = "merchLegalPers.officePhone", shortCircuit = true, message = "办公电话不能为空!"),
//			@RequiredFieldValidator(fieldName = "merchLegalPers.mobiPhone", shortCircuit = true, message = "手机不能为空!"),
//			@RequiredFieldValidator(fieldName = "merch.regTime", shortCircuit = true, message = "注册时间不能为空!"),
//			@RequiredFieldValidator(fieldName = "merch.openTime", shortCircuit = true, message = "开业时间不能为空!")
		}
	)
	public String updateMerchInfo() throws Exception{
		IafConsoleSession ics = (IafConsoleSession) SessionFilter.getIafSession();
		try{
			if(code!=null){
				//merch.setIndustry(mccService.queryById(code).getName());
				merch.setIndustry(code);
			}else{
				merch.setIndustry("--");
			}
			merch.setProvince(provinceCode);
			merch.setCityCode(cityCode);
			String proName="";
			String cityName="";
			Province prop=provinceService.queryByProvCode(provinceCode);
			Province procity=provinceService.queryByProvCode(cityCode);
			if(prop!=null){
				proName=prop.getName();
			}
			
			if(procity!=null){
				cityName=procity.getName();
			}
			merch.setAreaName(proName+cityName);
			provinceCode="";
			
			if (upload != null) {
				for (int i = 0; i < upload.length; i++) {
					String fileName = uploadFileName[i];
					Long ifile= tFileService.CommonFileUploadById(upload[i], filetype[i], fileName,uploadContentType[i], merch.getImerch(),null );
					if(filetype[i].equals("debitLogo")){
						merch.setDebitIFile(ifile);
					}else if(filetype[i].equals("merchLogo")){
						merch.setLogoIfile(ifile);
					}
				}
				
			}
			
			
			Merch _merch = this.merchService.findMerchById(merch.getImerch());
			if(!_merch.getMerchName().equals(merch.getMerchName())){
				//更新产品订单中商户名称
				List<LoanOrd> loanOrdList = loanOrdService.queryByImerch(merch.getImerch());
				for (LoanOrd loanOrd : loanOrdList) {
					loanOrd.setMerchName(merch.getMerchName());
					loanOrdService.updateLoanOrd(loanOrd);
				}
				//更新商户申诉表的商户名称
				List<MerchInfoAppeal> merchInfoAppealList= this.merchInfoAppealService.findAppealByMerchNo(merch.getMerchNo());
				for (MerchInfoAppeal mia : merchInfoAppealList){
					mia.setMerchName(merch.getMerchName());
					mia.setProvince(merch.getProvince());
					mia.setCityCode(merch.getCityCode());
					merchInfoAppealService.updateMerchInfoAppeal(mia);
				}
			}
			//更新fundFlow中的商户名称
			FundFlowCondition fundFlowCon = new FundFlowCondition();
			fundFlowCon.setImerch(merch.getImerch());
			List<FundFlow> fundFlowLists = fundFlowService.queryFundFlowBy(fundFlowCon);
			for(FundFlow fundflow : fundFlowLists){
				fundflow.setMerchName(merch.getMerchName());
				fundFlowService.update(fundflow,fundflow.getStatus());
			} 
			
			this.merchService.updateMerchInfoAll(merch, merchLegalPers, merchBusiInfo,ics,getIpaddr());
			
			MerchFieldSurvy merchFieldSurvy = this.merchService.getMerchFieldSurvyByImerch(merch.getImerch());
			this.merchService.updateCredit(merch,merchLegalPers,merchFieldSurvy);
			super.addActionError("商户资料修改成功!");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
	}
	
	@JqDataSet(content = "{o:merchName},{o:merchNo},{o:areaName},{o:contract},{o:contractTel},{o:merchStatus},{o:imerch}")
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

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public MerchBusiInfo getMerchBusiInfo() {
		return merchBusiInfo;
	}

	public void setMerchBusiInfo(MerchBusiInfo merchBusiInfo) {
		this.merchBusiInfo = merchBusiInfo;
	}

	public MerchLegalPers getMerchLegalPers() {
		return merchLegalPers;
	}

	public void setMerchLegalPers(MerchLegalPers merchLegalPers) {
		this.merchLegalPers = merchLegalPers;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public MerchUser getMerchUser() {
		return merchUser;
	}

	public void setMerchUser(MerchUser merchUser) {
		this.merchUser = merchUser;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public MerchFieldSurvy getMerchFieldSurvy() {
		return merchFieldSurvy;
	}

	public void setMerchFieldSurvy(MerchFieldSurvy merchFieldSurvy) {
		this.merchFieldSurvy = merchFieldSurvy;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getSecondCode() {
		return secondCode;
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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMccCode() {
		return mccCode;
	}

	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	public void setSecondCode(String secondCode) {
		this.secondCode = secondCode;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFirstCode() {
		return firstCode;
	}

	public void setFirstCode(String firstCode) {
		this.firstCode = firstCode;
	}

	public Map<String, String> getMccMap() {
		return mccMap;
	}

	public void setMccMap(Map<String, String> mccMap) {
		this.mccMap = mccMap;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public List<TFile> getOtherFiles() {
		return otherFiles;
	}

	public void setOtherFiles(List<TFile> otherFiles) {
		this.otherFiles = otherFiles;
	}

	public String getIfile() {
		return ifile;
	}

	public TFile getTfile() {
		return tfile;
	}

	public void setTfile(TFile tfile) {
		this.tfile = tfile;
	}

	public TFile getDebitTFile() {
		return debitTFile;
	}

	public void setDebitTFile(TFile debitTFile) {
		this.debitTFile = debitTFile;
	}

	public void setIfile(String ifile) {
		this.ifile = ifile;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getFiletype() {
		return filetype;
	}

	public void setFiletype(String[] filetype) {
		this.filetype = filetype;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
