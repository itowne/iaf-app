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
			<div id="content" class="report">
				<div class="report-tab-area">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="data-table">
						<tr>
							<td class="label" width="300" style="text-align: center;">图片信息</td>
							<td class="label" width="300" style="text-align: center;">操作</td>
						</tr>
						<s:iterator value="#request.tfileList" id="tfiles" status="status">
							<tr>
								<td class="data" width="350" style="text-align: center;">
										<img src="/DrawImage.do?id=${tfiles.ifile}&type=exp"
											width="200" height="150" />
								</td>
								<td class="data" style="text-align: center;">
								<a href="${ctx}/inst/information/instInformation!deletePic?ifile=${tfiles.ifile}" class="dark-btn">删除</a>
							</tr>
						</s:iterator>
					</table>
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
				//window.curTab = "BASIC_INFO";
				$("#workframe-wrapper").bind("load",function(){
					$(".tab-title li").removeClass("cur");
					$("#"+window.curTab).addClass("cur");
				});
			});
			function reinitLoanIframe(){
				var iframe = document.getElementById("workframe-wrapper");
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
		</script> 
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>