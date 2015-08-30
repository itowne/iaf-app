package newland.iaf.inst.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.condition.OperLogCondition;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.impl.InstSession;
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
 * 机构系统日志
 * @author tang
 *
 */

@ParentPackage("struts")
@Namespace("/inst")
@Action("operLog")
@Results({
	@Result(name = "success", type = "dispatcher",location = "/inst/sysLog.jsp"),
	@Result(name = "operLogsList", type = "dispatcher",location = "/inst/sysLog.jsp"),
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "viewOperLogs", location="/inst/sysLogDetail.jsp")
    })
public class InstOperLogAction extends InstBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8902804913632298516L;
	InstSession instSession = this.getUserSession();
	
	private DataSet dataSet;//用户JQGrid数据
	
	@Resource (name = "operLogService")
	private OperLogService operLogService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<OperLog> operLogsList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private OperLog operLog;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long instId;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loginName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String beginTime;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String endTime;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	
	private String operType;
	
	private String ipAddr;
	
	@Override
	@Begin
	
	public String execute(){
		return "success";
	}
	
	public String query(Page page) throws Exception{
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		OperLogCondition cond = new OperLogCondition();
		instId = instSession.getInst().getIinst();
		cond.setIinst(instId);
		
		if (StringUtils.isNotBlank(loginName)){
			cond.setLoginName(loginName);
		}
		
		if (StringUtils.isNotBlank(beginTime)){
			cond.setBeginTime(sft.parse(beginTime));
		}
		
		if (StringUtils.isNotBlank(endTime)){
			cond.setEndTime(sft.parse(endTime));
		}
		
		if(StringUtils.isNotBlank(operType)){
			cond.setOperStat(OperStat.valueOf(operType));
		}
		
		if(StringUtils.isNotBlank(ipAddr)){
			cond.setIpAddr(ipAddr+"%");
		}
		cond.setInstType(InstType.INST);
		this.operLogsList = this.operLogService.queryByCond(cond, page);
		return "operLogsList";
	}

	public String list() throws Exception{
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
			this.query(page);
			dataSet.setGridModel(this.operLogsList);
			dataSet.setRecords(operLogsList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";	
		} catch (Exception e) {
			logger.error("列表失败", e);
			throw e;
		}
	}
	
	public String viewOperLogs(){
		operLog = operLogsList.get(index);
		return "viewOperLogs";
	}

	@JqDataSet(content = "{o:loginName},{o:operType.desc},{o:operStat.desc},{o:ipAddr},{i:(format.time)({o:genTime})}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public OperLogService getOperLogService() {
		return operLogService;
	}

	public void setOperLogService(OperLogService operLogService) {
		this.operLogService = operLogService;
	}

	public List<OperLog> getOperLogsList() {
		return operLogsList;
	}

	public void setOperLogsList(List<OperLog> operLogsList) {
		this.operLogsList = operLogsList;
	}

	public Long getInstId() {
		return instId;
	}

	public void setInstId(Long instId) {
		this.instId = instId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public OperLog getOperLog() {
		return operLog;
	}

	public void setOperLog(OperLog operLog) {
		this.operLog = operLog;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

}
