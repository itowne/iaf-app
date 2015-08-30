<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html>

<html>
<head>
<n:ui includes="form" />
</head>
<body>
	<div id="workframe-wrapper" class="clearfix">
		<div id="location">
			<p>当前位置：借款申请</p>
		</div>
		<div id="content">
			<h3 class="title">
				<span>借款申请</span>
			</h3>
			<div class="results report">
			<br><br><br>
			<form id="storeformReq" name="storeformReq" >
				<div align="center">
				<div><p style="font-size:20px;font-weight: bold;color:#FF8500">借款产品申请成功!</p></div>
				<br><br><br><br>
				<p style="font-size:16px">如果您想继续申请其他借款产品,请点击【继续申请产品】,如果查看已申请的订单,请点击【查看我的申请】.</p>
				</div>
				<br><br><br><br>
				<div class="uop">
					<input type="button" class="dark-btn" value="继续申请产品" onclick="proReq();" />
					<input type="button" class="dark-btn" value="查看我的申请" onclick="myReq();" />
				</div>
				<input type="hidden" name="iloanPdt" id="iloanPdt" value="<s:property value="loanPdt.iloanPdt"/>">
				</form>
				<div class="results report"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function proReq() {
			window.location.href = "${ctx}/merch/merchProdReq";
		}
		function myReq(){
			window.location.href="${ctx}/merch/merchMyReq";
		}
	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>