<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/template/head.jsp" />
<html>
<head>
		<title>汇融易 - 商户管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
<style type="text/css">
	.pager{
		padding:15px 45px;text-align:right;
	}
	.pager a{
		display:inline-block;padding:2px 5px;border:1px solid #d1d1d1;color:#4a4a4a;
	}
	.pager a.cur,.pager a:hover{
		background:#0157ad;color:#fff;
	}
	.dark-btn{
		vertical-align:middle;
	}
</style>
<base target="_self"/>
</head>
<body>
		<div id="workframe-wrapper" class="clearfix">
			
			<div id="content" class="report">
				
				<div class="report-tab-area">
					<s:form target="" action="infoPermSet" method="POST" onsubmit="return checkFrom();">
					<div class="tab-content">
					<h3 style="line-height:42px;text-align:center;font-size:15px;">经营数据报告内容申诉</h3>
					<table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr align="center">
		    				<td class="lable" width="200" align="right">申诉内容:</td>
				    		<td class="data" align="left"><input type="text" name="merchInfoAppeal.appealContent" id="appealContent"/></td>
				    	</tr>
				    	<tr>
				    		<td class="lable" width="200" align="right">申诉原因及说明:</td>
				    		<td class="data" align="left"><textarea name="merchInfoAppeal.appealReason" id="appealReason" rows="7" cols="35"></textarea></td>
				    	</tr>
				    	<tr>
				    		<td class="lable" width="200" align="right">申诉联系人:</td>
				    		<td class="data" align="left"><input type="text" name="merchInfoAppeal.appealMan" id="appealMan"/></td>
				    	</tr>
				    	<tr>
				    		<td class="lable" width="200" align="right">联系电话:</td>
				    		<td class="data" align="left"><input type="text" name="merchInfoAppeal.appealPhone" id="appealPhone"/></td>
				    	</tr>
			    	</table>
			    	<div align="center"><font style="color:red">【说明】：你的申诉我们会安排客户方人员尽快处理跟进，请耐心等待。</font></div>
			    	<div style="margin: 20px" align="center">
			    	<s:submit cssClass="dark-btn" method="saveMerchInfoAppeal" value="确  定"></s:submit>
			    	<a class="dark-btn" href="JavaScript:close();" >关&nbsp;&nbsp;&nbsp;&nbsp;闭</a>
			    	</div>
					</div>
					</s:form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function checkFrom(){
				if(confirm("确认提交请求?")){
				if($("#appealContent").val()==""){
					alert("申诉内容不能为空!");
					return false;
				}
				if($("#appealReason").val()==""){
					alert("申诉原因不能为空!");
					return false;
				}
				if($("#appealMan").val()==""){
					alert("申诉人不能空!");
					return false;
				}
				if($("#appealPhone").val()==""){
					alert("申诉人电话不能空!");
					return false;
				}else{
					if(isNaN($("#appealPhone").val())){
						alert("申诉人电话请填写数字!");
						return false;
					}
				}
				}else{
					return false;
				}
			}
			function close(){
				parent.jDialogClose($("body").attr("id"));
			}
			
		</script> 
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>