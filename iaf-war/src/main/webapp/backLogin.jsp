<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!doctype html>
<html>
	<head>
		<%@include file="/template/portal/meta.jspf" %>
		<n:head scripts="jquery" />
<n:ui includes="form"/>
<script type="text/javascript" src="/resources/js/rsa/jsbn.js"></script>
<script type="text/javascript" src="/resources/js/rsa/prng4.js"></script>
<script type="text/javascript" src="/resources/js/rsa/rng.js"></script>
<script type="text/javascript" src="/resources/js/rsa/rsa.js"></script>
<script type="text/javascript" src="/resources/js/rsa/iafrsa.js"></script>		
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
		<div id="main" class="iaf-grid login">
			<ul class="tab-title">
				<li class="cur">管理后台登录</li>
			</ul>
			<div class="tab-content">
				<form action="/iafLogin!login" method="post" id="loginForm" onsubmit="return beforeSubmit();">
					<div id="error">
				<label>&nbsp;</label>
				<span style="color: red; font-size: 16px;" id="msg"><s:property value="%{errorMsg}"/></span>
				</div>
					<div class="form-row">
						<label>用户名：</label><input type="text" name="loginName" id="loginName" class="form-input validate[required]" placeholder="请输入用户名" onblur="accountCheck()"/>
					</div>
					<div class="form-row">
						<label>登录密码：</label><input type="password"  name="password" id="password" class="form-input validate[required]" placeholder="请输入密码" onblur="passwordCheck()"/>
					</div>
					<div class="form-row">
						<label>验证码：</label><input id="checkCode" name="checkCode" type="text" class="form-input validate[required]" placeholder="请输入验证码">&nbsp;
						<% long random=System.currentTimeMillis();%>
						<img src="${ctx}/kaptcha.jpg?d=<%=random%>" alt="看不清楚,换一张" onclick="reloadVerifyCode(this);"/>
					</div>
					<input type="submit" id="login-btn" value=""/>
					<a href="javascript:void(0);" id="join-btn"><span>立即加入</span></a>
				</form>
			</div>
		</div>
		<%@include file="/template/portal/co-bank.jspf" %>
		<%@include file="/template/portal/footer.jspf" %>
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
		$(function() {
			$("#loginForm").validationEngine();
		});
		
		function reloadVerifyCode(obj){  
			 var d=new Date();
		       obj.src = "${ctx}/kaptcha.jpg?d=" +d.getMilliseconds(); 
		    }  
		$('#error').hide();
		var errorMsg='<s:property value="%{errorMsg}"/>';
		if(errorMsg!=""){
			$('#error').show();
		}
			function beforeSubmit(){
				if(!$("#loginForm").validationEngine("validate")){
					return false;
				}
				
				if($('#loginName').val()==""){
					$('#error').show();
					$('#msg').html('用户名不能空!');
					return false;
				}
				if($('#password').val()=="请输入登录密码"){
					$('#error').show();
					$('#msg').html('密码不能为空!');
					return false;
				}
				if($('#checkCode').val()==""){
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
				return true;
			}
			if(self.location!=top.location){
				top.location=self.location;
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>