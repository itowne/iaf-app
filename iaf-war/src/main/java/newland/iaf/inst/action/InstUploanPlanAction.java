package newland.iaf.inst.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.model.HicardPayCfg;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchType;
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
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 机构产品审核
 * @author new
 *
 */
@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "planUpload",interceptorRefs = {
	@InterceptorRef(value = "fileUpload", params = {"includeMethods", "upload"}),
	@InterceptorRef("base_stack")
})
@Results({
	@Result(name = "loanOrdList", type = "dispatcher",location = "/inst/loanord/planupload/loanOrdIndex.jsp"),
	@Result(name = "list", type = "JqgridJsonResult", params = {"name", "dataSet"}),
	@Result(name = "planlist", type = "JqgridJsonResult", params = {"name", "planDataSet"}),
	@Result(name = "toNext", location="/inst/loanord/planupload/demo.jsp"),
	//@Result(name = "viewLoanOrd", location="/inst/loanord/planupload/loanOrdDetail.jsp")
	@Result(name = "viewLoanOrd", location="/inst/loanord/planupload/demo.jsp")
    })
public class InstUploanPlanAction extends InstBaseAction {
	
	
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
	private String termType;
	
	@Resource(name="loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	
	@Resource(name = "loanOrdService")
	private LoanOrdService loanordservice;
	
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
			cond.setStatus(viewStat);
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
		if(StringUtils.isNotBlank(minDate)){
			cond.setStartexpiryDate(this.parser(minDate,Date.class));
		}
		
		if(StringUtils.isNotBlank(maxDate)){
			cond.setEndexpiryDate(this.parser(maxDate,Date.class));
		}
		
		if(StringUtils.isNotBlank(termType)&&StringUtils.isNotBlank(minTerm)&&StringUtils.isNotBlank(maxTerm)){
			cond.setTermType(TermType.valueOf(termType));
		}
		
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iloanOrd"));
		cond.setOrders(orderList);
		//this.loanOrdList = this.instLoanService.queryByCond(cond, page);
		this.loanOrdList = this.instLoanService.queryByCon(cond);
		List<LoanOrd> loanOrdListx = new ArrayList<LoanOrd>();
		if(!CollectionUtils.isEmpty(loanOrdList)){
			for (LoanOrd loanOrd : loanOrdList) {
				List<LoanOrdPlan> plans = loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
				if(plans.size()!=0){
					loanOrdListx.add(loanOrd);
				}
			}
		}
		List<Long> listId= new ArrayList<Long>();
		if(CollectionUtils.isNotEmpty(loanOrdListx)){
			loanOrdList.removeAll(loanOrdListx);
		}
		if(CollectionUtils.isNotEmpty(loanOrdList)){
			for (LoanOrd loanOrd : loanOrdList) {
				listId.add(loanOrd.getIloanOrd());
			}
		}
		if(CollectionUtils.isEmpty(listId)){
			this.loanOrdList = new ArrayList<LoanOrd>();
		}else{
			cond.setIloanOrdList(listId);
			this.loanOrdList = this.instLoanService.queryByCond(cond, page);
		}
		
		return "loanOrdList";
	}

	public String list() throws Exception{
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
	
	
	
	private DataSet planDataSet;
	
	public String planList() throws Exception{
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
			planDataSet.setGridModel(list);
			
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
			planDataSet.setRecords(page.getRecAmt());
			planDataSet.setTotal(page.getPageAmt());
			return "planlist";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String toNext(){
		payment = BigDecimal.ZERO;
		interest = BigDecimal.ZERO;
		captial = BigDecimal.ZERO;
		if (CollectionUtils.isEmpty(loanOrdList)) return "loanOrdList";
		this.loanOrd = this.loanOrdList.get(index);
		this.setSessionObj("loanOrd", loanOrd);
		merch = this.merchService.findMerchById(loanOrd.getImerch());
		return "toNext";
	}
	
	public String viewLoanOrd(){
		//loanOrd = this.loanOrdList.get(index);
		//this.setSessionObj("loanOrd", loanOrd);
		try{
			//this.plans = this.instLoanService.queryPlan(loanOrd);
			payment = BigDecimal.ZERO;
			interest = BigDecimal.ZERO;
			captial = BigDecimal.ZERO;
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
	private List<LoanOrdPlan> newPlans;
	

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal captial = BigDecimal.ZERO;

	private BigDecimal interest = BigDecimal.ZERO;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal payment = BigDecimal.ZERO;
	
	@Resource(name = "paymentCfg")
	private HicardPayCfg payCfg;
	
	public String upload() {
		newPlans = this.instLoanService.convertPlanFrom(plan);
		this.plans = this.instLoanService.overRidePlan(plans, newPlans);
		if (CollectionUtils.isEmpty(plans) == false){
			for (LoanOrdPlan p : plans){
				captial = captial.add(p.getCapital());
//				BigDecimal tradeRate = new BigDecimal(this.payCfg.getTradeRate());
//				BigDecimal minRate = new BigDecimal(this.payCfg.getMinRate());
//				BigDecimal maxRate = new BigDecimal(this.payCfg.getMaxRate());
//				Double r = tradeRate.doubleValue()/100;
//				BigDecimal rate = loanOrd.getQuota().multiply(new BigDecimal(r.toString()));
//				if(rate.compareTo(minRate)==-1){
//					rate=minRate;
//				}
//				if(rate.compareTo(maxRate)==1){
//					rate=maxRate;
//				}
				interest = interest.add(p.getInterest());
				payment = payment.add(p.getRepayment());
			}
		}
		return "viewLoanOrd";
	}


	public String applyPlan(){
		try{
			if (CollectionUtils.isEmpty(newPlans)) throw new Exception("还款计划未上传！");
			this.instLoanService.uploadLoanOrdPlan(loanOrd, newPlans, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			this.addActionError(e.getMessage());
		}
		return "viewLoanOrd";
	}
	
	/**
	 * 撤销
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception{
		pdtList=loanPdtService.queryAll();
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
		pdtList=loanPdtService.queryAll();
		return "loanOrdList";
	}


	@Override
	@Begin
	public String execute() throws Exception {
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
		this.setFlowType(FlowType.UPLOAD);
		pdtList=loanPdtService.queryAll();
		return "loanOrdList";
	}
	static Set<OrdStat> viewStat = new HashSet<OrdStat>();
	static{
		viewStat.add(OrdStat.AUDIT);
		viewStat.add(OrdStat.CREDITING);
		viewStat.add(OrdStat.REFUND);
		//viewStat.add(OrdStat.REFUNDING);
		viewStat.add(OrdStat.DELIN_QUENCY);
	}
	static Set<OrdStat> status = new HashSet<OrdStat>();
	static{
/*		status.add(OrdStat.ACCEPT);
		status.add(OrdStat.ACCEPT_OVERDUE);
		status.add(OrdStat.ACCEPT_REFUSE);
		status.add(OrdStat.AUDIT_REFUSE);*/
		status.add(OrdStat.AUDIT);
		status.add(OrdStat.CREDITING);
		status.add(OrdStat.REFUND);
		status.add(OrdStat.REFUNDING);
		status.add(OrdStat.DELIN_QUENCY);
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

	@JqDataSet(name = "dataSet", content = "{o:loanOrdId},{o:pdtType.desc},{o:pdtName},{f:(wanyuanFormatter)({o:quota})},{o:pdtTerm},{o:merchName},{i:(format.date)({o:expiryDate})},{o:ordStat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}


	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}


	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
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
	@JqDataSet(name="planDataSet", content = "{o:period},{i:(format.date)({o:refundDate})},{i:(format.money)({o:repayment})},{i:(format.money)({o:capital})},{i:(format.money)({o:interest})},{i:(format.money)({o:remainAmount})},{o:saveFlag}")
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

	public List<LoanOrdPlan> getNewPlans() {
		return newPlans;
	}

	public void setNewPlans(List<LoanOrdPlan> newPlans) {
		this.newPlans = newPlans;
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

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public List<LoanPdt> getPdtList() {
		return pdtList;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public MerchService getMerchService() {
		return merchService;
	}

	public void setMerchService(MerchService merchService) {
		this.merchService = merchService;
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
	
}
