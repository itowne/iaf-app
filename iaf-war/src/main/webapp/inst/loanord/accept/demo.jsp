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
<script type="text/javascript" src="${ctx}/resources/js/workflow.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/workflow.css"/>
</head>

<body>
<s:form id="form" action="prodAccept">
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：申请受理&nbsp;&gt;&gt;&nbsp;订单受理&nbsp;&gt;&gt;&nbsp;订单明细<span style="margin-left:580px"><span class="related-menu"><a href="javascript:runQQ('${merch.qqUid}')">在线联系</a></span>&nbsp<input type="button" onclick="back()" value = "返回" class="dark-btn"/></span></p>
  </div>
  <div class="how-to-ask">
    	<strong>贷款处理流程：</strong>&nbsp;<div id="myflow"></div><!-- <img src="${ctx}/resources/images/mp/bg-vetting-step.gif" width="737" height="24" alt=""/> -->
  </div>
  <div><span style="font-size:14px;font-weight:bold;margin-left:20px;border-bottom:0;">贷款状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span></div>
  <div style="padding: 5px;width:98%;">
   <div align="center" style="font-size: 15px;font-weight: bold;">本笔贷款概况</div>
   <table width="97%"  border="0" cellspacing="0" cellpadding="0" class="data-table">
   	<tr align="center">
   		<td class="label" style="background: #F3F9FD;width:80px;height:30px">借贷编号</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款申请金额(万元)</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款审批金额(万元)</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px"">贷款周期</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px"">最低利率(%)</td>
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
   		<td class="data">
   		<s:if test='loanOrd.pdtType.toString()=="MERCH_PROD"'>
				<s:label name="debitBid.rateType.desc"/><s:label name="loanOrd.rate"/>起
				</s:if>
				<s:else>
				<s:label name="lp.rateType.desc"/><s:label name="loanOrd.rate"/>起
				</s:else>
   		</td>
   		<td class="data"><s:label name="periods"/>期</td>
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
   <div align="center" style="margin-top:20px;font-size: 15px;font-weight: bold;">本笔贷款详情</div>
  <div class="report-tab-area">
  <div id="content" class="report">
  <div align="right">
  	<s:if test='#request.loanOrd.ordStat.toString()=="APPLY"'>
		    <n:instAuthButton value="同意" cssClass="dark-btn" onclick="return beforeSubmit1('受理订单  - 填写备注信息','prodAccept!accept')" authCode="SQSL_CPSL_SL" disabledFlag="${disabledFlag}" />
	   		<n:instAuthButton value="不同意" cssClass="dark-btn" onclick="return beforeSubmit2('不受理该订单 - 填写备注信息','prodAccept!refuse')" authCode="SQSL_CPSL_JJ" disabledFlag="${disabledFlag}" />
	   		<!--<n:instAuthButton value="撤销" cssClass="dark-btn" onclick="return beforeSubmit1('撤销订单 - 填写备注信息','prodAccept!cancel')" authCode="SQSL_CPSL_CHEXIAO" disabledFlag="${disabledFlag}" />-->
		    </s:if>
		    <s:else>
		    <input type="button" style="background: #7B7B7B;color:#FFFFFF;" disabled="disabled" value="同意" class="dark-btn" onclick="return beforeSubmit1('受理订单  - 填写备注信息','prodAccept!accept')" authCode="SQSL_CPSL_SL"  />
	   		<input type="button" style="background: #7B7B7B;color:#FFFFFF;" disabled="disabled" value="不同意" class="dark-btn" onclick="return beforeSubmit1('不受理该订单 - 填写备注信息','prodAccept!refuse')" authCode="SQSL_CPSL_JJ"  />
	   		<!--  <input type="button" style="background: #7B7B7B;color:#FFFFFF;" disabled="disabled" value="撤销" class="dark-btn" onclick="return beforeSubmit1('撤销订单 - 填写备注信息','prodAccept!cancel')" authCode="SQSL_CPSL_CHEXIAO"  />-->
		    </s:else>
  </div>
 <!--  <h3 class="title"><span style="border-bottom:0;"><em style="color:red;">&nbsp;</em></span>&nbsp;&nbsp;
  
  		<span class="related-menu">
  			<n:instAuthA href="javaScript:addMerch(${loanOrd.imerch})" value="加入我的关注" authCode="WDGZ_GZKH_TJ"/>
  		</span>
  		 -->
  </h3>
	  	<ul class="tab-title clearfix">
			<li class="cur" onclick="window.curTab='loanOrdApply'" id="loanOrdApply"><a href="/inst/loanord/loanDetail" target="loanFrame">贷款申请</a></li>
			<li  onclick="window.curTab='operLogDetail'" id="operLogDetail"><a href="/inst/loanord/operLogDetail" target="loanFrame" >贷款进度跟踪</a></li>
			<li class="disabled" id="planMgr">还款计划</li>
			<li  onclick="window.curTab='loanOrdList'"id="loanOrdList"><a href="/inst/loanord/loanOrdList" target="loanFrame">商户借贷信用</a></li>
			<li  onclick="window.curTab='creditReport'" id="creditReport"><a id="creditReport" target="loanFrame" href="/inst/loanord/merchCreditReport">经营数据报告</a></li>
		</ul>
	  	<div class="tab-content" style="height:auto">
	  	<iframe id="loanFrame" name="loanFrame" marginheight="0" marginwidth="0" src="/inst/loanord/loanDetail" width="100%" align="middle" scrolling="no" frameborder="0"></iframe>
 	
		<div style="margin:15px auto;text-align:center;">
			<div id="memoDiv" style="display:none;width:400;height: 200;margin:15px auto;text-align:center">
			<div align="center" style="font-size: 14px">本次操作后，请及早在审批处理中进行下一步操作</div>
			<br/>
			<s:textarea  id="memo" name="memo" value="" cols="56" rows="10"></s:textarea>
		    </div>
		    <div id="refuseDiv" style="display:none;width:400;height: 200;margin:15px auto;text-align:center">
			<div align="center" style="font-size: 14px">请详细填写拒绝理由，以便借款人知悉原因</div>
			<br/>
			<s:textarea  id="memo" name="memo" value="" cols="56" rows="10"></s:textarea>
		    </div>
		    <input type="hidden" id="memo" name="memo"></input>
		    <s:if test="#request.loanOrd.ordStat.name() != 'APPLY'">
		      <s:set var="disabledFlag" >true</s:set>
		    </s:if>
		    <s:if test="#request.tip!=null">
		    	<div align="center" style="color:red;margin-bottom: 20px"><s:property value="%{#request.tip}"/></div>
		    </s:if>
	   		<!--<s:submit mehtod="returnAction" value = "返回" cssClass="dark-btn"/>-->
	    </div>

	</div>
</div>
</div>
</div>
</s:form>
<script type="text/javascript">
$("#myflow").flow(["受理","审核","放款","制定还款计划","还款管理","结束"],1);
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
	function reinitLoanIframe(){
		var iframe = document.getElementById("loanFrame");
		try{
			var height = iframe.contentWindow.document.body.scrollHeight;
			if(/webkit/.test(navigator.userAgent.toLowerCase())){
				height = $(iframe.contentWindow.document.body).height();
			}
			iframe.height = height + 10;
		}catch(e){}
		iframe=null;
		window.setTimeout(reinitLoanIframe,300);
	}
	
	function beforeSubmit1(title,action){
		dialog(title, "memoDiv", 500, 300, function(dialogObj){
			var dialogMemo = $(dialogObj).find("#memo").val();
			$("#memo").val(dialogMemo);
			$("#form").attr("action", action).submit();
		});
		return false;
	}
	
	function beforeSubmit2(title,action){
		dialog(title, "refuseDiv", 500, 300, function(dialogObj){
			var dialogMemo = $(dialogObj).find("#memo").val();
			$("#memo").val(dialogMemo);
			$("#form").attr("action", action).submit();
		});
		return false;
	}
	function addMerch(id){
		if(confirm("是否确认关注此商户?")){
			window.location.href="${ctx}/inst/instCollMerch!add?imerch="+id	;
		}
	}
	function back(){
		window.location.href="${ctx}/inst/loanord/prodAccept!returnAction";
	}
</script> 
</body>
