<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<html>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统参数设置&nbsp;&gt;&gt;&nbsp;金掌柜用户校验设置</p>
			</div>
			<div id="content">
				<h3 class="title"><span>金掌柜用户校验设置</span></h3>
				<div class="results report">
				<s:form id="instform" action="sysParam" namespace="/backstage/sysparam" >
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">金掌柜密码校验</td>
						</tr>
						<tr>
							<td class="label" style="width:100px">密码校验地址：</td>
							<td class="data"><s:textfield id="sysParam_value" style="width:600px" name="sysParam.value" /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="data" colspan="4">PC客户端跳转设置</td>
						</tr>
						<tr>
							<td class="label">跳转超时秒数：</td>
							<td class="data"><s:textfield id="jump_ticket_expire_second" style="width:600px" name="jump_ticket_expire_second" /><font color=red>*</font></td>
						</tr>
					</table>
					<div class="uop">
						<s:submit cssClass="dark-btn" value="保存" method="updatePwdCheck" onclick="return checkForm();"/>
					</div>
					</s:form>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
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
			
			
			
			function checkForm(){
				if(document.getElementById("jump_ticket_expire_second").value==""){
					alert("请填写超时时间!");
					return false;
				}else{
					if(isNaN(document.getElementById("jump_ticket_expire_second").value)){
						alert("超时时间请填写数字!");
						return false;
					}
				}
				if(confirm("是否确定要修改金掌柜密码校验地址?")){
					if(document.getElementById("sysParam_value").value=="") {
				    	alert("请输入金掌柜密码校验地址！");
				    	return false;
				    }
				}else{
					return false;
				}
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>