<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="${ctx}/resources/js/workflow.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/workflow.css"/>
</head>
<body>
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：我要还款<span style="margin-left:840px"><a href="${ctx}/merch/merchMyRefund.action" class="dark-btn">返&nbsp;回</a></span></p>
  </div>
  <div id="content" class="report">
    <h3 class="title"><span>商户借款订单详情</span>&nbsp;&nbsp;<!--<span class="related-menu"><a href="JavaScript:runQQ('${inst.qqUid}')">在线联系</a></span>--></h3>
    <div class="how-to-ask">
    	<strong>借贷流程：</strong>&nbsp;<div id="myflow"></div>
    </div>
     <div><span style="font-size:14px;font-weight:bold;margin-left:20px;border-bottom:0;">借贷状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span></div>
     <div align="center" style="font-size: 15px;font-weight: bold;">本笔贷款概况</div>
   <table width="97%"  border="0" cellspacing="0" cellpadding="0" class="data-table">
   	<tr>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:80px;height:30px">借贷编号</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款申请金额(万元)</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款审批金额(万元)</td>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:100px;height:30px"">贷款周期</td>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:100px;height:30px"">贷款利率(%)</td>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:80px;height:30px"">需还款总期数</td>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:80px;height:20px"">还款方式</td>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:80px;height:20px"">申请产品</td>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:80px;height:20px"">放贷机构</td>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:90px;height:20px"">申请日期</td>
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
				<s:label name="loanOrd.rateType.desc"/><s:label name="loanOrd.rate"/>
   		</td>
   		<td class="data"><s:label name="planList.size"/></td>
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
    <div align="center" style="margin-top:30px;font-size: 15px;font-weight: bold;">机构详情</div>
    <div class="report-tab-area">
    	<ul class="tab-title clearfix">
				<li onclick="location.href='javascript:process();'">借款进度跟踪</li>
				<li onclick="location.href='javascript:refundPlan();'">还款计划</li>
				<s:if test="debitFlag == false">
				<li onclick="location.href='javascript:productDetail();'">产品详情</li>
				</s:if>
				<s:else>
				<li class="disabled">产品详情</li>
				</s:else>
				<li class="cur" for="inst-info">机构信息</li>
			</ul>
			<div class="tab-content">
				<div class="">
					<!-- <h3 class="">机构信息</h3> -->
					<div class="results report" style="padding:10px 0 10px 50px;">
					<table cellspacing="0" cellpadding="0" class="data-table" style="margin:10px 30px;width: 80%;">
						<tr>
						<td class="label" width="20%">机构名称:</td>
						<td class="data" width="30%"><s:property value="inst.instName" /></td>
						<td class="label" width="20%">机构英文名称:</td>
						<td class="data" width="30%"><s:property value="inst.englishName" /></td>
					</tr>
					<tr>
						<td class="label">机构性质:</td>
						<td class="data"><s:property value="inst.instNature" /></td>
						<td class="label">经营范围:</td>
						<td class="data"><s:property value="inst.busiRegion" /></td>
					</tr>
					<tr>
						<td class="label">注册时间:</td>
						<td class="data"><fmt:formatDate value="${inst.regTime}" pattern="yyyy-MM-dd"/></td>
						<td class="label">开业时间:</td>
						<td class="data"><fmt:formatDate value="${inst.openTime}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td class="label">联系人:</td>
						<td class="data"><s:property value="inst.contact" /></td>
						<td class="label">联系电话:</td>
						<td class="data"><s:property value="inst.contactPhone" /></td>
					</tr>
					<tr>
						<td class="label">在线联系QQ号码:</td>
						<td class="data"><s:property value="inst.qqUid" /></td>
						<td class="label">官方电话:</td>
						<td class="data"><s:property value="inst.shortPhone" /></td>
					</tr>	
					<tr>
						
						<td class="label">注册地址:</td>
						<td class="data"><s:property value="inst.regAddr" /></td>
						<td class="label">员工人数:</td>
						<td class="data"><s:property value="inst.peopleCount" /></td>
					</tr>	
					<tr>
						<td class="label">公司口号:</td>
						<td class="data"><s:property value="inst.catchword" /></td>
						<td class="label">公司网址:</td>
						<td class="data"><s:property value="inst.officialWebsite" /></td>
					</tr>	
					<tr>
						<td class="label">机构简介:</td>
						<td class="data" colspan="3" style="width:250px;word-wrap:break-word;word-break:break-all"><s:property value="inst.introduce" /></td>
					</tr>
					<tr>
						<td class="label">其他资料:</td>
						<td class="data" colspan="3" align="center">
						<s:iterator value="tfileList" id="tfiles" status="status"> 
      							<s:if test="#tfiles.metaType.indexOf('image')>-1">
      							<img src="/DrawImage.do?id=${tfiles.ifile}&type=exp" width="250" height="168"/>&nbsp;&nbsp;<br/>
      							</s:if>
      							<s:else>
      							<a href="/DrawImage.do?id=${tfiles.ifile}&type=exp">${tfiles.uploadName}</a><br/>
      							</s:else>
      							</s:iterator>
						</td>
					</tr>
					</table>
					</div>
				</div>
			</div>
		</div>
  </div>
</div>
<script type="text/javascript">
$("#myflow").flow(["申请","受理","审核","放款","还款","结束"],5);
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
			
			function productDetail(){
				window.location.href="${ctx}/merch/merchMyRefund!prodDetail";
			}
			function instInfo(){
				window.location.href="${ctx}/merch/merchMyRefund!instInfo";
			}
			function process(){
				window.location.href="${ctx}/merch/merchMyRefund!processInfo";
			}
			function refundPlan(){
				window.location.href="${ctx}/merch/merchMyRefund!viewDetail";
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>
