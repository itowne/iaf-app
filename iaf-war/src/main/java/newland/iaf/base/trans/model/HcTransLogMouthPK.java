package newland.iaf.base.trans.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author rabbit
 * 
 */
@Embeddable
public class HcTransLogMouthPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3388458040077370593L;

	/**
	 * 主键
	 */
	@Column(name = "mouth", columnDefinition = "char(6)")
	private String mouth;

	/**
	 * 汇卡商户号(金掌柜)
	 */
	@Column(name = "merch_no", length = 30)
	private String merchNo;

	@Override
	public int hashCode() {
		String s = "" + mouth + merchNo + transType;
		return s.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		HcTransLogMouthPK pk = (HcTransLogMouthPK) obj;
		if (!StringUtils.equals(mouth, pk.mouth)) {
			return false;
		}

		if (!StringUtils.equals(merchNo, pk.merchNo)) {
			return false;
		}

		if (!StringUtils.equals(transType, pk.transType)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 */
	@Column(name = "trans_type", length = 2)
	private String transType;

	public String getMouth() {
		return mouth;
	}

	public void setMouth(String mouth) {
		this.mouth = mouth;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
}
