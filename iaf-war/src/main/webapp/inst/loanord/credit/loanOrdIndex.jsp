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
				<p>当前位置：放款处理&nbsp;&gt;&gt;&nbsp;</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form action="prodCredit">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
											<tr>
												<td class="label">借贷编号</td>
												<td class="data"><s:textfield  name="loanOrdId" id="loanOrdId" cssClass="data"></s:textfield></td>
												<td class="label">申请类型</td>
												<td class="data"><s:select id="pdtType" list="@newland.iaf.base.model.dict.PdtType@values()" listKey="name()" listValue="desc" headerKey="" headerValue="请选择" cssClass="data" name="pdtType"></s:select></td>
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
												<td class="data"><s:textfield name="merchName" id="merchName" cssClass="data"></s:textfield></td>
												</td>
												
												<td class="label">申请失效日期</td>
												<td class="data"><s:textfield name="acceptDate" id="acceptDate"></s:textfield>&nbsp;-&nbsp;<s:textfield name="endDate" id="endDate"></s:textfield></td>
												<td class="label">订单状态</td>
												<td class="data">
													<s:select list="#request.status" listKey="name()" listValue="desc" headerKey="" headerValue="请选择" cssClass="data" id="ordStat" name="ordStat"></s:select>
												</td>
											</tr>
										</table>
									</td>
									<td class="search-btn">
										&nbsp;&nbsp;
										<!--<s:submit cssClass="dark-btn" method="loanQuery" value="查  询" onclick="return checkForm()"></s:submit> -->
										<input type="button" class="dark-btn" onclick="storeQuery()" value="查 询"/>
									</td>
								</tr>
					</table>
					</s:form>
				</div>
				<h3 class="title">
				<span>查询结果</span>&nbsp;&nbsp;
				<font style="color:#29609F;font-size:15">您目前共有：
					<font style="color:red"><s:property value="creditCount"/>笔</font>借款申请等待放款，合计需放款金额：
					<font style="color:red"><s:property value="creditTotal"/>万元</font>。请点击以下的借款订单【详情】进行放款操作。
				</font>
				</h3>
				<div  style="margin:10px auto;width:100%">
					<table id="list"></table>
					<div id="pager"></div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
		$("#endDate,#acceptDate").datepicker({changeYear:true,changeMonth:true});
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
					url:'${ctx}/inst/loanord/prodCredit!list',
					//multiselect:true,
					colNames:['借贷编号','申请类型','贷款产品','贷款金额','贷款周期','贷款利率(%)','申请商户','申请失效日期','订单状态','操作'],
					colModel:[
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:70},
						{name:'pdtType',index:'pdtType',align:"center", width:40},
						{name:'pdtName',index:'pdtName',align:"center", width:50},
						{name:'quota',index:'region',align:"center", width:50},
						{name:'term',index:'term',align:"center", width:50},
						{name:'pdtRate',index:'pdtRate',align:"center", width:65},
						{name:'merchName',index:'merchName',align:"center", width:50},
						//{name:'merchType',index:'merchType',align:"center", width:50},
						{name:'checkDate',index:'checkDate',align:"center", width:50},
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
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewLoanOrd('+index+');">详情</a>]';
			}
			
			function viewLoanOrd(rowId){
				//window.location = "${ctx}/inst/loanord/prodCredit!viewLoanOrd?index=" + rowId;
				$("#index").val(rowId);
				$("#form").submit();
			}          
            function beforeSubmit() {
                var rows = $("#list").jqGrid('getGridParam','selarrrow');
                if( rows ==null||rows=='') {
                    alert('请至少选择一个账户。');
                    return false;
                }
                if( typeof(rows[0])=="undefined")rows.shift();
               
                if(rows[0]==rows[1])
                {
                    rows.shift();
                }
                $("#selectedRows").val(rows);
                return true;
            }
            function statFormatter(cellvalue, options, rowObject){
				if (cellvalue == "不受理" ||cellvalue == "审核未通过" || cellvalue == "未保存" || cellvalue == "逾期" || cellvalue == "欠款冻结中" || cellvalue == "欠款已冻结" || cellvalue == "受理过期" || cellvalue == "申请过期"){
					return "<div style='color:red'>" + cellvalue + "</div>";
				}
				return cellvalue;
			}
            
            function checkForm(){
            	if(isNaN($("#term").val())){
            		alert("期限请填写数字!");
            		return false;
            	}
              	if(isNaN($("#loanAmount").val())){
            		alert("金额请填写数字!");
            		return false;
            	}
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
				var ordStat=$("#ordStat").val();
			 	var termType=$("#termType").val();
			 	var endDate=$("#endDate").val();
				var acceptDate=$("#acceptDate").val();
				var rateType = $("#rateType").val();
				var minRate=$("#minRate").val();
				var maxRate=$("#maxRate").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/loanord/prodCredit!list?loanOrdId='+loanOrdId+'&pdtType='+pdtType+'&minloanAmount='+minloanAmount+'&maxloanAmount='+maxloanAmount+'&minTerm='+minTerm+'&maxTerm='+maxTerm+'&merchName='+encodeURI(merchName)+'&endDate='+endDate+'&acceptDate='+acceptDate+'&ordStat='+ordStat+'&termType='+termType+'&rateType='+rateType+'&minRate='+minRate+'&maxRate='+maxRate+'&pdtId='+pdtId,page:1}).trigger("reloadGrid");
            }
		</script>
		<s:form action="prodCredit!toNext" id="form" >
			<s:hidden id="index" name="index"></s:hidden>
		</s:form>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
