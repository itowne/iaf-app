/**
 * 
 */
package newland.iaf.merch.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import newland.iaf.base.model.Province;
import newland.iaf.base.model.dict.AppealState;
import newland.iaf.base.service.ProvinceService;

/**
 * 信用报告申诉
 * @author Mr.Towne
 *
 */
@Entity
@Table(name="t_merch_info_appeal")
public class MerchInfoAppeal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 899502366948121483L;
	
	/**
	 * 内部编号
	 */
	@Id
	@Column (name = "i_merch_info_appeal")
	@TableGenerator(name = "i_merch_info_appeal", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "i_merch_info_appeal")
	private Long iMerchInfoAppeal;
	
	/**
	 * 商户号
	 */
	@Column(name="merch_no",nullable=false,updatable=true)
	private String merchNo;
	/**
	 * 申诉内容
	 */
	@Column(name="appeal_content",nullable=false,updatable=true)
	private String appealContent;
	/**
	 * 申诉原因
	 */
	@Column(name="appeal_reason",nullable=false,updatable=true)
	private String appealReason;
	/**
	 * 申诉人
	 */
	@Column(name="appeal_man",nullable=false,updatable=true)
	private String appealMan;
	/**
	 * 申诉状态
	 */
	@Column(name="appeal_state",nullable=false,updatable=true)
	private AppealState appealState;
	/**
	 * 申诉时间
	 */
	@Column(name="gen_time")
	private Date genTime;
	/**
	 * 申诉电话
	 */
	@Column(name="appeal_phone",nullable=false,updatable=true)
	private String appealPhone;
	/**
	 * 申诉商户名
	 */
	@Column(name="merch_name",nullable=false,updatable=true)
	private String merchName;
	/**
	 * 商户所属省份
	 */
	@Column(name="province",nullable=false,updatable=true)
	private String province;
	/**
	 * 商户所属城市
	 */
	@Column(name="city_code",nullable=false,updatable=true)
	private String cityCode;
	
	/**
	 * 
	 */
	@Transient
	public String provinceCity;
	
	public String getProvinceCity() {
		return provinceCity;
	}
	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}
	public Long getiMerchInfoAppeal() {
		return iMerchInfoAppeal;
	}
	public void setiMerchInfoAppeal(Long iMerchInfoAppeal) {
		this.iMerchInfoAppeal = iMerchInfoAppeal;
	}
	public String getAppealContent() {
		return appealContent;
	}
	public void setAppealContent(String appealContent) {
		this.appealContent = appealContent;
	}
	public String getAppealReason() {
		return appealReason;
	}
	public void setAppealReason(String appealReason) {
		this.appealReason = appealReason;
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
