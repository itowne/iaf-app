package newland.iaf.base.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 冻结结果
 * @author new
 *
 */
public class FreezeResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String periods;
	
	private BigDecimal amount;

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

}
