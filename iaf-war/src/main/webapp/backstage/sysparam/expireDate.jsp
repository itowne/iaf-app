<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<html>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统参数设置&nbsp;&gt;&gt;&nbsp;借贷业务期限设置</p>
			</div>
			<div id="content">
				<h3 class="title"><span>借贷业务期限设置</span></h3>
				<div class="results report">
				<s:form id="instform" action="sysParam" namespace="/backstage/sysparam" >
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">失效期限设置</td>
						</tr>
						<tr>
							<td class="label">系统默认失效期限(天):</td>
							<td class="data"><s:textfield id="sysParam_value" style="width:30px" name="sysParam.value" /><font color=red>*</font>
							&nbsp;包括：1、商户发布借款信息的最长有效期限；2、机构受理和审核借款申请的最长有效期限。</td>
						</tr>
						
					</table>
					<div class="uop">
						<s:submit cssClass="dark-btn" value="保存" method="updateExpireDate" onclick="return checkForm();"/>
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
				if(confirm("是否确定要修改失效日期?")){
					if(document.getElementById("sysParam_value").value=="") {
				    	alert("请输入失效日期！");
				    	return false;
				    }
					
					if(isNaN(document.getElementById("sysParam_value").value)) {
				    	alert("失效日期只能填写数字！");
				    	return false;
				    }
				}else{
					return false;
				}
			}
			
			function checkRate(input)
			{
			     var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字     //判断正整数 /^[1-9]+[0-9]*]*$/  
			     if (!re.test(input.rate.value))
			    {
			        alert("请输入数字(例:0.02)");
			        input.rate.focus();
			        return false;
			     }
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>