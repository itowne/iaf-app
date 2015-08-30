package newland.iaf.base.trans.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 短信日志
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_sms_log")
public class SmsLog {

	/**
	 * 
	 */
	@Id
	@TableGenerator(name = "i_sms_log", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_sms_log")
	@Column(name = "i_sms_log", updatable = false)
	private Long smsLog;

	/**
	 * 
	 */
	@Column(name = "phone", nullable = false, updatable = false, length = 50)
	private String phone;

	/**
	 * 
	 */
	@Column(name = "msg", updatable = false, length = 100)
	private String msg;

	/**
	 * 
	 */
	@Column(name = "msg_type", nullable = false, length = 30)
	private SmsMsgType msgType;

	/**
	 * 
	 */
	@Column(name = "src_i_inst", updatable = false)
	private Long srcIinst;

	/**
	 * 
	 */
	@Column(name = "src_i_inst_usr", updatable = false)
	private Long srcIinstUsr;

	/**
	 * 
	 */
	@Column(name = "src_i_merch", updatable = false)
	private Long srcImerch;

	/**
	 * 
	 */
	@Column(name = "src_i_merch_usr", updatable = false)
	private Long srcImerchUsr;

	/**
	 * 
	 */
	@Column(name = "src_i_bs_user", updatable = false)
	private Long srcIbsUser;

	/**
	 * 
	 */
	@Column(name = "targ_i_inst", updatable = false)
	private Long targIinst;

	/**
	 * 
	 */
	@Column(name = "targ_i_inst_usr", updatable = false)
	private Long targIinstUsr;

	/**
	 * 
	 */
	@Column(name = "targ_i_merch", updatable = false)
	private Long targImerch;

	/**
	 * 
	 */
	@Column(name = "targ_i_merch_usr", updatable = false)
	private Long targImerchUsr;

	/**
	 * 
	 */
	@Column(name = "targ_i_bs_user", updatable = false)
	private Long targIbsUser;

	/**
	 * 
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;

	/**
	 * 
	 */
	@Column(name = "status", length = 20)
	private String status;

	@Column(name = "upd_time", nullable = false)
	private Date updTime;

	@Column(name = "sms_url")
	private String smsUrl;

	public Long getSmsLog() {
		return smsLog;
	}

	public void setSmsLog(Long smsLog) {
		this.smsLog = smsLog;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SmsMsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(SmsMsgType msgType) {
		this.msgType = msgType;
	}

	public Long getSrcIinst() {
		return srcIinst;
	}

	public void setSrcIinst(Long srcIinst) {
		this.srcIinst = srcIinst;
	}

	public Long getSrcIinstUsr() {
		return srcIinstUsr;
	}

	public void setSrcIinstUsr(Long srcIinstUsr) {
		this.srcIinstUsr = srcIinstUsr;
	}

	public Long getSrcImerch() {
		return srcImerch;
	}

	public void setSrcImerch(Long srcImerch) {
		this.srcImerch = srcImerch;
	}

	public Long getSrcImerchUsr() {
		return srcImerchUsr;
	}

	public void setSrcImerchUsr(Long srcImerchUsr) {
		this.srcImerchUsr = srcImerchUsr;
	}

	public Long getSrcIbsUser() {
		return srcIbsUser;
	}

	public void setSrcIbsUser(Long srcIbsUser) {
		this.srcIbsUser = srcIbsUser;
	}

	public Long getTargIinst() {
		return targIinst;
	}

	public void setTargIinst(Long targIinst) {
		this.targIinst = targIinst;
	}

	public Long getTargIinstUsr() {
		return targIinstUsr;
	}

	public void setTargIinstUsr(Long targIinstUsr) {
		this.targIinstUsr = targIinstUsr;
	}

	public Long getTargImerch() {
		return targImerch;
	}

	public void setTargImerch(Long targImerch) {
		this.targImerch = targImerch;
	}

	public Long getTargImerchUsr() {
		return targImerchUsr;
	}

	public void setTargImerchUsr(Long targImerchUsr) {
		this.targImerchUsr = targImerchUsr;
	}

	public Long getTargIbsUser() {
		return targIbsUser;
	}

	public void setTargIbsUser(Long targIbsUser) {
		this.targIbsUser = targIbsUser;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getSmsUrl() {
		return smsUrl;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
