<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html>
<html>
	<head>
	<n:ui includes="form" />
		<title>汇融易 - 机构管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/mp-workframe.css"/>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;机构角色&nbsp;&gt;&gt;&nbsp;修改角色</p>
			</div>
			<div id="content">
				<h3 class="title"><span>新增角色详细信息</span></h3>
				<div class="info" align="right">
					
				</div>
				<div class="results report">
				<s:form id="roleForm" action="/backstage/instmanager/instRole!instRoleUpdate" onsubmit="return checkform();">
					<table id="addRole" width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">角色名称：</td>
							<td class="data"><s:textfield id="roleName" name="roleAuth.roleName" maxLength="30" cssClass="validate[required,minSize[2]]"/><font color=red>*<s:property value="%{roleNameError}"/></font>
							<span id="msg"></span></td>
						</tr>
						<tr>
							<td class="label">菜单与权限设置：</td>
							<td class="label">&nbsp;</td>
						</tr>
						<tr id="">
							<td class="label">平台统计数据<br></td>
							<td class="data" id="">
								<input type="checkbox" name="auth" id="CKBB" value="CKBB">查看统计
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="WDSY_MENU_ALL" value="WDSY_MENU_ALL" />我的首页[菜单]
							</td>
							<td class="data">
								&nbsp;&nbsp;&nbsp;<!--  <input type="checkbox" id="WDSY_MENU"/>我的首页-->
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="SQSL_MENU_ALL" value="SQSL_MENU_ALL"/>借款受理[菜单]</td>
							<td class="data" id="SQSL_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="SQSL_CPSL_MENU" value="SQSL_CPSL_MENU" />受理申请
								<!--  &nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="SQSL_JTSL_MENU" value="SQSL_JTSL_MENU" />借贷需求-->
							</td>
						</tr>
						<tr id="CPSL_AUTH_TR">
							<td class="label">产品受理[权限]<br>
							<input type="checkbox" id="CPSL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="CPSL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SQSL_CPSL_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SQSL_CPSL_SL" />受理
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SQSL_CPSL_JJ" />拒绝
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SQSL_CPSL_CHEXIAO" />撤销
							</td>
						</tr>
						<!--  
						<tr id="JTSL_AUTH_TR">
							<td class="label">竞投受理[权限]<BR/>
							<input type="checkbox" id="JTSL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="JTSL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SQSL_JTSL_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SQSL_JTSL_SL" />受理
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SQSL_JTSL_JJ" />拒绝
							</td>
						</tr>
						-->
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="SPCL_MENU_ALL" value="SPCL_MENU_ALL"/>审批处理[菜单]
							</td>
							<td class="data" id="spcl">
								&nbsp;&nbsp;&nbsp;<!--  <input type="checkbox" id="SPCL_MENU" />审批处理-->
							</td>
						</tr>
						<tr id="SPCL_AUTH_TR">
							<td class="label">审批处理[权限]<br><input type="checkbox" id="SPCL_AUTH_SELECT_ALL">全选</td>
							<td class="data" id="SPCL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SPCL_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SPCL_SH" />审核
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SPCL_SCHKJH" />上传还款计划
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SPCL_SHBTG" />审核不通过
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SPCL_ZFDD" />作废订单
								<!--  &nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SPCL_XGJH" />修改计划-->
								<!--  &nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SPCL_ZFJH" />作废计划-->
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="FKGL_MENU_ALL" value="FKGL_MENU_ALL"/>放款管理[菜单]
							</td>
							<td class="data" id="fkgl">
								&nbsp;&nbsp;&nbsp;<!--  <input type="checkbox" id="FKGL_MENU" />放款管理-->
							</td>
						</tr>
						<tr id="FKGL_AUTH_TR">
							<td class="label">放款管理[权限]<br><input type="checkbox" id="FKGL_AUTH_SELECT_ALL">全选</td>
							<td class="data" id="FKGL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="FKGL_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="FKGL_FK" />放款
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="FKGL_ZFDD" />作废订单
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="DHGL_MENU_ALL" value="DHGL_MENU_ALL"/>贷后管理[菜单]
							</td>
							<td class="data" id="DHGL_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DHGL_ZDHKJH_MENU" />制定还款计划
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DHGL_FXKZ_MENU"/>风险控制
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DHGL_HKJH_MENU"/>还款管理
							</td>
						</tr>
						<tr id="DHGL_AUTH_TR">
							<td class="label">贷后管理[权限]<br><input type="checkbox" id="DHGL_AUTH_SELECT_ALL">全选</td>
							<td class="data" id="DHGL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DHGL_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DHGL_DJ" />冻结
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DHGL_JD" />解冻
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DHGL_FXCX" />风险查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DHGL_ZDHKJH_ZFDD" />制定还款计划-作废订单
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DHGL_HKGL_ZFDD" />还款管理-作废订单
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="WDCP_MENU_ALL" value="WDCP_MENU_ALL"/>我的产品[菜单]
							</td>
							<td class="data" id="">
								&nbsp;&nbsp;&nbsp;<!-- <input type="checkbox"  id="WDCP_MENU" />我的产品 -->
							</td>
						</tr>
						<tr id="WDCP_AUTH_TR">
							<td class="label">我的产品[权限]<br><input type="checkbox" id="WDCP_AUTH_SELECT_ALL">全选</td>
							<td class="data" id="WDCP_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDCP_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDCP_XG" />修改
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDCP_XZ" />新增
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDCP_SJ" />上架
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDCP_XJ" />下架
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDCP_SC" />删除
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="WDGZ_MENU_ALL" value="WDGZ_MENU_ALL"/>我的关注[菜单]</td>
							<td class="data" id="WDGZ_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="WDGZ_GZKH_MENU" value="WDGZ_GZKH_MENU" />关注的客户
								<!-- &nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="WDGZ_GZDD_MENU" value="WDGZ_GZDD_MENU" />关注的订单 -->
							</td>
						</tr>
						<tr id="GZKH_AUTH_TR">
							<td class="label">关注的客户[权限]<br><input type="checkbox" id="GZKH_AUTH_SELECT_ALL">全选</td>
							<td class="data" id="GZKH_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDGZ_GZKH_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDGZ_GZKH_TJ" />添加
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDGZ_GZKH_SC" />删除
							</td>
						</tr>
						<!--  
						<tr id="GZDD_AUTH_TR">
							<td class="label">关注的订单[权限]<br><input type="checkbox" id="GZDD_AUTH_SELECT_ALL">全选</td>
							<td class="data" id="GZDD_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDGZ_GZDD_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDGZ_GZDD_TJ" />添加
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDGZ_GZDD_SC" />删除
							</td>
						</tr>
						-->
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="WDZL_MENU_ALL" value="WDZL_MENU_ALL"/>我的资料[菜单]
							</td>
							<td class="data" id="WDZL_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth"  value="WDZL_ZLGL_MENU" />资料管理
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDZL_MMXG_MENU" />密码修改
							</td>
						</tr>
						<tr id="WDZL_AUTH_TR">
							<td class="label">我的资料[权限]<BR><input type="checkbox" id="WDZL_AUTH_SELECT_ALL">全选</td>
							<td class="data" id="WDZL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDZL_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDZL_XG" />修改
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="XTGL_MENU_ALL" value="XTGL_MENU_ALL"/>系统管理[菜单]
							</td>
							<td class="data" id="XTGL_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="XTGL_JSQX_MENU" value="XTGL_JSQX_MENU" />角色权限
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="XTGL_ZHGL_MENU" value="XTGL_ZHGL_MENU" />帐号管理
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="XTGL_XTRZ_MENU" value="XTGL_XTRZ_MENU" />系统日志
							</td>
						</tr>
						<tr id="JSQX_AUTH_TR">
							<td class="label">角色权限[权限]<br><input type="checkbox" id="JSQX_AUTH_SELECT_ALL">全选</td>
							<td class="data" id="JSQX_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_JSQX_XZJS" />新增角色
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_JSQX_XGJS" />修改角色
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_JSQX_ZTJS" />暂停角色
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_JSQX_SC" />删除角色
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_JSQX_QY" />启用角色
								<!-- &nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_JSQX_XGMM" />修改密码 -->
							</td>
						</tr>
						<tr id="ZHQX_AUTH_TR">
							<td class="label">帐号管理[权限]<br><input type="checkbox" id="ZHQX_AUTH_SELECT_ALL">全选</td>
							<td class="data" id="ZHQX_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_ZHQX_XGZH" />修改帐号
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_ZHQX_ZTZH" />暂停帐号
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_ZHQX_XZZH" />新增账号
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_ZHQX_XGMM" />修改密码
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_ZHQX_SCZH" />删除账号
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_ZHQX_QYZH" />启用账号
							</td>
						</tr>
						<tr>
						<td class="label">
							<div>
								<input type="checkbox" id="checkAll"/>全选
							</div>
						</td>
						<td class="label">&nbsp;</td>
						</tr>
					</table>
					<div class="uop">
						<input type="submit" class="dark-btn"  value="修&nbsp;&nbsp;改"/> 
						<s:if test="roleAuth.roleStat==1">
						<input type="button" class="dark-btn"  value="暂&nbsp;&nbsp;停" onclick="cancel();"/>
						</s:if>
						<s:else>
						<input type="button" class="dark-btn"  value="启&nbsp;&nbsp;用" onclick="recover();"/>
						</s:else>
						<input type="button" class="dark-btn"  value="取&nbsp;&nbsp;消" onclick="back();"/> 
					</div>
					</s:form>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		
		$("#XTGL_JSQX_MENU").click(function(){
			if($("#XTGL_JSQX_MENU").attr("checked")=="checked"){
				$("#XTGL_ZHGL_MENU").attr("checked",true);
				$("#ZHQX_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}else{
				$("#XTGL_ZHGL_MENU").attr("checked",false);
				if($("#XTGL_XTRZ_MENU").attr("checked")!="checked"){
					$("#XTGL_MENU_ALL").attr("checked",false);
				}
				$("#ZHQX_AUTH_TR input[type='checkbox']").attr("disabled",true);
			}
		});
		$("#XTGL_ZHGL_MENU").click(function(){
			if($("#XTGL_ZHGL_MENU").attr("checked")=="checked"){
				$("#XTGL_JSQX_MENU").attr("checked",true);
				$("#JSQX_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}else{
				$("#XTGL_JSQX_MENU").attr("checked",false);
				if($("#XTGL_XTRZ_MENU").attr("checked")!="checked"){
					$("#XTGL_MENU_ALL").attr("checked",false);
				}
				$("#JSQX_AUTH_TR input[type='checkbox']").attr("disabled",true);
			}
		});
		
		$('#checkAll').click(function(){
			if($("#checkAll").attr("checked")=="checked"){
				$(":checkbox").attr("checked","checked");
				$(":checkbox").removeAttr("disabled");
			}else{
				$(":checkbox").removeAttr("checked");
				$("#CPSL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JTSL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#SPCL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#FKGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#DHGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#WDCP_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#GZKH_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#GZDD_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#WDZL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JSQX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#ZHQX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			}
		});
		
		$(function() {
			$("#roleForm").validationEngine("attach",{   
				promptPosition:"centerRight"
			});
		});

		function checkform(){
			if($("#SQSL_MENU_ALL").attr("checked")=="checked"){
				if($("#SQSL_MENU input[type='checkbox']:checked").length==0){
					alert("借款受理请勾选子菜单");
					return false;
				}
			}
			if($("#DHGL_MENU_ALL").attr("checked")=="checked"){
				if($("#DHGL_MENU input[type='checkbox']:checked").length==0){
					alert("贷后管理请勾选子菜单");
					return false;
				}
			}
			
			if($("#WDGZ_MENU_ALL").attr("checked")=="checked"){
				if($("#WDGZ_MENU input[type='checkbox']:checked").length==0){
					alert("我的关注请勾选子菜单");
					return false;
				}
			}
			
			if($("#WDZL_MENU_ALL").attr("checked")=="checked"){
				if($("#WDZL_MENU input[type='checkbox']:checked").length==0){
					alert("我的资料请勾选子菜单");
					return false;
				}
			}
			
			if($("#XTGL_MENU_ALL").attr("checked")=="checked"){
				if($("#XTGL_MENU input[type='checkbox']:checked").length==0){
					alert("系统管理请勾选子菜单");
					return false;
				}
			}
			if (!$("#roleForm").validationEngine("validate")) {
				return false;
			}
			return true;
		}	
		 <c:forEach items="${authName}" var="authName">
			var an= '${authName}';
			$("input[value='"+an+"']").attr("checked","checked");
			

			if($("#SQSL_MENU input[type='checkbox']:checked").length>0){
				$("#SQSL_MENU_ALL").attr("checked","checked");
			}
			
			if($("#CPSL_AUTH_ALL input[type='checkbox']:checked").length==$("#CPSL_AUTH_ALL input[type='checkbox']").length){
				$("#CPSL_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			if($("#JTSL_AUTH_ALL input[type='checkbox']:checked").length==$("#JTSL_AUTH_ALL input[type='checkbox']").length){
				$("#JTSL_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			
			if($("#SPCL_AUTH_ALL input[type='checkbox']:checked").length==$("#SPCL_AUTH_ALL input[type='checkbox']").length){
				$("#SPCL_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			if($("#GZKH_AUTH_ALL input[type='checkbox']:checked").length==$("#GZKH_AUTH_ALL input[type='checkbox']").length){
				$("#GZKH_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			
			if($("#FKGL_AUTH_ALL input[type='checkbox']:checked").length==$("#FKGL_AUTH_ALL input[type='checkbox']").length){
				$("#FKGL_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			if($("#DHGL_MENU input[type='checkbox']:checked").length>0){
				$("#DHGL_MENU_ALL").attr("checked","checked");
			}
			
			if($("#DHGL_AUTH_ALL input[type='checkbox']:checked").length==$("#DHGL_AUTH_ALL input[type='checkbox']").length){
				$("#DHGL_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			
			if($("#WDCP_AUTH_ALL input[type='checkbox']:checked").length==$("#WDCP_AUTH_ALL input[type='checkbox']").length){
				$("#WDCP_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			if($("#WDGZ_MENU input[type='checkbox']:checked").length>0){
				$("#WDGZ_MENU_ALL").attr("checked","checked");
			}
			
			if($("#GZDD_AUTH_ALL input[type='checkbox']:checked").length==$("#GZDD_AUTH_ALL input[type='checkbox']").length){
				$("#GZDD_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			if($("#GZDD_AUTH_ALL input[type='checkbox']:checked").length==$("#GZDD_AUTH_ALL input[type='checkbox']").length){
				$("#GZDD_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			if($("#WDZL_MENU input[type='checkbox']:checked").length>0){
				$("#WDZL_MENU_ALL").attr("checked","checked");
			}
			
			if($("#WDZL_AUTH_ALL input[type='checkbox']:checked").length==$("#WDZL_AUTH_ALL input[type='checkbox']").length){
				$("#WDZL_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			if($("#XTGL_MENU input[type='checkbox']:checked").length>0){
				$("#XTGL_MENU_ALL").attr("checked","checked");
			}
			
			if($("#JSQX_AUTH_ALL input[type='checkbox']:checked").length==$("#JSQX_AUTH_ALL input[type='checkbox']").length){
				$("#JSQX_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			if($("#ZHQX_AUTH_ALL input[type='checkbox']:checked").length==$("#ZHQX_AUTH_ALL input[type='checkbox']").length){
				$("#ZHQX_AUTH_SELECT_ALL").attr("checked","checked");
			}
		 </c:forEach>
		//==========================================================================================================
		//menu
		
		$("#SQSL_MENU input[name='auth']").click(function (){
			if($("#SQSL_MENU input[name='auth']:checked").length>0){
				$('#SQSL_MENU_ALL').attr("checked","checked");
			}else{
				$('#SQSL_MENU_ALL').removeAttr("checked");
			}
		});
		
		
		$("#DHGL_MENU input[name='auth']").click(function (){
			if($("#DHGL_MENU input[name='auth']:checked").length>0){
				$('#DHGL_MENU_ALL').attr("checked","checked");
			}else{
				$('#DHGL_MENU_ALL').removeAttr("checked");
			}
		});
		
		
		$("#WDGZ_MENU input[name='auth']").click(function (){
			if($("#WDGZ_MENU input[name='auth']:checked").length>0){
				$('#WDGZ_MENU_ALL').attr("checked","checked");
			}else{
				$('#WDGZ_MENU_ALL').removeAttr("checked");
			}
		});
		
		$("#WDZL_MENU input[name='auth']").click(function (){
			if($("#WDZL_MENU input[name='auth']:checked").length>0){
				$('#WDZL_MENU_ALL').attr("checked","checked");
			}else{
				$('#WDZL_MENU_ALL').removeAttr("checked");
			}
		});
		
		$("#WDZL_MENU input[name='auth']").click(function (){
			if($("#WDZL_MENU input[name='auth']:checked").length>0){
				$('#WDZL_MENU_ALL').attr("checked","checked");
			}else{
				$('#WDZL_MENU_ALL').removeAttr("checked");
			}
		});
		
		$("#XTGL_MENU input[name='auth']").click(function (){
			if($("#XTGL_MENU input[name='auth']:checked").length>0){
				$('#XTGL_MENU_ALL').attr("checked","checked");
			}else{
				$('#XTGL_MENU_ALL').removeAttr("checked");
			}
		});
		
		//menu --- auth
		if($("#SQSL_CPSL_MENU").attr("checked")==undefined){
			$("#CPSL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#CPSL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#CPSL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#SQSL_CPSL_MENU").click(function (){
			if($("#SQSL_CPSL_MENU").attr("checked")==undefined){
				$("#CPSL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#CPSL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#CPSL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#SQSL_JTSL_MENU").attr("checked")==undefined){
				$("#JTSL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JTSL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#JTSL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		
		$("#SQSL_JTSL_MENU").click(function (){
			if($("#SQSL_JTSL_MENU").attr("checked")==undefined){
				$("#JTSL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JTSL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#JTSL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		});
		
		if($("#SPCL_MENU_ALL").attr("checked")==undefined){
			$("#SPCL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#SPCL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#SPCL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
	
	$("#SPCL_MENU_ALL").click(function (){
		if($("#SPCL_MENU_ALL").attr("checked")==undefined){
			$("#SPCL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#SPCL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#SPCL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
	});
	
	if($("#FKGL_MENU_ALL").attr("checked")==undefined){
		$("#FKGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#FKGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
	}else{
		$("#FKGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
	}

$("#FKGL_MENU_ALL").click(function (){
	if($("#FKGL_MENU_ALL").attr("checked")==undefined){
		$("#FKGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#FKGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
	}else{
		$("#FKGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
	}
});
	
		if($("#WDCP_MENU_ALL").attr("checked")==undefined){
			$("#WDCP_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#WDCP_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#WDCP_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}

		$("#WDCP_MENU_ALL").click(function (){
		if($("#WDCP_MENU_ALL").attr("checked")==undefined){
			$("#WDCP_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#WDCP_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#WDCP_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		});		
		
if($("#DHGL_MENU input[type='checkbox']:checked").length==0){
	$("#DHGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#DHGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#DHGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}

$("#DHGL_MENU input[type='checkbox']").click(function (){
	if($("#DHGL_MENU input[type='checkbox']:checked").length==0){
		$("#DHGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#DHGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
	}else{
		$("#DHGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
	}
});


if($("#WDGZ_GZKH_MENU").attr("checked")==undefined){
	$("#GZKH_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#GZKH_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#GZKH_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}

$("#WDGZ_GZKH_MENU").click(function (){
if($("#WDGZ_GZKH_MENU").attr("checked")==undefined){
	$("#GZKH_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#GZKH_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#GZKH_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}
});

if($("#WDGZ_GZDD_MENU").attr("checked")==undefined){
	$("#GZDD_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#GZDD_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#GZDD_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}

$("#WDGZ_GZDD_MENU").click(function (){
if($("#WDGZ_GZDD_MENU").attr("checked")==undefined){
	$("#GZDD_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#GZDD_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#GZDD_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}
});

if($("#WDZL_MENU input[type='checkbox']:checked").length==0){
	$("#WDZL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#WDZL_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#WDZL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}

$("#WDZL_MENU input[type='checkbox']").click(function (){
	if($("#WDZL_MENU input[type='checkbox']:checked").length==0){
		$("#WDZL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#WDZL_AUTH_TR input[type='checkbox']").removeAttr("checked");
	}else{
		$("#WDZL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
	}
});

if($("#XTGL_JSQX_MENU").attr("checked")==undefined){
	$("#JSQX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#JSQX_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#JSQX_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}

$("#XTGL_JSQX_MENU").click(function (){
if($("#XTGL_JSQX_MENU").attr("checked")==undefined){
	$("#JSQX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#JSQX_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#JSQX_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}
});

if($("#XTGL_ZHGL_MENU").attr("checked")==undefined){
	$("#ZHQX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#ZHQX_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#ZHQX_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}

$("#XTGL_ZHGL_MENU").click(function (){
if($("#XTGL_ZHGL_MENU").attr("checked")==undefined){
	$("#ZHQX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
	$("#ZHQX_AUTH_TR input[type='checkbox']").removeAttr("checked");
}else{
	$("#ZHQX_AUTH_TR input[type='checkbox']").removeAttr("disabled");
}
});
//all checked

$("#CPSL_AUTH_SELECT_ALL").click(function (){
	if($("#CPSL_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#CPSL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#CPSL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#CPSL_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#CPSL_AUTH_ALL input[type='checkbox']:checked").length==$("#CPSL_AUTH_ALL input[type='checkbox']").length){
		$("#CPSL_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#CPSL_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

$("#JTSL_AUTH_SELECT_ALL").click(function (){
	if($("#JTSL_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#JTSL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#JTSL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#JTSL_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#JTSL_AUTH_ALL input[type='checkbox']:checked").length==$("#JTSL_AUTH_ALL input[type='checkbox']").length){
		$("#JTSL_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#JTSL_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

$("#SPCL_AUTH_SELECT_ALL").click(function (){
	if($("#SPCL_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#SPCL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#SPCL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#SPCL_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#SPCL_AUTH_ALL input[type='checkbox']:checked").length==$("#SPCL_AUTH_ALL input[type='checkbox']").length){
		$("#SPCL_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#SPCL_AUTH_SELECT_ALL").removeAttr("checked");
	}
});


$("#FKGL_AUTH_SELECT_ALL").click(function (){
	if($("#FKGL_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#FKGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#FKGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#FKGL_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#FKGL_AUTH_ALL input[type='checkbox']:checked").length==$("#FKGL_AUTH_ALL input[type='checkbox']").length){
		$("#FKGL_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#FKGL_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

$("#DHGL_AUTH_SELECT_ALL").click(function (){
	if($("#DHGL_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#DHGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#DHGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#DHGL_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#DHGL_AUTH_ALL input[type='checkbox']:checked").length==$("#DHGL_AUTH_ALL input[type='checkbox']").length){
		$("#DHGL_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#DHGL_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

$("#WDCP_AUTH_SELECT_ALL").click(function (){
	if($("#WDCP_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#WDCP_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#WDCP_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#WDCP_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#WDCP_AUTH_ALL input[type='checkbox']:checked").length==$("#WDCP_AUTH_ALL input[type='checkbox']").length){
		$("#WDCP_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#WDCP_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

$("#GZKH_AUTH_SELECT_ALL").click(function (){
	if($("#GZKH_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#GZKH_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#GZKH_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#GZKH_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#GZKH_AUTH_ALL input[type='checkbox']:checked").length==$("#GZKH_AUTH_ALL input[type='checkbox']").length){
		$("#GZKH_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#GZKH_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

$("#GZDD_AUTH_SELECT_ALL").click(function (){
	if($("#GZDD_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#GZDD_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#GZDD_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#GZDD_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#GZDD_AUTH_ALL input[type='checkbox']:checked").length==$("#GZDD_AUTH_ALL input[type='checkbox']").length){
		$("#GZDD_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#GZDD_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

$("#WDZL_AUTH_SELECT_ALL").click(function (){
	if($("#WDZL_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#WDZL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#WDZL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#WDZL_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#WDZL_AUTH_ALL input[type='checkbox']:checked").length==$("#WDZL_AUTH_ALL input[type='checkbox']").length){
		$("#WDZL_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#WDZL_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

$("#JSQX_AUTH_SELECT_ALL").click(function (){
	if($("#JSQX_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#JSQX_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#JSQX_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#JSQX_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#JSQX_AUTH_ALL input[type='checkbox']:checked").length==$("#JSQX_AUTH_ALL input[type='checkbox']").length){
		$("#JSQX_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#JSQX_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

$("#ZHQX_AUTH_SELECT_ALL").click(function (){
	if($("#ZHQX_AUTH_SELECT_ALL").attr("checked")=="checked"){
		$("#ZHQX_AUTH_ALL input[type='checkbox']").attr("checked","checked");
	}else{
		$("#ZHQX_AUTH_ALL input[type='checkbox']").removeAttr("checked");
	}
});

$("#ZHQX_AUTH_ALL input[type='checkbox']").click(function (){
	if($("#ZHQX_AUTH_ALL input[type='checkbox']:checked").length==$("#ZHQX_AUTH_ALL input[type='checkbox']").length){
		$("#ZHQX_AUTH_SELECT_ALL").attr("checked","checked");
	}else{
		$("#ZHQX_AUTH_SELECT_ALL").removeAttr("checked");
	}
});

//menu all

$("#SQSL_MENU_ALL").click(function (){
	if($("#SQSL_MENU_ALL").attr("chceked")!="checked"){
		$("#SQSL_MENU input[type='checkbox']").removeAttr("checked");
		$("#CPSL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#CPSL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		$("#JTSL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#JTSL_AUTH_TR input[type='checkbox']").removeAttr("checked");
	}
})

$("#DHGL_MENU_ALL").click(function (){
	if($("#DHGL_MENU_ALL").attr("chceked")!="checked"){
		$("#DHGL_MENU input[type='checkbox']").removeAttr("checked");
		$("#DHGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#DHGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
	}
})


$("#WDGZ_MENU_ALL").click(function (){
	if($("#WDGZ_MENU_ALL").attr("chceked")!="checked"){
		$("#WDGZ_MENU input[type='checkbox']").removeAttr("checked");
		$("#GZKH_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#GZKH_AUTH_TR input[type='checkbox']").removeAttr("checked");
		$("#GZDD_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#GZDD_AUTH_TR input[type='checkbox']").removeAttr("checked");
	}
})

$("#WDZL_MENU_ALL").click(function (){
	if($("#WDZL_MENU_ALL").attr("chceked")!="checked"){
		$("#WDZL_MENU input[type='checkbox']").removeAttr("checked");
		$("#WDZL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#WDZL_AUTH_TR input[type='checkbox']").removeAttr("checked");
	}
})

$("#XTGL_MENU_ALL").click(function (){
	if($("#XTGL_MENU_ALL").attr("chceked")!="checked"){
		$("#XTGL_MENU input[type='checkbox']").removeAttr("checked");
		$("#JSQX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#JSQX_AUTH_TR input[type='checkbox']").removeAttr("checked");
		$("#ZHQX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		$("#ZHQX_AUTH_TR input[type='checkbox']").removeAttr("checked");
	}
})
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
			});
			
			function back(){
				window.location.href = "${ctx}/backstage/instmanager/instRole";
			}
			
			function cancel(){
				if(confirm("暂停角色会导致角色对应的账户不能使用,是否确认要暂停此角色?")){
					window.location.href = "${ctx}/backstage/instmanager/instRole!cancelRole";
				}
			}
			
			function recover(){
				if(confirm("是否启动此角色?")){
					window.location.href = "${ctx}/backstage/instmanager/instRole!recoverRole";
				}
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>