package com.newland.test;

import javax.annotation.Resource;

import newland.iaf.utils.service.SerialNoService;

import org.junit.Test;

import com.newland.BeanTest;


public class SerialNoServiceTest extends BeanTest {
	@Resource (name = "serialNoService")
	private SerialNoService serialNoService;
	@Test
	public void testSerialNoGenerator(){
		StringBuffer sb = new StringBuffer();
		sb.append("inst no:"+this.serialNoService.genInstNo() + "\n");
		sb.append("file no:"+this.serialNoService.genFileNo() + "\n");
		sb.append("instprod no:"+this.serialNoService.genInstProdNo() + "\n");
		sb.append("merch prod no:"+this.serialNoService.genMerchDebitDibNo() + "\n");
		sb.append("merch no:"+this.serialNoService.genMerchNo() + "\n");
		sb.append("ord no:"+this.serialNoService.genOrdNo() + "\n");
		sb.append("system no:"+this.serialNoService.genSystemNo() + "\n");
		sb.append("plan no:"+this.serialNoService.genPlanNo() + "\n");
		System.err.println(sb.toString());
	}

}
