package newland.iaf.base.json.riskControl;

import newland.iaf.base.format.BeanField;

/**
 * 
 * @author rabbit
 * 
 */
public class RespBody {

	@BeanField
	private String goldKeeperNo;

	@BeanField
	private String content;

	public String getGoldKeeperNo() {
		return goldKeeperNo;
	}

	public void setGoldKeeperNo(String goldKeeperNo) {
		this.goldKeeperNo = goldKeeperNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
