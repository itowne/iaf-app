package newland.iaf.backstage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.condition.ApplyRequestCondition;
import newland.iaf.base.model.dict.ApplyType;
import newland.iaf.base.service.ApplyReqService;
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
 * 
 * 冻结商户查询ACTION
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/merchmanager")
@Action(value = "frozenMerch")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "detail",  type = "dispatcher",location = "/backstage/merchmanager/frozenDetail.jsp"),
	@Result(name = "success",  type = "dispatcher",location = "/backstage/merchmanager/frozenMerchIndex.jsp")
    })
public class FrozenMerchAction extends BSBaseAction {
	
	private static final long serialVersionUID = -1911237719839093068L;

	private DataSet dataSet;//用户JQGrid数据
	
	@Resource(name = "applyReqService")
	private ApplyReqService applyReqService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<ApplyRequest> frozenList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String merchName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Date genTime;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private ApplyRequest applyRequest;
	
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
			ApplyRequestCondition applyCondition = new ApplyRequestCondition();
			applyCondition.setApplyType(ApplyType.FREEZE);
			//设置查询条件
			if(StringUtils.isNotBlank(merchName)){
				applyCondition.setMerchName(merchName.trim());
			}
			if(genTime != null){
				applyCondition.setBeginDate(genTime);
				applyCondition.setEndDate(genTime);
			}
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.asc("iapplyReq"));
			applyCondition.setOrders(orderList);
			frozenList = this.applyReqService.queryByPage(applyCondition, page);
			dataSet.setGridModel(frozenList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String detail() throws Exception{
		applyRequest = frozenList.get(index);
		//根据订单查找对应的
		return "detail";
	}
	
	
	@JqDataSet(content = "{o:imerch},{o:merchName},{i:(format.time)({o:genTime})},{o:reason},{o:stat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}
	
	
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<ApplyRequest> getFrozenList() {
		return frozenList;
	}

	public void setFrozenList(List<ApplyRequest> frozenList) {
		this.frozenList = frozenList;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public ApplyRequest getApplyRequest() {
		return applyRequest;
	}

	public void setApplyRequest(ApplyRequest applyRequest) {
		this.applyRequest = applyRequest;
	}
}
