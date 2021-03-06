<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
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
					
					<div class="tab-content">
					
					<h3 style="line-height:42px;text-align:center;font-size:15px;">文件修改成功！</h3>
					<h3 style="line-height:42px;text-align:center;font-size:15px;">
					<a href="javaScript:close()" class="dark-btn">确  定</a></h3>
					
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
			window.setTimeout(reinitLoanIframe,300);
			window.curTab = "BASIC_INFO";
			$("#merchFrame").bind("load",function(){
				$(".tab-title li").removeClass("cur");
				$("#"+window.curTab).addClass("cur");
			});
		});
		function reinitLoanIframe(){
			var iframe = document.getElementById("logoFrame");
			try{
				var height = iframe.contentWindow.document.body.scrollHeight;
				if(/webkit/.test(navigator.userAgent.toLowerCase())){
					height = $(iframe.contentWindow.document.body).height() + 40;
				}
				iframe.height = height;
			}catch(e){}
			iframe=null;
			window.setTimeout(reinitLoanIframe,300);
		}
		
		function close(){
			window.parent.location.href="${ctx}/inst/information/instInformation";
		}
		</script> 
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>