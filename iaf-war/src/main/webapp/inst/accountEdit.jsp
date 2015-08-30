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
		<n:ui includes="form" />
		<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;账号管理&nbsp;&gt;&gt;&nbsp;修改账号</p>
			</div>
			<div id="content">
				<h3 class="title"><span>修改账号详细信息</span></h3>
				<div class="results report">
				<s:form id="storeform" action="#" >
					<table width="50%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">账号名称：</td>
							<td class="data"><s:label name="instUser.loginName"/></td>
						</tr>
						<tr>
							<td class="label">使用者姓名：</td>
							<td class="data"><s:textfield id="userName" name="instUser.userName" maxLength="30" cssClass="validate[required]" /></td>
						</tr>
						<tr>
							<td class="label">所属角色：</td>
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
							<td class="data"><s:textfield  name="instUser.position" id="position"/></td>
						</tr>
						<tr>
							<td class="label">电子邮箱：</td>
							<td class="data"><s:textfield id="Mail" cssClass="validate[custom[email]]" name="instUser.Mail"/></td>
						</tr>
						<tr>
							<td class="label">QQ号码：</td>
							<td class="data"><s:textfield cssClass="validate[custom[onlyNumberSp]]" id="QQNum" name="instUser.QQNum" /></td>
						</tr>
						<tr>
							<td class="label">手机号码：</td>
							<td class="data"><s:textfield cssClass="validate[required,custom[onlyNumberSp]]" id="phone" name="instUser.phone"/></td>
						</tr>
						<tr>
							<td class="label">密码设置：</td>
							<td class="data"><s:password id="password" name="passwd" /></td>
						</tr>
						<tr>
							<td class="label">再次输入密码：</td>
							<td class="data"><s:password id="confirmpassword"/></td>
						</tr>
					</table>
					<div class="uop">
						<input type="hidden" id="oldRole" name="oldRole" value="<s:property value="%{role}"/>"/>
						<!-- <input type="hidden" id="index" name="index" value="<s:property value="instUser.iinstUser"/>"/> -->
						<s:if test="instUser.usrStat.ordinal()==1">
							<n:instAuthButton cssClass="dark-btn" onclick="beforeSubmit();" value="修&nbsp;&nbsp;改" authCode="XTGL_ZHQX_XGZH"/>
							<n:instAuthButton cssClass="dark-btn" onclick="zt();" value="暂&nbsp;&nbsp;停" authCode="XTGL_ZHQX_ZTZH"/>
							<n:instAuthButton cssClass="dark-btn" onclick="remove();" value="删&nbsp;&nbsp;除" authCode="XTGL_ZHQX_SCZH"/>
							<input type="button" class="dark-btn"  value="取&nbsp;&nbsp;消" onclick="back();"/>
      					</s:if>
						<s:else>
						<n:instAuthButton disabledFlag="true" cssClass="dark-btn" onclick="beforeSubmit();" value="修&nbsp;&nbsp;改" authCode="XTGL_ZHQX_XGZH"/>
							<n:instAuthButton cssClass="dark-btn" onclick="qy();" value="启&nbsp;&nbsp;用" authCode="XTGL_ZHQX_QYZH"/>
							<n:instAuthButton cssClass="dark-btn" onclick="delete();" value="删&nbsp;&nbsp;除" authCode="XTGL_ZHQX_SCZH"/>
							<input type="button" class="dark-btn"  value="取&nbsp;&nbsp;消" onclick="back();"/>
						<!-- <input type="button" class="dark-btn"  value="返&nbsp;&nbsp;回" onclick="back();"/> -->
						</s:else>
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
		function beforeSubmit() {
			if(confirm("确认修改该账户信息?")){
				if(!$("#storeform").validationEngine("validate")){
					return false;
				}
				if($('#role').val()==""){
					alert("请选择一个角色!");
					return false;
				}
				if ($('#password').val() != $('#confirmpassword').val()) {
					alert("两次密码输入不一致!");
					return false;
				}
				$("#storeform").attr("action","${ctx}/inst/instUser!instUserUpdate");
				$("#storeform").submit();
			}else{
				return false;
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
				var role=$('#oldRole').val();
				$("#role option[value='"+role+"']").attr("selected",true);
			});
			
			function checkform(){
				if($.trim($('#loginName').val())==""){
					alert("帐号不能空!");
					return false;
				}
				if($.trim($('#userName').val())==""){
					alert("使用者姓名不能为空!");
					return false;
				}
				
				//if($('#role').val()==""){
					//alert("请选择一个角色!");
					//return false;
				//}
				
				if($('#position').val()==""){
					alert("职位不能为空!");
					return false;
				}
				if($('#eMail').val()==""){
					alert("电子邮件不能为空!");
					return false;
				}
				if($('#QQNum').val()==""){
					alert("QQ号不能为空!");
					return false;
				}else{
					if(isNaN($('#QQNum').val())){
						alert("QQ号请填写数字!");
						return false;
					}
				}
				if($('#phone').val()==""){
					alert("手机不能为空!");
					return false;
				}else{
					if(isNaN($('#phone').val())){
						alert("手机号请填写数字!");
						return false;
					}
				}
			}
			
			function zt(){
				if(confirm("确定要暂停该账户吗?")){
					$("#storeform").attr("action","${ctx}/inst/instUser!disableAcct");
					$("#storeform").submit();
				}else{
					return false;
				}
			}
			function qy(){
				if(confirm("确定要启用该账户吗?")){
					$("#storeform").attr("action","${ctx}/inst/instUser!reCoverInstUser");
					$("#storeform").submit();
				}else{
					return false;
				}
			}
			function remove(){
				if(confirm("确定要删除该账户吗?")){
					$("#storeform").attr("action","${ctx}/inst/instUser!deleteInstUser");
					$("#storeform").submit();
				}else{
					return false;
				}
			}
			function back(){
				window.location.href = "${ctx}/inst/instUser.action";
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>