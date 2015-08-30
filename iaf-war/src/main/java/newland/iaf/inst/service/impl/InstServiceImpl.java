package newland.iaf.inst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.inst.dao.InstDao;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstBusiInfo;
import newland.iaf.inst.model.InstLegalPers;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.model.condition.InstCondition;
import newland.iaf.inst.service.InstService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.instService")
@Transactional
public class InstServiceImpl implements InstService {
	@Resource(name = "com.newland.iaf.instDao")
	private InstDao instDao;

	@Resource(name = "operLogService")
	private OperLogService operLogService;

	@Override
	public List<Inst> queryInst(){
		return this.instDao.queryInst();
	}

	@Override
	public void saveInst(Inst inst)
			throws Exception {
		this.instDao.saveInst(inst);
	}

	@Override
	public void updateInst(Inst inst,IafConsoleSession ics, String ipaddr)
			throws Exception {
		// 日志
		if(ics!=null&&ipaddr!=null){
			this.operLogService.iafLog(ics, ipaddr, "禁用机构", OperType.UNUSED_INST, OperStat.SUCCESS);
		}
		this.instDao.updateInst(inst);
	}

	@Override
	public Inst getInstById(Long iinst) throws Exception {
		return this.instDao.getInst(iinst);
	}

	@Override
	public void saveInstBusiInfo(InstBusiInfo instbusiinfo) throws Exception {

		this.instDao.saveInstBusiInfo(instbusiinfo);
	}

	@Override
	public void updateInstBusiInfo(InstBusiInfo instbusiinfo) throws Exception {

		this.instDao.updateInstBusiInfo(instbusiinfo);
	}

	@Override
	public InstBusiInfo findInstBusiInfoByiinst(Long iinst) throws Exception {
		return this.instDao.getInstBusiInfoByIinst(iinst);
	}
	
	@Override
	public List<Long> findInstBusiInfoByAcceptRegion(String acceptRegion) throws Exception{
		return this.instDao.getInstBusiInfoByAcceptRegion(acceptRegion);
	}

	// ----------------------
	@Override
	public void saveInstLegalPers(InstLegalPers instlegalpers) throws Exception {

		this.instDao.saveInstLegalPers(instlegalpers);
	}

	@Override
	public void updateInstLegalPers(InstLegalPers instlegalpers) throws Exception {
		
		this.instDao.updateInstLegalPers(instlegalpers);
	}

	@Override
	public InstLegalPers findInstLegalPersById(Long iInstLegalPers)
			throws Exception {
		return this.instDao.getInstLegalPersIinst(iInstLegalPers);
	}

	@Override
	public Inst findInstById(Long iinst) {
		return this.instDao.getInst(iinst);
	}

	/**
	 * 多条件查询机构列表 机构名称，注册资金
	 * 
	 * @return
	 */
	public List<Inst> queryInstByCon(InstCondition instCondition, Page page)
			throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iinst"));
		instCondition.setOrders(orderList);
		return this.instDao.queryInstByCon(instCondition, page);
	}

	/**
	 * 一次性保存所有的信息，在一个事务内。
	 * 
	 * @param inst
	 * @param instBusiInfo
	 * @param instLegalPers
	 * @throws Exception
	 */
	public void saveInstInfoAll(Inst inst, InstBusiInfo instBusiInfo,
			InstLegalPers instLegalPers,IafConsoleSession ics, String ipaddr)
			throws Exception {
			
		// 日志
		this.operLogService.iafLog(ics, ipaddr, "添加机构", OperType.ADD_INST, OperStat.SUCCESS);
		
		inst.setUpdTime(new Date());
		inst.setGenTime(new Date());
		this.instDao.saveInst(inst);
		instBusiInfo.setiInst(inst.getIinst());
		instLegalPers.setiInst(inst.getIinst());
		instLegalPers.setUpdTime(new Date());
		instLegalPers.setGenTime(new Date());
		this.instDao.saveInstBusiInfo(instBusiInfo);
		this.instDao.saveInstLegalPers(instLegalPers);
	}

	/**
	 * 一次性更新所有的信息，在一个事务内。
	 * 
	 * @param inst
	 * @param instBusiInfo
	 * @param instLegalPers
	 * @throws Exception
	 */
	public void updateInstInfoAll(Inst inst, InstBusiInfo instBusiInfo,
			InstLegalPers instLegalPers, IafConsoleSession ics, String ipaddr)
			throws Exception {
		
		// 日志
		this.operLogService.iafLog(ics, ipaddr, "修改机构", OperType.MODIFY_INST, OperStat.SUCCESS);
		
		inst.setUpdTime(new Date());
		this.instDao.updateInst(inst);
		instLegalPers.setUpdTime(new Date());
		this.instDao.updateInstBusiInfo(instBusiInfo);
		this.instDao.updateInstLegalPers(instLegalPers);
	}

	@Override
	public InstLegalPers getInstLegalPersByIinst(Long iinst) throws Exception {
		return this.instDao.getInstLegalPersByIinst(iinst);
	}

	@Override
	public List<Inst> getInstList(List<LoanOrd> iInst) throws Exception {
		return instDao.getInstList(iInst);
	}

	@Override
	public Inst queryByInstId(String instId) {
		return instDao.queryByInstId(instId);
	}

	@Override
	public InstUser queryUserByIinst(Long iinst) {
		return instDao.queryUserByIinst(iinst);
	}

}
