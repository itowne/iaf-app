package newland.iaf.base.service;

import java.util.List;

import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.condition.ApplyRequestCondition;
import newland.iaf.base.model.dict.ApplyType;

import org.ohuyo.commons.query.criterion.Page;
/**
 * 申请单处理服务
 * @author new
 *
 */
public interface ApplyReqService {
	/**
	 * 提交申请
	 * @param req
	 * @return
	 * @throws Exception
	 */
	ApplyRequest apply(ApplyRequest req) throws Exception;
	/**
	 * 处理请求
	 * @param req
	 * @return
	 * @throws Exception
	 */
	ApplyRequest process(ApplyRequest req) throws Exception;
	/**
	 * 退回请求
	 * @param req
	 * @return
	 * @throws Exception
	 */
	ApplyRequest refuse(ApplyRequest req) throws Exception;
	/**
	 * 根据状态查询申请单
	 * @param type
	 * @return
	 */
	List<ApplyRequest> queryBy(ApplyType type);
	/**
	 * 复合查询
	 * @param cond
	 * @return
	 */
	List<ApplyRequest> queryBy(ApplyRequestCondition cond);
	/**
	 * 解冻
	 * @param req
	 */
	void cancel(ApplyRequest req);
	
	/**
	 * 复合查询 分页
	 * @param cond
	 * @return
	 */
	List<ApplyRequest> queryByPage(ApplyRequestCondition cond,Page page);
	
	public ApplyRequest unFreezenUnPayed(ApplyRequest req) throws Exception;
	
	public ApplyRequest unFreezenPayed(ApplyRequest req) throws Exception;
	
	void update(ApplyRequest req);
	
	ApplyRequest queryByIloanOrd(Long iloanord,String periods);

}
