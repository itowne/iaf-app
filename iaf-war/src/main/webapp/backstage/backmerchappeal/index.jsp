<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<body>
	<div id="workframe-wrapper" class="clearfix">
		<div id="location">
			<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;经营数据报告申诉</p>
		</div>
		<div id="content">
			<h3 class="title"><span>查询条件</span></h3>
			<div class="search-bar">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
								<tr>
									<td class="label">商户名称</td>
									<td class="data"><s:textfield  name="merchName" id="merchName" cssClass="data"></s:textfield></td>
									<td class="label">汇卡商户号</td>
									<td class="data"><s:textfield  name="merchNo" id="merchNo" cssClass="data"></s:textfield></td>
									<td class="label">商户所属省市</td>
									<td class="data">
										<s:select list="provMap" name="provinceCode" id="provMap" style="width:90px" listKey="key" listValue="value" headerKey="" headerValue="请选择省份"></s:select>
										<select name="region" id="region" style="width:90px"></select>
									</td>
									<td class="label">申诉联系人</td>
									<td class="data"><s:textfield  name="appealMan" id="appealMan" cssClass="data"></s:textfield></td>
									
									
								</tr>
								<tr>
									<td class="label">申诉联系电话</td>
									<td class="data"><s:textfield  name="appealPhone" id="appealPhone" cssClass="data"></s:textfield></td>
									<td class="label">申诉时间</td>
									<td class="data">
										<s:textfield style="width:80px" name="beginDate" id="beginDate"></s:textfield>
										-
										<s:textfield style="width:80px" name="endDate" id="endDate"></s:textfield>
									</td>
									<td class="label">处理情况</td>
									<td class="data"><s:select list="@newland.iaf.base.model.dict.AppealState@values()" listKey="name()" listValue="desc" headerKey="" headerValue="请选择订单状态 " cssClass="data" id="appealState" name="appealState"></s:select></td>
									<td colspan="2"></td>
								</tr>
							</table>
						</td>
						<td>&nbsp;&nbsp;<input type="button" class="dark-btn" value="查询" onclick="storeQuery()"/>&nbsp;&nbsp;</td>
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
		$("#beginDate").datepicker({changeYear:true,changeMonth:true});
		$("#endDate").datepicker({changeYear:true,changeMonth:true});
	});
	
	$(function(){
		$(window).resize(function(){
			$("#list").setGridWidth($(window).width()*0.95);
		});
	});
	
	function getList(){
		jQuery("#list").jqGrid({
			url:'${ctx}/backstage/backMerchAppeal!list',
			colNames:['商户名称','汇卡商户号','商户所属省市','申诉联系人','申诉联系电话','申诉时间','处理情况','操作'],
			colModel:[
				{name:'merchName',index:'merchName',align:"center", width:60},    
				{name:'merchNo',index:'merchNo',align:"center", width:60},
				{name:'provinceCity',index:'provinceCity',align:"center", width:60},
				{name:'appealMan',index:'appealMan',align:"center", width:60},
				{name:'appealPhone',index:'appealPhone',align:"center", width:60},
				{name:'genTime',index:'genTime',align:"center", width:60},
				{name:'appealState',index:'appealState',align:"center",width:60},
				{name:'oper',index:'oper',align:"center", width:45, formatter:operFormatter},
			],
			height:240,
			rowNum:10,
			pager: '#pager',
			repeatitems: false, 
			shrinkToFit:true,
			autowidth:true,
			caption:"报告申诉列"
		});
		jQuery("#list").jqGrid('navGrid','#pager');
	}

	function operFormatter(cellvalue, options, rowObject){
		var index = options.rowId;
		return '[<a href="JavaScript:detail('+index+');">详情</a>]';
	}
	
	function detail(index){
		window.location.href="${ctx}/backstage/backMerchAppeal!detail?index="+index;
	}
	
	function storeQuery(){
		var merchName = $("#merchName").val();
		var merchNo = $("#merchNo").val();
		var appealMan = $("#appealMan").val();
		var appealPhone = $("#appealPhone").val();
		var beginDate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		var appealState = $("#appealState").val();
		var provinceCode =  $("#provMap").val();
		var region =  $("#region").val();
		$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/backMerchAppeal!list?merchName='+merchName+'&merchNo='+merchNo+'&appealMan='+appealMan+'&provinceCode='+provinceCode+'&region='+encodeURI(region)+'&appealPhone='+appealPhone+'&beginTime='+beginDate+'&endTime='+endDate+'&appealState='+appealState,page:1}).trigger("reloadGrid");
	}
</script>

<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
