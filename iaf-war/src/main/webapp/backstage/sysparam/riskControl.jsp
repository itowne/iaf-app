<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<html>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统参数设置&nbsp;&gt;&gt;&nbsp;风险及交易费率设置</p>
			</div>
			<div id="content">
				<h3 class="title"><span>风险及交易费率设置</span></h3>
				<div class="results report">
				<s:form id="instform" action="sysParam" namespace="/backstage/sysparam" >
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">交易风险及金掌柜手续费率设置：</td>
						</tr>
						<!--  
						<tr>
							<td class="label">风险参数地址：</td>
							<td class="data"><s:textfield id="sysParam_value" name="sysParam.value" /><font color=red>*</font></td>
						
						</tr>
						-->
						<tr>
							<td class="label">风险交易天数：</td>
							<td class="data"><s:textfield id="sysParam_value" name="sysParam.value" /><font color=red>*</font></td>
						
						</tr>
						<tr>
							<td class="label">上月交易额比前一个月下降百分比：</td>
							<td class="data"><s:textfield id="tradeRate_sysParam" name="tradeRate_sysParam.value" />%<font color=red>*</font></td>
						
						</tr>
						<tr>
							<td class="label">最低手续费：</td>
							<td class="data"><s:textfield id="min_rate" name="minRate.value" />元<font color=red>*</font></td>
						
						</tr>
						<tr>
							<td class="label">最高手续费：</td>
							<td class="data"><s:textfield id="max_rate" name="maxRate.value" />元<font color=red>*</font></td>
						
						</tr>
						<tr>
							<td class="label">交易费率：</td>
							<td class="data"><s:textfield id="trade_rate" name="tradeRate.value" onblur="check()"/>%<font color=red>*</font></td>
						
						</tr>
					</table>
					<div class="uop">
						<s:submit cssClass="dark-btn" value="保存" method="updateRiskControl" onclick="return checkForm();"/>
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
				if(confirm("是否确定要修风险交易参数?")){
					if(document.getElementById("sysParam_value").value=="") {
				    	alert("请输入风险交易天数！");
				    	return false;
				    }else{
				    	if(isNaN(document.getElementById("sysParam_value").value)){
				    		alert("风险交易天数请填写数字!");
				    		return false;
				    	}
				    }
					if(document.getElementById("tradeRate_sysParam").value==""){
						alert("请输入风险交易下降百分比！");
						return false;
					}else{
						if(isNaN(document.getElementById("tradeRate_sysParam").value)){
							alert("风险交易下降百分比请填写数字!");
							return false;
						}
					}
					
					if(document.getElementById("min_rate").value==""){
						alert("请填写最低手续费!");
						return false;
					}else{
						if(isNaN(document.getElementById("min_rate").value)){
							alert("最低手续费请填写数字!");
							return false;
						}else{
							var min = document.getElementById("min_rate").value;
							var max = document.getElementById("max_rate").value;
							if(parseInt(max)<parseInt(min)){
								alert("最低手续费必须低于最高手续费!");
								return false;
							}
							if(parseInt(min)<0){
								alert("最低手续费请填写正整数!");
								return false;
							}
						}
					}
					if(document.getElementById("max_rate").value==""){
						alert("请填写最高手续费!");
						return false;
					}else{
						if(isNaN(document.getElementById("max_rate").value)){
							alert("最高手续费请填写数字!");
							return false;
						}else{
							var min = document.getElementById("min_rate").value;
							var max = document.getElementById("max_rate").value;
							if(parseInt(max)<0){
								alert("最高手续费请填写正整数!");
								return false;
							}
						}
					}
					if(document.getElementById("trade_rate").value==""){
						alert("请填写交易费率!");
						return false;
					}else{
						if(isNaN(document.getElementById("trade_rate").value)){
							alert("交易费率请填写数字!");
							return false;
						}else{
							if(!/^-?\d+\.?\d{0,2}$/.test(document.getElementById("trade_rate").value)){
								alert("交易费率只能保存两位小数,请重新输入!");
								return false;
						}
						}
					}
				}else{
					return false;
				}
			}
			
			function check(){
				if(document.getElementById("trade_rate").value==""){
					alert("请填写交易费率!");
					return false;
				}else{
					if(isNaN(document.getElementById("trade_rate").value)){
						alert("交易费率请填写数字!");
						return false;
					}else{
						if(!/^-?\d+\.?\d{0,2}$/.test(document.getElementById("trade_rate").value)){
							alert("交易费率只能保存两位小数,请重新输入!");
							return false;
					}
					}
				}
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>