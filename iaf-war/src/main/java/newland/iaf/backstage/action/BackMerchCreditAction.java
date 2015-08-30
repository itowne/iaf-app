package newland.iaf.backstage.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.condition.DateRange;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.merch.model.AcceptType;
import newland.iaf.merch.model.LoanCreditReport;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchInfoPermission;
import newland.iaf.merch.service.MerchCreditReportService;
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
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 
 * 后台管理 商户信用报告 
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/merchcreditreport")
@Action(value = "backMerchCredit",interceptorRefs = {
	@InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png, image/pjpeg",
//			"maximumSize", "2097152"
	}),
	@InterceptorRef(value = "base_stack")})
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name="init",type="dispatcher",location="/backstage/merchcreditreport/initCreditreport.jsp"),
	@Result(name = "index",  type = "dispatcher",location = "/backstage/merchcreditreport/credit-report.jsp"),
	@Result(name = "merchInfoIndex",  type = "dispatcher",location = "/backstage/merchcreditreport/merchInfoIndex.jsp"),
	@Result(name = "showHis",  type = "dispatcher",location = "/backstage/merchcreditreport/credit-his.jsp"),
	@Result(name = "list",  type = "JqgridJsonResult"),
	@Result(name = "freemarker",  type = "ReportResult"),
	@Result(name = "viewReport",  type = "dispatcher",location = "/backstage/merchcreditreport/credit-report.jsp"),
	@Result(name = "other",type = "dispatcher",location = "/backstage/merchcreditreport/addMemoSuccess.jsp"),
	@Result(name = "addVerificationSuccess",type = "dispatcher", location = "/backstage/merchcreditreport/addVerificationSuccess.jsp"),
	@Result(name="uploadSuccess",type="dispatcher",location="/backstage/merchcreditreport/uploadSuccess.jsp"),
	@Result(name="toUpload",type="dispatcher",location="/backstage/merchcreditreport/upload.jsp"),
	@Result(name="picDetail",type="dispatcher",location="/backstage/merchcreditreport/picDetail.jsp"),
	@Result(name="error",type="dispatcher",location="/backstage/merchcreditreport/error.jsp"),
	@Result(name = "download", type = "fileDownload", params = {"sourceFileName", "downloadFile", "targetFileName", "targetFileName"})
    })
public class BackMerchCreditAction extends BSBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataSet dataSet;
	
	@Resource(name = "tfileService")
	private TFileService tfileService;
	
	@Resource(name = "tfileService")
	private TFileService tFileService;
	
	@Resource (name = "merchCreditReportService")
	private MerchCreditReportService merchCreditReportService;
	
	@Resource(name = "provinceService")
	private ProvinceService provinceService;
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanCreditReport report;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long imerch;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String dateRange;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanCreditReport> reportList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Date beginDate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Date endDate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Boolean first;
	
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
	private LoanOrd loanOrd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<TFile> tFileList;
	
	private Boolean wrapper = Boolean.FALSE;
	
	private String position;
	
	private String instType;
	
	private int index;
	
	private String memo;
	
	private String verification;
	
	private File downloadFile;
	
	private String targetFileName;
	
	private static String TIME_CTRL = "TIME_CTRL";
	
	private static int timeout = 10000;
	
	private File[] upload;
	
	private String[] filetype;
	
	private String[] uploadFileName;
	
	private String[] uploadContentType;
	
	private List<TFile> otherFiles;
	
	private String ifile;
	
	@Begin
	@End
	public String execute(){
		provMap = provinceService.getProvince();
		provinceCode="";
		cityCode="";
		region="";
		return "merchInfoIndex";
	}
	
	public String list(){
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(10);// 设置每页显示数
		if (dataSet == null) {
			dataSet = new DataSet();
			page.setPageOffset(0);
		} else {
			page.setPageOffset(dataSet.getPage() - 1);
		}
		this.reportList = this.merchCreditReportService.queryReoprtList(imerch,page);
		dataSet.setGridModel(reportList);
		dataSet.setRecords(reportList.size());
		dataSet.setTotal(page.getPageAmt());
		dataSet.setRecords(page.getRecAmt());
		return "list";
	}
	
	public String addMemo() throws Exception{
		if(memo.length()>=500){
			return "error";
		}
		Merch merch = merchService.findMerchById(imerch);
		if(merch==null){
			logger.debug("add otherfile Error! cause by : merch not found ...");
		}
		merch.setMemo(memo);
		merchService.updateMerch(merch);
		return "other";
	}
	
	public String addVerification() throws Exception{
		if(verification.length()>=500){
			return "error";
		}
		Merch merch = merchService.findMerchById(imerch);
		if(merch==null){
			logger.debug("add merch_busi_data_verification Error! cause by : merch not found ...");
		}
		merch.setBusiDataVerification(verification);;
		merchService.updateMerch(merch);
		return "addVerificationSuccess";
	}
	
	public String creditIndex(){
		first = true;
		this.dateRange = DateRange.PRE_MONTH.name();
		DateRange rangeObj = DateRange.valueOf(dateRange);
		this.report = this.merchCreditReportService.genCreditReport(imerch, rangeObj);
		return "index";
	}

	public String download(){
		this.report = this.reportList.get(index);
		try {
			DateRange rangeObj = DateRange.valueOf(dateRange);
			downloadFile = this.merchCreditReportService.createPdfFile(report, getBaseUrl(), false,rangeObj);
		} catch (Throwable e) {
			logger.error("保存报表出错", e);
			addActionError(e.getMessage());
			return "showHis";
		}
		return "download";
	}
	
	private boolean isTimeout(){
		Long queryTime = (Long)this.getSessionObj(TIME_CTRL);
		if (queryTime == null){
			this.setSessionObj(TIME_CTRL, System.currentTimeMillis());
			return true;
		}else{
			if ((System.currentTimeMillis() - queryTime) < timeout){
				return false;
			}else{
				return true;
			}
		}
	}
	
	public String queryNewest(){
		first = false;
		if (isTimeout() == false) {
			addActionError("查询间隔时间间隔太短，请10秒后再试！");
			if(dateRange==null){
				return "init";
			}
		}
		if (StringUtils.isNotBlank(dateRange)){
			DateRange rangeObj = DateRange.valueOf(dateRange);
			this.beginDate = rangeObj.getBeginDate();
			this.endDate = rangeObj.getEndDate();
			//商户经营数据审核情况查看权限赋予
			String permset ="";
			List<MerchInfoPermission> list = this.merchCreditReportService
					.queryInfoPermission(merchService.findMerchById(imerch));
			for (MerchInfoPermission merchInfoPermission : list) {
				permset=permset+merchInfoPermission.getPosition();
			}
			if(permset.indexOf("MERCH_BUSI_DATA_VERIFICATION")<0){
				this.merchCreditReportService.setInfoPermission(merchService.findMerchById(imerch),
						PagePosition.MERCH_BUSI_DATA_VERIFICATION, AcceptType.getAcceptType(true));
			}
			this.report = this.merchCreditReportService.genCreditReport(imerch, rangeObj);
		}else{
			addActionError("参数错误：查询时间未设置！");
		}
		return "index";
	}
	
	public String queryHis(){
		this.report = null;
		return "showHis";
	}
	
	public String saveReport(){
		if (report == null){
			addActionError("未生成经营数据报告，无法保存！");
			return "index";
		}
		if (report.getIreport() != null){
			addActionError("经营数据报告已保存！");
			return "index";
		}
		try {
			this.merchCreditReportService.saveReport(report);
			this.beginDate = report.getBeginDate();
			this.endDate = report.getEndDate();
			//this.merchCreditReportService.createPdfFile(report, getBaseUrl(), true);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("保存报表出错", e);
			addActionError(e.getMessage());
			return "index";
		}
		return this.queryHis();
	}
	
	public String viewPagePositon(){
		PagePosition page = null;
		if (StringUtils.isNotBlank(position)){
			page = PagePosition.valueOf(position);
		}
		if (page == null) return "index";
		return "freemarker";
	}
	
	public String viewReport(){
		first = false;
		this.report = this.reportList.get(index);
		this.beginDate = report.getBeginDate();
		this.endDate = report.getEndDate();
		//this.merchCreditReportService.genCreditReport(report);
		return "viewReport";
	}
	
	public String toUpload() {
		return "toUpload";
	}
	
	public String upload() throws Exception {
		Long length=upload[0].length();
		try {
			if (upload != null) {
				if(length > 5242880){
					return "input";
				}
				String fileName = uploadFileName[0];
				String ex=StringUtils.substringAfter(uploadFileName[0], ".");
				if(!ex.equals("jpg")&&!ex.equals("png")&&!ex.equals("gif")){
					throw new RuntimeException("上传的文件格式不正确!请重新上传");
				}
				// 构造文件上传信息
				tFileService.CommonFileUploadById(upload[0],filetype[0],fileName,uploadContentType[0],imerch, null);
			}
			return "uploadSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "toUpload";
		}
	}
	
	public String picDetail(){
		otherFiles=tFileService.queryByImerch(imerch);
		this.setSessionObj("otherFiles",otherFiles);
		return "picDetail";
	}
	
	public String deletePic(){
		tFileService.deleteByIfile(Long.parseLong(ifile));
		//tFileList=tfileService.queryBy(merchSession.getMerch().getImerch(), FileType.merchFile);
		otherFiles=tFileService.queryByImerch(imerch);
		this.setSessionObj("otherFiles", otherFiles);
		return "picDetail";
		
	} 
	
	@JqDataSet(content = "{o:reportId},{i:(format.date)({o:beginDate})},{i:(format.date)({o:endDate})},{i:(format.time)({o:reportDate})}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public LoanCreditReport getReport() {
		return report;
	}

	public void setReport(LoanCreditReport report) {
		this.report = report;
	}

	public Boolean getWrapper() {
		return wrapper;
	}

	public void setWrapper(Boolean wrapper) {
		this.wrapper = wrapper;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public List<LoanCreditReport> getReportList() {
		return reportList;
	}

	public void setReportList(List<LoanCreditReport> reportList) {
		this.reportList = reportList;
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

	public File getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(File downloadFile) {
		this.downloadFile = downloadFile;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	public Boolean getFirst() {
		return first;
	}

	public void setFirst(Boolean first) {
		this.first = first;
	}

	public String getInstType() {
		return instType;
	}

	public void setInstType(String instType) {
		this.instType = instType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public TFileService gettFileService() {
		return tFileService;
	}

	public void settFileService(TFileService tFileService) {
		this.tFileService = tFileService;
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

	public List<TFile> getOtherFiles() {
		return otherFiles;
	}

	public void setOtherFiles(List<TFile> otherFiles) {
		this.otherFiles = otherFiles;
	}

	public String getIfile() {
		return ifile;
	}

	public void setIfile(String ifile) {
		this.ifile = ifile;
	}

	public List<TFile> gettFileList() {
		return tFileList;
	}

	public void settFileList(List<TFile> tFileList) {
		this.tFileList = tFileList;
	}
	
}
