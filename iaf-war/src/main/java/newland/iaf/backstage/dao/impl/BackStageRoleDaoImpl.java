package newland.iaf.backstage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import newland.iaf.backstage.dao.BackStageRoleDao;
import newland.iaf.backstage.model.BackStageAuth;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.backstage.model.BsRoleAuth;
import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.condition.BackStageRoleCondition;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;

@Service("backStageRoleDao")
public class BackStageRoleDaoImpl extends BaseDao implements BackStageRoleDao {

	@Override
	public void save(BackStageRole role) {
		this.getSession().save(role);

	}

	@Override
	public void update(BackStageRole role) {
		this.getSession().update(role);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BackStageRole> query(BackStageRole role) {
		return this.getHibernateDao().findByExample(role);
	}
	
	/**
	 * 分页查询角色信息
	 * @param con
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BsRoleAuth> listRole(BackStageRoleCondition con,Page page) throws Exception{
		return this.getHibernateDao().findByCriteriaEx(con, page, true);
	}

	@Override
	public List<BackStageRole> queryAllRoles() {
		// TODO Auto-generated method stub
		BackStageRole bsr = new BackStageRole();
		bsr.setStat(1);
		return this.getHibernateDao().findByExample(bsr);
	}

	@Override
	public void saveRoleAuth(BsRoleAuth bsRoleAuth) {
		this.getSession().save(bsRoleAuth);
	}

	@Override
	public void deleteRoleAuth(BsRoleAuth bsRoleAuth) {
		this.getSession().delete(bsRoleAuth);
	}

	@Override
	public void updateRoleAuth(BsRoleAuth bsRoleAuth) {
		this.getSession().update(bsRoleAuth);
	}

	@Override
	public boolean checkRoleName(String roleName) {
		BackStageRole bsRole = new BackStageRole();
		bsRole.setRoleName(roleName);
		List<BackStageRole> RoleList = this.getHibernateDao().findByExample(bsRole);
		if (RoleList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean CheckUpdateRoleName(String roleName) {
		BackStageRole bsRole = new BackStageRole();
		bsRole.setRoleName(roleName);
		List<BackStageRole> RoleList = this.getHibernateDao().findByExample(bsRole);
		if (RoleList.size() > 1) {
			return true;
		}
		return false;
	}

	@Override
	public BackStageAuth queryByAuthCode(String authCode) {
		BackStageAuth bsa = new BackStageAuth();
		bsa.setAuthCode(authCode);
		return this.getHibernateDao().getFirstOneByExample(bsa);
	}

	@Override
	public List<BackStageAuth> queryByUser(BackStageUser user) {
		Set<BackStageRole> bsr = user.getBsRoleSet();
		if(bsr.isEmpty()){
			return new ArrayList<BackStageAuth>();
		}
		BackStageRole[]  bsRoles = bsr.toArray(new BackStageRole[bsr.size()]);
		BackStageRole role = bsRoles[0];
		BsRoleAuth bra =(BsRoleAuth) this.getSession().get(BsRoleAuth.class, role.getiBsRole());
		List<BackStageAuth> list  = new ArrayList<BackStageAuth>();
		list.addAll(bra.getBsAuthSet());
		return list;
	}

	@Override
	public List<BackStageAuth> queryAllAuth() {
		// TODO Auto-generated method stub
		return this.getHibernateDao().find(BackStageAuth.class);
	}

	@Override
	public BackStageRole queryById(Long id) {
		return (BackStageRole) this.getSession().get(BackStageRole.class, id);
	}

}
