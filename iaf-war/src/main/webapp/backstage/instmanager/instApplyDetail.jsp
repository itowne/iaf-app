<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<html>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;机构申请详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>机构申请详细信息</span></h3>
				<div class="results report">
				<s:form id="instMainForm" action="instApplyRequest" namespace="/backstage/instmanager">
					<table width="65%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label" style="width:200px">机构公司名称：</td>
							<td class="data"><s:property  value='instApplyRequest.instName'/></td>
							
						</tr>
						<tr>
							<td class="label">商户所属省市：</td>
							<td class="data"><s:property  value='instApplyRequest.addr'/></td>
						</tr>
						<tr>
							<td class="label">联系人：</td>
							<td class="data"><s:property  value='instApplyRequest.contactName'/></td>
						</tr>
						<tr>
							<td class="label">联系人性别：</td>
							<td class="data"><s:property  value='instApplyRequest.gender.desc'/></td>
						</tr>
						<tr>
							<td class="label">联系电话：</td>
							<td class="data"><s:property  value='instApplyRequest.mobilePhone'/></td>
						</tr>
						<tr>
							<td class="label">申请时间：</td>
							<td class="data"><fmt:formatDate value="${instApplyRequest.genTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<td class="label">处理状态：</td>
							<td class="data"><s:property  value='instApplyRequest.stat.desc'/></td>
						</tr>
						<tr>
							<td class="label">受理备注：</td>
							<td class="data"><s:property  value='instApplyRequest.memo'/></td>
						</tr>
					</table>
					<div class="uop">
						<s:if test='instApplyRequest.stat.toString()=="APPLY"'>
						<n:HcAuthButton cssClass="dark-btn" onclick="check()" value="受理" authCode="JGGL_JGSQ_TG"/>
						<n:HcAuthButton cssClass="dark-btn" onclick="cancel()" value="不受理" authCode="JGGL_JGSQ_TH"/>
						</s:if>
						<input type="button" class="dark-btn" value="返回" onclick="back()"/>
					</div>
					</s:form>
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
			});

			function check(){
				if(confirm("您确定要受理该机构的注册申请?")){
					window.location.href="${ctx}/backstage/instmanager/instApplyRequest!toInstApply";
				}
			}
			
			function cancel(){
				//window.location.href="${ctx}/backstage/instmanager/instApplyRequest!cancel";	
				window.location.href="${ctx}/backstage/instmanager/instApplyRequest!refuseInstApply";
			}
			
			function back(){
				window.location.href="${ctx}/backstage/instmanager/instApplyRequest";
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>