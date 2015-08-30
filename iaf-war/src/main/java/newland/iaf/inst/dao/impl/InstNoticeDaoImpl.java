/**
 * 
 */
package newland.iaf.inst.dao.impl;

import java.util.List;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Repository;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.inst.dao.InstNoticeDao;
import newland.iaf.inst.model.InstNotice;
import newland.iaf.inst.model.condition.InstNoticeCondition;


/**
 * 公告DAO接口服务实现
 * 
 * @author lxf
 * 
 */
@Repository("com.newland.iaf.instNoticeDao")
public class InstNoticeDaoImpl extends BaseDao implements InstNoticeDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<InstNotice> querytInstNoticeByCon(InstNoticeCondition instNoticeCondition, Page page) {
		List<InstNotice> instNoticeList = this.getHibernateDao().findByCriteriaEx(
				instNoticeCondition, page, true);
		return instNoticeList;
	}
	
	/**
	 * 保存公告信息
	 * @param instNotice 公告信息
	 */
	public void saveInstNotify(InstNotice instNotice) {
		this.getSession().save(instNotice);
	}
	
	/**
	 * 更新公告信息
	 * @param instNotice 公告信息
	 */
	public void updateInstNotice(InstNotice instNotice){
		this.getSession().update(instNotice);
	}
	
	/**
	 * 删除公告信息
	 * @param instNotice 公告信息
	 */
	public void deleteInstNotice(InstNotice instNotice){
		this.getSession().delete(instNotice);
	}
}
