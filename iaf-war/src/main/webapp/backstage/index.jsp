<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/nl-tags" prefix="n" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 汇卡管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/loadmask/jquery.loadmask.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/mp-base.css"/>
		
		<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>
		<script src="${ctx}/resources/ui/jquery.ui.js" type="text/javascript"></script>
		<script src="${ctx}/resources/loadmask/jquery.loadmask.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mp-main.js" type="text/javascript"></script>

		
		
		<style type="text/css">
			.infolist .col0{width:75px;}
			.infolist .col1{width:130px;}
			.infolist .col2{width:120px;}
			.infolist .col3{width:65px;}
			.infolist .col4{width:126px;}
			.infolist .col5{width:75px;}
			.input_text{
				border:font-size:14px;padding:4px 5px 4px;
				vertical-align: middle;
				width:150px;
		     }
		     .ui-jqgrid-btable a {color: #03309D}
		</style>
		
		<!--[if lte IE 7]><link rel="stylesheet" type="text/css" href="../resources/css/mp-patch.css"/><![endif]-->
		<!--[if IE]><script src="../resources/js/html5.js" type="text/javascript"></script><![endif]-->
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
						<li class="help"><a href="/help/index.jsp" target="_blank">帮&nbsp;助</a></li>
						<li class="logout"><a href="/iafLogin!logout" target="_top">退&nbsp;出</a></li>
					</ul>
				</div>
				<div class="clear"></div>
			</header>
			<div id="main">
				<aside id="sidebar">
					<menu id="menu">
						<h3 class="title">汇卡管理菜单</h3>
						<ul class="parent">
					 <n:iafmenu></n:iafmenu>
					</ul>
					</menu>
				</aside>
				<div class="outer clearfix">
					<iframe id="workframe" name="workframe" src="<s:property value='#request.target'/>" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
				</div>
			</div>
			<!-- 
			<div id="main" class="clearfix">
				<aside id="sidebar">
					<menu id="menu">
						<h3 class="title">汇卡管理菜单</h3>
						<ul class="parent">
							<li>
								<span class="top-href">商户管理</span>
								<ul class="sub">
									<li><a href="/backstage/merchmanager/merchManager">商户资料管理</a></li>
									<li><a href="/backstage/merchcreditreport/backMerchCredit">经营数据报告管理</a></li>
									<li><a href="/backstage/backMerchAppeal">经营数据报告申诉</a></li>
									<li><a href="/backstage/merchmanager/frozenMerch">被冻结商户</a></li>
								</ul>
							
							</li>
							<li>
								<span class="top-href">机构管理</span>
								<ul class="sub">
									<li><a href="/backstage/instmanager/instApplyRequest">机构申请</a></li>
									<li><a href="/backstage/instmanager/instManager">机构管理</a></li>
									<li><a href="/backstage/instmanager/backstageInstUser!instManagerIndexUser">机构操作员</a></li>
									<li><a href="/backstage/instmanager/instRole!instRoleIndex">机构角色</a></li>
								</ul>
							</li>
							<li><a href="/backstage/product/instProduce" class="top-href">贷款产品</a></li>
							<li><a href="/backstage/creditCheck" class="top-href">放款对账</a></li>
							<li><a href="/backstage/refundCheck" class="top-href">还款对账</a></li>
							<li><a href="/backstage/freezeCheck" class="top-href">冻结管理</a></li>
							<li><a href="/backstage/loanOrdMgr" class="top-href">借贷订单</a></li>
							<li><a href="/backstage/creditMgr" class="top-href">放款记录</a></li>
							<li><a href="/backstage/refundMgr" class="top-href">还款记录</a></li>
							<li><a href="/backstage/notifymanager/notifyManager" class="top-href">公告管理</a></li>
							<li>
								<span class="top-href">系统参数设置</span>
								<ul class="sub">
									<li><a href="/backstage/sysparam/sysParam!transLog">交易记录</a></li>
									<li><a href="/backstage/sysparam/sysParam!merchBaseInfo">商户基本信息</a></li>
									<li><a href="/backstage/sysparam/sysParam!installLog">安装记录</a></li>
									<li><a href="/backstage/sysparam/sysParam!serviceLog">巡检记录</a></li>
									<li><a href="/backstage/sysparam/sysParam!jumpTicket">跳转密钥</a></li>
									<li><a href="/backstage/sysparam/sysParam!payUrl">金掌柜支付网关</a></li>
									<li><a href="/backstage/sysparam/sysParam!smsEdit">短信网关</a></li>
									<li><a href="/backstage/sysparam/sysParam!pwdCheck">金掌柜密码校验</a></li>
									<li><a href="/backstage/sysparam/sysParam!riskControl">风险参数</a></li>
									<li><a href="/backstage/sysparam/sysParam!imageUrl">图片上传设置</a></li>
									<li><a href="/backstage/sysparam/sysParam!expireDate">失效日期设置</a></li>
								</ul>
							</li>
							<li><a href="/backstage/transmanage/transmanage" class="top-href">系统维护</a></li>
							<li>
								<span class="top-href">系统管理</span>
								<ul class="sub">
									<li><a href="/backstage/backuser/backUserManager!userInfo">我的账号信息</a></li>
									<li><a href="/backstage/backuser/backUserManager">操作账号管理</a></li>
									<li><a href="/backstage/backstagerole/backstageRole">角色管理</a></li>
									<li><a href="/backstage/backstagelog/backstageLog">操作日志</a></li>
								</ul>
							</li>
						</ul>
					</menu>
				</aside>
				<div class="outer clearfix">
					<iframe id="workframe" name="workframe" src="backstage/welcome.jsp" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
				</div>
			</div>
		</div>
		-->
		<footer id="footer" style="position:fixed;bottom:0px;width:100%">
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
		<script type="text/javascript">
			$(function(){
				window.setTimeout(reinitIframe,300);
				//$("#menu .top-href").hover(function(){
					//$(this).parent().addClass("top-li-hover");
				//},function(){
					//$(this).parent().removeClass("top-li-hover");
				//});
				//$("#menu span.top-href").click(function(){
					//$(this).toggleClass("unfolded").next(".sub").slideToggle();
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
		<!--[if IE 6]><script src="../resources/js/ui/jquery.bgiframe.js" type="text/javascript"></script><![endif]-->
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>