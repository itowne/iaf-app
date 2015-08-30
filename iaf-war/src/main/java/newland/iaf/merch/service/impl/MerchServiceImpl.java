package newland.iaf.merch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.MerchFieldSurvy;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.dict.CreditType;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.MerryType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.OperLogService;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.merch.dao.MerchDao;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.condition.MerchCondition;
import newland.iaf.merch.service.MerchService;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商户信息service服务接口
 * 
 * @author wwa
 * 
 */
@Service("com.newland.iaf.merchService")
@Transactional
public class MerchServiceImpl implements MerchService {

	@Resource(name = "com.newland.iaf.merchDao")
	private MerchDao merchDao;

	@Resource(name = "operLogService")
	private OperLogService operLogService;

	@Override
	public List<Merch> queryMerch() {
		return this.merchDao.queryMerch();
	}

	@Override
	public void saveMerch(Merch merch) {

		this.merchDao.saveMerch(merch);

	}

	@Override
	public void updateMerch(Merch merch) {
		this.merchDao.updateMerch(merch);
	}

	@Override
	public Merch findMerchById(Long id) {
		return this.merchDao.finMerchById(id);
	}

	/**
	 * 多条件查询商户列表
	 * 
	 * @return
	 */
	public List<Merch> queryMerchByCon(MerchCondition merchCondition, Page page)
			throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("imerch"));
		merchCondition.setOrders(orderList);
		return this.merchDao.queryMerchByCon(merchCondition, page);
	}

	@Override
	public MerchBusiInfo getMerchBusiInfoByImerch(Long imerch) {
		return this.merchDao.getMerchBusiInfoByImerch(imerch);
	}

	@Override
	public MerchLegalPers getMerchLegalPersByImerch(Long imerch) {
		return this.merchDao.getMerchLegalPersByImerch(imerch);
	}

	@Override
	public void updateMerchInfoAll(Merch merch, MerchLegalPers merchLegalPers,
			MerchBusiInfo merchBusiInfo, IafConsoleSession ics, String addrStr)
			throws Exception {

		// 日志
		String memo = "修改商户";
		this.operLogService.iafLog(ics, addrStr, memo, OperType.MODIFY_MERCH, OperStat.SUCCESS);

		this.merchDao.updateMerch(merch);
		if (merchLegalPers != null
				&& merchLegalPers.getiMerchLegalPers() != null) {
			this.merchDao.updateMerchLegalPers(merchLegalPers);
		} else if (merchBusiInfo != null) {
			this.merchDao.saveMerchLegalPers(merchLegalPers);
		}
		if (merchBusiInfo != null && merchBusiInfo.getiMerchBusiInfo() != null) {
			this.merchDao.updateMerchBusiInfo(merchBusiInfo);
		} else if (merchBusiInfo != null) {
			this.merchDao.saveMerchBusiInfo(merchBusiInfo);
		}
	}

	public Merch getMerch(String merchName) {
		return this.merchDao.getMerch(merchName);
	}

	@Override
	public MerchFieldSurvy getMerchFieldSurvyByImerch(Long iMerch) {
		return this.merchDao.getMerchFieldSurvyByImerch(iMerch);
	}

	@Override
	public List<HcInspectLog> queryHcInspectLogByMerchNo(String merchNo) {
		return this.merchDao.queryHcInspectLogByMerchNo(merchNo);
	}

	/**
	 * 保存现场调查信息
	 * 
	 * @param merchFieldSurvy
	 * @throws Exception
	 */
	public void saveMerchFieldSurvy(MerchFieldSurvy merchFieldSurvy,IafConsoleSession ics, String addrStr)
			throws Exception {
		// 日志
		String memo = "保存商户现场调查";
		this.operLogService.iafLog(ics, addrStr, memo, OperType.ADD_MERCHFIELDSURVY, OperStat.SUCCESS);
		
		this.merchDao.saveMerchFieldSurvy(merchFieldSurvy);
	}

	/**
	 * 更新现场调查信息
	 * 
	 * @param merchFieldSurvy
	 * @throws Exception
	 */
	public void updateMerchFieldSurvy(MerchFieldSurvy merchFieldSurvy,IafConsoleSession ics, String addrStr)
			throws Exception {
		String memo = "添加商户现场调查";
		this.operLogService.iafLog(ics, addrStr, memo, OperType.ADD_MERCHFIELDSURVY, OperStat.SUCCESS);
		
		this.merchDao.updateMerchFieldSurvy(merchFieldSurvy);
	}

	@Override
	public void updateMerchMerchLegalPers(MerchLegalPers merchLegalPers) {
		this.merchDao.updateMerchLegalPers(merchLegalPers);
	}

	@Override
	public void saveMerchBusiInfo(MerchBusiInfo merchBusiInfo) {
		this.merchDao.saveMerchBusiInfo(merchBusiInfo);
	}

	@Override
	public void updateMerchMerchBusiInfo(MerchBusiInfo merchBusiInfo) {
		this.merchDao.updateMerchBusiInfo(merchBusiInfo);
	}

	@Override
	public void saveMerchLegalPers(MerchLegalPers merchLegalPers) {
		this.merchDao.saveMerchLegalPers(merchLegalPers);
	}
	
	@Override
	public void updateCredit(Merch merch,MerchLegalPers merchLegalPers,MerchFieldSurvy merchFieldSurvy){
		
		//条件一：营业执照                 --> 商户基本资料中的数据；
		//条件二：已婚                     --〉汇融易系统上录入的数据；
		//条件三：营业用地性质：自有/租用  --〉汇融易系统上录入的调查资料数据；
		//条件四：员工人数：10人以上       --〉汇融易系统上录入的调查资料数据；
		//商户只分三个等级：A级、B级、C级
		//A级：全部满足上述4个条件；
		//B级：满足以上任意3个条件；
		//C级：其余情况；
		int num = 0;
		if(StringUtils.isNotBlank(merch.getBusinlic())){
			num++;
		}
		if(MerryType.MERRYED.equals(merchLegalPers.getMaritalStatus())){
			num++;
		}
		if(merchFieldSurvy!=null){
			if(merchFieldSurvy.getManageFieldNature()!=null){
				num++;
			}
			if(merchFieldSurvy.getManageScale()!=null){
				if(merchFieldSurvy.getManageScale()>10){
					num++;
				}
			}
		}
		
		if(num==4){
			merch.setCredit(CreditType.A);
		}else if(num==3){
			merch.setCredit(CreditType.B);
		}else{
			merch.setCredit(CreditType.C);
		}
		this.updateMerch(merch);
	}

	@Override
	public Merch queryByMerchNo(String merchNo) {
		// TODO Auto-generated method stub
		return merchDao.getMerchByMerchNo(merchNo);
	}

	@Override
	public MerchUser queryUserByImerch(Long imerch) {
		return merchDao.queryUserByImerch(imerch);
	}

	@Override
	public List<Object> queryTenDays(Date d, Date tenD, Long imerch) {
		return merchDao.queryTenDays(d, tenD, imerch);
	}

	@Override
	public List<LoanOrdPlan> queryIloanOrd(Date d, Date tenD, Long imerch) {
		return merchDao.queryIloanOrd(d, tenD, imerch);
	}
}
