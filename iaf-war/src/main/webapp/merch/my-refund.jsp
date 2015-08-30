<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/mp/meta.jspf" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
	<head>
		<%@include file="/template/mp/head.jspf" %>
	</head>
	<body id="workframe">
		<div id="wrapper">
			<div id="location">
				<p>当前位置：我要还款</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
								<tr>
									<td class="label">借款编号</td>
									<td class="data"><s:textfield id="loanOrdId"/>&nbsp;</td>
									<td class="label">所属产品</td>
									<td class="data"><s:select style="width:120px" id="pdtList" list="pdtList"  listValue="pdtName " listKey="iloanPdt " headerKey="" headerValue="请选择产品名称"></s:select></td>
									<td class="label">申请受理机构</td>
									<td class="data"><s:select style="width:120px" id="instList" list="instList"  listValue="instName " listKey="iinst " headerKey="" headerValue="请选择机构名称"></s:select></td>
								</tr>
								<tr>
									<td class="label">借款金额</td>
									<td class="data"><s:textfield style="width:50px" id="quota"/>&nbsp;(万元)&nbsp;<s:textfield style="width:50px" id="Maxquota"/>(万元)</td>
									<td class="label">最近一期还款时间</td>
									<td class="data">
										<s:select list="#{'':'请选择还款年份','2013':'2013年','2014':'2014年','2015':'2015年','2016':'2016年','2017':'2017年','2018':'2018年','2019':'2019年','2020':'2020年','2021':'2021年','2022':'2022年','2023':'2023年','2024':'2024年','2025':'2025年','2026':'2026年','2027':'2027年','2028':'2028年','2029':'2029年','2030':'2030年'}" cssClass="data" id="year" name="year"></s:select>
										<s:select list="#{'':'请选择还款月份','1':'1月','2':'2月','3':'3月','4':'4月','5':'5月','6':'6月','7':'7月','8':'8月','9':'9月','10':'10月','11':'11月','12':'12月'}" cssClass="data" id="term" name="term"></s:select>
									</td>
									<td class="label">订单状态</td>
									<td class="data">
										<s:select list="#request.status" listKey="name()" listValue="desc" headerKey="" headerValue="请选择订单状态" cssClass="data" id="ordStat" name="ordStat"></s:select>
									</td>
								</tr>
							</table>
						</td>
						<input type="hidden" name="flag" id="flag" value="<s:property value="flag"/>"/>
						<input type="hidden" name="debit" id="debit" value="<s:property value="debit"/>"/>
						<input type="hidden" name="debit" id="ten" value="<s:property value="ten"/>"/>
						<td class="search-btn-td"><input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/></td>
					</tr>
				</table>
			</div>
			<h3 class="title"><span>查询结果</span>&nbsp;&nbsp;<font style="color:#29609F;font-size:15">您目前共借款：<font style="color:red"><s:property value="creditTotalCount"/>笔</font>；合计借款金额：<font style="color:red"><s:property value="creditTotal"/>万元</font>,已还款<font style="color:red"><s:property value="refundTotal"/>万元</font>，目前剩余欠款<font style="color:red"><s:property value="surplus"/>万元</font>。如需还款，请点击以下的借款订单进行还款操作</font></h3>
			<div class="gridTable">
				<table id="list"></table>
				<div id="pager"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			$(function(){
				getList();
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				var flag=$("#flag").val();
				var debit=$("#debit").val();
				var ten=$("#ten").val();
				jQuery("#list").jqGrid({
					url:'${ctx}/merch/merchMyRefund!list?flag='+flag+'&debit='+debit+'&ten='+ten,
					colNames:['借款编号','所属产品','申请受理机构','借款金额','最近一期还款时间','订单状态','操作'],
					colModel:[
						{name:'loanPdtId',index:'loanPdtId',align:"center", width:85},
						//{name:'pdtType.desc',index:'pdtType.desc',align:"center", width:50},
						{name:'pdtName',index:'pdtName',align:"center",formatter:optionFormatter2, width:70},
						{name:'instName',index:'instName',align:"center",width:70},
						{name:'wangyuanQuota',index:'wangyuanQuota',align:"center", width:70},
						//{name:'term',index:'term',align:"center", width:70},
						//{name:'rate',index:'rate',align:"center", width:70},
						//{name:'ordDate',index:'ordDate',align:"center", width:70},
						{name:'updTime',index:'updTime',align:"center", width:70},
						{name:'ordStat.desc',index:'ordStat.desc',align:"center", width:70},
						{name:'option',index:'option',formatter:optionFormatter,width:90,align:"center"}
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
				}).navGrid("#pager");
			}
			
			function storeQuery(){
				var loanOrdId = $("#loanOrdId").val();
				var quota = $("#quota").val();
				var year = $("#year").val();
				var term = $("#term").val();
				if(year==''&&term!=''){
					alert('选择月份前请先选择年份');
					return false;
				}
				var ordStat=$('#ordStat').val();
				
				var pdtId=$("#pdtList").val();
				var instId=$("#instList").val();
				var Maxquota=$("#Maxquota").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/merch/merchMyRefund!list?loanOrdId='+loanOrdId+'&quota='+quota+'&year='+year+'&term='+term+'&ordStat='+ordStat+'&pdtId='+pdtId+'&instId='+instId+'&Maxquota='+Maxquota,page:1}).trigger("reloadGrid");
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				var stat = rowObject[5];
				if(stat=="已还清"){
					return '[<a href="JavaScript:viewDetail('+index+');">详情</a>]';
				}else{
				return '[<a href="JavaScript:viewDetail('+index+');">还款</a>]';
				}
			}
			
			function optionFormatter2(cellvalue, options, rowObject){
				var data = rowObject[1];
				if(data == ""){
					return "(非放贷产品)";
				}else{
					return data;
				}
				
			}
			
			function optionFormatterMoney(cellvalue, options, rowObject){
				var data = rowObject[3];
				return data;
			}
			
			function viewDetail(no){
				window.location.href="${ctx}/merch/merchMyRefund!viewDetail?index="+no;
			}
		</script> 
</body>
</html>