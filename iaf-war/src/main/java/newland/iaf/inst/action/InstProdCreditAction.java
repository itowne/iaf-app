package newland.iaf.inst.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.backstage.service.BackStageLoanService;
import newland.iaf.base.dao.TransMsgDao;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.Province;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.GateWayType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.RateType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.model.dict.TransStat;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.HicardPaymentService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.base.service.TransMsgService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
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
 * 
 * @author new
 * 
 */
@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "prodCredit")
@Results({
		@Result(name = "loanOrdList", type = "dispatcher", location = "/inst/loanord/credit/loanOrdIndex.jsp"),
		@Result(name = "batchCredit", type = "dispatcher", location = "/inst/loanord/credit/batchCredit.jsp"),
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "toNext", location = "/inst/loanord/credit/demo.jsp"),
		@Result(name = "viewTransMsg", location = "/inst/loanord/credit/transMsg.jsp"),
		@Result(name = "error", location = "/inst/loanord/credit/error.jsp"),
		@Result(name = "toPayment", location = "/inst/loanord/credit/payment.jsp"),
		@Result(name = "creditError", location = "/inst/loanord/credit/creditError.jsp"),
		@Result(name = "rePay", location = "/inst/loanord/credit/rePay.jsp"),
		@Result(name = "viewLoanOrd", location = "/inst/loanord/credit/demo.jsp") })
public class InstProdCreditAction extends InstBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataSet dataSet;// 用户JQGrid数据

	@Resource(name = "instLoanService")
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

	private String selectedRows;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private TransMsg trans;
	@Resource(name = "hicardPaymentService")
	private HicardPaymentService paymentService;
	
	@Resource (name = "transMsgService")
	private TransMsgService transMsgService;
	
	@Resource (name = "fundFlowService")
	private FundFlowService fundFlowService;
	
	@Resource(name="com.newland.iaf.instService")
	private InstService instService;
	@Resource(name = "loanOrdService")
	private LoanOrdService loanordservice;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String termType;
	/**
	 * 是否批量放款
	 */
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Boolean batchCredit = false;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Merch merch;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Merch> merchList;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchBusiInfo merchBusiInfo;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<MerchBusiInfo> merchBusiInfoList;

	@Resource(name = "backStageLoanService")
	private BackStageLoanService backStageLoanService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private FundFlow fundFolw;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal charge;
	
	private String minTerm;
	
	private String maxTerm;
	
	private String  endDate;
	
	private String minRate;
	
	private String maxRate;
	
	private String rateType;
	
	private String minloanAmount;
	
	private String maxloanAmount;
	
	private String pdtId;
	
	private DebitBid debitBid;
	
	@Resource(name = "debitBidService")
	private DebitBidService dbs;
	
	private String area;
	
	private LoanPdt lp;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService lps;
	
	private Integer periods;
	
	@Resource(name = "provinceService")
	private ProvinceService ps;
	
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService lops;
	
	private String target;
	
	private BigDecimal creditTotal;
	
	private Integer creditCount;
	
	public String loanQuery() {
		pdtList=loanPdtService.queryAll();
		return "loanOrdList";
	}
	
	static Set<OrdStat> viewStat = new HashSet<OrdStat>();

	static {
		viewStat.add(OrdStat.AUDIT);
		viewStat.add(OrdStat.CREDITING);
		viewStat.add(OrdStat.REFUND);
	}

	public String query(Page page) throws Exception {
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setShield(0);
		if (StringUtils.isNotBlank(loanOrdId)) {
			cond.setLoanOrdId(loanOrdId.trim() + "%");
		}
		cond.setIinst(this.getInst().getIinst());
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
		if(StringUtils.isNotBlank(acceptDate)){
			cond.setStartexpiryDate(this.parser(acceptDate,Date.class));
		}
		if(StringUtils.isNotBlank(endDate)){
			cond.setEndexpiryDate(this.parser(endDate, Date.class));
		}
		
		if(StringUtils.isNotBlank(termType)&&StringUtils.isNotBlank(minTerm)&&StringUtils.isNotBlank(maxTerm)){
			cond.setTermType(TermType.valueOf(termType));
		}
//		if (StringUtils.isNotBlank(applyDate)) {
//			cond.setAcceptDateEQ(this.parser(applyDate, Date.class));
//		}
//		if (StringUtils.isNotBlank(acceptDate)) {
//			cond.setExpiryDateEq(this.parser(acceptDate, Date.class));
//		}
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iloanOrd"));
		cond.setOrders(orderList);
		this.loanOrdList = this.instLoanService.queryByCond(cond, page);
		return "loanOrdList";
	}

	
	public String list() throws Exception {
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

	public String toNext() {
		if (CollectionUtils.isEmpty(loanOrdList))
			return "loanOrdList";
		if(StringUtils.isNotBlank(loanId)){
			this.loanOrd=loanordservice.queryByLoanOrdId(loanId);
		}else{
			this.loanOrd = this.loanOrdList.get(index);
		}
		this.setSessionObj("loanOrd", loanOrd);
		this.batchCredit = false;
		merch = this.merchService.findMerchById(loanOrd.getImerch());
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

	public String viewLoanOrd() {
		// loanOrd = this.loanOrdList.get(index);
		// this.setSessionObj("loanOrd", loanOrd);
		// this.operLogs = this.instLoanService.queryOperLogBy(loanOrd);
		this.batchCredit = false;
		return "viewLoanOrd";
	}

	/**
	 * 受理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String credit() throws Exception {
		if(loanOrd.getOrdStat()!=OrdStat.AUDIT){
			throw new Exception("该订单状态不正确, 处理失败");
		}
		try {
			this.instLoanService.credit(loanOrd, trans, getUserSession(), memo,
					getIpaddr(),fundFolw);
			Map<String, Object> map = this.paymentService.encryptAndSign(trans);
			signMsg = map.get("signMsg").toString();
			msg = map.get("msg").toString();
			url = trans.getPayUrl();
			this.paymentService.transfer(trans);
		} catch (Exception e) {
			this.addActionError("放款失败！失败原因：" + e.getMessage());
			logger.error("放款失败", e);
			try {
				this.backStageLoanService.expiryTrans(trans);
			} catch (Exception e1) {
				logger.error("回滚失败", e1);
			}
			return "error";
		}
		return "toPayment";
	}

	/**
	 *平台外放款
	 * 
	 * @return
	 * @throws Exception
	 */
	public String othCredit() throws Exception {
		pdtList=loanPdtService.queryAll();
		try {
			this.instLoanService.othCredit(loanOrd, getUserSession(), memo,
					getIpaddr());
		} catch (Exception e) {
			this.addActionError("平台外放款失败！失败原因：" + e.getMessage());
			logger.error("平台外放款失败", e);
			return "viewLoanOrd";
		}
		return "loanOrdList";
	}

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrd> ords;
	/**
	 * 放款总金额
	 */
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BigDecimal batchAmount;
	/**
	 * 支付账户名称
	 */
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String payAccountName;
	/**
	 * 支付账号
	 */
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String payAccountNo;
	/**
	 * 支付银行
	 */
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String payBankName;
	/**
	 * 支付方式
	 */
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String transType;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private InstBusiInfo instBusiInfo;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst ist;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	
	private List<LoanPdt> pdtList;
	
	private String loanId;
	


	public String batchCreditConfirm() {
		try {
			if (StringUtils.isNotBlank(this.selectedRows)) {
				String[] strs = StringUtils.split(this.selectedRows, ",");
				BigDecimal amount = BigDecimal.ZERO;
				ords = new ArrayList<LoanOrd>();
				for (String str : strs) {
					int index = Integer.parseInt(str.trim());
					LoanOrd ord = this.loanOrdList.get(index);
					if (ord.getOrdStat().ordinal() != OrdStat.AUDIT.ordinal()) {
						this.addActionError("定单号：" + ord.getLoanOrdId()
								+ " 状态不正确，请重新选择！");
						return "loanOrdList";
					}
					amount = amount.add(ord.getQuota());
					ords.add(ord);
				}

				this.batchAmount = amount;
				this.batchCredit = true;
				return "batchCredit";
			} else {
				this.addActionError("未选中订单");
				return "loanOrdList";
			}
		} catch (Exception e) {
			addActionError("批量放款失败！失败原因：" + e.getMessage());
			logger.error("批量放款失败", e);
			return "batchCredit";
		}

	}

	@Resource(name = "hicardPaymentService")
	private HicardPaymentService hicardPaymentService;

	public String batchCredit() throws Exception {
		try {
			if (StringUtils.isBlank(transType)) {
				addActionError("支付方式未设置！");
				return "batchCredit";
			}
			/*
			 * if (StringUtils.isBlank(payAccountNo)){
			 * addActionError("支付账号未设置！"); return "batchCredit"; } if
			 * (StringUtils.isBlank(payAccountName)){
			 * addActionError("支付账户名称未设置！"); return "batchCredit"; } if
			 * (StringUtils.isBlank(payBankName)){ addActionError("支付银行名称未设置！");
			 * return "batchCredit"; }
			 */
			this.instLoanService.batchCredit(ords, trans, getUserSession(),
					memo, getIpaddr());
			Map<String, Object> map = this.paymentService.encryptAndSign(trans);
			signMsg = map.get("signMsg").toString();
			msg = map.get("msg").toString();
			url = trans.getPayUrl();
			this.hicardPaymentService.transfer(trans);

		} catch (Exception e) {
			this.addActionError("处理批量放款请求失败！失败原因：" + e.getMessage());
			logger.error("批量放款失败", e);
			try {
				this.backStageLoanService.expiryTrans(trans);
			} catch (Exception e1) {
				logger.error("回滚失败", e1);
			}
			return "error";
		}
		return "toPayment";
	}

	private String signMsg;

	private String msg;

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	private String url;

	public String rePay(){
		target="/inst/loanord/prodCredit!toNext?loanId="+loanId;
		return "rePay";
	}
	
	public String viewTransMsg() {
		//交易前做一次查询
		List<FundFlow> FundFlowList =  fundFlowService.queryByIloanOrdAndType(loanOrd.getIloanOrd(), FundFlowType.CREDIT);
		if(!CollectionUtils.isEmpty(FundFlowList)){
			for (FundFlow ff : FundFlowList) {
				TransMsg tm= transMsgService.findById(ff.getOtherSysTraceNo());
				if(tm.getTransStat()!=TransStat.UNCONFIRMED){
					continue;
				}
				if(ff.getStatus()==FundFlowStat.EXPIRY){
					continue;
				}
				try {
					TransMsg transRes=paymentService.synchronize(tm);
					if(transRes.getRespCode().equals("00")==true){
						logger.info("交易已放款:"+tm.getOrderNo());
						return "creditError";
					}
				} catch (Throwable e) {
					logger.info("同步交易查询 orderNo:"+tm.getOrderNo(),e);
				}
			}
		}
				
		transType = GateWayType.HICARD.name();
		merchList = new ArrayList<Merch>();
		try {
			if (batchCredit == false) {
				trans = this.paymentService.genCreditTransMsg(loanOrd,
						GateWayType.HICARD, getBaseUrl());
				merch = merchService.findMerchById(loanOrd.getImerch());
				merchBusiInfo = merchService.getMerchBusiInfoByImerch(loanOrd
						.getImerch());
				fundFolw=instLoanService.genFundFlow(loanOrd, trans,FundFlowType.CREDIT,FundFlowStat.INIT);
				instBusiInfo = instService.findInstBusiInfoByiinst(loanOrd.getIinst());
				ist = instService.findInstById(loanOrd.getIinst());
				charge = trans.getOrderAmount().subtract(loanOrd.getQuota());
			} else {
				trans = this.paymentService.genCreditTransMsg(ords,
						GateWayType.HICARD, getBaseUrl());
				fundFolw=instLoanService.genFundFlow(loanOrd, trans,FundFlowType.CREDIT,FundFlowStat.INIT);
				for (LoanOrd loanOrd : ords) {
					merch = merchService.findMerchById(loanOrd.getImerch());
					merchList.add(merch);
					merchBusiInfo = merchService
							.getMerchBusiInfoByImerch(loanOrd.getImerch());
					merchBusiInfoList.add(merchBusiInfo);
				}
			}

		} catch (Exception e) {
			logger.error("生成支付指令失败！", e);
			addActionError("生成交易指令失败！失败原因：" + e.getMessage() + ", 请关闭后重新提交！");
			return "error";
		}
		this.setSessionObj("transMsg", trans);
		return "viewTransMsg";
	}

	/**
	 * 撤销
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		try {
			this.instLoanService.cancel(loanOrd, getUserSession(), memo,
					getIpaddr());
		} catch (Exception e) {
			this.addActionError("撤销订单失败！失败原因：" + e.getMessage());
			logger.error("撤销订单失败", e);
			return "viewLoanOrd";
		}
		return "loanOrdList";
	}

	/**
	 * 返回
	 * 
	 * @return
	 * @throws Exception
	 */

	public String returnAction() throws Exception {
		pdtList=loanPdtService.queryAll();
		return "loanOrdList";
	}

	@Override
	@Begin
	@End
	public String execute() throws Exception {
		//LoanPdt loanPdt = new LoanPdt();
		//loanPdt.setIloanPdt(Long.parseLong("00"));
		//loanPdt.setPdtName("(非放贷产品)");
		pdtList=loanPdtService.queryAll();
		//pdtList.add(loanPdt);
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
		this.setFlowType(FlowType.CREIDT);
		Set<OrdStat> auditStatus = new HashSet<OrdStat>();
		auditStatus.add(OrdStat.AUDIT);
		LoanOrdCondition loanordcondition = new LoanOrdCondition();
		loanordcondition.setStatus(auditStatus);
		loanordcondition.setIinst(getInst().getIinst());
		List<LoanOrd> list = loanordservice.queryByCondition(loanordcondition);
		creditTotal = BigDecimal.ZERO;
		if (list.size() > 0) {
			creditCount = Integer.valueOf(list.size());
		} else {
			creditCount = Integer.valueOf(0);
		}
		for (LoanOrd lo : list) {
			creditTotal = creditTotal.add(lo.getQuota());
		}
		creditTotal = creditTotal.divide(new BigDecimal(10000), 2, 4);
		return "loanOrdList";
	}

	static Set<OrdStat> status = new HashSet<OrdStat>();
	static {
		status.add(OrdStat.REFUND);
		status.add(OrdStat.AUDIT);
		status.add(OrdStat.CREDITING);
	}

	public Set<OrdStat> getStatus() {
		return status;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public List<LoanOrd> getLoanOrdList() {
		return loanOrdList;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public FundFlow getFundFolw() {
		return fundFolw;
	}

	public void setFundFolw(FundFlow fundFolw) {
		this.fundFolw = fundFolw;
	}

	public MerchBusiInfo getMerchBusiInfo() {
		return merchBusiInfo;
	}

	public void setMerchBusiInfo(MerchBusiInfo merchBusiInfo) {
		this.merchBusiInfo = merchBusiInfo;
	}

	public List<MerchBusiInfo> getMerchBusiInfoList() {
		return merchBusiInfoList;
	}

	public void setMerchBusiInfoList(List<MerchBusiInfo> merchBusiInfoList) {
		this.merchBusiInfoList = merchBusiInfoList;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public List<Merch> getMerchList() {
		return merchList;
	}

	public void setMerchList(List<Merch> merchList) {
		this.merchList = merchList;
	}

	@JqDataSet(content = "{o:loanOrdId},{o:pdtType.desc},{o:pdtName},{f:(wanyuanFormatter)({o:quota})},{o:pdtTerm},{o:pdtRate},{o:merchName},{i:(format.date)({o:checkDate})},{o:ordStat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public InstBusiInfo getInstBusiInfo() {
		return instBusiInfo;
	}

	public void setInstBusiInfo(InstBusiInfo instBusiInfo) {
		this.instBusiInfo = instBusiInfo;
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

	public List<OperLog> getOperLogs() {
		return operLogs;
	}

	public void setOperLogs(List<OperLog> operLogs) {
		this.operLogs = operLogs;
	}

	public String getSelectedRows() {
		return selectedRows;
	}

	public Inst getIst() {
		return ist;
	}

	public void setIst(Inst ist) {
		this.ist = ist;
	}

	public void setSelectedRows(String selectedRows) {
		this.selectedRows = selectedRows;
	}

	public List<LoanOrd> getOrds() {
		return ords;
	}

	public void setOrds(List<LoanOrd> ords) {
		this.ords = ords;
	}

	public BigDecimal getBatchAmount() {
		return batchAmount;
	}

	public void setBatchAmount(BigDecimal batchAmount) {
		this.batchAmount = batchAmount;
	}

	public String getPayAccountName() {
		return payAccountName;
	}

	public void setPayAccountName(String payAccountName) {
		this.payAccountName = payAccountName;
	}

	public String getPayAccountNo() {
		return payAccountNo;
	}

	public void setPayAccountNo(String payAccountNo) {
		this.payAccountNo = payAccountNo;
	}

	public String getPayBankName() {
		return payBankName;
	}

	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public TransMsg getTrans() {
		return trans;
	}

	public void setTrans(TransMsg trans) {
		this.trans = trans;
	}

	public Boolean isBatchCredit() {
		return batchCredit;
	}

	public void setBatchCredit(Boolean batchCredit) {
		this.batchCredit = batchCredit;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public DebitBid getDebitBid() {
		return debitBid;
	}

	public void setDebitBid(DebitBid debitBid) {
		this.debitBid = debitBid;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}


	public BigDecimal getCreditTotal() {
		return creditTotal;
	}


	public void setCreditTotal(BigDecimal creditTotal) {
		this.creditTotal = creditTotal;
	}


	public Integer getCreditCount() {
		return creditCount;
	}


	public void setCreditCount(Integer creditCount) {
		this.creditCount = creditCount;
	}

}
