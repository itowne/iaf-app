<%@ page language="java" contentType="application/msword; charset=gb2312" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
response.setHeader("Content-Disposition", "attachment;filename=jdpz.doc");
 %> 
<html>
<head>
<title>汇融易 - 机色管理后台</title>
<style type="text/css">
	.pager{
		padding:15px 45px;text-align:right;
	}
	.pager a{
		display:inline-block;padding:2px 5px;border:1px solid #d1d1d1;color:#4a4a4a;
	}
	.pager a.cur,.pager a:hover{
		background:#0157ad;color:#fff;
	}
	.dark-btn{
		vertical-align:middle;
	}
</style>
</head>
<body>

  <div align="center" style="font-size:16px;font-weight: bold ;color:red;margin-top: 10px">请打印以下的解冻申请凭证单，并盖上贵公司的印章后，扫描成图片上传到汇融易平台</div>
  <div id="content" class="report" align="center" style="margin-top: 20px">
    <table width="60%" border="1" cellspacing="0" cellpadding="0">
    <tr>
    	<td class="label" style="width:150px">借贷编号：</td>
    	<td class="data" colspan="3"><s:label name="loanOrd.loanOrdId"/></td>
    </tr>
    <tr>
    	<td class="label">贷款金额（万元）：</td>
    	<td class="data" colspan="3"><s:label name="loanOrd.wangyuanQuota"/></td>
    </tr>
    <tr>
    	<td class="label">贷款周期：</td>
    	<td class="data" colspan="3"><s:label name="loanOrd.pdtTerm"/></td>
    </tr>
    <tr>
    	<td class="label">贷款利率（%）：</td>
    	<td class="data" colspan="3"><s:label name="loanOrd.pdtRate"/></td>
    </tr>
    <tr>
    	<td class="label">冻结商户：</td>
    	<td class="data" colspan="3"><s:label name="merch.merchName"/></td>
    </tr>
    <tr>
    	<td class="label">冻结商户汇卡号：</td>
    	<td class="data" colspan="3"><s:label name="merch.merchNo"/></td>
    </tr>
    <tr>
    	<td class="label">解冻申请机构：</td>
    	<td class="data" colspan="3"><s:label name="instx.instName"/></td>
    </tr>
    <tr>
    	<td class="label" colspan="4" style="text-align:center">欠款详情</td>
    </tr>
    <tr>
    	<td class="label">&nbsp;</td>
    	<td class="label" style="text-align:center">冻结期数</td>
    	<td class="label" style="text-align:center">计划还款日期</td>
    	<td class="label" style="text-align:center">还款金额（元）</td>
    </tr>
    <s:iterator value="#request.loanOrdPlanList" id="lop" status="st">
    	<tr>
    		<td class="label" style="width:50px;text-align:center"><s:property value="#st.count"/></td>
    		<td class="data" style="text-align:center">第<s:property value="#request.lop.period"/>期</td>
    		<td class="data" style="text-align:center"><fmt:formatDate value="${lop.refundDate}" pattern="yyyy-MM-dd"/></td>
    		<td class="data" style="text-align:center"><s:property value="#request.lop.repayment"/></td>
    	</tr>
    </s:iterator>
    <tr>
    	<td class="label" style="text-align:center">合计</td>
    	<td class="data" style="text-align:center"><s:property value="#request.loanOrdPlanList.size"/>期</td>
    	<td class="data" style="text-align:center">--</td>
    	<td class="data" style="text-align:center"><s:property value="capital"/></td>
    </tr>
     <tr>
    	<td class="label">应还金额（元）：</td>
    	<td class="data" colspan="3" style="text-align:left"><s:property value="capital"/></td>
    </tr>
     <tr>
    	<td class="label">滞纳金（元）：</td>
    	<td class="data" colspan="3" style="text-align:left">${loanOrd.overdue}</td>
    </tr>
     <tr>
    	<td class="label">冻结总金额(元)：</td>
    	<td class="data" colspan="3" style="text-align:left"><s:property value="planTotal"/></td>
    </tr>
     <tr>
    	<td class="label">申请冻结时间：</td>
    	<td class="data" colspan="3" style="text-align:left"><fmt:formatDate value="${freezeTime}" pattern="yyyy-MM-dd"/></td>
    </tr>
    <tr>
    	<td class="label">解冻原因说明：</td>
    	<td class="data" colspan="3" style="text-align:left"><div>${unMemo}</div></td>
    </tr>
    </table>
   </div>
   </body>
   </html>