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
		<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
		<script type="text/javascript" src="${ctx}/resources/js/workflow.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/workflow.css"/>
</head>

<body>
<s:form id="form" action="prodAudit">
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：申请受理&nbsp;&gt;&gt;&nbsp;订单审核&nbsp;&gt;&gt;&nbsp;订单明细<span style="margin-left:580px"><span class="related-menu"><a href="javascript:runQQ('${merch.qqUid}')">在线联系</a></span>&nbsp;<input type="button" onclick="back()" value = "返回" class="dark-btn"/></span></p>
  </div>
  <div class="how-to-ask">
    	<strong>贷款处理流程：</strong>&nbsp;<div id="myflow"></div><!-- <img src="${ctx}/resources/images/mp/bg-vetting-step.gif" width="737" height="24" alt=""/> -->
  </div>
  <div><span style="font-size:14px;font-weight:bold;margin-left:20px;border-bottom:0;">贷款状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span></div>
   <div style="padding: 5px;width:98%;">
   <div align="center" style="font-size: 15px;font-weight: bold;">本笔贷款概况</div>
   <table width="97%"  border="0" cellspacing="0" cellpadding="0" class="data-table">
   	<tr align="center">
   		<td class="label" style="background: #F3F9FD;width:80px;height:30px">借贷编号</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款申请金额(万元)</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款审批金额(万元)</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px"">贷款周期</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px"">最低利率(%)</td>
   		<td class="label" style="background: #F3F9FD;width:80px;height:30px"">需还款总期数</td>
   		<td class="label" style="background: #F3F9FD;width:80px;height:20px"">还款方式</td>
   		<td class="label" style="background: #F3F9FD;width:80px;height:20px"">申请产品</td>
   		<td class="label" style="background: #F3F9FD;width:80px;height:20px"">放贷机构</td>
   		<td class="label" style="background: #F3F9FD;width:90px;height:20px"">申请日期</td>
   	</tr>
   	<tr align="center">
   		<td class="data"><s:label name="loanOrd.loanOrdId"/></td>
   		<td class="data"><s:label name="loanOrd.WangyuanPreQuota"/></td>
   		<s:if test='loanOrd.ordStat.toString()=="APPLY" || loanOrd.ordStat.toString()=="ACCEPT" || loanOrd.ordStat.toString()=="AUDIT_REFUSE" || loanOrd.ordStat.toString()=="ACCEPT_OVERDUE" || loanOrd.ordStat.toString()=="ACCEPT_REFUSE" || loanOrd.ordStat.toString()=="APPLY_OVERDUE" || loanOrd.ordStat.toString()=="CANCEL"'>
   			<td class="data">0</td>
   		</s:if>
   		<s:else>
	   		<td class="data"><s:label name="loanOrd.WangyuanQuota"/></td>
   		</s:else>
   		<td class="data"><s:label name="loanOrd.term"/><s:label name="loanOrd.termType.desc"/></td>
   		<td class="data">
   		<s:if test='loanOrd.pdtType.toString()=="MERCH_PROD"'>
				<s:label name="loanOrd.rateType.desc"/><s:label name="loanOrd.rate"/>起
				</s:if>
				<s:else>
				<s:label name="loanOrd.rateType.desc"/><s:label name="loanOrd.rate"/>起
				</s:else>
  		</td>
   		<td class="data"><s:label name="periods"/>期</td>
   		<td class="data">
		<s:if test='loanOrd.pdtType.toString()=="MERCH_PROD"'>
				<s:label name="debitBid.refundType.desc"/>
				</s:if>
				<s:else>
				<s:label name="lp.repayment.desc"/>
				</s:else>
   		</td>
   		<td class="data"><s:label name="loanOrd.pdtName"></s:label></td>
   		<td class="data"><s:label name="loanOrd.instName"></s:label></td>
   		<td class="data"><fmt:formatDate value="${loanOrd.ordDate}" pattern="yyyy-MM-dd"/></td>
   	</tr>
   </table>
   </div>
   <div align="center" style="margin-top:30px;font-size: 15px;font-weight: bold;">本笔贷款详情</div>
  <div class="report-tab-area">
  <div id="content" class="report">
   <div align="right">
   	 <s:if test='#request.loanOrd.ordStat.toString()=="APPLY"||#request.loanOrd.ordStat.toString()=="ACCEPT"'>
			    <n:instAuthButton cssClass="dark-btn" value="同意" onclick="return beforeSubmit1('是否确定审核通过此订单?','审核通过 - 填写备注信息', 'prodAudit!audit');" authCode="SPCL_SH" />
	    		<n:instAuthButton cssClass="dark-btn" value="不同意" onclick="return beforeSubmit2('是否确定审核不通过此订单?','审核不通过 - 填写备注信息', 'prodAudit!refuse');" authCode="SPCL_SHBTG" />
			   	<span id="cancel" style="display:none">
	    			<n:instAuthButton cssClass="dark-btn" value="作废订单" onclick="return beforeSubmit1('作废该订单 - 填写备注信息', 'prodAudit!cancel');" authCode="SPCL_ZFDD" disabledFlag="${disabledFlag}"/>
	    		</span>
			    </s:if>
			    <s:else>
			    <input type="button" style="background: #7B7B7B;color:#FFFFFF;" disabled="disabled" class="dark-btn" value="同意" onclick="return beforeSubmit1('是否确定审核通过此订单?','审核通过 - 填写备注信息', 'prodAudit!audit');" authCode="SPCL_SH" disabledFlag="${disabledFlag}"/>
	    		<input type="button" style="background: #7B7B7B;color:#FFFFFF;" disabled="disabled" class="dark-btn" value="不同意" onclick="return beforeSubmit1('是否确定审核不通过此订单?','审核不通过 - 填写备注信息', 'prodAudit!refuse');" authCode="SPCL_SHBTG" disabledFlag="${disabledFlag}"/>
	    		<span id="cancel" style="display:none">
	    			<input type="button" style="background: #7B7B7B;color:#FFFFFF;" disabled="disabled"  disabled="disabled" class="dark-btn" value="作废订单" onclick="return beforeSubmit1('作废该订单 - 填写备注信息', 'prodAudit!cancel');" authCode="SPCL_ZFDD" disabledFlag="${disabledFlag}"/>
	    		</span>
			    </s:else>
   </div>
 <!--  <h3 class="title"><span style="border-bottom:0;"><em style="color:red;">&nbsp;</em></span>&nbsp;&nbsp;<span class="related-menu"><a href="javascript:runQQ('${merch.qqUid}')">在线联系</a></span>&nbsp;&nbsp;<span class="related-menu"><a href="javaScript:addMerch(${loanOrd.imerch})">加入关注商户</a></span></h3> -->
	  	<ul class="tab-title clearfix">
			<li onclick="window.curTab='loanOrdApply';javascript:displayCancel('inline')" class="cur" id="loanOrdApply"><a href="/inst/loanord/loanDetail" target="loanFrame">贷款申请</a></li>
			<li onclick="window.curTab='operLogDetail';javascript:displayCancel('none')"  id="operLogDetail"><a href="/inst/loanord/operLogDetail" target="loanFrame" >贷款进度跟踪</a></li>
			<li class="disabled" id="planMgr">还款计划</li>
			<li onclick="window.curTab='loanOrdList';javascript:displayCancel('none')" id="loanOrdList"><a href="/inst/loanord/loanOrdList" target="loanFrame">商户借贷信用</a></li>
			<li onclick="window.curTab='creditReport';javascript:displayCancel('none')" id="creditReport"><a id="creditReport" target="loanFrame" href="/inst/loanord/merchCreditReport">经营数据报告</a></li>
		</ul>
	  	<div class="tab-content">
	  	<iframe id="loanFrame" name="loanFrame" marginheight="0" marginwidth="0" src="/inst/loanord/loanDetail" width="100%" align="middle" scrolling="no" frameborder="0"></iframe>
		<div id="memoDiv" style="display:none;width:400;height: 200;margin:15px auto;text-align:center">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			<tr>
				<td class="label" style="background:#D7EBFA;font-weight: bold;font-size:15px" colspan="4">本次操作后，请尽快上传还款计划和进行放款处理操作</td>
			</tr>
			<tr>
				<td class="label" style="background:#D7EBFA">&nbsp;</td>
				<td class="label" style="background:#D7EBFA">借款金额</td>
				<td class="label" style="background:#D7EBFA">借款期限</td>
				<td class="label" style="background:#D7EBFA">借款利率</td>
			</tr>
			<tr>
				<td class="label" style="background:#D7EBFA">原申请信息</td>
				<td class="data"><s:property value="%{#request.loanOrd.WangyuanQuota}"/>万</td>
				<td class="data"><s:property value="%{#request.loanOrd.term}"/><s:label name="#request.loanOrd.termType.desc"/></td>
				<td class="data">
				<s:if test='loanOrd.pdtType.toString()=="MERCH_PROD"'>
				<s:label name="debitBid.rateType.desc"/>
				</s:if>
				<s:else>
				<s:label name="lp.rateType.desc"/>
				</s:else>
				<s:property value="%{#request.loanOrd.rate}"/>%</td>
			</tr>
			<tr>
				<td class="label" style="background:#D7EBFA"><font style="color:red">*</font>审批款项结果</td>
				<td class="data"><input type="text" maxlength="9" name="quota" id="quota" value="<s:property value="%{#request.loanOrd.WangyuanQuota}"/>" onblur="quotacheck(this);"/>万</td>
				<td class="data"><input type="text" style="width:30px" maxlength="5" name="loanOrdTerm" id="loanOrdTerm" value="<s:property value="%{#request.loanOrd.term}"/>"onblur="termcheck(this)"/>
				<select id="termType" name="termType">
				<s:if test='#request.loanOrd.termType.toString()=="DAY"'>
				<option value="DAY" selected="selected">天</option>
				</s:if>
				<s:else>
				<option value="DAY" >天</option>
				</s:else>
				<s:if test='#request.loanOrd.termType.toString()=="MONTH"'>
				<option value="MONTH" selected="selected">个月</option>
				</s:if>
				<s:else>
				<option value="MONTH">个月</option>
				</s:else>
				<s:if test='#request.loanOrd.termType.toString()=="YEAR"'>
				<option value="YEAR" selected="selected">年</option>
				</s:if>
				<s:else>
				<option value="YEAR">年</option>
				</s:else>
				</td>
				</select>
				<td class="data">
				<select id="rateType" name="rateType">
					<s:if test='#request.loanOrd.rateType.toString()=="DAY"'>
						<option value="DAY" selected="selected">日利率</option>
					</s:if>
					<s:else>
						<option value="DAY">日利率</option>
					</s:else>
					<s:if test='#request.loanOrd.rateType.toString()=="MONTH"'>
						<option value="MONTH" selected="selected">月利率</option>
					</s:if>
					<s:else>
						<option value="MONTH">月利率</option>
					</s:else>
					<s:if test='#request.loanOrd.rateType.toString()=="YEAR"'>
						<option value="YEAR" selected="selected">年利率</option>
					</s:if>
					<s:else>
						<option value="YEAR">年利率</option>
					</s:else>
				</select>
				<input type="text" style="width:30px" maxlength="5" name="rate" id="rate" value="<s:property value="%{#request.loanOrd.rate}"/>"/>%</td>
			</tr>
			<tr>
			<td class="label" style="background:#D7EBFA">备注</td>
			<td colspan="3"><s:textarea  id="memo" name="memo" value="" cols="56" rows="10" style="width:95%;height:100%"></s:textarea></td>
			</tr>
		</table>
	    </div>
	    
	    <div id="refuse" style="display:none;width:400;height: 200;margin:15px auto;text-align:center">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			<tr>
				<td class="label" style="background:#D7EBFA" colspan="2">请详细填写审核不通过的原因,以便借款人知悉原因.</td>
			</tr>
			<tr>
			<!-- <td class="label" style="background:#D7EBFA">备注</td> -->
			<td class="data" colspan="2"><s:textarea  id="unmemo" name="unmemo" value="" cols="50" rows="10" style="width:99%;height:100%"></s:textarea></td>
			</tr>
		</table>
	    </div>
		<div style="margin:15px auto;text-align:center;">
				<input type="hidden" id="unmemo" name="unmemo" value=""/>
	    		<input type="hidden" id="memo" name="memo" value=""></input>
	    		<input type="hidden" id="quota" name="quota" value=""></input>
	    		<input type="hidden" id="loanOrdTerm" name="loanOrdTerm" value=""></input>
	    		<input type="hidden" id="rate" name="rate" value=""></input>
	    		<input type="hidden" id="rateType" name="rateType" value=""/>
	    		<input type="hidden" id="termType" name="termType" value=""/>
	    		<!-- 
	    		<s:if test="#request.loanOrd.ordStat.name() != 'ACCEPT'">
			      <s:set var="disabledFlag" >true</s:set>
			    </s:if>
			     -->
	    </div>

	</div>
</div>
</div>
</div>
</s:form>
<script type="text/javascript">
$("#myflow").flow(["受理","审核","放款","制定还款计划","还款管理","结束"],2);
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
		window.curTab = "loanOrdApply";
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
	function quotacheck(obj){
		if(obj.value==""){
			alert("借款金额不能为空!");
			return obj.focus();
		}
		if(isNaN(obj.value)){
			alert("借款金额请填写数字!");
			return false;
		}else{
			if(obj.value==0){
				alert("请输入大于0的数!");
				return false;
			}
			if(obj.value.length>1){
				if(!/^\d+(\d|(\.[0-9]{1}))$/.test(obj.value)){
					alert("借款金额只能保存一位小数,请重新输入！");
					return false;
				}
			}
			//obj.value=parseFloat(obj.value).toFixed(1);
		}
	}
	function termcheck(obj){
		if(obj.value==""){
			alert("借款期限不能为空!");
			return false;
		}
		if(isNaN(obj.value)){
			alert("借款期限请填写整数!");
			return false;
		}else{
			var reg = /^\+?[1-9][0-9]*$/;
			if(!reg.test(obj.value)){
				alert("借款期限请填写整数!");
				return false;
			}
		}
	}
	
	function beforeSubmit1(tip,title, action){
		dialog(title, "memoDiv", 650, 450, function(dialogObj){
			if(confirm(tip)){
			var dialogMemo = $(dialogObj).find("#memo").val();
			var dialogquota = $(dialogObj).find("#quota").val();
			var dialogterm = $(dialogObj).find("#loanOrdTerm").val();
			var dialograte = $(dialogObj).find("#rate").val();
			var termType=$(dialogObj).find("#termType").val();
			var rateType=$(dialogObj).find("#rateType").val();
			$("#memo").val(dialogMemo);
			$("#quota").val(dialogquota);
			$("#loanOrdTerm").val(dialogterm);
			$("#rate").val(dialograte);
			$("#termType").val(termType);
			$("#rateType").val(rateType);
			
			if(dialogquota==""){
				alert("借款金额不能为空!");
				return false;
			}
			if(dialogterm==""){
				alert("借款期限不能为空!");
				return false;
			}
			if(dialograte==""){
				alert("借款利率不能为空!");
				return false;
			}
			if(isNaN(dialogquota)){
				alert("借款金额请填写数字!");
				return false;
			}else{
				if(dialogquota==0){
					alert("请输入大于0的数!");
					return false;
				}
				if(dialogquota.length>1){
					if(!/^\d+(\d|(\.[0-9]{1}))$/.test(dialogquota)){
						alert("借款金额只能保存一位小数,请重新输入！");
						return false;
					}
				}
				//$(dialogObj).find("#quota").val(parseFloat(dialogquota).toFixed(1));
			}
			if(isNaN(dialogterm)){
				alert("借款期限请填写数字!");
				return false;
			}else{
				var reg = /^\+?[1-9][0-9]*$/;
				if(!reg.test(dialogterm)){
					alert("借款期限请填写整数!");
					return false;
					}
			}
			if(isNaN(dialograte)){
				alert("借款利率请填写数字!");
				return false;
			}
			if(dialogMemo==""){
				alert("请填写备注!");
				return false;
			}
			$("#form").attr("action", action).submit();
			}else{
				return false;
			}
		});
		return false;
	}
	function displayCancel(flag){
		$("#cancel").css('display',flag);	
	}
	
	function beforeSubmit2(tip,title, action){
		dialog(title, "refuse", 650, 450, function(dialogObj){
			if(confirm(tip)){
			var dialogMemo = $(dialogObj).find("#unmemo").val();
			$("#unmemo").val(dialogMemo);
			
			if(dialogMemo==""){
				alert("请填写备注!");
				return false;
			}
			$("#form").attr("action", action).submit();
			}else{
				return false;
			}
		});
		return false;
	}
	function addMerch(id){
		if(confirm("是否确认关注此商户?")){
			window.location.href="${ctx}/inst/instCollMerch!add?imerch="+id	;
		}
	}
	function back(){
		window.location.href="${ctx}/inst/loanord/prodAudit!returnAction";
	}
</script> 
</body>
