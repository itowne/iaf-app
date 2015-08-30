package newland.iaf.base.json;

import java.util.Date;

import newland.iaf.base.format.BeanField;

/**
 * 响应报文头
 * 
 * @author rabbit
 * 
 */
public class RespHeader {
	/**
	 * 接口类型
	 */
	@BeanField(firstLetterUpper = true)
	private String transType;

	/**
	 * 流水号
	 */
	@BeanField(firstLetterUpper = true)
	private String SerialNo;

	/**
	 * 日期时间
	 */
	@BeanField(firstLetterUpper = true, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date DateTime;

	/**
	 * 硬件版本
	 */
	@BeanField(firstLetterUpper = true)
	private String hardVer = "V1.0.0";

	/**
	 * 软件版本
	 */
	@BeanField(firstLetterUpper = true)
	private String softVers = "1.0.1.0";

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getSerialNo() {
		return SerialNo;
	}

	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}

	public Date getDateTime() {
		return DateTime;
	}

	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}

	public String getHardVer() {
		return hardVer;
	}

	public void setHardVer(String hardVer) {
		this.hardVer = hardVer;
	}

	public String getSoftVers() {
		return softVers;
	}

	public void setSoftVers(String softVers) {
		this.softVers = softVers;
	}

}
