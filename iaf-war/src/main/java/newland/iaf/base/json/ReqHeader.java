package newland.iaf.base.json;

import java.util.Date;

import newland.iaf.base.format.BeanField;

/**
 * 请求报文头
 * 
 * @author rabbit
 * 
 */
public class ReqHeader {

	/**
	 * 接口类型
	 */
	@BeanField(firstLetterUpper = true)
	private String transType;

	/**
	 * 流水号
	 */
	@BeanField(firstLetterUpper = true)
	private String serialNo;

	/**
	 * 日期时间
	 */
	@BeanField(firstLetterUpper = true, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataTime;

	/**
	 * 硬件版本
	 */
	@BeanField(firstLetterUpper = true)
	private String hardVer = "V1.0.0";

	/**
	 * 软件版本
	 */
	@BeanField(firstLetterUpper = true)
	private String softVer = "1.0.1.0";

	/**
	 * 请求类型
	 */
	@BeanField(firstLetterUpper = true)
	private String reqType;

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dateTime) {
		this.dataTime = dateTime;
	}

	public String getHardVer() {
		return hardVer;
	}

	public void setHardVer(String hardVer) {
		this.hardVer = hardVer;
	}

	public String getSoftVers() {
		return softVer;
	}

	public void setSoftVers(String softVers) {
		this.softVer = softVers;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

}
