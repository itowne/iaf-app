package newland.iaf.backstage.action.sysparam;

import javax.annotation.Resource;

import newland.iaf.backstage.action.BSBaseAction;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 * 参数管理
 * 
 * @author rabbit
 * 
 */
@ParentPackage("struts")
@Namespace("/backstage/sysparam")
@Action(value = "sysParamMgr")
@Results({
		@Result(name = "ftpSysParam", type = "dispatcher"),
		@Result(name = "checkPasswdSysParam", type = "dispatcher", location = "/backstage/creditcheck/index.jsp"),
		@Result(name = "viewDetail", type = "dispatcher", location = "/backstage/creditcheck/viewDetail.jsp"),
		@Result(name = "close", type = "dispatcher", location = "/backstage/creditcheck/close.jsp") })
public class SysParamMgrAction extends BSBaseAction {

	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5191855341075558524L;

	@Override
	public String execute() throws Exception {
		sysParamService.findSysParamByType(SysParamType.installLog);
		return super.execute();
	}
}
