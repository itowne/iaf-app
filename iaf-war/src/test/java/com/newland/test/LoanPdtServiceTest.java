package com.newland.test;

import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.base.service.LoanPdtService;

import org.junit.Test;
import org.ohuyo.commons.query.criterion.Page;

import com.newland.BeanTest;


public class LoanPdtServiceTest extends BeanTest {
	@Resource (name = "loanPdtService")
	private LoanPdtService loanPdtService;
		
	@Test
	public void testQuery() throws Exception{ 
		Page page = new Page();
		page.setCount(true);
		page.setCapacity(10);//设置每页显示数
		page.setPageOffset(0);
		LoanPdtCondition loadPdtCondition = new LoanPdtCondition();
		loadPdtCondition.setIinst(new Long(1));
		List<LoanPdt> loadPdtList = this.loanPdtService.queryLoanPdtByCon(loadPdtCondition, page);
		for(LoanPdt LoanPdt:loadPdtList){
			System.out.println(LoanPdt.getRegion());
		}
	}
	

}
