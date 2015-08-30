<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html>

<html>
<head>
<n:ui includes="form" />
<style type="text/css">
li {
    list-style:square;
     list-style-position:inside;
}
</style>
</head>
<body>
	<div id="workframe-wrapper" class="clearfix">
		<div id="location">
			<p>当前位置：风险控制&nbsp;&gt;&gt;&nbsp;风险控制明细</p>
		</div>
		<div id="content">
			<h3 class="title">
				<span>风险控制明细</span>
			</h3>
			<div class="results report">
			<div align="center" style="font-size:18px">商户风险提示</div>
			<div align="center" style="color:red;margin-bottom: 22px">(仅供参考)</div>
				<table width="60%" border="0" cellspacing="0" cellpadding="0"
					class="data-table">
					<tr>
						<td class="label" style="text-align: center" colspan="2">[经营风险]</td>
					</tr>
					<tr>
					<td class="data" colspan="2">
					<div style="margin-left:130px;">
						<ul>
							<s:if test="uninstallNum!=0">
							<li>放贷之后,商户的<strong>pos</strong>撤机：<strong><s:property value="uninstallNum" /></strong>台<font style="color: red">{高风险}</font></li>
							</s:if>
						</ul>
						</div>
					</td>
					</tr>
					<tr>
						<td class="label" style="text-align: center" colspan="2">[贷款风险]</td>
					</tr>
					<tr>
					<td class="data" colspan="2">
					<div style="margin-left:130px;">
					<ul>
						<s:if test="expireNum!=0">
							<li>商户目前还款逾期记录：<strong><s:property value="expireNum" />笔</strong><font style="color: red">{高风险}</font></li>
						</s:if>
						<s:if test="debitAmt!=0">
						<li>商户在平台内目前欠款总额：<strong><s:property value="debitAmt" /></strong>元</li>
						</s:if>
						<s:if test="freeze==true">
						<li>商户的清算账号是否被借贷方冻结：<strong>商户清算账户被冻结<font style="color: red"></strong>{高风险}</font></li>
						</s:if>
					</ul>
					</div>
						</td>
					</tr>
					<tr>
						<td class="label" style="text-align: center" colspan="2">[交易风险]</td>
					</tr>
					<tr>
						<td class="data" colspan="2">
						<div style="margin-left:130px;">
						<ul>
							<s:if test="rate>=riskRate">
							<li>上月POS交易额比前一个月下降:<strong><font style="color: red"><s:property value="rate" /></font></strong>%</li>
							</s:if>
							<s:if test="xx==true">
							<li>上个月的交易额是否少于本期应还款金额：<strong>上月交易少于本期还款金额</strong></li>
							</s:if>
							<s:if test="hasTransLog==true">
							<li>商户是否已经连续多日没有pos交易：<strong>商户已有<s:property value="%{riskDays}"/>日没有pos交易</strong><font style="color:red">{高风险}</font></li>
							</s:if>
						</ul>
						</div>
						</td>
					</tr>
				</table>
				<div class="uop">
					<input type="button" class="dark-btn" value="返回" onclick="back();" />
				</div>
				<div class="results report"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function back() {
			window.location.href = "${ctx}/inst/loanord/riskControl.action";
		}
	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>