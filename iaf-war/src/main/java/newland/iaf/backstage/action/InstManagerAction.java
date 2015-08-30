package newland.iaf.backstage.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.FileType;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.dict.InstStatusType;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.action.common.LoanOrdListAction;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.InstLegalPers;
import newland.iaf.inst.model.condition.InstCondition;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
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
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台管理机构信息管理
 * 
 * @author lindaqun
 * 
 */
@ParentPackage("struts")
@Namespace("/backstage/instmanager")
@Action(value = "instManager", interceptorRefs = {
		@InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png, image/pjpeg",
				"maximumSize", "2097152"
		}),
		@InterceptorRef(value = "base_stack")
})
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "instAdd", type = "dispatcher", location = "/backstage/instmanager/instAdd.jsp"),
		@Result(name = "modify", type = "dispatcher", location = "/backstage/instmanager/instModify.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/backstage/instmanager/instManagerIndex.jsp"),
		@Result(name = "instRole", type = "redirect", location = "instRole.action"),
		@Result(name="logoInfo",type="dispatcher",location="/backstage/instmanager/logoInfo.jsp"),
		@Result(name="otherInfo",type="dispatcher",location="/backstage/instmanager/otherInfo.jsp"),
		@Result(name="logoUpload",type="dispatcher",location="/backstage/instmanager/logoUpload.jsp"),
		@Result(name="otherUpload",type="dispatcher",location="/backstage/instmanager/otherUpload.jsp"),
		@Result(name="otherSuccess",type="dispatcher",location="/backstage/instmanager/otherSuccess.jsp"),
		@Result(name="logoSuccess",type="dispatcher",location="/backstage/instmanager/logoSuccess.jsp"),
		@Result(name="toProcess",type="dispatcher",location="/backstage/instmanager/instManagerIndexUser.jsp"),
		@Result(name = "backstageInstUser", type = "redirect", location = "backstageInstUser.action") })
public class InstManagerAction extends BSBaseAction {

	private static final long serialVersionUID = 6060608376169753996L;

	private DataSet dataSet;// 用户JQGrid数据

	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;

	@Resource(name = "tfileService")
	private TFileService tfileService;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String instName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String regAddr;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst inst;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private InstBusiInfo instBusiInfo;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private InstLegalPers instLegalPers;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String sex;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String maritalStatus;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String education;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String bankCode;
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	private String contact;
	
	private String tel;
	
	private String stat;
	
	/**
	 * 上传文件
	 */
	private File upload;

	private String filetype;

	private String uploadFileName;
	private String uploadContentType;

	private List<TFile> logoTFile;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<TFile> tfileList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String ifile;
	
	@Begin
	@End
	public String execute() {
		instList = instService.queryInst();
		instName="";
		regAddr="";
		contact="";
		tel="";
		stat="";
		return "success";
	}
	
	public  String toProcess(){
		instList = instService.queryInst();
		instName="";
		regAddr="";
		contact="";
		tel="";
		stat="";
		return "toProcess";
	}
	
    public String deletePic(){
    	tfileService.deleteByIfile(Long.parseLong(ifile));
    	
    	tfileList=tfileService.queryInstFile(inst.getIinst(), FileType.instFile);
		this.setSessionObj("tfileList",tfileList);
		logoTFile=tfileService.queryInstFile(inst.getIinst(), FileType.instLogo);
		this.setSessionObj("logoTFile",logoTFile);
    	return "modify";
    }

    public String logoInfo(){
    	logoTFile=tfileService.queryInstFile(inst.getIinst(), FileType.instLogo);
		this.setSessionObj("logoTFile",logoTFile);
    	return "logoInfo";
    }
	
    public String logoUpload(){
    	return "logoUpload";
    }
    
    public String otherUpload(){
    	return "otherUpload";
    }
    
    public String upload() throws Exception{
		if(upload!=null){
			String fileName = uploadFileName;
			// 构造文件上传信息
			tfileService.CommonFileUploadById(upload,
					filetype, fileName,
					uploadContentType,null, inst.getIinst());
		}
		return "otherSuccess";
	}
    
    public String instLogoUpload() throws Exception{
    	if(upload!=null){
			String fileName = uploadFileName;
			// 构造文件上传信息
			tfileService.CommonFileUploadById(upload,
					filetype, fileName,
					uploadContentType,null, inst.getIinst());
		}
		return "logoSuccess";
    }
    
    public String otherInfo(){
		tfileList=tfileService.queryInstFile(inst.getIinst(), FileType.instFile);
		this.setSessionObj("tfileList",tfileList);
    	return "otherInfo";
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
			InstCondition instCondition = new InstCondition();
			// 设置查询条件
			if (StringUtils.isNotBlank(instName)) {
				List<Long> iinstList =new ArrayList<Long>();
				iinstList.add(Long.parseLong(instName));
				instCondition.setIinst(iinstList);
			}
			if (StringUtils.isNotBlank(regAddr)) {
				instCondition.setRegAddr(regAddr.trim()+"%");
			}
			
			if(StringUtils.isNotBlank(contact)){
				instCondition.setContact(contact.trim()+"%");
			}
			
			if(StringUtils.isNotBlank(tel)){
				instCondition.setContactPhone(tel.trim()+"%");
			}
			
			if(StringUtils.isNotBlank(stat)){
				instCondition.setInstStatusType(InstStatusType.valueOf(stat));
			}
			
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.asc("iinst"));
			instCondition.setOrders(orderList);
			instList = this.instService.queryInstByCon(instCondition, page);
			dataSet.setGridModel(instList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String instAdd() {
		return "instAdd";
	}

	@InputConfig(resultName = "instAdd")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "inst.instName", message="机构名称不能为空！",shortCircuit=true)
//					@RequiredStringValidator(fieldName = "inst.loanPermit", message="融资性机构经营许可证不能为空！",shortCircuit=true),
//					@RequiredStringValidator(fieldName = "inst.busiLicense", message="营业执照号不能为空！",shortCircuit=true),
//					@RequiredStringValidator(fieldName = "inst.instNature", message="机构性质不能为空！",shortCircuit=true),
//					@RequiredStringValidator(fieldName = "inst.contact", message = "联系人不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.contactPhone", message = "联系电话不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.regAddr", message = "注册地址不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.busiRegion", message = "经营范围不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.officialWebsite", message = "官网不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.shortPhone", message = "官方电话不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.instId", message = "内部编号不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.introduce", message = "机构简介不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.qqUid", message = "qq不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.bankCode", message = "银行编号不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.acceptRegion", message = "受理地区不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.loanAccountNo", message = "放贷专用账号不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.bank", message = "开户行不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.loanName", message = "放贷持卡人名称不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.repaymentNo", message = "还贷银行卡账号不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.repaymentBank", message = "还贷银行开户行不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.repaymentBank", message = "还贷银行开户行不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.repaymentName", message = "还贷持卡人姓名不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instLegalPers.legalPersName", message = "姓名不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instLegalPers.cerdNo", message = "身份证号码不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instLegalPers.familyAddr", message = "家庭住址不能为空", shortCircuit = true)
			},
			requiredFields  = {
//					@RequiredFieldValidator(fieldName = "inst.regTime", message = "注册时间不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "inst.regCapital", message = "注册资金不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "inst.openTime", message = "开业时间不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "inst.totalCapital", message = "总资产不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "instLegalPers.sex", message = "性别不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "instLegalPers.age", message = "年龄不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "instLegalPers.education", message = "学历不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "instLegalPers.maritalStatus", message = "婚姻状况不能为空", shortCircuit = true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "inst.instName", minLength = "2", message="机构名称不能少于2个字符", shortCircuit = true)
			        //@StringLengthFieldValidator(fieldName = "inst.contact", minLength = "2", message="联系人不能少于2个字符", shortCircuit = true)
			}
	)
	public String saveInstInfo() throws Exception {
		IafConsoleSession ics = (IafConsoleSession) SessionFilter
				.getIafSession();
		try {
			inst.setInstStat(InstStatusType.USED);
			inst.setPdtCount(new Long(0));
			if(inst.getRegCapital()==null){
				inst.setRegCapital(BigDecimal.ZERO);
				inst.setTotalCapital(BigDecimal.ZERO);
			}
			if(StringUtils.isEmpty(inst.getLoanPermit())){
				inst.setLoanPermit("--");
			}
			if(inst.getRegTime()==null){
				inst.setRegTime(new Date());
			}
			this.instService.saveInstInfoAll(inst, instBusiInfo, instLegalPers,ics,getIpaddr());
			if (upload != null) {
				InstSession is = new InstSession();
				is.setInst(inst);
				String fileName = uploadFileName;
				tfileService.CommonFileUpload(upload, filetype, fileName,
						uploadContentType, null, is);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "instAdd";
		}
	}

	public String modifyInst() throws Exception {
		inst = instList.get(index);
//		List<TFile> tfileLs = tfileService.queryInstFile(inst.getIinst(), FileType.instLogo);
//		if(tfileLs !=null && tfileLs.size()>0){
//			TFile fil = tfileLs.get(tfileLs.size()-1);
//			inst.setLogoIfile(fil.getIfile());
//		}
		tfileList = this.tfileService.queryInstFile(inst.getIinst(), FileType.instFile);
		logoTFile=tfileService.queryInstFile(inst.getIinst(), FileType.instLogo);
		instBusiInfo = this.instService
				.findInstBusiInfoByiinst(inst.getIinst());
		if(instBusiInfo==null||instBusiInfo.equals("")){
			instBusiInfo = new InstBusiInfo();
			instBusiInfo.setiInst(inst.getIinst());
		}
		instLegalPers = this.instService.getInstLegalPersByIinst(inst
				.getIinst());
		if(instLegalPers==null||instLegalPers.equals("")){
			instLegalPers = new InstLegalPers();
			instLegalPers.setiInst(inst.getIinst());
		}
//		Merch instBankCode = merchService.queryByMerchNo(inst.getInstId());
//		
//		if(instBankCode!=null&&StringUtils.isEmpty(instBusiInfo.getBankCode())){
//			instBusiInfo.setBankCode(instBankCode.getBankCode());
//		}
		return "modify";
	}

	@InputConfig(resultName = "modify")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "inst.instName", message="机构名称不能为空！",shortCircuit=true)
//					@RequiredStringValidator(fieldName = "inst.loanPermit", message="融资性机构经营许可证不能为空！",shortCircuit=true),
//					@RequiredStringValidator(fieldName = "inst.busiLicense", message="营业执照号不能为空！",shortCircuit=true),
//					@RequiredStringValidator(fieldName = "inst.instNature", message="机构性质不能为空！",shortCircuit=true),
//					@RequiredStringValidator(fieldName = "inst.contact", message = "联系人不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.contactPhone", message = "联系电话不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.regAddr", message = "注册地址不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.busiRegion", message = "经营范围不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.officialWebsite", message = "官网不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.shortPhone", message = "官方电话不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.instId", message = "内部编号不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.introduce", message = "机构简介不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "inst.qqUid", message = "qq不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.acceptRegion", message = "受理地区不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.loanAccountNo", message = "放贷专用账号不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.bank", message = "开户行不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.bankCode", message = "银行编号不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.loanName", message = "放贷持卡人名称不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.repaymentNo", message = "还贷银行卡账号不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.repaymentBank", message = "还贷银行开户行不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.repaymentBank", message = "还贷银行开户行不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instBusiInfo.repaymentName", message = "还贷持卡人姓名不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instLegalPers.legalPersName", message = "姓名不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instLegalPers.cerdNo", message = "身份证号码不能为空", shortCircuit = true),
//					@RequiredStringValidator(fieldName = "instLegalPers.familyAddr", message = "家庭住址不能为空", shortCircuit = true)
			},
			requiredFields  = {
//					@RequiredFieldValidator(fieldName = "inst.regTime", message = "注册时间不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "inst.regCapital", message = "注册资金不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "inst.openTime", message = "开业时间不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "inst.totalCapital", message = "总资产不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "instLegalPers.sex", message = "性别不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "instLegalPers.age", message = "年龄不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "instLegalPers.education", message = "学历不能为空", shortCircuit = true),
//					@RequiredFieldValidator(fieldName = "instLegalPers.maritalStatus", message = "婚姻状况不能为空", shortCircuit = true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "inst.instName", minLength = "2", message="机构名称不能少于2个字符", shortCircuit = true)
			       // @StringLengthFieldValidator(fieldName = "inst.contact", minLength = "2", message="联系人不能少于2个字符", shortCircuit = true)
			}
	)
	public String updateInstInfo() throws Exception {
		try {
			IafConsoleSession ics = (IafConsoleSession) SessionFilter
					.getIafSession();
			
			Inst ist = instService.findInstById(inst.getIinst());
			if(inst.getRegCapital()==null){
				inst.setRegCapital(BigDecimal.ZERO);
				inst.setTotalCapital(BigDecimal.ZERO);
			}
			if(StringUtils.isEmpty(inst.getLoanPermit())){
				inst.setLoanPermit("--");
			}
			if(inst.getRegTime()==null){
				inst.setRegTime(new Date());
			}
			if(!ist.getInstName().equals(inst.getInstName())){
				List<LoanOrd> loanOrdList = loanOrdService.queryByIinst(inst.getIinst());
				if(loanOrdList!=null){
					for (LoanOrd loanOrd : loanOrdList) {
						loanOrd.setInstName(inst.getInstName());
						loanOrdService.updateLoanOrd(loanOrd);
					}
				}
			}
			//instBusiInfo.setBankCode(bankCode);
			this.instService.updateInstInfoAll(inst, instBusiInfo,
					instLegalPers, ics, getIpaddr());
			if (upload != null) {
				InstSession is = new InstSession();
				is.setInst(inst);
				String fileName = uploadFileName;
				tfileService.CommonFileUpload(upload, filetype, fileName,
						uploadContentType, null, is);
			}
			super.addActionError("机构信息修改成功!");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
	}

	public String disableInst() throws Exception {
		IafConsoleSession ics = (IafConsoleSession) SessionFilter
				.getIafSession();
		try {
			//inst = instList.get(index);
			if (inst.getInstStat().equals(InstStatusType.UNUSED)) {
				super.addActionMessage("机构已经是禁用状态,不能再禁用!");
				return "success";
			}

			inst.setInstStat(InstStatusType.UNUSED);
			this.instService.updateInst(inst,ics,getIpaddr());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
	}
	
	public String enableInst() throws Exception {
		IafConsoleSession ics = (IafConsoleSession) SessionFilter
				.getIafSession();
		try {
			//inst = instList.get(index);
			if (inst.getInstStat().equals(InstStatusType.USED)) {
				super.addActionMessage("机构已经是启用状态,不能再禁用!");
				return "success";
			}

			inst.setInstStat(InstStatusType.USED);
			this.instService.updateInst(inst,ics,getIpaddr());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
	}

	public String instRole() throws Exception {
		inst = instList.get(index);
		return "instRole";
	}

	public String backstageInstUser() {
		inst = instList.get(index);
		return "backstageInstUser";
	}

	@JqDataSet(content = "{o:instName},{o:regAddr},{o:contact},{o:contactPhone},{o:instStat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public InstBusiInfo getInstBusiInfo() {
		return instBusiInfo;
	}

	public void setInstBusiInfo(InstBusiInfo instBusiInfo) {
		this.instBusiInfo = instBusiInfo;
	}

	public InstLegalPers getInstLegalPers() {
		return instLegalPers;
	}

	public void setInstLegalPers(InstLegalPers instLegalPers) {
		this.instLegalPers = instLegalPers;
	}

	public String getSex() {
		return sex;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<TFile> getLogoTFile() {
		return logoTFile;
	}

	public void setLogoTFile(List<TFile> logoTFile) {
		this.logoTFile = logoTFile;
	}

	public List<TFile> getTfileList() {
		return tfileList;
	}

	public void setTfileList(List<TFile> tfileList) {
		this.tfileList = tfileList;
	}

	public String getIfile() {
		return ifile;
	}

	public void setIfile(String ifile) {
		this.ifile = ifile;
	}

}
