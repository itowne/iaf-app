<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>初始化脚本</title>
</head>
<body>
	<div id="wrap">
		<div id="header"></div>
		<div id="content-wrap">
			<s:if test="hasActionMessages()">
				<s:iterator value="actionMessages">
					<s:property value="actionMessages" />
				</s:iterator>
			</s:if>
		</div>
		<div id="footer"></div>
	</div>

</body>
</html>
