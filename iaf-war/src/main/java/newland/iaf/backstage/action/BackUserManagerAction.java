package newland.iaf.backstage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.base.util.DigestUtil;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.backstage.service.BackStageRoleService;
import newland.iaf.backstage.service.BackStageUserService;
import newland.iaf.base.model.condition.BackStageUserCondition;
import newland.iaf.base.model.dict.InstUserStatType;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;
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
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台管理 后台管理员管理
 * 
 * @author lindaqun
 * 
 */
@ParentPackage("struts")
@Namespace("/backstage/backuser")
@Action(value = "backUserManager")
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "userInfo", type = "dispatcher", location = "/backstage/backuser/userInfo.jsp"),
		@Result(name = "backstageUserAdd", type = "dispatcher", location = "/backstage/backuser/backstageUserAdd.jsp"),
		@Result(name = "backstageUserEdit", type = "dispatcher", location = "/backstage/backuser/backstageUserEdit.jsp"),
		@Result(name = "success", type = "dispatcher", location = "/backstage/backuser/backstageUserIndex.jsp") })
public class BackUserManagerAction extends BSBaseAction {

	private static final long serialVersionUID = -6851351692756452130L;

	@Resource(name = "backStageUserService")
	private BackStageUserService backStageUserService;
	
	@Resource(name="backStageRoleService")
	private BackStageRoleService backStageRoleService;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BackStageUser backStageUser;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BackStageUser sessionUser;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<BackStageUser> userList;

	private String password;
	private String passwordAgain;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loginName;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String userName;

	private DataSet dataSet;// 用户JQGrid数据
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<BackStageRole> bsRoleList;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String bsRole;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String oldRole;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String stat;

	private String loginUserName;
	
	private String roleName;
	
	@Begin
	public String execute() {
		bsRole="";
		stat="";
		bsRoleList=backStageRoleService.queryAllRoles();
		return "success";
	}

	public String userInfo() throws Exception {
		sessionUser = this.getUserSession().getUser();
		return "userInfo";
	}

	@InputConfig(resultName = "userInfo")
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "sessionUser.loginName", message = "登陆名称不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "sessionUser.userName", message = "真实名称不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "sessionUser.address", message = "地址不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "sessionUser.qqNum", message = "QQ号不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "sessionUser.email", message = "email不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "sessionUser.mobile", message = "手机号码不能为空！", shortCircuit = true) }, requiredFields = {
			@RequiredFieldValidator(fieldName = "sessionUser.sexType", message = "性别不能为空", shortCircuit = true),
			@RequiredFieldValidator(fieldName = "sessionUser.stat", message = "状态不能为空", shortCircuit = true) }, stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "sessionUser.loginName", minLength = "2", message = "登陆名称不能少于2个字符", shortCircuit = true),
			@StringLengthFieldValidator(fieldName = "sessionUser.userName", minLength = "2", message = "真实名称不能少于2个字符", shortCircuit = true) })
	public String updateUserInfo() throws Exception {
		try {
			if (password != null && !"".equals(password.trim()))
				sessionUser.setPassword(DigestUtil.getSHA(password));
			this.backStageUserService.updateBackStageUser(sessionUser);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "userInfo";
		}
		password = null;
		return "userInfo";
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
			BackStageUserCondition con = new BackStageUserCondition();
			// 设置查询条件
			if (StringUtils.isNotBlank(loginName)) {
				con.setLoginName(loginName.trim());
			}
			if (StringUtils.isNotBlank(userName)) {
				con.setUserName(userName.trim());
			}
			
			if(StringUtils.isNotBlank(stat)){
				con.setStatus(InstUserStatType.valueOf(stat));
			}
			
			if(StringUtils.isNotBlank(bsRole)){
				con.setiBsRole(Long.parseLong(bsRole));
			}
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.asc("iuser"));
			con.setOrders(orderList);
			userList = this.backStageUserService.listUser(con, page);
			dataSet.setGridModel(userList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String backstageUserAdd() throws Exception {
		backStageUser=new BackStageUser();
		bsRoleList=backStageRoleService.queryAllRoles();
		return "backstageUserAdd";
	}

	@InputConfig(resultName = "backstageUserAdd")
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "backStageUser.loginName", message = "登陆名称不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.userName", message = "真实名称不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "password", message = "新密码不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "passwordAgain", message = "确认新密码不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.address", message = "地址不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.qqNum", message = "QQ号不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.email", message = "email不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.mobile", message = "手机号码不能为空！", shortCircuit = true) }, requiredFields = {
			@RequiredFieldValidator(fieldName = "backStageUser.sexType", message = "性别不能为空", shortCircuit = true),
			@RequiredFieldValidator(fieldName = "backStageUser.stat", message = "状态不能为空", shortCircuit = true) }, stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "backStageUser.loginName", minLength = "2", message = "登陆名称不能少于2个字符", shortCircuit = true),
			@StringLengthFieldValidator(fieldName = "backStageUser.userName", minLength = "2", message = "真实名称不能少于2个字符", shortCircuit = true) })
	public String saveBackstageUser() throws Exception {
		try {
			backStageUser.setGenTime(new Date());
			backStageUser.setPassword(DigestUtil.getSHA(password));
			Set<BackStageRole> roleSet =new HashSet<BackStageRole>();
			BackStageRole bsr = backStageRoleService.queryById(Long.parseLong(bsRole));
			roleSet.add(bsr);
			backStageUser.setBsRoleSet(roleSet);
			this.backStageUserService.saveBackStageUser(backStageUser);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "success";
	}

	public String backstageUserEdit() {
		backStageUser = this.userList.get(index);
		bsRoleList=backStageRoleService.queryAllRoles();
		backStageUser.getBsRoles();
		bsRole="";
		if(backStageUser.getBsRoleSet()!=null){
			for (BackStageRole role : backStageUser.getBsRoleSet()) {
				bsRole=role.getiBsRole().toString();
				roleName=role.getRoleName();
			}
		}
		IafConsoleSession ics = (IafConsoleSession) SessionFilter.getIafSession();
		loginUserName=ics.getUser().getLoginName();
		return "backstageUserEdit";
	}

	@InputConfig(resultName = "backstageUserEdit")
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "backStageUser.loginName", message = "登陆名称不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.userName", message = "真实名称不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.address", message = "地址不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.qqNum", message = "QQ号不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.email", message = "email不能为空！", shortCircuit = true),
			@RequiredStringValidator(fieldName = "backStageUser.mobile", message = "手机号码不能为空！", shortCircuit = true) }, requiredFields = {
			@RequiredFieldValidator(fieldName = "backStageUser.sexType", message = "性别不能为空", shortCircuit = true),
			@RequiredFieldValidator(fieldName = "backStageUser.stat", message = "状态不能为空", shortCircuit = true) }, stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "backStageUser.loginName", minLength = "2", message = "登陆名称不能少于2个字符", shortCircuit = true),
			@StringLengthFieldValidator(fieldName = "backStageUser.userName", minLength = "2", message = "真实名称不能少于2个字符", shortCircuit = true) })
	public String updateBackstageUser() throws Exception {
		
		try {
			backStageUser.setUpdTime(new Date());
			if (password != null && !"".equals(password.trim())) {
				backStageUser.setPassword(DigestUtil.getSHA(password));
			}
			
			Set<BackStageRole> roleSet =new HashSet<BackStageRole>();
			BackStageRole bsr = backStageRoleService.queryById(Long.parseLong(oldRole));
			bsr.setiBsRole(Long.parseLong(bsRole));
			roleSet.add(bsr);
			backStageUser.setBsRoleSet(roleSet);
			this.backStageUserService.updateBackStageUser(backStageUser);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "success";
	}

	public String disableBackstageUser() throws Exception {
		try {
			backStageUser = this.userList.get(index);
			backStageUser.setStat(InstUserStatType.UNUSED);
			this.backStageUserService.updateBackStageUser(backStageUser);
			super.addActionMessage("禁用成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "success";
	}

	public String enableBackstageUser() throws Exception {
		try {
			backStageUser = this.userList.get(index);
			backStageUser.setStat(InstUserStatType.USED);
			this.backStageUserService.updateBackStageUser(backStageUser);
			super.addActionMessage("启用成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "success";
	}

	@JqDataSet(content = "{o:iuser},{o:loginName},{o:bsRoles},{o:userName},{i:(format.time)({o:genTime})},{o:mobile},{o:email},{o:stat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public BackStageUser getBackStageUser() {
		return backStageUser;
	}

	public void setBackStageUser(BackStageUser backStageUser) {
		this.backStageUser = backStageUser;
	}

	public List<BackStageUser> getUserList() {
		return userList;
	}

	public void setUserList(List<BackStageUser> userList) {
		this.userList = userList;
	}

	public BackStageUser getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(BackStageUser sessionUser) {
		this.sessionUser = sessionUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public List<BackStageRole> getBsRoleList() {
		return bsRoleList;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getOldRole() {
		return oldRole;
	}

	public void setOldRole(String oldRole) {
		this.oldRole = oldRole;
	}

	public String getBsRole() {
		return bsRole;
	}

	public void setBsRole(String bsRole) {
		this.bsRole = bsRole;
	}

	public void setBsRoleList(List<BackStageRole> bsRoleList) {
		this.bsRoleList = bsRoleList;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
