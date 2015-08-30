<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/template/head.jsp" />
<html>
<head>
		<title>汇融易 - 机构管理后台</title>
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
</head>
<body>
		<div id="workframe-wrapper" class="clearfix">
			<s:form id="instinfoform" action="instManager" namespace="/backstage/instmanager" enctype="multipart/form-data">
			<div id="content" class="report">
				<div class="report-tab-area">
					<div class="tab-content">
					<s:hidden name="filetype" value="instLogo"/>
					<s:file id="upload" name="upload" value="选择图片文件"></s:file>
					<s:submit cssClass="dark-btn" value="上传文件" method="instLogoUpload" onclick="return checkForm()"/>
					</div>
				</div>
			</div>
			</s:form>
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
						height = $(iframe.contentWindow.document.body).height();
					}
					iframe.height = height + 10;
				}catch(e){}
				iframe=null;
				window.setTimeout(reinitLoanIframe,300);
			}
			
			function checkForm(){
				if(document.getElementById("upload").value=="") {
			    	alert("请先选择文件！");
			    	return false;
			    }
			}
			
		</script> 
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>