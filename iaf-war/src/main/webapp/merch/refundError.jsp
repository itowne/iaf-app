<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<title>汇融易 - 商户管理后台</title>
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
  <div class="report-tab-area">
  	<div id="content" class="report" >
  	<h3 style="line-height:42px;text-align:center;font-size:15px;"><font style="color:red">订单已还款,不能重复交易!</font></h3>
	    <div style="margin:15px auto;text-align:center;">
	    	<input type="button" onclick="close1();" class="dark-btn" value="关闭窗口" />
	    </div>
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
				//$("#tab").height($("#workframe-wrapper").height());
				var h = $("#workframe-wrapper").height();
				if(h < 648){
					$("#workframe-wrapper").height(700)
				}
			});
			function close1(){
				parent.jDialogClose($("body").attr("id"));
			}
			
			function trans(url){
				window.open("${ctx}" + url);
				$("#trans").hide();
				$("#btn").show();
				return false;
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
