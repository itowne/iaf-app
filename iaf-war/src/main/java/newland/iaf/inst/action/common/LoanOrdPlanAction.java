package newland.iaf.inst.action.common;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.GateWayType;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.HicardPaymentService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.TransMsgService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
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
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "planMgr",interceptorRefs = {
		@InterceptorRef(value = "fileUpload", params = {"includeMethods", "upload"}),
		@InterceptorRef("base_stack")
		})
@Results({
	@Result(name = "loanPlans", type = "dispatcher",location = "/inst/loanord/common/loanPlans.jsp"),
	@Result(name = "crediplans", type = "dispatcher",location = "/inst/loanord/common/crediplans.jsp"),
	@Result(name = "toUpload", type = "dispatcher",location = "/inst/loanord/common/toUpload.jsp"),
	@Result(name = "deny", type = "dispatcher",location = "/common/deny.jsp"),
	@Result(name = "planlist", type = "JqgridJsonResult", params = {"name", "dataSet"})
    })
public class LoanOrdPlanAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataSet dataSet;
	
	@Resource(name = "fundFlowService")
	private FundFlowService fundFlowService;
	
	@Resource(name = "transMsgService")
	private TransMsgService transMsgService;
	
	@Resource(name = "hicardPaymentService")
	private HicardPaymentService paymentService;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrdPlan> plans ;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	protected LoanOrd loanOrd;
	
	private String flag="upload";
	
	private String planFlag;
	
	@Resource(name="loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	
	private BigDecimal total ;
	
	private List<FundFlow> FundFlowList;
	
	private String transType;

	private String orderNo;
	
	private String transDate;
	
	private List<Merch> merchList;
	
	private TransMsg trans;
	
	private FundFlow fundFlow;
	
	private BigDecimal charge;
	
	private Merch merch;
	
	private MerchBusiInfo merchBusiInfo;
	
	private InstBusiInfo instBusiInfo;
	
	private Inst ist;
	
	public String planList() throws Exception{
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			if (dataSet == null) {
				dataSet = new DataSet();
				page.setPageOffset(0);
			} else {
				page.setPageOffset(dataSet.getPage() - 1);
			}

			//==================================================
			List<LoanOrdPlan> planList = this.instLoanService.queryPlan(loanOrd);
			
			if(CollectionUtils.isNotEmpty(planList)&&planFlag.equals("saved")){
				this.plans = this.instLoanService.queryPlan(loanOrd);
			}
			
			//==================================================
//			if(!planFlag.equals("upload")){
//				this.plans = new ArrayList<LoanOrdPlan>();
//			}
//			if(planFlag.equals("saved")){
//				this.plans = this.instLoanService.queryPlan(loanOrd);
//			}
//			
//			List<LoanOrdPlan> planList = this.instLoanService.queryPlan(loanOrd);
//			if(CollectionUtils.isNotEmpty(planList)){
//				if(!planFlag.equals("upload")){
//					this.plans=planList;
//				}
//			}
			int total = plans.size();
			int rec = 0;
			if(total!=0){
				rec = total/10;
				if(total%10!=0){
					rec=rec+1;
				}
			}
			List<LoanOrdPlan> list = new ArrayList<LoanOrdPlan>();
			if(CollectionUtils.isNotEmpty(this.plans)){
				if(page.getPageOffset()==(rec-1)){
					list = this.plans.subList(page.getPageOffset()*10,page.getPageOffset()*10+total%10);
				}else{
					if(page.getPageOffset()==0){
						list= this.plans.subList(0, 10);
					}
					if(page.getPageOffset()>=1){
						list= this.plans.subList(page.getPageOffset()*10, page.getPageOffset()*10+10);
					}
				}
			}
			dataSet.setGridModel(list);
			
			if(page.getPageOffset()>=1){
				if(page.getPageOffset()==(rec-1)){
					page.setRecOffset(10*page.getPageOffset());
					page.setSize(total%10);
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
			dataSet.setRecords(page.getRecAmt());
			dataSet.setTotal(page.getPageAmt());
			return "planlist";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String crediplans() throws Exception{
	        FundFlowList = fundFlowService.queryByIloanOrdAndType(loanOrd.getIloanOrd(), FundFlowType.CREDIT);
	        if(!CollectionUtils.isEmpty(FundFlowList)){
	            for(FundFlow fundFlow : FundFlowList){
	                TransMsg tm = transMsgService.findById(fundFlow.getOtherSysTraceNo());
	                if(tm != null && !StringUtils.isBlank(tm.getRespCode()) && tm.getRespCode().equals("00")){
	                    orderNo = tm.getOrderNo();
	                    transDate = tm.getTransDate().toString();
	                }
	            }

	            transType = GateWayType.HICARD.name();
	        }
	        merch = merchService.findMerchById(loanOrd.getImerch());
	        merchBusiInfo = merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
	        instBusiInfo = instService.findInstBusiInfoByiinst(loanOrd.getIinst());
	        ist = instService.findInstById(loanOrd.getIinst());
	        if (this.allowRefundPlan() == false){
				return "deny";
			}
			this.loanOrd = (LoanOrd)super.getSessionObj("loanOrd");
			if (loanOrd == null) {
				addActionError("订单信息未找到！");
				return "error";
			}
			try {
				this.plans = this.instLoanService.queryPlan(loanOrd);
				if(CollectionUtils.isNotEmpty(plans)){
					flag="modify";
				}
				captial = BigDecimal.ZERO;
				interest=BigDecimal.ZERO;
				payment=BigDecimal.ZERO;
				if (CollectionUtils.isEmpty(plans) == false){
					for (LoanOrdPlan plan:plans){
						captial = captial.add(plan.getCapital());
						interest = interest.add(plan.getInterest()).setScale(1,BigDecimal.ROUND_HALF_UP);
						payment = payment.add(plan.getRepayment());
					}
				}
			//BigDecimal quota = loanOrd.getQuota().divide(new BigDecimal(10000), BigDecimal.ROUND_UNNECESSARY).setScale(2);
			//loanOrd.setQuota(quota);
			total = loanOrd.getQuota().add(interest).setScale(1);
			//this.setSessionObj("total", total);
			//this.setSessionObj("interest", interest);
			this.setSessionObj("loanOrd", loanOrd);
			this.setFlowType(FlowType.UPLOAD);
			} catch (Exception e) {
				addActionError("查询计划出错！");
				logger.error("查询计划出错", e);
			}
			return "crediplans";
	    }
	
	@Begin
	public String execute(){
        FundFlowList = fundFlowService.queryByIloanOrdAndType(loanOrd.getIloanOrd(), FundFlowType.CREDIT);
        if(!CollectionUtils.isEmpty(FundFlowList)){
            for(FundFlow fundFlow : FundFlowList){
                TransMsg tm = transMsgService.findById(fundFlow.getOtherSysTraceNo());
                if(tm != null && !StringUtils.isBlank(tm.getRespCode()) && tm.getRespCode().equals("00")){
                    orderNo = tm.getOrderNo();
                    transDate = tm.getTransDate().toString();
                }
            }

            transType = GateWayType.HICARD.name();
        }
        merch = merchService.findMerchById(loanOrd.getImerch());
        merchBusiInfo = merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
        try{
        	instBusiInfo = instService.findInstBusiInfoByiinst(loanOrd.getIinst());
        }catch(Exception e){
        	addActionError("机构基本信息不存在！");
			logger.error("机构基本信息不存在", e);
        }
        ist = instService.findInstById(loanOrd.getIinst());
		if (this.allowRefundPlan() == false){
			return "deny";
		}
		this.loanOrd = (LoanOrd)super.getSessionObj("loanOrd");
		if (loanOrd == null) {
			addActionError("订单信息未找到！");
			return "error";
		}
		try {
			this.plans = this.instLoanService.queryPlan(loanOrd);
			if(CollectionUtils.isNotEmpty(plans)){
				flag="modify";
			}
			captial = BigDecimal.ZERO;
			interest=BigDecimal.ZERO;
			payment=BigDecimal.ZERO;
			if (CollectionUtils.isEmpty(plans) == false){
				for (LoanOrdPlan plan:plans){
					captial = captial.add(plan.getCapital());
					interest = interest.add(plan.getInterest()).setScale(1,BigDecimal.ROUND_HALF_UP);
					payment = payment.add(plan.getRepayment());
				}
			}
		//BigDecimal quota = loanOrd.getQuota().divide(new BigDecimal(10000), BigDecimal.ROUND_UNNECESSARY).setScale(2);
		//loanOrd.setQuota(quota);
		total = loanOrd.getQuota().add(interest).setScale(1);
		//this.setSessionObj("total", total);
		//this.setSessionObj("interest", interest);
		this.setSessionObj("loanOrd", loanOrd);
		this.setFlowType(FlowType.UPLOAD);
		} catch (Exception e) {
			addActionError("查询计划出错！");
			logger.error("查询计划出错", e);
		}
		return "loanPlans";
	}
	
	
	public String applyPlan(){
		try{
			if (CollectionUtils.isEmpty(newPlans)) throw new Exception("还款计划未上传！");
			this.instLoanService.uploadLoanOrdPlan(loanOrd, newPlans, getUserSession(), "", getIpaddr());
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
			//this.setSessionObj("total", total);
			//this.setSessionObj("interest", interest);
			this.setSessionObj("loanOrd", loanOrd);
			flag="modify";
			planFlag="saved";
		}catch(Exception e){
			this.addActionError(e.getMessage());
		}
		return "loanPlans";
	}
	
	private String planFileName;
	
	private File plan;
	
	private String planContentType;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal captial = BigDecimal.ZERO;

	private BigDecimal interest = BigDecimal.ZERO;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal payment = BigDecimal.ZERO;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrdPlan> newPlans;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrdPlan> oldPlans;
	
	public String upload() throws Exception {
		payment = BigDecimal.ZERO;
		interest = BigDecimal.ZERO;
		captial = BigDecimal.ZERO;
		oldPlans = plans;
		//List<LoanOrdPlan> newPlans= new ArrayList<LoanOrdPlan>();
		try{
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
				flag = "save";
			}
			this.plans = this.instLoanService.overRidePlan(plans, newPlans);
			if (CollectionUtils.isEmpty(plans) == false){
				for (LoanOrdPlan p : plans){
					captial = captial.add(p.getCapital());
					interest = interest.add(p.getInterest()).setScale(2, BigDecimal.ROUND_HALF_UP);
					payment = payment.add(p.getRepayment());
				}
			}
			total = loanOrd.getQuota().add(interest).setScale(2, BigDecimal.ROUND_HALF_UP);
			planFlag="upload";
		}catch(Exception e){
			e.printStackTrace();
			this.addActionError("还款计划期数,本金 ,利率请填写数字!");
		}
		return "loanPlans";
	}
	
	public String toUpload(){
		//plans = oldPlans;
		return "toUpload";
	}
	
	@JqDataSet(name="dataSet", content = "{o:period},{i:(format.date)({o:refundDate})},{i:(format.money)({o:repayment})},{i:(format.money)({o:capital})},{i:(format.money)({o:interest})},{i:(format.money)({o:remainAmount})},{o:saveFlag}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public String getPlanFileName() {
		return planFileName;
	}

	public void setPlanFileName(String planFileName) {
		this.planFileName = planFileName;
	}

	public File getPlan() {
		return plan;
	}

	public void setPlan(File plan) {
		this.plan = plan;
	}

	public String getPlanContentType() {
		return planContentType;
	}

	public void setPlanContentType(String planContentType) {
		this.planContentType = planContentType;
	}

	public List<LoanOrdPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<LoanOrdPlan> plans) {
		this.plans = plans;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
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
	public List<LoanOrdPlan> getOldPlans() {
		return oldPlans;
	}
	public void setOldPlans(List<LoanOrdPlan> oldPlans) {
		this.oldPlans = oldPlans;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getPlanFlag() {
		return planFlag;
	}
	public void setPlanFlag(String planFlag) {
		this.planFlag = planFlag;
	}

	public List<FundFlow> getFundFlowList() {
		return FundFlowList;
	}

	public void setFundFlowList(List<FundFlow> fundFlowList) {
		FundFlowList = fundFlowList;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public List<Merch> getMerchList() {
		return merchList;
	}

	public void setMerchList(List<Merch> merchList) {
		this.merchList = merchList;
	}

	public TransMsg getTrans() {
		return trans;
	}

	public void setTrans(TransMsg trans) {
		this.trans = trans;
	}

	public FundFlow getFundFlow() {
		return fundFlow;
	}

	public void setFundFlow(FundFlow fundFlow) {
		this.fundFlow = fundFlow;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public MerchBusiInfo getMerchBusiInfo() {
		return merchBusiInfo;
	}

	public void setMerchBusiInfo(MerchBusiInfo merchBusiInfo) {
		this.merchBusiInfo = merchBusiInfo;
	}

	public InstBusiInfo getInstBusiInfo() {
		return instBusiInfo;
	}

	public void setInstBusiInfo(InstBusiInfo instBusiInfo) {
		this.instBusiInfo = instBusiInfo;
	}

	public Inst getIst() {
		return ist;
	}

	public void setIst(Inst ist) {
		this.ist = ist;
	}

}
