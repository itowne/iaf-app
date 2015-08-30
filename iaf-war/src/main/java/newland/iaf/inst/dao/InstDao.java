/**
 * 
 */
package newland.iaf.inst.dao;

import java.util.List;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.InstLegalPers;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.condition.InstCondition;

import org.ohuyo.commons.query.criterion.Page;

/**
 * @author Mr.Towne
 * 
 */
public interface InstDao {
	/**
	 * 新增机构
	 * 
	 * @param inst
	 */
	void saveInst(Inst inst);

	/**
	 * 更改机构
	 * 
	 * @param inst
	 */
	void updateInst(Inst inst);

	/**
	 * 根据id查询机构
	 * 
	 * @param id
	 * @return
	 */
	Inst getInst(Long iinst);

	/**
	 * 多条件查询机构列表
	 * 机构名称，注册资金
	 * @return
	 */
	List<Inst> queryInstByCon(InstCondition instCondition,Page page) throws Exception;

	// ---------------------------------------------

	/**
	 * 保存新增机构法人资料表信息
	 * 
	 * @param instlegalpers
	 *            机构法人资料表信息对象
	 */
	void saveInstLegalPers(InstLegalPers instlegalpers);

	/**
	 * 保存机构法人资料表信息
	 * 
	 * @param instlegalpers
	 *            机构法人资料表信息对象
	 */
	void updateInstLegalPers(InstLegalPers instlegalpers);

	/**
	 * 根据ID查询机构法人资料表信息
	 * 
	 * @param id
	 *            机构法人资料表信息ID
	 * @return
	 */
	InstLegalPers getInstLegalPersIinst(Long iInstLegalPers);

	// -------------------------------------

	/**
	 * 保存新增机构业务资料表信息
	 * 
	 * @param InstBusiInfo
	 *            机构业务资料表信息对象
	 */
	void saveInstBusiInfo(InstBusiInfo instbusiinfo);

	/**
	 * 保存机构业务资料表信息
	 * 
	 * @param instlegalpers
	 *            机构业务资料表信息对象
	 */
	void updateInstBusiInfo(InstBusiInfo instbusiinfo);

	/**
	 * 根据ID查询机构业务资料表信息
	 * 
	 * @param id
	 *            机构业务资料表信息ID
	 * @return
	 */
	InstBusiInfo getInstBusiInfoByIinst(Long iinst);
	/**
	 * 根据acceptRegion查询机构业务资料表信息
	 * 
	 * @param acceptRegion
	 *            机构业务资料表信息acceptRegion
	 * @return
	 */
	List<Long> getInstBusiInfoByAcceptRegion(String acceptRegion);
	/**
	 * 查询全部机构
	 * @return
	 */
	List<Inst> queryInst();
	
	/**
	 * 根据机构ID查找法人信息
	 * @param iinst
	 * @return
	 */
	InstLegalPers getInstLegalPersByIinst(Long iinst);
	/**
	 *根据id查找机构集合 
	 */
	List<Inst> getInstList(List<LoanOrd> iInst);
	/**
	 * 根据机构instId查询
	 */
	Inst queryByInstId(String instId);
	
	InstUser queryUserByIinst(Long iinst);
}
