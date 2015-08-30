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
	<div id="workframe-wrapper" class="clearfix">
			<div id="content" class="report">
				<div class="report-tab-area">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="data-table" align="center">
						<tr>
							<td class="label" width="15px">&nbsp;</td>
							<td class="label" width="300px" style="text-align: center;">图片信息</td>
							<td class="label" width="80px" style="text-align: center;">操作</td>
						</tr>
						<tr>
						<td class="label">营业执照</td>
						<td class="data" colspan="2">
						<table width="100%" border="0" style=" border-width:0px" cellspacing="0" cellpadding="0"class="" >
								<s:iterator value="#session.tFileList" id="tfiles" status="status">
								<s:if test='#tfiles.fileType.toString()=="YYZZ"'>
							<tr>
								<td class="data" width="500px">
									<img style="padding:5px" src="/DrawImage.do?id=${tfiles.ifile}&type=exp" width="450" height="300" />
								</td>
								<td class="data" align="center">
								<a href="javaScript:void(0)" onclick="remove(<s:property value='#tfiles.ifile'/>)" class="dark-btn">删除</a>
								</td>
							</tr>
							</s:if>
						</s:iterator>
							</table>
						</td>			
						</tr>
						<tr>
						<td class="label">法人身份证</td>
						<td class="data" colspan="2">
						<table width="100%" border="0" style=" border-width:0px" cellspacing="0" cellpadding="0"class="" >
								<s:iterator value="#session.tFileList" id="tfiles" status="status">
								<s:if test='#tfiles.fileType.toString()=="FRSFZ"'>
							<tr>
								<td class="data" width="500px">
									<img style="padding:5px" src="/DrawImage.do?id=${tfiles.ifile}&type=exp" width="450" height="300" />
								</td>
								<td class="data" align="center">
								<a href="javaScript:void(0)" onclick="remove(<s:property value='#tfiles.ifile'/>)" class="dark-btn">删除</a>
								</td>
							</tr>
							</s:if>
						</s:iterator>
							</table>
						</td>			
						</tr>
						<tr>
						<td class="label">税务登记证</td>
						<td class="data" colspan="2">
						<table width="100%" border="0" style=" border-width:0px" cellspacing="0" cellpadding="0"class="">
								<s:iterator value="#session.tFileList" id="tfiles" status="status">
								<s:if test='#tfiles.fileType.toString()=="SWDJZ"'>
							<tr>
								<td class="data" width="500px" >
									<img style="padding:5px" src="/DrawImage.do?id=${tfiles.ifile}&type=exp" width="450" height="300" />
								</td>
								<td class="data" align="center">
								<a href="javaScript:void(0)" onclick="remove(<s:property value='#tfiles.ifile'/>)" class="dark-btn">删除</a>
								</td>
							</tr>
							</s:if>
						</s:iterator>
							</table>
						</td>			
						</tr>
						<tr>
						<td class="label">组织机构证</td>
						<td class="data" colspan="2">
						<table width="100%" border="0" style=" border-width:0px" cellspacing="0" cellpadding="0"class="">
								<s:iterator value="#session.tFileList" id="tfiles" status="status">
								<s:if test='#tfiles.fileType.toString()=="ZZJGDMZ"'>
							<tr>
								<td class="data" width="500px" >
									<img style="padding:5px" src="/DrawImage.do?id=${tfiles.ifile}&type=exp" width="450" height="300" />
								</td>
								<td class="data" align="center">
								<a href="javaScript:void(0)" onclick="remove(<s:property value='#tfiles.ifile'/>)" class="dark-btn">删除</a>
								</td>
							</tr>
							</s:if>
						</s:iterator>
							</table>
						</td>			
						</tr>
						<tr>
						<td class="label">其他</td>
						<td class="data" colspan="2">
						<s:if test="#session.tFileList.size>0">
						<table width="100%" border="0" style=" border-width:0px" cellspacing="0" cellpadding="0"class="data-table">
								<s:iterator value="#session.tFileList" id="tfiles" status="status">
								<s:if test='#tfiles.fileType.toString()=="merchFile"'>
							<tr>
								<td class="data" width="500px" >
									<img style="padding:5px" src="/DrawImage.do?id=${tfiles.ifile}&type=exp" width="450" height="300" />
								</td>
								<td class="data" align="center">
								<a href="javaScript:void(0)" onclick="remove(<s:property value='#tfiles.ifile'/>)" class="dark-btn">删除</a>
								</td>
							</tr>
							</s:if>
						</s:iterator>
							</table>
						</s:if>
						<s:else>
						&nbsp;
						</s:else>
						</td>			
						</tr>
					</table>
				</div>
			</div>
	</div>
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
		function reinitLoanIframe() {
			var iframe = document.getElementById("merchFrame");
			try {
				var height = iframe.contentWindow.document.body.scrollHeight;
				if (/webkit/.test(navigator.userAgent.toLowerCase())) {
					height = $(iframe.contentWindow.document.body).height();
				}
				iframe.height = height + 10;
			} catch (e) {
			}
			iframe = null;
			window.setTimeout(reinitLoanIframe, 300);
		}
		function remove(id){
			if(confirm("确认要删除此图片吗?")){
				window.location.href="${ctx}/merch/merchInfoUpload!deletaPic.action?ifile="+id;
			}
		}
	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>