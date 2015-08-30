<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<html>
	<head>
		<meta charset="UTF-8"/>
		<title>汇融易 - 商户管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="../resources/css/mp-workframe.css"/>
		<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：我要借款&nbsp;&gt;&gt;&nbsp;借款发布</p>
			</div>
			<div id="content">
				<h3 class="title"><span>借款信息详情</span></h3>
				<div class="results report">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">借款金额</td>
							<td class="data"><s:property value="debitBid.wangyuanQuota" />（万元）</td>
							<td class="label">期限范围</td>
							<td class="data"><s:property value="debitBid.term" /><s:label name="debitBid.termType.desc"/></td>
						</tr>
						<tr>
							<td class="label">利率</td>
							<td class="data">
							<s:label name="debitBid.rateType.desc"/>
							<span id="yearRate"><s:property value="debitBid.yearRate" /></span>(%)</td>
							<!-- 
							<td class="label">月利率</td>
							<td class="data">
							<span id="monthRate"></span>%
							</td>
							 -->
							 <td class="label">还款方式</td>
							<td class="data">
							<s:property value="debitBid.refundType.desc" />
							</td>
						</tr>
						<tr>
							<td class="label">借款地区</td>
							<td class="data"><s:property value="areaName" /></td>
							<td class="label">申请时间</td>
							<td class="data">
							<s:date name="debitBid.genTime" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
						</tr>
						<tr>
							<td class="label">失效日期</td>
							<td class="data"><fmt:formatDate value="${debitBid.expireDate}" pattern="yyyy-MM-dd"/></td>
							<td class="label">申请状态</td>
							<td class="data">
							<s:property value="debitBid.bidStat.desc" />
							</td>
						</tr>
						<tr>
							<td class="label">受理机构</td>
							<td class="data" style="width:300px;word-wrap:break-word;word-break:break-all"><s:property value="%{instName}"/></td>
							<td class="label">目前受理情况</td>
							<td class="data">
							<s:if test="debitBid.acceptTotal==null">0次</s:if>
							<s:else><s:property value="debitBid.acceptTotal"/>次</s:else>
							</td>
						</tr>
						<tr>
							<td class="label">借款用途</td>
							<td class="data" colspan="3" style="width:650px;word-wrap:break-word;word-break:break-all"><s:property value="debitBid.purpose" /></td>
						</tr>
					</table>
					<div class="uop">
						<!-- <a href="javascript:void(0);" class="dark-btn">修&nbsp;改</a>&nbsp;&nbsp;&nbsp;-->
						<s:if test='debitBid.bidStat.toString()=="NORMAL"'>
						<button class="dark-btn" id="cancel" name="<s:property value="debitBid.idebitBid" />">撤销发布</button>&nbsp;&nbsp;&nbsp;
						</s:if>
						<button id="back" class="dark-btn" >返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</button>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		//var rate = $("#yearRate").text();
		//if(!isNaN(rate)){
			//if(rate!=0&&rate!=""&&rate){
			//var monthRate = rate/12;
				//$("#monthRate").html(monthRate.toFixed(2));
			//}
		//}
		
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
			});
			$('#back').click(function(){
				window.location.href="${ctx}/merch/merchDebitBid";
			})
			$('#cancel').click(function(){
				if(confirm("是否要撤销该竞投申请?")){
				window.location.href="${ctx}/merch/merchDebitBid!cancel?index="+this.name;
				}
			})
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>