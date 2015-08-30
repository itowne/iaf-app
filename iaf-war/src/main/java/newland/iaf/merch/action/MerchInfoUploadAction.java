package newland.iaf.merch.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import newland.iaf.base.model.FileType;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.dict.MetaType;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.service.MerchService;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "merchInfoUpload", interceptorRefs = {
		@InterceptorRef(value = "fileUpload", params = { "upload","image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png, image/pjpeg"}),//,"maximumSize", "5242880" }),
		@InterceptorRef("base_stack")
		})
@Results({
		@Result(name = "index", type = "dispatcher", location = "/merch/upload/index.jsp"),
		@Result(name = "detail", type = "dispatcher", location = "/merch/upload/picDetail.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/merch/upload/success.jsp"),
		@Result(name = "error", type = "dispatcher", location = "/merch/upload/error.jsp"),
		@Result(name = "other",type = "redirect",location = "/merch/upload/addMemoSuccess.jsp"),
		@Result(name = "input",type = "dispatcher",location = "/merch/upload/checkUploadSize.jsp" )
		})
public class MerchInfoUploadAction extends MerchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4152231215185450721L;

	@Resource(name = "tfileService")
	private TFileService tfileService;

	private String filetype;

	public String execute() {
		if (StringUtils.isBlank(filetype))
			filetype = "merchLogo";
		return "index";
	}

	private File upload;
	
	private String memo;
	 
	private String uploadFileName;
	private String uploadContentType;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<TFile> tFileList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String ifile;
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;

	public String upload() throws Exception {
		Long length=upload.length();
		try {
			if (upload != null) {
				if(length > 5242880){
					return "input";
				}
				String fileName = uploadFileName;
				String ex=StringUtils.substringAfter(uploadFileName, ".");
				if(!ex.equals("jpg")&&!ex.equals("png")&&!ex.equals("gif")){
					throw new RuntimeException("上传的文件格式不正确!请重新上传");
				}
				MerchSession merchSession = (MerchSession) SessionFilter
						.getIafSession();

				// 构造文件上传信息
				tfileService.CommonFileUpload(upload,
						filetype, fileName,
						uploadContentType, merchSession, null);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "index";
		}
	}

	public String picDetail(){
		MerchSession merchSession = (MerchSession) SessionFilter
				.getIafSession();
		//tFileList=tfileService.queryBy(merchSession.getMerch().getImerch(), FileType.merchFile);
		tFileList=tfileService.queryByImerch(merchSession.getMerch().getImerch());
		this.setSessionObj("tFileList", tFileList);
		return "detail";
	}
	public String deletaPic(){
		tfileService.deleteByIfile(Long.parseLong(ifile));
		
		MerchSession merchSession = (MerchSession) SessionFilter
				.getIafSession();
		//tFileList=tfileService.queryBy(merchSession.getMerch().getImerch(), FileType.merchFile);
		tFileList=tfileService.queryByImerch(merchSession.getMerch().getImerch());
		this.setSessionObj("tFileList", tFileList);
		return "detail";
	} 
	
	public String addMemo() throws Exception{
		if(memo.length()>=500){
			return "error";
		}
		MerchSession merchSession = (MerchSession) SessionFilter
				.getIafSession();
		Merch merch = merchSession.getMerch();
		if(merch==null){
			logger.debug("add otherfile Error! cause by : merch not found ...");
		}
		merch.setMemo(memo);
		merchService.updateMerch(merch);
		return "other";
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

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public List<TFile> gettFileList() {
		return tFileList;
	}

	public void settFileList(List<TFile> tFileList) {
		this.tFileList = tFileList;
	}

	public String getIfile() {
		return ifile;
	}

	public void setIfile(String ifile) {
		this.ifile = ifile;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
