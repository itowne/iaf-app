<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
	<s:if test="hasActionMessages()"> 
	<s:iterator value="actionMessages"> 
	<script language="JavaScript"> 
	alert("<s:property escape="false"/>") 
	</script> 
	</s:iterator> 
	</s:if>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：贷后管理&nbsp;&gt;&gt;&nbsp;还款管理</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form action="refundMgr">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
											<tr>
												<td class="label">借贷编号</td>
												<td class="data"><s:textfield  name="loanOrdId"  cssClass="data"></s:textfield></td>
												<td class="label">借贷申请类型</td>
												<td class="data"><s:select list="@newland.iaf.base.model.dict.PdtType@values()" listKey="name()" listValue="desc" headerKey="" headerValue="请选择" cssClass="data" name="pdtType"></s:select></td>
												<td class="label"></td>
												<td class="data">
													
												</td>
											</tr>
											<tr>
												<td class="label">申请商户</td>
												<td class="data"><s:textfield name="merchName" cssClass="data"></s:textfield></td>
												<td class="label">商户类型</td>
												<td class="data"><s:select list="@newland.iaf.merch.model.MerchType@values()" listKey="name()" listValue="desc" headerKey="" headerValue="请选择"  cssClass="data" name="merchType"></s:select></td>
												<td class="label">借贷金额</td>
												<td class="data"><s:textfield cssClass="data"  name="loanAmount"></s:textfield></td>
											</tr>
											<tr>
												<td class="label">借贷期限</td>
												<td class="data"><s:textfield name="term"></s:textfield></td>
												<td class="label">申请日期</td>
												<td class="data"><s:textfield name="applyDate"></s:textfield></td>
												<td class="label">受理日期</td>
												<td class="data"><s:textfield name="acceptDate"></s:textfield></td>
											</tr>
										</table>
									</td>
									<td class="search-btn">
										&nbsp;&nbsp;
										<s:submit cssClass="dark-btn" method="queryHis" value="查    询"></s:submit> 
									</td>
								</tr>
					</table>
					</s:form>
				</div>
				<h3 class="title"><span>查询结果</span>&nbsp;&nbsp;&nbsp;<a href="${ctx}/inst/loanord/refundMgr" class="dark-btn">返回还款管理</a></h3>
				<div  style="margin:10px auto;width:100%">
							<table id="list"></table>
							<div id="pager"></div>
						</div>
			</div>
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
					url:'${ctx}/inst/loanord/refundMgr!listhis',
					colNames:['编号','产品类型','借贷产品','借贷金额','借款期限(月)','申请商户','商户类型','申请日期','审核期限','订单状态','操作'],
					colModel:[
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:70},
						{name:'pdtType',index:'pdtType',align:"center", width:40},
						{name:'pdtName',index:'pdtName',align:"center", width:50},
						{name:'quota',index:'region',align:"center", width:50},
						{name:'term',index:'term',align:"center", width:50},
						{name:'merchName',index:'merchName',align:"center", width:50},
						{name:'merchType',index:'merchType',align:"center", width:50},
						{name:'ordDate',index:'ordDate',align:"center", width:65},
						{name:'checkDate',index:'checkDate',align:"center", width:50},
						{name:'ordStat',index:'ordStat',align:"center",formatter:statFormatter, width:50},
						{name:'oper',index:'count',formatter:optionFormatter,align:"center", width:50},
					],
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					rownumbers:true,
					shrinkToFit:true,
					autowidth:true,
					caption:"订单列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewLoanOrd('+index+');">详情</a>]';
			}
			
			function viewLoanOrd(rowId){
				//window.location = "${ctx}/inst/loanord/refundMgr!viewLoanOrd?index=" + rowId;
				$("#index").val(rowId);
				$("#form").submit();
			}          
			function statFormatter(cellvalue, options, rowObject){
				if (cellvalue == "不受理" ||cellvalue == "审核未通过" || cellvalue == "未保存" || cellvalue == "逾期" || cellvalue == "欠款冻结中" || cellvalue == "欠款已冻结" || cellvalue == "受理过期" || cellvalue == "申请过期"){
					return "<div style='color:red'>" + cellvalue + "</div>";
				}
				return cellvalue;
			}

		</script>
		<s:form action="refundMgr!toNext" id="form" >
			<s:hidden id="index" name="index"></s:hidden>
		</s:form>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
