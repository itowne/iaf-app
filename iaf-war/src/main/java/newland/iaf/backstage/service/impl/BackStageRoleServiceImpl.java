package newland.iaf.backstage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.backstage.dao.BackStageRoleDao;
import newland.iaf.backstage.model.BackStageAuth;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BackStageRoleAuth;
import newland.iaf.backstage.model.BsRoleAuth;
import newland.iaf.backstage.service.BackStageRoleService;
import newland.iaf.base.model.condition.BackStageRoleCondition;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
@Service("backStageRoleService")
@Transactional
public class BackStageRoleServiceImpl implements BackStageRoleService {
	@Resource (name = "backStageRoleDao")
	private BackStageRoleDao backStageRoleDao;

	@Override
	public void save(BackStageRole role) throws Exception {
		this.backStageRoleDao.save(role);
		
	}

	@Override
	public void update(BackStageRole role) throws Exception {
		this.backStageRoleDao.update(role);
		
	}

	@Override
	public BackStageRole queryByName(String roleName) throws Exception {
		BackStageRole role = new BackStageRole();
		role.setRoleName(roleName);
		List<BackStageRole> list = this.backStageRoleDao.query(role);
		if (CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

	@Override
	public BackStageRole queryById(Long irole) throws Exception {
//		BackStageRole role = new BackStageRole();
//		role.setiBsRole(irole);
//		List<BackStageRole> list = this.backStageRoleDao.query(role);
//		if (CollectionUtils.isEmpty(list)) return null;
//		return list.get(0);
		return backStageRoleDao.queryById(irole);
	}

	/**
	 * 分页查询角色信息
	 * @param con
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<BsRoleAuth> listRole(BackStageRoleCondition con,Page page) throws Exception{
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iBsRole"));
		con.setOrders(orderList);
		return this.backStageRoleDao.listRole(con, page);
	}

	@Override
	public List<BackStageRole> queryAllRoles() {
		// TODO Auto-generated method stub
		return backStageRoleDao.queryAllRoles();
	}

	@Override
	public void saveRoleAuth(BsRoleAuth bsRoleAuth) {
		backStageRoleDao.saveRoleAuth(bsRoleAuth);
	}

	@Override
	public void deleteRoleAuth(BsRoleAuth bsRoleAuth) {
		backStageRoleDao.deleteRoleAuth(bsRoleAuth);
	}

	@Override
	public void updateRoleAuth(BsRoleAuth bsRoleAuth) {
		backStageRoleDao.updateRoleAuth(bsRoleAuth);
	}

	@Override
	public boolean checkRoleName(String roleName) {
		return backStageRoleDao.checkRoleName(roleName);
	}

	@Override
	public boolean CheckUpdateRoleName(String roleName) {
		return backStageRoleDao.CheckUpdateRoleName(roleName);
	}

	@Override
	public BackStageAuth queryByAuthCode(String authCode) {
		return backStageRoleDao.queryByAuthCode(authCode);
	}

}
