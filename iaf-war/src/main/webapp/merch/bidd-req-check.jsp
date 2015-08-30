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
<base target="_self"/>
</head>
<body>
		<div id="workframe-wrapper" class="clearfix">
			
			<div id="content" class="report">
				
				<div class="report-tab-area">
					<s:form  id="form" action="#">
					<div class="tab-content">
					<h3 style="line-height:42px;text-align:center;font-size:15px;">发布借款时，请补充以下的企业基本资料：</h3>
					
					<table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr align="center">
		    				<td class="lable" width="200" align="right">商户名称：</td>
				    		<td class="data" align="left"><input  id="merchName" name="merchName" value="<s:property  value='merchName'/>" class="validate[required]"/></td>
				    	</tr>
				    	<tr>
				    		<td class="lable" width="200" align="right">商户行业类别：</td>
				    		<td class="data">
								<s:select list="mccMap" name="mccCode" id="mccMap" listKey="key" listValue="value" headerKey="" headerValue="请选择" style="width:100px;" class="validate[required]"></s:select>
								<select name="code" id="code" style="width:100px;"></select>
							</td>
				    	</tr>
			    	</table>
			    	
			    	<div style="margin: 20px" align="center">
			    	<input type="button" class="dark-btn" value="确  定" onclick="sub()"/>
			    	<a  class="dark-btn" href="JavaScript:close();" >关&nbsp;&nbsp;&nbsp;&nbsp;闭</a>
			    	</div>
					</div>
					</s:form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		
		function sub(){
			var merchName=$("#merchName").val();
			var code = $("#code").val();
			if(merchName==null||merchName==""){
				alert("商户名称不能为空，请重新输入！");
				return false;
			}
			
			if(code==null||code==""){
				alert("商户行业类别不能为空，请重新输入！");
				return false;
			}
			window.location.href="${ctx}/merch/merchDebitBid!saveCheck?merchName="+merchName+'&code='+code;
		}
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
		
		$("#mccMap").change(function(){
			if($("#mccMap").val()==""){
				$("#code").empty();                  
		    	$("#code").html('<option value="">请选择</option>'); 
			}else{
				$.getJSON("/../mcc",{code:$(this).val()},
				function(myJSON){
					var myOptions="";                  
					for(var i=0;i<myJSON.mccList.length;i++){    
						myOptions += '<option value="' + myJSON.mccList[i].id + '">' +myJSON.mccList[i].name + '</option>';                  
		             }   
		             $("#code").empty();                  
		             $("#code").html(myOptions);              
		       	}); 
			}         
			});
		$("#mccMap").change();
			
		function close(){
			parent.jDialogClose($("body").attr("id"));
		}
		</script> 
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>