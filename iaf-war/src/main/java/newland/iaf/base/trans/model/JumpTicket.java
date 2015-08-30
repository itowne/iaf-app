package newland.iaf.base.trans.model;

import java.util.Date;

import newland.iaf.base.format.BeanField;
import newland.iaf.base.format.BeanFieldType;

/**
 * 
 * @author rabbit
 * 
 */
public class JumpTicket {

	@BeanField(index = 0)
	String saltValue;

	@BeanField(index = 1)
	String hiMerchNo;

	@BeanField(index = 2)
	private String loginName;

	@BeanField(type = BeanFieldType.datetime, index = 3)
	private Date genTime;

	public String getSaltValue() {
		return saltValue;
	}

	public void setSaltValue(String saltValue) {
		this.saltValue = saltValue;
	}

	public String getHiMerchNo() {
		return hiMerchNo;
	}

	public void setHiMerchNo(String hiMerchNo) {
		this.hiMerchNo = hiMerchNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

}
