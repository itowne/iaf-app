<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html>

<html>
<head>
	<n:ui includes="form" />
</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：经营数据报告申诉&nbsp;&gt;&gt;&nbsp;详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>经营数据报告申诉详情</span></h3>
				<div class="results report">
					<table width="60%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label" style="width:300px;">商户名称:</td>
							<td class="data"><s:property value="merchInfoAppeal.merchName"/></td>
						</tr>
						<tr>
							<td class="label" style="width:300px;">商户操作员账号:</td>
							<td class="data"><s:property value="merchInfoAppeal.merchNo"/></td>
						</tr>
						<tr>
							<td class="label" style="width:300px;">商户所属省市:</td>
							<td class="data"><s:property value="merchInfoAppeal.provinceCity"/></td>
						</tr>
						<tr>
							<td class="label" style="width:300px;">申诉联系人:</td>
							<td class="data"><s:property value="merchInfoAppeal.appealMan"/></td>
						</tr>
						<tr>
							<td class="label" style="width:300px;">申诉联系电话:</td>
							<td class="data"><s:property value="merchInfoAppeal.appealPhone"/></td>
						</tr>
						<tr>
							<td class="label" style="width:300px;">申诉时间:</td>
							<td class="data"><s:date name="merchInfoAppeal.genTime" format="yyyy-MM-dd HH:mm:ss"/>
						</tr>
						<tr>
							<td class="label" style="width:300px;">处理情况:</td>
							<td class="data"><s:property value="merchInfoAppeal.appealState.desc"/></td>
						</tr>
						<tr>
							<td class="label" style="width:300px;">申诉主题:</td>
							<td class="data"><s:property value="merchInfoAppeal.appealReason"/></td>
						</tr>
						<tr>
							<td class="label" style="width:300px;">申诉内容:</td>
							<td class="data"><s:property value="merchInfoAppeal.appealContent"/>
						</tr>
						
					</table>
					<div class="uop">
						<s:if test='merchInfoAppeal.appealState.toString()=="APPLY"'>
							<input type="button" class="dark-btn"  value="受理" onclick="acceptAppeal();"/>
							<input type="button" class="dark-btn"  value="不受理" onclick="deny();"/>
						</s:if>
						<input type="button" class="dark-btn"  value="返回" onclick="back();"/> 
					</div>
					<div class="results report">
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function acceptAppeal(){
				if(confirm("您确定受理该商户的申诉申请?")){
					window.location.href="${ctx}/backstage/backMerchAppeal!acceptAppealApply";
				}
			}
			function deny(){
				if(confirm("您确定不受理该商户的申诉申请?")){
					window.location.href="${ctx}/backstage/backMerchAppeal!denyAppealApply";
				}
			}
			function back(){
				window.location.href = "${ctx}/backstage/backMerchAppeal";
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>