package newland.iaf.base.service;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.InstApplyRequest;
import newland.iaf.base.model.condition.InstApplyRequestCondition;
import newland.iaf.base.model.dict.ApplyStat;
/**
 * 机构申请注册服务
 * @author new
 *
 */
public interface InstApplyReqService {
	
	void apply(InstApplyRequest req) throws Exception;
	
	void update(InstApplyRequest req) throws Exception;
	
	void query(ApplyStat stat) throws Exception;
	
	/**
	 * 查询全部
	 */
	List<InstApplyRequest> queryAll();
	
	/**
	 * 根据ID查找对应的信息
	 * @param iinstApplyReq
	 * @return
	 */
	InstApplyRequest getInstApplyRequest(Long iinstApplyReq);
	
	/**
	 * 根据条件分页查询
	 * @param cond
	 * @param page
	 * @return
	 */
	List<InstApplyRequest> listInstApplyRequest(InstApplyRequestCondition cond,Page page);

}
