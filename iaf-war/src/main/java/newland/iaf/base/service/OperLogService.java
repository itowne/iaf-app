package newland.iaf.base.service;

import java.util.Date;
import java.util.List;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.OrdOperLog;
import newland.iaf.base.model.PdtOperLog;
import newland.iaf.base.model.condition.OperLogCondition;
import newland.iaf.base.model.condition.OrdOperLogCondition;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MerchSession;

import org.ohuyo.commons.query.criterion.Page;

public interface OperLogService {

	String save(OperLog log);

	/**
	 * 根据流水号查询
	 * 
	 * @param traceNo
	 * @return
	 */
	OperLog queryById(String traceNo);

	/**
	 * 根据商户号查询
	 * 
	 * @param merchId
	 * @return
	 */
	List<OperLog> queryByMerchId(Long merchId, Date beginTime, Date endTime);

	/**
	 * 根据商户角色查询
	 * 
	 * @param merchId
	 * @param RoleId
	 * @return
	 */
	List<OperLog> queryByMerchRole(Long imerch, Long irole, Date beginTime,
			Date endTime);

	/**
	 * 根据商户用户查询
	 * 
	 * @param merchId
	 * @param userId
	 * @return
	 */
	List<OperLog> queryByMerchUser(Long imerch, Long iuser, Date beginTime,
			Date endTime);

	/**
	 * 根据订单号查询
	 * 
	 * @param ordId
	 * @return
	 */
	List<OperLog> queryByOrdId(String ordId);

	/**
	 * 根据机构号查询
	 * 
	 * @param instId
	 * @return
	 */
	List<OperLog> queryByInstId(Long iinst, Date beginTime, Date endTime);

	/**
	 * 根据机构角色查询
	 * 
	 * @param instId
	 * @param roleId
	 * @return
	 */
	List<OperLog> queryByInstRole(Long iinst, Long irole, Date beginTime,
			Date endTime);

	/**
	 * 根据机构用户查询
	 * 
	 * @param instId
	 * @param userId
	 * @return
	 */
	List<OperLog> queryByInstUser(Long instId, Long iuser, Date beginTime,
			Date endTime);

	/**
	 * 根据后用角色查询
	 * 
	 * @param roleId
	 * @return
	 */
	List<OperLog> queryByBackStageRole(Long irole, Date beginTime, Date endTime);

	/**
	 * 根据后台用户查询
	 * 
	 * @param userId
	 * @return
	 */
	List<OperLog> queryByBackStageUser(Long iuser, Date beginTime, Date endTime);

	/**
	 * 复合查询
	 * 
	 * @param cond
	 */
	List<OperLog> queryByCond(OrdOperLogCondition cond);

	/**
	 * 复合查询
	 * 
	 * @param cond
	 * @return
	 */
	List<OperLog> queryByCond(OperLogCondition cond, Page page);

	/**
	 * 删除日志
	 * 
	 * @param traceNo
	 */
	void deleteByFundFlowTraceNo(String traceNo);

	/**
	 * 分页查询
	 * 
	 * @param cond
	 * @param page
	 * @return
	 */
	List<OperLog> queryByCond(OrdOperLogCondition cond, Page page);

	/**
	 * 机构订单日志
	 * 
	 * @return
	 */
	void instOrdLog(InstSession instSession, LoanOrd loanOrd, String ipAddr,
			String Memo, OperType type, OperStat stat);

	/**
	 * 机构商品日志
	 * 
	 * @param
	 * @return
	 */
	void instPdtLog(InstSession instSession, LoanPdt loanPdt, String ipAddr,
			String Memo, OperType type, OperStat stat);

	/**
	 * 机构普通日志
	 */
	void instLog(InstSession instSession, String ipAddr, String Memo,
			OperType type, OperStat stat);

	/**
	 * 商户订单日志
	 */
	void merchOrdLog(MerchSession merchSession, LoanOrd loanOrd, String ipAddr,
			String Memo, OperType type, OperStat stat);

	/**
	 * 商户竞投日志
	 */
	void merchDebitLog(MerchSession merchSession, DebitBid debitBid, String ipAddr,
			String Memo, OperType type, OperStat stat);

	/**
	 * 商户普通日志
	 */
	void merchLog(MerchSession merchSession, String ipAddr, String Memo,
			OperType type, OperStat stat);

	/**
	 * 汇卡订单日志
	 */
	void iafOrdLog(IafConsoleSession iafConsoleSession, LoanOrd loanOrd,
			String ipAddr, String Memo, OperType type, OperStat stat);

	/**
	 * 汇卡竞投日志
	 */
	void iafPdtLog(IafConsoleSession iafConsoleSession, DebitBid debitBid,
			String ipAddr, String Memo, OperType type, OperStat stat);

	/**
	 * 汇卡普通日志
	 */
	void iafLog(IafConsoleSession iafConsoleSession, String ipAddr,
			String Memo, OperType type, OperStat stat);
}
