<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<title>汇融易 - 机构管理后台</title>
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
	    <h3 style="line-height:42px;text-align:center;font-size:15px;">本期还款详情</h3>
	    <table width="400" border="0" cellspacing="0" cellpadding="0" class="data-table">
			<tr>
			<td class="label">借款编号：</td><td class="data"><s:property value="#request.loanOrd.loanOrdId" /></td>
			</tr>
			<tr>
			<td class="label">贷款金额(万元)：</td><td class="data"><s:property value="#request.loanOrd.wangyuanQuota" /></td>
			</tr>
			<tr>
			<td class="label">放贷机构：</td><td class="data"><s:property value="#request.instx.instName" /></td>
			</tr>
			<tr>
			<td class="label">借款方：</td><td class="data"><s:property value="#request.merch.merchName" /></td>
			</tr>
			<tr>
			<td class="label">还款方式：</td><td class="data"><s:property value="#request.refundFundFlow.type.desc" /></td>
			</tr>
			<tr>
			<td class="label">还款金额(元)：</td><td class="data"><s:property value="lopn.repayment" /></td>
			</tr>
			<tr>
			<td class="label">还款日期：</td><td class="data"><fmt:formatDate value="${lopn.updTime}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
			<td class="label">还款备注：</td>
			<td class="data"><s:property value="#request.lopn.memo"/>
			</td>
			</tr>
	    </table>
		<input type="hidden" name="loanOrdPlanId" id="loanOrdPlanId" value="<s:property value="loanOrdPlanId"/>">
	    <div style="margin:15px auto;text-align:center;">
	    	<div id="trans">
	    	<input type="button" onclick="close1();" class="dark-btn" value="返回" />
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
				history.go(-1);
			}
			function trans(url){
				if(confirm("确认提交请求?")){
					window.open("${ctx}" + url);
					$("#trans").hide();
					$("#btn").show();
					return false;
				}
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
