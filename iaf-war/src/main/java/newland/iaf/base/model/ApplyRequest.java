package newland.iaf.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.ApplyType;
/**
 * 申请单
 * @author new
 *
 */
@Entity
@Table (name = "t_apply_req")
public class ApplyRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4953790941049978900L;
	@Id
	@Column (name = "i_apply_req")
	@TableGenerator(name = "i_apply_req", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_apply_req")
	private Long iapplyReq;
	
	@Column (name = "batch_id", length = 16, nullable = false)
	private String batchId;
	/**
	 * 机构代号
	 */
	@Column (name = "i_inst")
	private Long iinst;
	/**
	 * 商户代号
	 */
	@Column (name = "i_merch")
	private Long imerch;
	@Column (name = "inst_name", length  = 100)
	private String instName;
	@Column (name = "merch_name", length = 100)
	private String merchName;
	/**
	 * 订单代号
	 */
	@Column (name = "i_loan_ord")
	private Long iloanOrd;
	/**
	 * 订单编号
	 */
	@Column (name = "loan_ord_id", length = 12)
	private String loanOrdId;
	/**
	 * 期号
	 */
	@Column (name = "periods", length = 20)
	private String periods;
	/**
	 * 金额
	 */
	@Column (name = "amount")
	private BigDecimal amount;
	/**
	 * 申请单类型
	 */
	@Enumerated
	@Column (name = "apply_type")
	private ApplyType applyType;
	/**
	 * 申请原因
	 */
	@Column (name = "reason", length = 100)
	private String reason;
	
	
	
	@Column (name = "freezeAcceptMemo", length = 100)
	private String freezeAcceptMemo;
	
	@Column (name = "freezeAcceptTime", length = 100)
	private Date freezeAcceptTime;
	
	@Column (name = "freezeSuccessMemo", length = 100)
	private String freezeSuccessMemo;
	
	@Column (name = "freezeSuccessTime", length = 100)
	private Date freezeSuccessTime;
	
	@Column (name = "unFreezeApplyTime", length = 100)
	private Date unFreezeApplyTime;
	
	@Column (name = "unFreezeApplyMemo", length = 100)
	private String unFreezeApplyMemo;
	
	@Column (name = "unFreezeAcceptMemo", length = 100)
	private String unFreezeAcceptMemo;
	
	@Column (name = "unFreezeAcceptTime", length = 100)
	private Date unFreezeAcceptTime;
	
	@Column (name = "unFreezeSuccessMemo", length = 100)
	private String unFreezeSuccessMemo;
	
	@Column (name = "unFreezeSuccessTime", length = 100)
	private Date unFreezeSuccessTime;
	
	@Column (name = "i_file", length = 100)
	private Long iFile;
	/**
	 * 处理结果
	 */
	@Column (name = "result", length = 200)
	private String result;

	@Column (name = "merch_No", length = 200)
	private String merchNo;
	
	@Column(name="inst_No")
	private String instNo;
	
	@Column(name="accept_date")
	private Date acceptDate; 
	
	/**
	 * 处理状态
	 */
	@Enumerated
	@Column (name = "status")
	private ApplyStat stat;
	@Column (name = "gen_time")
	private Date genTime;
	@Column (name = "upd_time")
	private Date updTime;
	
	@Version
	@Column (name = "version")
	private Integer version;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getIapplyReq() {
		return iapplyReq;
	}
	
	public void setIapplyReq(Long iapplyReq) {
		this.iapplyReq = iapplyReq;
	}
	public Long getIinst() {
		return iinst;
	}
	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}
	public Long getImerch() {
		return imerch;
	}
	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}
	public Long getIloanOrd() {
		return iloanOrd;
	}
	public void setIloanOrd(Long iloanOrd) {
		this.iloanOrd = iloanOrd;
	}
	public String getLoanOrdId() {
		return loanOrdId;
	}
	public void setLoanOrdId(String loanOrdId) {
		this.loanOrdId = loanOrdId;
	}
	public ApplyType getApplyType() {
		return applyType;
	}
	public void setApplyType(ApplyType applyType) {
		this.applyType = applyType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public ApplyStat getStat() {
		return stat;
	}
	public void setStat(ApplyStat stat) {
		this.stat = stat;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public String getInstNo() {
		return instNo;
	}

	public void setInstNo(String instNo) {
		this.instNo = instNo;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getFreezeAcceptMemo() {
		return freezeAcceptMemo;
	}

	public void setFreezeAcceptMemo(String freezeAcceptMemo) {
		this.freezeAcceptMemo = freezeAcceptMemo;
	}

	public Date getFreezeAcceptTime() {
		return freezeAcceptTime;
	}

	public void setFreezeAcceptTime(Date freezeAcceptTime) {
		this.freezeAcceptTime = freezeAcceptTime;
	}

	public Date getUnFreezeApplyTime() {
		return unFreezeApplyTime;
	}

	public void setUnFreezeApplyTime(Date unFreezeApplyTime) {
		this.unFreezeApplyTime = unFreezeApplyTime;
	}

	public String getUnFreezeApplyMemo() {
		return unFreezeApplyMemo;
	}

	public void setUnFreezeApplyMemo(String unFreezeApplyMemo) {
		this.unFreezeApplyMemo = unFreezeApplyMemo;
	}

	public String getUnFreezeAcceptMemo() {
		return unFreezeAcceptMemo;
	}

	public void setUnFreezeAcceptMemo(String unFreezeAcceptMemo) {
		this.unFreezeAcceptMemo = unFreezeAcceptMemo;
	}

	public Date getUnFreezeAcceptTime() {
		return unFreezeAcceptTime;
	}

	public void setUnFreezeAcceptTime(Date unFreezeAcceptTime) {
		this.unFreezeAcceptTime = unFreezeAcceptTime;
	}

	public String getUnFreezeSuccessMemo() {
		return unFreezeSuccessMemo;
	}

	public void setUnFreezeSuccessMemo(String unFreezeSuccessMemo) {
		this.unFreezeSuccessMemo = unFreezeSuccessMemo;
	}

	public Date getUnFreezeSuccessTime() {
		return unFreezeSuccessTime;
	}

	public void setUnFreezeSuccessTime(Date unFreezeSuccessTime) {
		this.unFreezeSuccessTime = unFreezeSuccessTime;
	}

	public String getFreezeSuccessMemo() {
		return freezeSuccessMemo;
	}

	public void setFreezeSuccessMemo(String freezeSuccessMemo) {
		this.freezeSuccessMemo = freezeSuccessMemo;
	}

	public Date getFreezeSuccessTime() {
		return freezeSuccessTime;
	}

	public void setFreezeSuccessTime(Date freezeSuccessTime) {
		this.freezeSuccessTime = freezeSuccessTime;
	}

	public Long getiFile() {
		return iFile;
	}

	public void setiFile(Long iFile) {
		this.iFile = iFile;
	}

}
