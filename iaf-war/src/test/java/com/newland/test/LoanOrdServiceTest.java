package com.newland.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import junit.framework.Assert;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.OrdOperLog;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.model.dict.OrdStat;
import newland.iaf.base.model.dict.PdtType;
import newland.iaf.base.service.LoanOrdService;

import org.junit.Test;

import com.newland.BeanTest;


public class LoanOrdServiceTest extends BeanTest {
	@Resource (name = "loanOrdService")
	private LoanOrdService loanOrdService;
	
	private LoanOrd ord;
	//@Test
	public LoanOrd testApply() throws Exception{
		newOrd();
		return ord;
	}

	private void newOrd() throws Exception {
		ord = new LoanOrd();
		ord.setCurtPayment(new BigDecimal(0));
		ord.setExpiryDate(new Date());
		ord.setGenTime(new Date());
		ord.setIinst(new Long(0));
		ord.setIloanPdt(new Long(0));
		ord.setImerch(new Long(0));
		ord.setOrdDate(new Date());
		ord.setOrdStat(OrdStat.APPLY);
		ord.setPdtType(PdtType.INST_PROD);
		ord.setQuota(new BigDecimal(10000));
		ord.setRate(new BigDecimal(0.1));
		ord.setRecivePeriod(0);
		ord.setRemainPayment(new BigDecimal(1000));
		ord.setRemainPeriod(9);
		ord.setRepayment(new BigDecimal(9000));
		ord.setTerm(12);
		ord.setUpdTime(new Date());
		ord.setLoanPdtId("P0000001");
		ord.setVersion(new Long(0));
		ord.setGenTime(new Date());
		OrdOperLog log = new OrdOperLog();
		log.setGenTime(new Date());
		log.setIinst(new Long(1));
		log.setIloanPdt(new Long(1));
		log.setInstType(InstType.MERCH);
		log.setOperType(OperType.APPLY);
		log.setPdtType(PdtType.INST_PROD);
		log.setIuser(new Long(1));
		log.setUserName("test");
		log.setLoginName("test");
		log.setIroles("");
		log.setTerm(10);
		log.setYearRate(new BigDecimal(0.1));
		log.setAmount(new BigDecimal(10000));
		log.setOperResult("");
		log.setIloanOrd(new Long(0));
		log.setLoanOrdId("P0000001");
		log.setIloanPdt(new Long(1));
		ord = loanOrdService.applyOrdBy(ord);
	}
	//@Test
	public void testAccept() throws Throwable{
		newOrd();
		OrdOperLog log = new OrdOperLog();
		log.setGenTime(new Date());
		log.setIinst(new Long(1));
		log.setIloanPdt(new Long(1));
		log.setInstType(InstType.MERCH);
		log.setOperType(OperType.APPLY);
		log.setPdtType(PdtType.INST_PROD);
		log.setIuser(new Long(1));
		log.setUserName("test");
		log.setLoginName("test");
		log.setIroles("");
		log.setTerm(10);
		log.setYearRate(new BigDecimal(0.1));
		log.setAmount(new BigDecimal(10000));
		log.setIloanOrd(new Long(0));
		log.setLoanOrdId("P0000001");
		log.setIloanPdt(new Long(1));
		this.loanOrdService.acceptOrd(ord);
		Assert.assertEquals(ord.getOrdStat(), OrdStat.ACCEPT);
	}
	//@Test
	public void testRefuseForApply_merch() throws Throwable{
		newOrd();
		OrdOperLog log = new OrdOperLog();
		log.setGenTime(new Date());
		log.setIinst(new Long(1));
		log.setIloanPdt(new Long(1));
		log.setInstType(InstType.MERCH);
		log.setOperType(OperType.APPLY);
		log.setPdtType(PdtType.INST_PROD);
		log.setIuser(new Long(1));
		log.setUserName("test");
		log.setLoginName("test");
		log.setIroles("");
		log.setTerm(10);
		log.setYearRate(new BigDecimal(0.1));
		log.setAmount(new BigDecimal(10000));
		log.setIloanOrd(new Long(0));
		log.setLoanOrdId("P0000001");
		log.setIloanPdt(new Long(1));
		this.loanOrdService.merchCancel(ord);
		Assert.assertEquals(ord.getOrdStat(), OrdStat.CANCEL);
		
	}
	@Test
	public void testRefund() throws Exception{
		this.loanOrdService.refundOrd(new LoanOrd(), new LoanOrdPlan() );
	}
	@Test
	public void testRefuseForAccept_merch() throws Throwable{
		testAccept();
		OrdOperLog log = new OrdOperLog();
		log.setGenTime(new Date());		log.setIinst(new Long(1));
		log.setIloanPdt(new Long(1));
		log.setInstType(InstType.MERCH);
		log.setOperType(OperType.APPLY);
		log.setPdtType(PdtType.INST_PROD);
		log.setIuser(new Long(1));
		log.setUserName("test");
		log.setLoginName("test");
		log.setIroles("");
		log.setTerm(10);
		log.setYearRate(new BigDecimal(0.1));
		log.setAmount(new BigDecimal(10000));
		log.setIloanOrd(new Long(0));
		log.setLoanOrdId("P0000001");
		log.setIloanPdt(new Long(1));
		this.loanOrdService.merchCancel(ord);
		Assert.assertEquals(ord.getOrdStat(), OrdStat.CANCEL);
	}

}
