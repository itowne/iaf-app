<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
</head>
<body>
		<div id="workframe-wrapper" class="clearfix">
			<s:form id="merchinfoform" action="backMerchCredit" namespace="/backstage/merchcreditreport" enctype="multipart/form-data">
			<div id="content" class="report">
				
				<div class="report-tab-area">
					<s:form action="merchInfoUpload">
					<div class="tab-content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
					<tr>
						<td class="label">选择类型:</td>
						<td class="data">
						<select id="filetype" name="filetype">
						<option value="YYZZ">营业执照</option>
						<option value="FRSFZ">法人身份证</option>
						<option value="SWDJZ">税务登记证</option>
						<option value="ZZJGDMZ">组织机构代码证</option>
						<option value="merchFile">其他</option>
						</select> 
						</td>
					</tr>
					<tr>
						<td class="label"><s:file id="upload" name="upload" value="选择图片文件"></s:file></td>
						<td class="data"><s:submit cssClass="dark-btn" value="上传图片" method="upload" onclick="return checkForm()"/></td>
					</tr>
					</table>
					<!--<s:hidden name="filetype" />-->
				</div>
					</s:form>
				</div>
				<div align="center">请上传<font style="color:red">JPG PNG GIF</font>格式文件!并且图片大小不能超过<font style="color:red">5M</font>！</br>为了页面有最佳显示效果，建议上传图片尺寸为<font style="color:red">650X500</font>。</div>
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
				var iframe = document.getElementById("merchFrame");
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
				if(confirm("确认提交请求?")){
				if(document.getElementById("upload").value=="") {
			    	alert("请先选择文件！");
			    	return false;
			    }
				}else{
					return false;
				}
			}
		</script> 
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>