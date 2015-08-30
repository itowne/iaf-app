<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：公告管理&nbsp;</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form id="instMainForm" action="notifyManager" namespace="/backstage/notifymanager">
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			              <tr>
								<td class="label" >公告标题</td>
								<td class="data"><s:textfield id="title" name="title"/>&nbsp;</td>
							</tr>
							<tr>
								<td class="label" >更新时间</td>
								<td class="data"><s:textfield id="updtime" name="updtime"/>&nbsp;</td>
							</tr>
			            </table></td> 
			          <td class="search-btn">
										&nbsp;&nbsp;
										<input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/> 
										&nbsp;&nbsp;
										<!--<n:HcAuthButton cssClass="dark-btn" value="新增公告" onclick="toAdd()" authCode="GGGL_XZ"/>-->
									</td>
			        </tr>
			      </table>
			      </s:form>
			    </div>
			    <div style="float:right;padding: 0"><n:HcAuthButton cssClass="dark-btn" value="新增公告" onclick="toAdd()" authCode="GGGL_XZ"/></div>
				<h3 class="title"><span>查询结果</span></h3>
				<div  style="margin:10px auto;width:100%">
							<table id="list"></table>
							<div id="pager"></div>
						</div>
			</div>
		</div>
		<script type="text/javascript">
		$("#updtime").datepicker({changeMonth:true,changeYear:true});  
		
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
					url:'${ctx}/backstage/notifymanager/notifyManager!list',
					colNames:['公告标题','更新时间','操作'],
					colModel:[
						//{name:'iinstNotice',index:'iinstNotice',align:"center", width:50},
						{name:'title',index:'title',align:"center"},
						{name:'updTime',index:'updTime',align:"center"},
						{name:'oper',index:'oper',align:"center", width:135, formatter:operFormatter},
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"公告信息列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}

			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<n:HcAuthA authCode="GGGL_XG" href="JavaScript:modify('+index+');" value="详情"></n:HcAuthA>]&nbsp&nbsp';
					//+ 
					//'[<n:HcAuthA authCode="GGGL_SC" href="JavaScript:del('+index+');" value="删除"></n:HcAuthA>]&nbsp&nbsp';
				
			}
			
			function modify(index){
				url ="${ctx}/backstage/notifymanager/notifyManager!notifyEdit?index="+index;	
				$("#instMainForm").attr("action",url)
				$("#instMainForm").submit();
			}
			
			function del(index){
				if(confirm("是否确定要删除该公告?")){
					url = "${ctx}/backstage/notifymanager/notifyManager!deleteNotify?index="+index;	
					$("#instMainForm").attr("action",url)
					$("#instMainForm").submit();
				}
			}
			
			function storeQuery(){
				var title = $("#title").val();
				var updtime=$("#updtime").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/notifymanager/notifyManager!list?title='+encodeURI(title)+'&updtime'+updtime,page:1}).trigger("reloadGrid");
			}
			function toAdd(){
				document.instMainForm.action="${ctx}/backstage/notifymanager/notifyManager!notifyAdd";	
		        document.instMainForm.submit();
			}
		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
