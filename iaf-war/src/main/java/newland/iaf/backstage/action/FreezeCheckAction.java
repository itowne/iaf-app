package newland.iaf.backstage.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.backstage.service.BackStageLoanService;
import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.Freeze;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.condition.ApplyRequestCondition;
import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.model.dict.ApplyType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.ApplyReqService;
import newland.iaf.base.service.LoanOrdPlanService;
import newland.iaf.base.service.TFileService;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.service.InstService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.ohuyo.commons.query.criterion.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
@ParentPackage("struts")
@Namespace("/backstage")
@Action(value = "freezeCheck")
@Results({
	@Result(name = "list", type = "JqgridJsonResult", params = {"name", "dataSet"}),
	@Result(name = "planlist", type = "JqgridJsonResult", params = {"name", "planDataSet"}),
	@Result(name = "download", type = "fileDownload", params = {"sourceFileName", "sourceFileName", "targetFileName", "targetFileName"}),
	@Result(name = "index",  type = "dispatcher",location = "/backstage/freezecheck/index.jsp"),
	@Result(name = "viewDetail",  type = "dispatcher",location = "/backstage/freezecheck/viewDetail.jsp"),
	@Result(name = "close",  type = "dispatcher",location = "/backstage/freezecheck/close.jsp")
    })
/**
 * 还款对账
 * @author new
 *
 */
public class FreezeCheckAction extends BSBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataSet dataSet;
	
	private DataSet planDataSet;
	@Resource (name = "backStageLoanService")
	private BackStageLoanService backStageLoanService;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	private List<Inst> instList;
	@Resource(name="loanOrdPlanService")
	private LoanOrdPlanService loanOrdPlanService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 主入口
	 */
	@Begin
	public String execute(){
		instList = instService.queryInst();
		return "index";
	}
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
	private List<ApplyRequest> requestList;
	
	private String acceptbeginDate;
	private String acceptendDate;
	private String ordStat;
	private String type;
	private String merchNo;
	
	@Resource(name = "tfileService")
	private TFileService tfileService;
	
	@Resource(name="applyReqService")
	private ApplyReqService applyReqService;
	
	private TFile freezefile;
	
	private TFile unfreezefile;
	
	public String freezeAccept(){
		applyRequest.setFreezeAcceptTime(new Date());
		applyRequest.setStat(ApplyStat.FREEZE_ACCEPT);
		applyRequest.setFreezeAcceptMemo(memo);
		applyReqService.update(applyRequest);
		return "close";
	}
	
	public String refuseFreezeAccept() throws Exception{
		applyRequest.setStat(ApplyStat.FREEZE_REFUSE);
		applyReqService.update(applyRequest);
		List<LoanOrdPlan> list = loanOrdPlanService.queryPlanById(applyRequest.getIloanOrd());
		String periods[] = applyRequest.getPeriods().split(",");
		for (int i = 0; i < periods.length; i++) {
			if(!CollectionUtils.isEmpty(list)){
				for (LoanOrdPlan plan : list) {
					if(periods[i].equals(plan.getPeriod().toString())){
						plan.setStat(PlanStat.BALANCE);
						loanOrdPlanService.update(plan);
					}
				}
			}
		}
		return "close";
	}
	
	public String freezeSuccess() throws Exception{
		applyRequest.setFreezeSuccessTime(new Date());
		applyRequest.setStat(ApplyStat.FREEZE_SUCCESS);
		applyRequest.setFreezeSuccessMemo(memo);
		applyReqService.update(applyRequest);
		List<LoanOrdPlan> list = loanOrdPlanService.queryPlanById(applyRequest.getIloanOrd());
		String periods[] = applyRequest.getPeriods().split(",");
		for (int i = 0; i < periods.length; i++) {
			if(!CollectionUtils.isEmpty(list)){
				for (LoanOrdPlan plan : list) {
					if(periods[i].equals(plan.getPeriod().toString())){
						plan.setStat(PlanStat.BALANCE_FREEZE);
						loanOrdPlanService.update(plan);
					}
				}
			}
		}
		return "close";
	}
	
	public String freezeFaile() throws Exception{
		applyRequest.setStat(ApplyStat.FREEZE_FAILE);
		applyReqService.update(applyRequest);
		List<LoanOrdPlan> list = loanOrdPlanService.queryPlanById(applyRequest.getIloanOrd());
		String periods[] = applyRequest.getPeriods().split(",");
		for (int i = 0; i < periods.length; i++) {
			if(!CollectionUtils.isEmpty(list)){
				for (LoanOrdPlan plan : list) {
					if(periods[i].equals(plan.getPeriod().toString())){
						plan.setStat(PlanStat.BALANCE);
						loanOrdPlanService.update(plan);
					}
				}
			}
		}
		return "close";
	}
	
	public String unFreezeAccept(){
		applyRequest.setUnFreezeAcceptTime(new Date());
		applyRequest.setStat(ApplyStat.UNFREEZE_ACCRPT);
		applyRequest.setUnFreezeAcceptMemo(memo);
		applyReqService.update(applyRequest);
		return "close";
	}
	
	public String unRefuseFreezeAccept(){
		applyRequest.setStat(ApplyStat.UNFREEZE_REFUSE);
		applyReqService.update(applyRequest);
		return "close";
	}
	
	public String unFreezeSuccess() throws Exception{
		applyRequest.setUnFreezeSuccessTime(new Date());
		applyRequest.setStat(ApplyStat.UNFREEZE_SUCCESS);
		applyRequest.setUnFreezeSuccessMemo(memo);
		applyReqService.update(applyRequest);
		List<LoanOrdPlan> list = loanOrdPlanService.queryPlanById(applyRequest.getIloanOrd());
		String periods[] = applyRequest.getPeriods().split(",");
		for (int i = 0; i < periods.length; i++) {
			if(!CollectionUtils.isEmpty(list)){
				for (LoanOrdPlan plan : list) {
					if(periods[i].equals(plan.getPeriod().toString())){
						plan.setStat(PlanStat.BALANCE);
						loanOrdPlanService.update(plan);
					}
				}
			}
		}
		return "close";
	}
	
	public String unFreezeFaile(){
		applyRequest.setStat(ApplyStat.UNFREEZE_FAILE);
		applyReqService.update(applyRequest);
		return "close";
	}
	
	public void query(Page page){
		
		ApplyRequestCondition cond = new ApplyRequestCondition();
		
		if (StringUtils.isNotBlank(instName)) {
			List<Long> iinstList =new ArrayList<Long>();
			iinstList.add(Long.parseLong(instName));
			cond.setIinstList(iinstList);
		}
		
		if(StringUtils.isNotBlank(merchName)){
			cond.setMerchName(merchName.trim()+"%");
		}
		if(StringUtils.isNotBlank(beginDate)){
			cond.setBeginDate(this.parser(beginDate, Date.class));
		}
		if(StringUtils.isNotBlank(endDate)){
			cond.setEndDate(this.parser(endDate, Date.class));
		}
		
		if(StringUtils.isNotBlank(acceptbeginDate)){
			cond.setAcceptbeginDate(this.parser(acceptbeginDate, Date.class));
		}
		if(StringUtils.isNotBlank(acceptendDate)){
			cond.setAcceptendDate(this.parser(acceptendDate, Date.class));
		}
		
		if(StringUtils.isNotBlank(type)){
			cond.setApplyType(ApplyType.valueOf(type));
		}
		
		if(StringUtils.isNotBlank(merchNo)){
			cond.setMerchNo(merchNo.trim()+"%");
		}
		
		if(StringUtils.isNotBlank(ordStat)){
			cond.setStat(ApplyStat.valueOf(ordStat));
		}else{
			cond.setStatus(status);
		}
		
		this.requestList = this.backStageLoanService.queryApplyRequest(cond, page);
	}
	
	public List<ApplyRequest> query(){
		ApplyRequestCondition cond = new ApplyRequestCondition();
		//cond.setInstName(instName);
		//cond.setMerchName(merchName);
		//cond.setBeginDate(this.parser(beginDate, Date.class));
		//cond.setEndDate(this.parser(endDate, Date.class));
		//cond.setApplyType(ApplyType.FREEZE);
		Set<ApplyStat> set = new HashSet<ApplyStat>();
		set.add(ApplyStat.FREEZE_ACCEPT);
		set.add(ApplyStat.UNFREEZE_ACCRPT);
		cond.setStatus(set);
		return this.backStageLoanService.queryApplyRequest(cond);
	}
	
	static Set<ApplyStat> status = new HashSet<ApplyStat>();
	{
		status.add(ApplyStat.FREEZE_ACCEPT);
		status.add(ApplyStat.FREEZE_APPLY);
		status.add(ApplyStat.FREEZE_FAILE);
		status.add(ApplyStat.FREEZE_REFUSE);
		status.add(ApplyStat.FREEZE_SUCCESS);
		
		status.add(ApplyStat.UNFREEZE_ACCRPT);
		status.add(ApplyStat.UNFREEZE_APPLY);
		status.add(ApplyStat.UNFREEZE_FAILE);
		status.add(ApplyStat.UNFREEZE_REFUSE);
		status.add(ApplyStat.UNFREEZE_SUCCESS);
	}

	public String list(){
		try{
			Page page = new Page();
			page.setCount(true);
			page.setCapacity(10);// 设置每页显示数
			page.setPageOffset(dataSet.getPage() - 1);
			this.query(page);
			dataSet.setGridModel(this.requestList);
			dataSet.setRecords(requestList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
		}catch(Exception e){
			logger.error("生成列表出错", e);
		}
		return "list";
	}
	
	public String planlist(){
		try{
			planDataSet.setGridModel(this.plans);
			planDataSet.setRecords(plans.size());
		}catch(Exception e){
			logger.error("生成列表出错", e);
		}
		return "planlist";
	}
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private LoanOrd loanOrd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrdPlan> plans;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private ApplyRequest applyRequest;
	
	
	private String index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private boolean disable;
	
	public String viewDetail() throws Exception{
		this.applyRequest = this.requestList.get(Integer.parseInt(index));
		if (this.applyRequest.getStat() == ApplyStat.SUCCESS||this.applyRequest.getStat()==ApplyStat.MERCH_DELIN||this.applyRequest.getStat()==ApplyStat.MERCH_PAYED ){
			this.disable = true;
		}else{
			this.disable = false;
		}
		
		this.loanOrd = this.backStageLoanService.queryLoanById(applyRequest.getIloanOrd());
		if (this.loanOrd == null) {
			addActionError("订单不存在");
			return "close";
		}
		freezefile = tfileService.quueryFreeze(loanOrd.getIloanOrd(), "FREEZE", applyRequest.getPeriods());
		unfreezefile = tfileService.quueryFreeze(loanOrd.getIloanOrd(), "UNFREEZE", applyRequest.getPeriods());
		this.plans = new ArrayList<LoanOrdPlan>();
		Integer[] ints = this.covert(this.applyRequest.getPeriods());
		for (Integer i : ints){
			plans.add(this.backStageLoanService.queryPlan(loanOrd.getIloanOrd(), i));
		}
		return "viewDetail";
	}
	
	private String memo;
	
	/**
	 * 受理
	 * @return
	 */
	public String proc(){
		try{
			this.backStageLoanService.processApply(this.applyRequest, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			addActionError("处理失败！失败原因：[" + e.getMessage() + "]");
			logger.error("处理失败", e);
			return "viewDetail";
		}
		return "close";
	}
	
	/**
	 * 解冻 ---商户已还款 状态变更成 已还款
	 * @return
	 */
	public String unFreezenPayed(){
		try{
			this.backStageLoanService.unFreezenPayed(this.applyRequest, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			addActionError("处理失败！失败原因：[" + e.getMessage() + "]");
			logger.error("处理失败", e);
			return "viewDetail";
		}
		return "close";
	}
	
	/**
	 * 解冻--- 商户未还款  状态变更成 逾期
	 */
	public String unFreezenUnPayed(){
		try{
			this.backStageLoanService.unFreezenUnPay(this.applyRequest, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			addActionError("处理失败！失败原因：[" + e.getMessage() + "]");
			logger.error("冻结请求处理失败", e);
			return "viewDetail";
		}
		return "close";
	}
	/**
	 * 拒决
	 * @return
	 */
	public String refuse(){
		try{
			this.backStageLoanService.refuseApply(this.applyRequest, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			addActionError("处理失败！失败原因：[" + e.getMessage() + "]");
			logger.error("冻结请求处理失败", e);
			return "viewDetail";
		}
		return "close";
	}
	
	private Integer[] covert(String periods){
		String[] strs = StringUtils.split(periods, ",");
		if (strs == null || strs.length == 0) return null;
		Integer[] ints = new Integer[strs.length];
		for (int i = 0 ; i < strs.length ; i++){
			if (StringUtils.isBlank(strs[i])) continue;
			ints[i] = Integer.valueOf(strs[i].trim());
		}
		return ints;
	}
	
	private File sourceFileName;
	
	private String targetFileName;
	/**
	 * 查询按钮
	 * @return
	 */
	public String applyQuery(){
		return "index";
	}
	
	public String download(){
		List<ApplyRequest> list = this.query();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			List<Freeze> freezeList = new ArrayList<Freeze>();
			if(!CollectionUtils.isEmpty(list)){
				for (ApplyRequest apply: list) {
					Freeze freeze = new Freeze();
					freeze.setAmount(apply.getAmount());
					freeze.setInstName(apply.getInstName());
					freeze.setInstNo(apply.getInstNo());
					freeze.setMerchName(apply.getMerchName());
					freeze.setMerchNo(apply.getMerchNo());
					freeze.setGenTime(apply.getGenTime());
					freeze.setApplyType(apply.getApplyType());
					freeze.setStat(apply.getStat());
					freeze.setLoanOrdId(apply.getLoanOrdId());
					if(apply.getApplyType()==ApplyType.FREEZE){
						freeze.setAcceptDate(apply.getFreezeAcceptTime());
					}else{
						freeze.setAcceptDate(apply.getUnFreezeAcceptTime());
					}
					freezeList.add(freeze);
				}
			}
			this.sourceFileName = this.backStageLoanService.genApplyFile(freezeList);
			this.targetFileName = "djqqwj_" + sdf.format(new Date()) + ".xls";
		}catch(Exception e){
			addActionError("文件生成失败!失败原因：[" + e.getMessage() + "]");
			logger.error("文件生成失败", e);
			return "index";
		}
		return "download";
	}
	
	@JqDataSet(name = "dataSet", content = "{o:instName},{o:applyType.desc},{o:merchName},{o:merchNo},{o:amount},{i:(format.time)({o:genTime})},{i:(format.time)({o:freezeAcceptTime})},{o:stat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<ApplyRequest> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<ApplyRequest> requestList) {
		this.requestList = requestList;
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

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}

	public List<LoanOrdPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<LoanOrdPlan> plans) {
		this.plans = plans;
	}

	public ApplyRequest getApplyRequest() {
		return applyRequest;
	}

	public void setApplyRequest(ApplyRequest applyRequest) {
		this.applyRequest = applyRequest;
	}


	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public File getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(File sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
	@JqDataSet(name="planDataSet", content = "{o:period},{i:(format.date)({o:refundDate})},{i:(format.money)({o:repayment})},{i:(format.money)({o:capital})},{i:(format.money)({o:interest})},{i:(format.money)({o:remainAmount})}")
	public DataSet getPlanDataSet() {
		return planDataSet;
	}

	public void setPlanDataSet(DataSet planDataSet) {
		this.planDataSet = planDataSet;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public String getAcceptbeginDate() {
		return acceptbeginDate;
	}

	public void setAcceptbeginDate(String acceptbeginDate) {
		this.acceptbeginDate = acceptbeginDate;
	}

	public String getAcceptendDate() {
		return acceptendDate;
	}

	public void setAcceptendDate(String acceptendDate) {
		this.acceptendDate = acceptendDate;
	}

	public String getOrdStat() {
		return ordStat;
	}

	public void setOrdStat(String ordStat) {
		this.ordStat = ordStat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public Set<ApplyStat> getStatus() {
		return status;
	}

	public TFile getFreezefile() {
		return freezefile;
	}

	public void setFreezefile(TFile freezefile) {
		this.freezefile = freezefile;
	}

	public TFile getUnfreezefile() {
		return unfreezefile;
	}

	public void setUnfreezefile(TFile unfreezefile) {
		this.unfreezefile = unfreezefile;
	}

}
