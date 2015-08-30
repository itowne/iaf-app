<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html>
<head>
	<n:ui includes="form" />
</head>
	<body>

		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：订单审核&nbsp;&gt;&gt;&nbsp;</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form action="prodAudit" id="prodAuditForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label">借贷编号</td>
										<td class="data"><s:textfield style="width:100px" id="loanOrdId" name="loanOrdId"  cssClass="data"></s:textfield></td>
										<td class="label">申请类型</td>
										<td class="data"><s:select id="pdtType" style="width:130px" list="@newland.iaf.base.model.dict.PdtType@values()" listKey="name()" listValue="desc" headerKey="" headerValue="请选择申请类型" cssClass="data" name="pdtType"></s:select></td>
										<td class="label">贷款产品</td>
										<td class="data"><s:select style="width:120px" id="pdtList" list="pdtList"  listValue="pdtName " listKey="iloanPdt " headerKey="" headerValue="请选择产品名称"></s:select></td>
									</tr>
									<tr>
										<td class="label">贷款金额</td>
										<td class="data"><input type="text" id="minloanAmount" style="width:50px" class="data,validate[custom[number]]"  name="loanAmount" />
										(万元)至<input type="text" style="width:50px" id="maxloanAmount" class="data,validate[custom[number]]"  name="loanAmount" />(万元)</td>
										<td class="label">贷款周期</td>
										<td class="data"><s:textfield style="width:50px" id="minTerm" name="term" cssClass="validate[custom[integer]]"></s:textfield>至
										<s:textfield name="term" style="width:50px" id="maxTerm" cssClass="validate[custom[integer]]"/>
										<select id="termType" name="termType" style="width:120px;" class="validate[required]">
												<option value="">请选择单位</option>
												<option value="DAY">天</option>
												<option value="MONTH">个月</option>
												<option value="YEAR">年</option>
												</select>
										</td>
										<td class="label">申请商户</td>
										<td class="data"><s:textfield style="width:100px" id="merchName" name="merchName" cssClass="data"/></td>
									</tr>
									<tr>
										<td class="label">受理日期</td>
										<td class="data"><s:textfield style="width:80px" id="minacceptDate" name="acceptDate" cssClass="validate[custom[date]]"/>至
										<s:textfield  style="width:80px" id="maxacceptDate" name="maxacceptDate" cssClass="validate[custom[date]]"/></td>
										<td class="label">截止审核日期</td>
										<td class="data"><s:textfield style="width:80px" id="minapplyDate" name="applyDate" cssClass="validate[custom[date]]"/>至
										<s:textfield  style="width:80px" id="maxapplyDate" name="maxapplyDate" cssClass="validate[custom[date]]"/></td>
										<td class="label">订单状态</td>
										<td class="data">
											<s:select list="#request.status" id="ordStat" listKey="name()" listValue="desc" headerKey="" headerValue="请选择订单状态" cssClass="data" name="ordStat"></s:select>
										</td>
									</tr>
								</table>
							</td>
							<td class="search-btn">
								&nbsp;&nbsp;
								<!--<s:submit cssClass="dark-btn" method="list" value="查  询" onclick="doSubmit();"></s:submit>--> 
								<input type="button" class="dark-btn" onclick="storeQuery()" value="查 询"/>
							</td>
						</tr>
					</table>
					</s:form>
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
				//$("#prodAuditForm").validationEngine();
				
				$("#applyDate,#acceptDate").datepicker({changeYear:true,changeMonth:true});
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
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/inst/loanord/prodAudit!list',
					colNames:['借贷编号','申请类型','贷款产品','贷款金额','贷款周期','申请商户','受理日期','截止审核日期','订单状态','操作'],
					colModel:[
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:70},
						{name:'pdtType',index:'pdtType',align:"center", width:45},
						{name:'pdtName',index:'pdtName',align:"center", width:45},
						{name:'quota',index:'region',align:"center", width:45},
						{name:'term',index:'term',align:"center", width:40},
						{name:'merchName',index:'merchName',align:"center", width:50},
						//{name:'merchType',index:'merchType',align:"center", width:45},
						//{name:'ordDate',index:'ordDate',align:"center", width:45},
						{name:'acceptDate',index:'acceptDate',align:"center", width:45},
						{name:'expireDate',index:'expireDate',align:"center", width:45},
						{name:'ordStat',index:'ordStat',align:"center",formatter:statFormatter, width:45},
						{name:'oper',index:'count',formatter:optionFormatter,align:"center", width:50},
					],
					height:240,
					rownumbers:true,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"订单列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			function doSubmit(){
				if (!$("#prodAuditForm").validationEngine("validate")) {
					return false;
				}
			}
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewLoanOrd('+index+');">详情</a>]';
			}
			
			function viewLoanOrd(rowId){
				//window.location = "${ctx}/inst/loanord/prodAudit!viewLoanOrd?index=" + rowId;
				$("#index").val(rowId);
				$("#form").submit();
			}
			function statFormatter(cellvalue, options, rowObject){
				if (cellvalue == "不受理" ||cellvalue == "审核未通过" || cellvalue == "未保存" || cellvalue == "逾期" || cellvalue == "欠款冻结中" || cellvalue == "欠款已冻结" || cellvalue == "受理过期" || cellvalue == "申请过期"){
					return "<div style='color:red'>" + cellvalue + "</div>";
				}
				return cellvalue;
			}
			
			function storeQuery(){
				var loanOrdId=$("#loanOrdId").val();
				var pdtType=$("#pdtType").val();
				var pdtId=$("#pdtList").val();
				var minloanAmount=$("#minloanAmount").val();
				if(isNaN(minloanAmount)){
					alert("贷款金额请填写数字!");
					return false;
				}
				var maxloanAmount=$("#maxloanAmount").val();
				if(isNaN(maxloanAmount)){
					alert("贷款金额请填写数字!");
					return false;
				}
				var minTerm=$("#minTerm").val();
				if(isNaN(minTerm)){
					alert("贷款周期请填写数字!");
					return false;
				}
				var maxTerm=$("#maxTerm").val();
				if(isNaN(maxTerm)){
					alert("贷款金额请填写数字!");
					return false;
				}
				var merchName=$("#merchName").val();
				var minacceptDate=$("#minacceptDate").val();
				var maxacceptDate=$("#maxacceptDate").val();
				var minapplyDate=$("#minapplyDate").val();
				var maxapplyDate=$("#maxapplyDate").val();
			 	var ordStat=$("#ordStat").val();
			 	var termType=$("#termType").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/loanord/prodAudit!list?loanOrdId='+loanOrdId+'&pdtType='+pdtType+'&pdtId='+pdtId+'&minloanAmount='+minloanAmount+'&maxloanAmount='+maxloanAmount+'&minTerm='+minTerm+'&maxTerm='+maxTerm+'&merchName='+merchName+'&minacceptDate='+minacceptDate+'&maxacceptDate='+maxacceptDate+'&minapplyDate='+minapplyDate+'&maxapplyDate='+maxapplyDate+'&ordStat='+ordStat+'&termType='+termType,page:1}).trigger("reloadGrid");
			}
			$("#minacceptDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			       $("#maxacceptDate").datepicker("option","minDate",dateText);
			    }});
				$("#maxacceptDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			        $("#minacceptDate").datepicker("option","maxDate",dateText);
			    }});
			
				$("#minapplyDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
				       $("#maxapplyDate").datepicker("option","minDate",dateText);
				    }});
					$("#maxapplyDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
				        $("#minapplyDate").datepicker("option","maxDate",dateText);
				    }});
		</script>
		
		<s:form action="prodAudit!toNext" id="form" >
			<s:hidden id="index" name="index"></s:hidden>
		</s:form>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
