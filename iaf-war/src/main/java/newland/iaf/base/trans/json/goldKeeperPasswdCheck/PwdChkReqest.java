package newland.iaf.base.trans.json.goldKeeperPasswdCheck;

import newland.iaf.base.format.BeanField;
import newland.iaf.base.json.ReqHeader;

/**
 * 
 * @author rabbit
 * 
 */
public class PwdChkReqest {

	@BeanField(firstLetterUpper = true)
	private ReqHeader appHead;

	@BeanField(firstLetterUpper = true)
	private UserData userData;

	public ReqHeader getAppHead() {
		return appHead;
	}

	public void setAppHead(ReqHeader appHead) {
		this.appHead = appHead;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userDate) {
		this.userData = userDate;
	}
}
