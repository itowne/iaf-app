package newland.iaf.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Index;

public class TransReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 交易金额
	 */
	private BigDecimal amount = BigDecimal.ZERO;

	/**
	 * 交易笔数
	 */
	private Long transCount = new Long(0);

	/**
	 * 日均值
	 */
	private Double dailyAvgAmount = new Double(0);

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getTransCount() {
		return transCount;
	}

	public void setTransCount(Long transCount) {
		this.transCount = transCount;
	}

	public Double getDailyAvgAmount() {
		return dailyAvgAmount;
	}

	public void setDailyAvgAmount(Double dailyAvgAmount) {
		this.dailyAvgAmount = dailyAvgAmount;
	}


}
