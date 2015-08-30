<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html>
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<n:head styles="portal-base,portal-hp" scripts="jquery" />
<n:ui includes="form" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />

<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
<script language="JavaScript">
   javascript:window.history.forward(1);
</script>
</head>
<body id="body">
	<%@include file="/template/portal/header.jspf"%>
	<div id="main-reg">
		<div class="content">
			<ul class="tab-reg">
				<li onclick="location.href='${ctx}/merchReg!execute';">商户注册申请</li>
				<li class="cur">机构注册登记</li>
			</ul>
			<div class="reg-fields">
			<!--  
				<p class="tips">
					<strong>高信用，零风险，高收益</strong>
				</p>
				-->
				<font class="red">温馨提示:</font><br>
				<p class="tips">
					请登记贵公司的基本信息，我司人员会尽快联系您并办理相关手续。只有成功注册，您才能在本站内开展放贷业务。如若您已登记注册申请，请致电 400-628-6928 咨询受理进度情况
				</p>
				<br>
				<form action="#" method="post" id="form1" name="form1">
					<div class="row">
						<label>公司名称：</label>
						<s:textfield id="instName" name="instName" cssClass="reg-input validate[required,minSize[2]]" />
						<s:if test="instNameError!=null">&nbsp;<span style="color: red">${instNameError}</span>
						</s:if>
						<span id="instNameTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">请填写您企业的工商登记注册名称.</span>
					</div>
					<div class="row">
						<label>所在城市：</label>
						<s:select cssClass="validate[required]" list="provMap" name="provinceCode" id="provMap"  listKey="key" listValue="value" headerKey="" headerValue="请选择省份" style="width:130px;"></s:select>
						<select class="validate[required]" name="cityCode" id="cityCode" style="width:120px;"></select>
						<span id="provMapTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">请填写企业所在的省份和地市.</span>
					</div>
					<div class="row">
						<label>联系人：</label>
						<s:textfield id="contactName" name="contactName" cssClass="reg-input validate[required,minSize[2]]" />
						<s:if test="contactNameError!=null">&nbsp;<span style="color: #FF8500">${contactNameError}</span>
						</s:if>
						<span id="contactNameTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">请填写联系人姓名.</span>
					</div>
					<div class="row">
						<label>联系电话：</label>
						<s:textfield id="mobilePhone" name="mobilePhone" cssClass="reg-input validate[required,custom[phone]" />
						<s:if test="mobilePhoneError!=null">&nbsp;<span style="color: #FF8500">${mobilePhoneError}</span>
						</s:if>
						<span id="mobilePhoneTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">请填写联系人的联系电话.</span>
					</div>
					<div class="row">
						<label>性别：</label> <input type="radio" name="gender" value="MAN" checked="checked" />男 <input type="radio" name="gender" value="woman" />女
					</div>
					<div class="row">
						<label>验证码：</label> <input style="padding-top: 18px" type="text" class="form-input validate[required]" id="checkCode" name="captcha">&nbsp;
						<%
							long random = System.currentTimeMillis();
						%>
						<img style="padding-top: 20px" alt="看不清楚,换一张" id="kaptcha" src="${ctx}/kaptcha.jpg?d=<%=random%>"/>看不清?<a href="javaScript:void(0)" onclick="reloadVerifyCode()">换一张</a>
						<s:if test="captchaError!=null">&nbsp;<span style="color: red">${captchaError}</span>
						</s:if>
						&nbsp;&nbsp;&nbsp;&nbsp;<span id="checkCodeTip" style="font-size:15px;background-color:#FFF4C1;color:#FF8500;display:none">请填写输入栏右侧图片上的字母或数字</span>
					</div>
					<div class="reg-uop">
						<input type="button" class="dark-btn" name="agree" id="agree" onclick="javaScript:agreed();" value="提&nbsp;&nbsp;交" /><br>
						我已有汇融易交易帐号?<a href="${ctx}/instLogin.jsp">立即登录</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="/template/portal/co-bank.jspf"%>
	<%@include file="/template/portal/footer.jspf"%>
	<script type="text/javascript">
	$("#instName").focus(function(){
		$("#instNameTip").css("display","inline");
	});
	$("#instName").blur(function(){
		$("#instNameTip").css("display","none");
	});
	$("#contactName").focus(function(){
		$("#contactNameTip").css("display","inline");
	});
	$("#contactName").blur(function(){
		$("#contactNameTip").css("display","none");
	});
	$("#mobilePhone").focus(function(){
		$("#mobilePhoneTip").css("display","inline");
	});
	$("#mobilePhone").blur(function(){
		$("#mobilePhoneTip").css("display","none");
	});
	$("#checkCode").focus(function(){
		$("#checkCodeTip").css("display","inline");
	});
	$("#checkCode").blur(function(){
		$("#checkCodeTip").css("display","none");
	});
	$("#provMap").focus(function(){
		$("#provMapTip").css("display","inline");
	});
	$("#provMap").blur(function(){
		$("#provMapTip").css("display","none");
	});
	$("#cityCode").focus(function(){
		$("#provMapTip").css("display","inline");
	});
	$("#cityCode").blur(function(){
		$("#provMapTip").css("display","none");
	});
	
	$(function(){
			$("#form1").validationEngine();
	});
			$("#provMap").change(function(){  
				if($("#provMap").val()==""){
					$("#cityCode").empty();                  
		        	$("#cityCode").html('<option value="">请选择市区</option>'); 
				}else{
					$.getJSON("province",{provinceCode:$(this).val()},
							function(myJSON){
								var myOptions="<option value=''>请选择市区</option>";                  
								for(var i=0;i<myJSON.provinceList.length;i++){    
									myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
					             }   
					             $("#cityCode").empty();                  
					             $("#cityCode").html(myOptions);              
					       	});      
				}
		   	});
			$("#provMap").change();
		//function reloadVerifyCode(obj) {
			//var d = new Date();
			//obj.src = "${ctx}/kaptcha.jpg?d=" + d.getMilliseconds();
		//}
		function reloadVerifyCode() {
			var d = new Date();
			$("#kaptcha").attr("src","${ctx}/kaptcha.jpg?d=" + d.getMilliseconds());
		}
		function check() {
			if (!$("#form1").validationEngine("validate")) {
				return false;
			}
			return true;
		}
		function agreed() {
			if (check()) {
				$("#form1").attr("action", "${ctx}/instReg!instReg");
				$("#form1").submit();
			}
		}
	</script>

	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>