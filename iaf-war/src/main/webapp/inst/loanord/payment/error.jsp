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
<script language="JavaScript">
   javascript:window.history.forward(1);
</script>
</head>

<body>
<div id="workframe-wrapper" class="clearfix">
  
  <div class="report-tab-area">
  	
  	<div id="content" class="report" >
	    
	    <div class="tab-content">
	   		<div align="center" style="height: 50px;font-size: 16px;font-weight: bold;color: red">交易失败!</div>
	   		<div style="text-align: center;margin-top: 10px;font-size: 15px">
	   		<p>
	   		十分抱歉！您本次的交易操作失败。如需重新交易，请点击【重新交易】，否则请点击【以后再交易】结束本次交易操作。<br><br>
       		如果对交易失败有任何疑问，请致电 400-628-6928  联系我们的客服人员咨询。</p>
	   		</div>
	   		<div style="margin-top: 10px;color: red;font-size: 15px;text-align: center;margin-bottom: 10px">
	   			  错误：[<s:property value="msg" />]
	   		</div>
	   		<br>
	   		<input type="hidden" name="loanId" id="loanId" value="<s:property value="ff.loanOrdId"/>"/>
	   		<div align="center" style="height: 50px;">
	   		<s:if test='trans.type.toString()=="CREDIT"||trans.type.toString()=="OTH_CREDIT"'>
	   		<s:submit cssClass="dark-btn" onclick="return credi();" value="重新交易"></s:submit>
	   		</s:if>
	   		<s:else>
	   		<s:submit cssClass="dark-btn" onclick="return refund();" value="重新交易"></s:submit>
	   		</s:else>
	   		<s:submit cssClass="dark-btn" onclick="return close1();" value="以后再交易"></s:submit>
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
				window.close();
			}
			function credi(){
				var loanId = $("#loanId").val();
				window.location.href="/inst/loanord/prodCredit!rePay?loanId="+loanId;
			}
			
			function refund(){
				var loanId = $("#loanId").val();
				window.location.href="/merch/merchMyRefund!rePay?loanId="+loanId;
				//window.location.href="/merch/merchMyRefund!rePay?loanId=201311072504";
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
