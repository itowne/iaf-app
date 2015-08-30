package newland.iaf.base.json.transLog;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import newland.iaf.base.format.BeanField;
import newland.iaf.base.format.BeanFieldType;
import newland.iaf.base.format.json.JsonBeanFormat;

/**
 * 
 * @author rabbit
 * 
 */
public class RespBody {
	@BeanField
	private String goldKeeperNo;

	@BeanField(type = BeanFieldType.date)
	private Date beginDate;

	@BeanField(type = BeanFieldType.date)
	private Date endDate;

	@BeanField
	private Integer recNum;

	@BeanField
	private ArrayList<Rec> recList;

	public String getGoldKeeperNo() {
		return goldKeeperNo;
	}

	public void setGoldKeeperNo(String goldKeeperNo) {
		this.goldKeeperNo = goldKeeperNo;
	}

	public static void main(String[] args) throws ParseException {
		RespBody rb = new RespBody();
		rb.setGoldKeeperNo("2222");
		rb.setBeginDate(new Date());
		rb.setEndDate(new Date());
		rb.setRecNum(12);
		ArrayList<Rec> list = new ArrayList<Rec>();
		for (int i = 0; i < 2; i++) {
			Rec r = new Rec();
			r.setAcqIssuer("222");
			r.setAmt(BigDecimal.valueOf(12.12));
			r.setCardIssuer("333");
			r.setCardRange("4444");
			r.setGoldKeeperNo("bbbbbbbbb");
			r.setTime(new Date());
			list.add(r);
		}
		JsonBeanFormat<RespBody> jbf = new JsonBeanFormat<RespBody>(
				RespBody.class);
		rb.setRecList(list);
		String json = jbf.format(rb);

		System.out.println(json);

		RespBody rb2 = new RespBody();
		jbf.parse(json, rb2);
		String json2 = jbf.format(rb2);

		if (json.equals(json2)) {
			System.err.print(json2);
		}
	}

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

	public Integer getRecNum() {
		return recNum;
	}

	public void setRecNum(Integer recNum) {
		this.recNum = recNum;
	}

	public List<Rec> getRecList() {
		return recList;
	}

	public void setRecList(ArrayList<Rec> recList) {
		this.recList = recList;
	}
}
