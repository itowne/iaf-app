package newland.iaf.merch.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import newland.iaf.IafApplication;
import newland.iaf.base.model.MerchFieldSurvy;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.base.trans.model.HcInstallLog;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table (name = "t_loan_rpt")
@org.hibernate.annotations.Table(appliesTo = "t_loan_rpt", indexes = {
		@Index(name = "idx_rpt_1", columnNames = { "rpt_id" }),
		@Index(name = "idx_rpt_2", columnNames = { "i_merch", "rpt_date" })
})
public class LoanCreditReport implements Serializable {
	
	public static String REPORT_PATH = IafApplication.creditReportPath();
	
	static Logger logger = LoggerFactory.getLogger(LoanCreditReport.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@TableGenerator(name = "i_report", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_report")
	@Column(name = "i_report")
	private Long ireport;
	/**
	 * 报告编号
	 */
	@Column (name = "rpt_id")
	private String reportId;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "report", fetch = FetchType.EAGER, targetEntity = ReportHtmlFile.class)
    @Fetch(FetchMode.SUBSELECT)
	private Set<ReportHtmlFile> reportFile;
	/**
	 * 商户ID
	 */
	@Column (name = "i_merch")
	private Long imerch;
	/**
	 * 商户名称
	 */
	@Column (name = "merch_name")
	private String merchName;
	
	
	/**
	 * 报告生成日期
	 */
	@Column (name = "rpt_date")
	private Date reportDate;
	/**
	 * 报告统计起始日期
	 */
	@Column (name = "begin_date")
	private Date beginDate;
	/**
	 * 报告统计结束日期
	 */
	@Column (name = "end_date")
	private Date endDate;
	/**
	 * 商户基本资料
	 */
	@Transient
	private Merch merch;
	/**
	 * 商户业务资料
	 */
	@Transient
	private MerchBusiInfo busiInfo;
	/**
	 * 商户法人资料
	 */
	@Transient
	private MerchLegalPers legalPers;
	
	/**
	 * 汇卡巡检记录
	 */
	@Transient
	private List<HcInspectLog> hcInspectLog;
	/**
	 * 安装记录
	 */
	@Transient
	private List<HcInstallLog> hcInstallLog;
	
	/**
	 * 汇卡交易记录
	 */
	@Transient
	private MerchTransReport transReport;
	
	/**
	 * 现场调查报告
	 */
	@Transient
	private MerchFieldSurvy fieldSurvy;
	
	@Transient
	private List<TFile> otherInfo;
	
	/**
	 * 商户经营数据核查情况 
	 */
	@Transient
	private List<TFile> busiVerifition;
	
	@Column (name = "pdf_filename", length = 200)
	private String pdfFileName;
	

	@Column (name = "status")
	private Integer status;

	public Long getIreport() {
		return ireport;
	}

	public void setIreport(Long ireport) {
		this.ireport = ireport;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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


	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public MerchBusiInfo getBusiInfo() {
		return busiInfo;
	}

	public void setBusiInfo(MerchBusiInfo busiInfo) {
		this.busiInfo = busiInfo;
	}

	public MerchLegalPers getLegalPers() {
		return legalPers;
	}

	public void setLegalPers(MerchLegalPers legalPers) {
		this.legalPers = legalPers;
	}

	public List<HcInstallLog> getHcInstallLog() {
		return hcInstallLog;
	}

	public void setHcInstallLog(List<HcInstallLog> hcInstallLog) {
		this.hcInstallLog = hcInstallLog;
	}

	public MerchTransReport getTransReport() {
		return transReport;
	}

	public void setTransReport(MerchTransReport transReport) {
		this.transReport = transReport;
	}

	public MerchFieldSurvy getFieldSurvy() {
		return fieldSurvy;
	}

	public void setFieldSurvy(MerchFieldSurvy fieldSurvy) {
		this.fieldSurvy = fieldSurvy;
	}

	public List<HcInspectLog> getHcInspectLog() {
		return hcInspectLog;
	}

	public void setHcInspectLog(List<HcInspectLog> hcInspectLog) {
		this.hcInspectLog = hcInspectLog;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public Set<ReportHtmlFile> getReportFile() {
		if (reportFile == null) return new HashSet<ReportHtmlFile>();
		return reportFile;
	}

	public void setReportFile(Set<ReportHtmlFile> reportFile) {
		this.reportFile = reportFile;
	}
	
	public ReportHtmlFile getHtmlFile(PagePosition position, HtmlFileType type){
		ReportHtmlFile forHtml = null;
		ReportHtmlFile forPdf = null;
		for (ReportHtmlFile file: this.getReportFile()){
			if (file.getPosition().ordinal() == position.ordinal()){
				if (file.getType() == HtmlFileType.FOR_HTML){
					forHtml = file;
				}else{
					forPdf = file;
				}
			}
		}
		if (type == HtmlFileType.FOR_HTML){
			return forHtml;
		}else{
			if (forPdf == null) {
				return forHtml;
			}else{
				return forPdf;
			}
		}
	}
	
	public void addHtmlFile(ReportHtmlFile file){
		if (this.reportFile == null){
			reportFile = new HashSet<ReportHtmlFile>();
		}
		reportFile.add(file);
	}

	public List<TFile> getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(List<TFile> otherInfo) {
		this.otherInfo = otherInfo;
	}

	public List<TFile> getBusiVerifition() {
		return busiVerifition;
	}

	public void setBusiVerifition(List<TFile> busiVerifition) {
		this.busiVerifition = busiVerifition;
	}
	

}
