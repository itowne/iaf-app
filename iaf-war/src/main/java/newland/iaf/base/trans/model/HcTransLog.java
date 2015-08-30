package newland.iaf.base.trans.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import newland.iaf.base.format.BeanField;
import newland.iaf.base.format.BeanFieldType;

import org.hibernate.annotations.Index;

/**
 * 
 * @author rabbit
 * 
 */
@Entity
@Table(name = "t_hc_trans_log")
@org.hibernate.annotations.Table(appliesTo = "t_hc_trans_log", indexes = {
		@Index(name = "idx_hc_trans_log1", columnNames = { "settle_date" }),
		@Index(name = "idx_hc_trans_log2", columnNames = { "trans_date" }),
		@Index(name = "idx_hc_trans_log3", columnNames = { "merch_no" }),
		@Index(name = "idx_hc_trans_log4", columnNames = { "trans_type" }) })
public class HcTransLog {
	@Id
	@TableGenerator(name = "i_hc_trans_log", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_hc_trans_log")
	@Column(name = "i_hc_trans_log")
	private Long ihcTransLog;

	@Column(name = "i_hc_trans", nullable = false)
	private Long ihcTrans;

	/**
	 * 汇卡交易流水
	 */
	@BeanField(index = 0)
	@Column(name = "hicard_no")
	private Long hicardNo;

	/**
	 * 订单编号
	 */
	@BeanField(index = 1)
	@Column(name = "order_no", length = 20)
	private String orderNo;

	/**
	 * 商户号
	 */
	@BeanField(index = 2)
	@Column(name = "merch_no", length = 15)
	private String merchNo;

	/**
	 * 交易金额
	 */
	@BeanField(index = 3)
	@Column(name = "amt")
	private BigDecimal amt;

	/**
	 * 手续费
	 */
	@BeanField(index = 4)
	@Column(name = "charge")
	private BigDecimal charge;

	/**
	 * 清算日期
	 */
	@BeanField(index = 5, type = BeanFieldType.date)
	@Column(name = "settle_date")
	private Date settleDate;

	/**
	 * 交易时间
	 */
	@BeanField(index = 6, type = BeanFieldType.date)
	@Column(name = "trans_date")
	private Date transDate;

	/**
	 * 
	 */
	@BeanField(index = 7)
	@Column(name = "trans_time")
	private String transTime;

	/**
	 * 是否金色快车
	 */
	@BeanField(index = 8)
	@Column(name = "golden_express_flag", length = 1)
	private String goldenExpressFlag;

	/**
	 * 
	 */
	@BeanField(index = 9)
	@Column(name = "trans_type", length = 1)
	private String transType;

	/**
	 * 境外卡标识
	 */
	@BeanField(index = 10)
	@Column(name = "abroad_card_flag", length = 1)
	private String abroadCardFlag;

	public Long getHicardNo() {
		return hicardNo;
	}

	public void setHicardNo(Long hicardNo) {
		this.hicardNo = hicardNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Long getIhcTrans() {
		return ihcTrans;
	}

	public void setIhcTrans(Long ihcTrans) {
		this.ihcTrans = ihcTrans;
	}

	public Long getIhcTransLog() {
		return ihcTransLog;
	}

	public void setIhcTransLog(Long ihcTransLog) {
		this.ihcTransLog = ihcTransLog;
	}

	public String getGoldenExpressFlag() {
		return goldenExpressFlag;
	}

	public void setGoldenExpressFlag(String goldenExpressFlag) {
		this.goldenExpressFlag = goldenExpressFlag;
	}

	public String getAbroadCardFlag() {
		return abroadCardFlag;
	}

	public void setAbroadCardFlag(String abroadCardFlag) {
		this.abroadCardFlag = abroadCardFlag;
	}

}
