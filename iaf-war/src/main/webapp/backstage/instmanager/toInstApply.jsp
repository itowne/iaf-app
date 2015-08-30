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
				<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;受理申请机构</p>
			</div>
  <div id="content" class="report">
  <h3 class="title"><span>详情</span></h3>
  <s:form id="instMainForm" action="instApplyRequest!toAdd" namespace="/backstage/instmanager">
 	<div align="center" style="margin-top: 50px"><div style="color:red;font-size: 18px;font-weight: bold"> 机构的注册申请已受理！</div></div>
 	<div style="margin-top: 50px;font-size: 15px" align="center"><p>如果您想现在录入新机构的注册信息，请点击【马上新增机构】按钮.</p></div>
 	<div style="margin-top: 50px;font-size: 15px" align="center"><p>如果您想以后再录入新机构注册信息，请点击【以后再新增机构】按钮.</p></div>
 	<div align="center" style="margin-top: 40px">
 	<input type="button" class="dark-btn" value="马上新增机构" onclick="toAdd()"/>&nbsp;&nbsp;&nbsp;<input class="dark-btn" type="button" name="" value="以后再新增机构" onclick="after()"/></div>
	<input type="hidden" name="inst" id="inst" value="<s:property value="%{inst}"/>">
	</s:form>
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
		window.setTimeout(reinitLoanIframe,300);
		window.curTab = "loanOrdApply";
		$("#loanFrame").bind("load",function(){
			$(".tab-title li").removeClass("cur");
			$("#"+window.curTab).addClass("cur");
		});
	});
	
	function toAdd(){
		//document.instMainForm.action="${ctx}/backstage/instmanager/";	
        document.instMainForm.submit();
	}
	function after(){
		window.location.href="${ctx}/backstage/instmanager/instApplyRequest";
	}
</script> 
</body>
