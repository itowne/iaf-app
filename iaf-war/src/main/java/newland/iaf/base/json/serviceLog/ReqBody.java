package newland.iaf.base.json.serviceLog;

import java.util.Date;

import newland.iaf.base.format.BeanField;

/**
 * 服务巡检记录请求
 * 
 * @author rabbit
 * 
 */
public class ReqBody {
	@BeanField
	private Date beginDate;

	@BeanField
	private Date endDate;

	@BeanField
	private String goldKeeperNo;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getGoldKeeperNo() {
		return goldKeeperNo;
	}

	public void setGoldKeeperNo(String goldKeeperNo) {
		this.goldKeeperNo = goldKeeperNo;
	}

}
