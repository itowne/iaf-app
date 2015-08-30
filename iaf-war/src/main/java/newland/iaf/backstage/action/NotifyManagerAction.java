package newland.iaf.backstage.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.dict.GeneralStatus;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.inst.model.InstNotice;
import newland.iaf.inst.model.condition.InstNoticeCondition;
import newland.iaf.inst.service.InstNoticeService;
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
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 
 * @author Administrator
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/notifymanager")
@Action(value = "notifyManager")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "notifyAdd",  type = "dispatcher",location = "/backstage/notifymanager/notifyAdd.jsp"),
	@Result(name = "notifyEdit",  type = "dispatcher",location = "/backstage/notifymanager/notifyEdit.jsp"),
	@Result(name="modifyEdit",type="dispatcher",location="/backstage/notifymanager/notifyView.jsp"),
	@Result(name = "success",  type = "dispatcher",location = "/backstage/notifymanager/notifyManager.jsp")
    })
public class NotifyManagerAction extends BSBaseAction {
	
	private static final long serialVersionUID = -8648836715521605087L;

	@Resource(name = "instNoticeService")
	private InstNoticeService instNoticeService;
	
	private DataSet dataSet;//用户JQGrid数据
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<InstNotice> notifyList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private InstNotice instNotice;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	
	private String title;
	
	private String updtime;
	
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
			InstNoticeCondition con = new InstNoticeCondition();
			//设置查询条件
			if(StringUtils.isNotBlank(title)){
				con.setTitle(title.trim());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(updtime)){
				con.setUpdTime(sdf.parse(updtime));
			}
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.desc("iinstNotice"));
			con.setOrders(orderList);
			notifyList = this.instNoticeService.queryInstNoticeByCon(con, page);
			dataSet.setGridModel(notifyList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String notifyAdd(){
		instNotice=null;
		return "notifyAdd";
	}
	
	@InputConfig(resultName = "notifyAdd")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "instNotice.title", message="公告标题不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "instNotice.content", message="公告内容不能为空！",shortCircuit=true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "instNotice.title", minLength = "2", message="公告标题不能少于2个字符", shortCircuit = true)
			}
	)
	public String saveNotify(){
		try{
			instNotice.setGenTime(new Date());
			instNotice.setStatus(GeneralStatus.VALID);
			instNotice.setUpdTime(new Date());
			this.instNoticeService.saveInstNotify(instNotice,OperType.NOTICE_ADD,this.getUserSession(),this.getIpaddr());
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
		return "success";
	}
	
	public String notifyEdit(){
		instNotice = this.notifyList.get(index);
		return "modifyEdit";
	}
	
	public String modifyNotice(){
		return "notifyEdit";
	}
	
	@InputConfig(resultName = "notifyEdit")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "instNotice.title", message="公告标题不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "instNotice.content", message="公告内容不能为空！",shortCircuit=true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "instNotice.title", minLength = "2", message="公告标题不能少于2个字符", shortCircuit = true)
			}
	)
	public String updateNotify(){
		try{
			instNotice.setUpdTime(new Date());
			this.instNoticeService.updateInstNotice(instNotice,OperType.NOTICE_UPDATE,this.getUserSession(),this.getIpaddr());
			super.addActionMessage("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
		return "success";
	}
	
	public String deleteNotify(){
		try{
			//instNotice = this.notifyList.get(index);
			this.instNoticeService.deleteInstNotice(instNotice,OperType.NOTICE_DEL,this.getUserSession(),this.getIpaddr());
			super.addActionMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
		return "success";
	}
	
	@JqDataSet(content = "{o:title},{o:updTime}")
	public DataSet getDataSet() {
		return dataSet;
	}
	
	
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<InstNotice> getNotifyList() {
		return notifyList;
	}

	public void setNotifyList(List<InstNotice> notifyList) {
		this.notifyList = notifyList;
	}

	public InstNotice getInstNotice() {
		return instNotice;
	}

	public void setInstNotice(InstNotice instNotice) {
		this.instNotice = instNotice;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpdtime() {
		return updtime;
	}

	public void setUpdtime(String updtime) {
		this.updtime = updtime;
	}

}
