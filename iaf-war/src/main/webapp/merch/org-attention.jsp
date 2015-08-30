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
				<p>当前位置：我的关注&nbsp;&gt;&gt;&nbsp;关注的机构</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label">机构名称：</td>
										<td class="data"><s:select style="width:120px" id="instName" list="instList"  listValue="instName " listKey="instName" headerKey="" headerValue="请选择机构名称"></s:select></td>
										<td class="label">机构类型：</td>
										<td class="data">
											<select id="instNature" name="instNature">
												<option value="">请选择机构类型</option>
												<option value="银行">银行</option>
												<option value="信贷公司">信贷公司</option>
												<option value="个人">个人</option>
											</select>
										</td>
										<td class="label">业务受理地区：</td>
										<td class="data"><s:textfield id="acceptRegion" name="acceptRegion" /></td>
<!-- 										<td class="data"> -->
<%-- 											<s:select list="provMap" name="provinceCode" id="provMap" style="width:90px" headerKey="" headerValue="请选择省份"></s:select> --%>
<!-- 											<select name="region" id="region" style="width:90px"></select> -->
<!-- 										</td> -->
									</tr>
									<tr>
										<td class="label">放贷产品数量：</td>
										<td class="data"><s:textfield id="loanPdtCount" name="loanPdtCount" />&nbsp;</td>
										<td class="label">联系人：</td>
										<td class="data"><s:textfield id="contact" name="contact" /></td>
										<td class="label">联系电话：</td>
										<td class="data"><s:textfield id="contactPhone" name="contactPhone" /></td></td>
									</tr>
								</table>
							</td>
							<td class="search-btn-td"><button class="dark-btn" onclick="storeQuery();">查&nbsp;&nbsp;询</button></td>
						</tr>
					</table>
				</div>
				<h3 class="title"><span>查询结果</span></h3>
				<div  class="gridTable">
					<table id="list"></table>
					<div id="pager"></div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
// 			$(function(){
// 				getList();
// 				$("#provMap").change(function(){    
					
// 					if($("#provMap").val()==""){
// 						$("#region").empty();                  
// 			        	$("#region").html('<option value="">选择地区</option>');
// 					}else{
// 						$.getJSON("/../province",{provinceCode:$(this).val()},
// 						function(myJSON){
// 							var myOptions='<option value="">所有地区</option>';
// 							for(var i=0;i<myJSON.provinceList.length;i++){    
// 								myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
// 				             }   
// 				             $("#region").empty();                  
// 				             $("#region").html(myOptions);              
// 				       	});    
// 					}
// 			   	});
// 				$("#provMap").change();
// 			});
			$(function(){
				getList();
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				var instName = $("#instName").val();
				var instNature = $("#instNature").val();
// 				var region = $("#region").val();
				var acceptRegion = $("#acceptRegion").val();
				var loanPdtCount=$("#loanPdtCount").val();
				var contact = $("#contact").val();
				var contactPhone=$("#contactPhone").val();
				jQuery("#list").jqGrid({
					url:'${ctx}/merch/merchCollInst!list?instName='+instName+'&instNature='+instNature+'&acceptRegion='+acceptRegion+'&loanPdtCount='+loanPdtCount+'&contact='+contact+'&contactPhone='+contactPhone,
					colNames:['机构名称','机构类型','业务受理地区','放贷产品数量','联系人','联系电话','操作'],
					colModel:[
						//{name:'iinst',index:'iinst',align:"center", width:30},
						{name:'instName',index:'instName',align:"center", width:70},
						{name:'instNature',index:'instNature',align:"center", width:70},
						{name:'acceptRegion',index:'acceptRegion',align:"center", width:70},
						{name:'count',index:'count',align:"center",formatter:optionFormatter1,width:50},
						{name:'contact',index:'contact',align:"center", width:50},
						{name:'contactPhone',index:'contactPhone',align:"center",width:50},
						{name:'option',index:'option',align:"center",formatter:optionFormatter,width:90}
					],
					rownumbers:true,
					height:230,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"我关注的贷款机构列表",
					gridComplete:function(){
						resetGridWidth();
					}
				}).navGrid("#pager");
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var id = options.rowId;
				//var id = "'"+rowObject[0]+"'";
				return '[<a href="JavaScript:viewInst('+id+')">详情</a>] [<a href="JavaScript:moveMerchColl('+id+');">取消关注</a>]';
			}
			
			function viewInst(no){
				window.location.href="${ctx}/merch/merchCollInst!viewInst?index="+no;
			}
			
			function optionFormatter1(cellvalue, options, rowObject){
				var id = rowObject[6];
				var data = rowObject[3];
				if(data == "") {
					return '0';
				}else{
					return '<a href="JavaScript:myreq('+id+');">'+data+'</a>';
				}
			}
			
			//跳转代码
			function myreq(no){
				window.location.href="${ctx}/merch/merchProdReq?iinst="+no;
			}
			
			function moveMerchColl(no){
				if(confirm("是否确定撤销关注该贷款机构?")){
					window.location.href="${ctx}/merch/merchCollInst!move?index="+no;
				};
			}
			function storeQuery(){
				var instName = $("#instName").val();
				var instNature = $("#instNature").val();
// 				var region = $("#region").val();
				var acceptRegion = $("#acceptRegion").val();
				var loanPdtCount=$("#loanPdtCount").val();
				var contact = $("#contact").val();
				var contactPhone=$("#contactPhone").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/merch/merchCollInst!list?instName='+encodeURI(instName)+'&instNature='+encodeURI(instNature)+'&acceptRegion='+acceptRegion+'&loanPdtCount='+loanPdtCount+'&contact='+contact+'&contactPhone='+contactPhone,page:1}).trigger("reloadGrid");
			}
		</script>
</body>
</html>
