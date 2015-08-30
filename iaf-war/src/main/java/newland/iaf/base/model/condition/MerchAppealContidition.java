package newland.iaf.base.model.condition;

import java.util.Date;
import java.util.List;

import newland.iaf.IafApplication;
import newland.iaf.base.model.dict.AppealState;
import newland.iaf.merch.model.MerchInfoAppeal;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(MerchInfoAppeal.class)
public class MerchAppealContidition extends DetachedCriteriaEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = 254084887452387392L;

	@Expression(operator = Operator.in, propertyName = "iMerchInfoAppeal")
	private List<Long> iMerchInfoAppealList;

	@Expression(operator = Operator.ilike, propertyName = "merchNo")
	private String merchNo;

	@Expression(operator = Operator.eq, propertyName = "genTime")
	private Date genTime;

	@Expression(operator = Operator.ge, propertyName = "genTime")
	private Date beginTime;

	@Expression(operator = Operator.lt, propertyName = "genTime")
	private Date endTime;
	
	@Expression(operator = Operator.ilike, propertyName = "appealMan")
	private String appealMan;
	
	@Expression(operator = Operator.ilike, propertyName = "appealPhone")
	private String appealPhone;
	
	@Expression(operator = Operator.eq, propertyName = "appealState")
	private AppealState appealState;
	
	@Expression(operator = Operator.ilike, propertyName = "merchName")
	private String merchName;
	
	@Expression(operator = Operator.eq, propertyName = "province")
	private String province;
	
	@Expression(operator = Operator.eq, propertyName = "cityCode")
	private String cityCode;

	public List<Long> getiMerchInfoAppealList() {
		return iMerchInfoAppealList;
	}

	public void setiMerchInfoAppealList(List<Long> iMerchInfoAppealList) {
		this.iMerchInfoAppealList = iMerchInfoAppealList;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = IafApplication.roundToBeginDate(beginTime);
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = IafApplication.roundToEndDate(endTime);
	}

	public String getAppealMan() {
		return appealMan;
	}

	public void setAppealMan(String appealMan) {
		this.appealMan = appealMan;
	}

	public String getAppealPhone() {
		return appealPhone;
	}

	public void setAppealPhone(String appealPhone) {
		this.appealPhone = appealPhone;
	}

	public AppealState getAppealState() {
		return appealState;
	}

	public void setAppealState(AppealState appealState) {
		this.appealState = appealState;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
