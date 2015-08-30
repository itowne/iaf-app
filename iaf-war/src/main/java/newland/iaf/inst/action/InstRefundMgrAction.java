package newland.iaf.inst.action;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.FileType;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.Province;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.ApplyReqService;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.TransMsgService;
import newland.iaf.base.service.impl.FreezeResult;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchType;
import newland.iaf.merch.service.MerchLoanService;
import newland.iaf.merch.service.MerchService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 机构产品审核
 * @author new
 *
 */
@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "refundMgr",interceptorRefs = {
		@InterceptorRef(value = "fileUpload", params = {"allowedTypes", "",
				"maximumSize", "2097152"
		}),
//		@InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png, image/pjpeg",
//				"maximumSize", "2097152"
//		}),
	@InterceptorRef("base_stack")
})
@Results({
	@Result(name = "loanOrdList", type = "dispatcher",location = "/inst/loanord/refundmgr/loanOrdIndex.jsp"),
	@Result(name = "list", type = "JqgridJsonResult", params = {"name", "dataSet"}),
	@Result(name = "freezeList", type = "JqgridJsonResult", params = {"name", "requestDataSet"}),
	@Result(name = "planlist", type = "JqgridJsonResult", params = {"name", "planDataSet"}),
	@Result(name = "viewLoanOrd", location="/inst/loanord/refundmgr/loanOrdDetail.jsp"),
	@Result(name = "viewFreezeList", location="/inst/loanord/refundmgr/freezeList.jsp"),
	@Result(name = "toNext", location="/inst/loanord/refundmgr/demo.jsp"),
	@Result(name = "error", location="/inst/loanord/refundmgr/demo.jsp"),
	@Result(name = "queryhis", location="/inst/loanord/refundmgr/queryhis.jsp"),
	@Result(name = "detail", location="/inst/loanord/refundmgr/otherRefundDetail.jsp"),
	@Result(name="otherRefund",location="/inst/loanord/refundmgr/demo.jsp"),
	@Result(name="applyFreeze",location="/inst/loanord/refundmgr/applyFreeze.jsp"),
	@Result(name="printFreeze",location="/inst/loanord/refundmgr/printFreeze.jsp"),
	@Result(name="toPrint",location="/inst/loanord/refundmgr/printFreeze.jsp"),
	@Result(name="toUnFreeze",location="/inst/loanord/refundmgr/unFreeze.jsp"),
	@Result(name="toPrintUnfreeze",location="/inst/loanord/refundmgr/printUnFreeze.jsp"),
	@Result(name = "print",  type = "dispatcher",location="/inst/loanord/refundmgr/freezeprint.jsp"),
	@Result(name = "unFreezeprint",  type = "dispatcher",location="/inst/loanord/refundmgr/unfreezeprint.jsp"),
	@Result(name = "toUpload",  type = "dispatcher",location="/inst/loanord/refundmgr/upload.jsp"),
	@Result(name = "success",  type = "dispatcher",location="/inst/loanord/refundmgr/success.jsp"),
	@Result(name = "close", location="/inst/loanord/refundmgr/freezeList.jsp")
    })
public class InstRefundMgrAction extends InstBaseAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataSet dataSet;//用户JQGrid数据

	@Resource (name = "instLoanService")
	private InstLoanService instLoanService;
	
	@Resource (name = "com.newland.iaf.merchService")
	private MerchService merchService; 
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Merch merch;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrd> loanOrdList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private int index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrd loanOrd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanOrdId;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String ordStat;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String merchName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String merchType;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanAmount;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String term;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String applyDate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String acceptDate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String memo;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String pdtType;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst instInfo;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String termType;
	
	private BigDecimal total;
	
	@Resource(name = "provinceService")
	private ProvinceService ps;
	
	private Inst instx;
	
	private TransMsg tm;
	
	private InstBusiInfo instBusiInfo;
	
	private FundFlow refundFundFlow;
	
	@Resource(name = "transMsgService")
	private TransMsgService transMsgService;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	private MerchBusiInfo mbi;
	
	@Resource(name = "fundFlowService")
	private FundFlowService fundFlowService;
	
	private String memox;
	
	private BigDecimal Ptotal;
	
	static Set<OrdStat> viewStat = new HashSet<OrdStat>();
	static{
		viewStat.add(OrdStat.REFUND);
		//viewStat.add(OrdStat.REFUNDING);
		viewStat.add(OrdStat.DELIN_QUENCY);
		viewStat.add(OrdStat.PAID_UP_LOAN);
	}
	
	static Set<OrdStat> stat = new HashSet<OrdStat>();
	static {
		//status.add(OrdStat.AUDIT_REFUSE);
		//status.add(OrdStat.CREDITING);
		stat.add(OrdStat.DELIN_QUENCY);
		stat.add(OrdStat.REFUND);
		stat.add(OrdStat.REFUNDING);
		stat.add(OrdStat.PAID_UP_LOAN);
	}
	
	@Resource(name="loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	
	@Resource(name="merchLoanService")
	private MerchLoanService merchLoanService;
	
	@Resource(name = "loanOrdService")
	private LoanOrdService loanordservice;
	
	private String loanOrdPlanId;
	
	private FundFlow fundFlow;
	
	private BigDecimal charge;
	
	private  LoanOrdPlan lopn ;
	
	private String ten;
	
	private String freeze;
	
	private List<LoanPdt> pdtList;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	
	private String minTerm;
	
	private String maxTerm;
	
	private String  endDate;
	
	private String minRate;
	
	private String maxRate;
	
	private String rateType;
	
	private String minloanAmount;
	
	private String maxloanAmount;
	
	private String pdtId;
	
	private String minDate;
	private String maxDate;
	private BigDecimal planTotal;
	private List<LoanOrdPlan> loanOrdPlanList= new ArrayList<LoanOrdPlan>();
	
	private File upload;
	private String filetype;
	private String uploadFileName;
	private String uploadContentType;
	
	private Long iloanOrd;
	
	@Resource(name = "tfileService")
	private TFileService tfileService;
	
	@Resource(name = "applyReqService")
	private ApplyReqService applyReqService;
	
	private BigDecimal capital;
	
	private Date freezeTime;
	
	private String unMemo;
	
	private String id;
	
	private String refundPlanMemo;
	
	public String toUpload(){
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
				if (debitBid.getRegion() != null) {//获取地区
					List<Province> list = ps.getProvinceAndCityName(debitBid
							.getRegion());
					area = "";
					if (!CollectionUtils.isEmpty(list)) {
						Collections.reverse(list);
						for (Province province : list) {
							area += province.getName();
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
		if(StringUtils.isNotBlank(loanOrd.getLoanOrdId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanOrdId()).size();
		}else{
			periods=0;
		}
		if (StringUtils.isNotBlank(selectedRows)){
			String[] strs = StringUtils.split(selectedRows, ",");
			for (String str: strs){
				int index = Integer.parseInt(str.trim());
				LoanOrdPlan plan = this.plans.get(index);
				if (plan.getStat() == PlanStat.PAID_UP_LOAN){
					this.addActionError("第" + plan.getPeriod() + "期已还款，请重新选择！");
					return "toNext";
				}
				if(plan.getStat()==PlanStat.FREEZING||plan.getStat()==PlanStat.THAW_APPLY){
					loanOrdPlanList.add(plan);
				}else{
					this.addActionError("第" + plan.getPeriod() + "期状态不对，不能上传凭证！");
					return "toNext";
				}
				
			}
		}
		return "toUpload";
	}
	
	public String uploadfile() throws Exception{
		String periods = "";
		if(StringUtils.isNotBlank(selectedRows)){
			String[] strs = StringUtils.split(selectedRows, ",");
			for (String str: strs){
				int index = Integer.parseInt(str.trim());
				LoanOrdPlan plan = this.plans.get(index);
				if (plan.getStat() == PlanStat.FREEZING||plan.getStat() == PlanStat.THAW_APPLY){
					loanOrdPlanList.add(plan);
					iloanOrd = plan.getIloanOrd();
					periods += plan.getPeriod().toString() + ",";
				}
			}
		}
		Long iFile=tfileService.CommonFileUploadForiloanOrd(upload, filetype, uploadFileName, uploadContentType,iloanOrd, periods, this.getInst().getIinst());
		ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),periods);
		req.setiFile(iFile);
		applyReqService.update(req);
		if(!CollectionUtils.isEmpty(loanOrdPlanList)){
			for (LoanOrdPlan lop : loanOrdPlanList) {
				if(FileType.valueOf(filetype)==FileType.FREEZE){
					lop.setIsUplod("1");
				}else{
					lop.setIsUplod("3");
				}
				loanOrdPlanService.update(lop);
			}
		}
		return "success";
	}
	
	public String Freezeprint() throws Exception{
		List<LoanOrdPlan> list = loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
		planTotal=BigDecimal.ZERO;
		capital=BigDecimal.ZERO;
		if(StringUtils.isNotBlank(selectedRows)){
			String[] strs = StringUtils.split(this.selectedRows, ",");
			for (String str: strs){
				int index = Integer.parseInt(str.trim());
				LoanOrdPlan plan = this.plans.get(index);
				if (plan.getStat() == PlanStat.FREEZING){
					loanOrdPlanList.add(plan);
					planTotal=planTotal.add(plan.getRepayment()).setScale(2);
					capital=capital.add(plan.getRepayment()).setScale(2);
					ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),plan.getPeriod().toString()+",");
					if(req==null){
						addActionError("解冻出错！");
						return "toNext";
					}
					freezeTime=req.getGenTime();
				}
			}
		}else{
			if(!CollectionUtils.isEmpty(list)){
				for (LoanOrdPlan plan : list) {
					if(plan.getStat() == PlanStat.FREEZING){
						loanOrdPlanList.add(plan);
						planTotal=planTotal.add(plan.getRepayment()).setScale(2);
						capital=capital.add(plan.getRepayment()).setScale(2);
						ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),plan.getPeriod().toString()+",");
						if(req==null){
							addActionError("解冻出错！");
							return "toNext";
						}
						freezeTime=req.getGenTime();
					}
				}
			}
		}
		Ptotal=planTotal;
		planTotal=planTotal.add(loanOrd.getOverdue()).setScale(2);
		if(loanOrdPlanList.size()<=0){
			this.addActionError("您没有冻结凭证需打印!");
			return "toNext";
		}
		
		if(StringUtils.isNotBlank(loanOrd.getLoanOrdId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanOrdId()).size();
		}else{
			periods=0;
		}
		freezeTime=new Date();
		planTotal.add(loanOrd.getOverdue()).setScale(2);
		merch=merchService.findMerchById(loanOrd.getImerch());
		instx = instService.findInstById(loanOrd.getIinst());
		return "print";
	}
	
	public String unFreezeprint() throws Exception{
		List<LoanOrdPlan> list = loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
		planTotal=BigDecimal.ZERO;
		capital=BigDecimal.ZERO;
		if(StringUtils.isNotBlank(selectedRows)){
			String[] strs = StringUtils.split(this.selectedRows, ",");
			for (String str: strs){
				int index = Integer.parseInt(str.trim());
				LoanOrdPlan plan = this.plans.get(index);
				if (plan.getStat() == PlanStat.THAW_APPLY){
					loanOrdPlanList.add(plan);
					planTotal=planTotal.add(plan.getRepayment()).setScale(2);
					capital=capital.add(plan.getRepayment()).setScale(2);
					ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),plan.getPeriod().toString()+",");
					if(req==null){
						addActionError("解冻出错！");
						return "toNext";
					}
					freezeTime=req.getGenTime();
					unMemo = req.getUnFreezeApplyMemo();
				}
			}
		}else{
			if(!CollectionUtils.isEmpty(list)){
				for (LoanOrdPlan plan : list) {
					if(plan.getStat() == PlanStat.THAW_APPLY){
						loanOrdPlanList.add(plan);
						//this.addActionError("第" + plan.getPeriod() + "期已还款或已冻结，请重新选择！");
						//return "toNext";
						planTotal=planTotal.add(plan.getRepayment()).setScale(2);
						capital=capital.add(plan.getRepayment()).setScale(2);
						ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),plan.getPeriod().toString()+",");
						if(req==null){
							addActionError("解冻出错！");
							return "toNext";
						}
						freezeTime=req.getGenTime();
						unMemo = req.getUnFreezeApplyMemo();
					}
				}
			}
		}
		if(loanOrdPlanList.size()<=0){
			this.addActionError("您没有解冻凭证需打印");
			return "toNext";
		}
		Ptotal=planTotal;
		planTotal=planTotal.add(loanOrd.getOverdue()).setScale(2);
		merch=merchService.findMerchById(loanOrd.getImerch());
		instx = instService.findInstById(loanOrd.getIinst());
		return "unFreezeprint";
	}
		
	public String applyFreeze(){
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
				if (debitBid.getRegion() != null) {//获取地区
					List<Province> list = ps.getProvinceAndCityName(debitBid
							.getRegion());
					area = "";
					if (!CollectionUtils.isEmpty(list)) {
						Collections.reverse(list);
						for (Province province : list) {
							area += province.getName();
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
		if(StringUtils.isNotBlank(loanOrd.getLoanOrdId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanOrdId()).size();
		}else{
			periods=0;
		}
		loanOrdPlanList = new ArrayList<LoanOrdPlan>();
		if (StringUtils.isNotBlank(this.selectedRows)){
			String[] strs = StringUtils.split(this.selectedRows, ",");
			for (String str: strs){
				int index = Integer.parseInt(str.trim());
				LoanOrdPlan plan = this.plans.get(index);
				if (plan.getStat() == PlanStat.PAID_UP_LOAN||plan.getStat() == PlanStat.FREEZING||plan.getStat() == PlanStat.BALANCE_FREEZE||plan.getStat() == PlanStat.THAW_APPLY){
					this.addActionError("第" + plan.getPeriod() + "期已还款或者已冻结，请重新选择！");
					return "toNext";
				}
				loanOrdPlanList.add(plan);
			}
			planTotal=BigDecimal.ZERO;
			capital=BigDecimal.ZERO;
			if(!CollectionUtils.isEmpty(loanOrdPlanList)){
				for (LoanOrdPlan plan : loanOrdPlanList) {
					planTotal=planTotal.add(plan.getRepayment()).setScale(2);
					capital=capital.add(plan.getRepayment()).setScale(2);
				}
			}
		}else{
			addActionError("未选择冻结计划！");
			return "toNext";
		}
		freezeTime = new Date();
		merch=merchService.findMerchById(loanOrd.getImerch());
		instx = instService.findInstById(loanOrd.getIinst());
		return "applyFreeze";
	}
	
	public String toUnFreeze(){
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
				if (debitBid.getRegion() != null) {//获取地区
					List<Province> list = ps.getProvinceAndCityName(debitBid
							.getRegion());
					area = "";
					if (!CollectionUtils.isEmpty(list)) {
						Collections.reverse(list);
						for (Province province : list) {
							area += province.getName();
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
		if(StringUtils.isNotBlank(loanOrd.getLoanOrdId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanOrdId()).size();
		}else{
			periods=0;
		}
		
		loanOrdPlanList = new ArrayList<LoanOrdPlan>();
		if (StringUtils.isNotBlank(this.selectedRows)){
			String[] strs = StringUtils.split(this.selectedRows, ",");
			for (String str: strs){
				int index = Integer.parseInt(str.trim());
				LoanOrdPlan plan = this.plans.get(index);
				if (plan.getStat() != PlanStat.BALANCE_FREEZE){
					this.addActionError("第" + plan.getPeriod() + "期不是冻结款项，请重新选择！");
					return "toNext";
				}
				loanOrdPlanList.add(plan);
			}
			planTotal=BigDecimal.ZERO;
			capital=BigDecimal.ZERO;
			if(!CollectionUtils.isEmpty(loanOrdPlanList)){
				for (LoanOrdPlan plan : loanOrdPlanList) {
					planTotal=planTotal.add(plan.getRepayment()).setScale(2);
					capital=capital.add(plan.getRepayment()).setScale(2);
					ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),plan.getPeriod().toString()+",");
					if(req==null){
						addActionError("解冻出错！");
						return "toNext";
					}
					freezeTime=req.getGenTime();
				}
			}
		}else{
			addActionError("未选择冻结计划！");
			return "toNext";
		}
		planTotal=planTotal.add(loanOrd.getOverdue()).setScale(2);
		merch=merchService.findMerchById(loanOrd.getImerch());
		instx = instService.findInstById(loanOrd.getIinst());
		return "toUnFreeze";
	}
	public String toPrintUnFreeze() throws Exception{
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
				if (debitBid.getRegion() != null) {//获取地区
					List<Province> list = ps.getProvinceAndCityName(debitBid
							.getRegion());
					area = "";
					if (!CollectionUtils.isEmpty(list)) {
						Collections.reverse(list);
						for (Province province : list) {
							area += province.getName();
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
		if(StringUtils.isNotBlank(loanOrd.getLoanOrdId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanOrdId()).size();
		}else{
			periods=0;
		}
		
		List<LoanOrdPlan> list = loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
		planTotal=BigDecimal.ZERO;
		if(StringUtils.isNotBlank(selectedRows)){
			String[] strs = StringUtils.split(this.selectedRows, ",");
			for (String str: strs){
				int index = Integer.parseInt(str.trim());
				LoanOrdPlan plan = this.plans.get(index);
				capital=BigDecimal.ZERO;
				if (plan.getStat() == PlanStat.THAW_APPLY){
					loanOrdPlanList.add(plan);
					planTotal=planTotal.add(plan.getRepayment()).setScale(2);
					capital=capital.add(plan.getRepayment()).setScale(2);
					ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),plan.getPeriod().toString()+",");
					freezeTime=req.getGenTime();
					unMemo = req.getUnFreezeApplyMemo();
				}
			}
		}else{
			capital=BigDecimal.ZERO;
			if(!CollectionUtils.isEmpty(list)){
				for (LoanOrdPlan plan : list) {
					if(plan.getStat() == PlanStat.THAW_APPLY){
						loanOrdPlanList.add(plan);
						//this.addActionError("第" + plan.getPeriod() + "期已还款或已冻结，请重新选择！");
						//return "toNext";
						planTotal=planTotal.add(plan.getRepayment()).setScale(2);
						capital=capital.add(plan.getRepayment()).setScale(2);
						ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),plan.getPeriod().toString()+",");
						freezeTime=req.getGenTime();
						unMemo = req.getUnFreezeApplyMemo();
					}
				}
			}
		}
		if(loanOrdPlanList.size()<=0){
			this.addActionError("您没有解冻凭证需打印");
			return "toNext";
		}
		Ptotal=planTotal;
		planTotal=planTotal.add(loanOrd.getOverdue()).setScale(2);
		merch=merchService.findMerchById(loanOrd.getImerch());
		instx = instService.findInstById(loanOrd.getIinst());
		
		return "toPrintUnfreeze";
	}
	
	public String toPrint() throws Exception{
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
				if (debitBid.getRegion() != null) {//获取地区
					List<Province> list = ps.getProvinceAndCityName(debitBid
							.getRegion());
					area = "";
					if (!CollectionUtils.isEmpty(list)) {
						Collections.reverse(list);
						for (Province province : list) {
							area += province.getName();
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
		List<LoanOrdPlan> list = loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
		planTotal=BigDecimal.ZERO;
		capital=BigDecimal.ZERO;
		String proids = "";
		if(StringUtils.isNotBlank(selectedRows)){
			String[] strs = StringUtils.split(this.selectedRows, ",");
			for (String str: strs){
				int index = Integer.parseInt(str.trim());
				LoanOrdPlan plan = this.plans.get(index);
				capital=BigDecimal.ZERO;
				if (plan.getStat() == PlanStat.FREEZING){
					loanOrdPlanList.add(plan);
					planTotal=planTotal.add(plan.getRepayment()).setScale(2);
					capital=capital.add(plan.getRepayment()).setScale(2);
					proids=plan.getPeriod().toString()+",";
				}
			}
		}else{
			if(!CollectionUtils.isEmpty(list)){
				for (LoanOrdPlan plan : list) {
					if(plan.getStat() == PlanStat.FREEZING){
						loanOrdPlanList.add(plan);
						planTotal=planTotal.add(plan.getRepayment()).setScale(2);
						capital=capital.add(plan.getRepayment()).setScale(2);
						proids=plan.getPeriod().toString()+",";
					}
				}
			}
		}
		Ptotal=planTotal;
		if(loanOrd.getOverdue()==null){
			loanOrd.setOverdue(BigDecimal.ZERO);
		}
		planTotal=planTotal.add(loanOrd.getOverdue()).setScale(2);
		if(loanOrdPlanList.size()<=0){
			this.addActionError("您没有冻结凭证需打印!");
			return "toNext";
		}
		ApplyRequest req=applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(), proids);
		if(req!=null){
			freezeTime=req.getGenTime();
		}else{
			freezeTime=new Date();
		}
		if(StringUtils.isNotBlank(loanOrd.getLoanOrdId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanOrdId()).size();
		}else{
			periods=0;
		}
		planTotal.add(loanOrd.getOverdue()).setScale(2);
		merch=merchService.findMerchById(loanOrd.getImerch());
		instx = instService.findInstById(loanOrd.getIinst());
		return "toPrint";
	}
	
	public String detail() throws Exception{
		if(StringUtils.isNotBlank(loanOrdPlanId)){
			lopn = loanOrdPlanService.queryByPlanId(Long.parseLong(loanOrdPlanId));
		}
		instInfo=this.getInst();
		instx = instService.findInstById(loanOrd.getIinst());
		//instBusiInfo=instService.findInstBusiInfoByiinst(loanOrd.getIinst());
		merch = merchService.findMerchById(loanOrd.getImerch());
		mbi=merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
//		FundFlowCondition ffc = new FundFlowCondition();
//		ffc.setIloanOrd(loanOrd.getIloanOrd());
//		Set<FundFlowType> setType = new HashSet<FundFlowType>();
//		setType.add(FundFlowType.CREDIT);
//		setType.add(FundFlowType.OTH_CREDIT);
//		ffc.setTypes(setType);
//			List<FundFlow> ffList= fundFlowService.queryFundFlowBy(ffc);
//			fundFlow = new FundFlow();
//			if(!CollectionUtils.isEmpty(ffList)){
//				fundFlow = ffList.get(0);
//				tm = transMsgService.findById(fundFlow.getOtherSysTraceNo());
//				if(tm!=null){
//					charge=tm.getOrderAmount().multiply(loanOrd.getQuota()).setScale(2);
//				}
//			}
			refundFundFlow=fundFlowService.queryByIloanOrdPlan(Long.parseLong(loanOrdPlanId));
			if(lopn!=null){
				total = lopn.getRepayment().add(lopn.getInterest());
			}else{
				total=refundFundFlow.getAmount();
			}
		return "detail";
	}
	
	public String otherRefund() throws Exception{
		LoanOrdPlan lop =new LoanOrdPlan();
		instx = instService.findInstById(loanOrd.getIinst());
		if(StringUtils.isNotBlank(loanOrdPlanId)){
			lop = loanOrdPlanService.queryByPlanId(Long.parseLong(loanOrdPlanId));
			InstSession instSession = (InstSession) SessionFilter
					.getIafSession();
			merchLoanService.instOthRefund(loanOrd, lop,instSession,memox, getIpaddr());
		}
		
		try{
			payment = BigDecimal.ZERO;
			interest = BigDecimal.ZERO;
			captial = BigDecimal.ZERO;
			this.plans = this.instLoanService.queryPlan(loanOrd);
			if (CollectionUtils.isEmpty(plans) == false){
				for (LoanOrdPlan p : plans){
					captial = captial.add(p.getCapital());
					interest = interest.add(p.getInterest());
					payment = payment.add(p.getRepayment());
				}
			}
		}catch(Exception e){
				addActionError(e.getMessage());
				return "loanOrdList";
			}
			total = captial.add(interest).setScale(2);
		
		merch = merchService.findMerchById(loanOrd.getImerch());
		mbi=merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
		charge = BigDecimal.ZERO;
		FundFlowCondition ffc = new FundFlowCondition();
		ffc.setIloanOrd(loanOrd.getIloanOrd());
		Set<FundFlowType> setType = new HashSet<FundFlowType>();
		setType.add(FundFlowType.CREDIT);
		setType.add(FundFlowType.OTH_CREDIT);
		ffc.setTypes(setType);
			List<FundFlow> ffList= fundFlowService.queryFundFlowBy(ffc);
			fundFlow = new FundFlow();
			if(!CollectionUtils.isEmpty(ffList)){
				fundFlow = ffList.get(0);
				tm = transMsgService.findById(fundFlow.getOtherSysTraceNo());
				if(tm!=null){
					charge=tm.getOrderAmount().multiply(loanOrd.getQuota()).setScale(2);
				}
			}
			
			if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
				if (loanOrd.getIloanPdt() != null) {
					try {
						debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
					} catch (Exception e) {
						super.addActionMessage(e.getMessage());
					}
					if (debitBid.getRegion() != null) {//获取地区
						List<Province> list = ps.getProvinceAndCityName(debitBid
								.getRegion());
						area = "";
						if (!CollectionUtils.isEmpty(list)) {
							Collections.reverse(list);
							for (Province province : list) {
								area += province.getName();
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
			if(StringUtils.isNotBlank(loanOrd.getLoanOrdId())){
				periods=lops.queryByLoanOrdId(loanOrd.getLoanOrdId()).size();
			}else{
				periods=0;
			}
			this.setSessionObj("loanOrd", loanOrd);
		return "otherRefund";
	}
	
	public String query(Page page) throws Exception{
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setShield(0);
		
		if (StringUtils.isNotBlank(loanOrdId)) {
			cond.setLoanOrdId(loanOrdId.trim() + "%");
		}
		cond.setIinst(getInst().getIinst());
		if (StringUtils.isNotBlank(ordStat)) {
			cond.setOrdStat(OrdStat.valueOf(ordStat));
		} else {
			cond.setStatus(stat);
		}
		if(StringUtils.isNotBlank(merchName)){
			cond.setMerchName(merchName);
		}
		
		if(StringUtils.isNotBlank(pdtId)){
			LoanPdt lp = loanPdtService.getLoanPdtById(Long.parseLong(pdtId));
			if(lp!=null){
				cond.setIloanPdt(lp.getIloanPdtHis());
			}
		}
		
		if (StringUtils.isNotBlank(merchType)) {
			cond.setMerchType(MerchType.valueOf(merchType));
		}
		if (StringUtils.isNotBlank(pdtType)) {
			cond.setPdtType(PdtType.valueOf(pdtType));
		}
		if(StringUtils.isNotBlank(minloanAmount)){
			cond.setMinLoanAmount(new BigDecimal(minloanAmount).multiply(new BigDecimal(10000)));
		}
		if(StringUtils.isNotBlank(maxloanAmount)){
			cond.setMaxLoanAmount(new BigDecimal(maxloanAmount).multiply(new BigDecimal(10000)));
		}
		
		if(StringUtils.isNotBlank(rateType)&&StringUtils.isNotBlank(minRate)&&StringUtils.isNotBlank(maxRate)){
			cond.setRateType(RateType.valueOf(rateType));
		}
		
		if(StringUtils.isNotBlank(minRate)){
			cond.setMinRate(parser(minRate, BigDecimal.class));
		}
		if(StringUtils.isNotBlank(maxRate)){
			cond.setMaxRate(parser(maxRate, BigDecimal.class));
		}
//		if (StringUtils.isNotBlank(term)) {
//			cond.setTerm(this.parser(term, Integer.class));
//		}
		
		if(StringUtils.isNotBlank(minTerm)){
			cond.setMinTerm(Integer.parseInt(minTerm));
		}
		if(StringUtils.isNotBlank(maxTerm)){
			cond.setMaxTerm(Integer.parseInt(maxTerm));
		}
		if(StringUtils.isNotBlank(acceptDate)){
			cond.setStartexpiryDate(this.parser(acceptDate,Date.class));
		}
		if(StringUtils.isNotBlank(minDate)){
			cond.setBeginLastRefundDate(this.parser(minDate, Date.class));
		}
		
		if(StringUtils.isNotBlank(maxDate)){
			cond.setEndLastRefundDate(this.parser(maxDate, Date.class));
		}
		
		if(StringUtils.isNotBlank(termType)&&StringUtils.isNotBlank(minTerm)&&StringUtils.isNotBlank(maxTerm)){
			cond.setTermType(TermType.valueOf(termType));
		}
		
		if(StringUtils.isNotBlank(ten)){
			Date d = getDateAfter(new Date(),10);
			List<LoanOrdPlan> obj = loanordservice.queryIloanOrd(new Date(), d, this.getInst().getIinst());
			List<Long> list = new ArrayList<Long>();
			if(!CollectionUtils.isEmpty(obj)){
				for (LoanOrdPlan lop : obj) {
					list.add(lop.getIloanOrd());
				}
			}
			cond.setIloanOrdList(list);
			cond.setStatus(stat);
		}
		if(StringUtils.isNotBlank(freeze)){
			List<LoanOrd> lolist=loanordservice.queryfreezeLoanOrd(this.getInst().getIinst());
			List<Long> list = new ArrayList<Long>();
			if(!CollectionUtils.isEmpty(lolist)){
				for (LoanOrd loanOrd : lolist) {
					list.add(loanOrd.getIloanOrd());
				}
				cond.setIloanOrdList(list);
			}
		}
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iloanOrd"));
		cond.setOrders(orderList);
		this.loanOrdList = this.loanordservice.queryOrdByCon(cond, page);
		return "loanOrdList";
	}
	
	public String loanQuery(){
		pdtList=loanPdtService.queryAll();
		if(StringUtils.isNotBlank(ten)){
			ordStat="";
		}
		return "loanOrdList";
	}
	
	public String listhis(){
		queryhis = true;
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			page.setPageOffset(dataSet.getPage() - 1);
			LoanOrdCondition cond = new LoanOrdCondition();
			cond.setLoanOrdId(loanOrdId);
			cond.setIinst(this.getInst().getIinst());
			cond.setOrdStat(OrdStat.PAID_UP_LOAN);
			cond.setMerchName(merchName);
			if (StringUtils.isNotBlank(merchType)){
				cond.setMerchType(MerchType.valueOf(merchType));
			}
			if (StringUtils.isNotBlank(pdtType)){
				cond.setPdtType(PdtType.valueOf(pdtType));
			}
			cond.setLoanAmount(this.parser(loanAmount, BigDecimal.class));
			cond.setTerm(this.parser(term, Integer.class));
			cond.setAcceptDate(this.parser(acceptDate, Date.class));
			cond.setApplyDate(this.parser(applyDate, Date.class));
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.desc("iloanOrd"));
			cond.setOrders(orderList);
			this.loanOrdList = this.instLoanService.queryByCond(cond, page);
			dataSet.setGridModel(this.loanOrdList);
			dataSet.setRecords(loanOrdList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
		} catch (Exception e) {
			logger.error("查询订单出错", e);
		}
		return "list";
	}
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private  Boolean queryhis = false;

	public String list() throws Exception{
		queryhis = false;
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			page.setPageOffset(dataSet.getPage() - 1);
			this.query(page);
			dataSet.setGridModel(this.loanOrdList);
			dataSet.setRecords(loanOrdList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrdPlan> plans;
	
	private DebitBid debitBid;
	
	@Resource(name = "debitBidService")
	private DebitBidService dbs;
	
	private String area;
	
	private LoanPdt lp;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService lps;
	
	private Integer periods;
	
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService lops;
	
	private DataSet planDataSet;
	
	private Integer flag;
	
	private Integer display;
	
	private String unFreezeMemo;
	
	public String planList() throws Exception{
		//如果本金也还款金额不符，回到未上传页面
//		loanOrd = (LoanOrd) this.getSessionObj("loan_ord");
//		queryhis = false;
//		payment = BigDecimal.ZERO;
//		interest = BigDecimal.ZERO;
//		captial = BigDecimal.ZERO;
//		if (CollectionUtils.isEmpty(plans) == false){
//			for (LoanOrdPlan plan:plans){
//				captial = captial.add(plan.getCapital()).setScale(2, BigDecimal.ROUND_HALF_UP);
//				interest = interest.add(plan.getInterest()).setScale(2, BigDecimal.ROUND_HALF_UP);
//				payment = payment.add(plan.getRepayment()).setScale(2, BigDecimal.ROUND_HALF_UP);
//			}
//		}
//		if(loanOrd != null){
//			total = loanOrd.getQuota().add(interest).setScale(2, BigDecimal.ROUND_HALF_UP);
//			if(total.compareTo(payment) != 0){
//				
//			}
//		}
		
		payment = BigDecimal.ZERO;
		interest = BigDecimal.ZERO;
		captial = BigDecimal.ZERO;
		if(CollectionUtils.isEmpty(this.plans)){
			this.plans = this.instLoanService.queryPlan(loanOrd);
		}
		for (LoanOrdPlan plan:plans){
			captial = captial.add(plan.getCapital()).setScale(2, BigDecimal.ROUND_HALF_UP);
			interest = interest.add(plan.getInterest()).setScale(2, BigDecimal.ROUND_HALF_UP);
			payment = payment.add(plan.getRepayment()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		if(loanOrd != null){
			total = loanOrd.getQuota().add(interest).setScale(2, BigDecimal.ROUND_HALF_UP);
			if(total.compareTo(payment) != 0){
				
			}
		}
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			if (planDataSet == null) {
				planDataSet = new DataSet();
				page.setPageOffset(0);
			} else {
				page.setPageOffset(planDataSet.getPage() - 1);
			}
			int totals = plans.size();
			int rec = 0;
			if(totals!=0){
				rec = totals/10;
				if(totals%10!=0){
					rec=rec+1;
				}
			}
			List<LoanOrdPlan> list = new ArrayList<LoanOrdPlan>();
			if(CollectionUtils.isNotEmpty(this.plans)){
				if(page.getPageOffset()==(rec-1)){
					list = this.plans.subList(page.getPageOffset()*10,page.getPageOffset()*10+totals%10);
				}else{
					if(page.getPageOffset()==0){
						list= this.plans.subList(0, 10);
					}
					if(page.getPageOffset()>=1){
						list= this.plans.subList(page.getPageOffset()*10, page.getPageOffset()*10+10);
					}
				}
			}
			planDataSet.setGridModel(list);
			
			if(page.getPageOffset()>=1){
				if(page.getPageOffset()==(rec-1)){
					page.setRecOffset(10*page.getPageOffset());
					page.setSize(totals%10);
				}else{
					page.setRecOffset(10*page.getPageOffset());
					page.setSize(10);
				}
			}
			
			if(page.getPageOffset()==0){
				page.setRecOffset(0);
			}
			
			page.setPageAmt(rec);
			page.setRecAmt(plans.size());
			planDataSet.setRecords(page.getRecAmt());
			planDataSet.setTotal(page.getPageAmt());
			
			return "planlist";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String toNext(){
		if(StringUtils.isNotBlank(id)){
			this.loanOrd = loanordservice.queryLoanOrdById(Long.parseLong(id));
			this.setSessionObj("loanOrd", loanOrd);
		}else{
			if (CollectionUtils.isEmpty(loanOrdList)) return "loanOrdList";
			this.loanOrd = this.loanOrdList.get(index);
			this.setSessionObj("loanOrd", loanOrd);
		}
		
		merch = this.merchService.findMerchById(loanOrd.getImerch());
		
		if(loanOrd!=null){
			
		}
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
				if (debitBid.getRegion() != null) {//获取地区
					List<Province> list = ps.getProvinceAndCityName(debitBid
							.getRegion());
					area = "";
					if (!CollectionUtils.isEmpty(list)) {
						Collections.reverse(list);
						for (Province province : list) {
							area += province.getName();
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
		if(StringUtils.isNotBlank(loanOrd.getLoanOrdId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanOrdId()).size();
		}else{
			periods=0;
		}
		
		return "toNext";
	}
	
	@Begin
	public String beginQueryhis(){
		queryhis = true;
		return "queryhis";
	}
	
	public String queryHis(){
		return "queryhis";
	}
	
	
	
	public String viewLoanOrd(){
		//loanOrd = this.loanOrdList.get(index);
		//this.setSessionObj("loanOrd", loanOrd);
		try{
			payment = BigDecimal.ZERO;
			interest = BigDecimal.ZERO;
			captial = BigDecimal.ZERO;
			flag=0;
			display=0;
			this.plans = this.instLoanService.queryPlan(loanOrd);
			if (CollectionUtils.isEmpty(plans) == false){
				for (LoanOrdPlan p : plans){
					captial = captial.add(p.getCapital());
					interest = interest.add(p.getInterest());
					payment = payment.add(p.getRepayment());
					if(p.getStat()==PlanStat.BALANCE_FREEZE||p.getStat()==PlanStat.FREEZING){
						flag=1;
					}
					if(p.getStat()==PlanStat.BALANCE_FREEZE||p.getStat()==PlanStat.FREEZING||p.getStat()==PlanStat.THAW_APPLY||p.getStat()==PlanStat.PAID_UP_LOAN){
						display=1;
					}
				}
			}
			total = captial.add(interest).setScale(2);
			instInfo=this.getInst();
			instx = instService.findInstById(loanOrd.getIinst());
			//instBusiInfo=instService.findInstBusiInfoByiinst(loanOrd.getIinst());
			merch = merchService.findMerchById(loanOrd.getImerch());
			mbi=merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
			charge = BigDecimal.ZERO;
			FundFlowCondition ffc = new FundFlowCondition();
			ffc.setIloanOrd(loanOrd.getIloanOrd());
			Set<FundFlowType> setType = new HashSet<FundFlowType>();
			setType.add(FundFlowType.CREDIT);
			setType.add(FundFlowType.OTH_CREDIT);
			ffc.setTypes(setType);
				List<FundFlow> ffList= fundFlowService.queryFundFlowBy(ffc);
				fundFlow = new FundFlow();
				if(!CollectionUtils.isEmpty(ffList)){
					fundFlow = ffList.get(0);
					tm = transMsgService.findById(fundFlow.getOtherSysTraceNo());
					if(tm!=null){
						charge=tm.getOrderAmount().subtract(loanOrd.getQuota()).setScale(2);
					}
				}
			
		}catch(Exception e){
			addActionError(e.getMessage());
			return "loanOrdList";
		}
		return "viewLoanOrd";
	}
	
	public String refreshViewLoanOrd(){
		try{
			payment = BigDecimal.ZERO;
			interest = BigDecimal.ZERO;
			captial = BigDecimal.ZERO;
			this.plans = this.instLoanService.queryPlan(loanOrd);
			if (CollectionUtils.isEmpty(plans) == false){
				for (LoanOrdPlan plan:plans){
					captial = captial.add(plan.getCapital());
					interest = interest.add(plan.getInterest());
					payment = payment.add(plan.getRepayment());
				}
			}
		}catch(Exception e){
			addActionError(e.getMessage());
			return "loanOrdList";
		}
		return "viewLoanOrd";
	}
	
	private String planFileName;
	
	private File plan;
	
	private String planContentType;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal captial = BigDecimal.ZERO;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal interest = BigDecimal.ZERO;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal payment = BigDecimal.ZERO;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrdPlan> newPlans;

	
	public String upload() {
		payment = BigDecimal.ZERO;
		interest = BigDecimal.ZERO;
		captial = BigDecimal.ZERO;
		display=0;
		try {
			//将上传excel文件转为loanord格式
			List<LoanOrdPlan> planList = this.instLoanService.convertPlanFrom(plan);
			
			//newPlans=this.instLoanService.convertPlanFrom(plan);
			if(CollectionUtils.isEmpty(planList) == false){
				for (LoanOrdPlan lop : planList) {
					if(StringUtils.isEmpty(lop.getPeriod().toString())){
						this.addActionError("还款计划期数不能为空!");
						return "loanPlans";
					}
					if(lop.getRefundDate()==null){
						this.addActionError("第"+lop.getPeriod()+"期还款日期不能为空！");
						return "loanPlans";
					}
					if(StringUtils.isEmpty(lop.getCapital().toString())){
						this.addActionError("第"+lop.getPeriod()+"期月还款总额不能为空！");
						return "loanPlans";
					}
					if(StringUtils.isEmpty(lop.getInterest().toString())){
						this.addActionError("第"+lop.getPeriod()+"期月还款利息不能为空！");
						return "loanPlans";
					}
					if(StringUtils.isEmpty(lop.getRepayment().toString())){
						this.addActionError("第"+lop.getPeriod()+"期月还款本金不能为空！");
						return "loanPlans";
					}
				}
				newPlans=planList;
			}
			if (CollectionUtils.isEmpty(planList) == false){
				for (LoanOrdPlan plan:planList){
					captial = captial.add(plan.getCapital()).setScale(2, BigDecimal.ROUND_HALF_UP);
					interest = interest.add(plan.getInterest()).setScale(2, BigDecimal.ROUND_HALF_UP);
					payment = payment.add(plan.getRepayment()).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
			}
			total = loanOrd.getQuota().add(interest).setScale(2, BigDecimal.ROUND_HALF_UP);
			if(loanOrd.getQuota().setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(captial) != 0){
				this.plans = this.instLoanService.queryPlan(loanOrd);
				this.addActionError("还款总本金与贷款金额金额不符,请重新上传!");
//				return "loanPlans";
			}else{
				this.plans = this.instLoanService.overRidePlan(plans, newPlans);
				//把plans设到session中，让refundplanAction可以取到值
				this.setSessionObj("planList", plans);
				this.setSessionObj("loan_ord", loanOrd);
			}
			instInfo=this.getInst();
			instx = instService.findInstById(loanOrd.getIinst());
			//instBusiInfo=instService.findInstBusiInfoByiinst(loanOrd.getIinst());
			merch = merchService.findMerchById(loanOrd.getImerch());
			mbi=merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
			charge = BigDecimal.ZERO;
			FundFlowCondition ffc = new FundFlowCondition();
			ffc.setIloanOrd(loanOrd.getIloanOrd());
			Set<FundFlowType> setType = new HashSet<FundFlowType>();
			setType.add(FundFlowType.CREDIT);
			setType.add(FundFlowType.OTH_CREDIT);
			ffc.setTypes(setType);
				List<FundFlow> ffList= fundFlowService.queryFundFlowBy(ffc);
				fundFlow = new FundFlow();
				if(!CollectionUtils.isEmpty(ffList)){
					fundFlow = ffList.get(0);
					tm = transMsgService.findById(fundFlow.getOtherSysTraceNo());
					if(tm!=null){
						charge=tm.getOrderAmount().multiply(loanOrd.getQuota()).setScale(2);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("还款计划期数,本金 ,利率请填写数字!");
		}
		return "viewLoanOrd";
	}


	public String applyPlan(){
		display=0;
		try{
			if(newPlans==null){
				this.addActionError("上传列表为空！");
				return viewLoanOrd();
			}
//			this.instLoanService.uploadLoanOrdPlan(loanOrd, newPlans, getUserSession(), refundPlanMemo, getIpaddr());
			if (CollectionUtils.isEmpty(newPlans) == false){
				captial=BigDecimal.ZERO;
				interest=BigDecimal.ZERO;
				payment=BigDecimal.ZERO;
				for (LoanOrdPlan plan:newPlans){
					captial = captial.add(plan.getCapital()).setScale(2, BigDecimal.ROUND_HALF_UP);
					interest = interest.add(plan.getInterest()).setScale(2, BigDecimal.ROUND_HALF_UP);
					payment = payment.add(plan.getRepayment()).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
			}
			total = loanOrd.getQuota().add(interest).setScale(2, BigDecimal.ROUND_HALF_UP);
			if(loanOrd.getQuota().setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(captial) != 0){
				this.addActionError("还款总本金与贷款金额金额不符,不能保存!");
				return "viewLoanOrd";
			}else{
				this.instLoanService.modifyLoanOrdPlan(loanOrd, newPlans, getUserSession(), refundPlanMemo, getIpaddr());
			}
			instInfo=this.getInst();
			instx = instService.findInstById(loanOrd.getIinst());
			//instBusiInfo=instService.findInstBusiInfoByiinst(loanOrd.getIinst());
			merch = merchService.findMerchById(loanOrd.getImerch());
			mbi=merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
			charge = BigDecimal.ZERO;
			FundFlowCondition ffc = new FundFlowCondition();
			ffc.setIloanOrd(loanOrd.getIloanOrd());
			Set<FundFlowType> setType = new HashSet<FundFlowType>();
			setType.add(FundFlowType.CREDIT);
			setType.add(FundFlowType.OTH_CREDIT);
			ffc.setTypes(setType);
				List<FundFlow> ffList= fundFlowService.queryFundFlowBy(ffc);
				fundFlow = new FundFlow();
				if(!CollectionUtils.isEmpty(ffList)){
					fundFlow = ffList.get(0);
					tm = transMsgService.findById(fundFlow.getOtherSysTraceNo());
					if(tm!=null){
						charge=tm.getOrderAmount().multiply(loanOrd.getQuota()).setScale(2);
					}
				}
		}catch(Exception e){
			this.addActionError(e.getMessage());
			logger.error("上传计划出错", e);
		}
		return "viewLoanOrd";
	}
	
	private String selectedRows;
	private String overdue;
	
	public String freeze() throws Exception{
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
				if (debitBid.getRegion() != null) {//获取地区
					List<Province> list = ps.getProvinceAndCityName(debitBid
							.getRegion());
					area = "";
					if (!CollectionUtils.isEmpty(list)) {
						Collections.reverse(list);
						for (Province province : list) {
							area += province.getName();
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
		if(StringUtils.isNotBlank(loanOrd.getLoanOrdId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanOrdId()).size();
		}else{
			periods=0;
		}
		
		try{
			loanOrdPlanList = new ArrayList<LoanOrdPlan>();
			if (StringUtils.isNotBlank(this.selectedRows)){
				String[] strs = StringUtils.split(this.selectedRows, ",");
				for (String str: strs){
					int index = Integer.parseInt(str.trim());
					LoanOrdPlan plan = this.plans.get(index);
					if (plan.getStat() == PlanStat.PAID_UP_LOAN){
						this.addActionError("第" + plan.getPeriod() + "期已还款，请重新选择！");
						return "toNext";
					}
					if(plan.getStat() == PlanStat.BALANCE_FREEZE||plan.getStat()==PlanStat.FREEZING||plan.getStat()==PlanStat.THAW_APPLY){
						this.addActionError("第" + plan.getPeriod() + "期冻结中，请重新选择！");
						return "toNext";
					}
					loanOrdPlanList.add(plan);
				}
				
				planTotal=BigDecimal.ZERO;
				capital=BigDecimal.ZERO;
				if(!CollectionUtils.isEmpty(loanOrdPlanList)){
					for (LoanOrdPlan plan : loanOrdPlanList) {
						planTotal=planTotal.add(plan.getRepayment()).setScale(2);
						capital=capital.add(plan.getRepayment()).setScale(2);
					}
				}
			}else{
				addActionError("未选择冻结计划！");
				return "viewLoanOrd";
			}
			loanOrd.setFreezeMemo(memo);
			if(StringUtils.isNotBlank(overdue)){
				loanOrd.setOverdue(new BigDecimal(overdue));
			}else{
				loanOrd.setOverdue(BigDecimal.ZERO);
			}
			Ptotal=planTotal;
			planTotal=planTotal.add(loanOrd.getOverdue()).setScale(2);
			
			loanOrd.setFreezeDate(new Date());
			this.instLoanService.freeze(loanOrd, loanOrdPlanList, getUserSession(), memo, this.getIpaddr());
			freezeTime = new Date();
			if(!CollectionUtils.isEmpty(loanOrdPlanList)){
				for (LoanOrdPlan lop : loanOrdPlanList) {
					lop.setIsUplod("2");
					loanOrdPlanService.update(lop);
				}
			}
			
			instInfo=this.getInst();
			instx = instService.findInstById(loanOrd.getIinst());
			//instBusiInfo=instService.findInstBusiInfoByiinst(loanOrd.getIinst());
			merch = merchService.findMerchById(loanOrd.getImerch());
			mbi=merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
			charge = BigDecimal.ZERO;
			FundFlowCondition ffc = new FundFlowCondition();
			ffc.setIloanOrd(loanOrd.getIloanOrd());
			Set<FundFlowType> setType = new HashSet<FundFlowType>();
			setType.add(FundFlowType.CREDIT);
			setType.add(FundFlowType.OTH_CREDIT);
			ffc.setTypes(setType);
				List<FundFlow> ffList= fundFlowService.queryFundFlowBy(ffc);
				fundFlow = new FundFlow();
				if(!CollectionUtils.isEmpty(ffList)){
					fundFlow = ffList.get(0);
					tm = transMsgService.findById(fundFlow.getOtherSysTraceNo());
					if(tm!=null){
						charge=tm.getOrderAmount().multiply(loanOrd.getQuota()).setScale(2);
					}
				}
				
				payment = BigDecimal.ZERO;
				interest = BigDecimal.ZERO;
				captial = BigDecimal.ZERO;
				this.plans = this.instLoanService.queryPlan(loanOrd);
				if (CollectionUtils.isEmpty(plans) == false){
					for (LoanOrdPlan p : plans){
						captial = captial.add(p.getCapital());
						interest = interest.add(p.getInterest());
						payment = payment.add(p.getRepayment());
					}
				}
				total = captial.add(interest).setScale(2);
				
		}catch(Exception e){
			this.addActionError(e.getMessage());
			logger.error("冻结时出错", e);
			return "applyFreeze";
		}
		return "printFreeze";
	}
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<ApplyRequest> requestList;
	
	private DataSet requestDataSet;
	
	public String freezeList(){
		try{
			this.requestList = this.instLoanService.queryFreezeReq(getInst(), loanOrd, ApplyStat.APPLY);
			requestDataSet.setGridModel(requestList);
			requestDataSet.setRecords(requestList.size());
		}catch(Exception e){
			logger.error("查询冻结请求失败", e);
		}
		instInfo=this.getInst();
		instx = instService.findInstById(loanOrd.getIinst());
		//instBusiInfo=instService.findInstBusiInfoByiinst(loanOrd.getIinst());
		merch = merchService.findMerchById(loanOrd.getImerch());
		mbi=merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
		charge = BigDecimal.ZERO;
		FundFlowCondition ffc = new FundFlowCondition();
		ffc.setIloanOrd(loanOrd.getIloanOrd());
		Set<FundFlowType> setType = new HashSet<FundFlowType>();
		setType.add(FundFlowType.CREDIT);
		setType.add(FundFlowType.OTH_CREDIT);
		ffc.setTypes(setType);
			List<FundFlow> ffList= fundFlowService.queryFundFlowBy(ffc);
			fundFlow = new FundFlow();
			if(!CollectionUtils.isEmpty(ffList)){
				fundFlow = ffList.get(0);
				tm = transMsgService.findById(fundFlow.getOtherSysTraceNo());
				if(tm!=null){
					charge=tm.getOrderAmount().multiply(loanOrd.getQuota()).setScale(2);
				}
			}
		
		return "freezeList";
	}
	
	/**
	 * 撤销冻结请求
	 * @return
	 */
	public String cancelFreeze(){
		if(CollectionUtils.isEmpty(requestList)){
			addActionError("冻结列表为空");
		}
		ApplyRequest req = this.requestList.get(index);
		
		try{
			this.instLoanService.cancelFreeze(loanOrd, req, getUserSession(), memo, this.getIpaddr());
			payment = BigDecimal.ZERO;
			interest = BigDecimal.ZERO;
			captial = BigDecimal.ZERO;
			this.plans = this.instLoanService.queryPlan(loanOrd);
			if (CollectionUtils.isEmpty(plans) == false){
				for (LoanOrdPlan p : plans){
					captial = captial.add(p.getCapital());
					interest = interest.add(p.getInterest());
					payment = payment.add(p.getRepayment());
				}
			}
			total = captial.add(interest).setScale(2);
		}catch(Exception e){
			this.addActionError(e.getMessage());
			logger.error("解冻时出错", e);
		}
		return "viewLoanOrd";
	}
	
	public String getTermType() {
		return termType;
	}


	public void setTermType(String termType) {
		this.termType = termType;
	}


	public Inst getInstInfo() {
		return instInfo;
	}


	public void setInstInfo(Inst instInfo) {
		this.instInfo = instInfo;
	}


	public String viewFreezeList(){
		return "viewFreezeList";
	}
	
	public String close(){
		return "close";
	}
	/**
	 * 解冻订单
	 * @return
	 */
	public String thraw(){
		if (StringUtils.isBlank(this.selectedRows)){
			addActionError("未选择计划");
			return "unfreeze";
		}
		try{
			loanOrdPlanList  = new ArrayList<LoanOrdPlan>();
			if (StringUtils.isNotBlank(this.selectedRows)){
				String[] strs = StringUtils.split(this.selectedRows, ",");
				for (String str: strs){
					int index = Integer.parseInt(str.trim());
					LoanOrdPlan plan = this.plans.get(index);
					if (plan.getStat() != PlanStat.BALANCE_FREEZE){
						this.addActionError("第" + plan.getPeriod() + "期款项不是冻结成功状态！");
						return "toNext";
					}
					loanOrdPlanList.add(plan);
				}
			}
			planTotal=BigDecimal.ZERO;
			capital=BigDecimal.ZERO;
			if(!CollectionUtils.isEmpty(loanOrdPlanList)){
				for (LoanOrdPlan plan : loanOrdPlanList) {
					planTotal=planTotal.add(plan.getRepayment()).setScale(2);
					capital=capital.add(plan.getRepayment()).setScale(2);
					ApplyRequest req = applyReqService.queryByIloanOrd(loanOrd.getIloanOrd(),plan.getPeriod().toString()+",");
					freezeTime=req.getGenTime();
				}
			}
			planTotal=planTotal.add(loanOrd.getOverdue()).setScale(2);
			loanOrd.setUnFreezeMemo(unFreezeMemo);
			this.instLoanService.thaw(loanOrd, loanOrdPlanList, getUserSession(), unFreezeMemo, this.getIpaddr());
			
			if(!CollectionUtils.isEmpty(loanOrdPlanList)){
				for (LoanOrdPlan lop : loanOrdPlanList) {
					lop.setIsUplod("4");
					loanOrdPlanService.update(lop);
				}
			}
		}catch(Exception e){
			this.addActionError(e.getMessage());
			logger.error("解冻时出错", e);
			return "toNext";
		}
		return "toPrintUnfreeze";
	}
	
	/**
	 * 撤销
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception{
		try{
			this.instLoanService.cancel(loanOrd, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			this.addActionError(e.getMessage());
			return "viewLoanOrd";
		}
		return "loanOrdList";
	}
	/**
	 * 返回
	 * @return
	 * @throws Exception 
	 */

	public String returnAction() throws Exception{
		if (queryhis) return "queryhis";
		pdtList=loanPdtService.queryAll();
		return "loanOrdList";
	}


	@Override
	@Begin
	@End
	public String execute() throws Exception {
		pdtList=loanPdtService.queryAll();
		this.setFlowType(FlowType.REFUND_MGR);
		queryhis = false;
		termType="";
		merchType="";
		pdtType="";
		loanAmount="";
		term="";
		acceptDate="";
		applyDate="";
		merchName="";
		endDate="";
		ordStat="";
		rateType="";
		minloanAmount="";
		maxloanAmount="";
		minRate="";
		minTerm="";
		maxRate="";
		maxTerm="";
		loanOrdId="";
		minDate="";
		maxDate="";
		return "loanOrdList";
	}
	static Set<OrdStat> status = new HashSet<OrdStat>();
	static{
		status.add(OrdStat.REFUND);
		//viewStat.add(OrdStat.REFUNDING);
		status.add(OrdStat.DELIN_QUENCY);
		status.add(OrdStat.PAID_UP_LOAN);
	}
	
	public Set<OrdStat> getStatus(){
		return status;
	}

	public List<LoanOrd> getLoanOrdList() {
		return loanOrdList;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@JqDataSet(name = "dataSet", content = "{o:loanOrdId},{o:pdtType.desc},{o:pdtName},{f:(wanyuanFormatter)({o:quota})},{o:pdtTerm},{o:pdtRate},{o:merchName},{i:(format.date)({o:lastRefundDate})},{o:ordStat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}


	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}


	public InstLoanService getInstLoanService() {
		return instLoanService;
	}


	public void setInstLoanService(InstLoanService instLoanService) {
		this.instLoanService = instLoanService;
	}


	public int getIndex() {
		return index;
	}


	public void setLoanOrdList(List<LoanOrd> loanOrdList) {
		this.loanOrdList = loanOrdList;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}

	public String getLoanOrdId() {
		return loanOrdId;
	}

	public void setLoanOrdId(String loanOrdId) {
		this.loanOrdId = loanOrdId;
	}

	public String getOrdStat() {
		return ordStat;
	}

	public void setOrdStat(String ordStat) {
		this.ordStat = ordStat;
	}

	public String getMerchName() {
		return merchName;
	}

	public String getMerchType() {
		return merchType;
	}

	public void setMerchType(String merchType) {
		this.merchType = merchType;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPdtType() {
		return pdtType;
	}

	public void setPdtType(String pdtType) {
		this.pdtType = pdtType;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	@JqDataSet(name="planDataSet", content = "{o:period},{i:(format.date)({o:refundDate})},{i:(format.money)({o:repayment})},{i:(format.money)({o:capital})},{i:(format.money)({o:interest})},{i:(format.money)({o:remainAmount})},{o:stat.desc},{o:saveFlag},{o:iloanOrdPlan},{o:isUplod}")
	public DataSet getPlanDataSet() {
		return planDataSet;
	}

	public void setPlanDataSet(DataSet planDataSet) {
		this.planDataSet = planDataSet;
	}

	public List<LoanOrdPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<LoanOrdPlan> plans) {
		this.plans = plans;
	}

	public File getPlan() {
		return plan;
	}

	public void setPlan(File plan) {
		this.plan = plan;
	}

	public String getPlanFileName() {
		return planFileName;
	}

	public void setPlanFileName(String planFileName) {
		this.planFileName = planFileName;
	}

	public String getPlanContentType() {
		return planContentType;
	}

	public void setPlanContentType(String planContentType) {
		this.planContentType = planContentType;
	}

	public String getSelectedRows() {
		return selectedRows;
	}

	public void setSelectedRows(String selectedRows) {
		this.selectedRows = selectedRows;
	}
	@JqDataSet(name = "requestDataSet", content = "{o:batchId},{o:merchName},{o:loanOrdId},{o:periods},{o:reason},{i:(format.time)({o:genTime})}")
	public DataSet getRequestDataSet() {
		return requestDataSet;
	}

	public void setRequestDataSet(DataSet requestDataSet) {
		this.requestDataSet = requestDataSet;
	}

	public List<ApplyRequest> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<ApplyRequest> requestList) {
		this.requestList = requestList;
	}

	public BigDecimal getCaptial() {
		return captial;
	}

	public void setCaptial(BigDecimal captial) {
		this.captial = captial;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public List<LoanOrdPlan> getNewPlans() {
		return newPlans;
	}

	public void setNewPlans(List<LoanOrdPlan> newPlans) {
		this.newPlans = newPlans;
	}

	public Boolean isQueryhis() {
		return queryhis;
	}

	public void setQueryhis(Boolean queryhis) {
		this.queryhis = queryhis;
	}

	public Merch getMerch() {
		return merch;
	}
	
	public void setMerch(Merch merch) {
		this.merch = merch;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getLoanOrdPlanId() {
		return loanOrdPlanId;
	}

	public void setLoanOrdPlanId(String loanOrdPlanId) {
		this.loanOrdPlanId = loanOrdPlanId;
	}

	public DebitBid getDebitBid() {
		return debitBid;
	}

	public void setDebitBid(DebitBid debitBid) {
		this.debitBid = debitBid;
	}

	public DebitBidService getDbs() {
		return dbs;
	}

	public void setDbs(DebitBidService dbs) {
		this.dbs = dbs;
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

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public InstBusiInfo getInstBusiInfo() {
		return instBusiInfo;
	}

	public void setInstBusiInfo(InstBusiInfo instBusiInfo) {
		this.instBusiInfo = instBusiInfo;
	}

	public MerchBusiInfo getMbi() {
		return mbi;
	}

	public void setMbi(MerchBusiInfo mbi) {
		this.mbi = mbi;
	}

	public TransMsg getTm() {
		return tm;
	}

	public void setTm(TransMsg tm) {
		this.tm = tm;
	}

	public Inst getInstx() {
		return instx;
	}

	public void setInstx(Inst instx) {
		this.instx = instx;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public FundFlow getFundFlow() {
		return fundFlow;
	}

	public void setFundFlow(FundFlow fundFlow) {
		this.fundFlow = fundFlow;
	}

	public LoanOrdPlan getLopn() {
		return lopn;
	}

	public void setLopn(LoanOrdPlan lopn) {
		this.lopn = lopn;
	}

	public FundFlow getRefundFundFlow() {
		return refundFundFlow;
	}

	public void setRefundFundFlow(FundFlow refundFundFlow) {
		this.refundFundFlow = refundFundFlow;
	}

	public String getMemox() {
		return memox;
	}

	public void setMemox(String memox) {
		this.memox = memox;
	}
	public static Date getDateAfter(Date d, int day) {   
        Calendar now = Calendar.getInstance();   
        now.setTime(d);   
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);   
        return now.getTime();   
    }

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getFreeze() {
		return freeze;
	}

	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}

	public List<LoanPdt> getPdtList() {
		return pdtList;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public String getMinTerm() {
		return minTerm;
	}

	public void setMinTerm(String minTerm) {
		this.minTerm = minTerm;
	}

	public String getMaxTerm() {
		return maxTerm;
	}

	public void setMaxTerm(String maxTerm) {
		this.maxTerm = maxTerm;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMinRate() {
		return minRate;
	}

	public void setMinRate(String minRate) {
		this.minRate = minRate;
	}

	public String getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(String maxRate) {
		this.maxRate = maxRate;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getMinloanAmount() {
		return minloanAmount;
	}

	public void setMinloanAmount(String minloanAmount) {
		this.minloanAmount = minloanAmount;
	}

	public String getMaxloanAmount() {
		return maxloanAmount;
	}

	public void setMaxloanAmount(String maxloanAmount) {
		this.maxloanAmount = maxloanAmount;
	}

	public String getPdtId() {
		return pdtId;
	}

	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}



	public List<LoanOrdPlan> getLoanOrdPlanList() {
		return loanOrdPlanList;
	}

	public void setLoanOrdPlanList(List<LoanOrdPlan> loanOrdPlanList) {
		this.loanOrdPlanList = loanOrdPlanList;
	}

	public BigDecimal getPlanTotal() {
		return planTotal;
	}

	public void setPlanTotal(BigDecimal planTotal) {
		this.planTotal = planTotal;
	}

	public String getOverdue() {
		return overdue;
	}

	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getUnFreezeMemo() {
		return unFreezeMemo;
	}

	public void setUnFreezeMemo(String unFreezeMemo) {
		this.unFreezeMemo = unFreezeMemo;
	}

	public BigDecimal getPtotal() {
		return Ptotal;
	}

	public void setPtotal(BigDecimal ptotal) {
		Ptotal = ptotal;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public Long getIloanOrd() {
		return iloanOrd;
	}

	public void setIloanOrd(Long iloanOrd) {
		this.iloanOrd = iloanOrd;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Date getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
	}

	public String getUnMemo() {
		return unMemo;
	}

	public void setUnMemo(String unMemo) {
		this.unMemo = unMemo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRefundPlanMemo() {
		return refundPlanMemo;
	}

	public void setRefundPlanMemo(String refundPlanMemo) {
		this.refundPlanMemo = refundPlanMemo;
	}
	
	
}
