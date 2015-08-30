package newland.iaf.base.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.dao.LoanPdtDao;
import newland.iaf.base.dao.LoanPdtHisDao;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.LoanPdtHis;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperStat;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.LoanPdtService;
import newland.iaf.base.service.OperLogService;
import newland.iaf.utils.service.SerialNoService;

import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Service("loanPdtService")
public class LoanPdtServiceImpl implements LoanPdtService {

	@Resource(name = "com.newland.iaf.loanPdtDao")
	private LoanPdtDao loanPdtDao;

	@Resource(name = "com.newland.iaf.loanPdtHisDao")
	private LoanPdtHisDao loanPdtHisDao;

	@Resource(name = "serialNoService")
	private SerialNoService serialNoService;

	@Resource (name = "operLogService")
	private OperLogService operLogService;
	
	@Override
	@Transactional
	//@MethodTrigger(stratgy="curStatistics")
	//@CacheEvict(value = "iafcache", key = "'loanPdtCount'")
	public void addLoanPdtService(LoanPdt loanPdt) throws Exception {
		Date t = new Date();
		loanPdt.setGenTime(t);
		loanPdt.setUpdTime(t);
		loanPdt.setLoadPdtId(serialNoService.genInstProdNo());
		loanPdt.setIloanPdtHis(-1L);
		loanPdtDao.saveLoanPdt(loanPdt);

		LoanPdtHis h = pdt2His(loanPdt);
		h.setIloanPdt(loanPdt.getIloanPdt());
		loanPdtHisDao.saveLoanPdtHis(h);

		loanPdt.setIloanPdtHis(h.getIloanPdtHis());
		loanPdtDao.updateLoanPdt(loanPdt);
	}

	@Override
	@Transactional
	public void updateLoanPdt(LoanPdt loanPdt) throws Exception {
		Date t = new Date();
		loanPdt.setUpdTime(t);
		loanPdtDao.updateLoanPdt(loanPdt);

		LoanPdtHis h = pdt2His(loanPdt);
		h.setIloanPdt(loanPdt.getIloanPdt());
		loanPdtHisDao.saveLoanPdtHis(h);

		loanPdt.setIloanPdtHis(h.getIloanPdtHis());
		loanPdtDao.updateLoanPdt(loanPdt);
	}

	private LoanPdtHis pdt2His(LoanPdt p) {
		LoanPdtHis h = new LoanPdtHis();
		h.setIloanPdt(p.getIloanPdt());
		h.setIinst(p.getIinst());
		h.setCl(p.getCl());
		h.setCondition(p.getCondition());
		h.setFeature(p.getFeature());
		h.setGenTime(p.getGenTime());
		h.setMaxQuota(p.getMaxQuota());
		h.setMinQuota(p.getMinQuota());
		h.setMaxTerm(p.getMaxTerm());
		h.setMinTerm(p.getMinTerm());
		h.setPdtName(p.getPdtName());
		h.setCreditTerm(p.getCreditTerm());
		h.setRate(p.getRate());
		h.setRegion(p.getRegion());
		h.setLoadPdtId(p.getLoadPdtId());
		h.setRepayment(p.getRepayment());
		h.setPdtStatus(p.getPdtStatus());
		h.setUpdTime(p.getUpdTime());
		h.setArea(p.getArea());
		h.setRateType(p.getRateType());;
		h.setMinTermType(p.getMinTermType());
		h.setMaxTermType(p.getMaxTermType());
		h.setProvinceCode(p.getProvinceCode());
		return h;
	}

	@Override
	@Transactional
	public LoanPdt getLoanPdtById(Long iloanPdt) throws Exception {
		return this.loanPdtDao.getLoanPdtById(iloanPdt);
	}
	
	/**
	 * 根据id查询贷款产品
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public LoanPdtHis getLoanPdtHisById(Long iloanPdtHis) throws Exception{
		return this.loanPdtHisDao.getLoanPdtHis(iloanPdtHis);
	}

	@Override
	@Transactional
	public List<LoanPdt> queryLoanPdtByCon(LoanPdtCondition loanPdtCondition,
			Page page) throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("updTime"));
		loanPdtCondition.setOrders(orderList);
		List<LoanPdt> loanPdtList = this.loanPdtDao.queryLoanPdtByCon(
				loanPdtCondition, page);
		return loanPdtList;
	}

	/**
	 * 删除借款产品信息
	 * 
	 * @param loanPdt
	 *            借款产品信息
	 * @throws Exception
	 */
	@Transactional
	public void deleteLoanPdt(LoanPdt loanPdt) throws Exception {
		this.loanPdtDao.deleteLoanPdt(loanPdt);
	}
	
	/**
	 * 同步方法，更新产品的请求次数
	 * @param loanPdt
	 * @throws Exception
	 */
	public synchronized void updateLoanPdtReqTotal(LoanPdt loanPdt) throws Exception{
		loanPdt.setReqTotal(loanPdt.getReqTotal() == null ?new Long(1):(loanPdt.getReqTotal()+1));
		this.loanPdtDao.updateLoanPdt(loanPdt);
	}

	@Override
	public BigInteger countPdt(Long iinst) {
		return this.loanPdtDao.countLoanPdtByIinst(iinst);
	}

	@Override
	public BigInteger countPdt() {
		return this.loanPdtDao.countLoanPdt();
	}
	@Override
	public BigInteger countGroundingPdt() {
		return this.loanPdtDao.countGroundingLoanPdt();
	}
	/**
	 * 增加贷款产品(后台)
	 * 
	 * @param loanPdt
	 */
	@Transactional
	public void addLoanPdtServiceBackstage(LoanPdt loanPdt,Long iinst,IafConsoleSession session, String ipaddr) throws Exception {
		Date t = new Date();
		loanPdt.setGenTime(t);
		loanPdt.setUpdTime(t);
		loanPdt.setLoadPdtId(serialNoService.genInstProdNo());
		loanPdt.setIloanPdtHis(-1L);
		loanPdtDao.saveLoanPdt(loanPdt);

		LoanPdtHis h = pdt2His(loanPdt);
		h.setIloanPdt(loanPdt.getIloanPdt());
		loanPdtHisDao.saveLoanPdtHis(h);

		loanPdt.setIloanPdtHis(h.getIloanPdtHis());
		loanPdtDao.updateLoanPdt(loanPdt);
		
		// 日志
		String memo = "添加产品成功";
		this.operLogService.iafLog(session, ipaddr, memo, OperType.ADD_PDT, OperStat.SUCCESS);

	}

	/**
	 * 修改贷款产品（后台）
	 * 
	 * @param loanPdt
	 */
	@Transactional
	public void updateLoanPdtBackstage(LoanPdt loanPdt,Long iinst,IafConsoleSession session,String ipaddr,OperType operType) throws Exception{
		Date t = new Date();
		loanPdt.setUpdTime(t);
		loanPdtDao.updateLoanPdt(loanPdt);

		LoanPdtHis h = pdt2His(loanPdt);
		h.setIloanPdt(loanPdt.getIloanPdt());
		loanPdtHisDao.saveLoanPdtHis(h);

		loanPdt.setIloanPdtHis(h.getIloanPdtHis());
		loanPdtDao.updateLoanPdt(loanPdt);
		
		// 日志
		String memo = "修改产品成功";
		this.operLogService.iafLog(session, ipaddr, memo, OperType.ADD_PDT, OperStat.SUCCESS);
	}
	/**
	 * 删除借款产品信息（后台）
	 * @param loanPdt 借款产品信息
	 * @throws Exception
	 */
	@Transactional
	public void deleteLoanPdtBackstage (LoanPdt loanPdt,Long iinst,IafConsoleSession session, String ipaddr) throws Exception {
		this.loanPdtDao.deleteLoanPdt(loanPdt);
		
		// 日志
		String memo = "删除产品成功";
		this.operLogService.iafLog(session, ipaddr, memo, OperType.ADD_PDT, OperStat.SUCCESS);
	}

	@Override
	@Transactional
	public List<LoanPdt> queryAll() {
		// TODO Auto-generated method stub
		return this.loanPdtDao.queryAll();
	}

	@Override
	@Transactional
	public LoanPdt queryByLoanPdtId(String loanPdtId) {
		// TODO Auto-generated method stub
		return loanPdtDao.queryByLoanPdtId(loanPdtId);
	}
}
