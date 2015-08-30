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
				<h3 class="title"><em>金掌柜</em></h3>
				<p><strong>加入金掌柜有啥好处？</strong></p>
<p>1、	金掌柜具备商品管理、订单管理、财务管理和报表管理等全面而强大的企业管理手段，它集成了商用收银机、银行POS机和远程监控等多项功能，是企业低投入、高效率实现全面信息化管理的最佳选择。</p>
<p>2、	日常经营使用金掌柜，积累流水记录，帮助企业增加申请贷款成功率及审批额度。信用即财富，流水可贷款！现在就开始积分您的信用财富吧！</p>

<p><strong>如何加入金掌柜</strong></p>
<p><strong>1．办理金掌柜•全能电子交易管家需准备什么资料？</strong></p>
<p>答：（1）商户必须提交的资质文件：</p>
<p>① 营业执照（最新年检）复印件一份</p>
<p>② 法人代表第二代身份证正反面复印件一份</p>
<p>③ 税务登记证（包含国税和地税）复印件一份 /<p>
<p>④ 组织机构代码证（非个体工商户）复印件一份</p>
<p>⑤ 结算账户复印件一份（注：开户名称必须与法人代表名称/申请单位名称一致）</p>
<p>（注：以上资料的复印件必须清晰，且均需加盖公章及法人代表签名。）</p>
<p>（2）合同文件：</p>
<p>① 合作协议(一式两份)（备注：协议内容必须正确完整填写，在盖章处均需加盖公章及法人代表签名）</p>
<p>（3）照片：</p>
<p>① 室内照（产品与营业执照综合照）</p>
 <p><img style="border:1px solid #999999;" src="../resources/images/portal/jzg1.jpg" width="425" height="319" alt=""></p>
<p>② 清晰的商户店铺门楣照（含字号、门牌号等信息）</p>
 <p><img style="border:1px solid #999999;" src="../resources/images/portal/jzg2.jpg" width="425" height="319" alt=""></p>
<p><strong>2．如果商户需要追加终端，该如何申请？</strong></p>
<p>答：同一家店分店装机，请同时提交《多台终端申请明细表》和各分店的营业执照/房屋租赁合同/房产证明复印件及各分店门楣照和室内照。</p>
<p><strong>3．办理一台金掌柜•全能电子交易管家大概需要多长时间？</strong></p>
<p>答：一般为7至9个工作日。</p>
<p><strong>4．手续费率是多少？</strong></p>
<p>答：手续费率是根据各地银联的费率标准执行，不同的行业有不同的手续费率。</p>
<p><strong>5．金掌柜•全能电子交易管家可受理的银行卡范围？</strong></p>
<p>答：金掌柜•全能电子交易管家可受理的银行卡范围严格遵照中国人民银行和中国银联的有关规定，支持带中国银联标识的银行卡。 </p>

<p>更多详情，请参考</p>
<p><a href="http://www.unionwebpay.com/html/help.html">http://www.unionwebpay.com/html/help.html</a></p>
				</p>
			</div>
			<div class="clear"></div>
		</div>
	<%@include file="/template/portal/co-bank.jspf"%>
	<%@include file="/template/portal/footer.jspf"%>
	<script type="text/javascript">
	$(function(){
		$("#nav-about").addClass("cur");
	});
	</script>
	<script type="text/javascript" src="http://kf.5251.net/jsp_admin/float_adjustable.jsp?companyId=39771&style=81311&keyword=1&auto=1&locate=cn"></script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>