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

import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.RoleAuth;
import newland.iaf.inst.model.condition.InstRoleCondition;
import newland.iaf.inst.service.InstRoleManagerService;
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
 * @author Mr.Towne
 *
 */
@ParentPackage("struts")
@Namespace("/inst")
@Action("instRole")
@Results({
		@Result(name = "success", type = "dispatcher", location = "/inst/roleMgr.jsp"),
		@Result(name = "list", type = "JqgridJsonResult"),
		@Result(name = "roleAddError", type = "dispatcher", location = "/inst/roleAdd.jsp"),
		@Result(name = "loadRoleAuth", type = "dispatcher", location = "/inst/roleEdit.jsp"),
		@Result(name = "roleView", type = "dispatcher", location = "/inst/roleView.jsp"),
		@Result(name = "toIndex", type = "redirect", location = "/inst/instRole.action") })
public class InstRoleAction extends InstBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2436838893047417131L;

	@Resource(name = "com.newland.iaf.instRoleManagerService")
	private InstRoleManagerService irms;

	private DataSet dataSet;// 用户JQGrid数据

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String roleName;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String roleStat;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private RoleAuth roleAuth;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<RoleAuth> roleAuthList;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String[] auth;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String roleNameError;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer index;

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<String> authName;
	
	private InstRole myrole;

	@Begin
	
	public String execute() {
		roleName="";
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		myrole=instSession.getRoles().get(0);
		return "success";
	}

	public String modify() {
		return "modify";
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
			InstRoleCondition instRoleCondition = new InstRoleCondition();
			// 设置查询条件
			if (StringUtils.isNotBlank(roleName)) {
				instRoleCondition.setRoleName(roleName.trim());
			}
			if (StringUtils.isNotBlank(roleStat)) {
				instRoleCondition.setRoleStat(Integer.parseInt(roleStat));
			}
			InstSession instSession = (InstSession) SessionFilter
					.getIafSession();
			instRoleCondition.setiInst(instSession.getInst().getIinst());
			roleAuthList = irms.queryInstRoleByCon(instRoleCondition, page);
			dataSet.setGridModel(roleAuthList);
			dataSet.setRecords(roleAuthList.size());
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "list";
	}

	/**
	 * 加载修改对象
	 * 
	 * @return
	 */
	public String loadInstRole() {
		authName = new ArrayList<String>();
		roleAuth = roleAuthList.get(index);
		for (InstAuth instAuth : roleAuth.getInstAuthSet()) {
			authName.add(instAuth.getAuthCode());
		}
		return "loadRoleAuth";
	}

	/**
	 * 详情
	 * 
	 * @return
	 */
	public String viewInstRole() {
		authName = new ArrayList<String>();
		roleAuth = roleAuthList.get(index);
		for (InstAuth instAuth : roleAuth.getInstAuthSet()) {
			authName.add(instAuth.getAuthCode());
		}
		return "roleView";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	@InputConfig(resultName = "loadRoleAuth")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "roleAuth.roleName", message="角色名称不能为空！",shortCircuit=true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "roleAuth.roleName", minLength = "2", message="角色名称不能少于2个字符", shortCircuit = true)
			}
	)
	public String instRoleUpdate(){
		roleAuth = roleAuthList.get(index);
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		if (irms.CheckUpdateRoleName(roleAuth.getRoleName(), instSession
				.getInst().getIinst())) {
			roleNameError = "角色名已存在!";
			return "loadRoleAuth";
		}
		int isCheck =0;
		Set<InstAuth> setRoleAuth = new HashSet<InstAuth>();
		List<String> authList = new ArrayList<String>();
		if(auth!=null){
			for (int i = 0; i < auth.length; i++) {
				InstAuth ia = new InstAuth();
				ia=irms.queryByAuthName(auth[i]);
				//if(auth[i].equals("XTGL_JSQX_MENU")||auth[i].equals("XTGL_ZHGL_MENU")){
					//authList.add(auth[i]);
				//}
				if(ia==null){
					continue;
				}
				if(auth[i].equals("CKBB")){
					isCheck=1;
				}
				setRoleAuth.add(ia);
			}
		}
		//if(authList.size()==2){
		//	roleAuth.setFlag(1);//管理员
		//}else{
		//	roleAuth.setFlag(0);//普通
		//}
		//roleAuth.setFlag(0);
		if(isCheck==1){
			roleAuth.setIsCheck(1);
		}else{
			roleAuth.setIsCheck(0);
		}
		roleAuth.setInstAuthSet(setRoleAuth);
		irms.updateRoleAuth(roleAuth, instSession, getIpaddr());
		return "toIndex";
	}

	/**
	 * 暂停
	 * 
	 * @return
	 */
	public String cancelRole() {
		roleAuth.setRoleStat(0);
		irms.cancelInstRole(roleAuth);
		return "toIndex";
	}

	/**
	 * 恢复角色
	 * 
	 * @return
	 */
	public String recoverRole() {
		roleAuth.setRoleStat(1);
		irms.cancelInstRole(roleAuth);
		return "toIndex";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String deleteInstRole() {
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		roleAuth = roleAuthList.get(index);
		irms.deleteRoleAuth(roleAuth, instSession, getIpaddr());
		return "toIndex";
	}

	/**
	 * 新增角色
	 * 
	 * @return
	 */
	@InputConfig(resultName = "roleAddError")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "roleName", message="角色名称不能为空！",shortCircuit=true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "roleName", minLength = "2", message="角色名称不能少于2个字符", shortCircuit = true)
			}
	)
	public String instRoleAdd(){
		InstSession instSession = (InstSession) SessionFilter.getIafSession();
		if (irms.checkRoleName(roleName, instSession.getInst().getIinst())) {
			roleNameError = "角色名已存在!";
			return "roleAddError";
		}
		roleAuth = new RoleAuth();
		roleAuth.setRoleName(roleName);
		roleAuth.setGenTime(new Date());
		roleAuth.setMemo(roleName);
		roleAuth.setiInst(instSession.getInst().getIinst());
		roleAuth.setRoleStat(1);
		int isCheck = 0;
		Set<InstAuth> setRoleAuth = new HashSet<InstAuth>();
		if (auth != null) {
			for (int i = 0; i < auth.length; i++) {
				InstAuth ia = new InstAuth();
				ia = irms.queryByAuthName(auth[i]);
				if(auth[i].equals("CKBB")){
					isCheck=1;
				}
				setRoleAuth.add(ia);
			}
		}
		roleAuth.setFlag(0);
		roleAuth.setInstAuthSet(setRoleAuth);
		if(isCheck==1){
			roleAuth.setIsCheck(1);
		}else{
			roleAuth.setIsCheck(0);
		}
		irms.saveRoleAuth(roleAuth, instSession, getIpaddr());
		return "toIndex";
	}

	public InstRoleManagerService getIrms() {
		return irms;
	}

	public void setIrms(InstRoleManagerService irms) {
		this.irms = irms;
	}

	@JqDataSet(content = "{o:roleName},{o:roleStat},{o:flag}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleStat() {
		return roleStat;
	}

	public void setRoleStat(String roleStat) {
		this.roleStat = roleStat;
	}

	public String[] getAuth() {
		return auth;
	}

	public void setAuth(String[] auth) {
		this.auth = auth;
	}

	public List<RoleAuth> getInstRoleList() {
		return roleAuthList;
	}

	public void setInstRoleList(List<RoleAuth> RoleAuthList) {
		this.roleAuthList = RoleAuthList;
	}

	public RoleAuth getRoleAuth() {
		return roleAuth;
	}

	public void setRoleAuth(RoleAuth roleAuth) {
		this.roleAuth = roleAuth;
	}

	public String getRoleNameError() {
		return roleNameError;
	}

	public void setRoleNameError(String roleNameError) {
		this.roleNameError = roleNameError;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<RoleAuth> getRoleAuthList() {
		return roleAuthList;
	}

	public void setRoleAuthList(List<RoleAuth> roleAuthList) {
		this.roleAuthList = roleAuthList;
	}

	public List<String> getAuthName() {
		return authName;
	}

	public void setAuthName(List<String> authName) {
		this.authName = authName;
	}

	public InstRole getMyrole() {
		return myrole;
	}

	public void setMyrole(InstRole myrole) {
		this.myrole = myrole;
	}
}
