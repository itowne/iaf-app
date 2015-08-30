<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>汇融易 - 我要借款</title>
<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
<n:head styles="portal-base,portal-hp" scripts="jquery"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/mp-workframe.css" />
<!--[if IE]><script src="resources/js/html5.js" type="text/javascript"></script><![endif]-->
<style type="text/css">
.small-title {
	padding: 5px 55px;
	font-size: 15px;
}

#content .results p {
	line-height: 28px;
	padding-left: 54px;
}
</style>
</head>
<body id="body">
	<%@include file="/template/portal/header.jspf"%>
	<div id="main" class="iaf-grid">
		<div id="content">
			<h3 class="title">
				<span style="font-size:15px;">机 构 详 情</span>&nbsp;&nbsp;
			</h3>
			<div class="results report" style="padding:10px 0 10px 50px;">
				<h3 class="small-title">
					<s:property value="inst.instName" />
				</h3>
				<table cellspacing="0" cellpadding="0" class="data-table" style="margin:10px 30px;width: 80%;">
					<tr>
						<td class="label" width="20%">机构名称</td>
						<td class="data" width="30%"><s:property value="inst.instName" /></td>
						<td class="label" width="20%">融资性机构经营许可证</td>
						<td class="data" width="30%"><s:property value="inst.loanPermit" /></td>
					</tr>
					<tr>
						<td class="label">注册时间</td>
						<td class="data"><fmt:formatDate value="${inst.regTime}" pattern="yyyy-MM-dd"/></td>
						<td class="label">注册资金(万元)</td>
						<td class="data"><s:property value="inst.regCapital" /></td>
					</tr>
					<tr>
						<td class="label" >营业执照号</td>
						<td class="data"><s:property value="inst.busiLicense" /></td>
						<td class="label">税务登记号</td>
						<td class="data"><s:property value="inst.taxRegNo" /></td>
					</tr>	
					<tr>
						<td class="label">开业时间</td>
						<td class="data"><fmt:formatDate value="${inst.openTime}" pattern="yyyy-MM-dd"/></td>
						<td class="label">机构性质</td>
						<td class="data"><s:property value="inst.instNature" /></td>
					</tr>
					<tr>
						<td class="label">联系人</td>
						<td class="data"><s:property value="inst.contact" /></td>
						<td class="label">联系电话</td>
						<td class="data"><s:property value="inst.contactPhone" /></td>
					</tr>
					<tr>
						<td class="label">注册地址</td>
						<td class="data"><s:property value="inst.regAddr" /></td>
						<td class="label">外文名称</td>
						<td class="data"><s:property value="inst.englishName" /></td>
					</tr>	
					<tr>
						<td class="label">经营范围</td>
						<td class="data"><s:property value="inst.busiRegion" /></td>
						<td class="label">公司口号</td>
						<td class="data"><s:property value="inst.catchword" /></td>
					</tr>	
					<tr>
						<td class="label">员工数</td>
						<td class="data"><s:property value="inst.peopleCount" /></td>
						<td class="label">官网</td>
						<td class="data"><s:property value="inst.officialWebsite" /></td>
					</tr>
					<tr>
						<td class="label">官方电话</td>
						<td class="data"><s:property value="inst.shortPhone" /></td>
						<td class="label">总资产(万元)</td>
						<td class="data"><s:property value="inst.totalCapital" /></td>
					</tr>	
					<tr>
						<td class="label">内部编号</td>
						<td class="data"><s:property value="inst.instId" /></td>
						<td class="label">QQ号</td>
						<td class="data"><s:property value="inst.qqUid" /></td>
					</tr>	
					<tr>
						<td class="label">机构简介</td>
						<td class="data" colspan="3"><s:property value="inst.introduce" /></td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" class="dark-btn" value="返回" onclick="back();" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$(".dark-btn").hover(function(){
				$(this).addClass("dark-btn-hover");
			},function(){
				$(this).removeClass("dark-btn-hover");
			});
		});
		function back(){
			window.location.href = "${ctx}/homePage!loanDetail?id="+<s:property value="id" />;
		}
	</script>
	<%@include file="/template/portal/co-bank.jspf"%>
	<%@include file="/template/portal/footer.jspf"%>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>