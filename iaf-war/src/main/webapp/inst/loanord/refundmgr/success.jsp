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
	<script type="text/javascript" src="${ctx}/resources/js/workflow.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/workflow.css"/>
</head>

<body>
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：贷后管理&nbsp;&gt;&gt;&nbsp;还款管理<span style="margin-left:680px"></p>
  </div>
  <div class="how-to-ask">
    	<strong>处理流程：</strong>&nbsp;<div id="myflow"></div><!-- <img src="${ctx}/resources/images/mp/bg-vetting-step.gif" width="737" height="24" alt=""/> -->
  </div>
  <div align="center" style="font-size:16px;font-weight: bold"></div>
  <div id="content" class="report" align="center" style="margin-top: 20px">
 	<div align="center" style="margin-top: 100px;font-size: 16px;font-weight: bold;color:red">上传成功!</div>
 	 <input type="hidden" name="iloanOrd" id="iloanOrd" value="<s:property value="loanOrd.iloanOrd"/>">
 	<div align="center" style="margin-top: 50px"><input type="button" class="dark-btn" value="返回" onclick="back();"></div>
  </div>
<script type="text/javascript">
$("#myflow").flow(["填写申请","打印申请凭证,并加盖公司印章","上传申请凭证的扫描图片"],3);
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
		window.curTab = "planMgr";
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


	function back(){
		var iloanord = $("#iloanOrd").val();
		window.location.href="${ctx}/inst/loanord/refundMgr!toNext?id="+iloanord;
	}
	
</script> 
</body>
