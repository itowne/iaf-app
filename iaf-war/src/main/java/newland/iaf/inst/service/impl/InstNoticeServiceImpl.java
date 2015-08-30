package newland.iaf.inst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.inst.dao.InstNoticeDao;
import newland.iaf.inst.model.InstNotice;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.condition.InstNoticeCondition;
import newland.iaf.inst.service.InstNoticeService;
import newland.iaf.inst.service.impl.InstUserServiceImpl.NameType;
import newland.iaf.notice.annotation.MethodTrigger;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 公告Service操作服务
 * 
 * @author lxf
 * 
 */
@Service("instNoticeService")
@Transactional
public class InstNoticeServiceImpl implements InstNoticeService {

	/** 公告DAO接口 **/
	@Resource(name = "com.newland.iaf.instNoticeDao")
	private InstNoticeDao instNoticeDao;
	
	@Resource(name = "operLogService")
	private OperLogService operLogService;

	/**
	 * 多条件查询公告
	 */
	@Override
	@Transactional
//	@Cacheable(value="iafcache", key = "#instNoticeCondition")
	public List<InstNotice> queryInstNoticeByCon(
			InstNoticeCondition instNoticeCondition, Page page) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iinstNotice"));
		instNoticeCondition.setOrders(orderList);
		return instNoticeDao.querytInstNoticeByCon(instNoticeCondition, page);
	}
	
	/**
	 * 保存公告信息
	 * @param instNotice 公告信息
	 */
	public void saveInstNotify(InstNotice instNotice,OperType operType,IafConsoleSession session, String ipaddr) {
		this.instNoticeDao.saveInstNotify(instNotice);
		// 日志
		String memo = operType.toString();
		this.operLogService.iafLog(session, ipaddr, memo, operType, OperStat.SUCCESS);
	}
	
	/**
	 * 更新公告信息
	 * @param instNotice 公告信息
	 */
	public void updateInstNotice(InstNotice instNotice,OperType operType,IafConsoleSession session, String ipaddr){
		this.instNoticeDao.updateInstNotice(instNotice);
		// 日志
		String memo = operType.toString();
		this.operLogService.iafLog(session, ipaddr, memo, operType, OperStat.SUCCESS);
	}
	
	/**
	 * 删除公告信息
	 * @param instNotice 公告信息
	 */
	public void deleteInstNotice(InstNotice instNotice,OperType operType,IafConsoleSession session, String ipaddr){
		this.instNoticeDao.deleteInstNotice(instNotice);
		// 日志
		String memo = operType.toString();
		this.operLogService.iafLog(session, ipaddr, memo, operType, OperStat.SUCCESS);
	}
	
}
