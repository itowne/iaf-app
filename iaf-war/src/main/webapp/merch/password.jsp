<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/mp/meta.jspf" %>
<!doctype html>
<html>
	<head>
	 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
		<%@include file="/template/mp/head.jspf" %>
		<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
	</head>
	<body id="workframe">
		<div id="wrapper">
			<div id="location">
				<p>当前位置：我的资料&nbsp;&gt;&gt;&nbsp;修改密码</p>
			</div>
			<div id="content" class="report">
				<h3 class="title"><span>修改密码</span></h3>
				<div class="search-bar">
				<s:if test='merch.merchType.toString()!="GOLD_SHOPKEEPER"'>
					<s:form action="#" id="sform">
						<table width="75%" border="0" cellspacing="0" cellpadding="0" class="data-table">
							<tr><td colspan="3" class="label" style="text-align:center">商户登陆账号密码修改</td></tr>
							<tr>
								<td width="20%" class="label">旧密码：</td>
								<td width="50%" >&nbsp;&nbsp;<s:password id="oldPwd" name="oldPwd" class="limit"  /> 
								<span id="opwd" style="background-color:#FFF4C1;color:#FF8500;display:none"">请输入您的旧密码</span>
								</td>
							</tr>
							<tr>
								<td class="label">新密码：</td>
								<td>&nbsp;&nbsp;<s:password id="newPwd" name="newPwd" class="limit" onblur="checkPwd()"/>
								<span id="npwd" style="background-color:#FFF4C1;color:#FF8500;display:none"">6-16个字符。请使用字母加数字或字符的组合密码</span>
								</td>
							</tr>
							<tr>
								<td class="label">新密码确认：</td>
								<td>&nbsp;&nbsp;<s:password id="confirmPwd" name="confirmPwd" class="limit" onblur="checkConfirmPwd()"/>
								<span id="ncpwd" style="background-color:#FFF4C1;color:#FF8500;display:none"">请再输入一次新密码</span>
								</td>
							</tr>
							<tr>
								<td colspan="3" class="label" style="text-align:center;padding:5px 0">
								<input class="dark-btn" type="button" value="确&nbsp;&nbsp;定" onclick="doSubmit()"></input>
								<!--  <input class="dark-btn" type="submit" ></input>-->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:fielderror cssStyle="color:red" fieldName="error"/> </td>
							</tr>
						</table>
					</s:form>
				</div>
				</s:if>
				<s:else>
				<div align="center">金掌柜商户密码修改功能，暂未开通，如需修改登陆密码，请在金掌柜管理系统上进行修改.</div>
				</s:else>
			</div>
		</div>
		<script type="text/javascript">
		$("#oldPwd").focus(function(){
			$("#opwd").css("display","inline");
		});
		$("#oldPwd").blur(function(){
			$("#opwd").css("display","none");
		});
		$("#newPwd").focus(function(){
			$("#npwd").css("display","inline");
		});
		$("#newPwd").blur(function(){
			$("#npwd").css("display","none");
		});
		$("#confirmPwd").focus(function(){
			$("#ncpwd").css("display","inline");
		});
		$("#confirmPwd").blur(function(){
			$("#ncpwd").css("display","none");
		});
		
			function doSubmit(){
				if(confirm("确认提交请求?")){
					
				if($("#oldPwd").val()==""){
					alert("请填写旧密码!");
					return false;
				}
				if($("#newPwd").val()==""){
					alert("请填写新密码!");
					return false;
				}
				if($("#confirmPwd").val()==""){
					alert("请填写确认密码!");
					return false;
				}
				
				if($("#confirmPwd").val()!=$("#newPwd").val()){
					alert("两次输入的密码不一致!");
					return false;
				}
				
				document.sform.action="/merch/merchPwd";
				document.sform.submit();
				}
			}
			
			function checkPwd(){
				if($("#confirmPwd").val()!=""){
					if($("#confirmPwd").val()!=$("#newPwd").val()){
						alert("两次输入的密码不一致!");
						return false;
					}
				}
			}
			
			function checkConfirmPwd(){
				if($("#newPwd").val()!=""){
					if($("#confirmPwd").val()!=$("#newPwd").val()){
						alert("两次输入的密码不一致!");
						return false;
					}
				}
			}
		</script>
	</body>
</html>