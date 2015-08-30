package newland.iaf.backstage.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.IafApplication;
import newland.iaf.base.model.dict.InstStatusType;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.condition.InstCondition;
import newland.iaf.inst.service.InstService;
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

@ParentPackage("struts")
@Namespace("/backstage/product")
@Action(value="instPro")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "success",  type = "dispatcher",location = "/backstage/product/instProduceIndex.jsp")
    })
public class InstProAction extends BSBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2754336253759874109L;
	
	private DataSet dataSet;// 用户JQGrid数据
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String instName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String regAddr;
	
	private String contact;
	
	private String tel;
	
	private String stat;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
	private String  startTime;
	
	private String endTime;
	
	public String execute(){
		instList = instService.queryInst();
		instName="";
		regAddr="";
		contact="";
		tel="";
		stat="";
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
			InstCondition instCondition = new InstCondition();
			// 设置查询条件
			if (StringUtils.isNotBlank(instName)) {
				List<Long> iinstList =new ArrayList<Long>();
				iinstList.add(Long.parseLong(instName));
				instCondition.setIinst(iinstList);
			}
			if (StringUtils.isNotBlank(regAddr)) {
				instCondition.setRegAddr(regAddr.trim()+"%");
			}
			
			if(StringUtils.isNotBlank(contact)){
				instCondition.setContact(contact.trim()+"%");
			}
			
			if(StringUtils.isNotBlank(tel)){
				instCondition.setContactPhone(tel.trim()+"%");
			}
			
			if(StringUtils.isNotBlank(stat)){
				instCondition.setInstStatusType(InstStatusType.valueOf(stat));
			}
			
			if(StringUtils.isNotBlank(startTime)){
				instCondition.setStartGenTime(this.parser(startTime,Date.class));
			}
			
			if(StringUtils.isNotBlank(endTime)){
				instCondition.setEndGenTime(this.parser(endTime, Date.class));
			}
			
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.asc("iinst"));
			instCondition.setOrders(orderList);
			instList = this.instService.queryInstByCon(instCondition, page);
			dataSet.setGridModel(instList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}
	
	@JqDataSet(content = "{o:instName},{o:regAddr},{o:contact},{o:contactPhone},{i:(format.time)({o:genTime})},{o:instStat.desc},{o:iinst}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
