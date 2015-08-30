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
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;操作账号管理&gt;&gt;&nbsp;账号信息修改</p>
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
							<td class="data"><s:label name="backStageUser.loginName"/></td>
							<td class="label">真实名称：</td>
							<td class="data"><input  name="backStageUser.userName" class="validate[required]" value="<s:property  value='backStageUser.userName'/>"/><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">新密码：</td>
							<td class="data"><input type="password"  name="password" />（不修改不输入）</td>
							<td class="label">确认新密码：</td>
							<td class="data"><input type="password"  name="passwordAgain" />（不修改不输入）</td>
						</tr>
						<tr>
							<td class="label">性别：</td>
							<td class="data">
							<s:select name="backStageUser.sexType" list="@newland.iaf.base.model.dict.SexType@values()" listKey="toString()" listValue="getDesc()" value="backStageUser.sexType"></s:select>
							<font color=red>*</font></td>
							<td class="label">地址：</td>
							<td class="data"><input  name="backStageUser.address" class="validate[required]" value="<s:property  value='backStageUser.address'/>" /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">QQ号：</td>
							<td class="data"><input  name="backStageUser.qqNum" class="validate[required,custom[onlyNumberSp]]" value="<s:property  value='backStageUser.qqNum'/>" /><font color=red>*</font></td>
							<td class="label">email：</td>
							<td class="data"><input  name="backStageUser.email" class="validate[required,custom[email]]" value="<s:property  value='backStageUser.email'/>" /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">手机号码：</td>
							<td class="data"><input  name="backStageUser.mobile" class="validate[required,custom[onlyNumberSp]]" value="<s:property  value='backStageUser.mobile'/>" /><font color=red>*</font></td>
							<td class="label">状态：</td>
							<td class="data">
							<s:select name="backStageUser.stat" list="@newland.iaf.base.model.dict.InstUserStatType@values()" listKey="toString()" listValue="getDesc()" value="backStageUser.stat"></s:select>
							<font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">所属角色：</td>
							<td class="data">
							<s:if test="loginUserName==backStageUser.loginName">
								<s:label name="roleName"/>
							</s:if>
							<s:else>
							<select id="bsRole" name="bsRole" class="validate[required]">
							<option value="">请选择所属角色</option>
									<s:iterator var="bsRoleList" value="bsRoleList">
										<option value="<s:property value="#bsRoleList.getiBsRole()"/>">
											<s:property value="#bsRoleList.getRoleName()" />
										</option>
									</s:iterator>
							</select>
							</s:else>
							</td>
							<td class="label"></td>
							<td class="data">&nbsp;</td>
						</tr>
					</table>
					<div class="uop">
						<input type="hidden" id="oldRole" name="oldRole" value="<s:property value="%{bsRole}"/>"/>
						<s:submit cssClass="dark-btn" value="保存" method="updateBackstageUser" onclick="return checkForm()"/>
						<s:submit cssClass="dark-btn" value="返回" method="execute"/>
					</div>
					</s:form>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		var bsRole = '${bsRole}';
		
		$("#bsRole option[value='"+bsRole+"']").attr("selected","selected");
		
		$(function() {
			$("#instform").validationEngine("attach",{   
				promptPosition:"centerRight"
			});
		});
		
		function checkForm(){
			if(!$("#instform").validationEngine("validate")){
				return false;
			}
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
			

		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>