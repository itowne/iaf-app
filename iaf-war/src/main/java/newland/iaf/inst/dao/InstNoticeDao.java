/**
 * 
 */
package newland.iaf.inst.dao;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.inst.model.InstNotice;
import newland.iaf.inst.model.condition.InstNoticeCondition;

/**
 * 公告操作DAO接口
 * 
 * @author lxf
 * 
 */
public interface InstNoticeDao {
	/**
	 * 
	 * @param instNoticeCondition
	 * @param page
	 * @return
	 */
	List<InstNotice> querytInstNoticeByCon(InstNoticeCondition instNoticeCondition,
			Page page);
	
	/**
	 * 保存公告信息
	 * @param instNotice 公告信息
	 */
	void saveInstNotify(InstNotice instNotice) ;
	
	/**
	 * 更新公告信息
	 * @param instNotice 公告信息
	 */
	void updateInstNotice(InstNotice instNotice);
	
	/**
	 * 删除公告信息
	 * @param instNotice 公告信息
	 */
	void deleteInstNotice(InstNotice instNotice);
}
