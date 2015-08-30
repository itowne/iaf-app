package newland.iaf.base.trans.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 交易流水每日汇总信息
 * 
 * @author rabbit
 * 
 */
@Table(name = "t_hc_trans_log_mouth", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"mouth", "merch_no", "trans_type" }) })
public class HcTransLogMouth {

	@EmbeddedId
	private HcTransLogMouthPK pk;

	/**
	 * 数量
	 */
	@Column(name = "trans_num")
	private Long transNum;

	/**
	 * 总交易金额
	 */
	@Column(name = "amt")
	private BigDecimal amt;

	/**
	 * 手续费
	 */
	@Column(name = "charge")
	private BigDecimal charge;

	public Long getTransNum() {
		return transNum;
	}

	public void setTransNum(Long transNum) {
		this.transNum = transNum;
	}

	public HcTransLogMouthPK getPk() {
		return pk;
	}

	public void setPk(HcTransLogMouthPK pk) {
		this.pk = pk;
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

}
