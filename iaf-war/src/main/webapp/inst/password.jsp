<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/mp/meta.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
<%@include file="/template/mp/head.jspf" %>
		<meta charset="utf-8"/>
		<title>汇融易 - 机构管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
				<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
		  <div id="location">
				<p>当前位置：我的资料&nbsp;&gt;&gt;&nbsp;修改密码</p>
			</div>
			<div id="content" class="report">
				<h3 class="title"><span>修改密码</span></h3>
                <div class="search-bar">
                 <form action="#" method="post" id="form1" name="form1">
	                 <table width="75%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						  <tr>
		                   <td colspan="2" class="label" style="text-align:center">
		                   	机构登陆账号密码修改
		                   </td>
		                  </tr>
		                  <tr>
		                   <td class="label" width="20%">旧密码：</td>
		                   <td width="50%">&nbsp;&nbsp;<s:password id="oldPwd" name="oldPwd" class="limit" />
							<span id="opwd" style="background-color:#FFF4C1;color:#FF8500;display:none"">&nbsp;请输入您的旧密码</span>
							</td>
		                  </tr>
		                  <tr>
		                   <td class="label">新密码：</td>
		                   <td>&nbsp;&nbsp;<s:password id="newPwd" name="newPwd" class="limit" onblur="checkPwd()"/>
		                   <span id="npwd" style="background-color:#FFF4C1;color:#FF8500;display:none"">&nbsp;6-16个字符。请使用字母加数字或字符的组合密码</span>
		                   </td>
		                  </tr>
		                  <tr>
		                   <td class="label">新密码确认：</td>
		                   <td>&nbsp;&nbsp;<s:password id="confirmPwd" name="confirmPwd" class="limit" onblur="checkConfirmPwd()"/>
		                  <span id="ncpwd" style="background-color:#FFF4C1;color:#FF8500;display:none"">请再输入一次新密码</span>
		                   </td>
		                  </tr>
		                  <tr>
		                   <td colspan="2" class="label" style="text-align:center;padding:5px 0">
		                     <n:instAuthButton cssClass="dark-btn" onclick="doSubmit()" value="确&nbsp;&nbsp定" authCode="XTGL_ZHQX_XGMM"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      						</td>
		                  </tr>
	                 </table>
                 </form>
                </div>
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
			$(function() {
				$("#form1").validationEngine("attach",{   
					promptPosition:"centerRight"
				});
			});
			function check() {
				if(!$("#form1").validationEngine("validate")){
					return false;
				}
				if($("#newPwd").val()!=$("#confirmPwd").val()){
					alert('两次密码不一致');
					return false;
				}
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
				return true;
			}
			function doSubmit(){
				if(check()){
					//$("#form1").attr("action","${ctx}/inst/instPwd");
					//$("#form1").submit();
					document.form1.action="/inst/instPwd";
					document.form1.submit();
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
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>