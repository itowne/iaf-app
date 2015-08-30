<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>汇融易 - 商户管理后台</title>
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
<s:form action="merchMyRefund">
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：我要还款<span style="margin-left:840px"><a href="${ctx}/merch/merchMyRefund.action" class="dark-btn">返&nbsp;回</a></span></p>
  </div>
  <div id="content" class="report">
    <h3 class="title"><span>商户借款订单详情</span>&nbsp;&nbsp;<!--<span class="related-menu"><a href="JavaScript:runQQ('${inst.qqUid}')">在线联系</a></span>--></h3>
    <div class="how-to-ask">
    	<strong>借贷流程：</strong>&nbsp;<div id="myflow"></div><!--<img src="${ctx}/resources/images/mp/bg-refund-step.gif" width="586" height="24" alt=""/>-->
    </div>
    <div><span style="font-size:14px;font-weight:bold;margin-left:20px;border-bottom:0;">借贷状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span></div>
    <div style="padding: 5px;width:98%;">
   <div align="center" style="font-size: 15px;font-weight: bold;">本笔贷款概况</div>
   <table width="97%"  border="0" cellspacing="0" cellpadding="0" class="data-table">
   	<tr>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:80px;height:30px">借款编号</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款申请金额(万元)</td>
   		<td class="label" style="background: #F3F9FD;width:100px;height:30px; text-align:center">贷款审批金额(万元)</td>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:100px;height:30px"">借款周期</td>
   		<td class="label" style="text-align:center;background: #F3F9FD;width:100px;height:30px"">借款利率(%)</td>
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
   		<td class="data"><s:label name="planList.size"/>期</td>
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
   <div align="center" style="margin-top:30px;font-size: 15px;font-weight: bold;">本笔借款详情</div>
    <div class="report-tab-area">
    	<ul class="tab-title clearfix">
				<li onclick="location.href='javascript:process();'">借款进度跟踪</li>
				<li class="cur" for="refund-plan">还款计划</li>
				<s:if test="debitFlag == false">
				<li onclick="location.href='javascript:productDetail();'">产品详情</li>
				</s:if>
				<s:else>
				<li class="disabled">产品详情</li>
				</s:else>
				<li onclick="location.href='javascript:instInfo();'">机构信息</li>
			</ul>
			<div class="tab-content">
				<div>
					<h3 style="font-size:15px;line-height:42px;text-align:center;">我的还款计划</h3>
					<div style="padding:0 45px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
							<tr>
								<td class="label">借款信息</td>
								<td class="data">
									本金(借款金额)：<em><s:property  value='loanOrd.wangyuanQuota'/>（万元）</em>&nbsp;&nbsp;<s:label name="loanOrd.rateType.desc"/>：<em><s:property  value='loanOrd.rate'/>%</em>&nbsp;&nbsp;借款期限：<em><s:property  value='loanOrd.term'/><s:label name="loanOrd.termType.desc"/></em>
								</td>
							</tr>
							<tr>
								<td class="label">预期还款</td>
								<td class="data">
									总利息：<em><s:property  value='interestTotal'/>元</em>&nbsp;&nbsp;总本金+总利息：<em><s:property  value='amtTotal'/>元</em>
								</td>
							</tr>
							<tr>
								<td class="label" colspan="2" style="text-align:center;font-size: 15px;font-weight: bold;">还款计划详情</td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table data-list" >
							<tr>
								<th>选择</th>
								<th>还款编号</th>
								<th>期数</th>
								<th>还款日期</th>
								<th>还款金额</th>
								<th>本金</th>
								<th>利息</th>
								<th>剩余还款金额</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							<s:iterator value="planList" id="id" status="st"> 
							<tr align="center">
								<td><input type="checkbox" id="iloanOrdPlan" name="iloanOrdPlan" value="${st.index}"/></td>
								<td><s:property value='#id.iloanOrdPlan'/></td>
								<td><s:property value='#id.period'/></td>
								<td><fmt:formatDate value="${id.refundDate}" pattern="yyyy-MM-dd"/></td>
								<td><s:property value='#id.repayment'/></td>
								<td><s:property value='#id.capital'/></td>
								<td><s:property value='#id.interest'/></td>
								<td><s:property value='#id.remainAmount'/></td>
								<td><s:property value='#id.stat.desc'/></td>
								<td><a href="javascript:void(0);" onclick="refundDetail('<s:property value='#id.iloanOrdPlan'/>')">还款详情</a></td>
							</tr>
							</s:iterator>
						</table>
					</div>
				</div>
				<div align="left" style="padding: 6px;width:300px;margin-left:50px ">至少选择以上的一期还款进行操作</div>
				<div align="center" style="padding:5px"><s:submit method="doPayment" cssClass="dark-btn" value="还    款"></s:submit></div>
			</div>
		</div>
  </div>
</div>
</s:form>
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
			
			function doPayment(){
				var str="";
	            $("#iloanOrdPlan").each(function(){ 
	                if($(this).attr("checked")){
	                    str += $(this).val()+",";
	                }
	            })
	            if(""==str){
	            	alert("请选择还款计划!");return false;
	            }

			}
			
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
			function refundDetail(id){
				window.location.href="${ctx}/merch/merchMyRefund!refundDetail?loanOrdPlanId="+id;
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>