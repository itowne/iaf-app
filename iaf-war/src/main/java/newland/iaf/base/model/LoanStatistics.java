package newland.iaf.base.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import newland.iaf.base.model.dict.InstType;

import org.hibernate.annotations.Index;
/**
 * 贷款订单统计
 * @author new
 *
 */
@Entity
@Table (name = "t_loan_statistics",uniqueConstraints = { @UniqueConstraint(columnNames = {"i_inst", "inst_type"}) })
@org.hibernate.annotations.Table(appliesTo = "t_loan_statistics", indexes = { 
		@Index(name = "idx_loan_statistics1", columnNames = { "i_inst", "inst_type" }) 
		})
public class LoanStatistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name = "t_loan_statistics", initialValue = 100, allocationSize = 1000)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "t_loan_statistics")
	@Column (name = "i_loan_statis")
	private Long iloanStatis;
	/**
	 * 机构ID 或 商户Id
	 */
	
	@Column (name = "i_inst")
	private Long iinst;
	/**
	 * 机构类型
	 */
	@Column (name = "inst_type")
	@Enumerated
	private InstType instType;
	/**
	 * 订单数
	 */
	@Column (name = "loan_count")
	private Long loanCount = new Long(0);
	 /**
	  * 机构产品数
	  */
	@Column (name = "prod_count")
	private Long prodCount = new Long(0);
	/**
	 * 商户竟投数
	 */
	@Column (name = "mer_deb_count")
	private Long merchDebidBitCount = new Long(0);
	/**
	 * 当前还款中订单数量
	 */
	@Column (name = "cur_refunding_count")
	private Long curRefundingCount = new Long(0);
	/**
	 * 到期还款数
	 */
	@Column (name = "expire_refund")
	private Long expireRefund = new Long(0);
	/**
	 * 订单申请数
	 */
	@Column (name = "loan_apl_count")
	private Long loanApplyCount = new Long(0);
	/**
	 * 订单受理数
	 */
	@Column (name = "loan_acce_count")
	private Long loanAcceptCount = new Long(0);
	/**
	 * 订单撤销数
	 */
	@Column (name = "loan_cancel_count")
	private Long loanCancelCount = new Long(0);
	/**
	 * 当前待受理订单数
	 */
	@Column (name = "cur_acce_count")
	private Long curAcceptCount = new Long(0);
	/**
	 * 当前待撤销订单数
	 */
	@Column (name = "cur_cancel_count")
	private Long curCancelCount = new Long(0);
	/**
	 * 订单审核通过数
	 */
	@Column (name = "loan_aud_count")
	private Long loanAuditCount = new Long(0);
	
	/**
	 * 当前待审核订单数
	 */
	@Column (name = "cur_audit_count")
	private Long curAuditCount = new Long(0);
	/**
	 * 当前待放款数
	 */
	@Column (name = "cur_credit_count")
	private Long curCreditCount = new Long(0);
	/**
	 * 拒决受理单数
	 */
	@Column (name = "refuse_accept_count")
	private Long refuseAcceptCount = new Long(0);
	
	/**
	 * 拒决审核数
	 */
	@Column (name = "refuse_audit_count")
	private Long refuseAuditCount = new Long(0);
	
	/**
	 * 总贷款额
	 */
	@Column (name = "loan_amount")
	private BigDecimal loanAmount = BigDecimal.ZERO;
	
	/**
	 * 当前待放款总额
	 */
	@Column (name = "cur_credit_amount")
	private BigDecimal curCreditAmount = BigDecimal.ZERO;
	/**
	 * 当前待还款总额
	 */
	@Column (name = "cur_refund_amount")
	private BigDecimal curRefundAmount = BigDecimal.ZERO;
	
	/**
	 * 当期待还款总额
	 */
	@Column (name = "cur_period_ref_amount")
	private BigDecimal curPeriodRefundAmount = BigDecimal.ZERO;
	/**
	 * 当前已还款总款
	 */
	@Column (name = "refund_amount")
	private BigDecimal refundAmount = BigDecimal.ZERO;
	/**
	 * 当前欠款总金额
	 */
	@Column (name = "debt_amount")
	private BigDecimal debtAmount = BigDecimal.ZERO;
	/**
	 * 当前逾期总金额
	 */
	@Column (name = "overdue_amount")
	private BigDecimal overDueAmount = BigDecimal.ZERO;
	
	/**
	 * 逾 期笔数
	 */
	@Column (name = "overdue_count")
	private Long overDueCount = new Long(0);
	/**
	 * 当前冻结总额
	 */
	@Column (name = "freeze_amount")
	private BigDecimal freezeAmount = BigDecimal.ZERO;
	
	
	public Long getIinst() {
		return iinst;
	}
	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}
	public InstType getInstType() {
		return instType;
	}
	public void setInstType(InstType instType) {
		this.instType = instType;
	}
	public Long getLoanCount() {
		return loanCount;
	}
	public void setLoanCount(Long loanCount) {
		this.loanCount = loanCount;
	}
	public Long getProdCount() {
		return prodCount;
	}
	public void setProdCount(Long prodCount) {
		this.prodCount = prodCount;
	}
	public Long getMerchDebidBitCount() {
		return merchDebidBitCount;
	}
	public void setMerchDebidBitCount(Long merchDebidBitCount) {
		this.merchDebidBitCount = merchDebidBitCount;
	}
	public Long getExpireRefund() {
		return expireRefund;
	}
	public void setExpireRefund(Long expireRefund) {
		this.expireRefund = expireRefund;
	}
	public Long getLoanApplyCount() {
		return loanApplyCount;
	}
	public void setLoanApplyCount(Long loanApplyCount) {
		this.loanApplyCount = loanApplyCount;
	}
	public Long getLoanAcceptCount() {
		return loanAcceptCount;
	}
	public void setLoanAcceptCount(Long loanAcceptCount) {
		this.loanAcceptCount = loanAcceptCount;
	}
	public Long getLoanCancelCount() {
		return loanCancelCount;
	}
	public void setLoanCancelCount(Long loanCancelCount) {
		this.loanCancelCount = loanCancelCount;
	}
	public Long getLoanAuditCount() {
		return loanAuditCount;
	}
	public void setLoanAuditCount(Long loanAuditCount) {
		this.loanAuditCount = loanAuditCount;
	}
	public Long getRefuseAcceptCount() {
		return refuseAcceptCount;
	}
	public void setRefuseAcceptCount(Long refuseAcceptCount) {
		this.refuseAcceptCount = refuseAcceptCount;
	}
	public Long getRefuseAuditCount() {
		return refuseAuditCount;
	}
	public void setRefuseAuditCount(Long refuseAuditCount) {
		this.refuseAuditCount = refuseAuditCount;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public BigDecimal getDebtAmount() {
		return debtAmount;
	}
	public void setDebtAmount(BigDecimal debtAmount) {
		this.debtAmount = debtAmount;
	}
	public BigDecimal getOverDueAmount() {
		return overDueAmount;
	}
	public void setOverDueAmount(BigDecimal overDueAmount) {
		this.overDueAmount = overDueAmount;
	}
	public Long getCurAcceptCount() {
		return curAcceptCount;
	}
	public void setCurAcceptCount(Long curAcceptCount) {
		this.curAcceptCount = curAcceptCount;
	}
	public Long getCurCancelCount() {
		return curCancelCount;
	}
	public void setCurCancelCount(Long curCancelCount) {
		this.curCancelCount = curCancelCount;
	}
	public Long getCurAuditCount() {
		return curAuditCount;
	}
	public void setCurAuditCount(Long curAuditCount) {
		this.curAuditCount = curAuditCount;
	}
	public Long getCurCreditCount() {
		return curCreditCount;
	}
	public void setCurCreditCount(Long curCreditCount) {
		this.curCreditCount = curCreditCount;
	}
	public BigDecimal getCurCreditAmount() {
		return curCreditAmount;
	}
	public void setCurCreditAmount(BigDecimal curCreditAmount) {
		this.curCreditAmount = curCreditAmount;
	}
	public BigDecimal getCurRefundAmount() {
		return curRefundAmount;
	}
	public void setCurRefundAmount(BigDecimal curRefundAmount) {
		this.curRefundAmount = curRefundAmount;
	}
	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}
	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public Long getIloanStatis() {
		return iloanStatis;
	}
	public void setIloanStatis(Long iloanStatis) {
		this.iloanStatis = iloanStatis;
	}
	public BigDecimal getCurPeriodRefundAmount() {
		return curPeriodRefundAmount;
	}
	public void setCurPeriodRefundAmount(BigDecimal curPeriodRefundAmount) {
		this.curPeriodRefundAmount = curPeriodRefundAmount;
	}
	public Long getCurRefundingCount() {
		return curRefundingCount;
	}
	public void setCurRefundingCount(Long curRefundingCount) {
		this.curRefundingCount = curRefundingCount;
	}
	public Long getOverDueCount() {
		return overDueCount;
	}
	public void setOverDueCount(Long overDueCount) {
		this.overDueCount = overDueCount;
	}


}
