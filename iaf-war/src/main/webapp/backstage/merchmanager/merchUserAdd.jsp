<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<html>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;新增商户用户</p>
			</div>
			<div id="content">
				<h3 class="title"><span>新增商户详细信息</span></h3>
				<div class="results report">
				<s:form id="merchUserform" action="merchUser" namespace="backstage/merchmanager">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">商户信息</td>
						</tr>
						<tr>
							<td class="label">登陆名称：</td>
							<td class="data"><input  name="merchUser.loginName"  value="<s:property  value='merchUser.loginName'/>" /><font color=red>*</font></td>
							
						</tr>
						<tr>
							<td class="label">用户姓名：</td>
							<td class="data"><input  name="merchUser.userName"  value="<s:property  value='merchUser.userName'/>"  /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">用户密码：</td>
							<td class="data"><input  name="merchUser.passwd"  value="<s:property  value='merchUser.passwd'/>" /><font color=red>*</font></td>
						</tr>
					</table>
					<div class="uop">
						<s:submit cssClass="dark-btn" value="确定" method="saveMerchUser"/>
						<s:submit cssClass="dark-btn" value="取消" method="execute"/>
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

		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>