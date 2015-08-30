package newland.iaf.inst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.dao.InstUserDao;
import newland.iaf.inst.dao.RoleAuthDao;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.RoleAuth;
import newland.iaf.inst.model.condition.InstRoleCondition;
import newland.iaf.inst.service.InstRoleManagerService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 机构角色管理操作Service接口服务
 * 
 * @author lindaqun
 * 
 */
@Service("com.newland.iaf.instRoleManagerService")
@Transactional
public class InstRoleManagerServiceImpl implements InstRoleManagerService {

	/** 机构用户角色操作DAO接口 **/
	@Resource(name = "com.newland.iaf.instUserDao")
	private InstUserDao instUserDao;
	
	@Resource (name = "operLogService")
	private OperLogService operLogService;
	
	@Resource(name="roleAuthDao")
	private RoleAuthDao roleAuthDao;

	@Override
	public List<InstRole> queryInstRoleByCon() throws Exception {
		// TODO Auto-generated method stub
		// 根据具体的条件实现分页查询功能
		return null;
	}

	@Override
	public void addInstRole(InstRole instrole, InstSession session, String ipaddr) throws Exception {
		//日志
		this.operLogService.instLog(session, ipaddr, "添加机构角色", OperType.ADD_ROLE, OperStat.SUCCESS);

		// 最后保存机构角色
		this.instUserDao.saveInstRole(instrole);
	}

	@Override
	public InstRole findInstRole(Long id) throws Exception {
		return (InstRole) this.instUserDao.getInstRole(id);
	}

	@Override
	public void updateInstRole(InstRole instrole, InstSession session, String ipaddr) throws Exception {
		// 根据需要设置对应的信息，包括角色，修改时间等信息
		//日志
		this.operLogService.instLog(session, ipaddr, "更新机构角色", OperType.MODIFY_ROLE, OperStat.SUCCESS);

		// 最后保存机构角色信息
		this.instUserDao.updateInstRole(instrole);
	}

	@Override
	public void deleteInstRole(InstRole instrole, InstSession session, String ipaddr) throws Exception {
		// 根据需要，可增加判断是否能允许删除，删除角色是否会影响到其他用户
		//日志
		this.operLogService.instLog(session, ipaddr, "删除机构角色", OperType.DEL_ROLE, OperStat.SUCCESS);

	}

	@Override
	public List<InstRole> queryByInst(Long iInst) {
		return instUserDao.queryByInstUser(iInst);
	}

	@Override
	public List<RoleAuth> queryInstRoleByCon(
			InstRoleCondition instRoleCondition, Page page) throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iInstRole"));
		instRoleCondition.setOrders(orderList);
		return roleAuthDao.queryInstRoleByCon(instRoleCondition, page);
	}

	@Override
	public boolean checkRoleName(String roleName,Long iInst) {
		return roleAuthDao.checkRoleName(roleName,iInst);
	}

	@Override
	public void saveRoleAuth(RoleAuth roleAuth, InstSession session, String ipaddr) {
		//日志
		this.operLogService.instLog(session, ipaddr, "添加机构角色", OperType.ADD_ROLE, OperStat.SUCCESS);

		roleAuthDao.saveRoleAuth(roleAuth);
	}

	@Override
	public void deleteRoleAuth(RoleAuth roleAuth, InstSession session, String ipaddr) {
		//日志
		this.operLogService.instLog(session, ipaddr, "删除机构角色", OperType.DEL_ROLE, OperStat.SUCCESS);

		roleAuthDao.deleteRoleAuth(roleAuth);
	}

	@Override
	public void updateRoleAuth(RoleAuth roleAuth, InstSession session, String ipaddr) {
		//日志
		this.operLogService.instLog(session, ipaddr, "修改机构角色", OperType.MODIFY_ROLE, OperStat.SUCCESS);

		roleAuthDao.updateRoleAuth(roleAuth);
	}
	
	/**
	 * 更新角色
	 * 
	 * @param roleAuth
	 */
	public void updateRoleAuthIAF(RoleAuth roleAuth,Long iinst,IafConsoleSession session, String ipaddr){
		//日志
		this.operLogService.iafLog(session, ipaddr, "修改汇卡角色", OperType.MODIFY_ROLE, OperStat.SUCCESS);
		
	}
	
	/**
	 * 新增角色
	 * 
	 * @param roleAuth
	 */
	public void saveRoleAuthIAF(RoleAuth roleAuth,Long iinst, IafConsoleSession session, String ipaddr){
		//日志
		this.operLogService.iafLog(session, ipaddr, "新增汇卡角色", OperType.ADD_ROLE, OperStat.SUCCESS);

		roleAuthDao.saveRoleAuth(roleAuth);
	}

	/**
	 * 删除角色
	 * 
	 * @param roleAuth
	 */
	public void deleteRoleAuthIAF(RoleAuth roleAuth,Long iinst,IafConsoleSession session, String ipaddr){
		//日志
		this.operLogService.iafLog(session, ipaddr, "删除汇卡角色", OperType.DEL_ROLE, OperStat.SUCCESS);

		roleAuthDao.deleteRoleAuth(roleAuth);
	}

	@Override
	public InstAuth queryByAuthName(String authName) {
		return roleAuthDao.queryByAuthName(authName);
	}

	@Override
	public boolean CheckUpdateRoleName(String roleName, Long iInst) {
		return roleAuthDao.CheckUpdateRoleName(roleName, iInst);
	}

	@Override
	public List<InstRole> queryByIinst(Long iInst) {
		return roleAuthDao.queryByIinst(iInst);
	}

	@Override
	public void cancelInstRole(RoleAuth roleAuth) {
		roleAuthDao.cancelInstRole(roleAuth);
	}
	
}	
