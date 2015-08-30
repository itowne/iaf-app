<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<base target="_self" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
		<!-- 
			<div id="location">
				<p>当前位置：冻结管理&nbsp;&gt;&gt;&nbsp;</p>
			</div>
			-->
			<div id="content">
				
				<!-- <h3 class="title"><span>查询结果</span></h3> -->
				<div  style="margin:10px auto;width:100%">
							<table id="list"></table>
							<div id="pager"></div>
						</div>
			</div>
		</div>
		<s:form target="" id="thawForm" action="refundMgr!cancelFreeze">
			<s:hidden name="index" id="index"></s:hidden>
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
					url:'${ctx}/inst/loanord/refundMgr!freezeList',
					colNames:['批次号','商户名称','订单号','冻结期数','申请冻结原因','申请日期','操作选项'],
					colModel:[
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:70},
						{name:'merchName',index:'merchName',align:"center", width:50},
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:50},
						{name:'periods',index:'periods',align:"center", width:50},
						{name:'reason',index:'reason',align:"center", width:50},
						{name:'date',index:'date',align:"center", width:45},
						{name:'oper',index:'oper',align:"center", width:45, formatter:operFormatter},
					],
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					javaObj:"requestDataSet",
					shrinkToFit:true,
					autowidth:true,
					caption:"冻结申请列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}

			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:proc('+index+');">撤销</a>]&nbsp&nbsp';
			}
			function proc(index){
				if (confirm("是否撤销冻结请求？")){
					$("#index").val(index);
					$("#thawForm").submit();
					window.returnValue = 0;
				}else{
					window.returnValue = 1;
				}
			}

		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
