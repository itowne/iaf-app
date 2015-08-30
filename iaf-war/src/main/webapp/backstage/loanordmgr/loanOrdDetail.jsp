<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<s:form action="loanOrdMgr">
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：订单管理&nbsp;&gt;&gt;&nbsp;订单明细</p>
  </div>
  <div id="content" class="report" style="padding:10px 10px 10px 50px;">
    <h3 class="title"><span>借贷状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span></h3>
    <h3 style="line-height:32px;text-align:center;font-size:15px;">借款订单详情</h3>
    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table">
    	<tr>
    		<td class="label">借贷编号</td>
    		<td class="data"><s:label name="loanOrd.loanOrdId"></s:label></td>
    		<td class="label">申请日期</td>
    		<td class="data"><s:date name="loanOrd.ordDate" format="yyyy-MM-dd HH:mm"/></td>
    	</tr>
    	<tr>
    		<td class="label">借款商户(收款方)</td>
    		<td class="data"><s:label name="merch.merchName"/></td>
    		<td class="label">借款方汇卡商户号</td>
    		<td class="data"><s:label name="merch.merchNo"/></td>
    	</tr>
    	<tr>
    		<td class="label">借款申请金额(万元)</td>
    		<td class="data"><s:label name="loanOrd.wangyuanPreQuota"></s:label></td>
    		<td class="label">已还金额</td>
    		<td class="data"><s:label name="loanOrd.repayment"></s:label>元</td>
    	</tr>
    	<tr>
    		<td class="label">借款审批金额(万元)</td>
    		<s:if test='loanOrd.ordStat.toString()=="APPLY" || loanOrd.ordStat.toString()=="ACCEPT" || loanOrd.ordStat.toString()=="AUDIT_REFUSE" || loanOrd.ordStat.toString()=="ACCEPT_OVERDUE" || loanOrd.ordStat.toString()=="ACCEPT_REFUSE" || loanOrd.ordStat.toString()=="APPLY_OVERDUE" || loanOrd.ordStat.toString()=="CANCEL"'>
    			<td class="data">0</td>
    		</s:if>
    		<s:else>
	    		<td class="data"><s:label name="loanOrd.wangyuanQuota"></s:label></td>
    		</s:else>
    		<td class="label">已还款期数(期)</td>
    		<td class="data"><s:label name="loanOrd.recivePeriod"/>期</td>
    	</tr>
    	<tr>
    		<td class="label">总还款期数(期)</td>
    		<td class="data"><s:label name="planList.size"/>期</td>
    		<td class="label">借款周期</td>
    		<td class="data"><s:label name="loanOrd.term"></s:label><s:label name="loanOrd.termType.desc"/></td>
    	</tr>
    	<tr>
    		<td class="label">最低利率(%)</td>
    		<td class="data"><s:label name="loanOrd.rateType.desc"/><s:label name="loanOrd.rate"/>起</td>
    		<td class="label">贷款方汇卡商户号</td>
    		<td class="data"><s:label name="inst.instId"/></td>
    	</tr>
    	<tr>
    		<td class="label">贷款机构(付款方)</td>
    		<td class="data"><s:label name="inst.instName"/></td>
    		<td class="label">借款用途</td>
    		<td class="data"><s:label name="loanOrd.purpose"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">所属贷款产品</td>
    		<td class="data" colspan="3"><s:label name="loanOrd.pdtName"></s:label></td>
    	</tr>
    </table>
    <br/>
     <h3 style="line-height:32px;text-align:center;font-size:15px;">借款订单处理跟踪</h3>
    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table" style="text-align:center">
    	<tr>
    		<th class="label" width="150" style="text-align:center">日期</th>
    		<th class="label" style="text-align:center">操作</th>
    		<th class="label" style="text-align:center">操作员账号</th>
    		<th class="label" style="text-align:center">操作备注</th>
    	</tr>
    	<s:if test="%{#request.operLogs.size > 0}">
    	<s:iterator value="#request.operLogs" id="ol" status="st" >
    	<tr>
    		<td class="data"><s:property value="%{getText('format.time', {#ol.genTime})}" /></td>
    		<td class="data"><s:property value="#ol.operType.desc" /></td>
    		<td class="data"><s:property value="#ol.loginName"/></td>
    		<td class="data"><s:property value="#ol.memo"/></td>
    	</tr>
    	</s:iterator>
		</s:if>
		<s:else>
		<tr>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    	</tr>
		</s:else>
    </table>
    <br>
    <h3 style="line-height:32px;text-align:center;font-size:15px;">还款计划详情</h3>
   <table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table">
							<tr>
								<td class="label">贷款信息</td>
								<td class="data">
									本金(借款金额)：<em><font style="color:red"><s:property  value='loanOrd.wangyuanQuota'/>（万元）</font></em>&nbsp;&nbsp;<s:label name="loanOrd.rateType.desc"/>：<em><font style="color:red"><s:property  value='loanOrd.rate'/>%</font></em>&nbsp;&nbsp;借款期限：<em><font style="color:red"><s:property  value='loanOrd.term'/><s:label name="loanOrd.termType.desc"/></font></em>
								</td>
							</tr>
							<tr>
								<td class="label">预期还款信息</td>
								<td class="data">
									总利息：<em><font style="color:red"><s:property  value='interestTotal'/>元</font></em>&nbsp;&nbsp;总本金+总利息：<em><s:if test="amtTotal==0"><font style="color:red"><s:property value="loanOrd.quota"/></font></s:if><s:else><font style="color:red"><s:property  value='amtTotal'/></font></s:else>元</em>
								</td>
							</tr>
							<tr>
								<td class="label" colspan="2" style="text-align:center;font-size: 15px;font-weight: bold;">还款计划列表</td>
							</tr>
						</table>
						<table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table data-list" >
							<tr>
								<th class="label" style="text-align:center">还款期数</th>
								<th class="label" style="text-align:center">还款日期</th>
								<th class="label" style="text-align:center">还款金额(元)</th>
								<th class="label" style="text-align:center">还款本金(元)</th>
								<th class="label" style="text-align:center">还款利息(元)</th>
								<th class="label" style="text-align:center">剩余还款金额(元)</th>
								<th class="label" style="text-align:center">状态</th>
							</tr>
							<s:iterator value="planList" id="id" status="st"> 
							<tr align="center">
								<td>第<s:property value='#id.period'/>期</td>
								<td><fmt:formatDate value="${id.refundDate}" pattern="yyyy-MM-dd"/></td>
								<td><s:property value='#id.repayment'/></td>
								<td><s:property value='#id.capital'/></td>
								<td><s:property value='#id.interest'/></td>
								<td><s:property value='#id.remainAmount'/></td>
								<td><s:property value='#id.stat.desc'/></td>
							</tr>
							</s:iterator>
						</table>
						<br/>
    <h3 style="line-height:32px;text-align:center;font-size:15px;">放款操作流水记录</h3>
    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table" style="text-align:center">
    	<tr>
    		<th class="label" style="text-align:center">交易订单号</th>
    		<th class="label" style="text-align:center">放款交易金额(万元)</th>
    		<th class="label" style="text-align:center">交易状态</th>
    		<th class="label" style="text-align:center">交易时间</th>
    		<th class="label" style="text-align:center">放款方式</th>
    	</tr>
    	<s:if test="%{#request.creditFF.size > 0}">
    	<s:iterator value="#request.creditFF" id="ff">
    	<tr>
    		<td class="data">
    		<s:if test="#ff.otherSysTraceNo==''||#ff.otherSysTraceNo==null">
    		--
    		</s:if>
    		<s:else>
    		<s:property value="#ff.otherSysTraceNo" />
    		</s:else></td>
    		<td class="data"><s:property value="#ff.wanyuanCapital" /></td>
    		<td class="data"><s:property value="#ff.status.desc" /></td>
    		<td class="data"><s:date name="#ff.genTime"  format="yyyy-MM-dd HH:mm"/></td>
    		<td class="data">
    		<s:if test="#ff.otherSysTraceNo==''||#ff.otherSysTraceNo==null">
    		平台外放款
    		</s:if>
    		<s:else>
    		<s:property value="#ff.transType.desc" />
    		</s:else>
    		</td>
    	</tr>
    	</s:iterator>
    	</s:if>
    	<s:else>
    	<tr>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    	</tr>
    	</s:else>
    </table>
    	<br/>
    <h3 style="line-height:32px;text-align:center;font-size:15px;">还款操作流水记录</h3>
    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table" style="text-align:center">
    	<tr>
    		<th class="label" style="text-align:center">交易订单号</th>
    		<th class="label" style="text-align:center">还款期数</th>
    		<th class="label" style="text-align:center">还款交易金额(万元)</th>
    		<th class="label" style="text-align:center">交易状态</th>
    		<th class="label" style="text-align:center">交易时间</th>
    		<th class="label" style="text-align:center">还款方式</th>
    	</tr>
    	<s:if test="%{#request.refundFF.size > 0}">
    	<s:iterator value="#request.refundFF" id="ff">
    	<tr>
    		<td class="data">
    		<s:if test="#ff.otherSysTraceNo==''||#ff.otherSysTraceNo==null">
    		--
    		</s:if>
    		<s:else>
    		<s:property value="#ff.otherSysTraceNo" />
    		</s:else>
    		</td>
    		<td class="data">第<s:property value="#ff.period" />期</td>
    		<td class="data"><s:property value="#ff.amount" /></td>
    		<td class="data"><s:property value="#ff.status.desc" /></td>
    		<td class="data"><s:date name="#ff.genTime"  format="yyyy-MM-dd HH:mm"/></td>
    		<td class="data">
    		 <s:if test="#ff.otherSysTraceNo==''||#ff.otherSysTraceNo==null">
    		平台外还款
    		</s:if>
    		<s:else>
    		<s:property value="#ff.transType.desc" />
    		</s:else>
    		</td>
    	</tr>
    	</s:iterator>
    	</s:if>
    	<s:else>
    	<tr>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    		<td class="data">&nbsp;</td>
    	</tr>
    	</s:else>
    </table>
    <div style="margin:15px auto;text-align:center;">
    <s:if test="loanOrd.shield==0">
    <n:HcAuthButton value="屏蔽订单"  cssClass="dark-btn" onclick="shield()" authCode="JDDD_YB"/>
    </s:if>
    <s:else>
    	<n:HcAuthButton value="取消屏蔽"  cssClass="dark-btn" onclick="reShield()" authCode="JDDD_XQ"/>
    </s:else>
   		<s:submit mehtod="returnAction" value = "取消" cssClass="dark-btn"/>
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
	function shield(){
		if (confirm("是否屏蔽该订单？")){
			//$("#index").val(index);
			//$("#shieldForm").submit();
			window.location = "${ctx}/backstage/loanOrdMgr!shield";
		}
	}
	
	function reShield(){
		if(confirm("是否取消屏蔽?")){
			window.location = "${ctx}/backstage/loanOrdMgr!reShield";
		}
	}
</script> 
</body>
