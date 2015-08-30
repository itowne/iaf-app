<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<body>
		<n:head styles="mp-workframe" scripts="jquery,mp-workframe"/>
		<n:ui includes="form"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;我的账号信息</p>
			</div>
			<div id="content">
				<h3 class="title"><span>修改详细信息</span></h3>
				<div class="results report">
				<s:form id="instform" action="backUserManager" namespace="/backstage/backuser" enctype="multipart/form-data">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">后台柜员信息</td>
						</tr>
						<tr>
							<td class="label">登陆名称：</td>
							<td class="data"><input  name="sessionUser.loginName" class="validate[required]" value="<s:property  value='sessionUser.loginName'/>" /><font color=red>*</font></td>
							<td class="label">真实名称：</td>
							<td class="data"><input  name="sessionUser.userName" class="validate[required]" value="<s:property  value='sessionUser.userName'/>"/><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">新密码：</td>
							<td class="data"><input type="password"  name="password" />（不修改不输入）</td>
							<td class="label">确认新密码：</td>
							<td class="data"><input type="password"  name="passwordAgain"  />（不修改不输入）</td>
						</tr>
						<tr>
							<td class="label">性别：</td>
							<td class="data">
							<s:select name="sessionUser.sexType" list="@newland.iaf.base.model.dict.SexType@values()" listKey="toString()" listValue="getDesc()" value="sessionUser.sexType"></s:select>
							<font color=red>*</font></td>
							<td class="label">地址：</td>
							<td class="data"><input  name="sessionUser.address" class="validate[required]" value="<s:property  value='sessionUser.address'/>" /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">QQ号：</td>
							<td class="data"><input  name="sessionUser.qqNum" class="validate[required,custom[onlyNumberSp]]" value="<s:property  value='sessionUser.qqNum'/>" /><font color=red>*</font></td>
							<td class="label">email：</td>
							<td class="data"><input  name="sessionUser.email" class="validate[required,custom[email]]" value="<s:property  value='sessionUser.email'/>" /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">手机号码：</td>
							<td class="data"><input  name="sessionUser.mobile" class="validate[required,custom[onlyNumberSp]]" value="<s:property  value='sessionUser.mobile'/>" /><font color=red>*</font></td>
							<td class="label">状态：</td>
							<td class="data"><s:label name="sessionUser.stat.desc"/>
							<!--<s:select name="sessionUser.stat" list="@newland.iaf.base.model.dict.InstUserStatType@values()" listKey="toString()" listValue="getDesc()" value="sessionUser.stat"></s:select>-->
							</td>
						</tr>
						<tr>
							<td class="label">注册时间：</td>
							<td class="data"><fmt:formatDate value="${sessionUser.genTime}" pattern="yyyy-MM-dd"/></td>
							<td class="label">修改时间：</td>
							<td class="data"><fmt:formatDate value="${sessionUser.updTime}" pattern="yyyy-MM-dd"/></td>
						</tr>
						
					</table>
					<div class="uop">
						<n:HcAuthButton cssClass="dark-btn" value="保存" onclick="return checkForm()" authCode="WDZHXX_BC"/>
					</div>
					</s:form>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$("#instform").validationEngine("attach",{   
				promptPosition:"centerRight"
			});
		});
		
		function checkForm(){
			if(!$("#instform").validationEngine("validate")){
				return false;
			}
			document.instform.action="${ctx}/backstage/backuser/backUserManager!updateUserInfo";	
	        document.instform.submit();
			return true;
		}
		
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
			});
			
			function doSubmit(){
				var isUpload = $("#isUpload").attr("checked");
				if(isUpload == "checked"){
					isUpload = "true";
				}else{
					isUpload = "false";
				}
				document.instform.action="${ctx}/inst/productPublish!saveProduct?isUpShelves="+isUpload;	
	           document.instform.submit();
			}
			
			function back(){
				window.location.href = "${ctx}/inst/productPublish.action";
			}
			
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>