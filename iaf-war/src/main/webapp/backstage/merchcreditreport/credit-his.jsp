<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/template/head.jsp" />
<html>
<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 商户管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<base target="_self"/>
</head>
<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;经营数据报告管理</p>
			</div>
			<div id="content" class="report">
				<h3 class="title"><span>我的经营数据报告</span></h3>
				<div class="search-bar">
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">经营数据报告生成时间范围</td>
							<td>
							<s:form action="backMerchCredit" namespace="/backstage/merchcreditreport">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<s:radio onclick="javascript:dateRangeChange()" name="dateRange"  list="#{'PRE_MONTH':'前一个月', 'PRE_THREE_MONTH':'前三个月', 'PRE_HALF_YEAR':'前六个月'}"></s:radio>
										</td>
										<td align="right">
											<s:submit cssClass="dark-btn"  method="queryNewest" value="查询最新报告"></s:submit>
											<s:if test="first==false">
											<s:submit cssClass="dark-btn" method="saveReport" value="保存报告"></s:submit>
											</s:if>
											<s:submit cssClass="dark-btn" method="queryHis" value="查询历史报告"></s:submit>
										</td>
									</tr>
								</table>
							</s:form>
							</td>
						</tr>
						<tr>
							<td class="label">报告统计日期范围</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<span id="dateRange"></span>
										</td>
										<td align="right">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<h3 class="title"><span>查询结果</span></h3>
				<div  style="margin:10px auto;width:100%">
					<table id="list"></table>
					<div id="pager"></div>
				</div>
			</div>
		</div>
		<s:form target="" id="viewForm" action="/backstage/merchcreditreport/backMerchCredit!viewReport">
			<input type="hidden" id="index1" name="index"></input>
		</s:form>
		<s:form target="" id="downloadForm" action="/backstage/merchcreditreport/backMerchCredit!download">
			<input type="hidden" id="index2" name="index"></input>
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
				getList();
				dateRangeChange();
			});
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/backstage/merchcreditreport/backMerchCredit!list',
					colNames:['经营数据报告编号','报告开始日期','报告结束日期','报告生成日期','操作'],
					colModel:[
						{name:'reportId',index:'reportId',align:"center"},
						{name:'beginDate',index:'beginDate',align:"center"},
						{name:'endDate',index:'endDate',align:"center"},
						{name:'genTime',index:'genTime',align:"center"},
						{name:'oper',index:'oper',align:"center",formatter:optionFormatter, width:100},
					],
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"报告列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
	
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:download('+index+');">下载</a>]&nbsp;&nbsp;[<a href="JavaScript:viewReport('+index+');">查看</a>]';
			}
			
			function viewReport(rowId){
				$("#index1").val(rowId);
				$("#viewForm").submit();
			}
			
			function download(rowId){
				$("#index2").val(rowId);
				$("#downloadForm").submit();
			}
			function dateRangeChange(){
				var New=document.getElementsByName("dateRange");
				var strNew;
				for(var i=0;i<New.length;i++)
				{
					if(New.item(i).checked){
						strNew = New.item(i).getAttribute("value"); 
						var beginDate = new Date();
						var endDate = new Date();
						if(strNew=='PRE_MONTH'){
							//前一个月
							beginDate.setMonth(beginDate.getMonth()-1, 1);
							endDate.setMonth(endDate.getMonth(),0);
						}else if(strNew=='PRE_THREE_MONTH'){
							//前三个月
							beginDate.setMonth(beginDate.getMonth()-3, 1);
							endDate.setMonth(endDate.getMonth(),0);
						}else if(strNew=='PRE_HALF_YEAR'){
							//前六个月
							beginDate.setMonth(beginDate.getMonth()-6, 1);
							endDate.setMonth(endDate.getMonth(),0);
						}
						strNew = beginDate.toLocaleDateString()+'---'+endDate.toLocaleDateString();
						$("#dateRange").empty();                  
			        	$("#dateRange").html(strNew); 
					}
				}
			}
		</script> 
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>