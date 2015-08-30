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
				<p>当前位置：我的关注&nbsp;&gt;&gt;&nbsp;关注的产品</p>
			</div>
			<div id="content">
				<h3 class="title">
					<span>查询条件</span>
				</h3>
				<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label">产品名称</td>
										<td class="data">
										<s:select style="width:120px" id="pdtList" list="pdtList"  listValue="pdtName " listKey="iloanPdt " headerKey="" headerValue="请选择产品名称"></s:select>
										<!--<s:textfield id="pdtName" name="pdtName"/>--></td>
										<td class="label">放贷机构</td>
										<td class="data"><s:select style="width:120px" id="iinst" list="instList"  listValue="instName " listKey="iinst " headerKey="" headerValue="请选择机构名称"></s:select></td>
										<td class="label">贷款额度</td>
										<td class="data"><s:textfield style="width:30px" id="quota" name="quota" cssClass="rate"/>&nbsp;(万元)</td>
									</tr>
									<tr>
										<td class="label">借款利率</td>
										<td class="data">
										<select id="rateType" name="rateType" style="width:80px;">
											<option value="">利率类型</option>
											<option value="DAY">日利率</option>
											<option value="MONTH">月利率</option>
											<option value="YEAR">年利率</option>
										</select>
										<s:textfield id="rate" style="width:30px" name="rate" cssClass="rate"/>&nbsp;(%)</td>
										<td class="label">最快放款时间</td>
										<td class="data"><s:textfield style="width:30px" id="creditTerm" name="creditTerm" cssClass="rate"/>&nbsp;(天)</td>
										<td class="label">申请次数</td>
										<td class="data"><s:textfield style="width:30px" id="times" name="times" cssClass="rate"/>&nbsp;(次)</td>
									</tr>
									<tr>
										<td class="label">受理地区</td>
										<td class="data">
											<s:select list="provMap" name="provinceCode" id="provMap" style="width:90px" headerKey="" headerValue="请选择省份"></s:select>
											<select name="region" id="region" style="width:90px"></select>
										</td>
										<td class="label">借款周期</td>
										<td class="data" colspan="3">
										<s:textfield id="minterm" name="minterm"style="width:40px" />
										<select id="minTermType" name="minTermType" style="width:90px;" >
											<option value="">请选择单位</option>
											<option value="DAY">天</option>
											<option value="MONTH">个月</option>
											<option value="YEAR">年</option>
											</select>
										至<s:textfield id="Maxterm" name="Maxterm" style="width:40px"/>
										<select id="MaxTermType" name="MaxTermType" style="width:90px;" >
											<option value="">请选择单位</option>
											<option value="DAY">天</option>
											<option value="MONTH">个月</option>
											<option value="YEAR">年</option>
										</select>
										</td>
									</tr>
								</table>
							</td>
							<td class="search-btn-td">
								<input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/> 
							</td>
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
				$("#provMap").change(function(){    
					
					if($("#provMap").val()==""){
						$("#region").empty();                  
			        	$("#region").html('<option value="">选择地区</option>');
					}else{
						$.getJSON("/../province",{provinceCode:$(this).val()},
						function(myJSON){
							var myOptions='<option value="">所有地区</option>';
							for(var i=0;i<myJSON.provinceList.length;i++){    
								myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
				             }   
				             $("#region").empty();                  
				             $("#region").html(myOptions);              
				       	});    
					}
			   	});
				$("#provMap").change();
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/merch/merchCollPdt!list',
					colNames:['产品名称','放贷机构','受理地区','贷款额度（万元）','贷款周期','最低利率（%）','最快放款时间（天）','申请统计','操作'],
					colModel:[
						//{name:'iloanPdt',index:'iloanPdt',align:"center", width:30},
						{name:'pdtName',index:'pdtName',align:"center", width:70},
						{name:'instName',index:'instName',align:"center", width:70,resizable:true},
						{name:'region',index:'region',align:"center", width:70},
						{name:'minQuota',index:'minQuota',align:"center",formatter:optionFormatter5, width:70},
						{name:'minTerm',index:'minTerm',align:"center", width:50},
						{name:'rate',index:'rate',align:"center", formatter:rateFormatter, width:70},
						{name:'creditTerm',index:'creditTerm',align:"center", width:70},
						{name:'reqTotal',index:'reqTotal',formatter:optionFormatter3,align:"center", width:50},
						{name:'option',index:'option',formatter:optionFormatter,align:"center",width:90}
					],
					rownumbers:true,
					height:230,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"我关注的贷款产品列表",
					gridComplete:function(){
						resetGridWidth();
					}
				}).navGrid("#pager");
			}
			
// 			function optionFormatter2(cellvalue, options, rowObject){
// 				var data = rowObject[7];
// 				if("GROUNDING" == data){
// 					return "上架";
// 				}else if("UNDERCARRIAGE" == data){
// 					return "下架";
// 				}
// 			}
			
			function rateFormatter(cellvalue, options, rowObject){
				var data = rowObject[5];
				if(data==null){
					return "0%";
				}else{
					return data+"起";
				}
			}
			
			function optionFormatter5(cellvalue, options, rowObject){
				var data = rowObject[3];
				return data+"万";
			}
			
			function optionFormatter3(cellvalue, options, rowObject){
				var data = rowObject[7];
				if("" == data){
					return '0';
				}
				return data;
			}
						
			function optionFormatter(cellvalue, options, rowObject){
				var id = options.rowId;
				//var id = "'"+rowObject[0]+"'";
				return '[<a href="JavaScript:viewProduct('+id+');">详情</a>] [<a href="JavaScript:moveMerchColl('+id+');">取消关注</a>]';
			}
			function viewProduct(no){
				window.location.href="${ctx}/merch/merchCollPdt!viewProduct?index="+no;
			}
			function moveMerchColl(no){
				if(confirm("是否确定撤销关注该贷款产品?")){
					window.location.href="${ctx}/merch/merchCollPdt!move?index="+no;
				};
			}
			function storeQuery(){
				var rate = $("#rate").val();
				//var pdtName = $("#pdtName").val();
				var quota = $("#quota").val();
				//var term = $("#term").val();
				var iinst = $("#iinst").val();
				var region =  $("#region").val();
				var iLoanPdt=$("#pdtList").val();
				
				var provinceCode =  $("#provMap").val();
				if(isNaN(rate)){
					alert("利率请填写数字!");
					return false;
				}
				
				var times=$("#times").val();
				if(isNaN(times)){
					alert("申请次数请填写数字!");
					return false;
				}
				var creditTerm=$("#creditTerm").val();
				if(isNaN(creditTerm)){
					alert("最快放款时间请填写数字!");
					return false;
				}
				var minterm=$("#minterm").val();
				var Maxterm=$("#Maxterm").val();
				var rateType=$("#rateType").val();
				var MaxTermType=$("#MaxTermType").val();
				var minTermType=$("#minTermType").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/merch/merchCollPdt!list?rate='+rate+'&quota='+quota+'&iinst='+iinst+'&provinceCode='+provinceCode+'&region='+encodeURI(region)+'&times='+times+'&creditTerm='+creditTerm+'&pdtId='+iLoanPdt+'&rateType='+rateType+'&minterm='+minterm+'&Maxterm='+Maxterm+'&MaxTermType='+MaxTermType+'&minTermType='+minTermType,page:1}).trigger("reloadGrid");
			}
		</script>
</body>
</html>