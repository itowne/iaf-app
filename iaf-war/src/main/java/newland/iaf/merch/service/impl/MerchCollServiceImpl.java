package newland.iaf.merch.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.dao.LoanPdtDao;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.inst.dao.InstDao;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.condition.InstCondition;
import newland.iaf.merch.dao.MerchCollDao;
import newland.iaf.merch.model.MerchCollInst;
import newland.iaf.merch.model.MerchCollPdt;
import newland.iaf.merch.service.MerchCollService;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("merchCollService")
public class MerchCollServiceImpl implements MerchCollService {

	@Resource(name = "merchCollDao")
	private MerchCollDao merchCollDao;
	
	@Resource(name = "com.newland.iaf.loanPdtDao")
	private LoanPdtDao loanPdtDao;
	@Resource(name = "com.newland.iaf.instDao")
	private InstDao instDao;
	
	@Transactional
	@Override
	public void addMerchCollInst(MerchCollInst merchCollInst) throws Exception {
		this.merchCollDao.saveMerchCollInst(merchCollInst);
	}
	
	@Transactional
	@Override
	public void deleteMerchCollInst(Long iMerchCollInst) throws Exception {
		MerchCollInst merchCollInst = this.merchCollDao.getMerchCollInstById(iMerchCollInst);
		this.merchCollDao.deleteMerchCollInst(merchCollInst);
	}
	
	@Transactional
	@Override
	public List<Inst> queryMerchCollInstByCon(String acceptRegion,Long imerch,InstCondition instCondition,Page page) throws Exception {
		List<MerchCollInst> merchCollInstList = this.merchCollDao.queryMerchCollInstByImerch(imerch);
		List<Long> mciList = new ArrayList<Long>();
		for(MerchCollInst mci : merchCollInstList){
			mciList.add(mci.getiInst());
		}
		if(StringUtils.isBlank(acceptRegion)){
			if(mciList.size() != 0){
				instCondition.setIinst(mciList);
			}
		}else{
			List<Long> acceptRegionList = new ArrayList<Long>();
			List<Long> resultList = new ArrayList<Long>();
			acceptRegionList = this.instDao.getInstBusiInfoByAcceptRegion(acceptRegion+"%");
			if(mciList.size() !=0 && acceptRegionList.size() != 0){
				for(Long arl : acceptRegionList){
					for(Long mci : mciList){
						if(arl == mci){
							resultList.add(arl);
						}
					}
				}
			}
			if(resultList.size() == 0){
				List<Long> nullList = new ArrayList<Long>();
				nullList.add(new Long(1));
				instCondition.setIinst(nullList);
			}else{
				instCondition.setIinst(resultList);
			}
		}
		return this.instDao.queryInstByCon(instCondition, page);
	}

	@Override
	@Transactional
	public void addMerchCollPdt(MerchCollPdt merchCollPdt) throws Exception {
		this.merchCollDao.saveMerchCollPdt(merchCollPdt);
	}

	@Override
	@Transactional
	public void deleteMerchCollPdt(Long iMerchCollPdt) throws Exception {
		MerchCollPdt merchCollPdt = this.merchCollDao.getMerchCollPdtById(iMerchCollPdt);
		this.merchCollDao.deleteMerchCollPdt(merchCollPdt);
	}

	@Override
	@Transactional
	public List<LoanPdt> queryMerchCollPdtByCon(Long imerch,LoanPdtCondition loanPdtCondition,Page page) throws Exception {
		List<MerchCollPdt> merchCollPdtList = this.merchCollDao.queryMerchCollPdtByImerch(imerch);
		List<Long> t = new ArrayList<Long>();
		for(MerchCollPdt merchCollPdt:merchCollPdtList){
			t.add(merchCollPdt.getiLoanPdt());
		}
		
		if(t.size()==0){
			return new ArrayList<LoanPdt>();
		}
		
		loanPdtCondition.setIloanPdt(t);
		
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("iloanPdt"));
		loanPdtCondition.setOrders(orderList);
		
		return this.loanPdtDao.queryLoanPdtByCon(loanPdtCondition, page);
	}

	@Override
	@Transactional
	public MerchCollInst queryByIinstAndImerch(Long iInst, Long imerch) throws Exception {
		return merchCollDao.queryByIinstAndImerch(iInst, imerch);
	}

	@Override
	@Transactional
	public MerchCollPdt queryByIloanPdtAndImerch(Long iLoanPdt,Long imerch) throws Exception {
		return merchCollDao.queryByIloanPdtAndImerch(iLoanPdt, imerch);
	}

}
