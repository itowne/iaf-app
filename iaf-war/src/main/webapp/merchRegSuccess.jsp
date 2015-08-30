<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册成功跳转页</title>
<n:head styles="portal-base,portal-inner" />
<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
<style type="text/css">
body {
	text-align: center
}

#main {
	width: 800px;
	margin: 10px auto;
	height: 265px;
	box-shadow: -2px 2px 8px -2px rgba(0, 0, 0, 0.3);
	border-radius: 5px;
}

.f {
	margin-left: 20px;
	text-align: left;
	font-size: 15px;
}
</style>
</head>
<body>
	<%@include file="/template/portal/header.jspf"%>
	<s:form id="form1" name="form1">
		<div
			style="margin: 80px auto; background-color: #FFF; width: 750px; height: 220px; padding: 20px;">
			<div>
				<div >
					<%-- 				<img src="${ctx}/resources/images/signup/success.png"/> --%>
					<div style="font-size: 24px; margin-left: 20px; text-align:center;color:red">注册成功!</div>
					<br />
					<!-- 
					<div class="f">
						<label style="font-weight: bold;">用户名：</label>
						<s:property value="loginName" />
					</div>
					<br>
					<div class="f">
						<label style="font-weight: bold;">商户名称：</label>
						<s:property value="merchName" />
					</div>
					<br>
					<br>
					<div class="f">
						<label style="font-weight: bold;">联系人：</label>
						<s:property value="contract" />
					</div>
					<br>
					<div class="f">
						<label style="font-weight: bold;">联系电话：</label>
						<s:property value="contractTel" />
					</div>
					 -->
					 <div class="f" >
						尊敬的 <font style="color:red"><s:property value="loginName" /></font>先生(女士)，
					</div>
					 <div class="f" style="padding-top: 20px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢您注册成为我们平台的用户。由于你目前仍是非金掌柜用户。因此，贵企业资料的真实性我们平台暂
					</div>
					<div class="f" style="padding-top: 20px">时未能核实。请您尽快安装 【金掌柜】，从今天开始就累积的经营流水，这将有助您在我们平台内建立良好的</div>
					<div class="f" style="padding-top: 20px">企业信用，并且可以大大提高您的每次借款申请的额度和成功率。</div>
       				<div class="f" style="padding-top: 20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如需安装【金掌柜】，请马上致电<font style="color:red"> 400-628-6928 </font>申请办理 。</div>
					<br>
					<div style="font-size: 14px; margin-left: 20px; text-align: left;">
						<span id="countdown">15</span>秒后将跳转到订单页面，如果没跳转请<a
							href="${ctx}/merchLogin.jsp">点击跳转</a>。
					</div>
				</div>

			</div>
		</div>
	</s:form>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/portal.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	window.setInterval(function(){
		$("#countdown").text($("#countdown").text()-1);
	}, 1000);
	
	window.setTimeout(function(){
		$("#form1").attr("action","${ctx}/merchLogin.jsp");
		$("#form1").submit();
	}, 15000);
});
</script>
</body>
</html>