package newland.iaf.backstage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.base.util.DigestUtil;
import newland.iaf.base.model.dict.ForbidenType;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.MerchUserType;
import newland.iaf.merch.model.condition.MerchUserCondition;
import newland.iaf.merch.service.MerchService;
import newland.iaf.merch.service.MerchUserService;
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
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台管理 商户管理Action
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/merchmanager")
@Action(value = "merchUser")
@Results({
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "merchUserAdd",  type = "dispatcher",location = "/backstage/merchmanager/merchUserAdd.jsp"),
	@Result(name = "merchUserEdit",  type = "dispatcher",location = "/backstage/merchmanager/merchUserEdit.jsp"),
	@Result(name = "success",  type = "dispatcher",location = "/backstage/merchmanager/merchUserIndex.jsp")
    })
public class MerchUserAction extends BSBaseAction {
	
	private static final long serialVersionUID = 1L;

	private DataSet dataSet;//用户JQGrid数据
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<MerchUser> merchUserList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Merch merch;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long imerch;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private MerchUser merchUser;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loginName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String userName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String newPassword;
	
	private String stat;
	
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	@Resource(name = "com.newland.iaf.merchUserService")
	private MerchUserService merchUserService;
	
	@Begin
	
	public String execute(){
		loginName="";
		stat="";
		userName="";
		return "success";
	}
	
	public String userDetail() throws Exception{
		loginName="";
		stat="";
		userName="";
		merch = this.merchService.findMerchById(imerch);
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
			MerchUserCondition con = new MerchUserCondition();
			con.setImerch(imerch);
			//设置查询条件
			if(StringUtils.isNotBlank(loginName)){
				con.setLoginName(loginName.trim());
			}
			
			if(StringUtils.isNotBlank(stat)){
				con.setMerchStat(ForbidenType.valueOf(stat));
			}
			if(StringUtils.isNotBlank(userName)){
				con.setUserName(userName.trim());
			}
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.asc("imerchUser"));
			con.setOrders(orderList);
			merchUserList = this.merchUserService.listMerchUser(con, page);
			dataSet.setGridModel(merchUserList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String merchUserAdd() throws Exception{
		merchUser=null;
		return "merchUserAdd";
	}
	
	public String saveMerchUser() throws Exception{
		try{
			merchUser.setGenTime(new Date());
			merchUser.setMerchStat(ForbidenType.USED);
			merchUser.setUpdTime(new Date());
			merchUser.setImerch(merch.getImerch());
			String pwd = DigestUtil.getSHA(merchUser.getPasswd());
			merchUser.setPasswd(pwd);
			merchUser.setUserType(MerchUserType.common);
			merchUser.setMerchNo(merch.getMerchNo());
			this.merchUserService.addMerchUserIAF(merchUser, this.getUserSession(), this.getIpaddr());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
	}
	public String merchUserEdit() throws Exception{
		if(index != null){
			merchUser = merchUserList.get(index);
		}
		return "merchUserEdit";
	}
	
	@InputConfig(resultName = "merchUserEdit")
	@Validations(
			requiredStrings = {
			@RequiredStringValidator(fieldName = "merchUser.loginName",  message = "登陆名称不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "merchUser.userName", message = "用户姓名不能为空！", shortCircuit = true)
			}
			)
	public String updateMerchUser() throws Exception{
		try{
			merchUser.setUpdTime(new Date());
			if(StringUtils.isNotBlank(newPassword)){
				String pwd = DigestUtil.getSHA(newPassword);
				merchUser.setPasswd(pwd);
			}
			this.merchUserService.updateMerchUserIAF(merchUser,this.getUserSession(), this.getIpaddr());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
		
	}
	
	public String disablemerchUser() throws Exception{
		try{
			//merchUser = merchUserList.get(index);
			if(merchUser.getMerchStat().equals(ForbidenType.UNUSED)){
				super.addActionMessage("商户用户已经是禁用状态,不能再禁用!");
				return "success";
			}
			merchUser.setMerchStat(ForbidenType.UNUSED);
			this.merchUserService.updateMerchUserIAF(merchUser,this.getUserSession(), this.getIpaddr());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
	}
	
	public String enablemerchUser() throws Exception{
		try{
			//merchUser = merchUserList.get(index);
			if(merchUser.getMerchStat().equals(ForbidenType.USED)){
				super.addActionMessage("商户用户已经是启用状态,不能再启用!");
				return "success";
			}
			merchUser.setMerchStat(ForbidenType.USED);
			this.merchUserService.updateMerchUserIAF(merchUser,this.getUserSession(), this.getIpaddr());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
			return "success";
		}
	}
	@JqDataSet(content = "{o:loginName},{o:userName},{o:merchStat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}
	
	
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public List<MerchUser> getMerchUserList() {
		return merchUserList;
	}

	public void setMerchUserList(List<MerchUser> merchUserList) {
		this.merchUserList = merchUserList;
	}

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public Long getImerch() {
		return imerch;
	}

	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public MerchUser getMerchUser() {
		return merchUser;
	}

	public void setMerchUser(MerchUser merchUser) {
		this.merchUser = merchUser;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
