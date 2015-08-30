package newland.iaf.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import newland.iaf.base.model.dict.AreaType;
import newland.iaf.base.model.dict.BusiType;
import newland.iaf.base.model.dict.DistrictType;
import newland.iaf.base.model.dict.FieldNatureType;
import newland.iaf.base.model.dict.OpinType;
import newland.iaf.base.model.dict.YesOrNoType;
/**
 * 商户现场调查记录
 * @author new
 *
 */
@Entity
@Table(name="t_field_survy")
public class MerchFieldSurvy implements Serializable {

	private static final long serialVersionUID = -3538874111753079742L;
	
	/**
	 * 内部编号
	 */
	@Id
	@TableGenerator(name = "i_field_survy", initialValue = 100, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_field_survy")
	@Column(name="i_field_survy")
	private Long iFieldSurvy;
	/**
	 * 商户号
	 */
	@Column(name="i_merch")
	private Long iMerch;
	/**
	 * ======商户经营信息
	 */
	/**
	 * 营业用地性质
	 */
	@Column(name="manage_field_nature")
	private FieldNatureType manageFieldNature;
	
	/**
	 * 营业用地面积
	 */
	@Column(name="manage_field_square", length = 50)
	private String manageFieldSquare;
	
	/**
	 * 经营地段
	 */
	@Column(name="manage_district")
	private DistrictType manageDistrict;
	
	/**
	 * 经营区域
	 */
	@Column(name="manage_area")
	private AreaType manageArea;
	
	/**
	 * 员工人数
	 */
	@Column(name="manage_scale")
	private Integer manageScale;
	
	/**
	 * 营业时间
	 */
	@Column(name="manage_bussiness_hours", length = 20)
	private String manageBussinessHours;
	
	/**
	 * 经营范围---主业
	 */
	@Column(name="manage_range_major", length = 50)
	private String manageRangeMajor;
	
	/**
	 * 经营范围---副业
	 */
	@Column(name="manage_range_avocation", length = 50)
	private String manageRangeAvocation;
	
	/**
	 * 分支机构---范围
	 */
	@Column(name="manage_branch_range", length = 30)
	private String manageBranchRange;
	/**
	 * 分支机构---数量
	 */
	@Column(name="manage_branch_count", length = 20)
	private String manageBranchCount;
	
	/**
	 * ======考察情况及意见
	 */
	/**
	 * 是否检查验证商户的营业执照，身份证，税务登记证，组织机构证等证明文件
	 * 是，符合规定
	 * 否，原因：
	 */
	@Column(name="inspect_lic")
	private YesOrNoType inspectLic;
	/**
	 * 是否检查验证商户的营业执照，身份证，税务登记证，组织机构证等证明文件
	 * 否的原因：
	 */
	@Column(name="inspect_licdesc", length = 100)
	private String inspectLicDesc;
	
	/**
	 * 是否了解商户有相对应的财务/销售报表，销售凭证以证明其营业时间和月平均银行卡营业额（人民币）
	 * 是。
	 * 否，原因：
	 */
	@Column(name="inspect_report")
	private YesOrNoType inspectReport;
	/**
	 * 是否了解商户有相对应的财务/销售报表，销售凭证以证明其营业时间和月平均银行卡营业额（人民币）
	 * 否的原因：
	 */
	@Column(name="inspect_reportdesc", length = 100)
	private String inspectReportDesc;
	/**
	 * 是否对收银员进行业务知识和操作技能的资格审核 
	 * 是。
	 * 否，原因：
	 */
	@Column(name="inspect_know")
	private YesOrNoType inspectKnow;
	/**
	 * 是否对收银员进行业务知识和操作技能的资格审核 
	 * 否的原因：
	 */
	@Column(name="inspect_knowdesc", length = 100)
	private String inspectKnowDesc;
	/**
	 * 是否对商户进行实地考察
	 * 是，在商户营业时间进行实地考察。
	 * 否，原因：
	 */
	@Column(name="inspect_onsite")
	private YesOrNoType inspectOnsite;
	/**
	 * 是否对收银员进行业务知识和操作技能的资格审核 
	 * 否的原因：
	 */
	@Column(name="inspect_onsitedesc", length = 100)
	private String inspectOnsiteDesc;
	/**
	 * 商户营业范围与营业执照登记相符
	 * 是，营业范围与营业执照登记相符
	 * 不符，原因：
	 */
	@Column(name="inspect_range")
	private YesOrNoType inspectRange;
	/**
	 * 商户营业范围与营业执照登记相符
	 * 否的原因：
	 */
	@Column(name="inspect_rangedesc", length = 100)
	private String inspectRangeDesc;
	/**
	 * 是否有足够的存贷来支持销售
	 * 是。
	 * 否，存贷数量不够或存贷与营业执照登记的营业类型不匹配。
	 */
	@Column(name="inspect_loan")
	private YesOrNoType inspectLoan;
	
	/**
	 * 商户的经营状况
	 * 商户经营总体良好。
	 * 商户经营状况一般。
	 * 商户经营状况较差，但会有改善。
	 * 商户经营状况差，风险很高。
	 */
	@Column(name="inspect_busi", length = 100)
	private BusiType inspectBusi;
	
	/**
	 * 商户的考察意见
	 * 优质商户。
	 * 一般商户，风险较低。
	 * 一般商户，有一定风险，需要对其业务有所监控。
	 * 高风险等级商户，暂不考虑发展。
	 */
	@Column(name="inspect_opin", length = 100)
	private OpinType inspectOpin;
	
	@Column(name="survy_time", length = 100)
	private String survyTime;
	
	@Column(name="start_hour", length = 100)
	private String startHour;
	
	@Column(name="start_min", length = 100)
	private String startMin;
	
	@Column(name="end_hour", length = 100)
	private String endHour;
	
	@Column(name="end_min", length = 100)
	private String endMin;
	
	public Long getiFieldSurvy() {
		return iFieldSurvy;
	}
	public void setiFieldSurvy(Long iFieldSurvy) {
		this.iFieldSurvy = iFieldSurvy;
	}
	public String getManageFieldSquare() {
		return manageFieldSquare;
	}
	public void setManageFieldSquare(String manageFieldSquare) {
		this.manageFieldSquare = manageFieldSquare;
	}
	
	public FieldNatureType getManageFieldNature() {
		return manageFieldNature;
	}
	public void setManageFieldNature(FieldNatureType manageFieldNature) {
		this.manageFieldNature = manageFieldNature;
	}
	public DistrictType getManageDistrict() {
		return manageDistrict;
	}
	public void setManageDistrict(DistrictType manageDistrict) {
		this.manageDistrict = manageDistrict;
	}
	public AreaType getManageArea() {
		return manageArea;
	}
	public void setManageArea(AreaType manageArea) {
		this.manageArea = manageArea;
	}
	public Integer getManageScale() {
		return manageScale;
	}
	public void setManageScale(Integer manageScale) {
		this.manageScale = manageScale;
	}
	public String getManageBussinessHours() {
		return manageBussinessHours;
	}
	public void setManageBussinessHours(String manageBussinessHours) {
		this.manageBussinessHours = manageBussinessHours;
	}
	public String getManageRangeMajor() {
		return manageRangeMajor;
	}
	public void setManageRangeMajor(String manageRangeMajor) {
		this.manageRangeMajor = manageRangeMajor;
	}
	public String getManageRangeAvocation() {
		return manageRangeAvocation;
	}
	public void setManageRangeAvocation(String manageRangeAvocation) {
		this.manageRangeAvocation = manageRangeAvocation;
	}
	public String getManageBranchRange() {
		return manageBranchRange;
	}
	public void setManageBranchRange(String manageBranchRange) {
		this.manageBranchRange = manageBranchRange;
	}
	public String getManageBranchCount() {
		return manageBranchCount;
	}
	public void setManageBranchCount(String manageBranchCount) {
		this.manageBranchCount = manageBranchCount;
	}
	public Long getiMerch() {
		return iMerch;
	}
	public void setiMerch(Long iMerch) {
		this.iMerch = iMerch;
	}
	public YesOrNoType getInspectLic() {
		return inspectLic;
	}
	public void setInspectLic(YesOrNoType inspectLic) {
		this.inspectLic = inspectLic;
	}
	public String getInspectLicDesc() {
		return inspectLicDesc;
	}
	public void setInspectLicDesc(String inspectLicDesc) {
		this.inspectLicDesc = inspectLicDesc;
	}
	public YesOrNoType getInspectReport() {
		return inspectReport;
	}
	public String getSurvyTime() {
		return survyTime;
	}
	public void setSurvyTime(String survyTime) {
		this.survyTime = survyTime;
	}
	public void setInspectReport(YesOrNoType inspectReport) {
		this.inspectReport = inspectReport;
	}
	public String getInspectReportDesc() {
		return inspectReportDesc;
	}
	public void setInspectReportDesc(String inspectReportDesc) {
		this.inspectReportDesc = inspectReportDesc;
	}
	public YesOrNoType getInspectKnow() {
		return inspectKnow;
	}
	public void setInspectKnow(YesOrNoType inspectKnow) {
		this.inspectKnow = inspectKnow;
	}
	public String getInspectKnowDesc() {
		return inspectKnowDesc;
	}
	public void setInspectKnowDesc(String inspectKnowDesc) {
		this.inspectKnowDesc = inspectKnowDesc;
	}
	public YesOrNoType getInspectOnsite() {
		return inspectOnsite;
	}
	public void setInspectOnsite(YesOrNoType inspectOnsite) {
		this.inspectOnsite = inspectOnsite;
	}
	public String getInspectOnsiteDesc() {
		return inspectOnsiteDesc;
	}
	public void setInspectOnsiteDesc(String inspectOnsiteDesc) {
		this.inspectOnsiteDesc = inspectOnsiteDesc;
	}
	public YesOrNoType getInspectRange() {
		return inspectRange;
	}
	public void setInspectRange(YesOrNoType inspectRange) {
		this.inspectRange = inspectRange;
	}
	public String getInspectRangeDesc() {
		return inspectRangeDesc;
	}
	public void setInspectRangeDesc(String inspectRangeDesc) {
		this.inspectRangeDesc = inspectRangeDesc;
	}
	public YesOrNoType getInspectLoan() {
		return inspectLoan;
	}
	public void setInspectLoan(YesOrNoType inspectLoan) {
		this.inspectLoan = inspectLoan;
	}
	public BusiType getInspectBusi() {
		return inspectBusi;
	}
	public void setInspectBusi(BusiType inspectBusi) {
		this.inspectBusi = inspectBusi;
	}
	public OpinType getInspectOpin() {
		return inspectOpin;
	}
	public void setInspectOpin(OpinType inspectOpin) {
		this.inspectOpin = inspectOpin;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMin() {
		return startMin;
	}
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMin() {
		return endMin;
	}
	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}
}
