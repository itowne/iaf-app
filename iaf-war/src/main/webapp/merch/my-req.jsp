<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/mp/meta.jspf" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
	<head>
		<%@include file="/template/mp/head.jspf" %>
	</head>
	<body id="workframe">
		<div id="wrapper">
			<div id="location">
				<p>当前位置：我的申请</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label" >借贷编号</td>
										<td class="data"><s:textfield id="loanordid"/>&nbsp;</td>
										<td class="label">所属产品</td>
										<td class="data"><s:select id="pdt" list="loanPdtList"  listValue="pdtName" listKey="loadPdtId" headerKey="" headerValue="请选择产品名称"></s:select></td>
										<td class="label">申请受理机构</td>
										<td class="data"><s:select id="inst" list="instList"  listValue="instName" listKey="iinst" headerKey="" headerValue="请选机构名称"></s:select></td>
									</tr>
									<tr>
									<td class="label" >借款金额</td>
										<td class="data"><s:textfield id="quota" style="width:50px"/>&nbsp;(万元)至<s:textfield id="Maxquota" style="width:50px"/>(万元)</td>
										<td class="label">最后更新日期</td>
										<td class="data"><s:textfield id="updateDate"/></td>
									<td class="label">订单状态</td>
										<td class="data">
										<s:select list="@newland.iaf.base.model.dict.OrdStat@values()" listKey="name()" listValue="desc" headerKey="" headerValue="请选择订单状态 " cssClass="data" id="ordStat" name="ordStat"></s:select>
										</td>
									</tr>
									<!--  
									<tr>
										<td class="label">申请发布起始日期</td>
										<td class="data"><s:textfield id="startDate"/></td>
										<td class="label">申请发布终止日期</td>
										<td class="data"><s:textfield id="endDate"/></td>
									</tr>
									-->
								</table>
							</td>
							<td class="search-btn-td"><input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/></td>
						</tr>
					</table>
				</div>
				<h3 class="title"><span>查询结果</span></h3>
				<div class="gridTable">
					<table id="list"></table>
					<div id="pager"></div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				getList();
				$("#startDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			       $("#endDate").datepicker("option","minDate",dateText);
			    }});
				$("#endDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			        $("#startDate").datepicker("option","maxDate",dateText);
			    }});
				$("#updateDate").datepicker({changeMonth:true,changeYear:true});   
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/merch/merchMyReq!list',
					colNames:['借贷编号','所属产品','申请受理机构','借款金额','最后更新日期','订单状态','操作'],
					colModel:[
						{name:'loanPdtId',index:'loanPdtId',align:"center", width:87},
						{name:'pdtName',index:'pdtName',align:"center",formatter:optionFormatter2, width:70},
						{name:'instName',index:'instName',align:"center", width:110},
						{name:'wangyuanQuota',index:'wangyuanQuota',formatter:optionFormatterMoney,align:"center", width:70},
						{name:'updTime',index:'updTime',align:"center", width:70},
						{name:'ordStat.desc',index:'ordStat.desc',align:"center", formatter:statOptionFormatter,width:70},
						{name:'option',index:'option',formatter:optionFormatter,align:"center",width:90}
					],
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"申请列表",
					gridComplete:function(){
						resetGridWidth();
					}
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			function statOptionFormatter(cellvalue, options, rowObject){
				var data=rowObject[5];
				if(data=='作废'){
					return "<font style='color:red'>作废</font>";
				}else{
					return data;
				}
			}
			
			function storeQuery(){
				//if(isNaN($("#yearRate").val())){
					//alert("利率请填写数字!");
					//return false;
				//}
				if(isNaN($("#quota").val())){
					alert("借款金额请填写数字!");
					return false;
				}
				//if(isNaN($("#term").val())){
					//alert("还款期限请填写数字!");
					///return false;
				//}
				var yearRate = $("#yearRate").val();
				var quota = $("#quota").val();
				var term = $("#term").val();
				var ordStat=$('#ordStat').val();
				//var startDate = $('#startDate').val();
				//var endDate = $('#endDate').val();
				var loanordid = $("#loanordid").val();
				var Maxquota=$("#Maxquota").val();
				var updateDate = $("#updateDate").val();
				var pdt = $("#pdt").val();
				var instid=$("#inst").val();
				
				$("#list").jqGrid('setGridParam',{url:'${ctx}/merch/merchMyReq!list?yearRate='+yearRate+'&quota='+quota+'&term='+term+'&ordStat='+ordStat+'&loanordid='+loanordid+'&Maxquota='+Maxquota+'&updateDate='+updateDate+'&pdt='+pdt+'&instid='+instid,page:1}).trigger("reloadGrid");
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewProduct('+index+');">详情</a>]';
			}
			
			function optionFormatter2(cellvalue, options, rowObject){
				var data = rowObject[1];
				if(data == ""){
					return "----";
				}else{
					return data;
				}
				
			}
			
			function optionFormatterMoney(cellvalue, options, rowObject){
				var data = rowObject[3];
				return data+"万";
			}
			function viewProduct(no){
				window.location.href="${ctx}/merch/merchMyReq!processInfo?index="+no;
			}
			
		</script> 
</body>
</html>