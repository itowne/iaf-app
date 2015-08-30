<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/template/mp/meta.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
		<n:head styles="mp-workframe" scripts="jquery,mp-workframe"/>
		<n:ui includes="grid"/>
        <meta http-equiv="Cache-Control" content="No-cache,must-revalidate" />
		<meta http-equiv="Expires" content="-10" />
		<meta http-equiv="Pragma" content="no-cache" />

<script type="text/javascript">
function beforeSubmit(desc){
	return confirm(desc);
}
</script>
</head>
<s:if test="hasActionErrors()"> 
<s:iterator value="actionErrors"> 
<script language="JavaScript"> 
alert('<s:property escape="false"/>') 
</script> 
</s:iterator> 
</s:if>
<s:if test="hasActionMessages()"> 
<s:iterator value="actionMessages"> 
<script language="JavaScript"> 
alert('<s:property escape="false"/>') 
</script> 
</s:iterator> 
</s:if>
<s:if test="hasFieldErrors()"> 
<s:iterator value="fieldErrors"> 
<script language="JavaScript"> 
alert('<s:property escape="false"/>') 
</script> 
</s:iterator> 
</s:if>
</html>