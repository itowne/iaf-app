<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<n:head styles="mp-workframe,portal-base,portal-hp"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
		<!--[if IE]><script src="resources/js/html5.js" type="text/javascript"></script><![endif]-->
		<style type="text/css">
			.small-title{
				padding:5px 55px;font-size:15px;
			}
			#prods .results p{
				line-height:28px;padding-left:54px;
			}
			#xxoo td{
				text-align:left;padding:5px 60px;letter-spacing:3px;
			}
		</style>
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
		<div id="main" class="iaf-grid">
			<div id="prods">
				<h3 class="title" style="padding:10px 10px 0 50px;">
					<span style="font-size:15px;">公 告 详 情</span>&nbsp;&nbsp;
				</h3>
				<div class="results report" style="padding:10px 0 10px 50px;">
					<h3 class="small-title"><s:property value="instNotice.title"/></h3>
					
					<p><s:property value="instNotice.content" escapeHtml="false"/></p>
					<br/><br/>
	    		</div>
	 	</div>
	 </div>
	 <%@include file="/template/portal/co-bank.jspf" %>
	<%@include file="/template/portal/footer.jspf" %>
	</body>
</html>