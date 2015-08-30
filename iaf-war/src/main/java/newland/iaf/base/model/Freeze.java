package newland.iaf.base.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.ApplyType;

public class Freeze {
		
	private Long iinst;
	/**
	 * 商户代号
	 */
	private Long imerch;
	private String instName;
	private String merchName;

	private Long iloanOrd;
	
	private String loanOrdId;

	private BigDecimal amount;

	private ApplyType applyType;

	private String merchNo;
	
	private String instNo;
	
	private Date acceptDate; 
	
	private Date genTime;
	
	private ApplyStat stat;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public ApplyType getApplyType() {
		return applyType;
	}

	public void setApplyType(ApplyType applyType) {
		this.applyType = applyType;
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

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public ApplyStat getStat() {
		return stat;
	}

	public void setStat(ApplyStat stat) {
		this.stat = stat;
	}
	
	

}
