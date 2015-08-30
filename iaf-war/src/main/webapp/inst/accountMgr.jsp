<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> 
<jsp:include page="${ctx}/template/head.jsp" />
<html>

<head>
<meta charset="utf-8"/>
<title>汇融易 - 机构管理后台</title>
<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
</head>
<body>
	<div id="workframe-wrapper" class="clearfix">
		<div id="location">
			<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;账号管理</p>
		</div>
		<div id="content">
			<h3 class="title"><span>查询条件</span></h3>
			<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label">登录账号</td>
										<td class="data"><s:textfield id="loginName" name="loginName"  cssClass="data"></s:textfield></td>
										<td class="label">所属角色</td>
										<td class="data">
											<select id="role">
											<option value="">请选择所属角色</option>
											<s:iterator var="roleList" value="instRoleList">
											<option value="<s:property value="#roleList.getiInstRole()"/>"><s:property value="#roleList.getRoleName()"/></option>
											</s:iterator>
											</select>
										</td>
									</tr>
									<tr>
										<td class="label">使用者名称</td>
										<td class="data"><s:textfield id="userName" name="userName" cssClass="data"></s:textfield></td>
										<td class="label">状态</td>
										<td class="data">
										<s:select name="userStat" list="@newland.iaf.base.model.dict.InstUserStatType@values()" listKey="toString()" listValue="getDesc()" headerKey="" headerValue="请选择账号状态"></s:select>
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
			<div style="float:right;padding: 0"><n:instAuthButton cssClass="dark-btn" onclick="addAccount()" value="新增账号" authCode="XTGL_ZHQX_XZZH"/></div>
			<h3 class="title"><span>查询结果</span>
			</h3>
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
			var loginName=$('#loginName').val();
			var userName=$('#userName').val();
			var userStat=$('#userStat').val();
			var role=$('#role').val();
			jQuery("#list").jqGrid({
				url:'${ctx}/inst/instUser!list?loginName='+loginName+'&userName='+userName+'&usrStat='+userStat+'&role='+role,
				colNames:['登录账号','所属角色','使用者','状态','操作'],
				colModel:[
					//{name:'iinstUser',index:'iinstUser',align:"center", width:30},
					{name:'loginName',index:'loginName',align:"center", width:50},
					{name:'instRoles',index:'instRoles',align:"center", width:50},
					{name:'userName',index:'userName',align:"center", width:50},
					{name:'usrStat.desc',index:'usrStat.desc',align:"center", width:50},
					{name:'option',index:'option',formatter:optionFormatter,align:"center", width:70},
				],
				rownumbers:true,
				height:240,
				rowNum:10,
				pager: '#pager',
				repeatitems: false,
				shrinkToFit:true,
				autowidth:true,
				caption:"账号列表"
			});
			jQuery("#list").jqGrid('navGrid','#pager');
		}
		
		function storeQuery(){
			var loginName=$('#loginName').val();
			var userName=$('#userName').val();
			var userStat=$('#userStat').val();
			var role=$('#role').val();
			$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/instUser!list?loginName='+loginName+'&userName='+encodeURI(userName)+'&usrStat='+userStat+'&role='+encodeURI(role),page:1}).trigger("reloadGrid");
		}
		
		function statFormatter(cellvalue, options, rowObject){
			var data = rowObject[3];
			if(data==1){
				return "正常";
			}else{
				return "<div style='color:red'>暂停</div>";
			}
		}
		
		function optionFormatter(cellvalue, options, rowObject){
			//var id = "'"+rowObject[0]+"'";
			var id = options.rowId;
			var data = rowObject[3];
			var roleName=rowObject[1];
			var myrolename='${myrole.roleName}';
			var flag = rowObject[4];
			if(flag==1){
				if(roleName==myrolename){
					return '[<a href="JavaScript:viewDetail('+id+');">详情</a>]' + '&nbsp;&nbsp;' ;
				}else{
					return "--";
				}
			}else{
				return '[<a href="JavaScript:viewDetail('+id+');">详情</a>]' + '&nbsp;&nbsp;' ;
			}
			//if(data =='正常'){
				//return '[<a href="JavaScript:viewDetail('+id+');">详情</a>]' + '&nbsp;&nbsp;' ;
				//+
				//'[<n:instAuthA href="javascript:disableUser('+id+');" authCode="XTGL_ZHQX_ZTZH" value="停用" />]' + '&nbsp;&nbsp;' +
				//'[<n:instAuthA href="javascript:deleteUser('+id+');" authCode="XTGL_ZHQX_SCZH" value="删除" />]';
			//}else{
				//return '[<a href="JavaScript:viewDetail('+id+');">详情</a>]' + '&nbsp;&nbsp;';
				//+
				//'[<n:instAuthA href="javascript:JavaScript:recover('+id+');" authCode="XTGL_ZHQX_QYZH" value="启用" />]' + '&nbsp;&nbsp;' +
				//'[<n:instAuthA href="javascript:deleteUser('+id+');" authCode="XTGL_ZHQX_SCZH" value="删除" />]';
			//}
		}
		function recover(id){
			if(confirm("确认要启用此用户?")){
				window.location.href = "${ctx}/inst/instUser!reCoverInstUser?index="+id;
			}
		}
		
		function viewDetail(id){
		window.location.href="${ctx}/inst/instUser!loadInsrUser?index="+id;
		}
		
		function disableUser(id){
			if(confirm("确认要停用此用户?")){
				window.location.href="${ctx}/inst/instUser!disableAcct?index="+id;
			}
		}
		
		function deleteUser(id){
			if(confirm("确认要删除此用户?")){
				window.location.href="${ctx}/inst/instUser!deleteInstUser?index="+id;
			}
		}
		
		function addAccount(){
			window.location.href="${ctx}/inst/instUser!instUserToAdd";
		}

	</script>
		
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>
