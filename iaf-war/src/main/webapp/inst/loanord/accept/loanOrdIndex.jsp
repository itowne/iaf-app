<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：借贷受理&nbsp;&gt;&gt;&nbsp;受理申请</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
											<tr>
												<td class="label">借贷编号</td>
												<td class="data"><s:textfield id="loanOrdId" style="width:120px" name="loanOrdId"  cssClass="data"></s:textfield></td>
												<td class="label">申请类型</td>
												<td class="data">
												<s:select id="pdtType" list="@newland.iaf.base.model.dict.PdtType@values()" listKey="name()" listValue="desc" headerKey="" headerValue="请选择申请类型" cssClass="data" name="pdtType"></s:select>
												<!--<select id="pdtType">
													<option value="">请选择申请类型</option>
													<option value="INST_PROD">产品申请</option>
													<option value="MERCH_PROD">普通申请</option>
												</select>-->
												</td>
												<td class="label">借贷产品</td>
												<td class="data"><s:select style="width:120px" id="pdtList" list="pdtList"  listValue="pdtName " listKey="iloanPdt " headerKey="" headerValue="请选择产品名称"></s:select></td>
											</tr>
											<tr>
												<td class="label">贷款金额</td>
												<td class="data"><s:textfield cssClass="data" style="width:50px" id="loanAmount" name="loanAmount"/>(万元)&nbsp;至&nbsp;<s:textfield style="width:50px" cssClass="data" id="endloanAmount" name="endloanAmount"/>(万元)</td>
												<td class="label">贷款周期</td>
												<td class="data"><s:textfield name="term" style="width:50px" id="term"/>至<s:textfield style="width:50px" name="endterm" id="endterm"/>
												<select id="termType" name="termType" style="width:120px;" class="validate[required]">
												<option value="">请选择单位</option>
												<option value="DAY">天</option>
												<option value="MONTH">个月</option>
												<option value="YEAR">年</option>
												</select>
												</td>
												<td class="label">申请商户</td>
												<td class="data"><s:textfield id="merchName" name="merchName" cssClass="data"></s:textfield></td>
											</tr>
											<tr>
												<td class="label">申请日期</td>
												<td class="data" ><s:textfield style="width:90px" id="beginDate" name="beginDate" />&nbsp;至&nbsp;<s:textfield style="width:90px" id="endDate" name="endDate" /></td>
												<td class="label">截止受理日期</td>
												<td class="data"><s:textfield style="width:90px" id="jzbeginDate" name="jzbeginDate" />&nbsp;至&nbsp;<s:textfield style="width:90px" id="jzendDate" name="jzendDate" /></td>
												<td class="label">订单状态</td>
												<td class="data">
													<s:select id="ordStat" list="#request.status" listKey="name()" listValue="desc" headerKey="" headerValue="请选择订单状态" cssClass="data" name="ordStat"></s:select>
												</td>
											</tr>
										</table>
									</td>
									<td class="search-btn">
										&nbsp;&nbsp;
										<input type="button" class="dark-btn" onclick="storeQuery();" value="查  询"/>
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
				$("#beginDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			       $("#endDate").datepicker("option","minDate",dateText);
			    }});
				$("#endDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			        $("#beginDate").datepicker("option","maxDate",dateText);
			    }});
				
				$("#jzbeginDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
				       $("#jzendDate").datepicker("option","minDate",dateText);
				    }});
					$("#jzendDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
				        $("#jzbeginDate").datepicker("option","maxDate",dateText);
				    }});
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				var pdtType = $("#pdtType").val();
				jQuery("#list").jqGrid({
					url:'${ctx}/inst/loanord/prodAccept!list?pdtType='+pdtType,
					colNames:['借贷编号','申请类型','贷款产品','贷款金额','贷款周期','申请商户','申请日期','截止受理日期','订单状态','操作'],
					colModel:[
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:70},
						{name:'pdtType',index:'pdtType',align:"center", width:50},
						{name:'pdtName',index:'pdtName',align:"center", width:50},
						{name:'quota',index:'region',align:"center", width:50},
						{name:'term',index:'term',align:"center", width:50},
						{name:'merchName',index:'merchName',align:"center", width:70},
						//{name:'merchType',index:'merchType',align:"center", width:45},
						{name:'ordDate',index:'ordDate',align:"center", width:45},
						{name:'expireDate',index:'expireDate',align:"center", width:45},
						{name:'ordStat',index:'ordStat',align:"center",formatter:statFormatter, width:50},
						{name:'oper',index:'count',formatter:optionFormatter,align:"center", width:50},
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"订单列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			function statFormatter(cellvalue, options, rowObject){
				if (cellvalue == "不受理" ||cellvalue == "审核未通过" || cellvalue == "未保存" || cellvalue == "逾期" || cellvalue == "欠款冻结中" || cellvalue == "欠款已冻结" || cellvalue == "受理过期" || cellvalue == "申请过期"){
					return "<div style='color:red'>" + cellvalue + "</div>";
				}
				return cellvalue;
			}
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewLoanOrd('+index+');">详情</a>]';
			}
			
			function viewLoanOrd(rowId){
				//window.location = "${ctx}/inst/loanord/prodAccept!viewLoanOrd?index=" + rowId;
				//$("#index").val(rowId);
				//$("form").submit();
				window.location = "${ctx}/inst/loanord/prodAccept!toNext?index="+rowId;
			}

			
			function storeQuery(){
				if(isNaN($("#loanAmount").val())){
					alert("金额请填写数字!");
					return false;
				}
				if(isNaN($("#term").val())){
					alert("期限请填写数字!");
					return false;
				}

				var loanAmount = $("#loanAmount").val();
				var term = $("#term").val();
				var loanOrdId = $("#loanOrdId").val();
				//var loanPdtName=$("#loanPdtName").val();
				var ordStat=$("#ordStat").val();
				var merchName=$("#merchName").val();
				var merchType=$("#merchType").val();
				var beginDate=$('#beginDate').val();
				var endDate=$('#endDate').val();
				
				var pdtId=$("#pdtList").val();
				var pdtType = $("#pdtType").val();
				var endloanAmount=$("#endloanAmount").val();
				var jzbeginDate=$("#jzbeginDate").val();
				var jzendDate=$("#jzendDate").val();
				var endterm = $("#endterm").val();
				
				var termType=$("#termType").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/loanord/prodAccept!list?pdtId='+pdtId+'&merchType='+merchType+'&merchName='+encodeURI(merchName)+'&loanAmount='+loanAmount+'&term='+term+'&loanOrdId='+loanOrdId+'&ordStat='+ordStat+'&beginDate='+beginDate+"&endDate="+endDate+'&pdtType='+pdtType+'&endloanAmount='+endloanAmount+'&jzbeginDate='+jzbeginDate+'&jzendDate='+jzendDate+'&endterm='+endterm+'&termType='+termType,page:1}).trigger("reloadGrid");
			}
		</script>
		<!--  
		<s:form action="prodAccept!toNext" id="form" >
			<s:hidden id="index" name="index"></s:hidden>
		</s:form>
		-->
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
