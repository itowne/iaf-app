<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>login</title>

</head>
<body>
<div id="wrap">
	<div id="header"> </div>
    <div id="content-wrap">
	<div></div>
	
   	  <form action="${basepath}/inst/userLogin.action"  method="post">
   	  	<div class="content">
   	  		<div class="field"><label>用户名:</label><input id="loginName" name="loginName"   type="text" maxlength="15"/><br /></div>
			<div class="field"><label>密    码：</label><input id="password" name="password"  type="password" maxlength="15"/><br /></div>
			<div class="btn">
					<input type="submit" value="登    陆"/>
			</div>
      	</div>
      </form>
    </div>
    <div id="footer"> </div>
</div>
			
</body>
</html>
