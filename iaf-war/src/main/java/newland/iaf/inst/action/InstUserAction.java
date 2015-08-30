/**
 * 
 */
package newland.iaf.inst.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.base.util.DigestUtil;
import newland.base.validator.ValidatorType;
import newland.iaf.base.model.dict.InstUserStatType;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.condition.InstUserCondition;
import newland.iaf.inst.service.InstUserService;
import newland.jqgrid.annotations.JqDataSet;
import newland.jqgrid.model.DataSet;

import org.apache.commons.collections.CollectionUtils;
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
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.CustomValidator;
/**
 * @author Mr.Towne
 * 
 */
@ParentPackage("struts")
@Namespace("/inst")
@Action("instUser")
@Results({
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "success", type = "dispatcher", location = "/inst/accountMgr.jsp"),
		@Result(name = "instUserAdd", type = "dispatcher", location = "/inst/accountAdd.jsp"),
		@Result(name = "loadInsrUser", type = "dispatcher", location = "/inst/accountEdit.jsp"),
		@Result(name="toIndex",type="redirect",location="/inst/instUser.action")})
		
public class InstUserAction extends InstBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -302221961084794474L;

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
	private List<InstUser> instUserList;
	
	private String loginUserName;
	
	private String roleName;
	
	private InstRole myrole;

	private DataSet dataSet;// 用户JQGrid数据

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
			instUserCondition.setIinst(this.getUserSession().getInst().getIinst());
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
	
	public String execute() {
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		instRoleList = instUserService.queryByInstUser(instSession.getInst()
				.getIinst());
		myrole=instSession.getRoles().get(0);
		return "success";
	}

	public String instUserToAdd() {
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		List<InstRole> list = new ArrayList<InstRole>();
		instRoleList = instUserService.queryByInstUser(instSession.getInst()
				.getIinst());
		InstRole myir = instSession.getRoles().get(0);
		if(!CollectionUtils.isEmpty(instRoleList)){
			for (InstRole instRole : instRoleList) {
				if(instRole.getFlag()==1){
					//if(!instRole.getRoleName().equals(myir.getRoleName())){
						//list.add(instRole);
					//}
					list.add(instRole);
				}
			}
		}
		instRoleList.removeAll(list);
		instUser=new InstUser();
		registerError="";
		return "instUserAdd";
	}

	
	@InputConfig(resultName = "instUserAdd")
	@Validations(
	    requiredStrings = {
	        @RequiredStringValidator(fieldName = "instUser.loginName",   shortCircuit=true, message="账号名称不能为空！"),
	        @RequiredStringValidator(fieldName = "instUser.userName", shortCircuit=true, message="使用者姓名不能为空！"),
	        @RequiredStringValidator(fieldName = "role",   shortCircuit=true, message="所属角色不能为空！"),
	        @RequiredStringValidator(fieldName = "instUser.passwd",   shortCircuit=true, message="密码不能为空！")
	    }
	)
	public String instUserAdd() {
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		if(instUserService.checkRegInstUser(instUser.getLoginName(), instSession.getInst().getIinst())){
			instRoleList = instUserService.queryByInstUser(instSession.getInst()
					.getIinst());
			registerError="此用户名已使用,请更换一个!";
			return "instUserAdd";
		}
		instUser.setGenTime(new Date());
		instUser.setIinst(instSession.getInst().getIinst());
		instUser.setUpdTime(new Date());
		instUser.setLastLoginTime(new Date());
		instUser.setUsrStat(InstUserStatType.USED);
		instUser.setPasswd(DigestUtil.getSHA(instUser.getPasswd()));
		Set<InstRole> set = new HashSet<InstRole>();
		InstRole ir = instUserService.queryByiInstAndIrole(instSession.getInst().getIinst(), Long.parseLong(role));
		set.add(ir);

		instUser.setInstRoleSet(set);
		instUserService.addInstUser(instUser,instSession,getIpaddr());
		return "toIndex";
	}

	/**
	 * 加载帐号修改信息
	 * 
	 * @return
	 */
	public String loadInsrUser() {
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		instRoleList = instUserService.queryByInstUser(instSession.getInst()
				.getIinst());
		instUser = instUserList.get(index);
		Set<InstRole> set= instUser.getInstRoleSet();
		InstRole ir= new InstRole();
		for (InstRole instRole : set) {
			if(instRole.getRoleName().equals(instUser.getInstRoles())){
				ir=instRole;
			}
		}
		InstRole myir = instSession.getRoles().get(0);
		List<InstRole> list = new ArrayList<InstRole>();
		if(!CollectionUtils.isEmpty(instRoleList)){
			for (InstRole instRole : instRoleList) {
				if(instRole.getFlag()==1){
					if(!instRole.getRoleName().equals(myir.getRoleName())){
						list.add(instRole);
					}else{
						myir=instRole;
					}
					//list.add(instRole);
				}
			}
		}
		if(!instUser.getInstRoles().equals(myir.getRoleName())){
			list.add(myir);
		}
		instRoleList.removeAll(list);
		
		if(ir.getiInstRole() != null)role=ir.getiInstRole().toString();
		roleName = ir.getRoleName();
		loginUserName=instSession.getInstUsr().getLoginName();
		return "loadInsrUser";
	}

	/**
	 * 账户修改
	 * 
	 * @return
	 */
	@InputConfig(resultName = "toIndex")
	@Validations(
	    requiredStrings = {
	        @RequiredStringValidator(fieldName = "instUser.loginName",   shortCircuit=true, message="账号名称不能为空！"),
	        @RequiredStringValidator(fieldName = "instUser.userName", shortCircuit=true, message="使用者姓名不能为空！"),
	        @RequiredStringValidator(fieldName = "role",   shortCircuit=true, message="所属角色不能为空！")
	    }
	)
	public String instUserUpdate() {
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		instRoleList = instUserService.queryByInstUser(instSession.getInst()
				.getIinst());
		if (StringUtils.isNotEmpty(passwd)) {
			instUser.setPasswd(DigestUtil.getSHA(passwd));
		}
		instUser.setUpdTime(new Date());
		Set<InstRole> set = new HashSet<InstRole>();
		InstRole ir = instUserService.queryByiInstAndIrole(instSession.getInst().getIinst(), Long.parseLong(oldRole));
		ir.setiInstRole(Long.parseLong(role));
		set.add(ir);
		instUser.setInstRoleSet(set);
		instUserService.updateInstUser(instUser,instSession,getIpaddr());
		return "toIndex";
	}

	/**
	 * 停用账户
	 * 
	 * @return
	 */
	public String disableAcct() {
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		//instUser = (InstUser) instUserService.queryByInstUserId(Long.parseLong(index));
		//instUser=instUserList.get(index);
		instUser.setUsrStat(InstUserStatType.UNUSED);
		instUserService.updateInstUser(instUser,instSession,getIpaddr());
		return "toIndex";
	}

	/**
	 * 删除账户
	 */
	public String deleteInstUser() {
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		//instUser = (InstUser) instUserService.queryByInstUserId(Long.parseLong(index));
		//instUser=instUserList.get(index);
		instUserService.deleteInstUser(instUser,instSession,getIpaddr());
		return "toIndex";
	}

	/**
	 * 恢复帐号
	 * 
	 * @return
	 */
	public String reCoverInstUser() {
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		//instUser = (InstUser) instUserService.queryByInstUserId(Long.parseLong(index));
		//instUser=instUserList.get(index);
		instUser.setUsrStat(InstUserStatType.USED);
		instUserService.updateInstUser(instUser,instSession,getIpaddr());
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

	@JqDataSet(content = "{o:loginName},{o:instRoles},{o:userName},{o:usrStat.desc},{o:flag}")
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


	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
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


	public String getRegisterError() {
		return registerError;
	}

	public void setRegisterError(String registerError) {
		this.registerError = registerError;
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

	public InstRole getMyrole() {
		return myrole;
	}

	public void setMyrole(InstRole myrole) {
		this.myrole = myrole;
	}

}
