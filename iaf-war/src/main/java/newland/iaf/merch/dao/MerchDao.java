package newland.iaf.merch.dao;

import java.util.Date;
import java.util.List;

import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.MerchFieldSurvy;
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
public interface MerchDao {

	/**
	 * 按商户名称查询
	 * 
	 * @param merch
	 */
	public Merch getMerch(String merchName);

	public boolean hasMerch(Merch merch);

	/**
	 * 新增商户
	 * 
	 * @param merch
	 */
	void saveMerch(Merch merch);

	/**
	 * 更改商户
	 * 
	 * @param merch
	 */
	void updateMerch(Merch merch);

	/**
	 * 
	 * @param imerch
	 * @return
	 */
	Merch getMerch(Long imerch);

	/**
	 * 
	 * @param merchNo
	 * @return
	 */
	Merch getMerchByMerchNo(String merchNo);

	/**
	 * 多条件查询商户 MerchCondition
	 */
	List<Merch> queryMerchByCon(MerchCondition merchCondition, Page page);

	/**
	 * 查询全部商户
	 * 
	 * @return
	 */
	List<Merch> queryMerch();

	// ----------------------
	/**
	 * 新增商户法人
	 * 
	 * @param merchLegalPers
	 */
	void saveMerchLegalPers(MerchLegalPers merchLegalPers);

	/**
	 * 更改商户法人
	 * 
	 * @param merchLegalPers
	 */
	void updateMerchLegalPers(MerchLegalPers merchLegalPers);

	MerchLegalPers getMerchLegalPers(Long iMerchLegalPers);

	// -----------------------------

	/**
	 * 新增商户业务资料
	 * 
	 * @param MerchBusiInfo
	 */
	void saveMerchBusiInfo(MerchBusiInfo MerchBusiInfo);

	/**
	 * 更改商户业务资料
	 * 
	 * @param MerchBusiInfo
	 */
	void updateMerchBusiInfo(MerchBusiInfo MerchBusiInfo);

	MerchBusiInfo getMerchBusiInfo(Long iMerchBusiInfo);

	Merch finMerchById(Long id);

	/**
	 * 根据ID查找业务信息
	 * 
	 * @param iinst
	 * @return
	 */
	MerchBusiInfo getMerchBusiInfoByImerch(Long imerch);

	/**
	 * 根据ID查找法人信息
	 * 
	 * @param iinst
	 * @return
	 */
	MerchLegalPers getMerchLegalPersByImerch(Long imerch);

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
	 * 
	 * @param merchFieldSurvy
	 * @throws Exception
	 */
	public void saveMerchFieldSurvy(MerchFieldSurvy merchFieldSurvy)
			throws Exception;

	/**
	 * 更新现场调查信息
	 * 
	 * @param merchFieldSurvy
	 * @throws Exception
	 */
	public void updateMerchFieldSurvy(MerchFieldSurvy merchFieldSurvy)
			throws Exception;
	
	public MerchUser queryUserByImerch(Long imerch);
	
	List<Object> queryTenDays(Date d,Date tenD,Long imerch); 
	
	List<LoanOrdPlan> queryIloanOrd(Date d,Date tenD,Long imerch); 
}
