/**
 * 
 */
package newland.iaf.inst.service;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.inst.model.InstNotice;
import newland.iaf.inst.model.condition.InstNoticeCondition;

/**
 * 公告Service操作服务
 * 
 * @author lxf
 * 
 */
public interface InstNoticeService {	
	/**
	 * 多条件查询公告
	 * @param page
	 * @return
	 */
	List<InstNotice> queryInstNoticeByCon(InstNoticeCondition instNoticeCondition,Page page);
	
	/**
	 * 保存公告信息
	 * @param instNotice 公告信息
	 */
	void saveInstNotify(InstNotice instNotice,OperType operType,IafConsoleSession session, String ipaddr) ;
	
	/**
	 * 更新公告信息
	 * @param instNotice 公告信息
	 */
	void updateInstNotice(InstNotice instNotice,OperType operType,IafConsoleSession session, String ipaddr);
	
	/**
	 * 删除公告信息
	 * @param instNotice 公告信息
	 */
	void deleteInstNotice(InstNotice instNotice,OperType operType,IafConsoleSession session, String ipaddr);
}
