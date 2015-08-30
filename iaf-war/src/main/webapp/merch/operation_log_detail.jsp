<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>


<body>
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：操作日志&nbsp;&gt;&gt;&nbsp;详情</p>
  </div>
  <div id="content" class="report">
    <h3 class="title"><span>操作日志详情</span>&nbsp;&nbsp;</h3>
    
    <div class="report-tab-area">
				<div class="">
					<div class="inst-info">
					<h3 class="title">操作日志</h3>
					</div>
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">系统日志跟踪号：</td>
							<td class="data"> <s:property  value="operLog.traceNo"></s:property></td>
							<td class="label">操作帐号：</td>
							<td class="data"><s:property  value="operLog.loginName"></s:property></td>
						</tr>
						<tr>
							<td class="label">操作员姓名：</td>
							<td class="data"><s:property value="operLog.userName"></s:property></td>
							<td class="label">操作员所属角色：</td>
							<td class="data"><s:property value="operLog.iroles"></s:property></td>
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
			</div>
			<div align="center" style="margin-top: 20px"><a href="${ctx}/merch/merchOperLog.action" class="dark-btn">返&nbsp;回</a></div>
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
			
			function productDetail(){
				window.location.href="${ctx}/merch/merchMyReq!prodDetail";
			}

			
			
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>
