package newland.iaf.backstage.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.InstApplyRequest;
import newland.iaf.base.model.condition.InstApplyRequestCondition;
import newland.iaf.base.model.dict.ApplyStat;
import newland.iaf.base.service.InstApplyReqService;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.inst.model.Inst;
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
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 后台管理 机构申请 ACTION
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/instmanager")
@Action(value = "instApplyRequest")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "detail",  type = "dispatcher",location = "/backstage/instmanager/instApplyDetail.jsp"),
	@Result(name = "checkAndInsert",  type = "dispatcher",location = "/backstage/instmanager/instAdd.jsp"),
	@Result(name="toInstApply",type="dispatcher",location="/backstage/instmanager/toInstApply.jsp"),
	@Result(name="toAdd",type="dispatcher",location="/backstage/instmanager/instAdd.jsp"),
	@Result(name="refuseInstApply",type="dispatcher",location="/backstage/instmanager/refuseInstApply.jsp"),
	@Result(name = "success",  type = "dispatcher",location = "/backstage/instmanager/instApplyRequest.jsp")
    })
public class InstApplyRequestAction extends BSBaseAction {
	
	private static final long serialVersionUID = 630507889943070640L;
	
	private DataSet dataSet;//用户JQGrid数据
	@Resource(name = "instApplyReqService")
	private InstApplyReqService instApplyReqService;
	
	@Resource(name = "provinceService")
	private ProvinceService provinceService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<String, String> provMap;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String cityCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String provinceCode;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String region;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<InstApplyRequest> instApplyList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private InstApplyRequest instApplyRequest;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String instName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String contactName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst inst;
	
	private String regTime;
	
	private String phone;
	
	private String stat;
	
	private String addr;
	private List<InstApplyRequest> instApplyReqList;
	
	private String radio;
	
	private String reason;
	
	@Begin
	
	public String execute(){
		instName="";
		contactName="";
		regTime="";
		phone="";
		stat="";
		addr="";
		provinceCode="";
		cityCode="";
		region="";
		instApplyReqList = instApplyReqService.queryAll();
		provMap = provinceService.getProvince();
		return "success";
	}
	
	public String toAdd(){
		
		return "toAdd";
	}
	
	public String refuseInstApply(){
		
		
		return "refuseInstApply";
	}
	
	public String toInstApply(){
		instApplyReqList = instApplyReqService.queryAll();
		provMap = provinceService.getProvince();
		try{
			//instApplyRequest = instApplyList.get(index);
			if(instApplyRequest.getStat() == ApplyStat.SUCCESS){
				super.addActionMessage("该申请已经受理，不能重复受理！");
				return "success";
			}
			instApplyRequest.setStat(ApplyStat.SUCCESS);
			this.instApplyReqService.update(instApplyRequest);
			inst = new Inst();
			inst.setInstName(instApplyRequest.getInstName());
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "success";
		}
		return "toInstApply";
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
			InstApplyRequestCondition cond = new InstApplyRequestCondition();
			//设置查询条件
			if(StringUtils.isNotBlank(instName)){
				cond.setName(instName);
			}
			if(StringUtils.isNotBlank(contactName)){
				cond.setContactName(contactName.trim()+"%");
			}
			
			if(StringUtils.isNotBlank(stat)){
				cond.setStat(ApplyStat.valueOf(stat));
			}
			if(StringUtils.isNotBlank(phone)){
				cond.setMobilePhone(phone.trim()+"%");
			}
			if(StringUtils.isNotBlank(regTime)){
				cond.setStartGenTime(this.parser(regTime, Date.class));
				cond.setEndtGenTime(this.parser(regTime, Date.class));
			}
			
			if(StringUtils.isNotBlank(provinceCode)&&!provinceCode.equals("1")){
				cond.setProvinceCode(provinceCode);
			}
			if(StringUtils.isNotBlank(region)){
				cond.setCityCode(region);
			}
			
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.asc("iinstApplyReq"));
			cond.setOrders(orderList);
			instApplyList = this.instApplyReqService.listInstApplyRequest(cond, page);
			dataSet.setGridModel(instApplyList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String detail(){
		instApplyRequest = instApplyList.get(index);
		return "detail";
	}
	
	public String check() throws Exception{
		instApplyReqList = instApplyReqService.queryAll();
		provMap = provinceService.getProvince();
		try{
			//instApplyRequest = instApplyList.get(index);
			if(instApplyRequest.getStat() == ApplyStat.SUCCESS){
				super.addActionMessage("该申请已经受理，不能重复受理！");
				return "success";
			}
			instApplyRequest.setStat(ApplyStat.SUCCESS);
			this.instApplyReqService.update(instApplyRequest);
			super.addActionMessage("已成功处理");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "success";
		}
		return "success";
	}
	
	public String checkAndInsert() throws Exception{
		instApplyReqList = instApplyReqService.queryAll();
		provMap = provinceService.getProvince();
		try{
			//instApplyRequest = instApplyList.get(index);
			if(instApplyRequest.getStat() == ApplyStat.SUCCESS){
				super.addActionMessage("该申请已经受理，不能重复受理！");
				return "success";
			}
			instApplyRequest.setStat(ApplyStat.SUCCESS);
			this.instApplyReqService.update(instApplyRequest);
			inst = new Inst();
			inst.setInstName(instApplyRequest.getInstName());
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "success";
		}
		return "checkAndInsert";
	}
	
	
	public String cancel() throws Exception{
		instApplyReqList = instApplyReqService.queryAll();
		provMap = provinceService.getProvince();
		try{
			//instApplyRequest = instApplyList.get(index);
			instApplyRequest.setStat(ApplyStat.REFUSE);
			if(radio.equals("4")){
				instApplyRequest.setMemo(reason);
			}else{
				instApplyRequest.setMemo(radio);
			}
			this.instApplyReqService.update(instApplyRequest);
			//super.addActionMessage("状态变更为退回");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "success";
		}
		return "success";
	}
	
	@JqDataSet(content = "{o:instName},{o:addr},{o:contactName},{o:mobilePhone},{i:(format.time)({o:genTime})},{o:stat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}
	
	
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<InstApplyRequest> getInstApplyList() {
		return instApplyList;
	}

	public void setInstApplyList(List<InstApplyRequest> instApplyList) {
		this.instApplyList = instApplyList;
	}

	public InstApplyRequest getInstApplyRequest() {
		return instApplyRequest;
	}

	public void setInstApplyRequest(InstApplyRequest instApplyRequest) {
		this.instApplyRequest = instApplyRequest;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public List<InstApplyRequest> getInstApplyReqList() {
		return instApplyReqList;
	}

	public void setInstApplyReqList(List<InstApplyRequest> instApplyReqList) {
		this.instApplyReqList = instApplyReqList;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Map<String, String> getProvMap() {
		return provMap;
	}

	public void setProvMap(Map<String, String> provMap) {
		this.provMap = provMap;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
