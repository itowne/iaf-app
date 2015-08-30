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
<script language="JavaScript">
   javascript:window.history.forward(1);
</script>
</head>
<body id="body">
	<%@include file="/template/portal/header.jspf"%>
	<div id="main-reg">
		<div class="content">
			<ul class="tab-reg">
				<li class="cur">商户注册申请</li>
				<li onclick="location.href='${ctx}/instReg!toInstReg';">机构注册登记</li>
			</ul>
			<div class="reg-fields" style="width:810px">
				<p class="tips">
					为了让放贷机构在审批您的借款业务时，能更好地了解您企业的经营情况，请补充完善以下的企业信息：
				</p>
				<s:form action="#" method="post" id="form1" name="form1">
					<div class="row">
						<label><font style="color:red">*</font>商户性质：</label>
						非金掌柜商户
						<!--<s:select
							list="#{'0':'请选择','stateRun':'国营','collective':'集体','privateEnter':'私企','indBusiness':'个体工商户','jointVenture':'合资'}"
							key="merchNature" id="merchNature"></s:select>-->
					</div>
					<!--  
					<div class="row">
						<label>在线联系QQ号:</label><s:textfield name="qqUid" id="qqUid" cssClass="reg-input validate[required,custom[onlyNumberSp]]" />
						<span id="qqUidTip" style="font-size:15px;background-color:#FFF4C1;margin-left:70px;color:#FF8500;display:none">请填写您的QQ号码，以便放贷机构网上在线与你联系</span>
					</div>
					-->
					<div class="row">
						<label><font style="color:red">*</font>联系人：</label><s:textfield name="contract" id="contract" cssClass="reg-input validate[required,minSize[2]]" />
						<span id="contractTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">请填写联系人姓名，以便放贷机构与你联系</span>
					</div>
					<div class="row">
						<label><font style="color:red">*</font>联系电话：</label><s:textfield name="contractTel" id="contractTel" cssClass="reg-input validate[required,custom[phone]]" />
						<span id="contractTelTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">请填写联系电话，以便放贷机构与你联系</span>
					</div>
					<div class="row" >
						<label>商户名称：</label><s:textfield name="merchName" id="merchName" cssClass="validate" />
						<span id="msg" style="display:none"></span>
						<s:if test="merchNameError!=null">
						<span id="error" style="color: #FF8500">${merchNameError}</span>
						</s:if>
						<span id="merchNameTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">请填写您企业的工商登记注册名称</span>
					</div>
					<div class="row">
					<label>商户行业类别</label>
					 <s:select list="mccMap" name="mccCode" id="mccMap" listKey="key" listValue="value" headerKey="" headerValue="请选择行业大类" style="width:130px;" cssClass="validate"></s:select>
							<select name="code" id="code" style="width:120px;" class="validate"></select>
					<span id="mccMapTip" style="font-size:15px;background-color:#FFF4C1;margin-left:85px;color:#FF8500;display:none">请选择您经营所属的行业类别</span>
					</div>
					<input type="hidden" name="passwd" value="<s:property value="%{passwd}"/>"/>
					<input type="hidden" name="loginName" value="<s:property value="%{loginName}"/>">
				</s:form>
			</div>
			<!-- 
			<div class="reg-uop">
				<a href="javascript:void(0);" onclick="show();">&lt;&lt;汇融易服务协议&gt;&gt;</a>&nbsp;&nbsp;&nbsp;&nbsp;已有汇融易账户?
				<a href="${ctx}/merchLogin.jsp">立即登录</a>
			</div>
			 -->
			<div class="reg-uop" style="margin-top: 20px;margin-left: 100px">
				<input type="button" class="dark-btn" name="agree" id="agree"
					onclick="javaScript:submit();" value="提 交" />&nbsp;&nbsp;
					<!-- <input type="button" class="dark-btn" name="agree_after" id="agree_after"
					onclick="javaScript:submit();" value="以后再补充" /> -->
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
// 	$("#merchName").focus(function(){
// 		$("#merchNameTip").css("display","inline");
// 	});
// 	$("#merchName").blur(function(){
// 		$("#merchNameTip").css("display","none");
// 	});
// 	$("#mccMap").focus(function(){
// 		$("#mccMapTip").css("display","inline");
// 	});
// 	$("#mccMap").blur(function(){
// 		$("#mccMapTip").css("display","none");
// 	});
// 	$("#code").focus(function(){
// 		$("#mccMapTip").css("display","inline");
// 	});
// 	$("#code").blur(function(){
// 		$("#mccMapTip").css("display","none");
// 	});
	$("#qqUid").focus(function(){
		$("#qqUidTip").css("display","inline");
	});
	$("#qqUid").blur(function(){
		$("#qqUidTip").css("display","none");
	});
	$("#contract").focus(function(){
		$("#contractTip").css("display","inline");
	});
	$("#contract").blur(function(){
		$("#contractTip").css("display","none");
	});
	$("#contractTel").focus(function(){
		$("#contractTelTip").css("display","inline");
	});
	$("#contractTel").blur(function(){
		$("#contractTelTip").css("display","none");
	});
	
	
	$("#mccMap").change(function(){
		if($("#mccMap").val()==""){
			$("#code").empty();                  
	    	$("#code").html('<option value="">请选择行业小类</option>'); 
		}else{
			$.getJSON("/../mcc",{code:$(this).val()},
			function(myJSON){
				var myOptions="";                  
				for(var i=0;i<myJSON.mccList.length;i++){    
					myOptions += '<option value="' + myJSON.mccList[i].id + '">' +myJSON.mccList[i].name + '</option>';                  
	             }   
	             $("#code").empty();                  
	             $("#code").html(myOptions);              
	       	}); 
		}         
		});
	$("#mccMap").change();
		function reloadVerifyCode(obj) {
			var d = new Date();
			obj.src = "${ctx}/kaptcha.jpg?d=" + d.getMilliseconds();
		}
		function show(){
			window.showModalDialog("/showProtocol.jsp",window,"dialogWidth=940px;dialogHeight=600px");
		}
		$(function() {
			$("#form1").validationEngine();
		});

		function submit() {
			if(confirm("确认提交请求？")){
				if (check()) {
					$("#form1").attr("action", "merchReg!reg");
					$("#form1").submit();
				}
			}else{
				return false;
			}
			
		}
		function check() {
			if(!$("#form1").validationEngine("validate")){
				return false;
			}
			if ($('#passwd').val() != $('#confirmPasswd').val()) {
				alert("两次密码输入不一致!");
				return false;
			}
// 			if ($('#merchNature').val() == "0") {
// 				alert("请选择商户性质!");
// 				return false;
// 			}
			var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\.,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
// 			if(txt.test($("#merchName").val())){
// 				alert("商户名不允许特殊字符");
// 				return false;
// 			}
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
// 		 $("#merchName").blur(function() {  
// 			 if($("#merchName").val()==""){
// 				 alert("请填写商户名称!");
// 				 return false;
// 			 }else{
// 				 var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\.,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
// 					if(txt.test($("#merchName").val())){
// 						alert("商户名称不允许特殊字符");
// 						return false;
// 					} 
// 			 }
// 			 $("#error").html('');
// 			 $("#msg").css("display","inline");
// 			 $("#msg").html('<font style="color:#FF8500">正在检测中 ...</font>');
// 			 $.getJSON("loginName!merchNameCheck",{merchName:$("#merchName").val()},
// 						function(myJSON){
// 							if(myJSON.tip=="not"){
// 								setTimeout(function(){$("#msg").html('<font style="color:#FF8500">恭喜,此商户名未注册.</font>');
// 								$("#agree").css("color","#FFFFFF");
// 								$("#agree").removeAttr("disabled");
// 								$("#agree_after").css("color","#FFFFFF");
// 								$("#agree_after").removeAttr("disabled");
// 								},2000);
// 							}else if(myJSON.tip=="yes"){
// 								setTimeout(function(){$("#msg").html('<font style="color:#FF8500">对不起,此商户名已存在.</font>');
// 								$("#agree").css("color","#ADADAD");
// 								$("#agree").attr("disabled","disabled");
// 								$("#agree_after").css("color","#ADADAD");
// 								$("#agree_after").attr("disabled","disabled");
// 								},2000);
// 							}else{
// 								alert("注册登录名检验异常!");
// 							}      
// 				       	});     
// 	                   });  
</script>
	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>