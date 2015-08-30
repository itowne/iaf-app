<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：借贷订单&nbsp;</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form action="loanOrdMgr">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
											<tr>
												<td class="label">借贷编号</td>
												<td class="data"><s:textfield  name="loanOrdId"  id="loanOrdId" cssClass="data"></s:textfield></td>
												<td class="label">借款商户</td>
												<td class="data"><s:textfield  name="merchName" id="merchName"  cssClass="data"></s:textfield></td>
												<td class="label">贷款机构</td>
												<td class="data"><s:select style="width:120px" id="instName" name="instName" list="instList"  listValue="instName " listKey="iinst " headerKey="" headerValue="请选择机构"></s:select></td>
											</tr>
											<tr>
												<td class="label">所属贷款产品</td>
												<td class="data"><s:select style="width:120px" id="pdtList" list="pdtList"  listValue="pdtName " listKey="iloanPdt " headerKey="" headerValue="请选择产品名称"></s:select></td>
												<td class="label">借款金额(万元)</td>
												<td class="data"><s:textfield cssClass="data" style="width:50px" id="loanAmount" name="loanAmount"/>(万元)&nbsp;至&nbsp;<s:textfield style="width:50px" cssClass="data" id="endloanAmount" name="endloanAmount"/>(万元)</td>
												<td class="label">利率(%)</td>
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
												<td class="label">借款周期</td>
												<td class="data">
												<s:textfield name="term" style="width:50px" id="term"/>至<s:textfield style="width:50px" name="endterm" id="endterm"/>
												<select id="termType" name="termType" style="width:120px;" class="validate[required]">
												<option value="">请选择单位</option>
												<option value="DAY">天</option>
												<option value="MONTH">个月</option>
												<option value="YEAR">年</option>
												</select>
												</td>
												<td class="label">订单状态</td>
												<td class="data">
												<s:select list="@newland.iaf.base.model.dict.OrdStat@values()" listKey="name()" listValue="desc" headerKey="" headerValue="请选择订单状态 " cssClass="data" id="ordStat" name="ordStat"></s:select></td>
												<td class="label">屏蔽状态</td>
												<td class="data"><select id="shield" name="shield">
												<option value="">请选择状态</option>
												<option value="0">未屏蔽</option>
												<option value="1">已屏蔽</option>
												</select></td>
											</tr>
											<!-- 
											<tr>
												<td class="label">借贷编号</td>
												<td class="data"><s:textfield  name="loanOrdId"  cssClass="data"></s:textfield></td>
												<td class="label">借款商户</td>
												<td class="data"><s:textfield  name="merchName"  cssClass="data"></s:textfield></td>
												<td class="label">机构名称</td>
												<td class="data"><s:textfield name="instName" cssClass="data"></s:textfield></td>
												<td class="label">订单进度</td>
												<td class="data">
													<s:select list="#{'ALL':'全部','APPLY':'申请中','ACCEPT':'受理中','AUDIT':'审核通过','REFUND':'已放款','REFUSE':'拒绝','CANCEL':'取消'}" cssClass="data" name="ordStat"></s:select>
												</td>
											</tr>
											 -->
										</table>
									</td>
									<td class="data">
									<input type="button" class="dark-btn" onclick="storeQuery();" value="查  询"/>
									<!--<s:submit cssClass="dark-btn" method="loanQuery" value="查  询"></s:submit>--></td>
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
		<s:form id="shieldForm" action="loanOrdMgr!shield">
			<s:hidden id="index" name="index"></s:hidden>
		</s:form>
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
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/backstage/loanOrdMgr!list',
					colNames:['借贷编号','借款商户','贷款机构','所属贷款产品','借款金额','最低利率','借款周期','订单状态','屏蔽','操作'],
					colModel:[
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:50},
						{name:'merchName',index:'merchName',align:"center", width:50},
						{name:'instName',index:'instName',align:"center", width:50},
						{name:'pdtName',index:'pdtName',align:"center",formatter:pdtFormatter,width:45},
						{name:'quota',index:'quota',align:"center", width:40},
						{name:'rate',index:'rate',align:"center", formatter:rateFormatter, width:40},
						{name:'term',index:'term',align:"center", width:30},
						{name:'ordStat',index:'ordStat',align:"center",formatter:statFormatter, width:50},
						{name:'sheild',index:'sheild',align:"center",formatter:sheildFormatter, width:40},
						{name:'oper',index:'count',formatter:optionFormatter,align:"center", width:50},
					],
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
			
			function rateFormatter(cellvalue, options, rowObject){
				var data = rowObject[5];
				if(data==null){
					return "0%";
				}else{
					return data+"起";
				}
			}
			function sheildFormatter(cellvalue, options, rowObject){
				var data = rowObject[9];
				if(data==0){
					return "未屏蔽";
				}else{
					return "<font style='color:red'>屏蔽</font>";
				}
			}
			function pdtFormatter(cellvalue, options, rowObject){
				var data = rowObject[3];
				if(data==""){
					return "--";
				}else{
					return data;
				}
			}
			
			function statFormatter(cellvalue, options, rowObject){
				var data = rowObject[8];
				if (data == "不受理" ||data == "审核未通过" || data == "未保存" || data == "逾期" || data == "欠款冻结中" || data == "欠款已冻结" || data == "受理过期" || data == "申请过期"){
					return "<div style='color:red'>" + data + "</div>";
				}
				return data;
			}
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				var data = rowObject[9];
				if(data==0){
					return '[<n:HcAuthA authCode="JDDD_XQ" href="JavaScript:viewLoanOrd('+index+');" value="详情"></n:HcAuthA>]';
					//'[<n:HcAuthA authCode="JDDD_YB" href="JavaScript:shield('+index+');" value="屏蔽"></n:HcAuthA>]';
				}else{
					return '[<n:HcAuthA authCode="JDDD_XQ" href="JavaScript:viewLoanOrd('+index+');" value="详情"></n:HcAuthA>]';
					//'[<a href="JavaScript:reShield('+index+');">取消屏蔽</a>]';
				}
			}
			
			function shield(index){
				if (confirm("是否屏蔽该订单？")){
					$("#index").val(index);
					$("#shieldForm").submit();
				}
			}
			
			function reShield(index){
				if(confirm("是否取消屏蔽?")){
					window.location = "${ctx}/backstage/loanOrdMgr!reShield?index=" + index;
				}
			}
			function viewLoanOrd(rowId){
				window.location = "${ctx}/backstage/loanOrdMgr!viewLoanOrd?index=" + rowId;
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

				var instName=$("#instName").val();
				var loanAmount = $("#loanAmount").val();
				var term = $("#term").val();
				var loanOrdId = $("#loanOrdId").val();
				//var loanPdtName=$("#loanPdtName").val();
				var ordStat=$("#ordStat").val();
				var merchName=$("#merchName").val();
				
				var pdtId=$("#pdtList").val();
				var endloanAmount=$("#endloanAmount").val();
				var shield = $("#shield").val();
				
				var termType=$("#termType").val();
				
				var rateType = $("#rateType").val();
				var minRate=$("#minRate").val();
				var maxRate=$("#maxRate").val();
				var endterm = $("#endterm").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/loanOrdMgr!list?pdtId='+pdtId+'&merchName='+encodeURI(merchName)+'&loanAmount='+loanAmount+'&term='+term+'&loanOrdId='+loanOrdId+'&ordStat='+ordStat+'&endloanAmount='+endloanAmount+'&endterm='+endterm+'&termType='+termType+'&instName='+instName+'&shield='+shield+'&rateType='+rateType+'&minRate='+minRate+'&maxRate='+maxRate,page:1}).trigger("reloadGrid");
			}
			
		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
