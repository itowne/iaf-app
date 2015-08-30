package newland.iaf.inst.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.base.model.dict.DebitbidStat;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.merch.action.MerchDebitBidAction;
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
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.util.CollectionUtils;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 竞投申请受理Action
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "bidAccept")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "detail", type = "dispatcher",location = "/inst/loanord/accept/bidAcceptDetail.jsp"),
	@Result(name = "bidDetail", type = "dispatcher",location = "/inst/loanord/accept/bidDetail.jsp"),
	@Result(name = "success",type = "dispatcher", location="/inst/loanord/accept/bidAcceptIndex.jsp")
    })
public class InstBidAcceptAction extends InstBaseAction {
	
	private static final long serialVersionUID = 136925427971756267L;

	private DataSet dataSet;// 用户JQGrid数据

	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	@Resource (name = "instLoanService")
	private InstLoanService instLoanService;
	@Resource(name = "debitBidService")
	private DebitBidService debitBidService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrd> loanOrdList;
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
	private String merchName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String merchType;
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
	private Merch merch; 
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String memo;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<DebitBid> debitBidList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private DebitBid debitBid;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String debitBidId;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long idebitBid;
	
	@Override
	@Begin
	@End
	public String execute() throws Exception {
		//可以初始化一些页面要的下拉选项，如受理地区
		return "success";
	}
	
	public String query(){
		return "success";
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
			DebitBidCondition debitBidCondition = new DebitBidCondition();
			debitBidCondition.setDebitbidStat(DebitbidStat.NORMAL);
			// 设置查询条件
			if (StringUtils.isNotBlank(yearRate)) {
				debitBidCondition.setRate(new BigDecimal(yearRate.trim()));
			}
			if (StringUtils.isNotBlank(quota)) {
				debitBidCondition.setQuota(new BigDecimal(quota.trim()).multiply(new BigDecimal(10000)));
			}
			if (StringUtils.isNotBlank(term)) {
				debitBidCondition.setTerm(new Integer(term.trim()));
			}
			if (StringUtils.isNotBlank(merchName)) {
				debitBidCondition.setMerchName(merchName);
			}
			if (StringUtils.isNotBlank(merchType)) {
				debitBidCondition.setMerchType(MerchType.valueOf(merchType));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(startDate)){
				debitBidCondition.setStartGenDate(sdf.parse(startDate.trim()));
			}
			if(StringUtils.isNotBlank(endDate)){
				debitBidCondition.setEndGenDate(sdf.parse(endDate.trim()));
			}
			debitBidCondition.setDebitBidId(debitBidId);

			debitBidList = this.debitBidService.queryDebitBidByCon(debitBidCondition, page);
			dataSet.setGridModel(debitBidList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			debitBidId = null;
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			debitBidId = null;
			throw e;
		}

	}
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	LoanOrd loanOrd;
	
	private boolean disable = false;
	
	public String queryOrderInfo() throws Exception {
		debitBid = this.debitBidService.getDebitBidById(idebitBid);
		LoanOrd ord = new LoanOrd();
		ord.setImerch(debitBid.getImerch());
		this.setSessionObj("loanOrd", ord);
		merch = merchService.findMerchById(debitBid.getImerch());
		loanOrd = this.instLoanService.queryLoanOrdByDebit(debitBid, getInst());
		if (loanOrd == null){
			this.disable = false;
		}else{
			if(loanOrd.getOrdStat()!=OrdStat.APPLY){
				this.disable = false;
			}else{
				this.disable = true;	
			}
		}
		
		return "detail";
	}
	
	public String acceptOrderInfo() throws Exception{
		if (CollectionUtils.isEmpty(debitBidList)) return "success";
		debitBid = debitBidList.get(index);
		LoanOrd ord = new LoanOrd();
		ord.setImerch(debitBid.getImerch());
		this.setSessionObj("loanOrd", ord);
		merch = merchService.findMerchById(debitBid.getImerch());
		loanOrd = this.instLoanService.queryLoanOrdByDebit(debitBid, getInst());
		if (loanOrd == null){
			this.disable = false;
		}else{
			this.disable = true;
		}
		return "detail";
	}
	
	public String bidDetail(){
		return "bidDetail";
	}
	
	/**
	 * 受理
	 * @return
	 * @throws Exception
	 */
	public String accept()throws Exception{
		try{
			if (StringUtils.isEmpty(memo)){
				addActionError("备注信息不能为空！");
				return "detail";
			}
			if(index!=null){
				debitBid=debitBidList.get(index);
			}
			if(idebitBid!=null){
				debitBid=debitBidService.getDebitBidById(idebitBid);
			}
			try{
				// 新增订单
				this.instLoanService.applyDebit(debitBid, getUserSession(), memo, getIpaddr());
			}catch(Exception e){
				this.addActionError("受理失败！失败原因：" + e.getMessage());
				logger.error("受理失败", e);
				return "detail";
			}
			return "success";
		} catch (Exception e) {
			logger.error("受理竟投产品错误", e);
			super.addActionError(e.getMessage());
			return "detail";
		}
	}
	
	/**
	 * 不受理（取消)
	 * @return
	 * @throws Exception
	 */
	public String refuse() throws Exception{
		if (StringUtils.isEmpty(memo)){
			addActionError("备注信息不能为空！");
			return "detail";
		}
		try{
			//this.instLoanService.refuse(loanOrd, getUserSession(), memo, getIpaddr());
		}catch(Exception e){
			this.addActionError(e.getMessage());
			return "detail";
		}
		return "success";
	}
	/**
	 * 撤销 （取消）
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception{
		try{
			if (StringUtils.isEmpty(memo)){
				addActionError("备注信息不能为空！");
				return "detail";
			}
			try{
				//this.instLoanService.cancel(loanOrd, getUserSession(), memo, getIpaddr());
			}catch(Exception e){
				this.addActionError("撤销失败！失败原因：" + e.getMessage());
				logger.error("撤销失败", e);
				return "detail";
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "detail";
		}
	}
	/**
	 * 返回
	 * @return
	 * @throws Exception 
	 */
	public String returnAction() throws Exception{
		return execute();
	}


	@JqDataSet(content = "{o:merchName},{o:merchType.desc},{f:(wanyuanFormatter)({o:quota})},{i:(format.percent)({o:yearRate})},{o:term},{o:genTime},{o:expireDate},{o:bidStat.desc}")
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

	public String getStartDate() {
		return startDate;
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


	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<DebitBid> getDebitBidList() {
		return debitBidList;
	}

	public void setDebitBidList(List<DebitBid> debitBidList) {
		this.debitBidList = debitBidList;
	}

	public DebitBid getDebitBid() {
		return debitBid;
	}

	public void setDebitBid(DebitBid debitBid) {
		this.debitBid = debitBid;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}

	public String getDebitBidId() {
		return debitBidId;
	}

	public void setDebitBidId(String debitBidId) {
		this.debitBidId = debitBidId;
	}

	public Long getIdebitBid() {
		return idebitBid;
	}

	public void setIdebitBid(Long idebitBid) {
		this.idebitBid = idebitBid;
	}

}
