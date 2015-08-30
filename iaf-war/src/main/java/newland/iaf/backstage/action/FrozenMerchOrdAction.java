package newland.iaf.backstage.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.condition.LoanOrdCondition;
import newland.iaf.inst.service.InstLoanService;
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
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 商户还款现状Action
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/merchmanager")
@Action(value = "frozenMerchOrd")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "success",  type = "dispatcher",location = "/backstage/merchmanager/frozenMerchIndex.jsp")
    })
public class FrozenMerchOrdAction extends BSBaseAction {
	
	private DataSet dataSet;//用户JQGrid数据
	
	@Resource (name = "instLoanService")
	private InstLoanService instLoanService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<LoanOrd> loanOrdList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long imerch;
	
	@Begin
	
	public String execute(){
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
			LoanOrdCondition cond = new LoanOrdCondition();
			cond.setImerch(imerch);
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.asc("iloanOrd"));
			cond.setOrders(orderList);
			loanOrdList = this.instLoanService.queryByCond(cond,page);
			dataSet.setGridModel(loanOrdList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@JqDataSet(content = "{o:loanOrdId},{o:instName},{i:(format.time)({o:creditDate})},{f:(wanyuanFormatter)({o:quota})},{o:term},{i:(format.money)({o:repayment})},{o:ordStat.desc}")
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

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}
	
	

}
