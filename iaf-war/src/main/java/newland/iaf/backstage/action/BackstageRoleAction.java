package newland.iaf.backstage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.iaf.backstage.model.BackStageAuth;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BsRoleAuth;
import newland.iaf.backstage.service.BackStageRoleService;
import newland.iaf.base.model.condition.BackStageRoleCondition;
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
import com.googlecode.scopeplugin.annotations.End;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@ParentPackage("struts")
@Namespace("/backstage/backstagerole")
@Action(value = "backstageRole")
@Results({
	@Result(name="success",type="dispatcher",location="/backstage/backstagerole/backstageRoleIndex.jsp"),
	@Result(name="list",type="JqgridJsonResult"),
	@Result(name="roleAdd",type="dispatcher",location="/backstage/backstagerole/backstsgeRoleAdd.jsp"),
	@Result(name="loadRole",type="dispatcher",location="/backstage/backstagerole/backstsgeRoleEdit.jsp"),
	@Result(name="roleView",type="dispatcher",location="/backstage/backstagerole/backstsgeRoleView.jsp")
    })
public class BackstageRoleAction extends BSBaseAction {
	
	private static final long serialVersionUID = 5252106702123810124L;

	@Resource(name="backStageRoleService")
	private BackStageRoleService BackStageRoleService;
	
	private DataSet dataSet;// 用户JQGrid数据
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String roleName;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Integer stat;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BackStageRole backstagerole;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<BsRoleAuth> roleList;
	
	private Integer index;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String roleNameError;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private String[] auth;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private BsRoleAuth bsRoleAuth;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private List<String> authName ;
	
	@Begin
	@End
	public String execute(){
		roleName="";
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
			}else {
				page.setPageOffset(dataSet.getPage() - 1);
			}
			BackStageRoleCondition con = new BackStageRoleCondition();
			// 设置查询条件
			if (StringUtils.isNotBlank(roleName)) {
				con.setRoleName(roleName.trim());
			}
			if(stat != null){
				con.setStat(stat);
			}
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(Order.asc("iBsRole"));
			con.setOrders(orderList);
			roleList = this.BackStageRoleService.listRole(con, page);
			dataSet.setGridModel(roleList);
			dataSet.setTotal(page.getPageAmt());
			dataSet.setRecords(page.getRecAmt());
		}catch (Exception e) {
				e.printStackTrace();
				throw e;
		}
		return "list";
	}
	
	public String roleAdd() throws Exception {
		return "roleAdd";
	}
	
	@InputConfig(resultName = "roleAdd")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "roleName", message="角色名称不能为空！",shortCircuit=true)
					//@RequiredStringValidator(fieldName = "backstagerole.roleDesc", message="角色描述不能为空！",shortCircuit=true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "roleName", minLength = "2", message="角色名称不能少于2个字符", shortCircuit = true)
			       // @StringLengthFieldValidator(fieldName = "backstagerole.roleDesc", minLength = "2", message="角色描述不能少于2个字符", shortCircuit = true)
			}
	)
	public String saveRole() throws Exception{
		if (BackStageRoleService.checkRoleName(roleName)){
			roleNameError = "角色名已存在!";
			return "roleAdd";
		}
		try {
			BsRoleAuth bsRoleAuth = new BsRoleAuth();
			bsRoleAuth.setStat(1);
			bsRoleAuth.setRoleName(roleName);
			bsRoleAuth.setGenTime(new Date());
			Set<BackStageAuth> setRoleAuth = new HashSet<BackStageAuth>();
			if (auth != null) {
				for (int i = 0; i < auth.length; i++) {
					BackStageAuth bsa = new BackStageAuth();
					bsa = BackStageRoleService.queryByAuthCode(auth[i]);
					setRoleAuth.add(bsa);
				}
			}
			bsRoleAuth.setBsAuthSet(setRoleAuth);
			this.BackStageRoleService.saveRoleAuth(bsRoleAuth);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return execute();
	}
	
	public String roleView() throws Exception{
		try {
			bsRoleAuth = this.roleList.get(index);
			authName=new ArrayList<String>();
			for (BackStageAuth bsa: bsRoleAuth.getBsAuthSet()) {
				authName.add(bsa.getAuthCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "roleView";
	}
	
	public String loadRole() throws Exception{
		try {
			bsRoleAuth = this.roleList.get(index);
			authName=new ArrayList<String>();
			for (BackStageAuth bsa: bsRoleAuth.getBsAuthSet()) {
				authName.add(bsa.getAuthCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "loadRole";
	}
	
	@InputConfig(resultName = "loadRole")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "bsRoleAuth.roleName", message="角色名称不能为空！",shortCircuit=true)
					//@RequiredStringValidator(fieldName = "backstagerole.roleDesc", message="角色描述不能为空！",shortCircuit=true)
			},
			stringLengthFields = {
			        @StringLengthFieldValidator(fieldName = "bsRoleAuth.roleName", minLength = "2", message="角色名称不能少于2个字符", shortCircuit = true)
			       // @StringLengthFieldValidator(fieldName = "backstagerole.roleDesc", minLength = "2", message="角色描述不能少于2个字符", shortCircuit = true)
			}
	)
	public String updateRole() throws Exception{
		if (BackStageRoleService.CheckUpdateRoleName(bsRoleAuth.getRoleName())){
			roleNameError = "角色名已存在!";
			return "loadRole";
		}
		try {
			bsRoleAuth.setUpdTime(new Date());
			Set<BackStageAuth> setRoleAuth = new HashSet<BackStageAuth>();
			if (auth != null) {
				for (int i = 0; i < auth.length; i++) {
					BackStageAuth bsa = new BackStageAuth();
					bsa = BackStageRoleService.queryByAuthCode(auth[i]);
					if(bsa==null){
						continue;
					}
					setRoleAuth.add(bsa);
				}
			}
			bsRoleAuth.setBsAuthSet(setRoleAuth);
			this.BackStageRoleService.updateRoleAuth(bsRoleAuth);
			super.addActionMessage("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return execute();
	}
	
	public String deleteRole() throws Exception{
		try {
			bsRoleAuth = this.roleList.get(index);
			bsRoleAuth.setStat(0);
			this.BackStageRoleService.updateRoleAuth(bsRoleAuth);
			super.addActionMessage("禁用成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return execute();
	}
	
	public String enableRole() throws Exception{
		try {
			bsRoleAuth = this.roleList.get(index);
			bsRoleAuth.setStat(1);
			this.BackStageRoleService.updateRoleAuth(bsRoleAuth);
			super.addActionMessage("启用成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return execute();
	}
	
	@JqDataSet(content = "{o:roleName},{o:stat}")
	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}


	public BackStageRole getBackstagerole() {
		return backstagerole;
	}

	public void setBackstagerole(BackStageRole backstagerole) {
		this.backstagerole = backstagerole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getRoleNameError() {
		return roleNameError;
	}

	public void setRoleNameError(String roleNameError) {
		this.roleNameError = roleNameError;
	}

	public String[] getAuth() {
		return auth;
	}

	public void setAuth(String[] auth) {
		this.auth = auth;
	}

	public BsRoleAuth getBsRoleAuth() {
		return bsRoleAuth;
	}

	public void setBsRoleAuth(BsRoleAuth bsRoleAuth) {
		this.bsRoleAuth = bsRoleAuth;
	}

	public void setRoleList(List<BsRoleAuth> roleList) {
		this.roleList = roleList;
	}

	public List<String> getAuthName() {
		return authName;
	}

	public void setAuthName(List<String> authName) {
		this.authName = authName;
	}

	public List<BsRoleAuth> getRoleList() {
		return roleList;
	}
	
	
}
