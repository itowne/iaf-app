<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>汇融易 - 商户管理后台</title>
<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
<style type="text/css">
.pager {
	padding: 15px 45px;
	text-align: right;
}

.pager a {
	display: inline-block;
	padding: 2px 5px;
	border: 1px solid #d1d1d1;
	color: #4a4a4a;
}

.pager a.cur,.pager a:hover {
	background: #0157ad;
	color: #fff;
}

.dark-btn {
	vertical-align: middle;
}
</style>
<base target="_self"/>
</head>
<body>
		<div class="report" style="border: 0px">
					<table width="100%" border="0px" style="border-width: 0px" cellspacing="0" cellpadding="0" class="data-table" align="center">
					<s:if test="#session.logoTFile.size<=0">
								&nbsp;
					</s:if>
					<s:else>
					<s:iterator value="#session.logoTFile" id="tfiles" status="status"> 
						<tr style="border: 0">
								<td class="data" width="450" style="text-align: center;border: 0px;">
      							<s:if test='#tfiles.metaType.indexOf("image")>-1&&#tfiles.fileType.toString()=="instLogo"'>
      							<img src="/DrawImage.do?id=${tfiles.ifile}&type=exp" width="250" height="168"/>&nbsp;&nbsp;<br/>
      							</s:if>
								</td>
								<td class="data" style="text-align: left;border: 0px;">
								<!-- <a href="javaScript:void(0)" onclick="showUpload('<s:property value="%{#tfiles.ifile}"/>');">[修改]</a>&nbsp;|&nbsp; --><a href="javaScript:void(0)" onclick="deletePic('<s:property value="%{#tfiles.ifile}"/>');">[删除]</a>
								</td>
							</tr>
					</s:iterator>
					</s:else>
					</table>
			</div>
	<script type="text/javascript">
		$(function() {
			$(".data-list tr:even").addClass("even");
			$(".data-list tr").hover(function() {
				$(this).addClass("hover");
			}, function() {
				$(this).removeClass("hover");
			});
			$(".dark-btn").hover(function() {
				$(this).addClass("dark-btn-hover");
			}, function() {
				$(this).removeClass("dark-btn-hover");
			});
			window.setTimeout(reinitLoanIframe, 300);
			window.curTab = "BASIC_INFO";
			$("#merchFrame").bind("load", function() {
				$(".tab-title li").removeClass("cur");
				$("#" + window.curTab).addClass("cur");
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
		
		function deletePic(id){
			if(confirm("确认要删除此资料?")){
				window.parent.location.href="${ctx}/backstage/instmanager/instManager!deletePic?ifile="+id;	
			}
		}
	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>