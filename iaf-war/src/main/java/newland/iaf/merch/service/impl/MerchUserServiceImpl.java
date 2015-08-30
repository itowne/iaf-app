package newland.iaf.merch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.base.util.DigestUtil;
import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.merch.dao.MerchDao;
import newland.iaf.merch.dao.MerchUserDao;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.MerchUserType;
import newland.iaf.merch.model.condition.MerchUserCondition;
import newland.iaf.merch.service.MerchUserService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service("com.newland.iaf.merchUserService")
@Transactional
public class MerchUserServiceImpl implements MerchUserService {

	@Resource(name = "com.newland.iaf.merchUserDao")
	private MerchUserDao merchUserDao;

	@Resource(name = "com.newland.iaf.merchDao")
	private MerchDao merchDao;

	@Resource(name = "operLogService")
	private OperLogService operLogService;

	

	@Override
	public void regMerchUser(MerchUser merchUser) {
		Date d = new Date();
		merchUser.setGenTime(d);
		merchUser.setUpdTime(d);
		this.merchUserDao.saveMerchUser(merchUser);
	}

	public MerchSession newMerchSession(Long imerchUser, String ipaddr) {
		MerchUser mu = merchUserDao.getMerchUser(imerchUser);
		mu.setLastLoginTime(new Date());
		merchUserDao.updateMerchUser(mu);

		Long imerch = mu.getImerch();
		Merch m = merchDao.getMerch(imerch);
		MerchLegalPers mlp = merchDao.getMerchLegalPers(imerch);
		MerchBusiInfo mbi = merchDao.getMerchBusiInfo(imerch);

		MerchSession sess = new MerchSession();
		sess.setMerchUser(mu);
		sess.setMerch(m);
		sess.setMerchLegalPers(mlp);
		sess.setMerchBusiInfo(mbi);

		
		// 日志
		String memo = "登录系统";
		this.operLogService.merchLog(sess, ipaddr, memo, OperType.LOGIN, OperStat.SUCCESS);
		return sess;
	}

	@Override
	public void updateMerchUserPwd(MerchUser merchUser, String newPwd,
			MerchSession session, String ipaddr) {
		merchUser.setUpdTime(new Date());
		merchUser.setPasswd(DigestUtil.getSHA(newPwd.trim()));
		this.merchUserDao.updateMerchUser(merchUser);

		// 日志
		String memo = "修改密码";
		this.operLogService.merchLog(session, ipaddr, memo, OperType.MODIFY_PWD, OperStat.SUCCESS);
	}

	/**
	 * 根据商户ID查找商户用户
	 * 
	 * @param con
	 *            查询条件
	 * @param page
	 *            分页
	 * @return
	 * @throws Exception
	 */
	public List<MerchUser> listMerchUser(MerchUserCondition con, Page page)
			throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("imerchUser"));
		con.setOrders(orderList);
		return this.merchUserDao.listMerchUser(con, page);
	}

	/**
	 * 更新商户用户
	 * 
	 * @param merchUser
	 * @throws Exception
	 */
	public void updateMerchUser(MerchUser merchUser, MerchSession session,
			String ipaddr) throws Exception {
		// 日志
		String memo = "修改用户";
		this.operLogService.merchLog(session, ipaddr, memo, OperType.MODIFY_USER, OperStat.SUCCESS);
		this.merchUserDao.updateMerchUser(merchUser);
	}
	
	public void addMerchUserIAF(MerchUser merchUser, IafConsoleSession session,
			String ipaddr) throws Exception {
		// 日志
		String memo = "添加用户";
		this.operLogService.iafLog(session, ipaddr, memo, OperType.ADD_USER, OperStat.SUCCESS);
		this.merchUserDao.saveMerchUser(merchUser);
	}
	
	public void updateMerchUserIAF(MerchUser merchUser, IafConsoleSession session,
			String ipaddr) throws Exception {
		// 日志
		String memo = "修改用户";
		this.operLogService.iafLog(session, ipaddr, memo, OperType.MODIFY_USER, OperStat.SUCCESS);
		this.merchUserDao.updateMerchUser(merchUser);
	}
	
	@Override
	public MerchUser getMerchUser(String merchNo, String loginName,
			MerchUserType type) {
		return this.merchUserDao.getMerchUser(merchNo, loginName, type);
	}

	@Override
	public List<MerchUser> getMerchUserByLoginName(String loginName){
		return this.merchUserDao.getMerchUserByLoginName(loginName);
	}
	
	@Override
	public MerchUser getMerchUser(String merchNo, String loginName) {
		return this.merchUserDao.getMerchUser(merchNo, loginName);
	}

	@Override
	public void logout(Merch merch, MerchUser merchUser, String ipaddr) {
		//日志
		String memo = "退出系统";
		MerchSession merchSession = new MerchSession();
		merchSession.setMerch(merch);
		merchSession.setMerchUser(merchUser);
		this.operLogService.merchLog(merchSession, ipaddr, memo, OperType.LOGOUT, OperStat.SUCCESS);
	}
}
