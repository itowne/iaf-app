package newland.iaf.merch.service;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import org.ohuyo.commons.query.criterion.Page;

import newland.iaf.base.model.condition.DateRange;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.merch.model.AcceptType;
import newland.iaf.merch.model.HtmlFileType;
import newland.iaf.merch.model.LoanCreditReport;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchInfoPermission;
import newland.iaf.merch.model.MerchType;
/**
 * 商户信用报告管理
 * @author new
 *
 */
public interface MerchCreditReportService {
	/**
	 * 保存信用报告
	 * @param rpt
	 * @param file
	 * @throws Exception 
	 * @throws Throwable 
	 */
	void saveReport(LoanCreditReport rpt) throws Exception, Throwable;
	/**
	 * 生成报告文件
	 * @param imerch
	 * @param 日期范围
	 * @return
	 */
	LoanCreditReport genCreditReport(Long imerch, DateRange dateRange);
	/**
	 * 生成Pdf文件
	 * @param report
	 * @param baseUrl TODO
	 * @param wrapper TODO
	 * @return
	 * @throws Exception
	 * @throws Throwable 
	 */
	File convertToPdf(LoanCreditReport report, String baseUrl, boolean wrapper,DateRange dateRange) throws Exception, Throwable;
	
	/**
	 * 生成Html文档
	 * @param position
	 * @param wrapper TODO
	 * @param type TODO
	 * @param imerch
	 * @return TODO
	 * @throws Throwable 
	 */
	boolean convertToHtml(LoanCreditReport report, PagePosition position, OutputStream out, boolean wrapper, HtmlFileType type,
			boolean gen,InstType instType) throws Throwable;
	
	/**
	 * 读取pdf文件
	 * @param report
	 * @param out
	 * @throws Throwable
	 */
	void readPdf(LoanCreditReport report, OutputStream out) throws Throwable;
	/**
	 * 查询信息报告列表
	 * @param imerch
	 * @return
	 */
	List<LoanCreditReport> queryReoprtList(Long imerch,Page page);
	
	/**
	 * 设置资源权限
	 * @param merch
	 * @param position
	 */
	void setInfoPermission(Merch merch, PagePosition position, AcceptType type);
	/**
	 * 查询资源权限
	 * @param merch
	 * @return
	 */
	List<MerchInfoPermission> queryInfoPermission(Merch merch);
	/**
	 * 查询访问权限
	 * @param merch
	 * @param position
	 * @return
	 */
	AcceptType queryAcceptType(Merch merch, PagePosition position);
	/**
	 * 查询报表数据
	 * @param report
	 * @return TODO
	 */
	LoanCreditReport genCreditReport(LoanCreditReport report,DateRange dateRange);
	/**
	 * 创建pdf文件
	 * @param report
	 * @param baseUrl TODO
	 * @param wrapper TODO
	 * @return TODO
	 * @throws Throwable 
	 */
	File createPdfFile(LoanCreditReport report, String baseUrl, boolean wrapper,DateRange dateRange) throws Throwable;
	/**
	 * 删除报告
	 * @param report
	 * @throws Exception
	 */
	void deleteReport(LoanCreditReport report) throws Exception;
}
