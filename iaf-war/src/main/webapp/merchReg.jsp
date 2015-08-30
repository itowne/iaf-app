<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<n:head styles="portal-base,portal-inner" scripts="portal,jquery" />
<n:ui includes="form"/>
<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
</head>
<body id="body">
	<%@include file="/template/portal/header.jspf"%>
	<div id="main-reg">
		<div class="content">
			<ul class="tab-reg">
				<li class="cur">商户注册申请</li>
				<li onclick="location.href='${ctx}/instReg!toInstReg';">机构注册登记</li>
			</ul>
			<div class="reg-fields" style="width:820px">
				<p class="tips">
					<font class="red">温馨提示：</font>只有注册后才能在平台内向机构申请借款.使用<a href="${ctx}/about/aboutJoin.jsp" target="_ablank">金掌柜</a>积累经营流水将大大提升您借款的额度和成功率,现在就<a href="javaScript:void(0)" onclick="addHicard();">申请安装金掌柜</a>吧.
				</p>
				<s:form action="#" method="post" id="form1" name="form1">
					<div class="row" >
						<label>登录账号：</label><s:textfield name="loginName" id="loginName"  cssClass="reg-input validate[required,minCHSize[6],maxCHSize[26]]" />
						<span id="msg" style="display:none"></span>
						<s:if test="loginNameError!=null">&nbsp;<span id="error"
								style="color: #FF8500">${loginNameError}</span>
						</s:if>
						<span id="loginNameTip" style="font-size:15px;background-color:#FFF4C1;margin-left:70px;color:#FF8500;display:none">6-26个字符。一个汉字为两个字符</span>
					</div>
					<div class="row">
						<label>登录密码：</label><input type="password" 
							id="passwd" name="passwd" class="reg-input validate[required,minSize[6],maxSize[16]]" />
						<s:if test="passwdError!=null">&nbsp;<span
								style="color: #FF8500">${passwdError}</span>
						</s:if>
						<span id="passwordTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">6-16个字符。请使用字母加数字或字符的组合密码</span>
					</div>
					<div class="row">
						<label>确认密码：</label><input type="password" class="reg-input validate[required]"
							id="confirmPasswd" name="confirmPasswd">
						<s:if test="confirmPasswdError!=null">&nbsp;<span
								style="color: #FF8500">${confirmPasswdError}</span>
						</s:if>
						<span id="confirmPasswdTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">再输入一次密码</span>
					</div>
					<!--  
					<div class="row">
						<label>商户名称：</label><s:textfield name="merchName" id="merchName" cssClass="reg-input validate[required,minSize[2]]" />
						<s:if test="merchNameError!=null">&nbsp;<span
								style="color: red">${merchNameError}</span>
						</s:if>
					</div>
					<div class="row">
						<label>商户性质：</label><s:select
							list="#{'0':'请选择','stateRun':'国营','collective':'集体','privateEnter':'私企','indBusiness':'个体工商户','jointVenture':'合资'}"
							key="merchNature" id="merchNature"></s:select>
					</div>
					<div class="row">
						<label>QQ号:</label><s:textfield name="qqUid" id="qqUid" cssClass="reg-input validate[required,custom[onlyNumberSp]]" />
					</div>
					<div class="row">
						<label>联系人：</label><s:textfield name="contract" id="contract" cssClass="reg-input validate[required,minSize[2]]" />
					</div>
					<div class="row">
						<label>联系电话：</label><s:textfield name="contractTel" id="contractTel" cssClass="reg-input validate[required,custom[onlyNumberSp]]" />
					</div>
					-->
					<div class="row">
						<label>验证码：</label> <input type="text" style="padding-top: 18px;" class="form-input validate[required]"
							id="checkCode" name="captcha">&nbsp;
						<%
							long random = System.currentTimeMillis();
						%>
						<img id="kaptcha" style="padding-top: 20px" alt="看不清楚,换一张" src="${ctx}/kaptcha.jpg?d=<%=random%>"
							 />看不清?<a href="javaScript:void(0)" onclick="reloadVerifyCode()">换一张</a>
						<s:if test="captchaError!=null">&nbsp;<span
								style="color: #FF8500">${captchaError}</span>
						</s:if>
						<span id="checkCodeTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">请填写输入栏右侧图片上的字母或数字</span>
					</div>
				</s:form>
			</div>
			<div class="reg-uop">
			</div>
			<div class="reg-uop" style="margin-top: 10px;margin-left: 40px">
				<p><a href="javascript:void(0);" onclick="show();">&lt;&lt;汇融易服务协议&gt;&gt;</a></p><br>
				<p><input type="button" name="agree" class="dark-btn" id="agree" onclick="javaScript:submit();" value="同意协议并注册" /></p><br>
				<p>已有汇融易账户?<a href="${ctx}/merchLogin.jsp">立即登录</a></p>
			</div>
		</div>
	</div>
	<div id="hicardDiv" style="display:none">
			<br><br>
			<div style="text-align: center;font-size: x-large;">信用即财富,流水可贷款。</div><br>
			<div>尊敬的客户:</div><br>
			<div>&nbsp;&nbsp;&nbsp;&nbsp;如若您想安装金掌柜,请致电<font style="color:red">400-628-6928</font>申请开通。</div><br>
		</div>
	<%@include file="/template/portal/co-bank.jspf"%>
	<%@include file="/template/portal/footer.jspf"%>
	<script type="text/javascript">
		$("#loginName").focus(function(){
			$("#loginNameTip").css("display","inline");
		});
		$("#loginName").blur(function(){
			$("#loginNameTip").css("display","none");
		});
		$("#passwd").focus(function(){
			$("#passwordTip").css("display","inline");
		});
		$("#passwd").blur(function(){
			$("#passwordTip").css("display","none");
		});
		$("#confirmPasswd").focus(function(){
			$("#confirmPasswdTip").css("display","inline");
		});
		$("#confirmPasswd").blur(function(){
			$("#confirmPasswdTip").css("display","none");
		});
		$("#checkCode").focus(function(){
			$("#checkCodeTip").css("display","inline");
		});
		$("#checkCode").blur(function(){
			$("#checkCodeTip").css("display","none");
		});
		
		//function reloadVerifyCode(obj) {
			//var d = new Date();
			//obj.src = "${ctx}/kaptcha.jpg?d=" + d.getMilliseconds();
		//}
		function reloadVerifyCode() {
			var d = new Date();
			$("#kaptcha").attr("src","${ctx}/kaptcha.jpg?d=" + d.getMilliseconds());
		}
		function show(){
			window.showModalDialog("/showProtocol.jsp",window,"dialogWidth=940px;dialogHeight=600px");
		}
		$(function() {
			$("#form1").validationEngine();
		});

		function submit() {
			if (check()) {
				//$("#form1").attr("action", "merchReg!reg");
				$("#form1").attr("action", "merchReg!merchRegNext");
				$("#form1").submit();
			}
		}
		function check() {
			if(!$("#form1").validationEngine("validate")){
				return false;
			}
			
			var pwd = $("#passwd").val();
 			if(pwd.length>=6&&pwd.length<=16){
 				if(pwd!=""){
 	 				if (/^.*?[\d]+.*$/.test(pwd) && /^.*?[A-Za-z].*$/.test(pwd) && /^.{6,16}$/.test(pwd)) {
 	 		 		}else{
 	 		 			alert("请使用字母加数字或字符的组合密码");
 	 					return false;
 	 		 		}
 	 			}
 			}else{
 				alert("密码不能为空");
 				return false;
 			}
			
			if ($('#passwd').val() != $('#confirmPasswd').val()) {
				alert("两次密码输入不一致!");
				return false;
			}
			if ($('#merchNature').val() == "0") {
				alert("请选择商户性质!");
				return false;
			}
			var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\.,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
			if(txt.test($("#merchName").val())){
				alert("商户名不允许特殊字符");
				return false;
			}
			if(txt.test($("#loginName").val())){
				alert("登录帐号不允许特殊字符");
				return false;
			}
			if(txt.test($("#contract").val())){
				alert("联系人不允许特殊字符");
				return false;
			}
			return true;
		}
		function addHicard(){
			$("#hicardDiv").dialog({
				modal: true,
				title: "加入金掌柜",
				width: 500,
				height: 300,
				close: function(){
					$(this).dialog("destroy");
				},
				buttons: [{
					text: "关闭",
					click: function(){
						$(this).dialog("destroy");
					}
				}]
			});
		};
		
		
			 $("#loginName").blur(function() {  
				 var loginname=$("#loginName").val();
				 if(len(loginname)>=6&&len(loginname)<=26){
				 if($("#loginName").val()==""){
					 alert("请填写登录名!");
					 return false;
				 }else{
					 var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\.,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
						if(txt.test($("#loginName").val())){
							alert("登录帐号不允许特殊字符");
							return false;
						} 
				 }
				 $("#error").html('');
				 $("#msg").css("display","inline");
				 $("#msg").html('<font style="color:#FF8500">正在检测中 ...</font>');
				 $.getJSON("loginName",{loginName:$("#loginName").val()},
							function(myJSON){
								if(myJSON.msg=="not"){
									setTimeout(
									function(){
										$("#msg").html('<font style="color:#FF8500">恭喜,此登录名未注册.</font>');
										$("#agree").css("color","#FFFFFF");
										$("#agree").removeAttr("disabled");
										},2000);
								}else if(myJSON.msg=="yes"){
									setTimeout(
											function(){
												$("#msg").html('<font style="color:#FF8500">对不起,此登录名已存在.</font>');
												$("#agree").css("color","#ADADAD");
												$("#agree").attr("disabled","disabled");
											},2000);
								}else{
									alert("注册登录名检验异常!");
								}      
					       	});  
			 }else{
				 $("#msg").html('');
			 }
		                   }); 
		
 		$("#passwd").blur(function(){
 			var pwd = $("#passwd").val();
 			if(pwd.length>=6&&pwd.length<=16){
 				if(pwd!=""){
 	 				if (/^.*?[\d]+.*$/.test(pwd) && /^.*?[A-Za-z].*$/.test(pwd) && /^.{6,16}$/.test(pwd)) {
 	 		 		}else{
 	 		 			alert("请使用字母加数字或字符的组合密码");
 	 					return false;
 	 		 		}
 	 			}
 			}else{
 				
 			}
 			
 		});
 		
 		function len(s)
 		{
 		var l=0;
 		var a=s.split("");
 		for(var i=0;i<a.length;i++)
 		{
 		if(a[i].charCodeAt(0)<299){l++ }
 		else l+=2;
 		}
 		return l;
 		}

	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>