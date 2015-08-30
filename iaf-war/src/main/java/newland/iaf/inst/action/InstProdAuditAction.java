package newland.iaf.inst.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.Province;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchType;
import newland.iaf.merch.service.MerchService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.util.CollectionUtils;

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
@Action(value = "prodAudit")
@Results({
	@Result(name = "loanOrdList", type = "dispatcher",location = "/inst/loanord/audit/loanOrdIndex.jsp"),
	@Result(name = "acceptLoanOrdList",type = "dispatcher",location = "/inst/loanord/planupload/demo.jsp"),
	@Result(name="audiSuccess",type="dispatcher",location="/inst/loanord/audit/audiSuccess.jsp"),
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "toNext", location="/inst/loanord/audit/demo.jsp"),
	//@Result(name = "viewLoanOrd", location="/inst/loanord/audit/loanOrdDetail.jsp")
	@Result(name = "viewLoanOrd", location="/inst/loanord/audit/demo.jsp")
    })
public class InstProdAuditAction extends InstBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataSet dataSet;//用户JQGrid数据

	@Resource (name = "instLoanService")
	private InstLoanService instLoanService;
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
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
	private Merch merch;
	
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
	private List<OperLog> operLogs;
	
	private BigDecimal quota;
	
	private BigDecimal rate;
	
	private Integer loanOrdTerm;
	
	private String unmemo;
	private List<LoanPdt> pdtList;
	
	private String pdtId;
	
	private String minloanAmount;
	
	private String maxloanAmount;
	
	private String minTerm;
	
	private String maxTerm;
	
	private String minacceptDate;
	
	private String maxacceptDate;
	
	private String minapplyDate;
	
	private String maxapplyDate;
	
	private DebitBid debitBid;
	
	private String area;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	
	@Resource(name = "debitBidService")
	private DebitBidService dbs;
	
	@Resource(name = "provinceService")
	private ProvinceService ps;
	
	private LoanPdt lp;
	
	
	@Resource(name = "loanPdtService")
	private LoanPdtService lps;
	
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService lops;
	
	private Integer periods;
	
	private String termType;
	
	private String rateType;
	
	public String query(Page page) throws Exception{
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setShield(0);
		if(StringUtils.isNotBlank(loanOrdId)){
			cond.setLoanOrdId(loanOrdId+"%");
		}
		cond.setIinst(this.getInst().getIinst());
		if (StringUtils.isNotBlank(ordStat)){
			cond.setOrdStat(OrdStat.valueOf(ordStat));
		}else{
			cond.setStatus(status);
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
		if(StringUtils.isNotBlank(pdtId)){
			LoanPdt lp = loanPdtService.getLoanPdtById(Long.parseLong(pdtId));
			if(lp!=null){
				cond.setIloanPdt(lp.getIloanPdtHis());
			}
		}
		if(StringUtils.isNotBlank(termType)){
			cond.setTermType(TermType.valueOf(termType));
		}
		
		if(StringUtils.isNotBlank(minloanAmount)){
			cond.setMinLoanAmount(new BigDecimal(minloanAmount).multiply(new BigDecimal(10000)));
		}
		if(StringUtils.isNotBlank(maxloanAmount)){
			cond.setMaxLoanAmount(new BigDecimal(maxloanAmount).multiply(new BigDecimal(10000)));
		}
		
		if(StringUtils.isNotBlank(minTerm)){
			cond.setMinTerm(Integer.parseInt(minTerm));
		}
		if(StringUtils.isNotBlank(maxTerm)){
			cond.setMaxTerm(Integer.parseInt(maxTerm));
		}
		
		if(StringUtils.isNotBlank(minacceptDate)){
			cond.setAcceptDate(this.parser(minacceptDate,Date.class));
		}
		if(StringUtils.isNotBlank(maxacceptDate)){
			cond.setMaxAcceptDate(this.parser(maxacceptDate, Date.class));
		}
		if(StringUtils.isNotBlank(minapplyDate)){
			cond.setStartexpiryDate(this.parser(minapplyDate, Date.class));
		}
		if(StringUtils.isNotBlank(maxapplyDate)){
			cond.setEndexpiryDate(this.parser(maxapplyDate, Date.class));
		}
//		BigDecimal bd = this.parser(loanAmount, BigDecimal.class);
//		if(bd!=null){
//			cond.setLoanAmount(bd.multiply(new BigDecimal(10000)));
//		}
//		cond.setTerm(this.parser(term, Integer.class));
//		cond.setAcceptDate(this.parser(acceptDate, Date.class));
//		cond.setApplyDate(this.parser(applyDate, Date.class));
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iloanOrd"));
		cond.setOrders(orderList);
		this.loanOrdList = this.instLoanService.queryByCondProAudi(cond,page);
		return "loanOrdList";
	}
	
	public String toNext(){
		if (CollectionUtils.isEmpty(loanOrdList)) return "loanOrdList";
		this.loanOrd = this.loanOrdList.get(index);
		this.setSessionObj("loanOrd", loanOrd);
		merch = this.merchService.findMerchById(loanOrd.getImerch());
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
					if (!CollectionUtils.isEmpty(list)) {
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
		if(StringUtils.isNotBlank(loanOrd.getLoanPdtId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanPdtId()).size();
		}else{
			periods=0;
		}
		return "toNext";
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
			logger.error("列表失败", e);
			throw e;
		}
		
	}
	
	public String viewLoanOrd(){
		/**
		 * 通过toNext查看
		 */
		//loanOrd = this.loanOrdList.get(index);
		//this.setSessionObj("loanOrd", loanOrd);
		//this.operLogs = this.instLoanService.queryOperLogBy(loanOrd);
		return "viewLoanOrd";
	}
	/**
	 * 审核
	 * @return
	 * @throws Exception
	 */
	public String audit()throws Exception{
		if (StringUtils.isEmpty(memo)){
			addActionError("备注信息不能为空！");
			return "viewLoanOrd";
		}
		if (quota == null){
			addActionError("金额不能为空！");
			return "viewLoanOrd";
		}
		if (loanOrdTerm==null){
			addActionError("期限不能为空！");
			return "viewLoanOrd";
		}
		if (rate==null){
			addActionError("利率不能为空！");
			return "viewLoanOrd";
		}
		try{
			if(StringUtils.isNotBlank(termType)){
				loanOrd.setRateType(RateType.valueOf(rateType));
			}
			if(StringUtils.isNotBlank(rateType)){
				loanOrd.setTermType(TermType.valueOf(termType));
			}
			loanOrd.setQuota(quota.multiply(new BigDecimal(10000)));
			
			loanOrd.setTerm(loanOrdTerm);
			loanOrd.setRate(rate);
			this.instLoanService.audit(loanOrd, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			this.addActionError("审核失败！失败原因：" + e.getMessage());
			logger.error("审核失败", e);
			return "viewLoanOrd";
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
		
		this.setSessionObj("loanOrd", loanOrd);
		this.setFlowType(FlowType.UPLOAD);
		return "audiSuccess";
	}
	
	/**
	 * 审核不通过
	 * @return
	 * @throws Exception
	 */
	public String refuse() throws Exception{
		pdtList=loanPdtService.queryAll();
		if (StringUtils.isEmpty(unmemo)){
			addActionError("备注信息不能为空！");
			return "viewLoanOrd";
		}
		try{
			this.instLoanService.refuse(loanOrd, getUserSession(), unmemo, getIpaddr());
		}catch(Exception e){
			this.addActionError("拒决失败！失败原因：" + e.getMessage());
			logger.error("拒决失败", e);
			return "viewLoanOrd";
		}
		return "loanOrdList";
	}
	/**
	 * 撤销
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception{
		pdtList=loanPdtService.queryAll();
		if (StringUtils.isEmpty(memo)){
			addActionError("备注信息不能为空！");
			return "viewLoanOrd";
		}
		try{
			this.instLoanService.cancel(loanOrd, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			this.addActionError("撤销失败！失败原因：" + e.getMessage());
			logger.error("撤销失败失败", e);
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

	public	String toPlan(){
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
		BigDecimal interest = BigDecimal.ZERO;
		if(loanOrd!=null){
			
			List<LoanOrdPlan> loanOrdPlans=lops.queryByLoanOrdId(loanOrd.getLoanPdtId());
			if(!CollectionUtils.isEmpty(loanOrdPlans)){
				for (LoanOrdPlan loanOrdPlan : loanOrdPlans) {
					interest=interest.add(loanOrdPlan.getInterest()).setScale(1);
				}
			}
		}
		//BigDecimal total = loanOrd.getQuota().add(interest).setScale(1);

		//this.setSessionObj("total", total);
		//this.setSessionObj("interest", interest);
		this.setSessionObj("loanOrd", loanOrd);
		this.setFlowType(FlowType.UPLOAD);
		return "acceptLoanOrdList";
	}

	@Override
	@Begin
	@End
	public String execute() throws Exception {
		this.setFlowType(FlowType.AUDIT);
		pdtList=loanPdtService.queryAll();
		loanOrdId="";
		pdtId="";
		minloanAmount="";
		maxloanAmount="";
		minTerm="";
		maxTerm="";
		minacceptDate="";
		maxacceptDate="";
		minapplyDate="";
		maxapplyDate="";
		termType="";
		return "loanOrdList";
	}
	
	public String loanQueryPage() {
		pdtList=loanPdtService.queryAll();
		return "loanOrdList";
	}
	
	static Set<OrdStat> status = new HashSet<OrdStat>();
	static {
		status.add(OrdStat.ACCEPT);
		status.add(OrdStat.ACCEPT_OVERDUE);
		status.add(OrdStat.AUDIT_REFUSE);
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

	@JqDataSet(content = "{o:loanOrdId},{o:pdtType.desc},{o:pdtName},{f:(wanyuanFormatter)({o:quota})},{o:pdtTerm},{o:merchName},{i:(format.date)({o:acceptDate})},{i:(format.date)({o:expiryDate})},{o:ordStat.desc}")
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

	public List<LoanPdt> getPdtList() {
		return pdtList;
	}


	public Integer getPeriods() {
		return periods;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}

	public DebitBid getDebitBid() {
		return debitBid;
	}

	public LoanPdt getLp() {
		return lp;
	}

	public void setLp(LoanPdt lp) {
		this.lp = lp;
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

	public String getMinapplyDate() {
		return minapplyDate;
	}

	public void setMinapplyDate(String minapplyDate) {
		this.minapplyDate = minapplyDate;
	}

	public String getMaxapplyDate() {
		return maxapplyDate;
	}

	public void setMaxapplyDate(String maxapplyDate) {
		this.maxapplyDate = maxapplyDate;
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

	public String getUnmemo() {
		return unmemo;
	}

	public void setUnmemo(String unmemo) {
		this.unmemo = unmemo;
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

	public List<OperLog> getOperLogs() {
		return operLogs;
	}

	public void setOperLogs(List<OperLog> operLogs) {
		this.operLogs = operLogs;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getLoanOrdTerm() {
		return loanOrdTerm;
	}

	public void setLoanOrdTerm(Integer loanOrdTerm) {
		this.loanOrdTerm = loanOrdTerm;
	}

	public String getPdtId() {
		return pdtId;
	}

	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
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

	public String getMinacceptDate() {
		return minacceptDate;
	}

	public void setMinacceptDate(String minacceptDate) {
		this.minacceptDate = minacceptDate;
	}

	public String getMaxacceptDate() {
		return maxacceptDate;
	}

	public void setMaxacceptDate(String maxacceptDate) {
		this.maxacceptDate = maxacceptDate;
	}

}
