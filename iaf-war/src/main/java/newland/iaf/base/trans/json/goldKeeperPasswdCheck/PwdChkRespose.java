package newland.iaf.base.trans.json.goldKeeperPasswdCheck;

import newland.iaf.base.format.BeanField;
import newland.iaf.base.json.RespHeader;

/**
 * 
 * @author rabbit
 * 
 */
public class PwdChkRespose {

	@BeanField(firstLetterUpper = true)
	private RespHeader appHead;

	@BeanField(firstLetterUpper = true)
	private String retCode;

	@BeanField(firstLetterUpper = true)
	private String retMessage;

	@BeanField(firstLetterUpper = true)
	private String merchNo;

	public RespHeader getAppHead() {
		return appHead;
	}

	public void setAppHead(RespHeader appHead) {
		this.appHead = appHead;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMessage() {
		return retMessage;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

}
