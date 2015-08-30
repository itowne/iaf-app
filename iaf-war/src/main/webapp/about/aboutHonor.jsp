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
				<h3 class="title"><em>公司荣誉</em></h3>
<p><strong>大事记</strong></p>
<p><strong>2012年</strong></p>
<p>2月28日  天津分公司正式成立。</p> 
<p>3月9日 公司产品“金掌柜•全能电子交易管家”、“汇卡商务积分卡软件”获得由广东省科学技术厅颁发的“广东省<p>高新技术产品证书”。</p> </p>
<p>3月21日  郑州分公司正式成立。 </p>
<p>3月23日 公司正式成为广州科技服务业协会会员单位。</p> 
<p><strong>2011年</strong></p>
<p>1月1日，成都办事处正式成立。</p> 
<p>3月24日，公司成为继银商、通联之后，宁波人行批准的第三个第三方专业化服务公司。</p> 
<p>4月15日，公司首家汇卡便民服务站在中山揭牌。</p> 
<p>5月6日，宁波分公司正式成立。</p> 
<p>5月10日，公司总部乔迁新址 </p>
<p>6月1日，公司获得中国国际软件和信息服务交易会组委会颁发的《2010-2011中国软件和信息服务业创新产品<p>奖》。</p></p> 
<p>6月7日，中国银联广东分公司总经理胡莹女士一行视察公司总部，胡总题词寄语：汇卡商务，事业猛进！</p> 
<p>6月28日，佛山办事处成立。 </p>
<p>6月29日，东莞办事处成立。</p> 
<p>7月8日，上海分公司正式成立。</p> 
<p>7月21日，公司荣获广州人社局颁发“广州高校毕业生就业指导见习示范基地”称号。</p> 
<p>8月26日，金掌柜电子交易柜台项目获得广州市创新型中小企业创新基金立项，获得广州市政府支持基金。 </p>
<p>8月22日  汇卡商务与英特尔（中国）有限公司、广东省中小企业信息化创新服务中心、中国银联广东分公司以及中国<p>民生银行股份有限公司广州分行共同发起的“助力中小企业成长创新联盟”宣布成立。</p></p> 
<p>9月23日，福州分公司正式成立。</p>  
<p>12月27日 公司顺利通过ISO9001质量管理体系认证，获得认证证书。</p>
<p><strong>2010年</strong></p>
<p>5月17日，北京分公司正式成立。 </p>
<p>6月，与六家合作服务商签订服务网点协议，将服务网点扩展至全国。</p> 
<p>6月19日，“汇卡网2010年营销管理交流大会”在杭州顺利召开，并成立杭州办事处”。</p> 
<p>8月，与兴业银行、农业银行、上海chinapay等多家银行签署合作协议。</p> 
<p><strong>2009年</strong></p>
<p>6月，汇卡网新版成功上线。</p> 
<p>7月，400-628-6928全国服务热线开通。</p>
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