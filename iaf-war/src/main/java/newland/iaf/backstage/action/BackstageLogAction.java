package newland.iaf.backstage.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.condition.OperLogCondition;
import newland.iaf.base.model.dict.InstType;
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

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;


/**
 * 后台管理 后台操作日志
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/backstagelog")
@Action("backstageLog")
@Results({
	@Result(name = "success", type = "dispatcher",location = "/backstage/backstagelog/backstageLogIndex.jsp"),
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "viewOperLogs", location="/backstage/backstagelog/viewOperLogs.jsp")
    })
public class BackstageLogAction extends BSBaseAction {
	
private DataSet dataSet;//用户JQGrid数据
	
	@Resource (name = "operLogService")
	private OperLogService operLogService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<OperLog> operLogsList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private OperLog operLogs;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loginName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Date beginTime;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Date endTime;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	
	@Override
	@Begin
	@End
	public String execute(){
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
			OperLogCondition cond = new OperLogCondition();
			cond.setInstType(InstType.HICARD);
			// 设置查询条件
			if (StringUtils.isNotBlank(loginName)){
				cond.setLoginName(loginName);
			}
			
			if (beginTime != null){
				cond.setBeginTime(beginTime);
			}
			
			if (endTime != null){
				cond.setEndTime(endTime);
			}
			
			operLogsList = this.operLogService.queryByCond(cond, page);
			dataSet.setGridModel(operLogsList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "list";
	}
	
	public String viewOperLogs() {
		operLogs = this.operLogsList.get(index);
		return "viewOperLogs";
	}
	
	@JqDataSet(content = "{o:traceNo},{o:loginName},{o:roleNames},{o:operType.desc},{o:operStat.desc},{i:(format.time)({o:genTime})},{o:ipAddr}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<OperLog> getOperLogsList() {
		return operLogsList;
	}

	public void setOperLogsList(List<OperLog> operLogsList) {
		this.operLogsList = operLogsList;
	}

	public OperLog getOperLogs() {
		return operLogs;
	}

	public void setOperLogs(OperLog operLogs) {
		this.operLogs = operLogs;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}


}
