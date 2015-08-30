package newland.iaf.merch.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.condition.OperLogCondition;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
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
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
@ParentPackage("struts")
@Namespace("/merch")
@Action(value="merchOperLog")
@Results({
	@Result(name="list",type="JqgridJsonResult"),
	@Result(name="operloginfo",location="/merch/operation_log_detail.jsp"),
	@Result(name="success",type="dispatcher",location="/merch/operation_log.jsp")
})
public class MerchOperationLogAction extends MerchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="operLogService")
	private OperLogService operLogService;
	
	private DataSet dataSet;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<OperLog> operLogList;
	
	private OperLog operLog;
	
	private Integer  index;
	private String loginName;
	private Date startDate;

	private Date endDate;
	
	private String operType;
	
	@Override
	public String execute() throws Exception{
		return "success";
	}
	public String list() throws Exception{
		try{
			Page page=new Page();
			page.setCount(true);
			page.setCapacity(10);
			if(dataSet==null){
				dataSet=new DataSet();
				page.setPageOffset(0);
			}else{
				page.setPageOffset(dataSet.getPage()-1);
			}
		    OperLogCondition operLogCondition=new OperLogCondition();
			//设置查询条件
			if(startDate != null){
				operLogCondition.setBeginTime(startDate);
			}
			if(endDate != null){
				operLogCondition.setEndTime(endDate);
			}
			if(StringUtils.isNotBlank(operType)){
				operLogCondition.setOperStat(OperStat.valueOf(operType));
			}
			if(StringUtils.isNotBlank(loginName)){
				operLogCondition.setLoginName(loginName);
			}
			MerchSession merchSession = (MerchSession) SessionFilter
					.getIafSession();
			operLogCondition.setImerch(merchSession.getMerch().getImerch());
			operLogCondition.setInstType(InstType.MERCH);
			operLogList=operLogService.queryByCond(operLogCondition, page);
			
			dataSet.setGridModel(operLogList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()	;
			throw e;
		}	
	}
	public String operLogInfo() throws Exception{
		operLog=operLogList.get(index);
		return "operloginfo";
	}
	
	public OperLogService getOperLogService() {
		return operLogService;
	}
	public void setOperLogService(OperLogService operLogService) {
		this.operLogService = operLogService;
	}
	//@JqDataSet(content = "{o:loginName},{o:operType.desc},{o:operStat},{o:memo},{i:(format.time)({o:genTime})}")
	@JqDataSet(content = "{o:loginName},{o:operType.desc},{o:operStat.desc},{o:memo},{i:(format.time)({o:genTime})}")
	public DataSet getDataSet() {
		return dataSet;
	}
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}
	public List<OperLog> getOperLogList() {
		return operLogList;
	}
	public void setOperLogList(List<OperLog> operLogList) {
		this.operLogList = operLogList;
	}
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public OperLog getOperLog() {
		return operLog;
	}
	public void setOperLog(OperLog operLog) {
		this.operLog = operLog;
	}

	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}

}
