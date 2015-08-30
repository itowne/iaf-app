package newland.iaf.base.dao;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.condition.OperLogCondition;
import newland.iaf.base.model.condition.OrdOperLogCondition;

public interface OperLogDao {
	/**
	 * 插入新记录
	 * @param log
	 */
	void save (OperLog log);
	/**
	 * 根据流水号查询
	 * @param traceNo
	 * @return
	 */
	OperLog queryById(String traceNo);

	/**
	 * 分页条件查询
	 * @param cond
	 * @param page
	 * @return
	 */
	List<OperLog> queryByCondition(OrdOperLogCondition cond, Page page);
	/**
	 * 条件查询不分页
	 * @param cond
	 * @return
	 */
	List<OperLog> queryByCondition(OrdOperLogCondition cond);
	/**
	 * 分页条件查询
	 * @param cond
	 * @param page
	 * @return
	 */
	List<OperLog> queryByCondition(OperLogCondition cond, Page page);
	/**
	 * 
	 * @param traceNo
	 */
	void deleteBy(String traceNo);

}
