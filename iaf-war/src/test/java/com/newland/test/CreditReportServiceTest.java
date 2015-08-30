package com.newland.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.base.service.ProvinceService;
import newland.iaf.merch.model.LoanCreditReport;
import newland.iaf.merch.service.MerchCreditReportService;

import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.newland.BeanTest;

public class CreditReportServiceTest extends BeanTest {
	@Resource(name = "merchCreditReportService")
	private MerchCreditReportService merchCreditReportService;
	@Resource(name = "provinceService")
	private ProvinceService provinceService;

	// @Test
	public void test() throws Throwable {
		LoanCreditReport report = new LoanCreditReport();
		FileOutputStream out = new FileOutputStream("d:/test.html");
		this.merchCreditReportService.convertToHtml(report,
				PagePosition.BACK_COVER, out, false, null, false,null);
		out.close();
	}

	//@Test
	public void test1() throws Throwable {
		LoanCreditReport report = new LoanCreditReport();
		report.setImerch(new Long(1));
		this.merchCreditReportService.saveReport(report);
//		List<LoanCreditReport> list = this.merchCreditReportService.queryReoprtList(new Long(1));
//		System.err.println(list.size());
	}
	@Test
	public void test2(){
		this.provinceService.getProvinceMap();
	}

	public static void main(String[] args) throws Throwable {
		// PDF文件输出路径 String 
		String outputFile = "D:/demo.pdf"; 
		OutputStream os = new FileOutputStream(outputFile); 
		ITextRenderer renderer = new ITextRenderer(); 
		// 解决中文支持问题
		ITextFontResolver fontResolver = renderer.getFontResolver(); 
		//simsun.ttc为字体文件
		FileInputStream fin = new FileInputStream(new File("E:/shizhenning/workspaces/iaf-app/iaf-war/application/rpt/2013050800000155.html"));
		byte[] buf = new byte[2048];
		int length = 0;
		StringBuffer html = new StringBuffer();
		while ((length = fin.read(buf)) > 0){
			html.append(new String(buf, 0, length, "utf-8"));
		}
		
		System.err.println(html.toString());
		renderer.setDocumentFromString(html.toString());
		// 解决图片的相对路径问题
		//renderer.getSharedContext().setBaseURL("file:/F:/Buteste/html/");
		renderer.layout();
		renderer.createPDF(os);
		os.close();
	}

}
