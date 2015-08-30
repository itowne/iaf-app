package newland.iaf.merch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import newland.iaf.base.model.dict.PagePosition;

@Entity
@Table(name = "t_rpt_file")
public class ReportHtmlFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name = "i_rpt_file", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_rpt_file")
	@Column(name = "i_rpt_file")
	private Long irptFile;

	@ManyToOne(targetEntity = LoanCreditReport.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "i_report")
	private LoanCreditReport report;

	@Column(name = "file_path", length = 200)
	private String filePath;
	/**
	 * 
	 */
	@Enumerated
	@Column(name = "file_type")
	private HtmlFileType type;

	@Column(name = "gen_time")
	private Date genTime;

	@Enumerated
	@Column(name = "position")
	private PagePosition position;

	public Long getIrptFile() {
		return irptFile;
	}

	public void setIrptFile(Long irptFile) {
		this.irptFile = irptFile;
	}

	public LoanCreditReport getReport() {
		return report;
	}

	public void setReport(LoanCreditReport report) {
		this.report = report;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public PagePosition getPosition() {
		return position;
	}

	public void setPosition(PagePosition position) {
		this.position = position;
	}

	public HtmlFileType getType() {
		return type;
	}

	public void setType(HtmlFileType type) {
		this.type = type;
	}
}
