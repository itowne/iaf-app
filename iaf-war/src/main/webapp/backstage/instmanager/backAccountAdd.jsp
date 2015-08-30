<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<title>汇融易 - 机构管理后台</title>
<n:head styles="mp-workframe" scripts="jquery,mp-workframe"/>
		<n:ui includes="form"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
<body>
	<div id="workframe-wrapper" class="clearfix">
		<div id="location">
			<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;操作员管理&nbsp;&gt;&gt;&nbsp;新增账号</p>
		</div>
		<div id="content">
			<h3 class="title">
				<span>新增账号详细信息</span>
			</h3>
			<div class="results report">
				<s:form id="storeform" action="/backstage/instmanager/backstageInstUser!instUserAdd"
					onsubmit="return checkform();">
					<table width="50%" border="0" cellspacing="0" cellpadding="0"
						class="data-table">
						<tr>
							<td class="label">账号名称：</td>
							<td class="data"><s:textfield id="loginName"
									name="instUser.loginName" cssClass="validate[required,minSize[2]]" maxLength="30" /><font color=red>*<s:property value="%{registerError}"/></font></td>
						</tr>
						<tr>
							<td class="label">使用者姓名：</td>
							<td class="data"><s:textfield id="userName"
									name="instUser.userName" maxLength="30" cssClass="validate[required,minSize[2]]"/><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">所属角色：</td>
							<td class="data"><select id="role" name="role" class="validate[required]">
									<option value="">请选择所属角色</option>
									<s:iterator var="roleList" value="instRoleList">
										<option value="<s:property value="#roleList.getiInstRole()"/>">
											<s:property value="#roleList.getRoleName()" />
										</option>
									</s:iterator>
							</select><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">使用者职务：</td>
							<td class="data"><s:textfield id="position"  name="instUser.position" /></td>
						</tr>
						<tr>
							<td class="label">电子邮箱：</td>
							<td class="data"><s:textfield id="eMail" cssClass="validate[custom[email]]" name="instUser.Mail" /></td>
						</tr>
						<tr>
							<td class="label">QQ号码：</td>
							<td class="data"><s:textfield id="QQNum" cssClass="validate[custom[onlyNumberSp]]" name="instUser.QQNum" /></td>
						</tr>
						<tr>
							<td class="label">手机号码：</td>
							<td class="data"><s:textfield id="phone" cssClass="validate[required,custom[onlyNumberSp]]" name="instUser.phone" /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">密码设置：</td>
							<td class="data"><s:password id="password"
									name="instUser.passwd" cssClass="validate[required]"/><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">再次输入密码：</td>
							<td class="data"><s:password id="confirmpassword" cssClass="validate[required]" name="confirmpassword"/><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="data" colspan="2"><input type="checkbox" name="userUsed" id="userUsed"/>启用新账号</td>
						</tr>
					</table>
					<div class="uop">
					<input type="hidden" name="iist" value="<s:property value="%{iist}"/>">
					<input type="hidden" id="isUsed" name="isUsed" value=""/>
						<input type="submit" class="dark-btn" value="确&nbsp;&nbsp;定" /> <input
							type="button" class="dark-btn" value="取&nbsp;&nbsp;消"
							onclick="back();" />
					</div>
				</s:form>
				<div class="results report"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$("#userUsed").attr("checked","checked");
	$(function() {
		$("#storeform").validationEngine("attach",{   
			promptPosition:"centerRight"
		});
	});

	
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
			
			
			function checkform(){
				if(!$("#storeform").validationEngine("validate")){
					return false;
				}
				//if($('#role').val()==""){
					//alert("请选择一个角色!");
					//return false;
				//}
				
				if($('#confirmpassword').val()!=""){
					if($('#password').val()!=$('#confirmpassword').val()){
						alert("两次密码输入不一致!");
						return false;
						}
				}
				
				if($("#userUsed").attr("checked")=="checked"){
					$("#isUsed").val("USED");
				}else{
					$("#isUsed").val("UNUSED");
				}		
				return true;
			}
			function back(){
				window.location.href = "${ctx}/backstage/instmanager/backAccountMgr.jsp";
			}
		</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>