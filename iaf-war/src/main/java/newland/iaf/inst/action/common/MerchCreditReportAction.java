package newland.iaf.inst.action.common;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.condition.DateRange;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.merch.model.LoanCreditReport;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchInfoPermission;
import newland.iaf.merch.service.MerchCreditReportService;
import newland.iaf.merch.service.MerchService;
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
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

import newland.iaf.merch.model.AcceptType;

/**
 * 商户信用报告管理  Action
 * @author wwa
 *
 */
@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "merchCreditReport")
@Results({
	@Result(name = "index",  type = "dispatcher",location = "/inst/loanord/common/credit-report.jsp"),
	@Result(name = "showHis",  type = "dispatcher",location = "/inst/loanord/common/credit-his.jsp"),
	@Result(name = "error",  type = "dispatcher",location = "/common/error/error.jsp"),
	@Result(name = "list",  type = "JqgridJsonResult"),
	@Result(name = "freemarker",  type = "ReportResult"),
	@Result(name = "addVerificationSuccess",type = "redirect", location = "/inst/loanord/common/addVerificationSuccess.jsp"),
	@Result(name="init",type="dispatcher",location="/inst/loanord/common/initCreditreport.jsp"),
	@Result(name = "viewReport",  type = "dispatcher",location = "/inst/loanord/common/credit-report.jsp"),
	@Result(name = "download", type = "fileDownload", params = {"sourceFileName", "downloadFile", "targetFileName", "targetFileName"})
    })
public class MerchCreditReportAction extends CommonAction {
		
	private static final long serialVersionUID = -3266295235527803603L;
	
	private DataSet dataSet;
	
	@Resource (name = "merchCreditReportService")
	private MerchCreditReportService merchCreditReportService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanCreditReport report;
	
	@Resource (name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	
	private Boolean wrapper = Boolean.TRUE;
	
	
	private String position;
	
	private String instType;
	
	private int index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String dateRange;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanCreditReport> reportList;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrd loanOrd;
	
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
	private Long imerch;
	
	private Merch mch;
	
	private String verification;
	/**
	 * 主入口
	 */
	@Override
	@Begin
	public String execute() {
		first = true;
		this.loanOrd = (LoanOrd)this.getSessionObj("loanOrd");
		if (loanOrd == null){
			addActionError("订单信息未找到");
		}
		this.dateRange = DateRange.PRE_MONTH.name();
		DateRange rangeObj = DateRange.valueOf(dateRange);
		this.report = this.merchCreditReportService.genCreditReport(getMerch().getImerch(), rangeObj);
		mch=getMerch();
		return "index";
	}
	
	private File downloadFile;
	
	private String targetFileName;
	

	public String download(){
		this.report = this.reportList.get(index);
		DateRange rangeObj = DateRange.valueOf(dateRange);
		try {
			this.downloadFile = this.merchCreditReportService.createPdfFile(report, getBaseUrl(), true,rangeObj);
		} catch (Throwable e) {
			logger.error("保存报表出错", e);
			addActionError(e.getMessage());
			return "showHis";
		}
		return "download";
	}
	private static String TIME_CTRL = "TIME_CTRL";
	
	private static int timeout = 10000;
	
	private boolean isTimeout(){
		Long queryTime = (Long)this.getSessionObj(TIME_CTRL);
		if (queryTime == null){
			this.setSessionObj(TIME_CTRL, System.currentTimeMillis());
			return true;
		}else{
			this.setSessionObj(TIME_CTRL, System.currentTimeMillis());
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
			String permset ="";
			List<MerchInfoPermission> list = this.merchCreditReportService
					.queryInfoPermission(getMerch());
			for (MerchInfoPermission merchInfoPermission : list) {
				permset=permset+merchInfoPermission.getPosition();
			}
			if(permset.indexOf("OTHER_INFO")<0){
				this.merchCreditReportService.setInfoPermission(getMerch(),
						PagePosition.OTHER_INFO, AcceptType.getAcceptType(true));
			}
			if(permset.indexOf("MERCH_BUSI_DATA_VERIFICATION")<0){
				this.merchCreditReportService.setInfoPermission(getMerch(),
						PagePosition.MERCH_BUSI_DATA_VERIFICATION, AcceptType.getAcceptType(true));
			}
			this.report = this.merchCreditReportService.genCreditReport(getMerch().getImerch(), rangeObj);
		}else{
			addActionError("参数错误：查询时间未设置！");
		}
		mch=getMerch();
		return "index";
	}
		
	private Merch getMerch(){
		Merch merch = this.merchService.findMerchById(loanOrd.getImerch());
		if (merch == null){
			logger.error("未找到对应的商户信息，订单号：" + loanOrd.getLoanOrdId());
			merch = new Merch();
			merch.setImerch(new Long(-1));
		}
		return merch;
	}
	
	public String queryHis(){
		this.report = null;
		return "showHis";
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
		this.reportList = this.merchCreditReportService.queryReoprtList(loanOrd.getImerch(),page);
		
		dataSet.setGridModel(reportList);
		dataSet.setRecords(reportList.size());
		dataSet.setTotal(page.getPageAmt());
		dataSet.setRecords(page.getRecAmt());
		return "list";
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
		}else{
			return "error";
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
		mch=getMerch();
		return "viewReport";
	}
	
	public String addVerification() throws Exception{
		if(verification.length()>=500){
			return "error";
		}
		Merch merch = this.merchService.findMerchById(report.getImerch());
		if(merch==null){
			logger.debug("add merch_busi_data_verification Error! cause by : merch not found ...");
		}
		merch.setBusiDataVerification(verification);;
		merchService.updateMerch(merch);
		return "addVerificationSuccess";
	}
	
	public String otherInfo(){
		return "otherInfo";
	}

	public LoanCreditReport getReport() {
		return report;
	}

	public void setReport(LoanCreditReport report) {
		this.report = report;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	@JqDataSet(content = "{o:reportId},{i:(format.date)({o:beginDate})},{i:(format.date)({o:endDate})},{i:(format.time)({o:reportDate})}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public Merch getMch() {
		return mch;
	}

	public void setMch(Merch mch) {
		this.mch = mch;
	}

	public List<LoanCreditReport> getReportList() {
		return reportList;
	}

	public void setReportList(List<LoanCreditReport> reportList) {
		this.reportList = reportList;
	}

	public Boolean getWrapper() {
		return wrapper;
	}

	public void setWrapper(Boolean wrapper) {
		this.wrapper = wrapper;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
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

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}
	
}
