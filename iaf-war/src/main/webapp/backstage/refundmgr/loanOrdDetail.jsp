<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<title>汇融易 - 汇卡管理后台</title>
<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/mp-workframe.css"/>
<style type="text/css">
#tab{
		position:absolute;width:40px;overflow:hidden;padding:20px 0 0;left:-3px;top:36px;background:url(/resources/images/mp/bg-inst-tab.gif) repeat-y;
	}
	#tab li{
		width:31px;height:97px;overflow:hidden;padding:20px 0 0 3px;letter-spacing:10px;text-align:center;cursor:pointer;font-weight:bold;border-bottom:1px solid #dadada;
	}
	#tab li.cur{
		height:106px;padding:30px 0 0 6px;background:url(/resources/images/mp/bg-inst-tab-cur.gif) no-repeat;color:#fff;border:0;
	}
</style>
<body style="position:relative;">
<s:form action="refundMgr">
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：还款管理&nbsp;&gt;&gt;&nbsp;订单明细</p>
  </div>
  <div id="content" class="report" style="padding:10px 10px 10px 50px;">
    <h3 class="title"><span>借贷状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span></h3>
    <h3 style="line-height:32px;text-align:center;font-size:15px;">产 品 借 款 申 请 情 况</h3>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
    	<tr>
    		<td class="label">借款申请金额</td>
    		<td class="data"><s:label name="loanOrd.preQuota"></s:label>元</td>
    		<td class="label">借款编号</td>
    		<td class="data"><s:label name="loanOrd.loanOrdId"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">借款审批金额</td>
    		<td class="data"><s:label name="loanOrd.quota"></s:label>元</td>
    		<td class="label">利率</td>
    		<td class="data"><s:label name="loanOrd.rateType.desc"/><s:label name="loanOrd.rate"/>%</td>
    	</tr>
    	<tr>
    		<td class="label">借贷产品</td>
    		<td class="data"><s:label name="loanOrd.pdtName"></s:label></td>
    		<td class="label">产品所属机构</td>
    		<td class="data"><s:label name="loanOrd.instName"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">借款期限</td>
    		<td class="data"><s:label name="loanOrd.term"></s:label><s:label name="loanOrd.termType.desc"/></td>
    		<td class="label">申请日期</td>
    		<td class="data"><s:date name="loanOrd.ordDate" format="yyyy-MM-dd HH:mm"/></td>
    	</tr>
    	<tr>
    		<td class="label">总还款金额</td>
    		<td class="data"><s:label name="loanOrd.repayment + loanOrd.remainPayment"></s:label>元</td>
    		<td class="label">已还款金额</td>
    		<td class="data"><s:label name="loanOrd.repayment"></s:label>元</td>
    	</tr>
    	<tr>
    		<td class="label">总还款期数</td>
    		<td class="data"><s:label name="loanOrd.term"/>期</td>
    		<td class="label">已还款期数</td>
    		<td class="data"><s:label name="loanOrd.recivePeriod"/>期</td>
    	</tr>
    	<tr>
    		<td class="label">借款用途</td>
    		<td class="data" colspan="3"><s:label name="loanOrd.purpose"></s:label></td>
    	</tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
    <tr>
    <td valign="top">
    <h3 style="line-height:32px;text-align:center;font-size:15px;">详细还款记录</h3>
     <table width="90%" border="0"  style="text-align: center" cellspacing="0" cellpadding="0" class="data-table">
    	<t>
    		<th class="label" width="15%" style="text-align: center">期号</th>
    		<th class="label" width="15%" style="text-align: center">还款金额</th>
    		<th class="label" width="15%" style="text-align: center">还款状态</th>
    		<th class="label" width="20%" style="text-align: center">还款时间</th>
    		<th class="label" width="15%" style="text-align: center">还款方式</th>
    	</tr>
    	<s:if test="%{#request.loanFundFlowList.size > 0}">
    	<s:iterator value="#request.loanFundFlowList" id="ff">
    	<tr>
    		<td class="data" style="border-width: 0.3px;border-style: solid;border-color: #CCCCCC;">第<s:property value="#ff.period" />期</td>
    		<td class="data" style="border-width: 0.3px;border-style: solid;border-color: #CCCCCC;"><s:property value="#ff.amount" />元</td>
    		<td class="data" style="border-width: 0.3px;border-style: solid;border-color: #CCCCCC;"><s:property value="#ff.status.desc" /></td>
    		<td class="data" style="border-width: 0.3px;border-style: solid;border-color: #CCCCCC;"><s:date name="#ff.genTime"  format="yyyy-MM-dd HH:mm"/></td>
    		<td class="data" style="border-width: 0.3px;border-style: solid;border-color: #CCCCCC;"><s:property value="#ff.transType.desc" /></td>
    	</tr>
    	</s:iterator>
    	</s:if>
    	<!--  
    	<s:else>
    	<tr>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    	</tr>
    	</s:else>
    	-->
    </table>
    </td>
    <td valign="top">
    <h3 style="line-height:32px;text-align:center;font-size:15px;">订单跟踪</h3>
<table width="90%" border="0" style="text-align: center" cellspacing="0" cellpadding="0" class="data-table">
    	<tr>
    		<th class="label" width="40%" style="text-align: center">日期</th>
    		<th class="label" width="60%" style="text-align: center">操作</th>
    	</tr>
    	<s:if test="%{#request.operLogs.size > 0}">
    	<s:iterator value="#request.operLogs" id="ol" status="st" >
    	<tr>
    		<td class="data" style="border-width: 0.3px;border-style: solid;border-color: #CCCCCC;"><s:property value="%{getText('format.time', {#ol.genTime})}" /></td>
    		<td class="data" style="border-width: 0.3px;border-style: solid;border-color: #CCCCCC;"><s:property value="#ol.operType.desc" /></td>
    	</tr>
    	</s:iterator>
		</s:if>
    </table>
    </td>
    </tr>
    </table>
    <div style="margin:15px auto;text-align:center;">
   		<s:submit mehtod="returnAction" value = "返回" cssClass="dark-btn"/>
    </div>
  </div>
</div>
</s:form>
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
	
</script> 
</body>
