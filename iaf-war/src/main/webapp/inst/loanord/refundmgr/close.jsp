<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<s:if test="hasActionErrors()"> 
<s:iterator value="actionErrors"> 
<script language="JavaScript"> 
window.returnValue = 1;
</script> 
</s:iterator> 
</s:if>
<s:if test="hasActionMessages()"> 
<s:iterator value="actionMessages"> 
<script language="JavaScript"> 
window.returnValue = 1;
</script> 
</s:iterator> 
</s:if>
<<script type="text/javascript">
<!--
	window.close();
//-->
</script>
