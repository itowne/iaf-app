package newland.iaf.inst.action.common;

import java.util.List;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.condition.OrdOperLogCondition;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.ohuyo.commons.query.criterion.Page;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

@ParentPackage("struts")
@Namespace("/inst/loanord")
@Action(value = "operLogDetail")
@Results({
	@Result(name = "operLogs", type = "dispatcher",location = "/inst/loanord/common/operLogs.jsp")
    })
public class OperLogDetailAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	protected LoanOrd loanOrd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	protected List<OperLog> operLogs;
	
	private Integer pageNum = 1;
	
	private static int pageSize = 10;
	
	private Integer pageCount = 1;
	
	private Boolean prePageDisalbe;
	
	private Boolean nextPageDisable;
	
	public String execute(){
		this.loanOrd = (LoanOrd)super.getSessionObj("loanOrd");
		if (loanOrd == null) {
			addActionError("订单信息未找到！");
			return "error";
		}
		if (pageNum > pageCount) pageNum = pageCount;
		if (pageNum < 1 ) pageNum = 1;
		if (pageNum == 1) {
			this.prePageDisalbe = true;
		}else{
			this.prePageDisalbe = false;
		}
		if (pageNum >= pageCount){
			this.nextPageDisable = true;
		}else{
			this.nextPageDisable = false;
		}
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(pageSize);
		page.setPageOffset(pageNum - 1);
		OrdOperLogCondition cond = new OrdOperLogCondition();
		cond.setIloanOrd(loanOrd.getIloanOrd());
		this.operLogs = this.operLogService.queryByCond(cond, page);
		this.pageCount = page.getPageAmt();
		return "operLogs";
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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public static int getPageSize() {
		return pageSize;
	}

	public static void setPageSize(int pageSize) {
		OperLogDetailAction.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Boolean getPrePageDisalbe() {
		return prePageDisalbe;
	}

	public void setPrePageDisalbe(Boolean prePageDisalbe) {
		this.prePageDisalbe = prePageDisalbe;
	}

	public Boolean getNextPageDisable() {
		return nextPageDisable;
	}

	public void setNextPageDisable(Boolean nextPageDisable) {
		this.nextPageDisable = nextPageDisable;
	}
	
	

}
