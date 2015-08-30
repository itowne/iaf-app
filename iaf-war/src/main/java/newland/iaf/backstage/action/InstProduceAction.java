package newland.iaf.backstage.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.model.dict.YesOrNoType;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.service.InstService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台管理贷款产品管理
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/product")
@Action(value = "instProduce", interceptorRefs = {
		@InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png, image/pjpeg",
				"maximumSize", "2097152"
		}),
		@InterceptorRef(value = "base_stack")
})
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "productAdd",  type = "dispatcher",location = "/backstage/product/instProductAdd.jsp"),
	@Result(name = "viewProduct",  type = "dispatcher",location = "/backstage/product/instViewProduct.jsp"),
	@Result(name = "editProduct",  type = "dispatcher",location = "/backstage/product/instEditProduct.jsp"),
	@Result(name = "failed",  type = "redirect",location = "/backstage/product/instProduce!instProdInfo"),
	@Result(name = "forward",  type = "redirect",location = "/backstage/product/instProduce!instProdInfo"),
	@Result(name = "instProdInfo",  type = "dispatcher",location = "/backstage/product/instProdInfo.jsp"),
	@Result(name = "success",  type = "redirect",location = "/backstage/product/instPro")
    })
public class InstProduceAction extends BSBaseAction {
	
	private static final long serialVersionUID = 5321770116877514991L;

	private DataSet dataSet;//用户JQGrid数据
	
	@Resource(name="loanPdtService")
	private LoanPdtService loanPdtService;
	
	@Resource(name = "provinceService")
	private ProvinceService provinceService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanPdt> loanPdtList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String pdtName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String yearRate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> provMap;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> cityMap;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String region;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String pdtStatus;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanPdt loanPdt;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long iinst;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String cityCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String provinceCode;
	
	private String minTermType;
	
	private String maxTermType;
	
	/**
	 * 是否上架销售
	 */
	private String isUpShelves;
	
	private String rateType;
	
	private String minterm;
	private String Maxterm;
	
	private String MinyearRate;
	private String MaxyearRate;
	
	private String minQuota;
	private String MaxQuota;
	/**
	 * 上传文件
	 */
	@Resource(name = "tfileService")
	private TFileService tfileService;
	
	private File[] upload;
	private String[] filetype;
	private String[] uploadFileName;
	private String[] uploadContentType;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private TFile tfile;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private TFile pdtFile;
	
	private String fastday;
	private String times;
	
	@Begin
	
	public String execute(){
		minQuota="";
		MaxQuota="";
		minterm="";
		Maxterm="";
		MinyearRate="";
		MaxyearRate="";
		fastday="";
		times="";
		pdtStatus="";
		pdtName="";
		provinceCode="";
		cityCode="";
		provMap = provinceService.getProvince();
		region="";
		rateType="";
		minTermType="";
		maxTermType="";
		return "success";
	}
	
	public String instProdInfo(){
		
		provinceCode="";
		cityCode="";
		provMap = provinceService.getProvince();
		return "instProdInfo";
	}
	
	public String list() throws Exception {
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
			LoanPdtCondition loanpdtcondition = new LoanPdtCondition();
			loanpdtcondition.setIinst(iinst);
			//设置查询条件
			if(StringUtils.isNotBlank(pdtName)){
				loanpdtcondition.setPdtName(pdtName);
			}
			
			if(StringUtils.isNotBlank(rateType)&&StringUtils.isNotBlank(MinyearRate)&&StringUtils.isNotBlank(MaxyearRate)){
				loanpdtcondition.setRateType(RateType.valueOf(rateType));
			}

			if(StringUtils.isNotBlank(minTermType)&&StringUtils.isNotBlank(minterm)){
				loanpdtcondition.setMaxTermType(TermType.valueOf(minTermType));
			}
			
			if(StringUtils.isNotBlank(maxTermType)&&StringUtils.isNotBlank(Maxterm)){
				loanpdtcondition.setMaxTermType(TermType.valueOf(maxTermType));
			}
			
			if(StringUtils.isNotBlank(minQuota)){
				loanpdtcondition.setMinleQuota(new BigDecimal(minQuota));
			}
			if(StringUtils.isNotBlank(MaxQuota)){
				loanpdtcondition.setMaxgeQuota(new BigDecimal(MaxQuota));
			}
			if(StringUtils.isNotBlank(minterm)&&StringUtils.isNotBlank(minTermType)){
				loanpdtcondition.setMinleTerm(Integer.parseInt(minterm));
			}
			if(StringUtils.isNotBlank(Maxterm)&&StringUtils.isNotBlank(maxTermType)){
				loanpdtcondition.setMaxTerm(Integer.parseInt(Maxterm));
			}
			
			if(StringUtils.isNotBlank(MinyearRate)&&StringUtils.isNotBlank(rateType)){
				loanpdtcondition.setMinRate(new BigDecimal(MinyearRate));
			}
			
			if(StringUtils.isNotBlank(MaxyearRate)&&StringUtils.isNotBlank(rateType)){
				loanpdtcondition.setMaxRate(new BigDecimal(MaxyearRate));
			}
			
			if(StringUtils.isNotBlank(fastday)){
				loanpdtcondition.setCreditTerm(Integer.parseInt(fastday));
			}
			
			if(StringUtils.isNotBlank(times)){
				loanpdtcondition.setReqTotal(Long.parseLong(times));
			}
			if(StringUtils.isNotBlank(pdtStatus)){
				loanpdtcondition.setPdtStatus(PdtStat.valueOf(pdtStatus));
			}
			if(StringUtils.isNotBlank(provinceCode)&&!provinceCode.equals("1")){
				loanpdtcondition.setProvinceCode(provinceCode);
			}
			if(StringUtils.isNotBlank(region)){
				loanpdtcondition.setRegion(region);
			}

			loanPdtList = this.loanPdtService.queryLoanPdtByCon(loanpdtcondition, page);
			dataSet.setGridModel(loanPdtList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public String productAdd() throws Exception{
		loanPdt=null;
		provMap = provinceService.getProvince();
		cityMap = provinceService.getProvince(provinceCode);
		return "productAdd";
	}
	
	@InputConfig(resultName = "productAdd")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "loanPdt.pdtName", message="产品名称不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "loanPdt.introduce", message="产品特点简述不能为空！",shortCircuit=true)
					//@RequiredStringValidator(fieldName = "loanPdt.feature", message="产品特点不能为空！",shortCircuit=true),
					//@RequiredStringValidator(fieldName = "loanPdt.condition", message="申请条件不能为空！",shortCircuit=true),
					//@RequiredStringValidator(fieldName = "loanPdt.cl", message="申请所需材料不能为空！",shortCircuit=true)
			},
			requiredFields  = {
					@RequiredFieldValidator(fieldName = "loanPdt.creditTerm", message = "放款周期不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.minQuota", message = "贷款最低金额不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.maxQuota", message = "贷款最高金额不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.minTerm", message = "贷款最短周期不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.maxTerm", message = "贷款最长周期不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.rate", message = "利率不能为空", shortCircuit = true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "loanPdt.pdtName", minLength = "2", message="产品名称不能少于2个字符", shortCircuit = true)
			}
	)
	public String saveProduct() throws Exception{
		try{
			if(loanPdt == null) {
				super.addActionMessage("产品对象为空，请检查");
				return "failed";
			}
			if(StringUtils.isNotBlank(rateType)){
				loanPdt.setRateType(RateType.valueOf(rateType));
			}
			
			if(StringUtils.isNotBlank(minTermType)){
				loanPdt.setMinTermType(TermType.valueOf(minTermType));
			}
			if(StringUtils.isNotBlank(maxTermType)){
				loanPdt.setMaxTermType(TermType.valueOf(maxTermType));
			}
			loanPdt.setRegion(cityCode);
			loanPdt.setProvinceCode(provinceCode);
			String provinceName=provMap.get(provinceCode)==null?"":provMap.get(provinceCode);
			cityMap = provinceService.getProvince(provinceCode);
			String cityName=cityMap.get(cityCode)==null?"":cityMap.get(cityCode);
			loanPdt.setArea(provinceName+cityName);
//			loanPdt.setInstName(this.instService.getInstById(iinst).getInstName());
			loanPdt.setIinst(iinst);
			loanPdt.setPdtStatus(PdtStat.UNDERCARRIAGE);
			loanPdt.setGenTime(new Date());
			loanPdt.setUpdTime(new Date());
			loanPdt.setDeleteFlag(YesOrNoType.NO);
			if(isUpShelves != null && isUpShelves.equals("true")){
				//产品上架
				loanPdt.setPdtStatus(PdtStat.GROUNDING);
			}
			
			InstSession instsession = new InstSession();
			instsession.setInst(this.instService.getInstById(iinst));
			
			for (int i = 0; i < upload.length; i++) {
				if (upload != null) {
					String fileName = uploadFileName[i];
					Long logoIfile = tfileService.CommonFileUpload(upload[i], filetype[i], fileName,uploadContentType[i], null, instsession);
					if(filetype[i].equals("PdtLogoPage")){
						loanPdt.setPdtIfile(logoIfile);
					}else{
						loanPdt.setLogoIfile(logoIfile);
					}
				}
			}
	
			this.loanPdtService.addLoanPdtServiceBackstage(loanPdt, iinst,getUserSession(), getIpaddr());
			
			return "forward";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "forward";
		}
	}
	
	/**
	 * 上架产品
	 * @return
	 * @throws Exception
	 */
	public String upStore() throws Exception{
		try{
			loanPdt = loanPdtList.get(index.intValue());
			//判断该产品是否已经上架，如果已经上架则不允许再次上架
			if(index == null){
				super.addActionMessage("产品对象为空，请检查");
				return "viewProduct";
			}
			if(loanPdt.getPdtStatus() == PdtStat.GROUNDING){
				super.addActionMessage("产品已经上架则不允许再次上架");
				return "viewProduct";
			}
			if(loanPdt.getPdtStatus() == PdtStat.EXPIRE){
				super.addActionMessage("产品已经过期则不允许再次上架");
				return "viewProduct";
			}
			//将产品上架
			loanPdt.setPdtStatus(PdtStat.GROUNDING);
			this.loanPdtService.updateLoanPdtBackstage(loanPdt, iinst,getUserSession(), getIpaddr(),OperType.UP_PDT);
			return "forward";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "forward";
		}
	}
	/**
	 * 明细页面上架使用
	 * @return
	 * @throws Exception
	 */
	public String upStoreview() throws Exception{
		try{
			//判断该产品是否已经上架，如果已经上架则不允许再次上架
			if(index == null){
				super.addActionMessage("产品对象为空，请检查");
				return "viewProduct";
			}
			if(loanPdt.getPdtStatus() == PdtStat.GROUNDING){
				super.addActionMessage("产品已经上架则不允许再次上架");
				return "viewProduct";
			}
			if(loanPdt.getPdtStatus() == PdtStat.EXPIRE){
				super.addActionMessage("产品已经过期则不允许再次上架");
				return "viewProduct";
			}
			//将产品上架
			loanPdt.setPdtStatus(PdtStat.GROUNDING);
			this.loanPdtService.updateLoanPdtBackstage(loanPdt, iinst,getUserSession(), getIpaddr(),OperType.UP_PDT);
			return "forward";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "forward";
		}
	}
	
	/**
	 * 下架产品(这个是在查看页面使用，发生错误后会跳回查看页面)
	 * @return
	 */
	public String downStoreView() throws Exception{
		try{
			//判断该产品是否已经下架，如果已经下架则不允许再次下架
			if(index == null){
				super.addActionMessage("产品对象为空，请检查");
				return "viewProduct";
			}
			if(loanPdt.getPdtStatus() == PdtStat.UNDERCARRIAGE){
				super.addActionMessage("产品已经下架则不允许再次下架");
				return "viewProduct";
			}
			if(loanPdt.getPdtStatus() == PdtStat.EXPIRE){
				super.addActionMessage("产品已经过期则不允许修改");
				return "viewProduct";
			}
			//判断该产品是否已经有申请，如果正在进行中则不允许下架
	//		if(true){
	//			super.addActionMessage("产品正在进行中则不允许下架!");
	//			return "failed";
	//		}
			//将产品下架
			loanPdt.setPdtStatus(PdtStat.UNDERCARRIAGE);
			this.loanPdtService.updateLoanPdtBackstage(loanPdt, iinst,getUserSession(), getIpaddr(),OperType.DOWN_PDT);
			return "forward";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "forward";
		}
	}
	
	/**
	 * 下架产品
	 * @return
	 */
	public String downStore() throws Exception{
		try{
			//判断该产品是否已经下架，如果已经下架则不允许再次下架
			if(index == null){
				super.addActionMessage("产品对象为空，请检查");
				return "failed";
			}
			loanPdt = loanPdtList.get(index.intValue());
			if(loanPdt.getPdtStatus() == PdtStat.UNDERCARRIAGE){
				super.addActionMessage("产品已经下架则不允许再次下架");
				return "failed";
			}
			if(loanPdt.getPdtStatus() == PdtStat.EXPIRE){
				super.addActionMessage("产品已经过期则不允许修改");
				return "viewProduct";
			}
			//判断该产品是否已经有申请，如果正在进行中则不允许下架
	//		if(true){
	//			super.addActionMessage("产品正在进行中则不允许下架!");
	//			return "failed";
	//		}
			//将产品下架
			loanPdt.setPdtStatus(PdtStat.UNDERCARRIAGE);
			this.loanPdtService.updateLoanPdtBackstage(loanPdt, iinst,getUserSession(), getIpaddr(),OperType.DOWN_PDT);
			return "forward";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "forward";
		}
	}
	
	public String viewProduct() throws Exception{
		if(index == null){
			super.addActionMessage("产品对象为空，请检查");
			return "failed";
		}
		//loanPdt = this.loanPdtService.getLoanPdtById(index);
		loanPdt=loanPdtList.get(index.intValue());
		if(loanPdt.getLogoIfile()!=null){
			tfile = this.tfileService.getTFileByIfile(loanPdt.getLogoIfile());
		}else{
			tfile=new TFile();
		}
		
		if(loanPdt.getPdtIfile()!=null){
			pdtFile=this.tfileService.getTFileByIfile(loanPdt.getPdtIfile());
		}else{
			pdtFile=new TFile();
		}
		return "viewProduct";
	}
	
	public String editProduct() throws Exception{
		
		if(index == null){
			super.addActionMessage("产品对象为空，请检查");
			return "failed";
		}
		loanPdt = loanPdtList.get(index.intValue());
		if(loanPdt.getLogoIfile()!=null){
			tfile = this.tfileService.getTFileByIfile(loanPdt.getLogoIfile());
		}else{
			tfile=new TFile();
		}
		if(loanPdt.getPdtIfile()!=null){
			pdtFile=this.tfileService.getTFileByIfile(loanPdt.getPdtIfile());
		}else{
			pdtFile=new TFile();
		}
		return "editProduct";
	}
	
	@InputConfig(resultName = "editProduct")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "loanPdt.pdtName", message="产品名称不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "loanPdt.introduce", message="产品特点简述不能为空！",shortCircuit=true)
					//@RequiredStringValidator(fieldName = "loanPdt.feature", message="产品特点不能为空！",shortCircuit=true),
					//@RequiredStringValidator(fieldName = "loanPdt.condition", message="申请条件不能为空！",shortCircuit=true),
					//@RequiredStringValidator(fieldName = "loanPdt.cl", message="申请所需材料不能为空！",shortCircuit=true)
			},
			requiredFields  = {
					@RequiredFieldValidator(fieldName = "loanPdt.creditTerm", message = "放款周期不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.minQuota", message = "贷款最低金额不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.maxQuota", message = "贷款最高金额不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.minTerm", message = "贷款最短周期不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.maxTerm", message = "贷款最长周期不能为空", shortCircuit = true),
					@RequiredFieldValidator(fieldName = "loanPdt.rate", message = "利率不能为空", shortCircuit = true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "loanPdt.pdtName", minLength = "2", message="产品名称不能少于2个字符", shortCircuit = true)
			}
	)
	//修改产品
	public String updateProduct() throws Exception{
		try{
			InstSession instsession = new InstSession();
			instsession.setInst(this.instService.getInstById(iinst));
			if(StringUtils.isNotBlank(rateType)){
				loanPdt.setRateType(RateType.valueOf(rateType));
			}
			if(StringUtils.isNotBlank(minTermType)){
				loanPdt.setMinTermType(TermType.valueOf(minTermType));
			}
			if(StringUtils.isNotBlank(maxTermType)){
				loanPdt.setMaxTermType(TermType.valueOf(maxTermType));
			}
			loanPdt.setRegion(cityCode);
			loanPdt.setProvinceCode(provinceCode);
			cityMap = provinceService.getProvince(provinceCode);
			String cityName=cityMap.get(cityCode)==null?"":cityMap.get(cityCode);
			String provinceName=provMap.get(provinceCode)==null?"":provMap.get(provinceCode);
			loanPdt.setArea(provinceName+cityName);
			if(upload!=null){
				for (int i = 0; i < upload.length; i++) {
					if (upload != null) {
						String fileName = uploadFileName[i];
						Long logoIfile = tfileService.CommonFileUpload(upload[i], filetype[i], fileName,uploadContentType[i], null, instsession);
						if(filetype[i].equals("PdtLogoPage")){
							loanPdt.setPdtIfile(logoIfile);
						}else if(filetype[i].equals("loanPdtLogo")){
							loanPdt.setLogoIfile(logoIfile);
						}
					}
				}
			}
			this.loanPdtService.updateLoanPdtBackstage(loanPdt, iinst,getUserSession(), getIpaddr(),OperType.MODIFY_PDT);
			
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "forward";
	}
	
	public String deleteProduct() throws Exception{
		this.loanPdtService.deleteLoanPdtBackstage(loanPdt, iinst,getUserSession(), getIpaddr());
		return "instProdInfo";
	}
	
	@JqDataSet(content = "{o:pdtName},{o:minQuota},{o:maxQuota},{o:pdtMinTerm},{o:pdtMaxTerm},{o:pdtRate},{o:creditTerm},{o:reqTotal},{o:area},{o:pdtStatus.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}
	
	
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<LoanPdt> getLoanPdtList() {
		return loanPdtList;
	}

	public void setLoanPdtList(List<LoanPdt> loanPdtList) {
		this.loanPdtList = loanPdtList;
	}

	public String getPdtName() {
		return pdtName;
	}

	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getMinTermType() {
		return minTermType;
	}

	public void setMinTermType(String minTermType) {
		this.minTermType = minTermType;
	}

	public String getMaxTermType() {
		return maxTermType;
	}

	public void setMaxTermType(String maxTermType) {
		this.maxTermType = maxTermType;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public TFile getPdtFile() {
		return pdtFile;
	}

	public void setPdtFile(TFile pdtFile) {
		this.pdtFile = pdtFile;
	}

	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPdtStatus() {
		return pdtStatus;
	}

	public void setPdtStatus(String pdtStatus) {
		this.pdtStatus = pdtStatus;
	}

	public LoanPdt getLoanPdt() {
		return loanPdt;
	}

	public void setLoanPdt(LoanPdt loanPdt) {
		this.loanPdt = loanPdt;
	}
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	public String getIsUpShelves() {
		return isUpShelves;
	}

	public void setIsUpShelves(String isUpShelves) {
		this.isUpShelves = isUpShelves;
	}
	

	public TFile getTfile() {
		return tfile;
	}

	public void setTfile(TFile tfile) {
		this.tfile = tfile;
	}

	public Map<String, String> getProvMap() {
		return provMap;
	}

	public void setProvMap(Map<String, String> provMap) {
		this.provMap = provMap;
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

	public String getMinterm() {
		return minterm;
	}

	public void setMinterm(String minterm) {
		this.minterm = minterm;
	}

	public String getMaxterm() {
		return Maxterm;
	}

	public void setMaxterm(String maxterm) {
		Maxterm = maxterm;
	}

	public String getMinyearRate() {
		return MinyearRate;
	}

	public void setMinyearRate(String minyearRate) {
		MinyearRate = minyearRate;
	}

	public String getMaxyearRate() {
		return MaxyearRate;
	}

	public void setMaxyearRate(String maxyearRate) {
		MaxyearRate = maxyearRate;
	}

	public String getMinQuota() {
		return minQuota;
	}

	public void setMinQuota(String minQuota) {
		this.minQuota = minQuota;
	}

	public String getMaxQuota() {
		return MaxQuota;
	}

	public void setMaxQuota(String maxQuota) {
		MaxQuota = maxQuota;
	}

	public Map<String, String> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<String, String> cityMap) {
		this.cityMap = cityMap;
	}

	public String getFastday() {
		return fastday;
	}

	public void setFastday(String fastday) {
		this.fastday = fastday;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

}
