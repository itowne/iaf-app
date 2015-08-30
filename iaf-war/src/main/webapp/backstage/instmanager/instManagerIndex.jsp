<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;机构管理</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form id="instMainForm" action="instManager" namespace="/backstage/instmanager">
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			              <tr>
								<td class="label" >机构名称</td>
								<td class="data"><s:select style="width:120px" id="instName" name="instName" list="instList"  listValue="instName " listKey="iinst " headerKey="" headerValue="请选择机构"></s:select></td>
								<td class="label" >机构注册地址</td>
								<td class="data"><s:textfield id="regAddr" name="regAddr"/></td>
								<td class="label">联系人</td>
								<td class="data"><input type="text" id="contact" name="contact"/></td>
							</tr>
							<tr>
								<td class="label">联系电话</td>
								<td class="data"><input type="text" id="tel" name="tel"/></td>
								<td class="label">状态</td>
								<td class="data"><s:select name="stat" id="stat" list="@newland.iaf.base.model.dict.InstStatusType@values()" listKey="toString()" headerKey="" headerValue="请选择" listValue="getDesc()" value="stat"></s:select></td>
								<td class="label">&nbsp;</td>
								<td class="data">&nbsp;</td>
							</tr>
			            </table></td> 
			          <td class="search-btn">
										&nbsp;&nbsp;
										<input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/> 
									</td>
			        </tr>
			      </table>
			      </s:form>
			    </div>
			    <div style="float:right;padding: 0"><n:HcAuthButton cssClass="dark-btn" value="新增机构" onclick="toAdd()" authCode="JGGL_JGGL_XZ"/></div>
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
					url:'${ctx}/backstage/instmanager/instManager!list',
					colNames:['机构名称','机构注册地址','联系人','联系电话','状态','操作'],
					colModel:[
						//{name:'iinst',index:'iinst',align:"center", width:50},
						{name:'instName',index:'instName',align:"center", width:100},
						{name:'regAddr',index:'regAddr',align:"center", width:150},
						//{name:'busiLicense',index:'busiLicense',align:"center", width:80},
						//{name:'taxRegNo',index:'taxRegNo',align:"center", width:85},
						{name:'contact',index:'contact',align:"center", width:65},
						{name:'contactPhone',index:'contactPhone',align:"center", width:85},
						{name:'instStat.desc',index:'instStat.desc',align:"center", width:45,formatter:operFormatter1},
						{name:'oper',index:'oper',align:"center", width:70, formatter:operFormatter},
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"机构信息列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}

			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				//var data = rowObject[4];
				return '<a href="JavaScript:modify('+index+')";>[详情]</a>';
				//if(data=="可用"){
					//return '[<n:HcAuthA authCode="JGGL_JGGL_XG" href="JavaScript:modify('+index+');" value="修改"></n:HcAuthA>]&nbsp&nbsp' + 
					//'[<n:HcAuthA authCode="JGGL_JGGL_JY" href="JavaScript:jinyong('+index+');" value="禁用"></n:HcAuthA>]&nbsp&nbsp';
				//}else{
					//return '[<n:HcAuthA authCode="JGGL_JGGL_QY" href="JavaScript:keyong('+index+');" value="启用"></n:HcAuthA>]&nbsp&nbsp';
				//}
			}
			
			function operFormatter1(cellvalue, options, rowObject){
				var data = rowObject[4];
				if(data=="可用"){
					return data;
				}else{
					return "<font style='color:red'>"+data+"</red>";
				}
			}
			
			function modify(index){
				document.instMainForm.action="${ctx}/backstage/instmanager/instManager!modifyInst?index="+index;	
		        document.instMainForm.submit();
			}
			
			function jinyong(index){
				if(confirm("是否确定要禁用该机构?")){
					document.instMainForm.action="${ctx}/backstage/instmanager/instManager!disableInst?index="+index;	
			        document.instMainForm.submit();
				}
			}
			
			function keyong(index){
				if(confirm("是否确定要启用该机构?")){
					document.instMainForm.action="${ctx}/backstage/instmanager/instManager!enableInst?index="+index;	
			        document.instMainForm.submit();
				}
			}
			
			function storeQuery(){
				var regAddr = $("#regAddr").val();
				var contact=$("#contact").val();
				var tel=$("#tel").val();
				var stat=$("#stat").val();
				var instName=$("#instName").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/instmanager/instManager!list?instName='+instName+'&regAddr='+encodeURI(regAddr)+'&contact='+contact+'&tel='+tel+'&stat='+stat,page:1}).trigger("reloadGrid");
			}
			function toAdd(){
				document.instMainForm.action="${ctx}/backstage/instmanager/instManager!instAdd";	
		        document.instMainForm.submit();
			}
		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
