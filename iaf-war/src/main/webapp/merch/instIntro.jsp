<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 商户管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/mp-workframe.css"/>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：我要借款&nbsp;&gt;&gt;&nbsp;借贷申请&nbsp;&gt;&gt;&nbsp;机构详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>机 构 详 情</span></h3>
    <div class="results report" style="padding:10px 0 10px 50px;">
					<table cellspacing="0" cellpadding="0" class="data-table" style="margin:10px 30px;width: 80%;">
					<tr>
						<td class="label" width="20%">机构名称:</td>
						<td class="data" width="30%"><s:property value="inst.instName" /></td>
						<td class="label" width="20%">机构英文名称:</td>
						<td class="data" width="30%"><s:property value="inst.englishName" /></td>
					</tr>
					<tr>
						<td class="label">机构性质:</td>
						<td class="data"><s:property value="inst.instNature" /></td>
						<td class="label">经营范围:</td>
						<td class="data"><s:property value="inst.busiRegion" /></td>
					</tr>
					<tr>
						<td class="label">注册时间:</td>
						<td class="data"><fmt:formatDate value="${inst.regTime}" pattern="yyyy-MM-dd"/></td>
						<td class="label">开业时间:</td>
						<td class="data"><fmt:formatDate value="${inst.openTime}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td class="label">联系人:</td>
						<td class="data"><s:property value="inst.contact" /></td>
						<td class="label">联系电话:</td>
						<td class="data"><s:property value="inst.contactPhone" /></td>
					</tr>
					<tr>
						<td class="label">在线联系QQ号码:</td>
						<td class="data"><s:property value="inst.qqUid" /></td>
						<td class="label">官方电话:</td>
						<td class="data"><s:property value="inst.shortPhone" /></td>
					</tr>	
					<tr>
						
						<td class="label">注册地址:</td>
						<td class="data"><s:property value="inst.regAddr" /></td>
						<td class="label">员工人数:</td>
						<td class="data"><s:property value="inst.peopleCount" /></td>
					</tr>	
					<tr>
						<td class="label">公司口号:</td>
						<td class="data"><s:property value="inst.catchword" /></td>
						<td class="label">公司网址:</td>
						<td class="data"><s:property value="inst.officialWebsite" /></td>
					</tr>	
					<tr>
						<td class="label">机构简介:</td>
						<td class="data" colspan="3" style="width:250px;word-wrap:break-word;word-break:break-all"><s:property value="inst.introduce" /></td>
					</tr>
					<tr>
						<td class="label">其他资料:</td>
						<td class="data" colspan="3" align="center">
						<s:iterator value="tfileList" id="tfiles" status="status"> 
      							<s:if test="#tfiles.metaType.indexOf('image')>-1">
      							<img src="/DrawImage.do?id=${tfiles.ifile}&type=exp" width="250" height="168"/>&nbsp;&nbsp;<br/>
      							</s:if>
      							<s:else>
      							<a href="/DrawImage.do?id=${tfiles.ifile}&type=exp">${tfiles.uploadName}</a><br/>
      							</s:else>
      							</s:iterator>
						</td>
					</tr>
				</table>
				</div>
				<div align="center"><input type="button" class="dark-btn" value="返回" onclick="back();" /></div>
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
				window.location.href="${ctx}/merch/merchProdReq!detail?index="+<s:property value="index" />;
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>