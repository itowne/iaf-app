package com.newland.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;
import newland.iaf.base.model.dict.DebitbidStat;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.OrdOperLog;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.service.MerchLoanService;
import newland.iaf.utils.service.SerialNoService;

import org.junit.Test;

import com.newland.BeanTest;


public class MerchLoanServiceTest extends BeanTest {
	@Resource (name = "merchLoanService")
	private MerchLoanService merchLoanService;
	@Resource (name = "serialNoService")
	private SerialNoService serialNoService;
	
	private DebitBid bid;
	
	private MerchUser user;
	
	private MerchSession session;
	
	private void init(){
		user = new MerchUser();
		user.setGenTime(new Date());
		user.setImerch(new Long(0));
		user.setLastLoginTime(new Date());
		user.setLoginName("test");
		user.setUpdTime(new Date());
		user.setUserName("testtest");
		user.setImerchUser(new Long(1));
		bid = new DebitBid();
		bid.setBidStat(DebitbidStat.NORMAL);
		bid.setExpireDate(new Date());
		bid.setGenTime(new Date());
		//bid.setiMerch(user.getImerch());
		bid.setPurpose("tsettseteserser");
		bid.setiMerchUser(user.getImerchUser());
		bid.setQuota(new BigDecimal(10000));
		bid.setTerm(10);
		bid.setUpdTime(new Date());
		bid.setYearRate(new BigDecimal(0.1));
		session = new MerchSession();
		Merch merch = new Merch();
		merch.setImerch(new Long(2));
		session.setMerch(merch);
		session.setMerchUser(user);
		pdt = new LoanPdt();
		pdt.setIinst(new Long(1));
		pdt.setCreditTerm(10);
		pdt.setGenTime(new Date());
		pdt.setIloanPdt(new Long(1));
		pdt.setLoadPdtId("P0000001");
		pdt.setMaxQuota(new BigDecimal(20000));
		pdt.setMinQuota(new BigDecimal(10000));
		pdt.setPdtName("dfisjijdf");
		pdt.setRate(new BigDecimal(0.1));
		pdt.setUpdTime(new Date());
		pdt.setMaxTerm(20);
		pdt.setMinTerm(10);
		
	}
	
	private void init1(){
		plan = new LoanOrdPlan();
		plan.setGenTime(new Date());
		plan.setIloanOrd(ord.getIloanOrd());
		plan.setIloanOrdPlan(new Long(1));
		plan.setLoanOrdId(ord.getLoanOrdId());
		plan.setPeriod(2);
		plan.setRefundDate(new Date());
		plan.setRepayment(new BigDecimal(2000));
		plan.setStat(PlanStat.BALANCE);
		plan.setUpdTime(new Date());
	}
	
	@Test
	public void testAddDebitBid() throws Exception{ 
		this.init();
		this.merchLoanService.addDebitBid(bid, session, "djiiijijsdf", "127.0.0.1");
	}
	@Test
	public void testUpdateDebitBid() throws Exception{
		this.init();
		this.merchLoanService.addDebitBid(bid, session, "djiiijijsdf", "127.0.0.1");
		this.merchLoanService.updateDebitBid(bid, session, "dfsksdfk", "127.0.0.1");
	}
	@Test
	public void testGrounging()throws Exception{
		this.init();
		this.merchLoanService.addDebitBid(bid, session, "djiiijijsdf", "127.0.0.1");
		this.merchLoanService.grounding(bid, session, "dfsksdfk", "127.0.0.1");
		Assert.assertEquals(bid.getBidStat(), PdtStat.GROUNDING);
	}
	@Test
	public void testUndercarriage() throws Exception{
		this.init();
		this.merchLoanService.addDebitBid(bid, session, "djiiijijsdf", "127.0.0.1");
		this.merchLoanService.undercarriage(bid, session, "dfsksdfk", "127.0.0.1");
		Assert.assertEquals(bid.getBidStat(), PdtStat.UNDERCARRIAGE);
	}
	@Test
	public void testApplyDebitBid() throws Exception{
		this.init();
		this.merchLoanService.addDebitBid(bid, session, "djiiijijsdf", "127.0.0.1");
		Set<Long> set = new HashSet<Long>();
		set.add(new Long(1));
		set.add(new Long(2));
		set.add(new Long(3));
		OrdOperLog log = new OrdOperLog();
		log.setMemo("");
		log.setIpAddr("");
		this.merchLoanService.applyDebitBid(bid,set, session, "", null);
	}
	
	LoanPdt pdt ;
	@Test
	public void testApplyInstPdt() throws Exception{
		this.init();
		this.merchLoanService.addDebitBid(bid, session, "djiiijijsdf", "127.0.0.1");
		Set<Long> set = new HashSet<Long>();
		set.add(new Long(1));
		set.add(new Long(2));
		set.add(new Long(3));
		OrdOperLog log = new OrdOperLog();
		log.setMemo("");
		log.setIpAddr("");
		//ord = this.merchLoanService.applyLoanPtd(pdt, new BigDecimal(15000), 10, session, "", null);
		LoanOrd lo = this.merchLoanService.queryOrdById(ord.getIloanOrd());
		Assert.assertNotNull(lo);
		this.init1();
	}
	
	LoanOrd ord ;
	LoanOrdPlan plan;
	@Test
	public void testCancelLoanOrd() throws Exception{
		this.testApplyInstPdt();
		OrdOperLog log = new OrdOperLog();
		log.setMemo("");
		log.setIpAddr("");
		this.merchLoanService.cancelLoanOrd(ord, session, "", null);
	}
	
	@Test
	public void testRefundLonaOrd() throws Exception{

		this.merchLoanService.refund(ord, plan, null, session, "", null);
	}

}
