<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/template/mp/meta.jspf" %>
<!doctype html>
<html>
	<head>
		<%@include file="/template/mp/head.jspf" %>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;被冻结商户</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form id="merchMainForm" action="frozenMerch" namespace="/backstage/merchmanager">
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			              <tr>
								<td class="label" >商户名称</td>
								<td class="data"><s:textfield id="merchName" name="merchName"/>&nbsp;</td>
								<td class="label">申请时间</td>
								<td class="data"><s:textfield id="genTime" name="genTime"/></td>
							</tr>
			            </table></td> 
			          <td class="search-btn">
										&nbsp;&nbsp;
										<input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/> 
										&nbsp;&nbsp;
									</td>
			        </tr>
			      </table>
			      </s:form>
			    </div>
				<h3 class="title"><span>商户信息</span></h3>
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
				$("#genTime").datepicker({changeYear:true,changeMonth:true});
				getList();
			});
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/backstage/merchmanager/frozenMerch!list',
					colNames:['商户ID','商户名称','申请时间','冻结原因','状态','操作选项'],
					colModel:[
						{name:'imerch',index:'imerch',align:"center", width:50},
						{name:'merchName',index:'merchName',align:"center", width:90},
						{name:'genTime',index:'genTime',align:"center", formatoptions:{srcformat:'Y-m-d H:i',newformat:'Y-m-d H:i'},width:110},
						{name:'reason',index:'reason',align:"center", width:100},
						{name:'stat.desc',index:'stat.desc',align:"center", width:85},
						{name:'oper',index:'oper',align:"center", width:130, formatter:operFormatter},
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
			
			function detail(index){
				document.merchMainForm.action="${ctx}/backstage/merchmanager/frozenMerch!detail?index="+index;	
		        document.merchMainForm.submit();
			}
			
			function storeQuery(){
				var merchName = $("#merchName").val();
				var genTime = $("#genTime").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/merchmanager/frozenMerch!list?merchName='+encodeURI(merchName)+"&genTime="+genTime,page:1}).trigger("reloadGrid");
			}

		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
