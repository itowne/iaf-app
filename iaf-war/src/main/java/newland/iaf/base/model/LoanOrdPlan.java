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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Index;

/**
 * 
 * @author new
 * 
 */
@Entity
@Table(name = "t_loanord_plan")
@org.hibernate.annotations.Table(appliesTo = "t_loanord_plan", indexes = { @Index(name = "idx_loanord_plan1", columnNames = { "i_loan_ord" }) })
public class LoanOrdPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 计划内部编号
	 */
	@Id
	@TableGenerator(name = "i_loanord_plan", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_loanord_plan")
	@Column(name = "i_loanord_plan")
	private Long iloanOrdPlan;

	/**
	 * 商户ID
	 */
	@Column(name = "i_merch")
	private Long imerch;

	/**
	 * 机构ID
	 */
	@Column(name = "i_inst")
	private Long iinst;

	/**
	 * 订单编号
	 */
	@Column(name = "i_loan_ord", nullable = false)
	private Long iloanOrd;

	/**
	 * 
	 */
	@Column(name = "loan_ord_id", nullable = false)
	private String loanOrdId;

	/**
	 * 还款期号
	 */
	@Column(name = "period", nullable = false)
	private Integer period;
	/**
	 * 还款日期
	 */
	@Column(name = "refund_date", nullable = false)
	private Date refundDate;
	/**
	 * 还款金额
	 */
	@Column(name = "repayment", nullable = false)
	private BigDecimal repayment;
	/**
	 * 本金
	 */
	@Column(name = "capital")
	private BigDecimal capital;
	/**
	 * 利息
	 */
	@Column(name = "interest")
	private BigDecimal interest;
	/**
	 * 剩余还款额
	 */
	@Column(name = "remain_amount")
	private BigDecimal remainAmount;
	/**
	 * 生成日期
	 */
	@Column(name = "gen_time", nullable = false)
	private Date genTime;
	/**
	 * 修改日期
	 */
	@Column(name = "upd_time", nullable = false)
	private Date updTime;
	/**
	 * 计划状态
	 */
	@Enumerated
	@Column(name = "plan_stat", nullable = false)
	private PlanStat stat;
	/**
	 * 前一状态
	 */
	@Enumerated
	@Column(name = "pre_stat", nullable = false)
	private PlanStat preStat;
	/**
	 * 备注
	 */
	@Column(name = "memo", length = 100)
	private String memo;
	
	/**
	 * 1：冻结已上传，2:冻结未上传，3：解冻已上传，4:解冻未上传
	 */
	@Column(name = "is_upload", length = 100)
	private String isUplod;

	@Version
	@Column(name = "version")
	private Long version;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getIlanOrdPlan() {
		return iloanOrdPlan;
	}

	public Long getIloanOrd() {
		return iloanOrd;
	}

	public Integer getPeriod() {
		return period;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public BigDecimal getRepayment() {
		return repayment;
	}

	public PlanStat getStat() {
		return stat;
	}

	public void setIloanOrdPlan(Long iloanOrdPlan) {
		this.iloanOrdPlan = iloanOrdPlan;
	}

	public void setIloanOrd(Long iLoanOrd) {
		this.iloanOrd = iLoanOrd;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public void setRepayment(BigDecimal repayment) {
		this.repayment = repayment;
	}

	public void setStat(PlanStat stat) {
		this.stat = stat;
	}

	public static enum PlanStat {

		/**
		 * 初始状态欠款
		 */
		BALANCE("欠款中"),
		/**
		 * 过期未还款
		 */
		DELIN_QUENCY("逾期"),
		/**
		 * 申请冻结
		 */
		FREEZING("欠款冻结中"),
		/**
		 * 冻结成功
		 */
		BALANCE_FREEZE("欠款已冻结"),
		/**
		 * 解冻中
		 */
		THAW_APPLY("解冻中"),
		/**
		 * 已还款
		 */
		PAID_UP_LOAN("已还款");

		String desc;

		PlanStat(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	public Date getGenTime() {
		return genTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getLoanOrdId() {
		return loanOrdId;
	}

	public void setLoanOrdId(String loanOrdId) {
		this.loanOrdId = loanOrdId;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Long getIloanOrdPlan() {
		return iloanOrdPlan;
	}

	public BigDecimal getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(BigDecimal remainAmount) {
		this.remainAmount = remainAmount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public PlanStat getPreStat() {
		return preStat;
	}

	public void setPreStat(PlanStat preStat) {
		this.preStat = preStat;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	@Transient
	public String getSaveFlag() {
		if (this.iloanOrdPlan == null) {
			return "未保存";
		} else {
			return "已保存";
		}
	}

	public String getIsUplod() {
		return isUplod;
	}

	public void setIsUplod(String isUplod) {
		this.isUplod = isUplod;
	}

}
