<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册成功跳转页</title>
<n:head styles="portal-base,portal-inner"/>
<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
<style type="text/css">
body{text-align:center}
#main{width:800px; margin:10px auto; height:265px;box-shadow: -2px 2px 8px -2px rgba(0, 0, 0, 0.3); border-radius:5px;}
.f {margin-left:20px;text-align:left;font-size:15px;}
</style>
</head>
<body>
	<%@include file="/template/portal/header.jspf"%>
	<s:form id="form1" name="form1">
		<div style="margin:80px auto;background-color:#FFF;width:500px;height:220px;padding:20px;" >
		    <div>
				<div >
	<%-- 				<img src="${ctx}/resources/images/signup/success.png"/> --%>
				    <div style="font-size:18px;margin-left:20px;text-align:left;" >欢迎您！您已注册成功。我们的客服会尽快与您联系。</div>
				    <br/>
				    <div style="font-size:16px;font-weight:bold;" class="f">您的注册信息如下：</div>
				    <br>
				    <div class="f"><label style="font-weight:bold;">登录名：</label><s:property value="merchUser.loginName"/></div>
				    <br>
				    <div class="f"><label style="font-weight:bold;">商户名称：</label><s:property value="merch.merchName"/></div>
				    <br>
				    <div class="f"><label style="font-weight:bold;">联系人：</label><s:property value="merch.contract"/></div>
				    <br>
				    <div class="f"><label style="font-weight:bold;">联系电话：</label><s:property value="merch.contractTel"/></div>
				    <br>
				    <div style="font-size:14px;margin-left:20px;text-align:left;">
						<span id="countdown">5</span>秒后将跳转到订单页面，如果没跳转请<a href="${ctx}/merchReg!toMerchLogin">点击跳转</a>。
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
		$("#form1").attr("action","merchReg!toMerchLogin");
		$("#form1").submit();
	}, 5000);
});
</script>
</body>
</html>