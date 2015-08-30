<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="${ctx}/template/head.jsp" />
<body>
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：我要还款>>受理查询</p>
  </div>
  <div id="content" class="report">
    <h3 class="title"><span>本期还款交易记录详情</span></h3>
    <div style="padding:30px 100px;">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
	    	<tr>
	    		<td class="label">借款详情</td>
	    		<td class="data" colspan="2" style="padding:0;">
	    			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table" >
	    				<tr>
	    					<th style="background: #F3F9FD">借款编号</th>
	    					<th style="background: #F3F9FD">借款金额(万元)</th>
	    					<th style="background: #F3F9FD">需还款总期数</th>
	    					<th style="background: #F3F9FD">放贷机构</th>
	    				</tr>
	    				<tr align="center">
	    					<td><a href="javaScript:void(0)" onclick="query();"><s:property  value='loanOrd.loanOrdId'/></a></td>
	    					<td><s:property  value='loanOrd.wangyuanQuota'/></td>
	    					<td><s:property  value='loanOrd.term'/>期</td>
	    					<td><s:property  value='inst.instName'/></td>
	    				</tr>
	    			</table>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">本次还款详情</td>
	    		<td class="data" colspan="2" style="padding:0;">
	    			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table" >
	    				<tr>
	    					<th style="background: #F3F9FD">还款编号</th>
							<th style="background: #F3F9FD">当前还款期数</th>
							<th style="background: #F3F9FD">计划还款日期</th>
							<th style="background: #F3F9FD">还款金额(元)</th>
							<th style="background: #F3F9FD">还款本金(元)</th>
							<th style="background: #F3F9FD">利息(元)</th>
							<th style="background: #F3F9FD">剩余还款金额(元)</th>
	    				</tr>
							<tr align="center">
								<td><s:property value='plan.iloanOrdPlan'/></td>
								<td>第<s:property value='plan.period'/>期</td>
								<td><fmt:formatDate value="${plan.refundDate}" pattern="yyyy-MM-dd"/></td>
								<td><s:property value='plan.repayment'/></td>
								<td><s:property value='plan.capital'/></td>
								<td><s:property value='plan.interest'/></td>
								<td><s:property value='plan.remainAmount'/></td>
							</tr>
	    			</table>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="4">收款人信息</td>
	    		<td class="label">收款方</td>
	    		<td class="data"><s:property  value='inst.instName'/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">开户姓名</td>
	    		<td class="data"><s:property  value='instBusiInfo.repaymentName'/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">银行卡(帐)号</td>
	    		<td class="data"><s:property  value='instBusiInfo.repaymentNo'/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">开户银行</td>
	    		<td class="data"><s:property  value='instBusiInfo.repaymentBank'/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">付款方信息</td>
	    		<td class="label">付款方</td>
	    		<td class="data"><s:property value="%{merch.merchName}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="3">交易信息</td>
	    		<td class="label">支付方式</td>
	    		<td class="data"><s:property value="%{ff.type.desc}" /></td>
	    	</tr>
	    	<tr>
	    		<td class="label">还款金额(元)</td>
	    		<td class="data"><s:property value="%{plan.repayment}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">备注</td>
	    		<td class="data" style="padding:5px;" colspan="2"><s:label name="plan.memo"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">交易订单号</td>
	    		<td class="data" colspan="2" align="center"><s:label name="tm.orderNo"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">还款交易日期</td>
	    		<td class="data" colspan="2" align="center"><fmt:formatDate value="${plan.updTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    	</tr>
	    </table>
    </div>
    <div align="center"><input type="button" class="dark-btn" value="返回" onclick="window.history.go(-1);"></div>
  </div>
</div>
<script type="text/javascript">
			$(function(){
				$(".data-list tr:even").addClass("even");
				$(".data-list tr").hover(function(){
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				});
				$(".dark-btn").hover(function(){
					$(this).addClass("dark-btn-hover");
				},function(){
					$(this).removeClass("dark-btn-hover");
				});
			});
			
			function query(){
				window.history.go(-1);
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
