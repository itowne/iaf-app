package newland.iaf.inst.action.common;

import java.util.ArrayList;
import java.util.List;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.merch.model.Merch;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

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

@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "loanOrdList")
@Results({
	@Result(name = "loanOrdList", type = "dispatcher",location = "/inst/loanord/common/loanOrdList.jsp"),
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "viewOrd", type = "dispatcher",location = "/inst/loanord/common/viewOrd.jsp")
    })
public class LoanOrdListAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	protected LoanOrd loanOrd;
	
	private DataSet dataSet;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	protected List<LoanOrd> loanOrdList;
	
	private LoanOrd viewOrd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	protected Merch merch;
	
	public String execute(){
		this.loanOrd = (LoanOrd)super.getSessionObj("loanOrd");
		if (loanOrd == null) {
			addActionError("订单信息未找到！");
			return "error";
		}
		
		this.merch = this.merchService.findMerchById(loanOrd.getImerch());
		if (merch == null) {
			addActionError("商户信息未找到！");
			return "error";
		}
		return "loanOrdList";
	}
	
	private int index;
	
	public String viewOrd(){
		this.viewOrd = this.loanOrdList.get(index);
		return "viewOrd";
	}
	
	
	public void query(Page page) throws Exception{
		LoanOrdCondition cond = new LoanOrdCondition();
		cond.setImerch(loanOrd.getImerch());
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.desc("ordDate"));
		cond.setOrders(orders);
		this.loanOrdList = this.instLoanService.queryByCond(cond, page);
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

	public LoanOrd getLoanOrd() {
		return loanOrd;
	}

	public void setLoanOrd(LoanOrd loanOrd) {
		this.loanOrd = loanOrd;
	}

	public List<LoanOrd> getLoanOrdList() {
		return loanOrdList;
	}

	public void setLoanOrdList(List<LoanOrd> loanOrdList) {
		this.loanOrdList = loanOrdList;
	}

	@JqDataSet(content = "{i:(format.date)({o:ordDate})},{f:(wanyuanFormatter)({o:quota})},{i:(format.money)({o:reciveAmount})},{o:term},{o:instName},{o:ordStat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}


	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public LoanOrd getViewOrd() {
		return viewOrd;
	}

	public void setViewOrd(LoanOrd viewOrd) {
		this.viewOrd = viewOrd;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public Merch getMerch() {
		return merch;
	}


	public void setMerch(Merch merch) {
		this.merch = merch;
	}
}
