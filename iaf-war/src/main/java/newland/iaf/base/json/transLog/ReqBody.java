package newland.iaf.base.json.transLog;

import java.util.Date;

import newland.iaf.base.format.BeanField;
import newland.iaf.base.format.BeanFieldType;

/**
 * 
 * @author rabbit
 * 
 */
public class ReqBody {
	/**
	 * 开始时间
	 */
	@BeanField(type = BeanFieldType.date)
	private Date beginDate;

	/**
	 * 截至时间
	 */
	@BeanField(type = BeanFieldType.date)
	private Date endDate;

	/**
	 * 金掌柜帐号
	 */
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
