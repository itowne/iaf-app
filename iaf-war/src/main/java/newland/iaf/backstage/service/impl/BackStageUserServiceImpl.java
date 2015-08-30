package newland.iaf.backstage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import newland.base.util.DigestUtil;
import newland.iaf.backstage.dao.BackStageRoleDao;
import newland.iaf.backstage.dao.BackStageUserDao;
import newland.iaf.backstage.model.BackStageAuth;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BackStageSession;
import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.backstage.model.BsRoleAuth;
import newland.iaf.backstage.service.BackStageUserService;
import newland.iaf.backstage.service.impl.BackStageLoanServiceImpl.NameType;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.condition.BackStageUserCondition;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.InstUserStatType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.RoleAuth;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service("backStageUserService")
@Transactional
public class BackStageUserServiceImpl implements BackStageUserService {
	@Resource(name = "backStageUserDao")
	private BackStageUserDao backStageUserDao;
	
	@Resource(name = "backStageRoleDao")
	private BackStageRoleDao backStageRoleDao;
	/**
	 * 日志
	 */
	@Resource(name = "operLogService")
	private OperLogService operLogService;

	@Override
	public BackStageUser login(String loginName, String password)
			throws Exception {
		BackStageUser user = this.queryBy(loginName);
		if (user == null)
			throw new Exception("用户不存在");
		if (user.getPassword().equals(DigestUtil.getSHA(password)))
			return user;
		else
			throw new Exception("登录密码错误");
	}

	@Override
	public BackStageUser queryBy(String loginName) throws Exception {
		BackStageUserCondition cond = new BackStageUserCondition();
		cond.setLoginName(loginName);
		//cond.setStatus(InstUserStatType.USED);
		List<BackStageUser> list = this.backStageUserDao.listUser(cond);
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

	@Override
	public void save(BackStageUser user) throws Exception {
		this.backStageUserDao.save(user);

	}

	@Override
	public BackStageUser changePwd(BackStageUser user, String oldPwd,
			String newPwd) throws Exception {
		BackStageUser bsu = this.login(user.getLoginName(), oldPwd);
		if (bsu != null) {
			bsu.setPassword(newPwd);
			this.save(bsu);
			return bsu;
		}
		return null;

	}

	@Override
	public IafConsoleSession newIafConsoleSession(BackStageUser user,
			HttpServletRequest req,String ipAddr) throws Exception {
		IafConsoleSession session = new IafConsoleSession();
		session.setUser(user);
		Set<BackStageRole> roleSet = user.getBsRoleSet();
		List<BackStageRole> roles = new ArrayList<BackStageRole>();
		roles.addAll(roleSet);
		List<BackStageAuth> auths = backStageRoleDao.queryAllAuth();
		List<BackStageAuth> allowAuths = backStageRoleDao.queryByUser(user);
		session.setRoles(user.getBsRoles());
		session.reg(req,user.getLoginName(),"iaf");
		session.setAuths(auths);
		session.setAllowAuths(allowAuths);

		//日志
		this.operLogService.iafLog(session, ipAddr, "汇卡登录", OperType.LOGIN, OperStat.SUCCESS);
		
		return session;
	}
	

	@Override
	public void logout(BackStageUser user, String ipaddr) {
		//日志
		IafConsoleSession iafconsoleSession = new IafConsoleSession();
		iafconsoleSession.setUser(user);
		this.operLogService.iafLog(iafconsoleSession, ipaddr, "汇卡退出", OperType.LOGOUT, OperStat.SUCCESS);

	}

	/**
	 * 保存后台管理用户
	 * 
	 * @param backStageUser
	 * @throws Exception
	 */
	public void saveBackStageUser(BackStageUser backStageUser) throws Exception {
		this.backStageUserDao.save(backStageUser);
	}

	/**
	 * 更新后台管理用户
	 * 
	 * @param backStageUser
	 * @throws Exception
	 */
	public void updateBackStageUser(BackStageUser backStageUser)
			throws Exception {
		this.backStageUserDao.update(backStageUser);
	}

	/**
	 * 分页查询
	 * 
	 * @param con
	 *            查询条件
	 * @param page
	 *            分页
	 * @return
	 * @throws Exception
	 */
	public List<BackStageUser> listUser(BackStageUserCondition con, Page page)
			throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iuser"));
		con.setOrders(orderList);
		return this.backStageUserDao.listUser(con, page);
	}

}
