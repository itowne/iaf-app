package newland.iaf.inst.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.FileType;
import newland.iaf.base.model.TFile;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.InstLegalPers;
import newland.iaf.inst.service.InstService;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 机构资料管理action
 * @author lindaqun
 *
 */

@ParentPackage("struts")
@Namespace("/inst/information")
@Action(value = "instInformation",interceptorRefs = {
		@InterceptorRef(value = "fileUpload", params = {"allowedTypes","",
				"maximumSize", "2097152"
			}),
			@InterceptorRef(value = "base_stack")}
)
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "detail", type = "dispatcher",location = "/inst/information/instInformation.jsp"),
	@Result(name = "success", type = "dispatcher",location = "/inst/information/success.jsp"),
	@Result(name = "picDetail", type = "dispatcher",location = "/inst/information/detail.jsp"),
	@Result(name = "otherInfo", type = "dispatcher",location = "/inst/information/otherInfo.jsp"),
	@Result(name="logoInfo",type="dispatcher",location="/inst/information/logoInfo.jsp"),
	@Result(name="toLogoUpload",type="dispatcher",location="/inst/information/logoUpload.jsp"),
	@Result(name="logoSuccess",type="dispatcher",location="/inst/information/logoSuccess.jsp"),
	@Result(name="modifySuccess",type="dispatcher",location="/inst/information/modifySuccess.jsp"),
	@Result(name="modifyLogo",type="dispatcher",location="/inst/information/modifyLogo.jsp"),
	@Result(name="otherModify",type="dispatcher",location="/inst/information/otherUpload.jsp"),
	@Result(name="otherModifySuccess",type="dispatcher",location="/inst/information/otherSuccess.jsp"),
	@Result(name = "uploadIndex", type = "dispatcher",location = "/inst/information/uploadIndex.jsp")
    })
public class InstInformationAction extends InstBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5388317491255791864L;

	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;
	
	@Resource(name = "tfileService")
	private TFileService tfileService;
	
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
	private List<TFile> tfileList;
	
	private List<TFile> logoTFile;
	
	private String filetype;
	
	private File upload;
	
	private String uploadFileName;
    private String uploadContentType;
    
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String ifile;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String fileId;
    
    @Begin
	
	public String execute() throws Exception{
    	inst = this.getUserSession().getInst();
		instBusiInfo = this.instService.findInstBusiInfoByiinst(inst.getIinst());
		instLegalPers = this.instService.getInstLegalPersByIinst(inst.getIinst());
		tfileList = this.tfileService.queryInstFile(inst.getIinst(), FileType.instFile);
		logoTFile=tfileService.queryInstFile(inst.getIinst(), FileType.instLogo);
		return "detail";
	}
    
    public String uploadIndex() throws Exception{
    	return "uploadIndex";
    }
    
    public String toLogoUpload(){
    	return "toLogoUpload";
    }
    
    public String modifyLogo(){
    	return "modifyLogo";
    }

    public String otherModify(){
    	return "otherModify";
    }
    
    public String logoInfoUpdate() throws Exception{
    	if(StringUtils.isNotBlank(fileId)){
    		tfileService.deleteByIfile(Long.parseLong(fileId));
    	}
    	if(upload!=null){
			InstSession instSession = (InstSession) SessionFilter.getIafSession();
			String fileName = uploadFileName;
			// 构造文件上传信息
			tfileService.CommonFileUpload(upload,
					filetype, fileName,
					uploadContentType,null, instSession);
		}
    	InstSession instSession = (InstSession) SessionFilter.getIafSession();
    	logoTFile=tfileService.queryInstFile(instSession.getInst().getIinst(), FileType.instLogo);
		this.setSessionObj("logoTFile",logoTFile);
		tfileList=tfileService.queryInstFile(instSession.getInst().getIinst(), FileType.instFile);
		this.setSessionObj("tfileList",tfileList);
    	return "modifySuccess";
    }
    
    public String otherInfoUpdate() throws Exception{
    	if(StringUtils.isNotBlank(fileId)){
    		tfileService.deleteByIfile(Long.parseLong(fileId));
    	}
    	if(upload!=null){
			InstSession instSession = (InstSession) SessionFilter.getIafSession();
			String fileName = uploadFileName;
			// 构造文件上传信息
			tfileService.CommonFileUpload(upload,
					filetype, fileName,
					uploadContentType,null, instSession);
		}
    	InstSession instSession = (InstSession) SessionFilter.getIafSession();
    	tfileList=tfileService.queryInstFile(instSession.getInst().getIinst(), FileType.instFile);
		this.setSessionObj("tfileList",tfileList);
		logoTFile=tfileService.queryInstFile(instSession.getInst().getIinst(), FileType.instLogo);
		this.setSessionObj("logoTFile",logoTFile);
    	return "otherModifySuccess";
    }
    
    public String detail(){
    	InstSession instSession = (InstSession) SessionFilter.getIafSession();
		tfileList=tfileService.queryInstFile(instSession.getInst().getIinst(), FileType.instFile);
		this.setSessionObj("tfileList",tfileList);
    	return "picDetail";
    }
    
    public String deletePic(){
    	tfileService.deleteByIfile(Long.parseLong(ifile));
    	
    	InstSession instSession = (InstSession) SessionFilter.getIafSession();
    	tfileList=tfileService.queryInstFile(instSession.getInst().getIinst(), FileType.instFile);
		this.setSessionObj("tfileList",tfileList);
		logoTFile=tfileService.queryInstFile(instSession.getInst().getIinst(), FileType.instLogo);
		this.setSessionObj("logoTFile",logoTFile);
    	return "detail";
    }
    
    public String upload() throws Exception{
		if(upload!=null){
			InstSession instSession = (InstSession) SessionFilter.getIafSession();
			String fileName = uploadFileName;
			// 构造文件上传信息
			tfileService.CommonFileUpload(upload,
					filetype, fileName,
					uploadContentType,null, instSession);
		}
		return "success";
	}
    
    public String instLogoUpload() throws Exception{
    	if(upload!=null){
			InstSession instSession = (InstSession) SessionFilter.getIafSession();
			String fileName = uploadFileName;
			// 构造文件上传信息
			tfileService.CommonFileUpload(upload,
					filetype, fileName,
					uploadContentType,null, instSession);
		}
		return "logoSuccess";
    }
    
    public String otherInfo(){
    	InstSession instSession = (InstSession) SessionFilter.getIafSession();
		tfileList=tfileService.queryInstFile(instSession.getInst().getIinst(), FileType.instFile);
		this.setSessionObj("tfileList",tfileList);
    	return "otherInfo";
    }
    
    public String logoInfo(){
    	InstSession instSession = (InstSession) SessionFilter.getIafSession();
    	logoTFile=tfileService.queryInstFile(instSession.getInst().getIinst(), FileType.instLogo);
		this.setSessionObj("logoTFile",logoTFile);
    	return "logoInfo";
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

	public List<TFile> getTfileList() {
		return tfileList;
	}

	public void setTfileList(List<TFile> tfileList) {
		this.tfileList = tfileList;
	}

	public InstLegalPers getInstLegalPers() {
		return instLegalPers;
	}

	public void setInstLegalPers(InstLegalPers instLegalPers) {
		this.instLegalPers = instLegalPers;
	}

	public List<TFile> getLogoTFile() {
		return logoTFile;
	}

	public void setLogoTFile(List<TFile> logoTFile) {
		this.logoTFile = logoTFile;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getIfile() {
		return ifile;
	}

	public void setIfile(String ifile) {
		this.ifile = ifile;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
    
}
