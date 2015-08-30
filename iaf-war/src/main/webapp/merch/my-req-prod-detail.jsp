<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script language="JavaScript">
   javascript:window.history.forward(1);
</script>
<script type="text/javascript" src="${ctx}/resources/js/workflow.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/workflow.css"/>
<style type="text/css">
			.small-title{
				padding:5px 55px;font-size:15px;
			}
			#content .results p{
				line-height:28px;padding-left:54px;
			}
			#xxoo td{
				text-align:left;padding:5px 60px;letter-spacing:3px;
			}
		</style>
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
				<li onclick="location.href='javascript:planInfo();'">还款计划</li>
				<li class="cur">产品详情</li>
				<li onclick="location.href='javascript:instInfo();'">机构信息</li>
			</ul>
			<div class="tab-content">
				<div class="results report" style="padding:10px 0 10px 50px;">
				<h3 class="title">
					<span>放 贷 产 品 详 情</span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="related-menu"><a href="javascript:runQQ('${loanPdt.inst.qqUid}');">在线联系</a></span>
				</h3>
    <div class="results report" style="padding:10px 0 10px 50px;">
					<h3 class="small-title">产 品 名 称：<font style="color:red"><s:property  value='loanPdtHis.pdtName'/></font>
					</h3>
					<h3 class="small-title">产 品 简 述 </h3>
			<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 30px;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<label name="loanPdtHis.introduce"  rows="3" cols="86" readonly="readonly"><s:property value='loanPdtHis.introduce'/></label>
		    		</td>
		    	</tr>
		    </table>
		    
			<h3 class="small-title">产 品 特 点</h3>
			<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 30px;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<!--  <label name="loanPdtHis.feature"  rows="3" cols="86" readonly="readonly"><s:property value='loanPdtHis.feature'/></label>-->
		    		<div>${loanPdtHis.feature }</div>
		    		</td>
		    	</tr>
		    </table>
					<h3 class="small-title">产 品 详 情</h3>	
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table" style="margin:10px 30px;">
						<tr>
							<td class="label">贷款最低金额</td>
							<td class="data"><s:property  value='loanPdtHis.minQuota'/>（万元）</td>
							<td class="label">贷款最高金额</td>
							<td class="data"><s:property  value='loanPdtHis.maxQuota'/>（万元）</td>
						</tr>
						<tr>
							<td class="label">贷款最短周期</td>
							<td class="data"><s:property  value='loanPdtHis.minTerm'/><s:label name="loanPdtHis.minTermType.desc"/></td>
							<td class="label">贷款最长周期</td>
							<td class="data"><s:property  value='loanPdtHis.maxTerm'/><s:label name="loanPdtHis.maxTermType.desc"/></td>
						</tr>
						<tr>
				    		<td class="label">还款方式</td>
				    		<td class="data"><s:property  value='loanPdtHis.repayment.desc'/></td>
				    		<td class="label">放贷时间</td>
				    		<td class="data"><s:property value="loanPdtHis.creditTerm"/>（天）</td>
				    	</tr>
				    	<tr>
				    		<td class="label">业务受理地区</td>
				    		<td class="data"><s:property value="loanPdtHis.area"/></td>
				    		<td class="label">最低利率</td>
				    		<td class="data">
				    		<s:label name="loanPdtHis.rateType.desc"/>
				    		<s:property  value='loanPdtHis.rate'/>（%）起</td>
				    	</tr>
				    	<tr>
				    		<td class="label">产品状态</td>
				    		<td class="data"><s:property  value='loanPdtHis.pdtStatus.desc'/></td>
				    		<td class="label">产品上架时间</td>
				    		<td class="data"><fmt:formatDate value="${loanPdtHis.updTime}" pattern="yyyy-MM-dd"/></td>
				    	</tr>
					</table>
					<h3 class="small-title">申 请 条 件</h3>
		<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 30px;" id="xxoo">
    	<tr>
    		<td class="label">
		    	<!--  <label name="loanPdtHis.condition"  rows="3" cols="86" readonly="readonly"><s:property value='loanPdtHis.condition'/></label>-->
		    	<div>${ loanPdtHis.condition}</div>
		    </td>
    	</tr>
    </table>

    <h3 class="small-title">所 需 材 料</h3>
    <table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 30px;" id="xxoo">
    	<tr>
    		<td class="label">
		    	<!--  <label name="loanPdtHis.cl"  rows="3" cols="86" readonly="readonly"><s:property value='loanPdtHis.cl'/></label>-->
		   		<div>${ loanPdtHis.cl}</div>
		    </td>
    	</tr>
    </table>
					<div class="uop">
						
					</div>
					<br/><br/>
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
			function process(){
				window.location.href="${ctx}/merch/merchMyReq!processInfo";
			}
			function instInfo(){
				window.location.href="${ctx}/merch/merchMyReq!instInfo";
			}
			function cancel(){
				if(confirm("确认提交请求?")){
					window.location.href="${ctx}/merch/merchMyReq!cancelMyReq";
				}else{
					
				}
			}
			function planInfo(){
				window.location.href="${ctx}/merch/merchMyReq!planInfo";
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>
