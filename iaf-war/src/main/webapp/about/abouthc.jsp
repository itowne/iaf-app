<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/nl-tags" prefix="n" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<n:head styles="portal-base,portal-hp" scripts="jquery"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
		<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
	<div id="main" class="iaf-grid">
			<%@include file="aboutSidenav.jsp" %>
            <div id="main-con">
            <span><img src="../resources/images/portal/help-ads.jpg" width="715" height="81" alt=""></span>
				<h3 class="title"><em>关于汇融易</em></h3>
				<p>

<p>汇融易是专门为中小微企业提供的标准化、开放性融资信息服务平台。它基于中国银联特约商户征信及企业投融资体系，以企业自行累积的结算交易数据作为融资信用依据，帮助企业与资金供应方之间进行融资撮合，同时提供相关的信用评估、贷款分发、还款处理、风险监控，拓展企业的融资渠道，提高企业的融资效率。</p>


<p>产品特色</p>
<p>1）多：丰富的融资产品，解决企业不同经营时期和状况下的资金需求；</p>
<p>2）易：无抵押，无担保，交易流水数据就可贷款；</p>
<p>3）快：放款快，最快2天到帐；</p>
<p>4）简：申请简单，还款便利；</P>

					
				</p>
			</div>
			<div class="clear"></div>
		</div>
	<%@include file="/template/portal/co-bank.jspf"%>
	<%@include file="/template/portal/footer.jspf"%>
	<script src="/resources/js/portal.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		$("#nav-about").addClass("cur");
	});
	</script>
	<script type="text/javascript" src="http://kf.5251.net/jsp_admin/float_adjustable.jsp?companyId=39771&style=81311&keyword=1&auto=1&locate=cn"></script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>