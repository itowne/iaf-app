package com.newland.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;
import newland.iaf.base.model.DebitBid;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.OrdOperLog;
import newland.iaf.base.model.dict.DebitbidStat;
import newland.iaf.base.model.dict.InstStatusType;
import newland.iaf.base.model.dict.InstUserStatType;
import newland.iaf.base.model.dict.PdtStat;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.inst.model.Inst;
import newland.iaf.inst.model.InstRole;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.service.MerchLoanService;

import org.junit.Test;

import com.newland.BeanTest;


public class InstLoanServiceTest extends BeanTest {
	@Resource(name = "instLoanService")
	private InstLoanService instLoanService;
	@Resource (name = "merchLoanService")
	private MerchLoanService merchLoanService;
	
	InstUser user;
	
	Inst inst;
	
	LoanOrd ord;
	
	InstSession session;
	
	List<InstRole> roles;
	
	private void init() throws Exception{
		user = new InstUser();
		user.setGenTime(new Date());
		user.setIinst(new Long(1));
		user.setIinstUser(new Long(1));
		user.setLastLoginTime(new Date());
		user.setLoginName("test");
		user.setPasswd("");
		user.setUpdTime(new Date());
		user.setUserName("test");
		user.setUsrStat(InstUserStatType.USED);
		inst = new Inst();
		inst.setGenTime(new Date());
		inst.setIinst(new Long(1));
		inst.setInstId("P0000001");
		inst.setInstName("中国工商银行");
		inst.setInstStat(InstStatusType.USED);
		inst.setUpdTime(new Date());
		roles = new ArrayList<InstRole>();
		InstRole role = new InstRole();
		role.setGenTime(new Date());
		role.setiInst(new Long(1));
		role.setiInstRole(new Long(1));
		role.setRoleName("经办");
		role.setRoleStat(1);
		roles.add(role);
		role = new InstRole();
		role.setGenTime(new Date());
		role.setiInst(new Long(1));
		role.setiInstRole(new Long(2));
		role.setRoleName("经办");
		role.setRoleStat(1);
		roles.add(role);
		role = new InstRole();
		role.setGenTime(new Date());
		role.setiInst(new Long(1));
		role.setiInstRole(new Long(3));
		role.setRoleName("经办");
		role.setRoleStat(1);
		roles.add(role);
			
		session = new InstSession();
		session.setInst(inst);
		session.setInstUsr(user);
		session.setRoles(roles);
		this.testApplyInstPdt();
	}
	private DebitBid bid;
	
	private MerchUser muser;
	
	private MerchSession msession;
	LoanPdt pdt ;
	
	private void initMerch(){
		muser = new MerchUser();
		muser.setGenTime(new Date());
		muser.setImerch(new Long(0));
		muser.setLastLoginTime(new Date());
		muser.setLoginName("test");
		muser.setUpdTime(new Date());
		muser.setUserName("testtest");
		muser.setImerchUser(new Long(1));
		bid = new DebitBid();
		bid.setBidStat(DebitbidStat.NORMAL);
		bid.setExpireDate(new Date());
		bid.setGenTime(new Date());
		//bid.setiMerch(muser.getImerch());
		bid.setPurpose("tsettseteserser");
		bid.setiMerchUser(muser.getImerchUser());
		bid.setQuota(new BigDecimal(10000));
		bid.setTerm(10);
		bid.setUpdTime(new Date());
		bid.setYearRate(new BigDecimal(0.1));
		msession = new MerchSession();
		Merch merch = new Merch();
		merch.setImerch(new Long(2));
		msession.setMerch(merch);
		msession.setMerchUser(muser);
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
	@Test
	public void testApplyInstPdt() throws Exception{
		this.initMerch();
		this.merchLoanService.addDebitBid(bid, msession, "djiiijijsdf", "127.0.0.1");
		Set<Long> set = new HashSet<Long>();
		set.add(new Long(1));
		set.add(new Long(2));
		set.add(new Long(3));
		//ord = this.merchLoanService.applyLoanPtd(pdt, new BigDecimal(15000), 10, msession, "", null);
		LoanOrd lo = this.merchLoanService.queryOrdById(ord.getIloanOrd());
		Assert.assertNotNull(lo);
	}
	@Test
	public void testAcceptOrd() throws Exception{
		this.init();
		OrdOperLog log = new OrdOperLog();
		log.setMemo("");
		log.setIpAddr("");
		this.instLoanService.accept(ord, session, "", null);
	}
	@Test
	public void testRefuseOrd() throws Exception{
		this.testAcceptOrd();
		this.instLoanService.refuse(ord, session, "", null);
	}

}
