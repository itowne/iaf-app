package newland.iaf.inst.service;

import java.util.List;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.inst.model.InstCollMerch;
import newland.iaf.inst.model.InstCollOrd;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.condition.MerchCondition;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 机构关注订单操作服务Service接口
 * 
 * @author lindaqun
 * 
 */
public interface InstCollService {

	/**
	 * 增加关注的订单信息，如果发现错误抛出对应错误信息
	 * 
	 * @param InstCollOrd
	 *            关注订单信息
	 * @throws Exception
	 *             数据库操作等异常
	 */
	void addInstCollOrd(InstCollOrd instcollord) throws Exception;

	/**
	 * 分页查询当前关注的订单信息，条件信息后续完善
	 * 
	 * @return 查询到的关注订单信息
	 * @throws Exception
	 *             查询异常信息
	 */
	List<DebitBid> queryInstCollOrdByCon(Long iInst,DebitBidCondition debitBidCondition, Page page) throws Exception;

	/**
	 * 删除关注的订单信息，如果发现错误抛出对应错误信息
	 * 
	 * @param instcollord
	 *            关注订单信息
	 * @throws Exception
	 *             数据库操作等异常
	 */
	void deleteInstCollOrd(InstCollOrd instcollord) throws Exception;

	/**
	 * 增加关注的商户信息，如果发现错误抛出对应错误信息
	 * 
	 * @param InstCollMerch
	 *            关注商户信息
	 * @throws Exception
	 *             数据库操作等异常
	 */
	void addInstCollMerch(InstCollMerch instcollmerch) throws Exception;

	/**
	 * 分页查询关注的商户信息，条件信息后续完善
	 * 
	 * @return 查询到的关注商户信息
	 * @throws Exception
	 *             查询异常信息
	 */
	List<Merch> queryInstCollMerchByCon(Long iinst,MerchCondition merchCondition, Page page) throws Exception;

	/**
	 * 删除关注的商户信息，如果发现错误抛出对应错误信息
	 * 
	 * @param iInstCollMerch
	 *            关注商户信息
	 * @throws Exception
	 *             数据库操作等异常
	 */
	void deleteInstCollMerch(Long iInstCollMerch) throws Exception;
	/**
	 * 根据机构号 、商户号查询机构关注商户
	 */
	InstCollMerch queryByIinstAndImerch(Long iInst,Long iMerch);
	/**
	 * 根据机构号 、商户号、竞投号查询机构关注订单
	 */
	InstCollOrd queryInstCollOrdByIinstAndImerchAndIdebitBid(Long iInst,Long iMerch,Long idebitBid);
}
