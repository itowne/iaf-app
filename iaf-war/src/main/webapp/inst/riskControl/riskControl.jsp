<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
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
				<p>当前位置：贷后管理&nbsp;&gt;&gt;&nbsp;风险监控</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form action="riskControl" namespace="/inst/loanord">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
										<tr>
												<td class="label">借贷编号</td>
												<td class="data"><s:textfield id="loanOrdId" style="width:120px" name="loanOrdId"  cssClass="data"></s:textfield></td>
												<td class="label">申请类型</td>
												<td class="data">
												<select id="pdtType" name="">
													<option value="">请选择申请类型</option>
													<option value="INST_PROD">产品申请</option>
													<option value="MERCH_PROD">普通申请</option>
												</select></td>
												<td class="label">借贷产品</td>
												<td class="data"><s:select style="width:120px" id="pdtList" list="pdtList"  listValue="pdtName " listKey="iloanPdt " headerKey="" headerValue="请选择产品名称"></s:select></td>
											</tr>
											<tr>
												<td class="label">贷款金额</td>
												<td class="data"><s:textfield cssClass="data" style="width:40px" id="loanAmount" name="loanAmount"/>(万元)&nbsp;至&nbsp;<s:textfield style="width:40px" cssClass="data" id="endloanAmount" name="endloanAmount"/>(万元)</td>
												<td class="label">贷款周期</td>
												<td class="data"><s:textfield style="width:40px" name="term" id="term"/>至<s:textfield style="width:40px" name="maxTerm" id="maxTerm"/>
												<s:select list="@newland.iaf.base.model.dict.TermType@values()" listKey="name()" listValue="desc" headerKey="" headerValue="请选择单位" cssClass="data" name="termType" id="termType"></s:select>
												</td>
												<td class="label">贷款利率</td>
												<td class="data">
													<select id="rateType" name="rateType" style="width:80px;">
											<option value="">利率类型</option>
											<option value="DAY">日利率</option>
											<option value="MONTH">月利率</option>
											<option value="YEAR">年利率</option>
											</select>
												<s:textfield cssClass="data" style="width:40px" id="minRate" name="minRate"/>
												&nbsp;至&nbsp;<s:textfield style="width:40px" cssClass="data" id="maxRate" name="maxRate"/>%
											</td>
											</tr>
											<tr>
												<td class="label">申请商户</td>
												<td class="data"><s:textfield id="merchName" name="merchName" cssClass="data"></s:textfield></td>
												<td class="label">申请日期</td>
												<td class="data"><s:textfield style="width:90px" id="jzbeginDate" name="jzbeginDate" />&nbsp;至&nbsp;<s:textfield style="width:90px" id="jzendDate" name="jzendDate" /></td>
												<td class="label">订单状态</td>
												<td class="data">
													<s:select id="ordStat" list="#request.status" listKey="name()" listValue="desc" headerKey="" headerValue="请选择" cssClass="data" name="ordStat"></s:select>
												</td>
											</tr>
										</table>
									</td>
									<td class="search-btn">
										&nbsp;&nbsp;
										<input type="button" class="dark-btn" onclick="storeQuery()" value="查    询"/>
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
		$("#jzbeginDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
		       $("#jzendDate").datepicker("option","minDate",dateText);
		    }});
			$("#jzendDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
		        $("#jzbeginDate").datepicker("option","maxDate",dateText);
		    }});
		
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
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/inst/loanord/riskControl!list',
					colNames:['借贷编号','申请类型','贷款产品','贷款金额','贷款周期','贷款利率(%)','申请商户','申请日期','订单状态','操作'],
					colModel:[
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:70},
						{name:'pdtType',index:'pdtType',align:"center",formatter:optionFormatter1, width:40},
						{name:'pdtName',index:'pdtName',align:"center", width:50},
						{name:'quota',index:'region',align:"center",formatter:Formatter1, width:50},
						{name:'term',index:'term',align:"center", width:50},
						{name:'rate',index:'rate',align:"center", width:50},
						{name:'merchName',index:'merchName',align:"center", width:50},
						{name:'ordDate',index:'ordDate',align:"center", width:65},
						{name:'ordStat',index:'ordStat',align:"center",formatter:statFormatter, width:50},
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

			function Formatter1(cellvalue, options, rowObject){
				var data = rowObject[3];
				return data+"万";
			}
			
			function optionFormatter1(cellvalue, options, rowObject){
				var data = rowObject[1];
				if(data=="竟投"){
					return "普通申请";
				}else if(data=="产品"){
					return "产品申请";
				}
				return data;
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewLoanOrd('+index+');">风险详情</a>]';
			}
			
			function viewLoanOrd(rowId){
				//window.location = "${ctx}/inst/loanord/planUpload!viewLoanOrd?index=" + rowId;
				$("#index").val(rowId);
				$("form").submit();
			}          
			function statFormatter(cellvalue, options, rowObject){
				if (cellvalue == "不受理" ||cellvalue == "审核未通过" || cellvalue == "未保存" || cellvalue == "逾期" || cellvalue == "欠款冻结中" || cellvalue == "欠款已冻结" || cellvalue == "受理过期" || cellvalue == "申请过期"){
					return "<div style='color:red'>" + cellvalue + "</div>";
				}
				return cellvalue;
			}

			
			function storeQuery(){
				if(isNaN($("#loanOrdId").val())){
					alert("借款编号请填写数字!");
					return false;
				}
				if(isNaN($("#term").val())){
					alert("借款期限请填写数字!");
					return false;
				}

				var loanOrdId = $("#loanOrdId").val();
				var merchName = $("#merchName").val();
				var term = $("#term").val();
				var termType=$("#termType").val();
				var pdtType=$("#pdtType").val();
				var pdtId=$("#pdtList").val();
				
				var endloanAmount=$("#endloanAmount").val();
				var jzbeginDate=$("#jzbeginDate").val();
				var jzendDate=$("#jzendDate").val();
				var maxTerm = $("#maxTerm").val();
				var rateType = $("#rateType").val();
				var minRate=$("#minRate").val();
				var maxRate=$("#maxRate").val();
				var loanAmount=$("#loanAmount").val();
				var ordStat=$("#ordStat").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/loanord/riskControl!list?loanOrdId='+loanOrdId+'&merchName='+encodeURI(merchName)+'&term='+term+'&termType='+termType+'&pdtType='+pdtType+'&pdtId='+pdtId+'&endloanAmount='+endloanAmount+'&jzbeginDate='+jzbeginDate+'&jzendDate='+jzendDate+'&maxTerm='+maxTerm+'&rateType='+rateType+'&minRate='+minRate+'&maxRate='+maxRate+'&loanAmount='+loanAmount+'&ordStat='+ordStat,page:1}).trigger("reloadGrid");
				}
		</script>
		<s:form action="riskControl!riskDetail" namespace="/inst/loanord" id="form" >
			<s:hidden id="index" name="index"></s:hidden>
		</s:form>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
