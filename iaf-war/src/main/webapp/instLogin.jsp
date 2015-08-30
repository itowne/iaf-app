<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!doctype html>
<html>
<head>
<%@include file="/template/portal/meta.jspf"%>
<n:head scripts="jquery" />
<n:ui includes="form"/>
<script type="text/javascript" src="/resources/js/rsa/jsbn.js"></script>
<script type="text/javascript" src="/resources/js/rsa/prng4.js"></script>
<script type="text/javascript" src="/resources/js/rsa/rng.js"></script>
<script type="text/javascript" src="/resources/js/rsa/rsa.js"></script>
<script type="text/javascript" src="/resources/js/rsa/iafrsa.js"></script>
		<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
</head>
<body id="body">
	<%@include file="/template/portal/header.jspf"%>
	<div id="main" class="iaf-grid login">
		<ul class="tab-title">
			<li><a href="merchLogin.jsp">商户登录</a></li>
			<li class="cur">机构登录</li>
		</ul>
		<div class="tab-content">
			<s:form action="instUserLogin.action" method="post" id="loginForm"
				class="instLogin" cssStyle="padding:45px 20px 55px;line-height:46px;background:url(resources/images/portal/bg-login-inst.jpg) no-repeat 600px center;position:relative;min-height:250px;height:auto !important;height:250px;">
				<s:hidden value="%{#request.target}" name="target"/>
				<div id="error">
					<label>&nbsp;</label>
					<span style="color: #FF8500; font-size: 16px;" id="msg"><s:property value="%{errorMsg}" /></span>
				</div>
				<div class="form-row">
					<label>用户名：</label><input type="text" name="loginName"
					id="loginName" class="form-input validate[required]" placeholder="请输入登录用户名" onblur="accountCheck()"/>
				</div>
				<div class="form-row ukey-login">
					<label>密码：</label><input type="password" name="password" 
						id="password"  class="form-input validate[required]" placeholder="请输入登录密码" onblur="passwordCheck()"/>
				</div>
				<div class="form-row mobile-login">
					<label>手机验证码：</label><input type="text" name="smsAuthCode"
						id="smsAuthCode" class="form-input validate[required]" placeholder="请输入手机短信验证码" onblur="accountCheck()">&nbsp;
						<input type="button" id="smsButton" onclick="sendSmsAuthCode();" value="发送验证码">
				</div>
				<div class="form-row ukey-login">
					<label>验证码：</label><input type="text"
						class="form-input validate[required]" id="checkCode" name="checkCode" placeholder="请输入验证码" onblur="accountCheck()">&nbsp;
						<% long random=System.currentTimeMillis();%>
						<img id="kaptcha" src="${ctx}/kaptcha.jpg?d=<%=random%>" alt="看不清楚,换一张" />看不清?<a href="javaScript:void(0)" onclick="reloadVerifyCode()">换一张</a>
				</div>
				<input type="submit" id="login-btn" value=""  onclick="return beforeSubmit();"/>
				<span style="margin-left:150px">还没有加入汇融易?<a href="${ctx}/instReg!toInstReg">立即注册</a></span>
				 <a href="${ctx}/merchReg" id="join-btn"><span>立即加入</span></a>
			</s:form>
		</div>
	</div>
   <%@include file="/template/portal/co-bank.jspf"%>
	<%@include file="/template/portal/footer.jspf"%>
	<script type="text/javascript">
	$.fn.extend({
	    inputTip:function(_color, _plaColor) {
	        _color = _color || "#000000";
	        _plaColor = _plaColor || "#a3a3a3";
	        function supportsInputPlaceholder() { // 判断浏览器是否支持html5的placeholder属性
	            var input = document.createElement('input');
	            return "placeholder" in input;
	        }

	        function showPassword(_bool, _passEle, _textEle) { // 密码框和文本框的转换
	            if (_bool) {
	                _passEle.show();
	                _textEle.hide();
	            } else {
	                _passEle.hide();
	                _textEle.show();
	            }
	        }

	        if (!supportsInputPlaceholder()) {
	            this.each(function() {
	                var thisEle = $(this);
	                var inputType = thisEle.attr("type")
	                var isPasswordInput = inputType == "password";
	                var isInputBox = inputType == "password" || inputType == "text";
	                if (isInputBox) { //如果是密码或文本输入框
	                    var isUserEnter = false; // 是否为用户输入内容,允许用户的输入和默认内容一样
	                    var placeholder = thisEle.attr("placeholder");

	                    if (isPasswordInput) { // 如果是密码输入框
	                        //原理：由于input标签的type属性不可以动态更改，所以要构造一个文本input替换整个密码input
	                        var textStr = "<input type='text' class='" + thisEle.attr("class") + "' style='" + (thisEle.attr("style") || "") + "' />";
	                        var textEle = $(textStr);
	                        textEle.css("color", _plaColor).val(placeholder).focus(
	                            function() {
	                                thisEle.focus();
	                            }).insertAfter(this);
	                        thisEle.hide();
	                    }
	                    thisEle.css("color", _plaColor).val("");//解决ie下刷新无法清空已输入input数据问题
	                    if (thisEle.val() == "") {
	                        thisEle.val(placeholder);
	                    }
	                    thisEle.focus(function() {
	                        if (thisEle.val() == placeholder && !isUserEnter) {
	                            thisEle.css("color", _color).val("");
	                            if (isPasswordInput) {
	                                showPassword(true, thisEle, textEle);
	                            }
	                        }
	                    });
	                    thisEle.blur(function() {
	                        if (thisEle.val() == "") {
	                            thisEle.css("color", _plaColor).val(placeholder);
	                            isUserEnter = false;
	                            if (isPasswordInput) {
	                                showPassword(false, thisEle, textEle);
	                            }
	                        }
	                    });
	                    thisEle.keyup(function() {
	                        if (thisEle.val() != placeholder) {
	                            isUserEnter = true;
	                        }
	                    });
	                }
	            });
	        }
	    }
	});
			
				$(function() {   
				       $("input").inputTip();  // 调用inputTip方法   
				       $("input[type='button']").focus();  // 页面打开后焦点置于button上，也可置于别处。否则IE上刷新页面后焦点在第一个输入框内造成placeholder文字后紧跟光标现象
				});
		
	// function reloadVerifyCode(obj){  
		 //var d=new Date();
	      // obj.src = "${ctx}/kaptcha.jpg?d=" +d.getMilliseconds(); 
	   // }  
	function reloadVerifyCode(){
		var d=new Date();
		$("#kaptcha").attr("src","${ctx}/kaptcha.jpg?d=" +d.getMilliseconds());
	}
		$('#error').hide();
		var errorMsg='<s:property value="%{errorMsg}"/>';
		if (errorMsg !="") {
			$('#error').show();
		}
		function beforeSubmit() {
			$('#login-btn').attr("disabled","disabled");
			if(!$("#loginForm").validationEngine("validate")){
				$('#login-btn').removeAttr("disabled"); 
				return false;
			}
			
			if ($('#loginName').val() == "") {
				$('#error').show();
				$('#msg').html('用户名不能空!');
				return false;
			}
			if ($('#password').val() == "请输入登录密码") {
				$('#error').show();
				$('#msg').html('密码不能为空!');
				return false;
			}
			if ($('#smsAuthCode').val() == "") {
				$('#error').show();
				$('#msg').html('手机验证码不能为空!');
				return false;
			}
			if ($('#checkCode').val() == "") {
				$('#error').show();
				$('#msg').html('验证码不能为空!');
				return false;
			}
			
			var pwd=$('#password').val();
			if(pwd.length<=20){
				var x=encrypt(pwd);
				$('#password').val(x);
			}else{
				alert("登录点击过于频繁,请重新登录!");
				return false;
			}
			$('#login-btn').removeAttr("disabled");
		}

		function sendSmsAuthCode() {
			if($("#loginName").val()==""){
				alert("请填写用户名!");
				return false;
			}
			alert("短信验证码已发送到您的手机,请注意查收!");
			$('#smsButton').attr("disabled",true);
			var loginName = $("#loginName").attr("value");
			var url = "/instUserLogin!smsAuthCode";
			$.post(url, {
				loginName : loginName
			});
			setTimeout(function(){//10秒
				$('#smsButton').removeAttr("disabled"); 
			},10000);
		}
		$(":input[name='loginType']")
				.change(
						function() {
							var showID = $(this).attr("id"), hideID = showID === "ukey" ? "mobile"
									: "ukey";
							$("." + hideID + "-login").hide();
							$("." + showID + "-login").show();
						});
		$(function() {
			$("#ukey").attr("checked", "checked");
		});
		if (self.location != top.location) {
			top.location = self.location;
		}
	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>