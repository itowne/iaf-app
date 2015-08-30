package com.newland.test;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.base.service.CreditReportQueryService;
import newland.iaf.utils.DateUtils;

import org.junit.Test;

import com.newland.BeanTest;

public class CreditReportQueryServiceTest extends BeanTest {
	@Resource (name = "creditReportQueryService")
	private CreditReportQueryService queryService;
	
	@Test
	public void test(){
		Date beginDate = DateUtils.rollDate(new Date(), Calendar.MONTH, -6);
		this.queryService.queryLoanTrans(new Long(1),new Date(), new Date());
		//this.queryService.queryInspectLog("0000000001", beginDate, new Date());
		//this.queryService.queryPayTrans("0000000001", beginDate, new Date());
		//this.queryService.queryReciveTrans("0000000001", beginDate, new Date());
		//this.queryService.queryRefundTrans(new Long(1), beginDate, new Date());
	}

}
