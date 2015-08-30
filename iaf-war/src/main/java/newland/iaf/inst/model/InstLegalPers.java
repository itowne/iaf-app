/**
 * 
 */
package newland.iaf.inst.model;

import java.io.Serializable;
import java.util.Date;

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

/**
 * 机构法人资料表
 * 
 * @author lindaqun
 * 
 */
@Entity
@Table(name="t_inst_legal_pers")
public class InstLegalPers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6402010061536853659L;

	/**
	 * 机构法人资料内部编号
	 */
	@Id
	@Column(name = "i_inst_legal_pers", nullable = false, updatable = false)
	@TableGenerator(name = "i_inst_legal_pers", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_inst_legal_pers")
	private Long iInstLegalPers;
	/**
	 * 机构内部编号
	 */
	@Column(name = "i_inst", nullable = false, updatable = true)
	private Long iInst;
	/**
	 * 姓名
	 */
	@Column(name = "legal_pers_name", nullable = true, updatable = true)
	private String legalPersName;
	/**
	 * 身份证号码
	 */
	@Column(name = "cerd_no", nullable = true, updatable = false)
	private String cerdNo;
	/**
	 * 性别
	 */
	@Column(name = "sex", nullable = true, updatable = false)
	private SexType sex;
	/**
	 * 年龄
	 */
	@Column(name = "age", nullable = true, updatable = true)
	private Integer age;
	/**
	 * 学历
	 */
	@Column(name = "education", nullable = true, updatable = true)
	private EducationType education;
	/**
	 * 婚姻状况
	 */
	@Column(name = "marital_status", nullable = true, updatable = true)
	private MerryType maritalStatus;
	/**
	 * 家庭住址
	 */
	@Column(name = "family_addr", nullable = false, updatable = true)
	private String familyAddr;
	/**
	 * 生成时间
	 */
	@Column(name = "gen_time", nullable = false, updatable = false)
	private Date genTime;
	/**
	 * 更新时间
	 */
	@Column(name = "upd_time", nullable = false, updatable = true)
	private Date updTime;

	public Long getiInstLegalPers() {
		return iInstLegalPers;
	}

	public void setiInstLegalPers(Long iInstLegalPers) {
		this.iInstLegalPers = iInstLegalPers;
	}

	public Long getiInst() {
		return iInst;
	}

	public void setiInst(Long iInst) {
		this.iInst = iInst;
	}

	public String getLegalPersName() {
		return legalPersName;
	}

	public void setLegalPersName(String legalPersName) {
		this.legalPersName = legalPersName;
	}

	public String getCerdNo() {
		return cerdNo;
	}

	public void setCerdNo(String cerdNo) {
		this.cerdNo = cerdNo;
	}

	

	public SexType getSex() {
		return sex;
	}

	public void setSex(SexType sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public EducationType getEducation() {
		return education;
	}

	public void setEducation(EducationType education) {
		this.education = education;
	}

	public MerryType getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MerryType maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getFamilyAddr() {
		return familyAddr;
	}

	public void setFamilyAddr(String familyAddr) {
		this.familyAddr = familyAddr;
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

}
