<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<html>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统参数设置&nbsp;&gt;&gt;&nbsp;商户信息FTP设置</p>
			</div>
			<div id="content">
				<h3 class="title"><span>商户信息FTP设置</span></h3>
				<div class="results report">
				<s:form id="instform" action="sysParam" namespace="/backstage/sysparam" >
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">商户资料文件的FTP访问路径及账号密码设置：</td>
						</tr>
						<tr>
							<td class="label">FTP服务器IP地址：</td>
							<td class="data"><s:textfield id="ftp_remote_ipaddr" name="ftp_remote_ipaddr" /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">FTP远程访问端口：</td>
							<td class="data"><s:textfield id="ftp_remote_port" name="ftp_remote_port" /><font color=red>*</font></td>
						
						</tr>
						<tr>
							<td class="label">文件读取路径：</td>
							<td class="data"><s:textfield id="ftp_reomte_path" name="ftp_reomte_path" /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">远程文件前缀：</td>
							<td class="data"><s:textfield id="ftp_file_prefix" name="ftp_file_prefix" /><font color=red>*</font></td>
						
						</tr>
						<tr>
							<td class="label">FTP本地路径：</td>
							<td class="data"><s:textfield id="ftp_local_path" name="ftp_local_path" /><font color=red>*</font></td>
						
						</tr>
						<tr>
							<td class="label">FTP用户名：</td>
							<td class="data"><s:textfield id="ftp_username" name="ftp_username" /><font color=red>*</font></td>
						
						</tr>
						<tr>
							<td class="label">FTP密码：</td>
							<td class="data"><s:textfield id="ftp_passwd" name="ftp_passwd" /><font color=red>*</font></td>
						
						</tr>
						
					</table>
					<div class="uop">
						<s:submit cssClass="dark-btn" value="保存" method="updateMerchBaseInfo" onclick="return checkForm();"/>
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
				if(confirm("是否确定要修改商户基本设置?")){
				
				}else{
					return false;
				}
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>