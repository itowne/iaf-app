<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<title>汇融易 - 机色管理后台</title>
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

<body style="position:relative;">
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：我的关注&nbsp;&gt;&gt;&nbsp;贷款机构</p>
  </div>
  <div id="content" class="report">
  <h3 class="title"><span>机构信息</span></h3>
	<div id="basic-info" style="padding:0 30px;">
		<div style="height:36px;text-align:right;padding-right:20px;">
			<input type="button" class="dark-btn" onclick="myreq()" value="产品介绍"/>
		</div>
		<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table report">
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
  	
    <div style="margin:15px auto;text-align:center;">
    		<input type="hidden" name="iinst" id="iinst" value="<s:property value="%{inst.iinst}"/>">
    		<!--  <input type="submit" class="dark-btn" value="我要申请" onclick="myreq()"/>-->
    		<input type="submit" class="dark-btn" value="返回" onclick="back()"/>
    </div>
</div>
</div>
<script type="text/javascript">
			$(function(){
				$(".data-list tr:even").addClass("even");
				$(".data-list tr").hover(function(){
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				});
				$(".dark-btn").hover(function(){
					$(this).addClass("dark-btn-hover");
				},function(){
					$(this).removeClass("dark-btn-hover");
				});
				//$("#tab").height($("#workframe-wrapper").height());
				var h = $("#workframe-wrapper").height();
				if(h < 648){
					$("#workframe-wrapper").height(700)
				}
			});
			function back(){
				window.location.href="${ctx}/merch/merchCollInst!returnAction";
			}
			
			function myreq(){
				var iinst = $("#iinst").val();
				window.location.href="${ctx}/merch/merchProdReq?iinst="+iinst;
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>