<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body id="workframe">
		<div id="wrapper">
			<div id="location">
				<p>当前位置：我要借款&nbsp;&gt;&gt;&nbsp;借款申请</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
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
											
										<td class="label">借款额度</td>
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
			        	$("#region").html('<option value="">请选择市区</option>'); 
					}else{
					$.getJSON("/../province",{provinceCode:$(this).val()},
						function(myJSON){
							var myOptions='<option value="">请选择市区</option>';
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
			
			//关注机构时候申请产品
			var instId='${iinst}';
			if(instId!=null||instId!=""){
				$("#iinst option[value='"+instId+"']").attr("selected","selected");
			}
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				var rate = $("#rate").val();
				//var pdtName = $("#pdtName").val();
				var quota = $("#quota").val();
				var term = $("#term").val();
				var iinst = $("#iinst").val();
				var region =  $("#region").val();
				var iLoanPdt=$("#pdtList").val();
				if("请选择受理地区"==region||region==null)region="";
				jQuery("#list").jqGrid({
					url:'${ctx}/merch/merchProdReq!list?rate='+rate+'&quota='+quota+'&term='+term+'&iinst='+iinst+'&region='+region+'&iLoanPdt='+iLoanPdt,
					colNames:['产品名称','放贷机构','受理地区','借款额度','借款周期','最低利率(%)','最快放款时间(天)','申请统计(次)','操作'],
					colModel:[
						//{name:'iloanPdt',index:'iloanPdt',align:"center", width:30},
						{name:'pdtName',index:'pdtName',align:"center", width:70},
						{name:'instName',index:'instName',align:"center", width:70,resizable:true},
						{name:'region',index:'region',align:"center", width:50},
						{name:'minQuota',index:'minQuota',align:"center",formatter:optionFormatter3, width:50},
						{name:'minTerm',index:'minTerm',align:"center", width:50},
						{name:'rate',index:'rate',align:"center", formatter:rateFormatter, width:65},
						{name:'creditTerm',index:'creditTerm',align:"center", width:70},
						{name:'reqTotal',index:'reqTotal',formatter:optionFormatter2,align:"center", width:50},
						{name:'option',index:'option',formatter:optionFormatter,align:"center",width:90}
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"产品列表",
					gridComplete:function(){
						resetGridWidth();
					}
				}).navGrid("#pager");
			}
			
			function rateFormatter(cellvalue, options, rowObject){
				var data = rowObject[5];
				if(data==null){
					return "0%";
				}else{
					return data+"起";
				}
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
				$("#list").jqGrid('setGridParam',{url:'${ctx}/merch/merchProdReq!list?rate='+rate+'&quota='+quota+'&iinst='+iinst+'&provinceCode='+provinceCode+'&region='+encodeURI(region)+'&times='+times+'&creditTerm='+creditTerm+'&pdtId='+iLoanPdt+'&rateType='+rateType+'&minterm='+minterm+'&Maxterm='+Maxterm+'&MaxTermType='+MaxTermType+'&minTermType='+minTermType,page:1}).trigger("reloadGrid");
			}
			function optionFormatter2(cellvalue, options, rowObject){
				var data = rowObject[7];
				if("" == data){
					return '0';
				}
				return data;
			}
			function optionFormatter3(cellvalue, options, rowObject){
				var data = rowObject[3];
				return data+"万";
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var id = options.rowId;
				var pdt = "'"+rowObject[0]+"'";
				return '[<a href="JavaScript:viewProduct('+id+');">详情</a>]';
			}
			
			function viewProduct(no){
				window.location.href="${ctx}/merch/merchProdReq!detail?index="+no;
			}
			
			function showService(message){
				service("http://localhost:8080", "商户[<s:property value="#session['IAF_LOGIN_SESSION'].merch.merchName" />]", "贷款产品：[" + message + "]");
			}
			
		</script>
	</body>
