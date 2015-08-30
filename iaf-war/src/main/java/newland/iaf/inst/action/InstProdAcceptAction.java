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
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.Province;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.model.dict.TermType;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.LoanOrdService;
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
 * 机构产品受理
 * @author new
 *
 */
@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "prodAccept")
@Results({
	@Result(name = "loanOrdList", type = "dispatcher",location = "/inst/loanord/accept/loanOrdIndex.jsp"),
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "toNext", location="/inst/loanord/accept/demo.jsp"),
	@Result(name = "viewLoanOrd", location="/inst/loanord/accept/demo.jsp")
	//@Result(name = "viewLoanOrd", location="/inst/loanord/accept/loanOrdDetail.jsp")
    })
public class InstProdAcceptAction extends InstBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataSet dataSet;//用户JQGrid数据

	@Resource (name = "instLoanService")
	private InstLoanService instLoanService;
	
	@Resource (name = "loanOrdService")
	private LoanOrdService loanOrdService;
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrd> loanOrdList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrd loanOrd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Merch merch; 
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private int index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanOrdId;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanPdtName;
	
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
	private String beginDate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String endDate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String memo;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService loanPdtService;
	
	@Resource(name = "provinceService")
	private ProvinceService ps;
	
	@Resource(name = "loanOrdPlanService")
	private LoanOrdPlanService lops;
	
	private String pdtType;
	
	private String endloanAmount;
	
	private String jzbeginDate;
	
	private String jzendDate;
	
	private String endterm;
	
	private String pdtId;
	
	private List<LoanPdt> pdtList;
	
	private DebitBid debitBid;
	
	@Resource(name = "debitBidService")
	private DebitBidService dbs;
	
	private String area;
	
	private LoanPdt lp;
	
	@Resource(name = "loanPdtService")
	private LoanPdtService lps;
	
	private Integer periods;
	
	private String termType;
	
	private Long idebitBid;
	
	private String tip;
	
	
	@Resource(name = "debitBidService")
	private DebitBidService debitBidService;
	
	public String query(Page page) throws Exception{
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setIinst(this.getInst().getIinst());
		cond.setShield(0);
		if(StringUtils.isNotBlank(loanOrdId)){
			cond.setLoanOrdId(loanOrdId);
		}

		if (StringUtils.isNotBlank(ordStat)){
			cond.setOrdStat(OrdStat.valueOf(ordStat));
		}else{
			cond.setStatus(status);
		}
		if(StringUtils.isNotBlank(merchName)){
			cond.setMerchName(merchName.trim()+"%");
		}
		if (StringUtils.isNotBlank(pdtType)){
			cond.setPdtType(PdtType.valueOf(pdtType));
		}
		if(StringUtils.isNotBlank(loanAmount)){
			cond.setMinLoanAmount(this.parser(loanAmount, BigDecimal.class).multiply(new BigDecimal(10000)));
		}
		if(StringUtils.isNotBlank(endloanAmount)){
			cond.setMaxLoanAmount(this.parser(endloanAmount, BigDecimal.class).multiply(new BigDecimal(10000)));
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
		
		if(StringUtils.isNotBlank(term)){
			cond.setMinTerm(this.parser(term, Integer.class));
		}
		
		if(StringUtils.isNotBlank(endterm)){
			cond.setMaxTerm(this.parser(endterm, Integer.class));
		}
		if(StringUtils.isNotBlank(beginDate)){
			cond.setStartReqDate(this.parser(beginDate, Date.class));
		}
		if(StringUtils.isNotBlank(endDate)){
			cond.setEndReqDate(this.parser(endDate, Date.class));
		}
		
		if(StringUtils.isNotBlank(jzbeginDate)){
			cond.setStartexpiryDate(this.parser(jzbeginDate, Date.class));
		}
		
		if(StringUtils.isNotBlank(jzendDate)){
			cond.setEndexpiryDate(this.parser(jzendDate, Date.class));
		}
		
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("loanOrdId"));
		cond.setOrders(orderList);
		this.loanOrdList = this.instLoanService.queryByCond(cond,page);
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
			logger.error("列表失败", e);
			throw e;
		}
		
	}
	
	public String instApplyDebit() throws Exception{
		pdtList=loanPdtService.queryAll();
		if(idebitBid==null){
			addActionError("放贷失败!检索不到放贷编号!");
			return "loanOrdList";
		}
		DebitBid debitBid = this.debitBidService.getDebitBidById(idebitBid);
		if(debitBid==null){
			addActionError("放贷失败!检索不到放贷产品!");
			return "loanOrdList";
		}
		loanOrd = this.instLoanService.queryDebitByInst(debitBid, getInst());
		if(loanOrd==null){
			addActionError("放贷失败!检索不到放贷订单!");
			return "loanOrdList";
		}
		if(loanOrd.getOrdStat()!=OrdStat.APPLY){
			tip="您已经受理过该竟投产品申请！";
		}
		this.setSessionObj("loanOrd", loanOrd);
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
		if(StringUtils.isNotBlank(loanOrd.getLoanPdtId())){
			periods=lops.queryByLoanOrdId(loanOrd.getLoanPdtId()).size();
		}else{
			periods=0;
		}
		return "toNext";
	}
	
	public String toNext(){
		if (CollectionUtils.isEmpty(loanOrdList)) return "loanOrdList";
		this.loanOrd = this.loanOrdList.get(index);
		this.setSessionObj("loanOrd", loanOrd);
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
	
	public String viewLoanOrd(){
		//this.loanOrd = this.loanOrdList.get(index);
		//this.setSessionObj("loanOrd", loanOrd);
		return "viewLoanOrd";
	}
	/**
	 * 受理
	 * @return
	 * @throws Exception
	 */
	public String accept()throws Exception{
		pdtList=loanPdtService.queryAll();
		if (StringUtils.isEmpty(memo)){
			addActionError("备注信息不能为空！");
			return "viewLoanOrd";
		}
		try{
			this.instLoanService.accept(loanOrd, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			this.addActionError("受理失败！失败原因：" + e.getMessage());
			logger.error("受理失败", e);
			return "viewLoanOrd";
		}
		return "loanOrdList";
	}
	
	/**
	 * 不受理
	 * @return
	 * @throws Exception
	 */
	public String refuse() throws Exception{
		pdtList=loanPdtService.queryAll();
		if (StringUtils.isEmpty(memo)){
			addActionError("备注信息不能为空！");
			return "viewLoanOrd";
		}
		try{
			this.instLoanService.refuse(loanOrd, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			this.addActionError(e.getMessage());
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
			logger.error("撤销失败", e);
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
	@End
	public String execute() throws Exception {
		this.setFlowType(FlowType.ACCEPT);
		pdtList=loanPdtService.queryAll();
		loanOrdId="";
		merchName="";
		//pdtType="";
		loanAmount="";
		endloanAmount="";
		pdtId="";
		term="";
		endterm="";
		beginDate="";
		endDate="";
		jzbeginDate="";
		jzendDate="";
		termType="";
		return "loanOrdList";
	}
	static Set<OrdStat> status = new HashSet<OrdStat>();
	static{
		status.add(OrdStat.APPLY);
		status.add(OrdStat.APPLY_OVERDUE);
		status.add(OrdStat.ACCEPT_REFUSE);
		status.add(OrdStat.CANCEL);
	}
	
	public String loanQueryPage() {
		pdtList=loanPdtService.queryAll();
		return "loanOrdList";
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

	@JqDataSet(content = "{o:loanOrdId},{o:pdtType.desc},{o:pdtName},{f:(wanyuanFormatter)({o:quota})},{o:pdtTerm},{o:merchName},{i:(format.date)({o:ordDate})},{i:(format.date)({o:expiryDate})},{o:ordStat.desc}")
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

	public List<LoanPdt> getPdtList() {
		return pdtList;
	}



	public void setPdtList(List<LoanPdt> pdtList) {
		this.pdtList = pdtList;
	}



	public String getLoanPdtName() {
		return loanPdtName;
	}


	public String getTip() {
		return tip;
	}



	public void setTip(String tip) {
		this.tip = tip;
	}



	public void setLoanPdtName(String loanPdtName) {
		this.loanPdtName = loanPdtName;
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



	public Integer getPeriods() {
		return periods;
	}



	public void setPeriods(Integer periods) {
		this.periods = periods;
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



	public DebitBid getDebitBid() {
		return debitBid;
	}



	public void setDebitBid(DebitBid debitBid) {
		this.debitBid = debitBid;
	}



	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
	}



	public String getPdtType() {
		return pdtType;
	}



	public void setPdtType(String pdtType) {
		this.pdtType = pdtType;
	}



	public String getEndloanAmount() {
		return endloanAmount;
	}



	public void setEndloanAmount(String endloanAmount) {
		this.endloanAmount = endloanAmount;
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}



	public Long getIdebitBid() {
		return idebitBid;
	}



	public void setIdebitBid(Long idebitBid) {
		this.idebitBid = idebitBid;
	}

}
