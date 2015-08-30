<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
</head>

<body>
<s:form action="prodAccept">
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：借贷受理&nbsp;&gt;&gt;&nbsp;订单受理&nbsp;&gt;&gt;&nbsp;订单明细</p>
  </div>
  <div class="how-to-ask">
    	<strong>借贷处理流程：</strong>&nbsp;<img src="${ctx}/resources/images/mp/bg-vetting-step.gif" width="737" height="24" alt=""/>
  </div>
  <div class="report-tab-area">
	  
	  	<ul class="tab-title clearfix">
	  		<li>借款申请</li>
	  		<li class="cur" for="loanord_accept">申请受理</li>
	  		<li>还款计划</li>
	  		<li>借贷信用</li>
	  		<li><a href="credit-report.html">经营数据报告</a></li>
	  	</ul>
	  	<div id="content" class="report">
	    <h3 class="title"><span>借贷状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span>&nbsp;&nbsp;<span class="related-menu"><a href="javascript:runQQ('${merch.qqUid}')">在线联系</a></span>&nbsp;&nbsp;<span class="related-menu"><a href="javaScript:addMerch(${loanOrd.imerch})">加入我的关注</a></span></h3>
	    <div class="tab-content">
		    <h3 style="line-height:32px;text-align:center;font-size:15px;">产 品 借 款 申 请 情 况</h3>
		    <table width="800" border="0" cellspacing="0" cellpadding="0" class="data-table">
		    	<tr>
		    		<td class="label" width="150">借款用途</td>
		    		<td class="data" width="250"><s:label name="loanOrd.purpose"></s:label></td>
		    		<td class="label" width="150">借款编号</td>
		    		<td class="data" width="250"><s:label name="loanOrd.loanOrdId"></s:label></td>
		    	</tr>
		    	<tr>
		    		<td class="label">借款金额</td>
		    		<td class="data"><s:label name="loanOrd.quota"></s:label>元</td>
		    		<td class="label">最高可接受利率</td>
		    		<td class="data"><s:label name="loanOrd.rate"></s:label></td>
		    	</tr>
		    	<tr>
		    		<td class="label">借贷产品</td>
		    		<td class="data"><s:label name="loanOrd.pdtName"></s:label></td>
		    		<td class="label">产品所属机构</td>
		    		<td class="data"><s:label name="loanOrd.instName"></s:label></td>
		    	</tr>
		    	<tr>
		    		<td class="label">借款期限</td>
		    		<td class="data"><s:label name="loanOrd.term"></s:label>个月</td>
		    		<td class="label">申请日期</td>
		    		<td class="data"><s:date name="loanOrd.ordDate" format="yyyy-MM-dd HH:mm:ss"/></td>
		    	</tr>
		    </table>
		    <h3 style="line-height:32px;text-align:center;font-size:15px;">商 户 基 本 情 况</h3>
		    <table width="800" border="0" cellspacing="0" cellpadding="0" class="data-table">
		    	<tr>
    		<td class="label" width="150">商户名称</td>
    		<td class="data" width="250"><s:property  value='merch.merchName'/></td>
    		<td class="label" width="150">商户类型</td>
    		<td class="data" width="250"><s:property  value='merch.MerchType.desc'/></td>
    	</tr>
    	<tr>
    		<td class="label">商户所属省市</td>
    		<td class="data"><s:property  value='merch.province'/></td>
    		<td class="label">商户所属行业</td>
    		<td class="data"><s:property  value='merch.industry'/></td>
    	</tr>
    	<tr>
    		<td class="label">开业时间</td>
    		<td class="data"><fmt:formatDate value="${merch.openTime}" pattern="yyyy-MM-dd"/></td>
    		<td class="label">经营状况</td>
    		<td class="data"><s:property  value='merch.companySize'/></td>
    	</tr>
    	<tr>
				<td class="label">注册编号</td>
				<td class="data"><s:label  name="merch.regNo"/></td>
				<td class="label">注册地址</td>
				<td class="data"><s:label name="merch.regAddr"/></td>
			</tr>
			<tr>
				<td class="label">注册时间</td>
				<td class="data"><fmt:formatDate value="${merch.regTime}" pattern="yyyy-MM-dd"/></td>
				<td class="label">机具地址</td>
				<td class="data"><s:label name="merch.posAddr"/></td>
			</tr>
			<tr>
				<td class="label">营业执照号</td>
				<td class="data"><s:label name="merch.businlic"/></td>
				<td class="label">税务登记号</td>
				<td class="data"><s:label name="merch.taxReg"/></td>
			</tr>
			<tr>
				<td class="label">注册资本</td>
				<td class="data"><s:label name="merch.regCap"/></td>
				<td class="label">邮件地址</td>
				<td class="data"><s:label name="merch.email"/></td>
			</tr>
			<tr>
				<td class="label">联系人</td>
				<td class="data"><s:label name="merch.contract"/></td>
				<td class="label">联系电话</td>
				<td class="data"><s:label name="merch.contractTel"/></td>
			</tr>
			<tr>
				<td class="label">信用情况</td>
				<td class="data"><s:label name="merch.credit.getDesc()"/></td>
				<td class="label">公司规模</td>
				<td class="data"><s:label name="merch.companySize"/></td>
			</tr>
		    </table>
		    <h3 style="line-height:32px;text-align:center;font-size:15px;">操作备注</h3>
		    <table width="800" border="0" cellspacing="0" cellpadding="0" class="data-table">
		    	<tr>
		    	<td align="center"><s:textarea name="memo" cols="110" rows="5"></s:textarea></td>
		    	</tr>
		    </table>
		    <div style="margin:15px auto;text-align:center;">
		   		<s:submit method="accept" value="受理" cssClass="dark-btn" onclick="return beforeSubmit('是否受理该订单？');"/>
		   		<s:submit method="refuse" value="不受理" cssClass="dark-btn" onclick="return beforeSubmit('是否拒决该订单？');"/>
		   		<s:submit method="cancel" value ="撤销" cssClass="dark-btn" onclick="return beforeSubmit('是否撤撤销该订单？');"/>
		   		<s:submit mehtod="returnAction" value = "返回" cssClass="dark-btn"/>
		    </div>
		  </div>
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
	function addMerch(id){
		if(confirm("是否确认关注此商户?")){
			window.top.location.href="${ctx}/inst/instCollMerch!add?imerch="+id	;
		}
	}
</script> 
</body>
