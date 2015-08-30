<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/template/mp/meta.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
		<n:head styles="mp-workframe" scripts="jquery,mp-workframe"/>
        <meta http-equiv="Cache-Control" content="No-cache,must-revalidate" />
		<meta http-equiv="Expires" content="-10" />
		<meta http-equiv="Pragma" content="no-cache" />
		
		<script type="text/javascript">
		    function close1(){
		    	parent.jDialogClose($("body").attr("id"));
		    	return false;
		    }
		</script>
</head>
	<body>
		<div id="content" class="report">
		<s:if test="hasActionErrors()"> 
		<s:iterator value="actionErrors"> 
		<h3 style="line-height:32px;text-align:center;font-size:15px;color:red;"><s:property escape="false"/>！</h3><br/>
		</s:iterator> 
		</s:if>
		<s:else>
		<h3 style="line-height:32px;text-align:center;font-size:15px;">处理成功！</h3><br/>
		</s:else>
		<div style="margin:15px auto;text-align:center;">
	   		<s:submit onclick="return close1();" value = "关  闭" cssClass="dark-btn"/>
	    </div>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>

