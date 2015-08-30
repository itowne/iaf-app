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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.GateWayType;

import org.hibernate.annotations.Index;
@Entity
@Table(name = "t_fund_flow", uniqueConstraints = { @UniqueConstraint(columnNames = "trace_no") })
@org.hibernate.annotations.Table(appliesTo = "t_fund_flow", indexes = {
		@Index(name = "idx_fund_flow1", columnNames = { "oth_sys_no" }),
		@Index(name = "idx_fund_flow2", columnNames = { "type", "status" })
})
public class FundFlow implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2611974256279128560L;
	@Id
	@TableGenerator(name = "i_fund_flow", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_fund_flow")
	@Column(name = "i_fund_flow")
	private Long ifundFlow;
	/**
	 * 流水号
	 */
	@Column (name = "trace_no", length = 16, nullable = false)
	private String traceNo;
	/**
	 * 机构代号
	 */
	@Column (name = "i_inst", nullable = false)
	private Long iinst;
	/**
	 * 机构代码
	 */
	@Column (name = "inst_id", length = 20)
	private String instId;
	/**
	 * 机构名称
	 */
	@Column (name = "inst_name", length = 100)
	private String instName;

	/**
	 * 商户代号
	 */
	@Column (name = "i_merch", nullable = false)
	private Long imerch;
	/**
	 * 商户代码
	 */
	@Column (name = "merch_id", length = 20)
	private String merchId;
	/**
	 * 商户名称
	 */
	@Column (name = "merch_name", length = 100)
	private String merchName;

	/**
	 * 资金类型
	 */
	@Enumerated
	@Column (name = "type", nullable = false)
	private FundFlowType type;
	/**
	 *  订单代号
	 */
	@Column (name = "i_loan_ord", nullable = false)
	private Long iloanOrd;
	/**
	 * 订单代码
	 */
	@Column (name = "loan_ord_id", length = 12, nullable = false)
	private String loanOrdId;
	/**
	 * 计划代号
	 */
	@Column (name = "i_loanord_plan")
	private Long iloanOrdPlan;
	/**
	 * 计划期号
	 */
	@Column (name = "period")
	private Integer period;
	/**
	 * 交易金额
	 */
	@Column (name = "amount", nullable = false)
	private BigDecimal amount;
	/**
	 * 总还款额
	 */
	@Column (name = "capital", nullable = false)
	private BigDecimal capital;
	@Transient
	private BigDecimal wanyuanCapital;
	/**
	 * 对方系统流水号
	 */
	@Column (name = "oth_sys_no", length = 32)
	private String otherSysTraceNo;
	/**
	 * 生成日期
	 */
	@Column (name = "gen_time", nullable = false)
	private Date genTime;
	/**
	 * 更新日期
	 */
	@Column (name = "upd_time", nullable = false)
	private Date updTime;
	/**
	 * 备注
	 */
	@Column (name = "memo", length = 50)
	private String memo;
	/**
	 * 对账备注
	 */
	@Column(name="check_Memo")
	private String checkMemo;
	/**
	 * 流水状态
	 */
	@Enumerated
	@Column (name = "status", nullable = false)
	private FundFlowStat status;
	
	@Transient
	private BigDecimal wanyuanAmount;
	
	/**
	 * 版本号
	 */
	@Version
	@Column (name = "version")
	private Integer version;
	
	@Enumerated
	@Column (name = "trans_type")
	private GateWayType transType;
	
	@Column (name = "recive_acct_no", length = 50)
	private String reciveAcctNo;
	
	@Column (name = "recive_acct_name", length = 100)
	private String reciveAcctName;
	
	@Column (name = "recive_bank_name", length = 200)
	private String reiciveBankName;
	
	@Column (name = "recive_bank_code", length = 20)
	private String reciveBankCode;
	
	public Long getIfundFlow() {
		return ifundFlow;
	}
	public void setIfundFlow(Long ifundFlow) {
		this.ifundFlow = ifundFlow;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public Long getIinst() {
		return iinst;
	}
	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public Long getImerch() {
		return imerch;
	}
	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}
	public String getMerchId() {
		return merchId;
	}
	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}
	public FundFlowType getType() {
		return type;
	}
	public void setType(FundFlowType type) {
		this.type = type;
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
	public Long getIloanOrdPlan() {
		return iloanOrdPlan;
	}
	public void setIloanOrdPlan(Long iloanOrdPlan) {
		this.iloanOrdPlan = iloanOrdPlan;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getOtherSysTraceNo() {
		return otherSysTraceNo;
	}
	public void setOtherSysTraceNo(String otherSysTraceNo) {
		this.otherSysTraceNo = otherSysTraceNo;
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
	public FundFlowStat getStatus() {
		return status;
	}
	public void setStatus(FundFlowStat stat) {
		this.status = stat;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public BigDecimal getCapital() {
		return capital;
	}
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public GateWayType getTransType() {
		return transType;
	}
	public void setTransType(GateWayType transType) {
		this.transType = transType;
	}
	public String getReciveAcctNo() {
		return reciveAcctNo;
	}
	public void setReciveAcctNo(String reciveAcctNo) {
		this.reciveAcctNo = reciveAcctNo;
	}
	public String getReciveAcctName() {
		return reciveAcctName;
	}
	public void setReciveAcctName(String reciveAcctName) {
		this.reciveAcctName = reciveAcctName;
	}
	public String getReiciveBankName() {
		return reiciveBankName;
	}
	public void setReiciveBankName(String reiciveBankName) {
		this.reiciveBankName = reiciveBankName;
	}
	public String getReciveBankCode() {
		return reciveBankCode;
	}
	public void setReciveBankCode(String reciveBankCode) {
		this.reciveBankCode = reciveBankCode;
	}
	public BigDecimal getWanyuanAmount() {
		return amount.divide(new BigDecimal(10000),1,BigDecimal.ROUND_HALF_UP).setScale(1);
	}
	public String getCheckMemo() {
		return checkMemo;
	}
	public void setCheckMemo(String checkMemo) {
		this.checkMemo = checkMemo;
	}
	public BigDecimal getWanyuanCapital() {
		return capital.divide(new BigDecimal(10000),1,BigDecimal.ROUND_UNNECESSARY).setScale(1);
	}
	
}
