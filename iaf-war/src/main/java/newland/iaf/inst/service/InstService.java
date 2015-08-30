/**
 * 
 */
package newland.iaf.inst.service;

import java.util.List;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.InstLegalPers;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.condition.InstCondition;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 机构信息操作接口
 * 
 * @author lindaqun
 * 
 */
public interface InstService {
	/**
	 * 查询所有的机构信息
	 * 
	 * @return
	 */
	List<Inst> queryInst();

	/**
	 * 保存新增机构信息
	 * 
	 * @param Inst
	 *            机构信息对象
	 */
	void saveInst(Inst inst) throws Exception;

	/**
	 * 保存机构信息
	 * 
	 * @param Inst
	 *            机构信息对象
	 */
	void updateInst(Inst inst,IafConsoleSession ics, String ipaddr) throws Exception;

	/**
	 * 根据ID查询机构信息
	 * 
	 * @param id
	 *            机构信息ID
	 * @return
	 */
	Inst getInstById(Long iinst) throws Exception;

	// --------------------------
	/**
	 * 保存新增机构业务资料表信息
	 * 
	 * @param InstBusiInfo
	 *            机构业务资料表信息对象
	 */
	void saveInstBusiInfo(InstBusiInfo instbusiinfo) throws Exception;

	/**
	 * 保存机构业务资料表信息
	 * 
	 * @param instlegalpers
	 *            机构业务资料表信息对象
	 */
	void updateInstBusiInfo(InstBusiInfo instbusiinfo) throws Exception;

	/**
	 * 根据ID查询机构业务资料表信息
	 * 
	 * @param iinst
	 *            机构信息ID
	 * @return
	 */
	InstBusiInfo findInstBusiInfoByiinst(Long iinst) throws Exception;
	
	/**
	 * 根据受理地区查询机构业务资料表信息
	 * 
	 * @param iinst
	 *            机构信息acceptRegion
	 * @return
	 */
	public List<Long> findInstBusiInfoByAcceptRegion(String acceptRegion) throws Exception;
	
	/**
	 * 根据机构ID查找法人信息
	 * @param iinst
	 * @return
	 */
	InstLegalPers getInstLegalPersByIinst(Long iinst) throws Exception;

	// -----------------
	/**
	 * 保存新增机构法人资料表信息
	 * 
	 * @param ksnkey
	 *            机构法人资料表信息对象
	 */
	void saveInstLegalPers(InstLegalPers instlegalper) throws Exception;

	/**
	 * 保存机构法人资料表信息
	 * 
	 * @param ksnkey
	 *            机构法人资料表信息对象
	 */
	void updateInstLegalPers(InstLegalPers instlegalpers) throws Exception;

	/**
	 * 根据ID查询机构法人资料表信息
	 * 
	 * @param id
	 *            机构法人资料表信息ID
	 * @return
	 */
	InstLegalPers findInstLegalPersById(Long iInstLegalPers) throws Exception;

	/**
	 * 根据机构ID查询
	 * @param iinst
	 * @return
	 */
	Inst findInstById(Long iinst);
	
	/**
	 * 多条件查询机构列表
	 * 机构名称，注册资金
	 * @return
	 */
	List<Inst> queryInstByCon(InstCondition instCondition,Page page) throws Exception;
	
	/**
	 * 一次性保存所有的信息，在一个事务内。
	 * @param inst
	 * @param instBusiInfo
	 * @param instLegalPers
	 * @throws Exception
	 */
	void saveInstInfoAll(Inst inst,InstBusiInfo instBusiInfo,InstLegalPers instLegalPers,IafConsoleSession ics, String ipaddr) throws Exception;
	
	/**
	 * 一次性更新所有的信息，在一个事务内。
	 * @param inst
	 * @param instBusiInfo
	 * @param instLegalPers
	 * @throws Exception
	 */
	void updateInstInfoAll(Inst inst,InstBusiInfo instBusiInfo,InstLegalPers instLegalPers, IafConsoleSession ics,String ipaddr) throws Exception;
	/**
	 *根据id查找机构集合 
	 */
	List<Inst> getInstList(List<LoanOrd> iInst)throws Exception;
	/**
	 * 根据机构instId查询
	 */
	Inst queryByInstId(String instId);
	
	InstUser queryUserByIinst(Long iinst);
}
