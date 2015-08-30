<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<jsp:include page="/template/head.jsp" />
<%@ taglib uri="/nl-tags" prefix="n" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<n:head styles="mp-workframe" />
		<title>汇融易 - 商户管理后台</title>
<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
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
	</head>
	<body>
	<div id="workframe-wrapper" class="clearfix">
			<div id="content" class="report">
				<div class="report-tab-area">
					<div class="tab-content">
					<h3 class="title" style="line-height:42px;text-align:center;font-size:15px;"><s:property value="instNotice.title"/></h3>
					<div style="margin:20px;">
					<s:property value="instNotice.content" escapeHtml="false"/>
					</div>
				</div>
			</div>
		</div>
	</div>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>