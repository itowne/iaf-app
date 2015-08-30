<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/nl-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<script type="text/javascript" src="${ctx}/resources/js/workflow.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/workflow.css"/>
</head>

<body>
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：贷后管理&nbsp;&gt;&gt;&nbsp;还款管理</p>
  </div>
  <div class="how-to-ask">
    	<strong>解冻处理流程：</strong>&nbsp;<div id="myflow"></div><!-- <img src="${ctx}/resources/images/mp/bg-vetting-step.gif" width="737" height="24" alt=""/> -->
  </div>
  <form action="/inst/loanord/refundMgr!thraw" onsubmit="return check();">
  <div align="center" style="font-size:16px;font-weight: bold">解冻申请信息</div>
  <div id="content" class="report" align="center" style="margin-top: 20px">
    <table width="60%" border="0" cellspacing="0" cellpadding="0" class="data-table">
    <tr>
    	<td class="label" style="width:150px">借贷编号：</td>
    	<td class="data" colspan="3"><s:label name="loanOrd.loanOrdId"/></td>
    </tr>
    <tr>
    	<td class="label">贷款金额（万元）：</td>
    	<td class="data" colspan="3"><s:label name="loanOrd.wangyuanQuota"/></td>
    </tr>
    <tr>
    	<td class="label">贷款周期：</td>
    	<td class="data" colspan="3"><s:label name="loanOrd.pdtTerm"/></td>
    </tr>
    <tr>
    	<td class="label">贷款利率（%）：</td>
    	<td class="data" colspan="3"><s:label name="loanOrd.pdtRate"/></td>
    </tr>
    <tr>
    	<td class="label">冻结商户：</td>
    	<td class="data" colspan="3"><s:label name="merch.merchName"/></td>
    </tr>
    <tr>
    	<td class="label">冻结商户汇卡号：</td>
    	<td class="data" colspan="3"><s:label name="merch.merchNo"/></td>
    </tr>
    <tr>
    	<td class="label">解冻申请机构：</td>
    	<td class="data" colspan="3"><s:label name="instx.instName"/></td>
    </tr>
    <tr>
    	<td class="label" colspan="4" style="text-align:center">欠款详情</td>
    </tr>
    <tr>
    	<td class="label">&nbsp;</td>
    	<td class="label" style="text-align:center">冻结期数</td>
    	<td class="label" style="text-align:center">计划还款日期</td>
    	<td class="label" style="text-align:center">还款金额（元）</td>
    </tr>
    <s:iterator value="#request.loanOrdPlanList" id="lop" status="st">
    	<tr>
    		<td class="label" style="width:50px;text-align:center"><s:property value="#st.count"/></td>
    		<td class="data" style="text-align:center">第<s:property value="#request.lop.period"/>期</td>
    		<td class="data" style="text-align:center"><fmt:formatDate value="${lop.refundDate}" pattern="yyyy-MM-dd"/></td>
    		<td class="data" style="text-align:center"><s:property value="#request.lop.repayment"/></td>
    	</tr>
    </s:iterator>
    <tr>
    	<td class="label" style="text-align:center">合计</td>
    	<td class="data" style="text-align:center"><s:property value="#request.loanOrdPlanList.size"/>期</td>
    	<td class="data" style="text-align:center">--</td>
    	<td class="data" style="text-align:center"><s:property value="capital"/></td>
    </tr>
     <tr>
    	<td class="label">应还金额（元）：</td>
    	<td class="data" colspan="3" style="text-align:left"><s:property value="capital"/></td>
    </tr>
     <tr>
    	<td class="label">滞纳金（元）：</td>
    	<td class="data" colspan="3" style="text-align:left">${loanOrd.overdue}</td>
    </tr>
     <tr>
    	<td class="label">冻结总金额(元)：</td>
    	<td class="data" colspan="3" style="text-align:left"><s:property value="planTotal"/></td>
    </tr>
    <tr>
    	<td class="label">申请冻结时间：</td>
    	<td class="data" colspan="3" style="text-align:left"><fmt:formatDate value="${freezeTime}" pattern="yyyy-MM-dd"/></td>
    </tr>
    <tr>
    	<td class="label">解冻原因说明：</td>
    	<td class="data" colspan="3" style="text-align:left"><textarea rows="3" cols="55" name="unFreezeMemo" id="unFreezeMemo"></textarea></td>
    </tr>
    </table>
    <input type="hidden" name="selectedRows" id="selectedRows" value="<s:property value="selectedRows"/>">
      <div align="center" style="margin-top: 10px"><input class="dark-btn" type="submit" value="确定">&nbsp;&nbsp;&nbsp;<input type="button" class="dark-btn" value="取消" onclick="cancel()"/></div>
  </div>
  </form>
<script type="text/javascript">
$("#myflow").flow(["填写解冻申请","下载打印解冻申请凭证，并加盖公司印章","上传申请凭证的扫描图片"],1);
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
		window.curTab = "planMgr";
		$("#loanFrame").bind("load",function(){
			$(".tab-title li").removeClass("cur");
			$("#"+window.curTab).addClass("cur");
		});
	});
	function reinitLoanIframe(){
		var iframe = document.getElementById("loanFrame");
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
	function beforeSubmit1(title,action){
		dialog(title, "memoDiv", 500, 300, function(dialogObj){
			var dialogMemo = $(dialogObj).find("#memo").val();
			$("#memo").val(dialogMemo);
			$("#form").attr("action", action).submit();
		});
		return false;
	}
	function displayCancel(flag1,flag2){
		$("#cancel").css('display',flag1);
		$("#returnAction").css('display',flag2);
	}
	
	function addMerch(id){
		if(confirm("是否确认关注此商户?")){
			window.location.href="${ctx}/inst/instCollMerch!add?imerch="+id	;
		}
	}
	function back(){
		window.location.href="${ctx}/inst/loanord/refundMgr!returnAction";
	}
	
	function cancel(){
		window.history.go(-1);
	}
	function check(){
		if(confirm("确认要解冻吗?")){
			//if($("#memo").text().size()>300){
				//alert("备注请填写小于500字");
				//return false;
			//}
			return true;
		}
		return false;
	}
</script> 
</body>
