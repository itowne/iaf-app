package newland.iaf.base.model.condition;

import java.util.Date;

import newland.iaf.IafApplication;
import newland.iaf.base.model.InstApplyRequest;
import newland.iaf.base.model.dict.ApplyStat;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.LikeMatchMode;
import org.ohuyo.commons.query.criterion.annotation.Operator;


@CriteriaClass(InstApplyRequest.class)
public class InstApplyRequestCondition extends DetachedCriteriaEx {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8401598026352108346L;
	/**
	 * 机构名称
	 */
	@Expression(operator = Operator.ilike,likeMatchMode=LikeMatchMode.anywhere, propertyName = "instName")
	private String instName;
	@Expression(operator = Operator.eq, propertyName = "instName")
	private String name;
	/**
	 * 联系人
	 */
	@Expression(operator = Operator.ilike, propertyName = "contactName")
	private String contactName;
	
	@Expression(operator = Operator.ilike, propertyName = "mobilePhone")
	private String mobilePhone;
	
	@Expression(operator = Operator.eq, propertyName = "provinceCode")
	private String provinceCode;
	
	@Expression(operator = Operator.eq, propertyName = "cityCode")
	private String cityCode;
	
	@Expression(operator = Operator.eq, propertyName = "stat")
	private ApplyStat stat;
	
	@Expression(operator = Operator.ilike, propertyName = "city")
	private String city;
	@Expression(operator = Operator.ilike, propertyName = "province")
	private String province;
	@Expression(operator = Operator.ge, propertyName = "genTime")
	private Date startGenTime;
	@Expression(operator = Operator.le, propertyName = "genTime")
	private Date endtGenTime;
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public ApplyStat getStat() {
		return stat;
	}
	public void setStat(ApplyStat stat) {
		this.stat = stat;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartGenTime() {
		return startGenTime;
	}
	public void setStartGenTime(Date startGenTime) {
		this.startGenTime = IafApplication.roundToBeginDate(startGenTime);
	}
	public Date getEndtGenTime() {
		return endtGenTime;
	}
	public void setEndtGenTime(Date endtGenTime) {
		this.endtGenTime = IafApplication.roundToEndDate(endtGenTime);
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	
}
