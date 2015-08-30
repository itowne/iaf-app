<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html>
<html>
<head>
	<n:ui includes="form" />
</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：公告管理&nbsp;&gt;&gt;&nbsp;公告信息详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>公告信息详情</span></h3>
				<div class="results report">
				<s:form id="notifyform"  action="notifyManager" namespace="/backstage/notifymanager">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">公告信息</td>
						</tr>
						<tr>
							<td class="label" style="width:100px">公告标题：</td>
							<td class="data" colspan="3"><label id="instNotice_title"  name="instNotice.title" ><s:property value="instNotice.title"/></label></td>
						</tr>
						<tr>
							<td class="label" style="width:100px">公告内容：</td>
							<td class="data" colspan="3" id="cont"><div>${instNotice.content}</div></td>
						</tr>
					</table>
					<div class="uop">
						<s:submit cssClass="dark-btn" value="修改" method="modifyNotice"/>
						<s:submit cssClass="dark-btn" value="删除" method="deleteNotify" onclick="return remove();"/>
						<input type="button" class="dark-btn"  value="取消" onclick="back();"/> 
					</div>
					</s:form>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<link rel="stylesheet" href="${ctx}/resources/kindeditor/default.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${ctx}/resources/kindeditor/sample.css" type="text/css" media="screen" />
		<script type="text/javascript" src="${ctx}/resources/kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/kindeditor/default.png"></script>
		<script type="text/javascript" src="${ctx}/resources/kindeditor/link.js"></script>
		<script type="text/javascript" src="${ctx}/resources/kindeditor/background.png"></script>
		<script type="text/javascript" src="${ctx}/resources/kindeditor/zh_CN.js"></script>
		<script type="text/javascript">
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="instNotice.content"]', {
				resizeType : 1,
				allowPreviewEmoticons : false,
				allowImageUpload : false,
				width:760,
				height:340,
				items : [
					'fontname', 'fontsize', '|', 'bold', 'italic', 'underline',
					'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
					'insertunorderedlist', '|','link','unlink','undo'],
				afterCreate : function() { 
					     this.sync(); 
					   }, 
				afterBlur:function(){ 
					     this.sync(); 
					        }             
			});
		});
		
		$(function() {
			$("#notifyform").validationEngine("attach",{   
				promptPosition:"centerRight"
			});
		});
			function checkForm(){
				if(confirm("确认修改公告?")){
					if (!$("#notifyform").validationEngine("validate")) {
						return false;
					}
					if($("#instNotice_content").val()==""){
						alter("请填写公告内容!");
						return false;
					}
					return true;
				}else{
					return false;
				}
			}
			
			function remove(){
				if(confirm("确认删除此公告吗?")){
				}else{
					return false;
				}
			}
			function back(){
				document.notifyform.action="${ctx}/backstage/notifymanager/notifyManager!execute";	
		        document.notifyform.submit();
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>