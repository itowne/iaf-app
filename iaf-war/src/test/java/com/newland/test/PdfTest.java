package com.newland.test;

import newland.iaf.base.model.ApplyRequest;
import newland.iaf.base.model.dict.ApplyType;

import org.apache.commons.jxpath.JXPathContext;

public class PdfTest {

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		String path = "applyType/desc";
		ApplyRequest ar = new ApplyRequest();
		ar.setApplyType(ApplyType.ERR_HANDLING);
		JXPathContext context = JXPathContext.newContext(ar);
		System.err.println(context.getValue(path));
	}

}
