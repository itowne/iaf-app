package newland.iaf.merch.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import newland.base.util.StringSplitParser;
import newland.iaf.backstage.service.BackStageLoanService;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.HicardPayCfg;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.LoanPdtHis;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.Province;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.GateWayType;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.TransStat;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.HicardPaymentService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.TransMsgService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchUsedPayer;
import newland.iaf.merch.service.MerchLoanService;
import newland.iaf.merch.service.MerchService;
import newland.iaf.merch.service.MerchUsedPayerService;
import newland.iaf.utils.DateUtils;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Order;
import org.hibernate.hql.internal.CollectionSubqueryFactory;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.util.CollectionUtils;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 我要还款申请ACTION
 * 
 * @author lindaqun
 * 
 */
@ParentPackage("struts")
@Namespace("/merch")
@Action(value = "merchMyRefund")
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "detail", type = "dispatcher", location = "/merch/my-refund-detail.jsp"),
		@Result(name = "doPayment", type = "dispatcher", location = "/merch/refund-info.jsp"),
		@Result(name = "viewTransMsg", type = "dispatcher", location = "/merch/refund-trans-msg.jsp"),
		@Result(name = "error", type = "dispatcher", location = "/merch/refund-error.jsp"),
		@Result(name = "payment", type = "dispatcher", location = "/merch/refund-payment.jsp"),
		@Result(name = "instInfo", type = "dispatcher", location = "/merch/refund-detail.jsp"),
		@Result(name = "processInfo", type = "dispatcher", location = "/merch/refund-process.jsp"),
		@Result(name = "prodDetail", type = "dispatcher", location = "/merch/refund-prod-detail.jsp"),
		@Result(name = "refundError", type = "dispatcher", location = "/merch/refundError.jsp"),
		@Result(name = "refundDetail", type = "dispatcher", location = "/merch/refund-plan-detail.jsp"),
		@Result(name = "rePay", type = "dispatcher", location = "/merch/rePay.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/merch/my-refund.jsp") })
public class MerchMyRefundAction extends MerchBaseAction {

	private static final long serialVersionUID = 644850442823445008L;
	/**
	 * 订单查询service
	 */
	@Resource(name = "loanOrdService")
	private LoanOrdService loanordservice;
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	@Resource(name = "merchUsedPayerService")
	private MerchUsedPayerService merchUsedPayerService;
	@Resource(name = "merchLoanService")
	private MerchLoanService merchLoanService;
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	@Resource(name = "instLoanService")
	private InstLoanService instLoanService;

	private DataSet dataSet;// 用户JQGrid数据
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrd> loanOrdList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String quota;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String year;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String term;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String ordStat;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanOrdId;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrd loanOrd;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrdPlan> planList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst inst;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private InstBusiInfo instBusiInfo;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchUsedPayer merchUsedPayer;

	@Resource(name = "paymentCfg")
	private HicardPayCfg payCfg;

	@Resource(name = "fundFlowService")
	private FundFlowService fundFlowService;

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
	private String memo;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String addFlag;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String iloanOrdPlan;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrdPlan plan;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrdPlan> payplanList;

	@Resource(name = "hicardPaymentService")
	private HicardPaymentService paymentService;

	@Resource(name = "backStageLoanService")
	private BackStageLoanService backStageLoanService;

	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;

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
	private FundFlow fundFlow;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanOrdPlanId;

	@Resource(name = "transMsgService")
	private TransMsgService transMsgService;

	private BigDecimal payAmt;

	private String payerName;

	private String payerCardNo;

	private String payerBankName;

	private String bankCode;

	private String attachment;

	private MerchBusiInfo mbi;

	private BigDecimal charge;

	private BigDecimal captial;
	
	private BigDecimal amt;
	
	private Integer count;
	
	private String periodRefund;
	
	private List<LoanPdt> pdtList;
	
	private List<Inst> instList;
	
	private String pdtId;
	
	private String instId;
	
	private String Maxquota;
	
	private DebitBid debitBid;
	
	@Resource(name = "debitBidService")
	private DebitBidService dbs;
	
	private String area;
	
	private LoanPdt lp;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService lps;
	
	@Resource(name = "provinceService")
	private ProvinceService ps;
	
	private TransMsg tm;
	
	private BigDecimal curRepayment;
	
	private BigDecimal curCapital;
	
	private BigDecimal curInterest;
	
	private FundFlow ff;
	
	private Merch merch;
	
	private BigDecimal total;
	
	private Integer totalCount;
	
	private  BigDecimal creditTotal;
	private Integer creditTotalCount;
	
	private BigDecimal refundTotal;
	
	private BigDecimal surplus;
	
	@Resource(name="fundFlowService")
	private FundFlowService ffs;
	
	private String flag;
	
	private String debit;
	
	private String ten;
	
	private String loanId;
	
	private String target;
	
	private String iloanOrd;
	

	@Override
	@Begin
	@End
	public String execute() throws Exception {
		// 初始化页面需要的一些下拉选框和其他信息
		quota="";
		Maxquota="";
		loanOrdId="";
		pdtId="";
		instId="";
		year="";
		term="";
		pdtList=loanPdtService.queryAll();
		instList=instService.queryInst();
		
		LoanOrdCondition loanordcondition = new LoanOrdCondition();
		loanordcondition.setStatus(status);
		loanordcondition.setImerch(this.getMerchSession().getMerch()
				.getImerch());
		List<LoanOrd> list = this.loanordservice.queryByCondition(loanordcondition);
		creditTotal=BigDecimal.ZERO;
		refundTotal = BigDecimal.ZERO;
		surplus = BigDecimal.ZERO;
		if(list.size()>0){
			creditTotalCount =list.size();
			
		}else{
			creditTotalCount =0;
		}
		
		if(!CollectionUtils.isEmpty(list)){
			for (LoanOrd loanOrd : list) {
				FundFlowCondition ffc = new FundFlowCondition();
				ffc.setIloanOrd(loanOrd.getIloanOrd());
				Set<FundFlowType> setff = new HashSet<FundFlowType>();
				setff.add(FundFlowType.CREDIT);
				setff.add(FundFlowType.OTH_CREDIT);
				ffc.setTypes(setff);
				ffc.setStat(FundFlowStat.SUCCESS);
				List<FundFlow> ffList = fundFlowService.queryFundFlowBy(ffc);
				if(!CollectionUtils.isEmpty(ffList)){
					for (FundFlow fundFlow : ffList) {
						creditTotal=creditTotal.add(fundFlow.getCapital()).setScale(2);
					}
				}
				//
				List<LoanOrdPlan> listplans = loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
				if(!CollectionUtils.isEmpty(listplans)){
					for (LoanOrdPlan loanOrdPlan : listplans) {
						if(loanOrdPlan.getStat()==PlanStat.PAID_UP_LOAN){
							refundTotal=refundTotal.add(loanOrdPlan.getRepayment()).setScale(2);
						}else{
							continue;
						}
					}
				}
			}
		}
		surplus = creditTotal.subtract(refundTotal).setScale(2);
		creditTotal = creditTotal.divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
		refundTotal=refundTotal.divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
		surplus=surplus.divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
		return "success";
	}
	
	public String loanQuery() throws Exception {
		if(StringUtils.isNotBlank(ten)||StringUtils.isNotBlank(debit)||StringUtils.isNotBlank(flag)){
			ordStat="";
		}
		pdtList=loanPdtService.queryAll();
		instList=instService.queryInst();
		
		LoanOrdCondition loanordcondition = new LoanOrdCondition();
		loanordcondition.setStatus(status);
		loanordcondition.setImerch(this.getMerchSession().getMerch()
				.getImerch());
		List<LoanOrd> list = this.loanordservice.queryByCondition(loanordcondition);
		creditTotal=BigDecimal.ZERO;
		refundTotal = BigDecimal.ZERO;
		surplus = BigDecimal.ZERO;
		if(list.size()>0){
			creditTotalCount =list.size();
			
		}else{
			creditTotalCount =0;
		}
		
		if(!CollectionUtils.isEmpty(list)){
			for (LoanOrd loanOrd : list) {
				FundFlowCondition ffc = new FundFlowCondition();
				ffc.setIloanOrd(loanOrd.getIloanOrd());
				Set<FundFlowType> setff = new HashSet<FundFlowType>();
				setff.add(FundFlowType.CREDIT);
				setff.add(FundFlowType.OTH_CREDIT);
				ffc.setTypes(setff);
				ffc.setStat(FundFlowStat.SUCCESS);
				List<FundFlow> ffList = fundFlowService.queryFundFlowBy(ffc);
				if(!CollectionUtils.isEmpty(ffList)){
					for (FundFlow fundFlow : ffList) {
						creditTotal=creditTotal.add(fundFlow.getCapital()).setScale(2);
					}
				}
				//
				List<LoanOrdPlan> listplans = loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
				if(!CollectionUtils.isEmpty(listplans)){
					for (LoanOrdPlan loanOrdPlan : listplans) {
						if(loanOrdPlan.getStat()==PlanStat.PAID_UP_LOAN){
							refundTotal=refundTotal.add(loanOrdPlan.getRepayment()).setScale(2);
						}else{
							continue;
						}
					}
				}
			}
		}
		surplus = creditTotal.subtract(refundTotal).setScale(2);
		creditTotal = creditTotal.divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
		refundTotal=refundTotal.divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
		surplus=surplus.divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
		return "success";
	}
	
	static Set<OrdStat> status = new HashSet<OrdStat>();
	static {
		//status.add(OrdStat.AUDIT_REFUSE);
		//status.add(OrdStat.CREDITING);
		status.add(OrdStat.DELIN_QUENCY);
		status.add(OrdStat.REFUND);
		status.add(OrdStat.REFUNDING);
		status.add(OrdStat.PAID_UP_LOAN);
	}

	public Set<OrdStat> getStatus() {
		return status;
	}

	public String list() throws Exception {
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
			LoanOrdCondition loanordcondition = new LoanOrdCondition();
			loanordcondition.setImerch(this.getMerchSession().getMerch()
					.getImerch());
			loanordcondition.setShield(0);
			// 设置查询条件
			if (StringUtils.isNotBlank(loanOrdId)) {
				loanordcondition.setLoanOrdId(loanOrdId.trim());
			}
			if (StringUtils.isNotBlank(quota)) {
				loanordcondition.setMinQuta(new BigDecimal(quota.trim()).multiply(new BigDecimal(10000)));
			}

			if(StringUtils.isNotBlank(instId)){
				loanordcondition.setIinst(Long.parseLong(instId));
			}
			
			if(StringUtils.isNotBlank(pdtId)){
				LoanPdt lp = loanPdtService.getLoanPdtById(Long.parseLong(pdtId));
				if(lp!=null){
					loanordcondition.setIloanPdt(lp.getIloanPdtHis());
				}
			}
			
			if(StringUtils.isNotBlank(Maxquota)){
				loanordcondition.setMaxQuota(new BigDecimal(Maxquota.trim()).multiply(new BigDecimal(10000)));
			}
			if (StringUtils.isNotBlank(year) || StringUtils.isNotBlank(term)) {
				Calendar beginCal = Calendar.getInstance();
				beginCal.set(Calendar.MONTH, 0);
				beginCal.set(Calendar.DATE, 1);
				Calendar endCal = Calendar.getInstance();
				endCal.set(Calendar.MONTH, 0);
				endCal.set(Calendar.DATE, 1);
				if (StringUtils.isNotBlank(year)) {
					beginCal.set(Calendar.YEAR, Integer.parseInt(year));
					endCal.set(Calendar.YEAR, Integer.parseInt(year) + 1);
					// 选择月份前要先选择年份
					if (StringUtils.isNotBlank(term)) {
						beginCal.set(Calendar.MONTH, Integer.parseInt(term) - 1);
						endCal.set(Calendar.YEAR, Integer.parseInt(year));
						endCal.set(Calendar.MONTH, Integer.parseInt(term) - 1);
					}
				}
				Date beginDate = beginCal.getTime();
				beginDate = DateUtils.roundDate(beginDate, Calendar.DATE);
				loanordcondition.setBeginLastRefundDate(beginDate);
				Date endDate = endCal.getTime();
				endDate = DateUtils.roundDate(endDate, Calendar.DATE);
				loanordcondition.setEndLastRefundDate(endDate);
			}
			if (StringUtils.isNotBlank(ordStat)) {
				loanordcondition.setOrdStat(OrdStat.valueOf(ordStat));
			} else {
				loanordcondition.setStatus(status);
			}
			if(StringUtils.isNotBlank(flag)){
				List<LoanOrdPlan> plans = loanOrdPlanService.queryByImerch(this.getMerchSession().getMerch()
						.getImerch());
				List<Long> iloanOrdList = new ArrayList<Long>();
				if(!CollectionUtils.isEmpty(plans)){
					for (LoanOrdPlan loanOrdPlan : plans) {
						if(loanOrdPlan.getStat()==PlanStat.BALANCE_FREEZE||loanOrdPlan.getStat()==PlanStat.FREEZING){
							iloanOrdList.add(loanOrdPlan.getIloanOrd());
						}else{
							continue;
						}
					}
				}
				loanordcondition.setIloanOrdList(iloanOrdList);
			}
			
			if(StringUtils.isNotBlank(debit)){
				List<LoanOrdPlan> plans = loanOrdPlanService.queryByImerch(this.getMerchSession().getMerch()
						.getImerch());
				List<Long> iloanOrdList = new ArrayList<Long>();
				if(!CollectionUtils.isEmpty(plans)){
					for (LoanOrdPlan loanOrdPlan : plans) {
						if(loanOrdPlan.getStat()==PlanStat.DELIN_QUENCY){
							if(!iloanOrdList.contains(loanOrdPlan)){
								iloanOrdList.add(loanOrdPlan.getIloanOrd());
							}
						}else{
							continue;
						}
					}
				}
				loanordcondition.setIloanOrdList(iloanOrdList);
			}
			
			if(StringUtils.isNotBlank(ten)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date d = getDateAfter(new Date(),10);
				String str = sdf.format(d);
				List<LoanOrdPlan> obj = merchService.queryIloanOrd(new Date(), d, this.getMerchSession().getMerch().getImerch());
				List<Long> list = new ArrayList<Long>();
				if(!CollectionUtils.isEmpty(obj)){
					for (LoanOrdPlan lop : obj) {
						list.add(lop.getIloanOrd());
					}
				}
				loanordcondition.setIloanOrdList(list);
			}
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.desc("iloanOrd"));
			loanordcondition.setOrders(orderList);
				loanOrdList = this.loanordservice.queryOrdByCon(loanordcondition,
						page);

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
	 * 
	 * @return
	 * @throws Exception
	 */
	public String instInfo() throws Exception {
		// 查询订单信息
		loanOrd = loanOrdList.get(index);
		inst = this.instService.findInstById(loanOrd.getIinst());
		if (PdtType.MERCH_PROD.name().equals(loanOrd.getPdtType().name())) {
			debitFlag = true;
		}
		if (PdtType.MERCH_PROD.name().equals(loanOrd.getPdtType().name())) {
			debitFlag = true;
		}
		
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
			}
		} else {// 产品
			if(StringUtils.isNotBlank(loanOrd.getLoanPdtId())){
				lp = lps.queryByLoanPdtId(loanOrd.getLoanPdtId());
			}
		}
		return "instInfo";
	}

	/**
	 * 受理情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public String processInfo() throws Exception {
		// 查询订单信息
		loanOrd = loanOrdList.get(index);
		this.operLogs = this.instLoanService.queryOperLogBy(loanOrd);
		if (PdtType.MERCH_PROD.name().equals(loanOrd.getPdtType().name())) {
			debitFlag = true;
		}
		
		if (PdtType.MERCH_PROD.name().equals(loanOrd.getPdtType().name())) {
			debitFlag = true;
		}
		
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
			}
		} else {// 产品
			if(StringUtils.isNotBlank(loanOrd.getLoanPdtId())){
				lp = lps.queryByLoanPdtId(loanOrd.getLoanPdtId());
			}
		}
		return "processInfo";
	}

	/**
	 * 产品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String prodDetail() throws Exception {
		// 查询订单信息
		loanOrd = loanOrdList.get(index);
		// 如果产品类型为竞投，则没有对应的产品信息
		if (PdtType.MERCH_PROD.equals(loanOrd.getPdtType())) {
			loanPdtHis = null;
		} else {
			loanPdtHis = this.loanPdtService.getLoanPdtHisById(loanOrd
					.getIloanPdt());
		}

		return "prodDetail";
	}

	public String rePay(){
		target="/merch/merchMyRefund!doPayment?loanId="+loanId;
		return "rePay";
	}
	
	public String viewDetail() throws Exception {
		if(StringUtils.isNotBlank(loanId)){
			loanOrd = loanordservice.queryByLoanOrdId(loanId);
		}else if(StringUtils.isNotBlank(iloanOrd)){
			loanOrd = loanordservice.queryByLoanOrdId(iloanOrd);
		}else{
			loanOrd = loanOrdList.get(index);
		}
		planList = this.loanOrdPlanService.queryPlanById(loanOrd.getIloanOrd());
		interestTotal = new BigDecimal(0);
		amtTotal = new BigDecimal(0);
		perMonth = new BigDecimal(0);
		if (PdtType.MERCH_PROD.name().equals(loanOrd.getPdtType().name())) {
			debitFlag = true;
		}
		
		if (loanOrd.getPdtType() == PdtType.MERCH_PROD) {// 竞投
			if (loanOrd.getIloanPdt() != null) {
				try {
					debitBid = dbs.getDebitBidById(loanOrd.getIloanPdt());
				} catch (Exception e) {
					super.addActionMessage(e.getMessage());
				}
			}
		} else {// 产品
			if(StringUtils.isNotBlank(loanOrd.getLoanPdtId())){
				lp = lps.queryByLoanPdtId(loanOrd.getLoanPdtId());
			}
		}
		
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
		return "detail";
	}

	public String doPayment() throws Exception {
		try {
			// loanOrd = loanOrdList.get(index);
			if(StringUtils.isNotBlank(loanId)){
				loanOrd = loanordservice.queryByLoanOrdId(loanId);
			}
			inst = this.instService.findInstById(loanOrd.getIinst());
			instBusiInfo = this.instService.findInstBusiInfoByiinst(loanOrd
					.getIinst());
			mbi = merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
			merch =merchService.findMerchById(loanOrd.getImerch());
			List<MerchUsedPayer> ls = this.merchUsedPayerService
					.listMerchUsedPayerByImerch(loanOrd.getImerch());
			if (StringUtils.isBlank(iloanOrdPlan)) {
				addActionError("请选择还款计划");
				return "detail";
			}
			String[] ids = iloanOrdPlan.split(",");
			payplanList = new ArrayList<LoanOrdPlan>();
			payAmt = new BigDecimal(0);
			captial = new BigDecimal(0);
			
			curCapital=BigDecimal.ZERO;
			curInterest=BigDecimal.ZERO;
			curRepayment=BigDecimal.ZERO;
			for (String id : ids) {
				if (StringUtils.isNotBlank(id)) {
					LoanOrdPlan pl = planList.get(new Integer(id.trim()));
					if (pl.getStat().ordinal() > PlanStat.DELIN_QUENCY
							.ordinal())
						throw new Exception("订单号：" + loanOrd.getLoanOrdId()
								+ ", 第" + pl.getPeriod() + "状态不正确！");
					BigDecimal tradeRate = new BigDecimal(
							this.payCfg.getTradeRate());
					BigDecimal minRate = new BigDecimal(
							this.payCfg.getMinRate());
					BigDecimal maxRate = new BigDecimal(
							this.payCfg.getMaxRate());
					Double r = tradeRate.doubleValue() / 100;
					BigDecimal rate = pl.getRepayment().multiply(
							new BigDecimal(r.toString()));
					rate = rate.setScale(2, BigDecimal.ROUND_HALF_UP);
					if (rate.compareTo(minRate) == -1) {
						rate = minRate;
					}
					if (rate.compareTo(maxRate) == 1) {
						rate = maxRate;
					}
					payAmt = payAmt.add(rate.add(pl.getRepayment()));
					captial = captial
							.add(pl.getCapital().add(pl.getInterest()));
					
					curCapital=curCapital.add(pl.getCapital()).setScale(2);
					curInterest=curInterest.add(pl.getInterest()).setScale(2);
					curRepayment=curRepayment.add(pl.getRepayment()).setScale(2);
					payplanList.add(pl);
				}
			}
			charge = payAmt.subtract(captial);
			if (ls != null && ls.size() > 0) {
				merchUsedPayer = ls.get(ls.size() - 1);
			}
			return "doPayment";
		} catch (Exception e) {
			logger.error("还款出错", e);
			addActionError("还款时异常:" + e.getMessage());
			return "detail";
		}

	}

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private TransMsg transMsg;

	public String viewTransMsg() {
		// 交易前做一次同步
		List<FundFlow> FundFlowList = fundFlowService.queryByIloanOrdAndType(
				loanOrd.getIloanOrd(), FundFlowType.REFUND);
		if (!CollectionUtils.isEmpty(FundFlowList)) {
			for (FundFlow fundFlow : FundFlowList) {
				TransMsg tm = transMsgService.findById(fundFlow
						.getOtherSysTraceNo());
				if (tm.getTransStat() != TransStat.UNCONFIRMED) {
					continue;
				}
				if (fundFlow.getStatus() == FundFlowStat.EXPIRY) {
					continue;
				}
				List<LoanOrdPlan> loanOrdPlanList = loanOrdPlanService
						.queryByLoanOrdId(loanOrd.getLoanOrdId());
				if (loanOrdPlanList != null) {
					for (LoanOrdPlan loanOrdPlan : loanOrdPlanList) {
						boolean bool = fundFlow
								.getIloanOrdPlan()
								.toString()
								.equals(loanOrdPlan.getIlanOrdPlan().toString());
						if (bool) {
							if (loanOrdPlan.getStat() == PlanStat.BALANCE
									|| loanOrdPlan.getStat() == PlanStat.DELIN_QUENCY) {
								try {
									TransMsg transRes = paymentService
											.synchronize(tm);
									if (transRes.getRespCode().equals("00") == true) {
										logger.info("订单已还款 orderNo:"
												+ tm.getOrderNo());
										return "refundError";
									}
								} catch (Throwable e) {
									// e.printStackTrace();
									logger.info(
											"同步交易查询 orderNo:" + tm.getOrderNo(),
											e);
								}
							}
						}
					}
				}
			}
		}

		try {
			transMsg = this.paymentService.genRefundTransMsg(loanOrd,
					payplanList, GateWayType.HICARD, getBaseUrl());

			for (LoanOrdPlan plan : payplanList) {
				BigDecimal rate = getRateValue(plan.getCapital(), this.payCfg);
				if(plan.getRepayment().add(rate).compareTo(new BigDecimal(500000))==1){
					addActionError("您好,单笔交易金额加上手续费不得大于或者等于50万");
					return "doPayment";
				}
				
				fundFlow = merchLoanService.genFundFlow(loanOrd, plan,
						transMsg, FundFlowType.REFUND,
						FundFlowStat.REFUND_INIT, this.getMerchSession(), "",
						this.getIpaddr());
			}
		} catch (Exception e) {
			logger.error("生成支付指令失败", e);
			addActionError("生成支付指令失败, 失败原因：" + e.getMessage());
			return "error";
		}
		return "viewTransMsg";
	}

	private String signMsg;

	private String msg;

	private String payUrl;

	public String refund() throws Exception {
		if (payplanList == null) {
			super.addActionError("为检索到还款计划!");
			return "error";
		}
		if (payplanList != null) {
			for (LoanOrdPlan plan : payplanList) {
				if (plan.getStat() == PlanStat.BALANCE
						|| plan.getStat() == PlanStat.DELIN_QUENCY) {

				} else {
					super.addActionError("订单状态不正确,处理失败!");
					return "error";
				}
			}
		}
		try {
			loanOrd = this.loanordservice
					.findLoanOrdById(loanOrd.getIloanOrd());
			this.merchLoanService.batchRefund(loanOrd, payplanList, transMsg,
					this.getMerchSession(), memo, this.getIpaddr());
			Map<String, Object> map = this.paymentService
					.encryptAndSign(transMsg);
			this.signMsg = map.get("signMsg").toString();
			this.msg = map.get("msg").toString();
			this.payUrl = transMsg.getPayUrl();

			return "payment";
		} catch (Exception e) {
			logger.error("还款失败", e);
			super.addActionError(e.getMessage());
			try {
				this.backStageLoanService.expiryTrans(transMsg);
			} catch (Exception e1) {
				logger.error("回滚失败", e1);
			}
			return "error";
		}
	}

	// 还款计划 ---受理查询
	public String refundDetail() throws Exception {
		// fundFlow=fundFlowService.queryByIloanOrdPlan(Long.parseLong(loanOrdPlanId));
		plan = loanOrdPlanService.queryByPlanId(Long.parseLong(loanOrdPlanId));
		inst = this.instService.findInstById(plan.getIinst());
		instBusiInfo = this.instService
				.findInstBusiInfoByiinst(plan.getIinst());
		MerchSession merchSession = (MerchSession) SessionFilter
				.getIafSession();
		mbi = merchService.getMerchBusiInfoByImerch(merchSession.getMerch()
				.getImerch());
		merch =merchService.findMerchById(merchSession.getMerch()
				.getImerch());
		if(plan!=null){
			ff= fundFlowService.queryByIloanOrdPlan(plan.getIlanOrdPlan());
			if(ff!=null){
				if(StringUtils.isNotBlank(ff.getOtherSysTraceNo())){
					tm = transMsgService.findById(ff.getOtherSysTraceNo());
				}
			}
		}
		return "refundDetail";
	}

	public BigDecimal  getRateValue(BigDecimal quota,HicardPayCfg payCfg){
		BigDecimal tradeRate = new BigDecimal(
				payCfg.getTradeRate());
		BigDecimal minRate = new BigDecimal(
				payCfg.getMinRate());
		BigDecimal maxRate = new BigDecimal(
				payCfg.getMaxRate());
		Double r = tradeRate.doubleValue() / 100;
		BigDecimal rate = quota.multiply(
				new BigDecimal(r.toString()));
		rate = rate.setScale(2, BigDecimal.ROUND_HALF_UP);
		if (rate.compareTo(minRate) == -1) {
			rate = minRate;
		}
		if (rate.compareTo(maxRate) == 1) {
			rate = maxRate;
		}
		return rate;
	}
	
	@JqDataSet(content = "{o:loanOrdId},{o:pdtName},{o:instName},{f:(wanyuanFormatter)({o:quota})},{i:(format.date)({o:lastRefundDate})},{o:ordStat.desc}")
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

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getLoanOrdPlanId() {
		return loanOrdPlanId;
	}

	public void setLoanOrdPlanId(String loanOrdPlanId) {
		this.loanOrdPlanId = loanOrdPlanId;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getPeriodRefund() {
		return periodRefund;
	}

	public BigDecimal getCurRepayment() {
		return curRepayment;
	}

	public void setCurRepayment(BigDecimal curRepayment) {
		this.curRepayment = curRepayment;
	}

	public BigDecimal getCurCapital() {
		return curCapital;
	}

	public void setCurCapital(BigDecimal curCapital) {
		this.curCapital = curCapital;
	}

	public BigDecimal getCurInterest() {
		return curInterest;
	}

	public void setCurInterest(BigDecimal curInterest) {
		this.curInterest = curInterest;
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

	public void setPeriodRefund(String periodRefund) {
		this.periodRefund = periodRefund;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public FundFlow getFundFlow() {
		return fundFlow;
	}

	public BigDecimal getCaptial() {
		return captial;
	}

	public void setCaptial(BigDecimal captial) {
		this.captial = captial;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public MerchBusiInfo getMbi() {
		return mbi;
	}

	public void setMbi(MerchBusiInfo mbi) {
		this.mbi = mbi;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public void setFundFlow(FundFlow fundFlow) {
		this.fundFlow = fundFlow;
	}

	public String getOrdStat() {
		return ordStat;
	}

	public void setOrdStat(String ordStat) {
		this.ordStat = ordStat;
	}

	public String getLoanOrdId() {
		return loanOrdId;
	}

	public void setLoanOrdId(String loanOrdId) {
		this.loanOrdId = loanOrdId;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerCardNo() {
		return payerCardNo;
	}

	public void setPayerCardNo(String payerCardNo) {
		this.payerCardNo = payerCardNo;
	}

	public String getPayerBankName() {
		return payerBankName;
	}

	public void setPayerBankName(String payerBankName) {
		this.payerBankName = payerBankName;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public InstBusiInfo getInstBusiInfo() {
		return instBusiInfo;
	}

	public void setInstBusiInfo(InstBusiInfo instBusiInfo) {
		this.instBusiInfo = instBusiInfo;
	}

	public MerchUsedPayer getMerchUsedPayer() {
		return merchUsedPayer;
	}

	public void setMerchUsedPayer(MerchUsedPayer merchUsedPayer) {
		this.merchUsedPayer = merchUsedPayer;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getIloanOrdPlan() {
		return iloanOrdPlan;
	}

	public void setIloanOrdPlan(String iloanOrdPlan) {
		this.iloanOrdPlan = iloanOrdPlan;
	}

	public LoanOrdPlan getPlan() {
		return plan;
	}

	public void setPlan(LoanOrdPlan plan) {
		this.plan = plan;
	}

	public List<LoanOrdPlan> getPayplanList() {
		return payplanList;
	}

	public void setPayplanList(List<LoanOrdPlan> payplanList) {
		this.payplanList = payplanList;
	}

	public BigDecimal getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	public TransMsg getTransMsg() {
		return transMsg;
	}

	public void setTransMsg(TransMsg transMsg) {
		this.transMsg = transMsg;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public LoanPdtHis getLoanPdtHis() {
		return loanPdtHis;
	}

	public void setLoanPdtHis(LoanPdtHis loanPdtHis) {
		this.loanPdtHis = loanPdtHis;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPdtId() {
		return pdtId;
	}

	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getMaxquota() {
		return Maxquota;
	}

	public void setMaxquota(String maxquota) {
		Maxquota = maxquota;
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

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
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

	public LoanPdtService getLps() {
		return lps;
	}

	public void setLps(LoanPdtService lps) {
		this.lps = lps;
	}

	public TransMsg getTm() {
		return tm;
	}

	public void setTm(TransMsg tm) {
		this.tm = tm;
	}

	public FundFlow getFf() {
		return ff;
	}

	public void setFf(FundFlow ff) {
		this.ff = ff;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(BigDecimal creditTotal) {
		this.creditTotal = creditTotal;
	}

	public Integer getCreditTotalCount() {
		return creditTotalCount;
	}

	public void setCreditTotalCount(Integer creditTotalCount) {
		this.creditTotalCount = creditTotalCount;
	}

	public BigDecimal getRefundTotal() {
		return refundTotal;
	}

	public void setRefundTotal(BigDecimal refundTotal) {
		this.refundTotal = refundTotal;
	}

	public BigDecimal getSurplus() {
		return surplus;
	}

	public void setSurplus(BigDecimal surplus) {
		this.surplus = surplus;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
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

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIloanOrd() {
		return iloanOrd;
	}

	public void setIloanOrd(String iloanOrd) {
		this.iloanOrd = iloanOrd;
	}

}
