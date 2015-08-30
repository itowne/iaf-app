<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<style type="text/css">
#tab{
		position:absolute;width:40px;overflow:hidden;padding:20px 0 0;left:-3px;top:36px;background:url(${ctx}/resources/images/mp/bg-inst-tab.gif) repeat-y;
	}
	#tab li{
		width:31px;height:97px;overflow:hidden;padding:20px 0 0 3px;letter-spacing:10px;text-align:center;cursor:pointer;font-weight:bold;border-bottom:1px solid #dadada;
	}
	#tab li.cur{
		height:106px;padding:30px 0 0 6px;background:url(${ctx}/resources/images/mp/bg-inst-tab-cur.gif) no-repeat;color:#fff;border:0;
	}
</style>
</head>

    <h3 style="line-height:32px;text-align:center;font-size:15px;">借 款 申 请 情 况</h3>
    <table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table report">
    	<tr>
    		<td class="label" width="150">借款用途</td>
    		<td class="data" width="250"><s:property  value='debitBid.purpose'/></td>
    		<td class="label" width="150">借款金额</td>
    		<td class="data" width="250">￥<s:property  value='%{wanyuanFormat(debitBid.quota)}'/>元</td>
    	</tr>
    	<tr>
    		<td class="label">利率</td>
    		<td class="data"><s:property  value='debitBid.yearRate'/>%</td>
    		<td class="label">借款期限</td>
    		<td class="data"><s:property  value='debitBid.term'/>个月</td>
    	</tr>
    	<tr>
    		<td class="label">失效时间</td>
    		<td class="data"><fmt:formatDate value="${debitBid.expireDate}" pattern="yyyy-MM-dd"/></td>
    		<td class="label">申请日期</td>
    		<td class="data"><fmt:formatDate value="${debitBid.genTime}" pattern="yyyy-MM-dd"/></td>
    	</tr>
    </table>
    <h3 style="line-height:32px;text-align:center;font-size:15px;">商 户 基 本 情 况</h3>
    <table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table report">
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
				<td class="data"><s:label name="merch.regNo"/></td>
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
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->

