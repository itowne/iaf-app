<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<n:head styles="mp-workframe" scripts="jquery,mp-workframe"/>
		<n:ui includes="form"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
		<meta charset="utf-8"/>
		<title>汇融易 - 机构管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;操作员管理&nbsp;&gt;&gt;&nbsp;账号详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>账号详情信息</span></h3>
				<div class="results report">
				<s:form id="storeform" action="/backstage/instmanager/backstageInstUser!instUserUpdate" >
					<table width="50%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">账号名称：</td>
							<td class="data"><s:label name="instUser.loginName"/></td>
						</tr>
						<tr>
							<td class="label"><font style="color:red">*</font>使用者姓名：</td>
							<td class="data"><s:textfield id="userName" cssClass="validate[required,minSize[2]]" name="instUser.userName" maxLength="30"/></td>
						</tr>
						<tr>
							<td class="label"><font style="color:red">*</font>所属角色：</td>
							<td class="data">
								<select id="role" name="role">
									<option value="">请选择所属角色</option>
									<s:iterator var="roleList" value="instRoleList">
									<option value="<s:property value="#roleList.getiInstRole()"/>"><s:property value="#roleList.getRoleName()"/></option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td class="label">使用者职务：</td>
							<td class="data"><s:textfield name="instUser.position" id="position"/></td>
						</tr>
						<tr>
							<td class="label">电子邮箱：</td>
							<td class="data"><s:textfield cssClass="validate[custom[email]]" id="Mail" name="instUser.Mail"/></td>
						</tr>
						<tr>
							<td class="label">QQ号码：</td>
							<td class="data"><s:textfield cssClass="validate[custom[onlyNumberSp]]" id="QQNum" name="instUser.QQNum" /></td>
						</tr>
						<tr>
							<td class="label"><font style="color:red">*</font>手机号码：</td>
							<td class="data"><s:textfield cssClass="validate[required,custom[onlyNumberSp]]" id="phone" name="instUser.phone"/></td>
						</tr>
						<tr>
							<td class="label"><font style="color:red">*</font>密码设置：</td>
							<td class="data"><s:password id="password" name="passwd" /></td>
						</tr>
						<tr>
							<td class="label"><font style="color:red">*</font>再次输入密码：</td>
							<td class="data"><s:password id="confirmpassword" name="confirmpassword"/></td>
						</tr>
					</table>
					<div class="uop">
						<input type="hidden" id="oldRole" name="oldRole" value="<s:property value="%{role}"/>"/>
						<input type="hidden" id="iinst" name="iinst" value="<s:property value="%{instUser.iinst}"/>"/>
						<!-- <input type="hidden" id="index" name="index" value="<s:property value="instUser.iinstUser"/>"/> -->
						<n:HcAuthButton cssClass="dark-btn" onclick="return checkform()" value="修&nbsp;&nbsp;改" authCode="JGGL_JGCZY_XG"/>
						<s:if test='instUser.usrStat.toString()=="USED"'>
						<n:HcAuthButton cssClass="dark-btn" onclick="disableUser()" value="停&nbsp;&nbsp;用" authCode="JGGL_JGCZY_JY"/>
						</s:if>
						<s:else>
						<n:HcAuthButton cssClass="dark-btn" onclick="recover()" value="启&nbsp;&nbsp;用" authCode="JGGL_JGCZY_QY"/>
						</s:else>
						<n:HcAuthButton cssClass="dark-btn" onclick="deleteUser()" value="删&nbsp;&nbsp;除" authCode="JGGL_JGCZY_SC"/>
						<input type="button" class="dark-btn"  value="取&nbsp;&nbsp;消" onclick="back();"/>
						<!-- <input type="button" class="dark-btn"  value="返&nbsp;&nbsp;回" onclick="back();"/> -->
					</div>
					</s:form>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$("#storeform").validationEngine("attach",{   
				promptPosition:"centerRight"
			});
		});
		
		var rolename='<s:property value="%{role}"/>';
		$("#role option[value='"+rolename+"']").attr("selected","selected");
			
			function checkform(){
				if(confirm("确认要修改操作员信息?")){
				if(!$("#storeform").validationEngine("validate")){
					return false;
				}
				
				if($('#role').val()==""){
					alert("请选择一个角色!");
					return false;
				}
				document.storeform.submit();
				}
			}
			
			function submit(){
				if(confirm("确认要修改操作员信息?")){
				document.storeform.submit();
				}
			}
			
			function back(){
				var iinst = $("#iinst").val();
				window.location.href = "${ctx}/backstage/instmanager/backstageInstUser!backToUserList?iinst="+iinst;
			}
			
			function recover(){
				if(confirm("确认要启用此用户?")){
				window.location.href = "${ctx}/backstage/instmanager/backstageInstUser!reCoverInstUser";
				}
			}
			
			function disableUser(){
				if(confirm("确认要停用此用户?")){
				window.location.href="${ctx}/backstage/instmanager/backstageInstUser!disableAcct";
				}
			}
			
			function deleteUser(){
				if(confirm("确认要删除此用户?")){
					window.location.href="${ctx}/backstage/instmanager/backstageInstUser!deleteInstUser";
				}
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>