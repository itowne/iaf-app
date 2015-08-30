<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/template/mp/meta.jspf" %>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 商户管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="${ctx }/resources/ui/jquery.ui.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx }/resources/loadmask/jquery.loadmask.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/mp-base.css"/>
		<!--[if lte IE 7]><link rel="stylesheet" type="text/css" href="${ctx }/resources/css/mp-patch.css"/><![endif]-->
		<!--[if IE]><script src="${ctx }/resources/js/html5.js" type="text/javascript"></script><![endif]-->
		<base target="workframe"/>
		<script language="JavaScript">
  		 javascript:window.history.forward(1);
		</script>
	</head>
	<body id="body">
		<div id="wrapper">
			<header id="header">
				<hgroup id="site">
					<h1 id="site-title"><span>汇融易</span></h1>
					<h2 id="site-description"><span>全国首家只需经营流水即可借贷的投融资服务平台</span></h2>
				</hgroup>
				<div id="guide">
					<ul>
						<li class="home"><a href="/homePage" target="_blank">网站首页</a></li>
						<li class="about"><a href="/about/about.jsp" target="_blank">关于我们</a></li>
						<li class="help"><a href="/help/index.jsp"  target="_blank">帮&nbsp;助</a></li>
						<li class="logout"><a href="/merchLogin!logout" target="_top">退&nbsp;出</a></li>
					</ul>
				</div>
				<div class="clear"></div>
			</header>
			<div id="main">
				<aside id="sidebar">
					<menu id="menu">
						<h3 class="title">商户管理菜单</h3>
						<ul class="parent">
							<li><a href="/merch/welcome" class="top-href">我的首页</a></li>
							<li>
								<span class="top-href">我要借款</span>
								<ul class="sub">
									<li><a href="/merch/merchProdReq.action">借款申请</a></li>
									<li><a href="/merch/merchDebitBid">借款发布</a></li>
								</ul>
							</li>
							<li><a href="/merch/merchMyReq.action" class="top-href">我的申请</a></li>
							<li><a href="/merch/merchMyRefund.action" class="top-href">我要还款</a></li>
							<li>
								<span class="top-href">我的关注</span>
								<ul class="sub">
									<li><a href="/merch/merchCollPdt.action">贷款产品</a></li>
									<li><a href="/merch/merchCollInst">贷款机构</a></li>
								</ul>
							</li>
							<li>
								<span class="top-href">我的资料</span>
								<ul class="sub">
									<li><a href="/merch/merchCreditReport!initReport">经营数据报告管理</a></li>
									<li><a href="/merch/merchPwd!input">修改密码</a></li>
								</ul>
							</li>
							<li><a href="/merch/merchOperLog.action" class="top-href">操作日志</a></li>
						</ul>
					</menu>
					<div id="tools" class="clearfix">
						<a href="calculator.jsp" target="_blank" class="calculator">贷款计算器</a>
						<a href="/merch/assessment" target="_blank" class="assessment">借款评估</a>
					</div>
				</aside>
				<div class="outer clearfix">
					<iframe id="workframe" name="workframe" src="<s:property value='#request.target'/>" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
				</div>
			</div>
		</div>
		<footer id="footer">
			<div class="copyright">
				Copyright&nbsp;©&nbsp;2013-2015&nbsp;&nbsp;广东汇卡商务服务有限公司&nbsp;&nbsp;版权所有&nbsp;&nbsp;粤ICP备&nbsp;&nbsp;11093203号-4
			</div>
			<div class="links">
				<a href="/homePage" target="_blank">首页</a><span class="sep">|</span>
				<a href="/about/about.jsp" target="_blank">关于我们</a><span class="sep">|</span>
				<a href="/about/aboutContact.jsp" target="_blank">联系我们</a><span class="sep">|</span>
				<a href="/about/aboutJoin.jsp" target="_blank">加入我们</a><span class="sep">|</span>
				<a href="/help/index.jsp" target="_blank">帮助中心</a> <span class="sep">|</span>
				<a href="/about/aboutPartner.jsp" target="_blank">合作伙伴</a><br/>
			</div>
		</footer>
		<script type="text/javascript" src="${ctx }/resources/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx }/resources/ui/jquery.ui.js"></script>
		<!--[if IE 6]><script type="text/javascript" src="${ctx }/resources/js/ui/jquery.bgiframe.js"></script><![endif]-->
		<script type="text/javascript" src="${ctx }/resources/loadmask/jquery.loadmask.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/mp-main.js"></script>
		<script type="text/javascript">
			$(function(){
				window.setTimeout(reinitIframe,300);
				//$("#menu .top-href").hover(function(){
					//$(this).parent().addClass("top-li-hover");
				//},function(){
					//$(this).parent().removeClass("top-li-hover");
				//});
				
				$("#menu .top-href").click(function(){
					//$("#menu .top-href").unbind("mouseenter").unbind("mouseleave");
					$("#menu a.top-href").parent().removeClass("top-li-hover");
					$("#menu span.top-href").parent().removeClass("top-li-hover");
					$("#menu .sub a").removeClass("cur");
					$(this).parent().addClass("top-li-hover");
				});
				
				$("#menu .sub a").click(function(){
					$("#menu .sub a").removeClass("cur");
					$("#menu a.top-href").parent().removeClass("top-li-hover");
					$("#menu span.top-href").parent().removeClass("top-li-hover");
					$(this).addClass("cur");
				});
								
				$("#menu span.top-href").click(function(){
					$("#menu span.top-href").removeClass("unfolded");
					$("#menu span.top-href").next(".sub").hide();
					$(this).toggleClass("unfolded").next(".sub").toggle();
				});
				$("#menu a.top-href").click(function(){
					$("#menu span.top-href").removeClass("unfolded");
					$("#menu span.top-href").next(".sub").hide();
				});
			});
		</script>
		<script type="text/javascript" src="http://kf.5251.net/jsp_admin/float_adjustable.jsp?companyId=39771&style=81311&keyword=1&auto=1&locate=cn"></script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>