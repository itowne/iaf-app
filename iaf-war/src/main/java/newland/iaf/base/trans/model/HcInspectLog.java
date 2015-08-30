package newland.iaf.base.trans.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import newland.iaf.base.format.BeanField;

import org.hibernate.annotations.Index;

/**
 * 汇卡巡检服务记录
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_hc_inspect_log")
@org.hibernate.annotations.Table(appliesTo = "t_hc_inspect_log", indexes = {
		@Index(name = "idx_hc_inspect_log1", columnNames = { "hc_merch_no" }),
		@Index(name = "idx_hc_inspect_log1", columnNames = { "hc_term_no" }) })
public class HcInspectLog {

	/**
	 * 
	 */
	@Id
	@TableGenerator(name = "i_hc_inspect_log", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_hc_inspect_log")
	@Column(name = "i_hc_inspect_log")
	private Long iinspectLog;

	@Column(name = "i_hc_trans")
	private Long ihcTrans;

	@Column(name = "serial_no")
	@BeanField(index = 0)
	private Long serialNo;

	@Column(name = "hc_merch_no", length = 45)
	@BeanField(index = 1)
	private String hcMerchNo;

	@Column(name = "hc_term_No", length = 45)
	@BeanField(index = 2)
	private String hcTermNo;

	@Column(name = "inspect_date")
	@BeanField(index = 3, pattern = "yyyy-MM-dd")
	private Date inspectDate;

	/**
	 * 巡检状况
	 */
	@Column(name = "inspect_stat", length = 45)
	@BeanField(index = 4)
	private String inspectStat;

	/**
	 * 巡检情况
	 */
	@Column(name = "inspect_case", length = 45)
	@BeanField(index = 5)
	private String inspectCase;

	/**
	 * 
	 */
	@Column(name = "memo", length = 255)
	@BeanField(index = 6)
	private String memo;

	/**
	 * 
	 */
	@Column(name = "inspector", length = 45)
	@BeanField(index = 7)
	private String inspector;

	public Long getIinspectLog() {
		return iinspectLog;
	}

	public void setIinspectLog(Long iinspectLog) {
		this.iinspectLog = iinspectLog;
	}

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	public String getHcMerchNo() {
		return hcMerchNo;
	}

	public void setHcMerchNo(String hcMerchNo) {
		this.hcMerchNo = hcMerchNo;
	}

	public String getHcTermNo() {
		return hcTermNo;
	}

	public void setHcTermNo(String hcTermNo) {
		this.hcTermNo = hcTermNo;
	}

	public Date getInspectDate() {
		return inspectDate;
	}

	public void setInspectDate(Date inspectDate) {
		this.inspectDate = inspectDate;
	}

	public String getInspectStat() {
		return inspectStat;
	}

	public void setInspectStat(String inspectStat) {
		this.inspectStat = inspectStat;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public Long getIhcTrans() {
		return ihcTrans;
	}

	public void setIhcTrans(Long ihcTrans) {
		this.ihcTrans = ihcTrans;
	}

	public String getInspectCase() {
		return inspectCase;
	}

	public void setInspectCase(String inspectCase) {
		this.inspectCase = inspectCase;
	}
}
