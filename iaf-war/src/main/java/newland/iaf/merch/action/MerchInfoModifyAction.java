/**
 * 
 */
package newland.iaf.merch.action;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.MerchFieldSurvy;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchInfoAppeal;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchType;
import newland.iaf.merch.service.MerchInfoAppealService;
import newland.iaf.merch.service.MerchService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * 信用报告修改商户基本信息
 * @author Mr.Towne
 *
 */
@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "merchInfoModify", interceptorRefs = {
		@InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png, image/pjpeg",
				"maximumSize", "2097152"
		}),
		@InterceptorRef(value = "base_stack")
})
@Results({
		@Result(name="modify",type="dispatcher",location="/merch/merchInfoModify/edit-base-info.jsp"),
		@Result(name="general",type="dispatcher",location="/merch/merchInfoModify/general-base-info.jsp"),
		@Result(name="index",type="dispatcher",location="/merch/merchInfoModify/success.jsp")
		
})
public class MerchInfoModifyAction extends MerchBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8508291395556205538L;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Merch merch;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchBusiInfo merchBusiInfo;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchLegalPers merchLegalPers;
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;
	
	@Resource(name = "fundFlowService")
	private FundFlowService fundFlowService;
	
	@Resource(name="merchInfoAppealService")
	private MerchInfoAppealService merchInfoAppealService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private TFile tfile;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private TFile debitTFile;
	
	@Resource(name = "tfileService")
	private TFileService tfileService;
	
	private File[] upload;
	private String[] filetype;
	private String[] uploadFileName;
	private String[] uploadContentType;
	
	/**
	 * 加载修改信息
	 * @return
	 * @throws Exception 
	 */
	public String loadMerchInfo() throws Exception{
		MerchSession merchSession = (MerchSession) SessionFilter
				.getIafSession();
		merch = merchService.findMerchById(merchSession.getMerch().getImerch());
		merchBusiInfo=merchService.getMerchBusiInfoByImerch(merchSession.getMerch().getImerch());
		merchLegalPers = merchService.getMerchLegalPersByImerch(merchSession.getMerch().getImerch());
		if(merch.getLogoIfile()==null){
			tfile=null;
		}else{
			tfile = this.tfileService.getTFileByIfile(merch.getLogoIfile());
		}
		if(merch.getDebitIFile()==null){
			debitTFile=null;
		}else{
			debitTFile=this.tfileService.getTFileByIfile(merch.getDebitIFile());
		}
		
		if(merch.getMerchType()==MerchType.GENERAL){
			return "general";
		}
		return "modify";
	}
	
	@InputConfig(resultName = "modify")
	@Validations(
			requiredStrings = {
			@RequiredStringValidator(fieldName = "merchLegalPers.faAddr", shortCircuit = true, message = "家庭住址不能空!")},
			requiredFields  = {
				@RequiredFieldValidator(type=ValidatorType.SIMPLE,fieldName = "merchLegalPers.mobiPhone", shortCircuit = true, message = "手机号不能为空!"),
				@RequiredFieldValidator(type=ValidatorType.SIMPLE,fieldName = "merchLegalPers.officePhone", shortCircuit = true, message = "办公电话不能为空!"),
				@RequiredFieldValidator(type=ValidatorType.SIMPLE,fieldName = "merchLegalPers.age",shortCircuit = true, message = "年龄不能为空!"),
				@RequiredFieldValidator(type=ValidatorType.SIMPLE,fieldName = "merch.qqUid",shortCircuit = true, message = "qq不能为空!"),
				@RequiredFieldValidator(type=ValidatorType.SIMPLE,fieldName = "merchBusiInfo.bankCode",shortCircuit = true, message = "银行编号不能为空!")
		}
	)
	public String modifyMerchInfo() throws Exception{
		MerchSession merchSession = this.getMerchSession();
		Long imerch = merchSession.getMerch().getImerch();
		MerchLegalPers mp = merchService.getMerchLegalPersByImerch(imerch);
		MerchBusiInfo mbi = merchService.getMerchBusiInfoByImerch(imerch);
		//Merch mer = merchService.findMerchById(imerch);
		
		mbi.setBankCode(merchBusiInfo.getBankCode());
		mbi.setAccountName(merchBusiInfo.getAccountName());
		mp.setSex(merchLegalPers.getSex());
		mp.setAge(merchLegalPers.getAge());
		mp.setEduBackground(merchLegalPers.getEduBackground());
		mp.setOfficePhone(merchLegalPers.getOfficePhone());
		mp.setMobiPhone(merchLegalPers.getMobiPhone());
		mp.setMaritalStatus(merchLegalPers.getMaritalStatus());
		mp.setFaAddr(merchLegalPers.getFaAddr());
		//mer.setQqUid(merch.getQqUid());
				
		MerchFieldSurvy merchFieldSurvy = this.merchService.getMerchFieldSurvyByImerch(imerch);
		
		if (upload != null) {
			for (int i = 0; i < upload.length; i++) {
				String fileName = uploadFileName[i];
				Long ifile= tfileService.CommonFileUpload(upload[i], filetype[i], fileName,uploadContentType[i], merchSession,null );
				if(filetype[i].equals("debitLogo")){
					merch.setDebitIFile(ifile);
				}else if(filetype[i].equals("merchLogo")){
					merch.setLogoIfile(ifile);
				}
			}
			
		}
		merchService.updateMerch(merch);
		merchService.updateMerchMerchBusiInfo(mbi);
		merchService.updateMerchMerchLegalPers(mp);
		
		merchService.updateCredit(merch, mp, merchFieldSurvy );
		
		return "index";
	}

	public String modifyMerchGeneral() throws Exception{
		MerchSession merchSession = this.getMerchSession();
		Long imerch = merchSession.getMerch().getImerch();
		MerchLegalPers mp = merchService.getMerchLegalPersByImerch(imerch);
		merchLegalPers.setImerch(imerch);
		merchLegalPers.setiMerchLegalPers(mp.getiMerchLegalPers());
		
		MerchFieldSurvy merchFieldSurvy = this.merchService.getMerchFieldSurvyByImerch(imerch);
		
		//Merch merch_general = merchService.findMerchById(imerch);
		if (upload != null) {
			for (int i = 0; i < upload.length; i++) {
				String fileName = uploadFileName[i];
				Long ifile= tfileService.CommonFileUpload(upload[i], filetype[i], fileName,uploadContentType[i], merchSession,null );
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
		
		merchService.updateMerch(merch);
		merchService.updateMerchMerchBusiInfo(merchBusiInfo);
		merchService.updateMerchMerchLegalPers(merchLegalPers);
		
		merchService.updateCredit(merch, merchLegalPers, merchFieldSurvy );
		return "index";
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
}
