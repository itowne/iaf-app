<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<title>汇融易 - 机色管理后台</title>
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
  
  <div class="report-tab-area">
  	
  	<div id="content" class="report" >
	    
	    <div class="tab-content">
	   		<div align="center">指令提交中......</div>
	   		<form target="" id="webpay" action='<s:property value="url"/>' method="post">
	   			<s:hidden name="signMsg"></s:hidden>
	   			<s:hidden name="msg"></s:hidden>
	   		</form>
	  </div>
	</div>
  </div>
</div>
<script type="text/javascript">
			$(function(){
				$("#webpay").submit();
			});
			
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
