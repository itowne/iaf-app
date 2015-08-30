package newland.iaf.backstage.action;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.backstage.service.BackStageLoanService;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.OperLogService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.service.MerchService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.ohuyo.commons.query.criterion.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

@ParentPackage("struts")
@Namespace("/backstage")
@Action(value = "loanOrdMgr")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "index",  type = "dispatcher",location = "/backstage/loanordmgr/index.jsp"),
	@Result(name = "viewLoanOrd",  type = "dispatcher",location = "/backstage/loanordmgr/loanOrdDetail.jsp")
    })
public class LoanOrdMgrAction extends BSBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource (name = "backStageLoanService")
	private BackStageLoanService backStageLoanService;
	@Resource (name = "operLogService")
	private OperLogService operLogService;
	
	private DataSet dataSet;
	
	private int index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrd> loanOrdList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String merchName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String instName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanOrdId;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String ordStat;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrd loanOrd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<FundFlow> fundFlowList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<OperLog> operLogs;
	
	private String loanAmount;
	
	private String endloanAmount;
	
	private String endterm;
	
	private String pdtId;
	
	private String minRate;
	
	private String maxRate;
	
	private String rateType;
	
	private List<FundFlow> creditFF;
	
	private List<FundFlow> refundFF;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	static Set<OrdStat> refuseStat = new HashSet<OrdStat>();
	static {
		refuseStat.add(OrdStat.ACCEPT_REFUSE);
		refuseStat.add(OrdStat.AUDIT_REFUSE);
	}
	
	static Set<OrdStat> creditStat = new HashSet<OrdStat>();
	static {
		creditStat.add(OrdStat.CREDITING);
		creditStat.add(OrdStat.DELIN_QUENCY);
		creditStat.add(OrdStat.REFUND);
		creditStat.add(OrdStat.REFUNDING);
	}
	
	public String loanQuery(){
		return "index";
	}
	
	private List<LoanPdt> pdtList;
	
	private String termType;
	
	private String term;
	
	private String shield;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	private List<Inst> instList;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	private Merch merch ;
	private Inst inst;
	
	@Resource(name="com.newland.iaf.merchService")
	private MerchService merchService;
	private List<LoanOrdPlan> planList;
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal interestTotal;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal amtTotal;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal perMonth;
	@Begin
	public String execute(){
		pdtList=loanPdtService.queryAll();
		instList = instService.queryInst();
		ordStat="";
		instName="";
		merchName="";
		term="";
		termType="";
		minRate="";
		maxRate="";
		loanAmount="";
		loanOrdId="";
		endloanAmount="";
		shield="";
		rateType="";
		endterm="";
		return "index";
	}
	
	static Set<OrdStat> status = new HashSet<OrdStat>();
	static{
		status.add(OrdStat.CREDITING);
		status.add(OrdStat.REFUND);
		status.add(OrdStat.REFUNDING);
		status.add(OrdStat.DELIN_QUENCY);
		status.add(OrdStat.ACCEPT);
		status.add(OrdStat.ACCEPT_OVERDUE);
		status.add(OrdStat.ACCEPT_REFUSE);
		status.add(OrdStat.APPLY);
		status.add(OrdStat.APPLY_OVERDUE);
		status.add(OrdStat.AUDIT);
		status.add(OrdStat.AUDIT_REFUSE);
		status.add(OrdStat.PAID_UP_LOAN);
		status.add(OrdStat.CANCEL);
	}
	@Resource(name = "loanOrdService")
	private LoanOrdService loanordservice;
	
	public void query(Page page) throws Exception{
		LoanOrdCondition cond = new LoanOrdCondition();
		if(StringUtils.isNotBlank(merchName)){
			cond.setMerchName(merchName+"%");
		}
		
		if(StringUtils.isNotBlank(instName)){
			cond.setIinst(Long.parseLong(instName));
		}
		if(StringUtils.isNotBlank(loanAmount)){
			cond.setMinLoanAmount(this.parser(loanAmount, BigDecimal.class).multiply(new BigDecimal(10000)));
		}
		if(StringUtils.isNotBlank(endloanAmount)){
			cond.setMaxLoanAmount(this.parser(endloanAmount, BigDecimal.class).multiply(new BigDecimal(10000)));
		}
		
		if(StringUtils.isNotBlank(termType)&&StringUtils.isNotBlank(term)&&StringUtils.isNotBlank(endterm)){
			cond.setTermType(TermType.valueOf(termType));
		}
		
		if(StringUtils.isNotBlank(term)){
			cond.setMinTerm(this.parser(term, Integer.class));
		}
		
		if(StringUtils.isNotBlank(endterm)){
			cond.setMaxTerm(this.parser(endterm, Integer.class));
		}
		if(StringUtils.isNotBlank(shield)){
			cond.setShield(Integer.parseInt(shield));
		}
		
		if(StringUtils.isNotBlank(loanOrdId)){
			cond.setLoanOrdId(loanOrdId.trim()+"%");
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
		
		if (StringUtils.isNotBlank(ordStat)){
			cond.setOrdStat(OrdStat.valueOf(ordStat));
		}else{
			cond.setStatus(status);
		}
		
		if(StringUtils.isNotBlank(pdtId)){
			LoanPdt lp = loanPdtService.getLoanPdtById(Long.parseLong(pdtId));
			if(lp!=null){
				cond.setIloanPdt(lp.getIloanPdtHis());
			}
		}
		//cond.setShield(new Integer(0));
		
		this.loanOrdList = this.backStageLoanService.queryLoanOrds(cond, page);
	}
	
	public String list(){
		try{
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			page.setPageOffset(dataSet.getPage() - 1);
			this.query(page);
			dataSet.setGridModel(this.loanOrdList);
			dataSet.setRecords(loanOrdList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
		}catch(Exception e){
			logger.error("查询订单列表出错", e);
		}
		return "list";
	}
	
	static Set<FundFlowType> types = new HashSet<FundFlowType>();
	static {
		types.add(FundFlowType.REFUND);
		types.add(FundFlowType.OTH_REFUND);
	}
	
	static Set<FundFlowType> credittypes = new HashSet<FundFlowType>();
	static {
		credittypes.add(FundFlowType.CREDIT);
		credittypes.add(FundFlowType.OTH_CREDIT);
	}
	
	public String viewLoanOrd(){
		this.loanOrd = this.loanOrdList.get(index);
		if (loanOrd == null) {
			addActionError("订单数据有误！");
			return "index";
		}
		merch = merchService.findMerchById(loanOrd.getImerch());
		
		try{
			planList = this.loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
			interestTotal = new BigDecimal(0);
			amtTotal = new BigDecimal(0);
			perMonth = new BigDecimal(0);		
			for (LoanOrdPlan plan : planList) {
				perMonth = plan.getRepayment();
				interestTotal = interestTotal
						.add(plan.getInterest() == null ? new BigDecimal(0) : plan
								.getInterest());
				amtTotal = amtTotal.add(
						plan.getCapital() == null ? new BigDecimal(0) : plan
								.getCapital()).add(
						plan.getInterest() == null ? new BigDecimal(0) : plan
								.getInterest());
			}
			
			inst = instService.getInstById(loanOrd.getIinst());
			this.creditFF = this.backStageLoanService.queryFundFlowBy(credittypes,loanOrd.getIloanOrd());
			this.refundFF = this.backStageLoanService.queryFundFlowBy(types,loanOrd.getIloanOrd());
			this.operLogs = this.operLogService.queryByOrdId(loanOrd.getLoanOrdId());
		}catch(Exception e){
			addActionError("查询记录出错");
			logger.error("查询记录出错",e);
			return "index";
		}
		return "viewLoanOrd";
	}
	
	public String returnAction(){
		return "index";
	}
	
	public String shield(){
		pdtList=loanPdtService.queryAll();
		instList = instService.queryInst();
		try {
			//this.loanOrd = this.loanOrdList.get(index);
			this.backStageLoanService.shield(loanOrd, getUserSession(), getIpaddr());
		} catch (Exception e) {
			addActionError("屏蔽订单出错!错误原因：" + e.getMessage());
			logger.error("屏蔽订单出错", e);
		}
		return "index";
	}
	
	public String reShield(){
		pdtList=loanPdtService.queryAll();
		instList = instService.queryInst();
		try {
			//this.loanOrd = this.loanOrdList.get(index);
			this.backStageLoanService.reShield(loanOrd, getUserSession(), getIpaddr());
		} catch (Exception e) {
			addActionError("屏蔽订单出错!错误原因：" + e.getMessage());
			logger.error("屏蔽订单出错", e);
		}
		return "index";
	}

	
	@JqDataSet(content = "{o:loanOrdId},{o:merchName},{o:instName},{o:pdtName},{f:(wanyuanFormatter)({o:quota})},{o:pdtRate},{o:pdtTerm},,{o:ordStat.desc},{o:shield}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<LoanOrd> getLoanOrdList() {
		return loanOrdList;
	}

	public void setLoanOrdList(List<LoanOrd> loanOrdList) {
		this.loanOrdList = loanOrdList;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
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

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}


	public List<OperLog> getOperLogs() {
		return operLogs;
	}

	public void setOperLogs(List<OperLog> operLogs) {
		this.operLogs = operLogs;
	}

	public List<FundFlow> getFundFlowList() {
		return fundFlowList;
	}

	public void setFundFlowList(List<FundFlow> fundFlowList) {
		this.fundFlowList = fundFlowList;
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public List<LoanPdt> getPdtList() {
		return pdtList;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public Set<OrdStat> getStatus() {
		return status;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getEndloanAmount() {
		return endloanAmount;
	}

	public void setEndloanAmount(String endloanAmount) {
		this.endloanAmount = endloanAmount;
	}

	public String getEndterm() {
		return endterm;
	}

	public void setEndterm(String endterm) {
		this.endterm = endterm;
	}

	public String getPdtId() {
		return pdtId;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
	}

	public String getShield() {
		return shield;
	}

	public void setShield(String shield) {
		this.shield = shield;
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

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public List<LoanOrdPlan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<LoanOrdPlan> planList) {
		this.planList = planList;
	}

	public BigDecimal getInterestTotal() {
		return interestTotal;
	}

	public void setInterestTotal(BigDecimal interestTotal) {
		this.interestTotal = interestTotal;
	}

	public BigDecimal getAmtTotal() {
		return amtTotal;
	}

	public void setAmtTotal(BigDecimal amtTotal) {
		this.amtTotal = amtTotal;
	}

	public BigDecimal getPerMonth() {
		return perMonth;
	}

	public void setPerMonth(BigDecimal perMonth) {
		this.perMonth = perMonth;
	}

	public List<FundFlow> getCreditFF() {
		return creditFF;
	}

	public void setCreditFF(List<FundFlow> creditFF) {
		this.creditFF = creditFF;
	}

	public List<FundFlow> getRefundFF() {
		return refundFF;
	}

	public void setRefundFF(List<FundFlow> refundFF) {
		this.refundFF = refundFF;
	}


}
