package newland.iaf.base.trans.json.goldKeeperPasswdCheck;

import newland.iaf.base.format.BeanField;

/**
 * 
 * @author rabbit
 * 
 */
public class UserData {

	@BeanField(firstLetterUpper = true)
	private String sysNo = "000001";

	@BeanField(firstLetterUpper = true)
	private String userName;

	@BeanField(firstLetterUpper = true)
	private String pwd;

	@BeanField(firstLetterUpper = true)
	private String mac;

	public String getSysNo() {
		return sysNo;
	}

	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
