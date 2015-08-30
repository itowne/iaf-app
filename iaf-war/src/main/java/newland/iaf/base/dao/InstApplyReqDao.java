package newland.iaf.base.dao;

import java.util.List;

import newland.iaf.base.model.InstApplyRequest;
import newland.iaf.base.model.condition.InstApplyRequestCondition;

import org.ohuyo.commons.query.criterion.Page;

public interface InstApplyReqDao {
	/**
	 * 插入
	 * @param req
	 */
	void save(InstApplyRequest req);
	/**
	 * 更新
	 * @param req
	 */
	void update(InstApplyRequest req);
	/**
	 * 查询全部
	 */
	List<InstApplyRequest> queryAll();
	/**
	 * 查询
	 * @param req
	 * @return
	 */
	List<InstApplyRequest> query(InstApplyRequest req);
	
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
