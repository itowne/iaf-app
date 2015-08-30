<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<jsp:include page="${ctx}/template/head.jsp" />
<html>
<body>
	<div id="workframe-wrapper" class="clearfix">
		<div id="location">
			<p>当前位置：我的关注&nbsp;&gt;&gt;&nbsp;关注的客户</p>
		</div>
		<div id="content">
			<h3 class="title">
				<span>查询条件</span>
			</h3>
			<div class="search-bar">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0" class="data-table">
							<tr>
								<td class="label">商户名称</td>
								<td class="data"><s:textfield name="merchName" /></td>
								<td class="label">商户性质</td>
								<td class="data">
									<select name="merchType" id="merchType">
										<option value="">请选择商户性质</option>
										<option value="UNION_PAY">银联商户</option>
										<option value="GOLD_SHOPKEEPER">金掌柜商户</option>
										<option value="GENERAL">普通商户</option>
									</select>
								</td>
								<td class="label">商户所属省市</td>
								<td class="data"><s:select list="provMap"
										name="provinceCode" id="provMap" listKey="key"
										listValue="value" headerKey="" headerValue="请选择省份"></s:select>
									<select name="region" id="region"></select></td>
							</tr>
							<tr>
								<td class="label">联系人</td>
								<td class="data"><s:textfield id="contract" name="contract" /></td>
								<td class="label">联系电话：</td>
								<td class="data"><s:textfield id="contractTel"
										name="contractTel" /></td>
								</td>
								<td class="label"></td>
								<td></td>
							</tr>
							
						</table></td>
						<td class="search-btn-td"><input type="button" class="dark-btn" onclick="storeQuery();" value="查&nbsp;&nbsp;询" /></td>
					</tr>
				</table>
			</div>
			<h3 class="title">
				<span>查询结果</span>
			</h3>
			<div style="margin: 10px auto; width: 100%">
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
});

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
$(function(){
	$(window).resize(function(){
		$("#list").setGridWidth($(window).width()*0.95);
	});
});
function getList(){
	jQuery("#list").jqGrid({
		url:'${ctx}/inst/instCollMerch!list',
		colNames:['商户名称','商户性质','商户所属省市','联系人','联系电话','操作'],
		colModel:[
			//{name:'imerch',index:'imerch',align:"center", width:30},
			{name:'merchName',index:'merchName',align:"center", width:70},
			{name:'merchType',index:'merchType',formatter:optionFormatter2,align:"center", width:70},
			{name:'regAddr',index:'regAddr',align:"center", width:50},
			{name:'contract',index:'contract',align:"center", width:50},
			{name:'contractTel',index:'contractTel',align:"center", width:50},
			{name:'option',index:'option',formatter:optionFormatter,align:"center",width:90}
		],
		rownumbers:true,
		height:230,
		rowNum:10,
		pager: '#pager',
		repeatitems: false,
		shrinkToFit:true,
		autowidth:true,
		caption:"我关注的商户列表"
	});
	jQuery("#list").jqGrid('navGrid','#pager');
}
			
	function optionFormatter2(cellvalue, options, rowObject){
		var data = rowObject[1];
		if("GOLD_SHOPKEEPER" == data){
			return "金掌柜";
		}else if("UNION_PAY" == data){
			return "银联商户";
		}else if("GENERAL" == data){
			return "普通商户";
		}
	}
	function optionFormatter(cellvalue, options, rowObject){
		var id = options.rowId;
		//var id = "'"+rowObject[0]+"'";
		return '[<a href="JavaScript:viewDetail('+id+');">详情</a>][<n:instAuthA href="javascript:moveInstColl('+id+');" authCode="WDGZ_GZKH_SC" value="取消关注"></n:instAuthA>]';
	}
	function viewDetail(no){
		window.location.href="${ctx}/inst/instCollMerch!viewDetail?index="+no;
	}
	function moveInstColl(no){
		if(confirm("是否确定撤销关注该商户?")){
			window.location.href="${ctx}/inst/instCollMerch!move?index="+no;
		};
	}
	function storeQuery(){
		var provinceCode =  $("#provMap").val();
		var region =  $("#region").val();
		var contract = $("#contract").val();
		var contractTel=$("#contractTel").val();
		var merchName = $("#merchName").val();
		var companySize = $("#companySize").val();
		var merchType = $("#merchType").val();
		if("请选择商户类型"==merchType)merchType="";
		$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/instCollMerch!list?merchType='+merchType+'&region='+encodeURI(region)+'&provinceCode='+provinceCode+'&contract='+contract+'&contractTel='+contractTel+'&merchName='+encodeURI(merchName)+'&companySize='+encodeURI(companySize),page:1}).trigger("reloadGrid");
	}
</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>