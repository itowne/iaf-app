<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/nl-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
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
		<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
</head>

<body>
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：申请受理&nbsp;&gt;&gt;&nbsp;订单审核&nbsp;&gt;&gt;&nbsp;订单明细</p>
  </div>
  <div class="how-to-ask">
    	<strong>借贷处理流程：</strong>&nbsp;<img src="${ctx}/resources/images/mp/bg-step2.gif" width="737" height="24" alt=""/>
  </div>
  <div><span style="font-weight:bold;margin-left:20px;border-bottom:0;">借贷状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span></div>
   <div style="padding: 5px;width:98%;">
   <div align="center" style="font-size: 15px;font-weight: bold;">本笔贷款概况</div>
   <table width="97%"  border="0" cellspacing="0" cellpadding="0" class="data-table">
   	<tr align="center">
   		<td class="label" style="background: #F3F9FD;width:80px;height:30px">借贷编号</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款申请金额(万元)</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款审批金额(万元)</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px"">贷款周期</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px"">贷款利率(%)</td>
   		<td class="label" style="background: #F3F9FD;width:80px;height:30px"">需还款总期数</td>
   		<td class="label" style="background: #F3F9FD;width:80px;height:20px"">还款方式</td>
   		<td class="label" style="background: #F3F9FD;width:80px;height:20px"">申请产品</td>
   		<td class="label" style="background: #F3F9FD;width:80px;height:20px"">放贷机构</td>
   		<td class="label" style="background: #F3F9FD;width:90px;height:20px"">申请日期</td>
   	</tr>
   	<tr align="center">
   		<td class="data"><s:label name="loanOrd.loanOrdId"/></td>
   		<td class="data"><s:label name="loanOrd.WangyuanPreQuota"/></td>
   		<s:if test='loanOrd.ordStat.toString()=="APPLY" || loanOrd.ordStat.toString()=="ACCEPT" || loanOrd.ordStat.toString()=="AUDIT_REFUSE" || loanOrd.ordStat.toString()=="ACCEPT_OVERDUE" || loanOrd.ordStat.toString()=="ACCEPT_REFUSE" || loanOrd.ordStat.toString()=="APPLY_OVERDUE" || loanOrd.ordStat.toString()=="CANCEL"'>
   			<td class="data">0</td>
   		</s:if>
   		<s:else>
	   		<td class="data"><s:label name="loanOrd.WangyuanQuota"/></td>
   		</s:else>
   		<td class="data"><s:label name="loanOrd.term"/><s:label name="loanOrd.termType.desc"/></td>
   		<td class="data"><s:label name="loanOrd.rateType.desc"/><s:label name="loanOrd.rate"/></td>
   		<td class="data">0</td>
   		<td class="data">
		<s:if test='loanOrd.pdtType.toString()=="MERCH_PROD"'>
				<s:label name="debitBid.refundType.desc"/>
				</s:if>
				<s:else>
				<s:label name="lp.repayment.desc"/>
				</s:else>
   		</td>
   		<td class="data"><s:label name="loanOrd.pdtName"></s:label></td>
   		<td class="data"><s:label name="loanOrd.instName"></s:label></td>
   		<td class="data"><fmt:formatDate value="${loanOrd.ordDate}" pattern="yyyy-MM-dd"/></td>
   	</tr>
   </table>
   </div>
  <div id="content" class="report">
 	<div align="center" style="margin-top: 50px"><div style="color:red;font-size: 18px;font-weight: bold">贷款审核成功！</div>
 	<div style="margin-top: 50px;font-size: 15px"><p>如果您想上传本借款的还款计划，请点击 【上传还款计划】 ，如想继续审核其它贷款申请，请点击  【继续审核贷款】.</p></div></div>
 	<div align="center" style="margin-top: 40px"><input type="button" class="dark-btn" value="上传还款计划" onclick="plan()"/>&nbsp;&nbsp;&nbsp;<input class="dark-btn" type="button" name="" value="继续审核贷款" onclick="audi()"/></div>
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
		window.setTimeout(reinitLoanIframe,300);
		window.curTab = "loanOrdApply";
		$("#loanFrame").bind("load",function(){
			$(".tab-title li").removeClass("cur");
			$("#"+window.curTab).addClass("cur");
		});
	});
	
	function plan(){
		window.location.href="${ctx}/inst/loanord/prodAudit!toPlan";
	}
	function audi(){
		window.location.href="${ctx}/inst/loanord/prodAudit";
	}
</script> 
</body>
