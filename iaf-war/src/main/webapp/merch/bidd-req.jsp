<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/mp/meta.jspf" %>
<!doctype html>
<html>
	<head>
		<%@include file="/template/mp/head.jspf" %>
	</head>
	<body id="workframe">
		<div id="wrapper">
			<div id="location">
				<p>当前位置：我要借款&nbsp;&gt;&gt;&nbsp;借款发布</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<input type="hidden" name="merchName" id="merchName" value="<s:property value='merchName'/>"/>
					<input type="hidden" name="industry" id="industry" value="<s:property value='industry'/>"/>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label" >借款金额</td>
										<td class="data"><s:textfield id="quota" style="width:50px"/>&nbsp;(万元)&nbsp;至&nbsp;<s:textfield id="Maxquota" style="width:50px"/>(万元)</td>
										<td class="label">借款周期</td>
										<td class="data"><s:textfield id="term" style="width:40px"/>&nbsp;至&nbsp;<s:textfield id="Maxterm" style="width:40px"/>
										<select id="termType" name="termType" style="width:120px;" class="validate[required]">
										<option value="">请选择单位</option>
										<option value="DAY">天</option>
										<option value="MONTH">个月</option>
										<option value="YEAR">年</option>
									</select>
										</td>
										<td class="label" >利率</td>
										<td class="data">
										<select id="rateType" name="rateType" style="width:80px;">
										<option value="">利率类型</option>
										<option value="DAY">日利率</option>
										<option value="MONTH">月利率</option>
										<option value="YEAR">年利率</option>
										</select>
										<s:textfield id="yearRate" style="width:50px"/>&nbsp;%至&nbsp;<s:textfield id="MaxyearRate" style="width:50px"/>%</td>
									</tr>
									<tr>
										<td class="label">状态</td>
										<td class="data"><select id="bidStat"><option value="" selected="selected">请选择申请状态</option><option value="1">正常</option><option value="0">撤销</option></select></td>
										<td class="label">申请受理次数</td>
										<td class="data"><s:textfield id="times" style="width:50px"/>&nbsp;(次)&nbsp;至&nbsp;<s:textfield id="maxTimes" style="width:50px"/>(次)</td>
										<td class="label">&nbsp;</td>
										<td class="data">&nbsp;</td>
									</tr>
								</table>
							</td>
							<td class="search-btn-td">
								<button class="dark-btn" onclick="storeQuery()">查&nbsp;&nbsp;询</button>
							</td>
						</tr>
					</table>
				</div>
				<div style="float:right;padding: 0"><button class="dark-btn" onclick="request()">发布新借款</button></div>
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
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				if(isNaN($("#yearRate").val())){
					alert("利率请填写数字!");
					return false;
				}
				if(isNaN($("#quota").val())){
					alert("借款金额请填写数字!");
					return false;
				}
				if(isNaN($("#term").val())){
					alert("还款期限请填写数字!");
					return false;
				}
				var yearRate = $("#yearRate").val();
				var quota = $("#quota").val();
				var term = $("#term").val();
				var bidStat=$('#bidStat').val();
				
				var Maxquota=$('#Maxquota').val();
				var Maxterm=$('#Maxterm').val();
				var MaxyearRate=$("#MaxyearRate").val();
				var times=$("#times").val();
				var maxTimes=$("#maxTimes").val();
				jQuery("#list").jqGrid({
					url:'${ctx}/merch/merchDebitBid!list?yearRate='+yearRate+'&quota='+quota+'&term='+term+'&bidStat='+bidStat+'&Maxquota='+Maxquota+'&Maxterm='+Maxterm+'&MaxyearRate='+MaxyearRate+'&times='+times+'&maxTimes='+maxTimes,
					colNames:['借款金额','借款周期','利率','状态','申请受理情况(次)','操作'],
					colModel:[
					   // {name:'idebitBid',index:'idebitBid',align:"center",width:70},
						{name:'wangyuanQuota',index:'wangyuanQuota',align:"center",formatter:optionFormatterMoney, width:100},
						{name:'term',index:'term',align:"center",width:100},
						{name:'yearRate',index:'yearRate',align:"center",width:75},
						{name:'bidStat',index:'bidStat',align:"center",formatter:optionFormatterBitStat,width:100},
						{name:'acceptTotal',index:'acceptTotal',align:"center",formatter:optionFormatterAcceptStute,width:100},
						{name:'operate',index:'operate',formatter:optionFormatter,align:"center",width:100}
					],
					rownumbers:true,
					height:230,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"竞投申请列表",
					gridComplete:function(){
						resetGridWidth();
					}
				}).navGrid("#pager");
			}
			
			function storeQuery(){
				if(isNaN($("#yearRate").val())){
					alert("利率请填写数字!");
					return false;
				}
				if(isNaN($("#quota").val())){
					alert("借款金额请填写数字!");
					return false;
				}
				if(isNaN($("#term").val())){
					alert("还款期限请填写数字!");
					return false;
				}
				var yearRate = $("#yearRate").val();
				var quota = $("#quota").val();
				var term = $("#term").val();
				var bidStat=$('#bidStat').val();
				//var startDate=$('#startDate').val();
				//var endDate=$('#endDate').val();
				var Maxquota=$('#Maxquota').val();
				
				var Maxterm=$('#Maxterm').val();
				var MaxyearRate=$("#MaxyearRate").val();
				var times=$("#times").val();
				var maxTimes=$("#maxTimes").val();
				
				var rateType = $("#rateType").val();
				var termType=$("#termType").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/merch/merchDebitBid!list?yearRate='+yearRate+'&quota='+quota+'&term='+term+'&bidStat='+bidStat+'&Maxquota='+Maxquota+'&Maxterm='+Maxterm+'&MaxyearRate='+MaxyearRate+'&times='+times+'&maxTimes='+maxTimes+'&rateType='+rateType+'&termType='+termType,page:1}).trigger("reloadGrid");
			}
			// 发布新借款
			function request(){
				var merchName = $("#merchName").val();
				var industry = $("#industry").val();
				if(merchName == "--" || industry == "--"){
					parent.jDialog("补充商户基本信息","${ctx}/merch/merchDebitBid!infoCheck?merchName="+merchName+"&industry="+industry,600,450,false);
				}else{
					window.location.href="${ctx}/merch/merchDebitBid!debitBidAdd.action";
				}
			}
			
			function optionFormatterRemainTime(cellvalue, options, rowObject){
				return "待定";
			}
			
			function optionFormatterAcceptStute(cellvalue, options, rowObject){
				var id = rowObject[5];
				var data = rowObject[4];
				if(data == "") {
					return '[<a href="JavaScript:acceptDetail('+id+');">0次</a>]';
				}else{
					return '[<a href="JavaScript:acceptDetail('+id+');">'+data+'次</a>]';
				}
			}
			
			function optionFormatterMoney(cellvalue, options, rowObject){
				var data = rowObject[0];
				return data+"万";
			}
			function optionFormatterBitStat(cellvalue, options, rowObject){
				var data = rowObject[3];
				if(data=="撤销"){
					return "<div style='color:red'>"+data+"</div>";
				}else{
					return data;
				}
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var id = options.rowId;
				//var id = "'"+rowObject[0]+"'";
				if(rowObject[3]=="NORMAL"){
				return '[<a href="JavaScript:viewDetail('+id+');">详情</a>] [<a href="JavaScript:cancelMerchDebitBid('+id+');">撤销</a>]';
				}else{
					return '[<a href="JavaScript:viewDetail('+id+');">详情</a>]';
				}
			}
			function viewDetail(no){
				window.location.href="${ctx}/merch/merchDebitBid!viewDebitBit?index="+no;
			}
			
			function acceptDetail(no){
				window.location.href="${ctx}/merch/merchMyReq!bebidIndex?idebitBid="+no;
			}
			
			function cancelMerchDebitBid(no){
				if(confirm("是否要撤销该竞投申请?")){
					window.location.href="${ctx}/merch/merchDebitBid!cancel?index="+no;
				}
			}
		</script>
	</body>
</html>