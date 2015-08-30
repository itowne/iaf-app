/**
 * 
 */
package newland.iaf.inst.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.inst.dao.RoleAuthDao;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.RoleAuth;
import newland.iaf.inst.model.condition.InstRoleCondition;

/**
 * @author Mr.Towne
 * 
 */
@Repository("roleAuthDao")
public class RoleAuthDaoImpl extends BaseDao implements RoleAuthDao {

	@Override
	public boolean checkRoleName(String roleName, Long iInst) {
		InstRole ir = new InstRole();
		ir.setRoleName(roleName);
		ir.setiInst(iInst);
		@SuppressWarnings("unchecked")
		List<InstRole> instRoleList = this.getHibernateDao().findByExample(ir);
		if (instRoleList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<RoleAuth> queryInstRoleByCon(
			InstRoleCondition instRoleCondition, Page page) throws Exception {
		@SuppressWarnings("unchecked")
		List<RoleAuth> roleAuthList = this.getHibernateDao().findByCriteriaEx(
				instRoleCondition, page, true);
		return roleAuthList;
	}
	
	@Override
	public void saveRoleAuth(RoleAuth roleAuth) {
		this.getSession().save(roleAuth);
	}

	@Override
	public void deleteRoleAuth(RoleAuth roleAuth) {
		this.getSession().delete(roleAuth);
	}

	@Override
	public void updateRoleAuth(RoleAuth roleAuth) {
		this.getSession().update(roleAuth);
	}

	@Override
	public InstAuth queryByAuthName(String authCode) {
		InstAuth ia = new InstAuth();
		ia.setAuthCode(authCode);
		return this.getHibernateDao().getFirstOneByExample(ia);
	}

	@Override
	public boolean CheckUpdateRoleName(String roleName,Long iInst) {
		InstRole ir = new InstRole();
		ir.setRoleName(roleName);
		ir.setiInst(iInst);
		@SuppressWarnings("unchecked")
		List<InstRole> instRoleList = this.getHibernateDao().findByExample(ir);
		if (instRoleList.size() > 1) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstRole> queryByIinst(Long iInst) {
		InstRole ir = new InstRole();
		ir.setiInst(iInst);
		return this.getHibernateDao().findByExample(ir);
	}

	@Override
	public void cancelInstRole(RoleAuth roleAuth) {
		this.getSession().update(roleAuth);
	}
}
