<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<title>汇融易 - 机色管理后台</title>
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
  
  <div class="report-tab-area">
  	
  	<div id="content" class="report" >
	    
	    <div class="tab-content">
	   		<div align="center" style="height: 50px;font-size: 16px;font-weight: bold;color:red">交易成功!<s:property value="transMsg.transStat.desc" /></div>
	   		<div style="margin-bottom: 10px;text-align:center;font-size: 15px">
	   			<p style="text-align: left;margin-left: 35px">尊敬的商户:</p>
	   			<br>
	   			<p>
	   			您的账资金正常会在T+1（T代表交易当天）天到账，节假日顺延。到账时间具体以银行处理时间为准。如果对还款到账日期有任何疑问，请致电 400-628-6928  联系我们的客服人员咨询。
	   			</p>
	   		</div>
	   		<div align="center" style="height: 50px;"><s:submit cssClass="dark-btn" onclick="close1();" value="确定"></s:submit></div>
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
				window.close();
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
