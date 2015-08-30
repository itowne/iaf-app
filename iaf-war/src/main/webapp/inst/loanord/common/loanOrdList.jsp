<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<div id="content">
<h3 style="line-height:42px;text-align:center;font-size:15px;">借贷信用</h3>
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
					url:'${ctx}/inst/loanord/loanOrdList!list',
					colNames:['订单日期','借贷金额','已还金额','借款周期','放贷机构','状态','操作'],
					colModel:[
						{name:'ordDate',index:'pdtName',align:"center", width:50},
						{name:'quota',index:'region',align:"center", width:50},
						{name:'reciveAmount',index:'term',align:"center", width:50},
						{name:'term',index:'merchName',align:"center", width:45},
						{name:'instName',index:'merchType',align:"center", width:45},
						{name:'ordStat',index:'ordStat',align:"center",formatter:statFormatter, width:50},
						{name:'oper',index:'count',formatter:optionFormatter,align:"center", width:50},
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,					
					caption:"订单列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			function serialFormatter(cellvalue, options, rowObject){
				return eval(new Number(options.rowId) + 1);
			}
			function statFormatter(cellvalue, options, rowObject){
				if (cellvalue == "不受理" ||cellvalue == "审核未通过" || cellvalue == "未保存" || cellvalue == "逾期" || cellvalue == "欠款冻结中" || cellvalue == "欠款已冻结" || cellvalue == "受理过期" || cellvalue == "申请过期"){
					return "<div style='color:red'>" + cellvalue + "</div>";
				}
				return cellvalue;
			}
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewLoanOrd('+index+');">详情</a>]';
			}
			
			function viewLoanOrd(rowId){
				window.location = "${ctx}/inst/loanord/loanOrdList!viewOrd?index=" + rowId;
			}

			parent.window.curTab = "loanOrdList";
		</script>
</div>

