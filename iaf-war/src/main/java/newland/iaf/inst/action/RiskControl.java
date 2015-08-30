package newland.iaf.inst.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.TransReport;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.risk.dao.RiskControlService;
import newland.iaf.base.service.CreditReportQueryService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.QueryType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchType;
import newland.iaf.merch.service.MerchService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
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
 * 风险控制页面
 * 
 * @author rabbit
 * 
 */
@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value="riskControl")
@Results({
	@Result(name = "list", type = "JqgridJsonResult", params = {"name", "dataSet"}),
	@Result(name = "loanOrdList", type = "dispatcher",location = "/inst/riskControl/riskControl.jsp"),
	@Result(name="detail",type="dispatcher",location="/inst/riskControl/riskControlDetail.jsp")
    })
public class RiskControl extends InstBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5895735768700360089L;

	@Resource(name = "com.newland.riskControlService")
	private RiskControlService riskControlService;

	@Resource(name = "creditReportQueryService")
	private CreditReportQueryService creditReportQueryService;
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;

	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;
	/**
	 * 撤机数
	 */
	private int uninstallNum;

	/**
	 * 上月POS交易额比前一个月下降比例
	 */
	private BigDecimal rate;

	/**
	 * 连续n天是否有交易记录
	 */
	private boolean hasTransLog;

	/**
	 * 商户目前还款逾期记录
	 */
	private int expireNum;

	/**
	 * 商户在平台内目前欠款总额
	 */
	private BigDecimal debitAmt;

	/**
	 * 由于未能按时还款，商户的清算账号已被借贷方冻结
	 */
	private boolean freeze;

	/**
	 * 上个月的交易额少于本期应还款金额
	 */
	private boolean xx;
	
	
	private DataSet dataSet;//用户JQGrid数据

	@Resource (name = "instLoanService")
	private InstLoanService instLoanService;
	
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
	private String pdtType;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String riskDays;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String riskRate;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String termType;
	
	private List<LoanPdt> pdtList;
	
	private String pdtId;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	
	private String endloanAmount;
	
	private String endterm;
	
	private String jzbeginDate;
	
	private String jzendDate;
	
	private String minRate;
	
	private String maxRate;
	
	private String rateType;
	
	private String maxTerm;
	
	@Override
	@Begin
	public String execute() throws Exception {
		pdtList=loanPdtService.queryAll();
		this.setFlowType(FlowType.UPLOAD);
		loanOrdId="";
		merchName="";
		term="";
		loanAmount="";
		endloanAmount="";
		pdtId="";
		term="";
		maxTerm="";
		jzbeginDate="";
		jzendDate="";
		termType="";
		rateType="";
		minRate="";
		maxRate="";
		ordStat="";
		return "loanOrdList";
	}
	
	public String list() throws Exception{
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			page.setPageOffset(dataSet.getPage() - 1);
			LoanOrdCondition cond = new LoanOrdCondition();
			cond.setShield(0);
			if(StringUtils.isNotBlank(loanOrdId)){
				cond.setLoanOrdId(loanOrdId+"%");
			}
			cond.setIinst(this.getInst().getIinst());
			if (StringUtils.isNotBlank(ordStat)){
				cond.setOrdStat(OrdStat.valueOf(ordStat));
			}else{
				cond.setStatus(viewStat);
			}
			if(StringUtils.isNotBlank(merchName)){
				cond.setMerchName(merchName);
			}
			if (StringUtils.isNotBlank(merchType)){
				cond.setMerchType(MerchType.valueOf(merchType));
			}
			if (StringUtils.isNotBlank(pdtType)){
				cond.setPdtType(PdtType.valueOf(pdtType));
			}
			if(StringUtils.isNotBlank(term)){
				cond.setMinTerm(Integer.parseInt(term));
			}
			if(StringUtils.isNotBlank(maxTerm)){
				cond.setMaxTerm(Integer.parseInt(maxTerm));
			}
			if(StringUtils.isNotBlank(termType)&&StringUtils.isNotBlank(term)&&StringUtils.isNotBlank(maxTerm)){
				cond.setTermType(TermType.valueOf(termType));
			}
			if(StringUtils.isNotBlank(pdtId)){
				LoanPdt lp = loanPdtService.getLoanPdtById(Long.parseLong(pdtId));
				if(lp!=null){
					cond.setIloanPdt(lp.getIloanPdtHis());
				}
			}
			if(StringUtils.isNotBlank(loanAmount)){
				cond.setMinLoanAmount(this.parser(loanAmount, BigDecimal.class).multiply(new BigDecimal(10000)));
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
			if(StringUtils.isNotBlank(endloanAmount)){
				cond.setMaxLoanAmount(this.parser(endloanAmount, BigDecimal.class).multiply(new BigDecimal(10000)));
			}
							
			if(StringUtils.isNotBlank(maxTerm)){
				cond.setMaxTerm(this.parser(maxTerm, Integer.class));
			}
			
			if(StringUtils.isNotBlank(jzbeginDate)){
				cond.setStartReqDate(this.parser(jzbeginDate, Date.class));
			}
			
			if(StringUtils.isNotBlank(jzendDate)){
				cond.setEndReqDate(this.parser(jzendDate, Date.class));
			}
			
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.desc("iloanOrd"));
			cond.setOrders(orderList);
			this.loanOrdList = this.instLoanService.queryByCond(cond, page);
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
	
	public String riskDetail() {
		loanOrd=loanOrdList.get(index);
		Merch merch = merchService.findMerchById(loanOrd.getImerch());
		riskControl(loanOrd.getImerch(),merch.getMerchNo(),loanOrd.getOrdDate());
		SysParam param = sysParamService.getSysParam(SysParamType.riskControl,
				SysParamName.RISK_CONTROL_POS_TRANS_DAYS);
		riskDays=param.getValue();
		SysParam param1 = sysParamService.getSysParam(SysParamType.riskControl,
				SysParamName.RISK_TRADE_RATE);
		riskRate=param1.getValue();
		return "detail";
	}

	private void riskControl(Long imerch, String merchNo, Date date) {
		uninstallNum = riskControlService.getUninstallPosNum(merchNo, date);
		rate = riskControlService.transLogAmtReduce(merchNo);
		hasTransLog = riskControlService.hasTransLog(merchNo);
		BigDecimal debit = creditReportQueryService.queryBalanceAmount(imerch);
		BigDecimal amt = riskControlService.getHcTransLogAmt(merchNo,
				DateUtils.addMonths(new Date(), -1));
		xx = debit.compareTo(amt) > 0;
		BigDecimal freezeAmt = creditReportQueryService
				.queryFreezeAmount(imerch);
		freeze = freezeAmt.compareTo(BigDecimal.ZERO) > 0;
		TransReport delin = creditReportQueryService.queryLoanRisk(imerch,
				QueryType.DELIN_QUENCY);
		expireNum = delin.getTransCount().intValue();
		debitAmt = creditReportQueryService.queryBalanceAmount(imerch);
	}
	
	static Set<OrdStat> viewStat = new HashSet<OrdStat>();
	static{
		//viewStat.add(OrdStat.AUDIT);
		viewStat.add(OrdStat.CREDITING);
		viewStat.add(OrdStat.REFUND);
		viewStat.add(OrdStat.REFUNDING);
		viewStat.add(OrdStat.DELIN_QUENCY);
	}
	static Set<OrdStat> status = new HashSet<OrdStat>();
	static{
		status.add(OrdStat.CREDITING);
		status.add(OrdStat.REFUND);
		status.add(OrdStat.REFUNDING);
		status.add(OrdStat.DELIN_QUENCY);
	}
	public int getUninstallNum() {
		return uninstallNum;
	}

	public void setUninstallNum(int uninstallNum) {
		this.uninstallNum = uninstallNum;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public boolean isHasTransLog() {
		return hasTransLog;
	}

	public void setHasTransLog(boolean hasTransLog) {
		this.hasTransLog = hasTransLog;
	}

	public int getExpireNum() {
		return expireNum;
	}

	public void setExpireNum(int expireNum) {
		this.expireNum = expireNum;
	}

	public BigDecimal getDebitAmt() {
		return debitAmt;
	}

	public void setDebitAmt(BigDecimal debitAmt) {
		this.debitAmt = debitAmt;
	}

	public boolean isFreeze() {
		return freeze;
	}

	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}

	public boolean isXx() {
		return xx;
	}

	public void setXx(boolean xx) {
		this.xx = xx;
	}

	@JqDataSet(name = "dataSet", content = "{o:loanOrdId},{o:pdtType.desc},{o:pdtName},{o:pdtQuoat},{o:pdtTerm},{o:pdtRate},{o:merchName},{i:(format.date)({o:ordDate})},{o:ordStat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<LoanOrd> getLoanOrdList() {
		return loanOrdList;
	}

	public void setLoanOrdList(List<LoanOrd> loanOrdList) {
		this.loanOrdList = loanOrdList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPdtId() {
		return pdtId;
	}

	public String getMaxTerm() {
		return maxTerm;
	}

	public void setMaxTerm(String maxTerm) {
		this.maxTerm = maxTerm;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
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

	public String getJzbeginDate() {
		return jzbeginDate;
	}

	public void setJzbeginDate(String jzbeginDate) {
		this.jzbeginDate = jzbeginDate;
	}

	public String getJzendDate() {
		return jzendDate;
	}

	public void setJzendDate(String jzendDate) {
		this.jzendDate = jzendDate;
	}

	public String getEndterm() {
		return endterm;
	}

	public void setEndterm(String endterm) {
		this.endterm = endterm;
	}

	public String getEndloanAmount() {
		return endloanAmount;
	}

	public void setEndloanAmount(String endloanAmount) {
		this.endloanAmount = endloanAmount;
	}

	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public List<LoanPdt> getPdtList() {
		return pdtList;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getRiskDays() {
		return riskDays;
	}

	public void setRiskDays(String riskDays) {
		this.riskDays = riskDays;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}

	public String getLoanOrdId() {
		return loanOrdId;
	}

	public InstLoanService getInstLoanService() {
		return instLoanService;
	}

	public void setInstLoanService(InstLoanService instLoanService) {
		this.instLoanService = instLoanService;
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

	public void setMerchName(String merchName) {
		this.merchName = merchName;
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

	public String getPdtType() {
		return pdtType;
	}

	public void setPdtType(String pdtType) {
		this.pdtType = pdtType;
	}

	public Set<OrdStat> getStatus() {
		return status;
	}

	public String getRiskRate() {
		return riskRate;
	}

	public void setRiskRate(String riskRate) {
		this.riskRate = riskRate;
	}

}
