package newland.iaf.inst.action.common;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.Province;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.merch.model.Merch;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "loanDetail")
@Results({
		@Result(name = "loanDetail", type = "dispatcher", location = "/inst/loanord/common/loanDetail.jsp"),
		@Result(name = "error", type = "dispatcher", location = "/inst/loanord/common/error.jsp") })
public class LoanOrdDetailAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	protected LoanOrd loanOrd;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	protected Merch merch;

	private DebitBid debitBid;

	private String area;

	@Resource(name = "debitBidService")
	private DebitBidService dbs;

	@Resource(name = "provinceService")
	private ProvinceService ps;

	private LoanPdt lp;

	@Resource(name = "loanPdtService")
	private LoanPdtService lps;

	public String execute() {
		this.loanOrd = (LoanOrd) super.getSessionObj("loanOrd");
		if (loanOrd == null) {
			addActionError("订单信息未找到！");
			return "error";
		}
		this.merch = this.merchService.findMerchById(loanOrd.getImerch());
		if (merch == null) {
			addActionError("商户信息未找到！");
			return "error";
		}

		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
				if(debitBid!=null){
				if (debitBid.getRegion() != null) {//获取地区
					List<Province> list = ps.getProvinceAndCityName(debitBid
							.getRegion());
					area = "";
					if (CollectionUtils.isNotEmpty(list)) {
						Collections.reverse(list);
						for (Province province : list) {
							area += province.getName();
						}
					}
				}
				}
			}
		} else {// 产品
			if(StringUtils.isNotBlank(loanOrd.getLoanPdtId())){
				area = "";
				lp = lps.queryByLoanPdtId(loanOrd.getLoanPdtId());
				if (lp != null) {
					area = lp.getArea();
				}
			}
		}
		return "loanDetail";
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public DebitBid getDebitBid() {
		return debitBid;
	}

	public void setDebitBid(DebitBid debitBid) {
		this.debitBid = debitBid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public LoanPdt getLp() {
		return lp;
	}

	public void setLp(LoanPdt lp) {
		this.lp = lp;
	}

}
