<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;商户操作员详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>商户操作员详细信息</span></h3>
				<div class="results report">
				<s:if test='merch.merchType.toString()!="GOLD_SHOPKEEPER"'>
				<s:form id="merchUserform" action="merchUser!updateMerchUser" namespace="backstage/merchmanager">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">商户操作员账号：</td>
							<td class="data"><input  name="merchUser.loginName" class="validate[required]" value="<s:property  value='merchUser.loginName'/>" /><font color=red>*</font></td>
							
						</tr>
						<tr>
							<td class="label">操作员姓名：</td>
							<td class="data"><input  name="merchUser.userName" class="validate[required]" value="<s:property  value='merchUser.userName'/>"  /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">操作员账号密码：</td>
							<td class="data"><input  name="newPassword" />（不输不修改密码）</td>
						</tr>
						<tr>
							<td class="label">创建时间：</td>
							<td class="data"><fmt:formatDate value="${merchUser.genTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<td class="label">最后登录时间：</td>
							<td class="data"><fmt:formatDate value="${merchUser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<td class="label">状态：</td>
							<td class="data"><s:label name="merchUser.merchStat.desc"/></td>
						</tr>
					</table>
					<div class="uop">
						<input type="hidden" name="imerch" id="imerch" value="<s:property value="merchUser.imerch"/>"/>
						<n:HcAuthButton cssClass="dark-btn" value="修改" onclick="return checkForm();" authCode="SHGL_SHZLGL_XG"/>
						<s:if test='merchUser.merchStat.toString()=="USED"'>
						<n:HcAuthButton cssClass="dark-btn" onclick="jinyong()" value="停用" authCode="SHGL_SHZLGL_JY"/>
						</s:if>
						<s:else>
						<n:HcAuthButton cssClass="dark-btn" onclick="qiyong()" value="启用" authCode="SHGL_SHZLGL_QY"/>
						</s:else>
						<input type="button" class="dark-btn" value="取消" onclick="back()"/>
					</div>
					</s:form>
					</s:if>
					<s:else>
					<div align="center">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
					<tr>
						<td class="label"><font color=red>*</font>提示:</td>
						<td class="data">金掌柜商户密码修改功能，暂未开通，如需修改登陆密码，请在金掌柜管理系统上进行修改.</td>
					</tr>
					</table>
					</div>
					<div align="center" style="margin-top: 10px"><input type="button" class="dark-btn" value="返回" onclick="back()"/></div>
					</s:else>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$("#merchUserform").validationEngine("attach",{   
				promptPosition:"centerRight"
			});
		});
		
		
		function checkForm(){
			if(!$("#merchUserform").validationEngine("validate")){
				return false;
			}
			if(confirm("是否要修改操作员信息?")){
				document.merchUserform.submit();
			}
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

			function back(){
				var imerch=$("#imerch").val();
				window.location.href="${ctx}/backstage/merchmanager/merchUser!userDetail?imerch="+imerch;
			}
			
			function jinyong(){
				if(confirm("是否确定要禁用该商户用户?")){
					document.merchUserform.action="${ctx}/backstage/merchmanager/merchUser!disablemerchUser";	
			        document.merchUserform.submit();
				}
			}
			function qiyong(){
				if(confirm("是否确定要启用该商户用户?")){
					document.merchUserform.action="${ctx}/backstage/merchmanager/merchUser!enablemerchUser?";	
			        document.merchUserform.submit();
				}
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>