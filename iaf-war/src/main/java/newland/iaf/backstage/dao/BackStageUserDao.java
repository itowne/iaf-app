package newland.iaf.backstage.dao;

import java.util.List;

import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.base.model.condition.BackStageUserCondition;

import org.ohuyo.commons.query.criterion.Page;

public interface BackStageUserDao {
	
	void save(BackStageUser user);
	
	void update(BackStageUser user);
	
	List<BackStageUser> query(BackStageUser user);
	
	/**
	 * 分页查询
	 * 
	 * @param con
	 *            查询条件
	 * @param page
	 *            分页
	 * @return
	 * @throws Exception
	 */
	List<BackStageUser> listUser(BackStageUserCondition con, Page page) throws Exception;

	List<BackStageUser> listUser(BackStageUserCondition con);

}
