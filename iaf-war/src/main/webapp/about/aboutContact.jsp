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
		<link rel="stylesheet" type="text/css" href="../resources/css/mp-workframe.css"/>
		<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
		<style type="text/css">
			.form {
  		  color: #666666;
   		 font-size: 12px;
			}
			.blue1 {
    color: #07679C;
    font-size: 12px;
    text-decoration: none;
}
	.blue3 {
    color: #07679C;
    font-size: 14px;
    font-weight: bold;
    text-decoration: none;
}
#tech_1 {
    display: inline;
    float: left;
    height: auto;
    margin-left: 8px;
    width: 700px;
}
#tech_3b {
    display: inline;
    float: left;
    height: auto;
    line-height: 22px;
    margin-left: 15px;
    margin-top: 15px;
    width: 685px;
}
#tech_6b {
    background: none repeat scroll 0 0 #CCDDE7;
    display: inline;
    float: left;
    height: auto;
    line-height: 22px;
    margin: 5px 5px 15px;
    padding: 5px;
    width: 650px;
}
		</style>
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
	<div id="main" class="iaf-grid">
			<%@include file="aboutSidenav.jsp" %>
            <div id="main-con">
				<span><img src="../resources/images/portal/help-ads.jpg" width="715" height="81" alt=""></span>
				<h3 class="title"><em>联系我们</em></h3>
				<!-- <p><img style="border:1px solid #999999;" src="../resources/images/portal/about-contact.jpg" width="715" height="450" alt=""></p>-->
							<div id="tech_3b">
							<img width="241" height="71" src="../resources/images/portal/400.gif">
			<br>
			<span class="blue3">公司总部</span>
			<br>
			<span class="form">电 话：020-85657833      传 真：020-85657834</span>
			<br>
			<span class="form">E-mail：</span>
			<a href="mailto:service@unionwebpay.com">service@unionwebpay.com</a>
			<br>
			<span class="form">金掌柜：</span>
			<a target="_blank" href="http://www.unionwebpay.com">http://www.unionwebpay.com</a>
			<br>
			<span class="form">汇卡网：</span>
			<a target="_blank" href="http://www.hi-card.cn">http://www.hi-card.cn</a>
			<br>
			<span class="form">汇融易：</span>
			<a target="_blank" href="https://www.uniondai.com">https://www.uniondai.com</a>
			<br>
			<span class="form">公司总部地址：广州市天河区中山大道西299号大舜丽池酒店20楼(510665) </span>
			<br>
			<span class="form">
			<br>
			</span>
			</div>
			<div id="tech_6b">
			<span class="blue3">  公司分部：</span>
			<br>
			<table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#CCDDE7" style="float:left">
			<tbody>
			<tr bgcolor="#FFFFFF">
			<td class="blue1" width="14%" height="25" align="center">省市 </td>
			<td class="blue1" width="17%" align="center">固话</td>
			<td class="blue1" width="17%" height="25" align="center">传真</td>
			<td class="blue1" width="52%" align="center">地址</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center">广州分公司</td>
			<td class="form" bgcolor="#FFFFFF" align="center">020-85668917</td>
			<td class="form" bgcolor="#FFFFFF" align="center">
			<td class="form" bgcolor="#FFFFFF" align="center">广州市天河软件园建中路62号迪宝大厦首层（510665）</td>
			</tr>
			<tr valign="middle">
			<td class="form" bgcolor="#FFFFFF" align="center">北京分公司</td>
			<td class="form" bgcolor="#FFFFFF" align="center">010-62249026</td>
			<td class="form" bgcolor="#FFFFFF" align="center">010-62249026</td>
			<td class="form" bgcolor="#FFFFFF" align="center">北京市海淀区交大东路66号院2号楼15层1538室</td>
			</tr>
			<tr valign="middle">
			<td class="form" bgcolor="#FFFFFF" align="center">天津分公司</td>
			<td class="form" bgcolor="#FFFFFF" align="center">
			<td class="form" bgcolor="#FFFFFF" align="center">022-28257501</td>
			<td class="form" bgcolor="#FFFFFF" align="center">天津市河西欧澳景大厦1门1502号</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center" rowspan="2">福州分公司</td>
			<td class="form" bgcolor="#FFFFFF" align="center" rowspan="2">0591-87767959</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0591-87767959</td>
			<td class="form" bgcolor="#FFFFFF" align="center">福州市鼓楼区东街96、98号东方大厦13层B2区</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center" rowspan="2">0574-87743199</td>
			<td class="form" bgcolor="#FFFFFF" align="center" rowspan="2">浙江省宁波市海曙区大来街47号亚细亚B座705室（315010）</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center">宁波分公司</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0574-87743199</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center">上海分公司</td>
			<td class="form" bgcolor="#FFFFFF" align="center">021-54590109</td>
			<td class="form" bgcolor="#FFFFFF" align="center">021-54590109</td>
			<td class="form" bgcolor="#FFFFFF" align="center">上海市徐汇区钦州路820号318室（200436）</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center">郑州分公司</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0371-65679099</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0371-65679099</td>
			<td class="form" bgcolor="#FFFFFF" align="center">河南省郑州市纬四路东段八号建业广场1号楼1905室</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center">武汉分公司</td>
			<td class="form" bgcolor="#FFFFFF" align="center">027-82288233</td>
			<td class="form" bgcolor="#FFFFFF" align="center">027-82288233</td>
			<td class="form" bgcolor="#FFFFFF" align="center">武汉市江汉区花楼街198号宝利金国际广场C2栋30楼07号</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center">佛山办事处</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0757-82064768</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0757-82064768</td>
			<td class="form" bgcolor="#FFFFFF" align="center">佛山市禅城区季华六路3号九鼎国际城2区4座301房</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center">中山办事处</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0760-88391994</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0760-88391994</td>
			<td class="form" bgcolor="#FFFFFF" align="center">中山市中山四路78号东区软件园首层11卡</td>
			</tr>
			<tr>
			<td class="form" bgcolor="#FFFFFF" align="center">东莞办事处</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0769-28631778</td>
			<td class="form" bgcolor="#FFFFFF" align="center">0769-28631778</td>
			<td class="form" bgcolor="#FFFFFF" align="center">东莞市南城区元美中心B2栋503</td>
			</tr>
			</tbody>
			</table>
			</div>
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