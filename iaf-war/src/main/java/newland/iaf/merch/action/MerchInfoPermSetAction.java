package newland.iaf.merch.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.dict.AppealState;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.merch.model.AcceptType;
import newland.iaf.merch.model.MerchInfoAppeal;
import newland.iaf.merch.model.MerchInfoPermission;
import newland.iaf.merch.service.MerchCreditReportService;
import newland.iaf.merch.service.MerchInfoAppealService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.util.CollectionUtils;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "infoPermSet")
@Results({
	@Result(name = "index",  type = "dispatcher",location = "/merch/infoset/index.jsp"),
	@Result(name = "success",  type = "dispatcher",location = "/merch/infoset/success.jsp"),
	@Result(name="appeal",type="dispatcher",location="/merch/appeal/merchInfoAppeal.jsp"),
	@Result(name="add",type="dispatcher",location="/merch/appeal/success.jsp")
    })
public class MerchInfoPermSetAction extends MerchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "merchCreditReportService")
	private MerchCreditReportService merchCreditReportService;
	
	@Resource(name="merchInfoAppealService")
	private MerchInfoAppealService merchInfoAppealService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchInfoAppeal merchInfoAppeal;

	private boolean baseInfo = true;

	private boolean fieldSurvy = true;

	private boolean transfer = true;

	private boolean roundingIspection = true;

	private boolean otherInfo = true;
	
	private boolean install=true;
	
	private boolean merchBusiDataVerification = true;

	@Begin
	public String execute() {
		this.query();
		return "index";
	}

	@SuppressWarnings("incomplete-switch")
	public void query() {

		List<MerchInfoPermission> list = this.merchCreditReportService
				.queryInfoPermission(getMerch());
		if (CollectionUtils.isEmpty(list) == false) {
			for (MerchInfoPermission perm : list) {
				switch (perm.getPosition()) {
				case BASIC_INFO:
					this.baseInfo = perm.getAcceptType().isAllow();
					break;
				case FIELD_SURVY:
					this.fieldSurvy = perm.getAcceptType().isAllow();
					break;
				case OTHER_INFO:
					this.otherInfo = perm.getAcceptType().isAllow();
					break;
				case ROUTING_ISPECTION:
					this.roundingIspection = perm.getAcceptType().isAllow();
					break;
				case TRANSFER:
					this.transfer = perm.getAcceptType().isAllow();
					break;
				case INSTALL:
					this.install=perm.getAcceptType().isAllow();
					break;
				case MERCH_BUSI_DATA_VERIFICATION:
					this.install = perm.getAcceptType().isAllow();
					break;
				}
			}
		}
	}

	public String setPerm() {
		this.merchCreditReportService.setInfoPermission(getMerch(),
				PagePosition.BASIC_INFO, AcceptType.getAcceptType(baseInfo));
		this.merchCreditReportService.setInfoPermission(getMerch(),
				PagePosition.FIELD_SURVY, AcceptType.getAcceptType(this.fieldSurvy));
		this.merchCreditReportService.setInfoPermission(getMerch(),
				PagePosition.OTHER_INFO, AcceptType.getAcceptType(this.otherInfo));
		this.merchCreditReportService.setInfoPermission(getMerch(),
				PagePosition.ROUTING_ISPECTION, AcceptType.getAcceptType(this.roundingIspection));
		this.merchCreditReportService.setInfoPermission(getMerch(),
				PagePosition.TRANSFER, AcceptType.getAcceptType(this.transfer));
		this.merchCreditReportService.setInfoPermission(getMerch(),
				PagePosition.INSTALL, AcceptType.getAcceptType(this.install));
		this.merchCreditReportService.setInfoPermission(getMerch(),
				PagePosition.MERCH_BUSI_DATA_VERIFICATION,AcceptType.getAcceptType(this.merchBusiDataVerification));
		return "success";

	}
	
	public String forwardMerchInfoAppeal(){
		return "appeal";
	}
	
	public String saveMerchInfoAppeal(){
		merchInfoAppeal.setMerchNo(this.getMerchSession().getMerch().getMerchNo());
		merchInfoAppeal.setMerchName(this.getMerchSession().getMerch().getMerchName());
		merchInfoAppeal.setProvince(this.getMerchSession().getMerch().getProvince());
		merchInfoAppeal.setCityCode(this.getMerchSession().getMerch().getCityCode());
		merchInfoAppeal.setAppealState(AppealState.APPLY);
		merchInfoAppeal.setGenTime(new Date());
		merchInfoAppealService.saveMerchInfoAppeal(merchInfoAppeal);
		return "add";
	}

	public boolean isBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(boolean baseInfo) {
		this.baseInfo = baseInfo;
	}

	public boolean isFieldSurvy() {
		return fieldSurvy;
	}

	public void setFieldSurvy(boolean fieldSurvy) {
		this.fieldSurvy = fieldSurvy;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	public boolean isRoundingIspection() {
		return roundingIspection;
	}

	public void setRoundingIspection(boolean roundingIspection) {
		this.roundingIspection = roundingIspection;
	}

	public boolean isOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(boolean otherInfo) {
		this.otherInfo = otherInfo;
	}

	public MerchInfoAppeal getMerchInfoAppeal() {
		return merchInfoAppeal;
	}

	public void setMerchInfoAppeal(MerchInfoAppeal merchInfoAppeal) {
		this.merchInfoAppeal = merchInfoAppeal;
	}

	public boolean isInstall() {
		return install;
	}

	public void setInstall(boolean install) {
		this.install = install;
	}

	public boolean isMerchBusiDataVerification() {
		return merchBusiDataVerification;
	}

	public void setMerchBusiDataVerification(boolean merchBusiDataVerification) {
		this.merchBusiDataVerification = merchBusiDataVerification;
	}

}
