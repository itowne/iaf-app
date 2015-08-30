<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<jsp:include page="${ctx}/template/head.jsp" />
		<title>汇融易 - 机构管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/mp-workframe.css"/>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;角色权限&nbsp;&gt;&gt;&nbsp;修改角色</p>
			</div>
			<div id="content">
				<h3 class="title"><span>新增角色详细信息</span></h3>
				<div class="info" align="right">
						<!-- <input type="button" class="dark-btn" value="导入已有角色编辑" onclick=""/> -->
				</div>
				<div class="results report">
				<s:form id="roleForm" action="/inst/instRole!" onsubmit="return checkform();">
					<table id="addRole" width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">角色名称：</td>
							<td class="data"><s:textfield id="roleName" name="roleAuth.roleName" maxLength="30" readonly="true" onblur="check(this);"/>
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
								<!--  &nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="WDGZ_GZDD_MENU" value="WDGZ_GZDD_MENU" />关注的订单-->
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
								<!--&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTGL_JSQX_XGMM" />修改密码-->
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
						<!-- 
						<tr>
						<td class="label">
							<div>
								<input type="checkbox" id="checkAll"/>全选
							</div>
						</td>
						<td class="label">&nbsp;</td>
						</tr>
						 -->
					</table>
					<div class="uop">
						<!--  <input type="submit" class="dark-btn"  value="修&nbsp;&nbsp;改"/> -->
						<input type="button" class="dark-btn"  value="返&nbsp;&nbsp;回" onclick="back();"/> 
					</div>
					</s:form>
					<div class="results report">
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
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
			
			if($("#GZKH_AUTH_ALL input[type='checkbox']:checked").length==$("#GZKH_AUTH_ALL input[type='checkbox']").length){
				$("#GZKH_AUTH_SELECT_ALL").attr("checked","checked");
			}
			
			if($("#SPCL_AUTH_ALL input[type='checkbox']:checked").length==$("#SPCL_AUTH_ALL input[type='checkbox']").length){
				$("#SPCL_AUTH_SELECT_ALL").attr("checked","checked");
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
				$(":checkbox").attr("disabled","disabled");
			});
			
			function back(){
				window.location.href = "${ctx}/inst/instRole";
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>