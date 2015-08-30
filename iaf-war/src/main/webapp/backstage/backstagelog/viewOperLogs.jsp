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
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;操作日志&nbsp;&gt;&gt;&nbsp;日志详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查看日志详细信息</span></h3>
				<s:form id="merchUserform" action="backstageLog" namespace="/backstage/backstagelog">
				<div class="results report">
					<table width="40%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">日志序号：</td>
							<td class="data"><s:property value="operLogs.traceNo"/></td>
						</tr>
						<tr>
							<td class="label">操作日期：</td>
							<td class="data">
							<s:date name="operLogs.genTime"  format="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<td class="label">操作IP地址：</td>
							<td class="data"><s:property  value='operLogs.ipAddr'/></td>
						</tr>
						<tr>
							<td class="label">备注信息：</td>
							<td class="data"><s:property  value='operLogs.memo'/></td>
						</tr>
						<tr>
							<td class="label">操作角色编号：</td>
							<td class="data"><s:property  value='operLogs.iroles'/></td>
						</tr>
						<tr>
							<td class="label">操作角色：</td>
							<td class="data"><s:property  value='operLogs.roleNames'/></td>
						</tr>
						<tr>
							<td class="label">操作用户编号：</td>
							<td class="data"><s:property  value='operLogs.iuser'/></td>
						</tr>
						<tr>
							<td class="label">操作用户：</td>
							<td class="data"><s:property  value='operLogs.userName'/></td>
						</tr>
						<tr>
							<td class="label">操作账号：</td>
							<td class="data"><s:property  value='operLogs.loginName'/></td>
						</tr>
						<tr>
							<td class="label">机构代号：</td>
							<td class="data"><s:property  value='operLogs.iinst'/></td>
						</tr>
						<tr>
							<td class="label">机构类型：</td>
							<td class="data"><s:property  value='operLogs.instType.desc'/></td>
						</tr>
						<tr>
							<td class="label">操作类型：</td>
							<td class="data"><s:property  value='operLogs.operType.desc'/></td>
						</tr> 
						<tr>
							<td class="label">操作结果：</td>
							<td class="data"><s:property  value='operLogs.operStat.desc'/></td>
						</tr>
						<tr>
							<td class="label">操作状态：</td>
							<td class="data"><s:property  value='operLogs.operStat.desc'/></td>
						</tr>
					</table>
					<div class="uop">
						<s:submit cssClass="dark-btn" value="返回" method="execute"/>
					</div>
				</div>
				</s:form>
			</div>
		</div>
		
	
			
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>
