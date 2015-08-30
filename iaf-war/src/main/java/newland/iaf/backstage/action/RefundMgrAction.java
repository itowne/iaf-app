package newland.iaf.backstage.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.backstage.service.BackStageLoanService;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.service.OperLogService;
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

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

@ParentPackage("struts")
@Namespace("/backstage")
@Action(value = "refundMgr")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "index",  type = "dispatcher",location = "/backstage/refundmgr/index.jsp"),
	@Result(name = "viewLoanOrd",  type = "dispatcher",location = "/backstage/refundmgr/loanOrdDetail.jsp")
    })
public class RefundMgrAction extends BSBaseAction {

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
	private List<FundFlow> loanFundFlowList;
	
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
	private LoanOrd loanOrd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<FundFlow> fundFlowList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<OperLog> operLogs;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private String type;
	
	public String flowQuery(){
		return "index";
	}
	
	public void query(Page page) throws Exception{
		FundFlowCondition cond = new FundFlowCondition();
		if(StringUtils.isNotBlank(merchName)){
			cond.setMerchName(merchName.trim()+"%");
		}
		if(StringUtils.isNotBlank(instName)){
			cond.setInstName(instName.trim()+"%");
		}
		if(StringUtils.isNotBlank(loanOrdId)){
			cond.setLoanOrdId(loanOrdId.trim()+"%");
		}
		
		if(StringUtils.isNotBlank(type)){
			cond.setType(FundFlowType.valueOf(type));
		}else{
			Set<FundFlowType> setFF=new HashSet<FundFlowType>();
			setFF.add(FundFlowType.REFUND);
			setFF.add(FundFlowType.OTH_REFUND);
			cond.setTypes(setFF);
		}
		
		//cond.setType(FundFlowType.REFUND);
		
		Set<FundFlowStat> statusFF=new HashSet<FundFlowStat>();
		statusFF.add(FundFlowStat.AUDIT);
		statusFF.add(FundFlowStat.SUCCESS);
		statusFF.add(FundFlowStat.EXPIRY);
		cond.setStatus(statusFF);
		this.fundFlowList = this.backStageLoanService.queryFundFlowBy(cond, page);

	}
	
	public String returnAction(){
		return "index";
	}
	
	static Set<FundFlowType> types = new HashSet<FundFlowType>();
	static {
		types.add(FundFlowType.REFUND);
		types.add(FundFlowType.OTH_REFUND);
	}
	
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
			logger.error("查询订单列表出错", e);
		}
		return "list";
	}
	
	public String viewFundFlowDetail(){
		FundFlow ff = this.fundFlowList.get(index);
		this.loanOrd = this.backStageLoanService.queryLoanById(ff.getIloanOrd());
		if (loanOrd == null) {
			addActionError("订单数据有误！");
			return "index";
		}
		try{
			loanFundFlowList = this.backStageLoanService.queryFundFlowBy(types,loanOrd.getIloanOrd());
			this.operLogs = this.operLogService.queryByOrdId(loanOrd.getLoanOrdId());
		}catch(Exception e){
			addActionError("查询记录出错");
			logger.error("查询记录出错",e);
			return "index";
		}
		return "viewLoanOrd";
	}

	@Begin
	
	public String execute(){
		merchName="";
		instName="";
		loanOrdId="";
		type="";
		return "index";
	}
	
	
	@JqDataSet(content = "{o:traceNo},{o:loanOrdId},{o:merchName},{o:instName},{i:(format.time)({o:genTime})},{o:amount},{o:type}")
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

	public List<FundFlow> getLoanFundFlowList() {
		return loanFundFlowList;
	}

	public void setLoanFundFlowList(List<FundFlow> loanFundFlowList) {
		this.loanFundFlowList = loanFundFlowList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
