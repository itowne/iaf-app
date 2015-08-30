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
    <p>当前位置：贷后管理&nbsp;&gt;&gt;&nbsp;还款管理</p>
  </div>
  <div class="how-to-ask">
    	<strong>处理流程：</strong>&nbsp;<div id="myflow"></div><!-- <img src="${ctx}/resources/images/mp/bg-vetting-step.gif" width="737" height="24" alt=""/> -->
  </div>
  <div align="center" style="font-size:16px;font-weight: bold"></div>
  <div id="content" class="report" align="center" style="margin-top: 20px">
  <s:form action="/inst/loanord/refundMgr!uploadfile" enctype="multipart/form-data" onsubmit="return check()">
  <div align="center">上传类型<select name="filetype" id="filetype">
  <option value="">请选择类型</option>
  <option value="FREEZE">冻结</option>
  <option value="UNFREEZE">解冻</option>
  </div>
  <br>
  <div align="center" style="margin-top: 10px"><input type="file" name="upload" id="file"></div><br>
  <div align="center" style="margin-top: 10px"><input type="submit" class="dark-btn" value="上传">&nbsp;&nbsp;<input class="dark-btn" type="button" value="取消" onclick="cancel()"></div>
   	</div>
    <input type="hidden" name="selectedRows" id="selectedRows" value="<s:property value="selectedRows"/>">
     </s:form>
  </div>
  </form>
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
	function beforeSubmit1(title,action){
		dialog(title, "memoDiv", 500, 300, function(dialogObj){
			var dialogMemo = $(dialogObj).find("#memo").val();
			$("#memo").val(dialogMemo);
			$("#form").attr("action", action).submit();
		});
		return false;
	}
	function displayCancel(flag1,flag2){
		$("#cancel").css('display',flag1);
		$("#returnAction").css('display',flag2);
	}

	function back(){
		window.location.href="${ctx}/inst/loanord/refundMgr!returnAction";
	}
	
	function cancel(){
		window.history.go(-1);
	}
	function check(){
		if(confirm("确认要上传吗?")){
			if($("#filetype").val()==""){
				alert("请选择上传类型");
				return false;
			}			
			if($("#file").val()==""){
				alert("请选择一张图片")
				return false;
				}
			return true;
			}
	}
</script> 
</body>
