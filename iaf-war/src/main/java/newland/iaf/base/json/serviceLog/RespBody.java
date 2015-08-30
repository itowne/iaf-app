package newland.iaf.base.json.serviceLog;

import java.util.Date;
import java.util.List;

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
	private Date beginDate;

	@BeanField
	private Date endDate;

	@BeanField
	private Integer recNum;

	@BeanField
	private List<Rec> recList;

}
