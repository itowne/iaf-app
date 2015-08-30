<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<html>
<head>
	<n:ui includes="form" />
</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;角色管理&nbsp;&gt;&gt;&nbsp;新增角色</p>
			</div>
			<div id="content">
				<h3 class="title"><span>新增角色详细信息</span></h3>
				<div class="results report">
				<s:form id="roleForm" action="backstageRole" namespace="/backstage/backstagerole">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label" >角色名称：</td>
							<td class="data"><s:textfield id="role_Name" name="roleName" maxLength="30" cssClass="validate[required,minSize[2]]"/><font color=red>*</font>
							</td>
						</tr>
						<!-- 
						<tr>
							<td class="label">角色描述：</td>
							<td class="data"><s:textfield id="role_Desc" name="backstagerole.roleDesc"  maxLength="30" cssClass="validate[required,minSize[2]]"/><font color=red>*</font>
							</td>
						</tr>
						 -->
						<tr>
							<td class="label">菜单与权限设置：</td>
							<td class="label">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="SHGL_MENU_ALL" value="SHGL_MENU_ALL" />商户管理[菜单]
							</td>
							<td class="data" id="SHGL_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="SHGL_SHZLGL_MENU" value="SHGL_SHZLGL_MENU" />商户资料管理
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="SHGL_XYBGGL_MENU" value="SHGL_XYBGGL_MENU" />经营数据报告管理
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="SHGL_XYBGSS_MENU" value="SHGL_XYBGSS_MENU" />经营数据报告申诉
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="SHGL_BDJSH_MENU" value="SHGL_BDJSH_MENU" />被冻结商户
							</td>
						</tr>
						<tr id="SHZLGL_AUTH_TR">
							<td class="label">商户资料管理[权限]<br>
							<input type="checkbox" id="SHZLGL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="SHZLGL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_SHZLGL_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_SHZLGL_XZ" />新增 
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_SHZLGL_XG" />修改
								<!--  &nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_SHZLGL_SC" />删除-->
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_SHZLGL_QY" />启用
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_SHZLGL_JY" />禁用
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_SHZLGL_XCDC" />现场调查
							</td>
						</tr>
						<tr id="XYBGGL_AUTH_TR">
							<td class="label">经营数据报告管理[权限]<BR/>
							<input type="checkbox" id="XYBGGL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="XYBGGL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_XYBGGL_CX" />查询
							</td>
						</tr>
						<tr id="XYBGSS_AUTH_TR">
							<td class="label">经营数据报告申诉[权限]<BR/>
							<input type="checkbox" id="XYBGSS_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="XYBGSS_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_XYBGSS_CX" />查询
							</td>
						</tr>
						<tr id="BDJSH_AUTH_TR">
							<td class="label">被冻结商户[权限]<BR/>
							<input type="checkbox" id="BDJSH_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="BDJSH_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="SHGL_BDJSH_CX" />查询
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="JGGL_MENU_ALL" value="JGGL_MENU_ALL" />机构管理[菜单]
							</td>
							<td class="data" id="JGGL_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="JGGL_JGSQ_MENU" value="JGGL_JGSQ_MENU" />机构申请
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="JGGL_JGGL_MENU" value="JGGL_JGGL_MENU" />机构管理
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="JGGL_JGCZY_MENU" value="JGGL_JGCZY_MENU" />机构操作员
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="JGGL_JGJS_MENU" value="JGGL_JGJS_MENU" />机构角色
							</td>
						</tr>
						<tr id="JGSQ_AUTH_TR">
							<td class="label">机构申请[权限]<BR/>
							<input type="checkbox" id="JGSQ_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="JGSQ_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGSQ_TG" />通过
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGSQ_TH" />退回
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGSQ_CX" />查询
							</td>
						</tr>
						<tr id="JGGL_AUTH_TR">
							<td class="label">机构管理[权限]<BR/>
							<input type="checkbox" id="JGGL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="JGGL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGGL_XG" />修改
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGGL_JY" />禁用
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGGL_XZ" />新增
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGGL_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGGL_QY" />启用
							</td>
						</tr>
						<tr id="JGCZY_AUTH_TR">
							<td class="label">机构操作员[权限]<BR/>
							<input type="checkbox" id="JGCZY_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="JGCZY_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGCZY_XG" />修改
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGCZY_JY" />禁用
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGCZY_XZ" />新增
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGCZY_CX" />查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGCZY_SC" />删除
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGCZY_QY" />启用
							</td>
						</tr>
						<tr id="JGJS_AUTH_TR">
							<td class="label">机构角色[权限]<BR/>
							<input type="checkbox" id="JGJS_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="JGJS_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGJS_XG" />修改
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGJS_JY" />禁用
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGJS_XZ" />新增
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGJS_SC" />刪除
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JGGL_JGJS_CX" />查询
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="HDCP_MENU_ALL" value="HDCP_MENU_ALL" />贷款产品[菜单]
							</td>
							<td class="data">
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr id="HDCP_AUTH_TR">
							<td class="label">贷款产品[权限]<BR/>
							<input type="checkbox" id="HDCP_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="HDCP_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="HDCP_XG" />修改
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="HDCP_XZ" />新增
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="HDCP_SC" />刪除
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="HDCP_SJ" />上架
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="HDCP_XJ" />下架
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="FKDZ_MENU_ALL" value="FKDZ_MENU_ALL" />放款对账[菜单]
							</td>
							<td class="data">
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr id="FKDZ_AUTH_TR">
							<td class="label">放款对账[权限]<BR/>
							<input type="checkbox" id="FKDZ_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="FKDZ_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="FKDZ_XZ" />下载
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="FKDZ_YDD" />已到达
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="FKDZ_TH" />退回
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="HKDZ_MENU_ALL" value="HKDZ_MENU_ALL" />还款对账[菜单]
							</td>
							<td class="data">
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr id="HKDZ_AUTH_TR">
							<td class="label">还款对账[权限]<BR/>
							<input type="checkbox" id="HKDZ_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="HKDZ_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="HKDZ_XZ" />下载
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="HKDZ_YDD" />已到达
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="HKDZ_TH" />退回
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="DJGL_MENU_ALL" value="DJGL_MENU_ALL" />冻结管理[菜单]
							</td>
							<td class="data">
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr id="DJGL_AUTH_TR">
							<td class="label">冻结管理[权限]<BR/>
							<input type="checkbox" id="DJGL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="DJGL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DJGL_DJ" />冻结
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DJGL_JJ" />拒绝
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DJGL_SHYHK"/>商户已还款
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DJGL_SHWHK"/>商户未还款
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="DJGL_XZ"/>下载
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr id="">
							<td class="label"><input type="checkbox" id="JDDDGL" type="checkbox" name="auth" value="JDDDGL"/>借贷订单管理[菜单]<BR/>
							<td class="data" id="JDDDGL_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="JDDD_MENU_ALL" value="JDDD_MENU_ALL" />借款订单查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="FKJL_MENU_ALL" value="FKJL_MENU_ALL" />放款记录查询
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="HKJL_MENU_ALL" value="HKJL_MENU_ALL" />还款记录查询
							</td>
						</tr>
						<tr>
							<td class="label">借贷订单[权限]<BR/>
							<input type="checkbox" id="JDDD_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="JDDD_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JDDD_YB" />隐蔽
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JDDD_XQ" />详情
							</td>
						</tr>
						<tr>
							<td class="label">还款记录[权限]<BR/>
							<input type="checkbox" id="HKJL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="HKJL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="HKJL_XQ" />详情
							</td>
						</tr>
						<tr>
							<td class="label">放款记录[权限]<BR/>
							<input type="checkbox" id="FKJL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="FKJL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="FKJL_XQ" />详情
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="GGGL_MENU_ALL" value="GGGL_MENU_ALL" />公告管理[菜单]
							</td>
							<td class="data">
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr id="GGGL_AUTH_TR">
							<td class="label">公告管理[权限]<BR/>
							<input type="checkbox" id="GGGL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="GGGL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="GGGL_XZ" />新增
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="GGGL_XG" />修改
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="GGGL_SC" />删除
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="XTCSSZ_MENU_ALL" value="XTCSSZ_MENU_ALL" />系统参数设置[菜单]
							</td>
							<td class="data" id="XTCSSZ_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_SHJBXX" />商户信息FTP设置
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_JYJL" />交易记录FTP设置
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_AZJL" />装机撤机记录FTP设置
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_XJJL" />巡检记录FTP设置
								<br/>
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_JZGZFWG" />支付网关设置
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_DXWG" />短信网关设置
								<!--  &nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_ZTMY" />跳转密钥-->
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_JZGMMJY" />金掌柜用户校验设置
								<br/>
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_FXCS" />风险及交易费率设置
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_SXRQSZ" />借贷业务期限设置
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="XTCSSZ_TPSCSZ" />用户图片上传设置
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="XTWH_MENU_ALL" value="XTWH_MENU_ALL" />系统维护[菜单]
							</td>
							<td class="data">
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td class="data" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td class="label"><input type="checkbox" name="auth" id="XTGL_MENU_ALL" value="XTGL_MENU_ALL" />系统管理[菜单]
							</td>
							<td class="data" id="XTGL_MENU">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="XTGL_WDZHXX_MENU" value="XTGL_WDZHXX_MENU" />我的帐号信息
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="XTGL_CZZHGL_MENU" value="XTGL_CZZHGL_MENU" />操作帐号管理
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="XTGL_JSGL_MENU" value="XTGL_JSGL_MENU" />角色管理
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" id="XTGL_CZRZ_MENU" value="XTGL_CZRZ_MENU" />操作日志
							</td>
						</tr>
						<tr id="WDZHXX_AUTH_TR">
							<td class="label">我的帐号信息[权限]<BR/>
							<input type="checkbox" id="WDZHXX_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="WDZHXX_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="WDZHXX_BC" />保存
							</td>
						</tr>
						<tr id="CZZHGL_AUTH_TR">
							<td class="label">操作帐号管理[权限]<BR/>
							<input type="checkbox" id="CZZHGL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="CZZHGL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="CZZHGL_XZ" />新增
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="CZZHGL_XG" />修改
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="CZZHGL_TY" />停用
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="CZZHGL_QY" />启用
							</td>
						</tr>
						<tr id="JSGL_AUTH_TR">
							<td class="label">角色管理[权限]<BR/>
							<input type="checkbox" id="JSGL_AUTH_SELECT_ALL"/>全选</td>
							<td class="data" id="JSGL_AUTH_ALL">
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JSGL_XZ" />新增
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JSGL_XG" />修改
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JSGL_TY" />停用
								&nbsp;&nbsp;&nbsp;<input type="checkbox" name="auth" value="JSGL_QY" />启用
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
						<s:submit cssClass="dark-btn" value="保存" method="saveRole" onclick="return doSubmit();"/>
						<input type="button" class="dark-btn"  value="返回" onclick="cancel();"/>
					</div>
					</s:form>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$('#checkAll').click(function(){
			if($("#checkAll").attr("checked")=="checked"){
				$(":checkbox").attr("checked","checked");
				$(":checkbox").removeAttr("disabled");
			}else{
				$(":checkbox").removeAttr("checked");
				$("#SHZLGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#XYBGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#XYBGSS_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#BDJSH_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JGSQ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JGCZY_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JGJS_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#HDCP_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#FKDZ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#HKDZ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#DJGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JDDD_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#HKJL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#FKJL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#GGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#WDZHXX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#CZZHGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JSGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			}
		});
		
		//menu
		$("#JDDDGL_MENU input[name='auth']").click(function (){
				if($("#JDDDGL_MENU input[name='auth']:checked").length>0){
					$('#JDDDGL').attr("checked","checked");
				}else{
					$('#JDDDGL').removeAttr("checked");
				}
			});
		//**===================
		$("#SHGL_MENU input[name='auth']").click(function (){
				if($("#SHGL_MENU input[name='auth']:checked").length>0){
					$('#SHGL_MENU_ALL').attr("checked","checked");
				}else{
					$('#SHGL_MENU_ALL').removeAttr("checked");
				}
			});
		
		$("#JGGL_MENU input[name='auth']").click(function (){
			if($("#JGGL_MENU input[name='auth']:checked").length>0){
				$('#JGGL_MENU_ALL').attr("checked","checked");
			}else{
				$('#JGGL_MENU_ALL').removeAttr("checked");
			}
		});
		$("#XTCSSZ_MENU input[name='auth']").click(function (){
			if($("#XTCSSZ_MENU input[name='auth']:checked").length>0){
				$('#XTCSSZ_MENU_ALL').attr("checked","checked");
			}else{
				$('#XTCSSZ_MENU_ALL').removeAttr("checked");
			}
		});
		$("#XTCSSZ_MENU input[name='auth']").click(function (){
			if($("#XTCSSZ_MENU input[name='auth']:checked").length>0){
				$('#XTCSSZ_MENU_ALL').attr("checked","checked");
			}else{
				$('#XTCSSZ_MENU_ALL').removeAttr("checked");
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
		if($("#SHGL_SHZLGL_MENU").attr("checked")==undefined){
			$("#SHZLGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#SHZLGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#SHZLGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#SHGL_SHZLGL_MENU").click(function (){
			if($("#SHGL_SHZLGL_MENU").attr("checked")==undefined){
				$("#SHZLGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#SHZLGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#SHZLGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#SHGL_XYBGGL_MENU").attr("checked")==undefined){
			$("#XYBGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#XYBGGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#XYBGGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#SHGL_XYBGGL_MENU").click(function (){
			if($("#SHGL_XYBGGL_MENU").attr("checked")==undefined){
				$("#XYBGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#XYBGGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#XYBGGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#SHGL_XYBGSS_MENU").attr("checked")==undefined){
			$("#XYBGSS_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#XYBGSS_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#XYBGSS_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#SHGL_XYBGSS_MENU").click(function (){
			if($("#SHGL_XYBGSS_MENU").attr("checked")==undefined){
				$("#XYBGSS_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#XYBGSS_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#XYBGSS_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#SHGL_BDJSH_MENU").attr("checked")==undefined){
			$("#BDJSH_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#BDJSH_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#BDJSH_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#SHGL_BDJSH_MENU").click(function (){
			if($("#SHGL_BDJSH_MENU").attr("checked")==undefined){
				$("#BDJSH_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#BDJSH_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#BDJSH_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#JGGL_JGSQ_MENU").attr("checked")==undefined){
			$("#JGSQ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JGSQ_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#JGSQ_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#JGGL_JGSQ_MENU").click(function (){
			if($("#JGGL_JGSQ_MENU").attr("checked")==undefined){
				$("#JGSQ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JGSQ_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#JGSQ_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#JGGL_JGGL_MENU").attr("checked")==undefined){
			$("#JGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JGGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#JGGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#JGGL_JGGL_MENU").click(function (){
			if($("#JGGL_JGGL_MENU").attr("checked")==undefined){
				$("#JGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JGGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#JGGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#JGGL_JGCZY_MENU").attr("checked")==undefined){
			$("#JGCZY_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JGCZY_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#JGCZY_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#JGGL_JGCZY_MENU").click(function (){
			if($("#JGGL_JGCZY_MENU").attr("checked")==undefined){
				$("#JGCZY_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JGCZY_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#JGCZY_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#JGGL_JGJS_MENU").attr("checked")==undefined){
			$("#JGJS_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JGJS_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#JGJS_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#JGGL_JGJS_MENU").click(function (){
			if($("#JGGL_JGJS_MENU").attr("checked")==undefined){
				$("#JGJS_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JGJS_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#JGJS_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#HDCP_MENU_ALL").attr("checked")==undefined){
			$("#HDCP_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#HDCP_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#HDCP_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#HDCP_MENU_ALL").click(function (){
			if($("#HDCP_MENU_ALL").attr("checked")==undefined){
				$("#HDCP_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#HDCP_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#HDCP_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#FKDZ_MENU_ALL").attr("checked")==undefined){
			$("#FKDZ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#FKDZ_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#FKDZ_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#FKDZ_MENU_ALL").click(function (){
			if($("#FKDZ_MENU_ALL").attr("checked")==undefined){
				$("#FKDZ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#FKDZ_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#FKDZ_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#HKDZ_MENU_ALL").attr("checked")==undefined){
			$("#HKDZ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#HKDZ_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#HKDZ_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#HKDZ_MENU_ALL").click(function (){
			if($("#HKDZ_MENU_ALL").attr("checked")==undefined){
				$("#HKDZ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#HKDZ_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#HKDZ_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#DJGL_MENU_ALL").attr("checked")==undefined){
			$("#DJGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#DJGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#DJGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#DJGL_MENU_ALL").click(function (){
			if($("#DJGL_MENU_ALL").attr("checked")==undefined){
				$("#DJGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#DJGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#DJGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#JDDD_MENU_ALL").attr("checked")==undefined){
			$("#JDDD_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JDDD_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#JDDD_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#JDDD_MENU_ALL").click(function (){
			if($("#JDDD_MENU_ALL").attr("checked")==undefined){
				$("#JDDD_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JDDD_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#JDDD_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#HKJL_MENU_ALL").attr("checked")==undefined){
			$("#HKJL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#HKJL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#HKJL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#HKJL_MENU_ALL").click(function (){
			if($("#HKJL_MENU_ALL").attr("checked")==undefined){
				$("#HKJL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#HKJL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#HKJL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#FKJL_MENU_ALL").attr("checked")==undefined){
			$("#FKJL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#FKJL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#FKJL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#FKJL_MENU_ALL").click(function (){
			if($("#FKJL_MENU_ALL").attr("checked")==undefined){
				$("#FKJL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#FKJL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#FKJL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#GGGL_MENU_ALL").attr("checked")==undefined){
			$("#GGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#GGGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#GGGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#GGGL_MENU_ALL").click(function (){
			if($("#GGGL_MENU_ALL").attr("checked")==undefined){
				$("#GGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#GGGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#GGGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#XTCSSZ_MENU_ALL").attr("checked")==undefined){
			$("#XTCSSZ_MENU input[type='checkbox']").attr("disabled","disabled");
			$("#XTCSSZ_MENU input[type='checkbox']").removeAttr("checked");
		}else{
			$("#XTCSSZ_MENU input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#XTCSSZ_MENU_ALL").click(function (){
			if($("#XTCSSZ_MENU_ALL").attr("checked")==undefined){
				$("#XTCSSZ_MENU input[type='checkbox']").attr("disabled","disabled");
				$("#XTCSSZ_MENU input[type='checkbox']").removeAttr("checked");
			}else{
				$("#XTCSSZ_MENU input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#XTGL_WDZHXX_MENU").attr("checked")==undefined){
			$("#WDZHXX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#WDZHXX_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#WDZHXX_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#XTGL_WDZHXX_MENU").click(function (){
			if($("#XTGL_WDZHXX_MENU").attr("checked")==undefined){
				$("#WDZHXX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#WDZHXX_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#WDZHXX_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#XTGL_CZZHGL_MENU").attr("checked")==undefined){
			$("#CZZHGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#CZZHGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#CZZHGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#XTGL_CZZHGL_MENU").click(function (){
			if($("#XTGL_CZZHGL_MENU").attr("checked")==undefined){
				$("#CZZHGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#CZZHGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#CZZHGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		
		if($("#XTGL_JSGL_MENU").attr("checked")==undefined){
			$("#JSGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JSGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}else{
			$("#JSGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
		}
		
		$("#XTGL_JSGL_MENU").click(function (){
			if($("#XTGL_JSGL_MENU").attr("checked")==undefined){
				$("#JSGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
				$("#JSGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			}else{
				$("#JSGL_AUTH_TR input[type='checkbox']").removeAttr("disabled");
			}
		})
		//all auth checked
		$("#SHZLGL_AUTH_SELECT_ALL").click(function (){
		if($("#SHZLGL_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#SHZLGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#SHZLGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#SHZLGL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#SHZLGL_AUTH_ALL input[type='checkbox']:checked").length==$("#SHZLGL_AUTH_ALL input[type='checkbox']").length){
			$("#SHZLGL_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#SHZLGL_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#XYBGGL_AUTH_SELECT_ALL").click(function (){
		if($("#XYBGGL_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#XYBGGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#XYBGGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#XYBGGL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#XYBGGL_AUTH_ALL input[type='checkbox']:checked").length==$("#XYBGGL_AUTH_ALL input[type='checkbox']").length){
			$("#XYBGGL_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#XYBGGL_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#XYBGSS_AUTH_SELECT_ALL").click(function (){
		if($("#XYBGSS_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#XYBGSS_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#XYBGSS_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#XYBGSS_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#XYBGSS_AUTH_ALL input[type='checkbox']:checked").length==$("#XYBGSS_AUTH_ALL input[type='checkbox']").length){
			$("#XYBGSS_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#XYBGSS_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#BDJSH_AUTH_SELECT_ALL").click(function (){
		if($("#BDJSH_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#BDJSH_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#BDJSH_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#BDJSH_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#BDJSH_AUTH_ALL input[type='checkbox']:checked").length==$("#BDJSH_AUTH_ALL input[type='checkbox']").length){
			$("#BDJSH_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#BDJSH_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#JGSQ_AUTH_SELECT_ALL").click(function (){
		if($("#JGSQ_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#JGSQ_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#JGSQ_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#JGSQ_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#JGSQ_AUTH_ALL input[type='checkbox']:checked").length==$("#JGSQ_AUTH_ALL input[type='checkbox']").length){
			$("#JGSQ_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#JGSQ_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#JGGL_AUTH_SELECT_ALL").click(function (){
		if($("#JGGL_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#JGGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#JGGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#JGGL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#JGGL_AUTH_ALL input[type='checkbox']:checked").length==$("#JGGL_AUTH_ALL input[type='checkbox']").length){
			$("#JGGL_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#JGGL_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#JGCZY_AUTH_SELECT_ALL").click(function (){
		if($("#JGCZY_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#JGCZY_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#JGCZY_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#JGCZY_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#JGCZY_AUTH_ALL input[type='checkbox']:checked").length==$("#JGCZY_AUTH_ALL input[type='checkbox']").length){
			$("#JGCZY_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#JGCZY_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#JGJS_AUTH_SELECT_ALL").click(function (){
		if($("#JGJS_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#JSGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#JSGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#JSGL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#JSGL_AUTH_ALL input[type='checkbox']:checked").length==$("#JSGL_AUTH_ALL input[type='checkbox']").length){
			$("#JGJS_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#JGJS_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#HDCP_AUTH_SELECT_ALL").click(function (){
		if($("#HDCP_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#HDCP_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#HDCP_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#HDCP_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#HDCP_AUTH_ALL input[type='checkbox']:checked").length==$("#HDCP_AUTH_ALL input[type='checkbox']").length){
			$("#HDCP_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#HDCP_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#FKDZ_AUTH_SELECT_ALL").click(function (){
		if($("#FKDZ_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#FKDZ_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#FKDZ_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#FKDZ_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#FKDZ_AUTH_ALL input[type='checkbox']:checked").length==$("#FKDZ_AUTH_ALL input[type='checkbox']").length){
			$("#FKDZ_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#FKDZ_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#HKDZ_AUTH_SELECT_ALL").click(function (){
		if($("#HKDZ_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#HKDZ_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#HKDZ_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#HKDZ_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#HKDZ_AUTH_ALL input[type='checkbox']:checked").length==$("#HKDZ_AUTH_ALL input[type='checkbox']").length){
			$("#HKDZ_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#HKDZ_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#DJGL_AUTH_SELECT_ALL").click(function (){
		if($("#DJGL_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#DJGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#DJGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#DJGL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#DJGL_AUTH_ALL input[type='checkbox']:checked").length==$("#DJGL_AUTH_ALL input[type='checkbox']").length){
			$("#DJGL_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#DJGL_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#JDDD_AUTH_SELECT_ALL").click(function (){
		if($("#JDDD_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#JDDD_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#JDDD_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#JDDD_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#JDDD_AUTH_ALL input[type='checkbox']:checked").length==$("#JDDD_AUTH_ALL input[type='checkbox']").length){
			$("#JDDD_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#JDDD_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#HKJL_AUTH_SELECT_ALL").click(function (){
		if($("#HKJL_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#HKJL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#HKJL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#HKJL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#HKJL_AUTH_ALL input[type='checkbox']:checked").length==$("#HKJL_AUTH_ALL input[type='checkbox']").length){
			$("#HKJL_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#HKJL_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#FKJL_AUTH_SELECT_ALL").click(function (){
		if($("#FKJL_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#FKJL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#FKJL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#FKJL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#FKJL_AUTH_ALL input[type='checkbox']:checked").length==$("#FKJL_AUTH_ALL input[type='checkbox']").length){
			$("#FKJL_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#FKJL_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#GGGL_AUTH_SELECT_ALL").click(function (){
		if($("#GGGL_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#GGGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#GGGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#GGGL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#GGGL_AUTH_ALL input[type='checkbox']:checked").length==$("#GGGL_AUTH_ALL input[type='checkbox']").length){
			$("#GGGL_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#GGGL_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#WDZHXX_AUTH_SELECT_ALL").click(function (){
		if($("#WDZHXX_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#WDZHXX_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#WDZHXX_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#WDZHXX_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#WDZHXX_AUTH_ALL input[type='checkbox']:checked").length==$("#WDZHXX_AUTH_ALL input[type='checkbox']").length){
			$("#WDZHXX_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#WDZHXX_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#CZZHGL_AUTH_SELECT_ALL").click(function (){
		if($("#CZZHGL_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#CZZHGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#CZZHGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#CZZHGL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#CZZHGL_AUTH_ALL input[type='checkbox']:checked").length==$("#CZZHGL_AUTH_ALL input[type='checkbox']").length){
			$("#CZZHGL_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#CZZHGL_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	$("#JSGL_AUTH_SELECT_ALL").click(function (){
		if($("#JSGL_AUTH_SELECT_ALL").attr("checked")=="checked"){
			$("#JSGL_AUTH_ALL input[type='checkbox']").attr("checked","checked");
		}else{
			$("#JSGL_AUTH_ALL input[type='checkbox']").removeAttr("checked");
		}
	});

	$("#JSGL_AUTH_ALL input[type='checkbox']").click(function (){
		if($("#JSGL_AUTH_ALL input[type='checkbox']:checked").length==$("#JSGL_AUTH_ALL input[type='checkbox']").length){
			$("#JSGL_AUTH_SELECT_ALL").attr("checked","checked");
		}else{
			$("#JSGL_AUTH_SELECT_ALL").removeAttr("checked");
		}
	});
	
	//menu all

	$("#SHGL_MENU_ALL").click(function (){
		if($("#SHGL_MENU_ALL").attr("chceked")!="checked"){
			$("#SHGL_MENU input[type='checkbox']").removeAttr("checked");
			$("#SHZLGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#SHZLGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			$("#XYBGGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			$("#XYBGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#XYBGSS_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#XYBGSS_AUTH_TR input[type='checkbox']").removeAttr("checked");
			$("#BDJSH_AUTH_TR input[type='checkbox']").removeAttr("checked");
			$("#BDJSH_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
		}
	})
	
	$("#JGGL_MENU_ALL").click(function (){
		if($("#JGGL_MENU_ALL").attr("chceked")!="checked"){
			$("#JGGL_MENU input[type='checkbox']").removeAttr("checked");
			$("#JGSQ_AUTH_TR input[type='checkbox']").removeAttr("checked");
			$("#JGSQ_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JGGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			$("#JGGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JGCZY_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JGCZY_AUTH_TR input[type='checkbox']").removeAttr("checked");
			$("#JGJS_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JGJS_AUTH_TR input[type='checkbox']").removeAttr("checked");
		}
	})
	
	$("#XTGL_MENU_ALL").click(function (){
		if($("#XTGL_MENU_ALL").attr("chceked")!="checked"){
			$("#XTGL_MENU input[type='checkbox']").removeAttr("checked");
			$("#WDZHXX_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#WDZHXX_AUTH_TR input[type='checkbox']").removeAttr("checked");
			$("#CZZHGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			$("#CZZHGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JSGL_AUTH_TR input[type='checkbox']").attr("disabled","disabled");
			$("#JSGL_AUTH_TR input[type='checkbox']").removeAttr("checked");
			
		}
	})
	
			$(function(){
				$("#roleForm").validationEngine("attach",{   
					promptPosition:"centerRight"
				});
				
				$(".dark-btn").hover(function(){
					$(this).addClass("dark-btn-hover");
				},function(){
					$(this).removeClass("dark-btn-hover");
				});
			});

			function doSubmit(){
				if (!$("#roleForm").validationEngine("validate")) {
					return false;
				}
			}
			function cancel(){
				document.roleForm.action="${ctx}/backstage/backstagerole/backstageRole!execute";	
	            document.roleForm.submit();
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>