package newland.iaf.inst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import newland.base.util.DigestUtil;
import newland.base.util.StringUtils;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.RsaService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.sms.AuthCodeService;
import newland.iaf.inst.dao.InstDao;
import newland.iaf.inst.dao.InstUserDao;
import newland.iaf.inst.dao.RoleAuthDao;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstAuth;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.InstLegalPers;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.InstUserRole;
import newland.iaf.inst.model.condition.InstUserCondition;
import newland.iaf.inst.service.InstUserService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 机构用户登录Service操作服务
 * 
 * @author lindaqun
 * 
 */
@Service("com.newland.iaf.instUserService")
@Transactional
public class InstUserServiceImpl implements InstUserService {

	/** 机构用户信息操作DAO接口 **/
	@Resource(name = "com.newland.iaf.instUserDao")
	private InstUserDao instUserDao;

	@Resource(name = "roleAuthDao")
	private RoleAuthDao roleAuthDao;

	/**
	 * 日志
	 */
	@Resource(name = "operLogService")
	private OperLogService operLogService;

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.instDao")
	private InstDao instDao;

	@Resource(name = "com.newland.iaf.authCodeService")
	private AuthCodeService authCodeService;

	@Resource(name = "com.newland.iaf.rsaService")
	private RsaService rsaService;

	@Override
	public void changeUserPassword(Long iinstuser, String oldPassword,
			String newPassword, InstSession instSession, String ipaddr)
			throws Exception {
		InstUser usr = instUserDao.getInstUser(iinstuser);

		if (StringUtils.equals(DigestUtil.getSHA(oldPassword), usr.getPasswd())) {
			usr.setPasswd(DigestUtil.getSHA(newPassword));

			// 日志
			this.operLogService.instLog(instSession, ipaddr, "修改密码", OperType.MODIFY_PWD, OperStat.SUCCESS);

			instUserDao.updateInstUser(usr);

		} else {
			throw new Exception("密码错误");
		}
	}

	@Override
	public void changeInstUserInfo(InstUser instuser, InstSession session,
			String ipaddr) throws Exception {
		// 修改用户的详细资料等信息
	}

	public InstUser loginInstUser(String loginName, String passwd) {
		String buf = rsaService.encrypt(passwd);
		buf = DigestUtil.getSHA(buf);
		return instUserDao.getInstUser(loginName, buf);
	}

	@Override
	public InstSession newInstSession(Long iinstUser, String address) {
		InstUser iu = instUserDao.getInstUser(iinstUser);
		Set<InstRole> roleSet = iu.getInstRoleSet();
		List<InstRole> roles = new ArrayList<InstRole>();
		roles.addAll(roleSet);
		List<InstAuth> auths = instUserDao.ListInstAuth();
		List<InstAuth> allowAuths = instUserDao
				.findInstAuthByIInstUsr(iinstUser);

		Long iinst = iu.getIinst();
		Inst inst = instDao.getInst(iinst);
		InstBusiInfo instBusiInfo = instDao.getInstBusiInfoByIinst(iinst);
		InstLegalPers instLegalPers = instDao.getInstLegalPersIinst(iinst);

		InstSession sess = new InstSession();
		sess.setAuths(auths);
		sess.setRoles(roles);
		sess.setAllowAuths(allowAuths);
		sess.setInstUsr(iu);

		sess.setInst(inst);
		sess.setInstBusiInfo(instBusiInfo);
		sess.setInstLegalPers(instLegalPers);

		// 日志
		this.operLogService.instLog(sess, address, "登录", OperType.LOGIN, OperStat.SUCCESS);

		return sess;
	}

	/**
	 * 多条件查询机构帐号管理 帐号 使用者 所属角色 状态
	 */
	@Override
	@Transactional
	public List<InstUser> queryInstUserByCon(
			InstUserCondition instUserCondition, Page page) throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iinstUser"));
		instUserCondition.setOrders(orderList);
		return instUserDao.querytInstUserByCon(instUserCondition, page);
	}

	@Override
	@Transactional
	public List<InstRole> queryByInstUser(Long iInst) {
		return instUserDao.queryByInstUser(iInst);
	}

	@Override
	public void addInstUser(InstUser instUser, InstSession instSession,
			String ipaddr) {

		// 日志
		this.operLogService.instLog(instSession, ipaddr, "添加用户", OperType.ADD_USER, OperStat.SUCCESS);

		instUserDao.saveInstUser(instUser);
	}

	public void updateInstUser(InstUser instUser, InstSession instSession,
			String ipaddr) {

		// 日志
		this.operLogService.instLog(instSession, ipaddr, "修改用户", OperType.MODIFY_USER, OperStat.SUCCESS);

		instUserDao.updateInstUser(instUser);
	}

	/**
	 * 新增机构用户
	 * 
	 * @param instUser
	 */
	public void addInstUserIAF(InstUser instUser, Long iinst,
			IafConsoleSession session, String ipaddr) {
		//日志
		this.operLogService.iafLog(session, ipaddr, "添加机构用户", OperType.ADD_USER, OperStat.SUCCESS);

		instUserDao.saveInstUser(instUser);
	}

	/**
	 * 更新机构用户
	 * 
	 * @param instUser
	 */
	public void updateInstUserIAF(InstUser instUser, Long iinst,
			IafConsoleSession session, String ipaddr) {
		//日志
		this.operLogService.iafLog(session, ipaddr, "修改机构用户", OperType.MODIFY_USER, OperStat.SUCCESS);

		instUserDao.updateInstUser(instUser);
	}

	@Override
	public InstUser queryByInstUserId(Long iInstUser) {
		return instUserDao.getInstUser(iInstUser);
	}

	@Override
	public void updateInstUserRole(InstUserRole iur, InstSession instSession,
			String ipaddr) {
		// 日志
		this.operLogService.instLog(instSession, ipaddr, "修改角色", OperType.MODIFY_ROLE, OperStat.SUCCESS);

		instUserDao.updateInstUserRole(iur);
	}

	@Override
	public void reCoverInstUsr(InstUser instUser) {
		instUserDao.updateInstUser(instUser);
	}

	@Override
	public void deleteInstUser(InstUser instUser, InstSession instSession,
			String ipaddr) {
		// 日志
		this.operLogService.instLog(instSession, ipaddr, "删除用户", OperType.DEL_USER, OperStat.SUCCESS);

		instUserDao.deleteInstUser(instUser);
	}

	/**
	 * 删除机构用户
	 */
	public void deleteInstUserIAF(InstUser instUser, Long iinst,
			IafConsoleSession session, String ipaddr) {
		//日志
		this.operLogService.iafLog(session, ipaddr, "删除用户", OperType.DEL_USER, OperStat.SUCCESS);

		instUserDao.deleteInstUser(instUser);
	}

	@Override
	public boolean checkRegInstUser(String loginName, Long iInst) {
		return instUserDao.checkRegInstUser(loginName, iInst);
	}

	@Override
	public InstRole queryByiInstAndIrole(Long iInst, Long iRole) {
		return instUserDao.queryByiInstAndIrole(iInst, iRole);
	}

	enum NameType {
		ID, NAME;
	}

	@Override
	public void logout(InstSession instSession, String ipaddr) {
		//日志
		this.operLogService.instLog(instSession, ipaddr, "机构退出", OperType.LOGOUT, OperStat.SUCCESS);
	}

	@Override
	public InstUser getInstUserByLoginName(String loginName) {
		return instUserDao.getInstUserByLoginName(loginName);
	}

	@Override
	public boolean checkUpdateInstUser(String loginName, Long iInst) {
		return instUserDao.checkUpdateInstUser(loginName, iInst);
	}

}
