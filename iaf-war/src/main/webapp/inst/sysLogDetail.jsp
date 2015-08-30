<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<jsp:include page="${ctx}/template/head.jsp" />
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 机构管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;系统日志&nbsp;&gt;&gt;&nbsp;日志详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查看日志详细信息</span></h3>
				<div class="results report">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">系统日志跟踪号：</td>
							<td class="data"> <s:property  value="operLog.traceNo"></s:property></td>
							<td class="label">操作员帐号：</td>
							<td class="data"><s:property  value="operLog.loginName"></s:property></td>
						</tr>
						<tr>
							<td class="label">操作员姓名：</td>
							<td class="data"><s:property value="operLog.userName"></s:property></td>
							<td class="label">操作员所属角色：</td>
							<td class="data"><s:property value="operLog.roleNames"></s:property></td>
						</tr>
						<tr>
							<td class="label">用户类型：</td>
							<td class="data"><s:property value="operLog.instType.desc"></s:property></td>
							<td class="label">系统用户ID号：</td>
							<td class="data"><s:property value="operLog.iuser"></s:property></td>
						</tr>
						<tr>
							<td class="label">操作IP地址：</td>
							<td class="data"><s:property value="operLog.ipAddr"></s:property></td>
							<td class="label">操作日期：</td>
							<td class="data">
							<s:date name="operLog.genTime" format="yyyy-MM-dd HH:MM:ss"/></td>
						</tr>
						<tr>
							<td class="label">操作内容概述：</td>
							<td class="data" colspan="3"><s:property value="operLog.operType.desc"/></td>
						</tr>
						<tr>
							<td class="label">操作结果：</td>
							<td class="data" colspan="3"><s:property value="operLog.operStat.desc"></s:property></td>
						</tr>
						<tr>
							<td class="label">操作备注：</td>
							<td class="data" colspan="3"><s:property value="operLog.memo"/></td>
						</tr>
					</table>
					<div class="uop">
						<input type="button" class="dark-btn"  value="返&nbsp;&nbsp;回" onclick="back();"/> 
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			
			function back(){
				window.location.href="${ctx}/inst/operLog";
			}
		</script>
			
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>
