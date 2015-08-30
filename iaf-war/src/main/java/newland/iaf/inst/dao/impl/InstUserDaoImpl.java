/**
 * 
 */
package newland.iaf.inst.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.inst.dao.InstUserDao;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstRoleAuth;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.InstUserRole;
import newland.iaf.inst.model.RoleAuth;
import newland.iaf.inst.model.condition.InstRoleCondition;
import newland.iaf.inst.model.condition.InstUserCondition;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 机构法人资料表法人资料表操作DAO接口服务实现
 * 
 * @author lindaqun
 * 
 */
@Repository("com.newland.iaf.instUserDao")
public class InstUserDaoImpl extends BaseDao implements InstUserDao {

	@Override
	public List<InstUser> queryInstUser() {
		return this.getHibernateDao().find(InstUser.class);
	}

	@Override
	public void saveInstUser(InstUser instuser) {
		this.getSession().save(instuser);
	}

	@Override
	public void updateInstUser(InstUser instuser) {
		this.getSession().update(instuser);
	}

	@Override
	public InstUser getInstUser(Long id) {
		return (InstUser) this.getSession().get(InstUser.class, id);
	}

	// -----------------------
	@Override
	public void saveInstUserRole(InstUserRole instuserrole) {
		this.getSession().save(instuserrole);
	}

	@Override
	public void updateInstUserRole(InstUserRole instuserrole) {
		this.getSession().update(instuserrole);
	}

	@Override
	public List<InstUserRole> listInstUserRoleByIInstUsr(Long iInstUsr) {
		InstUserRole iur = new InstUserRole();
		iur.setiInstUser(iInstUsr);
		return this.getHibernateDao().findByExample(iur);
	}

	// -------------------------
	@Override
	public void saveInstRole(InstRole instrole) {
		this.getSession().save(instrole);
	}

	@Override
	public void updateInstRole(InstRole instrole) {
		this.getSession().update(instrole);
	}

	@Override
	public InstRole getInstRole(Long id) {
		return (InstRole) this.getSession().load(InstRole.class, id);
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<InstRole> listInstRolesByIInstUsr(Long iInstUsr) {
	// List<InstUserRole> iurs = this.listInstUserRoleByIInstUsr(iInstUsr);
	// List<Long> t = new ArrayList<Long>();
	// for (InstUserRole r : iurs) {
	// t.add(r.getiInstRole());
	// }
	// DetachedCriteria dc = DetachedCriteria.forClass(InstRole.class);
	// dc.add(Restrictions.in("iInstRole", t));
	// return this.getHibernateDao().findByDetachedCriteria(dc, 0, -1);
	// }

	// ---------------------------------
	@Override
	public InstAuth getInstAuth(Long id) {
		return (InstAuth) this.getSession().load(InstAuth.class, id);
	}

	@Override
	public List<InstAuth> findInstAuthByIInstUsr(Long iInstUsr) {
		InstUser ia = (InstUser) this.getSession()
				.get(InstUser.class, iInstUsr);
		Set<InstRole> set = ia.getInstRoleSet();
		if (set.isEmpty()) {
			return new ArrayList<InstAuth>();
		}
		InstRole[] roles = set.toArray(new InstRole[set.size()]);
		InstRole role = roles[0];
		RoleAuth ra = (RoleAuth) getSession().get(RoleAuth.class,
				role.getiInstRole());
		List<InstAuth> list = new ArrayList<InstAuth>();
		list.addAll(ra.getInstAuthSet());
		return list;
	}

	@Override
	public List<InstAuth> ListInstAuth() {
		return this.getHibernateDao().find(InstAuth.class);
	}

	@Override
	public void saveInstRoleAuth(InstRoleAuth instroleauth) {
		this.getSession().save(instroleauth);
	}

	@Override
	public void updateInstRoleAuth(InstRoleAuth instroleauth) {
		this.getSession().update(instroleauth);
	}

	@Override
	public void deleteInstRoleAuth(InstRoleAuth instroleauth) {
		this.getSession().delete(instroleauth);
	}

	@Override
	public List<InstRoleAuth> listInstRoleAuthByIInstRole(Long iInstRole) {
		InstRoleAuth ira = new InstRoleAuth();
		ira.setiInstRole(iInstRole);
		return this.getHibernateDao().findByExample(ira);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstRoleAuth> listInstRoleAuthByIInstRole(
			List<Long> iInstRoleList) {
		DetachedCriteria dc = DetachedCriteria.forClass(InstRoleAuth.class);
		dc.add(Restrictions.in("iInstRole", iInstRoleList));
		return this.getHibernateDao().findByDetachedCriteria(dc, 0, -1);
	}

	@Override
	public InstUser getInstUser(String loginName, String passwd) {
		InstUser iu = new InstUser();
		iu.setLoginName(loginName);
		iu.setPasswd(passwd);
		return this.getHibernateDao().getFirstOneByExample(iu);
	}

	@Override
	@Transactional
	public InstUser test(String loginName, String roleName) {
		Criteria cri = this.getSession().createCriteria(InstUser.class)
				.add(Restrictions.eq("loginName", loginName))
				.createCriteria("instRoleSet")
				.add(Restrictions.eq("roleName", roleName));
		return (InstUser) cri.list().get(0);
	}

	/**
	 * 根据机构用户编号查询角色
	 * 
	 * @return
	 */
	@Override
	public List<InstRole> queryByInstUser(Long iInst) {
		InstRole instRole = new InstRole();
		instRole.setiInst(iInst);
		instRole.setRoleStat(1);
		return this.getHibernateDao().findByExample(instRole);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstUser> querytInstUserByCon(
			InstUserCondition instUserCondition, Page page) throws Exception {
		Long iInstRole = instUserCondition.getIinstRole();
		if (iInstRole != null) {
			InstRole iur = this.getInstRole(iInstRole);
			Set<InstUser> users = iur.getInstUserSet();
			List<Long> iusers = new ArrayList<Long>();
			for (InstUser instUser : users) {
				iusers.add(instUser.getIinstUser());
			}
			instUserCondition.setIinstUser(iusers);
		}
		List<InstUser> InstUserList = this.getHibernateDao().findByCriteriaEx(
				instUserCondition, page, true);
		return InstUserList;
	}

	@Override
	public void deleteInstUser(InstUser instUser) {
		this.getSession().delete(instUser);
	}

	@Override
	public boolean checkRegInstUser(String loginName, Long iInst) {
		InstUser iu = new InstUser();
		//iu.setIinst(iInst);
		iu.setLoginName(loginName);
		List<InstUser> lt = this.getHibernateDao().findByExample(iu);
		if (lt.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public InstRole queryByiInstAndIrole(Long iInst, Long iRole) {
//		InstRole ir = new InstRole();
//		ir.setiInst(iInst);
//		ir.setiInstRole(iRole);
//		return this.getHibernateDao().getFirstOneByExample(ir);
		return (InstRole) this.getSession().get(InstRole.class, iRole);
	}
	

	@Override
	public List<InstRole> queryInstRoleByCon(
			InstRoleCondition instRoleCondition, Page page) throws Exception {
		@SuppressWarnings("unchecked")
		List<InstRole> InstRoleList = this.getHibernateDao().findByCriteriaEx(
				instRoleCondition, page, true);
		return InstRoleList;
	}

	@Override
	public InstUser getInstUserByLoginName(String loginName) {
		InstUser usr = new InstUser();
		usr.setLoginName(loginName);
		return this.getHibernateDao().getFirstOneByExample(usr);
	}

	@Override
	public boolean checkUpdateInstUser(String loginName, Long iInst) {
		InstUser iu = new InstUser();
		iu.setIinst(iInst);
		iu.setLoginName(loginName);
		List<InstUser> lt = this.getHibernateDao().findByExample(iu);
		if (lt.size() > 1) {
			return true;
		}
		return false;
	}
}
