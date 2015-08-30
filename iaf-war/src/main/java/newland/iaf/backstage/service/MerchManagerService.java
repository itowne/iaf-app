package newland.iaf.backstage.service;

import java.util.List;

import newland.iaf.base.model.OperLog;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.merch.model.Merch;

public interface MerchManagerService {
	
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
	void saveMerch(Merch merch, IafConsoleSession session, OperLog log);

	/**
	 * 修改商户信息
	 * 
	 * @param merch
	 *            商户信息对象
	 */
	void updateMerch(Merch merch, IafConsoleSession session, OperLog log);

	/**
	 * 根据ID查询商户信息
	 * 
	 * @param id
	 *            商户信息ID
	 * @return
	 */
	Merch findMerchById(String id);

}
