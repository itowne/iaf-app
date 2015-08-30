<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 商户管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="../resources/css/mp-workframe.css"/>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
		  <div id="location">
				<p>当前位置：我的资料&nbsp;&gt;&gt;&nbsp;修改密码&nbsp;&gt;&gt;&nbsp;修改成功</p>
			</div>
			<div id="content" class="report">
				<h3 class="title"><span>修改密码</span></h3>
                <div class="search-bar">
                <div  style="text-align:center">
                <span style="color:#ff660a;font-size:15px;margin-top:50px">密码修改成功！</span>
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
			function doSubmit(){
				window.location.href = "${ctx}/merch/merchPwd.action";
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>