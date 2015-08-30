package newland.iaf.inst.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.model.InstCollOrd;
import newland.iaf.inst.service.InstCollService;
import newland.iaf.merch.model.MerchType;
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
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 机构关注订单 action
 * @author wwa
 *
 */
@ParentPackage("struts")
@Namespace("/inst")
@Action(value = "instCollOrd")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "failed",  type = "dispatcher",location = "/inst/orders-of-concern.jsp"),
	@Result(name = "success",  type = "dispatcher",location = "/inst/orders-of-concern.jsp")
    })
public class InstCollOrdAction extends InstBaseAction {
	
	private static final long serialVersionUID = -2932251310299204754L;

	private DataSet dataSet;//用户JQGrid数据
	
	@Resource(name="com.newland.iaf.instCollService")
	private InstCollService instCollService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<DebitBid> debitBidList;
	
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
	private String quota;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String startDate;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String endDate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long imerch;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long idebitBid;
	
	@Override
	public String execute() throws Exception {
		return "success";
	}

	public String add() throws Exception {
		
		InstSession instSession = this.getUserSession();
		Long iInst = instSession.getInst().getIinst();
		InstCollOrd ico = this.instCollService.queryInstCollOrdByIinstAndImerchAndIdebitBid(iInst, imerch, idebitBid);
		if(ico==null){
			ico = new InstCollOrd();
			ico.setiInst(iInst);
			ico.setiMerch(imerch);
			ico.setIdebitBid(idebitBid);
			ico.setGenTime(new Date());
			instCollService.addInstCollOrd(ico);
		}
		return execute();
	}
	
	public String move() throws Exception {
		if (index == null) {
			super.addActionMessage("机构对象为空，请检查");
			return "failed";
		}
		DebitBid debitBid = debitBidList.get(index.intValue());
		InstCollOrd ico = this.instCollService.queryInstCollOrdByIinstAndImerchAndIdebitBid(this.getInst().getIinst(), imerch, debitBid.getIdebitBid());
		this.instCollService.deleteInstCollOrd(ico);
		return execute();
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
			
			DebitBidCondition debitBidCondition = new DebitBidCondition();
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
			Long iInst = this.getInst().getIinst();
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.desc("idebitBid"));
			debitBidCondition.setOrders(orderList);
			debitBidList = this.instCollService.queryInstCollOrdByCon(iInst, debitBidCondition, page);
			dataSet.setGridModel(debitBidList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
		
	@JqDataSet(content = "{o:merchName},{o:merchType},{f:(wanyuanFormatter)({o:quota})},{o:term},{o:yearRate},{o:genTime}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public InstCollService getInstCollService() {
		return instCollService;
	}

	public void setInstCollService(InstCollService instCollService) {
		this.instCollService = instCollService;
	}

	public List<DebitBid> getDebitBidList() {
		return debitBidList;
	}

	public void setDebitBidList(List<DebitBid> debitBidList) {
		this.debitBidList = debitBidList;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public Long getIdebitBid() {
		return idebitBid;
	}

	public void setIdebitBid(Long idebitBid) {
		this.idebitBid = idebitBid;
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

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
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

}
