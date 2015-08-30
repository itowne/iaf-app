package newland.iaf.base.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.dao.OperLogDao;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.OrdOperLog;
import newland.iaf.base.model.PdtOperLog;
import newland.iaf.base.model.condition.OperLogCondition;
import newland.iaf.base.model.condition.OrdOperLogCondition;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.service.OperLogService;
import newland.iaf.utils.DateUtils;
import newland.iaf.utils.service.SerialNoService;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("operLogService")
public class OperLogServiceImpl implements OperLogService {
	@Resource(name = "operLogDao")
	private OperLogDao operLogDao;
	@Resource(name = "serialNoService")
	private SerialNoService serialNoService;

	@Override
	public String save(OperLog log) {
		log.setTraceNo(this.serialNoService.genSystemNo());
		this.operLogDao.save(log);
		return log.getTraceNo();
	}

	@Override
	public OperLog queryById(String traceNo) {
		return this.operLogDao.queryById(traceNo);
	}

	@Override
	public List<OperLog> queryByMerchId(Long imerch, Date beginTime,
			Date endTime) {
		OrdOperLogCondition cond = new OrdOperLogCondition();
		cond.setImerch(imerch);
		cond.setBeginTime(DateUtils.roundDate(beginTime, Calendar.DATE));
		endTime = DateUtils.rollDate(endTime, Calendar.DATE, 1);
		endTime = DateUtils.roundDate(endTime, Calendar.DATE);
		cond.setEndTime(endTime);
		return this.operLogDao.queryByCondition(cond);
	}

	@Override
	public List<OperLog> queryByMerchRole(Long merchId, Long irole,
			Date beginTime, Date endTime) {
		OrdOperLogCondition cond = new OrdOperLogCondition();
		cond.setImerch(merchId);
		cond.setIrole(irole);
		cond.setBeginTime(DateUtils.roundDate(beginTime, Calendar.DATE));
		endTime = DateUtils.rollDate(endTime, Calendar.DATE, 1);
		endTime = DateUtils.roundDate(endTime, Calendar.DATE);
		cond.setEndTime(endTime);
		return this.operLogDao.queryByCondition(cond);
	}

	@Override
	public List<OperLog> queryByMerchUser(Long imerch, Long iuser,
			Date beginTime, Date endTime) {
		OrdOperLogCondition cond = new OrdOperLogCondition();
		cond.setImerch(imerch);
		cond.setIuser(iuser);
		cond.setBeginTime(DateUtils.roundDate(beginTime, Calendar.DATE));
		endTime = DateUtils.rollDate(endTime, Calendar.DATE, 1);
		endTime = DateUtils.roundDate(endTime, Calendar.DATE);
		cond.setEndTime(endTime);
		return this.operLogDao.queryByCondition(cond);
	}

	@Override
	@Transactional
	public List<OperLog> queryByOrdId(String ordId) {
		OrdOperLogCondition cond = new OrdOperLogCondition();
		cond.setLoanOrdId(ordId);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.asc("genTime"));
		cond.setOrders(orderList);
		return this.operLogDao.queryByCondition(cond);
	}

	@Override
	public List<OperLog> queryByInstId(Long iinst, Date beginTime, Date endTime) {
		OrdOperLogCondition cond = new OrdOperLogCondition();
		cond.setIinst(iinst);
		cond.setBeginTime(DateUtils.roundDate(beginTime, Calendar.DATE));
		endTime = DateUtils.rollDate(endTime, Calendar.DATE, 1);
		endTime = DateUtils.roundDate(endTime, Calendar.DATE);
		cond.setEndTime(endTime);
		return this.operLogDao.queryByCondition(cond);
	}

	@Override
	public List<OperLog> queryByInstRole(Long iinst, Long irole,
			Date beginTime, Date endTime) {
		OrdOperLogCondition cond = new OrdOperLogCondition();
		cond.setIinst(iinst);
		cond.setIrole(irole);
		cond.setBeginTime(DateUtils.roundDate(beginTime, Calendar.DATE));
		endTime = DateUtils.rollDate(endTime, Calendar.DATE, 1);
		endTime = DateUtils.roundDate(endTime, Calendar.DATE);
		cond.setEndTime(endTime);
		return this.operLogDao.queryByCondition(cond);
	}

	@Override
	public List<OperLog> queryByInstUser(Long iinst, Long iuser,
			Date beginTime, Date endTime) {
		OrdOperLogCondition cond = new OrdOperLogCondition();
		cond.setIinst(iinst);
		cond.setIuser(iuser);
		cond.setBeginTime(DateUtils.roundDate(beginTime, Calendar.DATE));
		endTime = DateUtils.rollDate(endTime, Calendar.DATE, 1);
		endTime = DateUtils.roundDate(endTime, Calendar.DATE);
		cond.setEndTime(endTime);
		return this.operLogDao.queryByCondition(cond);
	}

	@Override
	public List<OperLog> queryByBackStageRole(Long roleId, Date beginTime,
			Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperLog> queryByBackStageUser(Long userId, Date beginTime,
			Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperLog> queryByCond(OrdOperLogCondition cond) {
		return this.operLogDao.queryByCondition(cond);
	}

	@Override
	@Transactional
	public List<OperLog> queryByCond(OperLogCondition cond, Page page) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.desc("traceNo"));
		cond.setOrders(orders);
		return this.operLogDao.queryByCondition(cond, page);
	}

	@Override
	public void deleteByFundFlowTraceNo(String traceNo) {
		this.operLogDao.deleteBy(traceNo);

	}

	@Override
	@Transactional
	public List<OperLog> queryByCond(OrdOperLogCondition cond, Page page) {
		return this.operLogDao.queryByCondition(cond, page);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void instOrdLog(InstSession instSession, LoanOrd loanOrd,
			String ipAddr, String memo, OperType type, OperStat stat) {
		OrdOperLog ordLog = new OrdOperLog();
		// set base log info
		ordLog.setIpAddr(ipAddr);
		ordLog.setMemo(StringUtils.isEmpty(memo) ? "" : memo);
		ordLog.setGenTime(new Date());
		ordLog.setIinst(instSession.getInst().getIinst());
		ordLog.setInstId(instSession.getInst().getInstId());
		ordLog.setRoleNames(instSession.getRoles().get(0).getRoleName());
		ordLog.setIuser(instSession.getInstUsr().getIinstUser());
		ordLog.setIroles(instSession.getRoles().get(0).getiInstRole()
				.toString());
		ordLog.setLoginName(instSession.getInstUsr().getLoginName());
		ordLog.setInstType(InstType.INST);
		ordLog.setOperStat(stat);
		ordLog.setOperType(type);

		// set ord log info
		ordLog.setIloanOrd(loanOrd.getIloanOrd());
		ordLog.setLoanOrdId(loanOrd.getLoanOrdId());
		ordLog.setIloanPdt(loanOrd.getIloanPdt());
		ordLog.setLoanPdtId(loanOrd.getLoanPdtId());
		ordLog.setPdtType(PdtType.INST_PROD);
		ordLog.setAmount(loanOrd.getQuota());
		ordLog.setTerm(loanOrd.getTerm());
		ordLog.setYearRate(loanOrd.getRate());

		this.save(ordLog);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void instPdtLog(InstSession instSession, LoanPdt loanPdt,
			String ipAddr, String Memo, OperType type, OperStat stat) {

		PdtOperLog pdtLog = new PdtOperLog();
		// set base log info
		pdtLog.setIpAddr(ipAddr);
		pdtLog.setMemo(StringUtils.isEmpty(Memo) ? "" : Memo);
		pdtLog.setGenTime(new Date());
		pdtLog.setIinst(instSession.getInst().getIinst());
		pdtLog.setInstId(instSession.getInst().getInstId());
		pdtLog.setRoleNames(instSession.getRoles().get(0).getRoleName());
		pdtLog.setIuser(instSession.getInstUsr().getIinstUser());
		pdtLog.setIroles(instSession.getRoles().get(0).getiInstRole()
				.toString());
		pdtLog.setLoginName(instSession.getInstUsr().getLoginName());
		pdtLog.setInstType(InstType.INST);
		pdtLog.setOperStat(stat);
		pdtLog.setOperType(type);

		// set ord log info
		pdtLog.setIpdt(loanPdt.getIloanPdt());
		pdtLog.setPdtId(loanPdt.getLoadPdtId());
		pdtLog.setPdtType(PdtType.INST_PROD);
		pdtLog.setPdtName(loanPdt.getPdtName());
		pdtLog.setTerm(loanPdt.getMinTerm());
		pdtLog.setYearRate(loanPdt.getRate());
		pdtLog.setMinAmount(loanPdt.getMinQuota());
		pdtLog.setMaxAmount(loanPdt.getMaxQuota());

		this.save(pdtLog);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void instLog(InstSession instSession, String ipAddr, String Memo,
			OperType type, OperStat stat) {
		OperLog operLog = new OperLog();
		// set base log info
		operLog.setIpAddr(ipAddr);
		operLog.setMemo(StringUtils.isEmpty(Memo) ? "" : Memo);
		operLog.setGenTime(new Date());
		operLog.setIinst(instSession.getInst().getIinst());
		operLog.setInstId(instSession.getInst().getInstId());
		operLog.setRoleNames(instSession.getRoles().get(0).getRoleName().toString());
		operLog.setIuser(instSession.getInstUsr().getIinstUser());
		operLog.setUserName(instSession.getInstUsr().getUserName());
		operLog.setIroles(instSession.getRoles().get(0).getiInstRole().toString());
		operLog.setLoginName(instSession.getInstUsr().getLoginName());
		operLog.setInstType(InstType.INST);
		operLog.setOperStat(stat);
		operLog.setOperType(type);

		this.save(operLog);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void merchOrdLog(MerchSession merchSession, LoanOrd loanOrd,
			String ipAddr, String Memo, OperType type, OperStat stat) {
		// TODO Auto-generated method stub
		OrdOperLog ordLog = new OrdOperLog();
		// set base log info
		ordLog.setIpAddr(ipAddr);
		ordLog.setMemo(StringUtils.isEmpty(Memo) ? "" : Memo);
		ordLog.setGenTime(new Date());
		ordLog.setImerch(merchSession.getMerch().getImerch());
		ordLog.setMerchId(merchSession.getMerch().getMerchNo());
		ordLog.setIuser(merchSession.getMerchUser().getImerchUser());
		ordLog.setLoginName(merchSession.getMerchUser().getLoginName());
		ordLog.setInstType(InstType.MERCH);
		ordLog.setOperStat(stat);
		ordLog.setOperType(type);

		// set ord log info
		ordLog.setIloanOrd(loanOrd.getIloanOrd());
		ordLog.setLoanOrdId(loanOrd.getLoanOrdId());
		ordLog.setIloanPdt(loanOrd.getIloanPdt());
		ordLog.setLoanPdtId(loanOrd.getLoanPdtId());
		ordLog.setPdtType(PdtType.MERCH_PROD);
		ordLog.setAmount(loanOrd.getQuota());
		ordLog.setTerm(loanOrd.getTerm());
		ordLog.setYearRate(loanOrd.getRate());
		ordLog.setLoanPdtId(loanOrd.getLoanPdtId());
		
		this.save(ordLog);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void merchDebitLog(MerchSession merchSession, DebitBid debitBid,
			String ipAddr, String Memo, OperType type, OperStat stat) {
		PdtOperLog pdtLog = new PdtOperLog();
		// set base log info
		pdtLog.setIpAddr(ipAddr);
		pdtLog.setMemo(StringUtils.isEmpty(Memo) ? "" : Memo);
		pdtLog.setGenTime(new Date());
		pdtLog.setImerch(merchSession.getMerch().getImerch());
		pdtLog.setMerchId(merchSession.getMerch().getMerchNo());
		pdtLog.setIuser(merchSession.getMerchUser().getImerchUser());
		pdtLog.setLoginName(merchSession.getMerchUser().getLoginName());
		pdtLog.setInstType(InstType.MERCH);
		pdtLog.setOperStat(stat);
		pdtLog.setOperType(type);

		// set ord log info
		pdtLog.setIpdt(debitBid.getIdebitBid());
		pdtLog.setPdtId(debitBid.getDebitBidId());
		pdtLog.setPdtType(PdtType.MERCH_PROD);
		pdtLog.setPdtName(debitBid.getMerchName());
		pdtLog.setTerm(debitBid.getTerm());
		pdtLog.setYearRate(debitBid.getYearRate());
		pdtLog.setMinAmount(debitBid.getQuota());
		pdtLog.setMaxAmount(debitBid.getQuota());

		this.save(pdtLog);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void merchLog(MerchSession merchSession, String ipAddr, String Memo,
			OperType type, OperStat stat) {
		OperLog operLog = new OperLog();
		// set base log info
		operLog.setIpAddr(ipAddr);
		operLog.setMemo(StringUtils.isEmpty(Memo) ? "" : Memo);
		operLog.setGenTime(new Date());
		operLog.setImerch(merchSession.getMerch().getImerch());
		operLog.setMerchId(merchSession.getMerch().getMerchNo());
		operLog.setIuser(merchSession.getMerchUser().getImerchUser());
		operLog.setLoginName(merchSession.getMerchUser().getLoginName());
		operLog.setInstType(InstType.MERCH);
		operLog.setOperStat(stat);
		operLog.setOperType(type);

		this.save(operLog);
	}

	@Override
	public void iafOrdLog(IafConsoleSession iafConsoleSession, LoanOrd loanOrd,
			String ipAddr, String Memo, OperType type, OperStat stat) {
		OrdOperLog ordLog = new OrdOperLog();
		// set base log info
		ordLog.setIpAddr(ipAddr);
		ordLog.setMemo(StringUtils.isEmpty(Memo) ? "" : Memo);
		ordLog.setGenTime(new Date());
		ordLog.setIinst(loanOrd.getIinst());
		ordLog.setInstId(loanOrd.getOrganId());
		ordLog.setImerch(loanOrd.getImerch());
		ordLog.setMerchId(loanOrd.getMerchId());
		ordLog.setRoleNames(iafConsoleSession.getRoles());
		ordLog.setIuser(iafConsoleSession.getUser().getIuser());
		ordLog.setLoginName(iafConsoleSession.getUser().getLoginName());
		ordLog.setInstType(InstType.HICARD);
		ordLog.setOperStat(stat);
		ordLog.setOperType(type);

		// set ord log info
		ordLog.setIloanOrd(loanOrd.getIloanOrd());
		ordLog.setLoanOrdId(loanOrd.getLoanOrdId());
		ordLog.setIloanPdt(loanOrd.getIloanPdt());
		ordLog.setLoanPdtId(loanOrd.getLoanPdtId());
		ordLog.setPdtType(PdtType.INST_PROD);
		ordLog.setAmount(loanOrd.getQuota());
		ordLog.setLoanPdtId(loanOrd.getLoanPdtId());
		ordLog.setTerm(loanOrd.getTerm());
		ordLog.setYearRate(loanOrd.getRate());
		
		ordLog.setUserName(iafConsoleSession.getUser().getUserName());
		ordLog.setIroles(iafConsoleSession.getRoles());
  		
		this.save(ordLog);
	}

	@Override
	public void iafPdtLog(IafConsoleSession iafConsoleSession,
			DebitBid debitBid, String ipAddr, String Memo, OperType type, OperStat stat) {
		PdtOperLog pdtLog = new PdtOperLog();
		// set base log info
		pdtLog.setIpAddr(ipAddr);
		pdtLog.setMemo(StringUtils.isEmpty(Memo) ? "" : Memo);
		pdtLog.setGenTime(new Date());
		pdtLog.setRoleNames(iafConsoleSession.getRoles());
		pdtLog.setIuser(iafConsoleSession.getUser().getIuser());
		pdtLog.setLoginName(iafConsoleSession.getUser().getLoginName());
		pdtLog.setInstType(InstType.HICARD);
		pdtLog.setOperStat(stat);
		pdtLog.setOperType(type);

		// set ord log info
		pdtLog.setIpdt(debitBid.getIdebitBid());
		pdtLog.setPdtId(debitBid.getDebitBidId());
		pdtLog.setPdtType(PdtType.INST_PROD);
		pdtLog.setPdtName(debitBid.getMerchName());
		pdtLog.setTerm(debitBid.getTerm());
		pdtLog.setYearRate(debitBid.getYearRate());
		pdtLog.setMinAmount(debitBid.getQuota());
		pdtLog.setMaxAmount(debitBid.getQuota());
		
		this.save(pdtLog);

	}

	@Override
	public void iafLog(IafConsoleSession iafConsoleSession, String ipAddr,
			String Memo, OperType type, OperStat stat) {
		// TODO Auto-generated method stub
		OperLog operLog = new OperLog();
		// set base log info
		operLog.setIpAddr(ipAddr);
		operLog.setMemo(StringUtils.isEmpty(Memo) ? "" : Memo);
		operLog.setGenTime(new Date());
		operLog.setInstType(InstType.HICARD);
		
		operLog.setIroles(iafConsoleSession.getRoles());
		operLog.setRoleNames(iafConsoleSession.getRoles());
		operLog.setIuser(iafConsoleSession.getUser().getIuser());
		operLog.setLoginName(iafConsoleSession.getUser().getLoginName());
		operLog.setUserName(iafConsoleSession.getUser().getUserName());
		
		operLog.setOperStat(stat);
		operLog.setOperType(type);

		this.save(operLog);

	}

}
