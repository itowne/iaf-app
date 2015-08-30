<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<script type="text/javascript" src="${ctx}/resources/js/workflow.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/workflow.css"/>
<div id="content">
<div id="workframe-wrapper" class="clearfix">
  <div class="report-tab-area">
  	<div id="content" class="report" >
		 <h3 style="line-height:42px;text-align:center;font-size:15px;">放款详情</h3>	    
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
	    	<tr>
	    		<td class="label">贷款详情</td>
	    		<td class="data" colspan="2" style="padding:0;">
	    			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table" >
	    				<tr>
	    					<th class="label" style="text-align: center">贷款编号</th>
	    					<th class="label" style="text-align: center">贷款金额(元)</th>
	    					<th class="label" style="text-align: center">放贷机构</th>
	    				</tr>
	    				<tr align="center">
	    					<td><s:if test="batchCredit">
								<s:iterator value="#request.ords" id="ord" status="st">
									<s:property value="#ord.loanOrdId" />,
								</s:iterator>
								</s:if>
								<s:else>
								<s:property value="#request.loanOrd.loanOrdId"/>
								</s:else>
							</td>
							<s:if test="FundFlowList.size()!=0">
	    						<td><s:property value="%{getText('format.money', {#request.trans.orderAmount})}"/>（元）</td>
	    					</s:if>
	    					<s:elseif test="FundFlowList.size()==0">
	    						<td><s:property value="%{getText('format.money',{#request.loanOrd.quota})}"/>（元）</td>
	    					</s:elseif>
	    					<td><s:property  value='%{#request.ist.instName}'/></td>
	    				</tr>
	    			</table>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="4">收款人信息</td>
	    		<td class="label">收款方</td>
	    		<td class="data">
	    		<s:if test="batchCredit">
				<s:iterator value="#request.merchBusiInfoList" id="merchBusiInfos" status="st">
					<s:property value="#merchBusiInfos.accountName" />,
				</s:iterator>
				</s:if>
				<s:else>
					<s:property value="#request.merchBusiInfo.accountName"/>
				</s:else>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">收款人姓名</td>
	    		<td class="data">
				<s:property value="#request.merch.merchName" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">收款人卡(帐)号</td>
	    		<td class="data">
	    			<s:if test="batchCredit">
				<s:iterator value="#request.merchBusiInfoList" id="merchBusiInfos" status="st">
					<s:property value="#merchBusiInfos.accountNo" />
				</s:iterator>
				</s:if>
				<s:else>
					<s:property value="#request.merchBusiInfo.accountNo"/>
				</s:else>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">收款人开户银行</td>
	    		<td class="data"><s:if test="batchCredit">
				<s:iterator value="#request.merchBusiInfoList" id="merchBusiInfos" status="st">
					<s:property value="#merchBusiInfos.bank" />
				</s:iterator>
				</s:if>
				<s:else>
					<s:property value="#request.merchBusiInfo.bank"/>
				</s:else></td>
	    	</tr>
	    	<tr>
	    		<td class="label">付款方信息</td>
	    		<td class="label">付款方</td>
	    		<td class="data"><s:property value="%{#request.instBusiInfo.RepaymentBank}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="5">交易信息</td>
	    		<td class="label">支付方式</td>
	    		<s:if test="FundFlowList.size()!=0">
	    			<td class="data">平台内放款：金掌柜</td>
	    		</s:if>
	    		<s:elseif test="FundFlowList.size()==0">
	    			<td class="data">平台外放款：普通商户</td>
	    		</s:elseif>
	    	</tr>
	    	<tr>
	    		<td class="label">放款总金额(元)</td>
	    		<td class="data"><s:property value="%{getText('format.money', {#request.loanOrd.quota})}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">手续费(元)</td>
	    		<s:if test="FundFlowList.size()!=0">
	    			<td class="data"><s:property value="%{#request.charge}"/></td>
	    		</s:if>
	    		<s:elseif test="FundFlowList.size()==0">
	    			<td class="data">0</td>
	    		</s:elseif> 
	    	</tr>
	    	<tr>
	    		<td class="label">合计(元)</td>
	    		<s:if test="FundFlowList.size()!=0">
	    			<td class="data"><s:property value="%{getText('format.money', {#request.trans.orderAmount})}"/></td>
	    		</s:if>
	    		<s:elseif test="FundFlowList.size()==0">
	    			<td class="data"><s:property value="%{getText('format.money', {#request.loanOrd.quota})}"/></td>
	    		</s:elseif> 
	    	</tr>
	    	<tr>
	    		<td class="label">备注</td>
	    		<td class="data" ><s:property value="%{#request.loanOrd.memo}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">交易订单号</td>
	    		<td class="data" colspan="2"><s:property value="%{#request.orderNo}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">放款交易日期</td>
	    		<td class="data" colspan="2"><s:property value="%{#request.transDate}"/></td>
	    	</tr>
	    </table>
	</div>
  </div>
</div>
	    <h3 style="line-height:42px;text-align:center;font-size:15px;">还款计划</h3>
    		
	    <div style="margin:15px auto;text-align:right;">
	    	<table align="left" cellpadding="0" border="0" >
	    		<tr>
	    			<td class="label">本金(借款金额)：</td>
	    			<td class="data" style="color:red"><s:label name="loanOrd.wangyuanQuota"/>(万元)</td>
	    			<td class="label">利率：</td>
	    			<td class="data" style="color:red">
	    			<s:label name="loanOrd.rateType.desc"/>
	    			<s:label name="loanOrd.rate"/>%</td>
	    			<td class="label">借款期限：</td>
	    			<td class="data" style="color:red"><s:label name="loanOrd.term"/><s:label name="loanOrd.termType.desc"/></td>
	    		</tr>
		    	<tr>
		    	<td class="label">总利息：</td>
		    	<td class="data" style="color:red"><s:property value="%{interest}" />元&nbsp;&nbsp;</td>
		    	<td class="label">总本金+总利息：</td>
		    	<td class="data" style="color:red"><s:property value="%{total}" />元&nbsp;&nbsp;</td>
		    	<td class="label">&nbsp;</td>
		    	<td class="data" style="color:red">&nbsp;</td>
		    	</tr>
		    </table>
		   
    			<s:form action="planMgr">
    			   <!-- <a href="${ctx}/resources/还款计划模版.xls" target="_blank" class="dark_btn" >[下载还款计划模版]</a> -->
    				<!--<s:submit method="toUpload" cssClass="dark-btn" value="上传还款计划" />-->
	    	</s:form>
	    	
    	</div>
    	<input type="hidden" id="planFlag" name="planFlag" value="${planFlag}">
	    <div  style="margin:10px auto;width:100%">
								<table width="800" id="list"></table>
								<div id="pager"></div>
							</div>
			<div id="myflow"></div>
			 <div id="modifyDiv" style="display:none;width:400;height: 100;margin:15px auto;text-align:center">
			 <div style="text-align:center"><p>系统只能修改未还款的计划，并且修改后，原来未还款的计划将会作废掉。</p>
			 <p></p>如果确认要修改原还款计划，请选择新的还款计划文件上传：</div><br><br>
	    		<s:form action="planMgr" method="POST" enctype="multipart/form-data">
	    			<s:file name="plan" label="还款计划文件"></s:file><br><br>
	    			<s:submit method="upload" onclick="return checkUpload();" cssClass="dark-btn" value="上传"></s:submit>
	    		</s:form>
	    	</div>
			<s:if test='flag=="save"'>
				<div style="margin:10px auto;width:100%;" align="center">
			 <s:form action="planMgr">
    				<s:submit method="applyPlan" cssClass="dark-btn" value="保存计划" onclick="return beforeSubmit()" />
    				&nbsp;&nbsp;<input type="button" class="dark-btn" value="取消" onclick="return conf()" />
	    	</s:form>
			</div>
			</s:if>
			<s:elseif test='flag=="upload"'>
			<div style="margin:15px auto;text-align:center;">
	    			<s:form action="planMgr" method="POST" enctype="multipart/form-data">
	    			<a href="${ctx}/resources/还款计划模版.xls" target="_blank" class="dark_btn" >下载还款计划模版</a>
	    			<s:file name="plan" id="upload" label="还款计划文件"></s:file><br/><br/><br/>
	    			<s:submit method="upload" onclick="return checkUpload();" cssClass="dark-btn" value="上传"></s:submit>
	    			</s:form>
	    	</div>
			</s:elseif>
			<s:elseif test='flag=="modify"'>
			<div style="margin:10px auto;width:100%;" align="center">
				<!--  <input type="button" value="修改计划" class="dark-btn" onclick="openModifyDiv();"/>-->
			</div>
			</s:elseif>
	    	<script type="text/javascript">
	    	$("#myflow").flow(["下载还款计划模版","按模版格式填写计划","上传填写好的计划"],0);
	    	function conf(){
	    		if(confirm("是否确认取消保存还款计划?")){
	    			window.parent.location.href="${ctx}/inst/loanord/planUpload";
	    		}else{
	    			return false;
	    		}
	    	}
	    	function beforeSubmit(){
				if(confirm("还款计划成功保存后，借款人将会按照此计划进行还款,你确认要保存本还款计划吗?")){
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    	 function checkUpload(){
	 			if($("#upload").val()==""){
	 				alert("请选择一个还款计划文件!");
	 				return false;
	 			}
	 			return true;
	 		}
	    	
	    	 function openModifyDiv(){
					$("#modifyDiv").dialog({
						modal: true,
						title: "修改还款计划",
						width: 500,
						height: 300,
						close: function(){
							$(this).dialog("destroy");
						},
						buttons: [{
							text: "关闭",
							click: function(){
								$(this).dialog("destroy");
							}
						}]
					});
				};
	    	
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
					getList();
				});
				
				$(function(){
					$(window).resize(function(){
						$("#list").setGridWidth($(window).width()*0.95);
					});
				});
				
	    		function getList(){
	    			var planFlag = $("#planFlag").val();
					jQuery("#list").jqGrid({
						url:'${ctx}/inst/loanord/planMgr!planList?planFlag='+planFlag,
						colNames:['还款期数','还款日期','还款金额(元)','还款本金(元)','还款利息(元)','剩余还款金额(元)','保存状态'],
						colModel:[
							{name:'period',index:'period',align:"center", width:30},
							{name:'refundDate',index:'refundDate',align:"center", width:40},
							{name:'refundAmount',index:'refundAmount',align:"center", width:50},
							{name:'capital',index:'capital',align:"center", width:50},
							{name:'interest',index:'interest',align:"center", width:50},
							{name:'remainAmount',index:'remainAmount',align:"center", width:50},
							{name:'saveFlag',index:'saveFlag',formatter:statFormatter, align:"center", width:50},
						],
						height:240,
						rownumbers:true,
						rowNum:10,
						pager: '#pager',
						repeatitems: false, 
						shrinkToFit:true,
						autowidth:true,
						caption:"计划列表"
					});
					jQuery("#list").jqGrid('navGrid','#pager');
				}
	    		function statFormatter(cellvalue, options, rowObject){
					if (cellvalue == "未保存" || cellvalue == "逾期" || cellvalue == "欠款冻结中" || cellvalue == "欠款已冻结" || cellvalue == "受理过期" || cellvalue == "申请过期"){
						return "<div style='color:red'>" + cellvalue + "</div>";
					}
					return cellvalue;
				}
	    		parent.window.curTab = "planMgr";
	    	</script>
</div>


