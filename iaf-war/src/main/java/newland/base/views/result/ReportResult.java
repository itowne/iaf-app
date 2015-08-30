/*
 * 功能：文件下载Result
 * 类名：FileDownResult.java
 *
 *  Ver     变更日期   	 修改人   	修改说明
 * ─────────────────────────
 * 	V1.0  	2012-8-7 	黄博飞         初版
 *
 * Copyright (c) 2006, 2008 NewlandComputer All Rights Reserved.
 * LICENSE INFORMATION
 */
package newland.base.views.result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.merch.model.HtmlFileType;
import newland.iaf.merch.model.LoanCreditReport;
import newland.iaf.merch.service.MerchCreditReportService;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 文件下载Result
 * 
 * @author 黄博飞
 * @since 2012-8-7
 */
public class ReportResult implements Result {
	private static final long serialVersionUID = 4244541560216181304L;
	Logger logger = LoggerFactory.getLogger(getClass());
	@Resource (name = "merchCreditReportService")
	MerchCreditReportService merchCreditReportService;


	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		ValueStack stack = invocation.getStack();
		response.setContentType("text/html;charset=utf-8");
		String position = (String)stack.findValue("position");
		String instType = (String)stack.findValue("instType");
		Boolean wrapper = (Boolean)stack.findValue("wrapper");
		boolean al = ((wrapper == null)?true:wrapper.booleanValue());
		LoanCreditReport report = (LoanCreditReport)stack.findValue("report");
		if (merchCreditReportService != null){
			if (report != null){
				if (StringUtils.isNotBlank(position)){
					PagePosition page = PagePosition.valueOf(position);
					try {
						if(StringUtils.isBlank(instType)){
							instType="OTHERS";
						}
						merchCreditReportService.convertToHtml(report, page, response.getOutputStream(), al, HtmlFileType.FOR_HTML, false,InstType.valueOf(instType));
					} catch (Throwable e) {
						logger.error("生成页面出错", e);
					}
				}else{
					logger.error("缺少页面参数 position");
					response.sendError(505, "position is not found!");
				}
			}else{
				logger.error("缺少页面参数 report");
				response.sendError(505, "report is not found!");
			}
		}else{
			logger.error("缺少页面参数 report");
			response.sendError(505, "reportService is not found!");
		}
		response.flushBuffer();
	}

}
