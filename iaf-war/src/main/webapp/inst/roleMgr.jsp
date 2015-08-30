<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<jsp:include page="${ctx}/template/head.jsp" />
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 机构管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;角色权限</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label">角色名称</td>
										<td class="data"><s:textfield id="roleName" name="roleName"  cssClass="data"></s:textfield></td>
										<td class="label">角色状态</td>
										<td class="data">
											<select id="roleStat">
												<option value="">请选择角色状态</option>
												<option value="1">正常</option>
												<option value="0">暂停</option>
											</select>
										</td>
									</tr>
								</table>
							</td>
							<td class="search-btn">
								&nbsp;&nbsp;
								<input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/> 
      						</td>
						</tr>
					</table>
				</div>
				<div style="float:right;padding: 0"><n:instAuthButton cssClass="dark-btn" onclick="addRole()" value="新增角色" authCode="XTGL_JSQX_XZJS"/></div>
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
				var roleName=$('#roleName').val();
				var roleStat=$('#roleStat').val();
				jQuery("#list").jqGrid({
					url:'${ctx}/inst/instRole!list?roleName='+roleName+'&roleStat='+roleStat,
					colNames:['角色名称','状态','操作'],
					colModel:[
						//{name:'iInstRole',index:'iInstRole',align:"center", width:30},
						{name:'roleName',index:'roleName',align:"center", width:50},
						{name:'roleStat',index:'roleStat',align:"center",formatter:statFormatter, width:50},
						{name:'option',index:'option',formatter:optionFormatter,align:"center", width:70},
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"角色列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			
			function storeQuery(){
				var roleName=$('#roleName').val();
				var roleStat=$('#roleStat').val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/instRole!list?roleName='+encodeURI(roleName)+'&roleStat='+roleStat,page:1}).trigger("reloadGrid");
			}
			
			function statFormatter(cellvalue, options, rowObject){
				var data = rowObject[1];
				if(data==1){
					return "正常";
				}else{
					return "<div style='color:red'>暂停</div>";
				}
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var id = options.rowId;
				var flag=rowObject[2];
				var roleName=rowObject[0];
				var myroleName='${myrole.roleName}';
				if(flag==1){
					if(roleName==myroleName){
						return '[<a href="JavaScript:viewDetail('+id+')">详情</a>]' + '&nbsp;&nbsp;&nbsp;' +
						'[<n:instAuthA href="javascript:updateRole('+id+');" authCode="XTGL_JSQX_XGJS" value="修改" />]' + '&nbsp;&nbsp;&nbsp;' +
						'[<n:instAuthA href="javascript:delteRole('+id+');" authCode="XTGL_JSQX_SC" value="删除" />]';
					}else{
						return"--";
						
					}
				}else{
					return '[<a href="JavaScript:viewDetail('+id+')">详情</a>]' + '&nbsp;&nbsp;&nbsp;' +
					'[<n:instAuthA href="javascript:updateRole('+id+');" authCode="XTGL_JSQX_XGJS" value="修改" />]' + '&nbsp;&nbsp;&nbsp;' +
					'[<n:instAuthA href="javascript:delteRole('+id+');" authCode="XTGL_JSQX_SC" value="删除" />]';
				}
			}
			
			function addRole(){
				window.location.href="${ctx}/inst/roleAdd.jsp";
			}
			function viewDetail(id){
				window.location.href="${ctx}/inst/instRole!viewInstRole?index="+id;
			}
			
			function updateRole(id){
				window.location.href="${ctx}/inst/instRole!loadInstRole?index="+id;
			}
			function delteRole(id){
				if(confirm("确认要删除此用户?")){
					window.location.href="${ctx}/inst/instRole!deleteInstRole?index="+id;
				}
			}
	
		</script>
			
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>
