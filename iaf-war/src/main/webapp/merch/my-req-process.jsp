<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
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
				<li class="cur">受理进度跟踪</li>
				<li onclick="location.href='javascript:planInfo();'">还款计划</li>
				<s:if test="debitFlag == false">
				<li onclick="location.href='javascript:productDetail();'">产品详情</li>
				</s:if>
				<s:else>
				<li class="disabled">产品详情</li>
				</s:else>
				<li onclick="location.href='javascript:instInfo();'">机构信息</li>
			</ul>
			<div class="tab-content">
				<h3 style="line-height:42px;text-align:center;font-size:15px;">借 贷 受 理 进 度</h3>
				<table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table">
					<tr>
    		<th>序号</th>
    		<th>处理</th>
    		<th>处理结果</th>
    		<th>处理日期</th>
    		<th>操作人员</th>
    		<th>操作员角色</th>
    		<th>操作备注</th>
    	</tr>
    	<s:if test="%{#request.operLogs.size > 0}">
    		<s:iterator  value="#request.operLogs" status="st" id="op">
    			<tr align="center">
    				<td class="data"><s:property value="%{#st.index + 1}" /></td>
		    		<td class="data"><s:property value="#op.operType.desc" /></td>
		    		<td class="data"><s:property value="#op.operResult" /></td>
		    		<td class="data"><fmt:formatDate value="${op.genTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		    		<td class="data">
		    		<s:if test='#op.userName==null'>
		    			&nbsp;
		    		</s:if>
		    		<s:else>
		    		<s:property value="#op.userName" />
		    		</s:else>
		    		</td>
		    		<td class="data">
		    		<s:if test='#op.roleNames==null'>
		    		&nbsp;
		    		</s:if>
		    		<s:else>
		    		<s:property value="#op.roleNames" />
		    		</s:else>
		    		</td>
		    		<td class="data">
		    		<s:if test='#op.memo==""||#op.memo==null'>
		    		&nbsp;
		    		</s:if>
		    		<s:else>
		    		<s:property value="#op.memo" />
		    		</s:else>
		    		</td>
    			</tr>
    		</s:iterator>
    	</s:if>
    	<s:else>
	    	<tr>
	    		<td class="data">1</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    	</tr>
	    	<tr>
	    		<td class="data">2</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    		<td class="data">&nbsp;</td>
	    	</tr>
	    	
	    </s:else>
				</table>
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
