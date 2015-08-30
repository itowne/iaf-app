<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/template/head.jsp" />
  <div class="report-tab-area">
	  	<div id="content" class="report">
	  	 <div align="center" style="margin-top:30px;font-size: 15px;font-weight: bold;">放款详情</div>
    <div style="padding:30px 100px;">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
    	<s:hidden id="loanOrdId" name="loanOrd.loanOrdId" />
    	<input type="hidden" id="planList" name="planList" value="${plans}"/>
	    	<tr>
	    		<td class="label">贷款详情</td>
	    		<td class="data" colspan="2" style="padding:0;">
	    			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table" >
	    				<tr>
	    					<th style="background: #F3F9FD">贷款编号</th>
	    					<th style="background: #F3F9FD">贷款金额(万元)</th>
	    					<th style="background: #F3F9FD">放贷机构</th>
	    				</tr>
	    				<tr align="center">
	    					<td><s:property  value='loanOrd.loanOrdId'/></td>
	    					<td><s:property  value='loanOrd.wangyuanQuota'/></td>
	    					<td><s:property  value='instx.instName'/></td>
	    				</tr>
	    			</table>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="4">收款人信息</td>
	    		<td class="label">收款方</td>
	    		<td class="data"><s:property  value='merch.merchName'/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">收款人姓名</td>
	    		<td class="data">
	    		<s:if test='mbi.accountName==""||mbi.accountName==null'>
	    			&nbsp;
	    		</s:if>
	    		<s:else>
	    		<s:property  value='mbi.accountName'/>
	    		</s:else>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">收款人卡(帐)号</td>
	    		<td class="data">
	    		<s:if test='mbi.accountNo==""||mbi.accountNo==null'>
	    		&nbsp;
	    		</s:if>
	    		<s:else>
	    		<s:property  value='mbi.accountNo'/>
	    		</s:else>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">收款人开户银行</td>
	    		<td class="data">
	    		<s:if test='mbi.bank==""||mbi.bank==null'>
	    		&nbsp;
	    		</s:if>
	    		<s:else>
	    		<s:property  value='mbi.bank'/>
	    		</s:else>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">付款方信息</td>
	    		<td class="label">付款方</td>
	    		<td class="data"><s:property value="%{instx.instName}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="5">交易信息</td>
	    		<td class="label">支付方式</td>
	    		<td class="data"><s:property value="%{fundFlow.type.desc}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">放款总金额(元)</td>
	    		<td class="data"><s:property value="%{loanOrd.quota}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">交易手续费(元)</td>
	    		<td class="data"><s:property value="%{charge}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">合计(元)</td>
	    		<td class="data">
	    		<s:if test='tm==""||tm==null'>
	    		<s:property value="%{fundFlow.amount}"/>
	    		</s:if>
	    		<s:else>
	    		<s:property value="%{tm.orderAmount}"/>
	    		</s:else>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">放款备注</td>
	    		<td class="data" style="padding:5px;" colspan="2"><s:label name="loanOrd.memo"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">交易订单号</td>
	    		<td class="data" colspan="2" align="center">
	    		<s:if test='tm.orderNo==""||tm.orderNo==null'>
	    		&nbsp;
	    		</s:if>
	    		<s:else>
	    		<s:label name="tm.orderNo"/>
	    		</s:else>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">放款交易日期</td>
	    		<td class="data" colspan="2" align="center"><fmt:formatDate value="${loanOrd.creditDate}" pattern="yyyy-MM-dd"/></td>
	    	</tr>
	    </table>
    </div>
	    <h3 style="line-height:42px;text-align:center;font-size:15px;">还款计划</h3>
	    <div style="padding: 20px">
		<table align="left" cellpadding="0" border="0" >
			<tr>
			<td class="label">贷款信息：</td>
	    	<td class="data">
			本金(借款金额)：<font style="color:red"><s:property value="%{loanOrd.wangyuanQuota}"/>(万元)</font>&nbsp;&nbsp;
			利率：<font style="color:red"><s:property value="%{loanOrd.pdtRate}"/></font>&nbsp;&nbsp;
			 借款期限：<font style="color:red"><s:property value="%{loanOrd.pdtTerm}"/></font></td>
			</tr>
	    	<tr>
	    	<td class="label">预期还款信息：</td>
	    	<td class="data">
	    	总利息：<font style="color:red"><s:property value="%{interest}"/>元</font>&nbsp;&nbsp; 
	    	 总本金+总利息：<font style="color:red"><s:property value="%{total}"/>元</font></td>
	    	</tr>
	    </table>
	    </div>
	    <div align="right"><s:if test="display == 0">
	    <input type="button" value="修改还款计划" class="dark-btn" onclick="openModifyDiv();"/>
	    <!-- 
					<s:submit method="applyPlan" cssClass="dark-btn" value="保存还款计划" onclick="beforeSubmit2();"/>
	     -->
	     <input type="button" value="保存还款计划" class="dark-btn" onclick="beforeSubmit2();"/>
		<!-- 					
			'保存还款计划 - 填写备注信息', 'refundMgr!applyPlan');" /> 
		-->
	    		</s:if>
	    		<s:else>
	    		&nbsp;
	    		</s:else>
	    		</div>
	    <div id="modifyDiv" style="display:none;width:400;height: 100;margin:15px auto;text-align:center">
	    	<s:if test="#request.queryhis == false">
	    		<s:form action="refundMgr" methodmethod="POST" enctype="multipart/form-data">
	    			<s:file name="plan" label="还款计划文件"></s:file>
	    			<s:submit method="upload" cssClass="dark-btn" value="修改"></s:submit>
	    		</s:form>
	    	</s:if>
	    	<s:else>
	    		&nbsp;
	    		</s:else>
	    	</div>
	    <div id="memoDiv" style="display:none;width:400;height: 200;margin:15px auto;text-align:center">
		<s:textarea  id="memo" name="memo" value="" cols="56" rows="10"></s:textarea>
	    </div>
	    <div  style="margin:10px auto;width:100%">
			<table id="list"></table>
			<div id="pager"></div>
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
					getList();
				});
				
				$(function(){
					$(window).resize(function(){
						$("#list").setGridWidth($(window).width()*0.95);
					});
				});
				
	    		function getList(){
					jQuery("#list").jqGrid({
						url:'${ctx}/inst/loanord/refundMgr!planList',multiselect:true,
						colNames:['还款期数','还款日期','还款金额(元)','还款本金(元)','还款利息(元)','剩余还款金额(元)','状态','操作'],
						colModel:[
							{name:'period',index:'period',align:"center", width:30},
							{name:'refundDate',index:'refundDate',align:"center", width:40},
							{name:'refundAmount',index:'refundAmount',align:"center", width:50},
							{name:'capital',index:'capital',align:"center", width:50},
							{name:'interest',index:'interest',align:"center", width:50},
							{name:'remainAmount',index:'remainAmount',align:"center", width:50},
							{name:'stat',index:'stat',align:"center", width:60, formatter:statFormatter},
							{name:'option',index:'option',formatter:optionFormatter1,align:"center",width:55}
						],
						height:240,
						rowNum:10,
						pager: '#pager',
						javaObj:"planDataSet",
						repeatitems: false, 
						shrinkToFit:true,
						autowidth:true,
						caption:"计划列表"
					});
					
					jQuery("#list").jqGrid('navGrid','#pager');
				}
	    	</script>
	    	
		<div id="otherDiv" style="display:none;width:400;height: 200;margin:15px auto;text-align:center">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			<tr>
				<td class="label" style="background:#D7EBFA" colspan="2">平台外还款确认后，系统会自动把本订单状态变更为“已还款”</td>
			</tr>
			<tr>
			<!-- <td class="label" style="background:#D7EBFA">备注</td> -->
			<td class="data" colspan="2"><s:textarea  id="OthMemo" name="OthMemo"  cols="56" value="" rows="10"></s:textarea></td>
			</tr>
		</table>
	    </div>
	    <div style="margin:15px auto;text-align:center;">
<!-- 	    	<input type="hidden" id="memox" name="memox" value=""/> -->
<!-- 	    	<input type="hidden" id="memo" name="memo" value=""/> -->
    		<s:hidden name="selectedRows" id="selectedRows"></s:hidden>
    		<input type="button"   class="dark-btn" value="冻结申请"  onclick="apply()" /> 
    		<input type="button" class="dark-btn" value="重新打印冻结申请凭证" onclick="toPrintFreeze()"/>
    		<input type="button" class="dark-btn" value="重新打印解冻申请凭证" onclick="toPrintUnFreeze()"/>
    		<input type="button" class="dark-btn" value="解冻申请" onclick="unFreeze()"/>
    		<input type="button" class="dark-btn" value="上传申请凭证" onclick="upload()"/>
    		<!--<s:submit method="thraw" disabled="queryhis"  cssClass="dark-btn" value="解冻申请"  onclick="return beforeSubmit3('提交解冻请求 - 填写备注信息', 'refundMgr!thraw');" />-->
    		<!-- 
    		<input type="button" class="dark-btn" value="解冻申请" onclick="unFreeze()"/>
    		<input type="button" class="dark-btn" value="上传冻结申请凭证" onclick="uploadFreeze"/>
    		<input type="button" class="dark-btn" value="上传解冻申请凭证" onclick="uploadUnFreeze"/>
    		-->
    		<!--<s:submit method="freeze" disabled="queryhis" cssClass="dark-btn" value="冻结申请"  onclick="return beforeSubmit3('提交冻结请求 - 填写备注信息', 'refundMgr!freeze');" />-->
    		<!-- <input type="button"   class="dark-btn" value="撤销冻结请求"  onclick="JavaScript:return openDialog();" /> -->
    		<!--<s:submit method="returnAction" onclick="return returnAction();" cssClass="dark-btn" value="返    回" />-->
	    </div>
	  </div>
	</div>
	<s:form id="forms" action="refundMgr!applyPlan" method="POST">
		<input type="hidden" id="refundPlanMemo" name="refundPlanMemo" value=""/>
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
					$("#workframe-wrapper").height(700);
				}
			});
			
			function optionFormatter1(cellvalue, options, rowObject){
				var id=rowObject[8];
				var stat = rowObject[6];
				var planstat = rowObject[7];
				if(stat=="已还款"){
					return '[<a href="javaScript:detail('+id+')">详情</a>]';
				}else if(stat=="欠款冻结中"||stat=="欠款已冻结"||stat=="解冻中"||planstat=="未保存"){
					return "--";
				}else{
					return '[<a href="javaScript:otherRefund('+id+')">平台外还款</a>]';
				}
			}
			function returnAction(){
				$("#returnForm").submit();
				return false;
			}
			function openDialog(){
				//var result = window.showModalDialog("/inst/loanord/refundMgr!viewFreezeList.action?method:viewFreezeList",null,"dialogWidth=900px;dialogHeight=500px");
				//window.location = "/inst/loanord/refundMgr!refreshViewLoanOrd";
				//return false;
				window.location.href="/inst/loanord/refundMgr!viewFreezeList.action?method:viewFreezeList";
			}
			
			function apply(){
				var rows = $("#list").jqGrid('getGridParam','selarrrow');
				if(rows.length>1){
					alert('请选择单个账户。');
					 return false;
				}
                if( rows ==null||rows=='') {
                    alert('请至少选择一个账户。');
                    return false;
                }
                if( typeof(rows[0])=="undefined")rows.shift();
               
                if(rows[0]==rows[1])
                {
                    rows.shift();
                }
				 $("#selectedRows").val(rows);
				 var selectedRows= $("#selectedRows").val();
				window.parent.location.href="/inst/loanord/refundMgr!applyFreeze.action?selectedRows="+selectedRows;
			}
			function beforeSubmit(desc){
				return confirm(desc);
			}
			function checkSelected(){
				var rows = $("#list").jqGrid('getGridParam','selarrrow');
				if(rows.length>1){
					alert('请选择单个账户。');
					 return false;
				}
                if( rows ==null||rows=='') {
                    alert('请至少选择一个账户。');
                    return false;
                }
                if( typeof(rows[0])=="undefined")rows.shift();
               
                if(rows[0]==rows[1])
                {
                    rows.shift();
                }
                $("#selectedRows").val(rows);
                return true;
			}
			function beforeSubmit1() {
				if (confirm(desc)){
	                return checkSelected();
				}else{
					return false;
				}
            }
			function statFormatter(cellvalue, options, rowObject){
				var data =rowObject[9];
				var stat = rowObject[6];
				if(stat=="欠款冻结中"||stat=="解冻中"){
					if(data==1){
						return "<div style='color:red'>冻结清算中，已上传凭证</div>";
					}else if(data==2){
						return "<div style='color:red'>冻结清算中，未上传凭证</div>";
					}else if(data==3){
						return "<div style='color:red'>解冻清算中，已上传凭证</div>";
					}else if(data==4){
						return "<div style='color:red'>解冻清算中，未上传凭证</div>";
					}else{
						return stat;
					}
				}else{
					if(stat==""||stat==null){
						return '<font style="color:red">'+rowObject[7]+'</font>';
					}else{
						return stat;
					}
				}
				
				//if (cellvalue == "不受理" ||cellvalue == "审核未通过" || cellvalue == "未保存" || cellvalue == "逾期" || cellvalue == "欠款冻结中" || cellvalue == "欠款已冻结" || cellvalue == "受理过期" || cellvalue == "申请过期"){
					//return "<div style='color:red'>" + cellvalue + "</div>";
				//}
				//return cellvalue;
			}
			function beforeSubmit2(){//title, action){
				jQuery.ajax({
		            type: "post",
		            async: false,
		            url: "/../refundplan",
// 		            data: "{'checkFlag':'checkFlag'}",
		            dataType: "json",
		            cache: false,
		            success: function (datalist) {
						if(datalist.checkFlag==2){
							alert("请上传还款计划");
						}else if(datalist.checkFlag==1){
							alert("还款总本金与贷款金额金额不符，不能保存！");
						}else if(datalist.checkFlag==0){
							dialog('保存还款计划 - 填写备注信息', "memoDiv", 500, 300, function(dialogObj){
								var dialogMemo = $(dialogObj).find("#memo").val();
								$("#refundPlanMemo").val(dialogMemo);
								$("#forms").attr("action", 'refundMgr!applyPlan').submit();
							});
						}
		            },
		            error: function (err) {
		                alert(err);
		            }
		        });
			}
			
// 			function beforeSubmit3(title, action){
// 				if (checkSelected()){
// 					return beforeSubmit2(title, action);
// 				}else{
// 					return false;
// 				}
// 			}
			function openModifyDiv(){
				$("#modifyDiv").dialog({
					modal: true,
					title: "修改还款计划",
					width: 400,
					height: 200,
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
			
			function detail(id){
				window.location.href="/inst/loanord/refundMgr!detail?loanOrdPlanId="+id;
			}
			
			function otherRefund(id){
				$("#otherDiv").dialog({
					modal: true,
					title: "平台外转账 - 填写备注信息",
					width: 500,
					height: 350,
					close: function(){
						$(this).dialog("destroy");
					},
					buttons: [{
						text: "确定",
						click: function(){
							var memoOth=$("#OthMemo").val();
							//$("#memox").val(memo);
							//$("#forms").attr("action", "refundMgr!otherRefund?loanOrdPlanId="+id).submit();
							window.parent.location.href="/inst/loanord/refundMgr!otherRefund?loanOrdPlanId="+id+"&memox="+encodeURI(memoOth);
						}
					},{
						text: "关闭",
						click: function(){
							$(this).dialog("destroy");
						}
					}]
				});
			}
			function toPrintFreeze(){
				var rows = $("#list").jqGrid('getGridParam','selarrrow');
				if(rows.length>1){
					alert('请选择单个账户。');
					 return false;
				}
                if( rows ==null||rows=='') {
                    alert('请选择一个账户。');
                    return false;
                }
                if( typeof(rows[0])=="undefined")rows.shift();
               
                if(rows[0]==rows[1])
                {
                    rows.shift();
                }
				 $("#selectedRows").val(rows);
				 var selectedRows= $("#selectedRows").val();
				window.parent.location.href="/inst/loanord/refundMgr!toPrint?selectedRows="+selectedRows;
			}
			function toPrintUnFreeze(){
				var rows = $("#list").jqGrid('getGridParam','selarrrow');
				if(rows.length>1){
					alert('请选择单个账户。');
					 return false;
				}
                if( rows ==null||rows=='') {
                  alert('请选择一个账户。');
                  return false;
                }
                if( typeof(rows[0])=="undefined")rows.shift();
               
                if(rows[0]==rows[1])
                {
                    rows.shift();
                }
				 $("#selectedRows").val(rows);
				 var selectedRows= $("#selectedRows").val();
				window.parent.location.href="/inst/loanord/refundMgr!toPrintUnFreeze?selectedRows="+selectedRows;
			}
			function unFreeze(){
				var rows = $("#list").jqGrid('getGridParam','selarrrow');
				if(rows.length>1){
					alert('请选择单个账户。');
					 return false;
				}
                if( rows ==null||rows=='') {
                    alert('请选择一个账户。');
                    return false;
                }
                if( typeof(rows[0])=="undefined")rows.shift();
               
                if(rows[0]==rows[1])
                {
                    rows.shift();
                }
				 $("#selectedRows").val(rows);
				 var selectedRows= $("#selectedRows").val();
				window.parent.location.href="/inst/loanord/refundMgr!toUnFreeze?selectedRows="+selectedRows;
			}
			
			function upload(){
				var rows = $("#list").jqGrid('getGridParam','selarrrow');
				if(rows.length>1){
					alert('请选择单个账户。');
					 return false;
				}
                if( rows ==null||rows=='') {
                    alert('请选择一个账户。');
                    return false;
                }
                if( typeof(rows[0])=="undefined")rows.shift();
               
                if(rows[0]==rows[1])
                {
                    rows.shift();
                }
				 $("#selectedRows").val(rows);
				 var selectedRows= $("#selectedRows").val();
				window.parent.location.href="/inst/loanord/refundMgr!toUpload?selectedRows="+selectedRows;
			}
		</script> 
