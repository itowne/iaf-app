package newland.iaf.merch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Index;

/**
 * t_merch_audithistory商户审核历史记录表
 * @author wwa
 *
 */
@Entity
@Table(name = "t_merch_audithistory")
@org.hibernate.annotations.Table(appliesTo = "t_merch_audithistory", indexes = {
		@Index(name = "idx_merch_audithistory1", columnNames = { "update_time" }),
		@Index(name = "idx_merch_audithistory2", columnNames = { "update_user" }) })
public class MerchAuditHistory implements Serializable{

	private static final long serialVersionUID = -1469807127957098600L;
	/**
	 * id
	 */
	@Id
	@TableGenerator(name = "i_merch_audithistory", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_merch_audithistory")
	@Column(name = "i_merch_audithistory", updatable = false)
	private Long iMerchAuditHistory;
	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	/**
	 * 修改内容
	 */
	@Column(name = "update_content")
	private String updateContent;
	/**
	 * 修改人
	 */
	@Column(name = "update_user")
	private String updateUser;
	/**
	 * 核实情况
	 */
	@Column(name = "audit_status")
	private String auditStatus;

	public Long getiMerchAuditHistory() {
		return iMerchAuditHistory;
	}
	public void setiMerchAuditHistory(Long iMerchAuditHistory) {
		this.iMerchAuditHistory = iMerchAuditHistory;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateContent() {
		return updateContent;
	}
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
}
