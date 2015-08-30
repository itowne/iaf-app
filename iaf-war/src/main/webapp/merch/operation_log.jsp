<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/template/mp/meta.jspf" %>
<html>
	<head>
		<n:head styles="mp-workframe" scripts="jquery"/>
		<n:ui includes="grid"/>
	</head>
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
    <p>当前位置：操作日志</p>
  </div>
  <div id="content">
    <h3 class="title"><span>查询条件</span></h3>
    <div class="search-bar">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">

				<tr>
				<td class="label">操作帐号：</td>
				<td class="data"><s:textfield id="loginName" name="loginName"></s:textfield></td>
				<td class="label">操作结果：</td>
				<td class="data">
				<select id="operType" name="operType">
				<option value="">请选择操作结果</option>
				<option value="SUCCESS">成功</option>
				<option value="FAIL">失败</option>
				</select></td>
				<td class="label">操作起始日期：</td>
				<td class="data">&nbsp;&nbsp;<s:textfield id="startDate" readonly="true"/> &nbsp;至&nbsp; <s:textfield id="endDate" readonly="true"/></td>
				</tr>
            </table>
            </td> 
          <td class="search-btn-td"><input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/></td>
        </tr>
      </table>
    </div>
    <h3 class="title"><span>查询结果</span>
      
    </h3>
    <div class="results" style="margin:10px auto;width:100%">
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
				$("#startDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
				       $("#endDate").datepicker("option","minDate",dateText);
			    }});
				$("#endDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			        $("#startDate").datepicker("option","maxDate",dateText);
			    }});
			});
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/merch/merchOperLog!list',
					colNames:['操作帐号','操作内容概述','操作结果','操作备注','操作日期',"日志详情"], 
					colModel:[
					   // {name:'traceNo',index:'traceNO',align:"center",width:70},
						{name:'loginName',index:'loginName',align:"center", width:50},
						//{name:'userName',index:'userName',align:"center", width:50},
						{name:'operType',index:'operType',align:"center", width:70},
						{name:'operStat',index:'operStat',align:"center", width:70},
						{name:'memo',index:'operResult',align:"center", width:70},
						{name:'genTime',index:'genTime',align:"center", width:70},
						//{name:'ipAddr',index:'ipAddr',align:"center", width:70},
						{name:'option',index:'option',formatter:optionFormatter,align:"center",width:70}
					
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					caption:"操作日志列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			
			function storeQuery(){
			
				
				var loginName = $('#loginName').val();
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				var operType=$("#operType").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/merch/merchOperLog!list?startDate='+startDate+'&endDate='+endDate+"&loginName="+loginName+'&operType='+operType,page:1}).trigger("reloadGrid");
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
			function viewProduct(no){
				
				window.location.href="${ctx}/merch/merchOperLog!operLogInfo?index="+no;
			}
			
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>