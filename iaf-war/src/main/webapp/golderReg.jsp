<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>

<html>
<head>
<n:head styles="portal-base,portal-hp" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />

<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
</head>
<body id="body">

	<%@include file="/template/portal/header.jspf"%>
	<div id="main-reg">
			<div class="content">
				<ul class="tab-reg">
					<li onclick="location.href='merchReg.jsp';">商户注册申请</li>
					<li onclick="location.href='instReg.jsp';">放贷机构注册申请</li>
					<li class="gold-keeper cur">开通金掌柜</li>
				</ul>
				<div class="reg-fields">
					<form action="#" method="post">
						<div class="row">
							<label>商户名称：</label><input type="text" class="reg-input">
						</div>
						<div class="row">
							<label>所在省市：</label><select><option>请选择</option><option>广东省</option></select>
							<select><option>请选择</option><option>广州市</option><option>东莞市</option></select>
						</div>
						<div class="row">
							<label>商户性质：</label><select><option>请选择企业性质</option><option>国营</option><option>集体</option><option>私企</option><option>个体工商户</option><option>合次</option></select>
						</div>
						<div class="row">
							<label>商户所属行业：</label><select><option>请选择行业</option><option>服装</option><option>农产品</option><option>汽车</option></select>
						</div>
						<div class="row">
							<label>商户性质：</label><select><option>请选择企业性质</option><option>国营</option><option>集体</option><option>私企</option><option>个体工商户</option><option>合次</option></select>
						</div>
						<div class="row">
							<label>联系人姓名：</label><input type="text" class="reg-input">
						</div>
						<div class="row">
							<label>联系电话：</label><input type="text" class="reg-input">
						</div>
						<div class="row">
							<label>性别：</label><select><option>请选择</option><option>男</option><option>女</option></select>
						</div>
						<div class="row">
							<label>验证码：</label><input type="text" class="login-input">&nbsp;<img src="resources/images/portal/captcha.gif" width="112" height="46" alt=""/>
						</div>
						<div class="reg-uop">
							<button>提&nbsp;&nbsp;交</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	<%@include file="/template/portal/co-bank.jspf"%>
	<%@include file="/template/portal/footer.jspf"%>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/portal.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$("#nav li").hover(function() {
				if ($(this).hasClass("cur")) {
					return;
				}
				$(this).addClass("hover");
			}, function() {
				$(this).removeClass("hover");
			});
		});
	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>