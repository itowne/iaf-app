<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/nl-tags" %>
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
</head>

<body>
<s:form id="form" action="bidAccept">
<div id="workframe-wrapper" class="clearfix">
	  <div id="location">
	    <p>当前位置：借贷受理&nbsp;&gt;&gt;&nbsp;借贷需求&nbsp;&gt;&gt;&nbsp;借贷需求明细</p>
	  </div>
  
  	<div class="how-to-ask">
    	<strong>借贷处理流程：</strong>&nbsp;<img src="/resources/images/mp/bg-vetting-step.gif" width="737" height="24" alt=""/>
    </div>
    <div class="report-tab-area">
    <div id="content" class="report">
    <h3 class="title"><span style="border-bottom:0;">借贷状态：
    <s:if test="#request.disable == false">
    <em style="color:red;">未申请</em>
    </s:if>
    <s:else>
    <em style="color:red;"><s:property value="loanOrd.ordStat.desc"/></em>
    </s:else>
    </span>&nbsp;&nbsp;
    <span class="related-menu"><a href="javascript:runQQ('${merch.qqUid}')">在线联系</a></span>&nbsp;&nbsp;
    <span class="related-menu">
    	<n:instAuthA href="javaScript:addMerch(${loanOrd.imerch})" value="加入我的关注商户" authCode="WDGZ_GZKH_TJ"/>
  	</span>&nbsp;&nbsp;
    <span class="related-menu">
    	<n:instAuthA href="javaScript:addDebit(${debitBid.idebitBid},${loanOrd.imerch})" value="加入我的关注订单" authCode="WDGZ_GZDD_TJ"/>
  	</span>
   </h3>
    <ul class="tab-title clearfix">
  		<li class="cur" onclick="window.curTab='debitApply'" id="debitApply"><a target="loanFrame" href="/inst/loanord/bidAccept!bidDetail">借款申请</a></li>
		<li  class="disabled">申请受理</li>
		<li class="disabled">还款计划</li>
		<li  onclick="window.curTab='loanOrdList'"id="loanOrdList"><a href="/inst/loanord/loanOrdList" target="loanFrame">借贷信用</a></li>
		<li  onclick="window.curTab='creditReport'" id="creditReport"><a id="creditReport" target="loanFrame" href="/inst/loanord/merchCreditReport">经营数据报告</a></li>
	</ul>
    <div class="tab-content">
	  	<iframe id="loanFrame" name="loanFrame" marginheight="0" marginwidth="0" src="/inst/loanord/bidAccept!bidDetail" width="100%" align="middle" scrolling="no" frameborder="0"></iframe>
	
    <div style="margin:15px auto;text-align:center;">
    		<div id="memoDiv" style="display:none;width:400;height: 200;margin:15px auto;text-align:center">
    		<div align="center" style="font-size: 14px">同意受理，仅代表接受商户的申请，但不代表最终放款！</div>
			<br/>
			<s:textarea  id="memo" name="memo" value="" cols="56" rows="10"></s:textarea>
		    </div>
		    <input type="hidden" id="memo" name="memo"></input>
		<s:if test="#request.disable">
			<s:if test="#request.loanOrd.ordStat == 'APPLY'">
			<div align="center" style="color:red;margin:10px;"><p style="color:red">商户已提交该竟投产品申请！</p></div>
			</s:if>
			<s:else>
			<div align="center" style="color:red;margin:10px;"><p style="color:red">您的贷款列表已有该竟投产品申请！</p></div>
			</s:else>
		</s:if>
    	<s:submit method="accept" value="受理" disabled="true" cssClass="dark-btn" onclick="return beforeSubmit1('受理订单  - 填写备注信息','bidAccept!accept')"/>
   		<s:submit mehtod="returnAction" value = "返回" cssClass="dark-btn"/>
    </div>
    </div>
    </div>
  </div>
</div>
</s:form>
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
		window.curTab = "debitApply";
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
	
	function addMerch(id){
		if(confirm("是否确认关注此商户?")){
			window.location.href="${ctx}/inst/instCollMerch!add?imerch="+id	;
		}
	}
	
	function addDebit(idebit,id){
		if(confirm("是否确认关注此订单?")){
			window.location.href="${ctx}/inst/instCollOrd!add?idebitBid="+idebit+"&imerch="+id;
		}
	}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>

