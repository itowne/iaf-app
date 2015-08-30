package newland.iaf.base.dao;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.condition.ApplyRequestCondition;

public interface ApplyReqDao {
	/**
	 * 保存
	 * @param req
	 */
	void save(ApplyRequest req);
	/**
	 * 更新
	 * @param req
	 */
	void update(ApplyRequest req);
	/**
	 * 查询
	 * @param req
	 * @return
	 */
	List<ApplyRequest> queryBy(ApplyRequest req);
	/**
	 * 复合查询
	 * @param cond
	 * @return
	 */
	List<ApplyRequest> queryBy(ApplyRequestCondition cond);
	/**
	 * 复合查询 分页
	 * @param cond
	 * @return
	 */
	List<ApplyRequest> queryByPage(ApplyRequestCondition cond,Page page);
	
	ApplyRequest queryByIloanOrd(Long iloanord,String periods);
	

}
