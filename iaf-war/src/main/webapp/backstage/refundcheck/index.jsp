<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：还款对账&nbsp;</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form id="form" action="refundCheck" namespace="backstage">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label">借贷编号</td>
										<td class="data"><s:textfield  name="loanOrdId" id="loanOrdId" cssClass="data"></s:textfield></td>
										<td class="label">交易订单号</td>
										<td class="data"><s:textfield name="otherNo" id="otherNo" cssClass="data"/></td>
										<td class="label">放贷机构</td>
										<td class="data">
										<s:select id="inst"  list="instList"  listValue="instName" listKey="iinst" headerKey="" headerValue="请选机构名称"></s:select></td>
									</tr>
									<tr>
									<td class="label">借款商户</td>
										<td class="data">
											<s:textfield  name="merchName"  id="merchName" cssClass="data"></s:textfield>
										</td>
										<td class="label">还款金额(万元)</td>
										<td class="data"><s:textfield style="width:50px" name="quota" id="quota" cssClass="data"></s:textfield>至<s:textfield style="width:50px" name="maxQuota" id="maxQuota" cssClass="data"></s:textfield></td>
										<td class="label">还款日期</td>
										<td class="data" colspan="3"><s:textfield style="width:80px" name="beginDate" id="beginDate" /> - <s:textfield style="width:80px" name="endDate" id="endDate" ></s:textfield>
										</td>
									</tr>
									<tr>
										<td class="label" >
										状态
										</td>
										<td class="data"><s:select id="fundFlowStat" list="#{'AUDIT':'未对账','SUCCESS':'已对账','EXPIRY':'退款'}" headerKey="" headerValue="请选择状态" cssClass="data" name="fundFlowStat"></s:select></td>
										<td class="data">&nbsp;</td>
										<td class="data">&nbsp;</td>
										<td class="data">&nbsp;</td>
										<td class="data">&nbsp;</td>
									</tr>
								</table>
							</td>
							<td class="data"><input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/></td>
							</tr>
					</table>
					</s:form>
				</div>
				<div style="float:right;padding: 0"><n:HcAuthButton cssClass="dark-btn" value="批量下载对账文件" onclick="dow()" authCode="HKDZ_XZ"/></div>
				<h3 class="title"><span>查询结果</span>&nbsp;&nbsp;<font style="color:#29609F;font-size:15">已对账总笔数：<s:property value="count"/>笔；已对账总金额：￥<s:property value="total"/></font></h3>
				<div  style="margin:10px auto;width:100%">
							<table id="list"></table>
							<div id="pager"></div>
						</div>
			</div>
		</div>
		
		<script type="text/javascript">
		//if($("#fundFlowStat").val()==""){
			//$("#fundFlowStat option[value='AUDIT']").attr("selected",true);
		//};
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
			});

			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/backstage/refundCheck!list',
					colNames:['借贷编号','交易订单号','放贷机构','借款商户','还款金额','还款日期','状态','操作'],
					colModel:[
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:70},
						{name:'otherSysNo',index:'otherSysNo',align:"center", width:75},
						{name:'instName',index:'instName',align:"center", width:50},
						{name:'merchName',index:'merchName',align:"center", width:50},
						{name:'amount',index:'amount',align:"center",formatter:operFormatter2, width:50},
						{name:'date',index:'date',align:"center", width:65},
						{name:'stat',index:'stat',align:"center", width:45,id:'stat',formatter:operFormatter1},
						{name:'oper',index:'oper',align:"center", width:55, formatter:operFormatter},
					],
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"交易订单列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}

			function operFormatter1(cellvalue, options, rowObject){
				var tmp=rowObject[6];
				if(tmp=="受理中"){
					return "<div style='color:red'>未对账</div>";
				}else{
					return tmp;
				}
			}
			
			function operFormatter2(cellvalue, options, rowObject){
				var tmp=rowObject[4];
				return tmp+'元';
			}
			
			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				var data = rowObject[7];
			}
			
			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				var data = rowObject[7];
				var tmp=rowObject[6];
				if(data=="OTH_REFUND"||tmp=="已对账"||tmp=="退款"){
					return '[<a href="JavaScript:proc('+index+');">对账详情</a>]&nbsp&nbsp' ; 
					//'[<n:HcAuthA authCode="HKDZ_XZ" href="JavaScript:download('+index+');" value="下载订单"></n:HcAuthA>]';
				}else{
					return '[<a href="JavaScript:proc('+index+');">手工对账处理</a>]&nbsp&nbsp'; 
					//'[<n:HcAuthA authCode="HKDZ_XZ" href="JavaScript:download('+index+');" value="下载订单"></n:HcAuthA>]';
				}
			}
			
			function proc(index){
				parent.jDialog("还款明细","${ctx}/backstage/refundCheck!viewDetail.action?method:viewDetail&index=" + index,860,540,true,function(){
					jQuery("#list").trigger('reloadGrid');
				});
			}
			
			function download(rowId){
				window.location = "${ctx}/backstage/refundCheck!downloadOrd.action?index=" + rowId;
			}
			
			function dow(){
				document.form.action="${ctx}/backstage/refundCheck!download";	
		        document.form.submit();
			}
			
			function storeQuery(){
				var loanOrdId =$("#loanOrdId").val();
				var otherNo=$("#otherNo").val();
				var inst = $("#inst").val();
				var merchName=$("#merchName").val();
				var quota=$("#quota").val();
				var beginDate=$("#beginDate").val();
				var endDate=$("#endDate").val();
				var fundFlowStat=$("#fundFlowStat").val();
				var maxQuota=$("#maxQuota").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/refundCheck!list?loanOrdId='+loanOrdId+'&otherNo='+otherNo+'&iinst='+inst+'&merchName='+encodeURI(merchName)+'&quota='+quota+'&beginDate='+beginDate+'&endDate='+endDate+'&fundFlowStat='+fundFlowStat+'&maxQuota='+maxQuota,page:1}).trigger("reloadGrid");
			}
			
		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
