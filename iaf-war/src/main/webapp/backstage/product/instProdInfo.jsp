<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />

<html>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：贷款产品&nbsp;&gt;&gt;&nbsp;产品管理</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar" >
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label">产品名称</td>
										<td class="data"><s:textfield id="pdtName" name="pdtName" style="width:150px"/></td>
										<td class="label">贷款金额</td>
										<td class="data"><s:textfield id="minQuota" name="minQuota" style="width:50px"/>&nbsp;(万元)至<s:textfield id="MaxQuota" name="MaxQuota" style="width:50px"/>(万元)</td>
										<td class="label">贷款周期</td>
										<td class="data"><s:textfield id="minterm" name="minterm"style="width:40px" />
										<select id="minTermType" name="minTermType" style="width:80px;" class="validate[required]">
								<option value="">选择单位</option>
								<option value="DAY">天</option>
								<option value="MONTH">个月</option>
								<option value="YEAR">年</option>
							</select>
										&nbsp;至<s:textfield id="Maxterm" name="Maxterm" style="width:40px"/>
										<select id="maxTermType" name="maxTermType" style="width:80px;" class="validate[required]">
								<option value="">选择单位</option>
								<option value="DAY">天</option>
								<option value="MONTH">个月</option>
								<option value="YEAR">年</option>
							</select>
										</td>
									</tr>
									<tr>
										<td class="label">贷款利率</td>
										<td class="data"><select id="rateType" name="rateType" style="width:80px;">
								<option value="">利率类型</option>
								<option value="DAY">日利率</option>
								<option value="MONTH">月利率</option>
								<option value="YEAR">年利率</option>
							</select><s:textfield id="MinyearRate" name="MinyearRate" style="width:40px"/>&nbsp;%至<s:textfield id="MaxyearRate" name="MaxyearRate" style="width:40px"/>%</td>
										<td class="label">最快放款时间</td>
										<td class="data"><s:textfield id="fastday" name="fastday" style="width:150px"/></td>
										<td class="label">申请统计(次)</td>
										<td class="data"><s:textfield id="times" name="times" style="width:150px"/></td>
									</tr>
									<tr>
										<td class="label">受理地区</td>
										<td class="data">
										<s:select list="provMap" name="provinceCode" id="provMap" listKey="key" listValue="value" headerKey="" headerValue="请选择省份"></s:select>
										<select name="region" id="region"></select>
										</td>
										<td class="label">产品状态</td>
										<td class="data">
										<s:select id="pdtStatus" list="#{'GROUNDING':'上架','UNDERCARRIAGE':'下架'}" listKey="key" listValue="value"  headerKey="" headerValue="产品状态"></s:select></td>
										<td class="label">&nbsp;</td>
										<td class="data">&nbsp;</td>
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
				<div style="float:right;padding: 0"><n:HcAuthButton cssClass="dark-btn"  value="发布新产品" onclick="productAdd();" authCode="HDCP_XZ"/></div>
				<h3 class="title"><span>查询结果</span>
				</h3>
				<div  style="margin:10px auto;width:100%">
					<table id="list"></table>
					<div id="pager"></div>
				</div>
				<div align="center"><input type="button" class="dark-btn"  value="返&nbsp;&nbsp;回" onclick="back();"/></div>
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

				$("#provMap").change(function(){
					if($("#provMap").val()==""){
						$("#region").empty();                  
			        	$("#region").html('<option value="">请选择</option>'); 
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
					url:'${ctx}/backstage/product/instProduce!list',
					colNames:['产品名称','贷款最低金额','贷款最高金额','贷款最短周期','贷款最长周期','最低利率(%)','最快放贷时间','申请统计(次)','受理地区','产品状态','操作'],
					colModel:[
						//{name:'iloanPdt',index:'iloanPdt',align:"center", width:30},
						{name:'pdtName',index:'pdtName',align:"center", width:70},
						{name:'minQuota',index:'minQuota',formatter:optionFormatter2,align:"center", width:70},
						{name:'maxQuota',index:'maxQuota',formatter:optionFormatter3,align:"center", width:70},
						{name:'minTerm',index:'minTerm',align:"center", formatter:optionFormatter4,width:65},
						{name:'maxTerm',index:'maxTerm',align:"center", formatter:optionFormatter5,width:65},
						{name:'rate',index:'rate',align:"center", formatter:rateFormatter, width:65},
						{name:'creditTerm',index:'creditTerm',align:"center",width:50},
						//{name:'introduce',index:'introduce',align:"center", width:60},
						{name:'reqTotal',index:'reqTotal',formatter:optionFormatter7,align:"center", width:50},
						{name:'area',index:'area',align:"center", width:70},
						{name:'pdtStatus.desc',index:'pdtStatus.desc',align:"center", width:40},
						{name:'option',index:'option',formatter:optionFormatter,align:"center",width:55}
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"产品列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			
			function rateFormatter(cellvalue, options, rowObject){
				var data = rowObject[5];
				if(data==null){
					return "0%";
				}else{
					return data+"起";
				}
			}
			
			function optionFormatter2(cellvalue, options, rowObject){
				var data = rowObject[1];
				return data+"万";
			}
			function optionFormatter3(cellvalue, options, rowObject){
				var data = rowObject[2];
				return data+"万";
			}
			function optionFormatter4(cellvalue, options, rowObject){
				var data = rowObject[3];
				return data;
			}
			function optionFormatter5(cellvalue, options, rowObject){
				var data = rowObject[4];
				return data;
			}
			function optionFormatter6(cellvalue, options, rowObject){
				var data = rowObject[5];
				return data;
			}
			
			function optionFormatter7(cellvalue, options, rowObject){
				var data = rowObject[7];
				if("" == data){
					return '0';
				}
				return data;
			}
			function optionFormatter(cellvalue, options, rowObject){
				var id = options.rowId;
				//var id = "'"+rowObject[0]+"'";
				var data = rowObject[9];
				if("上架" == data){
					return '[<a href="JavaScript:viewProduct('+id+');">详情</a>] [<n:HcAuthA authCode="HDCP_SJ" href="JavaScript:downStore('+id+');" value="下架"></n:HcAuthA>]';
				}else{
					return '[<a href="JavaScript:viewProduct('+id+');">详情</a>] [<n:HcAuthA authCode="HDCP_XJ" href="JavaScript:upStore('+id+');" value="上架"></n:HcAuthA>]';
				}
			}
			
			function productAdd(){
				window.location="${ctx}/backstage/product/instProduce!productAdd";
			}
			
			function viewProduct(no){
				window.location.href="${ctx}/backstage/product/instProduce!viewProduct?index="+no;
			}
			
			function downStore(no){
				if(confirm("是否确定下架该借贷产品?")){
					window.location.href="${ctx}/backstage/product/instProduce!downStore?index="+no;
				}
			}
			
			function upStore(no){
				if(confirm("是否确定上架该借贷产品?")){
					window.location.href="${ctx}/backstage/product/instProduce!upStore?index="+no;
				}
			}
			
			function back(){
				window.location.href="/backstage/product/instPro";
			}
			
			function storeQuery(){
				var yearRate = $("#yearRate").val();
				var pdtName = $("#pdtName").val();
				var pdtStatus = $("#pdtStatus").val();
				var provinceCode =  $("#provMap").val();
				var region =  $("#region").val();
				//if(isNaN(yearRate)){
				//	alert("利率请填写数字!");
					//return false;
				//}
				
				var minQuota=$("#minQuota").val();
				var MaxQuota=$("#MaxQuota").val();

				
				var minterm=$("#minterm").val();
				var Maxterm=$("#Maxterm").val();

				
				var MinyearRate=$("#MinyearRate").val();
				var MaxyearRate=$("#MaxyearRate").val();

				
				var fastday=$("#fastday").val();
				var times=$("#times").val();
				var rateType=$("#rateType").val();
				
				var minTermType=$("#minTermType").val();
				var maxTermType=$("#maxTermType").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/product/instProduce!list?yearRate='+yearRate+'&pdtName='+encodeURI(pdtName)+'&pdtStatus='+pdtStatus+'&provinceCode='+provinceCode+'&region='+encodeURI(region)+'&minQuota='+minQuota+'&MaxQuota='+MaxQuota+'&minterm='+minterm+'&Maxterm='+Maxterm+'&MinyearRate='+MinyearRate+'&MaxyearRate='+MaxyearRate+'&fastday='+fastday+'&times='+times+'&rateType='+rateType+'&minTermType='+minTermType+'&maxTermType='+maxTermType,page:1}).trigger("reloadGrid");
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>