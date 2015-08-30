package newland.iaf.inst.dao;

import java.util.List;

import newland.iaf.inst.model.InstCollMerch;
import newland.iaf.inst.model.InstCollOrd;

/**
 * 机构关注dao
 * 
 * @author Mr.Towne
 * 
 */
public interface InstCollDao {

	/**
	 * 新增机构关注订单
	 */
	void saveInstCollOrd(InstCollOrd instCollOrd);

	/**
	 * 删除机构关注订单
	 * 
	 * @param instCollOrd
	 */
	void deleteInstCollOrd(InstCollOrd instCollOrd);
	/**
	 * 根据机构内部编号查询机构关注订单
	 * 
	 * @param iInst
	 */
	List<InstCollOrd> queryCollOrdByIInst(Long iInst);
	
	// -----------------

	/**
	 * 新增机构关注商户
	 */
	void saveInstCollMerch(InstCollMerch instCollMerch);

	/**
	 * 删除机构关注商户
	 * 
	 * @param instCollMerch
	 */
	void deleteInstCollMerch(InstCollMerch instCollMerch);
	/**
	 * 根据机构内部编号查询机构关注商户
	 * 
	 * @param iInst
	 */
	List<InstCollMerch> queryCollMerchByIinst(Long iInst);
	/**
	 * 根据id查询机构关注商户
	 * 
	 * @param iInstCollMerch
	 */
	InstCollMerch getInstCollMerch(Long iInstCollMerch);
	/**
	 * 根据机构号 、商户号查询机构关注商户
	 */
	InstCollMerch queryByIinstAndImerch(Long iInst,Long iMerch);
	/**
	 * 根据机构号 、商户号、竞投号查询机构关注订单
	 */
	InstCollOrd queryInstCollOrdByIinstAndImerchAndIdebitBid(Long iInst,Long iMerch,Long idebitBid);
}
