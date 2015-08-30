<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<jsp:include page="${ctx}/template/head.jsp" />
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 机构管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;系统日志</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label" style="width:60px;">操作账号</td>
										<td class="data" style="width:80px;"><s:textfield style="width:80px;" id="loginName" name="loginName"  cssClass="data"></s:textfield></td>
										<td class="label" style="width:60px;">操作结果：</td>
										<td class="data" style="width:120px;">
										<select id="operType" name="operType" style="width:120px;">
										<option value="">请选择操作结果</option>
										<option value="SUCCESS">成功</option>
										<option value="FAIL">失败</option>
										</select></td>
										<td class="label" style="width:60px;">操作IP地址</td>
										<td class="data" style="width:80px;"><s:textfield style="width:80px;" id="ipAddr" name="ipAddr"  cssClass="data"></s:textfield></td>
										<td class="label" style="width:60px;">操作起始时间</td>
										<td class="data" style="width:130px">
											<s:textfield id="beginTime" style="width:60px;" name="beginTime" readonly="true"/>至
											<s:textfield id="endTime" style="width:60px;" name="endTime" readonly="true"/>
										</td>
									</tr>
								</table>
							</td>
							<td class="search-btn">
								&nbsp;&nbsp;
								<input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/> 
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
				$("#beginTime").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			       $("#endTime").datepicker("option","minDate",dateText);
			    }});
				$("#endTime").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			        $("#beginTime").datepicker("option","maxDate",dateText);
			    }});
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/inst/operLog!list',
					colNames:['操作员账号','操作内容概述','操作结果','IP地址','操作日期','日志详情'],
					colModel:[
						//{name:'traceNo',index:'traceNo',align:"center", width:70},
						{name:'loginName',index:'loginName',align:"center", width:40},
						//{name:'roleNames',index:'roleNames',align:"center", width:40},
						{name:'operType',index:'operType', formatter:optionFormatter2 ,align:"center", width:50},
						{name:'operStat',index:'operStat',align:"center", width:70},
						{name:'ipAddr',index:'ipAddr',align:"center", width:70},
						{name:'genTime',index:'genTime',align:"center", width:70},
						{name:'operDetail',index:'operDetail', formatter:optionFormatter , align:"center", width:30},
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"日志记录列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			
			function storeQuery(){
				var loginName=$('#loginName').val();
				var beginTime=$('#beginTime').val();
				var endTime=$('#endTime').val();
				var operType=$("#operType").val();
				var ipAddr=$("#ipAddr").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/operLog!list?loginName='+loginName+'&beginTime='+beginTime+'&endTime='+endTime+'&operType='+operType+'&ipAddr='+ipAddr,page:1}).trigger("reloadGrid");
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewDetail('+index+');">查看</a>]';
			}
			
			function optionFormatter2(cellvalue, options, rowObject){
				var data = rowObject[1];
				return '['+data+']';
			}
			
			function viewDetail(index){
				window.location.href="${ctx}/inst/operLog!viewOperLogs?index="+index;
			}
		</script>
			
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>
