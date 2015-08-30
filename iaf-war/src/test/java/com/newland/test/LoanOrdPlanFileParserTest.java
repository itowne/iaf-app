package com.newland.test;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.utils.file.FileParser;

import org.junit.Test;

import com.newland.BeanTest;


public class LoanOrdPlanFileParserTest extends BeanTest {
	@Resource (name = "loanOrdPlanFileParser")
	FileParser<List<LoanOrdPlan>> fileParser;
	@Test
	public void testConvert() throws Exception{
		File file = new File("e:/file.xlsx");
		List<LoanOrdPlan> plans = this.fileParser.convertFromFile(file);
		for (LoanOrdPlan plan : plans){
			System.err.println(plan.getPeriod() + " " + plan.getRepayment() + " " + plan.getRemainAmount());
		}
	}

}
