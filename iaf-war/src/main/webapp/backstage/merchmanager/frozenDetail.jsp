<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;被冻结商户</p>
			</div>
			<div id="content">
				<h3 class="title"><span>基本信息</span></h3>
				<div class="search-bar">
			      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			      	<input type="hidden" id='merchID' name='merchID' value='<s:property value="applyRequest.imerch"/>' />
		       		 <tr>
						<td class="label">商户名称：</td>
						<td class="data"><s:property value="applyRequest.merchName"/></td>
						<td class="label">冻结时间：</td>
						<td class="data"><fmt:formatDate value="${applyRequest.updTime}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td class="label">申请冻结机构：</td>
						<td class="data"><s:property value="applyRequest.pdtName"/></td>
						<td class="label">申请冻结时间：</td>
						<td class="data"><fmt:formatDate value="${applyRequest.genTime}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td class="label">申请冻结原因：</td>
						<td class="data"><s:property value="applyRequest.reason"/></td>
						<td class="label">冻结处理结果：</td>
						<td class="data"><s:property value="applyRequest.stat.desc"/></td>
					</tr>
			      </table>
			    </div>
				<h3 class="title"><span>还款现状</span></h3>
				<div  style="margin:10px auto;width:100%">
							<table id="list"></table>
							<div id="pager"></div>
						</div>
				<div class="uop">
						<input type="button" class="dark-btn"  value="返&nbsp;&nbsp;回" onclick="back();"/> 
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
					url:'${ctx}/backstage/merchmanager/frozenMerchOrd!list?imerch='+<s:property value="applyRequest.imerch"/>,
					colNames:['订单号','借贷机构','放款日期','贷款金额 ','借款期限','已还款金额 ','还款状态'],
					colModel:[
						{name:'loanOrdId',index:'loanOrdId',align:"center", width:50},
						{name:'instName',index:'instName',align:"center", width:90},
						{name:'creditDate',index:'creditDate',align:"center", formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},width:110},
						{name:'quota',index:'quota',align:"center", width:100},
						{name:'term',index:'term',align:"center", width:100},
						{name:'repayment',index:'repayment',align:"center", width:100},
						{name:'ordStat.desc',index:'ordStat.desc',align:"center", width:85}
					],
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"商户信息列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}

			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:detail('+index+');">详情</a>]&nbsp&nbsp' ;
			}
			
			function back(){
				window.location.href = "${ctx}/backstage/merchmanager/frozenMerch";
			}

		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
