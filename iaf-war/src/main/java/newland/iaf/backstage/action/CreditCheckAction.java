package newland.iaf.backstage.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.backstage.service.BackStageLoanService;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.HicardPaymentService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.service.InstService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
@ParentPackage("struts")
@Namespace("/backstage")
@Action(value = "creditCheck")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "download", type = "fileDownload", params = {"sourceFileName", "downloadFile", "targetFileName", "targetFileName"}),
	@Result(name = "index",  type = "dispatcher",location = "/backstage/creditcheck/index.jsp"),
	@Result(name = "viewDetail",  type = "dispatcher",location = "/backstage/creditcheck/viewDetail.jsp"),
	@Result(name = "close",  type = "dispatcher",location = "/backstage/creditcheck/close.jsp")
    })
/**
 * 放款对账
 * @author new
 *
 */
public class CreditCheckAction extends BSBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataSet dataSet;
	
	@Resource (name = "fundFlowService")
	private FundFlowService fundFlowService;
	@Resource (name = "loanOrdService")
	private LoanOrdService loanOrdService;
	
	private Inst inst;
	
	private Merch merch;
	
	@Resource(name="com.newland.iaf.instService")
	private InstService instService;
	
	@Resource(name="com.newland.iaf.merchService")
	private MerchService merchService;
	
	private InstBusiInfo instBusiInfo;
	
	private MerchBusiInfo merchBusiInfo;
	
	private BigDecimal charge;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;
	
	private Long count;
	
	private BigDecimal total;
	
	private String maxQuota;
	
	
	
	Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 主入口
	 */
	@Begin
	
	public String execute(){
		this.fundFlowStat  = FundFlowStat.AUDIT.name();
		instList = instService.queryInst();
		loanOrdId="";
		instName="";
		merchName="";
		beginDate="";
		endDate="";
		fundFlowStat="";
		quota="";
		otherNo="";
		maxQuota="";
		Object[] obj={FundFlowType.CREDIT};
		List<Object> objList=fundFlowService.queryTrade(FundFlowStat.SUCCESS,obj);
		Object[] res = (Object[]) objList.get(0);
		total = (BigDecimal) res[0];
		count = (Long) res[1];
		return "index";
	}
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<FundFlow> fundFlowList;
	
	/**
	 * 列表显示
	 * @return
	 */
	public String list(){
		try{
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			page.setPageOffset(dataSet.getPage() - 1);
			this.query(page);
			dataSet.setGridModel(this.fundFlowList);
			dataSet.setRecords(fundFlowList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
		}catch(Exception e){
			logger.error("生成列表出错", e);
		}
		return "list";
	}
	
	static Set<FundFlowStat> status = new HashSet<FundFlowStat>();
	
	static{
		status.add(FundFlowStat.AUDIT);
		status.add(FundFlowStat.SUCCESS);
		status.add(FundFlowStat.EXPIRY);
	}
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loanOrdId;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String instName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String merchName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String beginDate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String endDate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String fundFlowStat;
	
	private String quota;
	
	private String iinst;
	
	private String otherNo;
	
	/**
	 * 查询按钮
	 * @return
	 */
	public String flowQuery(){
		return "index";
	}
	
	/**
	 * 复合查询
	 * @return
	 */
	static List<Order> orders = new ArrayList<Order>();
	static{
		orders.add(Order.asc("otherSysTraceNo"));
	}
	
	public List<FundFlow> query(Page page){
		FundFlowCondition cond = new FundFlowCondition();
		if(StringUtils.isNotBlank(loanOrdId)){
			cond.setLoanOrdId(loanOrdId.trim()+"%");
		}
		if(StringUtils.isNotBlank(quota)){
			BigDecimal amount = new BigDecimal(quota);
			cond.setMinQuota(amount.multiply(new BigDecimal(10000)));
		}
		if(StringUtils.isNotBlank(maxQuota)){
			BigDecimal amount = new BigDecimal(maxQuota);
			cond.setMaxQuota(amount.multiply(new BigDecimal(10000)));
		}
		
		if(StringUtils.isNotBlank(beginDate)){
			cond.setBeginDate(this.parser(beginDate, Date.class));
			cond.setEndDate(this.parser(beginDate, Date.class));
		}
		if(StringUtils.isNotBlank(endDate)){
			cond.setEndDate(this.parser(endDate, Date.class));
		}
		if(StringUtils.isNotBlank(merchName)){
			cond.setMerchName(merchName+"%");
		}
		
		if(StringUtils.isNotBlank(iinst)){
			cond.setIinst(Long.parseLong(iinst));
		}
		
		if(StringUtils.isNotBlank(otherNo)){
			cond.setOtherSysNo(otherNo);
		}
		
		Set<FundFlowType> setFF= new HashSet<FundFlowType>();
		setFF.add(FundFlowType.CREDIT);
		//setFF.add(FundFlowType.OTH_CREDIT);
		cond.setTypes(setFF);
		
		cond.setOrders(orders);
//		if (StringUtils.isNotBlank(this.fundFlowStat)){
//			if(fundFlowStat.equals("ALL")){
//				Set<FundFlowStat> stat = new HashSet<FundFlowStat>();
//				stat.add(FundFlowStat.AUDIT);
//				stat.add(FundFlowStat.SUCCESS);
//				stat.add(FundFlowStat.EXPIRY);
//				cond.setStatus(stat);
//			}else{
//				
//			}
//		}else{
//			if(StringUtils.isNotBlank(loanOrdId)||StringUtils.isNotBlank(quota)||StringUtils.isNotBlank(iinst)||StringUtils.isNotBlank(beginDate)
//				||StringUtils.isNotBlank(merchName)||StringUtils.isNotBlank(this.fundFlowStat)){
//				Set<FundFlowStat> stat = new HashSet<FundFlowStat>();
//				stat.add(FundFlowStat.AUDIT);
//				stat.add(FundFlowStat.SUCCESS);
//				stat.add(FundFlowStat.EXPIRY);
//				cond.setStatus(stat);
//			}else{
//				cond.setStatus(status);
//			}
//		}
		if (StringUtils.isNotBlank(this.fundFlowStat)){
			cond.setStat(FundFlowStat.valueOf(fundFlowStat));
		}else{
			cond.setStatus(status);
		}
		return this.fundFlowList = this.fundFlowService.queryFundFlowBy(cond, page);
	}
	private List<FundFlow> query(){
		FundFlowCondition cond = new FundFlowCondition();
		cond.setLoanOrdId(loanOrdId);
		cond.setBeginDate(this.parser(beginDate, Date.class));
		cond.setEndDate(this.parser(endDate, Date.class));
		cond.setInstName(instName);
		cond.setMerchName(merchName);
		cond.setOrders(orders);
		
		Set<FundFlowType> setFF= new HashSet<FundFlowType>();
		setFF.add(FundFlowType.CREDIT);
		//setFF.add(FundFlowType.OTH_REFUND);
		cond.setTypes(setFF);
		
		if (StringUtils.isNotBlank(this.fundFlowStat)){
			Set<FundFlowStat> stat = new HashSet<FundFlowStat>();
			stat.add(FundFlowStat.AUDIT);
			stat.add(FundFlowStat.SUCCESS);
			stat.add(FundFlowStat.EXPIRY);
			cond.setStatus(stat);
		}
		return this.fundFlowService.queryFundFlowBy(cond);
	}
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private FundFlow fundFlow;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrd loanOrd;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private TransMsg transMsg;
	
	@Resource(name = "backStageLoanService")
	private BackStageLoanService backStageLoanService;
	
	@Resource (name = "hicardPaymentService")
	private HicardPaymentService hicardPaymentService;
	
	/**
	 * 查看流水明细
	 * @return
	 * @throws Exception 
	 */
	public String viewDetail() throws Exception {
		if (StringUtils.isBlank(index)){
			addActionError("参数错误！");
			return "index";
		}
		this.fundFlow = this.fundFlowList.get(Integer.parseInt(index));
		this.loanOrd = this.backStageLoanService.queryLoanById(fundFlow.getIloanOrd());
		this.transMsg = this.hicardPaymentService.queryById(fundFlow.getOtherSysTraceNo());
		if(fundFlow.getType()==FundFlowType.OTH_CREDIT){
			charge=BigDecimal.ZERO;
		}else{
			charge=transMsg.getOrderAmount().subtract(loanOrd.getQuota()).setScale(2);
		}
		if(loanOrd!=null&&loanOrd.getIinst()!=null){
			inst = instService.findInstById(loanOrd.getIinst());
			//instBusiInfo = instService.findInstBusiInfoByiinst(loanOrd.getIinst());
		}
		if(loanOrd!=null&&loanOrd.getImerch()!=null){
			merch = merchService.findMerchById(loanOrd.getImerch());
			merchBusiInfo=merchService.getMerchBusiInfoByImerch(loanOrd.getImerch());
		}
		if (loanOrd != null) {
			loanOrd.setTraceNo(this.fundFlow.getTraceNo());
		}else{
			addActionError("无法查询到订单信息");
			return "close";
		}
		if (this.fundFlow.getStatus() == FundFlowStat.SUCCESS) this.disable = true;
		return "viewDetail";
	}
	
	public String audit(){
		try {
			this.backStageLoanService.auditCredit(fundFlow, getUserSession(), memo, getIpaddr());
		} catch (Exception e) {
			addActionError("处理资金流水出错！错误原因：[" + e.getMessage() + "]");
			logger.error("", e);
			return "viewDetail";
		}
		return "close";
	}
	
	public String cancel(){
		try{
			this.backStageLoanService.cancelCredit(fundFlow, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			addActionError("撤销失败！失败原因：[" + e.getMessage() + "]");
			return "viewDetail";
		}
		return "close";
	}
	
	/**
	 * 选定的行序号
	 */
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String index;
	
	/**
	 * 下载文件
	 */
	private File downloadFile;
	/**
	 * 下载文件名
	 */
	private String targetFileName;
	
	private String memo;
	
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
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
	public Inst getInst() {
		return inst;
	}
	public void setInst(Inst inst) {
		this.inst = inst;
	}
	public Merch getMerch() {
		return merch;
	}
	public void setMerch(Merch merch) {
		this.merch = merch;
	}
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private boolean disable;
	
	/**
	 * 下载所有流水
	 * @return
	 */
	public String download(){
		try{
			List<FundFlow> list = this.query();
			if (CollectionUtils.isEmpty(this.fundFlowList)){
				addActionError("查询无数据！");
				return "index";
			}
			this.downloadFile = this.backStageLoanService.genFlowsFile(list, FundFlowType.CREDIT);
			targetFileName = "fkdzwj_" + this.getDateFormatter().format(new Date()) + ".xls";
		}catch(Exception e){
			addActionError("生成文件错误！");
			logger.error("生成文件错误", e);
			return "index";
		}
		return "download";
	}
	
	/**
	 * 下载订单明细
	 * @return
	 */
	public String downloadOrd(){
		try{
			this.viewDetail();
			//List<LoanOrd> list = new ArrayList<LoanOrd>();
			//list.add(this.loanOrd);
			List<FundFlow> list = new ArrayList<FundFlow>();
			list.add(fundFlow);
			targetFileName = "fkdzwj_" + fundFlow.getLoanOrdId() + ".xls";
			this.downloadFile = this.backStageLoanService.genFlowsFile(list, FundFlowType.CREDIT);
			//this.downloadFile = this.backStageLoanService.genOnceCreditFile(list);
		}catch(Exception e){
			addActionError("生成文件错误！");
			logger.error("生成文件错误", e);
			return "index";
		}
		return "download";
	}

	@JqDataSet(content = "{o:loanOrdId},{o:otherSysTraceNo},{o:instName},{o:merchName},{f:(wanyuanFormatter)({o:amount})},{i:(format.time)({o:genTime})},{o:status.desc},{o:type}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<FundFlow> getFundFlowList() {
		return fundFlowList;
	}

	public void setFundFlowList(List<FundFlow> fundFlowList) {
		this.fundFlowList = fundFlowList;
	}

	public LoanOrdService getLoanOrdService() {
		return loanOrdService;
	}

	public void setLoanOrdService(LoanOrdService loanOrdService) {
		this.loanOrdService = loanOrdService;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getLoanOrdId() {
		return loanOrdId;
	}

	public void setLoanOrdId(String loanOrdId) {
		this.loanOrdId = loanOrdId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public FundFlow getFundFlow() {
		return fundFlow;
	}

	public void setFundFlow(FundFlow fundFlow) {
		this.fundFlow = fundFlow;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}

	public File getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(File downloadFile) {
		this.downloadFile = downloadFile;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public TransMsg getTransMsg() {
		return transMsg;
	}

	public void setTransMsg(TransMsg transMsg) {
		this.transMsg = transMsg;
	}

	public String getFundFlowStat() {
		return fundFlowStat;
	}

	public void setFundFlowStat(String fundFlowStat) {
		this.fundFlowStat = fundFlowStat;
	}
	public String getQuota() {
		return quota;
	}
	public void setQuota(String quota) {
		this.quota = quota;
	}
	public List<Inst> getInstList() {
		return instList;
	}
	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}
	public String getIinst() {
		return iinst;
	}
	public void setIinst(String iinst) {
		this.iinst = iinst;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getOtherNo() {
		return otherNo;
	}
	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}
	public String getMaxQuota() {
		return maxQuota;
	}
	public void setMaxQuota(String maxQuota) {
		this.maxQuota = maxQuota;
	}
}
