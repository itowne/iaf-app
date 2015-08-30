<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/nl-tags" prefix="n" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<n:head styles="portal-base,portal-hp" scripts="jquery" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
		<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
	<div id="main" class="iaf-grid">
			<aside id="sidenav">
              <ul>
                <li class="sidenav-bg"><span class="sidenav-title">借款商户帮助</span>
                 <ul>
                  <li><a href="${ctx}/help/help.jsp" target="helpframe">如何注册加入</a></li>
                  <li><a href="${ctx}/help/helpCal.jsp" target="helpframe">利息计算器</a></li>
                  <li><a href="${ctx}/help/helpLoan.jsp" target="helpframe">如何申请借款</a></li>
                  <li><a href="${ctx}/help/helpMerchFaq.jsp" target="helpframe">常见问题</a></li>
                 </ul>
                </li>
                <li class="sidenav-bg"><span class="sidenav-title">信贷机构帮助</span>
                 <ul>
                  <li><a href="${ctx}/help/helpApply.jsp" target="helpframe">如何申请加入</a></li>
                  <li><a href="${ctx}/help/helpAccept.jsp" target="helpframe">如何受理</a></li>
                  <li><a href="${ctx}/help/helpLand.jsp" target="helpframe">如何放贷</a></li>
                  <li><a href="${ctx}/help/helpInstFaq.jsp" target="helpframe">常用问题</a></li>
                 </ul>
                </li>
              </ul>
			</aside>
            <div class="outer clearfix">
				<iframe id="helpframe" name="helpframe" src="/help/help.jsp" width="75%" height="100%" scrolling="no" frameborder="0" onload="this.height=150"></iframe>
			</div>
		</div>
	<%@include file="/template/portal/co-bank.jspf"%>
	<%@include file="/template/portal/footer.jspf"%>
	<script src="/resources/js/portal.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		$("#nav-help").addClass("cur");
		window.setInterval("reinitIframe()", 200);
	});
	function reinitIframe(){
		var iframe = document.getElementById("helpframe");
		try{
		var bHeight = iframe.contentWindow.document.body.scrollHeight;
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		var height = Math.max(bHeight, dHeight);
		iframe.height =  height;
		}catch (ex){}
	}
	</script>
	<script type="text/javascript" src="http://kf.5251.net/jsp_admin/float_adjustable.jsp?companyId=39771&style=81311&keyword=1&auto=1&locate=cn"></script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>