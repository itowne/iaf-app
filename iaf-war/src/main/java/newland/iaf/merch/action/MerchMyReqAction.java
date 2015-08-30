package newland.iaf.merch.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.FileType;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.LoanPdtHis;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.TFileService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.service.MerchLoanService;
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

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 商户我的申请查询Action
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "merchMyReq")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "instInfo",  type = "dispatcher",location = "/merch/myReqInstInfo.jsp"),
	@Result(name = "processInfo",  type = "dispatcher",location = "/merch/my-req-process.jsp"),
	@Result(name = "prodDetail",  type = "dispatcher",location = "/merch/my-req-prod-detail.jsp"),
	@Result(name = "planInfo",  type = "dispatcher",location = "/merch/my-req-planInfo.jsp"),
	@Result(name = "success",  type = "dispatcher",location = "/merch/my-req.jsp")
    })
public class MerchMyReqAction extends MerchBaseAction {
	
	private static final long serialVersionUID = 6593399382656216180L;
	
	/**
	 * 订单查询service
	 */
	@Resource(name="loanOrdService")
	private LoanOrdService loanordservice;
	@Resource(name="com.newland.iaf.instService")
	private InstService instService;
	@Resource(name="loanPdtService")
	private LoanPdtService loanPdtService;
	@Resource(name="merchLoanService")
	private MerchLoanService merchLoanService;
	@Resource (name = "instLoanService")
	private InstLoanService instLoanService;
	@Resource(name="loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	
	private DataSet dataSet;//用户JQGrid数据
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrd> loanOrdList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrd loanOrd;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String yearRate;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String quota;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String term;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String ordStat;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String startDate;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String endDate;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst inst;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanPdt loanPdt;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanPdtHis loanPdtHis;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private boolean debitFlag;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<OperLog> operLogs;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long idebitBid;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrdPlan> planList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal interestTotal;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal amtTotal;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal perMonth;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String itemStat;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanPdt> loanPdtList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;
	
	@Resource(name = "tfileService")
	private TFileService tfileService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<TFile> tfileList;
	
	private String loanordid;
	private String Maxquota;
	private String updateDate;
	private String pdt;
	private String instid;
	
	@Override
	@Begin
	@End
	public String execute() throws Exception {
		//初始化页面需要的一些下拉选框和其他信息
		loanPdtList=loanPdtService.queryAll();
		instList = instService.queryInst();
		return "success";
	}
	
	public String loanQuery(){
		loanPdtList=loanPdtService.queryAll();
		instList = instService.queryInst();
		return "success";
	}
	
	/**
	 * 竞投申请跳转过来
	 * @return
	 * @throws Exception
	 */
	@Begin
	public String bebidIndex() throws Exception{
		return "success";
	}
	
	public String list() throws Exception{
		try {
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);//设置每页显示数
			if(dataSet == null){
				dataSet = new DataSet();
				page.setPageOffset(0);
			}else{
				page.setPageOffset(dataSet.getPage()-1);
			}
			LoanOrdCondition loanordcondition = new LoanOrdCondition();
			loanordcondition.setImerch(this.getMerchSession().getMerch().getImerch());
			if(idebitBid != null){
				loanordcondition.setIloanPdt(idebitBid);
				loanordcondition.setPdtType(PdtType.MERCH_PROD);
			}
			loanordcondition.setShield(0);
			//设置查询条件
			if(yearRate != null && !"".equals(yearRate.trim())){
				//loanordcondition.setRate(new BigDecimal(yearRate.trim()));
			}
			if(StringUtils.isNotBlank(quota)){
			//	loanordcondition.setQuota((new BigDecimal(quota.trim())).multiply(new BigDecimal(10000)));
			}
			if(StringUtils.isNotBlank(quota)){
				loanordcondition.setMinQuta((new BigDecimal(quota.trim())).multiply(new BigDecimal(10000)));
			}
			
			if(StringUtils.isNotBlank(Maxquota)){
				loanordcondition.setMaxQuota((new BigDecimal(Maxquota.trim())).multiply(new BigDecimal(10000)));
			}
			
			if(StringUtils.isNotBlank(term)){
				//loanordcondition.setTerm(new Integer(term.trim()));
			}
			if(StringUtils.isNotBlank(ordStat)){
				loanordcondition.setOrdStat(OrdStat.valueOf(ordStat));
			}
			if(StringUtils.isNotBlank(itemStat)){//商户welcome页面订单状态显示
				loanordcondition.setOrdStat(OrdStat.valueOf(itemStat));
			}
			if(StringUtils.isNotBlank(loanordid)){
				loanordcondition.setLoanPdtId(loanordid);
			}
			if(StringUtils.isNotBlank(pdt)){
				loanordcondition.setIloanPdtId(pdt);
			}
			if(StringUtils.isNotBlank(instid)){
				loanordcondition.setIinst(Long.parseLong(instid));
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(updateDate)){
				loanordcondition.setUpdTime(sdf.parse(updateDate));
			}
//			if(StringUtils.isNotBlank(startDate)){
//				loanordcondition.setBeginDate(sdf.parse(startDate.trim()));
//			}
//			if(StringUtils.isNotBlank(endDate)){
//				loanordcondition.setEndDate(sdf.parse(endDate.trim()));
//			}

			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.desc("iloanOrd"));
			loanordcondition.setOrders(orderList);
			loanOrdList = this.loanordservice.queryOrdByCon(loanordcondition, page);
			dataSet.setGridModel(loanOrdList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 
	 * 详细的跳转机构信息页面
	 * @return
	 * @throws Exception
	 */
	public String instInfo() throws Exception {
		//查询订单信息
		loanOrd = this.loanOrdList.get(index);
		inst = this.instService.findInstById(loanOrd.getIinst());
		if(PdtType.MERCH_PROD.name().equals(loanOrd.getPdtType().name())){
			debitFlag = true;
		}else{
			debitFlag=false;
		}
		tfileList = this.tfileService.queryInstFile(inst.getIinst(), FileType.instFile);
		return "instInfo";
	}
	
	public String planInfo() throws Exception{
		loanOrd = loanOrdList.get(new Integer(index));
		planList = this.loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
		interestTotal = new BigDecimal(0);
		amtTotal = new BigDecimal(0);
		perMonth = new BigDecimal(0);
		if(PdtType.MERCH_PROD.name().equals(loanOrd.getPdtType().name())){
			debitFlag = true;
		}else{
			debitFlag=false;
		}
		for(LoanOrdPlan plan :planList){
			perMonth = plan.getRepayment();
			interestTotal = interestTotal.add(plan.getInterest()==null?new BigDecimal(0):plan.getInterest());
			amtTotal = amtTotal.add(plan.getCapital()==null?new BigDecimal(0):plan.getCapital()).add(plan.getInterest()==null?new BigDecimal(0):plan.getInterest());
		}
		return "planInfo";
	}
	
	/**
	 * 受理情况
	 * @return
	 * @throws Exception
	 */
	public String processInfo() throws Exception {
		//查询订单信息
		loanOrd = this.loanOrdList.get(index);
		//如果产品类型为竞投，则没有对应的产品信息
		if(PdtType.MERCH_PROD.name().equals(loanOrd.getPdtType().name())){
			debitFlag = true;
		}else{
			debitFlag=false;
		}
		this.operLogs = this.instLoanService.queryOperLogBy(loanOrd);
		return "processInfo";
	}
	
	/**
	 * 产品信息
	 * @return
	 * @throws Exception
	 */
	public String prodDetail() throws Exception {
		//查询订单信息
		//loanOrd = this.loanOrdList.get(index);
		//如果产品类型为竞投，则没有对应的产品信息
		if(PdtType.MERCH_PROD.name().equals(loanOrd.getPdtType().name())){
			debitFlag = true;
		}else{
			debitFlag=false;
		}
		if(PdtType.MERCH_PROD.equals(loanOrd.getPdtType())){
			loanPdtHis = null;
		}else{
			loanPdtHis = this.loanPdtService.getLoanPdtHisById(loanOrd.getIloanPdt());
		}
		if(loanPdtHis!=null){
			String feature = StringUtils.isNotBlank(loanPdtHis.getFeature())?loanPdtHis.getFeature().replaceAll("\r\n", "<br>"):"";
			String condition =StringUtils.isNotBlank(loanPdtHis.getCondition())?loanPdtHis.getCondition().replaceAll("\r\n", "<br>"):"";
			String cl = StringUtils.isNotBlank(loanPdtHis.getCl())?loanPdtHis.getCl().replaceAll("\r\n", "<br>"):"";
			loanPdtHis.setFeature(feature);
			loanPdtHis.setCondition(condition);
			loanPdtHis.setCl(cl);
		}
		return "prodDetail";
	}
	
	public String cancelMyReq() throws Exception{
		loanPdtList=loanPdtService.queryAll();
		instList = instService.queryInst();
		try{
			//查询订单信息
			loanOrd = this.loanOrdList.get(index);
			if (loanOrd.getOrdStat() != OrdStat.APPLY){
				super.addActionError("该订单状态不正确, 处理失败");
				return "success";
			}
			this.merchLoanService.cancelLoanOrd(loanOrd, getMerchSession(), "商户撤销订单", this.getIpaddr());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
	}

	@JqDataSet(content = "{o:loanOrdId},{o:pdtName},{o:instName},{o:wangyuanQuota},{i:(format.date)({o:updTime})},{o:ordStat.desc},{o:iloanOrd}")
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

	public LoanOrdService getLoanordservice() {
		return loanordservice;
	}

	public void setLoanordservice(LoanOrdService loanordservice) {
		this.loanordservice = loanordservice;
	}

	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}


	public String getOrdStat() {
		return ordStat;
	}

	public void setOrdStat(String ordStat) {
		this.ordStat = ordStat;
	}

	public String getStartDate() {
		return startDate;
	}

	public List<TFile> getTfileList() {
		return tfileList;
	}

	public void setTfileList(List<TFile> tfileList) {
		this.tfileList = tfileList;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}


	public String getLoanordid() {
		return loanordid;
	}

	public void setLoanordid(String loanordid) {
		this.loanordid = loanordid;
	}

	public String getMaxquota() {
		return Maxquota;
	}

	public void setMaxquota(String maxquota) {
		Maxquota = maxquota;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getPdt() {
		return pdt;
	}

	public void setPdt(String pdt) {
		this.pdt = pdt;
	}

	public String getInstid() {
		return instid;
	}

	public void setInstid(String instid) {
		this.instid = instid;
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public List<LoanPdt> getLoanPdtList() {
		return loanPdtList;
	}

	public void setLoanPdtList(List<LoanPdt> loanPdtList) {
		this.loanPdtList = loanPdtList;
	}

	public String getItemStat() {
		return itemStat;
	}

	public void setItemStat(String itemStat) {
		this.itemStat = itemStat;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public LoanPdt getLoanPdt() {
		return loanPdt;
	}

	public void setLoanPdt(LoanPdt loanPdt) {
		this.loanPdt = loanPdt;
	}

	public boolean isDebitFlag() {
		return debitFlag;
	}

	public void setDebitFlag(boolean debitFlag) {
		this.debitFlag = debitFlag;
	}

	public List<OperLog> getOperLogs() {
		return operLogs;
	}

	public void setOperLogs(List<OperLog> operLogs) {
		this.operLogs = operLogs;
	}

	public Long getIdebitBid() {
		return idebitBid;
	}

	public void setIdebitBid(Long idebitBid) {
		this.idebitBid = idebitBid;
	}

	public LoanPdtHis getLoanPdtHis() {
		return loanPdtHis;
	}

	public void setLoanPdtHis(LoanPdtHis loanPdtHis) {
		this.loanPdtHis = loanPdtHis;
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
	
	

}
