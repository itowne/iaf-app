package newland.iaf.merch.service;

import java.util.Date;
import java.util.List;

import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.MerchFieldSurvy;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.condition.MerchCondition;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 
 * @author rabbit
 * 
 */
public interface MerchService {
	/**
	 * 按商户名称查询
	 * 
	 * @param merch
	 */
	public Merch getMerch(String merchName);

	/**
	 * 查询所有的商户信息
	 * 
	 * @return
	 */
	List<Merch> queryMerch();

	/**
	 * 保存新增商户信息
	 * 
	 * @param merch
	 *            商户信息对象
	 */
	void saveMerch(Merch merch);

	/**
	 * 修改商户信息
	 * 
	 * @param merch
	 *            商户信息对象
	 */
	void updateMerch(Merch merch);

	/**
	 * 根据ID查询商户信息
	 * 
	 * @param id
	 *            商户信息ID
	 * @return
	 */
	Merch findMerchById(Long id);

	/**
	 * 多条件查询商户列表
	 * 
	 * @return
	 */
	List<Merch> queryMerchByCon(MerchCondition merchCondition, Page page)
			throws Exception;

	/**
	 * 根据ID查找业务信息
	 * 
	 * @param iinst
	 * @return
	 */
	MerchBusiInfo getMerchBusiInfoByImerch(Long imerch);
	/**
	 * 保存新增商户业务资料
	 * 
	 * @param MerchBusiInfo
	 *            商户业务资料对象
	 */
	void saveMerchBusiInfo(MerchBusiInfo merchBusiInfo);
	
	/**
	 * 保存新增商户法人信息
	 * 
	 * @param MerchLegalPers
	 *            商户法人信息对象
	 */
	void saveMerchLegalPers(MerchLegalPers merchLegalPers);
	/**
	 * 根据ID查找法人信息
	 * 
	 * @param iinst
	 * @return
	 */
	MerchLegalPers getMerchLegalPersByImerch(Long imerch);

	/**
	 * 更新商户所有的信息
	 * 
	 * @param merch
	 * @param merchLegalPers
	 * @param merchBusiInfo
	 * @throws Exception
	 */
	void updateMerchInfoAll(Merch merch, MerchLegalPers merchLegalPers,
			MerchBusiInfo merchBusiInfo,IafConsoleSession ics,String addrStr)
			throws Exception;

	/**
	 * 根据商户内部编号查找现场调查报告
	 * 
	 * @return
	 */
	MerchFieldSurvy getMerchFieldSurvyByImerch(Long iMerch);

	/**
	 * 根据商户内部编号查找巡检服务记录
	 * 
	 * @return
	 */
	List<HcInspectLog> queryHcInspectLogByMerchNo(String merchNo);
	
	/**
	 * 保存现场调查信息
	 * @param merchFieldSurvy
	 * @throws Exception
	 */
	public void saveMerchFieldSurvy(MerchFieldSurvy merchFieldSurvy,IafConsoleSession ics, String addrStr) throws Exception;
	
	/**
	 * 更新现场调查信息
	 * @param merchFieldSurvy
	 * @throws Exception
	 */
	public void updateMerchFieldSurvy(MerchFieldSurvy merchFieldSurvy,IafConsoleSession ics, String addrStr) throws Exception;
	/**
	 * 信用报告更新商户基本信息
	 */
	public void updateMerchMerchLegalPers(MerchLegalPers merchLegalPers);
	/**
	 * 信用报告更新商户信息
	 */
	public void updateMerchMerchBusiInfo(MerchBusiInfo merchBusiInfo);
	/**
	 * 修改商户的信用等级
	 */
	public void updateCredit(Merch merch,MerchLegalPers merchLegalPers,MerchFieldSurvy merchFieldSurvy);
	
	public Merch queryByMerchNo(String merchNo);
	
	public MerchUser queryUserByImerch(Long imerch);
	
	List<Object> queryTenDays(Date d,Date tenD,Long imerch); 
	
	List<LoanOrdPlan> queryIloanOrd(Date d,Date tenD,Long imerch); 
}
