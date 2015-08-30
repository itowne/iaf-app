package newland.iaf.backstage.action;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.base.util.DigestUtil;
import newland.iaf.base.model.dict.InstUserStatType;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.condition.InstUserCondition;
import newland.iaf.inst.service.InstService;
import newland.iaf.inst.service.InstUserService;
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
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台管理的机构用户管理ACTION 
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/instmanager")
@Action("backstageInstUser")
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "success", type = "dispatcher", location = "/backstage/instmanager/backAccountMgr.jsp"),
		@Result(name = "instUserAdd", type = "dispatcher", location = "/backstage/instmanager/backAccountAdd.jsp"),
		@Result(name = "loadInsrUser", type = "dispatcher", location = "/backstage/instmanager/backAccountEdit.jsp"),
		@Result(name = "instManagerIndexUser", type = "redirect", location = "/backstage/instmanager/instManager!toProcess"),
		@Result(name="toIndex",type="dispatcher",location="/backstage/instmanager/backAccountMgr.jsp")})
		
public class BackstageInstUserAction extends BSBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -302221961084794475L;

	@Resource(name = "com.newland.iaf.instUserService")
	private InstUserService instUserService;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<InstRole> instRoleList;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private InstUser instUser;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String loginName;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String userName;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String role;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String oldRole;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String usrStat;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String registerError;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String passwd;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String confirmpassword;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<InstUser> instUserList;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Long iinst;
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Inst inst;
	
	private DataSet dataSet;// 用户JQGrid数据
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<Inst> instList;
	
	private String isUsed;
	
	@Resource(name = "com.newland.iaf.instService")
	private InstService instService;
	
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
			InstUserCondition instUserCondition = new InstUserCondition();
			instUserCondition.setIinst(iinst);
			// 设置查询条件
			if (StringUtils.isNotBlank(loginName)) {
				instUserCondition.setLoginName(loginName.trim());
			}
			if (StringUtils.isNotBlank(userName)) {
				instUserCondition.setUserName(userName.trim());
			}
			if (StringUtils.isNotBlank(usrStat)) {
				instUserCondition.setUsrStat(InstUserStatType.valueOf(usrStat));
			}
			if (StringUtils.isNotBlank(role)) {
				instUserCondition.setIinstRole(Long.parseLong(role));
			}
			instUserList = instUserService.queryInstUserByCon(
					instUserCondition, page);
			dataSet.setGridModel(instUserList);
			dataSet.setRecords(instUserList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "list";
	}

	@Begin
	@End
	public String execute() throws Exception{
		iinst = inst.getIinst();
		role = null;
		instRoleList = instUserService.queryByInstUser(iinst);
		return "success";
	}
	
	public String backToUserList(){
		
		return "success";
	}
	
	public String instManagerIndexUser(){
		//instList = instService.queryInst();
		return "instManagerIndexUser";
	}

	public String instUserToAdd() {
		instRoleList = instUserService.queryByInstUser(iinst);
		instUser=new InstUser();
		registerError="";
		return "instUserAdd";
	}

	@InputConfig(resultName = "instUserAdd")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "instUser.loginName", message="账号名称不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "instUser.userName", message="使用者姓名不能为空！",shortCircuit=true),
					//@RequiredStringValidator(fieldName = "instUser.position", message="使用者职务不能为空！",shortCircuit=true),
					//@RequiredStringValidator(fieldName = "instUser.Mail", message="电子邮箱不能为空！",shortCircuit=true),
					//@RequiredStringValidator(fieldName = "instUser.QQNum", message="QQ号码不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "instUser.phone", message="手机号码不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "instUser.passwd", message="密码设置不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "confirmpassword", message="再次输入密码不能为空！",shortCircuit=true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "instUser.loginName", minLength = "2", message="账号名称不能少于2个字符", shortCircuit = true),
			        @StringLengthFieldValidator(fieldName = "instUser.userName", minLength = "2", message="使用者姓名不能少于2个字符", shortCircuit = true)
			}
	)
	public String instUserAdd() {
		if(instUserService.checkRegInstUser(instUser.getLoginName(),iinst)){
			instRoleList = instUserService.queryByInstUser(iinst);
			registerError="此用户名已使用,请更换一个!";
			return "instUserAdd";
		}
		instUser.setGenTime(new Date());
		instUser.setIinst(iinst);
		instUser.setUpdTime(new Date());
		instUser.setLastLoginTime(new Date());
		if(StringUtils.isNotBlank(isUsed)){
			instUser.setUsrStat(InstUserStatType.valueOf(isUsed));
		}else{
			instUser.setUsrStat(InstUserStatType.USED);
		}
		instUser.setPasswd(DigestUtil.getSHA(instUser.getPasswd()));
		Set<InstRole> set = new HashSet<InstRole>();
		InstRole ir = instUserService.queryByiInstAndIrole(iinst, Long.parseLong(role));
		set.add(ir);
		if(ir.getFlag()==1){
		instUser.setFlag(1);
		}else{
		instUser.setFlag(0);
		}
		instUser.setInstRoleSet(set);
		instUserService.addInstUserIAF(instUser,iinst,this.getUserSession(),getIpaddr());
		role = null;
		return "toIndex";
	}

	/**
	 * 加载帐号修改信息
	 * 
	 * @return
	 */
	public String loadInsrUser() {
		instRoleList = instUserService.queryByInstUser(iinst);
		instUser = instUserList.get(index);
		Set<InstRole> set= instUser.getInstRoleSet();
		InstRole ir= new InstRole();
		for (InstRole instRole : set) {
			if(instRole.getRoleName().equals(instUser.getInstRoles())){
				ir=instRole;
			}
		}
		if(ir.getiInstRole() != null)role=ir.getiInstRole().toString();
		return "loadInsrUser";
	}

	/**
	 * 账户修改
	 * 
	 * @return
	 */
	@InputConfig(resultName = "loadInsrUser")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "instUser.loginName", message="账号名称不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "instUser.userName", message="使用者姓名不能为空！",shortCircuit=true),
//					@RequiredStringValidator(fieldName = "instUser.position", message="使用者职务不能为空！",shortCircuit=true),
//					@RequiredStringValidator(fieldName = "instUser.Mail", message="电子邮箱不能为空！",shortCircuit=true),
//					@RequiredStringValidator(fieldName = "instUser.QQNum", message="QQ号码不能为空！",shortCircuit=true),
					@RequiredStringValidator(fieldName = "instUser.phone", message="手机号码不能为空！",shortCircuit=true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "instUser.loginName", minLength = "2", message="账号名称不能少于2个字符", shortCircuit = true),
			        @StringLengthFieldValidator(fieldName = "instUser.userName", minLength = "2", message="使用者姓名不能少于2个字符", shortCircuit = true)
			}
	)
	public String instUserUpdate() {
		instRoleList = instUserService.queryByInstUser(iinst);
		if (StringUtils.isNotEmpty(passwd)) {
			instUser.setPasswd(DigestUtil.getSHA(passwd));
		}
		instUser.setUpdTime(new Date());
		Set<InstRole> set = new HashSet<InstRole>();
		InstRole ir = new InstRole();
		if(StringUtils.isNotEmpty(oldRole)){
			ir = instUserService.queryByiInstAndIrole(iinst, Long.parseLong(oldRole));
		}
		else{
			ir.setFlag(1);
		}
		ir.setiInstRole(Long.parseLong(role));
		set.add(ir);
		if(ir.getFlag()==1){
		instUser.setFlag(1);
		}else{
		instUser.setFlag(0);
		}
		instUser.setInstRoleSet(set);
		instUserService.updateInstUserIAF(instUser,iinst,this.getUserSession(),getIpaddr());
		super.addActionError("操作员修改成功");
		return "toIndex";
	}

	/**
	 * 停用账户
	 * 
	 * @return
	 */
	public String disableAcct() {
		//instUser=instUserList.get(index);
		instUser.setUsrStat(InstUserStatType.UNUSED);
		instUserService.updateInstUserIAF(instUser,iinst,this.getUserSession(),getIpaddr());
		return "toIndex";
	}

	/**
	 * 删除账户
	 */
	public String deleteInstUser() {
		//instUser=instUserList.get(index);
		instUserService.deleteInstUserIAF(instUser,iinst,this.getUserSession(),getIpaddr());
		return "toIndex";
	}

	/**
	 * 恢复帐号
	 * 
	 * @return
	 */
	public String reCoverInstUser() {
		//instUser=instUserList.get(index);
		instUser.setUsrStat(InstUserStatType.USED);
		instUserService.updateInstUserIAF(instUser,iinst,this.getUserSession(),getIpaddr());
		return "toIndex";
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

	@JqDataSet(content = "{o:loginName},{o:instRoles},{o:userName},{o:usrStat.desc}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public InstUserService getInstUserService() {
		return instUserService;
	}

	public void setInstUserService(InstUserService instUserService) {
		this.instUserService = instUserService;
	}

	public String getUsrStat() {
		return usrStat;
	}

	public void setUsrStat(String userStat) {
		this.usrStat = userStat;
	}

	public List<InstUser> getInstUserList() {
		return instUserList;
	}

	public void setInstUserList(List<InstUser> instUserList) {
		this.instUserList = instUserList;
	}

	public List<InstRole> getInstRoleList() {
		return instRoleList;
	}

	public void setInstRoleList(List<InstRole> instRoleList) {
		this.instRoleList = instRoleList;
	}

	public String getOldRole() {
		return oldRole;
	}

	public void setOldRole(String oldRole) {
		this.oldRole = oldRole;
	}

	public InstUser getInstUser() {
		return instUser;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setInstUser(InstUser instUser) {
		this.instUser = instUser;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getRegisterError() {
		return registerError;
	}

	public void setRegisterError(String registerError) {
		this.registerError = registerError;
	}

	public Long getIinst() {
		return iinst;
	}

	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}


	public List<Inst> getInstList() {
		return instList;
	}

	public void setInstList(List<Inst> instList) {
		this.instList = instList;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

}