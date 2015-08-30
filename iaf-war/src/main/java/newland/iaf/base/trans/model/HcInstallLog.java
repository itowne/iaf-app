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
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_hc_install_log")
@org.hibernate.annotations.Table(appliesTo = "t_hc_install_log", indexes = {
		@Index(name = "idx_hc_install_log1", columnNames = { "hc_merch_no" }),
		@Index(name = "idx_hc_install_log4", columnNames = { "term_stat" }) })
public class HcInstallLog {
	@Id
	@TableGenerator(name = "i_hc_install_log", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_hc_install_log")
	@Column(name = "i_hc_install_log")
	private Long ihcInstallLog;

	@Column(name = "i_hc_trans")
	private Long ihcTrans;
	/**
	 * 
	 */
	@Column(name = "serial_no")
	@BeanField(index = 0)
	private Long serialNo;

	@BeanField(index = 1)
	@Column(name = "hc_merch_no", length = 45)
	private String hcMerchNo;

	@BeanField(index = 2)
	@Column(name = "hc_term_no", length = 45)
	private String hcTermNo;

	@BeanField(index = 3)
	@Column(name = "term_stat", length = 45)
	private String termStat;

	@BeanField(index = 4)
	@Column(name = "install_addr", length = 255)
	private String installAddr;

	@BeanField(index = 5, pattern = "yyyy-MM-dd")
	@Column(name = "uninstall_date")
	private Date uninstallDate;

	@BeanField(index = 6)
	@Column(name = "uninstall_reason", length = 200)
	private String uninstallReason;

	@BeanField(index = 7)
	@Column(name = "uninstall_oper", length = 45)
	private String uninstallOper;

	@BeanField(index = 8, pattern = "yyyy-MM-dd")
	@Column(name = "install_date")
	private Date installDate;

	@BeanField(index = 9)
	@Column(name = "install_oper", length = 45)
	private String installOper;

	public Long getIhcInstallLog() {
		return ihcInstallLog;
	}

	public void setIhcInstallLog(Long ihcInstallLog) {
		this.ihcInstallLog = ihcInstallLog;
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

	public String getTermStat() {
		return termStat;
	}

	public void setTermStat(String termStat) {
		this.termStat = termStat;
	}

	public String getInstallAddr() {
		return installAddr;
	}

	public void setInstallAddr(String installAddr) {
		this.installAddr = installAddr;
	}

	public Date getUninstallDate() {
		return uninstallDate;
	}

	public void setUninstallDate(Date uninstallDate) {
		this.uninstallDate = uninstallDate;
	}

	public String getUninstallReason() {
		return uninstallReason;
	}

	public void setUninstallReason(String uninstallReason) {
		this.uninstallReason = uninstallReason;
	}

	public String getUninstallOper() {
		return uninstallOper;
	}

	public void setUninstallOper(String uninstallOper) {
		this.uninstallOper = uninstallOper;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public String getInstallOper() {
		return installOper;
	}

	public void setInstallOper(String installOper) {
		this.installOper = installOper;
	}

	public Long getIhcTrans() {
		return ihcTrans;
	}

	public void setIhcTrans(Long ihcTrans) {
		this.ihcTrans = ihcTrans;
	}
	
	public boolean isInstallLog(){
		if (this.installDate != null) return true;
		return false;
	}

}
