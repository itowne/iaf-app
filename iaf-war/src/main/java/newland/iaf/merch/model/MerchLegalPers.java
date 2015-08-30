package newland.iaf.merch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import newland.iaf.base.model.dict.EducationType;
import newland.iaf.base.model.dict.MerryType;
import newland.iaf.base.model.dict.SexType;

import org.hibernate.annotations.Index;

/**
 * 
 * 商户法人
 * 
 * @author wwa
 * 
 */
@Entity
@Table(name = "t_merch_legal_pers")
@org.hibernate.annotations.Table(appliesTo = "t_merch_legal_pers", indexes = {
		@Index(name = "idx_merch_legal_pers1", columnNames = { "legal_pers_name" }),
		@Index(name = "idx_merch_legal_pers2", columnNames = { "cerd_no" }) })
public class MerchLegalPers implements Serializable {

	private static final long serialVersionUID = 7300468753118601173L;

	/**
	 * 内部编号
	 */
	@Id
	@TableGenerator(name = "i_merch_legal_pers", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_merch_legal_pers")
	@Column(name = "i_merch_legal_pers", updatable = false)
	private Long iMerchLegalPers;

	/**
	 * 
	 */
	@Column(name = "i_merch", updatable = false, nullable = false)
	private Long imerch;

	/**
	 * 法人姓名
	 */
	@Column(name = "legal_pers_name")
	private String legalPersName;

	/**
	 * 性别
	 */
	@Column(name = "sex")
	private SexType sex;

	/**
	 * 年龄
	 */
	@Column(name = "age")
	private Integer age;

	/**
	 * 身份证号码
	 */
	@Column(name = "cerd_no")
	private String cerdNo;

	/**
	 * 婚姻状况
	 */
	@Column(name = "marital_status")
	private MerryType maritalStatus;

	/**
	 * 办公电话
	 */
	@Column(name = "office_phone")
	private String officePhone;

	/**
	 * 手机电话
	 */
	@Column(name = "mobi_phone")
	private String mobiPhone;

	/**
	 * 家庭住址
	 */
	@Column(name = "fa_addr")
	private String faAddr;

	/**
	 * 学历
	 */
	@Column(name = "edu_background")
	private EducationType eduBackground;

	public Long getiMerchLegalPers() {
		return iMerchLegalPers;
	}

	public void setiMerchLegalPers(Long iMerchLegalPers) {
		this.iMerchLegalPers = iMerchLegalPers;
	}

	public String getLegalPersName() {
		return legalPersName;
	}

	public void setLegalPersName(String legalPersName) {
		this.legalPersName = legalPersName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCerdNo() {
		return cerdNo;
	}

	public void setCerdNo(String cerdNo) {
		this.cerdNo = cerdNo;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getMobiPhone() {
		return mobiPhone;
	}

	public void setMobiPhone(String mobiPhone) {
		this.mobiPhone = mobiPhone;
	}

	public String getFaAddr() {
		return faAddr;
	}

	public void setFaAddr(String faAddr) {
		this.faAddr = faAddr;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public SexType getSex() {
		return sex;
	}

	public void setSex(SexType sex) {
		this.sex = sex;
	}

	public MerryType getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MerryType maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public EducationType getEduBackground() {
		return eduBackground;
	}

	public void setEduBackground(EducationType eduBackground) {
		this.eduBackground = eduBackground;
	}

}
