package newland.iaf.inst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.condition.DebitBidCondition;
import newland.iaf.base.service.DebitBidService;
import newland.iaf.inst.dao.InstCollDao;
import newland.iaf.inst.model.InstCollMerch;
import newland.iaf.inst.model.InstCollOrd;
import newland.iaf.inst.service.InstCollService;
import newland.iaf.merch.dao.MerchDao;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.condition.MerchCondition;

import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 机构关注订单操作服务Service接口
 * 
 * @author lindaqun
 * 
 */
@Service("com.newland.iaf.instCollService")
@Transactional
public class InstCollServiceImpl implements InstCollService {

	/** 关注订单操作DAO接口 **/
	@Resource(name = "com.newland.iaf.instCollDao")
	private InstCollDao instCollDao;

	@Resource(name = "com.newland.iaf.merchDao")
	private MerchDao merchDao;
	
	@Resource(name = "debitBidService")
	private DebitBidService debitBidService;
	
	@Override
	public void addInstCollOrd(InstCollOrd instcollord) throws Exception {
		// 根据需要设置对应的信息，

		// 最后保存关注订单信息
		this.instCollDao.saveInstCollOrd(instcollord);
	}

	@Override
	public List<DebitBid> queryInstCollOrdByCon(Long iInst,DebitBidCondition debitBidCondition,Page page) throws Exception{
		List<InstCollOrd> instCollOrdList = this.instCollDao.queryCollOrdByIInst(iInst);
		List<Long> t = new ArrayList<Long>();
		for(InstCollOrd instCollOrd:instCollOrdList){
			t.add(instCollOrd.getIdebitBid());
		}
		if(t.size()==0){
			return new ArrayList<DebitBid>();
		}
		debitBidCondition.setIdebitBidList(t);
		return this.debitBidService.queryDebitBidByCon(debitBidCondition, page);
	}

	@Override
	public void deleteInstCollOrd(InstCollOrd instcollord) throws Exception {
		this.instCollDao.deleteInstCollOrd(instcollord);
	}

	@Override
	public void addInstCollMerch(InstCollMerch instcollmerch) throws Exception {
		// 根据需要设置对应的信息，

		// 最后保存关注商户信息
		instcollmerch.setGenTime(new Date());
		this.instCollDao.saveInstCollMerch(instcollmerch);
	}

	@Override
	public List<Merch> queryInstCollMerchByCon(Long iinst,MerchCondition merchCondition,Page page) throws Exception {
	// 根据具体的条件实现分页查询功能
		List<InstCollMerch> instCollMerchList = this.instCollDao.queryCollMerchByIinst(iinst);
		List<Long> t = new ArrayList<Long>();
		for(InstCollMerch instCollMerch:instCollMerchList){
			t.add(instCollMerch.getiMerch());
		}
		if(t.size()==0){
			return new ArrayList<Merch>();
		}
		merchCondition.setImerch(t);
		return this.merchDao.queryMerchByCon(merchCondition, page);
	}

	@Override
	public void deleteInstCollMerch(Long iInstCollMerch)
			throws Exception {
		InstCollMerch instcollmerch = this.instCollDao.getInstCollMerch(iInstCollMerch);
		this.instCollDao.deleteInstCollMerch(instcollmerch );
	}

	@Override
	public InstCollMerch queryByIinstAndImerch(Long iInst, Long iMerch) {
		return instCollDao.queryByIinstAndImerch(iInst, iMerch);
	}

	@Override
	public InstCollOrd queryInstCollOrdByIinstAndImerchAndIdebitBid(Long iInst, Long iMerch, Long idebitBid) {
		return this.instCollDao.queryInstCollOrdByIinstAndImerchAndIdebitBid(iInst, iMerch, idebitBid);
	}
}
