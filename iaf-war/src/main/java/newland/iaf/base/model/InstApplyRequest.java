package newland.iaf.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.SexType;

/**
 * 机构注册申请单
 * 
 * @author new
 * 
 */
@Entity
@Table(name = "T_INST_APPLY_REQ")
public class InstApplyRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8418807455928207806L;
	/**
	 * 内部编号
	 */
	@Id
	@TableGenerator(name = "i_inst_apply_req", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_apply_req")
	@Column(name = "i_inst_apply_req")
	private Long iinstApplyReq;
	/**
	 * 机构名称
	 */
	@Column(name = "inst_name", length = 50)
	private String instName;

	/**
	 * 省份代码
	 */
	@Column(name = "province_code", length = 6)
	private String provinceCode;
	/**
	 * 省份
	 */
	@Column(name = "province", length = 32)
	private String province;

	@Column(name = "city_code", length = 6)
	private String cityCode;

	/**
	 * 城市代码
	 */
	@Column(name = "city", length = 32)
	private String city;
	/**
	 * 性别
	 */
	@Column(name = "gender")
	private SexType gender;
	/**
	 * 联系人
	 */
	@Column(name = "cont_name", length = 50)
	private String contactName;
	/**
	 * 电话
	 */
	@Column(name = "mobile_phone", length = 50)
	private String mobilePhone;
	@Enumerated
	/**
	 * 处理状态
	 */
	@Column(name = "status")
	private ApplyStat stat;

	@Column(name = "gen_time")
	private Date genTime;

	@Column(name = "upt_time")
	private Date updTime;
	
	@Column(name = "memo")
	private String memo;
	
	@Transient
	private String addr;

	public Long getIinstApplyReq() {
		return iinstApplyReq;
	}

	public void setIinstApplyReq(Long iinstApplyReq) {
		this.iinstApplyReq = iinstApplyReq;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public SexType getGender() {
		return gender;
	}

	public void setGender(SexType gender) {
		this.gender = gender;
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

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
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

	public String getAddr() {
		return province+city;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
