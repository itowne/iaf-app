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
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;操作日志</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label">操作账号</td>
										<td class="data"><s:textfield id="loginName" name="loginName"  cssClass="data"></s:textfield></td>
										<td class="label">操作起始时间</td>
										<td class="data">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<s:textfield id="beginTime" name="beginTime" readonly="true"/>
											&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;
											<s:textfield id="endTime" name="endTime" readonly="true"/>
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
				       $("#endDate").datepicker("option","minDate",dateText);
				    }});
					$("#endDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
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
					url:'${ctx}/backstage/backstagelog/backstageLog!list',
					colNames:['序号','操作员账号','操作员角色','操作内容概述','操作结果','操作日期时间','操作IP地址','日志详情'],
					colModel:[
						{name:'traceNo',index:'traceNo',align:"center", width:70},
						{name:'loginName',index:'loginName',align:"center", width:40},
						{name:'roleNames',index:'roleNames',align:"center", width:40},
						{name:'operType',index:'operType', formatter:optionFormatter2 ,align:"center", width:50},
						{name:'operStat',index:'operStat',align:"center", width:70},
						{name:'genTime',index:'genTime',align:"center", width:70},
						{name:'ipAddr',index:'ipAddr',align:"center", width:70},
						{name:'operDetail',index:'operDetail', formatter:optionFormatter , align:"center", width:30},
					],
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
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/backstagelog/backstageLog!list?loginName='+loginName+'&beginTime='+beginTime+'&endTime='+endTime,page:1}).trigger("reloadGrid");
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewDetail('+index+');">查看</a>]';
			}
			
			function optionFormatter2(cellvalue, options, rowObject){
				var data = rowObject[3];
				return '['+data+']';
			}
			
			function viewDetail(index){
				window.location.href="${ctx}/backstage/backstagelog/backstageLog!viewOperLogs?index="+index;
			}
		</script>
			
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>
