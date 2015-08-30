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
<script language="JavaScript">
   javascript:window.history.forward(1);
</script>
<script type="text/javascript" src="${ctx}/resources/js/workflow.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/workflow.css"/>
</head>

<body>
<div id="workframe-wrapper" class="clearfix">
	<input type="hidden" id="loanStat" name="loanStat" value="<s:property value='%{loanOrd.ordStat.desc}'/>"/>
  	<div id="location">
    	<p>当前位置：我的申请</p>
  	</div>
  	<div id="content" class="report">
    	<h3 class="title"><span>商户借款订单详情</span>&nbsp;&nbsp;<span class="related-menu"><a href="JavaScript:runQQ('${inst.qqUid}')">在线联系</a></span></h3>
    <div class="how-to-ask">
    	<strong>借贷流程：</strong>&nbsp;<div id="myflow"></div><!--<img src="${ctx}/resources/images/mp/how-to-ask.gif" width="586" height="24" alt=""/>-->
    </div>
    <div class="report-tab-area">
    	<ul class="tab-title clearfix">
				<li onclick="location.href='javascript:process();'">受理进度跟踪</li>
				<li class="cur">还款计划</li>
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
					<h3 style="font-size:15px;line-height:42px;text-align:center;">借贷还款计划</h3>
					<div style="padding:0 45px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
							<tr>
								<td class="label">借款信息</td>
								<td class="data">
									本金(借款金额)：<em><s:property  value='loanOrd.wangyuanQuota'/>（万元）</em>&nbsp;&nbsp;利率：<em><s:property value="loanOrd.rateType.desc"/><s:property  value='loanOrd.rate'/>%</em>&nbsp;&nbsp;借款期限：<em><s:property  value='loanOrd.term'/><s:property value='loanOrd.termType.desc'/></em>
								</td>
							</tr>
							<tr>
								<td class="label">预期还款</td>
								<td class="data">
									总利息：<em><s:property  value='interestTotal'/>元</em>&nbsp;&nbsp;总本金+总利息：<em><s:property  value='amtTotal'/>元</em>
								</td>
							</tr>
							<tr>
								<td class="label">详细还款计划如下</td>
								<td class="data" align="right">
									&nbsp;
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table" >
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
							<s:iterator value="planList" id="id" status="status"> 
							<tr align="center">
								<td>
								<input type="checkbox" name="checkbox" value="${status.index}"/>
								</td>
								<td><s:property value='#id.iloanOrdPlan'/></td>
								<td><s:property value='#id.period'/></td>
								<td><fmt:formatDate value="${id.refundDate}" pattern="yyyy-MM-dd"/></td>
								<td><s:property value='#id.repayment'/></td>
								<td><s:property value='#id.capital'/></td>
								<td><s:property value='#id.interest'/></td>
								<td><s:property value='#id.remainAmount'/></td>
								<td><s:property value='#id.stat.desc'/></td>
								<td><a href="javascript:void(0);">受理查询</a></td>
							</tr>
							</s:iterator>
						</table>
					</div>
				</div>
			</div>
			<div class="uop">
			<s:if test="loanOrd.ordStat.toString()=='APPLY'">
				<a href="javascript:cancel();" class="dark-btn">申请撤销</a>
			</s:if>
			&nbsp;&nbsp;&nbsp;<a href="${ctx}/merch/merchMyReq.action" class="dark-btn">返&nbsp;回</a></div>
		</div>
  </div>
</div>
<script type="text/javascript">
	var strs = (["提交申请","已受理，审核中","审核通过,待放款","放款中","商户还款中，未审核","还款中","逾期","已还清","审核未通过","受理逾期","不受理","申请逾期","撤销申请"]);
	var stat = $("#loanStat").val();
	var loanstat;
	for(var i=1;i<14;i++){
		if(strs[i-1]==stat){
			break;
		}
	}
	if(i==1 || i==12 || i==13){
		loanstat=1;
	}else if(i==2 || i==3 || i==9 || i==10 || i==11){
		loanstat=2;
	}else if(i==4){
		loanstat=3;
	}else if(i==5 || i==6 || i==7 || i==8){
		loanstat=4;
	}
	$("#myflow").flow(["申请","受理审核","获取借款","还款"],loanstat);
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
				window.location.href="${ctx}/merch/merchMyReq!prodDetail";
			}
			function instInfo(){
				window.location.href="${ctx}/merch/merchMyReq!instInfo";
			}
			function process(){
				window.location.href="${ctx}/merch/merchMyReq!processInfo";
			}
			function refundPlan(){
				window.location.href="${ctx}/merch/merchMyReq!viewDetail";
			}
			function cancel(){
				if(confirm("确认提交请求?")){
					window.location.href="${ctx}/merch/merchMyReq!cancelMyReq";
				}else{
					
				}
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>