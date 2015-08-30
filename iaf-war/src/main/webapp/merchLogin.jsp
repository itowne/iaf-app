<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
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
			<li class="cur">商户登录</li>
			<li><a href="instLogin.jsp">机构登录</a></li>
		</ul>
		<div class="tab-content">
			<form action="/merchLogin.action" method="post" id="loginForm"
				 style="padding:45px 20px 55px;line-height:46px;background:url(resources/images/portal/bg-login-merch.jpg) no-repeat 600px center;position:relative;min-height:250px;height:auto !important;height:250px;">
				<s:hidden value="%{#request.target}" name="target"/>
				<div id="error">
					<label>&nbsp;</label> <span style="color: #FF8500; font-size: 16px;"
						id="msg"><s:property value="%{errorMsg}" /></span>
				</div>
				<div class="form-row">
				<label><font style="color:red">温馨提示:</font>
				</label>
				金掌柜商户,请直接使用金掌柜登录帐号和密码登录平台
				<div style="height:12px;padding-left:125px;line-height: 10px">非金掌柜商户,请使用本网站的注册帐号和密码登录平台</div>
				</div>
				<div class="form-row">
					<label>用户名：</label><input type="text" name="loginName"
						id="loginName" class="form-input validate[required]" placeholder="请输入登录用户名,并勾选商户类型" onblur="accountCheck()"/>
						<div id="merchtype" style="height:15px;line-height:8px;padding-left: 120px;"><input id="gold" type="checkbox" name="merchUserType"
						value="hicard">金掌柜用户&nbsp;&nbsp;<input id="ungold" type="checkbox" name="type"
						value="unhicard">非金掌柜用户</div>
				</div>
				<div class="form-row">
					<label>密码：</label><input type="password" name="password" 
						id="password" class="form-input validate[required]" placeholder="请输入登录密码" onblur="passwordCheck()"/>
				</div>
				<div class="form-row">
					<label>验证码：</label><input id="SecurityCode" type="text" class="form-input validate[required]" id="checkCode" name="SecurityCode" placeholder="请输入验证码" onblur="accountCheck()">&nbsp;
					<%
						long random = System.currentTimeMillis();
					%>
					<img id="kaptcha" alt="看不清楚,换一张" src="${ctx}/kaptcha.jpg?d=<%=random%>"
						 />看不清?<a href="javaScript:void(0)" onclick="reloadVerifyCode()">换一张</a>
				</div>
				<input type="submit" id="login-btn" value="" onclick="return beforeSubmit()"/>
				<span style="margin-left:150px">还没有加入汇融易?<a href="${ctx}/merchReg">立即注册</a></span>
				 <a href="${ctx}/merchReg" id="join-btn"><span>立即加入</span></a>
			</form>
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
	
	$("#gold").click(function(){
		if($("#gold").attr("checked")=="checked"){
			$("#ungold").removeAttr("checked");
		}
	});
	
	$("#ungold").click(function(){
		if($("#ungold").attr("checked")=="checked"){
			$("#gold").removeAttr("checked");
		}
	})
		
	$(function() {
		$("#loginForm").validationEngine();
	});
		//function reloadVerifyCode(obj) {
			//var d = new Date();
			//obj.src = "${ctx}/kaptcha.jpg?d=" + d.getMilliseconds();
		//}
		function reloadVerifyCode(){
		var d=new Date();
		$("#kaptcha").attr("src","${ctx}/kaptcha.jpg?d=" +d.getMilliseconds());
	}
		$('#error').hide();
		var errorMsg = '<s:property value="%{errorMsg}"/>';
		if (errorMsg != "") {
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
				$('#login-btn').removeAttr("disabled"); 
				return false;
			}
			
			if ($('#password').val() == "请输入登录密码") {
				$('#error').show();
				$('#msg').html('密码不能为空!');
				$('#login-btn').removeAttr("disabled"); 
				return false;
			}
			if ($('#checkCode').val() == "") {
				$('#error').show();
				$('#msg').html('验证码不能为空!');
				$('#login-btn').removeAttr("disabled"); 
				return false;
			}
			if($(":checkbox:checked").length<=0){
				alert("请选择用户类型!");
				$('#login-btn').removeAttr("disabled"); 
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
			//return true;
		}

		if (self.location != top.location) {
			top.location = self.location;
		}
	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>