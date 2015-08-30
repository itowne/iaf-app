package newland.iaf.backstage.service;

import java.util.List;

import newland.iaf.base.model.InstApplyRequest;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.inst.model.Inst;

public interface InstManagerService {
	
	/**
	 * 查询所有的机构信息
	 * 
	 * @return
	 */
	List<Inst> queryInst() throws Exception;

	/**
	 * 保存新增机构信息
	 * 
	 * @param Inst
	 *            机构信息对象
	 */
	void saveInst(Inst inst, IafConsoleSession session, OperLog log) throws Exception;

	/**
	 * 保存机构信息
	 * 
	 * @param Inst
	 *            机构信息对象
	 */
	void updateInst(Inst inst, IafConsoleSession session, OperLog log) throws Exception;
	
	/**
	 * 查询机构申请单
	 * @return
	 * @throws Exception
	 */
	List<InstApplyRequest> queryInstApply() throws Exception;
	
	/**
	 * 处理机构申请单
	 * @param req
	 * @throws Exception
	 */
	void procInstApply(InstApplyRequest req) throws Exception;

}
