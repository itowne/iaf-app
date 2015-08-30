<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
			<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;操作员管理&nbsp;&gt;&gt;&nbsp;当前机构&nbsp;：<s:label name="inst.instName"/></p>
		</div>
		<div id="content">
			<h3 class="title"><span>查询条件</span></h3>
			<div class="search-bar">
			<input type="hidden" id="iinst" name="iinst" value="<s:property value="iinst"/>"/>
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
			<div style="float:right;padding: 0"><n:HcAuthButton cssClass="dark-btn"  value="新增帐号" onclick="addAccount();" authCode="JGGL_JGJS_XZ"/></div>
			<h3 class="title"><span>查询结果</span></h3>
			<div  style="margin:10px auto;width:100%">
				<table id="list"></table>
				<div id="pager"></div>
			</div>
			<div align="center"><input type="button" class="dark-btn"  value="返回" onclick="backAccount();"/></div>
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
			var iinst=$("#iinst").val();
			jQuery("#list").jqGrid({
				url:'${ctx}/backstage/instmanager/backstageInstUser!list?role=&iinst='+iinst,
				colNames:['登录账号','所属角色','使用者','状态','操作'],
				colModel:[
					//{name:'iinstUser',index:'iinstUser',align:"center", width:30},
					{name:'loginName',index:'loginName',align:"center", width:50},
					{name:'instRoles',index:'instRoles',align:"center", width:50},
					{name:'userName',index:'userName',align:"center", width:50},
					{name:'usrStat。desc',index:'usrStat.desc',align:"center", width:50,formatter:optionFormatter1},
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
			var iinst=$("#iinst").val();
			var loginName=$('#loginName').val();
			var userName=$('#userName').val();
			var userStat=$('#userStat').val();
			var role=$('#role').val();
			$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/instmanager/backstageInstUser!list?loginName='+encodeURI(loginName)+'&userName='+encodeURI(userName)+'&usrStat='+userStat+'&role='+role+'&iinst='+iinst,page:1}).trigger("reloadGrid");
		}
		
		function optionFormatter(cellvalue, options, rowObject){
			//var id = "'"+rowObject[0]+"'";
			var id = options.rowId;
			//var data = rowObject[3];
			return '<a href="JavaScript:viewDetail('+id+')";>[详情]</a>';
		//	if(data =='正常'){
				//return '[<n:HcAuthA authCode="JGGL_JGCZY_XG" href="JavaScript:viewDetail('+id+');" value="详情"></n:HcAuthA>]' + '&nbsp;&nbsp;' +
				//'[<n:HcAuthA authCode="JGGL_JGCZY_JY" href="JavaScript:disableUser('+id+');" value="停用"></n:HcAuthA>]' + '&nbsp;&nbsp;' +
				//'[<n:HcAuthA authCode="JGGL_JGCZY_SC" href="JavaScript:deleteUser('+id+');" value="删除"></n:HcAuthA>]';
			//}else{
				//return '[<n:HcAuthA authCode="JGGL_JGCZY_QY" href="JavaScript:recover('+id+');" value="启用"></n:HcAuthA>]' + '&nbsp;&nbsp;' +
				//'[<n:HcAuthA authCode="JGGL_JGCZY_SC" href="JavaScript:deleteUser('+id+');" value="删除"></n:HcAuthA>]';
			//}
		}
		
		function optionFormatter1(cellvalue, options, rowObject){
			var data = rowObject[3];
			if(data =='正常'){
				return data;
			}else{
				return "<div style='color:red'>"+data+"</div>";
			}
		}
		function recover(id){
			if(confirm("确认要启用此用户?")){
			window.location.href = "${ctx}/backstage/instmanager/backstageInstUser!reCoverInstUser?index="+id;
			}
		}
		
		function viewDetail(id){
		window.location.href="${ctx}/backstage/instmanager/backstageInstUser!loadInsrUser?index="+id;
		}
		
		function disableUser(id){
			if(confirm("确认要停用此用户?")){
			window.location.href="${ctx}/backstage/instmanager/backstageInstUser!disableAcct?index="+id;
			}
		}
		
		function deleteUser(id){
			if(confirm("确认要删除此用户?")){
				window.location.href="${ctx}/backstage/instmanager/backstageInstUser!deleteInstUser?index="+id;
			}
		}
		
		function addAccount(){
			//window.location.href="${ctx}/backstage/instmanager/backstageInstUser!instUserToAdd";
			$("#form1").submit();
		}
		
		function backAccount(){
			window.location.href="${ctx}/backstage/instmanager/backstageInstUser!instManagerIndexUser";
		}

	</script>
	<form id="form1" action="/backstage/instmanager/backstageInstUser!instUserToAdd" method="post">
	<input type="hidden" name="iinst" value="<s:property value="%{inst.iinst}"/>">
	</form>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>
