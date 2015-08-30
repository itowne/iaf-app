<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 商户管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/mp-workframe.css"/>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：我的产品&nbsp;&gt;&gt;&nbsp;查看新产品</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查看产品详细信息</span></h3>
				<div class="results report">
				<s:form id="storeformDetail" action="/merch/merchCollPdt">
					<input type="hidden" name="loanPdt.iloanPdt" id="iloanPdt" value="<s:property value='loanPdt.iloanPdt'/>">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">产品名称</td>
							<td class="data"><s:property value="loanPdt.pdtName"/></td>
							<td class="label">放款周期</td>
							<td class="data"><s:property value="loanPdt.creditTerm"/>（天）</td>
						</tr>
						<tr>
							<td class="label">贷款最低金额</td>
							<td class="data"><s:property  value='loanPdt.minQuota'/>（万元）</td>
							<td class="label">贷款最高金额</td>
							<td class="data"><s:property  value='loanPdt.maxQuota'/>（万元）</td>
						</tr>
						<tr>
							<td class="label">贷款最短周期</td>
							<td class="data"><s:property  value='loanPdt.minTerm'/>（个月）</td>
							<td class="label">贷款最长周期</td>
							<td class="data"><s:property  value='loanPdt.maxTerm'/>（个月）</td>
						</tr>
						<tr>
							<td class="label">贷款利率</td>
							<td class="data"><s:property  value='loanPdt.rate'/>（%）起</td>
							<td class="label">放贷受理地区范围</td>
							<td class="data"><s:property value="loanPdt.area"/>
							</td>
						</tr>
						<tr>
							<td class="label">产品特点</td>
							<td class="data" colspan="3"><textarea name="loanPdt.feature"  rows="3" cols="71" readonly="readonly"><s:property value='loanPdt.feature'/></textarea></td>
						</tr>
						<tr>
							<td class="label">申请条件</td>
							<td class="data" colspan="3"><textarea name="loanPdt.condition"  rows="3" cols="71" readonly="readonly"><s:property value='loanPdt.condition'/></textarea></td>
						</tr>
						<tr>
							<td class="label">申请所需材料</td>
							<td class="data" colspan="3"><textarea name="loanPdt.cl" rows="3" cols="71" readonly="readonly"><s:property value='loanPdt.cl'/></textarea></td>
						</tr>
					</table>
					<div class="uop">
						<s:submit value="返回" cssClass="dark-btn" type="button" />
					</div>
					</s:form>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>