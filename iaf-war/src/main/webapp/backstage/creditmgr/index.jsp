<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：放款记录&nbsp;</p>
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
												<td class="data"><s:textfield  name="loanOrdId" id="loanOrdId" cssClass="data"/></td>
												<td class="label">商户名称</td>
												<td class="data"><s:textfield name="merchName" id="merchName" cssClass="data"/></td>
											</tr>
											<tr>
												<td class="label">机构名称</td>
												<td class="data"><s:textfield  name="instName" id="instName" cssClass="data"/></td>
												<td class="label">类型</td>
												<td class="data">
												<select name="type" id="type">
													<option value="">请选择类型</option>
													<option value="CREDIT">平台内放款</option>
													<option value="OTH_CREDIT">平台外放款</option>
												</select></td>
											</tr>
										</table>
									</td>
									<td class="search-btn">
													&nbsp;&nbsp;
													<input type="button" class="dark-btn" onclick="storeQuery()" value="查  询"/>
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
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/backstage/creditMgr!list',
					colNames:['订单编号','借贷编号','商户名称','机构名称','入账时间','放款金额','类型','操作'],
					colModel:[
						{name:'traceNo',index:'traceNo',align:"center", width:70},
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:70},
						{name:'merchName',index:'merchName',align:"center", width:50},
						{name:'instName',index:'instName',align:"center", width:50},
						{name:'genTime',index:'genTime',align:"center", width:75},
						{name:'amount',index:'amount',align:"center", width:45},
						{name:'type',index:'type',align:"center", width:45,formatter:optionFormatter1},
						{name:'oper',index:'count',formatter:optionFormatter,align:"center", width:50},
					],
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"放款列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			function optionFormatter1(cellvalue, options, rowObject){
				var id=rowObject[6];
				if(id=="CREDIT"){
					return "平台内放款";
				}else if(id=="OTH_CREDIT"){
					return "平台外放款";
				}
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:viewFundFlowDetail('+index+');">详情</a>]' + '&nbsp;&nbsp;';
			}
			
			function optionFormatter2(cellvalue, options, rowObject){
				var data = rowObject[5];
				return data+'元';
			}


			function viewFundFlowDetail(rowId){
				window.location = "${ctx}/backstage/creditMgr!viewFundFlowDetail?index=" + rowId;
			}
			
			function storeQuery(){
				var loanOrdId = $("#loanOrdId").val();
				var instName = $("#instName").val();
				var merchName = $("#merchName").val();
				var type=$("#type").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/creditMgr!list?loanOrdId='+loanOrdId+'&instName='+encodeURI(instName)+'&merchName='+encodeURI(merchName)+'&type='+type,page:1}).trigger("reloadGrid");
			}
		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
