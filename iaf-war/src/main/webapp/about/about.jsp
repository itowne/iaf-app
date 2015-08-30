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
				<h3 class="title"><em>关于我们</em></h3>
				<p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公司简介</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广东汇卡商务服务有限公司（以下简称汇卡商务），作为现代信息服务业中的金融科技类专业化服务公司，主要从事电子支付网络的应用开发与运营推广，并面向商业零售、专业批发市场、物流行业、大型（连锁）集团，提供包括银行卡收单在内的收银管理一体化、资金结算、数据管理和电子商务集成等第三方专业服务，致力成为国内领先的现代信息服务企业。 </p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;汇卡商务总部设在广州市，总部现有2000多平方米的办公场所，300多人从事设计、开发、安装、维护等工作。公司分支机构遍及广州、北京、福州、宁波、杭州、上海、长沙、武汉、天津、郑州、佛山、中山、成都、兰州等城市，并在全国31个省市设有特约维护服务网点。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作为一家优秀的科技创新型服务企业，汇卡商务在金融电子化方面积累了大量的成功经验，在电子支付领域相关应用系统的开发和推广中优势明显。凭借敏锐的市场触角和创新的技术实力，公司成为中国银联、各大银行的优质合作伙伴，并与英特尔、戴尔、联想、中国电信等结成长期稳定的合作伙伴关系。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;汇卡商务拥有一支技术精湛、基础扎实、经验丰富的公司团队，这支团队秉承“严谨务实、高效创新、诚信守法”的经营理念，以“为社会创造效益，为客户创造价值，为员工创造机会”为企业宗旨，以“客户至上，服务制胜”为工作精神，一如既往地专注于电子交易支付和信息处理领域，凭借多年的专业素养和服务经验为金融业、公共服务业和其他电子支付应用领域提供一流的网络设计、系统集成、技术支持、终端市场推广、维护服务等全面解决方案。</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;汇卡商务秉承“助力企业，释放能量”的经营理念，以专业的人才素质、过硬的技术手段和锐意创新的企业精神，与客户和员工一起，描绘精彩未来！</p>
					
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